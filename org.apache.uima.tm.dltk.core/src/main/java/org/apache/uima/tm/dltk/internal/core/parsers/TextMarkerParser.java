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

// $ANTLR 3.2 Sep 23, 2009 12:02:23 D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g 2011-06-16 19:07:54

package org.apache.uima.tm.dltk.internal.core.parsers;
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



import org.antlr.runtime.*;
import org.apache.uima.tm.dltk.core.extensions.TextMarkerExternalFactory;
import org.apache.uima.tm.dltk.internal.core.builder.DescriptorManager;
import org.apache.uima.tm.dltk.parser.ast.ActionFactory;
import org.apache.uima.tm.dltk.parser.ast.ComponentDeclaration;
import org.apache.uima.tm.dltk.parser.ast.ComponentReference;
import org.apache.uima.tm.dltk.parser.ast.ComposedRuleElement;
import org.apache.uima.tm.dltk.parser.ast.ConditionFactory;
import org.apache.uima.tm.dltk.parser.ast.ExpressionFactory;
import org.apache.uima.tm.dltk.parser.ast.ScriptFactory;
import org.apache.uima.tm.dltk.parser.ast.StatementFactory;
import org.apache.uima.tm.dltk.parser.ast.TMTypeConstants;
import org.apache.uima.tm.dltk.parser.ast.TextMarkerBlock;
import org.apache.uima.tm.dltk.parser.ast.TextMarkerExpression;
import org.apache.uima.tm.dltk.parser.ast.TextMarkerRule;
import org.apache.uima.tm.dltk.parser.ast.TextMarkerRuleElement;
import org.apache.uima.tm.dltk.parser.ast.TextMarkerScriptBlock;
import org.apache.uima.tm.dltk.parser.ast.actions.TextMarkerAction;
import org.apache.uima.tm.dltk.parser.ast.conditions.TextMarkerCondition;
import org.apache.uima.tm.dltk.parser.ast.declarations.TextMarkerFeatureDeclaration;
import org.apache.uima.tm.dltk.parser.ast.declarations.TextMarkerPackageDeclaration;

import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class TextMarkerParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "DocComment", "Annotation", "ListIdentifier", "TRIE", "CONTAINS", "DECLARE", "WORDLIST", "WORDTABLE", "AND", "CONTEXTCOUNT", "COUNT", "TOTALCOUNT", "CURRENTCOUNT", "INLIST", "ISINTAG", "LAST", "MOFN", "NEAR", "OR", "PARTOF", "PARTOFNEQ", "POSITION", "REGEXP", "SCORE", "VOTE", "IF", "FEATURE", "PARSE", "CREATE", "GATHER", "FILL", "ATTRIBUTE", "COLOR", "DEL", "LOG", "MARK", "MARKSCORE", "MARKONCE", "MARKFAST", "MARKTABLE", "MARKLAST", "REPLACE", "RETAINMARKUP", "RETAINTYPE", "FILTERMARKUP", "FILTERTYPE", "CALL", "EXEC", "ASSIGN", "SETFEATURE", "GETFEATURE", "UNMARK", "UNMARKALL", "TRANSFER", "EXPAND", "BEFORE", "AFTER", "IS", "STARTSWITH", "ENDSWITH", "NOT", "ADD", "REMOVE", "REMOVEDUPLICATE", "MERGE", "GET", "GETLIST", "SIZE", "MATCHEDTEXT", "REMOVESTRING", "CLEAR", "THEN", "BasicAnnotationType", "LogLevel", "OldColor", "PackageString", "ScriptString", "EngineString", "BlockString", "AutomataBlockString", "TypeString", "IntString", "DoubleString", "StringString", "BooleanString", "TypeSystemString", "SymbolString", "CONDITION", "ACTION", "BOOLEANLIST", "INTLIST", "DOUBLELIST", "STRINGLIST", "TYPELIST", "EXP", "LOGN", "SIN", "COS", "TAN", "XOR", "TRUE", "FALSE", "HexDigit", "IntegerTypeSuffix", "HexLiteral", "DecimalLiteral", "OctalLiteral", "Exponent", "FloatTypeSuffix", "FloatingPointLiteral", "EscapeSequence", "CharacterLiteral", "StringLiteral", "RessourceLiteral", "UnicodeEscape", "OctalEscape", "Letter", "JavaIDDigit", "Identifier", "LPAREN", "RPAREN", "LBRACK", "RBRACK", "LCURLY", "RCURLY", "CIRCUMFLEX", "AT", "DOT", "COLON", "COMMA", "SEMI", "PLUS", "MINUS", "STAR", "SLASH", "VBAR", "AMPER", "LESS", "GREATER", "ASSIGN_EQUAL", "PERCENT", "QUESTION", "EQUAL", "NOTEQUAL", "ALT_NOTEQUAL", "LESSEQUAL", "GREATEREQUAL", "WS", "COMMENT", "LINE_COMMENT"
    };
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
    public static final int EOF=-1;
    public static final int Identifier=122;
    public static final int ACTION=92;
    public static final int CLEAR=74;
    public static final int NOTEQUAL=147;
    public static final int ENDSWITH=63;
    public static final int DOUBLELIST=95;
    public static final int VBAR=139;
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
    public static final int UNMARK=55;
    public static final int PARTOF=23;
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
    public static final int POSITION=25;
    public static final int LPAREN=123;
    public static final int IF=29;
    public static final int AT=130;
    public static final int LogLevel=77;
    public static final int SLASH=138;
    public static final int THEN=75;
    public static final int FILL=34;
    public static final int COMMA=133;
    public static final int IS=61;
    public static final int GETLIST=70;
    public static final int REPLACE=45;
    public static final int AMPER=140;
    public static final int EQUAL=146;
    public static final int GATHER=33;
    public static final int INLIST=17;
    public static final int PLUS=135;
    public static final int BooleanString=88;
    public static final int GETFEATURE=54;
    public static final int DOT=131;
    public static final int ListIdentifier=6;
    public static final int PARTOFNEQ=24;
    public static final int ADD=65;
    public static final int BOOLEANLIST=93;
    public static final int MARKTABLE=43;
    public static final int HexLiteral=108;
    public static final int XOR=103;
    public static final int MARK=39;
    public static final int PERCENT=144;
    public static final int PackageString=79;
    public static final int PARSE=31;
    public static final int OldColor=78;
    public static final int MERGE=68;
    public static final int MARKLAST=44;
    public static final int CONTEXTCOUNT=13;
    public static final int BEFORE=59;
    public static final int EXEC=51;
    public static final int AFTER=60;
    public static final int MINUS=136;
    public static final int DecimalLiteral=109;
    public static final int TRUE=104;
    public static final int SEMI=134;
    public static final int FEATURE=30;
    public static final int SymbolString=90;
    public static final int StringString=87;
    public static final int StringLiteral=116;
    public static final int COLON=132;
    public static final int SCORE=27;
    public static final int QUESTION=145;
    public static final int UnicodeEscape=118;
    public static final int STARTSWITH=62;
    public static final int RCURLY=128;
    public static final int ASSIGN=52;
    public static final int TOTALCOUNT=15;
    public static final int DECLARE=9;
    public static final int ISINTAG=18;
    public static final int DocComment=4;
    public static final int MOFN=20;
    public static final int SETFEATURE=53;
    public static final int OctalEscape=119;
    public static final int DoubleString=86;
    public static final int CIRCUMFLEX=129;
    public static final int ALT_NOTEQUAL=148;

    // delegates
    // delegators


        public TextMarkerParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public TextMarkerParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return TextMarkerParser.tokenNames; }
    public String getGrammarFileName() { return "D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g"; }


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
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:210:1: file_input[String module] : p= packageDeclaration gs= globalStatements s= statements EOF ;
    public final void file_input(String module) throws RecognitionException {
        TextMarkerPackageDeclaration p = null;

        List<Statement> gs = null;

        List<Statement> s = null;



        TextMarkerScriptBlock rootBlock = null;
        List<Statement> stmts = new ArrayList<Statement>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:215:2: (p= packageDeclaration gs= globalStatements s= statements EOF )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:216:2: p= packageDeclaration gs= globalStatements s= statements EOF
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
            if ( state.backtracking==0 ) {
              blockDeclaration_stack.push(new blockDeclaration_scope());((blockDeclaration_scope)blockDeclaration_stack.peek()).env = rootBlock;
            }
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
        }
        return ;
    }
    // $ANTLR end "file_input"


    // $ANTLR start "packageDeclaration"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:244:1: packageDeclaration returns [TextMarkerPackageDeclaration pack] : pString= PackageString p= dottedId SEMI ;
    public final TextMarkerPackageDeclaration packageDeclaration() throws RecognitionException {
        TextMarkerPackageDeclaration pack = null;

        Token pString=null;
        Token p = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:245:2: (pString= PackageString p= dottedId SEMI )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:245:4: pString= PackageString p= dottedId SEMI
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
        }
        return pack;
    }
    // $ANTLR end "packageDeclaration"


    // $ANTLR start "statements"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:259:1: statements returns [List<Statement> stmts = new ArrayList<Statement>()] : (morestmts= statement )* ;
    public final List<Statement> statements() throws RecognitionException {
        List<Statement> stmts =  new ArrayList<Statement>();

        List<Statement> morestmts = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:260:2: ( (morestmts= statement )* )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:261:2: (morestmts= statement )*
            {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:261:2: (morestmts= statement )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=DECLARE && LA1_0<=WORDTABLE)||LA1_0==BasicAnnotationType||(LA1_0>=BlockString && LA1_0<=BooleanString)||(LA1_0>=CONDITION && LA1_0<=TYPELIST)||LA1_0==StringLiteral||(LA1_0>=Identifier && LA1_0<=LPAREN)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:261:3: morestmts= statement
            	    {
            	    pushFollow(FOLLOW_statement_in_statements163);
            	    morestmts=statement();

            	    state._fsp--;
            	    if (state.failed) return stmts;
            	    if ( state.backtracking==0 ) {
            	      if(morestmts != null) {stmts.addAll(morestmts);}
            	    }

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
        }
        return stmts;
    }
    // $ANTLR end "statements"


    // $ANTLR start "globalStatements"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:264:1: globalStatements returns [List<Statement> stmts = new ArrayList<Statement>()] : (morestmts= globalStatement )* ;
    public final List<Statement> globalStatements() throws RecognitionException {
        List<Statement> stmts =  new ArrayList<Statement>();

        List<Statement> morestmts = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:265:2: ( (morestmts= globalStatement )* )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:266:2: (morestmts= globalStatement )*
            {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:266:2: (morestmts= globalStatement )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>=ScriptString && LA2_0<=EngineString)||LA2_0==TypeSystemString) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:266:3: morestmts= globalStatement
            	    {
            	    pushFollow(FOLLOW_globalStatement_in_globalStatements189);
            	    morestmts=globalStatement();

            	    state._fsp--;
            	    if (state.failed) return stmts;
            	    if ( state.backtracking==0 ) {
            	      if(morestmts != null) {stmts.addAll(morestmts);}
            	    }

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
        }
        return stmts;
    }
    // $ANTLR end "globalStatements"


    // $ANTLR start "globalStatement"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:269:1: globalStatement returns [List<Statement> stmts = new ArrayList<Statement>()] : stmtImport= importStatement ;
    public final List<Statement> globalStatement() throws RecognitionException {
        List<Statement> stmts =  new ArrayList<Statement>();

        Statement stmtImport = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:270:2: (stmtImport= importStatement )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:271:2: stmtImport= importStatement
            {
            pushFollow(FOLLOW_importStatement_in_globalStatement213);
            stmtImport=importStatement();

            state._fsp--;
            if (state.failed) return stmts;
            if ( state.backtracking==0 ) {
              stmts.add(stmtImport);
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
        }
        return stmts;
    }
    // $ANTLR end "globalStatement"


    // $ANTLR start "statement"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:274:1: statement returns [List<Statement> stmts = new ArrayList<Statement>()] : (stmts1= declaration | stmtVariable= variableDeclaration | stmt3= blockDeclaration | stmt2= simpleStatement ) ;
    public final List<Statement> statement() throws RecognitionException {
        List<Statement> stmts =  new ArrayList<Statement>();

        List<Statement> stmts1 = null;

        List<Statement> stmtVariable = null;

        TextMarkerBlock stmt3 = null;

        TextMarkerRule stmt2 = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:275:2: ( (stmts1= declaration | stmtVariable= variableDeclaration | stmt3= blockDeclaration | stmt2= simpleStatement ) )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:276:2: (stmts1= declaration | stmtVariable= variableDeclaration | stmt3= blockDeclaration | stmt2= simpleStatement )
            {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:276:2: (stmts1= declaration | stmtVariable= variableDeclaration | stmt3= blockDeclaration | stmt2= simpleStatement )
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:276:4: stmts1= declaration
                    {
                    pushFollow(FOLLOW_declaration_in_statement239);
                    stmts1=declaration();

                    state._fsp--;
                    if (state.failed) return stmts;
                    if ( state.backtracking==0 ) {
                      stmts.addAll(stmts1);
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:277:4: stmtVariable= variableDeclaration
                    {
                    pushFollow(FOLLOW_variableDeclaration_in_statement250);
                    stmtVariable=variableDeclaration();

                    state._fsp--;
                    if (state.failed) return stmts;
                    if ( state.backtracking==0 ) {
                      stmts.addAll(stmtVariable);
                    }

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:278:4: stmt3= blockDeclaration
                    {
                    pushFollow(FOLLOW_blockDeclaration_in_statement261);
                    stmt3=blockDeclaration();

                    state._fsp--;
                    if (state.failed) return stmts;
                    if ( state.backtracking==0 ) {
                      stmts.add(stmt3);
                    }

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:280:4: stmt2= simpleStatement
                    {
                    pushFollow(FOLLOW_simpleStatement_in_statement274);
                    stmt2=simpleStatement();

                    state._fsp--;
                    if (state.failed) return stmts;
                    if ( state.backtracking==0 ) {
                      stmts.add(stmt2);
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
        }
        return stmts;
    }
    // $ANTLR end "statement"


    // $ANTLR start "importStatement"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:286:1: importStatement returns [Statement stmt = null] : (im= TypeSystemString name= dottedComponentDeclaration SEMI | im= ScriptString name= dottedComponentDeclaration SEMI | im= EngineString name= dottedComponentDeclaration SEMI );
    public final Statement importStatement() throws RecognitionException {
        Statement stmt =  null;

        Token im=null;
        ComponentDeclaration name = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:287:2: (im= TypeSystemString name= dottedComponentDeclaration SEMI | im= ScriptString name= dottedComponentDeclaration SEMI | im= EngineString name= dottedComponentDeclaration SEMI )
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:288:2: im= TypeSystemString name= dottedComponentDeclaration SEMI
                    {
                    im=(Token)match(input,TypeSystemString,FOLLOW_TypeSystemString_in_importStatement303); if (state.failed) return stmt;
                    if ( state.backtracking==0 ) {
                      stmt = StatementFactory.createImportTypeSystem(StatementFactory.createEmptyComponentDeclaration(im),im);
                    }
                    pushFollow(FOLLOW_dottedComponentDeclaration_in_importStatement315);
                    name=dottedComponentDeclaration();

                    state._fsp--;
                    if (state.failed) return stmt;
                    if ( state.backtracking==0 ) {
                      if(name != null) {stmt = StatementFactory.createImportTypeSystem(name,im);addImportTypeSystem(name);}
                    }
                    match(input,SEMI,FOLLOW_SEMI_in_importStatement323); if (state.failed) return stmt;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:293:4: im= ScriptString name= dottedComponentDeclaration SEMI
                    {
                    im=(Token)match(input,ScriptString,FOLLOW_ScriptString_in_importStatement333); if (state.failed) return stmt;
                    if ( state.backtracking==0 ) {
                      stmt = StatementFactory.createImportScript(StatementFactory.createEmptyComponentDeclaration(im),im);
                    }
                    pushFollow(FOLLOW_dottedComponentDeclaration_in_importStatement345);
                    name=dottedComponentDeclaration();

                    state._fsp--;
                    if (state.failed) return stmt;
                    if ( state.backtracking==0 ) {
                      if(name != null) {stmt = StatementFactory.createImportScript(name,im);addImportScript(name);}
                    }
                    match(input,SEMI,FOLLOW_SEMI_in_importStatement353); if (state.failed) return stmt;

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:298:4: im= EngineString name= dottedComponentDeclaration SEMI
                    {
                    im=(Token)match(input,EngineString,FOLLOW_EngineString_in_importStatement363); if (state.failed) return stmt;
                    if ( state.backtracking==0 ) {
                      stmt = StatementFactory.createImportEngine(StatementFactory.createEmptyComponentDeclaration(im),im);
                    }
                    pushFollow(FOLLOW_dottedComponentDeclaration_in_importStatement375);
                    name=dottedComponentDeclaration();

                    state._fsp--;
                    if (state.failed) return stmt;
                    if ( state.backtracking==0 ) {
                      if(name != null) {stmt = StatementFactory.createImportEngine(name,im);addImportEngine(name);}
                    }
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
        }
        return stmt;
    }
    // $ANTLR end "importStatement"


    // $ANTLR start "variableDeclaration"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:305:1: variableDeclaration returns [List<Statement> stmts = new ArrayList<Statement>()] : (type= IntString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= numberExpression )? SEMI | type= DoubleString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= numberExpression )? SEMI | type= StringString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= stringExpression )? SEMI | type= BooleanString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= booleanExpression )? SEMI | type= TypeString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= annotationType )? SEMI | type= WORDLIST id= Identifier ( ASSIGN_EQUAL list= wordListExpression )? SEMI | type= WORDTABLE id= Identifier ( ASSIGN_EQUAL table= wordTableExpression )? SEMI | type= BOOLEANLIST id= Identifier ( ASSIGN_EQUAL list= booleanListExpression )? SEMI | type= INTLIST id= Identifier ( ASSIGN_EQUAL list= numberListExpression )? SEMI | type= DOUBLELIST id= Identifier ( ASSIGN_EQUAL list= numberListExpression )? SEMI | type= STRINGLIST id= Identifier ( ASSIGN_EQUAL list= stringListExpression )? SEMI | type= TYPELIST id= Identifier ( ASSIGN_EQUAL list= typeListExpression )? SEMI | stmt= conditionDeclaration | stmt= actionDeclaration );
    public final List<Statement> variableDeclaration() throws RecognitionException {
        List<Statement> stmts =  new ArrayList<Statement>();

        Token type=null;
        Token id=null;
        Expression init = null;

        Expression list = null;

        Expression table = null;

        Statement stmt = null;



        	List decls = new ArrayList();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:309:2: (type= IntString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= numberExpression )? SEMI | type= DoubleString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= numberExpression )? SEMI | type= StringString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= stringExpression )? SEMI | type= BooleanString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= booleanExpression )? SEMI | type= TypeString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= annotationType )? SEMI | type= WORDLIST id= Identifier ( ASSIGN_EQUAL list= wordListExpression )? SEMI | type= WORDTABLE id= Identifier ( ASSIGN_EQUAL table= wordTableExpression )? SEMI | type= BOOLEANLIST id= Identifier ( ASSIGN_EQUAL list= booleanListExpression )? SEMI | type= INTLIST id= Identifier ( ASSIGN_EQUAL list= numberListExpression )? SEMI | type= DOUBLELIST id= Identifier ( ASSIGN_EQUAL list= numberListExpression )? SEMI | type= STRINGLIST id= Identifier ( ASSIGN_EQUAL list= stringListExpression )? SEMI | type= TYPELIST id= Identifier ( ASSIGN_EQUAL list= typeListExpression )? SEMI | stmt= conditionDeclaration | stmt= actionDeclaration )
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:310:2: type= IntString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= numberExpression )? SEMI
                    {
                    type=(Token)match(input,IntString,FOLLOW_IntString_in_variableDeclaration410); if (state.failed) return stmts;
                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration416); if (state.failed) return stmts;
                    if ( state.backtracking==0 ) {
                      addVariable(id.getText(), type.getText()); decls.add(StatementFactory.createIntVariable(id, type));
                    }
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:311:3: ( COMMA id= Identifier )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0==COMMA) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:311:4: COMMA id= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_variableDeclaration423); if (state.failed) return stmts;
                    	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration428); if (state.failed) return stmts;
                    	    if ( state.backtracking==0 ) {
                    	      addVariable(id.getText(), type.getText()); decls.add(StatementFactory.createIntVariable(id, type));
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);

                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:312:6: ( ASSIGN_EQUAL init= numberExpression )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==ASSIGN_EQUAL) ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:312:7: ASSIGN_EQUAL init= numberExpression
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:317:2: type= DoubleString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= numberExpression )? SEMI
                    {
                    type=(Token)match(input,DoubleString,FOLLOW_DoubleString_in_variableDeclaration463); if (state.failed) return stmts;
                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration469); if (state.failed) return stmts;
                    if ( state.backtracking==0 ) {
                      addVariable(id.getText(), type.getText());decls.add(StatementFactory.createDoubleVariable(id, type));
                    }
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:318:4: ( COMMA id= Identifier )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==COMMA) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:318:5: COMMA id= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_variableDeclaration477); if (state.failed) return stmts;
                    	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration483); if (state.failed) return stmts;
                    	    if ( state.backtracking==0 ) {
                    	      addVariable(id.getText(), type.getText());decls.add(StatementFactory.createDoubleVariable(id, type));
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);

                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:319:7: ( ASSIGN_EQUAL init= numberExpression )?
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0==ASSIGN_EQUAL) ) {
                        alt8=1;
                    }
                    switch (alt8) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:319:8: ASSIGN_EQUAL init= numberExpression
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:324:2: type= StringString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= stringExpression )? SEMI
                    {
                    type=(Token)match(input,StringString,FOLLOW_StringString_in_variableDeclaration519); if (state.failed) return stmts;
                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration525); if (state.failed) return stmts;
                    if ( state.backtracking==0 ) {
                      addVariable(id.getText(), type.getText());decls.add(StatementFactory.createStringVariable(id, type));
                    }
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:325:4: ( COMMA id= Identifier )*
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0==COMMA) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:325:5: COMMA id= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_variableDeclaration533); if (state.failed) return stmts;
                    	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration539); if (state.failed) return stmts;
                    	    if ( state.backtracking==0 ) {
                    	      addVariable(id.getText(), type.getText());decls.add(StatementFactory.createStringVariable(id, type));
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop9;
                        }
                    } while (true);

                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:326:7: ( ASSIGN_EQUAL init= stringExpression )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0==ASSIGN_EQUAL) ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:326:8: ASSIGN_EQUAL init= stringExpression
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:331:2: type= BooleanString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= booleanExpression )? SEMI
                    {
                    type=(Token)match(input,BooleanString,FOLLOW_BooleanString_in_variableDeclaration575); if (state.failed) return stmts;
                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration581); if (state.failed) return stmts;
                    if ( state.backtracking==0 ) {
                      addVariable(id.getText(), type.getText());decls.add(StatementFactory.createBooleanVariable(id, type));
                    }
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:332:4: ( COMMA id= Identifier )*
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( (LA11_0==COMMA) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:332:5: COMMA id= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_variableDeclaration589); if (state.failed) return stmts;
                    	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration595); if (state.failed) return stmts;
                    	    if ( state.backtracking==0 ) {
                    	      addVariable(id.getText(), type.getText());decls.add(StatementFactory.createBooleanVariable(id, type));
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop11;
                        }
                    } while (true);

                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:333:7: ( ASSIGN_EQUAL init= booleanExpression )?
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( (LA12_0==ASSIGN_EQUAL) ) {
                        alt12=1;
                    }
                    switch (alt12) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:333:8: ASSIGN_EQUAL init= booleanExpression
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:338:2: type= TypeString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL init= annotationType )? SEMI
                    {
                    type=(Token)match(input,TypeString,FOLLOW_TypeString_in_variableDeclaration631); if (state.failed) return stmts;
                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration637); if (state.failed) return stmts;
                    if ( state.backtracking==0 ) {
                      addVariable(id.getText(), type.getText());decls.add(StatementFactory.createTypeVariable(id,type));
                    }
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:339:4: ( COMMA id= Identifier )*
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( (LA13_0==COMMA) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:339:5: COMMA id= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_variableDeclaration645); if (state.failed) return stmts;
                    	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration651); if (state.failed) return stmts;
                    	    if ( state.backtracking==0 ) {
                    	      addVariable(id.getText(), type.getText());decls.add(StatementFactory.createTypeVariable(id,type));
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop13;
                        }
                    } while (true);

                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:340:7: ( ASSIGN_EQUAL init= annotationType )?
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0==ASSIGN_EQUAL) ) {
                        alt14=1;
                    }
                    switch (alt14) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:340:8: ASSIGN_EQUAL init= annotationType
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:345:9: type= WORDLIST id= Identifier ( ASSIGN_EQUAL list= wordListExpression )? SEMI
                    {
                    type=(Token)match(input,WORDLIST,FOLLOW_WORDLIST_in_variableDeclaration701); if (state.failed) return stmts;
                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration707); if (state.failed) return stmts;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:345:41: ( ASSIGN_EQUAL list= wordListExpression )?
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( (LA15_0==ASSIGN_EQUAL) ) {
                        alt15=1;
                    }
                    switch (alt15) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:345:42: ASSIGN_EQUAL list= wordListExpression
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:352:9: type= WORDTABLE id= Identifier ( ASSIGN_EQUAL table= wordTableExpression )? SEMI
                    {
                    type=(Token)match(input,WORDTABLE,FOLLOW_WORDTABLE_in_variableDeclaration754); if (state.failed) return stmts;
                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration760); if (state.failed) return stmts;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:352:42: ( ASSIGN_EQUAL table= wordTableExpression )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( (LA16_0==ASSIGN_EQUAL) ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:352:43: ASSIGN_EQUAL table= wordTableExpression
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:359:9: type= BOOLEANLIST id= Identifier ( ASSIGN_EQUAL list= booleanListExpression )? SEMI
                    {
                    type=(Token)match(input,BOOLEANLIST,FOLLOW_BOOLEANLIST_in_variableDeclaration808); if (state.failed) return stmts;
                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration814); if (state.failed) return stmts;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:359:44: ( ASSIGN_EQUAL list= booleanListExpression )?
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( (LA17_0==ASSIGN_EQUAL) ) {
                        alt17=1;
                    }
                    switch (alt17) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:359:45: ASSIGN_EQUAL list= booleanListExpression
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:366:9: type= INTLIST id= Identifier ( ASSIGN_EQUAL list= numberListExpression )? SEMI
                    {
                    type=(Token)match(input,INTLIST,FOLLOW_INTLIST_in_variableDeclaration862); if (state.failed) return stmts;
                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration868); if (state.failed) return stmts;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:366:40: ( ASSIGN_EQUAL list= numberListExpression )?
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( (LA18_0==ASSIGN_EQUAL) ) {
                        alt18=1;
                    }
                    switch (alt18) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:366:41: ASSIGN_EQUAL list= numberListExpression
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:373:9: type= DOUBLELIST id= Identifier ( ASSIGN_EQUAL list= numberListExpression )? SEMI
                    {
                    type=(Token)match(input,DOUBLELIST,FOLLOW_DOUBLELIST_in_variableDeclaration917); if (state.failed) return stmts;
                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration923); if (state.failed) return stmts;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:373:43: ( ASSIGN_EQUAL list= numberListExpression )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0==ASSIGN_EQUAL) ) {
                        alt19=1;
                    }
                    switch (alt19) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:373:44: ASSIGN_EQUAL list= numberListExpression
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:380:9: type= STRINGLIST id= Identifier ( ASSIGN_EQUAL list= stringListExpression )? SEMI
                    {
                    type=(Token)match(input,STRINGLIST,FOLLOW_STRINGLIST_in_variableDeclaration979); if (state.failed) return stmts;
                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration985); if (state.failed) return stmts;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:380:43: ( ASSIGN_EQUAL list= stringListExpression )?
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( (LA20_0==ASSIGN_EQUAL) ) {
                        alt20=1;
                    }
                    switch (alt20) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:380:44: ASSIGN_EQUAL list= stringListExpression
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:387:9: type= TYPELIST id= Identifier ( ASSIGN_EQUAL list= typeListExpression )? SEMI
                    {
                    type=(Token)match(input,TYPELIST,FOLLOW_TYPELIST_in_variableDeclaration1041); if (state.failed) return stmts;
                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration1047); if (state.failed) return stmts;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:387:41: ( ASSIGN_EQUAL list= typeListExpression )?
                    int alt21=2;
                    int LA21_0 = input.LA(1);

                    if ( (LA21_0==ASSIGN_EQUAL) ) {
                        alt21=1;
                    }
                    switch (alt21) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:387:42: ASSIGN_EQUAL list= typeListExpression
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:394:2: stmt= conditionDeclaration
                    {
                    pushFollow(FOLLOW_conditionDeclaration_in_variableDeclaration1088);
                    stmt=conditionDeclaration();

                    state._fsp--;
                    if (state.failed) return stmts;
                    if ( state.backtracking==0 ) {
                      stmts.add(stmt);
                    }

                    }
                    break;
                case 14 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:396:2: stmt= actionDeclaration
                    {
                    pushFollow(FOLLOW_actionDeclaration_in_variableDeclaration1100);
                    stmt=actionDeclaration();

                    state._fsp--;
                    if (state.failed) return stmts;
                    if ( state.backtracking==0 ) {
                      stmts.add(stmt);
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
        }
        return stmts;
    }
    // $ANTLR end "variableDeclaration"


    // $ANTLR start "conditionDeclaration"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:399:1: conditionDeclaration returns [Statement stmt = null] : declareToken= CONDITION id= Identifier ASSIGN_EQUAL LPAREN cons= conditions RPAREN SEMI ;
    public final Statement conditionDeclaration() throws RecognitionException {
        Statement stmt =  null;

        Token declareToken=null;
        Token id=null;
        List<TextMarkerCondition> cons = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:400:5: (declareToken= CONDITION id= Identifier ASSIGN_EQUAL LPAREN cons= conditions RPAREN SEMI )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:401:5: declareToken= CONDITION id= Identifier ASSIGN_EQUAL LPAREN cons= conditions RPAREN SEMI
            {
            declareToken=(Token)match(input,CONDITION,FOLLOW_CONDITION_in_conditionDeclaration1128); if (state.failed) return stmt;
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_conditionDeclaration1134); if (state.failed) return stmt;
            if ( state.backtracking==0 ) {
              addVariable(id.getText(), declareToken.getText());
            }
            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_conditionDeclaration1142); if (state.failed) return stmt;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionDeclaration1149); if (state.failed) return stmt;
            pushFollow(FOLLOW_conditions_in_conditionDeclaration1155);
            cons=conditions();

            state._fsp--;
            if (state.failed) return stmt;
            match(input,RPAREN,FOLLOW_RPAREN_in_conditionDeclaration1157); if (state.failed) return stmt;
            match(input,SEMI,FOLLOW_SEMI_in_conditionDeclaration1159); if (state.failed) return stmt;
            if ( state.backtracking==0 ) {
              stmt = StatementFactory.createComposedVariableConditionDeclaration(id, cons);
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
        }
        return stmt;
    }
    // $ANTLR end "conditionDeclaration"


    // $ANTLR start "actionDeclaration"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:408:1: actionDeclaration returns [Statement stmt = null] : declareToken= ACTION id= Identifier ASSIGN_EQUAL LPAREN a= actions RPAREN SEMI ;
    public final Statement actionDeclaration() throws RecognitionException {
        Statement stmt =  null;

        Token declareToken=null;
        Token id=null;
        List<TextMarkerAction> a = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:409:5: (declareToken= ACTION id= Identifier ASSIGN_EQUAL LPAREN a= actions RPAREN SEMI )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:410:5: declareToken= ACTION id= Identifier ASSIGN_EQUAL LPAREN a= actions RPAREN SEMI
            {
            declareToken=(Token)match(input,ACTION,FOLLOW_ACTION_in_actionDeclaration1195); if (state.failed) return stmt;
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_actionDeclaration1201); if (state.failed) return stmt;
            if ( state.backtracking==0 ) {
              addVariable(id.getText(), declareToken.getText());
            }
            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionDeclaration1209); if (state.failed) return stmt;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionDeclaration1215); if (state.failed) return stmt;
            pushFollow(FOLLOW_actions_in_actionDeclaration1221);
            a=actions();

            state._fsp--;
            if (state.failed) return stmt;
            match(input,RPAREN,FOLLOW_RPAREN_in_actionDeclaration1223); if (state.failed) return stmt;
            match(input,SEMI,FOLLOW_SEMI_in_actionDeclaration1225); if (state.failed) return stmt;
            if ( state.backtracking==0 ) {
              stmt = StatementFactory.createComposedVariableActionDeclaration(id, a);
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
        }
        return stmt;
    }
    // $ANTLR end "actionDeclaration"


    // $ANTLR start "declaration"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:416:1: declaration returns [List<Statement> stmts = new ArrayList<Statement>()] : (declareToken= DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* end= SEMI | declareToken= DECLARE type= annotationType id= Identifier ( LPAREN (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI ) ;
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
        Expression lazyParent = null;

        Expression type = null;

        Expression obj1 = null;



        	Statement stmt = null;
        	List<Object> featureTypes = new ArrayList<Object>();
        	List<Token> featureNames = new ArrayList<Token>();
        	List<Declaration> declarations = new ArrayList<Declaration>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:423:2: ( (declareToken= DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* end= SEMI | declareToken= DECLARE type= annotationType id= Identifier ( LPAREN (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI ) )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:425:2: (declareToken= DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* end= SEMI | declareToken= DECLARE type= annotationType id= Identifier ( LPAREN (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI )
            {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:425:2: (declareToken= DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* end= SEMI | declareToken= DECLARE type= annotationType id= Identifier ( LPAREN (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI )
            int alt28=2;
            alt28 = dfa28.predict(input);
            switch (alt28) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:425:3: declareToken= DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* end= SEMI
                    {
                    declareToken=(Token)match(input,DECLARE,FOLLOW_DECLARE_in_declaration1260); if (state.failed) return stmts;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:425:35: (lazyParent= annotationType )?
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
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:425:35: lazyParent= annotationType
                            {
                            pushFollow(FOLLOW_annotationType_in_declaration1266);
                            lazyParent=annotationType();

                            state._fsp--;
                            if (state.failed) return stmts;

                            }
                            break;

                    }

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1276); if (state.failed) return stmts;
                    if ( state.backtracking==0 ) {
                      addVariable(id.getText(), declareToken.getText());
                    }
                    if ( state.backtracking==0 ) {
                      addType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), lazyParent == null ? null : lazyParent.toString());
                      			declarations.add(StatementFactory.createAnnotationType(id,declareToken));
                    }
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:429:3: ( COMMA id= Identifier )*
                    loop24:
                    do {
                        int alt24=2;
                        int LA24_0 = input.LA(1);

                        if ( (LA24_0==COMMA) ) {
                            alt24=1;
                        }


                        switch (alt24) {
                    	case 1 :
                    	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:429:4: COMMA id= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_declaration1288); if (state.failed) return stmts;
                    	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1298); if (state.failed) return stmts;
                    	    if ( state.backtracking==0 ) {
                    	      addVariable(id.getText(), declareToken.getText());
                    	    }
                    	    if ( state.backtracking==0 ) {
                    	      addType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(),  lazyParent == null ? null : lazyParent.toString()); 
                    	      			declarations.add(StatementFactory.createAnnotationType(id,declareToken));
                    	    }

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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:439:2: declareToken= DECLARE type= annotationType id= Identifier ( LPAREN (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI
                    {
                    declareToken=(Token)match(input,DECLARE,FOLLOW_DECLARE_in_declaration1330); if (state.failed) return stmts;
                    pushFollow(FOLLOW_annotationType_in_declaration1334);
                    type=annotationType();

                    state._fsp--;
                    if (state.failed) return stmts;
                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1341); if (state.failed) return stmts;
                    if ( state.backtracking==0 ) {
                      addVariable(id.getText(), declareToken.getText());
                    }
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:441:3: ( LPAREN (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString ) fname= Identifier )* RPAREN )
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:441:4: LPAREN (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString ) fname= Identifier )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_declaration1348); if (state.failed) return stmts;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:442:4: (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString )
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
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:443:4: obj1= annotationType
                            {
                            pushFollow(FOLLOW_annotationType_in_declaration1363);
                            obj1=annotationType();

                            state._fsp--;
                            if (state.failed) return stmts;
                            if ( state.backtracking==0 ) {
                              featureTypes.add(obj1);
                            }

                            }
                            break;
                        case 2 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:444:6: obj2= StringString
                            {
                            obj2=(Token)match(input,StringString,FOLLOW_StringString_in_declaration1376); if (state.failed) return stmts;
                            if ( state.backtracking==0 ) {
                              featureTypes.add(obj2);
                            }

                            }
                            break;
                        case 3 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:445:6: obj3= DoubleString
                            {
                            obj3=(Token)match(input,DoubleString,FOLLOW_DoubleString_in_declaration1389); if (state.failed) return stmts;
                            if ( state.backtracking==0 ) {
                              featureTypes.add(obj3);
                            }

                            }
                            break;
                        case 4 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:446:6: obj4= IntString
                            {
                            obj4=(Token)match(input,IntString,FOLLOW_IntString_in_declaration1402); if (state.failed) return stmts;
                            if ( state.backtracking==0 ) {
                              featureTypes.add(obj4);
                            }

                            }
                            break;
                        case 5 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:447:6: obj5= BooleanString
                            {
                            obj5=(Token)match(input,BooleanString,FOLLOW_BooleanString_in_declaration1414); if (state.failed) return stmts;
                            if ( state.backtracking==0 ) {
                              featureTypes.add(obj5);
                            }

                            }
                            break;

                    }

                    fname=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1434); if (state.failed) return stmts;
                    if ( state.backtracking==0 ) {
                      featureNames.add(fname);
                    }
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:451:4: ( COMMA (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString ) fname= Identifier )*
                    loop27:
                    do {
                        int alt27=2;
                        int LA27_0 = input.LA(1);

                        if ( (LA27_0==COMMA) ) {
                            alt27=1;
                        }


                        switch (alt27) {
                    	case 1 :
                    	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:452:4: COMMA (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString ) fname= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_declaration1446); if (state.failed) return stmts;
                    	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:453:4: (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString )
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
                    	            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:454:4: obj1= annotationType
                    	            {
                    	            pushFollow(FOLLOW_annotationType_in_declaration1461);
                    	            obj1=annotationType();

                    	            state._fsp--;
                    	            if (state.failed) return stmts;
                    	            if ( state.backtracking==0 ) {
                    	              featureTypes.add(obj1);
                    	            }

                    	            }
                    	            break;
                    	        case 2 :
                    	            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:455:6: obj2= StringString
                    	            {
                    	            obj2=(Token)match(input,StringString,FOLLOW_StringString_in_declaration1474); if (state.failed) return stmts;
                    	            if ( state.backtracking==0 ) {
                    	              featureTypes.add(obj2);
                    	            }

                    	            }
                    	            break;
                    	        case 3 :
                    	            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:456:6: obj3= DoubleString
                    	            {
                    	            obj3=(Token)match(input,DoubleString,FOLLOW_DoubleString_in_declaration1487); if (state.failed) return stmts;
                    	            if ( state.backtracking==0 ) {
                    	              featureTypes.add(obj3);
                    	            }

                    	            }
                    	            break;
                    	        case 4 :
                    	            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:457:6: obj4= IntString
                    	            {
                    	            obj4=(Token)match(input,IntString,FOLLOW_IntString_in_declaration1499); if (state.failed) return stmts;
                    	            if ( state.backtracking==0 ) {
                    	              featureTypes.add(obj4);
                    	            }

                    	            }
                    	            break;
                    	        case 5 :
                    	            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:458:6: obj5= BooleanString
                    	            {
                    	            obj5=(Token)match(input,BooleanString,FOLLOW_BooleanString_in_declaration1511); if (state.failed) return stmts;
                    	            if ( state.backtracking==0 ) {
                    	              featureTypes.add(obj5);
                    	            }

                    	            }
                    	            break;

                    	    }

                    	    fname=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1530); if (state.failed) return stmts;
                    	    if ( state.backtracking==0 ) {
                    	      featureNames.add(fname);
                    	    }

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
        }
        return stmts;
    }
    // $ANTLR end "declaration"

    protected static class blockDeclaration_scope {
        TextMarkerBlock env;
    }
    protected Stack blockDeclaration_stack = new Stack();


    // $ANTLR start "blockDeclaration"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:483:1: blockDeclaration returns [TextMarkerBlock block = null] options {backtrack=true; } : (declareToken= BlockString | declareToken= AutomataBlockString ) LPAREN id= Identifier RPAREN re1= ruleElementWithCA LCURLY body= statements rc= RCURLY ;
    public final TextMarkerBlock blockDeclaration() throws RecognitionException {
        blockDeclaration_stack.push(new blockDeclaration_scope());
        TextMarkerBlock block =  null;

        Token declareToken=null;
        Token id=null;
        Token rc=null;
        TextMarkerRuleElement re1 = null;

        List<Statement> body = null;



        TextMarkerRuleElement re = null;
        level++;

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:497:2: ( (declareToken= BlockString | declareToken= AutomataBlockString ) LPAREN id= Identifier RPAREN re1= ruleElementWithCA LCURLY body= statements rc= RCURLY )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:499:2: (declareToken= BlockString | declareToken= AutomataBlockString ) LPAREN id= Identifier RPAREN re1= ruleElementWithCA LCURLY body= statements rc= RCURLY
            {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:499:2: (declareToken= BlockString | declareToken= AutomataBlockString )
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:499:3: declareToken= BlockString
                    {
                    declareToken=(Token)match(input,BlockString,FOLLOW_BlockString_in_blockDeclaration1602); if (state.failed) return block;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:499:32: declareToken= AutomataBlockString
                    {
                    declareToken=(Token)match(input,AutomataBlockString,FOLLOW_AutomataBlockString_in_blockDeclaration1610); if (state.failed) return block;

                    }
                    break;

            }

            match(input,LPAREN,FOLLOW_LPAREN_in_blockDeclaration1614); if (state.failed) return block;
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_blockDeclaration1621); if (state.failed) return block;
            if ( state.backtracking==0 ) {
              addVariable(id.getText(), declareToken.getText());
            }
            if ( state.backtracking==0 ) {

              		block = ScriptFactory.createScriptBlock(id, declareToken, ((blockDeclaration_scope)blockDeclaration_stack.elementAt(level - 1)).env);
              		((blockDeclaration_scope)blockDeclaration_stack.peek()).env = block;
              	
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_blockDeclaration1629); if (state.failed) return block;
            pushFollow(FOLLOW_ruleElementWithCA_in_blockDeclaration1636);
            re1=ruleElementWithCA();

            state._fsp--;
            if (state.failed) return block;
            if ( state.backtracking==0 ) {
              re = re1;
            }
            if ( state.backtracking==0 ) {
              ScriptFactory.finalizeScriptBlock(block, rc, re, body);
            }
            match(input,LCURLY,FOLLOW_LCURLY_in_blockDeclaration1644); if (state.failed) return block;
            pushFollow(FOLLOW_statements_in_blockDeclaration1650);
            body=statements();

            state._fsp--;
            if (state.failed) return block;
            rc=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_blockDeclaration1656); if (state.failed) return block;
            if ( state.backtracking==0 ) {
              ScriptFactory.finalizeScriptBlock(block, rc, re, body);
            }

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
            blockDeclaration_stack.pop();
        }
        return block;
    }
    // $ANTLR end "blockDeclaration"


    // $ANTLR start "ruleElementWithCA"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:514:1: ruleElementWithCA returns [TextMarkerRuleElement re = null] : idRef= typeExpression (quantifier= quantifierPart )? LCURLY (c= conditions )? ( THEN a= actions )? end= RCURLY ;
    public final TextMarkerRuleElement ruleElementWithCA() throws RecognitionException {
        TextMarkerRuleElement re =  null;

        Token end=null;
        Expression idRef = null;

        List<Expression> quantifier = null;

        List<TextMarkerCondition> c = null;

        List<TextMarkerAction> a = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:515:5: (idRef= typeExpression (quantifier= quantifierPart )? LCURLY (c= conditions )? ( THEN a= actions )? end= RCURLY )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:516:5: idRef= typeExpression (quantifier= quantifierPart )? LCURLY (c= conditions )? ( THEN a= actions )? end= RCURLY
            {
            pushFollow(FOLLOW_typeExpression_in_ruleElementWithCA1686);
            idRef=typeExpression();

            state._fsp--;
            if (state.failed) return re;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:516:37: (quantifier= quantifierPart )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==LBRACK||LA30_0==PLUS||LA30_0==STAR||LA30_0==QUESTION) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:516:37: quantifier= quantifierPart
                    {
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementWithCA1692);
                    quantifier=quantifierPart();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              re = ScriptFactory.createRuleElement(idRef,quantifier,c,a, end);
            }
            match(input,LCURLY,FOLLOW_LCURLY_in_ruleElementWithCA1705); if (state.failed) return re;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:517:18: (c= conditions )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==CONTAINS||(LA31_0>=AND && LA31_0<=PARSE)||(LA31_0>=BEFORE && LA31_0<=NOT)||LA31_0==SIZE||LA31_0==Identifier||LA31_0==MINUS) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:517:18: c= conditions
                    {
                    pushFollow(FOLLOW_conditions_in_ruleElementWithCA1711);
                    c=conditions();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }

            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:517:32: ( THEN a= actions )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==THEN) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:517:33: THEN a= actions
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
            if ( state.backtracking==0 ) {
              re = ScriptFactory.createRuleElement(idRef,quantifier,c,a, end);
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
        }
        return re;
    }
    // $ANTLR end "ruleElementWithCA"


    // $ANTLR start "ruleElementWithoutCA"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:522:1: ruleElementWithoutCA returns [TextMarkerRuleElement re = null] : idRef= typeExpression (quantifier= quantifierPart )? ;
    public final TextMarkerRuleElement ruleElementWithoutCA() throws RecognitionException {
        TextMarkerRuleElement re =  null;

        Expression idRef = null;

        List<Expression> quantifier = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:523:5: (idRef= typeExpression (quantifier= quantifierPart )? )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:524:5: idRef= typeExpression (quantifier= quantifierPart )?
            {
            pushFollow(FOLLOW_typeExpression_in_ruleElementWithoutCA1769);
            idRef=typeExpression();

            state._fsp--;
            if (state.failed) return re;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:524:37: (quantifier= quantifierPart )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==LBRACK||LA33_0==PLUS||LA33_0==STAR||LA33_0==QUESTION) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:524:37: quantifier= quantifierPart
                    {
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementWithoutCA1775);
                    quantifier=quantifierPart();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              re = ScriptFactory.createRuleElement(idRef,quantifier,null,null, null);
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
        }
        return re;
    }
    // $ANTLR end "ruleElementWithoutCA"


    // $ANTLR start "simpleStatement"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:529:1: simpleStatement returns [TextMarkerRule stmt = null] : elements= ruleElements SEMI ;
    public final TextMarkerRule simpleStatement() throws RecognitionException {
        TextMarkerRule stmt =  null;

        List<Expression> elements = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:530:2: (elements= ruleElements SEMI )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:531:2: elements= ruleElements SEMI
            {
            pushFollow(FOLLOW_ruleElements_in_simpleStatement1817);
            elements=ruleElements();

            state._fsp--;
            if (state.failed) return stmt;
            if ( state.backtracking==0 ) {
              stmt = ScriptFactory.createRule(elements);
            }
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
        }
        return stmt;
    }
    // $ANTLR end "simpleStatement"


    // $ANTLR start "ruleElements"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:536:1: ruleElements returns [List<Expression> elements = new ArrayList<Expression>()] : re= ruleElement (re= ruleElement )* ;
    public final List<Expression> ruleElements() throws RecognitionException {
        List<Expression> elements =  new ArrayList<Expression>();

        Expression re = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:537:2: (re= ruleElement (re= ruleElement )* )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:538:2: re= ruleElement (re= ruleElement )*
            {
            pushFollow(FOLLOW_ruleElement_in_ruleElements1847);
            re=ruleElement();

            state._fsp--;
            if (state.failed) return elements;
            if ( state.backtracking==0 ) {
              if(re!=null) elements.add(re);
            }
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:538:52: (re= ruleElement )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==BasicAnnotationType||LA34_0==StringLiteral||(LA34_0>=Identifier && LA34_0<=LPAREN)) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:538:53: re= ruleElement
            	    {
            	    pushFollow(FOLLOW_ruleElement_in_ruleElements1856);
            	    re=ruleElement();

            	    state._fsp--;
            	    if (state.failed) return elements;
            	    if ( state.backtracking==0 ) {
            	      if(re!=null) elements.add(re);
            	    }

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
        }
        return elements;
    }
    // $ANTLR end "ruleElements"


    // $ANTLR start "blockRuleElement"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:541:1: blockRuleElement returns [TextMarkerRuleElement rElement = null] : re= ruleElementType ;
    public final TextMarkerRuleElement blockRuleElement() throws RecognitionException {
        TextMarkerRuleElement rElement =  null;

        TextMarkerRuleElement re = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:542:2: (re= ruleElementType )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:543:2: re= ruleElementType
            {
            pushFollow(FOLLOW_ruleElementType_in_blockRuleElement1883);
            re=ruleElementType();

            state._fsp--;
            if (state.failed) return rElement;
            if ( state.backtracking==0 ) {
              rElement = re;
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
        }
        return rElement;
    }
    // $ANTLR end "blockRuleElement"


    // $ANTLR start "ruleElement"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:546:1: ruleElement returns [Expression re = null] : (re1= ruleElementType | re2= ruleElementLiteral | re3= ruleElementComposed );
    public final Expression ruleElement() throws RecognitionException {
        Expression re =  null;

        TextMarkerRuleElement re1 = null;

        TextMarkerRuleElement re2 = null;

        ComposedRuleElement re3 = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:547:2: (re1= ruleElementType | re2= ruleElementLiteral | re3= ruleElementComposed )
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:548:2: re1= ruleElementType
                    {
                    pushFollow(FOLLOW_ruleElementType_in_ruleElement1907);
                    re1=ruleElementType();

                    state._fsp--;
                    if (state.failed) return re;
                    if ( state.backtracking==0 ) {
                      re = re1;
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:549:4: re2= ruleElementLiteral
                    {
                    pushFollow(FOLLOW_ruleElementLiteral_in_ruleElement1918);
                    re2=ruleElementLiteral();

                    state._fsp--;
                    if (state.failed) return re;
                    if ( state.backtracking==0 ) {
                      re = re2;
                    }

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:550:4: re3= ruleElementComposed
                    {
                    pushFollow(FOLLOW_ruleElementComposed_in_ruleElement1929);
                    re3=ruleElementComposed();

                    state._fsp--;
                    if (state.failed) return re;
                    if ( state.backtracking==0 ) {
                      re = re3;
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
        }
        return re;
    }
    // $ANTLR end "ruleElement"


    // $ANTLR start "ruleElementComposed"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:553:1: ruleElementComposed returns [ComposedRuleElement re = null] : LPAREN res= ruleElements RPAREN q= quantifierPart ;
    public final ComposedRuleElement ruleElementComposed() throws RecognitionException {
        ComposedRuleElement re =  null;

        List<Expression> res = null;

        List<Expression> q = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:554:2: ( LPAREN res= ruleElements RPAREN q= quantifierPart )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:555:2: LPAREN res= ruleElements RPAREN q= quantifierPart
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_ruleElementComposed1949); if (state.failed) return re;
            pushFollow(FOLLOW_ruleElements_in_ruleElementComposed1955);
            res=ruleElements();

            state._fsp--;
            if (state.failed) return re;
            match(input,RPAREN,FOLLOW_RPAREN_in_ruleElementComposed1957); if (state.failed) return re;
            pushFollow(FOLLOW_quantifierPart_in_ruleElementComposed1963);
            q=quantifierPart();

            state._fsp--;
            if (state.failed) return re;
            if ( state.backtracking==0 ) {
              re = ScriptFactory.createComposedRuleElement(res, q, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return re;
    }
    // $ANTLR end "ruleElementComposed"


    // $ANTLR start "ruleElementType"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:559:1: ruleElementType returns [TextMarkerRuleElement re = null] : ( typeExpression )=>idRef= typeExpression (quantifier= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? end= RCURLY )? ;
    public final TextMarkerRuleElement ruleElementType() throws RecognitionException {
        TextMarkerRuleElement re =  null;

        Token end=null;
        Expression idRef = null;

        List<Expression> quantifier = null;

        List<TextMarkerCondition> c = null;

        List<TextMarkerAction> a = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:560:5: ( ( typeExpression )=>idRef= typeExpression (quantifier= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? end= RCURLY )? )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:561:5: ( typeExpression )=>idRef= typeExpression (quantifier= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? end= RCURLY )?
            {
            pushFollow(FOLLOW_typeExpression_in_ruleElementType1995);
            idRef=typeExpression();

            state._fsp--;
            if (state.failed) return re;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:561:55: (quantifier= quantifierPart )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==LBRACK||LA36_0==PLUS||LA36_0==STAR||LA36_0==QUESTION) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:561:55: quantifier= quantifierPart
                    {
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementType2001);
                    quantifier=quantifierPart();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }

            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:562:9: ( LCURLY (c= conditions )? ( THEN a= actions )? end= RCURLY )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==LCURLY) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:562:10: LCURLY (c= conditions )? ( THEN a= actions )? end= RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_ruleElementType2014); if (state.failed) return re;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:562:19: (c= conditions )?
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==CONTAINS||(LA37_0>=AND && LA37_0<=PARSE)||(LA37_0>=BEFORE && LA37_0<=NOT)||LA37_0==SIZE||LA37_0==Identifier||LA37_0==MINUS) ) {
                        alt37=1;
                    }
                    switch (alt37) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:562:19: c= conditions
                            {
                            pushFollow(FOLLOW_conditions_in_ruleElementType2020);
                            c=conditions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }

                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:562:33: ( THEN a= actions )?
                    int alt38=2;
                    int LA38_0 = input.LA(1);

                    if ( (LA38_0==THEN) ) {
                        alt38=1;
                    }
                    switch (alt38) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:562:34: THEN a= actions
                            {
                            match(input,THEN,FOLLOW_THEN_in_ruleElementType2024); if (state.failed) return re;
                            pushFollow(FOLLOW_actions_in_ruleElementType2030);
                            a=actions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }

                    end=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_ruleElementType2038); if (state.failed) return re;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                      // TODO handle quantifierPart.
                      re = ScriptFactory.createRuleElement(idRef,quantifier,c,a,end);
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
        }
        return re;
    }
    // $ANTLR end "ruleElementType"


    // $ANTLR start "ruleElementLiteral"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:569:1: ruleElementLiteral returns [TextMarkerRuleElement re = null] : ( simpleStringExpression )=>idRef= simpleStringExpression (quantifier= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? end= RCURLY )? ;
    public final TextMarkerRuleElement ruleElementLiteral() throws RecognitionException {
        TextMarkerRuleElement re =  null;

        Token end=null;
        Expression idRef = null;

        List<Expression> quantifier = null;

        List<TextMarkerCondition> c = null;

        List<TextMarkerAction> a = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:570:5: ( ( simpleStringExpression )=>idRef= simpleStringExpression (quantifier= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? end= RCURLY )? )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:571:5: ( simpleStringExpression )=>idRef= simpleStringExpression (quantifier= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? end= RCURLY )?
            {
            pushFollow(FOLLOW_simpleStringExpression_in_ruleElementLiteral2091);
            idRef=simpleStringExpression();

            state._fsp--;
            if (state.failed) return re;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:571:71: (quantifier= quantifierPart )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==LBRACK||LA40_0==PLUS||LA40_0==STAR||LA40_0==QUESTION) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:571:71: quantifier= quantifierPart
                    {
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementLiteral2097);
                    quantifier=quantifierPart();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }

            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:572:9: ( LCURLY (c= conditions )? ( THEN a= actions )? end= RCURLY )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==LCURLY) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:572:10: LCURLY (c= conditions )? ( THEN a= actions )? end= RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_ruleElementLiteral2110); if (state.failed) return re;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:572:19: (c= conditions )?
                    int alt41=2;
                    int LA41_0 = input.LA(1);

                    if ( (LA41_0==CONTAINS||(LA41_0>=AND && LA41_0<=PARSE)||(LA41_0>=BEFORE && LA41_0<=NOT)||LA41_0==SIZE||LA41_0==Identifier||LA41_0==MINUS) ) {
                        alt41=1;
                    }
                    switch (alt41) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:572:19: c= conditions
                            {
                            pushFollow(FOLLOW_conditions_in_ruleElementLiteral2116);
                            c=conditions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }

                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:572:33: ( THEN a= actions )?
                    int alt42=2;
                    int LA42_0 = input.LA(1);

                    if ( (LA42_0==THEN) ) {
                        alt42=1;
                    }
                    switch (alt42) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:572:34: THEN a= actions
                            {
                            match(input,THEN,FOLLOW_THEN_in_ruleElementLiteral2120); if (state.failed) return re;
                            pushFollow(FOLLOW_actions_in_ruleElementLiteral2126);
                            a=actions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }

                    end=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_ruleElementLiteral2134); if (state.failed) return re;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                      // TODO handle quantifierPart.
                      re = ScriptFactory.createRuleElement(idRef,quantifier,c,a,end);
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
        }
        return re;
    }
    // $ANTLR end "ruleElementLiteral"


    // $ANTLR start "conditions"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:579:1: conditions returns [List<TextMarkerCondition> conds = new ArrayList<TextMarkerCondition>()] : c= condition ( COMMA c= condition )* ;
    public final List<TextMarkerCondition> conditions() throws RecognitionException {
        List<TextMarkerCondition> conds =  new ArrayList<TextMarkerCondition>();

        TextMarkerCondition c = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:580:5: (c= condition ( COMMA c= condition )* )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:581:5: c= condition ( COMMA c= condition )*
            {
            pushFollow(FOLLOW_condition_in_conditions2188);
            c=condition();

            state._fsp--;
            if (state.failed) return conds;
            if ( state.backtracking==0 ) {
              conds.add(c);
            }
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:581:35: ( COMMA c= condition )*
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( (LA44_0==COMMA) ) {
                    alt44=1;
                }


                switch (alt44) {
            	case 1 :
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:581:36: COMMA c= condition
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_conditions2193); if (state.failed) return conds;
            	    pushFollow(FOLLOW_condition_in_conditions2199);
            	    c=condition();

            	    state._fsp--;
            	    if (state.failed) return conds;
            	    if ( state.backtracking==0 ) {
            	      conds.add(c);
            	    }

            	    }
            	    break;

            	default :
            	    break loop44;
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
        }
        return conds;
    }
    // $ANTLR end "conditions"


    // $ANTLR start "actions"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:585:1: actions returns [List<TextMarkerAction> actions = new ArrayList<TextMarkerAction>()] : a= action ( COMMA a= action )* ;
    public final List<TextMarkerAction> actions() throws RecognitionException {
        List<TextMarkerAction> actions =  new ArrayList<TextMarkerAction>();

        TextMarkerAction a = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:586:5: (a= action ( COMMA a= action )* )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:587:5: a= action ( COMMA a= action )*
            {
            pushFollow(FOLLOW_action_in_actions2236);
            a=action();

            state._fsp--;
            if (state.failed) return actions;
            if ( state.backtracking==0 ) {
              actions.add(a);
            }
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:587:34: ( COMMA a= action )*
            loop45:
            do {
                int alt45=2;
                int LA45_0 = input.LA(1);

                if ( (LA45_0==COMMA) ) {
                    alt45=1;
                }


                switch (alt45) {
            	case 1 :
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:587:35: COMMA a= action
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actions2241); if (state.failed) return actions;
            	    pushFollow(FOLLOW_action_in_actions2247);
            	    a=action();

            	    state._fsp--;
            	    if (state.failed) return actions;
            	    if ( state.backtracking==0 ) {
            	      actions.add(a);
            	    }

            	    }
            	    break;

            	default :
            	    break loop45;
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
        }
        return actions;
    }
    // $ANTLR end "actions"


    // $ANTLR start "listExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:591:1: listExpression returns [Expression expr = null] : ( ( booleanListExpression )=>e= booleanListExpression | ( intListExpression )=>e= intListExpression | ( doubleListExpression )=>e= doubleListExpression | ( stringListExpression )=>e= stringListExpression | ( typeListExpression )=>e= typeListExpression );
    public final Expression listExpression() throws RecognitionException {
        Expression expr =  null;

        Expression e = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:592:2: ( ( booleanListExpression )=>e= booleanListExpression | ( intListExpression )=>e= intListExpression | ( doubleListExpression )=>e= doubleListExpression | ( stringListExpression )=>e= stringListExpression | ( typeListExpression )=>e= typeListExpression )
            int alt46=5;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==LCURLY) ) {
                int LA46_1 = input.LA(2);

                if ( (synpred3_TextMarkerParser()) ) {
                    alt46=1;
                }
                else if ( (synpred4_TextMarkerParser()) ) {
                    alt46=2;
                }
                else if ( (synpred5_TextMarkerParser()) ) {
                    alt46=3;
                }
                else if ( (synpred6_TextMarkerParser()) ) {
                    alt46=4;
                }
                else if ( (synpred7_TextMarkerParser()) ) {
                    alt46=5;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 46, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA46_0==Identifier) ) {
                int LA46_2 = input.LA(2);

                if ( ((synpred3_TextMarkerParser()&&(isVariableOfType(input.LT(1).getText(), "BOOLEANLIST")))) ) {
                    alt46=1;
                }
                else if ( ((synpred4_TextMarkerParser()&&(isVariableOfType(input.LT(1).getText(), "INTLIST")))) ) {
                    alt46=2;
                }
                else if ( ((synpred5_TextMarkerParser()&&(isVariableOfType(input.LT(1).getText(), "DOUBLELIST")))) ) {
                    alt46=3;
                }
                else if ( ((synpred6_TextMarkerParser()&&(isVariableOfType(input.LT(1).getText(), "STRINGLIST")))) ) {
                    alt46=4;
                }
                else if ( ((synpred7_TextMarkerParser()&&(isVariableOfType(input.LT(1).getText(), "TYPELIST")))) ) {
                    alt46=5;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 46, 2, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 46, 0, input);

                throw nvae;
            }
            switch (alt46) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:593:2: ( booleanListExpression )=>e= booleanListExpression
                    {
                    pushFollow(FOLLOW_booleanListExpression_in_listExpression2285);
                    e=booleanListExpression();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = e;
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:594:4: ( intListExpression )=>e= intListExpression
                    {
                    pushFollow(FOLLOW_intListExpression_in_listExpression2301);
                    e=intListExpression();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = e;
                    }

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:595:4: ( doubleListExpression )=>e= doubleListExpression
                    {
                    pushFollow(FOLLOW_doubleListExpression_in_listExpression2317);
                    e=doubleListExpression();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = e;
                    }

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:596:4: ( stringListExpression )=>e= stringListExpression
                    {
                    pushFollow(FOLLOW_stringListExpression_in_listExpression2333);
                    e=stringListExpression();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = e;
                    }

                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:597:4: ( typeListExpression )=>e= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_listExpression2349);
                    e=typeListExpression();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = e;
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
        }
        return expr;
    }
    // $ANTLR end "listExpression"


    // $ANTLR start "booleanListExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:600:1: booleanListExpression returns [Expression expr = null] : e= simpleBooleanListExpression ;
    public final Expression booleanListExpression() throws RecognitionException {
        Expression expr =  null;

        Expression e = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:602:2: (e= simpleBooleanListExpression )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:603:2: e= simpleBooleanListExpression
            {
            pushFollow(FOLLOW_simpleBooleanListExpression_in_booleanListExpression2373);
            e=simpleBooleanListExpression();

            state._fsp--;
            if (state.failed) return expr;
            if ( state.backtracking==0 ) {
              expr = e;
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
        }
        return expr;
    }
    // $ANTLR end "booleanListExpression"


    // $ANTLR start "simpleBooleanListExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:606:1: simpleBooleanListExpression returns [Expression expr = null] : ( LCURLY (e= simpleBooleanExpression ( COMMA e= simpleBooleanExpression )* )? RCURLY | {...}?var= Identifier );
    public final Expression simpleBooleanListExpression() throws RecognitionException {
        Expression expr =  null;

        Token var=null;
        Expression e = null;



        	List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:609:3: ( LCURLY (e= simpleBooleanExpression ( COMMA e= simpleBooleanExpression )* )? RCURLY | {...}?var= Identifier )
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==LCURLY) ) {
                alt49=1;
            }
            else if ( (LA49_0==Identifier) ) {
                alt49=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 49, 0, input);

                throw nvae;
            }
            switch (alt49) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:610:2: LCURLY (e= simpleBooleanExpression ( COMMA e= simpleBooleanExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleBooleanListExpression2394); if (state.failed) return expr;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:610:9: (e= simpleBooleanExpression ( COMMA e= simpleBooleanExpression )* )?
                    int alt48=2;
                    int LA48_0 = input.LA(1);

                    if ( ((LA48_0>=TRUE && LA48_0<=FALSE)||LA48_0==Identifier) ) {
                        alt48=1;
                    }
                    switch (alt48) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:610:10: e= simpleBooleanExpression ( COMMA e= simpleBooleanExpression )*
                            {
                            pushFollow(FOLLOW_simpleBooleanExpression_in_simpleBooleanListExpression2401);
                            e=simpleBooleanExpression();

                            state._fsp--;
                            if (state.failed) return expr;
                            if ( state.backtracking==0 ) {
                              list.add(e);
                            }
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:610:53: ( COMMA e= simpleBooleanExpression )*
                            loop47:
                            do {
                                int alt47=2;
                                int LA47_0 = input.LA(1);

                                if ( (LA47_0==COMMA) ) {
                                    alt47=1;
                                }


                                switch (alt47) {
                            	case 1 :
                            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:610:54: COMMA e= simpleBooleanExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleBooleanListExpression2406); if (state.failed) return expr;
                            	    pushFollow(FOLLOW_simpleBooleanExpression_in_simpleBooleanListExpression2412);
                            	    e=simpleBooleanExpression();

                            	    state._fsp--;
                            	    if (state.failed) return expr;
                            	    if ( state.backtracking==0 ) {
                            	      list.add(e);
                            	    }

                            	    }
                            	    break;

                            	default :
                            	    break loop47;
                                }
                            } while (true);


                            }
                            break;

                    }

                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleBooleanListExpression2421); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createListExpression(list, TMTypeConstants.TM_TYPE_BL);
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:613:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(input.LT(1).getText(), "BOOLEANLIST"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleBooleanListExpression", "isVariableOfType(input.LT(1).getText(), \"BOOLEANLIST\")");
                    }
                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleBooleanListExpression2438); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createListExpression(var, TMTypeConstants.TM_TYPE_BL);
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
        }
        return expr;
    }
    // $ANTLR end "simpleBooleanListExpression"


    // $ANTLR start "intListExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:619:1: intListExpression returns [Expression expr = null] : e= simpleIntListExpression ;
    public final Expression intListExpression() throws RecognitionException {
        Expression expr =  null;

        Expression e = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:620:2: (e= simpleIntListExpression )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:621:2: e= simpleIntListExpression
            {
            pushFollow(FOLLOW_simpleIntListExpression_in_intListExpression2463);
            e=simpleIntListExpression();

            state._fsp--;
            if (state.failed) return expr;
            if ( state.backtracking==0 ) {
              expr = e;
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
        }
        return expr;
    }
    // $ANTLR end "intListExpression"


    // $ANTLR start "simpleIntListExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:624:1: simpleIntListExpression returns [Expression expr = null] : ( LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY | {...}?var= Identifier );
    public final Expression simpleIntListExpression() throws RecognitionException {
        Expression expr =  null;

        Token var=null;
        Expression e = null;



        	List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:627:3: ( LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY | {...}?var= Identifier )
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==LCURLY) ) {
                alt52=1;
            }
            else if ( (LA52_0==Identifier) ) {
                alt52=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 52, 0, input);

                throw nvae;
            }
            switch (alt52) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:628:2: LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleIntListExpression2484); if (state.failed) return expr;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:628:9: (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )?
                    int alt51=2;
                    int LA51_0 = input.LA(1);

                    if ( (LA51_0==DecimalLiteral||LA51_0==FloatingPointLiteral||(LA51_0>=Identifier && LA51_0<=LPAREN)||LA51_0==MINUS) ) {
                        alt51=1;
                    }
                    switch (alt51) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:628:10: e= simpleNumberExpression ( COMMA e= simpleNumberExpression )*
                            {
                            pushFollow(FOLLOW_simpleNumberExpression_in_simpleIntListExpression2491);
                            e=simpleNumberExpression();

                            state._fsp--;
                            if (state.failed) return expr;
                            if ( state.backtracking==0 ) {
                              list.add(e);
                            }
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:628:52: ( COMMA e= simpleNumberExpression )*
                            loop50:
                            do {
                                int alt50=2;
                                int LA50_0 = input.LA(1);

                                if ( (LA50_0==COMMA) ) {
                                    alt50=1;
                                }


                                switch (alt50) {
                            	case 1 :
                            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:628:53: COMMA e= simpleNumberExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleIntListExpression2496); if (state.failed) return expr;
                            	    pushFollow(FOLLOW_simpleNumberExpression_in_simpleIntListExpression2502);
                            	    e=simpleNumberExpression();

                            	    state._fsp--;
                            	    if (state.failed) return expr;
                            	    if ( state.backtracking==0 ) {
                            	      list.add(e);
                            	    }

                            	    }
                            	    break;

                            	default :
                            	    break loop50;
                                }
                            } while (true);


                            }
                            break;

                    }

                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleIntListExpression2511); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createListExpression(list, TMTypeConstants.TM_TYPE_NL);
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:631:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(input.LT(1).getText(), "INTLIST"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleIntListExpression", "isVariableOfType(input.LT(1).getText(), \"INTLIST\")");
                    }
                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleIntListExpression2528); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createListExpression(var, TMTypeConstants.TM_TYPE_NL);
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
        }
        return expr;
    }
    // $ANTLR end "simpleIntListExpression"


    // $ANTLR start "numberListExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:637:1: numberListExpression returns [Expression expr = null] : ( (e1= doubleListExpression )=>e1= doubleListExpression | e2= intListExpression );
    public final Expression numberListExpression() throws RecognitionException {
        Expression expr =  null;

        Expression e1 = null;

        Expression e2 = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:638:2: ( (e1= doubleListExpression )=>e1= doubleListExpression | e2= intListExpression )
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==LCURLY) ) {
                int LA53_1 = input.LA(2);

                if ( (synpred8_TextMarkerParser()) ) {
                    alt53=1;
                }
                else if ( (true) ) {
                    alt53=2;
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

                if ( ((synpred8_TextMarkerParser()&&(isVariableOfType(input.LT(1).getText(), "DOUBLELIST")))) ) {
                    alt53=1;
                }
                else if ( ((isVariableOfType(input.LT(1).getText(), "INTLIST"))) ) {
                    alt53=2;
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:639:2: (e1= doubleListExpression )=>e1= doubleListExpression
                    {
                    pushFollow(FOLLOW_doubleListExpression_in_numberListExpression2562);
                    e1=doubleListExpression();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = e1;
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:641:2: e2= intListExpression
                    {
                    pushFollow(FOLLOW_intListExpression_in_numberListExpression2574);
                    e2=intListExpression();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = e2;
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
        }
        return expr;
    }
    // $ANTLR end "numberListExpression"


    // $ANTLR start "doubleListExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:644:1: doubleListExpression returns [Expression expr = null] : e= simpleDoubleListExpression ;
    public final Expression doubleListExpression() throws RecognitionException {
        Expression expr =  null;

        Expression e = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:645:2: (e= simpleDoubleListExpression )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:646:2: e= simpleDoubleListExpression
            {
            pushFollow(FOLLOW_simpleDoubleListExpression_in_doubleListExpression2597);
            e=simpleDoubleListExpression();

            state._fsp--;
            if (state.failed) return expr;
            if ( state.backtracking==0 ) {
              expr = e;
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
        }
        return expr;
    }
    // $ANTLR end "doubleListExpression"


    // $ANTLR start "simpleDoubleListExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:649:1: simpleDoubleListExpression returns [Expression expr = null] : ( LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY | {...}?var= Identifier );
    public final Expression simpleDoubleListExpression() throws RecognitionException {
        Expression expr =  null;

        Token var=null;
        Expression e = null;



        	List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:652:3: ( LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY | {...}?var= Identifier )
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:653:2: LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleDoubleListExpression2618); if (state.failed) return expr;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:653:9: (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )?
                    int alt55=2;
                    int LA55_0 = input.LA(1);

                    if ( (LA55_0==DecimalLiteral||LA55_0==FloatingPointLiteral||(LA55_0>=Identifier && LA55_0<=LPAREN)||LA55_0==MINUS) ) {
                        alt55=1;
                    }
                    switch (alt55) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:653:10: e= simpleNumberExpression ( COMMA e= simpleNumberExpression )*
                            {
                            pushFollow(FOLLOW_simpleNumberExpression_in_simpleDoubleListExpression2625);
                            e=simpleNumberExpression();

                            state._fsp--;
                            if (state.failed) return expr;
                            if ( state.backtracking==0 ) {
                              list.add(e);
                            }
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:653:52: ( COMMA e= simpleNumberExpression )*
                            loop54:
                            do {
                                int alt54=2;
                                int LA54_0 = input.LA(1);

                                if ( (LA54_0==COMMA) ) {
                                    alt54=1;
                                }


                                switch (alt54) {
                            	case 1 :
                            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:653:53: COMMA e= simpleNumberExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleDoubleListExpression2630); if (state.failed) return expr;
                            	    pushFollow(FOLLOW_simpleNumberExpression_in_simpleDoubleListExpression2636);
                            	    e=simpleNumberExpression();

                            	    state._fsp--;
                            	    if (state.failed) return expr;
                            	    if ( state.backtracking==0 ) {
                            	      list.add(e);
                            	    }

                            	    }
                            	    break;

                            	default :
                            	    break loop54;
                                }
                            } while (true);


                            }
                            break;

                    }

                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleDoubleListExpression2645); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createListExpression(list, TMTypeConstants.TM_TYPE_NL);
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:656:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(input.LT(1).getText(), "DOUBLELIST"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleDoubleListExpression", "isVariableOfType(input.LT(1).getText(), \"DOUBLELIST\")");
                    }
                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleDoubleListExpression2662); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createListExpression(var, TMTypeConstants.TM_TYPE_NL);
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
        }
        return expr;
    }
    // $ANTLR end "simpleDoubleListExpression"


    // $ANTLR start "stringListExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:662:1: stringListExpression returns [Expression expr = null] : e= simpleStringListExpression ;
    public final Expression stringListExpression() throws RecognitionException {
        Expression expr =  null;

        Expression e = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:663:2: (e= simpleStringListExpression )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:664:2: e= simpleStringListExpression
            {
            pushFollow(FOLLOW_simpleStringListExpression_in_stringListExpression2687);
            e=simpleStringListExpression();

            state._fsp--;
            if (state.failed) return expr;
            if ( state.backtracking==0 ) {
              expr = e;
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
        }
        return expr;
    }
    // $ANTLR end "stringListExpression"


    // $ANTLR start "simpleStringListExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:667:1: simpleStringListExpression returns [Expression expr = null] : ( LCURLY (e= simpleStringExpression ( COMMA e= simpleStringExpression )* )? RCURLY | {...}?var= Identifier );
    public final Expression simpleStringListExpression() throws RecognitionException {
        Expression expr =  null;

        Token var=null;
        Expression e = null;



        	List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:670:3: ( LCURLY (e= simpleStringExpression ( COMMA e= simpleStringExpression )* )? RCURLY | {...}?var= Identifier )
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:671:2: LCURLY (e= simpleStringExpression ( COMMA e= simpleStringExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleStringListExpression2708); if (state.failed) return expr;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:671:9: (e= simpleStringExpression ( COMMA e= simpleStringExpression )* )?
                    int alt58=2;
                    int LA58_0 = input.LA(1);

                    if ( (LA58_0==StringLiteral||LA58_0==Identifier) ) {
                        alt58=1;
                    }
                    switch (alt58) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:671:10: e= simpleStringExpression ( COMMA e= simpleStringExpression )*
                            {
                            pushFollow(FOLLOW_simpleStringExpression_in_simpleStringListExpression2715);
                            e=simpleStringExpression();

                            state._fsp--;
                            if (state.failed) return expr;
                            if ( state.backtracking==0 ) {
                              list.add(e);
                            }
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:671:52: ( COMMA e= simpleStringExpression )*
                            loop57:
                            do {
                                int alt57=2;
                                int LA57_0 = input.LA(1);

                                if ( (LA57_0==COMMA) ) {
                                    alt57=1;
                                }


                                switch (alt57) {
                            	case 1 :
                            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:671:53: COMMA e= simpleStringExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleStringListExpression2720); if (state.failed) return expr;
                            	    pushFollow(FOLLOW_simpleStringExpression_in_simpleStringListExpression2726);
                            	    e=simpleStringExpression();

                            	    state._fsp--;
                            	    if (state.failed) return expr;
                            	    if ( state.backtracking==0 ) {
                            	      list.add(e);
                            	    }

                            	    }
                            	    break;

                            	default :
                            	    break loop57;
                                }
                            } while (true);


                            }
                            break;

                    }

                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleStringListExpression2735); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createListExpression(list, TMTypeConstants.TM_TYPE_SL);
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:674:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(input.LT(1).getText(), "STRINGLIST"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleStringListExpression", "isVariableOfType(input.LT(1).getText(), \"STRINGLIST\")");
                    }
                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleStringListExpression2752); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createListExpression(var, TMTypeConstants.TM_TYPE_SL);
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
        }
        return expr;
    }
    // $ANTLR end "simpleStringListExpression"


    // $ANTLR start "typeListExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:680:1: typeListExpression returns [Expression expr = null] : e= simpleTypeListExpression ;
    public final Expression typeListExpression() throws RecognitionException {
        Expression expr =  null;

        Expression e = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:681:2: (e= simpleTypeListExpression )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:682:2: e= simpleTypeListExpression
            {
            pushFollow(FOLLOW_simpleTypeListExpression_in_typeListExpression2777);
            e=simpleTypeListExpression();

            state._fsp--;
            if (state.failed) return expr;
            if ( state.backtracking==0 ) {
              expr = e;
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
        }
        return expr;
    }
    // $ANTLR end "typeListExpression"


    // $ANTLR start "simpleTypeListExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:685:1: simpleTypeListExpression returns [Expression expr = null] : ( LCURLY (e= simpleTypeExpression ( COMMA e= simpleTypeExpression )* )? RCURLY | {...}?var= Identifier );
    public final Expression simpleTypeListExpression() throws RecognitionException {
        Expression expr =  null;

        Token var=null;
        Expression e = null;



        	List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:688:3: ( LCURLY (e= simpleTypeExpression ( COMMA e= simpleTypeExpression )* )? RCURLY | {...}?var= Identifier )
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:689:2: LCURLY (e= simpleTypeExpression ( COMMA e= simpleTypeExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleTypeListExpression2798); if (state.failed) return expr;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:689:9: (e= simpleTypeExpression ( COMMA e= simpleTypeExpression )* )?
                    int alt61=2;
                    int LA61_0 = input.LA(1);

                    if ( (LA61_0==BasicAnnotationType||LA61_0==Identifier) ) {
                        alt61=1;
                    }
                    switch (alt61) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:689:10: e= simpleTypeExpression ( COMMA e= simpleTypeExpression )*
                            {
                            pushFollow(FOLLOW_simpleTypeExpression_in_simpleTypeListExpression2805);
                            e=simpleTypeExpression();

                            state._fsp--;
                            if (state.failed) return expr;
                            if ( state.backtracking==0 ) {
                              list.add(e);
                            }
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:689:50: ( COMMA e= simpleTypeExpression )*
                            loop60:
                            do {
                                int alt60=2;
                                int LA60_0 = input.LA(1);

                                if ( (LA60_0==COMMA) ) {
                                    alt60=1;
                                }


                                switch (alt60) {
                            	case 1 :
                            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:689:51: COMMA e= simpleTypeExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleTypeListExpression2810); if (state.failed) return expr;
                            	    pushFollow(FOLLOW_simpleTypeExpression_in_simpleTypeListExpression2816);
                            	    e=simpleTypeExpression();

                            	    state._fsp--;
                            	    if (state.failed) return expr;
                            	    if ( state.backtracking==0 ) {
                            	      list.add(e);
                            	    }

                            	    }
                            	    break;

                            	default :
                            	    break loop60;
                                }
                            } while (true);


                            }
                            break;

                    }

                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleTypeListExpression2825); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createListExpression(list, TMTypeConstants.TM_TYPE_TL);
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:692:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(input.LT(1).getText(), "TYPELIST"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleTypeListExpression", "isVariableOfType(input.LT(1).getText(), \"TYPELIST\")");
                    }
                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleTypeListExpression2842); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createListExpression(var, TMTypeConstants.TM_TYPE_TL);
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
        }
        return expr;
    }
    // $ANTLR end "simpleTypeListExpression"


    // $ANTLR start "typeExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:697:1: typeExpression returns [Expression expr = null] : (tf= typeFunction | st= simpleTypeExpression );
    public final Expression typeExpression() throws RecognitionException {
        Expression expr =  null;

        Expression tf = null;

        Expression st = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:698:2: (tf= typeFunction | st= simpleTypeExpression )
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==Identifier) ) {
                int LA63_1 = input.LA(2);

                if ( ((isVariableOfType(input.LT(1).getText(), "TYPEFUNCTION"))) ) {
                    alt63=1;
                }
                else if ( (true) ) {
                    alt63=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 63, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA63_0==BasicAnnotationType) ) {
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:699:2: tf= typeFunction
                    {
                    pushFollow(FOLLOW_typeFunction_in_typeExpression2868);
                    tf=typeFunction();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = tf;
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:700:4: st= simpleTypeExpression
                    {
                    pushFollow(FOLLOW_simpleTypeExpression_in_typeExpression2879);
                    st=simpleTypeExpression();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createTypeExpression(st);
                      	 
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
        }
        return expr;
    }
    // $ANTLR end "typeExpression"


    // $ANTLR start "typeFunction"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:706:1: typeFunction returns [Expression expr = null] : (e= externalTypeFunction )=>e= externalTypeFunction ;
    public final Expression typeFunction() throws RecognitionException {
        Expression expr =  null;

        Expression e = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:707:2: ( (e= externalTypeFunction )=>e= externalTypeFunction )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:708:2: (e= externalTypeFunction )=>e= externalTypeFunction
            {
            pushFollow(FOLLOW_externalTypeFunction_in_typeFunction2913);
            e=externalTypeFunction();

            state._fsp--;
            if (state.failed) return expr;
            if ( state.backtracking==0 ) {
              expr = e;
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
        }
        return expr;
    }
    // $ANTLR end "typeFunction"


    // $ANTLR start "externalTypeFunction"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:712:1: externalTypeFunction returns [Expression expr = null] : {...}?id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final Expression externalTypeFunction() throws RecognitionException {
        Expression expr =  null;

        Token id=null;
        List<Expression> args = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:713:2: ({...}?id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:714:2: {...}?id= Identifier LPAREN args= varArgumentList RPAREN
            {
            if ( !((isVariableOfType(input.LT(1).getText(), "TYPEFUNCTION"))) ) {
                if (state.backtracking>0) {state.failed=true; return expr;}
                throw new FailedPredicateException(input, "externalTypeFunction", "isVariableOfType(input.LT(1).getText(), \"TYPEFUNCTION\")");
            }
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalTypeFunction2938); if (state.failed) return expr;
            match(input,LPAREN,FOLLOW_LPAREN_in_externalTypeFunction2942); if (state.failed) return expr;
            pushFollow(FOLLOW_varArgumentList_in_externalTypeFunction2949);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return expr;
            match(input,RPAREN,FOLLOW_RPAREN_in_externalTypeFunction2953); if (state.failed) return expr;
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
        }
        return expr;
    }
    // $ANTLR end "externalTypeFunction"


    // $ANTLR start "simpleTypeExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:723:1: simpleTypeExpression returns [Expression type = null] : at= annotationType ;
    public final Expression simpleTypeExpression() throws RecognitionException {
        Expression type =  null;

        Expression at = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:724:2: (at= annotationType )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:725:2: at= annotationType
            {
            pushFollow(FOLLOW_annotationType_in_simpleTypeExpression2976);
            at=annotationType();

            state._fsp--;
            if (state.failed) return type;
            if ( state.backtracking==0 ) {
              type = at;
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
        }
        return type;
    }
    // $ANTLR end "simpleTypeExpression"


    // $ANTLR start "variable"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:728:1: variable returns [Expression var = null] : {...}?v= Identifier ;
    public final Expression variable() throws RecognitionException {
        Expression var =  null;

        Token v=null;

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:729:2: ({...}?v= Identifier )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:730:2: {...}?v= Identifier
            {
            if ( !((vars.contains(input.LT(1).getText()))) ) {
                if (state.backtracking>0) {state.failed=true; return var;}
                throw new FailedPredicateException(input, "variable", "vars.contains(input.LT(1).getText())");
            }
            v=(Token)match(input,Identifier,FOLLOW_Identifier_in_variable3000); if (state.failed) return var;
            if ( state.backtracking==0 ) {
              var=ExpressionFactory.createGenericVariableReference(v);
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
        }
        return var;
    }
    // $ANTLR end "variable"


    // $ANTLR start "listVariable"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:734:1: listVariable returns [Expression var = null] : {...}?v= Identifier ;
    public final Expression listVariable() throws RecognitionException {
        Expression var =  null;

        Token v=null;

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:735:2: ({...}?v= Identifier )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:736:2: {...}?v= Identifier
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
            v=(Token)match(input,Identifier,FOLLOW_Identifier_in_listVariable3027); if (state.failed) return var;
            if ( state.backtracking==0 ) {
              var=ExpressionFactory.createGenericVariableReference(v);
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
        }
        return var;
    }
    // $ANTLR end "listVariable"


    // $ANTLR start "quantifierPart"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:746:1: quantifierPart returns [List<Expression> exprs = new ArrayList<Expression>()] : (s= STAR (q= QUESTION )? | p= PLUS (q= QUESTION )? | q1= QUESTION (q= QUESTION )? | ( LBRACK min= numberExpression ( COMMA (max= numberExpression )? )? RBRACK (q= QUESTION )? ) );
    public final List<Expression> quantifierPart() throws RecognitionException {
        List<Expression> exprs =  new ArrayList<Expression>();

        Token s=null;
        Token q=null;
        Token p=null;
        Token q1=null;
        Expression min = null;

        Expression max = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:747:2: (s= STAR (q= QUESTION )? | p= PLUS (q= QUESTION )? | q1= QUESTION (q= QUESTION )? | ( LBRACK min= numberExpression ( COMMA (max= numberExpression )? )? RBRACK (q= QUESTION )? ) )
            int alt70=4;
            switch ( input.LA(1) ) {
            case STAR:
                {
                alt70=1;
                }
                break;
            case PLUS:
                {
                alt70=2;
                }
                break;
            case QUESTION:
                {
                alt70=3;
                }
                break;
            case LBRACK:
                {
                alt70=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return exprs;}
                NoViableAltException nvae =
                    new NoViableAltException("", 70, 0, input);

                throw nvae;
            }

            switch (alt70) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:748:3: s= STAR (q= QUESTION )?
                    {
                    s=(Token)match(input,STAR,FOLLOW_STAR_in_quantifierPart3054); if (state.failed) return exprs;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:748:14: (q= QUESTION )?
                    int alt64=2;
                    int LA64_0 = input.LA(1);

                    if ( (LA64_0==QUESTION) ) {
                        alt64=1;
                    }
                    switch (alt64) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:748:14: q= QUESTION
                            {
                            q=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_quantifierPart3060); if (state.failed) return exprs;

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {
                      exprs.add(ExpressionFactory.createQuantifierLiteralExpression(s,q));
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:749:4: p= PLUS (q= QUESTION )?
                    {
                    p=(Token)match(input,PLUS,FOLLOW_PLUS_in_quantifierPart3072); if (state.failed) return exprs;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:749:15: (q= QUESTION )?
                    int alt65=2;
                    int LA65_0 = input.LA(1);

                    if ( (LA65_0==QUESTION) ) {
                        alt65=1;
                    }
                    switch (alt65) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:749:15: q= QUESTION
                            {
                            q=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_quantifierPart3078); if (state.failed) return exprs;

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {
                      exprs.add(ExpressionFactory.createQuantifierLiteralExpression(p,q));
                    }

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:750:4: q1= QUESTION (q= QUESTION )?
                    {
                    q1=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_quantifierPart3090); if (state.failed) return exprs;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:750:20: (q= QUESTION )?
                    int alt66=2;
                    int LA66_0 = input.LA(1);

                    if ( (LA66_0==QUESTION) ) {
                        alt66=1;
                    }
                    switch (alt66) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:750:20: q= QUESTION
                            {
                            q=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_quantifierPart3096); if (state.failed) return exprs;

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {
                      exprs.add(ExpressionFactory.createQuantifierLiteralExpression(q1,q));
                    }

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:751:4: ( LBRACK min= numberExpression ( COMMA (max= numberExpression )? )? RBRACK (q= QUESTION )? )
                    {
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:751:4: ( LBRACK min= numberExpression ( COMMA (max= numberExpression )? )? RBRACK (q= QUESTION )? )
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:751:5: LBRACK min= numberExpression ( COMMA (max= numberExpression )? )? RBRACK (q= QUESTION )?
                    {
                    match(input,LBRACK,FOLLOW_LBRACK_in_quantifierPart3105); if (state.failed) return exprs;
                    pushFollow(FOLLOW_numberExpression_in_quantifierPart3111);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return exprs;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:751:35: ( COMMA (max= numberExpression )? )?
                    int alt68=2;
                    int LA68_0 = input.LA(1);

                    if ( (LA68_0==COMMA) ) {
                        alt68=1;
                    }
                    switch (alt68) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:751:36: COMMA (max= numberExpression )?
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_quantifierPart3114); if (state.failed) return exprs;
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:751:42: (max= numberExpression )?
                            int alt67=2;
                            int LA67_0 = input.LA(1);

                            if ( ((LA67_0>=EXP && LA67_0<=TAN)||LA67_0==DecimalLiteral||LA67_0==FloatingPointLiteral||(LA67_0>=Identifier && LA67_0<=LPAREN)||LA67_0==MINUS) ) {
                                alt67=1;
                            }
                            switch (alt67) {
                                case 1 :
                                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:751:43: max= numberExpression
                                    {
                                    pushFollow(FOLLOW_numberExpression_in_quantifierPart3121);
                                    max=numberExpression();

                                    state._fsp--;
                                    if (state.failed) return exprs;

                                    }
                                    break;

                            }


                            }
                            break;

                    }

                    match(input,RBRACK,FOLLOW_RBRACK_in_quantifierPart3127); if (state.failed) return exprs;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:751:79: (q= QUESTION )?
                    int alt69=2;
                    int LA69_0 = input.LA(1);

                    if ( (LA69_0==QUESTION) ) {
                        alt69=1;
                    }
                    switch (alt69) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:751:79: q= QUESTION
                            {
                            q=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_quantifierPart3133); if (state.failed) return exprs;

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {
                      if(min!=null) {exprs.add(min);}
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
        }
        return exprs;
    }
    // $ANTLR end "quantifierPart"


    // $ANTLR start "condition"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:759:1: condition returns [TextMarkerCondition result = null] : (c= conditionAnd | c= conditionContains | c= conditionContextCount | c= conditionCount | c= conditionCurrentCount | c= conditionInList | c= conditionIsInTag | c= conditionLast | c= conditionMofN | c= conditionNear | c= conditionNot | c= conditionOr | c= conditionPartOf | c= conditionPosition | c= conditionRegExp | c= conditionScore | c= conditionTotalCount | c= conditionVote | c= conditionIf | c= conditionFeature | c= conditionParse | c= conditionIs | c= conditionBefore | c= conditionAfter | c= conditionStartsWith | c= conditionEndsWith | c= conditionPartOfNeq | c= conditionSize | (c= externalCondition )=>c= externalCondition | c= variableCondition ) ;
    public final TextMarkerCondition condition() throws RecognitionException {
        TextMarkerCondition result =  null;

        TextMarkerCondition c = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:760:2: ( (c= conditionAnd | c= conditionContains | c= conditionContextCount | c= conditionCount | c= conditionCurrentCount | c= conditionInList | c= conditionIsInTag | c= conditionLast | c= conditionMofN | c= conditionNear | c= conditionNot | c= conditionOr | c= conditionPartOf | c= conditionPosition | c= conditionRegExp | c= conditionScore | c= conditionTotalCount | c= conditionVote | c= conditionIf | c= conditionFeature | c= conditionParse | c= conditionIs | c= conditionBefore | c= conditionAfter | c= conditionStartsWith | c= conditionEndsWith | c= conditionPartOfNeq | c= conditionSize | (c= externalCondition )=>c= externalCondition | c= variableCondition ) )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:761:2: (c= conditionAnd | c= conditionContains | c= conditionContextCount | c= conditionCount | c= conditionCurrentCount | c= conditionInList | c= conditionIsInTag | c= conditionLast | c= conditionMofN | c= conditionNear | c= conditionNot | c= conditionOr | c= conditionPartOf | c= conditionPosition | c= conditionRegExp | c= conditionScore | c= conditionTotalCount | c= conditionVote | c= conditionIf | c= conditionFeature | c= conditionParse | c= conditionIs | c= conditionBefore | c= conditionAfter | c= conditionStartsWith | c= conditionEndsWith | c= conditionPartOfNeq | c= conditionSize | (c= externalCondition )=>c= externalCondition | c= variableCondition )
            {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:761:2: (c= conditionAnd | c= conditionContains | c= conditionContextCount | c= conditionCount | c= conditionCurrentCount | c= conditionInList | c= conditionIsInTag | c= conditionLast | c= conditionMofN | c= conditionNear | c= conditionNot | c= conditionOr | c= conditionPartOf | c= conditionPosition | c= conditionRegExp | c= conditionScore | c= conditionTotalCount | c= conditionVote | c= conditionIf | c= conditionFeature | c= conditionParse | c= conditionIs | c= conditionBefore | c= conditionAfter | c= conditionStartsWith | c= conditionEndsWith | c= conditionPartOfNeq | c= conditionSize | (c= externalCondition )=>c= externalCondition | c= variableCondition )
            int alt71=30;
            alt71 = dfa71.predict(input);
            switch (alt71) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:762:2: c= conditionAnd
                    {
                    pushFollow(FOLLOW_conditionAnd_in_condition3171);
                    c=conditionAnd();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:763:4: c= conditionContains
                    {
                    pushFollow(FOLLOW_conditionContains_in_condition3180);
                    c=conditionContains();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:764:4: c= conditionContextCount
                    {
                    pushFollow(FOLLOW_conditionContextCount_in_condition3189);
                    c=conditionContextCount();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:765:4: c= conditionCount
                    {
                    pushFollow(FOLLOW_conditionCount_in_condition3198);
                    c=conditionCount();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:766:4: c= conditionCurrentCount
                    {
                    pushFollow(FOLLOW_conditionCurrentCount_in_condition3207);
                    c=conditionCurrentCount();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 6 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:767:4: c= conditionInList
                    {
                    pushFollow(FOLLOW_conditionInList_in_condition3216);
                    c=conditionInList();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 7 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:768:4: c= conditionIsInTag
                    {
                    pushFollow(FOLLOW_conditionIsInTag_in_condition3225);
                    c=conditionIsInTag();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 8 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:769:4: c= conditionLast
                    {
                    pushFollow(FOLLOW_conditionLast_in_condition3234);
                    c=conditionLast();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 9 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:770:4: c= conditionMofN
                    {
                    pushFollow(FOLLOW_conditionMofN_in_condition3243);
                    c=conditionMofN();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 10 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:771:4: c= conditionNear
                    {
                    pushFollow(FOLLOW_conditionNear_in_condition3252);
                    c=conditionNear();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 11 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:772:4: c= conditionNot
                    {
                    pushFollow(FOLLOW_conditionNot_in_condition3261);
                    c=conditionNot();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 12 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:773:4: c= conditionOr
                    {
                    pushFollow(FOLLOW_conditionOr_in_condition3270);
                    c=conditionOr();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 13 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:774:4: c= conditionPartOf
                    {
                    pushFollow(FOLLOW_conditionPartOf_in_condition3279);
                    c=conditionPartOf();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 14 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:775:4: c= conditionPosition
                    {
                    pushFollow(FOLLOW_conditionPosition_in_condition3288);
                    c=conditionPosition();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 15 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:776:4: c= conditionRegExp
                    {
                    pushFollow(FOLLOW_conditionRegExp_in_condition3297);
                    c=conditionRegExp();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 16 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:777:4: c= conditionScore
                    {
                    pushFollow(FOLLOW_conditionScore_in_condition3306);
                    c=conditionScore();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 17 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:778:4: c= conditionTotalCount
                    {
                    pushFollow(FOLLOW_conditionTotalCount_in_condition3315);
                    c=conditionTotalCount();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 18 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:779:4: c= conditionVote
                    {
                    pushFollow(FOLLOW_conditionVote_in_condition3324);
                    c=conditionVote();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 19 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:780:4: c= conditionIf
                    {
                    pushFollow(FOLLOW_conditionIf_in_condition3333);
                    c=conditionIf();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 20 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:781:4: c= conditionFeature
                    {
                    pushFollow(FOLLOW_conditionFeature_in_condition3342);
                    c=conditionFeature();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 21 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:782:4: c= conditionParse
                    {
                    pushFollow(FOLLOW_conditionParse_in_condition3351);
                    c=conditionParse();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 22 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:783:4: c= conditionIs
                    {
                    pushFollow(FOLLOW_conditionIs_in_condition3360);
                    c=conditionIs();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 23 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:784:4: c= conditionBefore
                    {
                    pushFollow(FOLLOW_conditionBefore_in_condition3369);
                    c=conditionBefore();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 24 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:785:4: c= conditionAfter
                    {
                    pushFollow(FOLLOW_conditionAfter_in_condition3378);
                    c=conditionAfter();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 25 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:786:4: c= conditionStartsWith
                    {
                    pushFollow(FOLLOW_conditionStartsWith_in_condition3387);
                    c=conditionStartsWith();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 26 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:787:4: c= conditionEndsWith
                    {
                    pushFollow(FOLLOW_conditionEndsWith_in_condition3396);
                    c=conditionEndsWith();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 27 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:788:4: c= conditionPartOfNeq
                    {
                    pushFollow(FOLLOW_conditionPartOfNeq_in_condition3405);
                    c=conditionPartOfNeq();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 28 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:789:4: c= conditionSize
                    {
                    pushFollow(FOLLOW_conditionSize_in_condition3414);
                    c=conditionSize();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 29 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:790:4: (c= externalCondition )=>c= externalCondition
                    {
                    pushFollow(FOLLOW_externalCondition_in_condition3432);
                    c=externalCondition();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 30 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:791:4: c= variableCondition
                    {
                    pushFollow(FOLLOW_variableCondition_in_condition3441);
                    c=variableCondition();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              result = c;
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
        }
        return result;
    }
    // $ANTLR end "condition"


    // $ANTLR start "variableCondition"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:796:1: variableCondition returns [TextMarkerCondition condition = null] : id= Identifier ;
    public final TextMarkerCondition variableCondition() throws RecognitionException {
        TextMarkerCondition condition =  null;

        Token id=null;

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:797:2: (id= Identifier )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:800:2: id= Identifier
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableCondition3474); if (state.failed) return condition;
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
        }
        return condition;
    }
    // $ANTLR end "variableCondition"


    // $ANTLR start "externalCondition"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:806:1: externalCondition returns [TextMarkerCondition condition = null] : {...}?id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final TextMarkerCondition externalCondition() throws RecognitionException {
        TextMarkerCondition condition =  null;

        Token id=null;
        List<Expression> args = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:807:2: ({...}?id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:808:2: {...}?id= Identifier LPAREN args= varArgumentList RPAREN
            {
            if ( !((isVariableOfType(input.LT(1).getText(), "CONDITION"))) ) {
                if (state.backtracking>0) {state.failed=true; return condition;}
                throw new FailedPredicateException(input, "externalCondition", "isVariableOfType(input.LT(1).getText(), \"CONDITION\")");
            }
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalCondition3501); if (state.failed) return condition;
            match(input,LPAREN,FOLLOW_LPAREN_in_externalCondition3504); if (state.failed) return condition;
            if ( state.backtracking==0 ) {
              condition = external.createExternalCondition(id, args);
            }
            pushFollow(FOLLOW_varArgumentList_in_externalCondition3514);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return condition;
            if ( state.backtracking==0 ) {
              condition = external.createExternalCondition(id, args);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_externalCondition3521); if (state.failed) return condition;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return condition;
    }
    // $ANTLR end "externalCondition"


    // $ANTLR start "conditionAnd"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:815:1: conditionAnd returns [TextMarkerCondition cond = null] : name= AND LPAREN conds= conditions RPAREN ;
    public final TextMarkerCondition conditionAnd() throws RecognitionException {
        TextMarkerCondition cond =  null;

        Token name=null;
        List<TextMarkerCondition> conds = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:816:5: (name= AND LPAREN conds= conditions RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:817:5: name= AND LPAREN conds= conditions RPAREN
            {
            name=(Token)match(input,AND,FOLLOW_AND_in_conditionAnd3549); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionAnd3551); if (state.failed) return cond;
            pushFollow(FOLLOW_conditions_in_conditionAnd3557);
            conds=conditions();

            state._fsp--;
            if (state.failed) return cond;
            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createCondition(name, conds);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_conditionAnd3571); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return cond;
    }
    // $ANTLR end "conditionAnd"


    // $ANTLR start "conditionContains"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:822:1: conditionContains returns [TextMarkerCondition cond = null] options {backtrack=true; } : name= CONTAINS LPAREN (type= typeExpression | list= listExpression COMMA a= argument ) ( COMMA min= numberExpression COMMA max= numberExpression ( COMMA percent= booleanExpression )? )? RPAREN ;
    public final TextMarkerCondition conditionContains() throws RecognitionException {
        TextMarkerCondition cond =  null;

        Token name=null;
        Expression type = null;

        Expression list = null;

        Expression a = null;

        Expression min = null;

        Expression max = null;

        Expression percent = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:826:5: (name= CONTAINS LPAREN (type= typeExpression | list= listExpression COMMA a= argument ) ( COMMA min= numberExpression COMMA max= numberExpression ( COMMA percent= booleanExpression )? )? RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:827:5: name= CONTAINS LPAREN (type= typeExpression | list= listExpression COMMA a= argument ) ( COMMA min= numberExpression COMMA max= numberExpression ( COMMA percent= booleanExpression )? )? RPAREN
            {
            name=(Token)match(input,CONTAINS,FOLLOW_CONTAINS_in_conditionContains3617); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionContains3619); if (state.failed) return cond;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:827:29: (type= typeExpression | list= listExpression COMMA a= argument )
            int alt72=2;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA72_1 = input.LA(2);

                if ( (!((((isVariableOfType(input.LT(1).getText(), "INTLIST"))||(isVariableOfType(input.LT(1).getText(), "DOUBLELIST"))||(isVariableOfType(input.LT(1).getText(), "TYPELIST"))||(isVariableOfType(input.LT(1).getText(), "BOOLEANLIST"))||(isVariableOfType(input.LT(1).getText(), "STRINGLIST")))))) ) {
                    alt72=1;
                }
                else if ( (((isVariableOfType(input.LT(1).getText(), "INTLIST"))||(isVariableOfType(input.LT(1).getText(), "DOUBLELIST"))||(isVariableOfType(input.LT(1).getText(), "TYPELIST"))||(isVariableOfType(input.LT(1).getText(), "BOOLEANLIST"))||(isVariableOfType(input.LT(1).getText(), "STRINGLIST")))) ) {
                    alt72=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return cond;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 72, 1, input);

                    throw nvae;
                }
                }
                break;
            case BasicAnnotationType:
                {
                alt72=1;
                }
                break;
            case LCURLY:
                {
                alt72=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return cond;}
                NoViableAltException nvae =
                    new NoViableAltException("", 72, 0, input);

                throw nvae;
            }

            switch (alt72) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:827:30: type= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionContains3626);
                    type=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:827:54: list= listExpression COMMA a= argument
                    {
                    pushFollow(FOLLOW_listExpression_in_conditionContains3634);
                    list=listExpression();

                    state._fsp--;
                    if (state.failed) return cond;
                    match(input,COMMA,FOLLOW_COMMA_in_conditionContains3636); if (state.failed) return cond;
                    pushFollow(FOLLOW_argument_in_conditionContains3642);
                    a=argument();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }

            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:828:5: ( COMMA min= numberExpression COMMA max= numberExpression ( COMMA percent= booleanExpression )? )?
            int alt74=2;
            int LA74_0 = input.LA(1);

            if ( (LA74_0==COMMA) ) {
                alt74=1;
            }
            switch (alt74) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:828:6: COMMA min= numberExpression COMMA max= numberExpression ( COMMA percent= booleanExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionContains3651); if (state.failed) return cond;
                    pushFollow(FOLLOW_numberExpression_in_conditionContains3657);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;
                    match(input,COMMA,FOLLOW_COMMA_in_conditionContains3659); if (state.failed) return cond;
                    pushFollow(FOLLOW_numberExpression_in_conditionContains3665);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:828:64: ( COMMA percent= booleanExpression )?
                    int alt73=2;
                    int LA73_0 = input.LA(1);

                    if ( (LA73_0==COMMA) ) {
                        alt73=1;
                    }
                    switch (alt73) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:828:65: COMMA percent= booleanExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionContains3668); if (state.failed) return cond;
                            pushFollow(FOLLOW_booleanExpression_in_conditionContains3674);
                            percent=booleanExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              if(type != null) {cond = ConditionFactory.createCondition(name,type, min, max, percent);}
                  else {cond = ConditionFactory.createCondition(name,list,a, min, max, percent);};
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_conditionContains3691); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return cond;
    }
    // $ANTLR end "conditionContains"


    // $ANTLR start "conditionContextCount"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:834:1: conditionContextCount returns [TextMarkerCondition cond = null] : name= CONTEXTCOUNT LPAREN typeExpr= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN ;
    public final TextMarkerCondition conditionContextCount() throws RecognitionException {
        TextMarkerCondition cond =  null;

        Token name=null;
        Expression typeExpr = null;

        Expression min = null;

        Expression max = null;

        Expression var = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:835:5: (name= CONTEXTCOUNT LPAREN typeExpr= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:836:5: name= CONTEXTCOUNT LPAREN typeExpr= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
            {
            name=(Token)match(input,CONTEXTCOUNT,FOLLOW_CONTEXTCOUNT_in_conditionContextCount3727); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionContextCount3729); if (state.failed) return cond;
            pushFollow(FOLLOW_typeExpression_in_conditionContextCount3735);
            typeExpr=typeExpression();

            state._fsp--;
            if (state.failed) return cond;
            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createCondition(name, typeExpr, min, max, var);
            }
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:838:5: ( COMMA min= numberExpression COMMA max= numberExpression )?
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( (LA75_0==COMMA) ) {
                int LA75_1 = input.LA(2);

                if ( (LA75_1==Identifier) ) {
                    int LA75_3 = input.LA(3);

                    if ( (LA75_3==LPAREN||LA75_3==COMMA||(LA75_3>=PLUS && LA75_3<=SLASH)||LA75_3==PERCENT) ) {
                        alt75=1;
                    }
                }
                else if ( ((LA75_1>=EXP && LA75_1<=TAN)||LA75_1==DecimalLiteral||LA75_1==FloatingPointLiteral||LA75_1==LPAREN||LA75_1==MINUS) ) {
                    alt75=1;
                }
            }
            switch (alt75) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:838:6: COMMA min= numberExpression COMMA max= numberExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionContextCount3749); if (state.failed) return cond;
                    pushFollow(FOLLOW_numberExpression_in_conditionContextCount3755);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;
                    match(input,COMMA,FOLLOW_COMMA_in_conditionContextCount3757); if (state.failed) return cond;
                    pushFollow(FOLLOW_numberExpression_in_conditionContextCount3763);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createCondition(name, typeExpr, min, max, var);
            }
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:840:5: ( COMMA var= numberVariable )?
            int alt76=2;
            int LA76_0 = input.LA(1);

            if ( (LA76_0==COMMA) ) {
                alt76=1;
            }
            switch (alt76) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:840:6: COMMA var= numberVariable
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionContextCount3778); if (state.failed) return cond;
                    pushFollow(FOLLOW_numberVariable_in_conditionContextCount3784);
                    var=numberVariable();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createCondition(name, typeExpr, min, max, var);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_conditionContextCount3799); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return cond;
    }
    // $ANTLR end "conditionContextCount"


    // $ANTLR start "conditionCount"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:845:1: conditionCount returns [TextMarkerCondition cond = null] options {backtrack=true; } : (name= COUNT LPAREN type= listExpression COMMA a= argument ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN | name= COUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN );
    public final TextMarkerCondition conditionCount() throws RecognitionException {
        TextMarkerCondition cond =  null;

        Token name=null;
        Expression type = null;

        Expression a = null;

        Expression min = null;

        Expression max = null;

        Expression var = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:849:6: (name= COUNT LPAREN type= listExpression COMMA a= argument ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN | name= COUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
            int alt81=2;
            int LA81_0 = input.LA(1);

            if ( (LA81_0==COUNT) ) {
                int LA81_1 = input.LA(2);

                if ( (synpred11_TextMarkerParser()) ) {
                    alt81=1;
                }
                else if ( (true) ) {
                    alt81=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return cond;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 81, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return cond;}
                NoViableAltException nvae =
                    new NoViableAltException("", 81, 0, input);

                throw nvae;
            }
            switch (alt81) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:850:6: name= COUNT LPAREN type= listExpression COMMA a= argument ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
                    {
                    name=(Token)match(input,COUNT,FOLLOW_COUNT_in_conditionCount3850); if (state.failed) return cond;
                    match(input,LPAREN,FOLLOW_LPAREN_in_conditionCount3852); if (state.failed) return cond;
                    pushFollow(FOLLOW_listExpression_in_conditionCount3858);
                    type=listExpression();

                    state._fsp--;
                    if (state.failed) return cond;
                    if ( state.backtracking==0 ) {
                      cond = ConditionFactory.createCondition(name, type, a, min, max, var);
                    }
                    match(input,COMMA,FOLLOW_COMMA_in_conditionCount3873); if (state.failed) return cond;
                    pushFollow(FOLLOW_argument_in_conditionCount3879);
                    a=argument();

                    state._fsp--;
                    if (state.failed) return cond;
                    if ( state.backtracking==0 ) {
                      cond = ConditionFactory.createCondition(name, type, a, min, max, var);
                    }
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:854:6: ( COMMA min= numberExpression COMMA max= numberExpression )?
                    int alt77=2;
                    int LA77_0 = input.LA(1);

                    if ( (LA77_0==COMMA) ) {
                        int LA77_1 = input.LA(2);

                        if ( (LA77_1==Identifier) ) {
                            int LA77_3 = input.LA(3);

                            if ( (LA77_3==LPAREN||LA77_3==COMMA||(LA77_3>=PLUS && LA77_3<=SLASH)||LA77_3==PERCENT) ) {
                                alt77=1;
                            }
                        }
                        else if ( ((LA77_1>=EXP && LA77_1<=TAN)||LA77_1==DecimalLiteral||LA77_1==FloatingPointLiteral||LA77_1==LPAREN||LA77_1==MINUS) ) {
                            alt77=1;
                        }
                    }
                    switch (alt77) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:854:7: COMMA min= numberExpression COMMA max= numberExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount3895); if (state.failed) return cond;
                            pushFollow(FOLLOW_numberExpression_in_conditionCount3901);
                            min=numberExpression();

                            state._fsp--;
                            if (state.failed) return cond;
                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount3903); if (state.failed) return cond;
                            pushFollow(FOLLOW_numberExpression_in_conditionCount3909);
                            max=numberExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {
                      cond = ConditionFactory.createCondition(name, type, a, min, max, var);
                    }
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:856:6: ( COMMA var= numberVariable )?
                    int alt78=2;
                    int LA78_0 = input.LA(1);

                    if ( (LA78_0==COMMA) ) {
                        alt78=1;
                    }
                    switch (alt78) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:856:7: COMMA var= numberVariable
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount3927); if (state.failed) return cond;
                            pushFollow(FOLLOW_numberVariable_in_conditionCount3933);
                            var=numberVariable();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {
                      cond = ConditionFactory.createCondition(name, type, a, min, max, var);
                    }
                    match(input,RPAREN,FOLLOW_RPAREN_in_conditionCount3949); if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:860:5: name= COUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
                    {
                    name=(Token)match(input,COUNT,FOLLOW_COUNT_in_conditionCount3965); if (state.failed) return cond;
                    match(input,LPAREN,FOLLOW_LPAREN_in_conditionCount3967); if (state.failed) return cond;
                    pushFollow(FOLLOW_typeExpression_in_conditionCount3973);
                    type=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;
                    if ( state.backtracking==0 ) {
                      cond = ConditionFactory.createCondition(name, type, min, max, var);
                    }
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:862:5: ( COMMA min= numberExpression COMMA max= numberExpression )?
                    int alt79=2;
                    int LA79_0 = input.LA(1);

                    if ( (LA79_0==COMMA) ) {
                        int LA79_1 = input.LA(2);

                        if ( ((LA79_1>=EXP && LA79_1<=TAN)||LA79_1==DecimalLiteral||LA79_1==FloatingPointLiteral||LA79_1==LPAREN||LA79_1==MINUS) ) {
                            alt79=1;
                        }
                        else if ( (LA79_1==Identifier) ) {
                            int LA79_4 = input.LA(3);

                            if ( (LA79_4==LPAREN||LA79_4==COMMA||(LA79_4>=PLUS && LA79_4<=SLASH)||LA79_4==PERCENT) ) {
                                alt79=1;
                            }
                        }
                    }
                    switch (alt79) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:862:6: COMMA min= numberExpression COMMA max= numberExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount3987); if (state.failed) return cond;
                            pushFollow(FOLLOW_numberExpression_in_conditionCount3993);
                            min=numberExpression();

                            state._fsp--;
                            if (state.failed) return cond;
                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount3995); if (state.failed) return cond;
                            pushFollow(FOLLOW_numberExpression_in_conditionCount4001);
                            max=numberExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {
                      cond = ConditionFactory.createCondition(name, type, min, max, var);
                    }
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:864:5: ( COMMA var= numberVariable )?
                    int alt80=2;
                    int LA80_0 = input.LA(1);

                    if ( (LA80_0==COMMA) ) {
                        alt80=1;
                    }
                    switch (alt80) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:864:6: COMMA var= numberVariable
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount4016); if (state.failed) return cond;
                            pushFollow(FOLLOW_numberVariable_in_conditionCount4022);
                            var=numberVariable();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {
                      cond = ConditionFactory.createCondition(name, type, min, max, var);
                    }
                    match(input,RPAREN,FOLLOW_RPAREN_in_conditionCount4039); if (state.failed) return cond;

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
        }
        return cond;
    }
    // $ANTLR end "conditionCount"


    // $ANTLR start "conditionCurrentCount"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:869:1: conditionCurrentCount returns [TextMarkerCondition cond = null] : name= CURRENTCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN ;
    public final TextMarkerCondition conditionCurrentCount() throws RecognitionException {
        TextMarkerCondition cond =  null;

        Token name=null;
        Expression type = null;

        Expression min = null;

        Expression max = null;

        Expression var = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:870:5: (name= CURRENTCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:871:5: name= CURRENTCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
            {
            name=(Token)match(input,CURRENTCOUNT,FOLLOW_CURRENTCOUNT_in_conditionCurrentCount4079); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionCurrentCount4081); if (state.failed) return cond;
            pushFollow(FOLLOW_typeExpression_in_conditionCurrentCount4087);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;
            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createCondition(name,type, min, max, var);
            }
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:873:5: ( COMMA min= numberExpression COMMA max= numberExpression )?
            int alt82=2;
            int LA82_0 = input.LA(1);

            if ( (LA82_0==COMMA) ) {
                int LA82_1 = input.LA(2);

                if ( (LA82_1==Identifier) ) {
                    int LA82_3 = input.LA(3);

                    if ( (LA82_3==LPAREN||LA82_3==COMMA||(LA82_3>=PLUS && LA82_3<=SLASH)||LA82_3==PERCENT) ) {
                        alt82=1;
                    }
                }
                else if ( ((LA82_1>=EXP && LA82_1<=TAN)||LA82_1==DecimalLiteral||LA82_1==FloatingPointLiteral||LA82_1==LPAREN||LA82_1==MINUS) ) {
                    alt82=1;
                }
            }
            switch (alt82) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:873:6: COMMA min= numberExpression COMMA max= numberExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionCurrentCount4101); if (state.failed) return cond;
                    pushFollow(FOLLOW_numberExpression_in_conditionCurrentCount4107);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;
                    match(input,COMMA,FOLLOW_COMMA_in_conditionCurrentCount4109); if (state.failed) return cond;
                    pushFollow(FOLLOW_numberExpression_in_conditionCurrentCount4115);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createCondition(name,type, min, max, var);
            }
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:875:5: ( COMMA var= numberVariable )?
            int alt83=2;
            int LA83_0 = input.LA(1);

            if ( (LA83_0==COMMA) ) {
                alt83=1;
            }
            switch (alt83) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:875:6: COMMA var= numberVariable
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionCurrentCount4131); if (state.failed) return cond;
                    pushFollow(FOLLOW_numberVariable_in_conditionCurrentCount4137);
                    var=numberVariable();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createCondition(name,type, min, max, var);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_conditionCurrentCount4152); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return cond;
    }
    // $ANTLR end "conditionCurrentCount"


    // $ANTLR start "conditionTotalCount"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:880:1: conditionTotalCount returns [TextMarkerCondition cond = null] : name= TOTALCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN ;
    public final TextMarkerCondition conditionTotalCount() throws RecognitionException {
        TextMarkerCondition cond =  null;

        Token name=null;
        Expression type = null;

        Expression min = null;

        Expression max = null;

        Expression var = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:881:5: (name= TOTALCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:882:5: name= TOTALCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
            {
            name=(Token)match(input,TOTALCOUNT,FOLLOW_TOTALCOUNT_in_conditionTotalCount4191); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionTotalCount4193); if (state.failed) return cond;
            pushFollow(FOLLOW_typeExpression_in_conditionTotalCount4199);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;
            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createCondition(name,type, min, max, var);
            }
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:884:5: ( COMMA min= numberExpression COMMA max= numberExpression )?
            int alt84=2;
            int LA84_0 = input.LA(1);

            if ( (LA84_0==COMMA) ) {
                int LA84_1 = input.LA(2);

                if ( (LA84_1==Identifier) ) {
                    int LA84_3 = input.LA(3);

                    if ( (LA84_3==LPAREN||LA84_3==COMMA||(LA84_3>=PLUS && LA84_3<=SLASH)||LA84_3==PERCENT) ) {
                        alt84=1;
                    }
                }
                else if ( ((LA84_1>=EXP && LA84_1<=TAN)||LA84_1==DecimalLiteral||LA84_1==FloatingPointLiteral||LA84_1==LPAREN||LA84_1==MINUS) ) {
                    alt84=1;
                }
            }
            switch (alt84) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:884:6: COMMA min= numberExpression COMMA max= numberExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionTotalCount4213); if (state.failed) return cond;
                    pushFollow(FOLLOW_numberExpression_in_conditionTotalCount4219);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;
                    match(input,COMMA,FOLLOW_COMMA_in_conditionTotalCount4221); if (state.failed) return cond;
                    pushFollow(FOLLOW_numberExpression_in_conditionTotalCount4227);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createCondition(name,type, min, max, var);
            }
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:886:5: ( COMMA var= numberVariable )?
            int alt85=2;
            int LA85_0 = input.LA(1);

            if ( (LA85_0==COMMA) ) {
                alt85=1;
            }
            switch (alt85) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:886:6: COMMA var= numberVariable
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionTotalCount4242); if (state.failed) return cond;
                    pushFollow(FOLLOW_numberVariable_in_conditionTotalCount4248);
                    var=numberVariable();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createCondition(name, type, min, max, var);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_conditionTotalCount4263); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return cond;
    }
    // $ANTLR end "conditionTotalCount"


    // $ANTLR start "conditionInList"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:890:1: conditionInList returns [TextMarkerCondition cond = null] options {backtrack=true; } : name= INLIST LPAREN ( (list2= stringListExpression )=>list2= stringListExpression | list1= wordListExpression ) ( COMMA dist= numberExpression ( COMMA rel= booleanExpression )? )? RPAREN ;
    public final TextMarkerCondition conditionInList() throws RecognitionException {
        TextMarkerCondition cond =  null;

        Token name=null;
        Expression list2 = null;

        Expression list1 = null;

        Expression dist = null;

        Expression rel = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:894:5: (name= INLIST LPAREN ( (list2= stringListExpression )=>list2= stringListExpression | list1= wordListExpression ) ( COMMA dist= numberExpression ( COMMA rel= booleanExpression )? )? RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:895:5: name= INLIST LPAREN ( (list2= stringListExpression )=>list2= stringListExpression | list1= wordListExpression ) ( COMMA dist= numberExpression ( COMMA rel= booleanExpression )? )? RPAREN
            {
            name=(Token)match(input,INLIST,FOLLOW_INLIST_in_conditionInList4304); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionInList4306); if (state.failed) return cond;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:895:26: ( (list2= stringListExpression )=>list2= stringListExpression | list1= wordListExpression )
            int alt86=2;
            int LA86_0 = input.LA(1);

            if ( (LA86_0==LCURLY) && (synpred12_TextMarkerParser())) {
                alt86=1;
            }
            else if ( (LA86_0==Identifier) ) {
                int LA86_2 = input.LA(2);

                if ( ((synpred12_TextMarkerParser()&&(isVariableOfType(input.LT(1).getText(), "STRINGLIST")))) ) {
                    alt86=1;
                }
                else if ( (true) ) {
                    alt86=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return cond;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 86, 2, input);

                    throw nvae;
                }
            }
            else if ( (LA86_0==RessourceLiteral) ) {
                alt86=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return cond;}
                NoViableAltException nvae =
                    new NoViableAltException("", 86, 0, input);

                throw nvae;
            }
            switch (alt86) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:895:27: (list2= stringListExpression )=>list2= stringListExpression
                    {
                    pushFollow(FOLLOW_stringListExpression_in_conditionInList4321);
                    list2=stringListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:895:90: list1= wordListExpression
                    {
                    pushFollow(FOLLOW_wordListExpression_in_conditionInList4329);
                    list1=wordListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }

            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:896:5: ( COMMA dist= numberExpression ( COMMA rel= booleanExpression )? )?
            int alt88=2;
            int LA88_0 = input.LA(1);

            if ( (LA88_0==COMMA) ) {
                alt88=1;
            }
            switch (alt88) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:896:6: COMMA dist= numberExpression ( COMMA rel= booleanExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionInList4338); if (state.failed) return cond;
                    pushFollow(FOLLOW_numberExpression_in_conditionInList4344);
                    dist=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:896:36: ( COMMA rel= booleanExpression )?
                    int alt87=2;
                    int LA87_0 = input.LA(1);

                    if ( (LA87_0==COMMA) ) {
                        alt87=1;
                    }
                    switch (alt87) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:896:37: COMMA rel= booleanExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionInList4347); if (state.failed) return cond;
                            pushFollow(FOLLOW_booleanExpression_in_conditionInList4353);
                            rel=booleanExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              if(list1 != null) {cond = ConditionFactory.createCondition(name, list1, dist, rel);}
                  else {cond = ConditionFactory.createCondition(name, list2, dist, rel);};
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_conditionInList4371); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return cond;
    }
    // $ANTLR end "conditionInList"


    // $ANTLR start "conditionIsInTag"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:903:1: conditionIsInTag returns [TextMarkerCondition cond = null] : name= ISINTAG LPAREN id= stringExpression ( COMMA id1= stringExpression ASSIGN_EQUAL id2= stringExpression )* RPAREN ;
    public final TextMarkerCondition conditionIsInTag() throws RecognitionException {
        TextMarkerCondition cond =  null;

        Token name=null;
        Expression id = null;

        Expression id1 = null;

        Expression id2 = null;



        List<Expression> list1 = new ArrayList<Expression>();
        List<Expression> list2 = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:908:5: (name= ISINTAG LPAREN id= stringExpression ( COMMA id1= stringExpression ASSIGN_EQUAL id2= stringExpression )* RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:909:5: name= ISINTAG LPAREN id= stringExpression ( COMMA id1= stringExpression ASSIGN_EQUAL id2= stringExpression )* RPAREN
            {
            name=(Token)match(input,ISINTAG,FOLLOW_ISINTAG_in_conditionIsInTag4414); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionIsInTag4416); if (state.failed) return cond;
            pushFollow(FOLLOW_stringExpression_in_conditionIsInTag4422);
            id=stringExpression();

            state._fsp--;
            if (state.failed) return cond;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:909:49: ( COMMA id1= stringExpression ASSIGN_EQUAL id2= stringExpression )*
            loop89:
            do {
                int alt89=2;
                int LA89_0 = input.LA(1);

                if ( (LA89_0==COMMA) ) {
                    alt89=1;
                }


                switch (alt89) {
            	case 1 :
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:909:50: COMMA id1= stringExpression ASSIGN_EQUAL id2= stringExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_conditionIsInTag4425); if (state.failed) return cond;
            	    pushFollow(FOLLOW_stringExpression_in_conditionIsInTag4431);
            	    id1=stringExpression();

            	    state._fsp--;
            	    if (state.failed) return cond;
            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_conditionIsInTag4433); if (state.failed) return cond;
            	    pushFollow(FOLLOW_stringExpression_in_conditionIsInTag4439);
            	    id2=stringExpression();

            	    state._fsp--;
            	    if (state.failed) return cond;
            	    if ( state.backtracking==0 ) {
            	      list1.add(id1);list2.add(id2);
            	    }

            	    }
            	    break;

            	default :
            	    break loop89;
                }
            } while (true);

            if ( state.backtracking==0 ) {
              List exprs = new ArrayList();
                  exprs.add(id);
                  exprs.addAll(list1);
                  exprs.addAll(list2);
                  cond = ConditionFactory.createCondition(name, exprs);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_conditionIsInTag4458); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return cond;
    }
    // $ANTLR end "conditionIsInTag"


    // $ANTLR start "conditionLast"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:918:1: conditionLast returns [TextMarkerCondition cond = null] : name= LAST LPAREN type= typeExpression RPAREN ;
    public final TextMarkerCondition conditionLast() throws RecognitionException {
        TextMarkerCondition cond =  null;

        Token name=null;
        Expression type = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:919:5: (name= LAST LPAREN type= typeExpression RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:920:5: name= LAST LPAREN type= typeExpression RPAREN
            {
            name=(Token)match(input,LAST,FOLLOW_LAST_in_conditionLast4498); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionLast4500); if (state.failed) return cond;
            pushFollow(FOLLOW_typeExpression_in_conditionLast4506);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;
            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createCondition(name, type);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_conditionLast4519); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return cond;
    }
    // $ANTLR end "conditionLast"


    // $ANTLR start "conditionMofN"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:925:1: conditionMofN returns [TextMarkerCondition cond = null] : name= MOFN LPAREN min= numberExpression COMMA max= numberExpression COMMA conds= conditions RPAREN ;
    public final TextMarkerCondition conditionMofN() throws RecognitionException {
        TextMarkerCondition cond =  null;

        Token name=null;
        Expression min = null;

        Expression max = null;

        List<TextMarkerCondition> conds = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:926:5: (name= MOFN LPAREN min= numberExpression COMMA max= numberExpression COMMA conds= conditions RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:927:5: name= MOFN LPAREN min= numberExpression COMMA max= numberExpression COMMA conds= conditions RPAREN
            {
            name=(Token)match(input,MOFN,FOLLOW_MOFN_in_conditionMofN4555); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionMofN4557); if (state.failed) return cond;
            pushFollow(FOLLOW_numberExpression_in_conditionMofN4563);
            min=numberExpression();

            state._fsp--;
            if (state.failed) return cond;
            match(input,COMMA,FOLLOW_COMMA_in_conditionMofN4565); if (state.failed) return cond;
            pushFollow(FOLLOW_numberExpression_in_conditionMofN4571);
            max=numberExpression();

            state._fsp--;
            if (state.failed) return cond;
            match(input,COMMA,FOLLOW_COMMA_in_conditionMofN4573); if (state.failed) return cond;
            pushFollow(FOLLOW_conditions_in_conditionMofN4579);
            conds=conditions();

            state._fsp--;
            if (state.failed) return cond;
            if ( state.backtracking==0 ) {
              List exprs = new ArrayList();
                  exprs.add(min);
                  exprs.add(max);
                  exprs.addAll(conds);
                  cond = ConditionFactory.createCondition(name, exprs);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_conditionMofN4594); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return cond;
    }
    // $ANTLR end "conditionMofN"


    // $ANTLR start "conditionNear"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:936:1: conditionNear returns [TextMarkerCondition cond = null] : name= NEAR LPAREN type= typeExpression COMMA min= numberExpression COMMA max= numberExpression ( COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )? )? RPAREN ;
    public final TextMarkerCondition conditionNear() throws RecognitionException {
        TextMarkerCondition cond =  null;

        Token name=null;
        Expression type = null;

        Expression min = null;

        Expression max = null;

        Expression direction = null;

        Expression filtered = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:937:5: (name= NEAR LPAREN type= typeExpression COMMA min= numberExpression COMMA max= numberExpression ( COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )? )? RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:938:5: name= NEAR LPAREN type= typeExpression COMMA min= numberExpression COMMA max= numberExpression ( COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )? )? RPAREN
            {
            name=(Token)match(input,NEAR,FOLLOW_NEAR_in_conditionNear4626); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionNear4628); if (state.failed) return cond;
            pushFollow(FOLLOW_typeExpression_in_conditionNear4634);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;
            match(input,COMMA,FOLLOW_COMMA_in_conditionNear4636); if (state.failed) return cond;
            pushFollow(FOLLOW_numberExpression_in_conditionNear4642);
            min=numberExpression();

            state._fsp--;
            if (state.failed) return cond;
            match(input,COMMA,FOLLOW_COMMA_in_conditionNear4644); if (state.failed) return cond;
            pushFollow(FOLLOW_numberExpression_in_conditionNear4650);
            max=numberExpression();

            state._fsp--;
            if (state.failed) return cond;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:939:5: ( COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )? )?
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( (LA91_0==COMMA) ) {
                alt91=1;
            }
            switch (alt91) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:939:6: COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionNear4658); if (state.failed) return cond;
                    pushFollow(FOLLOW_booleanExpression_in_conditionNear4664);
                    direction=booleanExpression();

                    state._fsp--;
                    if (state.failed) return cond;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:939:42: ( COMMA filtered= booleanExpression )?
                    int alt90=2;
                    int LA90_0 = input.LA(1);

                    if ( (LA90_0==COMMA) ) {
                        alt90=1;
                    }
                    switch (alt90) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:939:43: COMMA filtered= booleanExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionNear4667); if (state.failed) return cond;
                            pushFollow(FOLLOW_booleanExpression_in_conditionNear4673);
                            filtered=booleanExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createCondition(name, type, min, max, direction, filtered);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_conditionNear4693); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return cond;
    }
    // $ANTLR end "conditionNear"


    // $ANTLR start "conditionNot"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:943:1: conditionNot returns [TextMarkerCondition cond = null] : ( (name= MINUS c= condition ) | (name= NOT LPAREN c= condition RPAREN ) ) ;
    public final TextMarkerCondition conditionNot() throws RecognitionException {
        TextMarkerCondition cond =  null;

        Token name=null;
        TextMarkerCondition c = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:944:5: ( ( (name= MINUS c= condition ) | (name= NOT LPAREN c= condition RPAREN ) ) )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:945:5: ( (name= MINUS c= condition ) | (name= NOT LPAREN c= condition RPAREN ) )
            {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:945:5: ( (name= MINUS c= condition ) | (name= NOT LPAREN c= condition RPAREN ) )
            int alt92=2;
            int LA92_0 = input.LA(1);

            if ( (LA92_0==MINUS) ) {
                alt92=1;
            }
            else if ( (LA92_0==NOT) ) {
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:945:6: (name= MINUS c= condition )
                    {
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:945:6: (name= MINUS c= condition )
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:945:7: name= MINUS c= condition
                    {
                    name=(Token)match(input,MINUS,FOLLOW_MINUS_in_conditionNot4726); if (state.failed) return cond;
                    pushFollow(FOLLOW_condition_in_conditionNot4732);
                    c=condition();

                    state._fsp--;
                    if (state.failed) return cond;

                    }


                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:945:38: (name= NOT LPAREN c= condition RPAREN )
                    {
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:945:38: (name= NOT LPAREN c= condition RPAREN )
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:945:39: name= NOT LPAREN c= condition RPAREN
                    {
                    name=(Token)match(input,NOT,FOLLOW_NOT_in_conditionNot4743); if (state.failed) return cond;
                    match(input,LPAREN,FOLLOW_LPAREN_in_conditionNot4745); if (state.failed) return cond;
                    pushFollow(FOLLOW_condition_in_conditionNot4751);
                    c=condition();

                    state._fsp--;
                    if (state.failed) return cond;
                    match(input,RPAREN,FOLLOW_RPAREN_in_conditionNot4753); if (state.failed) return cond;

                    }


                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createCondition(name, c);
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
        }
        return cond;
    }
    // $ANTLR end "conditionNot"


    // $ANTLR start "conditionOr"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:948:1: conditionOr returns [TextMarkerCondition cond = null] : name= OR LPAREN conds= conditions RPAREN ;
    public final TextMarkerCondition conditionOr() throws RecognitionException {
        TextMarkerCondition cond =  null;

        Token name=null;
        List<TextMarkerCondition> conds = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:949:5: (name= OR LPAREN conds= conditions RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:950:5: name= OR LPAREN conds= conditions RPAREN
            {
            name=(Token)match(input,OR,FOLLOW_OR_in_conditionOr4793); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionOr4795); if (state.failed) return cond;
            pushFollow(FOLLOW_conditions_in_conditionOr4801);
            conds=conditions();

            state._fsp--;
            if (state.failed) return cond;
            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createCondition(name, conds);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_conditionOr4814); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return cond;
    }
    // $ANTLR end "conditionOr"


    // $ANTLR start "conditionPartOf"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:954:1: conditionPartOf returns [TextMarkerCondition cond = null] : name= PARTOF LPAREN (type= typeExpression | type= typeListExpression ) RPAREN ;
    public final TextMarkerCondition conditionPartOf() throws RecognitionException {
        TextMarkerCondition cond =  null;

        Token name=null;
        Expression type = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:955:5: (name= PARTOF LPAREN (type= typeExpression | type= typeListExpression ) RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:956:5: name= PARTOF LPAREN (type= typeExpression | type= typeListExpression ) RPAREN
            {
            name=(Token)match(input,PARTOF,FOLLOW_PARTOF_in_conditionPartOf4842); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionPartOf4844); if (state.failed) return cond;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:956:26: (type= typeExpression | type= typeListExpression )
            int alt93=2;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA93_1 = input.LA(2);

                if ( (!(((isVariableOfType(input.LT(1).getText(), "TYPELIST"))))) ) {
                    alt93=1;
                }
                else if ( ((isVariableOfType(input.LT(1).getText(), "TYPELIST"))) ) {
                    alt93=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return cond;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 93, 1, input);

                    throw nvae;
                }
                }
                break;
            case BasicAnnotationType:
                {
                alt93=1;
                }
                break;
            case LCURLY:
                {
                alt93=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return cond;}
                NoViableAltException nvae =
                    new NoViableAltException("", 93, 0, input);

                throw nvae;
            }

            switch (alt93) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:956:27: type= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionPartOf4851);
                    type=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:956:49: type= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionPartOf4857);
                    type=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createCondition(name, type);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_conditionPartOf4874); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return cond;
    }
    // $ANTLR end "conditionPartOf"


    // $ANTLR start "conditionPartOfNeq"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:961:1: conditionPartOfNeq returns [TextMarkerCondition cond = null] : name= PARTOFNEQ LPAREN (type= typeExpression | type= typeListExpression ) RPAREN ;
    public final TextMarkerCondition conditionPartOfNeq() throws RecognitionException {
        TextMarkerCondition cond =  null;

        Token name=null;
        Expression type = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:962:5: (name= PARTOFNEQ LPAREN (type= typeExpression | type= typeListExpression ) RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:963:5: name= PARTOFNEQ LPAREN (type= typeExpression | type= typeListExpression ) RPAREN
            {
            name=(Token)match(input,PARTOFNEQ,FOLLOW_PARTOFNEQ_in_conditionPartOfNeq4907); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionPartOfNeq4909); if (state.failed) return cond;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:963:29: (type= typeExpression | type= typeListExpression )
            int alt94=2;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA94_1 = input.LA(2);

                if ( (!(((isVariableOfType(input.LT(1).getText(), "TYPELIST"))))) ) {
                    alt94=1;
                }
                else if ( ((isVariableOfType(input.LT(1).getText(), "TYPELIST"))) ) {
                    alt94=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return cond;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 94, 1, input);

                    throw nvae;
                }
                }
                break;
            case BasicAnnotationType:
                {
                alt94=1;
                }
                break;
            case LCURLY:
                {
                alt94=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return cond;}
                NoViableAltException nvae =
                    new NoViableAltException("", 94, 0, input);

                throw nvae;
            }

            switch (alt94) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:963:30: type= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionPartOfNeq4916);
                    type=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:963:52: type= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionPartOfNeq4922);
                    type=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createCondition(name, type);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_conditionPartOfNeq4939); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return cond;
    }
    // $ANTLR end "conditionPartOfNeq"


    // $ANTLR start "conditionPosition"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:969:1: conditionPosition returns [TextMarkerCondition cond = null] : name= POSITION LPAREN type= typeExpression COMMA pos= numberExpression RPAREN ;
    public final TextMarkerCondition conditionPosition() throws RecognitionException {
        TextMarkerCondition cond =  null;

        Token name=null;
        Expression type = null;

        Expression pos = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:970:5: (name= POSITION LPAREN type= typeExpression COMMA pos= numberExpression RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:971:5: name= POSITION LPAREN type= typeExpression COMMA pos= numberExpression RPAREN
            {
            name=(Token)match(input,POSITION,FOLLOW_POSITION_in_conditionPosition4976); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionPosition4978); if (state.failed) return cond;
            pushFollow(FOLLOW_typeExpression_in_conditionPosition4984);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;
            match(input,COMMA,FOLLOW_COMMA_in_conditionPosition4986); if (state.failed) return cond;
            pushFollow(FOLLOW_numberExpression_in_conditionPosition4992);
            pos=numberExpression();

            state._fsp--;
            if (state.failed) return cond;
            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createCondition(name, type, pos);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_conditionPosition5005); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return cond;
    }
    // $ANTLR end "conditionPosition"


    // $ANTLR start "conditionRegExp"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:975:1: conditionRegExp returns [TextMarkerCondition cond = null] : name= REGEXP LPAREN pattern= stringExpression ( COMMA caseSensitive= booleanExpression )? RPAREN ;
    public final TextMarkerCondition conditionRegExp() throws RecognitionException {
        TextMarkerCondition cond =  null;

        Token name=null;
        Expression pattern = null;

        Expression caseSensitive = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:976:5: (name= REGEXP LPAREN pattern= stringExpression ( COMMA caseSensitive= booleanExpression )? RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:977:5: name= REGEXP LPAREN pattern= stringExpression ( COMMA caseSensitive= booleanExpression )? RPAREN
            {
            name=(Token)match(input,REGEXP,FOLLOW_REGEXP_in_conditionRegExp5033); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionRegExp5035); if (state.failed) return cond;
            pushFollow(FOLLOW_stringExpression_in_conditionRegExp5041);
            pattern=stringExpression();

            state._fsp--;
            if (state.failed) return cond;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:977:53: ( COMMA caseSensitive= booleanExpression )?
            int alt95=2;
            int LA95_0 = input.LA(1);

            if ( (LA95_0==COMMA) ) {
                alt95=1;
            }
            switch (alt95) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:977:54: COMMA caseSensitive= booleanExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionRegExp5044); if (state.failed) return cond;
                    pushFollow(FOLLOW_booleanExpression_in_conditionRegExp5050);
                    caseSensitive=booleanExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createCondition(name, pattern, caseSensitive);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_conditionRegExp5068); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return cond;
    }
    // $ANTLR end "conditionRegExp"


    // $ANTLR start "conditionScore"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:982:1: conditionScore returns [TextMarkerCondition cond = null] : name= SCORE LPAREN min= numberExpression ( COMMA max= numberExpression ( COMMA var= numberVariable )? )? RPAREN ;
    public final TextMarkerCondition conditionScore() throws RecognitionException {
        TextMarkerCondition cond =  null;

        Token name=null;
        Expression min = null;

        Expression max = null;

        Expression var = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:983:5: (name= SCORE LPAREN min= numberExpression ( COMMA max= numberExpression ( COMMA var= numberVariable )? )? RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:984:5: name= SCORE LPAREN min= numberExpression ( COMMA max= numberExpression ( COMMA var= numberVariable )? )? RPAREN
            {
            name=(Token)match(input,SCORE,FOLLOW_SCORE_in_conditionScore5102); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionScore5104); if (state.failed) return cond;
            pushFollow(FOLLOW_numberExpression_in_conditionScore5110);
            min=numberExpression();

            state._fsp--;
            if (state.failed) return cond;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:984:48: ( COMMA max= numberExpression ( COMMA var= numberVariable )? )?
            int alt97=2;
            int LA97_0 = input.LA(1);

            if ( (LA97_0==COMMA) ) {
                alt97=1;
            }
            switch (alt97) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:984:49: COMMA max= numberExpression ( COMMA var= numberVariable )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionScore5113); if (state.failed) return cond;
                    pushFollow(FOLLOW_numberExpression_in_conditionScore5119);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:985:5: ( COMMA var= numberVariable )?
                    int alt96=2;
                    int LA96_0 = input.LA(1);

                    if ( (LA96_0==COMMA) ) {
                        alt96=1;
                    }
                    switch (alt96) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:985:6: COMMA var= numberVariable
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionScore5128); if (state.failed) return cond;
                            pushFollow(FOLLOW_numberVariable_in_conditionScore5134);
                            var=numberVariable();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createCondition(name, min, max, var);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_conditionScore5151); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return cond;
    }
    // $ANTLR end "conditionScore"


    // $ANTLR start "conditionVote"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:990:1: conditionVote returns [TextMarkerCondition cond = null] : name= VOTE LPAREN type1= typeExpression COMMA type2= typeExpression RPAREN ;
    public final TextMarkerCondition conditionVote() throws RecognitionException {
        TextMarkerCondition cond =  null;

        Token name=null;
        Expression type1 = null;

        Expression type2 = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:991:5: (name= VOTE LPAREN type1= typeExpression COMMA type2= typeExpression RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:992:5: name= VOTE LPAREN type1= typeExpression COMMA type2= typeExpression RPAREN
            {
            name=(Token)match(input,VOTE,FOLLOW_VOTE_in_conditionVote5183); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionVote5185); if (state.failed) return cond;
            pushFollow(FOLLOW_typeExpression_in_conditionVote5191);
            type1=typeExpression();

            state._fsp--;
            if (state.failed) return cond;
            match(input,COMMA,FOLLOW_COMMA_in_conditionVote5193); if (state.failed) return cond;
            pushFollow(FOLLOW_typeExpression_in_conditionVote5199);
            type2=typeExpression();

            state._fsp--;
            if (state.failed) return cond;
            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createCondition(name, type1, type2);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_conditionVote5212); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return cond;
    }
    // $ANTLR end "conditionVote"


    // $ANTLR start "conditionIf"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:996:1: conditionIf returns [TextMarkerCondition cond = null] : name= IF LPAREN e= booleanExpression RPAREN ;
    public final TextMarkerCondition conditionIf() throws RecognitionException {
        TextMarkerCondition cond =  null;

        Token name=null;
        Expression e = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:997:5: (name= IF LPAREN e= booleanExpression RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:998:5: name= IF LPAREN e= booleanExpression RPAREN
            {
            name=(Token)match(input,IF,FOLLOW_IF_in_conditionIf5246); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionIf5248); if (state.failed) return cond;
            pushFollow(FOLLOW_booleanExpression_in_conditionIf5254);
            e=booleanExpression();

            state._fsp--;
            if (state.failed) return cond;
            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createCondition(name, e);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_conditionIf5267); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return cond;
    }
    // $ANTLR end "conditionIf"


    // $ANTLR start "conditionFeature"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1003:1: conditionFeature returns [TextMarkerCondition cond = null] : name= FEATURE LPAREN se= stringExpression COMMA v= argument RPAREN ;
    public final TextMarkerCondition conditionFeature() throws RecognitionException {
        TextMarkerCondition cond =  null;

        Token name=null;
        Expression se = null;

        Expression v = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1004:5: (name= FEATURE LPAREN se= stringExpression COMMA v= argument RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1005:5: name= FEATURE LPAREN se= stringExpression COMMA v= argument RPAREN
            {
            name=(Token)match(input,FEATURE,FOLLOW_FEATURE_in_conditionFeature5306); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionFeature5308); if (state.failed) return cond;
            pushFollow(FOLLOW_stringExpression_in_conditionFeature5314);
            se=stringExpression();

            state._fsp--;
            if (state.failed) return cond;
            match(input,COMMA,FOLLOW_COMMA_in_conditionFeature5316); if (state.failed) return cond;
            pushFollow(FOLLOW_argument_in_conditionFeature5322);
            v=argument();

            state._fsp--;
            if (state.failed) return cond;
            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createCondition(name, se, v);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_conditionFeature5335); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return cond;
    }
    // $ANTLR end "conditionFeature"


    // $ANTLR start "conditionParse"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1009:1: conditionParse returns [TextMarkerCondition cond = null] : name= PARSE LPAREN var= genericVariableReference RPAREN ;
    public final TextMarkerCondition conditionParse() throws RecognitionException {
        TextMarkerCondition cond =  null;

        Token name=null;
        Expression var = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1010:5: (name= PARSE LPAREN var= genericVariableReference RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1011:5: name= PARSE LPAREN var= genericVariableReference RPAREN
            {
            name=(Token)match(input,PARSE,FOLLOW_PARSE_in_conditionParse5366); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionParse5368); if (state.failed) return cond;
            pushFollow(FOLLOW_genericVariableReference_in_conditionParse5377);
            var=genericVariableReference();

            state._fsp--;
            if (state.failed) return cond;
            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createCondition(name, var);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_conditionParse5390); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return cond;
    }
    // $ANTLR end "conditionParse"


    // $ANTLR start "conditionIs"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1018:1: conditionIs returns [TextMarkerCondition cond = null] : name= IS LPAREN (type= typeExpression | type= typeListExpression ) RPAREN ;
    public final TextMarkerCondition conditionIs() throws RecognitionException {
        TextMarkerCondition cond =  null;

        Token name=null;
        Expression type = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1019:5: (name= IS LPAREN (type= typeExpression | type= typeListExpression ) RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1020:5: name= IS LPAREN (type= typeExpression | type= typeListExpression ) RPAREN
            {
            name=(Token)match(input,IS,FOLLOW_IS_in_conditionIs5420); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionIs5422); if (state.failed) return cond;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1020:22: (type= typeExpression | type= typeListExpression )
            int alt98=2;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA98_1 = input.LA(2);

                if ( (!(((isVariableOfType(input.LT(1).getText(), "TYPELIST"))))) ) {
                    alt98=1;
                }
                else if ( ((isVariableOfType(input.LT(1).getText(), "TYPELIST"))) ) {
                    alt98=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return cond;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 98, 1, input);

                    throw nvae;
                }
                }
                break;
            case BasicAnnotationType:
                {
                alt98=1;
                }
                break;
            case LCURLY:
                {
                alt98=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return cond;}
                NoViableAltException nvae =
                    new NoViableAltException("", 98, 0, input);

                throw nvae;
            }

            switch (alt98) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1020:23: type= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionIs5429);
                    type=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1020:45: type= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionIs5435);
                    type=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createCondition(name, type);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_conditionIs5449); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return cond;
    }
    // $ANTLR end "conditionIs"


    // $ANTLR start "conditionBefore"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1025:1: conditionBefore returns [TextMarkerCondition cond = null] : name= BEFORE LPAREN (type= typeExpression | type= typeListExpression ) RPAREN ;
    public final TextMarkerCondition conditionBefore() throws RecognitionException {
        TextMarkerCondition cond =  null;

        Token name=null;
        Expression type = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1026:5: (name= BEFORE LPAREN (type= typeExpression | type= typeListExpression ) RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1027:5: name= BEFORE LPAREN (type= typeExpression | type= typeListExpression ) RPAREN
            {
            name=(Token)match(input,BEFORE,FOLLOW_BEFORE_in_conditionBefore5478); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionBefore5480); if (state.failed) return cond;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1027:26: (type= typeExpression | type= typeListExpression )
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1027:27: type= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionBefore5487);
                    type=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1027:49: type= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionBefore5493);
                    type=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createCondition(name, type);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_conditionBefore5507); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return cond;
    }
    // $ANTLR end "conditionBefore"


    // $ANTLR start "conditionAfter"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1032:1: conditionAfter returns [TextMarkerCondition cond = null] : name= AFTER LPAREN (type= typeExpression | type= typeListExpression ) RPAREN ;
    public final TextMarkerCondition conditionAfter() throws RecognitionException {
        TextMarkerCondition cond =  null;

        Token name=null;
        Expression type = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1033:5: (name= AFTER LPAREN (type= typeExpression | type= typeListExpression ) RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1034:5: name= AFTER LPAREN (type= typeExpression | type= typeListExpression ) RPAREN
            {
            name=(Token)match(input,AFTER,FOLLOW_AFTER_in_conditionAfter5536); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionAfter5538); if (state.failed) return cond;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1034:25: (type= typeExpression | type= typeListExpression )
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1034:26: type= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionAfter5545);
                    type=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1034:48: type= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionAfter5551);
                    type=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createCondition(name, type);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_conditionAfter5565); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return cond;
    }
    // $ANTLR end "conditionAfter"


    // $ANTLR start "conditionStartsWith"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1039:1: conditionStartsWith returns [TextMarkerCondition cond = null] : name= STARTSWITH LPAREN (type= typeExpression | type= typeListExpression ) RPAREN ;
    public final TextMarkerCondition conditionStartsWith() throws RecognitionException {
        TextMarkerCondition cond =  null;

        Token name=null;
        Expression type = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1040:5: (name= STARTSWITH LPAREN (type= typeExpression | type= typeListExpression ) RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1041:5: name= STARTSWITH LPAREN (type= typeExpression | type= typeListExpression ) RPAREN
            {
            name=(Token)match(input,STARTSWITH,FOLLOW_STARTSWITH_in_conditionStartsWith5598); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionStartsWith5600); if (state.failed) return cond;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1041:30: (type= typeExpression | type= typeListExpression )
            int alt101=2;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA101_1 = input.LA(2);

                if ( (!(((isVariableOfType(input.LT(1).getText(), "TYPELIST"))))) ) {
                    alt101=1;
                }
                else if ( ((isVariableOfType(input.LT(1).getText(), "TYPELIST"))) ) {
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1041:31: type= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionStartsWith5607);
                    type=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1041:53: type= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionStartsWith5613);
                    type=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createCondition(name, type);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_conditionStartsWith5627); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return cond;
    }
    // $ANTLR end "conditionStartsWith"


    // $ANTLR start "conditionEndsWith"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1046:1: conditionEndsWith returns [TextMarkerCondition cond = null] : name= ENDSWITH LPAREN (type= typeExpression | type= typeListExpression ) RPAREN ;
    public final TextMarkerCondition conditionEndsWith() throws RecognitionException {
        TextMarkerCondition cond =  null;

        Token name=null;
        Expression type = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1047:5: (name= ENDSWITH LPAREN (type= typeExpression | type= typeListExpression ) RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1048:5: name= ENDSWITH LPAREN (type= typeExpression | type= typeListExpression ) RPAREN
            {
            name=(Token)match(input,ENDSWITH,FOLLOW_ENDSWITH_in_conditionEndsWith5660); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionEndsWith5662); if (state.failed) return cond;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1048:28: (type= typeExpression | type= typeListExpression )
            int alt102=2;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA102_1 = input.LA(2);

                if ( (!(((isVariableOfType(input.LT(1).getText(), "TYPELIST"))))) ) {
                    alt102=1;
                }
                else if ( ((isVariableOfType(input.LT(1).getText(), "TYPELIST"))) ) {
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1048:29: type= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionEndsWith5669);
                    type=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1048:51: type= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionEndsWith5675);
                    type=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createCondition(name, type);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_conditionEndsWith5689); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return cond;
    }
    // $ANTLR end "conditionEndsWith"


    // $ANTLR start "conditionSize"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1053:1: conditionSize returns [TextMarkerCondition cond = null] : name= SIZE LPAREN list= listExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN ;
    public final TextMarkerCondition conditionSize() throws RecognitionException {
        TextMarkerCondition cond =  null;

        Token name=null;
        Expression list = null;

        Expression min = null;

        Expression max = null;

        Expression var = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1054:5: (name= SIZE LPAREN list= listExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1055:5: name= SIZE LPAREN list= listExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
            {
            name=(Token)match(input,SIZE,FOLLOW_SIZE_in_conditionSize5722); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionSize5724); if (state.failed) return cond;
            pushFollow(FOLLOW_listExpression_in_conditionSize5730);
            list=listExpression();

            state._fsp--;
            if (state.failed) return cond;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1055:46: ( COMMA min= numberExpression COMMA max= numberExpression )?
            int alt103=2;
            int LA103_0 = input.LA(1);

            if ( (LA103_0==COMMA) ) {
                int LA103_1 = input.LA(2);

                if ( ((LA103_1>=EXP && LA103_1<=TAN)||LA103_1==DecimalLiteral||LA103_1==FloatingPointLiteral||LA103_1==LPAREN||LA103_1==MINUS) ) {
                    alt103=1;
                }
                else if ( (LA103_1==Identifier) ) {
                    int LA103_4 = input.LA(3);

                    if ( (LA103_4==LPAREN||LA103_4==COMMA||(LA103_4>=PLUS && LA103_4<=SLASH)||LA103_4==PERCENT) ) {
                        alt103=1;
                    }
                }
            }
            switch (alt103) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1055:47: COMMA min= numberExpression COMMA max= numberExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionSize5733); if (state.failed) return cond;
                    pushFollow(FOLLOW_numberExpression_in_conditionSize5739);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;
                    match(input,COMMA,FOLLOW_COMMA_in_conditionSize5741); if (state.failed) return cond;
                    pushFollow(FOLLOW_numberExpression_in_conditionSize5747);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }

            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1055:107: ( COMMA var= numberVariable )?
            int alt104=2;
            int LA104_0 = input.LA(1);

            if ( (LA104_0==COMMA) ) {
                alt104=1;
            }
            switch (alt104) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1055:108: COMMA var= numberVariable
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionSize5752); if (state.failed) return cond;
                    pushFollow(FOLLOW_numberVariable_in_conditionSize5758);
                    var=numberVariable();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createCondition(name, list, min, max, var);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_conditionSize5773); if (state.failed) return cond;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return cond;
    }
    // $ANTLR end "conditionSize"


    // $ANTLR start "action"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1061:1: action returns [TextMarkerAction result = null] : (a= actionColor | a= actionDel | a= actionLog | a= actionMark | a= actionMarkScore | a= actionMarkFast | a= actionMarkLast | a= actionReplace | a= actionRetainMarkup | a= actionRetainType | a= actionFilterMarkup | a= actionFilterType | a= actionCreate | a= actionFill | a= actionCall | a= actionAssign | a= actionSetFeature | a= actionGetFeature | a= actionUnmark | a= actionUnmarkAll | a= actionTransfer | a= actionMarkOnce | a= actionTrie | a= actionGather | a= actionExec | a= actionMarkTable | a= actionAdd | a= actionRemove | a= actionRemoveDuplicate | a= actionMerge | a= actionGet | a= actionGetList | a= actionMatchedText | a= actionClear | a= actionExpand | (a= externalAction )=>a= externalAction | a= variableAction ) ;
    public final TextMarkerAction action() throws RecognitionException {
        TextMarkerAction result =  null;

        TextMarkerAction a = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1062:2: ( (a= actionColor | a= actionDel | a= actionLog | a= actionMark | a= actionMarkScore | a= actionMarkFast | a= actionMarkLast | a= actionReplace | a= actionRetainMarkup | a= actionRetainType | a= actionFilterMarkup | a= actionFilterType | a= actionCreate | a= actionFill | a= actionCall | a= actionAssign | a= actionSetFeature | a= actionGetFeature | a= actionUnmark | a= actionUnmarkAll | a= actionTransfer | a= actionMarkOnce | a= actionTrie | a= actionGather | a= actionExec | a= actionMarkTable | a= actionAdd | a= actionRemove | a= actionRemoveDuplicate | a= actionMerge | a= actionGet | a= actionGetList | a= actionMatchedText | a= actionClear | a= actionExpand | (a= externalAction )=>a= externalAction | a= variableAction ) )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1063:2: (a= actionColor | a= actionDel | a= actionLog | a= actionMark | a= actionMarkScore | a= actionMarkFast | a= actionMarkLast | a= actionReplace | a= actionRetainMarkup | a= actionRetainType | a= actionFilterMarkup | a= actionFilterType | a= actionCreate | a= actionFill | a= actionCall | a= actionAssign | a= actionSetFeature | a= actionGetFeature | a= actionUnmark | a= actionUnmarkAll | a= actionTransfer | a= actionMarkOnce | a= actionTrie | a= actionGather | a= actionExec | a= actionMarkTable | a= actionAdd | a= actionRemove | a= actionRemoveDuplicate | a= actionMerge | a= actionGet | a= actionGetList | a= actionMatchedText | a= actionClear | a= actionExpand | (a= externalAction )=>a= externalAction | a= variableAction )
            {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1063:2: (a= actionColor | a= actionDel | a= actionLog | a= actionMark | a= actionMarkScore | a= actionMarkFast | a= actionMarkLast | a= actionReplace | a= actionRetainMarkup | a= actionRetainType | a= actionFilterMarkup | a= actionFilterType | a= actionCreate | a= actionFill | a= actionCall | a= actionAssign | a= actionSetFeature | a= actionGetFeature | a= actionUnmark | a= actionUnmarkAll | a= actionTransfer | a= actionMarkOnce | a= actionTrie | a= actionGather | a= actionExec | a= actionMarkTable | a= actionAdd | a= actionRemove | a= actionRemoveDuplicate | a= actionMerge | a= actionGet | a= actionGetList | a= actionMatchedText | a= actionClear | a= actionExpand | (a= externalAction )=>a= externalAction | a= variableAction )
            int alt105=37;
            alt105 = dfa105.predict(input);
            switch (alt105) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1064:2: a= actionColor
                    {
                    pushFollow(FOLLOW_actionColor_in_action5801);
                    a=actionColor();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1065:4: a= actionDel
                    {
                    pushFollow(FOLLOW_actionDel_in_action5810);
                    a=actionDel();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1066:4: a= actionLog
                    {
                    pushFollow(FOLLOW_actionLog_in_action5819);
                    a=actionLog();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1067:4: a= actionMark
                    {
                    pushFollow(FOLLOW_actionMark_in_action5828);
                    a=actionMark();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1068:4: a= actionMarkScore
                    {
                    pushFollow(FOLLOW_actionMarkScore_in_action5837);
                    a=actionMarkScore();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 6 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1069:4: a= actionMarkFast
                    {
                    pushFollow(FOLLOW_actionMarkFast_in_action5846);
                    a=actionMarkFast();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 7 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1070:4: a= actionMarkLast
                    {
                    pushFollow(FOLLOW_actionMarkLast_in_action5855);
                    a=actionMarkLast();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 8 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1071:4: a= actionReplace
                    {
                    pushFollow(FOLLOW_actionReplace_in_action5864);
                    a=actionReplace();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 9 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1072:4: a= actionRetainMarkup
                    {
                    pushFollow(FOLLOW_actionRetainMarkup_in_action5873);
                    a=actionRetainMarkup();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 10 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1073:4: a= actionRetainType
                    {
                    pushFollow(FOLLOW_actionRetainType_in_action5882);
                    a=actionRetainType();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 11 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1074:4: a= actionFilterMarkup
                    {
                    pushFollow(FOLLOW_actionFilterMarkup_in_action5891);
                    a=actionFilterMarkup();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 12 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1075:4: a= actionFilterType
                    {
                    pushFollow(FOLLOW_actionFilterType_in_action5900);
                    a=actionFilterType();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 13 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1076:4: a= actionCreate
                    {
                    pushFollow(FOLLOW_actionCreate_in_action5909);
                    a=actionCreate();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 14 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1077:4: a= actionFill
                    {
                    pushFollow(FOLLOW_actionFill_in_action5918);
                    a=actionFill();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 15 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1078:4: a= actionCall
                    {
                    pushFollow(FOLLOW_actionCall_in_action5927);
                    a=actionCall();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 16 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1079:4: a= actionAssign
                    {
                    pushFollow(FOLLOW_actionAssign_in_action5936);
                    a=actionAssign();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 17 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1080:4: a= actionSetFeature
                    {
                    pushFollow(FOLLOW_actionSetFeature_in_action5945);
                    a=actionSetFeature();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 18 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1081:4: a= actionGetFeature
                    {
                    pushFollow(FOLLOW_actionGetFeature_in_action5954);
                    a=actionGetFeature();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 19 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1082:4: a= actionUnmark
                    {
                    pushFollow(FOLLOW_actionUnmark_in_action5963);
                    a=actionUnmark();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 20 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1083:4: a= actionUnmarkAll
                    {
                    pushFollow(FOLLOW_actionUnmarkAll_in_action5972);
                    a=actionUnmarkAll();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 21 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1084:4: a= actionTransfer
                    {
                    pushFollow(FOLLOW_actionTransfer_in_action5981);
                    a=actionTransfer();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 22 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1085:4: a= actionMarkOnce
                    {
                    pushFollow(FOLLOW_actionMarkOnce_in_action5990);
                    a=actionMarkOnce();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 23 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1086:4: a= actionTrie
                    {
                    pushFollow(FOLLOW_actionTrie_in_action5999);
                    a=actionTrie();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 24 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1087:4: a= actionGather
                    {
                    pushFollow(FOLLOW_actionGather_in_action6008);
                    a=actionGather();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 25 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1088:4: a= actionExec
                    {
                    pushFollow(FOLLOW_actionExec_in_action6018);
                    a=actionExec();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 26 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1089:4: a= actionMarkTable
                    {
                    pushFollow(FOLLOW_actionMarkTable_in_action6027);
                    a=actionMarkTable();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 27 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1090:4: a= actionAdd
                    {
                    pushFollow(FOLLOW_actionAdd_in_action6036);
                    a=actionAdd();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 28 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1091:4: a= actionRemove
                    {
                    pushFollow(FOLLOW_actionRemove_in_action6045);
                    a=actionRemove();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 29 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1092:4: a= actionRemoveDuplicate
                    {
                    pushFollow(FOLLOW_actionRemoveDuplicate_in_action6054);
                    a=actionRemoveDuplicate();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 30 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1093:4: a= actionMerge
                    {
                    pushFollow(FOLLOW_actionMerge_in_action6063);
                    a=actionMerge();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 31 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1094:4: a= actionGet
                    {
                    pushFollow(FOLLOW_actionGet_in_action6072);
                    a=actionGet();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 32 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1095:4: a= actionGetList
                    {
                    pushFollow(FOLLOW_actionGetList_in_action6082);
                    a=actionGetList();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 33 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1096:4: a= actionMatchedText
                    {
                    pushFollow(FOLLOW_actionMatchedText_in_action6091);
                    a=actionMatchedText();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 34 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1097:4: a= actionClear
                    {
                    pushFollow(FOLLOW_actionClear_in_action6100);
                    a=actionClear();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 35 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1098:4: a= actionExpand
                    {
                    pushFollow(FOLLOW_actionExpand_in_action6109);
                    a=actionExpand();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 36 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1099:4: (a= externalAction )=>a= externalAction
                    {
                    pushFollow(FOLLOW_externalAction_in_action6127);
                    a=externalAction();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 37 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1100:4: a= variableAction
                    {
                    pushFollow(FOLLOW_variableAction_in_action6136);
                    a=variableAction();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              result = a;
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
        }
        return result;
    }
    // $ANTLR end "action"


    // $ANTLR start "variableAction"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1105:1: variableAction returns [TextMarkerAction action = null] : id= Identifier ;
    public final TextMarkerAction variableAction() throws RecognitionException {
        TextMarkerAction action =  null;

        Token id=null;

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1106:2: (id= Identifier )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1109:3: id= Identifier
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableAction6167); if (state.failed) return action;
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
        }
        return action;
    }
    // $ANTLR end "variableAction"


    // $ANTLR start "externalAction"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1114:1: externalAction returns [TextMarkerAction action = null] : {...}?id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final TextMarkerAction externalAction() throws RecognitionException {
        TextMarkerAction action =  null;

        Token id=null;
        List<Expression> args = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1115:2: ({...}?id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1116:2: {...}?id= Identifier LPAREN args= varArgumentList RPAREN
            {
            if ( !((isVariableOfType(input.LT(1).getText(), "ACTION"))) ) {
                if (state.backtracking>0) {state.failed=true; return action;}
                throw new FailedPredicateException(input, "externalAction", "isVariableOfType(input.LT(1).getText(), \"ACTION\")");
            }
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalAction6191); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_externalAction6195); if (state.failed) return action;
            pushFollow(FOLLOW_varArgumentList_in_externalAction6204);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return action;
            match(input,RPAREN,FOLLOW_RPAREN_in_externalAction6209); if (state.failed) return action;
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
        }
        return action;
    }
    // $ANTLR end "externalAction"


    // $ANTLR start "actionCreate"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1126:1: actionCreate returns [TextMarkerAction action = null] : name= CREATE LPAREN structure= typeExpression ( COMMA (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? )? RPAREN ;
    public final TextMarkerAction actionCreate() throws RecognitionException {
        TextMarkerAction action =  null;

        Token name=null;
        Expression structure = null;

        Expression index = null;

        Expression fname = null;

        Expression obj1 = null;



            List left = new ArrayList();
            List right = new ArrayList();
            List indexes = new ArrayList();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1132:5: (name= CREATE LPAREN structure= typeExpression ( COMMA (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? )? RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1133:5: name= CREATE LPAREN structure= typeExpression ( COMMA (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? )? RPAREN
            {
            name=(Token)match(input,CREATE,FOLLOW_CREATE_in_actionCreate6245); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionCreate6247); if (state.failed) return action;
            pushFollow(FOLLOW_typeExpression_in_actionCreate6253);
            structure=typeExpression();

            state._fsp--;
            if (state.failed) return action;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1134:5: ( COMMA (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? )?
            int alt110=2;
            int LA110_0 = input.LA(1);

            if ( (LA110_0==COMMA) ) {
                alt110=1;
            }
            switch (alt110) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1134:6: COMMA (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionCreate6260); if (state.failed) return action;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1136:5: (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )?
                    int alt107=2;
                    alt107 = dfa107.predict(input);
                    switch (alt107) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1136:6: index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA
                            {
                            pushFollow(FOLLOW_numberExpression_in_actionCreate6281);
                            index=numberExpression();

                            state._fsp--;
                            if (state.failed) return action;
                            if ( state.backtracking==0 ) {
                              indexes.add(index);
                            }
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1136:53: ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )*
                            loop106:
                            do {
                                int alt106=2;
                                int LA106_0 = input.LA(1);

                                if ( (LA106_0==COMMA) ) {
                                    int LA106_1 = input.LA(2);

                                    if ( (synpred14_TextMarkerParser()) ) {
                                        alt106=1;
                                    }


                                }


                                switch (alt106) {
                            	case 1 :
                            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1136:54: ( COMMA index= numberExpression )=> ( COMMA index= numberExpression )
                            	    {
                            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1136:89: ( COMMA index= numberExpression )
                            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1136:90: COMMA index= numberExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_actionCreate6298); if (state.failed) return action;
                            	    pushFollow(FOLLOW_numberExpression_in_actionCreate6304);
                            	    index=numberExpression();

                            	    state._fsp--;
                            	    if (state.failed) return action;

                            	    }

                            	    if ( state.backtracking==0 ) {
                            	      indexes.add(index);
                            	    }

                            	    }
                            	    break;

                            	default :
                            	    break loop106;
                                }
                            } while (true);

                            match(input,COMMA,FOLLOW_COMMA_in_actionCreate6310); if (state.failed) return action;

                            }
                            break;

                    }

                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1138:5: (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )?
                    int alt109=2;
                    int LA109_0 = input.LA(1);

                    if ( (LA109_0==REMOVESTRING||LA109_0==StringLiteral||LA109_0==Identifier) ) {
                        alt109=1;
                    }
                    switch (alt109) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1138:6: fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )*
                            {
                            pushFollow(FOLLOW_stringExpression_in_actionCreate6328);
                            fname=stringExpression();

                            state._fsp--;
                            if (state.failed) return action;
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionCreate6330); if (state.failed) return action;
                            pushFollow(FOLLOW_argument_in_actionCreate6336);
                            obj1=argument();

                            state._fsp--;
                            if (state.failed) return action;
                            if ( state.backtracking==0 ) {
                              left.add(fname); right.add(obj1);
                            }
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1139:5: ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )*
                            loop108:
                            do {
                                int alt108=2;
                                int LA108_0 = input.LA(1);

                                if ( (LA108_0==COMMA) ) {
                                    alt108=1;
                                }


                                switch (alt108) {
                            	case 1 :
                            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1139:6: COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_actionCreate6346); if (state.failed) return action;
                            	    pushFollow(FOLLOW_stringExpression_in_actionCreate6352);
                            	    fname=stringExpression();

                            	    state._fsp--;
                            	    if (state.failed) return action;
                            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionCreate6354); if (state.failed) return action;
                            	    pushFollow(FOLLOW_argument_in_actionCreate6360);
                            	    obj1=argument();

                            	    state._fsp--;
                            	    if (state.failed) return action;
                            	    if ( state.backtracking==0 ) {
                            	      left.add(fname);right.add(obj1);
                            	    }

                            	    }
                            	    break;

                            	default :
                            	    break loop108;
                                }
                            } while (true);


                            }
                            break;

                    }


                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              action = ActionFactory.createStructureAction(name, structure, indexes, left, right);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_actionCreate6391); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return action;
    }
    // $ANTLR end "actionCreate"


    // $ANTLR start "actionMarkTable"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1147:1: actionMarkTable returns [TextMarkerAction action = null] : name= MARKTABLE LPAREN structure= typeExpression COMMA index= numberExpression COMMA table= wordTableExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )* )? RPAREN ;
    public final TextMarkerAction actionMarkTable() throws RecognitionException {
        TextMarkerAction action =  null;

        Token name=null;
        Expression structure = null;

        Expression index = null;

        Expression table = null;

        Expression fname = null;

        Expression obj1 = null;



            List left = new ArrayList();
            List right = new ArrayList();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1152:5: (name= MARKTABLE LPAREN structure= typeExpression COMMA index= numberExpression COMMA table= wordTableExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )* )? RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1153:5: name= MARKTABLE LPAREN structure= typeExpression COMMA index= numberExpression COMMA table= wordTableExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )* )? RPAREN
            {
            name=(Token)match(input,MARKTABLE,FOLLOW_MARKTABLE_in_actionMarkTable6426); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkTable6428); if (state.failed) return action;
            pushFollow(FOLLOW_typeExpression_in_actionMarkTable6439);
            structure=typeExpression();

            state._fsp--;
            if (state.failed) return action;
            match(input,COMMA,FOLLOW_COMMA_in_actionMarkTable6441); if (state.failed) return action;
            pushFollow(FOLLOW_numberExpression_in_actionMarkTable6452);
            index=numberExpression();

            state._fsp--;
            if (state.failed) return action;
            match(input,COMMA,FOLLOW_COMMA_in_actionMarkTable6454); if (state.failed) return action;
            pushFollow(FOLLOW_wordTableExpression_in_actionMarkTable6464);
            table=wordTableExpression();

            state._fsp--;
            if (state.failed) return action;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1157:5: ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )* )?
            int alt112=2;
            int LA112_0 = input.LA(1);

            if ( (LA112_0==COMMA) ) {
                alt112=1;
            }
            switch (alt112) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1157:6: COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )*
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionMarkTable6472); if (state.failed) return action;
                    pushFollow(FOLLOW_stringExpression_in_actionMarkTable6483);
                    fname=stringExpression();

                    state._fsp--;
                    if (state.failed) return action;
                    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionMarkTable6485); if (state.failed) return action;
                    pushFollow(FOLLOW_numberExpression_in_actionMarkTable6491);
                    obj1=numberExpression();

                    state._fsp--;
                    if (state.failed) return action;
                    if ( state.backtracking==0 ) {
                      left.add(fname); right.add(obj1);
                    }
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1159:5: ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )*
                    loop111:
                    do {
                        int alt111=2;
                        int LA111_0 = input.LA(1);

                        if ( (LA111_0==COMMA) ) {
                            alt111=1;
                        }


                        switch (alt111) {
                    	case 1 :
                    	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1159:6: COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionMarkTable6501); if (state.failed) return action;
                    	    pushFollow(FOLLOW_stringExpression_in_actionMarkTable6507);
                    	    fname=stringExpression();

                    	    state._fsp--;
                    	    if (state.failed) return action;
                    	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionMarkTable6509); if (state.failed) return action;
                    	    pushFollow(FOLLOW_numberExpression_in_actionMarkTable6515);
                    	    obj1=numberExpression();

                    	    state._fsp--;
                    	    if (state.failed) return action;
                    	    if ( state.backtracking==0 ) {
                    	      left.add(fname);right.add(obj1);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop111;
                        }
                    } while (true);


                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              action = ActionFactory.createStructureAction(name, structure, index, table, left, right);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkTable6539); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return action;
    }
    // $ANTLR end "actionMarkTable"


    // $ANTLR start "actionGather"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1165:1: actionGather returns [TextMarkerAction action = null] : name= GATHER LPAREN structure= typeExpression ( COMMA (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression | obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression | obj2= numberListExpression ) )* )? )? RPAREN ;
    public final TextMarkerAction actionGather() throws RecognitionException {
        TextMarkerAction action =  null;

        Token name=null;
        Expression structure = null;

        Expression index = null;

        Expression fname = null;

        Expression obj1 = null;

        Expression obj2 = null;



            List left = new ArrayList();
            List right = new ArrayList();
            List indexes = new ArrayList();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1171:5: (name= GATHER LPAREN structure= typeExpression ( COMMA (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression | obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression | obj2= numberListExpression ) )* )? )? RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1172:5: name= GATHER LPAREN structure= typeExpression ( COMMA (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression | obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression | obj2= numberListExpression ) )* )? )? RPAREN
            {
            name=(Token)match(input,GATHER,FOLLOW_GATHER_in_actionGather6573); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionGather6575); if (state.failed) return action;
            pushFollow(FOLLOW_typeExpression_in_actionGather6581);
            structure=typeExpression();

            state._fsp--;
            if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createStructureAction(name, structure, indexes, left, right);
            }
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1174:5: ( COMMA (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression | obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression | obj2= numberListExpression ) )* )? )?
            int alt119=2;
            int LA119_0 = input.LA(1);

            if ( (LA119_0==COMMA) ) {
                alt119=1;
            }
            switch (alt119) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1174:6: COMMA (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression | obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression | obj2= numberListExpression ) )* )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionGather6595); if (state.failed) return action;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1175:5: (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )?
                    int alt114=2;
                    alt114 = dfa114.predict(input);
                    switch (alt114) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1175:6: index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA
                            {
                            pushFollow(FOLLOW_numberExpression_in_actionGather6607);
                            index=numberExpression();

                            state._fsp--;
                            if (state.failed) return action;
                            if ( state.backtracking==0 ) {
                              indexes.add(index);
                            }
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1175:53: ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )*
                            loop113:
                            do {
                                int alt113=2;
                                int LA113_0 = input.LA(1);

                                if ( (LA113_0==COMMA) ) {
                                    int LA113_1 = input.LA(2);

                                    if ( (synpred15_TextMarkerParser()) ) {
                                        alt113=1;
                                    }


                                }


                                switch (alt113) {
                            	case 1 :
                            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1175:54: ( COMMA index= numberExpression )=> ( COMMA index= numberExpression )
                            	    {
                            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1175:88: ( COMMA index= numberExpression )
                            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1175:89: COMMA index= numberExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_actionGather6623); if (state.failed) return action;
                            	    pushFollow(FOLLOW_numberExpression_in_actionGather6629);
                            	    index=numberExpression();

                            	    state._fsp--;
                            	    if (state.failed) return action;

                            	    }

                            	    if ( state.backtracking==0 ) {
                            	      indexes.add(index);
                            	    }

                            	    }
                            	    break;

                            	default :
                            	    break loop113;
                                }
                            } while (true);

                            match(input,COMMA,FOLLOW_COMMA_in_actionGather6636); if (state.failed) return action;

                            }
                            break;

                    }

                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1176:5: (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression | obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression | obj2= numberListExpression ) )* )?
                    int alt118=2;
                    int LA118_0 = input.LA(1);

                    if ( (LA118_0==REMOVESTRING||LA118_0==StringLiteral||LA118_0==Identifier) ) {
                        alt118=1;
                    }
                    switch (alt118) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1176:6: fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression | obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression | obj2= numberListExpression ) )*
                            {
                            pushFollow(FOLLOW_stringExpression_in_actionGather6649);
                            fname=stringExpression();

                            state._fsp--;
                            if (state.failed) return action;
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionGather6651); if (state.failed) return action;
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1176:44: (obj1= numberExpression | obj2= numberListExpression )
                            int alt115=2;
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
                                alt115=1;
                                }
                                break;
                            case Identifier:
                                {
                                int LA115_2 = input.LA(2);

                                if ( (((isVariableOfType(input.LT(1).getText(), "INT"))||(isVariableOfType(input.LT(1).getText(), "DOUBLE"))||(isVariableOfType(input.LT(1).getText(), "NUMBERFUNCTION")))) ) {
                                    alt115=1;
                                }
                                else if ( (((isVariableOfType(input.LT(1).getText(), "INTLIST"))||(isVariableOfType(input.LT(1).getText(), "DOUBLELIST")))) ) {
                                    alt115=2;
                                }
                                else {
                                    if (state.backtracking>0) {state.failed=true; return action;}
                                    NoViableAltException nvae =
                                        new NoViableAltException("", 115, 2, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case LCURLY:
                                {
                                alt115=2;
                                }
                                break;
                            default:
                                if (state.backtracking>0) {state.failed=true; return action;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 115, 0, input);

                                throw nvae;
                            }

                            switch (alt115) {
                                case 1 :
                                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1176:45: obj1= numberExpression
                                    {
                                    pushFollow(FOLLOW_numberExpression_in_actionGather6658);
                                    obj1=numberExpression();

                                    state._fsp--;
                                    if (state.failed) return action;

                                    }
                                    break;
                                case 2 :
                                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1176:71: obj2= numberListExpression
                                    {
                                    pushFollow(FOLLOW_numberListExpression_in_actionGather6666);
                                    obj2=numberListExpression();

                                    state._fsp--;
                                    if (state.failed) return action;

                                    }
                                    break;

                            }

                            if ( state.backtracking==0 ) {
                              left.add(fname); right.add(obj1 != null? obj1 : obj2);
                            }
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1177:5: ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression | obj2= numberListExpression ) )*
                            loop117:
                            do {
                                int alt117=2;
                                int LA117_0 = input.LA(1);

                                if ( (LA117_0==COMMA) ) {
                                    alt117=1;
                                }


                                switch (alt117) {
                            	case 1 :
                            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1177:6: COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression | obj2= numberListExpression )
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_actionGather6677); if (state.failed) return action;
                            	    pushFollow(FOLLOW_stringExpression_in_actionGather6683);
                            	    fname=stringExpression();

                            	    state._fsp--;
                            	    if (state.failed) return action;
                            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionGather6685); if (state.failed) return action;
                            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1177:50: (obj1= numberExpression | obj2= numberListExpression )
                            	    int alt116=2;
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
                            	        alt116=1;
                            	        }
                            	        break;
                            	    case Identifier:
                            	        {
                            	        int LA116_2 = input.LA(2);

                            	        if ( (((isVariableOfType(input.LT(1).getText(), "INT"))||(isVariableOfType(input.LT(1).getText(), "DOUBLE"))||(isVariableOfType(input.LT(1).getText(), "NUMBERFUNCTION")))) ) {
                            	            alt116=1;
                            	        }
                            	        else if ( (((isVariableOfType(input.LT(1).getText(), "INTLIST"))||(isVariableOfType(input.LT(1).getText(), "DOUBLELIST")))) ) {
                            	            alt116=2;
                            	        }
                            	        else {
                            	            if (state.backtracking>0) {state.failed=true; return action;}
                            	            NoViableAltException nvae =
                            	                new NoViableAltException("", 116, 2, input);

                            	            throw nvae;
                            	        }
                            	        }
                            	        break;
                            	    case LCURLY:
                            	        {
                            	        alt116=2;
                            	        }
                            	        break;
                            	    default:
                            	        if (state.backtracking>0) {state.failed=true; return action;}
                            	        NoViableAltException nvae =
                            	            new NoViableAltException("", 116, 0, input);

                            	        throw nvae;
                            	    }

                            	    switch (alt116) {
                            	        case 1 :
                            	            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1177:51: obj1= numberExpression
                            	            {
                            	            pushFollow(FOLLOW_numberExpression_in_actionGather6692);
                            	            obj1=numberExpression();

                            	            state._fsp--;
                            	            if (state.failed) return action;

                            	            }
                            	            break;
                            	        case 2 :
                            	            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1177:77: obj2= numberListExpression
                            	            {
                            	            pushFollow(FOLLOW_numberListExpression_in_actionGather6700);
                            	            obj2=numberListExpression();

                            	            state._fsp--;
                            	            if (state.failed) return action;

                            	            }
                            	            break;

                            	    }

                            	    if ( state.backtracking==0 ) {
                            	      left.add(fname);right.add(obj1 != null? obj1 : obj2);
                            	    }

                            	    }
                            	    break;

                            	default :
                            	    break loop117;
                                }
                            } while (true);


                            }
                            break;

                    }


                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              action = ActionFactory.createStructureAction(name, structure, indexes, left, right);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_actionGather6732); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return action;
    }
    // $ANTLR end "actionGather"


    // $ANTLR start "actionFill"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1185:1: actionFill returns [TextMarkerAction action = null] : name= FILL LPAREN structure= typeExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )+ RPAREN ;
    public final TextMarkerAction actionFill() throws RecognitionException {
        TextMarkerAction action =  null;

        Token name=null;
        Expression structure = null;

        Expression fname = null;

        Expression obj1 = null;



            List left = new ArrayList();
            List right = new ArrayList();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1190:5: (name= FILL LPAREN structure= typeExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )+ RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1191:5: name= FILL LPAREN structure= typeExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )+ RPAREN
            {
            name=(Token)match(input,FILL,FOLLOW_FILL_in_actionFill6767); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionFill6769); if (state.failed) return action;
            pushFollow(FOLLOW_typeExpression_in_actionFill6775);
            structure=typeExpression();

            state._fsp--;
            if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createStructureAction(name, structure, null, left, right);
            }
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1193:5: ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )+
            int cnt120=0;
            loop120:
            do {
                int alt120=2;
                int LA120_0 = input.LA(1);

                if ( (LA120_0==COMMA) ) {
                    alt120=1;
                }


                switch (alt120) {
            	case 1 :
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1194:5: COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionFill6793); if (state.failed) return action;
            	    pushFollow(FOLLOW_stringExpression_in_actionFill6799);
            	    fname=stringExpression();

            	    state._fsp--;
            	    if (state.failed) return action;
            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionFill6801); if (state.failed) return action;
            	    pushFollow(FOLLOW_argument_in_actionFill6811);
            	    obj1=argument();

            	    state._fsp--;
            	    if (state.failed) return action;
            	    if ( state.backtracking==0 ) {
            	      left.add(fname); right.add(obj1);
            	    }

            	    }
            	    break;

            	default :
            	    if ( cnt120 >= 1 ) break loop120;
            	    if (state.backtracking>0) {state.failed=true; return action;}
                        EarlyExitException eee =
                            new EarlyExitException(120, input);
                        throw eee;
                }
                cnt120++;
            } while (true);

            if ( state.backtracking==0 ) {
              action = ActionFactory.createStructureAction(name, structure, null, left, right);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_actionFill6833); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return action;
    }
    // $ANTLR end "actionFill"


    // $ANTLR start "actionColor"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1202:1: actionColor returns [TextMarkerAction action = null] : name= COLOR LPAREN type= typeExpression COMMA bgcolor= stringExpression ( COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )? )? RPAREN ;
    public final TextMarkerAction actionColor() throws RecognitionException {
        TextMarkerAction action =  null;

        Token name=null;
        Expression type = null;

        Expression bgcolor = null;

        Expression fgcolor = null;

        Expression selected = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1203:5: (name= COLOR LPAREN type= typeExpression COMMA bgcolor= stringExpression ( COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )? )? RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1204:5: name= COLOR LPAREN type= typeExpression COMMA bgcolor= stringExpression ( COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )? )? RPAREN
            {
            name=(Token)match(input,COLOR,FOLLOW_COLOR_in_actionColor6870); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionColor6872); if (state.failed) return action;
            pushFollow(FOLLOW_typeExpression_in_actionColor6878);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createAction(name, type, bgcolor, fgcolor, selected);
            }
            match(input,COMMA,FOLLOW_COMMA_in_actionColor6892); if (state.failed) return action;
            pushFollow(FOLLOW_stringExpression_in_actionColor6903);
            bgcolor=stringExpression();

            state._fsp--;
            if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createAction(name, type, bgcolor, fgcolor, selected);
            }
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1209:5: ( COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )? )?
            int alt122=2;
            int LA122_0 = input.LA(1);

            if ( (LA122_0==COMMA) ) {
                alt122=1;
            }
            switch (alt122) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1209:6: COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionColor6917); if (state.failed) return action;
                    pushFollow(FOLLOW_stringExpression_in_actionColor6927);
                    fgcolor=stringExpression();

                    state._fsp--;
                    if (state.failed) return action;
                    if ( state.backtracking==0 ) {
                      action = ActionFactory.createAction(name, type, bgcolor, fgcolor, selected);
                    }
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1212:5: ( COMMA selected= booleanExpression )?
                    int alt121=2;
                    int LA121_0 = input.LA(1);

                    if ( (LA121_0==COMMA) ) {
                        alt121=1;
                    }
                    switch (alt121) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1212:6: COMMA selected= booleanExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_actionColor6941); if (state.failed) return action;
                            pushFollow(FOLLOW_booleanExpression_in_actionColor6951);
                            selected=booleanExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            }
                            break;

                    }


                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              action = ActionFactory.createAction(name, type, bgcolor, fgcolor, selected);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_actionColor6967); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return action;
    }
    // $ANTLR end "actionColor"


    // $ANTLR start "actionDel"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1218:1: actionDel returns [TextMarkerAction action = null] : name= DEL ;
    public final TextMarkerAction actionDel() throws RecognitionException {
        TextMarkerAction action =  null;

        Token name=null;

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1219:5: (name= DEL )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1220:5: name= DEL
            {
            name=(Token)match(input,DEL,FOLLOW_DEL_in_actionDel6999); if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createAction(name, new ArrayList());
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
        }
        return action;
    }
    // $ANTLR end "actionDel"


    // $ANTLR start "actionLog"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1224:1: actionLog returns [TextMarkerAction action = null] : name= LOG LPAREN lit= stringExpression ( COMMA log= LogLevel )? RPAREN ;
    public final TextMarkerAction actionLog() throws RecognitionException {
        TextMarkerAction action =  null;

        Token name=null;
        Token log=null;
        Expression lit = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1225:5: (name= LOG LPAREN lit= stringExpression ( COMMA log= LogLevel )? RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1226:5: name= LOG LPAREN lit= stringExpression ( COMMA log= LogLevel )? RPAREN
            {
            name=(Token)match(input,LOG,FOLLOW_LOG_in_actionLog7045); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionLog7047); if (state.failed) return action;
            pushFollow(FOLLOW_stringExpression_in_actionLog7053);
            lit=stringExpression();

            state._fsp--;
            if (state.failed) return action;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1226:46: ( COMMA log= LogLevel )?
            int alt123=2;
            int LA123_0 = input.LA(1);

            if ( (LA123_0==COMMA) ) {
                alt123=1;
            }
            switch (alt123) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1226:47: COMMA log= LogLevel
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionLog7056); if (state.failed) return action;
                    log=(Token)match(input,LogLevel,FOLLOW_LogLevel_in_actionLog7062); if (state.failed) return action;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              action = ActionFactory.createLogAction(name, lit, log);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_actionLog7078); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return action;
    }
    // $ANTLR end "actionLog"


    // $ANTLR start "actionMark"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1231:1: actionMark returns [TextMarkerAction action = null] : name= MARK LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN ;
    public final TextMarkerAction actionMark() throws RecognitionException {
        TextMarkerAction action =  null;

        Token name=null;
        Expression type = null;

        Expression index = null;



        List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1235:5: (name= MARK LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1236:5: name= MARK LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN
            {
            name=(Token)match(input,MARK,FOLLOW_MARK_in_actionMark7116); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionMark7118); if (state.failed) return action;
            pushFollow(FOLLOW_typeExpression_in_actionMark7129);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;
            if ( state.backtracking==0 ) {
              list.add(type);
            }
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1239:5: ( COMMA (index= numberExpression )=>index= numberExpression )*
            loop124:
            do {
                int alt124=2;
                int LA124_0 = input.LA(1);

                if ( (LA124_0==COMMA) ) {
                    alt124=1;
                }


                switch (alt124) {
            	case 1 :
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1240:5: COMMA (index= numberExpression )=>index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMark7147); if (state.failed) return action;
            	    pushFollow(FOLLOW_numberExpression_in_actionMark7163);
            	    index=numberExpression();

            	    state._fsp--;
            	    if (state.failed) return action;
            	    if ( state.backtracking==0 ) {
            	      list.add(index);
            	    }

            	    }
            	    break;

            	default :
            	    break loop124;
                }
            } while (true);

            if ( state.backtracking==0 ) {
              action = ActionFactory.createAction(name, list);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_actionMark7185); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return action;
    }
    // $ANTLR end "actionMark"


    // $ANTLR start "actionExpand"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1246:1: actionExpand returns [TextMarkerAction action = null] : name= EXPAND LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN ;
    public final TextMarkerAction actionExpand() throws RecognitionException {
        TextMarkerAction action =  null;

        Token name=null;
        Expression type = null;

        Expression index = null;



        List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1250:5: (name= EXPAND LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1251:5: name= EXPAND LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN
            {
            name=(Token)match(input,EXPAND,FOLLOW_EXPAND_in_actionExpand7222); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionExpand7224); if (state.failed) return action;
            pushFollow(FOLLOW_typeExpression_in_actionExpand7235);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;
            if ( state.backtracking==0 ) {
              list.add(type);
            }
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1254:5: ( COMMA (index= numberExpression )=>index= numberExpression )*
            loop125:
            do {
                int alt125=2;
                int LA125_0 = input.LA(1);

                if ( (LA125_0==COMMA) ) {
                    alt125=1;
                }


                switch (alt125) {
            	case 1 :
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1255:5: COMMA (index= numberExpression )=>index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionExpand7253); if (state.failed) return action;
            	    pushFollow(FOLLOW_numberExpression_in_actionExpand7269);
            	    index=numberExpression();

            	    state._fsp--;
            	    if (state.failed) return action;
            	    if ( state.backtracking==0 ) {
            	      list.add(index);
            	    }

            	    }
            	    break;

            	default :
            	    break loop125;
                }
            } while (true);

            if ( state.backtracking==0 ) {
              action = ActionFactory.createAction(name, list);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_actionExpand7291); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return action;
    }
    // $ANTLR end "actionExpand"


    // $ANTLR start "actionMarkScore"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1261:1: actionMarkScore returns [TextMarkerAction action = null] : name= MARKSCORE LPAREN score= numberExpression COMMA type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN ;
    public final TextMarkerAction actionMarkScore() throws RecognitionException {
        TextMarkerAction action =  null;

        Token name=null;
        Expression score = null;

        Expression type = null;

        Expression index = null;



        List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1265:5: (name= MARKSCORE LPAREN score= numberExpression COMMA type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1266:5: name= MARKSCORE LPAREN score= numberExpression COMMA type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN
            {
            name=(Token)match(input,MARKSCORE,FOLLOW_MARKSCORE_in_actionMarkScore7328); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkScore7330); if (state.failed) return action;
            pushFollow(FOLLOW_numberExpression_in_actionMarkScore7336);
            score=numberExpression();

            state._fsp--;
            if (state.failed) return action;
            match(input,COMMA,FOLLOW_COMMA_in_actionMarkScore7338); if (state.failed) return action;
            pushFollow(FOLLOW_typeExpression_in_actionMarkScore7344);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;
            if ( state.backtracking==0 ) {
              list.add(score); list.add(type);
            }
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1268:5: ( COMMA (index= numberExpression )=>index= numberExpression )*
            loop126:
            do {
                int alt126=2;
                int LA126_0 = input.LA(1);

                if ( (LA126_0==COMMA) ) {
                    alt126=1;
                }


                switch (alt126) {
            	case 1 :
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1269:5: COMMA (index= numberExpression )=>index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMarkScore7362); if (state.failed) return action;
            	    pushFollow(FOLLOW_numberExpression_in_actionMarkScore7378);
            	    index=numberExpression();

            	    state._fsp--;
            	    if (state.failed) return action;
            	    if ( state.backtracking==0 ) {
            	      list.add(index);
            	    }

            	    }
            	    break;

            	default :
            	    break loop126;
                }
            } while (true);

            if ( state.backtracking==0 ) {
              action = ActionFactory.createAction(name, list);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkScore7400); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return action;
    }
    // $ANTLR end "actionMarkScore"


    // $ANTLR start "actionMarkOnce"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1275:1: actionMarkOnce returns [TextMarkerAction action = null] : name= MARKONCE LPAREN ( (score= numberExpression )=>score= numberExpression COMMA )? (type= typeExpression )=>type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN ;
    public final TextMarkerAction actionMarkOnce() throws RecognitionException {
        TextMarkerAction action =  null;

        Token name=null;
        Expression score = null;

        Expression type = null;

        Expression index = null;



        List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1279:5: (name= MARKONCE LPAREN ( (score= numberExpression )=>score= numberExpression COMMA )? (type= typeExpression )=>type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1280:5: name= MARKONCE LPAREN ( (score= numberExpression )=>score= numberExpression COMMA )? (type= typeExpression )=>type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN
            {
            name=(Token)match(input,MARKONCE,FOLLOW_MARKONCE_in_actionMarkOnce7437); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkOnce7439); if (state.failed) return action;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1280:28: ( (score= numberExpression )=>score= numberExpression COMMA )?
            int alt127=2;
            int LA127_0 = input.LA(1);

            if ( (LA127_0==MINUS) && (synpred19_TextMarkerParser())) {
                alt127=1;
            }
            else if ( (LA127_0==Identifier) ) {
                int LA127_2 = input.LA(2);

                if ( (((synpred19_TextMarkerParser()&&(isVariableOfType(input.LT(1).getText(), "DOUBLE")))||(synpred19_TextMarkerParser()&&(isVariableOfType(input.LT(1).getText(), "NUMBERFUNCTION")))||(synpred19_TextMarkerParser()&&(isVariableOfType(input.LT(1).getText(), "INT"))))) ) {
                    alt127=1;
                }
            }
            else if ( (LA127_0==DecimalLiteral) && (synpred19_TextMarkerParser())) {
                alt127=1;
            }
            else if ( (LA127_0==FloatingPointLiteral) && (synpred19_TextMarkerParser())) {
                alt127=1;
            }
            else if ( (LA127_0==LPAREN) && (synpred19_TextMarkerParser())) {
                alt127=1;
            }
            else if ( ((LA127_0>=EXP && LA127_0<=TAN)) && (synpred19_TextMarkerParser())) {
                alt127=1;
            }
            switch (alt127) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1280:29: (score= numberExpression )=>score= numberExpression COMMA
                    {
                    pushFollow(FOLLOW_numberExpression_in_actionMarkOnce7456);
                    score=numberExpression();

                    state._fsp--;
                    if (state.failed) return action;
                    match(input,COMMA,FOLLOW_COMMA_in_actionMarkOnce7458); if (state.failed) return action;

                    }
                    break;

            }

            pushFollow(FOLLOW_typeExpression_in_actionMarkOnce7476);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;
            if ( state.backtracking==0 ) {
              list.add(score); list.add(type);
            }
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1282:5: ( COMMA (index= numberExpression )=>index= numberExpression )*
            loop128:
            do {
                int alt128=2;
                int LA128_0 = input.LA(1);

                if ( (LA128_0==COMMA) ) {
                    alt128=1;
                }


                switch (alt128) {
            	case 1 :
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1283:5: COMMA (index= numberExpression )=>index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMarkOnce7494); if (state.failed) return action;
            	    pushFollow(FOLLOW_numberExpression_in_actionMarkOnce7510);
            	    index=numberExpression();

            	    state._fsp--;
            	    if (state.failed) return action;
            	    if ( state.backtracking==0 ) {
            	      list.add(index);
            	    }

            	    }
            	    break;

            	default :
            	    break loop128;
                }
            } while (true);

            if ( state.backtracking==0 ) {
              action = ActionFactory.createAction(name, list);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkOnce7532); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return action;
    }
    // $ANTLR end "actionMarkOnce"


    // $ANTLR start "actionMarkFast"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1289:1: actionMarkFast returns [TextMarkerAction action = null] : name= MARKFAST LPAREN type= typeExpression COMMA list= wordListExpression ( COMMA ignore= booleanExpression ( COMMA numExpr= numberExpression )? )? RPAREN ;
    public final TextMarkerAction actionMarkFast() throws RecognitionException {
        TextMarkerAction action =  null;

        Token name=null;
        Expression type = null;

        Expression list = null;

        Expression ignore = null;

        Expression numExpr = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1290:5: (name= MARKFAST LPAREN type= typeExpression COMMA list= wordListExpression ( COMMA ignore= booleanExpression ( COMMA numExpr= numberExpression )? )? RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1291:5: name= MARKFAST LPAREN type= typeExpression COMMA list= wordListExpression ( COMMA ignore= booleanExpression ( COMMA numExpr= numberExpression )? )? RPAREN
            {
            name=(Token)match(input,MARKFAST,FOLLOW_MARKFAST_in_actionMarkFast7564); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkFast7566); if (state.failed) return action;
            pushFollow(FOLLOW_typeExpression_in_actionMarkFast7572);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createAction(name, type, list, ignore, numExpr);
            }
            match(input,COMMA,FOLLOW_COMMA_in_actionMarkFast7585); if (state.failed) return action;
            pushFollow(FOLLOW_wordListExpression_in_actionMarkFast7591);
            list=wordListExpression();

            state._fsp--;
            if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createAction(name, type, list, ignore, numExpr);
            }
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1295:5: ( COMMA ignore= booleanExpression ( COMMA numExpr= numberExpression )? )?
            int alt130=2;
            int LA130_0 = input.LA(1);

            if ( (LA130_0==COMMA) ) {
                alt130=1;
            }
            switch (alt130) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1295:6: COMMA ignore= booleanExpression ( COMMA numExpr= numberExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionMarkFast7605); if (state.failed) return action;
                    pushFollow(FOLLOW_booleanExpression_in_actionMarkFast7611);
                    ignore=booleanExpression();

                    state._fsp--;
                    if (state.failed) return action;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1295:39: ( COMMA numExpr= numberExpression )?
                    int alt129=2;
                    int LA129_0 = input.LA(1);

                    if ( (LA129_0==COMMA) ) {
                        alt129=1;
                    }
                    switch (alt129) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1295:40: COMMA numExpr= numberExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_actionMarkFast7614); if (state.failed) return action;
                            pushFollow(FOLLOW_numberExpression_in_actionMarkFast7620);
                            numExpr=numberExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            }
                            break;

                    }


                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              action = ActionFactory.createAction(name, type, list, ignore, numExpr);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkFast7638); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return action;
    }
    // $ANTLR end "actionMarkFast"


    // $ANTLR start "actionMarkLast"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1300:1: actionMarkLast returns [TextMarkerAction action = null] : name= MARKLAST LPAREN type= typeExpression RPAREN ;
    public final TextMarkerAction actionMarkLast() throws RecognitionException {
        TextMarkerAction action =  null;

        Token name=null;
        Expression type = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1301:5: (name= MARKLAST LPAREN type= typeExpression RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1302:5: name= MARKLAST LPAREN type= typeExpression RPAREN
            {
            name=(Token)match(input,MARKLAST,FOLLOW_MARKLAST_in_actionMarkLast7670); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkLast7672); if (state.failed) return action;
            pushFollow(FOLLOW_typeExpression_in_actionMarkLast7678);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createAction(name, type);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkLast7691); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return action;
    }
    // $ANTLR end "actionMarkLast"


    // $ANTLR start "actionReplace"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1308:1: actionReplace returns [TextMarkerAction action = null] : name= REPLACE LPAREN lit= stringExpression RPAREN ;
    public final TextMarkerAction actionReplace() throws RecognitionException {
        TextMarkerAction action =  null;

        Token name=null;
        Expression lit = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1309:5: (name= REPLACE LPAREN lit= stringExpression RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1310:5: name= REPLACE LPAREN lit= stringExpression RPAREN
            {
            name=(Token)match(input,REPLACE,FOLLOW_REPLACE_in_actionReplace7724); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionReplace7726); if (state.failed) return action;
            pushFollow(FOLLOW_stringExpression_in_actionReplace7732);
            lit=stringExpression();

            state._fsp--;
            if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createAction(name, lit);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_actionReplace7745); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return action;
    }
    // $ANTLR end "actionReplace"


    // $ANTLR start "actionRetainMarkup"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1315:1: actionRetainMarkup returns [TextMarkerAction action = null] : name= RETAINMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )? ;
    public final TextMarkerAction actionRetainMarkup() throws RecognitionException {
        TextMarkerAction action =  null;

        Token name=null;
        Expression id = null;



        List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1319:5: (name= RETAINMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )? )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1320:5: name= RETAINMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )?
            {
            name=(Token)match(input,RETAINMARKUP,FOLLOW_RETAINMARKUP_in_actionRetainMarkup7782); if (state.failed) return action;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1320:25: ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )?
            int alt132=2;
            int LA132_0 = input.LA(1);

            if ( (LA132_0==LPAREN) ) {
                alt132=1;
            }
            switch (alt132) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1320:26: LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_actionRetainMarkup7785); if (state.failed) return action;
                    pushFollow(FOLLOW_stringExpression_in_actionRetainMarkup7791);
                    id=stringExpression();

                    state._fsp--;
                    if (state.failed) return action;
                    if ( state.backtracking==0 ) {
                      list.add(id);
                    }
                    if ( state.backtracking==0 ) {
                      action = ActionFactory.createAction(name, list);
                    }
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1322:5: ( COMMA id= stringExpression )*
                    loop131:
                    do {
                        int alt131=2;
                        int LA131_0 = input.LA(1);

                        if ( (LA131_0==COMMA) ) {
                            alt131=1;
                        }


                        switch (alt131) {
                    	case 1 :
                    	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1322:6: COMMA id= stringExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionRetainMarkup7807); if (state.failed) return action;
                    	    pushFollow(FOLLOW_stringExpression_in_actionRetainMarkup7813);
                    	    id=stringExpression();

                    	    state._fsp--;
                    	    if (state.failed) return action;
                    	    if ( state.backtracking==0 ) {
                    	      list.add(id);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop131;
                        }
                    } while (true);

                    if ( state.backtracking==0 ) {
                      action = ActionFactory.createAction(name, list);
                    }
                    match(input,RPAREN,FOLLOW_RPAREN_in_actionRetainMarkup7830); if (state.failed) return action;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              action = ActionFactory.createAction(name, list);
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
        }
        return action;
    }
    // $ANTLR end "actionRetainMarkup"


    // $ANTLR start "actionRetainType"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1328:1: actionRetainType returns [TextMarkerAction action = null] : name= RETAINTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )? ;
    public final TextMarkerAction actionRetainType() throws RecognitionException {
        TextMarkerAction action =  null;

        Token name=null;
        Expression id = null;



        List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1332:5: (name= RETAINTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )? )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1333:5: name= RETAINTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )?
            {
            name=(Token)match(input,RETAINTYPE,FOLLOW_RETAINTYPE_in_actionRetainType7883); if (state.failed) return action;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1333:23: ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )?
            int alt134=2;
            int LA134_0 = input.LA(1);

            if ( (LA134_0==LPAREN) ) {
                alt134=1;
            }
            switch (alt134) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1333:24: LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_actionRetainType7886); if (state.failed) return action;
                    pushFollow(FOLLOW_typeExpression_in_actionRetainType7892);
                    id=typeExpression();

                    state._fsp--;
                    if (state.failed) return action;
                    if ( state.backtracking==0 ) {
                      list.add(id);
                    }
                    if ( state.backtracking==0 ) {
                      action = ActionFactory.createAction(name, list);
                    }
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1335:5: ( COMMA id= typeExpression )*
                    loop133:
                    do {
                        int alt133=2;
                        int LA133_0 = input.LA(1);

                        if ( (LA133_0==COMMA) ) {
                            alt133=1;
                        }


                        switch (alt133) {
                    	case 1 :
                    	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1335:6: COMMA id= typeExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionRetainType7908); if (state.failed) return action;
                    	    pushFollow(FOLLOW_typeExpression_in_actionRetainType7914);
                    	    id=typeExpression();

                    	    state._fsp--;
                    	    if (state.failed) return action;
                    	    if ( state.backtracking==0 ) {
                    	      list.add(id);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop133;
                        }
                    } while (true);

                    if ( state.backtracking==0 ) {
                      action = ActionFactory.createAction(name, list);
                    }
                    match(input,RPAREN,FOLLOW_RPAREN_in_actionRetainType7931); if (state.failed) return action;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              action = ActionFactory.createAction(name, list);
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
        }
        return action;
    }
    // $ANTLR end "actionRetainType"


    // $ANTLR start "actionFilterMarkup"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1341:1: actionFilterMarkup returns [TextMarkerAction action = null] : name= FILTERMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )? ;
    public final TextMarkerAction actionFilterMarkup() throws RecognitionException {
        TextMarkerAction action =  null;

        Token name=null;
        Expression id = null;



        List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1345:5: (name= FILTERMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )? )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1346:5: name= FILTERMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )?
            {
            name=(Token)match(input,FILTERMARKUP,FOLLOW_FILTERMARKUP_in_actionFilterMarkup7980); if (state.failed) return action;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1346:25: ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )?
            int alt136=2;
            int LA136_0 = input.LA(1);

            if ( (LA136_0==LPAREN) ) {
                alt136=1;
            }
            switch (alt136) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1346:26: LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_actionFilterMarkup7983); if (state.failed) return action;
                    pushFollow(FOLLOW_stringExpression_in_actionFilterMarkup7989);
                    id=stringExpression();

                    state._fsp--;
                    if (state.failed) return action;
                    if ( state.backtracking==0 ) {
                      list.add(id);
                    }
                    if ( state.backtracking==0 ) {
                      action = ActionFactory.createAction(name, list);
                    }
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1348:5: ( COMMA id= stringExpression )*
                    loop135:
                    do {
                        int alt135=2;
                        int LA135_0 = input.LA(1);

                        if ( (LA135_0==COMMA) ) {
                            alt135=1;
                        }


                        switch (alt135) {
                    	case 1 :
                    	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1348:6: COMMA id= stringExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionFilterMarkup8005); if (state.failed) return action;
                    	    pushFollow(FOLLOW_stringExpression_in_actionFilterMarkup8011);
                    	    id=stringExpression();

                    	    state._fsp--;
                    	    if (state.failed) return action;
                    	    if ( state.backtracking==0 ) {
                    	      list.add(id);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop135;
                        }
                    } while (true);

                    if ( state.backtracking==0 ) {
                      action = ActionFactory.createAction(name, list);
                    }
                    match(input,RPAREN,FOLLOW_RPAREN_in_actionFilterMarkup8028); if (state.failed) return action;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              action = ActionFactory.createAction(name, list);
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
        }
        return action;
    }
    // $ANTLR end "actionFilterMarkup"


    // $ANTLR start "actionFilterType"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1354:1: actionFilterType returns [TextMarkerAction action = null] : name= FILTERTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )? ;
    public final TextMarkerAction actionFilterType() throws RecognitionException {
        TextMarkerAction action =  null;

        Token name=null;
        Expression id = null;



        List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1358:5: (name= FILTERTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )? )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1359:5: name= FILTERTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )?
            {
            name=(Token)match(input,FILTERTYPE,FOLLOW_FILTERTYPE_in_actionFilterType8073); if (state.failed) return action;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1359:23: ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )?
            int alt138=2;
            int LA138_0 = input.LA(1);

            if ( (LA138_0==LPAREN) ) {
                alt138=1;
            }
            switch (alt138) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1359:24: LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_actionFilterType8076); if (state.failed) return action;
                    pushFollow(FOLLOW_typeExpression_in_actionFilterType8082);
                    id=typeExpression();

                    state._fsp--;
                    if (state.failed) return action;
                    if ( state.backtracking==0 ) {
                      list.add(id);
                    }
                    if ( state.backtracking==0 ) {
                      action = ActionFactory.createAction(name, list);
                    }
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1361:5: ( COMMA id= typeExpression )*
                    loop137:
                    do {
                        int alt137=2;
                        int LA137_0 = input.LA(1);

                        if ( (LA137_0==COMMA) ) {
                            alt137=1;
                        }


                        switch (alt137) {
                    	case 1 :
                    	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1361:6: COMMA id= typeExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionFilterType8098); if (state.failed) return action;
                    	    pushFollow(FOLLOW_typeExpression_in_actionFilterType8104);
                    	    id=typeExpression();

                    	    state._fsp--;
                    	    if (state.failed) return action;
                    	    if ( state.backtracking==0 ) {
                    	      list.add(id);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop137;
                        }
                    } while (true);

                    if ( state.backtracking==0 ) {
                      action = ActionFactory.createAction(name, list);
                    }
                    match(input,RPAREN,FOLLOW_RPAREN_in_actionFilterType8121); if (state.failed) return action;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              action = ActionFactory.createAction(name, list);
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
        }
        return action;
    }
    // $ANTLR end "actionFilterType"


    // $ANTLR start "actionCall"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1367:1: actionCall returns [TextMarkerAction action = null] : name= CALL lp= LPAREN ns= dottedComponentReference RPAREN ;
    public final TextMarkerAction actionCall() throws RecognitionException {
        TextMarkerAction action =  null;

        Token name=null;
        Token lp=null;
        ComponentReference ns = null;



        String string = "";


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1372:5: (name= CALL lp= LPAREN ns= dottedComponentReference RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1373:5: name= CALL lp= LPAREN ns= dottedComponentReference RPAREN
            {
            name=(Token)match(input,CALL,FOLLOW_CALL_in_actionCall8170); if (state.failed) return action;
            lp=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_actionCall8176); if (state.failed) return action;
            if ( state.backtracking==0 ) {
                 action = ActionFactory.createCallAction(name, StatementFactory.createEmtpyComponentReference(lp));
            }
            pushFollow(FOLLOW_dottedComponentReference_in_actionCall8198);
            ns=dottedComponentReference();

            state._fsp--;
            if (state.failed) return action;
            if ( state.backtracking==0 ) {
                 if(ns != null) {action = ActionFactory.createCallAction(name, ns);}
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_actionCall8212); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return action;
    }
    // $ANTLR end "actionCall"


    // $ANTLR start "actionExec"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1381:1: actionExec returns [TextMarkerAction action = null] : name= EXEC lp= LPAREN ns= dottedComponentReference ( COMMA tl= typeListExpression )? RPAREN ;
    public final TextMarkerAction actionExec() throws RecognitionException {
        TextMarkerAction action =  null;

        Token name=null;
        Token lp=null;
        ComponentReference ns = null;

        Expression tl = null;



        String string = "";

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1385:5: (name= EXEC lp= LPAREN ns= dottedComponentReference ( COMMA tl= typeListExpression )? RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1386:5: name= EXEC lp= LPAREN ns= dottedComponentReference ( COMMA tl= typeListExpression )? RPAREN
            {
            name=(Token)match(input,EXEC,FOLLOW_EXEC_in_actionExec8246); if (state.failed) return action;
            lp=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_actionExec8252); if (state.failed) return action;
            if ( state.backtracking==0 ) {
                 action = ActionFactory.createCallAction(name, StatementFactory.createEmtpyComponentReference(lp));
            }
            pushFollow(FOLLOW_dottedComponentReference_in_actionExec8270);
            ns=dottedComponentReference();

            state._fsp--;
            if (state.failed) return action;
            if ( state.backtracking==0 ) {
                 if(ns != null) {action = ActionFactory.createCallAction(name, ns, null);}
            }
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1390:6: ( COMMA tl= typeListExpression )?
            int alt139=2;
            int LA139_0 = input.LA(1);

            if ( (LA139_0==COMMA) ) {
                alt139=1;
            }
            switch (alt139) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1390:7: COMMA tl= typeListExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionExec8286); if (state.failed) return action;
                    pushFollow(FOLLOW_typeListExpression_in_actionExec8292);
                    tl=typeListExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
                 if(ns != null) {action = ActionFactory.createCallAction(name, ns, tl);}
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_actionExec8308); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return action;
    }
    // $ANTLR end "actionExec"


    // $ANTLR start "actionAssign"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1396:1: actionAssign returns [TextMarkerAction action = null] : name= ASSIGN LPAREN (id= Identifier COMMA e= argument ) RPAREN ;
    public final TextMarkerAction actionAssign() throws RecognitionException {
        TextMarkerAction action =  null;

        Token name=null;
        Token id=null;
        Expression e = null;



            VariableReference ref = null;

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1400:5: (name= ASSIGN LPAREN (id= Identifier COMMA e= argument ) RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1401:5: name= ASSIGN LPAREN (id= Identifier COMMA e= argument ) RPAREN
            {
            name=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_actionAssign8350); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionAssign8352); if (state.failed) return action;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1402:5: (id= Identifier COMMA e= argument )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1402:6: id= Identifier COMMA e= argument
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_actionAssign8363); if (state.failed) return action;
            if ( state.backtracking==0 ) {

                  ref = ExpressionFactory.createGenericVariableReference(id);
                  action = ActionFactory.createAction(name, ref, e);
            }
            match(input,COMMA,FOLLOW_COMMA_in_actionAssign8381); if (state.failed) return action;
            pushFollow(FOLLOW_argument_in_actionAssign8387);
            e=argument();

            state._fsp--;
            if (state.failed) return action;

            }

            match(input,RPAREN,FOLLOW_RPAREN_in_actionAssign8395); if (state.failed) return action;
            if ( state.backtracking==0 ) {

                  ref = ExpressionFactory.createGenericVariableReference(id);
                  action = ActionFactory.createAction(name, ref, e);
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
        }
        return action;
    }
    // $ANTLR end "actionAssign"


    // $ANTLR start "actionSetFeature"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1415:1: actionSetFeature returns [TextMarkerAction action = null] : name= SETFEATURE LPAREN f= stringExpression COMMA v= argument RPAREN ;
    public final TextMarkerAction actionSetFeature() throws RecognitionException {
        TextMarkerAction action =  null;

        Token name=null;
        Expression f = null;

        Expression v = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1416:5: (name= SETFEATURE LPAREN f= stringExpression COMMA v= argument RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1417:5: name= SETFEATURE LPAREN f= stringExpression COMMA v= argument RPAREN
            {
            name=(Token)match(input,SETFEATURE,FOLLOW_SETFEATURE_in_actionSetFeature8432); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionSetFeature8434); if (state.failed) return action;
            pushFollow(FOLLOW_stringExpression_in_actionSetFeature8440);
            f=stringExpression();

            state._fsp--;
            if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createAction(name, f, v);
            }
            match(input,COMMA,FOLLOW_COMMA_in_actionSetFeature8454); if (state.failed) return action;
            pushFollow(FOLLOW_argument_in_actionSetFeature8460);
            v=argument();

            state._fsp--;
            if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createAction(name, f, v);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_actionSetFeature8473); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return action;
    }
    // $ANTLR end "actionSetFeature"


    // $ANTLR start "actionGetFeature"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1424:1: actionGetFeature returns [TextMarkerAction action = null] : name= GETFEATURE LPAREN f= stringExpression COMMA v= variable RPAREN ;
    public final TextMarkerAction actionGetFeature() throws RecognitionException {
        TextMarkerAction action =  null;

        Token name=null;
        Expression f = null;

        Expression v = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1425:5: (name= GETFEATURE LPAREN f= stringExpression COMMA v= variable RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1426:5: name= GETFEATURE LPAREN f= stringExpression COMMA v= variable RPAREN
            {
            name=(Token)match(input,GETFEATURE,FOLLOW_GETFEATURE_in_actionGetFeature8502); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionGetFeature8504); if (state.failed) return action;
            pushFollow(FOLLOW_stringExpression_in_actionGetFeature8510);
            f=stringExpression();

            state._fsp--;
            if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createAction(name, f, v);
            }
            match(input,COMMA,FOLLOW_COMMA_in_actionGetFeature8523); if (state.failed) return action;
            pushFollow(FOLLOW_variable_in_actionGetFeature8529);
            v=variable();

            state._fsp--;
            if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createAction(name, f, v);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_actionGetFeature8542); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return action;
    }
    // $ANTLR end "actionGetFeature"


    // $ANTLR start "actionUnmark"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1434:1: actionUnmark returns [TextMarkerAction action = null] : name= UNMARK LPAREN f= typeExpression RPAREN ;
    public final TextMarkerAction actionUnmark() throws RecognitionException {
        TextMarkerAction action =  null;

        Token name=null;
        Expression f = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1435:5: (name= UNMARK LPAREN f= typeExpression RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1436:5: name= UNMARK LPAREN f= typeExpression RPAREN
            {
            name=(Token)match(input,UNMARK,FOLLOW_UNMARK_in_actionUnmark8572); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionUnmark8574); if (state.failed) return action;
            pushFollow(FOLLOW_typeExpression_in_actionUnmark8580);
            f=typeExpression();

            state._fsp--;
            if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createAction(name, f);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_actionUnmark8593); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return action;
    }
    // $ANTLR end "actionUnmark"


    // $ANTLR start "actionUnmarkAll"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1441:1: actionUnmarkAll returns [TextMarkerAction action = null] : name= UNMARKALL LPAREN f= typeExpression ( COMMA list= typeListExpression )? RPAREN ;
    public final TextMarkerAction actionUnmarkAll() throws RecognitionException {
        TextMarkerAction action =  null;

        Token name=null;
        Expression f = null;

        Expression list = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1442:5: (name= UNMARKALL LPAREN f= typeExpression ( COMMA list= typeListExpression )? RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1443:5: name= UNMARKALL LPAREN f= typeExpression ( COMMA list= typeListExpression )? RPAREN
            {
            name=(Token)match(input,UNMARKALL,FOLLOW_UNMARKALL_in_actionUnmarkAll8622); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionUnmarkAll8624); if (state.failed) return action;
            pushFollow(FOLLOW_typeExpression_in_actionUnmarkAll8630);
            f=typeExpression();

            state._fsp--;
            if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createAction(name, f, list);
            }
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1445:5: ( COMMA list= typeListExpression )?
            int alt140=2;
            int LA140_0 = input.LA(1);

            if ( (LA140_0==COMMA) ) {
                alt140=1;
            }
            switch (alt140) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1445:6: COMMA list= typeListExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionUnmarkAll8644); if (state.failed) return action;
                    pushFollow(FOLLOW_typeListExpression_in_actionUnmarkAll8650);
                    list=typeListExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              action = ActionFactory.createAction(name, f, list);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_actionUnmarkAll8665); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return action;
    }
    // $ANTLR end "actionUnmarkAll"


    // $ANTLR start "actionTransfer"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1453:1: actionTransfer returns [TextMarkerAction action = null] : name= TRANSFER LPAREN f= typeExpression RPAREN ;
    public final TextMarkerAction actionTransfer() throws RecognitionException {
        TextMarkerAction action =  null;

        Token name=null;
        Expression f = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1454:5: (name= TRANSFER LPAREN f= typeExpression RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1455:5: name= TRANSFER LPAREN f= typeExpression RPAREN
            {
            name=(Token)match(input,TRANSFER,FOLLOW_TRANSFER_in_actionTransfer8697); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionTransfer8699); if (state.failed) return action;
            pushFollow(FOLLOW_typeExpression_in_actionTransfer8705);
            f=typeExpression();

            state._fsp--;
            if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createAction(name, f);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_actionTransfer8718); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return action;
    }
    // $ANTLR end "actionTransfer"


    // $ANTLR start "actionTrie"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1460:1: actionTrie returns [TextMarkerAction action = null] : name= TRIE LPAREN key= stringExpression ASSIGN_EQUAL value= typeExpression ( COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression )* COMMA list= wordListExpression COMMA ignoreCase= booleanExpression COMMA ignoreLength= numberExpression COMMA edit= booleanExpression COMMA distance= numberExpression COMMA ignoreChar= stringExpression RPAREN ;
    public final TextMarkerAction actionTrie() throws RecognitionException {
        TextMarkerAction action =  null;

        Token name=null;
        Expression key = null;

        Expression value = null;

        Expression list = null;

        Expression ignoreCase = null;

        Expression ignoreLength = null;

        Expression edit = null;

        Expression distance = null;

        Expression ignoreChar = null;



        Map<Expression, Expression> map = new HashMap<Expression, Expression>();
        List<Expression> left = new ArrayList<Expression>();
        List<Expression> right = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1466:5: (name= TRIE LPAREN key= stringExpression ASSIGN_EQUAL value= typeExpression ( COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression )* COMMA list= wordListExpression COMMA ignoreCase= booleanExpression COMMA ignoreLength= numberExpression COMMA edit= booleanExpression COMMA distance= numberExpression COMMA ignoreChar= stringExpression RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1467:5: name= TRIE LPAREN key= stringExpression ASSIGN_EQUAL value= typeExpression ( COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression )* COMMA list= wordListExpression COMMA ignoreCase= booleanExpression COMMA ignoreLength= numberExpression COMMA edit= booleanExpression COMMA distance= numberExpression COMMA ignoreChar= stringExpression RPAREN
            {
            name=(Token)match(input,TRIE,FOLLOW_TRIE_in_actionTrie8756); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionTrie8758); if (state.failed) return action;
            pushFollow(FOLLOW_stringExpression_in_actionTrie8772);
            key=stringExpression();

            state._fsp--;
            if (state.failed) return action;
            if ( state.backtracking==0 ) {
              left.add(key);
            }
            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionTrie8775); if (state.failed) return action;
            pushFollow(FOLLOW_typeExpression_in_actionTrie8790);
            value=typeExpression();

            state._fsp--;
            if (state.failed) return action;
            if ( state.backtracking==0 ) {
              right.add(value);
            }
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1470:9: ( COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression )*
            loop141:
            do {
                int alt141=2;
                int LA141_0 = input.LA(1);

                if ( (LA141_0==COMMA) ) {
                    int LA141_1 = input.LA(2);

                    if ( (LA141_1==Identifier) ) {
                        int LA141_2 = input.LA(3);

                        if ( (LA141_2==LPAREN||LA141_2==PLUS||LA141_2==ASSIGN_EQUAL) ) {
                            alt141=1;
                        }


                    }
                    else if ( (LA141_1==REMOVESTRING||LA141_1==StringLiteral) ) {
                        alt141=1;
                    }


                }


                switch (alt141) {
            	case 1 :
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1470:10: COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionTrie8803); if (state.failed) return action;
            	    pushFollow(FOLLOW_stringExpression_in_actionTrie8809);
            	    key=stringExpression();

            	    state._fsp--;
            	    if (state.failed) return action;
            	    if ( state.backtracking==0 ) {
            	      left.add(key);
            	    }
            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionTrie8813); if (state.failed) return action;
            	    pushFollow(FOLLOW_typeExpression_in_actionTrie8828);
            	    value=typeExpression();

            	    state._fsp--;
            	    if (state.failed) return action;
            	    if ( state.backtracking==0 ) {
            	      right.add(value);
            	    }

            	    }
            	    break;

            	default :
            	    break loop141;
                }
            } while (true);

            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8842); if (state.failed) return action;
            pushFollow(FOLLOW_wordListExpression_in_actionTrie8848);
            list=wordListExpression();

            state._fsp--;
            if (state.failed) return action;
            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8864); if (state.failed) return action;
            pushFollow(FOLLOW_booleanExpression_in_actionTrie8870);
            ignoreCase=booleanExpression();

            state._fsp--;
            if (state.failed) return action;
            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8877); if (state.failed) return action;
            pushFollow(FOLLOW_numberExpression_in_actionTrie8883);
            ignoreLength=numberExpression();

            state._fsp--;
            if (state.failed) return action;
            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8890); if (state.failed) return action;
            pushFollow(FOLLOW_booleanExpression_in_actionTrie8896);
            edit=booleanExpression();

            state._fsp--;
            if (state.failed) return action;
            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8903); if (state.failed) return action;
            pushFollow(FOLLOW_numberExpression_in_actionTrie8909);
            distance=numberExpression();

            state._fsp--;
            if (state.failed) return action;
            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8916); if (state.failed) return action;
            pushFollow(FOLLOW_stringExpression_in_actionTrie8922);
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
                  
                  action = ActionFactory.createStructureAction(name, list, args, left, right);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_actionTrie8945); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return action;
    }
    // $ANTLR end "actionTrie"


    // $ANTLR start "actionAdd"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1494:1: actionAdd returns [TextMarkerAction action = null] : name= ADD LPAREN f= listVariable ( COMMA a= argument )+ RPAREN ;
    public final TextMarkerAction actionAdd() throws RecognitionException {
        TextMarkerAction action =  null;

        Token name=null;
        Expression f = null;

        Expression a = null;



        	List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1498:5: (name= ADD LPAREN f= listVariable ( COMMA a= argument )+ RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1499:5: name= ADD LPAREN f= listVariable ( COMMA a= argument )+ RPAREN
            {
            name=(Token)match(input,ADD,FOLLOW_ADD_in_actionAdd8983); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionAdd8985); if (state.failed) return action;
            pushFollow(FOLLOW_listVariable_in_actionAdd8991);
            f=listVariable();

            state._fsp--;
            if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createAction(name, f, list);
            }
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1501:5: ( COMMA a= argument )+
            int cnt142=0;
            loop142:
            do {
                int alt142=2;
                int LA142_0 = input.LA(1);

                if ( (LA142_0==COMMA) ) {
                    alt142=1;
                }


                switch (alt142) {
            	case 1 :
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1501:6: COMMA a= argument
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionAdd9005); if (state.failed) return action;
            	    pushFollow(FOLLOW_argument_in_actionAdd9011);
            	    a=argument();

            	    state._fsp--;
            	    if (state.failed) return action;
            	    if ( state.backtracking==0 ) {
            	      list.add(a);
            	    }

            	    }
            	    break;

            	default :
            	    if ( cnt142 >= 1 ) break loop142;
            	    if (state.backtracking>0) {state.failed=true; return action;}
                        EarlyExitException eee =
                            new EarlyExitException(142, input);
                        throw eee;
                }
                cnt142++;
            } while (true);

            if ( state.backtracking==0 ) {
              action = ActionFactory.createAction(name, f, list);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_actionAdd9028); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return action;
    }
    // $ANTLR end "actionAdd"


    // $ANTLR start "actionRemove"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1506:1: actionRemove returns [TextMarkerAction action = null] : name= REMOVE LPAREN f= listVariable ( COMMA a= argument )+ RPAREN ;
    public final TextMarkerAction actionRemove() throws RecognitionException {
        TextMarkerAction action =  null;

        Token name=null;
        Expression f = null;

        Expression a = null;



        	List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1510:5: (name= REMOVE LPAREN f= listVariable ( COMMA a= argument )+ RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1511:5: name= REMOVE LPAREN f= listVariable ( COMMA a= argument )+ RPAREN
            {
            name=(Token)match(input,REMOVE,FOLLOW_REMOVE_in_actionRemove9062); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionRemove9064); if (state.failed) return action;
            pushFollow(FOLLOW_listVariable_in_actionRemove9070);
            f=listVariable();

            state._fsp--;
            if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createAction(name, f, list);
            }
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1513:5: ( COMMA a= argument )+
            int cnt143=0;
            loop143:
            do {
                int alt143=2;
                int LA143_0 = input.LA(1);

                if ( (LA143_0==COMMA) ) {
                    alt143=1;
                }


                switch (alt143) {
            	case 1 :
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1513:6: COMMA a= argument
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionRemove9084); if (state.failed) return action;
            	    pushFollow(FOLLOW_argument_in_actionRemove9090);
            	    a=argument();

            	    state._fsp--;
            	    if (state.failed) return action;
            	    if ( state.backtracking==0 ) {
            	      list.add(a);
            	    }

            	    }
            	    break;

            	default :
            	    if ( cnt143 >= 1 ) break loop143;
            	    if (state.backtracking>0) {state.failed=true; return action;}
                        EarlyExitException eee =
                            new EarlyExitException(143, input);
                        throw eee;
                }
                cnt143++;
            } while (true);

            if ( state.backtracking==0 ) {
              action = ActionFactory.createAction(name, f, list);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_actionRemove9107); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return action;
    }
    // $ANTLR end "actionRemove"


    // $ANTLR start "actionRemoveDuplicate"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1519:1: actionRemoveDuplicate returns [TextMarkerAction action = null] : name= REMOVEDUPLICATE LPAREN f= listVariable RPAREN ;
    public final TextMarkerAction actionRemoveDuplicate() throws RecognitionException {
        TextMarkerAction action =  null;

        Token name=null;
        Expression f = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1520:5: (name= REMOVEDUPLICATE LPAREN f= listVariable RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1521:5: name= REMOVEDUPLICATE LPAREN f= listVariable RPAREN
            {
            name=(Token)match(input,REMOVEDUPLICATE,FOLLOW_REMOVEDUPLICATE_in_actionRemoveDuplicate9137); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionRemoveDuplicate9139); if (state.failed) return action;
            pushFollow(FOLLOW_listVariable_in_actionRemoveDuplicate9145);
            f=listVariable();

            state._fsp--;
            if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createAction(name, f);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_actionRemoveDuplicate9158); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return action;
    }
    // $ANTLR end "actionRemoveDuplicate"


    // $ANTLR start "actionMerge"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1526:1: actionMerge returns [TextMarkerAction action = null] : name= MERGE LPAREN join= booleanExpression COMMA t= listVariable COMMA f= listExpression ( COMMA f= listExpression )+ RPAREN ;
    public final TextMarkerAction actionMerge() throws RecognitionException {
        TextMarkerAction action =  null;

        Token name=null;
        Expression join = null;

        Expression t = null;

        Expression f = null;



        	List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1530:5: (name= MERGE LPAREN join= booleanExpression COMMA t= listVariable COMMA f= listExpression ( COMMA f= listExpression )+ RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1531:5: name= MERGE LPAREN join= booleanExpression COMMA t= listVariable COMMA f= listExpression ( COMMA f= listExpression )+ RPAREN
            {
            name=(Token)match(input,MERGE,FOLLOW_MERGE_in_actionMerge9195); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionMerge9197); if (state.failed) return action;
            pushFollow(FOLLOW_booleanExpression_in_actionMerge9203);
            join=booleanExpression();

            state._fsp--;
            if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createAction(name, join, t, list);
            }
            match(input,COMMA,FOLLOW_COMMA_in_actionMerge9217); if (state.failed) return action;
            pushFollow(FOLLOW_listVariable_in_actionMerge9223);
            t=listVariable();

            state._fsp--;
            if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createAction(name, join, t, list);
            }
            match(input,COMMA,FOLLOW_COMMA_in_actionMerge9237); if (state.failed) return action;
            pushFollow(FOLLOW_listExpression_in_actionMerge9243);
            f=listExpression();

            state._fsp--;
            if (state.failed) return action;
            if ( state.backtracking==0 ) {
              list.add(f);
            }
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1536:5: ( COMMA f= listExpression )+
            int cnt144=0;
            loop144:
            do {
                int alt144=2;
                int LA144_0 = input.LA(1);

                if ( (LA144_0==COMMA) ) {
                    alt144=1;
                }


                switch (alt144) {
            	case 1 :
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1536:6: COMMA f= listExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMerge9253); if (state.failed) return action;
            	    pushFollow(FOLLOW_listExpression_in_actionMerge9259);
            	    f=listExpression();

            	    state._fsp--;
            	    if (state.failed) return action;
            	    if ( state.backtracking==0 ) {
            	      list.add(f);
            	    }

            	    }
            	    break;

            	default :
            	    if ( cnt144 >= 1 ) break loop144;
            	    if (state.backtracking>0) {state.failed=true; return action;}
                        EarlyExitException eee =
                            new EarlyExitException(144, input);
                        throw eee;
                }
                cnt144++;
            } while (true);

            if ( state.backtracking==0 ) {
              action = ActionFactory.createAction(name, join, t, list);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_actionMerge9276); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return action;
    }
    // $ANTLR end "actionMerge"


    // $ANTLR start "actionGet"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1541:1: actionGet returns [TextMarkerAction action = null] : name= GET LPAREN f= listExpression COMMA var= variable COMMA op= stringExpression RPAREN ;
    public final TextMarkerAction actionGet() throws RecognitionException {
        TextMarkerAction action =  null;

        Token name=null;
        Expression f = null;

        Expression var = null;

        Expression op = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1542:5: (name= GET LPAREN f= listExpression COMMA var= variable COMMA op= stringExpression RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1543:5: name= GET LPAREN f= listExpression COMMA var= variable COMMA op= stringExpression RPAREN
            {
            name=(Token)match(input,GET,FOLLOW_GET_in_actionGet9305); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionGet9307); if (state.failed) return action;
            pushFollow(FOLLOW_listExpression_in_actionGet9313);
            f=listExpression();

            state._fsp--;
            if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createAction(name, f, var, op);
            }
            match(input,COMMA,FOLLOW_COMMA_in_actionGet9326); if (state.failed) return action;
            pushFollow(FOLLOW_variable_in_actionGet9332);
            var=variable();

            state._fsp--;
            if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createAction(name, f, var, op);
            }
            match(input,COMMA,FOLLOW_COMMA_in_actionGet9345); if (state.failed) return action;
            pushFollow(FOLLOW_stringExpression_in_actionGet9351);
            op=stringExpression();

            state._fsp--;
            if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createAction(name, f, var, op);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_actionGet9364); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return action;
    }
    // $ANTLR end "actionGet"


    // $ANTLR start "actionGetList"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1553:1: actionGetList returns [TextMarkerAction action = null] : name= GETLIST LPAREN var= listVariable COMMA op= stringExpression RPAREN ;
    public final TextMarkerAction actionGetList() throws RecognitionException {
        TextMarkerAction action =  null;

        Token name=null;
        Expression var = null;

        Expression op = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1554:5: (name= GETLIST LPAREN var= listVariable COMMA op= stringExpression RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1555:5: name= GETLIST LPAREN var= listVariable COMMA op= stringExpression RPAREN
            {
            name=(Token)match(input,GETLIST,FOLLOW_GETLIST_in_actionGetList9394); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionGetList9396); if (state.failed) return action;
            pushFollow(FOLLOW_listVariable_in_actionGetList9402);
            var=listVariable();

            state._fsp--;
            if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createAction(name, var, op);
            }
            match(input,COMMA,FOLLOW_COMMA_in_actionGetList9415); if (state.failed) return action;
            pushFollow(FOLLOW_stringExpression_in_actionGetList9421);
            op=stringExpression();

            state._fsp--;
            if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createAction(name, var, op);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_actionGetList9434); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return action;
    }
    // $ANTLR end "actionGetList"


    // $ANTLR start "actionMatchedText"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1562:1: actionMatchedText returns [TextMarkerAction action = null] : name= MATCHEDTEXT LPAREN var= variable ( COMMA index= numberExpression )* RPAREN ;
    public final TextMarkerAction actionMatchedText() throws RecognitionException {
        TextMarkerAction action =  null;

        Token name=null;
        Expression var = null;

        Expression index = null;



        List<Expression> list = new ArrayList<Expression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1566:5: (name= MATCHEDTEXT LPAREN var= variable ( COMMA index= numberExpression )* RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1567:5: name= MATCHEDTEXT LPAREN var= variable ( COMMA index= numberExpression )* RPAREN
            {
            name=(Token)match(input,MATCHEDTEXT,FOLLOW_MATCHEDTEXT_in_actionMatchedText9471); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionMatchedText9473); if (state.failed) return action;
            pushFollow(FOLLOW_variable_in_actionMatchedText9484);
            var=variable();

            state._fsp--;
            if (state.failed) return action;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1569:5: ( COMMA index= numberExpression )*
            loop145:
            do {
                int alt145=2;
                int LA145_0 = input.LA(1);

                if ( (LA145_0==COMMA) ) {
                    alt145=1;
                }


                switch (alt145) {
            	case 1 :
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1570:5: COMMA index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMatchedText9496); if (state.failed) return action;
            	    pushFollow(FOLLOW_numberExpression_in_actionMatchedText9502);
            	    index=numberExpression();

            	    state._fsp--;
            	    if (state.failed) return action;
            	    if ( state.backtracking==0 ) {
            	      list.add(index);
            	    }

            	    }
            	    break;

            	default :
            	    break loop145;
                }
            } while (true);

            if ( state.backtracking==0 ) {
              action = ActionFactory.createAction(name, var, list);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_actionMatchedText9524); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return action;
    }
    // $ANTLR end "actionMatchedText"


    // $ANTLR start "actionClear"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1576:1: actionClear returns [TextMarkerAction action = null] : name= CLEAR LPAREN var= listVariable RPAREN ;
    public final TextMarkerAction actionClear() throws RecognitionException {
        TextMarkerAction action =  null;

        Token name=null;
        Expression var = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1577:5: (name= CLEAR LPAREN var= listVariable RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1578:5: name= CLEAR LPAREN var= listVariable RPAREN
            {
            name=(Token)match(input,CLEAR,FOLLOW_CLEAR_in_actionClear9557); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionClear9559); if (state.failed) return action;
            pushFollow(FOLLOW_listVariable_in_actionClear9565);
            var=listVariable();

            state._fsp--;
            if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createAction(name, var);
            }
            match(input,RPAREN,FOLLOW_RPAREN_in_actionClear9578); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		if( reporter != null ) {
        			reporter.reportError(exception1);
        		}
        		recover(input,exception1);
        	}
        	catch (Throwable exception2) {
        		if( reporter != null ) {
        			reporter.reportThrowable(exception2);
        		}
        	}
        finally {
        }
        return action;
    }
    // $ANTLR end "actionClear"


    // $ANTLR start "varArgumentList"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1585:1: varArgumentList returns [List<Expression> args = new ArrayList<Expression>()] : ( LPAREN arg= argument ( COMMA arg= argument )* RPAREN )? ;
    public final List<Expression> varArgumentList() throws RecognitionException {
        List<Expression> args =  new ArrayList<Expression>();

        Expression arg = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1586:2: ( ( LPAREN arg= argument ( COMMA arg= argument )* RPAREN )? )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1587:2: ( LPAREN arg= argument ( COMMA arg= argument )* RPAREN )?
            {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1587:2: ( LPAREN arg= argument ( COMMA arg= argument )* RPAREN )?
            int alt147=2;
            int LA147_0 = input.LA(1);

            if ( (LA147_0==LPAREN) ) {
                alt147=1;
            }
            switch (alt147) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1587:3: LPAREN arg= argument ( COMMA arg= argument )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_varArgumentList9600); if (state.failed) return args;
                    pushFollow(FOLLOW_argument_in_varArgumentList9606);
                    arg=argument();

                    state._fsp--;
                    if (state.failed) return args;
                    if ( state.backtracking==0 ) {
                      args.add(arg);
                    }
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1587:42: ( COMMA arg= argument )*
                    loop146:
                    do {
                        int alt146=2;
                        int LA146_0 = input.LA(1);

                        if ( (LA146_0==COMMA) ) {
                            alt146=1;
                        }


                        switch (alt146) {
                    	case 1 :
                    	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1587:43: COMMA arg= argument
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_varArgumentList9611); if (state.failed) return args;
                    	    pushFollow(FOLLOW_argument_in_varArgumentList9617);
                    	    arg=argument();

                    	    state._fsp--;
                    	    if (state.failed) return args;
                    	    if ( state.backtracking==0 ) {
                    	      args.add(arg);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop146;
                        }
                    } while (true);

                    match(input,RPAREN,FOLLOW_RPAREN_in_varArgumentList9623); if (state.failed) return args;

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
        }
        return args;
    }
    // $ANTLR end "varArgumentList"


    // $ANTLR start "argument"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1591:1: argument returns [Expression expr = null] options {backtrack=true; } : (a4= stringExpression | a2= booleanExpression | a3= numberExpression | a1= typeExpression );
    public final Expression argument() throws RecognitionException {
        Expression expr =  null;

        Expression a4 = null;

        Expression a2 = null;

        Expression a3 = null;

        Expression a1 = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1595:2: (a4= stringExpression | a2= booleanExpression | a3= numberExpression | a1= typeExpression )
            int alt148=4;
            alt148 = dfa148.predict(input);
            switch (alt148) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1596:3: a4= stringExpression
                    {
                    pushFollow(FOLLOW_stringExpression_in_argument9660);
                    a4=stringExpression();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = a4;
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1597:4: a2= booleanExpression
                    {
                    pushFollow(FOLLOW_booleanExpression_in_argument9671);
                    a2=booleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = a2;
                    }

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1598:4: a3= numberExpression
                    {
                    pushFollow(FOLLOW_numberExpression_in_argument9682);
                    a3=numberExpression();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = a3;
                    }

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1599:4: a1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_argument9693);
                    a1=typeExpression();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = a1;
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
        }
        return expr;
    }
    // $ANTLR end "argument"


    // $ANTLR start "dottedIdentifier"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1610:1: dottedIdentifier returns [String idString = \"\"] : id= Identifier (dot= DOT idn= Identifier )* ;
    public final String dottedIdentifier() throws RecognitionException {
        String idString =  "";

        Token id=null;
        Token dot=null;
        Token idn=null;

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1611:2: (id= Identifier (dot= DOT idn= Identifier )* )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1612:2: id= Identifier (dot= DOT idn= Identifier )*
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedIdentifier9730); if (state.failed) return idString;
            if ( state.backtracking==0 ) {
              idString += id.getText();
            }
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1613:2: (dot= DOT idn= Identifier )*
            loop149:
            do {
                int alt149=2;
                int LA149_0 = input.LA(1);

                if ( (LA149_0==DOT) ) {
                    alt149=1;
                }


                switch (alt149) {
            	case 1 :
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1614:3: dot= DOT idn= Identifier
            	    {
            	    dot=(Token)match(input,DOT,FOLLOW_DOT_in_dottedIdentifier9743); if (state.failed) return idString;
            	    if ( state.backtracking==0 ) {
            	      idString += dot.getText();
            	    }
            	    idn=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedIdentifier9753); if (state.failed) return idString;
            	    if ( state.backtracking==0 ) {
            	      idString += idn.getText();
            	    }

            	    }
            	    break;

            	default :
            	    break loop149;
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
        }
        return idString;
    }
    // $ANTLR end "dottedIdentifier"


    // $ANTLR start "dottedId"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1620:1: dottedId returns [Token token = null ] : id= Identifier (dot= DOT id= Identifier )* ;
    public final Token dottedId() throws RecognitionException {
        Token token =  null;

        Token id=null;
        Token dot=null;

        CommonToken ct = null;
        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1622:2: (id= Identifier (dot= DOT id= Identifier )* )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1623:2: id= Identifier (dot= DOT id= Identifier )*
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedId9785); if (state.failed) return token;
            if ( state.backtracking==0 ) {

              		ct = new CommonToken(id);
              		
            }
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1626:2: (dot= DOT id= Identifier )*
            loop150:
            do {
                int alt150=2;
                int LA150_0 = input.LA(1);

                if ( (LA150_0==DOT) ) {
                    alt150=1;
                }


                switch (alt150) {
            	case 1 :
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1627:3: dot= DOT id= Identifier
            	    {
            	    dot=(Token)match(input,DOT,FOLLOW_DOT_in_dottedId9798); if (state.failed) return token;
            	    if ( state.backtracking==0 ) {
            	      ct.setText(ct.getText() + dot.getText());
            	    }
            	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedId9808); if (state.failed) return token;
            	    if ( state.backtracking==0 ) {
            	      ct.setStopIndex(getBounds(id)[1]);
            	      		                 ct.setText(ct.getText() + id.getText());
            	    }

            	    }
            	    break;

            	default :
            	    break loop150;
                }
            } while (true);

            if ( state.backtracking==0 ) {
              token = ct;
              	 return token;
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
        }
        return token;
    }
    // $ANTLR end "dottedId"


    // $ANTLR start "dottedComponentReference"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1636:1: dottedComponentReference returns [ComponentReference ref = null ] : id= Identifier (dot= ( DOT | MINUS ) id= Identifier )* ;
    public final ComponentReference dottedComponentReference() throws RecognitionException {
        ComponentReference ref =  null;

        Token id=null;
        Token dot=null;

        CommonToken ct = null;
        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1638:2: (id= Identifier (dot= ( DOT | MINUS ) id= Identifier )* )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1639:2: id= Identifier (dot= ( DOT | MINUS ) id= Identifier )*
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedComponentReference9843); if (state.failed) return ref;
            if ( state.backtracking==0 ) {

              		ct = new CommonToken(id);
              		//if (ct.getText().equals("<missing Identifier>")) {
              	        //    CommonTokenStream cts = (CommonTokenStream) input;
              	        //    Token lt = cts.LT(1);
              	        //    ct = new CommonToken(lt);
              	        //  }
              		
            }
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1647:2: (dot= ( DOT | MINUS ) id= Identifier )*
            loop151:
            do {
                int alt151=2;
                int LA151_0 = input.LA(1);

                if ( (LA151_0==DOT||LA151_0==MINUS) ) {
                    alt151=1;
                }


                switch (alt151) {
            	case 1 :
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1648:3: dot= ( DOT | MINUS ) id= Identifier
            	    {
            	    dot=(Token)input.LT(1);
            	    if ( input.LA(1)==DOT||input.LA(1)==MINUS ) {
            	        input.consume();
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ref;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    if ( state.backtracking==0 ) {
            	      ct.setText(ct.getText() + dot.getText());
            	    }
            	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedComponentReference9872); if (state.failed) return ref;
            	    if ( state.backtracking==0 ) {
            	      ct.setStopIndex(getBounds(id)[1]);
            	      		                 ct.setText(ct.getText() + id.getText());
            	    }

            	    }
            	    break;

            	default :
            	    break loop151;
                }
            } while (true);

            if ( state.backtracking==0 ) {

              	 if (!ct.getText().equals("<missing Identifier>")) ref = StatementFactory.createComponentReference(ct);
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
        }
        return ref;
    }
    // $ANTLR end "dottedComponentReference"


    // $ANTLR start "dottedComponentDeclaration"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1656:1: dottedComponentDeclaration returns [ComponentDeclaration ref = null ] : id= Identifier (dot= ( DOT | MINUS ) id= Identifier )* ;
    public final ComponentDeclaration dottedComponentDeclaration() throws RecognitionException {
        ComponentDeclaration ref =  null;

        Token id=null;
        Token dot=null;

        CommonToken ct = null;
        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1658:2: (id= Identifier (dot= ( DOT | MINUS ) id= Identifier )* )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1659:2: id= Identifier (dot= ( DOT | MINUS ) id= Identifier )*
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedComponentDeclaration9906); if (state.failed) return ref;
            if ( state.backtracking==0 ) {

              		ct = new CommonToken(id);
              		
            }
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1662:2: (dot= ( DOT | MINUS ) id= Identifier )*
            loop152:
            do {
                int alt152=2;
                int LA152_0 = input.LA(1);

                if ( (LA152_0==DOT||LA152_0==MINUS) ) {
                    alt152=1;
                }


                switch (alt152) {
            	case 1 :
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1663:3: dot= ( DOT | MINUS ) id= Identifier
            	    {
            	    dot=(Token)input.LT(1);
            	    if ( input.LA(1)==DOT||input.LA(1)==MINUS ) {
            	        input.consume();
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ref;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    if ( state.backtracking==0 ) {
            	      ct.setText(ct.getText() + dot.getText());
            	    }
            	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedComponentDeclaration9935); if (state.failed) return ref;
            	    if ( state.backtracking==0 ) {
            	      ct.setStopIndex(getBounds(id)[1]);
            	      		                 ct.setText(ct.getText() + id.getText());
            	    }

            	    }
            	    break;

            	default :
            	    break loop152;
                }
            } while (true);

            if ( state.backtracking==0 ) {

              	 if (!ct.getText().equals("<missing Identifier>")) ref = StatementFactory.createComponentDeclaration(ct);
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
        }
        return ref;
    }
    // $ANTLR end "dottedComponentDeclaration"


    // $ANTLR start "annotationType"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1672:1: annotationType returns [Expression at = null] : (atRef= annotationTypeVariableReference | basicAT= BasicAnnotationType ) ;
    public final Expression annotationType() throws RecognitionException {
        Expression at =  null;

        Token basicAT=null;
        Expression atRef = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1673:2: ( (atRef= annotationTypeVariableReference | basicAT= BasicAnnotationType ) )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1674:2: (atRef= annotationTypeVariableReference | basicAT= BasicAnnotationType )
            {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1674:2: (atRef= annotationTypeVariableReference | basicAT= BasicAnnotationType )
            int alt153=2;
            int LA153_0 = input.LA(1);

            if ( (LA153_0==Identifier) ) {
                alt153=1;
            }
            else if ( (LA153_0==BasicAnnotationType) ) {
                alt153=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return at;}
                NoViableAltException nvae =
                    new NoViableAltException("", 153, 0, input);

                throw nvae;
            }
            switch (alt153) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1675:2: atRef= annotationTypeVariableReference
                    {
                    pushFollow(FOLLOW_annotationTypeVariableReference_in_annotationType9969);
                    atRef=annotationTypeVariableReference();

                    state._fsp--;
                    if (state.failed) return at;
                    if ( state.backtracking==0 ) {
                      at = atRef;
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1676:4: basicAT= BasicAnnotationType
                    {
                    basicAT=(Token)match(input,BasicAnnotationType,FOLLOW_BasicAnnotationType_in_annotationType9980); if (state.failed) return at;
                    if ( state.backtracking==0 ) {
                      at = ExpressionFactory.createAnnotationTypeConstantReference(basicAT);
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
        }
        return at;
    }
    // $ANTLR end "annotationType"


    // $ANTLR start "annotationTypeVariableReference"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1680:1: annotationTypeVariableReference returns [Expression typeVar = null] : atRef= dottedId ;
    public final Expression annotationTypeVariableReference() throws RecognitionException {
        Expression typeVar =  null;

        Token atRef = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1681:3: (atRef= dottedId )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1682:3: atRef= dottedId
            {
            pushFollow(FOLLOW_dottedId_in_annotationTypeVariableReference10009);
            atRef=dottedId();

            state._fsp--;
            if (state.failed) return typeVar;
            if ( state.backtracking==0 ) {
              typeVar = ExpressionFactory.createAnnotationTypeVariableReference(atRef);
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
        }
        return typeVar;
    }
    // $ANTLR end "annotationTypeVariableReference"


    // $ANTLR start "wordListExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1686:1: wordListExpression returns [Expression expr = null] : (id= Identifier | path= RessourceLiteral );
    public final Expression wordListExpression() throws RecognitionException {
        Expression expr =  null;

        Token id=null;
        Token path=null;

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1687:2: (id= Identifier | path= RessourceLiteral )
            int alt154=2;
            int LA154_0 = input.LA(1);

            if ( (LA154_0==Identifier) ) {
                alt154=1;
            }
            else if ( (LA154_0==RessourceLiteral) ) {
                alt154=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 154, 0, input);

                throw nvae;
            }
            switch (alt154) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1688:2: id= Identifier
                    {
                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_wordListExpression10033); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createListVariableReference(id);
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1691:2: path= RessourceLiteral
                    {
                    path=(Token)match(input,RessourceLiteral,FOLLOW_RessourceLiteral_in_wordListExpression10046); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createRessourceReference(path);
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
        }
        return expr;
    }
    // $ANTLR end "wordListExpression"


    // $ANTLR start "wordTableExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1696:1: wordTableExpression returns [Expression expr = null] : (id= Identifier | path= RessourceLiteral );
    public final Expression wordTableExpression() throws RecognitionException {
        Expression expr =  null;

        Token id=null;
        Token path=null;

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1697:2: (id= Identifier | path= RessourceLiteral )
            int alt155=2;
            int LA155_0 = input.LA(1);

            if ( (LA155_0==Identifier) ) {
                alt155=1;
            }
            else if ( (LA155_0==RessourceLiteral) ) {
                alt155=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 155, 0, input);

                throw nvae;
            }
            switch (alt155) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1698:2: id= Identifier
                    {
                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_wordTableExpression10070); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createTableVariableReference(id);
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1701:2: path= RessourceLiteral
                    {
                    path=(Token)match(input,RessourceLiteral,FOLLOW_RessourceLiteral_in_wordTableExpression10083); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createRessourceReference(path);
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
        }
        return expr;
    }
    // $ANTLR end "wordTableExpression"


    // $ANTLR start "numberExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1706:1: numberExpression returns [Expression expr = null] : e= additiveExpression ;
    public final Expression numberExpression() throws RecognitionException {
        Expression expr =  null;

        Expression e = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1707:2: (e= additiveExpression )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1708:2: e= additiveExpression
            {
            pushFollow(FOLLOW_additiveExpression_in_numberExpression10107);
            e=additiveExpression();

            state._fsp--;
            if (state.failed) return expr;
            if ( state.backtracking==0 ) {
              if(e!=null) expr = ExpressionFactory.createNumberExpression(e);
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
        }
        return expr;
    }
    // $ANTLR end "numberExpression"


    // $ANTLR start "additiveExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1713:1: additiveExpression returns [Expression root = null] : expr1= multiplicativeExpression (op= ( PLUS | MINUS ) expr2= multiplicativeExpression )* ;
    public final Expression additiveExpression() throws RecognitionException {
        Expression root =  null;

        Token op=null;
        Expression expr1 = null;

        Expression expr2 = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1714:5: (expr1= multiplicativeExpression (op= ( PLUS | MINUS ) expr2= multiplicativeExpression )* )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1714:9: expr1= multiplicativeExpression (op= ( PLUS | MINUS ) expr2= multiplicativeExpression )*
            {
            pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression10133);
            expr1=multiplicativeExpression();

            state._fsp--;
            if (state.failed) return root;
            if ( state.backtracking==0 ) {
              root=expr1;
            }
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1715:2: (op= ( PLUS | MINUS ) expr2= multiplicativeExpression )*
            loop156:
            do {
                int alt156=2;
                int LA156_0 = input.LA(1);

                if ( ((LA156_0>=PLUS && LA156_0<=MINUS)) ) {
                    alt156=1;
                }


                switch (alt156) {
            	case 1 :
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1715:4: op= ( PLUS | MINUS ) expr2= multiplicativeExpression
            	    {
            	    op=(Token)input.LT(1);
            	    if ( (input.LA(1)>=PLUS && input.LA(1)<=MINUS) ) {
            	        input.consume();
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return root;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression10152);
            	    expr2=multiplicativeExpression();

            	    state._fsp--;
            	    if (state.failed) return root;
            	    if ( state.backtracking==0 ) {
            	      root=ExpressionFactory.createBinaryArithmeticExpr(root,expr2,op);
            	    }

            	    }
            	    break;

            	default :
            	    break loop156;
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
        }
        return root;
    }
    // $ANTLR end "additiveExpression"


    // $ANTLR start "multiplicativeExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1719:1: multiplicativeExpression returns [Expression root = null] : (expr1= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) sNE= simpleNumberExpression )* | e1= numberFunction ) ;
    public final Expression multiplicativeExpression() throws RecognitionException {
        Expression root =  null;

        Token op=null;
        Expression expr1 = null;

        Expression sNE = null;

        Expression e1 = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1720:5: ( (expr1= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) sNE= simpleNumberExpression )* | e1= numberFunction ) )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1721:2: (expr1= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) sNE= simpleNumberExpression )* | e1= numberFunction )
            {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1721:2: (expr1= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) sNE= simpleNumberExpression )* | e1= numberFunction )
            int alt158=2;
            switch ( input.LA(1) ) {
            case DecimalLiteral:
            case FloatingPointLiteral:
            case LPAREN:
            case MINUS:
                {
                alt158=1;
                }
                break;
            case Identifier:
                {
                int LA158_2 = input.LA(2);

                if ( (LA158_2==LPAREN) ) {
                    alt158=2;
                }
                else if ( (LA158_2==EOF||LA158_2==RPAREN||LA158_2==RBRACK||(LA158_2>=COMMA && LA158_2<=SLASH)||(LA158_2>=LESS && LA158_2<=GREATER)||LA158_2==PERCENT||(LA158_2>=EQUAL && LA158_2<=NOTEQUAL)||(LA158_2>=LESSEQUAL && LA158_2<=GREATEREQUAL)) ) {
                    alt158=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return root;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 158, 2, input);

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
                alt158=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return root;}
                NoViableAltException nvae =
                    new NoViableAltException("", 158, 0, input);

                throw nvae;
            }

            switch (alt158) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1721:3: expr1= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) sNE= simpleNumberExpression )*
                    {
                    pushFollow(FOLLOW_simpleNumberExpression_in_multiplicativeExpression10181);
                    expr1=simpleNumberExpression();

                    state._fsp--;
                    if (state.failed) return root;
                    if ( state.backtracking==0 ) {
                      root=expr1;
                    }
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1722:2: (op= ( STAR | SLASH | PERCENT ) sNE= simpleNumberExpression )*
                    loop157:
                    do {
                        int alt157=2;
                        int LA157_0 = input.LA(1);

                        if ( ((LA157_0>=STAR && LA157_0<=SLASH)||LA157_0==PERCENT) ) {
                            alt157=1;
                        }


                        switch (alt157) {
                    	case 1 :
                    	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1722:4: op= ( STAR | SLASH | PERCENT ) sNE= simpleNumberExpression
                    	    {
                    	    op=(Token)input.LT(1);
                    	    if ( (input.LA(1)>=STAR && input.LA(1)<=SLASH)||input.LA(1)==PERCENT ) {
                    	        input.consume();
                    	        state.errorRecovery=false;state.failed=false;
                    	    }
                    	    else {
                    	        if (state.backtracking>0) {state.failed=true; return root;}
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        throw mse;
                    	    }

                    	    pushFollow(FOLLOW_simpleNumberExpression_in_multiplicativeExpression10208);
                    	    sNE=simpleNumberExpression();

                    	    state._fsp--;
                    	    if (state.failed) return root;
                    	    if ( state.backtracking==0 ) {
                    	      root=ExpressionFactory.createBinaryArithmeticExpr(root,sNE,op);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop157;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1723:6: e1= numberFunction
                    {
                    pushFollow(FOLLOW_numberFunction_in_multiplicativeExpression10224);
                    e1=numberFunction();

                    state._fsp--;
                    if (state.failed) return root;
                    if ( state.backtracking==0 ) {
                      root = e1;
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
        }
        return root;
    }
    // $ANTLR end "multiplicativeExpression"


    // $ANTLR start "numberExpressionInPar"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1727:1: numberExpressionInPar returns [TextMarkerExpression expr = null] : lp= LPAREN numE= numberExpression rp= RPAREN ;
    public final TextMarkerExpression numberExpressionInPar() throws RecognitionException {
        TextMarkerExpression expr =  null;

        Token lp=null;
        Token rp=null;
        Expression numE = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1728:2: (lp= LPAREN numE= numberExpression rp= RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1729:2: lp= LPAREN numE= numberExpression rp= RPAREN
            {
            lp=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_numberExpressionInPar10248); if (state.failed) return expr;
            pushFollow(FOLLOW_numberExpression_in_numberExpressionInPar10254);
            numE=numberExpression();

            state._fsp--;
            if (state.failed) return expr;
            rp=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_numberExpressionInPar10260); if (state.failed) return expr;
            if ( state.backtracking==0 ) {
              expr = ExpressionFactory.createNumberExpression((TextMarkerExpression)numE); 
              	  expr.setInParantheses(true);
                        expr.setStart(((CommonToken) lp).getStartIndex());
                        expr.setEnd(((CommonToken) rp).getStopIndex()+1);
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
        }
        return expr;
    }
    // $ANTLR end "numberExpressionInPar"


    // $ANTLR start "simpleNumberExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1737:1: simpleNumberExpression returns [Expression expr = null] : ( (m= MINUS )? numVarRef= numberVariable | (m= MINUS )? decLit= DecimalLiteral | (m= MINUS )? fpLit= FloatingPointLiteral | numExprPar= numberExpressionInPar );
    public final Expression simpleNumberExpression() throws RecognitionException {
        Expression expr =  null;

        Token m=null;
        Token decLit=null;
        Token fpLit=null;
        Expression numVarRef = null;

        TextMarkerExpression numExprPar = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1738:2: ( (m= MINUS )? numVarRef= numberVariable | (m= MINUS )? decLit= DecimalLiteral | (m= MINUS )? fpLit= FloatingPointLiteral | numExprPar= numberExpressionInPar )
            int alt162=4;
            switch ( input.LA(1) ) {
            case MINUS:
                {
                switch ( input.LA(2) ) {
                case Identifier:
                    {
                    alt162=1;
                    }
                    break;
                case FloatingPointLiteral:
                    {
                    alt162=3;
                    }
                    break;
                case DecimalLiteral:
                    {
                    alt162=2;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 162, 1, input);

                    throw nvae;
                }

                }
                break;
            case Identifier:
                {
                alt162=1;
                }
                break;
            case DecimalLiteral:
                {
                alt162=2;
                }
                break;
            case FloatingPointLiteral:
                {
                alt162=3;
                }
                break;
            case LPAREN:
                {
                alt162=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 162, 0, input);

                throw nvae;
            }

            switch (alt162) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1739:2: (m= MINUS )? numVarRef= numberVariable
                    {
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1739:4: (m= MINUS )?
                    int alt159=2;
                    int LA159_0 = input.LA(1);

                    if ( (LA159_0==MINUS) ) {
                        alt159=1;
                    }
                    switch (alt159) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1739:4: m= MINUS
                            {
                            m=(Token)match(input,MINUS,FOLLOW_MINUS_in_simpleNumberExpression10285); if (state.failed) return expr;

                            }
                            break;

                    }

                    pushFollow(FOLLOW_numberVariable_in_simpleNumberExpression10292);
                    numVarRef=numberVariable();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      if(m == null) {expr = numVarRef;} else {expr = ExpressionFactory.createNegatedNumberExpression(m, numVarRef);}
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1741:4: (m= MINUS )? decLit= DecimalLiteral
                    {
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1741:4: (m= MINUS )?
                    int alt160=2;
                    int LA160_0 = input.LA(1);

                    if ( (LA160_0==MINUS) ) {
                        alt160=1;
                    }
                    switch (alt160) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1741:5: m= MINUS
                            {
                            m=(Token)match(input,MINUS,FOLLOW_MINUS_in_simpleNumberExpression10307); if (state.failed) return expr;

                            }
                            break;

                    }

                    decLit=(Token)match(input,DecimalLiteral,FOLLOW_DecimalLiteral_in_simpleNumberExpression10315); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createDecimalLiteral(decLit,m);
                    }

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1743:4: (m= MINUS )? fpLit= FloatingPointLiteral
                    {
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1743:6: (m= MINUS )?
                    int alt161=2;
                    int LA161_0 = input.LA(1);

                    if ( (LA161_0==MINUS) ) {
                        alt161=1;
                    }
                    switch (alt161) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1743:6: m= MINUS
                            {
                            m=(Token)match(input,MINUS,FOLLOW_MINUS_in_simpleNumberExpression10329); if (state.failed) return expr;

                            }
                            break;

                    }

                    fpLit=(Token)match(input,FloatingPointLiteral,FOLLOW_FloatingPointLiteral_in_simpleNumberExpression10336); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createFloatingPointLiteral(fpLit,m);
                    }

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1746:4: numExprPar= numberExpressionInPar
                    {
                    pushFollow(FOLLOW_numberExpressionInPar_in_simpleNumberExpression10352);
                    numExprPar=numberExpressionInPar();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = numExprPar;
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
        }
        return expr;
    }
    // $ANTLR end "simpleNumberExpression"


    // $ANTLR start "numberFunction"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1751:1: numberFunction returns [Expression expr = null] : ( (op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar ) | (e= externalNumberFunction )=>e= externalNumberFunction );
    public final Expression numberFunction() throws RecognitionException {
        Expression expr =  null;

        Token op=null;
        TextMarkerExpression numExprP = null;

        Expression e = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1752:2: ( (op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar ) | (e= externalNumberFunction )=>e= externalNumberFunction )
            int alt163=2;
            int LA163_0 = input.LA(1);

            if ( ((LA163_0>=EXP && LA163_0<=TAN)) ) {
                alt163=1;
            }
            else if ( (LA163_0==Identifier) && (synpred25_TextMarkerParser())) {
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1753:2: (op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar )
                    {
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1753:2: (op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar )
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1753:3: op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar
                    {
                    op=(Token)input.LT(1);
                    if ( (input.LA(1)>=EXP && input.LA(1)<=TAN) ) {
                        input.consume();
                        state.errorRecovery=false;state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }

                    pushFollow(FOLLOW_numberExpressionInPar_in_numberFunction10399);
                    numExprP=numberExpressionInPar();

                    state._fsp--;
                    if (state.failed) return expr;

                    }

                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createUnaryArithmeticExpr(numExprP,op);
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1756:4: (e= externalNumberFunction )=>e= externalNumberFunction
                    {
                    pushFollow(FOLLOW_externalNumberFunction_in_numberFunction10423);
                    e=externalNumberFunction();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = e;
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
        }
        return expr;
    }
    // $ANTLR end "numberFunction"


    // $ANTLR start "externalNumberFunction"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1760:1: externalNumberFunction returns [Expression expr = null] : {...}?id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final Expression externalNumberFunction() throws RecognitionException {
        Expression expr =  null;

        Token id=null;
        List<Expression> args = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1761:2: ({...}?id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1762:2: {...}?id= Identifier LPAREN args= varArgumentList RPAREN
            {
            if ( !((isVariableOfType(input.LT(1).getText(), "NUMBERFUNCTION"))) ) {
                if (state.backtracking>0) {state.failed=true; return expr;}
                throw new FailedPredicateException(input, "externalNumberFunction", "isVariableOfType(input.LT(1).getText(), \"NUMBERFUNCTION\")");
            }
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalNumberFunction10448); if (state.failed) return expr;
            match(input,LPAREN,FOLLOW_LPAREN_in_externalNumberFunction10452); if (state.failed) return expr;
            pushFollow(FOLLOW_varArgumentList_in_externalNumberFunction10459);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return expr;
            match(input,RPAREN,FOLLOW_RPAREN_in_externalNumberFunction10462); if (state.failed) return expr;
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
        }
        return expr;
    }
    // $ANTLR end "externalNumberFunction"


    // $ANTLR start "numberVariable"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1772:1: numberVariable returns [Expression expr = null] : ({...}?numVarRef= Identifier | {...}?numVarRef= Identifier ) ;
    public final Expression numberVariable() throws RecognitionException {
        Expression expr =  null;

        Token numVarRef=null;

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1773:2: ( ({...}?numVarRef= Identifier | {...}?numVarRef= Identifier ) )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1774:5: ({...}?numVarRef= Identifier | {...}?numVarRef= Identifier )
            {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1774:5: ({...}?numVarRef= Identifier | {...}?numVarRef= Identifier )
            int alt164=2;
            int LA164_0 = input.LA(1);

            if ( (LA164_0==Identifier) ) {
                int LA164_1 = input.LA(2);

                if ( ((isVariableOfType(input.LT(1).getText(), "INT"))) ) {
                    alt164=1;
                }
                else if ( ((isVariableOfType(input.LT(1).getText(), "DOUBLE"))) ) {
                    alt164=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 164, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 164, 0, input);

                throw nvae;
            }
            switch (alt164) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1774:7: {...}?numVarRef= Identifier
                    {
                    if ( !((isVariableOfType(input.LT(1).getText(), "INT"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "numberVariable", "isVariableOfType(input.LT(1).getText(), \"INT\")");
                    }
                    numVarRef=(Token)match(input,Identifier,FOLLOW_Identifier_in_numberVariable10493); if (state.failed) return expr;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1775:5: {...}?numVarRef= Identifier
                    {
                    if ( !((isVariableOfType(input.LT(1).getText(), "DOUBLE"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "numberVariable", "isVariableOfType(input.LT(1).getText(), \"DOUBLE\")");
                    }
                    numVarRef=(Token)match(input,Identifier,FOLLOW_Identifier_in_numberVariable10506); if (state.failed) return expr;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              	 expr = ExpressionFactory.createNumberVariableReference(numVarRef);
            }

            }

        }
        catch (Exception e) {
            expr = ExpressionFactory.createNumberVariableReference(input.LT(1));
        }
        finally {
        }
        return expr;
    }
    // $ANTLR end "numberVariable"


    // $ANTLR start "stringExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1781:1: stringExpression returns [Expression expr = null] : (e= stringFunction | strExpr1= simpleStringExpression ( PLUS (nextstrExpr= simpleStringExpression | ne= numberExpressionInPar | be= simpleBooleanExpression | ( listExpression )=>le= listExpression | te= typeExpression ) )* );
    public final Expression stringExpression() throws RecognitionException {
        Expression expr =  null;

        Expression e = null;

        Expression strExpr1 = null;

        Expression nextstrExpr = null;

        TextMarkerExpression ne = null;

        Expression be = null;

        Expression le = null;

        Expression te = null;


        List<Expression> exprList = new ArrayList<Expression>();
        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1783:2: (e= stringFunction | strExpr1= simpleStringExpression ( PLUS (nextstrExpr= simpleStringExpression | ne= numberExpressionInPar | be= simpleBooleanExpression | ( listExpression )=>le= listExpression | te= typeExpression ) )* )
            int alt167=2;
            switch ( input.LA(1) ) {
            case REMOVESTRING:
                {
                alt167=1;
                }
                break;
            case Identifier:
                {
                int LA167_2 = input.LA(2);

                if ( (LA167_2==LPAREN) ) {
                    alt167=1;
                }
                else if ( (LA167_2==EOF||LA167_2==RPAREN||(LA167_2>=COMMA && LA167_2<=PLUS)||LA167_2==ASSIGN_EQUAL) ) {
                    alt167=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 167, 2, input);

                    throw nvae;
                }
                }
                break;
            case StringLiteral:
                {
                alt167=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 167, 0, input);

                throw nvae;
            }

            switch (alt167) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1784:2: e= stringFunction
                    {
                    pushFollow(FOLLOW_stringFunction_in_stringExpression10544);
                    e=stringFunction();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = e;
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1786:2: strExpr1= simpleStringExpression ( PLUS (nextstrExpr= simpleStringExpression | ne= numberExpressionInPar | be= simpleBooleanExpression | ( listExpression )=>le= listExpression | te= typeExpression ) )*
                    {
                    pushFollow(FOLLOW_simpleStringExpression_in_stringExpression10557);
                    strExpr1=simpleStringExpression();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      if (strExpr1!=null) exprList.add(strExpr1);
                    }
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1787:2: ( PLUS (nextstrExpr= simpleStringExpression | ne= numberExpressionInPar | be= simpleBooleanExpression | ( listExpression )=>le= listExpression | te= typeExpression ) )*
                    loop166:
                    do {
                        int alt166=2;
                        int LA166_0 = input.LA(1);

                        if ( (LA166_0==PLUS) ) {
                            alt166=1;
                        }


                        switch (alt166) {
                    	case 1 :
                    	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1787:3: PLUS (nextstrExpr= simpleStringExpression | ne= numberExpressionInPar | be= simpleBooleanExpression | ( listExpression )=>le= listExpression | te= typeExpression )
                    	    {
                    	    match(input,PLUS,FOLLOW_PLUS_in_stringExpression10563); if (state.failed) return expr;
                    	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1787:8: (nextstrExpr= simpleStringExpression | ne= numberExpressionInPar | be= simpleBooleanExpression | ( listExpression )=>le= listExpression | te= typeExpression )
                    	    int alt165=5;
                    	    int LA165_0 = input.LA(1);

                    	    if ( (LA165_0==StringLiteral) ) {
                    	        alt165=1;
                    	    }
                    	    else if ( (LA165_0==Identifier) ) {
                    	        int LA165_2 = input.LA(2);

                    	        if ( ((isVariableOfType(input.LT(1).getText(), "STRING"))) ) {
                    	            alt165=1;
                    	        }
                    	        else if ( ((isVariableOfType(input.LT(1).getText(), "BOOLEAN"))) ) {
                    	            alt165=3;
                    	        }
                    	        else if ( (((synpred26_TextMarkerParser()&&(isVariableOfType(input.LT(1).getText(), "BOOLEANLIST")))||(synpred26_TextMarkerParser()&&(isVariableOfType(input.LT(1).getText(), "TYPELIST")))||(synpred26_TextMarkerParser()&&(isVariableOfType(input.LT(1).getText(), "STRINGLIST")))||(synpred26_TextMarkerParser()&&(isVariableOfType(input.LT(1).getText(), "INTLIST")))||(synpred26_TextMarkerParser()&&(isVariableOfType(input.LT(1).getText(), "DOUBLELIST"))))) ) {
                    	            alt165=4;
                    	        }
                    	        else if ( (true) ) {
                    	            alt165=5;
                    	        }
                    	        else {
                    	            if (state.backtracking>0) {state.failed=true; return expr;}
                    	            NoViableAltException nvae =
                    	                new NoViableAltException("", 165, 2, input);

                    	            throw nvae;
                    	        }
                    	    }
                    	    else if ( (LA165_0==LPAREN) ) {
                    	        alt165=2;
                    	    }
                    	    else if ( ((LA165_0>=TRUE && LA165_0<=FALSE)) ) {
                    	        alt165=3;
                    	    }
                    	    else if ( (LA165_0==LCURLY) && (synpred26_TextMarkerParser())) {
                    	        alt165=4;
                    	    }
                    	    else if ( (LA165_0==BasicAnnotationType) ) {
                    	        alt165=5;
                    	    }
                    	    else {
                    	        if (state.backtracking>0) {state.failed=true; return expr;}
                    	        NoViableAltException nvae =
                    	            new NoViableAltException("", 165, 0, input);

                    	        throw nvae;
                    	    }
                    	    switch (alt165) {
                    	        case 1 :
                    	            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1787:9: nextstrExpr= simpleStringExpression
                    	            {
                    	            pushFollow(FOLLOW_simpleStringExpression_in_stringExpression10570);
                    	            nextstrExpr=simpleStringExpression();

                    	            state._fsp--;
                    	            if (state.failed) return expr;
                    	            if ( state.backtracking==0 ) {
                    	              if (nextstrExpr!=null) exprList.add(nextstrExpr);
                    	            }

                    	            }
                    	            break;
                    	        case 2 :
                    	            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1788:5: ne= numberExpressionInPar
                    	            {
                    	            pushFollow(FOLLOW_numberExpressionInPar_in_stringExpression10582);
                    	            ne=numberExpressionInPar();

                    	            state._fsp--;
                    	            if (state.failed) return expr;
                    	            if ( state.backtracking==0 ) {
                    	              if (ne!=null) exprList.add(ne);
                    	            }

                    	            }
                    	            break;
                    	        case 3 :
                    	            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1789:5: be= simpleBooleanExpression
                    	            {
                    	            pushFollow(FOLLOW_simpleBooleanExpression_in_stringExpression10594);
                    	            be=simpleBooleanExpression();

                    	            state._fsp--;
                    	            if (state.failed) return expr;
                    	            if ( state.backtracking==0 ) {
                    	              if (be!=null) exprList.add(be);
                    	            }

                    	            }
                    	            break;
                    	        case 4 :
                    	            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1790:5: ( listExpression )=>le= listExpression
                    	            {
                    	            pushFollow(FOLLOW_listExpression_in_stringExpression10611);
                    	            le=listExpression();

                    	            state._fsp--;
                    	            if (state.failed) return expr;
                    	            if ( state.backtracking==0 ) {
                    	              if (le!=null) exprList.add(le);
                    	            }

                    	            }
                    	            break;
                    	        case 5 :
                    	            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1791:5: te= typeExpression
                    	            {
                    	            pushFollow(FOLLOW_typeExpression_in_stringExpression10623);
                    	            te=typeExpression();

                    	            state._fsp--;
                    	            if (state.failed) return expr;
                    	            if ( state.backtracking==0 ) {
                    	              if (te!=null) exprList.add(te);
                    	            }

                    	            }
                    	            break;

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop166;
                        }
                    } while (true);

                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createStringExpression(exprList);
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
        }
        return expr;
    }
    // $ANTLR end "stringExpression"


    // $ANTLR start "stringFunction"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1798:1: stringFunction returns [Expression expr = null] : (name= REMOVESTRING LPAREN var= variable ( COMMA s= stringExpression )+ RPAREN | (e= externalStringFunction )=>e= externalStringFunction );
    public final Expression stringFunction() throws RecognitionException {
        Expression expr =  null;

        Token name=null;
        Expression var = null;

        Expression s = null;

        Expression e = null;


        List<Expression> list = new ArrayList<Expression>();
        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1800:1: (name= REMOVESTRING LPAREN var= variable ( COMMA s= stringExpression )+ RPAREN | (e= externalStringFunction )=>e= externalStringFunction )
            int alt169=2;
            int LA169_0 = input.LA(1);

            if ( (LA169_0==REMOVESTRING) ) {
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1801:2: name= REMOVESTRING LPAREN var= variable ( COMMA s= stringExpression )+ RPAREN
                    {
                    name=(Token)match(input,REMOVESTRING,FOLLOW_REMOVESTRING_in_stringFunction10660); if (state.failed) return expr;
                    match(input,LPAREN,FOLLOW_LPAREN_in_stringFunction10662); if (state.failed) return expr;
                    pushFollow(FOLLOW_variable_in_stringFunction10668);
                    var=variable();

                    state._fsp--;
                    if (state.failed) return expr;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1801:44: ( COMMA s= stringExpression )+
                    int cnt168=0;
                    loop168:
                    do {
                        int alt168=2;
                        int LA168_0 = input.LA(1);

                        if ( (LA168_0==COMMA) ) {
                            alt168=1;
                        }


                        switch (alt168) {
                    	case 1 :
                    	    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1801:45: COMMA s= stringExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_stringFunction10671); if (state.failed) return expr;
                    	    pushFollow(FOLLOW_stringExpression_in_stringFunction10677);
                    	    s=stringExpression();

                    	    state._fsp--;
                    	    if (state.failed) return expr;
                    	    if ( state.backtracking==0 ) {
                    	      list.add(s);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt168 >= 1 ) break loop168;
                    	    if (state.backtracking>0) {state.failed=true; return expr;}
                                EarlyExitException eee =
                                    new EarlyExitException(168, input);
                                throw eee;
                        }
                        cnt168++;
                    } while (true);

                    match(input,RPAREN,FOLLOW_RPAREN_in_stringFunction10682); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createStringFunction(name,var,list);
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1804:2: (e= externalStringFunction )=>e= externalStringFunction
                    {
                    pushFollow(FOLLOW_externalStringFunction_in_stringFunction10704);
                    e=externalStringFunction();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = e;
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
        }
        return expr;
    }
    // $ANTLR end "stringFunction"


    // $ANTLR start "externalStringFunction"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1808:1: externalStringFunction returns [Expression expr = null] : {...}?id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final Expression externalStringFunction() throws RecognitionException {
        Expression expr =  null;

        Token id=null;
        List<Expression> args = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1809:2: ({...}?id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1810:2: {...}?id= Identifier LPAREN args= varArgumentList RPAREN
            {
            if ( !((isVariableOfType(input.LT(1).getText(), "STRINGFUNCTION"))) ) {
                if (state.backtracking>0) {state.failed=true; return expr;}
                throw new FailedPredicateException(input, "externalStringFunction", "isVariableOfType(input.LT(1).getText(), \"STRINGFUNCTION\")");
            }
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalStringFunction10729); if (state.failed) return expr;
            match(input,LPAREN,FOLLOW_LPAREN_in_externalStringFunction10733); if (state.failed) return expr;
            pushFollow(FOLLOW_varArgumentList_in_externalStringFunction10740);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return expr;
            match(input,RPAREN,FOLLOW_RPAREN_in_externalStringFunction10743); if (state.failed) return expr;
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
        }
        return expr;
    }
    // $ANTLR end "externalStringFunction"


    // $ANTLR start "simpleStringExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1820:1: simpleStringExpression returns [Expression expr = null] : (lit= StringLiteral | {...}?variableId= Identifier );
    public final Expression simpleStringExpression() throws RecognitionException {
        Expression expr =  null;

        Token lit=null;
        Token variableId=null;

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1821:2: (lit= StringLiteral | {...}?variableId= Identifier )
            int alt170=2;
            int LA170_0 = input.LA(1);

            if ( (LA170_0==StringLiteral) ) {
                alt170=1;
            }
            else if ( (LA170_0==Identifier) ) {
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1822:2: lit= StringLiteral
                    {
                    lit=(Token)match(input,StringLiteral,FOLLOW_StringLiteral_in_simpleStringExpression10768); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createSimpleString(lit);
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1823:5: {...}?variableId= Identifier
                    {
                    if ( !((isVariableOfType(input.LT(1).getText(), "STRING"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleStringExpression", "isVariableOfType(input.LT(1).getText(), \"STRING\")");
                    }
                    variableId=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleStringExpression10783); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createStringVariableReference(variableId);
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
        }
        return expr;
    }
    // $ANTLR end "simpleStringExpression"


    // $ANTLR start "booleanExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1834:1: booleanExpression returns [Expression expr = null] : (bcE= composedBooleanExpression | sbE= simpleBooleanExpression );
    public final Expression booleanExpression() throws RecognitionException {
        Expression expr =  null;

        Expression bcE = null;

        Expression sbE = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1835:2: (bcE= composedBooleanExpression | sbE= simpleBooleanExpression )
            int alt171=2;
            switch ( input.LA(1) ) {
            case TRUE:
                {
                int LA171_1 = input.LA(2);

                if ( (LA171_1==EOF||LA171_1==RPAREN||(LA171_1>=COMMA && LA171_1<=SEMI)) ) {
                    alt171=2;
                }
                else if ( ((LA171_1>=EQUAL && LA171_1<=NOTEQUAL)) ) {
                    alt171=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 171, 1, input);

                    throw nvae;
                }
                }
                break;
            case FALSE:
                {
                int LA171_2 = input.LA(2);

                if ( (LA171_2==EOF||LA171_2==RPAREN||(LA171_2>=COMMA && LA171_2<=SEMI)) ) {
                    alt171=2;
                }
                else if ( ((LA171_2>=EQUAL && LA171_2<=NOTEQUAL)) ) {
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
            case Identifier:
                {
                int LA171_3 = input.LA(2);

                if ( (LA171_3==LPAREN||LA171_3==DOT||(LA171_3>=EQUAL && LA171_3<=NOTEQUAL)) ) {
                    alt171=1;
                }
                else if ( (LA171_3==EOF||LA171_3==RPAREN||(LA171_3>=COMMA && LA171_3<=SEMI)) ) {
                    alt171=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 171, 3, input);

                    throw nvae;
                }
                }
                break;
            case BasicAnnotationType:
            case XOR:
            case LPAREN:
                {
                alt171=1;
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1836:2: bcE= composedBooleanExpression
                    {
                    pushFollow(FOLLOW_composedBooleanExpression_in_booleanExpression10816);
                    bcE=composedBooleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = bcE;
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1837:4: sbE= simpleBooleanExpression
                    {
                    pushFollow(FOLLOW_simpleBooleanExpression_in_booleanExpression10827);
                    sbE=simpleBooleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = sbE;
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
        }
        return expr;
    }
    // $ANTLR end "booleanExpression"


    // $ANTLR start "simpleBooleanExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1840:1: simpleBooleanExpression returns [Expression expr = null] : (lbE= literalBooleanExpression | {...}? (variableId= Identifier ) ) ;
    public final Expression simpleBooleanExpression() throws RecognitionException {
        Expression expr =  null;

        Token variableId=null;
        BooleanLiteral lbE = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1841:2: ( (lbE= literalBooleanExpression | {...}? (variableId= Identifier ) ) )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1842:3: (lbE= literalBooleanExpression | {...}? (variableId= Identifier ) )
            {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1842:3: (lbE= literalBooleanExpression | {...}? (variableId= Identifier ) )
            int alt172=2;
            int LA172_0 = input.LA(1);

            if ( ((LA172_0>=TRUE && LA172_0<=FALSE)) ) {
                alt172=1;
            }
            else if ( (LA172_0==Identifier) ) {
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1842:4: lbE= literalBooleanExpression
                    {
                    pushFollow(FOLLOW_literalBooleanExpression_in_simpleBooleanExpression10852);
                    lbE=literalBooleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = lbE;
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1843:4: {...}? (variableId= Identifier )
                    {
                    if ( !((isVariableOfType(input.LT(1).getText(), "BOOLEAN"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleBooleanExpression", "isVariableOfType(input.LT(1).getText(), \"BOOLEAN\")");
                    }
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1843:57: (variableId= Identifier )
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1843:58: variableId= Identifier
                    {
                    variableId=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleBooleanExpression10865); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createBooleanVariableReference(variableId);
                    }

                    }


                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              expr = ExpressionFactory.createBooleanExpression(expr);
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
        }
        return expr;
    }
    // $ANTLR end "simpleBooleanExpression"


    // $ANTLR start "composedBooleanExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1850:1: composedBooleanExpression returns [Expression expr = null] : ( (e2= booleanCompare )=>e2= booleanCompare | (bte= booleanTypeExpression )=>bte= booleanTypeExpression | (bne= booleanNumberExpression )=>bne= booleanNumberExpression | e1= booleanFunction );
    public final Expression composedBooleanExpression() throws RecognitionException {
        Expression expr =  null;

        Expression e2 = null;

        Expression bte = null;

        Expression bne = null;

        Expression e1 = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1851:2: ( (e2= booleanCompare )=>e2= booleanCompare | (bte= booleanTypeExpression )=>bte= booleanTypeExpression | (bne= booleanNumberExpression )=>bne= booleanNumberExpression | e1= booleanFunction )
            int alt173=4;
            int LA173_0 = input.LA(1);

            if ( (LA173_0==TRUE) && (synpred28_TextMarkerParser())) {
                alt173=1;
            }
            else if ( (LA173_0==FALSE) && (synpred28_TextMarkerParser())) {
                alt173=1;
            }
            else if ( (LA173_0==Identifier) ) {
                int LA173_3 = input.LA(2);

                if ( ((synpred28_TextMarkerParser()&&(isVariableOfType(input.LT(1).getText(), "BOOLEAN")))) ) {
                    alt173=1;
                }
                else if ( (((synpred29_TextMarkerParser()&&(isVariableOfType(input.LT(1).getText(), "TYPEFUNCTION")))||synpred29_TextMarkerParser())) ) {
                    alt173=2;
                }
                else if ( ((isVariableOfType(input.LT(1).getText(), "BOOLEANFUNCTION"))) ) {
                    alt173=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 173, 3, input);

                    throw nvae;
                }
            }
            else if ( (LA173_0==BasicAnnotationType) && (synpred29_TextMarkerParser())) {
                alt173=2;
            }
            else if ( (LA173_0==LPAREN) && (synpred30_TextMarkerParser())) {
                alt173=3;
            }
            else if ( (LA173_0==XOR) ) {
                alt173=4;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 173, 0, input);

                throw nvae;
            }
            switch (alt173) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1852:2: (e2= booleanCompare )=>e2= booleanCompare
                    {
                    pushFollow(FOLLOW_booleanCompare_in_composedBooleanExpression10911);
                    e2=booleanCompare();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = e2;
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1853:4: (bte= booleanTypeExpression )=>bte= booleanTypeExpression
                    {
                    pushFollow(FOLLOW_booleanTypeExpression_in_composedBooleanExpression10931);
                    bte=booleanTypeExpression();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = bte;
                    }

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1854:4: (bne= booleanNumberExpression )=>bne= booleanNumberExpression
                    {
                    pushFollow(FOLLOW_booleanNumberExpression_in_composedBooleanExpression10950);
                    bne=booleanNumberExpression();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = bne;
                    }

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1855:4: e1= booleanFunction
                    {
                    pushFollow(FOLLOW_booleanFunction_in_composedBooleanExpression10960);
                    e1=booleanFunction();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = e1;
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
        }
        return expr;
    }
    // $ANTLR end "composedBooleanExpression"


    // $ANTLR start "booleanFunction"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1859:1: booleanFunction returns [Expression expr = null] : ( (op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN ) | (e= externalBooleanFunction )=>e= externalBooleanFunction );
    public final Expression booleanFunction() throws RecognitionException {
        Expression expr =  null;

        Token op=null;
        Expression e1 = null;

        Expression e2 = null;

        Expression e = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1861:2: ( (op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN ) | (e= externalBooleanFunction )=>e= externalBooleanFunction )
            int alt174=2;
            int LA174_0 = input.LA(1);

            if ( (LA174_0==XOR) ) {
                alt174=1;
            }
            else if ( (LA174_0==Identifier) && (synpred31_TextMarkerParser())) {
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1862:2: (op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN )
                    {
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1862:2: (op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN )
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1862:3: op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN
                    {
                    op=(Token)match(input,XOR,FOLLOW_XOR_in_booleanFunction10985); if (state.failed) return expr;
                    match(input,LPAREN,FOLLOW_LPAREN_in_booleanFunction10987); if (state.failed) return expr;
                    pushFollow(FOLLOW_booleanExpression_in_booleanFunction10993);
                    e1=booleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;
                    match(input,COMMA,FOLLOW_COMMA_in_booleanFunction10995); if (state.failed) return expr;
                    pushFollow(FOLLOW_booleanExpression_in_booleanFunction11001);
                    e2=booleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;
                    match(input,RPAREN,FOLLOW_RPAREN_in_booleanFunction11003); if (state.failed) return expr;

                    }

                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createBooleanFunction(op,e1,e2);
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1864:4: (e= externalBooleanFunction )=>e= externalBooleanFunction
                    {
                    pushFollow(FOLLOW_externalBooleanFunction_in_booleanFunction11025);
                    e=externalBooleanFunction();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = e;
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
        }
        return expr;
    }
    // $ANTLR end "booleanFunction"


    // $ANTLR start "externalBooleanFunction"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1868:1: externalBooleanFunction returns [Expression expr = null] : {...}?id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final Expression externalBooleanFunction() throws RecognitionException {
        Expression expr =  null;

        Token id=null;
        List<Expression> args = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1869:2: ({...}?id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1870:2: {...}?id= Identifier LPAREN args= varArgumentList RPAREN
            {
            if ( !((isVariableOfType(input.LT(1).getText(), "BOOLEANFUNCTION"))) ) {
                if (state.backtracking>0) {state.failed=true; return expr;}
                throw new FailedPredicateException(input, "externalBooleanFunction", "isVariableOfType(input.LT(1).getText(), \"BOOLEANFUNCTION\")");
            }
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalBooleanFunction11051); if (state.failed) return expr;
            match(input,LPAREN,FOLLOW_LPAREN_in_externalBooleanFunction11054); if (state.failed) return expr;
            pushFollow(FOLLOW_varArgumentList_in_externalBooleanFunction11061);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return expr;
            match(input,RPAREN,FOLLOW_RPAREN_in_externalBooleanFunction11065); if (state.failed) return expr;
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
        }
        return expr;
    }
    // $ANTLR end "externalBooleanFunction"


    // $ANTLR start "booleanCompare"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1880:1: booleanCompare returns [Expression expr = null] : (e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression ) ;
    public final Expression booleanCompare() throws RecognitionException {
        Expression expr =  null;

        Token op=null;
        Expression e1 = null;

        Expression e2 = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1881:2: ( (e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression ) )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1882:2: (e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression )
            {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1882:2: (e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1882:3: e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression
            {
            pushFollow(FOLLOW_simpleBooleanExpression_in_booleanCompare11090);
            e1=simpleBooleanExpression();

            state._fsp--;
            if (state.failed) return expr;
            op=(Token)input.LT(1);
            if ( (input.LA(1)>=EQUAL && input.LA(1)<=NOTEQUAL) ) {
                input.consume();
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }

            pushFollow(FOLLOW_booleanExpression_in_booleanCompare11108);
            e2=booleanExpression();

            state._fsp--;
            if (state.failed) return expr;

            }

            if ( state.backtracking==0 ) {
              expr = ExpressionFactory.createBooleanFunction(op,e1,e2);
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
        }
        return expr;
    }
    // $ANTLR end "booleanCompare"


    // $ANTLR start "literalBooleanExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1887:1: literalBooleanExpression returns [BooleanLiteral expr = null] : (value= TRUE | value= FALSE ) ;
    public final BooleanLiteral literalBooleanExpression() throws RecognitionException {
        BooleanLiteral expr =  null;

        Token value=null;

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1888:2: ( (value= TRUE | value= FALSE ) )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1889:2: (value= TRUE | value= FALSE )
            {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1889:2: (value= TRUE | value= FALSE )
            int alt175=2;
            int LA175_0 = input.LA(1);

            if ( (LA175_0==TRUE) ) {
                alt175=1;
            }
            else if ( (LA175_0==FALSE) ) {
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1889:3: value= TRUE
                    {
                    value=(Token)match(input,TRUE,FOLLOW_TRUE_in_literalBooleanExpression11135); if (state.failed) return expr;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1890:4: value= FALSE
                    {
                    value=(Token)match(input,FALSE,FOLLOW_FALSE_in_literalBooleanExpression11145); if (state.failed) return expr;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              expr = ExpressionFactory.createSimpleBooleanExpression(value);
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
        }
        return expr;
    }
    // $ANTLR end "literalBooleanExpression"


    // $ANTLR start "booleanTypeExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1895:1: booleanTypeExpression returns [Expression expr = null] : e1= typeExpression op= ( EQUAL | NOTEQUAL ) e2= typeExpression ;
    public final Expression booleanTypeExpression() throws RecognitionException {
        Expression expr =  null;

        Token op=null;
        Expression e1 = null;

        Expression e2 = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1896:2: (e1= typeExpression op= ( EQUAL | NOTEQUAL ) e2= typeExpression )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1897:2: e1= typeExpression op= ( EQUAL | NOTEQUAL ) e2= typeExpression
            {
            pushFollow(FOLLOW_typeExpression_in_booleanTypeExpression11172);
            e1=typeExpression();

            state._fsp--;
            if (state.failed) return expr;
            op=(Token)input.LT(1);
            if ( (input.LA(1)>=EQUAL && input.LA(1)<=NOTEQUAL) ) {
                input.consume();
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }

            pushFollow(FOLLOW_typeExpression_in_booleanTypeExpression11192);
            e2=typeExpression();

            state._fsp--;
            if (state.failed) return expr;
            if ( state.backtracking==0 ) {
              expr = ExpressionFactory.createBooleanTypeExpression(e1,op,e2);
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
        }
        return expr;
    }
    // $ANTLR end "booleanTypeExpression"


    // $ANTLR start "booleanNumberExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1905:1: booleanNumberExpression returns [Expression expr = null] : LPAREN e1= numberExpression op= ( LESS | GREATER | GREATEREQUAL | LESSEQUAL | EQUAL | NOTEQUAL ) e2= numberExpression RPAREN ;
    public final Expression booleanNumberExpression() throws RecognitionException {
        Expression expr =  null;

        Token op=null;
        Expression e1 = null;

        Expression e2 = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1906:2: ( LPAREN e1= numberExpression op= ( LESS | GREATER | GREATEREQUAL | LESSEQUAL | EQUAL | NOTEQUAL ) e2= numberExpression RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1907:2: LPAREN e1= numberExpression op= ( LESS | GREATER | GREATEREQUAL | LESSEQUAL | EQUAL | NOTEQUAL ) e2= numberExpression RPAREN
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_booleanNumberExpression11215); if (state.failed) return expr;
            pushFollow(FOLLOW_numberExpression_in_booleanNumberExpression11222);
            e1=numberExpression();

            state._fsp--;
            if (state.failed) return expr;
            op=(Token)input.LT(1);
            if ( (input.LA(1)>=LESS && input.LA(1)<=GREATER)||(input.LA(1)>=EQUAL && input.LA(1)<=NOTEQUAL)||(input.LA(1)>=LESSEQUAL && input.LA(1)<=GREATEREQUAL) ) {
                input.consume();
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }

            pushFollow(FOLLOW_numberExpression_in_booleanNumberExpression11258);
            e2=numberExpression();

            state._fsp--;
            if (state.failed) return expr;
            match(input,RPAREN,FOLLOW_RPAREN_in_booleanNumberExpression11261); if (state.failed) return expr;
            if ( state.backtracking==0 ) {
              expr = ExpressionFactory.createBooleanNumberExpression(e1,op,e2);
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
        }
        return expr;
    }
    // $ANTLR end "booleanNumberExpression"


    // $ANTLR start "genericVariableReference"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1915:1: genericVariableReference returns [Expression varRef] : id= Identifier ;
    public final Expression genericVariableReference() throws RecognitionException {
        Expression varRef = null;

        Token id=null;

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1916:1: (id= Identifier )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1917:3: id= Identifier
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_genericVariableReference11281); if (state.failed) return varRef;
            if ( state.backtracking==0 ) {
              return ExpressionFactory.createGenericVariableReference(id);
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
        }
        return varRef;
    }
    // $ANTLR end "genericVariableReference"

    // $ANTLR start synpred3_TextMarkerParser
    public final void synpred3_TextMarkerParser_fragment() throws RecognitionException {   
        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:593:2: ( booleanListExpression )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:593:3: booleanListExpression
        {
        pushFollow(FOLLOW_booleanListExpression_in_synpred3_TextMarkerParser2277);
        booleanListExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred3_TextMarkerParser

    // $ANTLR start synpred4_TextMarkerParser
    public final void synpred4_TextMarkerParser_fragment() throws RecognitionException {   
        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:594:4: ( intListExpression )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:594:5: intListExpression
        {
        pushFollow(FOLLOW_intListExpression_in_synpred4_TextMarkerParser2293);
        intListExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred4_TextMarkerParser

    // $ANTLR start synpred5_TextMarkerParser
    public final void synpred5_TextMarkerParser_fragment() throws RecognitionException {   
        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:595:4: ( doubleListExpression )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:595:5: doubleListExpression
        {
        pushFollow(FOLLOW_doubleListExpression_in_synpred5_TextMarkerParser2309);
        doubleListExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred5_TextMarkerParser

    // $ANTLR start synpred6_TextMarkerParser
    public final void synpred6_TextMarkerParser_fragment() throws RecognitionException {   
        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:596:4: ( stringListExpression )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:596:5: stringListExpression
        {
        pushFollow(FOLLOW_stringListExpression_in_synpred6_TextMarkerParser2325);
        stringListExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred6_TextMarkerParser

    // $ANTLR start synpred7_TextMarkerParser
    public final void synpred7_TextMarkerParser_fragment() throws RecognitionException {   
        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:597:4: ( typeListExpression )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:597:5: typeListExpression
        {
        pushFollow(FOLLOW_typeListExpression_in_synpred7_TextMarkerParser2341);
        typeListExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred7_TextMarkerParser

    // $ANTLR start synpred8_TextMarkerParser
    public final void synpred8_TextMarkerParser_fragment() throws RecognitionException {   
        Expression e1 = null;


        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:639:2: (e1= doubleListExpression )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:639:3: e1= doubleListExpression
        {
        pushFollow(FOLLOW_doubleListExpression_in_synpred8_TextMarkerParser2554);
        e1=doubleListExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred8_TextMarkerParser

    // $ANTLR start synpred10_TextMarkerParser
    public final void synpred10_TextMarkerParser_fragment() throws RecognitionException {   
        TextMarkerCondition c = null;


        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:790:4: (c= externalCondition )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:790:5: c= externalCondition
        {
        pushFollow(FOLLOW_externalCondition_in_synpred10_TextMarkerParser3424);
        c=externalCondition();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred10_TextMarkerParser

    // $ANTLR start synpred11_TextMarkerParser
    public final void synpred11_TextMarkerParser_fragment() throws RecognitionException {   
        Token name=null;
        Expression type = null;

        Expression a = null;

        Expression min = null;

        Expression max = null;

        Expression var = null;


        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:850:6: (name= COUNT LPAREN type= listExpression COMMA a= argument ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:850:6: name= COUNT LPAREN type= listExpression COMMA a= argument ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
        {
        name=(Token)match(input,COUNT,FOLLOW_COUNT_in_synpred11_TextMarkerParser3850); if (state.failed) return ;
        match(input,LPAREN,FOLLOW_LPAREN_in_synpred11_TextMarkerParser3852); if (state.failed) return ;
        pushFollow(FOLLOW_listExpression_in_synpred11_TextMarkerParser3858);
        type=listExpression();

        state._fsp--;
        if (state.failed) return ;
        match(input,COMMA,FOLLOW_COMMA_in_synpred11_TextMarkerParser3873); if (state.failed) return ;
        pushFollow(FOLLOW_argument_in_synpred11_TextMarkerParser3879);
        a=argument();

        state._fsp--;
        if (state.failed) return ;
        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:854:6: ( COMMA min= numberExpression COMMA max= numberExpression )?
        int alt176=2;
        int LA176_0 = input.LA(1);

        if ( (LA176_0==COMMA) ) {
            int LA176_1 = input.LA(2);

            if ( (LA176_1==Identifier) ) {
                int LA176_3 = input.LA(3);

                if ( (LA176_3==LPAREN||LA176_3==COMMA||(LA176_3>=PLUS && LA176_3<=SLASH)||LA176_3==PERCENT) ) {
                    alt176=1;
                }
            }
            else if ( ((LA176_1>=EXP && LA176_1<=TAN)||LA176_1==DecimalLiteral||LA176_1==FloatingPointLiteral||LA176_1==LPAREN||LA176_1==MINUS) ) {
                alt176=1;
            }
        }
        switch (alt176) {
            case 1 :
                // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:854:7: COMMA min= numberExpression COMMA max= numberExpression
                {
                match(input,COMMA,FOLLOW_COMMA_in_synpred11_TextMarkerParser3895); if (state.failed) return ;
                pushFollow(FOLLOW_numberExpression_in_synpred11_TextMarkerParser3901);
                min=numberExpression();

                state._fsp--;
                if (state.failed) return ;
                match(input,COMMA,FOLLOW_COMMA_in_synpred11_TextMarkerParser3903); if (state.failed) return ;
                pushFollow(FOLLOW_numberExpression_in_synpred11_TextMarkerParser3909);
                max=numberExpression();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:856:6: ( COMMA var= numberVariable )?
        int alt177=2;
        int LA177_0 = input.LA(1);

        if ( (LA177_0==COMMA) ) {
            alt177=1;
        }
        switch (alt177) {
            case 1 :
                // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:856:7: COMMA var= numberVariable
                {
                match(input,COMMA,FOLLOW_COMMA_in_synpred11_TextMarkerParser3927); if (state.failed) return ;
                pushFollow(FOLLOW_numberVariable_in_synpred11_TextMarkerParser3933);
                var=numberVariable();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        match(input,RPAREN,FOLLOW_RPAREN_in_synpred11_TextMarkerParser3949); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred11_TextMarkerParser

    // $ANTLR start synpred12_TextMarkerParser
    public final void synpred12_TextMarkerParser_fragment() throws RecognitionException {   
        Expression list2 = null;


        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:895:27: (list2= stringListExpression )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:895:28: list2= stringListExpression
        {
        pushFollow(FOLLOW_stringListExpression_in_synpred12_TextMarkerParser4314);
        list2=stringListExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred12_TextMarkerParser

    // $ANTLR start synpred13_TextMarkerParser
    public final void synpred13_TextMarkerParser_fragment() throws RecognitionException {   
        TextMarkerAction a = null;


        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1099:4: (a= externalAction )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1099:5: a= externalAction
        {
        pushFollow(FOLLOW_externalAction_in_synpred13_TextMarkerParser6119);
        a=externalAction();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred13_TextMarkerParser

    // $ANTLR start synpred14_TextMarkerParser
    public final void synpred14_TextMarkerParser_fragment() throws RecognitionException {   
        Expression index = null;


        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1136:54: ( COMMA index= numberExpression )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1136:55: COMMA index= numberExpression
        {
        match(input,COMMA,FOLLOW_COMMA_in_synpred14_TextMarkerParser6287); if (state.failed) return ;
        pushFollow(FOLLOW_numberExpression_in_synpred14_TextMarkerParser6293);
        index=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred14_TextMarkerParser

    // $ANTLR start synpred15_TextMarkerParser
    public final void synpred15_TextMarkerParser_fragment() throws RecognitionException {   
        Expression index = null;


        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1175:54: ( COMMA index= numberExpression )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1175:55: COMMA index= numberExpression
        {
        match(input,COMMA,FOLLOW_COMMA_in_synpred15_TextMarkerParser6613); if (state.failed) return ;
        pushFollow(FOLLOW_numberExpression_in_synpred15_TextMarkerParser6619);
        index=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred15_TextMarkerParser

    // $ANTLR start synpred19_TextMarkerParser
    public final void synpred19_TextMarkerParser_fragment() throws RecognitionException {   
        Expression score = null;


        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1280:29: (score= numberExpression )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1280:30: score= numberExpression
        {
        pushFollow(FOLLOW_numberExpression_in_synpred19_TextMarkerParser7447);
        score=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred19_TextMarkerParser

    // $ANTLR start synpred20_TextMarkerParser
    public final void synpred20_TextMarkerParser_fragment() throws RecognitionException {   
        Expression type = null;


        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1280:92: (type= typeExpression )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1280:93: type= typeExpression
        {
        pushFollow(FOLLOW_typeExpression_in_synpred20_TextMarkerParser7467);
        type=typeExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred20_TextMarkerParser

    // $ANTLR start synpred22_TextMarkerParser
    public final void synpred22_TextMarkerParser_fragment() throws RecognitionException {   
        Expression a4 = null;


        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1596:3: (a4= stringExpression )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1596:3: a4= stringExpression
        {
        pushFollow(FOLLOW_stringExpression_in_synpred22_TextMarkerParser9660);
        a4=stringExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred22_TextMarkerParser

    // $ANTLR start synpred23_TextMarkerParser
    public final void synpred23_TextMarkerParser_fragment() throws RecognitionException {   
        Expression a2 = null;


        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1597:4: (a2= booleanExpression )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1597:4: a2= booleanExpression
        {
        pushFollow(FOLLOW_booleanExpression_in_synpred23_TextMarkerParser9671);
        a2=booleanExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred23_TextMarkerParser

    // $ANTLR start synpred24_TextMarkerParser
    public final void synpred24_TextMarkerParser_fragment() throws RecognitionException {   
        Expression a3 = null;


        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1598:4: (a3= numberExpression )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1598:4: a3= numberExpression
        {
        pushFollow(FOLLOW_numberExpression_in_synpred24_TextMarkerParser9682);
        a3=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred24_TextMarkerParser

    // $ANTLR start synpred25_TextMarkerParser
    public final void synpred25_TextMarkerParser_fragment() throws RecognitionException {   
        Expression e = null;


        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1756:4: (e= externalNumberFunction )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1756:5: e= externalNumberFunction
        {
        pushFollow(FOLLOW_externalNumberFunction_in_synpred25_TextMarkerParser10415);
        e=externalNumberFunction();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred25_TextMarkerParser

    // $ANTLR start synpred26_TextMarkerParser
    public final void synpred26_TextMarkerParser_fragment() throws RecognitionException {   
        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1790:5: ( listExpression )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1790:6: listExpression
        {
        pushFollow(FOLLOW_listExpression_in_synpred26_TextMarkerParser10603);
        listExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred26_TextMarkerParser

    // $ANTLR start synpred27_TextMarkerParser
    public final void synpred27_TextMarkerParser_fragment() throws RecognitionException {   
        Expression e = null;


        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1804:2: (e= externalStringFunction )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1804:3: e= externalStringFunction
        {
        pushFollow(FOLLOW_externalStringFunction_in_synpred27_TextMarkerParser10696);
        e=externalStringFunction();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred27_TextMarkerParser

    // $ANTLR start synpred28_TextMarkerParser
    public final void synpred28_TextMarkerParser_fragment() throws RecognitionException {   
        Expression e2 = null;


        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1852:2: (e2= booleanCompare )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1852:3: e2= booleanCompare
        {
        pushFollow(FOLLOW_booleanCompare_in_synpred28_TextMarkerParser10903);
        e2=booleanCompare();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred28_TextMarkerParser

    // $ANTLR start synpred29_TextMarkerParser
    public final void synpred29_TextMarkerParser_fragment() throws RecognitionException {   
        Expression bte = null;


        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1853:4: (bte= booleanTypeExpression )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1853:5: bte= booleanTypeExpression
        {
        pushFollow(FOLLOW_booleanTypeExpression_in_synpred29_TextMarkerParser10923);
        bte=booleanTypeExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred29_TextMarkerParser

    // $ANTLR start synpred30_TextMarkerParser
    public final void synpred30_TextMarkerParser_fragment() throws RecognitionException {   
        Expression bne = null;


        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1854:4: (bne= booleanNumberExpression )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1854:5: bne= booleanNumberExpression
        {
        pushFollow(FOLLOW_booleanNumberExpression_in_synpred30_TextMarkerParser10942);
        bne=booleanNumberExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred30_TextMarkerParser

    // $ANTLR start synpred31_TextMarkerParser
    public final void synpred31_TextMarkerParser_fragment() throws RecognitionException {   
        Expression e = null;


        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1864:4: (e= externalBooleanFunction )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.dltk.core\\src\\de\\uniwue\\dltk\\textmarker\\internal\\core\\parsers\\TextMarkerParser.g:1864:5: e= externalBooleanFunction
        {
        pushFollow(FOLLOW_externalBooleanFunction_in_synpred31_TextMarkerParser11017);
        e=externalBooleanFunction();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred31_TextMarkerParser

    // Delegated rules

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
    public final boolean synpred3_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred3_TextMarkerParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred4_TextMarkerParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred4_TextMarkerParser_fragment(); // can never throw exception
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
    protected DFA71 dfa71 = new DFA71(this);
    protected DFA105 dfa105 = new DFA105(this);
    protected DFA107 dfa107 = new DFA107(this);
    protected DFA114 dfa114 = new DFA114(this);
    protected DFA148 dfa148 = new DFA148(this);
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
            return "425:2: (declareToken= DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* end= SEMI | declareToken= DECLARE type= annotationType id= Identifier ( LPAREN (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI )";
        }
    }
    static final String DFA71_eotS =
        "\40\uffff";
    static final String DFA71_eofS =
        "\40\uffff";
    static final String DFA71_minS =
        "\1\10\34\uffff\1\113\2\uffff";
    static final String DFA71_maxS =
        "\1\u0088\34\uffff\1\u0085\2\uffff";
    static final String DFA71_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1"+
        "\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31"+
        "\1\32\1\33\1\34\1\uffff\1\35\1\36";
    static final String DFA71_specialS =
        "\35\uffff\1\0\2\uffff}>";
    static final String[] DFA71_transitionS = {
            "\1\2\3\uffff\1\1\1\3\1\4\1\21\1\5\1\6\1\7\1\10\1\11\1\12\1"+
            "\14\1\15\1\33\1\16\1\17\1\20\1\22\1\23\1\24\1\25\33\uffff\1"+
            "\27\1\30\1\26\1\31\1\32\1\13\6\uffff\1\34\62\uffff\1\35\15\uffff"+
            "\1\13",
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
            "\1\37\57\uffff\1\36\1\37\3\uffff\1\37\4\uffff\1\37",
            "",
            ""
    };

    static final short[] DFA71_eot = DFA.unpackEncodedString(DFA71_eotS);
    static final short[] DFA71_eof = DFA.unpackEncodedString(DFA71_eofS);
    static final char[] DFA71_min = DFA.unpackEncodedStringToUnsignedChars(DFA71_minS);
    static final char[] DFA71_max = DFA.unpackEncodedStringToUnsignedChars(DFA71_maxS);
    static final short[] DFA71_accept = DFA.unpackEncodedString(DFA71_acceptS);
    static final short[] DFA71_special = DFA.unpackEncodedString(DFA71_specialS);
    static final short[][] DFA71_transition;

    static {
        int numStates = DFA71_transitionS.length;
        DFA71_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA71_transition[i] = DFA.unpackEncodedString(DFA71_transitionS[i]);
        }
    }

    class DFA71 extends DFA {

        public DFA71(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 71;
            this.eot = DFA71_eot;
            this.eof = DFA71_eof;
            this.min = DFA71_min;
            this.max = DFA71_max;
            this.accept = DFA71_accept;
            this.special = DFA71_special;
            this.transition = DFA71_transition;
        }
        public String getDescription() {
            return "761:2: (c= conditionAnd | c= conditionContains | c= conditionContextCount | c= conditionCount | c= conditionCurrentCount | c= conditionInList | c= conditionIsInTag | c= conditionLast | c= conditionMofN | c= conditionNear | c= conditionNot | c= conditionOr | c= conditionPartOf | c= conditionPosition | c= conditionRegExp | c= conditionScore | c= conditionTotalCount | c= conditionVote | c= conditionIf | c= conditionFeature | c= conditionParse | c= conditionIs | c= conditionBefore | c= conditionAfter | c= conditionStartsWith | c= conditionEndsWith | c= conditionPartOfNeq | c= conditionSize | (c= externalCondition )=>c= externalCondition | c= variableCondition )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA71_29 = input.LA(1);

                         
                        int index71_29 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA71_29==LPAREN) && (synpred10_TextMarkerParser())) {s = 30;}

                        else if ( (LA71_29==THEN||LA71_29==RPAREN||LA71_29==RCURLY||LA71_29==COMMA) ) {s = 31;}

                         
                        input.seek(index71_29);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 71, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA105_eotS =
        "\47\uffff";
    static final String DFA105_eofS =
        "\47\uffff";
    static final String DFA105_minS =
        "\1\7\43\uffff\1\173\2\uffff";
    static final String DFA105_maxS =
        "\1\172\43\uffff\1\u0085\2\uffff";
    static final String DFA105_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1"+
        "\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31"+
        "\1\32\1\33\1\34\1\35\1\36\1\37\1\40\1\41\1\42\1\43\1\uffff\1\44"+
        "\1\45";
    static final String DFA105_specialS =
        "\44\uffff\1\0\2\uffff}>";
    static final String[] DFA105_transitionS = {
            "\1\27\30\uffff\1\15\1\30\1\16\1\uffff\1\1\1\2\1\3\1\4\1\5\1"+
            "\26\1\6\1\32\1\7\1\10\1\11\1\12\1\13\1\14\1\17\1\31\1\20\1\21"+
            "\1\22\1\23\1\24\1\25\1\43\6\uffff\1\33\1\34\1\35\1\36\1\37\1"+
            "\40\1\uffff\1\41\1\uffff\1\42\57\uffff\1\44",
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
            "",
            "",
            "",
            "",
            "",
            "\1\45\1\46\3\uffff\1\46\4\uffff\1\46",
            "",
            ""
    };

    static final short[] DFA105_eot = DFA.unpackEncodedString(DFA105_eotS);
    static final short[] DFA105_eof = DFA.unpackEncodedString(DFA105_eofS);
    static final char[] DFA105_min = DFA.unpackEncodedStringToUnsignedChars(DFA105_minS);
    static final char[] DFA105_max = DFA.unpackEncodedStringToUnsignedChars(DFA105_maxS);
    static final short[] DFA105_accept = DFA.unpackEncodedString(DFA105_acceptS);
    static final short[] DFA105_special = DFA.unpackEncodedString(DFA105_specialS);
    static final short[][] DFA105_transition;

    static {
        int numStates = DFA105_transitionS.length;
        DFA105_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA105_transition[i] = DFA.unpackEncodedString(DFA105_transitionS[i]);
        }
    }

    class DFA105 extends DFA {

        public DFA105(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 105;
            this.eot = DFA105_eot;
            this.eof = DFA105_eof;
            this.min = DFA105_min;
            this.max = DFA105_max;
            this.accept = DFA105_accept;
            this.special = DFA105_special;
            this.transition = DFA105_transition;
        }
        public String getDescription() {
            return "1063:2: (a= actionColor | a= actionDel | a= actionLog | a= actionMark | a= actionMarkScore | a= actionMarkFast | a= actionMarkLast | a= actionReplace | a= actionRetainMarkup | a= actionRetainType | a= actionFilterMarkup | a= actionFilterType | a= actionCreate | a= actionFill | a= actionCall | a= actionAssign | a= actionSetFeature | a= actionGetFeature | a= actionUnmark | a= actionUnmarkAll | a= actionTransfer | a= actionMarkOnce | a= actionTrie | a= actionGather | a= actionExec | a= actionMarkTable | a= actionAdd | a= actionRemove | a= actionRemoveDuplicate | a= actionMerge | a= actionGet | a= actionGetList | a= actionMatchedText | a= actionClear | a= actionExpand | (a= externalAction )=>a= externalAction | a= variableAction )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA105_36 = input.LA(1);

                         
                        int index105_36 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA105_36==LPAREN) && (synpred13_TextMarkerParser())) {s = 37;}

                        else if ( (LA105_36==RPAREN||LA105_36==RCURLY||LA105_36==COMMA) ) {s = 38;}

                         
                        input.seek(index105_36);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 105, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA107_eotS =
        "\12\uffff";
    static final String DFA107_eofS =
        "\12\uffff";
    static final String DFA107_minS =
        "\1\111\1\uffff\1\0\7\uffff";
    static final String DFA107_maxS =
        "\1\u0088\1\uffff\1\0\7\uffff";
    static final String DFA107_acceptS =
        "\1\uffff\1\1\5\uffff\1\2\2\uffff";
    static final String DFA107_specialS =
        "\2\uffff\1\0\7\uffff}>";
    static final String[] DFA107_transitionS = {
            "\1\7\30\uffff\5\1\6\uffff\1\1\3\uffff\1\1\2\uffff\1\7\5\uffff"+
            "\1\2\1\1\1\7\13\uffff\1\1",
            "",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA107_eot = DFA.unpackEncodedString(DFA107_eotS);
    static final short[] DFA107_eof = DFA.unpackEncodedString(DFA107_eofS);
    static final char[] DFA107_min = DFA.unpackEncodedStringToUnsignedChars(DFA107_minS);
    static final char[] DFA107_max = DFA.unpackEncodedStringToUnsignedChars(DFA107_maxS);
    static final short[] DFA107_accept = DFA.unpackEncodedString(DFA107_acceptS);
    static final short[] DFA107_special = DFA.unpackEncodedString(DFA107_specialS);
    static final short[][] DFA107_transition;

    static {
        int numStates = DFA107_transitionS.length;
        DFA107_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA107_transition[i] = DFA.unpackEncodedString(DFA107_transitionS[i]);
        }
    }

    class DFA107 extends DFA {

        public DFA107(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 107;
            this.eot = DFA107_eot;
            this.eof = DFA107_eof;
            this.min = DFA107_min;
            this.max = DFA107_max;
            this.accept = DFA107_accept;
            this.special = DFA107_special;
            this.transition = DFA107_transition;
        }
        public String getDescription() {
            return "1136:5: (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA107_2 = input.LA(1);

                         
                        int index107_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (((isVariableOfType(input.LT(1).getText(), "INT"))||(isVariableOfType(input.LT(1).getText(), "DOUBLE"))||(isVariableOfType(input.LT(1).getText(), "NUMBERFUNCTION")))) ) {s = 1;}

                        else if ( (((isVariableOfType(input.LT(1).getText(), "STRINGFUNCTION"))||(isVariableOfType(input.LT(1).getText(), "STRING")))) ) {s = 7;}

                         
                        input.seek(index107_2);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 107, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA114_eotS =
        "\12\uffff";
    static final String DFA114_eofS =
        "\12\uffff";
    static final String DFA114_minS =
        "\1\111\1\uffff\1\0\7\uffff";
    static final String DFA114_maxS =
        "\1\u0088\1\uffff\1\0\7\uffff";
    static final String DFA114_acceptS =
        "\1\uffff\1\1\5\uffff\1\2\2\uffff";
    static final String DFA114_specialS =
        "\2\uffff\1\0\7\uffff}>";
    static final String[] DFA114_transitionS = {
            "\1\7\30\uffff\5\1\6\uffff\1\1\3\uffff\1\1\2\uffff\1\7\5\uffff"+
            "\1\2\1\1\1\7\13\uffff\1\1",
            "",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA114_eot = DFA.unpackEncodedString(DFA114_eotS);
    static final short[] DFA114_eof = DFA.unpackEncodedString(DFA114_eofS);
    static final char[] DFA114_min = DFA.unpackEncodedStringToUnsignedChars(DFA114_minS);
    static final char[] DFA114_max = DFA.unpackEncodedStringToUnsignedChars(DFA114_maxS);
    static final short[] DFA114_accept = DFA.unpackEncodedString(DFA114_acceptS);
    static final short[] DFA114_special = DFA.unpackEncodedString(DFA114_specialS);
    static final short[][] DFA114_transition;

    static {
        int numStates = DFA114_transitionS.length;
        DFA114_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA114_transition[i] = DFA.unpackEncodedString(DFA114_transitionS[i]);
        }
    }

    class DFA114 extends DFA {

        public DFA114(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 114;
            this.eot = DFA114_eot;
            this.eof = DFA114_eof;
            this.min = DFA114_min;
            this.max = DFA114_max;
            this.accept = DFA114_accept;
            this.special = DFA114_special;
            this.transition = DFA114_transition;
        }
        public String getDescription() {
            return "1175:5: (index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA114_2 = input.LA(1);

                         
                        int index114_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (((isVariableOfType(input.LT(1).getText(), "INT"))||(isVariableOfType(input.LT(1).getText(), "DOUBLE"))||(isVariableOfType(input.LT(1).getText(), "NUMBERFUNCTION")))) ) {s = 1;}

                        else if ( (((isVariableOfType(input.LT(1).getText(), "STRINGFUNCTION"))||(isVariableOfType(input.LT(1).getText(), "STRING")))) ) {s = 7;}

                         
                        input.seek(index114_2);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 114, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA148_eotS =
        "\16\uffff";
    static final String DFA148_eofS =
        "\16\uffff";
    static final String DFA148_minS =
        "\1\111\1\uffff\1\0\3\uffff\2\0\6\uffff";
    static final String DFA148_maxS =
        "\1\u0088\1\uffff\1\0\3\uffff\2\0\6\uffff";
    static final String DFA148_acceptS =
        "\1\uffff\1\1\2\uffff\1\2\4\uffff\1\3\3\uffff\1\4";
    static final String DFA148_specialS =
        "\2\uffff\1\0\3\uffff\1\1\1\2\6\uffff}>";
    static final String[] DFA148_transitionS = {
            "\1\1\2\uffff\1\6\25\uffff\5\11\3\4\3\uffff\1\11\3\uffff\1\11"+
            "\2\uffff\1\1\5\uffff\1\2\1\7\14\uffff\1\11",
            "",
            "\1\uffff",
            "",
            "",
            "",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA148_eot = DFA.unpackEncodedString(DFA148_eotS);
    static final short[] DFA148_eof = DFA.unpackEncodedString(DFA148_eofS);
    static final char[] DFA148_min = DFA.unpackEncodedStringToUnsignedChars(DFA148_minS);
    static final char[] DFA148_max = DFA.unpackEncodedStringToUnsignedChars(DFA148_maxS);
    static final short[] DFA148_accept = DFA.unpackEncodedString(DFA148_acceptS);
    static final short[] DFA148_special = DFA.unpackEncodedString(DFA148_specialS);
    static final short[][] DFA148_transition;

    static {
        int numStates = DFA148_transitionS.length;
        DFA148_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA148_transition[i] = DFA.unpackEncodedString(DFA148_transitionS[i]);
        }
    }

    class DFA148 extends DFA {

        public DFA148(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 148;
            this.eot = DFA148_eot;
            this.eof = DFA148_eof;
            this.min = DFA148_min;
            this.max = DFA148_max;
            this.accept = DFA148_accept;
            this.special = DFA148_special;
            this.transition = DFA148_transition;
        }
        public String getDescription() {
            return "1591:1: argument returns [Expression expr = null] options {backtrack=true; } : (a4= stringExpression | a2= booleanExpression | a3= numberExpression | a1= typeExpression );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA148_2 = input.LA(1);

                         
                        int index148_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (((synpred22_TextMarkerParser()&&(isVariableOfType(input.LT(1).getText(), "STRINGFUNCTION")))||(synpred22_TextMarkerParser()&&(isVariableOfType(input.LT(1).getText(), "STRING"))))) ) {s = 1;}

                        else if ( (((synpred23_TextMarkerParser()&&(isVariableOfType(input.LT(1).getText(), "TYPEFUNCTION")))||(synpred23_TextMarkerParser()&&(isVariableOfType(input.LT(1).getText(), "BOOLEAN")))||(synpred23_TextMarkerParser()&&(isVariableOfType(input.LT(1).getText(), "BOOLEANFUNCTION")))||synpred23_TextMarkerParser()||(synpred23_TextMarkerParser()&&(isVariableOfType(input.LT(1).getText(), "BOOLEAN"))))) ) {s = 4;}

                        else if ( (((synpred24_TextMarkerParser()&&(isVariableOfType(input.LT(1).getText(), "DOUBLE")))||(synpred24_TextMarkerParser()&&(isVariableOfType(input.LT(1).getText(), "NUMBERFUNCTION")))||(synpred24_TextMarkerParser()&&(isVariableOfType(input.LT(1).getText(), "INT"))))) ) {s = 9;}

                        else if ( (true) ) {s = 13;}

                         
                        input.seek(index148_2);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA148_6 = input.LA(1);

                         
                        int index148_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred23_TextMarkerParser()) ) {s = 4;}

                        else if ( (true) ) {s = 13;}

                         
                        input.seek(index148_6);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA148_7 = input.LA(1);

                         
                        int index148_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred23_TextMarkerParser()) ) {s = 4;}

                        else if ( (synpred24_TextMarkerParser()) ) {s = 9;}

                         
                        input.seek(index148_7);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 148, _s, input);
            error(nvae);
            throw nvae;
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
    public static final BitSet FOLLOW_ruleElement_in_ruleElements1847 = new BitSet(new long[]{0x0000000000000E02L,0x0C100003F9FC1000L});
    public static final BitSet FOLLOW_ruleElement_in_ruleElements1856 = new BitSet(new long[]{0x0000000000000E02L,0x0C100003F9FC1000L});
    public static final BitSet FOLLOW_ruleElementType_in_blockRuleElement1883 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementType_in_ruleElement1907 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementLiteral_in_ruleElement1918 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementComposed_in_ruleElement1929 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_ruleElementComposed1949 = new BitSet(new long[]{0x0000000000000E00L,0x0C100003F9FC1000L});
    public static final BitSet FOLLOW_ruleElements_in_ruleElementComposed1955 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_ruleElementComposed1957 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L,0x0000000000020280L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementComposed1963 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_ruleElementType1995 = new BitSet(new long[]{0x0000000000000002L,0xA000000000000000L,0x0000000000020280L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementType2001 = new BitSet(new long[]{0x0000000000000002L,0x8000000000000000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleElementType2014 = new BitSet(new long[]{0xF8000000FFFFF100L,0x0400000000000881L,0x0000000000000101L});
    public static final BitSet FOLLOW_conditions_in_ruleElementType2020 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L,0x0000000000000001L});
    public static final BitSet FOLLOW_THEN_in_ruleElementType2024 = new BitSet(new long[]{0x07FFFFF700000080L,0x040000000000057EL});
    public static final BitSet FOLLOW_actions_in_ruleElementType2030 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RCURLY_in_ruleElementType2038 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleStringExpression_in_ruleElementLiteral2091 = new BitSet(new long[]{0x0000000000000002L,0xA000000000000000L,0x0000000000020280L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementLiteral2097 = new BitSet(new long[]{0x0000000000000002L,0x8000000000000000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleElementLiteral2110 = new BitSet(new long[]{0xF8000000FFFFF100L,0x0400000000000881L,0x0000000000000101L});
    public static final BitSet FOLLOW_conditions_in_ruleElementLiteral2116 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L,0x0000000000000001L});
    public static final BitSet FOLLOW_THEN_in_ruleElementLiteral2120 = new BitSet(new long[]{0x07FFFFF700000080L,0x040000000000057EL});
    public static final BitSet FOLLOW_actions_in_ruleElementLiteral2126 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RCURLY_in_ruleElementLiteral2134 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_condition_in_conditions2188 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditions2193 = new BitSet(new long[]{0xF8000000FFFFF100L,0x0400000000000081L,0x0000000000000100L});
    public static final BitSet FOLLOW_condition_in_conditions2199 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_action_in_actions2236 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actions2241 = new BitSet(new long[]{0x07FFFFF700000080L,0x040000000000057EL});
    public static final BitSet FOLLOW_action_in_actions2247 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_booleanListExpression_in_listExpression2285 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_intListExpression_in_listExpression2301 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_doubleListExpression_in_listExpression2317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringListExpression_in_listExpression2333 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeListExpression_in_listExpression2349 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleBooleanListExpression_in_booleanListExpression2373 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleBooleanListExpression2394 = new BitSet(new long[]{0x0000000000000000L,0x0400030000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_simpleBooleanListExpression2401 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_COMMA_in_simpleBooleanListExpression2406 = new BitSet(new long[]{0x0000000000000000L,0x0400030000000000L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_simpleBooleanListExpression2412 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_RCURLY_in_simpleBooleanListExpression2421 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleBooleanListExpression2438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleIntListExpression_in_intListExpression2463 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleIntListExpression2484 = new BitSet(new long[]{0x0000000000000000L,0x0C02200000000000L,0x0000000000000101L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleIntListExpression2491 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_COMMA_in_simpleIntListExpression2496 = new BitSet(new long[]{0x0000000000000000L,0x0C02200000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleIntListExpression2502 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_RCURLY_in_simpleIntListExpression2511 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleIntListExpression2528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_doubleListExpression_in_numberListExpression2562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_intListExpression_in_numberListExpression2574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleDoubleListExpression_in_doubleListExpression2597 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleDoubleListExpression2618 = new BitSet(new long[]{0x0000000000000000L,0x0C02200000000000L,0x0000000000000101L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleDoubleListExpression2625 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_COMMA_in_simpleDoubleListExpression2630 = new BitSet(new long[]{0x0000000000000000L,0x0C02200000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleDoubleListExpression2636 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_RCURLY_in_simpleDoubleListExpression2645 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleDoubleListExpression2662 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleStringListExpression_in_stringListExpression2687 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleStringListExpression2708 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_simpleStringExpression_in_simpleStringListExpression2715 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_COMMA_in_simpleStringListExpression2720 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000000L});
    public static final BitSet FOLLOW_simpleStringExpression_in_simpleStringListExpression2726 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_RCURLY_in_simpleStringListExpression2735 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleStringListExpression2752 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleTypeListExpression_in_typeListExpression2777 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleTypeListExpression2798 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L,0x0000000000000001L});
    public static final BitSet FOLLOW_simpleTypeExpression_in_simpleTypeListExpression2805 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_COMMA_in_simpleTypeListExpression2810 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_simpleTypeExpression_in_simpleTypeListExpression2816 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_RCURLY_in_simpleTypeListExpression2825 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleTypeListExpression2842 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeFunction_in_typeExpression2868 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleTypeExpression_in_typeExpression2879 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalTypeFunction_in_typeFunction2913 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalTypeFunction2938 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_externalTypeFunction2942 = new BitSet(new long[]{0x0000000000000000L,0x1800000000000000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalTypeFunction2949 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalTypeFunction2953 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotationType_in_simpleTypeExpression2976 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_variable3000 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_listVariable3027 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAR_in_quantifierPart3054 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart3060 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_quantifierPart3072 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart3078 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart3090 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart3096 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACK_in_quantifierPart3105 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_quantifierPart3111 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_quantifierPart3114 = new BitSet(new long[]{0x0000000000000000L,0x4C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_quantifierPart3121 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000000L});
    public static final BitSet FOLLOW_RBRACK_in_quantifierPart3127 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart3133 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionAnd_in_condition3171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionContains_in_condition3180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionContextCount_in_condition3189 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionCount_in_condition3198 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionCurrentCount_in_condition3207 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionInList_in_condition3216 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionIsInTag_in_condition3225 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionLast_in_condition3234 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionMofN_in_condition3243 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionNear_in_condition3252 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionNot_in_condition3261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionOr_in_condition3270 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionPartOf_in_condition3279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionPosition_in_condition3288 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionRegExp_in_condition3297 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionScore_in_condition3306 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionTotalCount_in_condition3315 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionVote_in_condition3324 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionIf_in_condition3333 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionFeature_in_condition3342 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionParse_in_condition3351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionIs_in_condition3360 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionBefore_in_condition3369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionAfter_in_condition3378 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionStartsWith_in_condition3387 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionEndsWith_in_condition3396 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionPartOfNeq_in_condition3405 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionSize_in_condition3414 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalCondition_in_condition3432 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableCondition_in_condition3441 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_variableCondition3474 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalCondition3501 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_externalCondition3504 = new BitSet(new long[]{0x0000000000000000L,0x1800000000000000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalCondition3514 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalCondition3521 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AND_in_conditionAnd3549 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionAnd3551 = new BitSet(new long[]{0xF8000000FFFFF100L,0x0400000000000081L,0x0000000000000100L});
    public static final BitSet FOLLOW_conditions_in_conditionAnd3557 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionAnd3571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONTAINS_in_conditionContains3617 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionContains3619 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionContains3626 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_listExpression_in_conditionContains3634 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionContains3636 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_conditionContains3642 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionContains3651 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionContains3657 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionContains3659 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionContains3665 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionContains3668 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionContains3674 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionContains3691 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONTEXTCOUNT_in_conditionContextCount3727 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionContextCount3729 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionContextCount3735 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionContextCount3749 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionContextCount3755 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionContextCount3757 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionContextCount3763 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionContextCount3778 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberVariable_in_conditionContextCount3784 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionContextCount3799 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COUNT_in_conditionCount3850 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionCount3852 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_listExpression_in_conditionCount3858 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount3873 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_conditionCount3879 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount3895 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCount3901 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount3903 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCount3909 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount3927 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberVariable_in_conditionCount3933 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionCount3949 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COUNT_in_conditionCount3965 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionCount3967 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionCount3973 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount3987 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCount3993 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount3995 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCount4001 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount4016 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberVariable_in_conditionCount4022 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionCount4039 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CURRENTCOUNT_in_conditionCurrentCount4079 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionCurrentCount4081 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionCurrentCount4087 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionCurrentCount4101 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCurrentCount4107 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionCurrentCount4109 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCurrentCount4115 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionCurrentCount4131 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberVariable_in_conditionCurrentCount4137 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionCurrentCount4152 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOTALCOUNT_in_conditionTotalCount4191 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionTotalCount4193 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionTotalCount4199 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionTotalCount4213 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionTotalCount4219 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionTotalCount4221 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionTotalCount4227 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionTotalCount4242 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberVariable_in_conditionTotalCount4248 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionTotalCount4263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INLIST_in_conditionInList4304 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionInList4306 = new BitSet(new long[]{0x0000000000000000L,0x8420000000000000L});
    public static final BitSet FOLLOW_stringListExpression_in_conditionInList4321 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_wordListExpression_in_conditionInList4329 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionInList4338 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionInList4344 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionInList4347 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionInList4353 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionInList4371 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ISINTAG_in_conditionIsInTag4414 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionIsInTag4416 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_conditionIsInTag4422 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionIsInTag4425 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_conditionIsInTag4431 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_conditionIsInTag4433 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_conditionIsInTag4439 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_conditionIsInTag4458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LAST_in_conditionLast4498 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionLast4500 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionLast4506 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionLast4519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MOFN_in_conditionMofN4555 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionMofN4557 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionMofN4563 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionMofN4565 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionMofN4571 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionMofN4573 = new BitSet(new long[]{0xF8000000FFFFF100L,0x0400000000000081L,0x0000000000000100L});
    public static final BitSet FOLLOW_conditions_in_conditionMofN4579 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionMofN4594 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEAR_in_conditionNear4626 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionNear4628 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionNear4634 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionNear4636 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionNear4642 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionNear4644 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionNear4650 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionNear4658 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionNear4664 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionNear4667 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionNear4673 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionNear4693 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_conditionNot4726 = new BitSet(new long[]{0xF8000000FFFFF100L,0x0400000000000081L,0x0000000000000100L});
    public static final BitSet FOLLOW_condition_in_conditionNot4732 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_conditionNot4743 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionNot4745 = new BitSet(new long[]{0xF8000000FFFFF100L,0x0400000000000081L,0x0000000000000100L});
    public static final BitSet FOLLOW_condition_in_conditionNot4751 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionNot4753 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OR_in_conditionOr4793 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionOr4795 = new BitSet(new long[]{0xF8000000FFFFF100L,0x0400000000000081L,0x0000000000000100L});
    public static final BitSet FOLLOW_conditions_in_conditionOr4801 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionOr4814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARTOF_in_conditionPartOf4842 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionPartOf4844 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionPartOf4851 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionPartOf4857 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionPartOf4874 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARTOFNEQ_in_conditionPartOfNeq4907 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionPartOfNeq4909 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionPartOfNeq4916 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionPartOfNeq4922 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionPartOfNeq4939 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_POSITION_in_conditionPosition4976 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionPosition4978 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionPosition4984 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionPosition4986 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionPosition4992 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionPosition5005 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REGEXP_in_conditionRegExp5033 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionRegExp5035 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_conditionRegExp5041 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionRegExp5044 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionRegExp5050 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionRegExp5068 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SCORE_in_conditionScore5102 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionScore5104 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionScore5110 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionScore5113 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionScore5119 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionScore5128 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberVariable_in_conditionScore5134 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionScore5151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VOTE_in_conditionVote5183 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionVote5185 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionVote5191 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionVote5193 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionVote5199 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionVote5212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_conditionIf5246 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionIf5248 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionIf5254 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionIf5267 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FEATURE_in_conditionFeature5306 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionFeature5308 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_conditionFeature5314 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionFeature5316 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_conditionFeature5322 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionFeature5335 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARSE_in_conditionParse5366 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionParse5368 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_genericVariableReference_in_conditionParse5377 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionParse5390 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IS_in_conditionIs5420 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionIs5422 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionIs5429 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionIs5435 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionIs5449 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BEFORE_in_conditionBefore5478 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionBefore5480 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionBefore5487 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionBefore5493 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionBefore5507 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AFTER_in_conditionAfter5536 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionAfter5538 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionAfter5545 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionAfter5551 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionAfter5565 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STARTSWITH_in_conditionStartsWith5598 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionStartsWith5600 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionStartsWith5607 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionStartsWith5613 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionStartsWith5627 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ENDSWITH_in_conditionEndsWith5660 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionEndsWith5662 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionEndsWith5669 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionEndsWith5675 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionEndsWith5689 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SIZE_in_conditionSize5722 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionSize5724 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_listExpression_in_conditionSize5730 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionSize5733 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionSize5739 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionSize5741 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionSize5747 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionSize5752 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberVariable_in_conditionSize5758 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionSize5773 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionColor_in_action5801 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionDel_in_action5810 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionLog_in_action5819 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMark_in_action5828 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMarkScore_in_action5837 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMarkFast_in_action5846 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMarkLast_in_action5855 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionReplace_in_action5864 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionRetainMarkup_in_action5873 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionRetainType_in_action5882 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionFilterMarkup_in_action5891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionFilterType_in_action5900 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionCreate_in_action5909 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionFill_in_action5918 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionCall_in_action5927 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionAssign_in_action5936 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionSetFeature_in_action5945 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionGetFeature_in_action5954 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionUnmark_in_action5963 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionUnmarkAll_in_action5972 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionTransfer_in_action5981 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMarkOnce_in_action5990 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionTrie_in_action5999 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionGather_in_action6008 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionExec_in_action6018 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMarkTable_in_action6027 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionAdd_in_action6036 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionRemove_in_action6045 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionRemoveDuplicate_in_action6054 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMerge_in_action6063 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionGet_in_action6072 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionGetList_in_action6082 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMatchedText_in_action6091 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionClear_in_action6100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionExpand_in_action6109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalAction_in_action6127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableAction_in_action6136 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_variableAction6167 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalAction6191 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_externalAction6195 = new BitSet(new long[]{0x0000000000000000L,0x1800000000000000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalAction6204 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalAction6209 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CREATE_in_actionCreate6245 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionCreate6247 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionCreate6253 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionCreate6260 = new BitSet(new long[]{0x0000000000000000L,0x1C12207C00000200L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionCreate6281 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionCreate6298 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionCreate6304 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionCreate6310 = new BitSet(new long[]{0x0000000000000000L,0x1410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionCreate6328 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionCreate6330 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_actionCreate6336 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionCreate6346 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionCreate6352 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionCreate6354 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_actionCreate6360 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionCreate6391 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKTABLE_in_actionMarkTable6426 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkTable6428 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkTable6439 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkTable6441 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkTable6452 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkTable6454 = new BitSet(new long[]{0x0000000000000000L,0x0420000000000000L});
    public static final BitSet FOLLOW_wordTableExpression_in_actionMarkTable6464 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkTable6472 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionMarkTable6483 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionMarkTable6485 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkTable6491 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkTable6501 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionMarkTable6507 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionMarkTable6509 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkTable6515 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkTable6539 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GATHER_in_actionGather6573 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionGather6575 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionGather6581 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionGather6595 = new BitSet(new long[]{0x0000000000000000L,0x1C12207C00000200L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionGather6607 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionGather6623 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionGather6629 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionGather6636 = new BitSet(new long[]{0x0000000000000000L,0x1410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionGather6649 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionGather6651 = new BitSet(new long[]{0x0000000000000000L,0x8C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionGather6658 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_numberListExpression_in_actionGather6666 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionGather6677 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionGather6683 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionGather6685 = new BitSet(new long[]{0x0000000000000000L,0x8C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionGather6692 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_numberListExpression_in_actionGather6700 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionGather6732 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILL_in_actionFill6767 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionFill6769 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionFill6775 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionFill6793 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionFill6799 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionFill6801 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_actionFill6811 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionFill6833 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLOR_in_actionColor6870 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionColor6872 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionColor6878 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionColor6892 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionColor6903 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionColor6917 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionColor6927 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionColor6941 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionColor6951 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionColor6967 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEL_in_actionDel6999 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LOG_in_actionLog7045 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionLog7047 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionLog7053 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionLog7056 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_LogLevel_in_actionLog7062 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionLog7078 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARK_in_actionMark7116 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMark7118 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionMark7129 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMark7147 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionMark7163 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionMark7185 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXPAND_in_actionExpand7222 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionExpand7224 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionExpand7235 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionExpand7253 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionExpand7269 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionExpand7291 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKSCORE_in_actionMarkScore7328 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkScore7330 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkScore7336 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkScore7338 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkScore7344 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkScore7362 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkScore7378 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkScore7400 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKONCE_in_actionMarkOnce7437 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkOnce7439 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00001000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkOnce7456 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkOnce7458 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkOnce7476 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkOnce7494 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkOnce7510 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkOnce7532 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKFAST_in_actionMarkFast7564 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkFast7566 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkFast7572 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkFast7585 = new BitSet(new long[]{0x0000000000000000L,0x0420000000000000L});
    public static final BitSet FOLLOW_wordListExpression_in_actionMarkFast7591 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkFast7605 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionMarkFast7611 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkFast7614 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkFast7620 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkFast7638 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKLAST_in_actionMarkLast7670 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkLast7672 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkLast7678 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkLast7691 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REPLACE_in_actionReplace7724 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionReplace7726 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionReplace7732 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionReplace7745 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RETAINMARKUP_in_actionRetainMarkup7782 = new BitSet(new long[]{0x0000000000000002L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionRetainMarkup7785 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionRetainMarkup7791 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionRetainMarkup7807 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionRetainMarkup7813 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionRetainMarkup7830 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RETAINTYPE_in_actionRetainType7883 = new BitSet(new long[]{0x0000000000000002L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionRetainType7886 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionRetainType7892 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionRetainType7908 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionRetainType7914 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionRetainType7931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILTERMARKUP_in_actionFilterMarkup7980 = new BitSet(new long[]{0x0000000000000002L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionFilterMarkup7983 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionFilterMarkup7989 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionFilterMarkup8005 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionFilterMarkup8011 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionFilterMarkup8028 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILTERTYPE_in_actionFilterType8073 = new BitSet(new long[]{0x0000000000000002L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionFilterType8076 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionFilterType8082 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionFilterType8098 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionFilterType8104 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionFilterType8121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CALL_in_actionCall8170 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionCall8176 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_dottedComponentReference_in_actionCall8198 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionCall8212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXEC_in_actionExec8246 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionExec8252 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_dottedComponentReference_in_actionExec8270 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionExec8286 = new BitSet(new long[]{0x0000000000000000L,0x8400000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_actionExec8292 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionExec8308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSIGN_in_actionAssign8350 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionAssign8352 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_actionAssign8363 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionAssign8381 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_actionAssign8387 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionAssign8395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SETFEATURE_in_actionSetFeature8432 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionSetFeature8434 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionSetFeature8440 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionSetFeature8454 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_actionSetFeature8460 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionSetFeature8473 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GETFEATURE_in_actionGetFeature8502 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionGetFeature8504 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionGetFeature8510 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionGetFeature8523 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_variable_in_actionGetFeature8529 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionGetFeature8542 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNMARK_in_actionUnmark8572 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionUnmark8574 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionUnmark8580 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionUnmark8593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNMARKALL_in_actionUnmarkAll8622 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionUnmarkAll8624 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionUnmarkAll8630 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionUnmarkAll8644 = new BitSet(new long[]{0x0000000000000000L,0x8400000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_actionUnmarkAll8650 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionUnmarkAll8665 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRANSFER_in_actionTransfer8697 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionTransfer8699 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionTransfer8705 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionTransfer8718 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRIE_in_actionTrie8756 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionTrie8758 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionTrie8772 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionTrie8775 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionTrie8790 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8803 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionTrie8809 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionTrie8813 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionTrie8828 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8842 = new BitSet(new long[]{0x0000000000000000L,0x0420000000000000L});
    public static final BitSet FOLLOW_wordListExpression_in_actionTrie8848 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8864 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionTrie8870 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8877 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionTrie8883 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8890 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionTrie8896 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8903 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionTrie8909 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8916 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionTrie8922 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionTrie8945 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ADD_in_actionAdd8983 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionAdd8985 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_listVariable_in_actionAdd8991 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionAdd9005 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_actionAdd9011 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionAdd9028 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REMOVE_in_actionRemove9062 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionRemove9064 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_listVariable_in_actionRemove9070 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionRemove9084 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_actionRemove9090 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionRemove9107 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REMOVEDUPLICATE_in_actionRemoveDuplicate9137 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionRemoveDuplicate9139 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_listVariable_in_actionRemoveDuplicate9145 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionRemoveDuplicate9158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MERGE_in_actionMerge9195 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMerge9197 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionMerge9203 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMerge9217 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_listVariable_in_actionMerge9223 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMerge9237 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_listExpression_in_actionMerge9243 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMerge9253 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_listExpression_in_actionMerge9259 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionMerge9276 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GET_in_actionGet9305 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionGet9307 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_listExpression_in_actionGet9313 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionGet9326 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_variable_in_actionGet9332 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionGet9345 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionGet9351 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionGet9364 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GETLIST_in_actionGetList9394 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionGetList9396 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_listVariable_in_actionGetList9402 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionGetList9415 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionGetList9421 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionGetList9434 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MATCHEDTEXT_in_actionMatchedText9471 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMatchedText9473 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_variable_in_actionMatchedText9484 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMatchedText9496 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionMatchedText9502 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionMatchedText9524 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLEAR_in_actionClear9557 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionClear9559 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_listVariable_in_actionClear9565 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionClear9578 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_varArgumentList9600 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_varArgumentList9606 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_varArgumentList9611 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_varArgumentList9617 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_varArgumentList9623 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringExpression_in_argument9660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanExpression_in_argument9671 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpression_in_argument9682 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_argument9693 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_dottedIdentifier9730 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_DOT_in_dottedIdentifier9743 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_dottedIdentifier9753 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_Identifier_in_dottedId9785 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_DOT_in_dottedId9798 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_dottedId9808 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_Identifier_in_dottedComponentReference9843 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000108L});
    public static final BitSet FOLLOW_set_in_dottedComponentReference9856 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_dottedComponentReference9872 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000108L});
    public static final BitSet FOLLOW_Identifier_in_dottedComponentDeclaration9906 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000108L});
    public static final BitSet FOLLOW_set_in_dottedComponentDeclaration9919 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_dottedComponentDeclaration9935 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000108L});
    public static final BitSet FOLLOW_annotationTypeVariableReference_in_annotationType9969 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BasicAnnotationType_in_annotationType9980 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dottedId_in_annotationTypeVariableReference10009 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_wordListExpression10033 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RessourceLiteral_in_wordListExpression10046 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_wordTableExpression10070 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RessourceLiteral_in_wordTableExpression10083 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_additiveExpression_in_numberExpression10107 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression10133 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000180L});
    public static final BitSet FOLLOW_set_in_additiveExpression10142 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression10152 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000180L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_multiplicativeExpression10181 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000010600L});
    public static final BitSet FOLLOW_set_in_multiplicativeExpression10190 = new BitSet(new long[]{0x0000000000000000L,0x0C02200000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_multiplicativeExpression10208 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000010600L});
    public static final BitSet FOLLOW_numberFunction_in_multiplicativeExpression10224 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_numberExpressionInPar10248 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_numberExpressionInPar10254 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_numberExpressionInPar10260 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_simpleNumberExpression10285 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberVariable_in_simpleNumberExpression10292 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_simpleNumberExpression10307 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
    public static final BitSet FOLLOW_DecimalLiteral_in_simpleNumberExpression10315 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_simpleNumberExpression10329 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
    public static final BitSet FOLLOW_FloatingPointLiteral_in_simpleNumberExpression10336 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpressionInPar_in_simpleNumberExpression10352 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_numberFunction10377 = new BitSet(new long[]{0x0000000000000000L,0x0C02200000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpressionInPar_in_numberFunction10399 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalNumberFunction_in_numberFunction10423 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalNumberFunction10448 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_externalNumberFunction10452 = new BitSet(new long[]{0x0000000000000000L,0x1800000000000000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalNumberFunction10459 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalNumberFunction10462 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_numberVariable10493 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_numberVariable10506 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringFunction_in_stringExpression10544 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleStringExpression_in_stringExpression10557 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_PLUS_in_stringExpression10563 = new BitSet(new long[]{0x0000000000000000L,0x8C12230000001000L,0x0000000000000100L});
    public static final BitSet FOLLOW_simpleStringExpression_in_stringExpression10570 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_numberExpressionInPar_in_stringExpression10582 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_stringExpression10594 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_listExpression_in_stringExpression10611 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_typeExpression_in_stringExpression10623 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_REMOVESTRING_in_stringFunction10660 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_stringFunction10662 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_variable_in_stringFunction10668 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_stringFunction10671 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_stringFunction10677 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_stringFunction10682 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalStringFunction_in_stringFunction10704 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalStringFunction10729 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_externalStringFunction10733 = new BitSet(new long[]{0x0000000000000000L,0x1800000000000000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalStringFunction10740 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalStringFunction10743 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_StringLiteral_in_simpleStringExpression10768 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleStringExpression10783 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_composedBooleanExpression_in_booleanExpression10816 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_booleanExpression10827 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_literalBooleanExpression_in_simpleBooleanExpression10852 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleBooleanExpression10865 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanCompare_in_composedBooleanExpression10911 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanTypeExpression_in_composedBooleanExpression10931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanNumberExpression_in_composedBooleanExpression10950 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanFunction_in_composedBooleanExpression10960 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_XOR_in_booleanFunction10985 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_booleanFunction10987 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_booleanFunction10993 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_booleanFunction10995 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_booleanFunction11001 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_booleanFunction11003 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalBooleanFunction_in_booleanFunction11025 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalBooleanFunction11051 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_externalBooleanFunction11054 = new BitSet(new long[]{0x0000000000000000L,0x1800000000000000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalBooleanFunction11061 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalBooleanFunction11065 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_booleanCompare11090 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x00000000000C0000L});
    public static final BitSet FOLLOW_set_in_booleanCompare11096 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_booleanCompare11108 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_literalBooleanExpression11135 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_literalBooleanExpression11145 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_booleanTypeExpression11172 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x00000000000C0000L});
    public static final BitSet FOLLOW_set_in_booleanTypeExpression11179 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_booleanTypeExpression11192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_booleanNumberExpression11215 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_booleanNumberExpression11222 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x00000000006C6000L});
    public static final BitSet FOLLOW_set_in_booleanNumberExpression11229 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_booleanNumberExpression11258 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_booleanNumberExpression11261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_genericVariableReference11281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanListExpression_in_synpred3_TextMarkerParser2277 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_intListExpression_in_synpred4_TextMarkerParser2293 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_doubleListExpression_in_synpred5_TextMarkerParser2309 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringListExpression_in_synpred6_TextMarkerParser2325 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeListExpression_in_synpred7_TextMarkerParser2341 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_doubleListExpression_in_synpred8_TextMarkerParser2554 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalCondition_in_synpred10_TextMarkerParser3424 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COUNT_in_synpred11_TextMarkerParser3850 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_synpred11_TextMarkerParser3852 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_listExpression_in_synpred11_TextMarkerParser3858 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_synpred11_TextMarkerParser3873 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_synpred11_TextMarkerParser3879 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_synpred11_TextMarkerParser3895 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_synpred11_TextMarkerParser3901 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_synpred11_TextMarkerParser3903 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_synpred11_TextMarkerParser3909 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_synpred11_TextMarkerParser3927 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberVariable_in_synpred11_TextMarkerParser3933 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_synpred11_TextMarkerParser3949 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringListExpression_in_synpred12_TextMarkerParser4314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalAction_in_synpred13_TextMarkerParser6119 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_synpred14_TextMarkerParser6287 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_synpred14_TextMarkerParser6293 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_synpred15_TextMarkerParser6613 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_synpred15_TextMarkerParser6619 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpression_in_synpred19_TextMarkerParser7447 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_synpred20_TextMarkerParser7467 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringExpression_in_synpred22_TextMarkerParser9660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanExpression_in_synpred23_TextMarkerParser9671 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpression_in_synpred24_TextMarkerParser9682 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalNumberFunction_in_synpred25_TextMarkerParser10415 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_listExpression_in_synpred26_TextMarkerParser10603 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalStringFunction_in_synpred27_TextMarkerParser10696 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanCompare_in_synpred28_TextMarkerParser10903 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanTypeExpression_in_synpred29_TextMarkerParser10923 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanNumberExpression_in_synpred30_TextMarkerParser10942 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalBooleanFunction_in_synpred31_TextMarkerParser11017 = new BitSet(new long[]{0x0000000000000002L});

}
