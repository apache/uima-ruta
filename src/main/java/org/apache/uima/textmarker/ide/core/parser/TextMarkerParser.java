// $ANTLR 3.4 D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g 2012-01-09 13:42:27

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

                if ( (LA1_0==ACTION||LA1_0==AutomataBlockString||(LA1_0 >= BOOLEANLIST && LA1_0 <= BooleanString)||LA1_0==CONDITION||LA1_0==DECLARE||LA1_0==DOUBLELIST||LA1_0==DoubleString||(LA1_0 >= FLOATLIST && LA1_0 <= FloatString)||LA1_0==INTLIST||(LA1_0 >= Identifier && LA1_0 <= IntString)||LA1_0==LPAREN||LA1_0==STRINGLIST||(LA1_0 >= StringLiteral && LA1_0 <= StringString)||(LA1_0 >= TYPELIST && LA1_0 <= TypeString)||(LA1_0 >= WORDLIST && LA1_0 <= WORDTABLE)) ) {
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:324:1: variableDeclaration returns [List<Statement> stmts = new ArrayList<Statement>()] : (type= IntString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= numberExpression )? SEMI |type= DoubleString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= numberExpression )? SEMI |type= FloatString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= numberExpression )? SEMI |type= StringString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= stringExpression )? SEMI |type= BooleanString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= booleanExpression )? SEMI |type= TypeString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= annotationType )? SEMI |type= WORDLIST id= Identifier ( ASSIGN_EQUAL list= wordListExpression )? SEMI |type= WORDTABLE id= Identifier ( ASSIGN_EQUAL table= wordTableExpression )? SEMI |type= BOOLEANLIST id= Identifier ( ASSIGN_EQUAL list= booleanListExpression )? SEMI |type= INTLIST id= Identifier ( ASSIGN_EQUAL list= numberListExpression )? SEMI |type= DOUBLELIST id= Identifier ( ASSIGN_EQUAL list= numberListExpression )? SEMI |type= FLOATLIST id= Identifier ( ASSIGN_EQUAL list= numberListExpression )? SEMI |type= STRINGLIST id= Identifier ( ASSIGN_EQUAL list= stringListExpression )? SEMI |type= TYPELIST id= Identifier ( ASSIGN_EQUAL list= typeListExpression )? SEMI |stmt= conditionDeclaration |stmt= actionDeclaration );
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:328:2: (type= IntString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= numberExpression )? SEMI |type= DoubleString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= numberExpression )? SEMI |type= FloatString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= numberExpression )? SEMI |type= StringString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= stringExpression )? SEMI |type= BooleanString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= booleanExpression )? SEMI |type= TypeString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= annotationType )? SEMI |type= WORDLIST id= Identifier ( ASSIGN_EQUAL list= wordListExpression )? SEMI |type= WORDTABLE id= Identifier ( ASSIGN_EQUAL table= wordTableExpression )? SEMI |type= BOOLEANLIST id= Identifier ( ASSIGN_EQUAL list= booleanListExpression )? SEMI |type= INTLIST id= Identifier ( ASSIGN_EQUAL list= numberListExpression )? SEMI |type= DOUBLELIST id= Identifier ( ASSIGN_EQUAL list= numberListExpression )? SEMI |type= FLOATLIST id= Identifier ( ASSIGN_EQUAL list= numberListExpression )? SEMI |type= STRINGLIST id= Identifier ( ASSIGN_EQUAL list= stringListExpression )? SEMI |type= TYPELIST id= Identifier ( ASSIGN_EQUAL list= typeListExpression )? SEMI |stmt= conditionDeclaration |stmt= actionDeclaration )
            int alt25=16;
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
            case CONDITION:
                {
                alt25=15;
                }
                break;
            case ACTION:
                {
                alt25=16;
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
                case 15 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:427:2: stmt= conditionDeclaration
                    {
                    pushFollow(FOLLOW_conditionDeclaration_in_variableDeclaration1200);
                    stmt=conditionDeclaration();

                    state._fsp--;
                    if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {stmts.add(stmt);}

                    }
                    break;
                case 16 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:429:2: stmt= actionDeclaration
                    {
                    pushFollow(FOLLOW_actionDeclaration_in_variableDeclaration1212);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:432:1: conditionDeclaration returns [Statement stmt = null] : declareToken= CONDITION id= Identifier ASSIGN_EQUAL LPAREN cons= conditions RPAREN SEMI ;
    public final Statement conditionDeclaration() throws RecognitionException {
        Statement stmt =  null;


        Token declareToken=null;
        Token id=null;
        List<TextMarkerCondition> cons =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:433:5: (declareToken= CONDITION id= Identifier ASSIGN_EQUAL LPAREN cons= conditions RPAREN SEMI )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:434:5: declareToken= CONDITION id= Identifier ASSIGN_EQUAL LPAREN cons= conditions RPAREN SEMI
            {
            declareToken=(Token)match(input,CONDITION,FOLLOW_CONDITION_in_conditionDeclaration1240); if (state.failed) return stmt;

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_conditionDeclaration1246); if (state.failed) return stmt;

            if ( state.backtracking==0 ) {addVariable(id.getText(), declareToken.getText());}

            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_conditionDeclaration1254); if (state.failed) return stmt;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionDeclaration1261); if (state.failed) return stmt;

            pushFollow(FOLLOW_conditions_in_conditionDeclaration1267);
            cons=conditions();

            state._fsp--;
            if (state.failed) return stmt;

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionDeclaration1269); if (state.failed) return stmt;

            match(input,SEMI,FOLLOW_SEMI_in_conditionDeclaration1271); if (state.failed) return stmt;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:441:1: actionDeclaration returns [Statement stmt = null] : declareToken= ACTION id= Identifier ASSIGN_EQUAL LPAREN a= actions RPAREN SEMI ;
    public final Statement actionDeclaration() throws RecognitionException {
        Statement stmt =  null;


        Token declareToken=null;
        Token id=null;
        List<TextMarkerAction> a =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:442:5: (declareToken= ACTION id= Identifier ASSIGN_EQUAL LPAREN a= actions RPAREN SEMI )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:443:5: declareToken= ACTION id= Identifier ASSIGN_EQUAL LPAREN a= actions RPAREN SEMI
            {
            declareToken=(Token)match(input,ACTION,FOLLOW_ACTION_in_actionDeclaration1307); if (state.failed) return stmt;

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_actionDeclaration1313); if (state.failed) return stmt;

            if ( state.backtracking==0 ) {addVariable(id.getText(), declareToken.getText());}

            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionDeclaration1321); if (state.failed) return stmt;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionDeclaration1327); if (state.failed) return stmt;

            pushFollow(FOLLOW_actions_in_actionDeclaration1333);
            a=actions();

            state._fsp--;
            if (state.failed) return stmt;

            match(input,RPAREN,FOLLOW_RPAREN_in_actionDeclaration1335); if (state.failed) return stmt;

            match(input,SEMI,FOLLOW_SEMI_in_actionDeclaration1337); if (state.failed) return stmt;

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
                    declareToken=(Token)match(input,DECLARE,FOLLOW_DECLARE_in_declaration1372); if (state.failed) return stmts;

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
                            pushFollow(FOLLOW_annotationType_in_declaration1378);
                            lazyParent=annotationType();

                            state._fsp--;
                            if (state.failed) return stmts;

                            }
                            break;

                    }


                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1388); if (state.failed) return stmts;

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
                    	    match(input,COMMA,FOLLOW_COMMA_in_declaration1400); if (state.failed) return stmts;

                    	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1410); if (state.failed) return stmts;

                    	    if ( state.backtracking==0 ) {addVariable(id.getText(), declareToken.getText());}

                    	    if ( state.backtracking==0 ) {addType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(),  lazyParent == null ? null : lazyParent.toString()); 
                    	    			declarations.add(StatementFactory.createAnnotationType(id,declareToken));}

                    	    }
                    	    break;

                    	default :
                    	    break loop27;
                        }
                    } while (true);


                    end=(Token)match(input,SEMI,FOLLOW_SEMI_in_declaration1429); if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {
                    		 stmt = StatementFactory.createDeclareDeclarationsStatement(declareToken, declarations, lazyParent);
                    		 stmts.add(stmt);
                    		 }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:472:2: declareToken= DECLARE type= annotationType id= Identifier ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI
                    {
                    declareToken=(Token)match(input,DECLARE,FOLLOW_DECLARE_in_declaration1442); if (state.failed) return stmts;

                    pushFollow(FOLLOW_annotationType_in_declaration1446);
                    type=annotationType();

                    state._fsp--;
                    if (state.failed) return stmts;

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1453); if (state.failed) return stmts;

                    if ( state.backtracking==0 ) {addVariable(id.getText(), declareToken.getText());}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:474:3: ( LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN )
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:474:4: LPAREN (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |obj2= StringString |obj3= DoubleString |obj6= FloatString |obj4= IntString |obj5= BooleanString ) fname= Identifier )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_declaration1460); if (state.failed) return stmts;

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
                            pushFollow(FOLLOW_annotationType_in_declaration1475);
                            obj1=annotationType();

                            state._fsp--;
                            if (state.failed) return stmts;

                            if ( state.backtracking==0 ) {featureTypes.add(obj1);}

                            }
                            break;
                        case 2 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:477:6: obj2= StringString
                            {
                            obj2=(Token)match(input,StringString,FOLLOW_StringString_in_declaration1488); if (state.failed) return stmts;

                            if ( state.backtracking==0 ) {featureTypes.add(obj2);}

                            }
                            break;
                        case 3 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:478:6: obj3= DoubleString
                            {
                            obj3=(Token)match(input,DoubleString,FOLLOW_DoubleString_in_declaration1501); if (state.failed) return stmts;

                            if ( state.backtracking==0 ) {featureTypes.add(obj3);}

                            }
                            break;
                        case 4 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:479:6: obj6= FloatString
                            {
                            obj6=(Token)match(input,FloatString,FOLLOW_FloatString_in_declaration1513); if (state.failed) return stmts;

                            if ( state.backtracking==0 ) {featureTypes.add(obj6);}

                            }
                            break;
                        case 5 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:480:6: obj4= IntString
                            {
                            obj4=(Token)match(input,IntString,FOLLOW_IntString_in_declaration1527); if (state.failed) return stmts;

                            if ( state.backtracking==0 ) {featureTypes.add(obj4);}

                            }
                            break;
                        case 6 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:481:6: obj5= BooleanString
                            {
                            obj5=(Token)match(input,BooleanString,FOLLOW_BooleanString_in_declaration1539); if (state.failed) return stmts;

                            if ( state.backtracking==0 ) {featureTypes.add(obj5);}

                            }
                            break;

                    }


                    fname=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1559); if (state.failed) return stmts;

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
                    	    match(input,COMMA,FOLLOW_COMMA_in_declaration1571); if (state.failed) return stmts;

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
                    	            pushFollow(FOLLOW_annotationType_in_declaration1586);
                    	            obj1=annotationType();

                    	            state._fsp--;
                    	            if (state.failed) return stmts;

                    	            if ( state.backtracking==0 ) {featureTypes.add(obj1);}

                    	            }
                    	            break;
                    	        case 2 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:489:6: obj2= StringString
                    	            {
                    	            obj2=(Token)match(input,StringString,FOLLOW_StringString_in_declaration1599); if (state.failed) return stmts;

                    	            if ( state.backtracking==0 ) {featureTypes.add(obj2);}

                    	            }
                    	            break;
                    	        case 3 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:490:6: obj3= DoubleString
                    	            {
                    	            obj3=(Token)match(input,DoubleString,FOLLOW_DoubleString_in_declaration1612); if (state.failed) return stmts;

                    	            if ( state.backtracking==0 ) {featureTypes.add(obj3);}

                    	            }
                    	            break;
                    	        case 4 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:491:6: obj6= FloatString
                    	            {
                    	            obj6=(Token)match(input,FloatString,FOLLOW_FloatString_in_declaration1624); if (state.failed) return stmts;

                    	            if ( state.backtracking==0 ) {featureTypes.add(obj6);}

                    	            }
                    	            break;
                    	        case 5 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:492:6: obj4= IntString
                    	            {
                    	            obj4=(Token)match(input,IntString,FOLLOW_IntString_in_declaration1638); if (state.failed) return stmts;

                    	            if ( state.backtracking==0 ) {featureTypes.add(obj4);}

                    	            }
                    	            break;
                    	        case 6 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:493:6: obj5= BooleanString
                    	            {
                    	            obj5=(Token)match(input,BooleanString,FOLLOW_BooleanString_in_declaration1650); if (state.failed) return stmts;

                    	            if ( state.backtracking==0 ) {featureTypes.add(obj5);}

                    	            }
                    	            break;

                    	    }


                    	    fname=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1669); if (state.failed) return stmts;

                    	    if ( state.backtracking==0 ) {featureNames.add(fname);}

                    	    }
                    	    break;

                    	default :
                    	    break loop30;
                        }
                    } while (true);


                    match(input,RPAREN,FOLLOW_RPAREN_in_declaration1677); if (state.failed) return stmts;

                    }


                    match(input,SEMI,FOLLOW_SEMI_in_declaration1680); if (state.failed) return stmts;

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
                    declareToken=(Token)match(input,BlockString,FOLLOW_BlockString_in_blockDeclaration1741); if (state.failed) return block;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:534:32: declareToken= AutomataBlockString
                    {
                    declareToken=(Token)match(input,AutomataBlockString,FOLLOW_AutomataBlockString_in_blockDeclaration1749); if (state.failed) return block;

                    }
                    break;

            }


            match(input,LPAREN,FOLLOW_LPAREN_in_blockDeclaration1753); if (state.failed) return block;

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_blockDeclaration1760); if (state.failed) return block;

            if ( state.backtracking==0 ) {addVariable(id.getText(), declareToken.getText());}

            if ( state.backtracking==0 ) {
            		block = ScriptFactory.createScriptBlock(id, declareToken, ((blockDeclaration_scope)blockDeclaration_stack.elementAt(level - 1)).env);
            		((blockDeclaration_scope)blockDeclaration_stack.peek()).env = block;
            	}

            match(input,RPAREN,FOLLOW_RPAREN_in_blockDeclaration1768); if (state.failed) return block;

            pushFollow(FOLLOW_ruleElementWithCA_in_blockDeclaration1775);
            re1=ruleElementWithCA();

            state._fsp--;
            if (state.failed) return block;

            if ( state.backtracking==0 ) {re = re1;}

            if ( state.backtracking==0 ) {ScriptFactory.finalizeScriptBlock(block, rc, re, body);}

            match(input,LCURLY,FOLLOW_LCURLY_in_blockDeclaration1783); if (state.failed) return block;

            pushFollow(FOLLOW_statements_in_blockDeclaration1789);
            body=statements();

            state._fsp--;
            if (state.failed) return block;

            rc=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_blockDeclaration1795); if (state.failed) return block;

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
            pushFollow(FOLLOW_typeExpression_in_ruleElementWithCA1825);
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
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementWithCA1831);
                    quantifier=quantifierPart();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {re = ScriptFactory.createRuleElement(idRef,quantifier,c,a, end);}

            match(input,LCURLY,FOLLOW_LCURLY_in_ruleElementWithCA1844); if (state.failed) return re;

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
                    pushFollow(FOLLOW_conditions_in_ruleElementWithCA1850);
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
                    match(input,THEN,FOLLOW_THEN_in_ruleElementWithCA1854); if (state.failed) return re;

                    pushFollow(FOLLOW_actions_in_ruleElementWithCA1860);
                    a=actions();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            end=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_ruleElementWithCA1868); if (state.failed) return re;

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
            pushFollow(FOLLOW_typeExpression_in_ruleElementWithoutCA1908);
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
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementWithoutCA1914);
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
            pushFollow(FOLLOW_ruleElements_in_simpleStatement1956);
            elements=ruleElements();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {stmt = ScriptFactory.createRule(elements);}

            match(input,SEMI,FOLLOW_SEMI_in_simpleStatement1965); if (state.failed) return stmt;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
            pushFollow(FOLLOW_ruleElement_in_ruleElements1986);
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
            	    pushFollow(FOLLOW_ruleElement_in_ruleElements1995);
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
            pushFollow(FOLLOW_ruleElementType_in_blockRuleElement2022);
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
                    pushFollow(FOLLOW_ruleElementType_in_ruleElement2046);
                    re1=ruleElementType();

                    state._fsp--;
                    if (state.failed) return re;

                    if ( state.backtracking==0 ) {re = re1;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:584:4: re2= ruleElementLiteral
                    {
                    pushFollow(FOLLOW_ruleElementLiteral_in_ruleElement2057);
                    re2=ruleElementLiteral();

                    state._fsp--;
                    if (state.failed) return re;

                    if ( state.backtracking==0 ) {re = re2;}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:585:4: re3= ruleElementComposed
                    {
                    pushFollow(FOLLOW_ruleElementComposed_in_ruleElement2068);
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
            match(input,LPAREN,FOLLOW_LPAREN_in_ruleElementComposed2092); if (state.failed) return re;

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
                    pushFollow(FOLLOW_ruleElementType_in_ruleElementComposed2110);
                    re1=ruleElementType();

                    state._fsp--;
                    if (state.failed) return re;

                    if ( state.backtracking==0 ) {disjunctive = true; res = new ArrayList<Expression>(); res.add(re1);}

                    match(input,VBAR,FOLLOW_VBAR_in_ruleElementComposed2116); if (state.failed) return re;

                    pushFollow(FOLLOW_ruleElementType_in_ruleElementComposed2122);
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
                    	    match(input,VBAR,FOLLOW_VBAR_in_ruleElementComposed2128); if (state.failed) return re;

                    	    pushFollow(FOLLOW_ruleElementType_in_ruleElementComposed2134);
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
                    pushFollow(FOLLOW_ruleElements_in_ruleElementComposed2151);
                    res=ruleElements();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_ruleElementComposed2157); if (state.failed) return re;

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
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementComposed2163);
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
                    match(input,LCURLY,FOLLOW_LCURLY_in_ruleElementComposed2167); if (state.failed) return re;

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
                            pushFollow(FOLLOW_conditions_in_ruleElementComposed2173);
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
                            match(input,THEN,FOLLOW_THEN_in_ruleElementComposed2177); if (state.failed) return re;

                            pushFollow(FOLLOW_actions_in_ruleElementComposed2183);
                            a=actions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    match(input,RCURLY,FOLLOW_RCURLY_in_ruleElementComposed2187); if (state.failed) return re;

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
            pushFollow(FOLLOW_typeExpression_in_ruleElementType2221);
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
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementType2227);
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
                    match(input,LCURLY,FOLLOW_LCURLY_in_ruleElementType2240); if (state.failed) return re;

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
                            pushFollow(FOLLOW_conditions_in_ruleElementType2246);
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
                            match(input,THEN,FOLLOW_THEN_in_ruleElementType2250); if (state.failed) return re;

                            pushFollow(FOLLOW_actions_in_ruleElementType2256);
                            a=actions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    end=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_ruleElementType2264); if (state.failed) return re;

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
            pushFollow(FOLLOW_simpleStringExpression_in_ruleElementLiteral2317);
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
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementLiteral2323);
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
                    match(input,LCURLY,FOLLOW_LCURLY_in_ruleElementLiteral2336); if (state.failed) return re;

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
                            pushFollow(FOLLOW_conditions_in_ruleElementLiteral2342);
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
                            match(input,THEN,FOLLOW_THEN_in_ruleElementLiteral2346); if (state.failed) return re;

                            pushFollow(FOLLOW_actions_in_ruleElementLiteral2352);
                            a=actions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }


                    end=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_ruleElementLiteral2360); if (state.failed) return re;

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
            pushFollow(FOLLOW_condition_in_conditions2414);
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
            	    match(input,COMMA,FOLLOW_COMMA_in_conditions2419); if (state.failed) return conds;

            	    pushFollow(FOLLOW_condition_in_conditions2425);
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
            pushFollow(FOLLOW_action_in_actions2462);
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
            	    match(input,COMMA,FOLLOW_COMMA_in_actions2467); if (state.failed) return actions;

            	    pushFollow(FOLLOW_action_in_actions2473);
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
                    pushFollow(FOLLOW_booleanListExpression_in_listExpression2511);
                    e=booleanListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:639:4: ( intListExpression )=>e= intListExpression
                    {
                    pushFollow(FOLLOW_intListExpression_in_listExpression2527);
                    e=intListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e;}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:640:4: ( doubleListExpression )=>e= doubleListExpression
                    {
                    pushFollow(FOLLOW_doubleListExpression_in_listExpression2543);
                    e=doubleListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e;}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:641:4: ( floatListExpression )=>e= floatListExpression
                    {
                    pushFollow(FOLLOW_floatListExpression_in_listExpression2559);
                    e=floatListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e;}

                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:642:4: ( stringListExpression )=>e= stringListExpression
                    {
                    pushFollow(FOLLOW_stringListExpression_in_listExpression2575);
                    e=stringListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e;}

                    }
                    break;
                case 6 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:643:4: ( typeListExpression )=>e= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_listExpression2591);
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
            pushFollow(FOLLOW_simpleBooleanListExpression_in_booleanListExpression2615);
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
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleBooleanListExpression2636); if (state.failed) return expr;

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
                            pushFollow(FOLLOW_simpleBooleanExpression_in_simpleBooleanListExpression2643);
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
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleBooleanListExpression2648); if (state.failed) return expr;

                            	    pushFollow(FOLLOW_simpleBooleanExpression_in_simpleBooleanListExpression2654);
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


                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleBooleanListExpression2663); if (state.failed) return expr;

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

                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleBooleanListExpression2680); if (state.failed) return expr;

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
            pushFollow(FOLLOW_simpleIntListExpression_in_intListExpression2705);
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
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleIntListExpression2726); if (state.failed) return expr;

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
                            pushFollow(FOLLOW_simpleNumberExpression_in_simpleIntListExpression2733);
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
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleIntListExpression2738); if (state.failed) return expr;

                            	    pushFollow(FOLLOW_simpleNumberExpression_in_simpleIntListExpression2744);
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


                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleIntListExpression2753); if (state.failed) return expr;

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

                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleIntListExpression2770); if (state.failed) return expr;

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
                    pushFollow(FOLLOW_doubleListExpression_in_numberListExpression2804);
                    e1=doubleListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e1;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:687:2: (e1= floatListExpression )=>e1= floatListExpression
                    {
                    pushFollow(FOLLOW_floatListExpression_in_numberListExpression2825);
                    e1=floatListExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e1;}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:689:2: e2= intListExpression
                    {
                    pushFollow(FOLLOW_intListExpression_in_numberListExpression2837);
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
            pushFollow(FOLLOW_simpleDoubleListExpression_in_doubleListExpression2860);
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
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleDoubleListExpression2881); if (state.failed) return expr;

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
                            pushFollow(FOLLOW_simpleNumberExpression_in_simpleDoubleListExpression2888);
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
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleDoubleListExpression2893); if (state.failed) return expr;

                            	    pushFollow(FOLLOW_simpleNumberExpression_in_simpleDoubleListExpression2899);
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


                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleDoubleListExpression2908); if (state.failed) return expr;

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

                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleDoubleListExpression2925); if (state.failed) return expr;

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
            pushFollow(FOLLOW_simpleFloatListExpression_in_floatListExpression2949);
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
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleFloatListExpression2970); if (state.failed) return expr;

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
                            pushFollow(FOLLOW_simpleNumberExpression_in_simpleFloatListExpression2977);
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
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleFloatListExpression2982); if (state.failed) return expr;

                            	    pushFollow(FOLLOW_simpleNumberExpression_in_simpleFloatListExpression2988);
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


                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleFloatListExpression2997); if (state.failed) return expr;

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

                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleFloatListExpression3014); if (state.failed) return expr;

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
            pushFollow(FOLLOW_simpleStringListExpression_in_stringListExpression3039);
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
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleStringListExpression3060); if (state.failed) return expr;

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
                            pushFollow(FOLLOW_simpleStringExpression_in_simpleStringListExpression3067);
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
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleStringListExpression3072); if (state.failed) return expr;

                            	    pushFollow(FOLLOW_simpleStringExpression_in_simpleStringListExpression3078);
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


                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleStringListExpression3087); if (state.failed) return expr;

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

                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleStringListExpression3104); if (state.failed) return expr;

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
            pushFollow(FOLLOW_simpleTypeListExpression_in_typeListExpression3129);
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
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleTypeListExpression3150); if (state.failed) return expr;

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
                            pushFollow(FOLLOW_simpleTypeExpression_in_simpleTypeListExpression3157);
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
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleTypeListExpression3162); if (state.failed) return expr;

                            	    pushFollow(FOLLOW_simpleTypeExpression_in_simpleTypeListExpression3168);
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


                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleTypeListExpression3177); if (state.failed) return expr;

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

                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleTypeListExpression3194); if (state.failed) return expr;

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
                    pushFollow(FOLLOW_typeFunction_in_typeExpression3220);
                    tf=typeFunction();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = tf;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:765:4: st= simpleTypeExpression
                    {
                    pushFollow(FOLLOW_simpleTypeExpression_in_typeExpression3231);
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
            pushFollow(FOLLOW_externalTypeFunction_in_typeFunction3265);
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

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalTypeFunction3290); if (state.failed) return expr;

            match(input,LPAREN,FOLLOW_LPAREN_in_externalTypeFunction3294); if (state.failed) return expr;

            pushFollow(FOLLOW_varArgumentList_in_externalTypeFunction3301);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return expr;

            match(input,RPAREN,FOLLOW_RPAREN_in_externalTypeFunction3305); if (state.failed) return expr;

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
            pushFollow(FOLLOW_annotationType_in_simpleTypeExpression3328);
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

            v=(Token)match(input,Identifier,FOLLOW_Identifier_in_variable3352); if (state.failed) return var;

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

            v=(Token)match(input,Identifier,FOLLOW_Identifier_in_listVariable3379); if (state.failed) return var;

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
                    s=(Token)match(input,STAR,FOLLOW_STAR_in_quantifierPart3406); if (state.failed) return exprs;

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
                            q=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_quantifierPart3412); if (state.failed) return exprs;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {exprs.add(ExpressionFactory.createQuantifierLiteralExpression(s,q));}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:815:4: p= PLUS (q= QUESTION )?
                    {
                    p=(Token)match(input,PLUS,FOLLOW_PLUS_in_quantifierPart3424); if (state.failed) return exprs;

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
                            q=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_quantifierPart3430); if (state.failed) return exprs;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {exprs.add(ExpressionFactory.createQuantifierLiteralExpression(p,q));}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:816:4: q1= QUESTION (q= QUESTION )?
                    {
                    q1=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_quantifierPart3442); if (state.failed) return exprs;

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
                            q=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_quantifierPart3448); if (state.failed) return exprs;

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
                    match(input,LBRACK,FOLLOW_LBRACK_in_quantifierPart3457); if (state.failed) return exprs;

                    pushFollow(FOLLOW_numberExpression_in_quantifierPart3463);
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
                            match(input,COMMA,FOLLOW_COMMA_in_quantifierPart3466); if (state.failed) return exprs;

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
                                    pushFollow(FOLLOW_numberExpression_in_quantifierPart3473);
                                    max=numberExpression();

                                    state._fsp--;
                                    if (state.failed) return exprs;

                                    }
                                    break;

                            }


                            }
                            break;

                    }


                    match(input,RBRACK,FOLLOW_RBRACK_in_quantifierPart3479); if (state.failed) return exprs;

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
                            q=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_quantifierPart3485); if (state.failed) return exprs;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:825:1: condition returns [TextMarkerCondition result = null] : (c= conditionAnd |c= conditionContains |c= conditionContextCount |c= conditionCount |c= conditionCurrentCount |c= conditionInList |c= conditionIsInTag |c= conditionLast |c= conditionMofN |c= conditionNear |c= conditionNot |c= conditionOr |c= conditionPartOf |c= conditionPosition |c= conditionRegExp |c= conditionScore |c= conditionTotalCount |c= conditionVote |c= conditionIf |c= conditionFeature |c= conditionParse |c= conditionIs |c= conditionBefore |c= conditionAfter |c= conditionStartsWith |c= conditionEndsWith |c= conditionPartOfNeq |c= conditionSize | (c= externalCondition )=>c= externalCondition |c= variableCondition ) ;
    public final TextMarkerCondition condition() throws RecognitionException {
        TextMarkerCondition result =  null;


        TextMarkerCondition c =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:826:2: ( (c= conditionAnd |c= conditionContains |c= conditionContextCount |c= conditionCount |c= conditionCurrentCount |c= conditionInList |c= conditionIsInTag |c= conditionLast |c= conditionMofN |c= conditionNear |c= conditionNot |c= conditionOr |c= conditionPartOf |c= conditionPosition |c= conditionRegExp |c= conditionScore |c= conditionTotalCount |c= conditionVote |c= conditionIf |c= conditionFeature |c= conditionParse |c= conditionIs |c= conditionBefore |c= conditionAfter |c= conditionStartsWith |c= conditionEndsWith |c= conditionPartOfNeq |c= conditionSize | (c= externalCondition )=>c= externalCondition |c= variableCondition ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:827:2: (c= conditionAnd |c= conditionContains |c= conditionContextCount |c= conditionCount |c= conditionCurrentCount |c= conditionInList |c= conditionIsInTag |c= conditionLast |c= conditionMofN |c= conditionNear |c= conditionNot |c= conditionOr |c= conditionPartOf |c= conditionPosition |c= conditionRegExp |c= conditionScore |c= conditionTotalCount |c= conditionVote |c= conditionIf |c= conditionFeature |c= conditionParse |c= conditionIs |c= conditionBefore |c= conditionAfter |c= conditionStartsWith |c= conditionEndsWith |c= conditionPartOfNeq |c= conditionSize | (c= externalCondition )=>c= externalCondition |c= variableCondition )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:827:2: (c= conditionAnd |c= conditionContains |c= conditionContextCount |c= conditionCount |c= conditionCurrentCount |c= conditionInList |c= conditionIsInTag |c= conditionLast |c= conditionMofN |c= conditionNear |c= conditionNot |c= conditionOr |c= conditionPartOf |c= conditionPosition |c= conditionRegExp |c= conditionScore |c= conditionTotalCount |c= conditionVote |c= conditionIf |c= conditionFeature |c= conditionParse |c= conditionIs |c= conditionBefore |c= conditionAfter |c= conditionStartsWith |c= conditionEndsWith |c= conditionPartOfNeq |c= conditionSize | (c= externalCondition )=>c= externalCondition |c= variableCondition )
            int alt83=30;
            switch ( input.LA(1) ) {
            case AND:
                {
                alt83=1;
                }
                break;
            case CONTAINS:
                {
                alt83=2;
                }
                break;
            case CONTEXTCOUNT:
                {
                alt83=3;
                }
                break;
            case COUNT:
                {
                alt83=4;
                }
                break;
            case CURRENTCOUNT:
                {
                alt83=5;
                }
                break;
            case INLIST:
                {
                alt83=6;
                }
                break;
            case ISINTAG:
                {
                alt83=7;
                }
                break;
            case LAST:
                {
                alt83=8;
                }
                break;
            case MOFN:
                {
                alt83=9;
                }
                break;
            case NEAR:
                {
                alt83=10;
                }
                break;
            case MINUS:
            case NOT:
                {
                alt83=11;
                }
                break;
            case OR:
                {
                alt83=12;
                }
                break;
            case PARTOF:
                {
                alt83=13;
                }
                break;
            case POSITION:
                {
                alt83=14;
                }
                break;
            case REGEXP:
                {
                alt83=15;
                }
                break;
            case SCORE:
                {
                alt83=16;
                }
                break;
            case TOTALCOUNT:
                {
                alt83=17;
                }
                break;
            case VOTE:
                {
                alt83=18;
                }
                break;
            case IF:
                {
                alt83=19;
                }
                break;
            case FEATURE:
                {
                alt83=20;
                }
                break;
            case PARSE:
                {
                alt83=21;
                }
                break;
            case IS:
                {
                alt83=22;
                }
                break;
            case BEFORE:
                {
                alt83=23;
                }
                break;
            case AFTER:
                {
                alt83=24;
                }
                break;
            case STARTSWITH:
                {
                alt83=25;
                }
                break;
            case ENDSWITH:
                {
                alt83=26;
                }
                break;
            case PARTOFNEQ:
                {
                alt83=27;
                }
                break;
            case SIZE:
                {
                alt83=28;
                }
                break;
            case Identifier:
                {
                int LA83_29 = input.LA(2);

                if ( (LA83_29==LPAREN) && (synpred14_TextMarkerParser())) {
                    alt83=29;
                }
                else if ( (LA83_29==COMMA||LA83_29==RCURLY||LA83_29==RPAREN||LA83_29==THEN) ) {
                    alt83=30;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return result;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 83, 29, input);

                    throw nvae;

                }
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return result;}
                NoViableAltException nvae =
                    new NoViableAltException("", 83, 0, input);

                throw nvae;

            }

            switch (alt83) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:828:2: c= conditionAnd
                    {
                    pushFollow(FOLLOW_conditionAnd_in_condition3523);
                    c=conditionAnd();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:829:4: c= conditionContains
                    {
                    pushFollow(FOLLOW_conditionContains_in_condition3532);
                    c=conditionContains();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:830:4: c= conditionContextCount
                    {
                    pushFollow(FOLLOW_conditionContextCount_in_condition3541);
                    c=conditionContextCount();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:831:4: c= conditionCount
                    {
                    pushFollow(FOLLOW_conditionCount_in_condition3550);
                    c=conditionCount();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:832:4: c= conditionCurrentCount
                    {
                    pushFollow(FOLLOW_conditionCurrentCount_in_condition3559);
                    c=conditionCurrentCount();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 6 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:833:4: c= conditionInList
                    {
                    pushFollow(FOLLOW_conditionInList_in_condition3568);
                    c=conditionInList();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 7 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:834:4: c= conditionIsInTag
                    {
                    pushFollow(FOLLOW_conditionIsInTag_in_condition3577);
                    c=conditionIsInTag();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 8 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:835:4: c= conditionLast
                    {
                    pushFollow(FOLLOW_conditionLast_in_condition3586);
                    c=conditionLast();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 9 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:836:4: c= conditionMofN
                    {
                    pushFollow(FOLLOW_conditionMofN_in_condition3595);
                    c=conditionMofN();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 10 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:837:4: c= conditionNear
                    {
                    pushFollow(FOLLOW_conditionNear_in_condition3604);
                    c=conditionNear();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 11 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:838:4: c= conditionNot
                    {
                    pushFollow(FOLLOW_conditionNot_in_condition3613);
                    c=conditionNot();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 12 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:839:4: c= conditionOr
                    {
                    pushFollow(FOLLOW_conditionOr_in_condition3622);
                    c=conditionOr();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 13 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:840:4: c= conditionPartOf
                    {
                    pushFollow(FOLLOW_conditionPartOf_in_condition3631);
                    c=conditionPartOf();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 14 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:841:4: c= conditionPosition
                    {
                    pushFollow(FOLLOW_conditionPosition_in_condition3640);
                    c=conditionPosition();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 15 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:842:4: c= conditionRegExp
                    {
                    pushFollow(FOLLOW_conditionRegExp_in_condition3649);
                    c=conditionRegExp();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 16 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:843:4: c= conditionScore
                    {
                    pushFollow(FOLLOW_conditionScore_in_condition3658);
                    c=conditionScore();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 17 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:844:4: c= conditionTotalCount
                    {
                    pushFollow(FOLLOW_conditionTotalCount_in_condition3667);
                    c=conditionTotalCount();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 18 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:845:4: c= conditionVote
                    {
                    pushFollow(FOLLOW_conditionVote_in_condition3676);
                    c=conditionVote();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 19 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:846:4: c= conditionIf
                    {
                    pushFollow(FOLLOW_conditionIf_in_condition3685);
                    c=conditionIf();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 20 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:847:4: c= conditionFeature
                    {
                    pushFollow(FOLLOW_conditionFeature_in_condition3694);
                    c=conditionFeature();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 21 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:848:4: c= conditionParse
                    {
                    pushFollow(FOLLOW_conditionParse_in_condition3703);
                    c=conditionParse();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 22 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:849:4: c= conditionIs
                    {
                    pushFollow(FOLLOW_conditionIs_in_condition3712);
                    c=conditionIs();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 23 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:850:4: c= conditionBefore
                    {
                    pushFollow(FOLLOW_conditionBefore_in_condition3721);
                    c=conditionBefore();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 24 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:851:4: c= conditionAfter
                    {
                    pushFollow(FOLLOW_conditionAfter_in_condition3730);
                    c=conditionAfter();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 25 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:852:4: c= conditionStartsWith
                    {
                    pushFollow(FOLLOW_conditionStartsWith_in_condition3739);
                    c=conditionStartsWith();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 26 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:853:4: c= conditionEndsWith
                    {
                    pushFollow(FOLLOW_conditionEndsWith_in_condition3748);
                    c=conditionEndsWith();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 27 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:854:4: c= conditionPartOfNeq
                    {
                    pushFollow(FOLLOW_conditionPartOfNeq_in_condition3757);
                    c=conditionPartOfNeq();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 28 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:855:4: c= conditionSize
                    {
                    pushFollow(FOLLOW_conditionSize_in_condition3766);
                    c=conditionSize();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 29 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:856:4: (c= externalCondition )=>c= externalCondition
                    {
                    pushFollow(FOLLOW_externalCondition_in_condition3784);
                    c=externalCondition();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 30 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:857:4: c= variableCondition
                    {
                    pushFollow(FOLLOW_variableCondition_in_condition3793);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:862:1: variableCondition returns [TextMarkerCondition condition = null] : id= Identifier ;
    public final TextMarkerCondition variableCondition() throws RecognitionException {
        TextMarkerCondition condition =  null;


        Token id=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:863:2: (id= Identifier )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:866:2: id= Identifier
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableCondition3826); if (state.failed) return condition;

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

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalCondition3853); if (state.failed) return condition;

            match(input,LPAREN,FOLLOW_LPAREN_in_externalCondition3856); if (state.failed) return condition;

            if ( state.backtracking==0 ) {condition = external.createExternalCondition(id, args);}

            pushFollow(FOLLOW_varArgumentList_in_externalCondition3866);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return condition;

            if ( state.backtracking==0 ) {condition = external.createExternalCondition(id, args);}

            match(input,RPAREN,FOLLOW_RPAREN_in_externalCondition3873); if (state.failed) return condition;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
            name=(Token)match(input,AND,FOLLOW_AND_in_conditionAnd3901); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionAnd3903); if (state.failed) return cond;

            pushFollow(FOLLOW_conditions_in_conditionAnd3909);
            conds=conditions();

            state._fsp--;
            if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, conds);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionAnd3923); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
            name=(Token)match(input,CONTAINS,FOLLOW_CONTAINS_in_conditionContains3969); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionContains3971); if (state.failed) return cond;

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
                    pushFollow(FOLLOW_typeExpression_in_conditionContains3978);
                    type=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:893:54: list= listExpression COMMA a= argument
                    {
                    pushFollow(FOLLOW_listExpression_in_conditionContains3986);
                    list=listExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,COMMA,FOLLOW_COMMA_in_conditionContains3988); if (state.failed) return cond;

                    pushFollow(FOLLOW_argument_in_conditionContains3994);
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
                    match(input,COMMA,FOLLOW_COMMA_in_conditionContains4003); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionContains4009);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,COMMA,FOLLOW_COMMA_in_conditionContains4011); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionContains4017);
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
                            match(input,COMMA,FOLLOW_COMMA_in_conditionContains4020); if (state.failed) return cond;

                            pushFollow(FOLLOW_booleanExpression_in_conditionContains4026);
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

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionContains4043); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
            name=(Token)match(input,CONTEXTCOUNT,FOLLOW_CONTEXTCOUNT_in_conditionContextCount4079); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionContextCount4081); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionContextCount4087);
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
                    match(input,COMMA,FOLLOW_COMMA_in_conditionContextCount4101); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionContextCount4107);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,COMMA,FOLLOW_COMMA_in_conditionContextCount4109); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionContextCount4115);
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
                    match(input,COMMA,FOLLOW_COMMA_in_conditionContextCount4130); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberVariable_in_conditionContextCount4136);
                    var=numberVariable();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, typeExpr, min, max, var);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionContextCount4151); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
                    name=(Token)match(input,COUNT,FOLLOW_COUNT_in_conditionCount4202); if (state.failed) return cond;

                    match(input,LPAREN,FOLLOW_LPAREN_in_conditionCount4204); if (state.failed) return cond;

                    pushFollow(FOLLOW_listExpression_in_conditionCount4210);
                    type=listExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type, a, min, max, var);}

                    match(input,COMMA,FOLLOW_COMMA_in_conditionCount4225); if (state.failed) return cond;

                    pushFollow(FOLLOW_argument_in_conditionCount4231);
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
                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount4247); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberExpression_in_conditionCount4253);
                            min=numberExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount4255); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberExpression_in_conditionCount4261);
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
                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount4279); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberVariable_in_conditionCount4285);
                            var=numberVariable();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type, a, min, max, var);}

                    match(input,RPAREN,FOLLOW_RPAREN_in_conditionCount4301); if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:926:5: name= COUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
                    {
                    name=(Token)match(input,COUNT,FOLLOW_COUNT_in_conditionCount4317); if (state.failed) return cond;

                    match(input,LPAREN,FOLLOW_LPAREN_in_conditionCount4319); if (state.failed) return cond;

                    pushFollow(FOLLOW_typeExpression_in_conditionCount4325);
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
                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount4339); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberExpression_in_conditionCount4345);
                            min=numberExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount4347); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberExpression_in_conditionCount4353);
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
                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount4368); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberVariable_in_conditionCount4374);
                            var=numberVariable();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type, min, max, var);}

                    match(input,RPAREN,FOLLOW_RPAREN_in_conditionCount4391); if (state.failed) return cond;

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
            name=(Token)match(input,CURRENTCOUNT,FOLLOW_CURRENTCOUNT_in_conditionCurrentCount4431); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionCurrentCount4433); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionCurrentCount4439);
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
                    match(input,COMMA,FOLLOW_COMMA_in_conditionCurrentCount4453); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionCurrentCount4459);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,COMMA,FOLLOW_COMMA_in_conditionCurrentCount4461); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionCurrentCount4467);
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
                    match(input,COMMA,FOLLOW_COMMA_in_conditionCurrentCount4483); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberVariable_in_conditionCurrentCount4489);
                    var=numberVariable();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name,type, min, max, var);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionCurrentCount4504); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
            name=(Token)match(input,TOTALCOUNT,FOLLOW_TOTALCOUNT_in_conditionTotalCount4543); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionTotalCount4545); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionTotalCount4551);
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
                    match(input,COMMA,FOLLOW_COMMA_in_conditionTotalCount4565); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionTotalCount4571);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,COMMA,FOLLOW_COMMA_in_conditionTotalCount4573); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionTotalCount4579);
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
                    match(input,COMMA,FOLLOW_COMMA_in_conditionTotalCount4594); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberVariable_in_conditionTotalCount4600);
                    var=numberVariable();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type, min, max, var);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionTotalCount4615); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
            name=(Token)match(input,INLIST,FOLLOW_INLIST_in_conditionInList4656); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionInList4658); if (state.failed) return cond;

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
                    pushFollow(FOLLOW_stringListExpression_in_conditionInList4673);
                    list2=stringListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:961:90: list1= wordListExpression
                    {
                    pushFollow(FOLLOW_wordListExpression_in_conditionInList4681);
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
                    match(input,COMMA,FOLLOW_COMMA_in_conditionInList4690); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionInList4696);
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
                            match(input,COMMA,FOLLOW_COMMA_in_conditionInList4699); if (state.failed) return cond;

                            pushFollow(FOLLOW_booleanExpression_in_conditionInList4705);
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

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionInList4723); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:969:1: conditionIsInTag returns [TextMarkerCondition cond = null] : name= ISINTAG LPAREN id= stringExpression ( COMMA id1= stringExpression ASSIGN_EQUAL id2= stringExpression )* RPAREN ;
    public final TextMarkerCondition conditionIsInTag() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression id =null;

        Expression id1 =null;

        Expression id2 =null;



        List<Expression> list1 = new ArrayList<Expression>();
        List<Expression> list2 = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:974:5: (name= ISINTAG LPAREN id= stringExpression ( COMMA id1= stringExpression ASSIGN_EQUAL id2= stringExpression )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:975:5: name= ISINTAG LPAREN id= stringExpression ( COMMA id1= stringExpression ASSIGN_EQUAL id2= stringExpression )* RPAREN
            {
            name=(Token)match(input,ISINTAG,FOLLOW_ISINTAG_in_conditionIsInTag4766); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionIsInTag4768); if (state.failed) return cond;

            pushFollow(FOLLOW_stringExpression_in_conditionIsInTag4774);
            id=stringExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:975:49: ( COMMA id1= stringExpression ASSIGN_EQUAL id2= stringExpression )*
            loop101:
            do {
                int alt101=2;
                int LA101_0 = input.LA(1);

                if ( (LA101_0==COMMA) ) {
                    alt101=1;
                }


                switch (alt101) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:975:50: COMMA id1= stringExpression ASSIGN_EQUAL id2= stringExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_conditionIsInTag4777); if (state.failed) return cond;

            	    pushFollow(FOLLOW_stringExpression_in_conditionIsInTag4783);
            	    id1=stringExpression();

            	    state._fsp--;
            	    if (state.failed) return cond;

            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_conditionIsInTag4785); if (state.failed) return cond;

            	    pushFollow(FOLLOW_stringExpression_in_conditionIsInTag4791);
            	    id2=stringExpression();

            	    state._fsp--;
            	    if (state.failed) return cond;

            	    if ( state.backtracking==0 ) {list1.add(id1);list2.add(id2);}

            	    }
            	    break;

            	default :
            	    break loop101;
                }
            } while (true);


            if ( state.backtracking==0 ) {List exprs = new ArrayList();
                exprs.add(id);
                exprs.addAll(list1);
                exprs.addAll(list2);
                cond = ConditionFactory.createCondition(name, exprs);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionIsInTag4810); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:984:1: conditionLast returns [TextMarkerCondition cond = null] : name= LAST LPAREN type= typeExpression RPAREN ;
    public final TextMarkerCondition conditionLast() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression type =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:985:5: (name= LAST LPAREN type= typeExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:986:5: name= LAST LPAREN type= typeExpression RPAREN
            {
            name=(Token)match(input,LAST,FOLLOW_LAST_in_conditionLast4850); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionLast4852); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionLast4858);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionLast4871); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:991:1: conditionMofN returns [TextMarkerCondition cond = null] : name= MOFN LPAREN min= numberExpression COMMA max= numberExpression COMMA conds= conditions RPAREN ;
    public final TextMarkerCondition conditionMofN() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression min =null;

        Expression max =null;

        List<TextMarkerCondition> conds =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:992:5: (name= MOFN LPAREN min= numberExpression COMMA max= numberExpression COMMA conds= conditions RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:993:5: name= MOFN LPAREN min= numberExpression COMMA max= numberExpression COMMA conds= conditions RPAREN
            {
            name=(Token)match(input,MOFN,FOLLOW_MOFN_in_conditionMofN4907); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionMofN4909); if (state.failed) return cond;

            pushFollow(FOLLOW_numberExpression_in_conditionMofN4915);
            min=numberExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionMofN4917); if (state.failed) return cond;

            pushFollow(FOLLOW_numberExpression_in_conditionMofN4923);
            max=numberExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionMofN4925); if (state.failed) return cond;

            pushFollow(FOLLOW_conditions_in_conditionMofN4931);
            conds=conditions();

            state._fsp--;
            if (state.failed) return cond;

            if ( state.backtracking==0 ) {List exprs = new ArrayList();
                exprs.add(min);
                exprs.add(max);
                exprs.addAll(conds);
                cond = ConditionFactory.createCondition(name, exprs);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionMofN4946); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1002:1: conditionNear returns [TextMarkerCondition cond = null] : name= NEAR LPAREN type= typeExpression COMMA min= numberExpression COMMA max= numberExpression ( COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )? )? RPAREN ;
    public final TextMarkerCondition conditionNear() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression type =null;

        Expression min =null;

        Expression max =null;

        Expression direction =null;

        Expression filtered =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1003:5: (name= NEAR LPAREN type= typeExpression COMMA min= numberExpression COMMA max= numberExpression ( COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1004:5: name= NEAR LPAREN type= typeExpression COMMA min= numberExpression COMMA max= numberExpression ( COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )? )? RPAREN
            {
            name=(Token)match(input,NEAR,FOLLOW_NEAR_in_conditionNear4978); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionNear4980); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionNear4986);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionNear4988); if (state.failed) return cond;

            pushFollow(FOLLOW_numberExpression_in_conditionNear4994);
            min=numberExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionNear4996); if (state.failed) return cond;

            pushFollow(FOLLOW_numberExpression_in_conditionNear5002);
            max=numberExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1005:5: ( COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )? )?
            int alt103=2;
            int LA103_0 = input.LA(1);

            if ( (LA103_0==COMMA) ) {
                alt103=1;
            }
            switch (alt103) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1005:6: COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionNear5010); if (state.failed) return cond;

                    pushFollow(FOLLOW_booleanExpression_in_conditionNear5016);
                    direction=booleanExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1005:42: ( COMMA filtered= booleanExpression )?
                    int alt102=2;
                    int LA102_0 = input.LA(1);

                    if ( (LA102_0==COMMA) ) {
                        alt102=1;
                    }
                    switch (alt102) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1005:43: COMMA filtered= booleanExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionNear5019); if (state.failed) return cond;

                            pushFollow(FOLLOW_booleanExpression_in_conditionNear5025);
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

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionNear5045); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1009:1: conditionNot returns [TextMarkerCondition cond = null] : ( (name= MINUS c= condition ) | (name= NOT LPAREN c= condition RPAREN ) ) ;
    public final TextMarkerCondition conditionNot() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        TextMarkerCondition c =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1010:5: ( ( (name= MINUS c= condition ) | (name= NOT LPAREN c= condition RPAREN ) ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1011:5: ( (name= MINUS c= condition ) | (name= NOT LPAREN c= condition RPAREN ) )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1011:5: ( (name= MINUS c= condition ) | (name= NOT LPAREN c= condition RPAREN ) )
            int alt104=2;
            int LA104_0 = input.LA(1);

            if ( (LA104_0==MINUS) ) {
                alt104=1;
            }
            else if ( (LA104_0==NOT) ) {
                alt104=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return cond;}
                NoViableAltException nvae =
                    new NoViableAltException("", 104, 0, input);

                throw nvae;

            }
            switch (alt104) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1011:6: (name= MINUS c= condition )
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1011:6: (name= MINUS c= condition )
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1011:7: name= MINUS c= condition
                    {
                    name=(Token)match(input,MINUS,FOLLOW_MINUS_in_conditionNot5078); if (state.failed) return cond;

                    pushFollow(FOLLOW_condition_in_conditionNot5084);
                    c=condition();

                    state._fsp--;
                    if (state.failed) return cond;

                    }


                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1011:38: (name= NOT LPAREN c= condition RPAREN )
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1011:38: (name= NOT LPAREN c= condition RPAREN )
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1011:39: name= NOT LPAREN c= condition RPAREN
                    {
                    name=(Token)match(input,NOT,FOLLOW_NOT_in_conditionNot5095); if (state.failed) return cond;

                    match(input,LPAREN,FOLLOW_LPAREN_in_conditionNot5097); if (state.failed) return cond;

                    pushFollow(FOLLOW_condition_in_conditionNot5103);
                    c=condition();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,RPAREN,FOLLOW_RPAREN_in_conditionNot5105); if (state.failed) return cond;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1014:1: conditionOr returns [TextMarkerCondition cond = null] : name= OR LPAREN conds= conditions RPAREN ;
    public final TextMarkerCondition conditionOr() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        List<TextMarkerCondition> conds =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1015:5: (name= OR LPAREN conds= conditions RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1016:5: name= OR LPAREN conds= conditions RPAREN
            {
            name=(Token)match(input,OR,FOLLOW_OR_in_conditionOr5145); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionOr5147); if (state.failed) return cond;

            pushFollow(FOLLOW_conditions_in_conditionOr5153);
            conds=conditions();

            state._fsp--;
            if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, conds);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionOr5166); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1020:1: conditionPartOf returns [TextMarkerCondition cond = null] : name= PARTOF LPAREN (type= typeExpression |type= typeListExpression ) RPAREN ;
    public final TextMarkerCondition conditionPartOf() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression type =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1021:5: (name= PARTOF LPAREN (type= typeExpression |type= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1022:5: name= PARTOF LPAREN (type= typeExpression |type= typeListExpression ) RPAREN
            {
            name=(Token)match(input,PARTOF,FOLLOW_PARTOF_in_conditionPartOf5194); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionPartOf5196); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1022:26: (type= typeExpression |type= typeListExpression )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1022:27: type= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionPartOf5203);
                    type=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1022:49: type= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionPartOf5209);
                    type=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionPartOf5226); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1027:1: conditionPartOfNeq returns [TextMarkerCondition cond = null] : name= PARTOFNEQ LPAREN (type= typeExpression |type= typeListExpression ) RPAREN ;
    public final TextMarkerCondition conditionPartOfNeq() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression type =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1028:5: (name= PARTOFNEQ LPAREN (type= typeExpression |type= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1029:5: name= PARTOFNEQ LPAREN (type= typeExpression |type= typeListExpression ) RPAREN
            {
            name=(Token)match(input,PARTOFNEQ,FOLLOW_PARTOFNEQ_in_conditionPartOfNeq5259); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionPartOfNeq5261); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1029:29: (type= typeExpression |type= typeListExpression )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1029:30: type= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionPartOfNeq5268);
                    type=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1029:52: type= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionPartOfNeq5274);
                    type=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionPartOfNeq5291); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1035:1: conditionPosition returns [TextMarkerCondition cond = null] : name= POSITION LPAREN type= typeExpression COMMA pos= numberExpression RPAREN ;
    public final TextMarkerCondition conditionPosition() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression type =null;

        Expression pos =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1036:5: (name= POSITION LPAREN type= typeExpression COMMA pos= numberExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1037:5: name= POSITION LPAREN type= typeExpression COMMA pos= numberExpression RPAREN
            {
            name=(Token)match(input,POSITION,FOLLOW_POSITION_in_conditionPosition5328); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionPosition5330); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionPosition5336);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionPosition5338); if (state.failed) return cond;

            pushFollow(FOLLOW_numberExpression_in_conditionPosition5344);
            pos=numberExpression();

            state._fsp--;
            if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type, pos);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionPosition5357); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1041:1: conditionRegExp returns [TextMarkerCondition cond = null] : name= REGEXP LPAREN pattern= stringExpression ( COMMA caseSensitive= booleanExpression )? RPAREN ;
    public final TextMarkerCondition conditionRegExp() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression pattern =null;

        Expression caseSensitive =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1042:5: (name= REGEXP LPAREN pattern= stringExpression ( COMMA caseSensitive= booleanExpression )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1043:5: name= REGEXP LPAREN pattern= stringExpression ( COMMA caseSensitive= booleanExpression )? RPAREN
            {
            name=(Token)match(input,REGEXP,FOLLOW_REGEXP_in_conditionRegExp5385); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionRegExp5387); if (state.failed) return cond;

            pushFollow(FOLLOW_stringExpression_in_conditionRegExp5393);
            pattern=stringExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1043:53: ( COMMA caseSensitive= booleanExpression )?
            int alt107=2;
            int LA107_0 = input.LA(1);

            if ( (LA107_0==COMMA) ) {
                alt107=1;
            }
            switch (alt107) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1043:54: COMMA caseSensitive= booleanExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionRegExp5396); if (state.failed) return cond;

                    pushFollow(FOLLOW_booleanExpression_in_conditionRegExp5402);
                    caseSensitive=booleanExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, pattern, caseSensitive);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionRegExp5420); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1048:1: conditionScore returns [TextMarkerCondition cond = null] : name= SCORE LPAREN min= numberExpression ( COMMA max= numberExpression ( COMMA var= numberVariable )? )? RPAREN ;
    public final TextMarkerCondition conditionScore() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression min =null;

        Expression max =null;

        Expression var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1049:5: (name= SCORE LPAREN min= numberExpression ( COMMA max= numberExpression ( COMMA var= numberVariable )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1050:5: name= SCORE LPAREN min= numberExpression ( COMMA max= numberExpression ( COMMA var= numberVariable )? )? RPAREN
            {
            name=(Token)match(input,SCORE,FOLLOW_SCORE_in_conditionScore5454); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionScore5456); if (state.failed) return cond;

            pushFollow(FOLLOW_numberExpression_in_conditionScore5462);
            min=numberExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1050:48: ( COMMA max= numberExpression ( COMMA var= numberVariable )? )?
            int alt109=2;
            int LA109_0 = input.LA(1);

            if ( (LA109_0==COMMA) ) {
                alt109=1;
            }
            switch (alt109) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1050:49: COMMA max= numberExpression ( COMMA var= numberVariable )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionScore5465); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionScore5471);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1051:5: ( COMMA var= numberVariable )?
                    int alt108=2;
                    int LA108_0 = input.LA(1);

                    if ( (LA108_0==COMMA) ) {
                        alt108=1;
                    }
                    switch (alt108) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1051:6: COMMA var= numberVariable
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionScore5480); if (state.failed) return cond;

                            pushFollow(FOLLOW_numberVariable_in_conditionScore5486);
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

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionScore5503); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1056:1: conditionVote returns [TextMarkerCondition cond = null] : name= VOTE LPAREN type1= typeExpression COMMA type2= typeExpression RPAREN ;
    public final TextMarkerCondition conditionVote() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression type1 =null;

        Expression type2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1057:5: (name= VOTE LPAREN type1= typeExpression COMMA type2= typeExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1058:5: name= VOTE LPAREN type1= typeExpression COMMA type2= typeExpression RPAREN
            {
            name=(Token)match(input,VOTE,FOLLOW_VOTE_in_conditionVote5535); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionVote5537); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionVote5543);
            type1=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionVote5545); if (state.failed) return cond;

            pushFollow(FOLLOW_typeExpression_in_conditionVote5551);
            type2=typeExpression();

            state._fsp--;
            if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type1, type2);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionVote5564); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1062:1: conditionIf returns [TextMarkerCondition cond = null] : name= IF LPAREN e= booleanExpression RPAREN ;
    public final TextMarkerCondition conditionIf() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1063:5: (name= IF LPAREN e= booleanExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1064:5: name= IF LPAREN e= booleanExpression RPAREN
            {
            name=(Token)match(input,IF,FOLLOW_IF_in_conditionIf5598); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionIf5600); if (state.failed) return cond;

            pushFollow(FOLLOW_booleanExpression_in_conditionIf5606);
            e=booleanExpression();

            state._fsp--;
            if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, e);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionIf5619); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1069:1: conditionFeature returns [TextMarkerCondition cond = null] : name= FEATURE LPAREN se= stringExpression COMMA v= argument RPAREN ;
    public final TextMarkerCondition conditionFeature() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression se =null;

        Expression v =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1070:5: (name= FEATURE LPAREN se= stringExpression COMMA v= argument RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1071:5: name= FEATURE LPAREN se= stringExpression COMMA v= argument RPAREN
            {
            name=(Token)match(input,FEATURE,FOLLOW_FEATURE_in_conditionFeature5658); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionFeature5660); if (state.failed) return cond;

            pushFollow(FOLLOW_stringExpression_in_conditionFeature5666);
            se=stringExpression();

            state._fsp--;
            if (state.failed) return cond;

            match(input,COMMA,FOLLOW_COMMA_in_conditionFeature5668); if (state.failed) return cond;

            pushFollow(FOLLOW_argument_in_conditionFeature5674);
            v=argument();

            state._fsp--;
            if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, se, v);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionFeature5687); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1075:1: conditionParse returns [TextMarkerCondition cond = null] : name= PARSE LPAREN var= genericVariableReference RPAREN ;
    public final TextMarkerCondition conditionParse() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1076:5: (name= PARSE LPAREN var= genericVariableReference RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1077:5: name= PARSE LPAREN var= genericVariableReference RPAREN
            {
            name=(Token)match(input,PARSE,FOLLOW_PARSE_in_conditionParse5718); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionParse5720); if (state.failed) return cond;

            pushFollow(FOLLOW_genericVariableReference_in_conditionParse5729);
            var=genericVariableReference();

            state._fsp--;
            if (state.failed) return cond;

            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, var);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionParse5742); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1084:1: conditionIs returns [TextMarkerCondition cond = null] : name= IS LPAREN (type= typeExpression |type= typeListExpression ) RPAREN ;
    public final TextMarkerCondition conditionIs() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression type =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1085:5: (name= IS LPAREN (type= typeExpression |type= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1086:5: name= IS LPAREN (type= typeExpression |type= typeListExpression ) RPAREN
            {
            name=(Token)match(input,IS,FOLLOW_IS_in_conditionIs5772); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionIs5774); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1086:22: (type= typeExpression |type= typeListExpression )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1086:23: type= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionIs5781);
                    type=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1086:45: type= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionIs5787);
                    type=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionIs5801); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1091:1: conditionBefore returns [TextMarkerCondition cond = null] : name= BEFORE LPAREN (type= typeExpression |type= typeListExpression ) RPAREN ;
    public final TextMarkerCondition conditionBefore() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression type =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1092:5: (name= BEFORE LPAREN (type= typeExpression |type= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1093:5: name= BEFORE LPAREN (type= typeExpression |type= typeListExpression ) RPAREN
            {
            name=(Token)match(input,BEFORE,FOLLOW_BEFORE_in_conditionBefore5830); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionBefore5832); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1093:26: (type= typeExpression |type= typeListExpression )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1093:27: type= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionBefore5839);
                    type=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1093:49: type= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionBefore5845);
                    type=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionBefore5859); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1098:1: conditionAfter returns [TextMarkerCondition cond = null] : name= AFTER LPAREN (type= typeExpression |type= typeListExpression ) RPAREN ;
    public final TextMarkerCondition conditionAfter() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression type =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1099:5: (name= AFTER LPAREN (type= typeExpression |type= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1100:5: name= AFTER LPAREN (type= typeExpression |type= typeListExpression ) RPAREN
            {
            name=(Token)match(input,AFTER,FOLLOW_AFTER_in_conditionAfter5888); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionAfter5890); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1100:25: (type= typeExpression |type= typeListExpression )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1100:26: type= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionAfter5897);
                    type=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1100:48: type= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionAfter5903);
                    type=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionAfter5917); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1105:1: conditionStartsWith returns [TextMarkerCondition cond = null] : name= STARTSWITH LPAREN (type= typeExpression |type= typeListExpression ) RPAREN ;
    public final TextMarkerCondition conditionStartsWith() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression type =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1106:5: (name= STARTSWITH LPAREN (type= typeExpression |type= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1107:5: name= STARTSWITH LPAREN (type= typeExpression |type= typeListExpression ) RPAREN
            {
            name=(Token)match(input,STARTSWITH,FOLLOW_STARTSWITH_in_conditionStartsWith5950); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionStartsWith5952); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1107:30: (type= typeExpression |type= typeListExpression )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1107:31: type= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionStartsWith5959);
                    type=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1107:53: type= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionStartsWith5965);
                    type=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionStartsWith5979); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1112:1: conditionEndsWith returns [TextMarkerCondition cond = null] : name= ENDSWITH LPAREN (type= typeExpression |type= typeListExpression ) RPAREN ;
    public final TextMarkerCondition conditionEndsWith() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression type =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1113:5: (name= ENDSWITH LPAREN (type= typeExpression |type= typeListExpression ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1114:5: name= ENDSWITH LPAREN (type= typeExpression |type= typeListExpression ) RPAREN
            {
            name=(Token)match(input,ENDSWITH,FOLLOW_ENDSWITH_in_conditionEndsWith6012); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionEndsWith6014); if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1114:28: (type= typeExpression |type= typeListExpression )
            int alt114=2;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA114_1 = input.LA(2);

                if ( (!(((isVariableOfType(input.LT(1).getText(), "TYPELIST"))))) ) {
                    alt114=1;
                }
                else if ( ((isVariableOfType(input.LT(1).getText(), "TYPELIST"))) ) {
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1114:29: type= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionEndsWith6021);
                    type=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1114:51: type= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionEndsWith6027);
                    type=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, type);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionEndsWith6041); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1119:1: conditionSize returns [TextMarkerCondition cond = null] : name= SIZE LPAREN list= listExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN ;
    public final TextMarkerCondition conditionSize() throws RecognitionException {
        TextMarkerCondition cond =  null;


        Token name=null;
        Expression list =null;

        Expression min =null;

        Expression max =null;

        Expression var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1120:5: (name= SIZE LPAREN list= listExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1121:5: name= SIZE LPAREN list= listExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
            {
            name=(Token)match(input,SIZE,FOLLOW_SIZE_in_conditionSize6074); if (state.failed) return cond;

            match(input,LPAREN,FOLLOW_LPAREN_in_conditionSize6076); if (state.failed) return cond;

            pushFollow(FOLLOW_listExpression_in_conditionSize6082);
            list=listExpression();

            state._fsp--;
            if (state.failed) return cond;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1121:46: ( COMMA min= numberExpression COMMA max= numberExpression )?
            int alt115=2;
            int LA115_0 = input.LA(1);

            if ( (LA115_0==COMMA) ) {
                int LA115_1 = input.LA(2);

                if ( (LA115_1==COS||LA115_1==DecimalLiteral||LA115_1==EXP||LA115_1==FloatingPointLiteral||(LA115_1 >= LOGN && LA115_1 <= LPAREN)||LA115_1==MINUS||LA115_1==SIN||LA115_1==TAN) ) {
                    alt115=1;
                }
                else if ( (LA115_1==Identifier) ) {
                    int LA115_4 = input.LA(3);

                    if ( (LA115_4==COMMA||LA115_4==LPAREN||LA115_4==MINUS||(LA115_4 >= PERCENT && LA115_4 <= PLUS)||(LA115_4 >= SLASH && LA115_4 <= STAR)) ) {
                        alt115=1;
                    }
                }
            }
            switch (alt115) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1121:47: COMMA min= numberExpression COMMA max= numberExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionSize6085); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionSize6091);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    match(input,COMMA,FOLLOW_COMMA_in_conditionSize6093); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberExpression_in_conditionSize6099);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1121:107: ( COMMA var= numberVariable )?
            int alt116=2;
            int LA116_0 = input.LA(1);

            if ( (LA116_0==COMMA) ) {
                alt116=1;
            }
            switch (alt116) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1121:108: COMMA var= numberVariable
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionSize6104); if (state.failed) return cond;

                    pushFollow(FOLLOW_numberVariable_in_conditionSize6110);
                    var=numberVariable();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {cond = ConditionFactory.createCondition(name, list, min, max, var);}

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionSize6125); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1127:1: action returns [TextMarkerAction result = null] : (a= actionColor |a= actionDel |a= actionLog |a= actionMark |a= actionMarkScore |a= actionMarkFast |a= actionMarkLast |a= actionReplace |a= actionRetainMarkup |a= actionRetainType |a= actionFilterMarkup |a= actionFilterType |a= actionCreate |a= actionFill |a= actionCall |a= actionAssign |a= actionSetFeature |a= actionGetFeature |a= actionUnmark |a= actionUnmarkAll |a= actionTransfer |a= actionMarkOnce |a= actionTrie |a= actionGather |a= actionExec |a= actionMarkTable |a= actionAdd |a= actionRemove |a= actionRemoveDuplicate |a= actionMerge |a= actionGet |a= actionGetList |a= actionMatchedText |a= actionClear |a= actionExpand |a= actionConfigure |a= actionDynamicAnchoring | (a= externalAction )=>a= externalAction |a= variableAction ) ;
    public final TextMarkerAction action() throws RecognitionException {
        TextMarkerAction result =  null;


        TextMarkerAction a =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1128:2: ( (a= actionColor |a= actionDel |a= actionLog |a= actionMark |a= actionMarkScore |a= actionMarkFast |a= actionMarkLast |a= actionReplace |a= actionRetainMarkup |a= actionRetainType |a= actionFilterMarkup |a= actionFilterType |a= actionCreate |a= actionFill |a= actionCall |a= actionAssign |a= actionSetFeature |a= actionGetFeature |a= actionUnmark |a= actionUnmarkAll |a= actionTransfer |a= actionMarkOnce |a= actionTrie |a= actionGather |a= actionExec |a= actionMarkTable |a= actionAdd |a= actionRemove |a= actionRemoveDuplicate |a= actionMerge |a= actionGet |a= actionGetList |a= actionMatchedText |a= actionClear |a= actionExpand |a= actionConfigure |a= actionDynamicAnchoring | (a= externalAction )=>a= externalAction |a= variableAction ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1129:2: (a= actionColor |a= actionDel |a= actionLog |a= actionMark |a= actionMarkScore |a= actionMarkFast |a= actionMarkLast |a= actionReplace |a= actionRetainMarkup |a= actionRetainType |a= actionFilterMarkup |a= actionFilterType |a= actionCreate |a= actionFill |a= actionCall |a= actionAssign |a= actionSetFeature |a= actionGetFeature |a= actionUnmark |a= actionUnmarkAll |a= actionTransfer |a= actionMarkOnce |a= actionTrie |a= actionGather |a= actionExec |a= actionMarkTable |a= actionAdd |a= actionRemove |a= actionRemoveDuplicate |a= actionMerge |a= actionGet |a= actionGetList |a= actionMatchedText |a= actionClear |a= actionExpand |a= actionConfigure |a= actionDynamicAnchoring | (a= externalAction )=>a= externalAction |a= variableAction )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1129:2: (a= actionColor |a= actionDel |a= actionLog |a= actionMark |a= actionMarkScore |a= actionMarkFast |a= actionMarkLast |a= actionReplace |a= actionRetainMarkup |a= actionRetainType |a= actionFilterMarkup |a= actionFilterType |a= actionCreate |a= actionFill |a= actionCall |a= actionAssign |a= actionSetFeature |a= actionGetFeature |a= actionUnmark |a= actionUnmarkAll |a= actionTransfer |a= actionMarkOnce |a= actionTrie |a= actionGather |a= actionExec |a= actionMarkTable |a= actionAdd |a= actionRemove |a= actionRemoveDuplicate |a= actionMerge |a= actionGet |a= actionGetList |a= actionMatchedText |a= actionClear |a= actionExpand |a= actionConfigure |a= actionDynamicAnchoring | (a= externalAction )=>a= externalAction |a= variableAction )
            int alt117=39;
            switch ( input.LA(1) ) {
            case COLOR:
                {
                alt117=1;
                }
                break;
            case DEL:
                {
                alt117=2;
                }
                break;
            case LOG:
                {
                alt117=3;
                }
                break;
            case MARK:
                {
                alt117=4;
                }
                break;
            case MARKSCORE:
                {
                alt117=5;
                }
                break;
            case MARKFAST:
                {
                alt117=6;
                }
                break;
            case MARKLAST:
                {
                alt117=7;
                }
                break;
            case REPLACE:
                {
                alt117=8;
                }
                break;
            case RETAINMARKUP:
                {
                alt117=9;
                }
                break;
            case RETAINTYPE:
                {
                alt117=10;
                }
                break;
            case FILTERMARKUP:
                {
                alt117=11;
                }
                break;
            case FILTERTYPE:
                {
                alt117=12;
                }
                break;
            case CREATE:
                {
                alt117=13;
                }
                break;
            case FILL:
                {
                alt117=14;
                }
                break;
            case CALL:
                {
                alt117=15;
                }
                break;
            case ASSIGN:
                {
                alt117=16;
                }
                break;
            case SETFEATURE:
                {
                alt117=17;
                }
                break;
            case GETFEATURE:
                {
                alt117=18;
                }
                break;
            case UNMARK:
                {
                alt117=19;
                }
                break;
            case UNMARKALL:
                {
                alt117=20;
                }
                break;
            case TRANSFER:
                {
                alt117=21;
                }
                break;
            case MARKONCE:
                {
                alt117=22;
                }
                break;
            case TRIE:
                {
                alt117=23;
                }
                break;
            case GATHER:
                {
                alt117=24;
                }
                break;
            case EXEC:
                {
                alt117=25;
                }
                break;
            case MARKTABLE:
                {
                alt117=26;
                }
                break;
            case ADD:
                {
                alt117=27;
                }
                break;
            case REMOVE:
                {
                alt117=28;
                }
                break;
            case REMOVEDUPLICATE:
                {
                alt117=29;
                }
                break;
            case MERGE:
                {
                alt117=30;
                }
                break;
            case GET:
                {
                alt117=31;
                }
                break;
            case GETLIST:
                {
                alt117=32;
                }
                break;
            case MATCHEDTEXT:
                {
                alt117=33;
                }
                break;
            case CLEAR:
                {
                alt117=34;
                }
                break;
            case EXPAND:
                {
                alt117=35;
                }
                break;
            case CONFIGURE:
                {
                alt117=36;
                }
                break;
            case DYNAMICANCHORING:
                {
                alt117=37;
                }
                break;
            case Identifier:
                {
                int LA117_38 = input.LA(2);

                if ( (LA117_38==LPAREN) && (synpred17_TextMarkerParser())) {
                    alt117=38;
                }
                else if ( (LA117_38==COMMA||LA117_38==RCURLY||LA117_38==RPAREN) ) {
                    alt117=39;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return result;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 117, 38, input);

                    throw nvae;

                }
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return result;}
                NoViableAltException nvae =
                    new NoViableAltException("", 117, 0, input);

                throw nvae;

            }

            switch (alt117) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1130:2: a= actionColor
                    {
                    pushFollow(FOLLOW_actionColor_in_action6153);
                    a=actionColor();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1131:4: a= actionDel
                    {
                    pushFollow(FOLLOW_actionDel_in_action6162);
                    a=actionDel();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1132:4: a= actionLog
                    {
                    pushFollow(FOLLOW_actionLog_in_action6171);
                    a=actionLog();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1133:4: a= actionMark
                    {
                    pushFollow(FOLLOW_actionMark_in_action6180);
                    a=actionMark();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1134:4: a= actionMarkScore
                    {
                    pushFollow(FOLLOW_actionMarkScore_in_action6189);
                    a=actionMarkScore();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 6 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1135:4: a= actionMarkFast
                    {
                    pushFollow(FOLLOW_actionMarkFast_in_action6198);
                    a=actionMarkFast();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 7 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1136:4: a= actionMarkLast
                    {
                    pushFollow(FOLLOW_actionMarkLast_in_action6207);
                    a=actionMarkLast();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 8 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1137:4: a= actionReplace
                    {
                    pushFollow(FOLLOW_actionReplace_in_action6216);
                    a=actionReplace();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 9 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1138:4: a= actionRetainMarkup
                    {
                    pushFollow(FOLLOW_actionRetainMarkup_in_action6225);
                    a=actionRetainMarkup();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 10 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1139:4: a= actionRetainType
                    {
                    pushFollow(FOLLOW_actionRetainType_in_action6234);
                    a=actionRetainType();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 11 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1140:4: a= actionFilterMarkup
                    {
                    pushFollow(FOLLOW_actionFilterMarkup_in_action6243);
                    a=actionFilterMarkup();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 12 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1141:4: a= actionFilterType
                    {
                    pushFollow(FOLLOW_actionFilterType_in_action6252);
                    a=actionFilterType();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 13 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1142:4: a= actionCreate
                    {
                    pushFollow(FOLLOW_actionCreate_in_action6261);
                    a=actionCreate();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 14 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1143:4: a= actionFill
                    {
                    pushFollow(FOLLOW_actionFill_in_action6270);
                    a=actionFill();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 15 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1144:4: a= actionCall
                    {
                    pushFollow(FOLLOW_actionCall_in_action6279);
                    a=actionCall();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 16 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1145:4: a= actionAssign
                    {
                    pushFollow(FOLLOW_actionAssign_in_action6288);
                    a=actionAssign();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 17 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1146:4: a= actionSetFeature
                    {
                    pushFollow(FOLLOW_actionSetFeature_in_action6297);
                    a=actionSetFeature();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 18 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1147:4: a= actionGetFeature
                    {
                    pushFollow(FOLLOW_actionGetFeature_in_action6306);
                    a=actionGetFeature();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 19 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1148:4: a= actionUnmark
                    {
                    pushFollow(FOLLOW_actionUnmark_in_action6315);
                    a=actionUnmark();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 20 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1149:4: a= actionUnmarkAll
                    {
                    pushFollow(FOLLOW_actionUnmarkAll_in_action6324);
                    a=actionUnmarkAll();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 21 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1150:4: a= actionTransfer
                    {
                    pushFollow(FOLLOW_actionTransfer_in_action6333);
                    a=actionTransfer();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 22 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1151:4: a= actionMarkOnce
                    {
                    pushFollow(FOLLOW_actionMarkOnce_in_action6342);
                    a=actionMarkOnce();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 23 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1152:4: a= actionTrie
                    {
                    pushFollow(FOLLOW_actionTrie_in_action6351);
                    a=actionTrie();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 24 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1153:4: a= actionGather
                    {
                    pushFollow(FOLLOW_actionGather_in_action6360);
                    a=actionGather();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 25 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1154:4: a= actionExec
                    {
                    pushFollow(FOLLOW_actionExec_in_action6370);
                    a=actionExec();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 26 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1155:4: a= actionMarkTable
                    {
                    pushFollow(FOLLOW_actionMarkTable_in_action6379);
                    a=actionMarkTable();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 27 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1156:4: a= actionAdd
                    {
                    pushFollow(FOLLOW_actionAdd_in_action6388);
                    a=actionAdd();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 28 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1157:4: a= actionRemove
                    {
                    pushFollow(FOLLOW_actionRemove_in_action6397);
                    a=actionRemove();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 29 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1158:4: a= actionRemoveDuplicate
                    {
                    pushFollow(FOLLOW_actionRemoveDuplicate_in_action6406);
                    a=actionRemoveDuplicate();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 30 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1159:4: a= actionMerge
                    {
                    pushFollow(FOLLOW_actionMerge_in_action6415);
                    a=actionMerge();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 31 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1160:4: a= actionGet
                    {
                    pushFollow(FOLLOW_actionGet_in_action6424);
                    a=actionGet();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 32 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1161:4: a= actionGetList
                    {
                    pushFollow(FOLLOW_actionGetList_in_action6434);
                    a=actionGetList();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 33 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1162:4: a= actionMatchedText
                    {
                    pushFollow(FOLLOW_actionMatchedText_in_action6443);
                    a=actionMatchedText();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 34 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1163:4: a= actionClear
                    {
                    pushFollow(FOLLOW_actionClear_in_action6452);
                    a=actionClear();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 35 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1164:4: a= actionExpand
                    {
                    pushFollow(FOLLOW_actionExpand_in_action6461);
                    a=actionExpand();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 36 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1165:4: a= actionConfigure
                    {
                    pushFollow(FOLLOW_actionConfigure_in_action6470);
                    a=actionConfigure();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 37 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1166:4: a= actionDynamicAnchoring
                    {
                    pushFollow(FOLLOW_actionDynamicAnchoring_in_action6479);
                    a=actionDynamicAnchoring();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 38 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1167:4: (a= externalAction )=>a= externalAction
                    {
                    pushFollow(FOLLOW_externalAction_in_action6497);
                    a=externalAction();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 39 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1168:4: a= variableAction
                    {
                    pushFollow(FOLLOW_variableAction_in_action6506);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1173:1: variableAction returns [TextMarkerAction action = null] : id= Identifier ;
    public final TextMarkerAction variableAction() throws RecognitionException {
        TextMarkerAction action =  null;


        Token id=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1174:2: (id= Identifier )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1177:3: id= Identifier
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableAction6537); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1182:1: externalAction returns [TextMarkerAction action = null] :{...}?id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final TextMarkerAction externalAction() throws RecognitionException {
        TextMarkerAction action =  null;


        Token id=null;
        List<Expression> args =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1183:2: ({...}?id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1184:2: {...}?id= Identifier LPAREN args= varArgumentList RPAREN
            {
            if ( !((isVariableOfType(input.LT(1).getText(), "ACTION"))) ) {
                if (state.backtracking>0) {state.failed=true; return action;}
                throw new FailedPredicateException(input, "externalAction", "isVariableOfType(input.LT(1).getText(), \"ACTION\")");
            }

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalAction6561); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_externalAction6565); if (state.failed) return action;

            pushFollow(FOLLOW_varArgumentList_in_externalAction6574);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return action;

            match(input,RPAREN,FOLLOW_RPAREN_in_externalAction6579); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1194:1: actionCreate returns [TextMarkerAction action = null] : name= CREATE LPAREN structure= typeExpression ( COMMA (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? )? RPAREN ;
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1200:5: (name= CREATE LPAREN structure= typeExpression ( COMMA (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1201:5: name= CREATE LPAREN structure= typeExpression ( COMMA (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? )? RPAREN
            {
            name=(Token)match(input,CREATE,FOLLOW_CREATE_in_actionCreate6615); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionCreate6617); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionCreate6623);
            structure=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1202:5: ( COMMA (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? )?
            int alt122=2;
            int LA122_0 = input.LA(1);

            if ( (LA122_0==COMMA) ) {
                alt122=1;
            }
            switch (alt122) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1202:6: COMMA (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionCreate6630); if (state.failed) return action;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1204:5: (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )?
                    int alt119=2;
                    int LA119_0 = input.LA(1);

                    if ( (LA119_0==COS||LA119_0==DecimalLiteral||LA119_0==EXP||LA119_0==FloatingPointLiteral||(LA119_0 >= LOGN && LA119_0 <= LPAREN)||LA119_0==MINUS||LA119_0==SIN||LA119_0==TAN) ) {
                        alt119=1;
                    }
                    else if ( (LA119_0==Identifier) ) {
                        int LA119_2 = input.LA(2);

                        if ( (((isVariableOfType(input.LT(1).getText(), "INT"))||(isVariableOfType(input.LT(1).getText(), "DOUBLE"))||(isVariableOfType(input.LT(1).getText(), "NUMBERFUNCTION"))||(isVariableOfType(input.LT(1).getText(), "FLOAT")))) ) {
                            alt119=1;
                        }
                    }
                    switch (alt119) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1204:6: index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA
                            {
                            pushFollow(FOLLOW_numberExpression_in_actionCreate6651);
                            index=numberExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            if ( state.backtracking==0 ) {indexes.add(index);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1204:53: ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )*
                            loop118:
                            do {
                                int alt118=2;
                                int LA118_0 = input.LA(1);

                                if ( (LA118_0==COMMA) ) {
                                    int LA118_1 = input.LA(2);

                                    if ( (synpred18_TextMarkerParser()) ) {
                                        alt118=1;
                                    }


                                }


                                switch (alt118) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1204:54: ( COMMA index= numberExpression )=> ( COMMA index= numberExpression )
                            	    {
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1204:89: ( COMMA index= numberExpression )
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1204:90: COMMA index= numberExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_actionCreate6668); if (state.failed) return action;

                            	    pushFollow(FOLLOW_numberExpression_in_actionCreate6674);
                            	    index=numberExpression();

                            	    state._fsp--;
                            	    if (state.failed) return action;

                            	    }


                            	    if ( state.backtracking==0 ) {indexes.add(index);}

                            	    }
                            	    break;

                            	default :
                            	    break loop118;
                                }
                            } while (true);


                            match(input,COMMA,FOLLOW_COMMA_in_actionCreate6680); if (state.failed) return action;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1206:5: (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )?
                    int alt121=2;
                    int LA121_0 = input.LA(1);

                    if ( (LA121_0==Identifier||LA121_0==REMOVESTRING||LA121_0==StringLiteral) ) {
                        alt121=1;
                    }
                    switch (alt121) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1206:6: fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )*
                            {
                            pushFollow(FOLLOW_stringExpression_in_actionCreate6698);
                            fname=stringExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionCreate6700); if (state.failed) return action;

                            pushFollow(FOLLOW_argument_in_actionCreate6706);
                            obj1=argument();

                            state._fsp--;
                            if (state.failed) return action;

                            if ( state.backtracking==0 ) {left.add(fname); right.add(obj1);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1207:5: ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )*
                            loop120:
                            do {
                                int alt120=2;
                                int LA120_0 = input.LA(1);

                                if ( (LA120_0==COMMA) ) {
                                    alt120=1;
                                }


                                switch (alt120) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1207:6: COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_actionCreate6716); if (state.failed) return action;

                            	    pushFollow(FOLLOW_stringExpression_in_actionCreate6722);
                            	    fname=stringExpression();

                            	    state._fsp--;
                            	    if (state.failed) return action;

                            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionCreate6724); if (state.failed) return action;

                            	    pushFollow(FOLLOW_argument_in_actionCreate6730);
                            	    obj1=argument();

                            	    state._fsp--;
                            	    if (state.failed) return action;

                            	    if ( state.backtracking==0 ) {left.add(fname);right.add(obj1);}

                            	    }
                            	    break;

                            	default :
                            	    break loop120;
                                }
                            } while (true);


                            }
                            break;

                    }


                    }
                    break;

            }


            if ( state.backtracking==0 ) {action = ActionFactory.createStructureAction(name, structure, indexes, left, right);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionCreate6761); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1215:1: actionMarkTable returns [TextMarkerAction action = null] : name= MARKTABLE LPAREN structure= typeExpression COMMA index= numberExpression COMMA table= wordTableExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )* )? RPAREN ;
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1220:5: (name= MARKTABLE LPAREN structure= typeExpression COMMA index= numberExpression COMMA table= wordTableExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )* )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1221:5: name= MARKTABLE LPAREN structure= typeExpression COMMA index= numberExpression COMMA table= wordTableExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )* )? RPAREN
            {
            name=(Token)match(input,MARKTABLE,FOLLOW_MARKTABLE_in_actionMarkTable6796); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkTable6798); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionMarkTable6809);
            structure=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionMarkTable6811); if (state.failed) return action;

            pushFollow(FOLLOW_numberExpression_in_actionMarkTable6822);
            index=numberExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionMarkTable6824); if (state.failed) return action;

            pushFollow(FOLLOW_wordTableExpression_in_actionMarkTable6834);
            table=wordTableExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1225:5: ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )* )?
            int alt124=2;
            int LA124_0 = input.LA(1);

            if ( (LA124_0==COMMA) ) {
                alt124=1;
            }
            switch (alt124) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1225:6: COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )*
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionMarkTable6842); if (state.failed) return action;

                    pushFollow(FOLLOW_stringExpression_in_actionMarkTable6853);
                    fname=stringExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionMarkTable6855); if (state.failed) return action;

                    pushFollow(FOLLOW_numberExpression_in_actionMarkTable6861);
                    obj1=numberExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {left.add(fname); right.add(obj1);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1227:5: ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )*
                    loop123:
                    do {
                        int alt123=2;
                        int LA123_0 = input.LA(1);

                        if ( (LA123_0==COMMA) ) {
                            alt123=1;
                        }


                        switch (alt123) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1227:6: COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionMarkTable6871); if (state.failed) return action;

                    	    pushFollow(FOLLOW_stringExpression_in_actionMarkTable6877);
                    	    fname=stringExpression();

                    	    state._fsp--;
                    	    if (state.failed) return action;

                    	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionMarkTable6879); if (state.failed) return action;

                    	    pushFollow(FOLLOW_numberExpression_in_actionMarkTable6885);
                    	    obj1=numberExpression();

                    	    state._fsp--;
                    	    if (state.failed) return action;

                    	    if ( state.backtracking==0 ) {left.add(fname);right.add(obj1);}

                    	    }
                    	    break;

                    	default :
                    	    break loop123;
                        }
                    } while (true);


                    }
                    break;

            }


            if ( state.backtracking==0 ) {action = ActionFactory.createStructureAction(name, structure, index, table, left, right);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkTable6909); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1233:1: actionGather returns [TextMarkerAction action = null] : name= GATHER LPAREN structure= typeExpression ( COMMA (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )? )? RPAREN ;
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1239:5: (name= GATHER LPAREN structure= typeExpression ( COMMA (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1240:5: name= GATHER LPAREN structure= typeExpression ( COMMA (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )? )? RPAREN
            {
            name=(Token)match(input,GATHER,FOLLOW_GATHER_in_actionGather6943); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionGather6945); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionGather6951);
            structure=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createStructureAction(name, structure, indexes, left, right);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1242:5: ( COMMA (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )? )?
            int alt131=2;
            int LA131_0 = input.LA(1);

            if ( (LA131_0==COMMA) ) {
                alt131=1;
            }
            switch (alt131) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1242:6: COMMA (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionGather6965); if (state.failed) return action;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1243:5: (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )?
                    int alt126=2;
                    int LA126_0 = input.LA(1);

                    if ( (LA126_0==COS||LA126_0==DecimalLiteral||LA126_0==EXP||LA126_0==FloatingPointLiteral||(LA126_0 >= LOGN && LA126_0 <= LPAREN)||LA126_0==MINUS||LA126_0==SIN||LA126_0==TAN) ) {
                        alt126=1;
                    }
                    else if ( (LA126_0==Identifier) ) {
                        int LA126_2 = input.LA(2);

                        if ( (((isVariableOfType(input.LT(1).getText(), "INT"))||(isVariableOfType(input.LT(1).getText(), "DOUBLE"))||(isVariableOfType(input.LT(1).getText(), "NUMBERFUNCTION"))||(isVariableOfType(input.LT(1).getText(), "FLOAT")))) ) {
                            alt126=1;
                        }
                    }
                    switch (alt126) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1243:6: index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA
                            {
                            pushFollow(FOLLOW_numberExpression_in_actionGather6977);
                            index=numberExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            if ( state.backtracking==0 ) {indexes.add(index);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1243:53: ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )*
                            loop125:
                            do {
                                int alt125=2;
                                int LA125_0 = input.LA(1);

                                if ( (LA125_0==COMMA) ) {
                                    int LA125_1 = input.LA(2);

                                    if ( (synpred19_TextMarkerParser()) ) {
                                        alt125=1;
                                    }


                                }


                                switch (alt125) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1243:54: ( COMMA index= numberExpression )=> ( COMMA index= numberExpression )
                            	    {
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1243:88: ( COMMA index= numberExpression )
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1243:89: COMMA index= numberExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_actionGather6993); if (state.failed) return action;

                            	    pushFollow(FOLLOW_numberExpression_in_actionGather6999);
                            	    index=numberExpression();

                            	    state._fsp--;
                            	    if (state.failed) return action;

                            	    }


                            	    if ( state.backtracking==0 ) {indexes.add(index);}

                            	    }
                            	    break;

                            	default :
                            	    break loop125;
                                }
                            } while (true);


                            match(input,COMMA,FOLLOW_COMMA_in_actionGather7006); if (state.failed) return action;

                            }
                            break;

                    }


                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1244:5: (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )* )?
                    int alt130=2;
                    int LA130_0 = input.LA(1);

                    if ( (LA130_0==Identifier||LA130_0==REMOVESTRING||LA130_0==StringLiteral) ) {
                        alt130=1;
                    }
                    switch (alt130) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1244:6: fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )*
                            {
                            pushFollow(FOLLOW_stringExpression_in_actionGather7019);
                            fname=stringExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionGather7021); if (state.failed) return action;

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1244:44: (obj1= numberExpression |obj2= numberListExpression )
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
                                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1244:45: obj1= numberExpression
                                    {
                                    pushFollow(FOLLOW_numberExpression_in_actionGather7028);
                                    obj1=numberExpression();

                                    state._fsp--;
                                    if (state.failed) return action;

                                    }
                                    break;
                                case 2 :
                                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1244:71: obj2= numberListExpression
                                    {
                                    pushFollow(FOLLOW_numberListExpression_in_actionGather7036);
                                    obj2=numberListExpression();

                                    state._fsp--;
                                    if (state.failed) return action;

                                    }
                                    break;

                            }


                            if ( state.backtracking==0 ) {left.add(fname); right.add(obj1 != null? obj1 : obj2);}

                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1245:5: ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression ) )*
                            loop129:
                            do {
                                int alt129=2;
                                int LA129_0 = input.LA(1);

                                if ( (LA129_0==COMMA) ) {
                                    alt129=1;
                                }


                                switch (alt129) {
                            	case 1 :
                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1245:6: COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression |obj2= numberListExpression )
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_actionGather7047); if (state.failed) return action;

                            	    pushFollow(FOLLOW_stringExpression_in_actionGather7053);
                            	    fname=stringExpression();

                            	    state._fsp--;
                            	    if (state.failed) return action;

                            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionGather7055); if (state.failed) return action;

                            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1245:50: (obj1= numberExpression |obj2= numberListExpression )
                            	    int alt128=2;
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
                            	        alt128=1;
                            	        }
                            	        break;
                            	    case Identifier:
                            	        {
                            	        int LA128_2 = input.LA(2);

                            	        if ( (((isVariableOfType(input.LT(1).getText(), "INT"))||(isVariableOfType(input.LT(1).getText(), "DOUBLE"))||(isVariableOfType(input.LT(1).getText(), "NUMBERFUNCTION"))||(isVariableOfType(input.LT(1).getText(), "FLOAT")))) ) {
                            	            alt128=1;
                            	        }
                            	        else if ( (((isVariableOfType(input.LT(1).getText(), "INTLIST"))||(isVariableOfType(input.LT(1).getText(), "DOUBLELIST"))||(isVariableOfType(input.LT(1).getText(), "FLOATLIST")))) ) {
                            	            alt128=2;
                            	        }
                            	        else {
                            	            if (state.backtracking>0) {state.failed=true; return action;}
                            	            NoViableAltException nvae =
                            	                new NoViableAltException("", 128, 2, input);

                            	            throw nvae;

                            	        }
                            	        }
                            	        break;
                            	    case LCURLY:
                            	        {
                            	        alt128=2;
                            	        }
                            	        break;
                            	    default:
                            	        if (state.backtracking>0) {state.failed=true; return action;}
                            	        NoViableAltException nvae =
                            	            new NoViableAltException("", 128, 0, input);

                            	        throw nvae;

                            	    }

                            	    switch (alt128) {
                            	        case 1 :
                            	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1245:51: obj1= numberExpression
                            	            {
                            	            pushFollow(FOLLOW_numberExpression_in_actionGather7062);
                            	            obj1=numberExpression();

                            	            state._fsp--;
                            	            if (state.failed) return action;

                            	            }
                            	            break;
                            	        case 2 :
                            	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1245:77: obj2= numberListExpression
                            	            {
                            	            pushFollow(FOLLOW_numberListExpression_in_actionGather7070);
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
                            	    break loop129;
                                }
                            } while (true);


                            }
                            break;

                    }


                    }
                    break;

            }


            if ( state.backtracking==0 ) {action = ActionFactory.createStructureAction(name, structure, indexes, left, right);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionGather7102); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1253:1: actionFill returns [TextMarkerAction action = null] : name= FILL LPAREN structure= typeExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )+ RPAREN ;
    public final TextMarkerAction actionFill() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression structure =null;

        Expression fname =null;

        Expression obj1 =null;



            List left = new ArrayList();
            List right = new ArrayList();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1258:5: (name= FILL LPAREN structure= typeExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )+ RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1259:5: name= FILL LPAREN structure= typeExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )+ RPAREN
            {
            name=(Token)match(input,FILL,FOLLOW_FILL_in_actionFill7137); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionFill7139); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionFill7145);
            structure=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createStructureAction(name, structure, null, left, right);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1261:5: ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )+
            int cnt132=0;
            loop132:
            do {
                int alt132=2;
                int LA132_0 = input.LA(1);

                if ( (LA132_0==COMMA) ) {
                    alt132=1;
                }


                switch (alt132) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1262:5: COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionFill7163); if (state.failed) return action;

            	    pushFollow(FOLLOW_stringExpression_in_actionFill7169);
            	    fname=stringExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionFill7171); if (state.failed) return action;

            	    pushFollow(FOLLOW_argument_in_actionFill7181);
            	    obj1=argument();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {left.add(fname); right.add(obj1);}

            	    }
            	    break;

            	default :
            	    if ( cnt132 >= 1 ) break loop132;
            	    if (state.backtracking>0) {state.failed=true; return action;}
                        EarlyExitException eee =
                            new EarlyExitException(132, input);
                        throw eee;
                }
                cnt132++;
            } while (true);


            if ( state.backtracking==0 ) {action = ActionFactory.createStructureAction(name, structure, null, left, right);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionFill7203); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1270:1: actionColor returns [TextMarkerAction action = null] : name= COLOR LPAREN type= typeExpression COMMA bgcolor= stringExpression ( COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )? )? RPAREN ;
    public final TextMarkerAction actionColor() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression type =null;

        Expression bgcolor =null;

        Expression fgcolor =null;

        Expression selected =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1271:5: (name= COLOR LPAREN type= typeExpression COMMA bgcolor= stringExpression ( COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1272:5: name= COLOR LPAREN type= typeExpression COMMA bgcolor= stringExpression ( COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )? )? RPAREN
            {
            name=(Token)match(input,COLOR,FOLLOW_COLOR_in_actionColor7240); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionColor7242); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionColor7248);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, type, bgcolor, fgcolor, selected);}

            match(input,COMMA,FOLLOW_COMMA_in_actionColor7262); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionColor7273);
            bgcolor=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, type, bgcolor, fgcolor, selected);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1277:5: ( COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )? )?
            int alt134=2;
            int LA134_0 = input.LA(1);

            if ( (LA134_0==COMMA) ) {
                alt134=1;
            }
            switch (alt134) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1277:6: COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionColor7287); if (state.failed) return action;

                    pushFollow(FOLLOW_stringExpression_in_actionColor7297);
                    fgcolor=stringExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, type, bgcolor, fgcolor, selected);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1280:5: ( COMMA selected= booleanExpression )?
                    int alt133=2;
                    int LA133_0 = input.LA(1);

                    if ( (LA133_0==COMMA) ) {
                        alt133=1;
                    }
                    switch (alt133) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1280:6: COMMA selected= booleanExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_actionColor7311); if (state.failed) return action;

                            pushFollow(FOLLOW_booleanExpression_in_actionColor7321);
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

            match(input,RPAREN,FOLLOW_RPAREN_in_actionColor7337); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1286:1: actionDel returns [TextMarkerAction action = null] : name= DEL ;
    public final TextMarkerAction actionDel() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1287:5: (name= DEL )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1288:5: name= DEL
            {
            name=(Token)match(input,DEL,FOLLOW_DEL_in_actionDel7369); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1292:1: actionLog returns [TextMarkerAction action = null] : name= LOG LPAREN lit= stringExpression ( COMMA log= LogLevel )? RPAREN ;
    public final TextMarkerAction actionLog() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Token log=null;
        Expression lit =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1293:5: (name= LOG LPAREN lit= stringExpression ( COMMA log= LogLevel )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1294:5: name= LOG LPAREN lit= stringExpression ( COMMA log= LogLevel )? RPAREN
            {
            name=(Token)match(input,LOG,FOLLOW_LOG_in_actionLog7415); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionLog7417); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionLog7423);
            lit=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1294:46: ( COMMA log= LogLevel )?
            int alt135=2;
            int LA135_0 = input.LA(1);

            if ( (LA135_0==COMMA) ) {
                alt135=1;
            }
            switch (alt135) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1294:47: COMMA log= LogLevel
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionLog7426); if (state.failed) return action;

                    log=(Token)match(input,LogLevel,FOLLOW_LogLevel_in_actionLog7432); if (state.failed) return action;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {action = ActionFactory.createLogAction(name, lit, log);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionLog7448); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1299:1: actionMark returns [TextMarkerAction action = null] : name= MARK LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN ;
    public final TextMarkerAction actionMark() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression type =null;

        Expression index =null;



        List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1303:5: (name= MARK LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1304:5: name= MARK LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN
            {
            name=(Token)match(input,MARK,FOLLOW_MARK_in_actionMark7486); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMark7488); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionMark7499);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {list.add(type);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1307:5: ( COMMA (index= numberExpression )=>index= numberExpression )*
            loop136:
            do {
                int alt136=2;
                int LA136_0 = input.LA(1);

                if ( (LA136_0==COMMA) ) {
                    alt136=1;
                }


                switch (alt136) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1308:5: COMMA (index= numberExpression )=>index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMark7517); if (state.failed) return action;

            	    pushFollow(FOLLOW_numberExpression_in_actionMark7533);
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

            match(input,RPAREN,FOLLOW_RPAREN_in_actionMark7555); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1314:1: actionExpand returns [TextMarkerAction action = null] : name= EXPAND LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN ;
    public final TextMarkerAction actionExpand() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression type =null;

        Expression index =null;



        List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1318:5: (name= EXPAND LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1319:5: name= EXPAND LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN
            {
            name=(Token)match(input,EXPAND,FOLLOW_EXPAND_in_actionExpand7592); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionExpand7594); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionExpand7605);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {list.add(type);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1322:5: ( COMMA (index= numberExpression )=>index= numberExpression )*
            loop137:
            do {
                int alt137=2;
                int LA137_0 = input.LA(1);

                if ( (LA137_0==COMMA) ) {
                    alt137=1;
                }


                switch (alt137) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1323:5: COMMA (index= numberExpression )=>index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionExpand7623); if (state.failed) return action;

            	    pushFollow(FOLLOW_numberExpression_in_actionExpand7639);
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

            match(input,RPAREN,FOLLOW_RPAREN_in_actionExpand7661); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1329:1: actionMarkScore returns [TextMarkerAction action = null] : name= MARKSCORE LPAREN score= numberExpression COMMA type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN ;
    public final TextMarkerAction actionMarkScore() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression score =null;

        Expression type =null;

        Expression index =null;



        List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1333:5: (name= MARKSCORE LPAREN score= numberExpression COMMA type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1334:5: name= MARKSCORE LPAREN score= numberExpression COMMA type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN
            {
            name=(Token)match(input,MARKSCORE,FOLLOW_MARKSCORE_in_actionMarkScore7698); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkScore7700); if (state.failed) return action;

            pushFollow(FOLLOW_numberExpression_in_actionMarkScore7706);
            score=numberExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionMarkScore7708); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionMarkScore7714);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {list.add(score); list.add(type);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1336:5: ( COMMA (index= numberExpression )=>index= numberExpression )*
            loop138:
            do {
                int alt138=2;
                int LA138_0 = input.LA(1);

                if ( (LA138_0==COMMA) ) {
                    alt138=1;
                }


                switch (alt138) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1337:5: COMMA (index= numberExpression )=>index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMarkScore7732); if (state.failed) return action;

            	    pushFollow(FOLLOW_numberExpression_in_actionMarkScore7748);
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


            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, list);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkScore7770); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1343:1: actionMarkOnce returns [TextMarkerAction action = null] : name= MARKONCE LPAREN ( (score= numberExpression )=>score= numberExpression COMMA )? (type= typeExpression )=>type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN ;
    public final TextMarkerAction actionMarkOnce() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression score =null;

        Expression type =null;

        Expression index =null;



        List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1347:5: (name= MARKONCE LPAREN ( (score= numberExpression )=>score= numberExpression COMMA )? (type= typeExpression )=>type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1348:5: name= MARKONCE LPAREN ( (score= numberExpression )=>score= numberExpression COMMA )? (type= typeExpression )=>type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN
            {
            name=(Token)match(input,MARKONCE,FOLLOW_MARKONCE_in_actionMarkOnce7807); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkOnce7809); if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1348:28: ( (score= numberExpression )=>score= numberExpression COMMA )?
            int alt139=2;
            int LA139_0 = input.LA(1);

            if ( (LA139_0==MINUS) && (synpred23_TextMarkerParser())) {
                alt139=1;
            }
            else if ( (LA139_0==Identifier) ) {
                int LA139_2 = input.LA(2);

                if ( (((((isVariableOfType(input.LT(1).getText(), "INT"))||(isVariableOfType(input.LT(1).getText(), "DOUBLE"))||(isVariableOfType(input.LT(1).getText(), "NUMBERFUNCTION"))||(isVariableOfType(input.LT(1).getText(), "FLOAT")))&&((isVariableOfType(input.LT(1).getText(), "INT"))||(isVariableOfType(input.LT(1).getText(), "DOUBLE"))||(isVariableOfType(input.LT(1).getText(), "NUMBERFUNCTION"))||(isVariableOfType(input.LT(1).getText(), "FLOAT"))))&&synpred23_TextMarkerParser())) ) {
                    alt139=1;
                }
            }
            else if ( (LA139_0==DecimalLiteral) && (synpred23_TextMarkerParser())) {
                alt139=1;
            }
            else if ( (LA139_0==FloatingPointLiteral) && (synpred23_TextMarkerParser())) {
                alt139=1;
            }
            else if ( (LA139_0==LPAREN) && (synpred23_TextMarkerParser())) {
                alt139=1;
            }
            else if ( (LA139_0==COS||LA139_0==EXP||LA139_0==LOGN||LA139_0==SIN||LA139_0==TAN) && (synpred23_TextMarkerParser())) {
                alt139=1;
            }
            switch (alt139) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1348:29: (score= numberExpression )=>score= numberExpression COMMA
                    {
                    pushFollow(FOLLOW_numberExpression_in_actionMarkOnce7826);
                    score=numberExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    match(input,COMMA,FOLLOW_COMMA_in_actionMarkOnce7828); if (state.failed) return action;

                    }
                    break;

            }


            pushFollow(FOLLOW_typeExpression_in_actionMarkOnce7846);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {list.add(score); list.add(type);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1350:5: ( COMMA (index= numberExpression )=>index= numberExpression )*
            loop140:
            do {
                int alt140=2;
                int LA140_0 = input.LA(1);

                if ( (LA140_0==COMMA) ) {
                    alt140=1;
                }


                switch (alt140) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1351:5: COMMA (index= numberExpression )=>index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMarkOnce7864); if (state.failed) return action;

            	    pushFollow(FOLLOW_numberExpression_in_actionMarkOnce7880);
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


            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, list);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkOnce7902); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1357:1: actionMarkFast returns [TextMarkerAction action = null] : name= MARKFAST LPAREN type= typeExpression COMMA list= wordListExpression ( COMMA ignore= booleanExpression ( COMMA numExpr= numberExpression )? )? RPAREN ;
    public final TextMarkerAction actionMarkFast() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression type =null;

        Expression list =null;

        Expression ignore =null;

        Expression numExpr =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1358:5: (name= MARKFAST LPAREN type= typeExpression COMMA list= wordListExpression ( COMMA ignore= booleanExpression ( COMMA numExpr= numberExpression )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1359:5: name= MARKFAST LPAREN type= typeExpression COMMA list= wordListExpression ( COMMA ignore= booleanExpression ( COMMA numExpr= numberExpression )? )? RPAREN
            {
            name=(Token)match(input,MARKFAST,FOLLOW_MARKFAST_in_actionMarkFast7934); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkFast7936); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionMarkFast7942);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, type, list, ignore, numExpr);}

            match(input,COMMA,FOLLOW_COMMA_in_actionMarkFast7955); if (state.failed) return action;

            pushFollow(FOLLOW_wordListExpression_in_actionMarkFast7961);
            list=wordListExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, type, list, ignore, numExpr);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1363:5: ( COMMA ignore= booleanExpression ( COMMA numExpr= numberExpression )? )?
            int alt142=2;
            int LA142_0 = input.LA(1);

            if ( (LA142_0==COMMA) ) {
                alt142=1;
            }
            switch (alt142) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1363:6: COMMA ignore= booleanExpression ( COMMA numExpr= numberExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionMarkFast7975); if (state.failed) return action;

                    pushFollow(FOLLOW_booleanExpression_in_actionMarkFast7981);
                    ignore=booleanExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1363:39: ( COMMA numExpr= numberExpression )?
                    int alt141=2;
                    int LA141_0 = input.LA(1);

                    if ( (LA141_0==COMMA) ) {
                        alt141=1;
                    }
                    switch (alt141) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1363:40: COMMA numExpr= numberExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_actionMarkFast7984); if (state.failed) return action;

                            pushFollow(FOLLOW_numberExpression_in_actionMarkFast7990);
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

            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkFast8008); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1368:1: actionMarkLast returns [TextMarkerAction action = null] : name= MARKLAST LPAREN type= typeExpression RPAREN ;
    public final TextMarkerAction actionMarkLast() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression type =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1369:5: (name= MARKLAST LPAREN type= typeExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1370:5: name= MARKLAST LPAREN type= typeExpression RPAREN
            {
            name=(Token)match(input,MARKLAST,FOLLOW_MARKLAST_in_actionMarkLast8040); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkLast8042); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionMarkLast8048);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, type);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkLast8061); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1376:1: actionReplace returns [TextMarkerAction action = null] : name= REPLACE LPAREN lit= stringExpression RPAREN ;
    public final TextMarkerAction actionReplace() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression lit =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1377:5: (name= REPLACE LPAREN lit= stringExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1378:5: name= REPLACE LPAREN lit= stringExpression RPAREN
            {
            name=(Token)match(input,REPLACE,FOLLOW_REPLACE_in_actionReplace8094); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionReplace8096); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionReplace8102);
            lit=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, lit);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionReplace8115); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1383:1: actionRetainMarkup returns [TextMarkerAction action = null] : name= RETAINMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )? ;
    public final TextMarkerAction actionRetainMarkup() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression id =null;



        List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1387:5: (name= RETAINMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1388:5: name= RETAINMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )?
            {
            name=(Token)match(input,RETAINMARKUP,FOLLOW_RETAINMARKUP_in_actionRetainMarkup8152); if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1388:25: ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )?
            int alt144=2;
            int LA144_0 = input.LA(1);

            if ( (LA144_0==LPAREN) ) {
                alt144=1;
            }
            switch (alt144) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1388:26: LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_actionRetainMarkup8155); if (state.failed) return action;

                    pushFollow(FOLLOW_stringExpression_in_actionRetainMarkup8161);
                    id=stringExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {list.add(id);}

                    if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, list);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1390:5: ( COMMA id= stringExpression )*
                    loop143:
                    do {
                        int alt143=2;
                        int LA143_0 = input.LA(1);

                        if ( (LA143_0==COMMA) ) {
                            alt143=1;
                        }


                        switch (alt143) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1390:6: COMMA id= stringExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionRetainMarkup8177); if (state.failed) return action;

                    	    pushFollow(FOLLOW_stringExpression_in_actionRetainMarkup8183);
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


                    if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, list);}

                    match(input,RPAREN,FOLLOW_RPAREN_in_actionRetainMarkup8200); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1396:1: actionRetainType returns [TextMarkerAction action = null] : name= RETAINTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )? ;
    public final TextMarkerAction actionRetainType() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression id =null;



        List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1400:5: (name= RETAINTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1401:5: name= RETAINTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )?
            {
            name=(Token)match(input,RETAINTYPE,FOLLOW_RETAINTYPE_in_actionRetainType8253); if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1401:23: ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )?
            int alt146=2;
            int LA146_0 = input.LA(1);

            if ( (LA146_0==LPAREN) ) {
                alt146=1;
            }
            switch (alt146) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1401:24: LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_actionRetainType8256); if (state.failed) return action;

                    pushFollow(FOLLOW_typeExpression_in_actionRetainType8262);
                    id=typeExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {list.add(id);}

                    if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, list);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1403:5: ( COMMA id= typeExpression )*
                    loop145:
                    do {
                        int alt145=2;
                        int LA145_0 = input.LA(1);

                        if ( (LA145_0==COMMA) ) {
                            alt145=1;
                        }


                        switch (alt145) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1403:6: COMMA id= typeExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionRetainType8278); if (state.failed) return action;

                    	    pushFollow(FOLLOW_typeExpression_in_actionRetainType8284);
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


                    if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, list);}

                    match(input,RPAREN,FOLLOW_RPAREN_in_actionRetainType8301); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1409:1: actionFilterMarkup returns [TextMarkerAction action = null] : name= FILTERMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )? ;
    public final TextMarkerAction actionFilterMarkup() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression id =null;



        List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1413:5: (name= FILTERMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1414:5: name= FILTERMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )?
            {
            name=(Token)match(input,FILTERMARKUP,FOLLOW_FILTERMARKUP_in_actionFilterMarkup8350); if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1414:25: ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )?
            int alt148=2;
            int LA148_0 = input.LA(1);

            if ( (LA148_0==LPAREN) ) {
                alt148=1;
            }
            switch (alt148) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1414:26: LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_actionFilterMarkup8353); if (state.failed) return action;

                    pushFollow(FOLLOW_stringExpression_in_actionFilterMarkup8359);
                    id=stringExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {list.add(id);}

                    if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, list);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1416:5: ( COMMA id= stringExpression )*
                    loop147:
                    do {
                        int alt147=2;
                        int LA147_0 = input.LA(1);

                        if ( (LA147_0==COMMA) ) {
                            alt147=1;
                        }


                        switch (alt147) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1416:6: COMMA id= stringExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionFilterMarkup8375); if (state.failed) return action;

                    	    pushFollow(FOLLOW_stringExpression_in_actionFilterMarkup8381);
                    	    id=stringExpression();

                    	    state._fsp--;
                    	    if (state.failed) return action;

                    	    if ( state.backtracking==0 ) {list.add(id);}

                    	    }
                    	    break;

                    	default :
                    	    break loop147;
                        }
                    } while (true);


                    if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, list);}

                    match(input,RPAREN,FOLLOW_RPAREN_in_actionFilterMarkup8398); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1422:1: actionFilterType returns [TextMarkerAction action = null] : name= FILTERTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )? ;
    public final TextMarkerAction actionFilterType() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression id =null;



        List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1426:5: (name= FILTERTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1427:5: name= FILTERTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )?
            {
            name=(Token)match(input,FILTERTYPE,FOLLOW_FILTERTYPE_in_actionFilterType8443); if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1427:23: ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )?
            int alt150=2;
            int LA150_0 = input.LA(1);

            if ( (LA150_0==LPAREN) ) {
                alt150=1;
            }
            switch (alt150) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1427:24: LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_actionFilterType8446); if (state.failed) return action;

                    pushFollow(FOLLOW_typeExpression_in_actionFilterType8452);
                    id=typeExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {list.add(id);}

                    if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, list);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1429:5: ( COMMA id= typeExpression )*
                    loop149:
                    do {
                        int alt149=2;
                        int LA149_0 = input.LA(1);

                        if ( (LA149_0==COMMA) ) {
                            alt149=1;
                        }


                        switch (alt149) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1429:6: COMMA id= typeExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionFilterType8468); if (state.failed) return action;

                    	    pushFollow(FOLLOW_typeExpression_in_actionFilterType8474);
                    	    id=typeExpression();

                    	    state._fsp--;
                    	    if (state.failed) return action;

                    	    if ( state.backtracking==0 ) {list.add(id);}

                    	    }
                    	    break;

                    	default :
                    	    break loop149;
                        }
                    } while (true);


                    if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, list);}

                    match(input,RPAREN,FOLLOW_RPAREN_in_actionFilterType8491); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1435:1: actionCall returns [TextMarkerAction action = null] : name= CALL lp= LPAREN ns= dottedComponentReference RPAREN ;
    public final TextMarkerAction actionCall() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Token lp=null;
        ComponentReference ns =null;



        String string = "";


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1440:5: (name= CALL lp= LPAREN ns= dottedComponentReference RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1441:5: name= CALL lp= LPAREN ns= dottedComponentReference RPAREN
            {
            name=(Token)match(input,CALL,FOLLOW_CALL_in_actionCall8540); if (state.failed) return action;

            lp=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_actionCall8546); if (state.failed) return action;

            if ( state.backtracking==0 ) {   action = ActionFactory.createCallAction(name, StatementFactory.createEmtpyComponentReference(lp));}

            pushFollow(FOLLOW_dottedComponentReference_in_actionCall8568);
            ns=dottedComponentReference();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {   if(ns != null) {action = ActionFactory.createCallAction(name, ns);}}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionCall8582); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1450:1: actionConfigure returns [TextMarkerAction action = null] : name= CONFIGURE lp= LPAREN ns= dottedComponentReference ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? RPAREN ;
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1455:5: (name= CONFIGURE lp= LPAREN ns= dottedComponentReference ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1456:5: name= CONFIGURE lp= LPAREN ns= dottedComponentReference ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? RPAREN
            {
            name=(Token)match(input,CONFIGURE,FOLLOW_CONFIGURE_in_actionConfigure8617); if (state.failed) return action;

            lp=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_actionConfigure8623); if (state.failed) return action;

            if ( state.backtracking==0 ) {   action = ActionFactory.createConfigureAction(name, StatementFactory.createEmtpyComponentReference(lp), null , null);}

            pushFollow(FOLLOW_dottedComponentReference_in_actionConfigure8645);
            ns=dottedComponentReference();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {   if(ns != null) {action = ActionFactory.createConfigureAction(name, ns, null , null);}}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1462:6: ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )?
            int alt152=2;
            int LA152_0 = input.LA(1);

            if ( (LA152_0==COMMA) ) {
                alt152=1;
            }
            switch (alt152) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1462:7: COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )*
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionConfigure8666); if (state.failed) return action;

                    pushFollow(FOLLOW_stringExpression_in_actionConfigure8672);
                    fname=stringExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionConfigure8674); if (state.failed) return action;

                    pushFollow(FOLLOW_argument_in_actionConfigure8680);
                    obj1=argument();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {left.add(fname); right.add(obj1);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1463:5: ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )*
                    loop151:
                    do {
                        int alt151=2;
                        int LA151_0 = input.LA(1);

                        if ( (LA151_0==COMMA) ) {
                            alt151=1;
                        }


                        switch (alt151) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1463:6: COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionConfigure8690); if (state.failed) return action;

                    	    pushFollow(FOLLOW_stringExpression_in_actionConfigure8696);
                    	    fname=stringExpression();

                    	    state._fsp--;
                    	    if (state.failed) return action;

                    	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionConfigure8698); if (state.failed) return action;

                    	    pushFollow(FOLLOW_argument_in_actionConfigure8704);
                    	    obj1=argument();

                    	    state._fsp--;
                    	    if (state.failed) return action;

                    	    if ( state.backtracking==0 ) {left.add(fname);right.add(obj1);}

                    	    }
                    	    break;

                    	default :
                    	    break loop151;
                        }
                    } while (true);


                    }
                    break;

            }


            if ( state.backtracking==0 ) {   action = ActionFactory.createConfigureAction(name, ns, left , right);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionConfigure8728); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1470:1: actionExec returns [TextMarkerAction action = null] : name= EXEC lp= LPAREN ns= dottedComponentReference ( COMMA tl= typeListExpression )? RPAREN ;
    public final TextMarkerAction actionExec() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Token lp=null;
        ComponentReference ns =null;

        Expression tl =null;



        String string = "";

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1474:5: (name= EXEC lp= LPAREN ns= dottedComponentReference ( COMMA tl= typeListExpression )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1475:5: name= EXEC lp= LPAREN ns= dottedComponentReference ( COMMA tl= typeListExpression )? RPAREN
            {
            name=(Token)match(input,EXEC,FOLLOW_EXEC_in_actionExec8763); if (state.failed) return action;

            lp=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_actionExec8769); if (state.failed) return action;

            if ( state.backtracking==0 ) {   action = ActionFactory.createCallAction(name, StatementFactory.createEmtpyComponentReference(lp));}

            pushFollow(FOLLOW_dottedComponentReference_in_actionExec8787);
            ns=dottedComponentReference();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {   if(ns != null) {action = ActionFactory.createCallAction(name, ns, null);}}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1479:6: ( COMMA tl= typeListExpression )?
            int alt153=2;
            int LA153_0 = input.LA(1);

            if ( (LA153_0==COMMA) ) {
                alt153=1;
            }
            switch (alt153) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1479:7: COMMA tl= typeListExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionExec8803); if (state.failed) return action;

                    pushFollow(FOLLOW_typeListExpression_in_actionExec8809);
                    tl=typeListExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {   if(ns != null) {action = ActionFactory.createCallAction(name, ns, tl);}}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionExec8825); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1485:1: actionAssign returns [TextMarkerAction action = null] : name= ASSIGN LPAREN (id= Identifier COMMA e= argument ) RPAREN ;
    public final TextMarkerAction actionAssign() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Token id=null;
        Expression e =null;



            VariableReference ref = null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1489:5: (name= ASSIGN LPAREN (id= Identifier COMMA e= argument ) RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1490:5: name= ASSIGN LPAREN (id= Identifier COMMA e= argument ) RPAREN
            {
            name=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_actionAssign8867); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionAssign8869); if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1491:5: (id= Identifier COMMA e= argument )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1491:6: id= Identifier COMMA e= argument
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_actionAssign8880); if (state.failed) return action;

            if ( state.backtracking==0 ) {
                ref = ExpressionFactory.createGenericVariableReference(id);
                action = ActionFactory.createAction(name, ref, e);}

            match(input,COMMA,FOLLOW_COMMA_in_actionAssign8898); if (state.failed) return action;

            pushFollow(FOLLOW_argument_in_actionAssign8904);
            e=argument();

            state._fsp--;
            if (state.failed) return action;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_actionAssign8912); if (state.failed) return action;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1504:1: actionSetFeature returns [TextMarkerAction action = null] : name= SETFEATURE LPAREN f= stringExpression COMMA v= argument RPAREN ;
    public final TextMarkerAction actionSetFeature() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression f =null;

        Expression v =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1505:5: (name= SETFEATURE LPAREN f= stringExpression COMMA v= argument RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1506:5: name= SETFEATURE LPAREN f= stringExpression COMMA v= argument RPAREN
            {
            name=(Token)match(input,SETFEATURE,FOLLOW_SETFEATURE_in_actionSetFeature8949); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionSetFeature8951); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionSetFeature8957);
            f=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f, v);}

            match(input,COMMA,FOLLOW_COMMA_in_actionSetFeature8971); if (state.failed) return action;

            pushFollow(FOLLOW_argument_in_actionSetFeature8977);
            v=argument();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f, v);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionSetFeature8990); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1513:1: actionGetFeature returns [TextMarkerAction action = null] : name= GETFEATURE LPAREN f= stringExpression COMMA v= variable RPAREN ;
    public final TextMarkerAction actionGetFeature() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression f =null;

        Expression v =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1514:5: (name= GETFEATURE LPAREN f= stringExpression COMMA v= variable RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1515:5: name= GETFEATURE LPAREN f= stringExpression COMMA v= variable RPAREN
            {
            name=(Token)match(input,GETFEATURE,FOLLOW_GETFEATURE_in_actionGetFeature9019); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionGetFeature9021); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionGetFeature9027);
            f=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f, v);}

            match(input,COMMA,FOLLOW_COMMA_in_actionGetFeature9040); if (state.failed) return action;

            pushFollow(FOLLOW_variable_in_actionGetFeature9046);
            v=variable();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f, v);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionGetFeature9059); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1523:1: actionDynamicAnchoring returns [TextMarkerAction action = null] : name= DYNAMICANCHORING LPAREN active= booleanExpression ( COMMA penalty= numberExpression ( COMMA factor= numberExpression )? )? RPAREN ;
    public final TextMarkerAction actionDynamicAnchoring() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression active =null;

        Expression penalty =null;

        Expression factor =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1524:5: (name= DYNAMICANCHORING LPAREN active= booleanExpression ( COMMA penalty= numberExpression ( COMMA factor= numberExpression )? )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1525:5: name= DYNAMICANCHORING LPAREN active= booleanExpression ( COMMA penalty= numberExpression ( COMMA factor= numberExpression )? )? RPAREN
            {
            name=(Token)match(input,DYNAMICANCHORING,FOLLOW_DYNAMICANCHORING_in_actionDynamicAnchoring9089); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionDynamicAnchoring9091); if (state.failed) return action;

            pushFollow(FOLLOW_booleanExpression_in_actionDynamicAnchoring9097);
            active=booleanExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, active);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1527:5: ( COMMA penalty= numberExpression ( COMMA factor= numberExpression )? )?
            int alt155=2;
            int LA155_0 = input.LA(1);

            if ( (LA155_0==COMMA) ) {
                alt155=1;
            }
            switch (alt155) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1527:6: COMMA penalty= numberExpression ( COMMA factor= numberExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionDynamicAnchoring9112); if (state.failed) return action;

                    pushFollow(FOLLOW_numberExpression_in_actionDynamicAnchoring9118);
                    penalty=numberExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, active, penalty);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1529:5: ( COMMA factor= numberExpression )?
                    int alt154=2;
                    int LA154_0 = input.LA(1);

                    if ( (LA154_0==COMMA) ) {
                        alt154=1;
                    }
                    switch (alt154) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1529:6: COMMA factor= numberExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_actionDynamicAnchoring9132); if (state.failed) return action;

                            pushFollow(FOLLOW_numberExpression_in_actionDynamicAnchoring9138);
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

            match(input,RPAREN,FOLLOW_RPAREN_in_actionDynamicAnchoring9155); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1535:1: actionUnmark returns [TextMarkerAction action = null] : name= UNMARK LPAREN f= typeExpression RPAREN ;
    public final TextMarkerAction actionUnmark() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression f =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1536:5: (name= UNMARK LPAREN f= typeExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1537:5: name= UNMARK LPAREN f= typeExpression RPAREN
            {
            name=(Token)match(input,UNMARK,FOLLOW_UNMARK_in_actionUnmark9185); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionUnmark9187); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionUnmark9193);
            f=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionUnmark9206); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1542:1: actionUnmarkAll returns [TextMarkerAction action = null] : name= UNMARKALL LPAREN f= typeExpression ( COMMA list= typeListExpression )? RPAREN ;
    public final TextMarkerAction actionUnmarkAll() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression f =null;

        Expression list =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1543:5: (name= UNMARKALL LPAREN f= typeExpression ( COMMA list= typeListExpression )? RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1544:5: name= UNMARKALL LPAREN f= typeExpression ( COMMA list= typeListExpression )? RPAREN
            {
            name=(Token)match(input,UNMARKALL,FOLLOW_UNMARKALL_in_actionUnmarkAll9235); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionUnmarkAll9237); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionUnmarkAll9243);
            f=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f, list);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1546:5: ( COMMA list= typeListExpression )?
            int alt156=2;
            int LA156_0 = input.LA(1);

            if ( (LA156_0==COMMA) ) {
                alt156=1;
            }
            switch (alt156) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1546:6: COMMA list= typeListExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionUnmarkAll9257); if (state.failed) return action;

                    pushFollow(FOLLOW_typeListExpression_in_actionUnmarkAll9263);
                    list=typeListExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f, list);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionUnmarkAll9278); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1554:1: actionTransfer returns [TextMarkerAction action = null] : name= TRANSFER LPAREN f= typeExpression RPAREN ;
    public final TextMarkerAction actionTransfer() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression f =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1555:5: (name= TRANSFER LPAREN f= typeExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1556:5: name= TRANSFER LPAREN f= typeExpression RPAREN
            {
            name=(Token)match(input,TRANSFER,FOLLOW_TRANSFER_in_actionTransfer9310); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionTransfer9312); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionTransfer9318);
            f=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionTransfer9331); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1561:1: actionTrie returns [TextMarkerAction action = null] : name= TRIE LPAREN key= stringExpression ASSIGN_EQUAL value= typeExpression ( COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression )* COMMA list= wordListExpression COMMA ignoreCase= booleanExpression COMMA ignoreLength= numberExpression COMMA edit= booleanExpression COMMA distance= numberExpression COMMA ignoreChar= stringExpression RPAREN ;
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1567:5: (name= TRIE LPAREN key= stringExpression ASSIGN_EQUAL value= typeExpression ( COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression )* COMMA list= wordListExpression COMMA ignoreCase= booleanExpression COMMA ignoreLength= numberExpression COMMA edit= booleanExpression COMMA distance= numberExpression COMMA ignoreChar= stringExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1568:5: name= TRIE LPAREN key= stringExpression ASSIGN_EQUAL value= typeExpression ( COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression )* COMMA list= wordListExpression COMMA ignoreCase= booleanExpression COMMA ignoreLength= numberExpression COMMA edit= booleanExpression COMMA distance= numberExpression COMMA ignoreChar= stringExpression RPAREN
            {
            name=(Token)match(input,TRIE,FOLLOW_TRIE_in_actionTrie9369); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionTrie9371); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionTrie9385);
            key=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {left.add(key);}

            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionTrie9388); if (state.failed) return action;

            pushFollow(FOLLOW_typeExpression_in_actionTrie9403);
            value=typeExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {right.add(value);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1571:9: ( COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression )*
            loop157:
            do {
                int alt157=2;
                int LA157_0 = input.LA(1);

                if ( (LA157_0==COMMA) ) {
                    int LA157_1 = input.LA(2);

                    if ( (LA157_1==Identifier) ) {
                        int LA157_2 = input.LA(3);

                        if ( (LA157_2==ASSIGN_EQUAL||LA157_2==LPAREN||LA157_2==PLUS) ) {
                            alt157=1;
                        }


                    }
                    else if ( (LA157_1==REMOVESTRING||LA157_1==StringLiteral) ) {
                        alt157=1;
                    }


                }


                switch (alt157) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1571:10: COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionTrie9416); if (state.failed) return action;

            	    pushFollow(FOLLOW_stringExpression_in_actionTrie9422);
            	    key=stringExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {left.add(key);}

            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionTrie9426); if (state.failed) return action;

            	    pushFollow(FOLLOW_typeExpression_in_actionTrie9441);
            	    value=typeExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {right.add(value);}

            	    }
            	    break;

            	default :
            	    break loop157;
                }
            } while (true);


            match(input,COMMA,FOLLOW_COMMA_in_actionTrie9455); if (state.failed) return action;

            pushFollow(FOLLOW_wordListExpression_in_actionTrie9461);
            list=wordListExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionTrie9477); if (state.failed) return action;

            pushFollow(FOLLOW_booleanExpression_in_actionTrie9483);
            ignoreCase=booleanExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionTrie9490); if (state.failed) return action;

            pushFollow(FOLLOW_numberExpression_in_actionTrie9496);
            ignoreLength=numberExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionTrie9503); if (state.failed) return action;

            pushFollow(FOLLOW_booleanExpression_in_actionTrie9509);
            edit=booleanExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionTrie9516); if (state.failed) return action;

            pushFollow(FOLLOW_numberExpression_in_actionTrie9522);
            distance=numberExpression();

            state._fsp--;
            if (state.failed) return action;

            match(input,COMMA,FOLLOW_COMMA_in_actionTrie9529); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionTrie9535);
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

            match(input,RPAREN,FOLLOW_RPAREN_in_actionTrie9558); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1595:1: actionAdd returns [TextMarkerAction action = null] : name= ADD LPAREN f= listVariable ( COMMA a= argument )+ RPAREN ;
    public final TextMarkerAction actionAdd() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression f =null;

        Expression a =null;



        	List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1599:5: (name= ADD LPAREN f= listVariable ( COMMA a= argument )+ RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1600:5: name= ADD LPAREN f= listVariable ( COMMA a= argument )+ RPAREN
            {
            name=(Token)match(input,ADD,FOLLOW_ADD_in_actionAdd9596); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionAdd9598); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionAdd9604);
            f=listVariable();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f, list);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1602:5: ( COMMA a= argument )+
            int cnt158=0;
            loop158:
            do {
                int alt158=2;
                int LA158_0 = input.LA(1);

                if ( (LA158_0==COMMA) ) {
                    alt158=1;
                }


                switch (alt158) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1602:6: COMMA a= argument
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionAdd9618); if (state.failed) return action;

            	    pushFollow(FOLLOW_argument_in_actionAdd9624);
            	    a=argument();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {list.add(a);}

            	    }
            	    break;

            	default :
            	    if ( cnt158 >= 1 ) break loop158;
            	    if (state.backtracking>0) {state.failed=true; return action;}
                        EarlyExitException eee =
                            new EarlyExitException(158, input);
                        throw eee;
                }
                cnt158++;
            } while (true);


            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f, list);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionAdd9641); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1607:1: actionRemove returns [TextMarkerAction action = null] : name= REMOVE LPAREN f= listVariable ( COMMA a= argument )+ RPAREN ;
    public final TextMarkerAction actionRemove() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression f =null;

        Expression a =null;



        	List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1611:5: (name= REMOVE LPAREN f= listVariable ( COMMA a= argument )+ RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1612:5: name= REMOVE LPAREN f= listVariable ( COMMA a= argument )+ RPAREN
            {
            name=(Token)match(input,REMOVE,FOLLOW_REMOVE_in_actionRemove9675); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionRemove9677); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionRemove9683);
            f=listVariable();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f, list);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1614:5: ( COMMA a= argument )+
            int cnt159=0;
            loop159:
            do {
                int alt159=2;
                int LA159_0 = input.LA(1);

                if ( (LA159_0==COMMA) ) {
                    alt159=1;
                }


                switch (alt159) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1614:6: COMMA a= argument
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionRemove9697); if (state.failed) return action;

            	    pushFollow(FOLLOW_argument_in_actionRemove9703);
            	    a=argument();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {list.add(a);}

            	    }
            	    break;

            	default :
            	    if ( cnt159 >= 1 ) break loop159;
            	    if (state.backtracking>0) {state.failed=true; return action;}
                        EarlyExitException eee =
                            new EarlyExitException(159, input);
                        throw eee;
                }
                cnt159++;
            } while (true);


            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f, list);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionRemove9720); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1620:1: actionRemoveDuplicate returns [TextMarkerAction action = null] : name= REMOVEDUPLICATE LPAREN f= listVariable RPAREN ;
    public final TextMarkerAction actionRemoveDuplicate() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression f =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1621:5: (name= REMOVEDUPLICATE LPAREN f= listVariable RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1622:5: name= REMOVEDUPLICATE LPAREN f= listVariable RPAREN
            {
            name=(Token)match(input,REMOVEDUPLICATE,FOLLOW_REMOVEDUPLICATE_in_actionRemoveDuplicate9750); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionRemoveDuplicate9752); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionRemoveDuplicate9758);
            f=listVariable();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionRemoveDuplicate9771); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1627:1: actionMerge returns [TextMarkerAction action = null] : name= MERGE LPAREN join= booleanExpression COMMA t= listVariable COMMA f= listExpression ( COMMA f= listExpression )+ RPAREN ;
    public final TextMarkerAction actionMerge() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression join =null;

        Expression t =null;

        Expression f =null;



        	List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1631:5: (name= MERGE LPAREN join= booleanExpression COMMA t= listVariable COMMA f= listExpression ( COMMA f= listExpression )+ RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1632:5: name= MERGE LPAREN join= booleanExpression COMMA t= listVariable COMMA f= listExpression ( COMMA f= listExpression )+ RPAREN
            {
            name=(Token)match(input,MERGE,FOLLOW_MERGE_in_actionMerge9808); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMerge9810); if (state.failed) return action;

            pushFollow(FOLLOW_booleanExpression_in_actionMerge9816);
            join=booleanExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, join, t, list);}

            match(input,COMMA,FOLLOW_COMMA_in_actionMerge9830); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionMerge9836);
            t=listVariable();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, join, t, list);}

            match(input,COMMA,FOLLOW_COMMA_in_actionMerge9850); if (state.failed) return action;

            pushFollow(FOLLOW_listExpression_in_actionMerge9856);
            f=listExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {list.add(f);}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1637:5: ( COMMA f= listExpression )+
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
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1637:6: COMMA f= listExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMerge9866); if (state.failed) return action;

            	    pushFollow(FOLLOW_listExpression_in_actionMerge9872);
            	    f=listExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {list.add(f);}

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


            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, join, t, list);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionMerge9889); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1642:1: actionGet returns [TextMarkerAction action = null] : name= GET LPAREN f= listExpression COMMA var= variable COMMA op= stringExpression RPAREN ;
    public final TextMarkerAction actionGet() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression f =null;

        Expression var =null;

        Expression op =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1643:5: (name= GET LPAREN f= listExpression COMMA var= variable COMMA op= stringExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1644:5: name= GET LPAREN f= listExpression COMMA var= variable COMMA op= stringExpression RPAREN
            {
            name=(Token)match(input,GET,FOLLOW_GET_in_actionGet9918); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionGet9920); if (state.failed) return action;

            pushFollow(FOLLOW_listExpression_in_actionGet9926);
            f=listExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f, var, op);}

            match(input,COMMA,FOLLOW_COMMA_in_actionGet9939); if (state.failed) return action;

            pushFollow(FOLLOW_variable_in_actionGet9945);
            var=variable();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f, var, op);}

            match(input,COMMA,FOLLOW_COMMA_in_actionGet9958); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionGet9964);
            op=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, f, var, op);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionGet9977); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1654:1: actionGetList returns [TextMarkerAction action = null] : name= GETLIST LPAREN var= listVariable COMMA op= stringExpression RPAREN ;
    public final TextMarkerAction actionGetList() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression var =null;

        Expression op =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1655:5: (name= GETLIST LPAREN var= listVariable COMMA op= stringExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1656:5: name= GETLIST LPAREN var= listVariable COMMA op= stringExpression RPAREN
            {
            name=(Token)match(input,GETLIST,FOLLOW_GETLIST_in_actionGetList10007); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionGetList10009); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionGetList10015);
            var=listVariable();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, var, op);}

            match(input,COMMA,FOLLOW_COMMA_in_actionGetList10028); if (state.failed) return action;

            pushFollow(FOLLOW_stringExpression_in_actionGetList10034);
            op=stringExpression();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, var, op);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionGetList10047); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1663:1: actionMatchedText returns [TextMarkerAction action = null] : name= MATCHEDTEXT LPAREN var= variable ( COMMA index= numberExpression )* RPAREN ;
    public final TextMarkerAction actionMatchedText() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression var =null;

        Expression index =null;



        List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1667:5: (name= MATCHEDTEXT LPAREN var= variable ( COMMA index= numberExpression )* RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1668:5: name= MATCHEDTEXT LPAREN var= variable ( COMMA index= numberExpression )* RPAREN
            {
            name=(Token)match(input,MATCHEDTEXT,FOLLOW_MATCHEDTEXT_in_actionMatchedText10084); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionMatchedText10086); if (state.failed) return action;

            pushFollow(FOLLOW_variable_in_actionMatchedText10097);
            var=variable();

            state._fsp--;
            if (state.failed) return action;

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1670:5: ( COMMA index= numberExpression )*
            loop161:
            do {
                int alt161=2;
                int LA161_0 = input.LA(1);

                if ( (LA161_0==COMMA) ) {
                    alt161=1;
                }


                switch (alt161) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1671:5: COMMA index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMatchedText10109); if (state.failed) return action;

            	    pushFollow(FOLLOW_numberExpression_in_actionMatchedText10115);
            	    index=numberExpression();

            	    state._fsp--;
            	    if (state.failed) return action;

            	    if ( state.backtracking==0 ) {list.add(index);}

            	    }
            	    break;

            	default :
            	    break loop161;
                }
            } while (true);


            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, var, list);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionMatchedText10137); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1677:1: actionClear returns [TextMarkerAction action = null] : name= CLEAR LPAREN var= listVariable RPAREN ;
    public final TextMarkerAction actionClear() throws RecognitionException {
        TextMarkerAction action =  null;


        Token name=null;
        Expression var =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1678:5: (name= CLEAR LPAREN var= listVariable RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1679:5: name= CLEAR LPAREN var= listVariable RPAREN
            {
            name=(Token)match(input,CLEAR,FOLLOW_CLEAR_in_actionClear10170); if (state.failed) return action;

            match(input,LPAREN,FOLLOW_LPAREN_in_actionClear10172); if (state.failed) return action;

            pushFollow(FOLLOW_listVariable_in_actionClear10178);
            var=listVariable();

            state._fsp--;
            if (state.failed) return action;

            if ( state.backtracking==0 ) {action = ActionFactory.createAction(name, var);}

            match(input,RPAREN,FOLLOW_RPAREN_in_actionClear10191); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1686:1: varArgumentList returns [List<Expression> args = new ArrayList<Expression>()] : ( LPAREN arg= argument ( COMMA arg= argument )* RPAREN )? ;
    public final List<Expression> varArgumentList() throws RecognitionException {
        List<Expression> args =  new ArrayList<Expression>();


        Expression arg =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1687:2: ( ( LPAREN arg= argument ( COMMA arg= argument )* RPAREN )? )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1688:2: ( LPAREN arg= argument ( COMMA arg= argument )* RPAREN )?
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1688:2: ( LPAREN arg= argument ( COMMA arg= argument )* RPAREN )?
            int alt163=2;
            int LA163_0 = input.LA(1);

            if ( (LA163_0==LPAREN) ) {
                alt163=1;
            }
            switch (alt163) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1688:3: LPAREN arg= argument ( COMMA arg= argument )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_varArgumentList10213); if (state.failed) return args;

                    pushFollow(FOLLOW_argument_in_varArgumentList10219);
                    arg=argument();

                    state._fsp--;
                    if (state.failed) return args;

                    if ( state.backtracking==0 ) {args.add(arg);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1688:42: ( COMMA arg= argument )*
                    loop162:
                    do {
                        int alt162=2;
                        int LA162_0 = input.LA(1);

                        if ( (LA162_0==COMMA) ) {
                            alt162=1;
                        }


                        switch (alt162) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1688:43: COMMA arg= argument
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_varArgumentList10224); if (state.failed) return args;

                    	    pushFollow(FOLLOW_argument_in_varArgumentList10230);
                    	    arg=argument();

                    	    state._fsp--;
                    	    if (state.failed) return args;

                    	    if ( state.backtracking==0 ) {args.add(arg);}

                    	    }
                    	    break;

                    	default :
                    	    break loop162;
                        }
                    } while (true);


                    match(input,RPAREN,FOLLOW_RPAREN_in_varArgumentList10236); if (state.failed) return args;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1692:1: argument returns [Expression expr = null] options {backtrack=true; } : (a4= stringExpression |a2= booleanExpression |a3= numberExpression |a1= typeExpression );
    public final Expression argument() throws RecognitionException {
        Expression expr =  null;


        Expression a4 =null;

        Expression a2 =null;

        Expression a3 =null;

        Expression a1 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1696:2: (a4= stringExpression |a2= booleanExpression |a3= numberExpression |a1= typeExpression )
            int alt164=4;
            switch ( input.LA(1) ) {
            case REMOVESTRING:
            case StringLiteral:
                {
                alt164=1;
                }
                break;
            case Identifier:
                {
                int LA164_2 = input.LA(2);

                if ( (((synpred26_TextMarkerParser()&&synpred26_TextMarkerParser())&&((isVariableOfType(input.LT(1).getText(), "STRINGFUNCTION"))||(isVariableOfType(input.LT(1).getText(), "STRING"))))) ) {
                    alt164=1;
                }
                else if ( (synpred27_TextMarkerParser()) ) {
                    alt164=2;
                }
                else if ( (((((isVariableOfType(input.LT(1).getText(), "INT"))||(isVariableOfType(input.LT(1).getText(), "DOUBLE"))||(isVariableOfType(input.LT(1).getText(), "NUMBERFUNCTION"))||(isVariableOfType(input.LT(1).getText(), "FLOAT")))&&((isVariableOfType(input.LT(1).getText(), "INT"))||(isVariableOfType(input.LT(1).getText(), "DOUBLE"))||(isVariableOfType(input.LT(1).getText(), "NUMBERFUNCTION"))||(isVariableOfType(input.LT(1).getText(), "FLOAT"))))&&synpred28_TextMarkerParser())) ) {
                    alt164=3;
                }
                else if ( (true) ) {
                    alt164=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 164, 2, input);

                    throw nvae;

                }
                }
                break;
            case FALSE:
            case TRUE:
            case XOR:
                {
                alt164=2;
                }
                break;
            case BasicAnnotationType:
                {
                int LA164_6 = input.LA(2);

                if ( (synpred27_TextMarkerParser()) ) {
                    alt164=2;
                }
                else if ( (true) ) {
                    alt164=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 164, 6, input);

                    throw nvae;

                }
                }
                break;
            case LPAREN:
                {
                int LA164_7 = input.LA(2);

                if ( (synpred27_TextMarkerParser()) ) {
                    alt164=2;
                }
                else if ( (synpred28_TextMarkerParser()) ) {
                    alt164=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 164, 7, input);

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
                alt164=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 164, 0, input);

                throw nvae;

            }

            switch (alt164) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1697:3: a4= stringExpression
                    {
                    pushFollow(FOLLOW_stringExpression_in_argument10273);
                    a4=stringExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = a4;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1698:4: a2= booleanExpression
                    {
                    pushFollow(FOLLOW_booleanExpression_in_argument10284);
                    a2=booleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = a2;}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1699:4: a3= numberExpression
                    {
                    pushFollow(FOLLOW_numberExpression_in_argument10295);
                    a3=numberExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = a3;}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1700:4: a1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_argument10306);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1711:1: dottedIdentifier returns [String idString = \"\"] : id= Identifier (dot= DOT idn= Identifier )* ;
    public final String dottedIdentifier() throws RecognitionException {
        String idString =  "";


        Token id=null;
        Token dot=null;
        Token idn=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1712:2: (id= Identifier (dot= DOT idn= Identifier )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1713:2: id= Identifier (dot= DOT idn= Identifier )*
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedIdentifier10343); if (state.failed) return idString;

            if ( state.backtracking==0 ) {idString += id.getText();}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1714:2: (dot= DOT idn= Identifier )*
            loop165:
            do {
                int alt165=2;
                int LA165_0 = input.LA(1);

                if ( (LA165_0==DOT) ) {
                    alt165=1;
                }


                switch (alt165) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1715:3: dot= DOT idn= Identifier
            	    {
            	    dot=(Token)match(input,DOT,FOLLOW_DOT_in_dottedIdentifier10356); if (state.failed) return idString;

            	    if ( state.backtracking==0 ) {idString += dot.getText();}

            	    idn=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedIdentifier10366); if (state.failed) return idString;

            	    if ( state.backtracking==0 ) {idString += idn.getText();}

            	    }
            	    break;

            	default :
            	    break loop165;
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1721:1: dottedId returns [Token token = null ] : id= Identifier (dot= DOT id= Identifier )* ;
    public final Token dottedId() throws RecognitionException {
        Token token =  null;


        Token id=null;
        Token dot=null;

        CommonToken ct = null;
        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1723:2: (id= Identifier (dot= DOT id= Identifier )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1724:2: id= Identifier (dot= DOT id= Identifier )*
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedId10398); if (state.failed) return token;

            if ( state.backtracking==0 ) {
            		ct = new CommonToken(id);
            		}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1727:2: (dot= DOT id= Identifier )*
            loop166:
            do {
                int alt166=2;
                int LA166_0 = input.LA(1);

                if ( (LA166_0==DOT) ) {
                    alt166=1;
                }


                switch (alt166) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1728:3: dot= DOT id= Identifier
            	    {
            	    dot=(Token)match(input,DOT,FOLLOW_DOT_in_dottedId10411); if (state.failed) return token;

            	    if ( state.backtracking==0 ) {ct.setText(ct.getText() + dot.getText());}

            	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedId10421); if (state.failed) return token;

            	    if ( state.backtracking==0 ) {ct.setStopIndex(getBounds(id)[1]);
            	    		                 ct.setText(ct.getText() + id.getText());}

            	    }
            	    break;

            	default :
            	    break loop166;
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1737:1: dottedComponentReference returns [ComponentReference ref = null ] : id= Identifier (dot= ( DOT | MINUS ) id= Identifier )* ;
    public final ComponentReference dottedComponentReference() throws RecognitionException {
        ComponentReference ref =  null;


        Token id=null;
        Token dot=null;

        CommonToken ct = null;
        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1739:2: (id= Identifier (dot= ( DOT | MINUS ) id= Identifier )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1740:2: id= Identifier (dot= ( DOT | MINUS ) id= Identifier )*
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedComponentReference10456); if (state.failed) return ref;

            if ( state.backtracking==0 ) {
            		ct = new CommonToken(id);
            		//if (ct.getText().equals("<missing Identifier>")) {
            	        //    CommonTokenStream cts = (CommonTokenStream) input;
            	        //    Token lt = cts.LT(1);
            	        //    ct = new CommonToken(lt);
            	        //  }
            		}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1748:2: (dot= ( DOT | MINUS ) id= Identifier )*
            loop167:
            do {
                int alt167=2;
                int LA167_0 = input.LA(1);

                if ( (LA167_0==DOT||LA167_0==MINUS) ) {
                    alt167=1;
                }


                switch (alt167) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1749:3: dot= ( DOT | MINUS ) id= Identifier
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

            	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedComponentReference10485); if (state.failed) return ref;

            	    if ( state.backtracking==0 ) {ct.setStopIndex(getBounds(id)[1]);
            	    		                 ct.setText(ct.getText() + id.getText());}

            	    }
            	    break;

            	default :
            	    break loop167;
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1757:1: dottedComponentDeclaration returns [ComponentDeclaration ref = null ] : id= Identifier (dot= ( DOT | MINUS ) id= Identifier )* ;
    public final ComponentDeclaration dottedComponentDeclaration() throws RecognitionException {
        ComponentDeclaration ref =  null;


        Token id=null;
        Token dot=null;

        CommonToken ct = null;
        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1759:2: (id= Identifier (dot= ( DOT | MINUS ) id= Identifier )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1760:2: id= Identifier (dot= ( DOT | MINUS ) id= Identifier )*
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedComponentDeclaration10519); if (state.failed) return ref;

            if ( state.backtracking==0 ) {
            		ct = new CommonToken(id);
            		}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1763:2: (dot= ( DOT | MINUS ) id= Identifier )*
            loop168:
            do {
                int alt168=2;
                int LA168_0 = input.LA(1);

                if ( (LA168_0==DOT||LA168_0==MINUS) ) {
                    alt168=1;
                }


                switch (alt168) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1764:3: dot= ( DOT | MINUS ) id= Identifier
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

            	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedComponentDeclaration10548); if (state.failed) return ref;

            	    if ( state.backtracking==0 ) {ct.setStopIndex(getBounds(id)[1]);
            	    		                 ct.setText(ct.getText() + id.getText());}

            	    }
            	    break;

            	default :
            	    break loop168;
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1773:1: annotationType returns [Expression at = null] : (atRef= annotationTypeVariableReference |basicAT= BasicAnnotationType ) ;
    public final Expression annotationType() throws RecognitionException {
        Expression at =  null;


        Token basicAT=null;
        Expression atRef =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1774:2: ( (atRef= annotationTypeVariableReference |basicAT= BasicAnnotationType ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1775:2: (atRef= annotationTypeVariableReference |basicAT= BasicAnnotationType )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1775:2: (atRef= annotationTypeVariableReference |basicAT= BasicAnnotationType )
            int alt169=2;
            int LA169_0 = input.LA(1);

            if ( (LA169_0==Identifier) ) {
                alt169=1;
            }
            else if ( (LA169_0==BasicAnnotationType) ) {
                alt169=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return at;}
                NoViableAltException nvae =
                    new NoViableAltException("", 169, 0, input);

                throw nvae;

            }
            switch (alt169) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1776:2: atRef= annotationTypeVariableReference
                    {
                    pushFollow(FOLLOW_annotationTypeVariableReference_in_annotationType10582);
                    atRef=annotationTypeVariableReference();

                    state._fsp--;
                    if (state.failed) return at;

                    if ( state.backtracking==0 ) {at = atRef;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1777:4: basicAT= BasicAnnotationType
                    {
                    basicAT=(Token)match(input,BasicAnnotationType,FOLLOW_BasicAnnotationType_in_annotationType10593); if (state.failed) return at;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1781:1: annotationTypeVariableReference returns [Expression typeVar = null] : atRef= dottedId ;
    public final Expression annotationTypeVariableReference() throws RecognitionException {
        Expression typeVar =  null;


        Token atRef =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1782:3: (atRef= dottedId )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1783:3: atRef= dottedId
            {
            pushFollow(FOLLOW_dottedId_in_annotationTypeVariableReference10622);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1787:1: wordListExpression returns [Expression expr = null] : (id= Identifier |path= RessourceLiteral );
    public final Expression wordListExpression() throws RecognitionException {
        Expression expr =  null;


        Token id=null;
        Token path=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1788:2: (id= Identifier |path= RessourceLiteral )
            int alt170=2;
            int LA170_0 = input.LA(1);

            if ( (LA170_0==Identifier) ) {
                alt170=1;
            }
            else if ( (LA170_0==RessourceLiteral) ) {
                alt170=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 170, 0, input);

                throw nvae;

            }
            switch (alt170) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1789:2: id= Identifier
                    {
                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_wordListExpression10646); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createListVariableReference(id);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1792:2: path= RessourceLiteral
                    {
                    path=(Token)match(input,RessourceLiteral,FOLLOW_RessourceLiteral_in_wordListExpression10659); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1797:1: wordTableExpression returns [Expression expr = null] : (id= Identifier |path= RessourceLiteral );
    public final Expression wordTableExpression() throws RecognitionException {
        Expression expr =  null;


        Token id=null;
        Token path=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1798:2: (id= Identifier |path= RessourceLiteral )
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1799:2: id= Identifier
                    {
                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_wordTableExpression10683); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createTableVariableReference(id);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1802:2: path= RessourceLiteral
                    {
                    path=(Token)match(input,RessourceLiteral,FOLLOW_RessourceLiteral_in_wordTableExpression10696); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1807:1: numberExpression returns [Expression expr = null] : e= additiveExpression ;
    public final Expression numberExpression() throws RecognitionException {
        Expression expr =  null;


        Expression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1808:2: (e= additiveExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1809:2: e= additiveExpression
            {
            pushFollow(FOLLOW_additiveExpression_in_numberExpression10720);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1814:1: additiveExpression returns [Expression root = null] : expr1= multiplicativeExpression (op= ( PLUS | MINUS ) expr2= multiplicativeExpression )* ;
    public final Expression additiveExpression() throws RecognitionException {
        Expression root =  null;


        Token op=null;
        Expression expr1 =null;

        Expression expr2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1815:5: (expr1= multiplicativeExpression (op= ( PLUS | MINUS ) expr2= multiplicativeExpression )* )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1815:9: expr1= multiplicativeExpression (op= ( PLUS | MINUS ) expr2= multiplicativeExpression )*
            {
            pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression10746);
            expr1=multiplicativeExpression();

            state._fsp--;
            if (state.failed) return root;

            if ( state.backtracking==0 ) {root=expr1;}

            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1816:2: (op= ( PLUS | MINUS ) expr2= multiplicativeExpression )*
            loop172:
            do {
                int alt172=2;
                int LA172_0 = input.LA(1);

                if ( (LA172_0==MINUS||LA172_0==PLUS) ) {
                    alt172=1;
                }


                switch (alt172) {
            	case 1 :
            	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1816:4: op= ( PLUS | MINUS ) expr2= multiplicativeExpression
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


            	    pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression10765);
            	    expr2=multiplicativeExpression();

            	    state._fsp--;
            	    if (state.failed) return root;

            	    if ( state.backtracking==0 ) {root=ExpressionFactory.createBinaryArithmeticExpr(root,expr2,op);}

            	    }
            	    break;

            	default :
            	    break loop172;
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1820:1: multiplicativeExpression returns [Expression root = null] : (expr1= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) sNE= simpleNumberExpression )* |e1= numberFunction ) ;
    public final Expression multiplicativeExpression() throws RecognitionException {
        Expression root =  null;


        Token op=null;
        Expression expr1 =null;

        Expression sNE =null;

        Expression e1 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1821:5: ( (expr1= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) sNE= simpleNumberExpression )* |e1= numberFunction ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1822:2: (expr1= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) sNE= simpleNumberExpression )* |e1= numberFunction )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1822:2: (expr1= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) sNE= simpleNumberExpression )* |e1= numberFunction )
            int alt174=2;
            switch ( input.LA(1) ) {
            case DecimalLiteral:
            case FloatingPointLiteral:
            case LPAREN:
            case MINUS:
                {
                alt174=1;
                }
                break;
            case Identifier:
                {
                int LA174_2 = input.LA(2);

                if ( (LA174_2==LPAREN) ) {
                    alt174=2;
                }
                else if ( (LA174_2==EOF||LA174_2==COMMA||LA174_2==EQUAL||(LA174_2 >= GREATER && LA174_2 <= GREATEREQUAL)||(LA174_2 >= LESS && LA174_2 <= LESSEQUAL)||LA174_2==MINUS||LA174_2==NOTEQUAL||(LA174_2 >= PERCENT && LA174_2 <= PLUS)||LA174_2==RBRACK||LA174_2==RPAREN||LA174_2==SEMI||(LA174_2 >= SLASH && LA174_2 <= STAR)) ) {
                    alt174=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return root;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 174, 2, input);

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
                alt174=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return root;}
                NoViableAltException nvae =
                    new NoViableAltException("", 174, 0, input);

                throw nvae;

            }

            switch (alt174) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1822:3: expr1= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) sNE= simpleNumberExpression )*
                    {
                    pushFollow(FOLLOW_simpleNumberExpression_in_multiplicativeExpression10794);
                    expr1=simpleNumberExpression();

                    state._fsp--;
                    if (state.failed) return root;

                    if ( state.backtracking==0 ) {root=expr1;}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1823:2: (op= ( STAR | SLASH | PERCENT ) sNE= simpleNumberExpression )*
                    loop173:
                    do {
                        int alt173=2;
                        int LA173_0 = input.LA(1);

                        if ( (LA173_0==PERCENT||(LA173_0 >= SLASH && LA173_0 <= STAR)) ) {
                            alt173=1;
                        }


                        switch (alt173) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1823:4: op= ( STAR | SLASH | PERCENT ) sNE= simpleNumberExpression
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


                    	    pushFollow(FOLLOW_simpleNumberExpression_in_multiplicativeExpression10821);
                    	    sNE=simpleNumberExpression();

                    	    state._fsp--;
                    	    if (state.failed) return root;

                    	    if ( state.backtracking==0 ) {root=ExpressionFactory.createBinaryArithmeticExpr(root,sNE,op);}

                    	    }
                    	    break;

                    	default :
                    	    break loop173;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1824:6: e1= numberFunction
                    {
                    pushFollow(FOLLOW_numberFunction_in_multiplicativeExpression10837);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1828:1: numberExpressionInPar returns [TextMarkerExpression expr = null] : lp= LPAREN numE= numberExpression rp= RPAREN ;
    public final TextMarkerExpression numberExpressionInPar() throws RecognitionException {
        TextMarkerExpression expr =  null;


        Token lp=null;
        Token rp=null;
        Expression numE =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1829:2: (lp= LPAREN numE= numberExpression rp= RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1830:2: lp= LPAREN numE= numberExpression rp= RPAREN
            {
            lp=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_numberExpressionInPar10861); if (state.failed) return expr;

            pushFollow(FOLLOW_numberExpression_in_numberExpressionInPar10867);
            numE=numberExpression();

            state._fsp--;
            if (state.failed) return expr;

            rp=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_numberExpressionInPar10873); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1838:1: simpleNumberExpression returns [Expression expr = null] : ( (m= MINUS )? numVarRef= numberVariable | (m= MINUS )? decLit= DecimalLiteral | (m= MINUS )? fpLit= FloatingPointLiteral |numExprPar= numberExpressionInPar );
    public final Expression simpleNumberExpression() throws RecognitionException {
        Expression expr =  null;


        Token m=null;
        Token decLit=null;
        Token fpLit=null;
        Expression numVarRef =null;

        TextMarkerExpression numExprPar =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1839:2: ( (m= MINUS )? numVarRef= numberVariable | (m= MINUS )? decLit= DecimalLiteral | (m= MINUS )? fpLit= FloatingPointLiteral |numExprPar= numberExpressionInPar )
            int alt178=4;
            switch ( input.LA(1) ) {
            case MINUS:
                {
                switch ( input.LA(2) ) {
                case Identifier:
                    {
                    alt178=1;
                    }
                    break;
                case DecimalLiteral:
                    {
                    alt178=2;
                    }
                    break;
                case FloatingPointLiteral:
                    {
                    alt178=3;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 178, 1, input);

                    throw nvae;

                }

                }
                break;
            case Identifier:
                {
                alt178=1;
                }
                break;
            case DecimalLiteral:
                {
                alt178=2;
                }
                break;
            case FloatingPointLiteral:
                {
                alt178=3;
                }
                break;
            case LPAREN:
                {
                alt178=4;
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1840:2: (m= MINUS )? numVarRef= numberVariable
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1840:4: (m= MINUS )?
                    int alt175=2;
                    int LA175_0 = input.LA(1);

                    if ( (LA175_0==MINUS) ) {
                        alt175=1;
                    }
                    switch (alt175) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1840:4: m= MINUS
                            {
                            m=(Token)match(input,MINUS,FOLLOW_MINUS_in_simpleNumberExpression10898); if (state.failed) return expr;

                            }
                            break;

                    }


                    pushFollow(FOLLOW_numberVariable_in_simpleNumberExpression10905);
                    numVarRef=numberVariable();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {if(m == null) {expr = numVarRef;} else {expr = ExpressionFactory.createNegatedNumberExpression(m, numVarRef);}}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1842:4: (m= MINUS )? decLit= DecimalLiteral
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1842:4: (m= MINUS )?
                    int alt176=2;
                    int LA176_0 = input.LA(1);

                    if ( (LA176_0==MINUS) ) {
                        alt176=1;
                    }
                    switch (alt176) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1842:5: m= MINUS
                            {
                            m=(Token)match(input,MINUS,FOLLOW_MINUS_in_simpleNumberExpression10920); if (state.failed) return expr;

                            }
                            break;

                    }


                    decLit=(Token)match(input,DecimalLiteral,FOLLOW_DecimalLiteral_in_simpleNumberExpression10928); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createDecimalLiteral(decLit,m);}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1844:4: (m= MINUS )? fpLit= FloatingPointLiteral
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1844:6: (m= MINUS )?
                    int alt177=2;
                    int LA177_0 = input.LA(1);

                    if ( (LA177_0==MINUS) ) {
                        alt177=1;
                    }
                    switch (alt177) {
                        case 1 :
                            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1844:6: m= MINUS
                            {
                            m=(Token)match(input,MINUS,FOLLOW_MINUS_in_simpleNumberExpression10942); if (state.failed) return expr;

                            }
                            break;

                    }


                    fpLit=(Token)match(input,FloatingPointLiteral,FOLLOW_FloatingPointLiteral_in_simpleNumberExpression10949); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createFloatingPointLiteral(fpLit,m);}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1847:4: numExprPar= numberExpressionInPar
                    {
                    pushFollow(FOLLOW_numberExpressionInPar_in_simpleNumberExpression10965);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1852:1: numberFunction returns [Expression expr = null] : ( (op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar ) | (e= externalNumberFunction )=>e= externalNumberFunction );
    public final Expression numberFunction() throws RecognitionException {
        Expression expr =  null;


        Token op=null;
        TextMarkerExpression numExprP =null;

        Expression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1853:2: ( (op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar ) | (e= externalNumberFunction )=>e= externalNumberFunction )
            int alt179=2;
            int LA179_0 = input.LA(1);

            if ( (LA179_0==COS||LA179_0==EXP||LA179_0==LOGN||LA179_0==SIN||LA179_0==TAN) ) {
                alt179=1;
            }
            else if ( (LA179_0==Identifier) && (synpred29_TextMarkerParser())) {
                alt179=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 179, 0, input);

                throw nvae;

            }
            switch (alt179) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1854:2: (op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar )
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1854:2: (op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar )
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1854:3: op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar
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


                    pushFollow(FOLLOW_numberExpressionInPar_in_numberFunction11012);
                    numExprP=numberExpressionInPar();

                    state._fsp--;
                    if (state.failed) return expr;

                    }


                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createUnaryArithmeticExpr(numExprP,op);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1857:4: (e= externalNumberFunction )=>e= externalNumberFunction
                    {
                    pushFollow(FOLLOW_externalNumberFunction_in_numberFunction11036);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1861:1: externalNumberFunction returns [Expression expr = null] :{...}?id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final Expression externalNumberFunction() throws RecognitionException {
        Expression expr =  null;


        Token id=null;
        List<Expression> args =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1862:2: ({...}?id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1863:2: {...}?id= Identifier LPAREN args= varArgumentList RPAREN
            {
            if ( !((isVariableOfType(input.LT(1).getText(), "NUMBERFUNCTION"))) ) {
                if (state.backtracking>0) {state.failed=true; return expr;}
                throw new FailedPredicateException(input, "externalNumberFunction", "isVariableOfType(input.LT(1).getText(), \"NUMBERFUNCTION\")");
            }

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalNumberFunction11061); if (state.failed) return expr;

            match(input,LPAREN,FOLLOW_LPAREN_in_externalNumberFunction11065); if (state.failed) return expr;

            pushFollow(FOLLOW_varArgumentList_in_externalNumberFunction11072);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return expr;

            match(input,RPAREN,FOLLOW_RPAREN_in_externalNumberFunction11075); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1873:1: numberVariable returns [Expression expr = null] : ({...}?numVarRef= Identifier |{...}?numVarRef= Identifier |{...}?numVarRef= Identifier ) ;
    public final Expression numberVariable() throws RecognitionException {
        Expression expr =  null;


        Token numVarRef=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1874:2: ( ({...}?numVarRef= Identifier |{...}?numVarRef= Identifier |{...}?numVarRef= Identifier ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1875:5: ({...}?numVarRef= Identifier |{...}?numVarRef= Identifier |{...}?numVarRef= Identifier )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1875:5: ({...}?numVarRef= Identifier |{...}?numVarRef= Identifier |{...}?numVarRef= Identifier )
            int alt180=3;
            int LA180_0 = input.LA(1);

            if ( (LA180_0==Identifier) ) {
                int LA180_1 = input.LA(2);

                if ( ((isVariableOfType(input.LT(1).getText(), "INT"))) ) {
                    alt180=1;
                }
                else if ( ((isVariableOfType(input.LT(1).getText(), "DOUBLE"))) ) {
                    alt180=2;
                }
                else if ( ((isVariableOfType(input.LT(1).getText(), "FLOAT"))) ) {
                    alt180=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 180, 1, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 180, 0, input);

                throw nvae;

            }
            switch (alt180) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1875:7: {...}?numVarRef= Identifier
                    {
                    if ( !((isVariableOfType(input.LT(1).getText(), "INT"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "numberVariable", "isVariableOfType(input.LT(1).getText(), \"INT\")");
                    }

                    numVarRef=(Token)match(input,Identifier,FOLLOW_Identifier_in_numberVariable11106); if (state.failed) return expr;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1876:5: {...}?numVarRef= Identifier
                    {
                    if ( !((isVariableOfType(input.LT(1).getText(), "DOUBLE"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "numberVariable", "isVariableOfType(input.LT(1).getText(), \"DOUBLE\")");
                    }

                    numVarRef=(Token)match(input,Identifier,FOLLOW_Identifier_in_numberVariable11119); if (state.failed) return expr;

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1877:6: {...}?numVarRef= Identifier
                    {
                    if ( !((isVariableOfType(input.LT(1).getText(), "FLOAT"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "numberVariable", "isVariableOfType(input.LT(1).getText(), \"FLOAT\")");
                    }

                    numVarRef=(Token)match(input,Identifier,FOLLOW_Identifier_in_numberVariable11132); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1883:1: stringExpression returns [Expression expr = null] : (e= stringFunction |strExpr1= simpleStringExpression ( PLUS (nextstrExpr= simpleStringExpression |ne= numberExpressionInPar |be= simpleBooleanExpression | ( listExpression )=>le= listExpression |te= typeExpression ) )* );
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
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1885:2: (e= stringFunction |strExpr1= simpleStringExpression ( PLUS (nextstrExpr= simpleStringExpression |ne= numberExpressionInPar |be= simpleBooleanExpression | ( listExpression )=>le= listExpression |te= typeExpression ) )* )
            int alt183=2;
            switch ( input.LA(1) ) {
            case REMOVESTRING:
                {
                alt183=1;
                }
                break;
            case Identifier:
                {
                int LA183_2 = input.LA(2);

                if ( (LA183_2==LPAREN) ) {
                    alt183=1;
                }
                else if ( (LA183_2==EOF||LA183_2==ASSIGN_EQUAL||LA183_2==COMMA||LA183_2==PLUS||LA183_2==RPAREN||LA183_2==SEMI) ) {
                    alt183=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 183, 2, input);

                    throw nvae;

                }
                }
                break;
            case StringLiteral:
                {
                alt183=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 183, 0, input);

                throw nvae;

            }

            switch (alt183) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1886:2: e= stringFunction
                    {
                    pushFollow(FOLLOW_stringFunction_in_stringExpression11170);
                    e=stringFunction();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1888:2: strExpr1= simpleStringExpression ( PLUS (nextstrExpr= simpleStringExpression |ne= numberExpressionInPar |be= simpleBooleanExpression | ( listExpression )=>le= listExpression |te= typeExpression ) )*
                    {
                    pushFollow(FOLLOW_simpleStringExpression_in_stringExpression11183);
                    strExpr1=simpleStringExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {if (strExpr1!=null) exprList.add(strExpr1);}

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1889:2: ( PLUS (nextstrExpr= simpleStringExpression |ne= numberExpressionInPar |be= simpleBooleanExpression | ( listExpression )=>le= listExpression |te= typeExpression ) )*
                    loop182:
                    do {
                        int alt182=2;
                        int LA182_0 = input.LA(1);

                        if ( (LA182_0==PLUS) ) {
                            alt182=1;
                        }


                        switch (alt182) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1889:3: PLUS (nextstrExpr= simpleStringExpression |ne= numberExpressionInPar |be= simpleBooleanExpression | ( listExpression )=>le= listExpression |te= typeExpression )
                    	    {
                    	    match(input,PLUS,FOLLOW_PLUS_in_stringExpression11189); if (state.failed) return expr;

                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1889:8: (nextstrExpr= simpleStringExpression |ne= numberExpressionInPar |be= simpleBooleanExpression | ( listExpression )=>le= listExpression |te= typeExpression )
                    	    int alt181=5;
                    	    int LA181_0 = input.LA(1);

                    	    if ( (LA181_0==StringLiteral) ) {
                    	        alt181=1;
                    	    }
                    	    else if ( (LA181_0==Identifier) ) {
                    	        int LA181_2 = input.LA(2);

                    	        if ( ((isVariableOfType(input.LT(1).getText(), "STRING"))) ) {
                    	            alt181=1;
                    	        }
                    	        else if ( ((isVariableOfType(input.LT(1).getText(), "BOOLEAN"))) ) {
                    	            alt181=3;
                    	        }
                    	        else if ( (((((isVariableOfType(input.LT(1).getText(), "INTLIST"))||(isVariableOfType(input.LT(1).getText(), "TYPELIST"))||(isVariableOfType(input.LT(1).getText(), "DOUBLELIST"))||(isVariableOfType(input.LT(1).getText(), "FLOATLIST"))||(isVariableOfType(input.LT(1).getText(), "BOOLEANLIST"))||(isVariableOfType(input.LT(1).getText(), "STRINGLIST")))&&((isVariableOfType(input.LT(1).getText(), "INTLIST"))||(isVariableOfType(input.LT(1).getText(), "TYPELIST"))||(isVariableOfType(input.LT(1).getText(), "DOUBLELIST"))||(isVariableOfType(input.LT(1).getText(), "FLOATLIST"))||(isVariableOfType(input.LT(1).getText(), "BOOLEANLIST"))||(isVariableOfType(input.LT(1).getText(), "STRINGLIST"))))&&synpred30_TextMarkerParser())) ) {
                    	            alt181=4;
                    	        }
                    	        else if ( (true) ) {
                    	            alt181=5;
                    	        }
                    	        else {
                    	            if (state.backtracking>0) {state.failed=true; return expr;}
                    	            NoViableAltException nvae =
                    	                new NoViableAltException("", 181, 2, input);

                    	            throw nvae;

                    	        }
                    	    }
                    	    else if ( (LA181_0==LPAREN) ) {
                    	        alt181=2;
                    	    }
                    	    else if ( (LA181_0==FALSE||LA181_0==TRUE) ) {
                    	        alt181=3;
                    	    }
                    	    else if ( (LA181_0==LCURLY) && (synpred30_TextMarkerParser())) {
                    	        alt181=4;
                    	    }
                    	    else if ( (LA181_0==BasicAnnotationType) ) {
                    	        alt181=5;
                    	    }
                    	    else {
                    	        if (state.backtracking>0) {state.failed=true; return expr;}
                    	        NoViableAltException nvae =
                    	            new NoViableAltException("", 181, 0, input);

                    	        throw nvae;

                    	    }
                    	    switch (alt181) {
                    	        case 1 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1889:9: nextstrExpr= simpleStringExpression
                    	            {
                    	            pushFollow(FOLLOW_simpleStringExpression_in_stringExpression11196);
                    	            nextstrExpr=simpleStringExpression();

                    	            state._fsp--;
                    	            if (state.failed) return expr;

                    	            if ( state.backtracking==0 ) {if (nextstrExpr!=null) exprList.add(nextstrExpr);}

                    	            }
                    	            break;
                    	        case 2 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1890:5: ne= numberExpressionInPar
                    	            {
                    	            pushFollow(FOLLOW_numberExpressionInPar_in_stringExpression11208);
                    	            ne=numberExpressionInPar();

                    	            state._fsp--;
                    	            if (state.failed) return expr;

                    	            if ( state.backtracking==0 ) {if (ne!=null) exprList.add(ne);}

                    	            }
                    	            break;
                    	        case 3 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1891:5: be= simpleBooleanExpression
                    	            {
                    	            pushFollow(FOLLOW_simpleBooleanExpression_in_stringExpression11220);
                    	            be=simpleBooleanExpression();

                    	            state._fsp--;
                    	            if (state.failed) return expr;

                    	            if ( state.backtracking==0 ) {if (be!=null) exprList.add(be);}

                    	            }
                    	            break;
                    	        case 4 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1892:5: ( listExpression )=>le= listExpression
                    	            {
                    	            pushFollow(FOLLOW_listExpression_in_stringExpression11237);
                    	            le=listExpression();

                    	            state._fsp--;
                    	            if (state.failed) return expr;

                    	            if ( state.backtracking==0 ) {if (le!=null) exprList.add(le);}

                    	            }
                    	            break;
                    	        case 5 :
                    	            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1893:5: te= typeExpression
                    	            {
                    	            pushFollow(FOLLOW_typeExpression_in_stringExpression11249);
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
                    	    break loop182;
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1900:1: stringFunction returns [Expression expr = null] : (name= REMOVESTRING LPAREN var= variable ( COMMA s= stringExpression )+ RPAREN | (e= externalStringFunction )=>e= externalStringFunction );
    public final Expression stringFunction() throws RecognitionException {
        Expression expr =  null;


        Token name=null;
        Expression var =null;

        Expression s =null;

        Expression e =null;


        List<Expression> list = new ArrayList<Expression>();
        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1902:2: (name= REMOVESTRING LPAREN var= variable ( COMMA s= stringExpression )+ RPAREN | (e= externalStringFunction )=>e= externalStringFunction )
            int alt185=2;
            int LA185_0 = input.LA(1);

            if ( (LA185_0==REMOVESTRING) ) {
                alt185=1;
            }
            else if ( (LA185_0==Identifier) && (synpred31_TextMarkerParser())) {
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1903:2: name= REMOVESTRING LPAREN var= variable ( COMMA s= stringExpression )+ RPAREN
                    {
                    name=(Token)match(input,REMOVESTRING,FOLLOW_REMOVESTRING_in_stringFunction11286); if (state.failed) return expr;

                    match(input,LPAREN,FOLLOW_LPAREN_in_stringFunction11288); if (state.failed) return expr;

                    pushFollow(FOLLOW_variable_in_stringFunction11294);
                    var=variable();

                    state._fsp--;
                    if (state.failed) return expr;

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1903:44: ( COMMA s= stringExpression )+
                    int cnt184=0;
                    loop184:
                    do {
                        int alt184=2;
                        int LA184_0 = input.LA(1);

                        if ( (LA184_0==COMMA) ) {
                            alt184=1;
                        }


                        switch (alt184) {
                    	case 1 :
                    	    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1903:45: COMMA s= stringExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_stringFunction11297); if (state.failed) return expr;

                    	    pushFollow(FOLLOW_stringExpression_in_stringFunction11303);
                    	    s=stringExpression();

                    	    state._fsp--;
                    	    if (state.failed) return expr;

                    	    if ( state.backtracking==0 ) {list.add(s);}

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt184 >= 1 ) break loop184;
                    	    if (state.backtracking>0) {state.failed=true; return expr;}
                                EarlyExitException eee =
                                    new EarlyExitException(184, input);
                                throw eee;
                        }
                        cnt184++;
                    } while (true);


                    match(input,RPAREN,FOLLOW_RPAREN_in_stringFunction11308); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createStringFunction(name,var,list);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1906:2: (e= externalStringFunction )=>e= externalStringFunction
                    {
                    pushFollow(FOLLOW_externalStringFunction_in_stringFunction11330);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1910:1: externalStringFunction returns [Expression expr = null] :{...}?id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final Expression externalStringFunction() throws RecognitionException {
        Expression expr =  null;


        Token id=null;
        List<Expression> args =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1911:2: ({...}?id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1912:2: {...}?id= Identifier LPAREN args= varArgumentList RPAREN
            {
            if ( !((isVariableOfType(input.LT(1).getText(), "STRINGFUNCTION"))) ) {
                if (state.backtracking>0) {state.failed=true; return expr;}
                throw new FailedPredicateException(input, "externalStringFunction", "isVariableOfType(input.LT(1).getText(), \"STRINGFUNCTION\")");
            }

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalStringFunction11355); if (state.failed) return expr;

            match(input,LPAREN,FOLLOW_LPAREN_in_externalStringFunction11359); if (state.failed) return expr;

            pushFollow(FOLLOW_varArgumentList_in_externalStringFunction11366);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return expr;

            match(input,RPAREN,FOLLOW_RPAREN_in_externalStringFunction11369); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1922:1: simpleStringExpression returns [Expression expr = null] : (lit= StringLiteral |{...}?variableId= Identifier );
    public final Expression simpleStringExpression() throws RecognitionException {
        Expression expr =  null;


        Token lit=null;
        Token variableId=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1923:2: (lit= StringLiteral |{...}?variableId= Identifier )
            int alt186=2;
            int LA186_0 = input.LA(1);

            if ( (LA186_0==StringLiteral) ) {
                alt186=1;
            }
            else if ( (LA186_0==Identifier) ) {
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1924:2: lit= StringLiteral
                    {
                    lit=(Token)match(input,StringLiteral,FOLLOW_StringLiteral_in_simpleStringExpression11394); if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createSimpleString(lit);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1925:5: {...}?variableId= Identifier
                    {
                    if ( !((isVariableOfType(input.LT(1).getText(), "STRING"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleStringExpression", "isVariableOfType(input.LT(1).getText(), \"STRING\")");
                    }

                    variableId=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleStringExpression11409); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1936:1: booleanExpression returns [Expression expr = null] : (bcE= composedBooleanExpression |sbE= simpleBooleanExpression );
    public final Expression booleanExpression() throws RecognitionException {
        Expression expr =  null;


        Expression bcE =null;

        Expression sbE =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1937:2: (bcE= composedBooleanExpression |sbE= simpleBooleanExpression )
            int alt187=2;
            switch ( input.LA(1) ) {
            case TRUE:
                {
                int LA187_1 = input.LA(2);

                if ( (LA187_1==EQUAL||LA187_1==NOTEQUAL) ) {
                    alt187=1;
                }
                else if ( (LA187_1==EOF||LA187_1==COMMA||LA187_1==RPAREN||LA187_1==SEMI) ) {
                    alt187=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 187, 1, input);

                    throw nvae;

                }
                }
                break;
            case FALSE:
                {
                int LA187_2 = input.LA(2);

                if ( (LA187_2==EQUAL||LA187_2==NOTEQUAL) ) {
                    alt187=1;
                }
                else if ( (LA187_2==EOF||LA187_2==COMMA||LA187_2==RPAREN||LA187_2==SEMI) ) {
                    alt187=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 187, 2, input);

                    throw nvae;

                }
                }
                break;
            case Identifier:
                {
                int LA187_3 = input.LA(2);

                if ( (LA187_3==DOT||LA187_3==EQUAL||LA187_3==LPAREN||LA187_3==NOTEQUAL) ) {
                    alt187=1;
                }
                else if ( (LA187_3==EOF||LA187_3==COMMA||LA187_3==RPAREN||LA187_3==SEMI) ) {
                    alt187=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 187, 3, input);

                    throw nvae;

                }
                }
                break;
            case BasicAnnotationType:
            case LPAREN:
            case XOR:
                {
                alt187=1;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 187, 0, input);

                throw nvae;

            }

            switch (alt187) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1938:2: bcE= composedBooleanExpression
                    {
                    pushFollow(FOLLOW_composedBooleanExpression_in_booleanExpression11442);
                    bcE=composedBooleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = bcE;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1939:4: sbE= simpleBooleanExpression
                    {
                    pushFollow(FOLLOW_simpleBooleanExpression_in_booleanExpression11453);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1942:1: simpleBooleanExpression returns [Expression expr = null] : (lbE= literalBooleanExpression |{...}? (variableId= Identifier ) ) ;
    public final Expression simpleBooleanExpression() throws RecognitionException {
        Expression expr =  null;


        Token variableId=null;
        BooleanLiteral lbE =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1943:2: ( (lbE= literalBooleanExpression |{...}? (variableId= Identifier ) ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1944:3: (lbE= literalBooleanExpression |{...}? (variableId= Identifier ) )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1944:3: (lbE= literalBooleanExpression |{...}? (variableId= Identifier ) )
            int alt188=2;
            int LA188_0 = input.LA(1);

            if ( (LA188_0==FALSE||LA188_0==TRUE) ) {
                alt188=1;
            }
            else if ( (LA188_0==Identifier) ) {
                alt188=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 188, 0, input);

                throw nvae;

            }
            switch (alt188) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1944:4: lbE= literalBooleanExpression
                    {
                    pushFollow(FOLLOW_literalBooleanExpression_in_simpleBooleanExpression11478);
                    lbE=literalBooleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = lbE;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1945:4: {...}? (variableId= Identifier )
                    {
                    if ( !((isVariableOfType(input.LT(1).getText(), "BOOLEAN"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleBooleanExpression", "isVariableOfType(input.LT(1).getText(), \"BOOLEAN\")");
                    }

                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1945:57: (variableId= Identifier )
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1945:58: variableId= Identifier
                    {
                    variableId=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleBooleanExpression11491); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1952:1: composedBooleanExpression returns [Expression expr = null] : ( (e2= booleanCompare )=>e2= booleanCompare | (bte= booleanTypeExpression )=>bte= booleanTypeExpression | (bne= booleanNumberExpression )=>bne= booleanNumberExpression |e1= booleanFunction );
    public final Expression composedBooleanExpression() throws RecognitionException {
        Expression expr =  null;


        Expression e2 =null;

        Expression bte =null;

        Expression bne =null;

        Expression e1 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1953:2: ( (e2= booleanCompare )=>e2= booleanCompare | (bte= booleanTypeExpression )=>bte= booleanTypeExpression | (bne= booleanNumberExpression )=>bne= booleanNumberExpression |e1= booleanFunction )
            int alt189=4;
            int LA189_0 = input.LA(1);

            if ( (LA189_0==TRUE) && (synpred32_TextMarkerParser())) {
                alt189=1;
            }
            else if ( (LA189_0==FALSE) && (synpred32_TextMarkerParser())) {
                alt189=1;
            }
            else if ( (LA189_0==Identifier) ) {
                int LA189_3 = input.LA(2);

                if ( (((synpred32_TextMarkerParser()&&synpred32_TextMarkerParser())&&(isVariableOfType(input.LT(1).getText(), "BOOLEAN")))) ) {
                    alt189=1;
                }
                else if ( (synpred33_TextMarkerParser()) ) {
                    alt189=2;
                }
                else if ( ((isVariableOfType(input.LT(1).getText(), "BOOLEANFUNCTION"))) ) {
                    alt189=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 189, 3, input);

                    throw nvae;

                }
            }
            else if ( (LA189_0==BasicAnnotationType) && (synpred33_TextMarkerParser())) {
                alt189=2;
            }
            else if ( (LA189_0==LPAREN) && (synpred34_TextMarkerParser())) {
                alt189=3;
            }
            else if ( (LA189_0==XOR) ) {
                alt189=4;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 189, 0, input);

                throw nvae;

            }
            switch (alt189) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1954:2: (e2= booleanCompare )=>e2= booleanCompare
                    {
                    pushFollow(FOLLOW_booleanCompare_in_composedBooleanExpression11537);
                    e2=booleanCompare();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = e2;}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1955:4: (bte= booleanTypeExpression )=>bte= booleanTypeExpression
                    {
                    pushFollow(FOLLOW_booleanTypeExpression_in_composedBooleanExpression11557);
                    bte=booleanTypeExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = bte;}

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1956:4: (bne= booleanNumberExpression )=>bne= booleanNumberExpression
                    {
                    pushFollow(FOLLOW_booleanNumberExpression_in_composedBooleanExpression11576);
                    bne=booleanNumberExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) {expr = bne;}

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1957:4: e1= booleanFunction
                    {
                    pushFollow(FOLLOW_booleanFunction_in_composedBooleanExpression11586);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1961:1: booleanFunction returns [Expression expr = null] : ( (op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN ) | (e= externalBooleanFunction )=>e= externalBooleanFunction );
    public final Expression booleanFunction() throws RecognitionException {
        Expression expr =  null;


        Token op=null;
        Expression e1 =null;

        Expression e2 =null;

        Expression e =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1963:2: ( (op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN ) | (e= externalBooleanFunction )=>e= externalBooleanFunction )
            int alt190=2;
            int LA190_0 = input.LA(1);

            if ( (LA190_0==XOR) ) {
                alt190=1;
            }
            else if ( (LA190_0==Identifier) && (synpred35_TextMarkerParser())) {
                alt190=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 190, 0, input);

                throw nvae;

            }
            switch (alt190) {
                case 1 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1964:2: (op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN )
                    {
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1964:2: (op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN )
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1964:3: op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN
                    {
                    op=(Token)match(input,XOR,FOLLOW_XOR_in_booleanFunction11611); if (state.failed) return expr;

                    match(input,LPAREN,FOLLOW_LPAREN_in_booleanFunction11613); if (state.failed) return expr;

                    pushFollow(FOLLOW_booleanExpression_in_booleanFunction11619);
                    e1=booleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    match(input,COMMA,FOLLOW_COMMA_in_booleanFunction11621); if (state.failed) return expr;

                    pushFollow(FOLLOW_booleanExpression_in_booleanFunction11627);
                    e2=booleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;

                    match(input,RPAREN,FOLLOW_RPAREN_in_booleanFunction11629); if (state.failed) return expr;

                    }


                    if ( state.backtracking==0 ) {expr = ExpressionFactory.createBooleanFunction(op,e1,e2);}

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1966:4: (e= externalBooleanFunction )=>e= externalBooleanFunction
                    {
                    pushFollow(FOLLOW_externalBooleanFunction_in_booleanFunction11651);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1970:1: externalBooleanFunction returns [Expression expr = null] :{...}?id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final Expression externalBooleanFunction() throws RecognitionException {
        Expression expr =  null;


        Token id=null;
        List<Expression> args =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1971:2: ({...}?id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1972:2: {...}?id= Identifier LPAREN args= varArgumentList RPAREN
            {
            if ( !((isVariableOfType(input.LT(1).getText(), "BOOLEANFUNCTION"))) ) {
                if (state.backtracking>0) {state.failed=true; return expr;}
                throw new FailedPredicateException(input, "externalBooleanFunction", "isVariableOfType(input.LT(1).getText(), \"BOOLEANFUNCTION\")");
            }

            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalBooleanFunction11677); if (state.failed) return expr;

            match(input,LPAREN,FOLLOW_LPAREN_in_externalBooleanFunction11680); if (state.failed) return expr;

            pushFollow(FOLLOW_varArgumentList_in_externalBooleanFunction11687);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return expr;

            match(input,RPAREN,FOLLOW_RPAREN_in_externalBooleanFunction11691); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1982:1: booleanCompare returns [Expression expr = null] : (e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression ) ;
    public final Expression booleanCompare() throws RecognitionException {
        Expression expr =  null;


        Token op=null;
        Expression e1 =null;

        Expression e2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1983:2: ( (e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1984:2: (e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1984:2: (e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1984:3: e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression
            {
            pushFollow(FOLLOW_simpleBooleanExpression_in_booleanCompare11716);
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


            pushFollow(FOLLOW_booleanExpression_in_booleanCompare11734);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1989:1: literalBooleanExpression returns [BooleanLiteral expr = null] : (value= TRUE |value= FALSE ) ;
    public final BooleanLiteral literalBooleanExpression() throws RecognitionException {
        BooleanLiteral expr =  null;


        Token value=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1990:2: ( (value= TRUE |value= FALSE ) )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1991:2: (value= TRUE |value= FALSE )
            {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1991:2: (value= TRUE |value= FALSE )
            int alt191=2;
            int LA191_0 = input.LA(1);

            if ( (LA191_0==TRUE) ) {
                alt191=1;
            }
            else if ( (LA191_0==FALSE) ) {
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
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1991:3: value= TRUE
                    {
                    value=(Token)match(input,TRUE,FOLLOW_TRUE_in_literalBooleanExpression11761); if (state.failed) return expr;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1992:4: value= FALSE
                    {
                    value=(Token)match(input,FALSE,FOLLOW_FALSE_in_literalBooleanExpression11771); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1997:1: booleanTypeExpression returns [Expression expr = null] : e1= typeExpression op= ( EQUAL | NOTEQUAL ) e2= typeExpression ;
    public final Expression booleanTypeExpression() throws RecognitionException {
        Expression expr =  null;


        Token op=null;
        Expression e1 =null;

        Expression e2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1998:2: (e1= typeExpression op= ( EQUAL | NOTEQUAL ) e2= typeExpression )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1999:2: e1= typeExpression op= ( EQUAL | NOTEQUAL ) e2= typeExpression
            {
            pushFollow(FOLLOW_typeExpression_in_booleanTypeExpression11798);
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


            pushFollow(FOLLOW_typeExpression_in_booleanTypeExpression11818);
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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:2007:1: booleanNumberExpression returns [Expression expr = null] : LPAREN e1= numberExpression op= ( LESS | GREATER | GREATEREQUAL | LESSEQUAL | EQUAL | NOTEQUAL ) e2= numberExpression RPAREN ;
    public final Expression booleanNumberExpression() throws RecognitionException {
        Expression expr =  null;


        Token op=null;
        Expression e1 =null;

        Expression e2 =null;


        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:2008:2: ( LPAREN e1= numberExpression op= ( LESS | GREATER | GREATEREQUAL | LESSEQUAL | EQUAL | NOTEQUAL ) e2= numberExpression RPAREN )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:2009:2: LPAREN e1= numberExpression op= ( LESS | GREATER | GREATEREQUAL | LESSEQUAL | EQUAL | NOTEQUAL ) e2= numberExpression RPAREN
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_booleanNumberExpression11841); if (state.failed) return expr;

            pushFollow(FOLLOW_numberExpression_in_booleanNumberExpression11848);
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


            pushFollow(FOLLOW_numberExpression_in_booleanNumberExpression11884);
            e2=numberExpression();

            state._fsp--;
            if (state.failed) return expr;

            match(input,RPAREN,FOLLOW_RPAREN_in_booleanNumberExpression11887); if (state.failed) return expr;

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
    // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:2017:1: genericVariableReference returns [Expression varRef] : id= Identifier ;
    public final Expression genericVariableReference() throws RecognitionException {
        Expression varRef = null;


        Token id=null;

        try {
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:2018:3: (id= Identifier )
            // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:2019:3: id= Identifier
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_genericVariableReference11907); if (state.failed) return varRef;

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
        pushFollow(FOLLOW_ruleElementType_in_synpred1_TextMarkerParser2100);
        ruleElementType();

        state._fsp--;
        if (state.failed) return ;

        match(input,VBAR,FOLLOW_VBAR_in_synpred1_TextMarkerParser2102); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred1_TextMarkerParser

    // $ANTLR start synpred2_TextMarkerParser
    public final void synpred2_TextMarkerParser_fragment() throws RecognitionException {
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:598:4: ( ruleElements )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:598:5: ruleElements
        {
        pushFollow(FOLLOW_ruleElements_in_synpred2_TextMarkerParser2144);
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
        pushFollow(FOLLOW_booleanListExpression_in_synpred5_TextMarkerParser2503);
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
        pushFollow(FOLLOW_intListExpression_in_synpred6_TextMarkerParser2519);
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
        pushFollow(FOLLOW_doubleListExpression_in_synpred7_TextMarkerParser2535);
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
        pushFollow(FOLLOW_floatListExpression_in_synpred8_TextMarkerParser2551);
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
        pushFollow(FOLLOW_stringListExpression_in_synpred9_TextMarkerParser2567);
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
        pushFollow(FOLLOW_typeListExpression_in_synpred10_TextMarkerParser2583);
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
        pushFollow(FOLLOW_doubleListExpression_in_synpred11_TextMarkerParser2796);
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
        pushFollow(FOLLOW_floatListExpression_in_synpred12_TextMarkerParser2817);
        e1=floatListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred12_TextMarkerParser

    // $ANTLR start synpred14_TextMarkerParser
    public final void synpred14_TextMarkerParser_fragment() throws RecognitionException {
        TextMarkerCondition c =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:856:4: (c= externalCondition )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:856:5: c= externalCondition
        {
        pushFollow(FOLLOW_externalCondition_in_synpred14_TextMarkerParser3776);
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
        name=(Token)match(input,COUNT,FOLLOW_COUNT_in_synpred15_TextMarkerParser4202); if (state.failed) return ;

        match(input,LPAREN,FOLLOW_LPAREN_in_synpred15_TextMarkerParser4204); if (state.failed) return ;

        pushFollow(FOLLOW_listExpression_in_synpred15_TextMarkerParser4210);
        type=listExpression();

        state._fsp--;
        if (state.failed) return ;

        match(input,COMMA,FOLLOW_COMMA_in_synpred15_TextMarkerParser4225); if (state.failed) return ;

        pushFollow(FOLLOW_argument_in_synpred15_TextMarkerParser4231);
        a=argument();

        state._fsp--;
        if (state.failed) return ;

        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:920:6: ( COMMA min= numberExpression COMMA max= numberExpression )?
        int alt192=2;
        int LA192_0 = input.LA(1);

        if ( (LA192_0==COMMA) ) {
            int LA192_1 = input.LA(2);

            if ( (LA192_1==COS||LA192_1==DecimalLiteral||LA192_1==EXP||LA192_1==FloatingPointLiteral||(LA192_1 >= LOGN && LA192_1 <= LPAREN)||LA192_1==MINUS||LA192_1==SIN||LA192_1==TAN) ) {
                alt192=1;
            }
            else if ( (LA192_1==Identifier) ) {
                int LA192_4 = input.LA(3);

                if ( (LA192_4==COMMA||LA192_4==LPAREN||LA192_4==MINUS||(LA192_4 >= PERCENT && LA192_4 <= PLUS)||(LA192_4 >= SLASH && LA192_4 <= STAR)) ) {
                    alt192=1;
                }
            }
        }
        switch (alt192) {
            case 1 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:920:7: COMMA min= numberExpression COMMA max= numberExpression
                {
                match(input,COMMA,FOLLOW_COMMA_in_synpred15_TextMarkerParser4247); if (state.failed) return ;

                pushFollow(FOLLOW_numberExpression_in_synpred15_TextMarkerParser4253);
                min=numberExpression();

                state._fsp--;
                if (state.failed) return ;

                match(input,COMMA,FOLLOW_COMMA_in_synpred15_TextMarkerParser4255); if (state.failed) return ;

                pushFollow(FOLLOW_numberExpression_in_synpred15_TextMarkerParser4261);
                max=numberExpression();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:922:6: ( COMMA var= numberVariable )?
        int alt193=2;
        int LA193_0 = input.LA(1);

        if ( (LA193_0==COMMA) ) {
            alt193=1;
        }
        switch (alt193) {
            case 1 :
                // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:922:7: COMMA var= numberVariable
                {
                match(input,COMMA,FOLLOW_COMMA_in_synpred15_TextMarkerParser4279); if (state.failed) return ;

                pushFollow(FOLLOW_numberVariable_in_synpred15_TextMarkerParser4285);
                var=numberVariable();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        match(input,RPAREN,FOLLOW_RPAREN_in_synpred15_TextMarkerParser4301); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred15_TextMarkerParser

    // $ANTLR start synpred16_TextMarkerParser
    public final void synpred16_TextMarkerParser_fragment() throws RecognitionException {
        Expression list2 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:961:27: (list2= stringListExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:961:28: list2= stringListExpression
        {
        pushFollow(FOLLOW_stringListExpression_in_synpred16_TextMarkerParser4666);
        list2=stringListExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred16_TextMarkerParser

    // $ANTLR start synpred17_TextMarkerParser
    public final void synpred17_TextMarkerParser_fragment() throws RecognitionException {
        TextMarkerAction a =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1167:4: (a= externalAction )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1167:5: a= externalAction
        {
        pushFollow(FOLLOW_externalAction_in_synpred17_TextMarkerParser6489);
        a=externalAction();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred17_TextMarkerParser

    // $ANTLR start synpred18_TextMarkerParser
    public final void synpred18_TextMarkerParser_fragment() throws RecognitionException {
        Expression index =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1204:54: ( COMMA index= numberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1204:55: COMMA index= numberExpression
        {
        match(input,COMMA,FOLLOW_COMMA_in_synpred18_TextMarkerParser6657); if (state.failed) return ;

        pushFollow(FOLLOW_numberExpression_in_synpred18_TextMarkerParser6663);
        index=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred18_TextMarkerParser

    // $ANTLR start synpred19_TextMarkerParser
    public final void synpred19_TextMarkerParser_fragment() throws RecognitionException {
        Expression index =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1243:54: ( COMMA index= numberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1243:55: COMMA index= numberExpression
        {
        match(input,COMMA,FOLLOW_COMMA_in_synpred19_TextMarkerParser6983); if (state.failed) return ;

        pushFollow(FOLLOW_numberExpression_in_synpred19_TextMarkerParser6989);
        index=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred19_TextMarkerParser

    // $ANTLR start synpred23_TextMarkerParser
    public final void synpred23_TextMarkerParser_fragment() throws RecognitionException {
        Expression score =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1348:29: (score= numberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1348:30: score= numberExpression
        {
        pushFollow(FOLLOW_numberExpression_in_synpred23_TextMarkerParser7817);
        score=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred23_TextMarkerParser

    // $ANTLR start synpred24_TextMarkerParser
    public final void synpred24_TextMarkerParser_fragment() throws RecognitionException {
        Expression type =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1348:92: (type= typeExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1348:93: type= typeExpression
        {
        pushFollow(FOLLOW_typeExpression_in_synpred24_TextMarkerParser7837);
        type=typeExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred24_TextMarkerParser

    // $ANTLR start synpred26_TextMarkerParser
    public final void synpred26_TextMarkerParser_fragment() throws RecognitionException {
        Expression a4 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1697:3: (a4= stringExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1697:3: a4= stringExpression
        {
        pushFollow(FOLLOW_stringExpression_in_synpred26_TextMarkerParser10273);
        a4=stringExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred26_TextMarkerParser

    // $ANTLR start synpred27_TextMarkerParser
    public final void synpred27_TextMarkerParser_fragment() throws RecognitionException {
        Expression a2 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1698:4: (a2= booleanExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1698:4: a2= booleanExpression
        {
        pushFollow(FOLLOW_booleanExpression_in_synpred27_TextMarkerParser10284);
        a2=booleanExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred27_TextMarkerParser

    // $ANTLR start synpred28_TextMarkerParser
    public final void synpred28_TextMarkerParser_fragment() throws RecognitionException {
        Expression a3 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1699:4: (a3= numberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1699:4: a3= numberExpression
        {
        pushFollow(FOLLOW_numberExpression_in_synpred28_TextMarkerParser10295);
        a3=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred28_TextMarkerParser

    // $ANTLR start synpred29_TextMarkerParser
    public final void synpred29_TextMarkerParser_fragment() throws RecognitionException {
        Expression e =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1857:4: (e= externalNumberFunction )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1857:5: e= externalNumberFunction
        {
        pushFollow(FOLLOW_externalNumberFunction_in_synpred29_TextMarkerParser11028);
        e=externalNumberFunction();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred29_TextMarkerParser

    // $ANTLR start synpred30_TextMarkerParser
    public final void synpred30_TextMarkerParser_fragment() throws RecognitionException {
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1892:5: ( listExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1892:6: listExpression
        {
        pushFollow(FOLLOW_listExpression_in_synpred30_TextMarkerParser11229);
        listExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred30_TextMarkerParser

    // $ANTLR start synpred31_TextMarkerParser
    public final void synpred31_TextMarkerParser_fragment() throws RecognitionException {
        Expression e =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1906:2: (e= externalStringFunction )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1906:3: e= externalStringFunction
        {
        pushFollow(FOLLOW_externalStringFunction_in_synpred31_TextMarkerParser11322);
        e=externalStringFunction();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred31_TextMarkerParser

    // $ANTLR start synpred32_TextMarkerParser
    public final void synpred32_TextMarkerParser_fragment() throws RecognitionException {
        Expression e2 =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1954:2: (e2= booleanCompare )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1954:3: e2= booleanCompare
        {
        pushFollow(FOLLOW_booleanCompare_in_synpred32_TextMarkerParser11529);
        e2=booleanCompare();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred32_TextMarkerParser

    // $ANTLR start synpred33_TextMarkerParser
    public final void synpred33_TextMarkerParser_fragment() throws RecognitionException {
        Expression bte =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1955:4: (bte= booleanTypeExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1955:5: bte= booleanTypeExpression
        {
        pushFollow(FOLLOW_booleanTypeExpression_in_synpred33_TextMarkerParser11549);
        bte=booleanTypeExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred33_TextMarkerParser

    // $ANTLR start synpred34_TextMarkerParser
    public final void synpred34_TextMarkerParser_fragment() throws RecognitionException {
        Expression bne =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1956:4: (bne= booleanNumberExpression )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1956:5: bne= booleanNumberExpression
        {
        pushFollow(FOLLOW_booleanNumberExpression_in_synpred34_TextMarkerParser11568);
        bne=booleanNumberExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred34_TextMarkerParser

    // $ANTLR start synpred35_TextMarkerParser
    public final void synpred35_TextMarkerParser_fragment() throws RecognitionException {
        Expression e =null;


        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1966:4: (e= externalBooleanFunction )
        // D:\\work\\workspace-uima3\\uimaj-ep-textmarker-ide\\src\\main\\java\\org\\apache\\uima\\textmarker\\ide\\core\\parser\\TextMarkerParser.g:1966:5: e= externalBooleanFunction
        {
        pushFollow(FOLLOW_externalBooleanFunction_in_synpred35_TextMarkerParser11643);
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
        "\1\45\1\22\1\32\2\113\1\32\1\uffff\1\47\1\uffff";
    static final String DFA31_maxS =
        "\1\45\1\113\1\u0080\2\113\1\u0080\1\uffff\1\113\1\uffff";
    static final String DFA31_acceptS =
        "\6\uffff\1\1\1\uffff\1\2";
    static final String DFA31_specialS =
        "\11\uffff}>";
    static final String[] DFA31_transitionS = {
            "\1\1",
            "\1\3\70\uffff\1\2",
            "\1\6\14\uffff\1\4\43\uffff\1\5\64\uffff\1\6",
            "\1\5",
            "\1\7",
            "\1\6\74\uffff\1\10\50\uffff\1\6",
            "",
            "\1\4\43\uffff\1\5",
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
 

    public static final BitSet FOLLOW_packageDeclaration_in_file_input73 = new BitSet(new long[]{0x0C041120101E8010L,0x0000000000801900L,0x000000000C1C0780L});
    public static final BitSet FOLLOW_globalStatements_in_file_input87 = new BitSet(new long[]{0x0C001120101E8010L,0x0000000000801900L,0x000000000C0C0680L});
    public static final BitSet FOLLOW_statements_in_file_input94 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_file_input100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PackageString_in_packageDeclaration121 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_dottedId_in_packageDeclaration132 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_packageDeclaration139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_statements163 = new BitSet(new long[]{0x0C001120101E8012L,0x0000000000801900L,0x000000000C0C0680L});
    public static final BitSet FOLLOW_globalStatement_in_globalStatements189 = new BitSet(new long[]{0x0004000000000002L,0x0000000000000000L,0x0000000000100100L});
    public static final BitSet FOLLOW_importStatement_in_globalStatement213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declaration_in_statement239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableDeclaration_in_statement250 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_blockDeclaration_in_statement261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleStatement_in_statement274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TypeSystemString_in_importStatement303 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_dottedComponentDeclaration_in_importStatement315 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_importStatement323 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ScriptString_in_importStatement333 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_dottedComponentDeclaration_in_importStatement345 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_importStatement353 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EngineString_in_importStatement363 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_dottedComponentDeclaration_in_importStatement375 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_importStatement383 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IntString_in_variableDeclaration410 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration416 = new BitSet(new long[]{0x0000000004000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_COMMA_in_variableDeclaration423 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration428 = new BitSet(new long[]{0x0000000004000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration438 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_variableDeclaration444 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration449 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DoubleString_in_variableDeclaration463 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration469 = new BitSet(new long[]{0x0000000004000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_COMMA_in_variableDeclaration477 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration483 = new BitSet(new long[]{0x0000000004000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration494 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_variableDeclaration500 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration505 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FloatString_in_variableDeclaration519 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration525 = new BitSet(new long[]{0x0000000004000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_COMMA_in_variableDeclaration533 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration539 = new BitSet(new long[]{0x0000000004000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration550 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_variableDeclaration556 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration561 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_StringString_in_variableDeclaration575 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration581 = new BitSet(new long[]{0x0000000004000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_COMMA_in_variableDeclaration589 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration595 = new BitSet(new long[]{0x0000000004000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration606 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_variableDeclaration612 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration617 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BooleanString_in_variableDeclaration631 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration637 = new BitSet(new long[]{0x0000000004000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_COMMA_in_variableDeclaration645 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration651 = new BitSet(new long[]{0x0000000004000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration662 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_variableDeclaration668 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration673 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TypeString_in_variableDeclaration687 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration693 = new BitSet(new long[]{0x0000000004000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_COMMA_in_variableDeclaration701 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration707 = new BitSet(new long[]{0x0000000004000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration718 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_annotationType_in_variableDeclaration724 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration729 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WORDLIST_in_variableDeclaration757 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration763 = new BitSet(new long[]{0x0000000000000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration766 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000800L});
    public static final BitSet FOLLOW_wordListExpression_in_variableDeclaration772 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration776 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WORDTABLE_in_variableDeclaration810 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration816 = new BitSet(new long[]{0x0000000000000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration819 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000800L});
    public static final BitSet FOLLOW_wordTableExpression_in_variableDeclaration825 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration830 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEANLIST_in_variableDeclaration864 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration870 = new BitSet(new long[]{0x0000000000000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration873 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_booleanListExpression_in_variableDeclaration879 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration884 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTLIST_in_variableDeclaration918 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration924 = new BitSet(new long[]{0x0000000000000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration927 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_numberListExpression_in_variableDeclaration933 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration938 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLELIST_in_variableDeclaration973 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration979 = new BitSet(new long[]{0x0000000000000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration982 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_numberListExpression_in_variableDeclaration988 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration993 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOATLIST_in_variableDeclaration1029 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration1035 = new BitSet(new long[]{0x0000000000000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration1038 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_numberListExpression_in_variableDeclaration1044 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration1049 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRINGLIST_in_variableDeclaration1091 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration1097 = new BitSet(new long[]{0x0000000000000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration1100 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_stringListExpression_in_variableDeclaration1106 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration1111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPELIST_in_variableDeclaration1153 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaration1159 = new BitSet(new long[]{0x0000000000000800L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration1162 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_typeListExpression_in_variableDeclaration1168 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_variableDeclaration1173 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionDeclaration_in_variableDeclaration1200 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionDeclaration_in_variableDeclaration1212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONDITION_in_conditionDeclaration1240 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_conditionDeclaration1246 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_conditionDeclaration1254 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionDeclaration1261 = new BitSet(new long[]{0x0040200AC0010240L,0x8042717800008EC0L,0x0000000002004048L});
    public static final BitSet FOLLOW_conditions_in_conditionDeclaration1267 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionDeclaration1269 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_conditionDeclaration1271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ACTION_in_actionDeclaration1307 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_actionDeclaration1313 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionDeclaration1321 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionDeclaration1327 = new BitSet(new long[]{0xC382824422A00420L,0x1D800007F8200803L,0x0000000000618002L});
    public static final BitSet FOLLOW_actions_in_actionDeclaration1333 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionDeclaration1335 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_actionDeclaration1337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECLARE_in_declaration1372 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_annotationType_in_declaration1378 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_declaration1388 = new BitSet(new long[]{0x0000000004000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_COMMA_in_declaration1400 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_declaration1410 = new BitSet(new long[]{0x0000000004000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_declaration1429 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECLARE_in_declaration1442 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_annotationType_in_declaration1446 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_declaration1453 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_declaration1460 = new BitSet(new long[]{0x0800100000140000L,0x0000000000001800L,0x0000000000000400L});
    public static final BitSet FOLLOW_annotationType_in_declaration1475 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_StringString_in_declaration1488 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_DoubleString_in_declaration1501 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_FloatString_in_declaration1513 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_IntString_in_declaration1527 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_BooleanString_in_declaration1539 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_declaration1559 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_declaration1571 = new BitSet(new long[]{0x0800100000140000L,0x0000000000001800L,0x0000000000000400L});
    public static final BitSet FOLLOW_annotationType_in_declaration1586 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_StringString_in_declaration1599 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_DoubleString_in_declaration1612 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_FloatString_in_declaration1624 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_IntString_in_declaration1638 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_BooleanString_in_declaration1650 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_declaration1669 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_declaration1677 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_declaration1680 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BlockString_in_blockDeclaration1741 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_AutomataBlockString_in_blockDeclaration1749 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_blockDeclaration1753 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_blockDeclaration1760 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_blockDeclaration1768 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_ruleElementWithCA_in_blockDeclaration1775 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_LCURLY_in_blockDeclaration1783 = new BitSet(new long[]{0x0C001120101E8010L,0x0020000000801900L,0x000000000C0C0680L});
    public static final BitSet FOLLOW_statements_in_blockDeclaration1789 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_blockDeclaration1795 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_ruleElementWithCA1825 = new BitSet(new long[]{0x0000000000000000L,0x0009000000030000L,0x0000000000000020L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementWithCA1831 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleElementWithCA1844 = new BitSet(new long[]{0x0040200AC0010240L,0x8062717800008EC0L,0x0000000002006048L});
    public static final BitSet FOLLOW_conditions_in_ruleElementWithCA1850 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_THEN_in_ruleElementWithCA1854 = new BitSet(new long[]{0xC382824422A00420L,0x1D800007F8200803L,0x0000000000618002L});
    public static final BitSet FOLLOW_actions_in_ruleElementWithCA1860 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_ruleElementWithCA1868 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_ruleElementWithoutCA1908 = new BitSet(new long[]{0x0000000000000002L,0x0009000000010000L,0x0000000000000020L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementWithoutCA1914 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElements_in_simpleStatement1956 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_SEMI_in_simpleStatement1965 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElement_in_ruleElements1986 = new BitSet(new long[]{0x0000000000040002L,0x0000000000800800L,0x0000000000000200L});
    public static final BitSet FOLLOW_ruleElement_in_ruleElements1995 = new BitSet(new long[]{0x0000000000040002L,0x0000000000800800L,0x0000000000000200L});
    public static final BitSet FOLLOW_ruleElementType_in_blockRuleElement2022 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementType_in_ruleElement2046 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementLiteral_in_ruleElement2057 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementComposed_in_ruleElement2068 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_ruleElementComposed2092 = new BitSet(new long[]{0x0000000000040000L,0x0000000000800800L,0x0000000000000200L});
    public static final BitSet FOLLOW_ruleElementType_in_ruleElementComposed2110 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_VBAR_in_ruleElementComposed2116 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_ruleElementType_in_ruleElementComposed2122 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_VBAR_in_ruleElementComposed2128 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_ruleElementType_in_ruleElementComposed2134 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_ruleElements_in_ruleElementComposed2151 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_ruleElementComposed2157 = new BitSet(new long[]{0x0000000000000002L,0x0009000000030000L,0x0000000000000020L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementComposed2163 = new BitSet(new long[]{0x0000000000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleElementComposed2167 = new BitSet(new long[]{0x0040200AC0010240L,0x8062717800008EC0L,0x0000000002006048L});
    public static final BitSet FOLLOW_conditions_in_ruleElementComposed2173 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_THEN_in_ruleElementComposed2177 = new BitSet(new long[]{0xC382824422A00420L,0x1D800007F8200803L,0x0000000000618002L});
    public static final BitSet FOLLOW_actions_in_ruleElementComposed2183 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_ruleElementComposed2187 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_ruleElementType2221 = new BitSet(new long[]{0x0000000000000002L,0x0009000000030000L,0x0000000000000020L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementType2227 = new BitSet(new long[]{0x0000000000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleElementType2240 = new BitSet(new long[]{0x0040200AC0010240L,0x8062717800008EC0L,0x0000000002006048L});
    public static final BitSet FOLLOW_conditions_in_ruleElementType2246 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_THEN_in_ruleElementType2250 = new BitSet(new long[]{0xC382824422A00420L,0x1D800007F8200803L,0x0000000000618002L});
    public static final BitSet FOLLOW_actions_in_ruleElementType2256 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_ruleElementType2264 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleStringExpression_in_ruleElementLiteral2317 = new BitSet(new long[]{0x0000000000000002L,0x0009000000030000L,0x0000000000000020L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementLiteral2323 = new BitSet(new long[]{0x0000000000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleElementLiteral2336 = new BitSet(new long[]{0x0040200AC0010240L,0x8062717800008EC0L,0x0000000002006048L});
    public static final BitSet FOLLOW_conditions_in_ruleElementLiteral2342 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_THEN_in_ruleElementLiteral2346 = new BitSet(new long[]{0xC382824422A00420L,0x1D800007F8200803L,0x0000000000618002L});
    public static final BitSet FOLLOW_actions_in_ruleElementLiteral2352 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_ruleElementLiteral2360 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_condition_in_conditions2414 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_COMMA_in_conditions2419 = new BitSet(new long[]{0x0040200AC0010240L,0x8042717800008EC0L,0x0000000002004048L});
    public static final BitSet FOLLOW_condition_in_conditions2425 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_action_in_actions2462 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_COMMA_in_actions2467 = new BitSet(new long[]{0xC382824422A00420L,0x1D800007F8200803L,0x0000000000618002L});
    public static final BitSet FOLLOW_action_in_actions2473 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_booleanListExpression_in_listExpression2511 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_intListExpression_in_listExpression2527 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_doubleListExpression_in_listExpression2543 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_floatListExpression_in_listExpression2559 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringListExpression_in_listExpression2575 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeListExpression_in_listExpression2591 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleBooleanListExpression_in_booleanListExpression2615 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleBooleanListExpression2636 = new BitSet(new long[]{0x0020000000000000L,0x0020000000000800L,0x0000000000020000L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_simpleBooleanListExpression2643 = new BitSet(new long[]{0x0000000004000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_COMMA_in_simpleBooleanListExpression2648 = new BitSet(new long[]{0x0020000000000000L,0x0000000000000800L,0x0000000000020000L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_simpleBooleanListExpression2654 = new BitSet(new long[]{0x0000000004000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_simpleBooleanListExpression2663 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleBooleanListExpression2680 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleIntListExpression_in_intListExpression2705 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleIntListExpression2726 = new BitSet(new long[]{0x2000040000000000L,0x0020000800800800L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleIntListExpression2733 = new BitSet(new long[]{0x0000000004000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_COMMA_in_simpleIntListExpression2738 = new BitSet(new long[]{0x2000040000000000L,0x0000000800800800L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleIntListExpression2744 = new BitSet(new long[]{0x0000000004000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_simpleIntListExpression2753 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleIntListExpression2770 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_doubleListExpression_in_numberListExpression2804 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_floatListExpression_in_numberListExpression2825 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_intListExpression_in_numberListExpression2837 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleDoubleListExpression_in_doubleListExpression2860 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleDoubleListExpression2881 = new BitSet(new long[]{0x2000040000000000L,0x0020000800800800L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleDoubleListExpression2888 = new BitSet(new long[]{0x0000000004000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_COMMA_in_simpleDoubleListExpression2893 = new BitSet(new long[]{0x2000040000000000L,0x0000000800800800L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleDoubleListExpression2899 = new BitSet(new long[]{0x0000000004000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_simpleDoubleListExpression2908 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleDoubleListExpression2925 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleFloatListExpression_in_floatListExpression2949 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleFloatListExpression2970 = new BitSet(new long[]{0x2000040000000000L,0x0020000800800800L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleFloatListExpression2977 = new BitSet(new long[]{0x0000000004000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_COMMA_in_simpleFloatListExpression2982 = new BitSet(new long[]{0x2000040000000000L,0x0000000800800800L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleFloatListExpression2988 = new BitSet(new long[]{0x0000000004000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_simpleFloatListExpression2997 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleFloatListExpression3014 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleStringListExpression_in_stringListExpression3039 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleStringListExpression3060 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_simpleStringExpression_in_simpleStringListExpression3067 = new BitSet(new long[]{0x0000000004000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_COMMA_in_simpleStringListExpression3072 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_simpleStringExpression_in_simpleStringListExpression3078 = new BitSet(new long[]{0x0000000004000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_simpleStringListExpression3087 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleStringListExpression3104 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleTypeListExpression_in_typeListExpression3129 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleTypeListExpression3150 = new BitSet(new long[]{0x0000000000040000L,0x0020000000000800L});
    public static final BitSet FOLLOW_simpleTypeExpression_in_simpleTypeListExpression3157 = new BitSet(new long[]{0x0000000004000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_COMMA_in_simpleTypeListExpression3162 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_simpleTypeExpression_in_simpleTypeListExpression3168 = new BitSet(new long[]{0x0000000004000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_RCURLY_in_simpleTypeListExpression3177 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleTypeListExpression3194 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeFunction_in_typeExpression3220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleTypeExpression_in_typeExpression3231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalTypeFunction_in_typeFunction3265 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalTypeFunction3290 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_externalTypeFunction3294 = new BitSet(new long[]{0x0000000000000000L,0x2000000000800000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalTypeFunction3301 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalTypeFunction3305 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotationType_in_simpleTypeExpression3328 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_variable3352 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_listVariable3379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAR_in_quantifierPart3406 = new BitSet(new long[]{0x0000000000000002L,0x0008000000000000L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart3412 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_quantifierPart3424 = new BitSet(new long[]{0x0000000000000002L,0x0008000000000000L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart3430 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart3442 = new BitSet(new long[]{0x0000000000000002L,0x0008000000000000L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart3448 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACK_in_quantifierPart3457 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_quantifierPart3463 = new BitSet(new long[]{0x0000000004000000L,0x0010000000000000L});
    public static final BitSet FOLLOW_COMMA_in_quantifierPart3466 = new BitSet(new long[]{0x2001040100000000L,0x0010000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_quantifierPart3473 = new BitSet(new long[]{0x0000000000000000L,0x0010000000000000L});
    public static final BitSet FOLLOW_RBRACK_in_quantifierPart3479 = new BitSet(new long[]{0x0000000000000002L,0x0008000000000000L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart3485 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionAnd_in_condition3523 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionContains_in_condition3532 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionContextCount_in_condition3541 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionCount_in_condition3550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionCurrentCount_in_condition3559 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionInList_in_condition3568 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionIsInTag_in_condition3577 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionLast_in_condition3586 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionMofN_in_condition3595 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionNear_in_condition3604 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionNot_in_condition3613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionOr_in_condition3622 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionPartOf_in_condition3631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionPosition_in_condition3640 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionRegExp_in_condition3649 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionScore_in_condition3658 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionTotalCount_in_condition3667 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionVote_in_condition3676 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionIf_in_condition3685 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionFeature_in_condition3694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionParse_in_condition3703 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionIs_in_condition3712 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionBefore_in_condition3721 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionAfter_in_condition3730 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionStartsWith_in_condition3739 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionEndsWith_in_condition3748 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionPartOfNeq_in_condition3757 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionSize_in_condition3766 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalCondition_in_condition3784 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableCondition_in_condition3793 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_variableCondition3826 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalCondition3853 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_externalCondition3856 = new BitSet(new long[]{0x0000000000000000L,0x2000000000800000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalCondition3866 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalCondition3873 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AND_in_conditionAnd3901 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionAnd3903 = new BitSet(new long[]{0x0040200AC0010240L,0x8042717800008EC0L,0x0000000002004048L});
    public static final BitSet FOLLOW_conditions_in_conditionAnd3909 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionAnd3923 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONTAINS_in_conditionContains3969 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionContains3971 = new BitSet(new long[]{0x0000000000040000L,0x0000000000020800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionContains3978 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_listExpression_in_conditionContains3986 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionContains3988 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_conditionContains3994 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionContains4003 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionContains4009 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionContains4011 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionContains4017 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionContains4020 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionContains4026 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionContains4043 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONTEXTCOUNT_in_conditionContextCount4079 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionContextCount4081 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionContextCount4087 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionContextCount4101 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionContextCount4107 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionContextCount4109 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionContextCount4115 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionContextCount4130 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_numberVariable_in_conditionContextCount4136 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionContextCount4151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COUNT_in_conditionCount4202 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionCount4204 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_listExpression_in_conditionCount4210 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount4225 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_conditionCount4231 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount4247 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCount4253 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount4255 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCount4261 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount4279 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_numberVariable_in_conditionCount4285 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionCount4301 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COUNT_in_conditionCount4317 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionCount4319 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionCount4325 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount4339 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCount4345 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount4347 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCount4353 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount4368 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_numberVariable_in_conditionCount4374 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionCount4391 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CURRENTCOUNT_in_conditionCurrentCount4431 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionCurrentCount4433 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionCurrentCount4439 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCurrentCount4453 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCurrentCount4459 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCurrentCount4461 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCurrentCount4467 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionCurrentCount4483 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_numberVariable_in_conditionCurrentCount4489 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionCurrentCount4504 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOTALCOUNT_in_conditionTotalCount4543 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionTotalCount4545 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionTotalCount4551 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionTotalCount4565 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionTotalCount4571 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionTotalCount4573 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionTotalCount4579 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionTotalCount4594 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_numberVariable_in_conditionTotalCount4600 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionTotalCount4615 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INLIST_in_conditionInList4656 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionInList4658 = new BitSet(new long[]{0x0000000000000000L,0x4000000000020800L});
    public static final BitSet FOLLOW_stringListExpression_in_conditionInList4673 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_wordListExpression_in_conditionInList4681 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionInList4690 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionInList4696 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionInList4699 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionInList4705 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionInList4723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ISINTAG_in_conditionIsInTag4766 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionIsInTag4768 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_conditionIsInTag4774 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionIsInTag4777 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_conditionIsInTag4783 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_conditionIsInTag4785 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_conditionIsInTag4791 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionIsInTag4810 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LAST_in_conditionLast4850 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionLast4852 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionLast4858 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionLast4871 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MOFN_in_conditionMofN4907 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionMofN4909 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionMofN4915 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionMofN4917 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionMofN4923 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionMofN4925 = new BitSet(new long[]{0x0040200AC0010240L,0x8042717800008EC0L,0x0000000002004048L});
    public static final BitSet FOLLOW_conditions_in_conditionMofN4931 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionMofN4946 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEAR_in_conditionNear4978 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionNear4980 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionNear4986 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionNear4988 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionNear4994 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionNear4996 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionNear5002 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionNear5010 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionNear5016 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionNear5019 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionNear5025 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionNear5045 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_conditionNot5078 = new BitSet(new long[]{0x0040200AC0010240L,0x8042717800008EC0L,0x0000000002004048L});
    public static final BitSet FOLLOW_condition_in_conditionNot5084 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_conditionNot5095 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionNot5097 = new BitSet(new long[]{0x0040200AC0010240L,0x8042717800008EC0L,0x0000000002004048L});
    public static final BitSet FOLLOW_condition_in_conditionNot5103 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionNot5105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OR_in_conditionOr5145 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionOr5147 = new BitSet(new long[]{0x0040200AC0010240L,0x8042717800008EC0L,0x0000000002004048L});
    public static final BitSet FOLLOW_conditions_in_conditionOr5153 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionOr5166 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARTOF_in_conditionPartOf5194 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionPartOf5196 = new BitSet(new long[]{0x0000000000040000L,0x0000000000020800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionPartOf5203 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionPartOf5209 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionPartOf5226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARTOFNEQ_in_conditionPartOfNeq5259 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionPartOfNeq5261 = new BitSet(new long[]{0x0000000000040000L,0x0000000000020800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionPartOfNeq5268 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionPartOfNeq5274 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionPartOfNeq5291 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_POSITION_in_conditionPosition5328 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionPosition5330 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionPosition5336 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionPosition5338 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionPosition5344 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionPosition5357 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REGEXP_in_conditionRegExp5385 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionRegExp5387 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_conditionRegExp5393 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionRegExp5396 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionRegExp5402 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionRegExp5420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SCORE_in_conditionScore5454 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionScore5456 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionScore5462 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionScore5465 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionScore5471 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionScore5480 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_numberVariable_in_conditionScore5486 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionScore5503 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VOTE_in_conditionVote5535 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionVote5537 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionVote5543 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionVote5545 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionVote5551 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionVote5564 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_conditionIf5598 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionIf5600 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionIf5606 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionIf5619 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FEATURE_in_conditionFeature5658 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionFeature5660 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_conditionFeature5666 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionFeature5668 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_conditionFeature5674 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionFeature5687 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARSE_in_conditionParse5718 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionParse5720 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_genericVariableReference_in_conditionParse5729 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionParse5742 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IS_in_conditionIs5772 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionIs5774 = new BitSet(new long[]{0x0000000000040000L,0x0000000000020800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionIs5781 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionIs5787 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionIs5801 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BEFORE_in_conditionBefore5830 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionBefore5832 = new BitSet(new long[]{0x0000000000040000L,0x0000000000020800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionBefore5839 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionBefore5845 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionBefore5859 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AFTER_in_conditionAfter5888 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionAfter5890 = new BitSet(new long[]{0x0000000000040000L,0x0000000000020800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionAfter5897 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionAfter5903 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionAfter5917 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STARTSWITH_in_conditionStartsWith5950 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionStartsWith5952 = new BitSet(new long[]{0x0000000000040000L,0x0000000000020800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionStartsWith5959 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionStartsWith5965 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionStartsWith5979 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ENDSWITH_in_conditionEndsWith6012 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionEndsWith6014 = new BitSet(new long[]{0x0000000000040000L,0x0000000000020800L});
    public static final BitSet FOLLOW_typeExpression_in_conditionEndsWith6021 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionEndsWith6027 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionEndsWith6041 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SIZE_in_conditionSize6074 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionSize6076 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_listExpression_in_conditionSize6082 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionSize6085 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionSize6091 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionSize6093 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_conditionSize6099 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_conditionSize6104 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_numberVariable_in_conditionSize6110 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionSize6125 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionColor_in_action6153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionDel_in_action6162 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionLog_in_action6171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMark_in_action6180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMarkScore_in_action6189 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMarkFast_in_action6198 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMarkLast_in_action6207 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionReplace_in_action6216 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionRetainMarkup_in_action6225 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionRetainType_in_action6234 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionFilterMarkup_in_action6243 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionFilterType_in_action6252 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionCreate_in_action6261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionFill_in_action6270 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionCall_in_action6279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionAssign_in_action6288 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionSetFeature_in_action6297 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionGetFeature_in_action6306 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionUnmark_in_action6315 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionUnmarkAll_in_action6324 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionTransfer_in_action6333 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMarkOnce_in_action6342 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionTrie_in_action6351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionGather_in_action6360 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionExec_in_action6370 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMarkTable_in_action6379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionAdd_in_action6388 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionRemove_in_action6397 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionRemoveDuplicate_in_action6406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMerge_in_action6415 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionGet_in_action6424 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionGetList_in_action6434 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMatchedText_in_action6443 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionClear_in_action6452 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionExpand_in_action6461 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionConfigure_in_action6470 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionDynamicAnchoring_in_action6479 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalAction_in_action6497 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableAction_in_action6506 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_variableAction6537 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalAction6561 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_externalAction6565 = new BitSet(new long[]{0x0000000000000000L,0x2000000000800000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalAction6574 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalAction6579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CREATE_in_actionCreate6615 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionCreate6617 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionCreate6623 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionCreate6630 = new BitSet(new long[]{0x2001040100000000L,0x2200000800C00800L,0x0000000000001204L});
    public static final BitSet FOLLOW_numberExpression_in_actionCreate6651 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionCreate6668 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionCreate6674 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionCreate6680 = new BitSet(new long[]{0x0000000000000000L,0x2200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionCreate6698 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionCreate6700 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_actionCreate6706 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionCreate6716 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionCreate6722 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionCreate6724 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_actionCreate6730 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionCreate6761 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKTABLE_in_actionMarkTable6796 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkTable6798 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkTable6809 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkTable6811 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkTable6822 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkTable6824 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000800L});
    public static final BitSet FOLLOW_wordTableExpression_in_actionMarkTable6834 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkTable6842 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionMarkTable6853 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionMarkTable6855 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkTable6861 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkTable6871 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionMarkTable6877 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionMarkTable6879 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkTable6885 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkTable6909 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GATHER_in_actionGather6943 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionGather6945 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionGather6951 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionGather6965 = new BitSet(new long[]{0x2001040100000000L,0x2200000800C00800L,0x0000000000001204L});
    public static final BitSet FOLLOW_numberExpression_in_actionGather6977 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionGather6993 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionGather6999 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionGather7006 = new BitSet(new long[]{0x0000000000000000L,0x2200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionGather7019 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionGather7021 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C20800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionGather7028 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_numberListExpression_in_actionGather7036 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionGather7047 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionGather7053 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionGather7055 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C20800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionGather7062 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_numberListExpression_in_actionGather7070 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionGather7102 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILL_in_actionFill7137 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionFill7139 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionFill7145 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionFill7163 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionFill7169 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionFill7171 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_actionFill7181 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionFill7203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLOR_in_actionColor7240 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionColor7242 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionColor7248 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionColor7262 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionColor7273 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionColor7287 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionColor7297 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionColor7311 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionColor7321 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionColor7337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEL_in_actionDel7369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LOG_in_actionLog7415 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionLog7417 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionLog7423 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionLog7426 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_LogLevel_in_actionLog7432 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionLog7448 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARK_in_actionMark7486 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMark7488 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionMark7499 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMark7517 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionMark7533 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMark7555 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXPAND_in_actionExpand7592 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionExpand7594 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionExpand7605 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionExpand7623 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionExpand7639 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionExpand7661 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKSCORE_in_actionMarkScore7698 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkScore7700 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkScore7706 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkScore7708 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkScore7714 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkScore7732 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkScore7748 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkScore7770 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKONCE_in_actionMarkOnce7807 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkOnce7809 = new BitSet(new long[]{0x2001040100040000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkOnce7826 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkOnce7828 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkOnce7846 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkOnce7864 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkOnce7880 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkOnce7902 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKFAST_in_actionMarkFast7934 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkFast7936 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkFast7942 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkFast7955 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000800L});
    public static final BitSet FOLLOW_wordListExpression_in_actionMarkFast7961 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkFast7975 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionMarkFast7981 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkFast7984 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkFast7990 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkFast8008 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKLAST_in_actionMarkLast8040 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkLast8042 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkLast8048 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkLast8061 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REPLACE_in_actionReplace8094 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionReplace8096 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionReplace8102 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionReplace8115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RETAINMARKUP_in_actionRetainMarkup8152 = new BitSet(new long[]{0x0000000000000002L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionRetainMarkup8155 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionRetainMarkup8161 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionRetainMarkup8177 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionRetainMarkup8183 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionRetainMarkup8200 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RETAINTYPE_in_actionRetainType8253 = new BitSet(new long[]{0x0000000000000002L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionRetainType8256 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionRetainType8262 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionRetainType8278 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionRetainType8284 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionRetainType8301 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILTERMARKUP_in_actionFilterMarkup8350 = new BitSet(new long[]{0x0000000000000002L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionFilterMarkup8353 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionFilterMarkup8359 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionFilterMarkup8375 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionFilterMarkup8381 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionFilterMarkup8398 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILTERTYPE_in_actionFilterType8443 = new BitSet(new long[]{0x0000000000000002L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionFilterType8446 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionFilterType8452 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionFilterType8468 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionFilterType8474 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionFilterType8491 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CALL_in_actionCall8540 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionCall8546 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_dottedComponentReference_in_actionCall8568 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionCall8582 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONFIGURE_in_actionConfigure8617 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionConfigure8623 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_dottedComponentReference_in_actionConfigure8645 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionConfigure8666 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionConfigure8672 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionConfigure8674 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_actionConfigure8680 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionConfigure8690 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionConfigure8696 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionConfigure8698 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_actionConfigure8704 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionConfigure8728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXEC_in_actionExec8763 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionExec8769 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_dottedComponentReference_in_actionExec8787 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionExec8803 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_typeListExpression_in_actionExec8809 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionExec8825 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSIGN_in_actionAssign8867 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionAssign8869 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_actionAssign8880 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionAssign8898 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_actionAssign8904 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionAssign8912 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SETFEATURE_in_actionSetFeature8949 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionSetFeature8951 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionSetFeature8957 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionSetFeature8971 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_actionSetFeature8977 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionSetFeature8990 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GETFEATURE_in_actionGetFeature9019 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionGetFeature9021 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionGetFeature9027 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionGetFeature9040 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_variable_in_actionGetFeature9046 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionGetFeature9059 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DYNAMICANCHORING_in_actionDynamicAnchoring9089 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionDynamicAnchoring9091 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionDynamicAnchoring9097 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionDynamicAnchoring9112 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionDynamicAnchoring9118 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionDynamicAnchoring9132 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionDynamicAnchoring9138 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionDynamicAnchoring9155 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNMARK_in_actionUnmark9185 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionUnmark9187 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionUnmark9193 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionUnmark9206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNMARKALL_in_actionUnmarkAll9235 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionUnmarkAll9237 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionUnmarkAll9243 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionUnmarkAll9257 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_typeListExpression_in_actionUnmarkAll9263 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionUnmarkAll9278 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRANSFER_in_actionTransfer9310 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionTransfer9312 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionTransfer9318 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionTransfer9331 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRIE_in_actionTrie9369 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionTrie9371 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionTrie9385 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionTrie9388 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionTrie9403 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie9416 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionTrie9422 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionTrie9426 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_actionTrie9441 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie9455 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000800L});
    public static final BitSet FOLLOW_wordListExpression_in_actionTrie9461 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie9477 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionTrie9483 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie9490 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionTrie9496 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie9503 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionTrie9509 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie9516 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionTrie9522 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie9529 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionTrie9535 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionTrie9558 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ADD_in_actionAdd9596 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionAdd9598 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_listVariable_in_actionAdd9604 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionAdd9618 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_actionAdd9624 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionAdd9641 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REMOVE_in_actionRemove9675 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionRemove9677 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_listVariable_in_actionRemove9683 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionRemove9697 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_actionRemove9703 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionRemove9720 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REMOVEDUPLICATE_in_actionRemoveDuplicate9750 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionRemoveDuplicate9752 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_listVariable_in_actionRemoveDuplicate9758 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionRemoveDuplicate9771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MERGE_in_actionMerge9808 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMerge9810 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionMerge9816 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMerge9830 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_listVariable_in_actionMerge9836 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMerge9850 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_listExpression_in_actionMerge9856 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMerge9866 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_listExpression_in_actionMerge9872 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMerge9889 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GET_in_actionGet9918 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionGet9920 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_listExpression_in_actionGet9926 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionGet9939 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_variable_in_actionGet9945 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionGet9958 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionGet9964 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionGet9977 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GETLIST_in_actionGetList10007 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionGetList10009 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_listVariable_in_actionGetList10015 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_actionGetList10028 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionGetList10034 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionGetList10047 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MATCHEDTEXT_in_actionMatchedText10084 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMatchedText10086 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_variable_in_actionMatchedText10097 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_actionMatchedText10109 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_actionMatchedText10115 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMatchedText10137 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLEAR_in_actionClear10170 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_actionClear10172 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_listVariable_in_actionClear10178 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionClear10191 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_varArgumentList10213 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_varArgumentList10219 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_varArgumentList10224 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_varArgumentList10230 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_varArgumentList10236 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringExpression_in_argument10273 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanExpression_in_argument10284 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpression_in_argument10295 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_argument10306 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_dottedIdentifier10343 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_DOT_in_dottedIdentifier10356 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_dottedIdentifier10366 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_Identifier_in_dottedId10398 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_DOT_in_dottedId10411 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_dottedId10421 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_Identifier_in_dottedComponentReference10456 = new BitSet(new long[]{0x0000008000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_set_in_dottedComponentReference10469 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_dottedComponentReference10485 = new BitSet(new long[]{0x0000008000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_Identifier_in_dottedComponentDeclaration10519 = new BitSet(new long[]{0x0000008000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_set_in_dottedComponentDeclaration10532 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_Identifier_in_dottedComponentDeclaration10548 = new BitSet(new long[]{0x0000008000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_annotationTypeVariableReference_in_annotationType10582 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BasicAnnotationType_in_annotationType10593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dottedId_in_annotationTypeVariableReference10622 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_wordListExpression10646 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RessourceLiteral_in_wordListExpression10659 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_wordTableExpression10683 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RessourceLiteral_in_wordTableExpression10696 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_additiveExpression_in_numberExpression10720 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression10746 = new BitSet(new long[]{0x0000000000000002L,0x0001000800000000L});
    public static final BitSet FOLLOW_set_in_additiveExpression10755 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression10765 = new BitSet(new long[]{0x0000000000000002L,0x0001000800000000L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_multiplicativeExpression10794 = new BitSet(new long[]{0x0000000000000002L,0x0000800000000000L,0x0000000000000030L});
    public static final BitSet FOLLOW_set_in_multiplicativeExpression10803 = new BitSet(new long[]{0x2000040000000000L,0x0000000800800800L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_multiplicativeExpression10821 = new BitSet(new long[]{0x0000000000000002L,0x0000800000000000L,0x0000000000000030L});
    public static final BitSet FOLLOW_numberFunction_in_multiplicativeExpression10837 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_numberExpressionInPar10861 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_numberExpressionInPar10867 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_numberExpressionInPar10873 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_simpleNumberExpression10898 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_numberVariable_in_simpleNumberExpression10905 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_simpleNumberExpression10920 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_DecimalLiteral_in_simpleNumberExpression10928 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_simpleNumberExpression10942 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_FloatingPointLiteral_in_simpleNumberExpression10949 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpressionInPar_in_simpleNumberExpression10965 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_numberFunction10990 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_numberExpressionInPar_in_numberFunction11012 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalNumberFunction_in_numberFunction11036 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalNumberFunction11061 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_externalNumberFunction11065 = new BitSet(new long[]{0x0000000000000000L,0x2000000000800000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalNumberFunction11072 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalNumberFunction11075 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_numberVariable11106 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_numberVariable11119 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_numberVariable11132 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringFunction_in_stringExpression11170 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleStringExpression_in_stringExpression11183 = new BitSet(new long[]{0x0000000000000002L,0x0001000000000000L});
    public static final BitSet FOLLOW_PLUS_in_stringExpression11189 = new BitSet(new long[]{0x0020000000040000L,0x0000000000820800L,0x0000000000020200L});
    public static final BitSet FOLLOW_simpleStringExpression_in_stringExpression11196 = new BitSet(new long[]{0x0000000000000002L,0x0001000000000000L});
    public static final BitSet FOLLOW_numberExpressionInPar_in_stringExpression11208 = new BitSet(new long[]{0x0000000000000002L,0x0001000000000000L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_stringExpression11220 = new BitSet(new long[]{0x0000000000000002L,0x0001000000000000L});
    public static final BitSet FOLLOW_listExpression_in_stringExpression11237 = new BitSet(new long[]{0x0000000000000002L,0x0001000000000000L});
    public static final BitSet FOLLOW_typeExpression_in_stringExpression11249 = new BitSet(new long[]{0x0000000000000002L,0x0001000000000000L});
    public static final BitSet FOLLOW_REMOVESTRING_in_stringFunction11286 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_stringFunction11288 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_variable_in_stringFunction11294 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_stringFunction11297 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000800L,0x0000000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_stringFunction11303 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_stringFunction11308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalStringFunction_in_stringFunction11330 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalStringFunction11355 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_externalStringFunction11359 = new BitSet(new long[]{0x0000000000000000L,0x2000000000800000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalStringFunction11366 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalStringFunction11369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_StringLiteral_in_simpleStringExpression11394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleStringExpression11409 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_composedBooleanExpression_in_booleanExpression11442 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_booleanExpression11453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_literalBooleanExpression_in_simpleBooleanExpression11478 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleBooleanExpression11491 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanCompare_in_composedBooleanExpression11537 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanTypeExpression_in_composedBooleanExpression11557 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanNumberExpression_in_composedBooleanExpression11576 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanFunction_in_composedBooleanExpression11586 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_XOR_in_booleanFunction11611 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_booleanFunction11613 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_booleanFunction11619 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_booleanFunction11621 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_booleanFunction11627 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_booleanFunction11629 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalBooleanFunction_in_booleanFunction11651 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalBooleanFunction11677 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_externalBooleanFunction11680 = new BitSet(new long[]{0x0000000000000000L,0x2000000000800000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalBooleanFunction11687 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalBooleanFunction11691 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_booleanCompare11716 = new BitSet(new long[]{0x0000400000000000L,0x0000008000000000L});
    public static final BitSet FOLLOW_set_in_booleanCompare11722 = new BitSet(new long[]{0x0020000000040000L,0x0000000000800800L,0x0000000020020000L});
    public static final BitSet FOLLOW_booleanExpression_in_booleanCompare11734 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_literalBooleanExpression11761 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_literalBooleanExpression11771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_booleanTypeExpression11798 = new BitSet(new long[]{0x0000400000000000L,0x0000008000000000L});
    public static final BitSet FOLLOW_set_in_booleanTypeExpression11805 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000800L});
    public static final BitSet FOLLOW_typeExpression_in_booleanTypeExpression11818 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_booleanNumberExpression11841 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_booleanNumberExpression11848 = new BitSet(new long[]{0x0000400000000000L,0x00000080000C000CL});
    public static final BitSet FOLLOW_set_in_booleanNumberExpression11855 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_booleanNumberExpression11884 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_booleanNumberExpression11887 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_genericVariableReference11907 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementType_in_synpred1_TextMarkerParser2100 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_VBAR_in_synpred1_TextMarkerParser2102 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElements_in_synpred2_TextMarkerParser2144 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanListExpression_in_synpred5_TextMarkerParser2503 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_intListExpression_in_synpred6_TextMarkerParser2519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_doubleListExpression_in_synpred7_TextMarkerParser2535 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_floatListExpression_in_synpred8_TextMarkerParser2551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringListExpression_in_synpred9_TextMarkerParser2567 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeListExpression_in_synpred10_TextMarkerParser2583 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_doubleListExpression_in_synpred11_TextMarkerParser2796 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_floatListExpression_in_synpred12_TextMarkerParser2817 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalCondition_in_synpred14_TextMarkerParser3776 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COUNT_in_synpred15_TextMarkerParser4202 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_LPAREN_in_synpred15_TextMarkerParser4204 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});
    public static final BitSet FOLLOW_listExpression_in_synpred15_TextMarkerParser4210 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_synpred15_TextMarkerParser4225 = new BitSet(new long[]{0x2021040100040000L,0x0200000800C00800L,0x0000000020021204L});
    public static final BitSet FOLLOW_argument_in_synpred15_TextMarkerParser4231 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_synpred15_TextMarkerParser4247 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_synpred15_TextMarkerParser4253 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_COMMA_in_synpred15_TextMarkerParser4255 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_synpred15_TextMarkerParser4261 = new BitSet(new long[]{0x0000000004000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_COMMA_in_synpred15_TextMarkerParser4279 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_numberVariable_in_synpred15_TextMarkerParser4285 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_synpred15_TextMarkerParser4301 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringListExpression_in_synpred16_TextMarkerParser4666 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalAction_in_synpred17_TextMarkerParser6489 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_synpred18_TextMarkerParser6657 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_synpred18_TextMarkerParser6663 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_synpred19_TextMarkerParser6983 = new BitSet(new long[]{0x2001040100000000L,0x0000000800C00800L,0x0000000000001004L});
    public static final BitSet FOLLOW_numberExpression_in_synpred19_TextMarkerParser6989 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpression_in_synpred23_TextMarkerParser7817 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_synpred24_TextMarkerParser7837 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringExpression_in_synpred26_TextMarkerParser10273 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanExpression_in_synpred27_TextMarkerParser10284 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpression_in_synpred28_TextMarkerParser10295 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalNumberFunction_in_synpred29_TextMarkerParser11028 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_listExpression_in_synpred30_TextMarkerParser11229 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalStringFunction_in_synpred31_TextMarkerParser11322 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanCompare_in_synpred32_TextMarkerParser11529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanTypeExpression_in_synpred33_TextMarkerParser11549 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanNumberExpression_in_synpred34_TextMarkerParser11568 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalBooleanFunction_in_synpred35_TextMarkerParser11643 = new BitSet(new long[]{0x0000000000000002L});

}