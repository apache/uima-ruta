// $ANTLR 3.2 Sep 23, 2009 12:02:23 D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g 2011-04-27 10:13:31

package org.apache.uima.tm.textmarker.parser;
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
import org.apache.uima.tm.textmarker.action.AbstractTextMarkerAction;
import org.apache.uima.tm.textmarker.action.ActionFactory;
import org.apache.uima.tm.textmarker.condition.AbstractTextMarkerCondition;
import org.apache.uima.tm.textmarker.condition.ConditionFactory;
import org.apache.uima.tm.textmarker.kernel.TextMarkerAutomataBlock;
import org.apache.uima.tm.textmarker.kernel.TextMarkerAutomataFactory;
import org.apache.uima.tm.textmarker.kernel.TextMarkerBlock;
import org.apache.uima.tm.textmarker.kernel.TextMarkerModule;
import org.apache.uima.tm.textmarker.kernel.TextMarkerScriptBlock;
import org.apache.uima.tm.textmarker.kernel.TextMarkerScriptFactory;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;
import org.apache.uima.tm.textmarker.kernel.expression.ExpressionFactory;
import org.apache.uima.tm.textmarker.kernel.expression.TextMarkerExpression;
import org.apache.uima.tm.textmarker.kernel.expression.bool.BooleanExpression;
import org.apache.uima.tm.textmarker.kernel.expression.list.BooleanListExpression;
import org.apache.uima.tm.textmarker.kernel.expression.list.ListExpression;
import org.apache.uima.tm.textmarker.kernel.expression.list.NumberListExpression;
import org.apache.uima.tm.textmarker.kernel.expression.list.StringListExpression;
import org.apache.uima.tm.textmarker.kernel.expression.list.TypeListExpression;
import org.apache.uima.tm.textmarker.kernel.expression.number.NumberExpression;
import org.apache.uima.tm.textmarker.kernel.expression.resource.WordListExpression;
import org.apache.uima.tm.textmarker.kernel.expression.resource.WordTableExpression;
import org.apache.uima.tm.textmarker.kernel.expression.string.StringExpression;
import org.apache.uima.tm.textmarker.kernel.expression.string.StringFunctionFactory;
import org.apache.uima.tm.textmarker.kernel.expression.type.TypeExpression;
import org.apache.uima.tm.textmarker.kernel.extensions.TextMarkerExternalFactory;
import org.apache.uima.tm.textmarker.kernel.rule.ComposedRuleElement;
import org.apache.uima.tm.textmarker.kernel.rule.RuleElement;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRule;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.rule.quantifier.RuleElementQuantifier;



import org.antlr.runtime.*;
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
    public String getGrammarFileName() { return "D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g"; }


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
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:185:1: file_input[String moduleName] returns [TextMarkerModule module] : p= packageDeclaration gs= globalStatements s= statements EOF ;
    public final TextMarkerModule file_input(String moduleName) throws RecognitionException {
        TextMarkerModule module = null;

        String p = null;

        List<TextMarkerStatement> gs = null;

        List<TextMarkerStatement> s = null;



        TextMarkerScriptBlock rootBlock = null;
        List<TextMarkerStatement> stmts = new ArrayList<TextMarkerStatement>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:190:2: (p= packageDeclaration gs= globalStatements s= statements EOF )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:191:2: p= packageDeclaration gs= globalStatements s= statements EOF
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
            if ( state.backtracking==0 ) {
              blockDeclaration_stack.push(new blockDeclaration_scope());((blockDeclaration_scope)blockDeclaration_stack.peek()).env = rootBlock;
            }
            pushFollow(FOLLOW_globalStatements_in_file_input92);
            gs=globalStatements();

            state._fsp--;
            if (state.failed) return module;
            if ( state.backtracking==0 ) {
              stmts.addAll(gs);
            }
            pushFollow(FOLLOW_statements_in_file_input101);
            s=statements();

            state._fsp--;
            if (state.failed) return module;
            if ( state.backtracking==0 ) {
              stmts.addAll(s);
            }
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
        }
        return module;
    }
    // $ANTLR end "file_input"


    // $ANTLR start "packageDeclaration"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:216:1: packageDeclaration returns [String pack = \"\"] : PackageString p= dottedIdentifier SEMI ;
    public final String packageDeclaration() throws RecognitionException {
        String pack =  "";

        String p = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:217:2: ( PackageString p= dottedIdentifier SEMI )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:217:4: PackageString p= dottedIdentifier SEMI
            {
            match(input,PackageString,FOLLOW_PackageString_in_packageDeclaration124); if (state.failed) return pack;
            pushFollow(FOLLOW_dottedIdentifier_in_packageDeclaration130);
            p=dottedIdentifier();

            state._fsp--;
            if (state.failed) return pack;
            match(input,SEMI,FOLLOW_SEMI_in_packageDeclaration132); if (state.failed) return pack;
            if ( state.backtracking==0 ) {
              pack = p;
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
        }
        return pack;
    }
    // $ANTLR end "packageDeclaration"


    // $ANTLR start "statements"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:220:1: statements returns [List<TextMarkerStatement> stmts = new ArrayList<TextMarkerStatement>()] : (stmt= statement )* ;
    public final List<TextMarkerStatement> statements() throws RecognitionException {
        List<TextMarkerStatement> stmts =  new ArrayList<TextMarkerStatement>();

        TextMarkerStatement stmt = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:221:2: ( (stmt= statement )* )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:222:2: (stmt= statement )*
            {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:222:2: (stmt= statement )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=DECLARE && LA1_0<=WORDTABLE)||LA1_0==BasicAnnotationType||(LA1_0>=BlockString && LA1_0<=BooleanString)||(LA1_0>=CONDITION && LA1_0<=TYPELIST)||LA1_0==StringLiteral||(LA1_0>=Identifier && LA1_0<=LPAREN)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:222:3: stmt= statement
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
        }
        return stmts;
    }
    // $ANTLR end "statements"


    // $ANTLR start "globalStatements"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:225:1: globalStatements returns [List<TextMarkerStatement> stmts = new ArrayList<TextMarkerStatement>()] : (morestmts= globalStatement )* ;
    public final List<TextMarkerStatement> globalStatements() throws RecognitionException {
        List<TextMarkerStatement> stmts =  new ArrayList<TextMarkerStatement>();

        List<TextMarkerStatement> morestmts = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:226:2: ( (morestmts= globalStatement )* )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:227:2: (morestmts= globalStatement )*
            {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:227:2: (morestmts= globalStatement )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>=ScriptString && LA2_0<=EngineString)||LA2_0==TypeSystemString) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:227:3: morestmts= globalStatement
            	    {
            	    pushFollow(FOLLOW_globalStatement_in_globalStatements180);
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
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}
        finally {
        }
        return stmts;
    }
    // $ANTLR end "globalStatements"


    // $ANTLR start "globalStatement"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:230:1: globalStatement returns [List<TextMarkerStatement> stmts = new ArrayList<TextMarkerStatement>()] : stmtImport= importStatement ;
    public final List<TextMarkerStatement> globalStatement() throws RecognitionException {
        List<TextMarkerStatement> stmts =  new ArrayList<TextMarkerStatement>();

        TextMarkerStatement stmtImport = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:231:2: (stmtImport= importStatement )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:232:2: stmtImport= importStatement
            {
            pushFollow(FOLLOW_importStatement_in_globalStatement204);
            stmtImport=importStatement();

            state._fsp--;
            if (state.failed) return stmts;
            if ( state.backtracking==0 ) {
              stmts.add(stmtImport);
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
        }
        return stmts;
    }
    // $ANTLR end "globalStatement"


    // $ANTLR start "statement"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:235:1: statement returns [TextMarkerStatement stmt = null] : (stmtDecl= declaration | stmtVariable= variableDeclaration | stmtRule= simpleStatement | stmtBlock= blockDeclaration | stmtAutomata= automataDeclaration ) ;
    public final TextMarkerStatement statement() throws RecognitionException {
        TextMarkerStatement stmt =  null;

        TextMarkerStatement stmtDecl = null;

        TextMarkerStatement stmtVariable = null;

        TextMarkerRule stmtRule = null;

        TextMarkerBlock stmtBlock = null;

        TextMarkerBlock stmtAutomata = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:236:2: ( (stmtDecl= declaration | stmtVariable= variableDeclaration | stmtRule= simpleStatement | stmtBlock= blockDeclaration | stmtAutomata= automataDeclaration ) )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:237:2: (stmtDecl= declaration | stmtVariable= variableDeclaration | stmtRule= simpleStatement | stmtBlock= blockDeclaration | stmtAutomata= automataDeclaration )
            {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:237:2: (stmtDecl= declaration | stmtVariable= variableDeclaration | stmtRule= simpleStatement | stmtBlock= blockDeclaration | stmtAutomata= automataDeclaration )
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:237:4: stmtDecl= declaration
                    {
                    pushFollow(FOLLOW_declaration_in_statement230);
                    stmtDecl=declaration();

                    state._fsp--;
                    if (state.failed) return stmt;
                    if ( state.backtracking==0 ) {
                      stmt = stmtDecl;
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:238:4: stmtVariable= variableDeclaration
                    {
                    pushFollow(FOLLOW_variableDeclaration_in_statement241);
                    stmtVariable=variableDeclaration();

                    state._fsp--;
                    if (state.failed) return stmt;
                    if ( state.backtracking==0 ) {
                      stmt = stmtVariable;
                    }

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:239:4: stmtRule= simpleStatement
                    {
                    pushFollow(FOLLOW_simpleStatement_in_statement252);
                    stmtRule=simpleStatement();

                    state._fsp--;
                    if (state.failed) return stmt;
                    if ( state.backtracking==0 ) {
                      stmt = stmtRule;
                    }

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:240:4: stmtBlock= blockDeclaration
                    {
                    pushFollow(FOLLOW_blockDeclaration_in_statement263);
                    stmtBlock=blockDeclaration();

                    state._fsp--;
                    if (state.failed) return stmt;
                    if ( state.backtracking==0 ) {
                      stmt = stmtBlock;
                    }

                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:241:4: stmtAutomata= automataDeclaration
                    {
                    pushFollow(FOLLOW_automataDeclaration_in_statement274);
                    stmtAutomata=automataDeclaration();

                    state._fsp--;
                    if (state.failed) return stmt;
                    if ( state.backtracking==0 ) {
                      stmt = stmtBlock;
                    }

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
        }
        return stmt;
    }
    // $ANTLR end "statement"


    // $ANTLR start "variableDeclaration"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:245:1: variableDeclaration returns [TextMarkerStatement stmt = null] : (type= IntString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value1= numberExpression )? SEMI | type= DoubleString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value2= numberExpression )? SEMI | type= StringString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value3= stringExpression )? SEMI | type= BooleanString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value4= booleanExpression )? SEMI | type= TypeString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value5= annotationType )? SEMI | type= WORDLIST {...}?name= Identifier ( ASSIGN_EQUAL list= wordListExpression )? SEMI | type= WORDTABLE {...}?name= Identifier ( ASSIGN_EQUAL table= wordTableExpression )? SEMI | type= BOOLEANLIST {...}?name= Identifier ( ASSIGN_EQUAL bl= booleanListExpression )? SEMI | type= STRINGLIST {...}?name= Identifier ( ASSIGN_EQUAL sl= stringListExpression )? SEMI | type= INTLIST {...}?name= Identifier ( ASSIGN_EQUAL il= numberListExpression )? SEMI | type= DOUBLELIST {...}?name= Identifier ( ASSIGN_EQUAL dl= numberListExpression )? SEMI | type= TYPELIST {...}?name= Identifier ( ASSIGN_EQUAL tl= typeListExpression )? SEMI | stmt1= conditionDeclaration | stmt2= actionDeclaration );
    public final TextMarkerStatement variableDeclaration() throws RecognitionException {
        TextMarkerStatement stmt =  null;

        Token type=null;
        Token id=null;
        Token name=null;
        NumberExpression value1 = null;

        NumberExpression value2 = null;

        StringExpression value3 = null;

        BooleanExpression value4 = null;

        Token value5 = null;

        WordListExpression list = null;

        WordTableExpression table = null;

        BooleanListExpression bl = null;

        StringListExpression sl = null;

        NumberListExpression il = null;

        NumberListExpression dl = null;

        TypeListExpression tl = null;

        TextMarkerStatement stmt1 = null;

        TextMarkerStatement stmt2 = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:246:2: (type= IntString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value1= numberExpression )? SEMI | type= DoubleString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value2= numberExpression )? SEMI | type= StringString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value3= stringExpression )? SEMI | type= BooleanString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value4= booleanExpression )? SEMI | type= TypeString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value5= annotationType )? SEMI | type= WORDLIST {...}?name= Identifier ( ASSIGN_EQUAL list= wordListExpression )? SEMI | type= WORDTABLE {...}?name= Identifier ( ASSIGN_EQUAL table= wordTableExpression )? SEMI | type= BOOLEANLIST {...}?name= Identifier ( ASSIGN_EQUAL bl= booleanListExpression )? SEMI | type= STRINGLIST {...}?name= Identifier ( ASSIGN_EQUAL sl= stringListExpression )? SEMI | type= INTLIST {...}?name= Identifier ( ASSIGN_EQUAL il= numberListExpression )? SEMI | type= DOUBLELIST {...}?name= Identifier ( ASSIGN_EQUAL dl= numberListExpression )? SEMI | type= TYPELIST {...}?name= Identifier ( ASSIGN_EQUAL tl= typeListExpression )? SEMI | stmt1= conditionDeclaration | stmt2= actionDeclaration )
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:247:2: type= IntString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value1= numberExpression )? SEMI
                    {
                    type=(Token)match(input,IntString,FOLLOW_IntString_in_variableDeclaration299); if (state.failed) return stmt;
                    if ( !((!ownsVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!ownsVariable($blockDeclaration::env, input.LT(1).getText())");
                    }
                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration308); if (state.failed) return stmt;
                    if ( state.backtracking==0 ) {
                      addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());
                    }
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:249:3: ( COMMA {...}?id= Identifier )*
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0==COMMA) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:249:4: COMMA {...}?id= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_variableDeclaration315); if (state.failed) return stmt;
                    	    if ( !((!ownsVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                    	        if (state.backtracking>0) {state.failed=true; return stmt;}
                    	        throw new FailedPredicateException(input, "variableDeclaration", "!ownsVariable($blockDeclaration::env, input.LT(1).getText())");
                    	    }
                    	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration323); if (state.failed) return stmt;
                    	    if ( state.backtracking==0 ) {
                    	      addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);

                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:250:6: ( ASSIGN_EQUAL value1= numberExpression )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==ASSIGN_EQUAL) ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:250:7: ASSIGN_EQUAL value1= numberExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration333); if (state.failed) return stmt;
                            pushFollow(FOLLOW_numberExpression_in_variableDeclaration339);
                            value1=numberExpression();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {
                      setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), value1);
                    }
                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration346); if (state.failed) return stmt;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:252:2: type= DoubleString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value2= numberExpression )? SEMI
                    {
                    type=(Token)match(input,DoubleString,FOLLOW_DoubleString_in_variableDeclaration356); if (state.failed) return stmt;
                    if ( !((!ownsVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!ownsVariable($blockDeclaration::env, input.LT(1).getText())");
                    }
                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration365); if (state.failed) return stmt;
                    if ( state.backtracking==0 ) {
                      addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());
                    }
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:254:3: ( COMMA {...}?id= Identifier )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==COMMA) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:254:4: COMMA {...}?id= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_variableDeclaration372); if (state.failed) return stmt;
                    	    if ( !((!ownsVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                    	        if (state.backtracking>0) {state.failed=true; return stmt;}
                    	        throw new FailedPredicateException(input, "variableDeclaration", "!ownsVariable($blockDeclaration::env, input.LT(1).getText())");
                    	    }
                    	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration380); if (state.failed) return stmt;
                    	    if ( state.backtracking==0 ) {
                    	      addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);

                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:255:7: ( ASSIGN_EQUAL value2= numberExpression )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0==ASSIGN_EQUAL) ) {
                        alt7=1;
                    }
                    switch (alt7) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:255:8: ASSIGN_EQUAL value2= numberExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration391); if (state.failed) return stmt;
                            pushFollow(FOLLOW_numberExpression_in_variableDeclaration397);
                            value2=numberExpression();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {
                      setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), value2);
                    }
                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration403); if (state.failed) return stmt;

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:257:2: type= StringString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value3= stringExpression )? SEMI
                    {
                    type=(Token)match(input,StringString,FOLLOW_StringString_in_variableDeclaration413); if (state.failed) return stmt;
                    if ( !((!ownsVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!ownsVariable($blockDeclaration::env, input.LT(1).getText())");
                    }
                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration422); if (state.failed) return stmt;
                    if ( state.backtracking==0 ) {
                      addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());
                    }
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:259:3: ( COMMA {...}?id= Identifier )*
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( (LA8_0==COMMA) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:259:4: COMMA {...}?id= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_variableDeclaration429); if (state.failed) return stmt;
                    	    if ( !((!ownsVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                    	        if (state.backtracking>0) {state.failed=true; return stmt;}
                    	        throw new FailedPredicateException(input, "variableDeclaration", "!ownsVariable($blockDeclaration::env, input.LT(1).getText())");
                    	    }
                    	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration437); if (state.failed) return stmt;
                    	    if ( state.backtracking==0 ) {
                    	      addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop8;
                        }
                    } while (true);

                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:260:7: ( ASSIGN_EQUAL value3= stringExpression )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0==ASSIGN_EQUAL) ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:260:8: ASSIGN_EQUAL value3= stringExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration448); if (state.failed) return stmt;
                            pushFollow(FOLLOW_stringExpression_in_variableDeclaration454);
                            value3=stringExpression();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {
                      setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), value3);
                    }
                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration460); if (state.failed) return stmt;

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:262:2: type= BooleanString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value4= booleanExpression )? SEMI
                    {
                    type=(Token)match(input,BooleanString,FOLLOW_BooleanString_in_variableDeclaration470); if (state.failed) return stmt;
                    if ( !((!ownsVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!ownsVariable($blockDeclaration::env, input.LT(1).getText())");
                    }
                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration479); if (state.failed) return stmt;
                    if ( state.backtracking==0 ) {
                      addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());
                    }
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:264:3: ( COMMA {...}?id= Identifier )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0==COMMA) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:264:4: COMMA {...}?id= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_variableDeclaration486); if (state.failed) return stmt;
                    	    if ( !((!ownsVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                    	        if (state.backtracking>0) {state.failed=true; return stmt;}
                    	        throw new FailedPredicateException(input, "variableDeclaration", "!ownsVariable($blockDeclaration::env, input.LT(1).getText())");
                    	    }
                    	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration494); if (state.failed) return stmt;
                    	    if ( state.backtracking==0 ) {
                    	      addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);

                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:265:7: ( ASSIGN_EQUAL value4= booleanExpression )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0==ASSIGN_EQUAL) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:265:8: ASSIGN_EQUAL value4= booleanExpression
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration505); if (state.failed) return stmt;
                            pushFollow(FOLLOW_booleanExpression_in_variableDeclaration511);
                            value4=booleanExpression();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {
                      setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), value4);
                    }
                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration517); if (state.failed) return stmt;

                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:267:2: type= TypeString {...}?id= Identifier ( COMMA {...}?id= Identifier )* ( ASSIGN_EQUAL value5= annotationType )? SEMI
                    {
                    type=(Token)match(input,TypeString,FOLLOW_TypeString_in_variableDeclaration527); if (state.failed) return stmt;
                    if ( !((!ownsVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!ownsVariable($blockDeclaration::env, input.LT(1).getText())");
                    }
                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration536); if (state.failed) return stmt;
                    if ( state.backtracking==0 ) {
                      addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());
                    }
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:269:3: ( COMMA {...}?id= Identifier )*
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( (LA12_0==COMMA) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:269:4: COMMA {...}?id= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_variableDeclaration543); if (state.failed) return stmt;
                    	    if ( !((!ownsVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                    	        if (state.backtracking>0) {state.failed=true; return stmt;}
                    	        throw new FailedPredicateException(input, "variableDeclaration", "!ownsVariable($blockDeclaration::env, input.LT(1).getText())");
                    	    }
                    	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration551); if (state.failed) return stmt;
                    	    if ( state.backtracking==0 ) {
                    	      addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop12;
                        }
                    } while (true);

                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:270:7: ( ASSIGN_EQUAL value5= annotationType )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0==ASSIGN_EQUAL) ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:270:8: ASSIGN_EQUAL value5= annotationType
                            {
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_variableDeclaration562); if (state.failed) return stmt;
                            pushFollow(FOLLOW_annotationType_in_variableDeclaration568);
                            value5=annotationType();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {
                      setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), value5);
                    }
                    match(input,SEMI,FOLLOW_SEMI_in_variableDeclaration574); if (state.failed) return stmt;

                    }
                    break;
                case 6 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:272:2: type= WORDLIST {...}?name= Identifier ( ASSIGN_EQUAL list= wordListExpression )? SEMI
                    {
                    type=(Token)match(input,WORDLIST,FOLLOW_WORDLIST_in_variableDeclaration585); if (state.failed) return stmt;
                    if ( !((!isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), type.getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())");
                    }
                    name=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration597); if (state.failed) return stmt;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:274:20: ( ASSIGN_EQUAL list= wordListExpression )?
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0==ASSIGN_EQUAL) ) {
                        alt14=1;
                    }
                    switch (alt14) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:274:21: ASSIGN_EQUAL list= wordListExpression
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
                    if ( state.backtracking==0 ) {
                      addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), type.getText());if(list != null){setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), list);}
                    }

                    }
                    break;
                case 7 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:276:2: type= WORDTABLE {...}?name= Identifier ( ASSIGN_EQUAL table= wordTableExpression )? SEMI
                    {
                    type=(Token)match(input,WORDTABLE,FOLLOW_WORDTABLE_in_variableDeclaration624); if (state.failed) return stmt;
                    if ( !((!isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), type.getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())");
                    }
                    name=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration636); if (state.failed) return stmt;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:278:20: ( ASSIGN_EQUAL table= wordTableExpression )?
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( (LA15_0==ASSIGN_EQUAL) ) {
                        alt15=1;
                    }
                    switch (alt15) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:278:21: ASSIGN_EQUAL table= wordTableExpression
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
                    if ( state.backtracking==0 ) {
                      addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), type.getText());if(table != null){setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), table);}
                    }

                    }
                    break;
                case 8 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:280:2: type= BOOLEANLIST {...}?name= Identifier ( ASSIGN_EQUAL bl= booleanListExpression )? SEMI
                    {
                    type=(Token)match(input,BOOLEANLIST,FOLLOW_BOOLEANLIST_in_variableDeclaration661); if (state.failed) return stmt;
                    if ( !((!isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), type.getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())");
                    }
                    name=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration673); if (state.failed) return stmt;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:282:20: ( ASSIGN_EQUAL bl= booleanListExpression )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( (LA16_0==ASSIGN_EQUAL) ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:282:21: ASSIGN_EQUAL bl= booleanListExpression
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
                    if ( state.backtracking==0 ) {
                      addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), type.getText());if(bl != null){setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), bl);}
                    }

                    }
                    break;
                case 9 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:284:2: type= STRINGLIST {...}?name= Identifier ( ASSIGN_EQUAL sl= stringListExpression )? SEMI
                    {
                    type=(Token)match(input,STRINGLIST,FOLLOW_STRINGLIST_in_variableDeclaration699); if (state.failed) return stmt;
                    if ( !((!isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), type.getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())");
                    }
                    name=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration711); if (state.failed) return stmt;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:286:20: ( ASSIGN_EQUAL sl= stringListExpression )?
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( (LA17_0==ASSIGN_EQUAL) ) {
                        alt17=1;
                    }
                    switch (alt17) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:286:21: ASSIGN_EQUAL sl= stringListExpression
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
                    if ( state.backtracking==0 ) {
                      addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), type.getText());if(sl != null){setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), sl);}
                    }

                    }
                    break;
                case 10 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:288:2: type= INTLIST {...}?name= Identifier ( ASSIGN_EQUAL il= numberListExpression )? SEMI
                    {
                    type=(Token)match(input,INTLIST,FOLLOW_INTLIST_in_variableDeclaration737); if (state.failed) return stmt;
                    if ( !((!isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), type.getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())");
                    }
                    name=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration749); if (state.failed) return stmt;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:290:20: ( ASSIGN_EQUAL il= numberListExpression )?
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( (LA18_0==ASSIGN_EQUAL) ) {
                        alt18=1;
                    }
                    switch (alt18) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:290:21: ASSIGN_EQUAL il= numberListExpression
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
                    if ( state.backtracking==0 ) {
                      addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), type.getText());if(il != null){setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), il);}
                    }

                    }
                    break;
                case 11 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:292:2: type= DOUBLELIST {...}?name= Identifier ( ASSIGN_EQUAL dl= numberListExpression )? SEMI
                    {
                    type=(Token)match(input,DOUBLELIST,FOLLOW_DOUBLELIST_in_variableDeclaration775); if (state.failed) return stmt;
                    if ( !((!isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), type.getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())");
                    }
                    name=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration787); if (state.failed) return stmt;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:294:20: ( ASSIGN_EQUAL dl= numberListExpression )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0==ASSIGN_EQUAL) ) {
                        alt19=1;
                    }
                    switch (alt19) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:294:21: ASSIGN_EQUAL dl= numberListExpression
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
                    if ( state.backtracking==0 ) {
                      addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), type.getText());if(dl != null){setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), dl);}
                    }

                    }
                    break;
                case 12 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:296:2: type= TYPELIST {...}?name= Identifier ( ASSIGN_EQUAL tl= typeListExpression )? SEMI
                    {
                    type=(Token)match(input,TYPELIST,FOLLOW_TYPELIST_in_variableDeclaration813); if (state.failed) return stmt;
                    if ( !((!isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), type.getText()))) ) {
                        if (state.backtracking>0) {state.failed=true; return stmt;}
                        throw new FailedPredicateException(input, "variableDeclaration", "!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())");
                    }
                    name=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaration825); if (state.failed) return stmt;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:298:20: ( ASSIGN_EQUAL tl= typeListExpression )?
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( (LA20_0==ASSIGN_EQUAL) ) {
                        alt20=1;
                    }
                    switch (alt20) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:298:21: ASSIGN_EQUAL tl= typeListExpression
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
                    if ( state.backtracking==0 ) {
                      addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), type.getText());if(tl != null){setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, name.getText(), tl);}
                    }

                    }
                    break;
                case 13 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:300:2: stmt1= conditionDeclaration
                    {
                    pushFollow(FOLLOW_conditionDeclaration_in_variableDeclaration851);
                    stmt1=conditionDeclaration();

                    state._fsp--;
                    if (state.failed) return stmt;
                    if ( state.backtracking==0 ) {
                      stmt = stmt1;
                    }

                    }
                    break;
                case 14 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:302:2: stmt2= actionDeclaration
                    {
                    pushFollow(FOLLOW_actionDeclaration_in_variableDeclaration863);
                    stmt2=actionDeclaration();

                    state._fsp--;
                    if (state.failed) return stmt;
                    if ( state.backtracking==0 ) {
                      stmt = stmt2;
                    }

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
        }
        return stmt;
    }
    // $ANTLR end "variableDeclaration"


    // $ANTLR start "conditionDeclaration"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:306:1: conditionDeclaration returns [TextMarkerStatement stmt = null] : type= CONDITION id= Identifier ASSIGN_EQUAL LPAREN cons= conditions RPAREN SEMI ;
    public final TextMarkerStatement conditionDeclaration() throws RecognitionException {
        TextMarkerStatement stmt =  null;

        Token type=null;
        Token id=null;
        List<AbstractTextMarkerCondition> cons = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:307:5: (type= CONDITION id= Identifier ASSIGN_EQUAL LPAREN cons= conditions RPAREN SEMI )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:308:5: type= CONDITION id= Identifier ASSIGN_EQUAL LPAREN cons= conditions RPAREN SEMI
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
            if ( state.backtracking==0 ) {
              addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());
                  AbstractTextMarkerCondition condition = ConditionFactory.createConditionAnd(cons,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
                  setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), condition);
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
        }
        return stmt;
    }
    // $ANTLR end "conditionDeclaration"


    // $ANTLR start "actionDeclaration"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:315:1: actionDeclaration returns [TextMarkerStatement stmt = null] : type= ACTION id= Identifier ASSIGN_EQUAL LPAREN a= actions RPAREN SEMI ;
    public final TextMarkerStatement actionDeclaration() throws RecognitionException {
        TextMarkerStatement stmt =  null;

        Token type=null;
        Token id=null;
        List<AbstractTextMarkerAction> a = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:316:5: (type= ACTION id= Identifier ASSIGN_EQUAL LPAREN a= actions RPAREN SEMI )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:317:5: type= ACTION id= Identifier ASSIGN_EQUAL LPAREN a= actions RPAREN SEMI
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
            if ( state.backtracking==0 ) {
              addVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), type.getText());
                  AbstractTextMarkerAction action = ActionFactory.createComposedAction(a,((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
                  setValue(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, id.getText(), action);
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
        }
        return stmt;
    }
    // $ANTLR end "actionDeclaration"


    // $ANTLR start "importStatement"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:323:1: importStatement returns [TextMarkerStatement stmt = null] : ( TypeSystemString ts= dottedIdentifier2 SEMI | ScriptString ns= dottedIdentifier2 SEMI | EngineString ns= dottedIdentifier2 SEMI );
    public final TextMarkerStatement importStatement() throws RecognitionException {
        TextMarkerStatement stmt =  null;

        String ts = null;

        String ns = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:324:2: ( TypeSystemString ts= dottedIdentifier2 SEMI | ScriptString ns= dottedIdentifier2 SEMI | EngineString ns= dottedIdentifier2 SEMI )
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:325:2: TypeSystemString ts= dottedIdentifier2 SEMI
                    {
                    match(input,TypeSystemString,FOLLOW_TypeSystemString_in_importStatement993); if (state.failed) return stmt;
                    pushFollow(FOLLOW_dottedIdentifier2_in_importStatement999);
                    ts=dottedIdentifier2();

                    state._fsp--;
                    if (state.failed) return stmt;
                    if ( state.backtracking==0 ) {
                      addImportTypeSystem(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, ts);
                    }
                    match(input,SEMI,FOLLOW_SEMI_in_importStatement1002); if (state.failed) return stmt;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:326:4: ScriptString ns= dottedIdentifier2 SEMI
                    {
                    match(input,ScriptString,FOLLOW_ScriptString_in_importStatement1007); if (state.failed) return stmt;
                    pushFollow(FOLLOW_dottedIdentifier2_in_importStatement1013);
                    ns=dottedIdentifier2();

                    state._fsp--;
                    if (state.failed) return stmt;
                    if ( state.backtracking==0 ) {
                      addImportScript(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, ns);
                    }
                    match(input,SEMI,FOLLOW_SEMI_in_importStatement1016); if (state.failed) return stmt;

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:327:4: EngineString ns= dottedIdentifier2 SEMI
                    {
                    match(input,EngineString,FOLLOW_EngineString_in_importStatement1021); if (state.failed) return stmt;
                    pushFollow(FOLLOW_dottedIdentifier2_in_importStatement1027);
                    ns=dottedIdentifier2();

                    state._fsp--;
                    if (state.failed) return stmt;
                    if ( state.backtracking==0 ) {
                      addImportEngine(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, ns);
                    }
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
        }
        return stmt;
    }
    // $ANTLR end "importStatement"


    // $ANTLR start "declaration"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:330:1: declaration returns [TextMarkerStatement stmt = null] : ( DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* SEMI | DECLARE type= annotationType newName= Identifier ( LPAREN (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI ) ;
    public final TextMarkerStatement declaration() throws RecognitionException {
        TextMarkerStatement stmt =  null;

        Token id=null;
        Token newName=null;
        Token obj2=null;
        Token obj3=null;
        Token obj4=null;
        Token obj5=null;
        Token fname=null;
        Token lazyParent = null;

        Token type = null;

        Token obj1 = null;



        List featureTypes = new ArrayList();
        List featureNames = new ArrayList();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:335:2: ( ( DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* SEMI | DECLARE type= annotationType newName= Identifier ( LPAREN (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI ) )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:336:2: ( DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* SEMI | DECLARE type= annotationType newName= Identifier ( LPAREN (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI )
            {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:336:2: ( DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* SEMI | DECLARE type= annotationType newName= Identifier ( LPAREN (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI )
            int alt28=2;
            alt28 = dfa28.predict(input);
            switch (alt28) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:337:2: DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* SEMI
                    {
                    match(input,DECLARE,FOLLOW_DECLARE_in_declaration1054); if (state.failed) return stmt;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:339:13: (lazyParent= annotationType )?
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
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:339:13: lazyParent= annotationType
                            {
                            pushFollow(FOLLOW_annotationType_in_declaration1064);
                            lazyParent=annotationType();

                            state._fsp--;
                            if (state.failed) return stmt;

                            }
                            break;

                    }

                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1072); if (state.failed) return stmt;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:341:4: ( COMMA id= Identifier )*
                    loop24:
                    do {
                        int alt24=2;
                        int LA24_0 = input.LA(1);

                        if ( (LA24_0==COMMA) ) {
                            alt24=1;
                        }


                        switch (alt24) {
                    	case 1 :
                    	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:341:5: COMMA id= Identifier
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:346:2: DECLARE type= annotationType newName= Identifier ( LPAREN (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI
                    {
                    match(input,DECLARE,FOLLOW_DECLARE_in_declaration1109); if (state.failed) return stmt;
                    pushFollow(FOLLOW_annotationType_in_declaration1115);
                    type=annotationType();

                    state._fsp--;
                    if (state.failed) return stmt;
                    newName=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1121); if (state.failed) return stmt;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:347:3: ( LPAREN (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString ) fname= Identifier )* RPAREN )
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:347:4: LPAREN (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString ) fname= Identifier )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_declaration1127); if (state.failed) return stmt;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:348:4: (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString )
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
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:349:4: obj1= annotationType
                            {
                            pushFollow(FOLLOW_annotationType_in_declaration1142);
                            obj1=annotationType();

                            state._fsp--;
                            if (state.failed) return stmt;
                            if ( state.backtracking==0 ) {
                              featureTypes.add(obj1.getText());
                            }

                            }
                            break;
                        case 2 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:350:6: obj2= StringString
                            {
                            obj2=(Token)match(input,StringString,FOLLOW_StringString_in_declaration1155); if (state.failed) return stmt;
                            if ( state.backtracking==0 ) {
                              featureTypes.add(obj2.getText());
                            }

                            }
                            break;
                        case 3 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:351:6: obj3= DoubleString
                            {
                            obj3=(Token)match(input,DoubleString,FOLLOW_DoubleString_in_declaration1168); if (state.failed) return stmt;
                            if ( state.backtracking==0 ) {
                              featureTypes.add(obj3.getText());
                            }

                            }
                            break;
                        case 4 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:352:6: obj4= IntString
                            {
                            obj4=(Token)match(input,IntString,FOLLOW_IntString_in_declaration1180); if (state.failed) return stmt;
                            if ( state.backtracking==0 ) {
                              featureTypes.add(obj4.getText());
                            }

                            }
                            break;
                        case 5 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:353:6: obj5= BooleanString
                            {
                            obj5=(Token)match(input,BooleanString,FOLLOW_BooleanString_in_declaration1192); if (state.failed) return stmt;
                            if ( state.backtracking==0 ) {
                              featureTypes.add(obj5.getText());
                            }

                            }
                            break;

                    }

                    fname=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1208); if (state.failed) return stmt;
                    if ( state.backtracking==0 ) {
                      featureNames.add(fname.getText());
                    }
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:356:4: ( COMMA (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString ) fname= Identifier )*
                    loop27:
                    do {
                        int alt27=2;
                        int LA27_0 = input.LA(1);

                        if ( (LA27_0==COMMA) ) {
                            alt27=1;
                        }


                        switch (alt27) {
                    	case 1 :
                    	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:357:4: COMMA (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString ) fname= Identifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_declaration1220); if (state.failed) return stmt;
                    	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:358:4: (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString )
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
                    	            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:359:4: obj1= annotationType
                    	            {
                    	            pushFollow(FOLLOW_annotationType_in_declaration1235);
                    	            obj1=annotationType();

                    	            state._fsp--;
                    	            if (state.failed) return stmt;
                    	            if ( state.backtracking==0 ) {
                    	              featureTypes.add(obj1.getText());
                    	            }

                    	            }
                    	            break;
                    	        case 2 :
                    	            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:360:6: obj2= StringString
                    	            {
                    	            obj2=(Token)match(input,StringString,FOLLOW_StringString_in_declaration1248); if (state.failed) return stmt;
                    	            if ( state.backtracking==0 ) {
                    	              featureTypes.add(obj2.getText());
                    	            }

                    	            }
                    	            break;
                    	        case 3 :
                    	            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:361:6: obj3= DoubleString
                    	            {
                    	            obj3=(Token)match(input,DoubleString,FOLLOW_DoubleString_in_declaration1261); if (state.failed) return stmt;
                    	            if ( state.backtracking==0 ) {
                    	              featureTypes.add(obj3.getText());
                    	            }

                    	            }
                    	            break;
                    	        case 4 :
                    	            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:362:6: obj4= IntString
                    	            {
                    	            obj4=(Token)match(input,IntString,FOLLOW_IntString_in_declaration1273); if (state.failed) return stmt;
                    	            if ( state.backtracking==0 ) {
                    	              featureTypes.add(obj4.getText());
                    	            }

                    	            }
                    	            break;
                    	        case 5 :
                    	            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:363:6: obj5= BooleanString
                    	            {
                    	            obj5=(Token)match(input,BooleanString,FOLLOW_BooleanString_in_declaration1285); if (state.failed) return stmt;
                    	            if ( state.backtracking==0 ) {
                    	              featureTypes.add(obj5.getText());
                    	            }

                    	            }
                    	            break;

                    	    }

                    	    fname=(Token)match(input,Identifier,FOLLOW_Identifier_in_declaration1301); if (state.failed) return stmt;
                    	    if ( state.backtracking==0 ) {
                    	      featureNames.add(fname.getText());
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop27;
                        }
                    } while (true);

                    match(input,RPAREN,FOLLOW_RPAREN_in_declaration1309); if (state.failed) return stmt;

                    }

                    match(input,SEMI,FOLLOW_SEMI_in_declaration1312); if (state.failed) return stmt;
                    if ( state.backtracking==0 ) {
                      addType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, newName.getText(), type.getText(), featureTypes, featureNames);
                    }

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
        }
        return stmt;
    }
    // $ANTLR end "declaration"

    protected static class blockDeclaration_scope {
        TextMarkerBlock env;
    }
    protected Stack blockDeclaration_stack = new Stack();


    // $ANTLR start "blockDeclaration"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:372:1: blockDeclaration returns [TextMarkerBlock block = null] options {backtrack=true; } : type= BlockString LPAREN id= Identifier RPAREN re1= ruleElementWithCA LCURLY body= statements RCURLY ;
    public final TextMarkerBlock blockDeclaration() throws RecognitionException {
        blockDeclaration_stack.push(new blockDeclaration_scope());
        TextMarkerBlock block =  null;

        Token type=null;
        Token id=null;
        TextMarkerRuleElement re1 = null;

        List<TextMarkerStatement> body = null;



        TextMarkerRuleElement re = null;
        level++;

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:388:2: (type= BlockString LPAREN id= Identifier RPAREN re1= ruleElementWithCA LCURLY body= statements RCURLY )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:389:2: type= BlockString LPAREN id= Identifier RPAREN re1= ruleElementWithCA LCURLY body= statements RCURLY
            {
            type=(Token)match(input,BlockString,FOLLOW_BlockString_in_blockDeclaration1370); if (state.failed) return block;
            match(input,LPAREN,FOLLOW_LPAREN_in_blockDeclaration1374); if (state.failed) return block;
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_blockDeclaration1381); if (state.failed) return block;
            match(input,RPAREN,FOLLOW_RPAREN_in_blockDeclaration1385); if (state.failed) return block;
            if ( state.backtracking==0 ) {
              block = factory.createScriptBlock(id, re, body, ((blockDeclaration_scope)blockDeclaration_stack.elementAt(level - 1)).env, cas);
            }
            if ( state.backtracking==0 ) {
              ((blockDeclaration_scope)blockDeclaration_stack.peek()).env = block;
            }
            pushFollow(FOLLOW_ruleElementWithCA_in_blockDeclaration1398);
            re1=ruleElementWithCA();

            state._fsp--;
            if (state.failed) return block;
            if ( state.backtracking==0 ) {
              re = re1;
            }
            if ( state.backtracking==0 ) {
              TextMarkerRule rule = factory.createRule(re, block);
              	if(block instanceof TextMarkerScriptBlock) {
              	((TextMarkerScriptBlock)block).setMatchRule(rule);
              	}
            }
            match(input,LCURLY,FOLLOW_LCURLY_in_blockDeclaration1406); if (state.failed) return block;
            pushFollow(FOLLOW_statements_in_blockDeclaration1412);
            body=statements();

            state._fsp--;
            if (state.failed) return block;
            match(input,RCURLY,FOLLOW_RCURLY_in_blockDeclaration1414); if (state.failed) return block;
            if ( state.backtracking==0 ) {
              block.setElements(body);
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
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:406:1: automataDeclaration returns [TextMarkerBlock block = null] options {backtrack=true; } : type= AutomataBlockString LPAREN id= Identifier RPAREN re1= ruleElementWithCA LCURLY body= statements RCURLY ;
    public final TextMarkerBlock automataDeclaration() throws RecognitionException {
        automataDeclaration_stack.push(new automataDeclaration_scope());
        TextMarkerBlock block =  null;

        Token type=null;
        Token id=null;
        TextMarkerRuleElement re1 = null;

        List<TextMarkerStatement> body = null;



        TextMarkerRuleElement re = null;
        TextMarkerScriptFactory oldFactory = factory;
        factory = automataFactory; 
        level++;

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:424:2: (type= AutomataBlockString LPAREN id= Identifier RPAREN re1= ruleElementWithCA LCURLY body= statements RCURLY )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:426:2: type= AutomataBlockString LPAREN id= Identifier RPAREN re1= ruleElementWithCA LCURLY body= statements RCURLY
            {
            type=(Token)match(input,AutomataBlockString,FOLLOW_AutomataBlockString_in_automataDeclaration1466); if (state.failed) return block;
            match(input,LPAREN,FOLLOW_LPAREN_in_automataDeclaration1470); if (state.failed) return block;
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_automataDeclaration1477); if (state.failed) return block;
            match(input,RPAREN,FOLLOW_RPAREN_in_automataDeclaration1481); if (state.failed) return block;
            if ( state.backtracking==0 ) {
              block = factory.createAutomataBlock(id, re, body, ((blockDeclaration_scope)blockDeclaration_stack.elementAt(level - 1)).env, cas);
            }
            if ( state.backtracking==0 ) {
              ((blockDeclaration_scope)blockDeclaration_stack.peek()).env = block;
            }
            pushFollow(FOLLOW_ruleElementWithCA_in_automataDeclaration1494);
            re1=ruleElementWithCA();

            state._fsp--;
            if (state.failed) return block;
            if ( state.backtracking==0 ) {
              re = re1;
            }
            if ( state.backtracking==0 ) {
              TextMarkerRule rule = factory.createRule(re, block);
              	if(block instanceof TextMarkerAutomataBlock) {
              	((TextMarkerAutomataBlock)block).setMatchRule(rule);
              	}
            }
            match(input,LCURLY,FOLLOW_LCURLY_in_automataDeclaration1502); if (state.failed) return block;
            pushFollow(FOLLOW_statements_in_automataDeclaration1508);
            body=statements();

            state._fsp--;
            if (state.failed) return block;
            match(input,RCURLY,FOLLOW_RCURLY_in_automataDeclaration1510); if (state.failed) return block;
            if ( state.backtracking==0 ) {
              block.setElements(body);
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
            automataDeclaration_stack.pop();
        }
        return block;
    }
    // $ANTLR end "automataDeclaration"


    // $ANTLR start "ruleElementWithCA"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:445:1: ruleElementWithCA returns [TextMarkerRuleElement re = null] : idRef= typeExpression (quantifier= quantifierPart )? LCURLY (c= conditions )? ( THEN a= actions )? RCURLY ;
    public final TextMarkerRuleElement ruleElementWithCA() throws RecognitionException {
        TextMarkerRuleElement re =  null;

        TypeExpression idRef = null;

        RuleElementQuantifier quantifier = null;

        List<AbstractTextMarkerCondition> c = null;

        List<AbstractTextMarkerAction> a = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:446:5: (idRef= typeExpression (quantifier= quantifierPart )? LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:447:5: idRef= typeExpression (quantifier= quantifierPart )? LCURLY (c= conditions )? ( THEN a= actions )? RCURLY
            {
            pushFollow(FOLLOW_typeExpression_in_ruleElementWithCA1541);
            idRef=typeExpression();

            state._fsp--;
            if (state.failed) return re;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:447:37: (quantifier= quantifierPart )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==LBRACK||LA29_0==PLUS||LA29_0==STAR||LA29_0==QUESTION) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:447:37: quantifier= quantifierPart
                    {
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementWithCA1547);
                    quantifier=quantifierPart();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }

            match(input,LCURLY,FOLLOW_LCURLY_in_ruleElementWithCA1559); if (state.failed) return re;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:448:18: (c= conditions )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==CONTAINS||(LA30_0>=AND && LA30_0<=PARSE)||(LA30_0>=BEFORE && LA30_0<=NOT)||LA30_0==SIZE||LA30_0==Identifier||LA30_0==MINUS) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:448:18: c= conditions
                    {
                    pushFollow(FOLLOW_conditions_in_ruleElementWithCA1565);
                    c=conditions();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }

            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:448:32: ( THEN a= actions )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==THEN) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:448:33: THEN a= actions
                    {
                    match(input,THEN,FOLLOW_THEN_in_ruleElementWithCA1569); if (state.failed) return re;
                    pushFollow(FOLLOW_actions_in_ruleElementWithCA1575);
                    a=actions();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }

            match(input,RCURLY,FOLLOW_RCURLY_in_ruleElementWithCA1579); if (state.failed) return re;
            if ( state.backtracking==0 ) {
              re = factory.createRuleElement(idRef,quantifier,c,a, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return re;
    }
    // $ANTLR end "ruleElementWithCA"


    // $ANTLR start "ruleElementWithoutCA"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:453:1: ruleElementWithoutCA returns [TextMarkerRuleElement re = null] : idRef= typeExpression (quantifier= quantifierPart )? ;
    public final TextMarkerRuleElement ruleElementWithoutCA() throws RecognitionException {
        TextMarkerRuleElement re =  null;

        TypeExpression idRef = null;

        RuleElementQuantifier quantifier = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:454:5: (idRef= typeExpression (quantifier= quantifierPart )? )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:455:5: idRef= typeExpression (quantifier= quantifierPart )?
            {
            pushFollow(FOLLOW_typeExpression_in_ruleElementWithoutCA1619);
            idRef=typeExpression();

            state._fsp--;
            if (state.failed) return re;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:455:37: (quantifier= quantifierPart )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==LBRACK||LA32_0==PLUS||LA32_0==STAR||LA32_0==QUESTION) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:455:37: quantifier= quantifierPart
                    {
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementWithoutCA1625);
                    quantifier=quantifierPart();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              re = factory.createRuleElement(idRef,quantifier,null,null, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return re;
    }
    // $ANTLR end "ruleElementWithoutCA"


    // $ANTLR start "simpleStatement"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:460:1: simpleStatement returns [TextMarkerRule stmt = null] : elements= ruleElements SEMI ;
    public final TextMarkerRule simpleStatement() throws RecognitionException {
        TextMarkerRule stmt =  null;

        List<RuleElement> elements = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:461:2: (elements= ruleElements SEMI )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:462:2: elements= ruleElements SEMI
            {
            pushFollow(FOLLOW_ruleElements_in_simpleStatement1669);
            elements=ruleElements();

            state._fsp--;
            if (state.failed) return stmt;
            match(input,SEMI,FOLLOW_SEMI_in_simpleStatement1671); if (state.failed) return stmt;
            if ( state.backtracking==0 ) {
              stmt = factory.createRule(elements, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return stmt;
    }
    // $ANTLR end "simpleStatement"


    // $ANTLR start "ruleElements"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:466:1: ruleElements returns [List<RuleElement> elements = new ArrayList<RuleElement>()] : re= ruleElement (re= ruleElement )* ;
    public final List<RuleElement> ruleElements() throws RecognitionException {
        List<RuleElement> elements =  new ArrayList<RuleElement>();

        RuleElement re = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:467:2: (re= ruleElement (re= ruleElement )* )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:468:2: re= ruleElement (re= ruleElement )*
            {
            pushFollow(FOLLOW_ruleElement_in_ruleElements1697);
            re=ruleElement();

            state._fsp--;
            if (state.failed) return elements;
            if ( state.backtracking==0 ) {
              elements.add(re);
            }
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:468:39: (re= ruleElement )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==BasicAnnotationType||LA33_0==StringLiteral||(LA33_0>=Identifier && LA33_0<=LPAREN)) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:468:40: re= ruleElement
            	    {
            	    pushFollow(FOLLOW_ruleElement_in_ruleElements1706);
            	    re=ruleElement();

            	    state._fsp--;
            	    if (state.failed) return elements;
            	    if ( state.backtracking==0 ) {
            	      elements.add(re);
            	    }

            	    }
            	    break;

            	default :
            	    break loop33;
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
        }
        return elements;
    }
    // $ANTLR end "ruleElements"


    // $ANTLR start "blockRuleElement"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:471:1: blockRuleElement returns [List<RuleElement> elements = new ArrayList<RuleElement>()] : re= ruleElementType ;
    public final List<RuleElement> blockRuleElement() throws RecognitionException {
        List<RuleElement> elements =  new ArrayList<RuleElement>();

        TextMarkerRuleElement re = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:472:2: (re= ruleElementType )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:473:2: re= ruleElementType
            {
            pushFollow(FOLLOW_ruleElementType_in_blockRuleElement1731);
            re=ruleElementType();

            state._fsp--;
            if (state.failed) return elements;
            if ( state.backtracking==0 ) {
              elements.add(re);
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
        }
        return elements;
    }
    // $ANTLR end "blockRuleElement"


    // $ANTLR start "ruleElement"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:477:1: ruleElement returns [RuleElement re = null] : (re1= ruleElementType | re2= ruleElementLiteral | re3= ruleElementComposed );
    public final RuleElement ruleElement() throws RecognitionException {
        RuleElement re =  null;

        TextMarkerRuleElement re1 = null;

        TextMarkerRuleElement re2 = null;

        ComposedRuleElement re3 = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:478:2: (re1= ruleElementType | re2= ruleElementLiteral | re3= ruleElementComposed )
            int alt34=3;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA34_1 = input.LA(2);

                if ( (!(((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRING"))))) ) {
                    alt34=1;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRING"))) ) {
                    alt34=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return re;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 34, 1, input);

                    throw nvae;
                }
                }
                break;
            case BasicAnnotationType:
                {
                alt34=1;
                }
                break;
            case StringLiteral:
                {
                alt34=2;
                }
                break;
            case LPAREN:
                {
                alt34=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return re;}
                NoViableAltException nvae =
                    new NoViableAltException("", 34, 0, input);

                throw nvae;
            }

            switch (alt34) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:479:2: re1= ruleElementType
                    {
                    pushFollow(FOLLOW_ruleElementType_in_ruleElement1755);
                    re1=ruleElementType();

                    state._fsp--;
                    if (state.failed) return re;
                    if ( state.backtracking==0 ) {
                      re = re1;
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:480:4: re2= ruleElementLiteral
                    {
                    pushFollow(FOLLOW_ruleElementLiteral_in_ruleElement1766);
                    re2=ruleElementLiteral();

                    state._fsp--;
                    if (state.failed) return re;
                    if ( state.backtracking==0 ) {
                      re = re2;
                    }

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:481:4: re3= ruleElementComposed
                    {
                    pushFollow(FOLLOW_ruleElementComposed_in_ruleElement1777);
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
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}
        finally {
        }
        return re;
    }
    // $ANTLR end "ruleElement"


    // $ANTLR start "ruleElementComposed"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:484:1: ruleElementComposed returns [ComposedRuleElement re = null] : LPAREN res= ruleElements RPAREN q= quantifierPart ;
    public final ComposedRuleElement ruleElementComposed() throws RecognitionException {
        ComposedRuleElement re =  null;

        List<RuleElement> res = null;

        RuleElementQuantifier q = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:485:2: ( LPAREN res= ruleElements RPAREN q= quantifierPart )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:486:2: LPAREN res= ruleElements RPAREN q= quantifierPart
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_ruleElementComposed1797); if (state.failed) return re;
            pushFollow(FOLLOW_ruleElements_in_ruleElementComposed1803);
            res=ruleElements();

            state._fsp--;
            if (state.failed) return re;
            match(input,RPAREN,FOLLOW_RPAREN_in_ruleElementComposed1805); if (state.failed) return re;
            pushFollow(FOLLOW_quantifierPart_in_ruleElementComposed1811);
            q=quantifierPart();

            state._fsp--;
            if (state.failed) return re;
            if ( state.backtracking==0 ) {
              re = factory.createComposedRuleElement(res, q, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return re;
    }
    // $ANTLR end "ruleElementComposed"


    // $ANTLR start "ruleElementType"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:490:1: ruleElementType returns [TextMarkerRuleElement re = null] : ( typeExpression )=>typeExpr= typeExpression (quantifier= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )? ;
    public final TextMarkerRuleElement ruleElementType() throws RecognitionException {
        TextMarkerRuleElement re =  null;

        TypeExpression typeExpr = null;

        RuleElementQuantifier quantifier = null;

        List<AbstractTextMarkerCondition> c = null;

        List<AbstractTextMarkerAction> a = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:491:5: ( ( typeExpression )=>typeExpr= typeExpression (quantifier= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )? )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:493:5: ( typeExpression )=>typeExpr= typeExpression (quantifier= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )?
            {
            pushFollow(FOLLOW_typeExpression_in_ruleElementType1849);
            typeExpr=typeExpression();

            state._fsp--;
            if (state.failed) return re;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:493:60: (quantifier= quantifierPart )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==LBRACK||LA35_0==PLUS||LA35_0==STAR||LA35_0==QUESTION) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:493:60: quantifier= quantifierPart
                    {
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementType1855);
                    quantifier=quantifierPart();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }

            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:494:9: ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==LCURLY) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:494:10: LCURLY (c= conditions )? ( THEN a= actions )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_ruleElementType1868); if (state.failed) return re;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:494:19: (c= conditions )?
                    int alt36=2;
                    int LA36_0 = input.LA(1);

                    if ( (LA36_0==CONTAINS||(LA36_0>=AND && LA36_0<=PARSE)||(LA36_0>=BEFORE && LA36_0<=NOT)||LA36_0==SIZE||LA36_0==Identifier||LA36_0==MINUS) ) {
                        alt36=1;
                    }
                    switch (alt36) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:494:19: c= conditions
                            {
                            pushFollow(FOLLOW_conditions_in_ruleElementType1874);
                            c=conditions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }

                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:494:33: ( THEN a= actions )?
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==THEN) ) {
                        alt37=1;
                    }
                    switch (alt37) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:494:34: THEN a= actions
                            {
                            match(input,THEN,FOLLOW_THEN_in_ruleElementType1878); if (state.failed) return re;
                            pushFollow(FOLLOW_actions_in_ruleElementType1884);
                            a=actions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }

                    match(input,RCURLY,FOLLOW_RCURLY_in_ruleElementType1888); if (state.failed) return re;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              re = factory.createRuleElement(typeExpr, quantifier, c, a, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return re;
    }
    // $ANTLR end "ruleElementType"


    // $ANTLR start "ruleElementLiteral"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:498:1: ruleElementLiteral returns [TextMarkerRuleElement re = null] : ( simpleStringExpression )=>stringExpr= simpleStringExpression (quantifier= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )? ;
    public final TextMarkerRuleElement ruleElementLiteral() throws RecognitionException {
        TextMarkerRuleElement re =  null;

        StringExpression stringExpr = null;

        RuleElementQuantifier quantifier = null;

        List<AbstractTextMarkerCondition> c = null;

        List<AbstractTextMarkerAction> a = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:499:5: ( ( simpleStringExpression )=>stringExpr= simpleStringExpression (quantifier= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )? )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:500:5: ( simpleStringExpression )=>stringExpr= simpleStringExpression (quantifier= quantifierPart )? ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )?
            {
            pushFollow(FOLLOW_simpleStringExpression_in_ruleElementLiteral1932);
            stringExpr=simpleStringExpression();

            state._fsp--;
            if (state.failed) return re;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:500:78: (quantifier= quantifierPart )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==LBRACK||LA39_0==PLUS||LA39_0==STAR||LA39_0==QUESTION) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:500:78: quantifier= quantifierPart
                    {
                    pushFollow(FOLLOW_quantifierPart_in_ruleElementLiteral1938);
                    quantifier=quantifierPart();

                    state._fsp--;
                    if (state.failed) return re;

                    }
                    break;

            }

            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:501:9: ( LCURLY (c= conditions )? ( THEN a= actions )? RCURLY )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==LCURLY) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:501:10: LCURLY (c= conditions )? ( THEN a= actions )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_ruleElementLiteral1951); if (state.failed) return re;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:501:19: (c= conditions )?
                    int alt40=2;
                    int LA40_0 = input.LA(1);

                    if ( (LA40_0==CONTAINS||(LA40_0>=AND && LA40_0<=PARSE)||(LA40_0>=BEFORE && LA40_0<=NOT)||LA40_0==SIZE||LA40_0==Identifier||LA40_0==MINUS) ) {
                        alt40=1;
                    }
                    switch (alt40) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:501:19: c= conditions
                            {
                            pushFollow(FOLLOW_conditions_in_ruleElementLiteral1957);
                            c=conditions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }

                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:501:33: ( THEN a= actions )?
                    int alt41=2;
                    int LA41_0 = input.LA(1);

                    if ( (LA41_0==THEN) ) {
                        alt41=1;
                    }
                    switch (alt41) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:501:34: THEN a= actions
                            {
                            match(input,THEN,FOLLOW_THEN_in_ruleElementLiteral1961); if (state.failed) return re;
                            pushFollow(FOLLOW_actions_in_ruleElementLiteral1967);
                            a=actions();

                            state._fsp--;
                            if (state.failed) return re;

                            }
                            break;

                    }

                    match(input,RCURLY,FOLLOW_RCURLY_in_ruleElementLiteral1971); if (state.failed) return re;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              re = factory.createRuleElement(stringExpr, quantifier, c, a, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return re;
    }
    // $ANTLR end "ruleElementLiteral"


    // $ANTLR start "conditions"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:505:1: conditions returns [List<AbstractTextMarkerCondition> conds = new ArrayList<AbstractTextMarkerCondition>()] : c= condition ( COMMA c= condition )* ;
    public final List<AbstractTextMarkerCondition> conditions() throws RecognitionException {
        List<AbstractTextMarkerCondition> conds =  new ArrayList<AbstractTextMarkerCondition>();

        AbstractTextMarkerCondition c = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:506:5: (c= condition ( COMMA c= condition )* )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:507:5: c= condition ( COMMA c= condition )*
            {
            pushFollow(FOLLOW_condition_in_conditions2010);
            c=condition();

            state._fsp--;
            if (state.failed) return conds;
            if ( state.backtracking==0 ) {
              conds.add(c);
            }
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:507:35: ( COMMA c= condition )*
            loop43:
            do {
                int alt43=2;
                int LA43_0 = input.LA(1);

                if ( (LA43_0==COMMA) ) {
                    alt43=1;
                }


                switch (alt43) {
            	case 1 :
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:507:36: COMMA c= condition
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_conditions2015); if (state.failed) return conds;
            	    pushFollow(FOLLOW_condition_in_conditions2021);
            	    c=condition();

            	    state._fsp--;
            	    if (state.failed) return conds;
            	    if ( state.backtracking==0 ) {
            	      conds.add(c);
            	    }

            	    }
            	    break;

            	default :
            	    break loop43;
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
        }
        return conds;
    }
    // $ANTLR end "conditions"


    // $ANTLR start "actions"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:510:1: actions returns [List<AbstractTextMarkerAction> actions = new ArrayList<AbstractTextMarkerAction>()] : a= action ( COMMA a= action )* ;
    public final List<AbstractTextMarkerAction> actions() throws RecognitionException {
        List<AbstractTextMarkerAction> actions =  new ArrayList<AbstractTextMarkerAction>();

        AbstractTextMarkerAction a = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:511:5: (a= action ( COMMA a= action )* )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:512:5: a= action ( COMMA a= action )*
            {
            pushFollow(FOLLOW_action_in_actions2059);
            a=action();

            state._fsp--;
            if (state.failed) return actions;
            if ( state.backtracking==0 ) {
              actions.add(a);
            }
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:512:34: ( COMMA a= action )*
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( (LA44_0==COMMA) ) {
                    alt44=1;
                }


                switch (alt44) {
            	case 1 :
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:512:35: COMMA a= action
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actions2064); if (state.failed) return actions;
            	    pushFollow(FOLLOW_action_in_actions2070);
            	    a=action();

            	    state._fsp--;
            	    if (state.failed) return actions;
            	    if ( state.backtracking==0 ) {
            	      actions.add(a);
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
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}
        finally {
        }
        return actions;
    }
    // $ANTLR end "actions"


    // $ANTLR start "listExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:516:1: listExpression returns [ListExpression expr = null] : ( ( booleanListExpression )=>bl= booleanListExpression | ( intListExpression )=>il= intListExpression | ( doubleListExpression )=>dl= doubleListExpression | ( stringListExpression )=>sl= stringListExpression | ( typeListExpression )=>tl= typeListExpression );
    public final ListExpression listExpression() throws RecognitionException {
        ListExpression expr =  null;

        BooleanListExpression bl = null;

        NumberListExpression il = null;

        NumberListExpression dl = null;

        StringListExpression sl = null;

        TypeListExpression tl = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:517:2: ( ( booleanListExpression )=>bl= booleanListExpression | ( intListExpression )=>il= intListExpression | ( doubleListExpression )=>dl= doubleListExpression | ( stringListExpression )=>sl= stringListExpression | ( typeListExpression )=>tl= typeListExpression )
            int alt45=5;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==LCURLY) ) {
                int LA45_1 = input.LA(2);

                if ( (synpred3_TextMarkerParser()) ) {
                    alt45=1;
                }
                else if ( (synpred4_TextMarkerParser()) ) {
                    alt45=2;
                }
                else if ( (synpred5_TextMarkerParser()) ) {
                    alt45=3;
                }
                else if ( (synpred6_TextMarkerParser()) ) {
                    alt45=4;
                }
                else if ( (synpred7_TextMarkerParser()) ) {
                    alt45=5;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 45, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA45_0==Identifier) ) {
                int LA45_2 = input.LA(2);

                if ( ((synpred3_TextMarkerParser()&&(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "BOOLEANLIST")))) ) {
                    alt45=1;
                }
                else if ( ((synpred4_TextMarkerParser()&&(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INTLIST")))) ) {
                    alt45=2;
                }
                else if ( ((synpred5_TextMarkerParser()&&(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLELIST")))) ) {
                    alt45=3;
                }
                else if ( ((synpred6_TextMarkerParser()&&(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRINGLIST")))) ) {
                    alt45=4;
                }
                else if ( ((synpred7_TextMarkerParser()&&(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST")))) ) {
                    alt45=5;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 45, 2, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 45, 0, input);

                throw nvae;
            }
            switch (alt45) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:518:2: ( booleanListExpression )=>bl= booleanListExpression
                    {
                    pushFollow(FOLLOW_booleanListExpression_in_listExpression2106);
                    bl=booleanListExpression();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = bl;
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:519:4: ( intListExpression )=>il= intListExpression
                    {
                    pushFollow(FOLLOW_intListExpression_in_listExpression2122);
                    il=intListExpression();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = il;
                    }

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:520:4: ( doubleListExpression )=>dl= doubleListExpression
                    {
                    pushFollow(FOLLOW_doubleListExpression_in_listExpression2138);
                    dl=doubleListExpression();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = dl;
                    }

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:521:4: ( stringListExpression )=>sl= stringListExpression
                    {
                    pushFollow(FOLLOW_stringListExpression_in_listExpression2154);
                    sl=stringListExpression();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = sl;
                    }

                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:522:4: ( typeListExpression )=>tl= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_listExpression2170);
                    tl=typeListExpression();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = tl;
                    }

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
        }
        return expr;
    }
    // $ANTLR end "listExpression"


    // $ANTLR start "booleanListExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:525:1: booleanListExpression returns [BooleanListExpression expr = null] : e= simpleBooleanListExpression ;
    public final BooleanListExpression booleanListExpression() throws RecognitionException {
        BooleanListExpression expr =  null;

        BooleanListExpression e = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:526:2: (e= simpleBooleanListExpression )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:527:2: e= simpleBooleanListExpression
            {
            pushFollow(FOLLOW_simpleBooleanListExpression_in_booleanListExpression2192);
            e=simpleBooleanListExpression();

            state._fsp--;
            if (state.failed) return expr;
            if ( state.backtracking==0 ) {
              expr = e;
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
        }
        return expr;
    }
    // $ANTLR end "booleanListExpression"


    // $ANTLR start "simpleBooleanListExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:530:1: simpleBooleanListExpression returns [BooleanListExpression expr = null] : ( LCURLY (e= simpleBooleanExpression ( COMMA e= simpleBooleanExpression )* )? RCURLY | {...}?var= Identifier );
    public final BooleanListExpression simpleBooleanListExpression() throws RecognitionException {
        BooleanListExpression expr =  null;

        Token var=null;
        BooleanExpression e = null;



        	List<BooleanExpression> list = new ArrayList<BooleanExpression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:533:3: ( LCURLY (e= simpleBooleanExpression ( COMMA e= simpleBooleanExpression )* )? RCURLY | {...}?var= Identifier )
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==LCURLY) ) {
                alt48=1;
            }
            else if ( (LA48_0==Identifier) ) {
                alt48=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 48, 0, input);

                throw nvae;
            }
            switch (alt48) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:534:2: LCURLY (e= simpleBooleanExpression ( COMMA e= simpleBooleanExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleBooleanListExpression2213); if (state.failed) return expr;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:534:9: (e= simpleBooleanExpression ( COMMA e= simpleBooleanExpression )* )?
                    int alt47=2;
                    int LA47_0 = input.LA(1);

                    if ( ((LA47_0>=TRUE && LA47_0<=FALSE)||LA47_0==Identifier) ) {
                        alt47=1;
                    }
                    switch (alt47) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:534:10: e= simpleBooleanExpression ( COMMA e= simpleBooleanExpression )*
                            {
                            pushFollow(FOLLOW_simpleBooleanExpression_in_simpleBooleanListExpression2220);
                            e=simpleBooleanExpression();

                            state._fsp--;
                            if (state.failed) return expr;
                            if ( state.backtracking==0 ) {
                              list.add(e);
                            }
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:534:53: ( COMMA e= simpleBooleanExpression )*
                            loop46:
                            do {
                                int alt46=2;
                                int LA46_0 = input.LA(1);

                                if ( (LA46_0==COMMA) ) {
                                    alt46=1;
                                }


                                switch (alt46) {
                            	case 1 :
                            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:534:54: COMMA e= simpleBooleanExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleBooleanListExpression2225); if (state.failed) return expr;
                            	    pushFollow(FOLLOW_simpleBooleanExpression_in_simpleBooleanListExpression2231);
                            	    e=simpleBooleanExpression();

                            	    state._fsp--;
                            	    if (state.failed) return expr;
                            	    if ( state.backtracking==0 ) {
                            	      list.add(e);
                            	    }

                            	    }
                            	    break;

                            	default :
                            	    break loop46;
                                }
                            } while (true);


                            }
                            break;

                    }

                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleBooleanListExpression2240); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createBooleanListExpression(list);
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:537:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "BOOLEANLIST"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleBooleanListExpression", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"BOOLEANLIST\")");
                    }
                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleBooleanListExpression2255); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createReferenceBooleanListExpression(var);
                    }

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
        }
        return expr;
    }
    // $ANTLR end "simpleBooleanListExpression"


    // $ANTLR start "intListExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:542:1: intListExpression returns [NumberListExpression expr = null] : e= simpleIntListExpression ;
    public final NumberListExpression intListExpression() throws RecognitionException {
        NumberListExpression expr =  null;

        NumberListExpression e = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:543:2: (e= simpleIntListExpression )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:544:2: e= simpleIntListExpression
            {
            pushFollow(FOLLOW_simpleIntListExpression_in_intListExpression2280);
            e=simpleIntListExpression();

            state._fsp--;
            if (state.failed) return expr;
            if ( state.backtracking==0 ) {
              expr = e;
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
        }
        return expr;
    }
    // $ANTLR end "intListExpression"


    // $ANTLR start "simpleIntListExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:547:1: simpleIntListExpression returns [NumberListExpression expr = null] : ( LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY | {...}?var= Identifier );
    public final NumberListExpression simpleIntListExpression() throws RecognitionException {
        NumberListExpression expr =  null;

        Token var=null;
        NumberExpression e = null;



        	List<NumberExpression> list = new ArrayList<NumberExpression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:550:3: ( LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY | {...}?var= Identifier )
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==LCURLY) ) {
                alt51=1;
            }
            else if ( (LA51_0==Identifier) ) {
                alt51=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 51, 0, input);

                throw nvae;
            }
            switch (alt51) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:551:2: LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleIntListExpression2301); if (state.failed) return expr;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:551:9: (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )?
                    int alt50=2;
                    int LA50_0 = input.LA(1);

                    if ( (LA50_0==DecimalLiteral||LA50_0==FloatingPointLiteral||(LA50_0>=Identifier && LA50_0<=LPAREN)||LA50_0==MINUS) ) {
                        alt50=1;
                    }
                    switch (alt50) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:551:10: e= simpleNumberExpression ( COMMA e= simpleNumberExpression )*
                            {
                            pushFollow(FOLLOW_simpleNumberExpression_in_simpleIntListExpression2308);
                            e=simpleNumberExpression();

                            state._fsp--;
                            if (state.failed) return expr;
                            if ( state.backtracking==0 ) {
                              list.add(e);
                            }
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:551:52: ( COMMA e= simpleNumberExpression )*
                            loop49:
                            do {
                                int alt49=2;
                                int LA49_0 = input.LA(1);

                                if ( (LA49_0==COMMA) ) {
                                    alt49=1;
                                }


                                switch (alt49) {
                            	case 1 :
                            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:551:53: COMMA e= simpleNumberExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleIntListExpression2313); if (state.failed) return expr;
                            	    pushFollow(FOLLOW_simpleNumberExpression_in_simpleIntListExpression2319);
                            	    e=simpleNumberExpression();

                            	    state._fsp--;
                            	    if (state.failed) return expr;
                            	    if ( state.backtracking==0 ) {
                            	      list.add(e);
                            	    }

                            	    }
                            	    break;

                            	default :
                            	    break loop49;
                                }
                            } while (true);


                            }
                            break;

                    }

                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleIntListExpression2328); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createNumberListExpression(list);
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:554:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INTLIST"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleIntListExpression", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"INTLIST\")");
                    }
                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleIntListExpression2343); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createReferenceIntListExpression(var);
                    }

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
        }
        return expr;
    }
    // $ANTLR end "simpleIntListExpression"


    // $ANTLR start "numberListExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:559:1: numberListExpression returns [NumberListExpression expr = null] : ( (e1= doubleListExpression )=>e1= doubleListExpression | e2= intListExpression );
    public final NumberListExpression numberListExpression() throws RecognitionException {
        NumberListExpression expr =  null;

        NumberListExpression e1 = null;

        NumberListExpression e2 = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:560:2: ( (e1= doubleListExpression )=>e1= doubleListExpression | e2= intListExpression )
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==LCURLY) ) {
                int LA52_1 = input.LA(2);

                if ( (synpred8_TextMarkerParser()) ) {
                    alt52=1;
                }
                else if ( (true) ) {
                    alt52=2;
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

                if ( ((synpred8_TextMarkerParser()&&(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLELIST")))) ) {
                    alt52=1;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INTLIST"))) ) {
                    alt52=2;
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:561:2: (e1= doubleListExpression )=>e1= doubleListExpression
                    {
                    pushFollow(FOLLOW_doubleListExpression_in_numberListExpression2377);
                    e1=doubleListExpression();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = e1;
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:563:2: e2= intListExpression
                    {
                    pushFollow(FOLLOW_intListExpression_in_numberListExpression2389);
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
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}
        finally {
        }
        return expr;
    }
    // $ANTLR end "numberListExpression"


    // $ANTLR start "doubleListExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:566:1: doubleListExpression returns [NumberListExpression expr = null] : e= simpleDoubleListExpression ;
    public final NumberListExpression doubleListExpression() throws RecognitionException {
        NumberListExpression expr =  null;

        NumberListExpression e = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:567:2: (e= simpleDoubleListExpression )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:568:2: e= simpleDoubleListExpression
            {
            pushFollow(FOLLOW_simpleDoubleListExpression_in_doubleListExpression2412);
            e=simpleDoubleListExpression();

            state._fsp--;
            if (state.failed) return expr;
            if ( state.backtracking==0 ) {
              expr = e;
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
        }
        return expr;
    }
    // $ANTLR end "doubleListExpression"


    // $ANTLR start "simpleDoubleListExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:571:1: simpleDoubleListExpression returns [NumberListExpression expr = null] : ( LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY | {...}?var= Identifier );
    public final NumberListExpression simpleDoubleListExpression() throws RecognitionException {
        NumberListExpression expr =  null;

        Token var=null;
        NumberExpression e = null;



        	List<NumberExpression> list = new ArrayList<NumberExpression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:574:3: ( LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY | {...}?var= Identifier )
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:575:2: LCURLY (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleDoubleListExpression2433); if (state.failed) return expr;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:575:9: (e= simpleNumberExpression ( COMMA e= simpleNumberExpression )* )?
                    int alt54=2;
                    int LA54_0 = input.LA(1);

                    if ( (LA54_0==DecimalLiteral||LA54_0==FloatingPointLiteral||(LA54_0>=Identifier && LA54_0<=LPAREN)||LA54_0==MINUS) ) {
                        alt54=1;
                    }
                    switch (alt54) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:575:10: e= simpleNumberExpression ( COMMA e= simpleNumberExpression )*
                            {
                            pushFollow(FOLLOW_simpleNumberExpression_in_simpleDoubleListExpression2440);
                            e=simpleNumberExpression();

                            state._fsp--;
                            if (state.failed) return expr;
                            if ( state.backtracking==0 ) {
                              list.add(e);
                            }
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:575:52: ( COMMA e= simpleNumberExpression )*
                            loop53:
                            do {
                                int alt53=2;
                                int LA53_0 = input.LA(1);

                                if ( (LA53_0==COMMA) ) {
                                    alt53=1;
                                }


                                switch (alt53) {
                            	case 1 :
                            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:575:53: COMMA e= simpleNumberExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleDoubleListExpression2445); if (state.failed) return expr;
                            	    pushFollow(FOLLOW_simpleNumberExpression_in_simpleDoubleListExpression2451);
                            	    e=simpleNumberExpression();

                            	    state._fsp--;
                            	    if (state.failed) return expr;
                            	    if ( state.backtracking==0 ) {
                            	      list.add(e);
                            	    }

                            	    }
                            	    break;

                            	default :
                            	    break loop53;
                                }
                            } while (true);


                            }
                            break;

                    }

                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleDoubleListExpression2460); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createNumberListExpression(list);
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:578:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLELIST"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleDoubleListExpression", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"DOUBLELIST\")");
                    }
                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleDoubleListExpression2475); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createReferenceDoubleListExpression(var);
                    }

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
        }
        return expr;
    }
    // $ANTLR end "simpleDoubleListExpression"


    // $ANTLR start "stringListExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:583:1: stringListExpression returns [StringListExpression expr = null] : e= simpleStringListExpression ;
    public final StringListExpression stringListExpression() throws RecognitionException {
        StringListExpression expr =  null;

        StringListExpression e = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:584:2: (e= simpleStringListExpression )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:585:2: e= simpleStringListExpression
            {
            pushFollow(FOLLOW_simpleStringListExpression_in_stringListExpression2500);
            e=simpleStringListExpression();

            state._fsp--;
            if (state.failed) return expr;
            if ( state.backtracking==0 ) {
              expr = e;
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
        }
        return expr;
    }
    // $ANTLR end "stringListExpression"


    // $ANTLR start "simpleStringListExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:588:1: simpleStringListExpression returns [StringListExpression expr = null] : ( LCURLY (e= simpleStringExpression ( COMMA e= simpleStringExpression )* )? RCURLY | {...}?var= Identifier );
    public final StringListExpression simpleStringListExpression() throws RecognitionException {
        StringListExpression expr =  null;

        Token var=null;
        StringExpression e = null;



        	List<StringExpression> list = new ArrayList<StringExpression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:591:3: ( LCURLY (e= simpleStringExpression ( COMMA e= simpleStringExpression )* )? RCURLY | {...}?var= Identifier )
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:592:2: LCURLY (e= simpleStringExpression ( COMMA e= simpleStringExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleStringListExpression2521); if (state.failed) return expr;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:592:9: (e= simpleStringExpression ( COMMA e= simpleStringExpression )* )?
                    int alt57=2;
                    int LA57_0 = input.LA(1);

                    if ( (LA57_0==StringLiteral||LA57_0==Identifier) ) {
                        alt57=1;
                    }
                    switch (alt57) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:592:10: e= simpleStringExpression ( COMMA e= simpleStringExpression )*
                            {
                            pushFollow(FOLLOW_simpleStringExpression_in_simpleStringListExpression2528);
                            e=simpleStringExpression();

                            state._fsp--;
                            if (state.failed) return expr;
                            if ( state.backtracking==0 ) {
                              list.add(e);
                            }
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:592:52: ( COMMA e= simpleStringExpression )*
                            loop56:
                            do {
                                int alt56=2;
                                int LA56_0 = input.LA(1);

                                if ( (LA56_0==COMMA) ) {
                                    alt56=1;
                                }


                                switch (alt56) {
                            	case 1 :
                            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:592:53: COMMA e= simpleStringExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleStringListExpression2533); if (state.failed) return expr;
                            	    pushFollow(FOLLOW_simpleStringExpression_in_simpleStringListExpression2539);
                            	    e=simpleStringExpression();

                            	    state._fsp--;
                            	    if (state.failed) return expr;
                            	    if ( state.backtracking==0 ) {
                            	      list.add(e);
                            	    }

                            	    }
                            	    break;

                            	default :
                            	    break loop56;
                                }
                            } while (true);


                            }
                            break;

                    }

                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleStringListExpression2548); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createStringListExpression(list);
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:595:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRINGLIST"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleStringListExpression", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"STRINGLIST\")");
                    }
                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleStringListExpression2564); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createReferenceStringListExpression(var);
                    }

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
        }
        return expr;
    }
    // $ANTLR end "simpleStringListExpression"


    // $ANTLR start "typeListExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:600:1: typeListExpression returns [TypeListExpression expr = null] : e= simpleTypeListExpression ;
    public final TypeListExpression typeListExpression() throws RecognitionException {
        TypeListExpression expr =  null;

        TypeListExpression e = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:601:2: (e= simpleTypeListExpression )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:602:2: e= simpleTypeListExpression
            {
            pushFollow(FOLLOW_simpleTypeListExpression_in_typeListExpression2589);
            e=simpleTypeListExpression();

            state._fsp--;
            if (state.failed) return expr;
            if ( state.backtracking==0 ) {
              expr = e;
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
        }
        return expr;
    }
    // $ANTLR end "typeListExpression"


    // $ANTLR start "simpleTypeListExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:605:1: simpleTypeListExpression returns [TypeListExpression expr = null] : ( LCURLY (e= simpleTypeExpression ( COMMA e= simpleTypeExpression )* )? RCURLY | {...}?var= Identifier );
    public final TypeListExpression simpleTypeListExpression() throws RecognitionException {
        TypeListExpression expr =  null;

        Token var=null;
        TypeExpression e = null;



        	List<TypeExpression> list = new ArrayList<TypeExpression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:608:3: ( LCURLY (e= simpleTypeExpression ( COMMA e= simpleTypeExpression )* )? RCURLY | {...}?var= Identifier )
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:609:2: LCURLY (e= simpleTypeExpression ( COMMA e= simpleTypeExpression )* )? RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_simpleTypeListExpression2610); if (state.failed) return expr;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:609:9: (e= simpleTypeExpression ( COMMA e= simpleTypeExpression )* )?
                    int alt60=2;
                    int LA60_0 = input.LA(1);

                    if ( (LA60_0==BasicAnnotationType||LA60_0==Identifier) ) {
                        alt60=1;
                    }
                    switch (alt60) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:609:10: e= simpleTypeExpression ( COMMA e= simpleTypeExpression )*
                            {
                            pushFollow(FOLLOW_simpleTypeExpression_in_simpleTypeListExpression2617);
                            e=simpleTypeExpression();

                            state._fsp--;
                            if (state.failed) return expr;
                            if ( state.backtracking==0 ) {
                              list.add(e);
                            }
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:609:50: ( COMMA e= simpleTypeExpression )*
                            loop59:
                            do {
                                int alt59=2;
                                int LA59_0 = input.LA(1);

                                if ( (LA59_0==COMMA) ) {
                                    alt59=1;
                                }


                                switch (alt59) {
                            	case 1 :
                            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:609:51: COMMA e= simpleTypeExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_simpleTypeListExpression2622); if (state.failed) return expr;
                            	    pushFollow(FOLLOW_simpleTypeExpression_in_simpleTypeListExpression2628);
                            	    e=simpleTypeExpression();

                            	    state._fsp--;
                            	    if (state.failed) return expr;
                            	    if ( state.backtracking==0 ) {
                            	      list.add(e);
                            	    }

                            	    }
                            	    break;

                            	default :
                            	    break loop59;
                                }
                            } while (true);


                            }
                            break;

                    }

                    match(input,RCURLY,FOLLOW_RCURLY_in_simpleTypeListExpression2637); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createTypeListExpression(list);
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:612:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleTypeListExpression", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"TYPELIST\")");
                    }
                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleTypeListExpression2652); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createReferenceTypeListExpression(var);
                    }

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
        }
        return expr;
    }
    // $ANTLR end "simpleTypeListExpression"


    // $ANTLR start "typeExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:617:1: typeExpression returns [TypeExpression type = null] options {backtrack=true; } : (tf= typeFunction | st= simpleTypeExpression );
    public final TypeExpression typeExpression() throws RecognitionException {
        TypeExpression type =  null;

        TypeExpression tf = null;

        TypeExpression st = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:621:2: (tf= typeFunction | st= simpleTypeExpression )
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==Identifier) ) {
                int LA62_1 = input.LA(2);

                if ( (synpred9_TextMarkerParser()) ) {
                    alt62=1;
                }
                else if ( (true) ) {
                    alt62=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return type;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 62, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA62_0==BasicAnnotationType) ) {
                alt62=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return type;}
                NoViableAltException nvae =
                    new NoViableAltException("", 62, 0, input);

                throw nvae;
            }
            switch (alt62) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:622:2: tf= typeFunction
                    {
                    pushFollow(FOLLOW_typeFunction_in_typeExpression2689);
                    tf=typeFunction();

                    state._fsp--;
                    if (state.failed) return type;
                    if ( state.backtracking==0 ) {
                      type = tf;
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:623:4: st= simpleTypeExpression
                    {
                    pushFollow(FOLLOW_simpleTypeExpression_in_typeExpression2700);
                    st=simpleTypeExpression();

                    state._fsp--;
                    if (state.failed) return type;
                    if ( state.backtracking==0 ) {
                      type = st;
                    }

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
        }
        return type;
    }
    // $ANTLR end "typeExpression"


    // $ANTLR start "typeFunction"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:628:1: typeFunction returns [TypeExpression expr = null] : (e= externalTypeFunction )=>e= externalTypeFunction ;
    public final TypeExpression typeFunction() throws RecognitionException {
        TypeExpression expr =  null;

        TypeExpression e = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:629:2: ( (e= externalTypeFunction )=>e= externalTypeFunction )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:630:2: (e= externalTypeFunction )=>e= externalTypeFunction
            {
            pushFollow(FOLLOW_externalTypeFunction_in_typeFunction2734);
            e=externalTypeFunction();

            state._fsp--;
            if (state.failed) return expr;
            if ( state.backtracking==0 ) {
              expr = e;
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
        }
        return expr;
    }
    // $ANTLR end "typeFunction"


    // $ANTLR start "externalTypeFunction"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:634:1: externalTypeFunction returns [TypeExpression expr = null] : id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final TypeExpression externalTypeFunction() throws RecognitionException {
        TypeExpression expr =  null;

        Token id=null;
        List args = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:635:2: (id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:637:2: id= Identifier LPAREN args= varArgumentList RPAREN
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalTypeFunction2759); if (state.failed) return expr;
            match(input,LPAREN,FOLLOW_LPAREN_in_externalTypeFunction2761); if (state.failed) return expr;
            pushFollow(FOLLOW_varArgumentList_in_externalTypeFunction2768);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return expr;
            match(input,RPAREN,FOLLOW_RPAREN_in_externalTypeFunction2770); if (state.failed) return expr;
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
        }
        return expr;
    }
    // $ANTLR end "externalTypeFunction"


    // $ANTLR start "simpleTypeExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:644:1: simpleTypeExpression returns [TypeExpression type = null] : ({...}?var= Identifier | at= annotationType );
    public final TypeExpression simpleTypeExpression() throws RecognitionException {
        TypeExpression type =  null;

        Token var=null;
        Token at = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:645:2: ({...}?var= Identifier | at= annotationType )
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==Identifier) ) {
                int LA63_1 = input.LA(2);

                if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPE"))) ) {
                    alt63=1;
                }
                else if ( (true) ) {
                    alt63=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return type;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 63, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA63_0==BasicAnnotationType) ) {
                alt63=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return type;}
                NoViableAltException nvae =
                    new NoViableAltException("", 63, 0, input);

                throw nvae;
            }
            switch (alt63) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:646:2: {...}?var= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPE"))) ) {
                        if (state.backtracking>0) {state.failed=true; return type;}
                        throw new FailedPredicateException(input, "simpleTypeExpression", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"TYPE\")");
                    }
                    var=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleTypeExpression2795); if (state.failed) return type;
                    if ( state.backtracking==0 ) {
                      type = ExpressionFactory.createReferenceTypeExpression(var);
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:649:2: at= annotationType
                    {
                    pushFollow(FOLLOW_annotationType_in_simpleTypeExpression2809);
                    at=annotationType();

                    state._fsp--;
                    if (state.failed) return type;
                    if ( state.backtracking==0 ) {
                      type = ExpressionFactory.createSimpleTypeExpression(at, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
                    }

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
        }
        return type;
    }
    // $ANTLR end "simpleTypeExpression"


    // $ANTLR start "variable"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:654:1: variable returns [Token var = null] : {...}?v= Identifier ;
    public final Token variable() throws RecognitionException {
        Token var =  null;

        Token v=null;

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:655:2: ({...}?v= Identifier )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:656:2: {...}?v= Identifier
            {
            if ( !((isVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText()))) ) {
                if (state.backtracking>0) {state.failed=true; return var;}
                throw new FailedPredicateException(input, "variable", "isVariable($blockDeclaration::env, input.LT(1).getText())");
            }
            v=(Token)match(input,Identifier,FOLLOW_Identifier_in_variable2835); if (state.failed) return var;
            if ( state.backtracking==0 ) {
              var = v;
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
        }
        return var;
    }
    // $ANTLR end "variable"


    // $ANTLR start "listVariable"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:659:1: listVariable returns [Token var = null] : {...}?v= Identifier ;
    public final Token listVariable() throws RecognitionException {
        Token var =  null;

        Token v=null;

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:660:2: ({...}?v= Identifier )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:661:2: {...}?v= Identifier
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
            v=(Token)match(input,Identifier,FOLLOW_Identifier_in_listVariable2859); if (state.failed) return var;
            if ( state.backtracking==0 ) {
              var = v;
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
        }
        return var;
    }
    // $ANTLR end "listVariable"


    // $ANTLR start "quantifierPart"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:684:1: quantifierPart returns [RuleElementQuantifier quantifier = null] : ( STAR (q= QUESTION )? | PLUS (q= QUESTION )? | QUESTION (q= QUESTION )? | LBRACK min= numberExpression (comma= COMMA (max= numberExpression )? )? RBRACK (q= QUESTION )? );
    public final RuleElementQuantifier quantifierPart() throws RecognitionException {
        RuleElementQuantifier quantifier =  null;

        Token q=null;
        Token comma=null;
        NumberExpression min = null;

        NumberExpression max = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:685:2: ( STAR (q= QUESTION )? | PLUS (q= QUESTION )? | QUESTION (q= QUESTION )? | LBRACK min= numberExpression (comma= COMMA (max= numberExpression )? )? RBRACK (q= QUESTION )? )
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
                if (state.backtracking>0) {state.failed=true; return quantifier;}
                NoViableAltException nvae =
                    new NoViableAltException("", 70, 0, input);

                throw nvae;
            }

            switch (alt70) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:686:3: STAR (q= QUESTION )?
                    {
                    match(input,STAR,FOLLOW_STAR_in_quantifierPart2893); if (state.failed) return quantifier;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:686:10: (q= QUESTION )?
                    int alt64=2;
                    int LA64_0 = input.LA(1);

                    if ( (LA64_0==QUESTION) ) {
                        alt64=1;
                    }
                    switch (alt64) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:686:10: q= QUESTION
                            {
                            q=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_quantifierPart2899); if (state.failed) return quantifier;

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {
                      if(q != null) {quantifier = TextMarkerScriptFactory.createStarReluctantQuantifier();} 
                      	 	else{quantifier = TextMarkerScriptFactory.createStarGreedyQuantifier();}
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:689:4: PLUS (q= QUESTION )?
                    {
                    match(input,PLUS,FOLLOW_PLUS_in_quantifierPart2910); if (state.failed) return quantifier;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:689:11: (q= QUESTION )?
                    int alt65=2;
                    int LA65_0 = input.LA(1);

                    if ( (LA65_0==QUESTION) ) {
                        alt65=1;
                    }
                    switch (alt65) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:689:11: q= QUESTION
                            {
                            q=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_quantifierPart2916); if (state.failed) return quantifier;

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {
                      if(q != null) {quantifier = TextMarkerScriptFactory.createPlusReluctantQuantifier();}
                      	 else {quantifier = TextMarkerScriptFactory.createPlusGreedyQuantifier();}
                    }

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:692:4: QUESTION (q= QUESTION )?
                    {
                    match(input,QUESTION,FOLLOW_QUESTION_in_quantifierPart2926); if (state.failed) return quantifier;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:692:15: (q= QUESTION )?
                    int alt66=2;
                    int LA66_0 = input.LA(1);

                    if ( (LA66_0==QUESTION) ) {
                        alt66=1;
                    }
                    switch (alt66) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:692:15: q= QUESTION
                            {
                            q=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_quantifierPart2932); if (state.failed) return quantifier;

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {
                      if(q != null) {quantifier = TextMarkerScriptFactory.createQuestionReluctantQuantifier();} 
                      	 else {quantifier = TextMarkerScriptFactory.createQuestionGreedyQuantifier();}
                    }

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:695:4: LBRACK min= numberExpression (comma= COMMA (max= numberExpression )? )? RBRACK (q= QUESTION )?
                    {
                    match(input,LBRACK,FOLLOW_LBRACK_in_quantifierPart2943); if (state.failed) return quantifier;
                    pushFollow(FOLLOW_numberExpression_in_quantifierPart2949);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return quantifier;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:695:34: (comma= COMMA (max= numberExpression )? )?
                    int alt68=2;
                    int LA68_0 = input.LA(1);

                    if ( (LA68_0==COMMA) ) {
                        alt68=1;
                    }
                    switch (alt68) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:695:35: comma= COMMA (max= numberExpression )?
                            {
                            comma=(Token)match(input,COMMA,FOLLOW_COMMA_in_quantifierPart2956); if (state.failed) return quantifier;
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:695:49: (max= numberExpression )?
                            int alt67=2;
                            int LA67_0 = input.LA(1);

                            if ( ((LA67_0>=EXP && LA67_0<=TAN)||LA67_0==DecimalLiteral||LA67_0==FloatingPointLiteral||(LA67_0>=Identifier && LA67_0<=LPAREN)||LA67_0==MINUS) ) {
                                alt67=1;
                            }
                            switch (alt67) {
                                case 1 :
                                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:695:50: max= numberExpression
                                    {
                                    pushFollow(FOLLOW_numberExpression_in_quantifierPart2963);
                                    max=numberExpression();

                                    state._fsp--;
                                    if (state.failed) return quantifier;

                                    }
                                    break;

                            }


                            }
                            break;

                    }

                    match(input,RBRACK,FOLLOW_RBRACK_in_quantifierPart2969); if (state.failed) return quantifier;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:695:86: (q= QUESTION )?
                    int alt69=2;
                    int LA69_0 = input.LA(1);

                    if ( (LA69_0==QUESTION) ) {
                        alt69=1;
                    }
                    switch (alt69) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:695:86: q= QUESTION
                            {
                            q=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_quantifierPart2975); if (state.failed) return quantifier;

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {
                      if(q != null) {quantifier = TextMarkerScriptFactory.createMinMaxReluctantQuantifier(min,max,comma);} 
                      	 else {quantifier = TextMarkerScriptFactory.createMinMaxGreedyQuantifier(min,max,comma);}
                    }

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
        }
        return quantifier;
    }
    // $ANTLR end "quantifierPart"


    // $ANTLR start "condition"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:701:1: condition returns [AbstractTextMarkerCondition result = null] : (c= conditionAnd | c= conditionContains | c= conditionContextCount | c= conditionCount | c= conditionCurrentCount | c= conditionInList | c= conditionIsInTag | c= conditionLast | c= conditionMofN | c= conditionNear | c= conditionNot | c= conditionOr | c= conditionPartOf | c= conditionPosition | c= conditionRegExp | c= conditionScore | c= conditionTotalCount | c= conditionVote | c= conditionIf | c= conditionFeature | c= conditionParse | c= conditionIs | c= conditionBefore | c= conditionAfter | c= conditionStartsWith | c= conditionEndsWith | c= conditionPartOfNeq | c= conditionSize | (c= externalCondition )=>c= externalCondition | c= variableCondition ) ;
    public final AbstractTextMarkerCondition condition() throws RecognitionException {
        AbstractTextMarkerCondition result =  null;

        AbstractTextMarkerCondition c = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:702:2: ( (c= conditionAnd | c= conditionContains | c= conditionContextCount | c= conditionCount | c= conditionCurrentCount | c= conditionInList | c= conditionIsInTag | c= conditionLast | c= conditionMofN | c= conditionNear | c= conditionNot | c= conditionOr | c= conditionPartOf | c= conditionPosition | c= conditionRegExp | c= conditionScore | c= conditionTotalCount | c= conditionVote | c= conditionIf | c= conditionFeature | c= conditionParse | c= conditionIs | c= conditionBefore | c= conditionAfter | c= conditionStartsWith | c= conditionEndsWith | c= conditionPartOfNeq | c= conditionSize | (c= externalCondition )=>c= externalCondition | c= variableCondition ) )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:703:2: (c= conditionAnd | c= conditionContains | c= conditionContextCount | c= conditionCount | c= conditionCurrentCount | c= conditionInList | c= conditionIsInTag | c= conditionLast | c= conditionMofN | c= conditionNear | c= conditionNot | c= conditionOr | c= conditionPartOf | c= conditionPosition | c= conditionRegExp | c= conditionScore | c= conditionTotalCount | c= conditionVote | c= conditionIf | c= conditionFeature | c= conditionParse | c= conditionIs | c= conditionBefore | c= conditionAfter | c= conditionStartsWith | c= conditionEndsWith | c= conditionPartOfNeq | c= conditionSize | (c= externalCondition )=>c= externalCondition | c= variableCondition )
            {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:703:2: (c= conditionAnd | c= conditionContains | c= conditionContextCount | c= conditionCount | c= conditionCurrentCount | c= conditionInList | c= conditionIsInTag | c= conditionLast | c= conditionMofN | c= conditionNear | c= conditionNot | c= conditionOr | c= conditionPartOf | c= conditionPosition | c= conditionRegExp | c= conditionScore | c= conditionTotalCount | c= conditionVote | c= conditionIf | c= conditionFeature | c= conditionParse | c= conditionIs | c= conditionBefore | c= conditionAfter | c= conditionStartsWith | c= conditionEndsWith | c= conditionPartOfNeq | c= conditionSize | (c= externalCondition )=>c= externalCondition | c= variableCondition )
            int alt71=30;
            alt71 = dfa71.predict(input);
            switch (alt71) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:704:2: c= conditionAnd
                    {
                    pushFollow(FOLLOW_conditionAnd_in_condition3005);
                    c=conditionAnd();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:705:4: c= conditionContains
                    {
                    pushFollow(FOLLOW_conditionContains_in_condition3014);
                    c=conditionContains();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:706:4: c= conditionContextCount
                    {
                    pushFollow(FOLLOW_conditionContextCount_in_condition3023);
                    c=conditionContextCount();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:707:4: c= conditionCount
                    {
                    pushFollow(FOLLOW_conditionCount_in_condition3032);
                    c=conditionCount();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:708:4: c= conditionCurrentCount
                    {
                    pushFollow(FOLLOW_conditionCurrentCount_in_condition3041);
                    c=conditionCurrentCount();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 6 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:709:4: c= conditionInList
                    {
                    pushFollow(FOLLOW_conditionInList_in_condition3050);
                    c=conditionInList();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 7 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:710:4: c= conditionIsInTag
                    {
                    pushFollow(FOLLOW_conditionIsInTag_in_condition3059);
                    c=conditionIsInTag();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 8 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:711:4: c= conditionLast
                    {
                    pushFollow(FOLLOW_conditionLast_in_condition3068);
                    c=conditionLast();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 9 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:712:4: c= conditionMofN
                    {
                    pushFollow(FOLLOW_conditionMofN_in_condition3077);
                    c=conditionMofN();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 10 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:713:4: c= conditionNear
                    {
                    pushFollow(FOLLOW_conditionNear_in_condition3086);
                    c=conditionNear();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 11 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:714:4: c= conditionNot
                    {
                    pushFollow(FOLLOW_conditionNot_in_condition3095);
                    c=conditionNot();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 12 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:715:4: c= conditionOr
                    {
                    pushFollow(FOLLOW_conditionOr_in_condition3104);
                    c=conditionOr();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 13 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:716:4: c= conditionPartOf
                    {
                    pushFollow(FOLLOW_conditionPartOf_in_condition3113);
                    c=conditionPartOf();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 14 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:717:4: c= conditionPosition
                    {
                    pushFollow(FOLLOW_conditionPosition_in_condition3122);
                    c=conditionPosition();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 15 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:718:4: c= conditionRegExp
                    {
                    pushFollow(FOLLOW_conditionRegExp_in_condition3131);
                    c=conditionRegExp();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 16 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:719:4: c= conditionScore
                    {
                    pushFollow(FOLLOW_conditionScore_in_condition3140);
                    c=conditionScore();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 17 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:720:4: c= conditionTotalCount
                    {
                    pushFollow(FOLLOW_conditionTotalCount_in_condition3149);
                    c=conditionTotalCount();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 18 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:721:4: c= conditionVote
                    {
                    pushFollow(FOLLOW_conditionVote_in_condition3158);
                    c=conditionVote();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 19 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:722:4: c= conditionIf
                    {
                    pushFollow(FOLLOW_conditionIf_in_condition3167);
                    c=conditionIf();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 20 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:723:4: c= conditionFeature
                    {
                    pushFollow(FOLLOW_conditionFeature_in_condition3176);
                    c=conditionFeature();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 21 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:724:4: c= conditionParse
                    {
                    pushFollow(FOLLOW_conditionParse_in_condition3185);
                    c=conditionParse();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 22 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:725:4: c= conditionIs
                    {
                    pushFollow(FOLLOW_conditionIs_in_condition3194);
                    c=conditionIs();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 23 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:726:4: c= conditionBefore
                    {
                    pushFollow(FOLLOW_conditionBefore_in_condition3203);
                    c=conditionBefore();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 24 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:727:4: c= conditionAfter
                    {
                    pushFollow(FOLLOW_conditionAfter_in_condition3212);
                    c=conditionAfter();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 25 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:728:4: c= conditionStartsWith
                    {
                    pushFollow(FOLLOW_conditionStartsWith_in_condition3222);
                    c=conditionStartsWith();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 26 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:729:4: c= conditionEndsWith
                    {
                    pushFollow(FOLLOW_conditionEndsWith_in_condition3231);
                    c=conditionEndsWith();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 27 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:730:4: c= conditionPartOfNeq
                    {
                    pushFollow(FOLLOW_conditionPartOfNeq_in_condition3240);
                    c=conditionPartOfNeq();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 28 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:731:4: c= conditionSize
                    {
                    pushFollow(FOLLOW_conditionSize_in_condition3249);
                    c=conditionSize();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 29 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:732:4: (c= externalCondition )=>c= externalCondition
                    {
                    pushFollow(FOLLOW_externalCondition_in_condition3268);
                    c=externalCondition();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 30 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:733:4: c= variableCondition
                    {
                    pushFollow(FOLLOW_variableCondition_in_condition3277);
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
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}
        finally {
        }
        return result;
    }
    // $ANTLR end "condition"


    // $ANTLR start "variableCondition"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:738:1: variableCondition returns [AbstractTextMarkerCondition condition = null] : id= Identifier ;
    public final AbstractTextMarkerCondition variableCondition() throws RecognitionException {
        AbstractTextMarkerCondition condition =  null;

        Token id=null;

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:739:2: (id= Identifier )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:741:2: id= Identifier
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableCondition3307); if (state.failed) return condition;
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
        }
        return condition;
    }
    // $ANTLR end "variableCondition"


    // $ANTLR start "externalCondition"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:747:1: externalCondition returns [AbstractTextMarkerCondition condition = null] : id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final AbstractTextMarkerCondition externalCondition() throws RecognitionException {
        AbstractTextMarkerCondition condition =  null;

        Token id=null;
        List args = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:748:2: (id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:750:2: id= Identifier LPAREN args= varArgumentList RPAREN
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalCondition3334); if (state.failed) return condition;
            match(input,LPAREN,FOLLOW_LPAREN_in_externalCondition3336); if (state.failed) return condition;
            pushFollow(FOLLOW_varArgumentList_in_externalCondition3342);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return condition;
            match(input,RPAREN,FOLLOW_RPAREN_in_externalCondition3344); if (state.failed) return condition;
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
        }
        return condition;
    }
    // $ANTLR end "externalCondition"


    // $ANTLR start "conditionAnd"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:756:1: conditionAnd returns [AbstractTextMarkerCondition cond = null] : AND LPAREN conds= conditions RPAREN ;
    public final AbstractTextMarkerCondition conditionAnd() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;

        List<AbstractTextMarkerCondition> conds = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:757:5: ( AND LPAREN conds= conditions RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:758:5: AND LPAREN conds= conditions RPAREN
            {
            match(input,AND,FOLLOW_AND_in_conditionAnd3373); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionAnd3375); if (state.failed) return cond;
            pushFollow(FOLLOW_conditions_in_conditionAnd3381);
            conds=conditions();

            state._fsp--;
            if (state.failed) return cond;
            match(input,RPAREN,FOLLOW_RPAREN_in_conditionAnd3383); if (state.failed) return cond;
            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createConditionAnd(conds, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return cond;
    }
    // $ANTLR end "conditionAnd"


    // $ANTLR start "conditionContains"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:761:1: conditionContains returns [AbstractTextMarkerCondition cond = null] options {backtrack=true; } : CONTAINS LPAREN (type= typeExpression | list= listExpression COMMA a= argument ) ( COMMA min= numberExpression COMMA max= numberExpression ( COMMA percent= booleanExpression )? )? RPAREN ;
    public final AbstractTextMarkerCondition conditionContains() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;

        TypeExpression type = null;

        ListExpression list = null;

        TextMarkerExpression a = null;

        NumberExpression min = null;

        NumberExpression max = null;

        BooleanExpression percent = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:765:5: ( CONTAINS LPAREN (type= typeExpression | list= listExpression COMMA a= argument ) ( COMMA min= numberExpression COMMA max= numberExpression ( COMMA percent= booleanExpression )? )? RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:766:5: CONTAINS LPAREN (type= typeExpression | list= listExpression COMMA a= argument ) ( COMMA min= numberExpression COMMA max= numberExpression ( COMMA percent= booleanExpression )? )? RPAREN
            {
            match(input,CONTAINS,FOLLOW_CONTAINS_in_conditionContains3430); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionContains3432); if (state.failed) return cond;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:766:21: (type= typeExpression | list= listExpression COMMA a= argument )
            int alt72=2;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA72_1 = input.LA(2);

                if ( (!((((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLELIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRINGLIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "BOOLEANLIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INTLIST")))))) ) {
                    alt72=1;
                }
                else if ( (((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLELIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRINGLIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "BOOLEANLIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INTLIST")))) ) {
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:766:22: type= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionContains3439);
                    type=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:766:46: list= listExpression COMMA a= argument
                    {
                    pushFollow(FOLLOW_listExpression_in_conditionContains3447);
                    list=listExpression();

                    state._fsp--;
                    if (state.failed) return cond;
                    match(input,COMMA,FOLLOW_COMMA_in_conditionContains3449); if (state.failed) return cond;
                    pushFollow(FOLLOW_argument_in_conditionContains3455);
                    a=argument();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }

            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:767:5: ( COMMA min= numberExpression COMMA max= numberExpression ( COMMA percent= booleanExpression )? )?
            int alt74=2;
            int LA74_0 = input.LA(1);

            if ( (LA74_0==COMMA) ) {
                alt74=1;
            }
            switch (alt74) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:767:6: COMMA min= numberExpression COMMA max= numberExpression ( COMMA percent= booleanExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionContains3464); if (state.failed) return cond;
                    pushFollow(FOLLOW_numberExpression_in_conditionContains3470);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;
                    match(input,COMMA,FOLLOW_COMMA_in_conditionContains3472); if (state.failed) return cond;
                    pushFollow(FOLLOW_numberExpression_in_conditionContains3478);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:767:64: ( COMMA percent= booleanExpression )?
                    int alt73=2;
                    int LA73_0 = input.LA(1);

                    if ( (LA73_0==COMMA) ) {
                        alt73=1;
                    }
                    switch (alt73) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:767:65: COMMA percent= booleanExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionContains3481); if (state.failed) return cond;
                            pushFollow(FOLLOW_booleanExpression_in_conditionContains3487);
                            percent=booleanExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    }
                    break;

            }

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionContains3493); if (state.failed) return cond;
            if ( state.backtracking==0 ) {
              if(type != null) {cond = ConditionFactory.createConditionContains(type, min, max, percent, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}
                  else {cond = ConditionFactory.createConditionContains(list,a, min, max, percent, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);};
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
        }
        return cond;
    }
    // $ANTLR end "conditionContains"


    // $ANTLR start "conditionContextCount"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:771:1: conditionContextCount returns [AbstractTextMarkerCondition cond = null] : CONTEXTCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN ;
    public final AbstractTextMarkerCondition conditionContextCount() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;

        TypeExpression type = null;

        NumberExpression min = null;

        NumberExpression max = null;

        Token var = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:772:5: ( CONTEXTCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:773:5: CONTEXTCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
            {
            match(input,CONTEXTCOUNT,FOLLOW_CONTEXTCOUNT_in_conditionContextCount3526); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionContextCount3528); if (state.failed) return cond;
            pushFollow(FOLLOW_typeExpression_in_conditionContextCount3534);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:773:47: ( COMMA min= numberExpression COMMA max= numberExpression )?
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( (LA75_0==COMMA) ) {
                int LA75_1 = input.LA(2);

                if ( ((LA75_1>=EXP && LA75_1<=TAN)||LA75_1==DecimalLiteral||LA75_1==FloatingPointLiteral||LA75_1==LPAREN||LA75_1==MINUS) ) {
                    alt75=1;
                }
                else if ( (LA75_1==Identifier) ) {
                    int LA75_4 = input.LA(3);

                    if ( (LA75_4==LPAREN||LA75_4==COMMA||(LA75_4>=PLUS && LA75_4<=SLASH)||LA75_4==PERCENT) ) {
                        alt75=1;
                    }
                }
            }
            switch (alt75) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:773:48: COMMA min= numberExpression COMMA max= numberExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionContextCount3537); if (state.failed) return cond;
                    pushFollow(FOLLOW_numberExpression_in_conditionContextCount3543);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;
                    match(input,COMMA,FOLLOW_COMMA_in_conditionContextCount3545); if (state.failed) return cond;
                    pushFollow(FOLLOW_numberExpression_in_conditionContextCount3551);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }

            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:774:5: ( COMMA var= numberVariable )?
            int alt76=2;
            int LA76_0 = input.LA(1);

            if ( (LA76_0==COMMA) ) {
                alt76=1;
            }
            switch (alt76) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:774:6: COMMA var= numberVariable
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionContextCount3561); if (state.failed) return cond;
                    pushFollow(FOLLOW_numberVariable_in_conditionContextCount3567);
                    var=numberVariable();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionContextCount3571); if (state.failed) return cond;
            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createConditionContextCount(type, min, max, var, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return cond;
    }
    // $ANTLR end "conditionContextCount"


    // $ANTLR start "conditionCount"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:777:1: conditionCount returns [AbstractTextMarkerCondition cond = null] options {backtrack=true; } : ( COUNT LPAREN type= listExpression COMMA a= argument ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN | COUNT LPAREN list= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN );
    public final AbstractTextMarkerCondition conditionCount() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;

        ListExpression type = null;

        TextMarkerExpression a = null;

        NumberExpression min = null;

        NumberExpression max = null;

        Token var = null;

        TypeExpression list = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:781:5: ( COUNT LPAREN type= listExpression COMMA a= argument ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN | COUNT LPAREN list= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
            int alt81=2;
            int LA81_0 = input.LA(1);

            if ( (LA81_0==COUNT) ) {
                int LA81_1 = input.LA(2);

                if ( (synpred12_TextMarkerParser()) ) {
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:782:5: COUNT LPAREN type= listExpression COMMA a= argument ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
                    {
                    match(input,COUNT,FOLLOW_COUNT_in_conditionCount3617); if (state.failed) return cond;
                    match(input,LPAREN,FOLLOW_LPAREN_in_conditionCount3619); if (state.failed) return cond;
                    pushFollow(FOLLOW_listExpression_in_conditionCount3625);
                    type=listExpression();

                    state._fsp--;
                    if (state.failed) return cond;
                    match(input,COMMA,FOLLOW_COMMA_in_conditionCount3627); if (state.failed) return cond;
                    pushFollow(FOLLOW_argument_in_conditionCount3633);
                    a=argument();

                    state._fsp--;
                    if (state.failed) return cond;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:782:59: ( COMMA min= numberExpression COMMA max= numberExpression )?
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
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:782:60: COMMA min= numberExpression COMMA max= numberExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount3636); if (state.failed) return cond;
                            pushFollow(FOLLOW_numberExpression_in_conditionCount3642);
                            min=numberExpression();

                            state._fsp--;
                            if (state.failed) return cond;
                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount3644); if (state.failed) return cond;
                            pushFollow(FOLLOW_numberExpression_in_conditionCount3650);
                            max=numberExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }

                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:783:5: ( COMMA var= numberVariable )?
                    int alt78=2;
                    int LA78_0 = input.LA(1);

                    if ( (LA78_0==COMMA) ) {
                        alt78=1;
                    }
                    switch (alt78) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:783:6: COMMA var= numberVariable
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount3660); if (state.failed) return cond;
                            pushFollow(FOLLOW_numberVariable_in_conditionCount3666);
                            var=numberVariable();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }

                    match(input,RPAREN,FOLLOW_RPAREN_in_conditionCount3670); if (state.failed) return cond;
                    if ( state.backtracking==0 ) {
                      cond = ConditionFactory.createConditionCount(type, a, min, max, var, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:786:5: COUNT LPAREN list= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
                    {
                    match(input,COUNT,FOLLOW_COUNT_in_conditionCount3688); if (state.failed) return cond;
                    match(input,LPAREN,FOLLOW_LPAREN_in_conditionCount3690); if (state.failed) return cond;
                    pushFollow(FOLLOW_typeExpression_in_conditionCount3696);
                    list=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:786:40: ( COMMA min= numberExpression COMMA max= numberExpression )?
                    int alt79=2;
                    int LA79_0 = input.LA(1);

                    if ( (LA79_0==COMMA) ) {
                        int LA79_1 = input.LA(2);

                        if ( (LA79_1==Identifier) ) {
                            int LA79_3 = input.LA(3);

                            if ( (LA79_3==LPAREN||LA79_3==COMMA||(LA79_3>=PLUS && LA79_3<=SLASH)||LA79_3==PERCENT) ) {
                                alt79=1;
                            }
                        }
                        else if ( ((LA79_1>=EXP && LA79_1<=TAN)||LA79_1==DecimalLiteral||LA79_1==FloatingPointLiteral||LA79_1==LPAREN||LA79_1==MINUS) ) {
                            alt79=1;
                        }
                    }
                    switch (alt79) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:786:41: COMMA min= numberExpression COMMA max= numberExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount3699); if (state.failed) return cond;
                            pushFollow(FOLLOW_numberExpression_in_conditionCount3705);
                            min=numberExpression();

                            state._fsp--;
                            if (state.failed) return cond;
                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount3707); if (state.failed) return cond;
                            pushFollow(FOLLOW_numberExpression_in_conditionCount3713);
                            max=numberExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }

                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:787:5: ( COMMA var= numberVariable )?
                    int alt80=2;
                    int LA80_0 = input.LA(1);

                    if ( (LA80_0==COMMA) ) {
                        alt80=1;
                    }
                    switch (alt80) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:787:6: COMMA var= numberVariable
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionCount3723); if (state.failed) return cond;
                            pushFollow(FOLLOW_numberVariable_in_conditionCount3729);
                            var=numberVariable();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }

                    match(input,RPAREN,FOLLOW_RPAREN_in_conditionCount3733); if (state.failed) return cond;
                    if ( state.backtracking==0 ) {
                      cond = ConditionFactory.createConditionCount(list, min, max, var, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
                    }

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
        }
        return cond;
    }
    // $ANTLR end "conditionCount"


    // $ANTLR start "conditionTotalCount"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:790:1: conditionTotalCount returns [AbstractTextMarkerCondition cond = null] : TOTALCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN ;
    public final AbstractTextMarkerCondition conditionTotalCount() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;

        TypeExpression type = null;

        NumberExpression min = null;

        NumberExpression max = null;

        Token var = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:791:5: ( TOTALCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:792:5: TOTALCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
            {
            match(input,TOTALCOUNT,FOLLOW_TOTALCOUNT_in_conditionTotalCount3769); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionTotalCount3771); if (state.failed) return cond;
            pushFollow(FOLLOW_typeExpression_in_conditionTotalCount3777);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:792:45: ( COMMA min= numberExpression COMMA max= numberExpression )?
            int alt82=2;
            int LA82_0 = input.LA(1);

            if ( (LA82_0==COMMA) ) {
                int LA82_1 = input.LA(2);

                if ( ((LA82_1>=EXP && LA82_1<=TAN)||LA82_1==DecimalLiteral||LA82_1==FloatingPointLiteral||LA82_1==LPAREN||LA82_1==MINUS) ) {
                    alt82=1;
                }
                else if ( (LA82_1==Identifier) ) {
                    int LA82_4 = input.LA(3);

                    if ( (LA82_4==LPAREN||LA82_4==COMMA||(LA82_4>=PLUS && LA82_4<=SLASH)||LA82_4==PERCENT) ) {
                        alt82=1;
                    }
                }
            }
            switch (alt82) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:792:46: COMMA min= numberExpression COMMA max= numberExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionTotalCount3780); if (state.failed) return cond;
                    pushFollow(FOLLOW_numberExpression_in_conditionTotalCount3786);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;
                    match(input,COMMA,FOLLOW_COMMA_in_conditionTotalCount3788); if (state.failed) return cond;
                    pushFollow(FOLLOW_numberExpression_in_conditionTotalCount3794);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }

            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:793:5: ( COMMA var= numberVariable )?
            int alt83=2;
            int LA83_0 = input.LA(1);

            if ( (LA83_0==COMMA) ) {
                alt83=1;
            }
            switch (alt83) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:793:6: COMMA var= numberVariable
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionTotalCount3803); if (state.failed) return cond;
                    pushFollow(FOLLOW_numberVariable_in_conditionTotalCount3809);
                    var=numberVariable();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionTotalCount3813); if (state.failed) return cond;
            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createConditionTotalCount(type, min, max, var, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return cond;
    }
    // $ANTLR end "conditionTotalCount"


    // $ANTLR start "conditionCurrentCount"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:796:1: conditionCurrentCount returns [AbstractTextMarkerCondition cond = null] : CURRENTCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN ;
    public final AbstractTextMarkerCondition conditionCurrentCount() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;

        TypeExpression type = null;

        NumberExpression min = null;

        NumberExpression max = null;

        Token var = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:797:5: ( CURRENTCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:798:5: CURRENTCOUNT LPAREN type= typeExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
            {
            match(input,CURRENTCOUNT,FOLLOW_CURRENTCOUNT_in_conditionCurrentCount3846); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionCurrentCount3848); if (state.failed) return cond;
            pushFollow(FOLLOW_typeExpression_in_conditionCurrentCount3854);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:798:47: ( COMMA min= numberExpression COMMA max= numberExpression )?
            int alt84=2;
            int LA84_0 = input.LA(1);

            if ( (LA84_0==COMMA) ) {
                int LA84_1 = input.LA(2);

                if ( ((LA84_1>=EXP && LA84_1<=TAN)||LA84_1==DecimalLiteral||LA84_1==FloatingPointLiteral||LA84_1==LPAREN||LA84_1==MINUS) ) {
                    alt84=1;
                }
                else if ( (LA84_1==Identifier) ) {
                    int LA84_4 = input.LA(3);

                    if ( (LA84_4==LPAREN||LA84_4==COMMA||(LA84_4>=PLUS && LA84_4<=SLASH)||LA84_4==PERCENT) ) {
                        alt84=1;
                    }
                }
            }
            switch (alt84) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:798:48: COMMA min= numberExpression COMMA max= numberExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionCurrentCount3857); if (state.failed) return cond;
                    pushFollow(FOLLOW_numberExpression_in_conditionCurrentCount3863);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;
                    match(input,COMMA,FOLLOW_COMMA_in_conditionCurrentCount3865); if (state.failed) return cond;
                    pushFollow(FOLLOW_numberExpression_in_conditionCurrentCount3871);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }

            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:799:5: ( COMMA var= numberVariable )?
            int alt85=2;
            int LA85_0 = input.LA(1);

            if ( (LA85_0==COMMA) ) {
                alt85=1;
            }
            switch (alt85) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:799:6: COMMA var= numberVariable
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionCurrentCount3881); if (state.failed) return cond;
                    pushFollow(FOLLOW_numberVariable_in_conditionCurrentCount3887);
                    var=numberVariable();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionCurrentCount3891); if (state.failed) return cond;
            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createConditionCurrentCount(type, min, max, var, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return cond;
    }
    // $ANTLR end "conditionCurrentCount"


    // $ANTLR start "conditionInList"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:802:1: conditionInList returns [AbstractTextMarkerCondition cond = null] options {backtrack=true; } : INLIST LPAREN ( (list2= stringListExpression )=>list2= stringListExpression | list1= wordListExpression ) ( COMMA dist= numberExpression ( COMMA rel= booleanExpression )? )? RPAREN ;
    public final AbstractTextMarkerCondition conditionInList() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;

        StringListExpression list2 = null;

        WordListExpression list1 = null;

        NumberExpression dist = null;

        BooleanExpression rel = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:806:5: ( INLIST LPAREN ( (list2= stringListExpression )=>list2= stringListExpression | list1= wordListExpression ) ( COMMA dist= numberExpression ( COMMA rel= booleanExpression )? )? RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:807:5: INLIST LPAREN ( (list2= stringListExpression )=>list2= stringListExpression | list1= wordListExpression ) ( COMMA dist= numberExpression ( COMMA rel= booleanExpression )? )? RPAREN
            {
            match(input,INLIST,FOLLOW_INLIST_in_conditionInList3934); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionInList3936); if (state.failed) return cond;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:807:19: ( (list2= stringListExpression )=>list2= stringListExpression | list1= wordListExpression )
            int alt86=2;
            int LA86_0 = input.LA(1);

            if ( (LA86_0==LCURLY) && (synpred13_TextMarkerParser())) {
                alt86=1;
            }
            else if ( (LA86_0==Identifier) ) {
                int LA86_2 = input.LA(2);

                if ( ((synpred13_TextMarkerParser()&&(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRINGLIST")))) ) {
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:807:20: (list2= stringListExpression )=>list2= stringListExpression
                    {
                    pushFollow(FOLLOW_stringListExpression_in_conditionInList3951);
                    list2=stringListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:807:83: list1= wordListExpression
                    {
                    pushFollow(FOLLOW_wordListExpression_in_conditionInList3959);
                    list1=wordListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }

            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:807:111: ( COMMA dist= numberExpression ( COMMA rel= booleanExpression )? )?
            int alt88=2;
            int LA88_0 = input.LA(1);

            if ( (LA88_0==COMMA) ) {
                alt88=1;
            }
            switch (alt88) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:807:112: COMMA dist= numberExpression ( COMMA rel= booleanExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionInList3963); if (state.failed) return cond;
                    pushFollow(FOLLOW_numberExpression_in_conditionInList3969);
                    dist=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:807:142: ( COMMA rel= booleanExpression )?
                    int alt87=2;
                    int LA87_0 = input.LA(1);

                    if ( (LA87_0==COMMA) ) {
                        alt87=1;
                    }
                    switch (alt87) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:807:143: COMMA rel= booleanExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionInList3972); if (state.failed) return cond;
                            pushFollow(FOLLOW_booleanExpression_in_conditionInList3978);
                            rel=booleanExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    }
                    break;

            }

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionInList3984); if (state.failed) return cond;
            if ( state.backtracking==0 ) {
              if(list1 != null) {cond = ConditionFactory.createConditionInList(list1, dist, rel, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);}
                  else {cond = ConditionFactory.createConditionInList(list2, dist, rel, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);};
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
        }
        return cond;
    }
    // $ANTLR end "conditionInList"


    // $ANTLR start "conditionIsInTag"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:811:1: conditionIsInTag returns [AbstractTextMarkerCondition cond = null] : ISINTAG LPAREN id= stringExpression ( COMMA id1= stringExpression ASSIGN_EQUAL id2= stringExpression )* RPAREN ;
    public final AbstractTextMarkerCondition conditionIsInTag() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;

        StringExpression id = null;

        StringExpression id1 = null;

        StringExpression id2 = null;



        List<StringExpression> list1 = new ArrayList<StringExpression>();
        List<StringExpression> list2 = new ArrayList<StringExpression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:816:5: ( ISINTAG LPAREN id= stringExpression ( COMMA id1= stringExpression ASSIGN_EQUAL id2= stringExpression )* RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:817:5: ISINTAG LPAREN id= stringExpression ( COMMA id1= stringExpression ASSIGN_EQUAL id2= stringExpression )* RPAREN
            {
            match(input,ISINTAG,FOLLOW_ISINTAG_in_conditionIsInTag4019); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionIsInTag4021); if (state.failed) return cond;
            pushFollow(FOLLOW_stringExpression_in_conditionIsInTag4027);
            id=stringExpression();

            state._fsp--;
            if (state.failed) return cond;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:817:42: ( COMMA id1= stringExpression ASSIGN_EQUAL id2= stringExpression )*
            loop89:
            do {
                int alt89=2;
                int LA89_0 = input.LA(1);

                if ( (LA89_0==COMMA) ) {
                    alt89=1;
                }


                switch (alt89) {
            	case 1 :
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:817:43: COMMA id1= stringExpression ASSIGN_EQUAL id2= stringExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_conditionIsInTag4030); if (state.failed) return cond;
            	    pushFollow(FOLLOW_stringExpression_in_conditionIsInTag4036);
            	    id1=stringExpression();

            	    state._fsp--;
            	    if (state.failed) return cond;
            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_conditionIsInTag4038); if (state.failed) return cond;
            	    pushFollow(FOLLOW_stringExpression_in_conditionIsInTag4044);
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

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionIsInTag4050); if (state.failed) return cond;
            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createConditionIsInTag(id, list1, list2, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return cond;
    }
    // $ANTLR end "conditionIsInTag"


    // $ANTLR start "conditionLast"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:821:1: conditionLast returns [AbstractTextMarkerCondition cond = null] : LAST LPAREN type= typeExpression RPAREN ;
    public final AbstractTextMarkerCondition conditionLast() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;

        TypeExpression type = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:822:5: ( LAST LPAREN type= typeExpression RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:823:5: LAST LPAREN type= typeExpression RPAREN
            {
            match(input,LAST,FOLLOW_LAST_in_conditionLast4089); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionLast4091); if (state.failed) return cond;
            pushFollow(FOLLOW_typeExpression_in_conditionLast4097);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;
            match(input,RPAREN,FOLLOW_RPAREN_in_conditionLast4099); if (state.failed) return cond;
            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createConditionLast(type, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return cond;
    }
    // $ANTLR end "conditionLast"


    // $ANTLR start "conditionMofN"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:828:1: conditionMofN returns [AbstractTextMarkerCondition cond = null] : MOFN LPAREN min= numberExpression COMMA max= numberExpression COMMA conds= conditions RPAREN ;
    public final AbstractTextMarkerCondition conditionMofN() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;

        NumberExpression min = null;

        NumberExpression max = null;

        List<AbstractTextMarkerCondition> conds = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:829:5: ( MOFN LPAREN min= numberExpression COMMA max= numberExpression COMMA conds= conditions RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:830:5: MOFN LPAREN min= numberExpression COMMA max= numberExpression COMMA conds= conditions RPAREN
            {
            match(input,MOFN,FOLLOW_MOFN_in_conditionMofN4146); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionMofN4148); if (state.failed) return cond;
            pushFollow(FOLLOW_numberExpression_in_conditionMofN4154);
            min=numberExpression();

            state._fsp--;
            if (state.failed) return cond;
            match(input,COMMA,FOLLOW_COMMA_in_conditionMofN4156); if (state.failed) return cond;
            pushFollow(FOLLOW_numberExpression_in_conditionMofN4162);
            max=numberExpression();

            state._fsp--;
            if (state.failed) return cond;
            match(input,COMMA,FOLLOW_COMMA_in_conditionMofN4164); if (state.failed) return cond;
            pushFollow(FOLLOW_conditions_in_conditionMofN4170);
            conds=conditions();

            state._fsp--;
            if (state.failed) return cond;
            match(input,RPAREN,FOLLOW_RPAREN_in_conditionMofN4172); if (state.failed) return cond;
            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createConditionMOfN(conds, min, max, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return cond;
    }
    // $ANTLR end "conditionMofN"


    // $ANTLR start "conditionNear"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:834:1: conditionNear returns [AbstractTextMarkerCondition cond = null] : NEAR LPAREN type= typeExpression COMMA min= numberExpression COMMA max= numberExpression ( COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )? )? RPAREN ;
    public final AbstractTextMarkerCondition conditionNear() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;

        TypeExpression type = null;

        NumberExpression min = null;

        NumberExpression max = null;

        BooleanExpression direction = null;

        BooleanExpression filtered = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:835:5: ( NEAR LPAREN type= typeExpression COMMA min= numberExpression COMMA max= numberExpression ( COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )? )? RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:836:5: NEAR LPAREN type= typeExpression COMMA min= numberExpression COMMA max= numberExpression ( COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )? )? RPAREN
            {
            match(input,NEAR,FOLLOW_NEAR_in_conditionNear4207); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionNear4209); if (state.failed) return cond;
            pushFollow(FOLLOW_typeExpression_in_conditionNear4215);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;
            match(input,COMMA,FOLLOW_COMMA_in_conditionNear4217); if (state.failed) return cond;
            pushFollow(FOLLOW_numberExpression_in_conditionNear4223);
            min=numberExpression();

            state._fsp--;
            if (state.failed) return cond;
            match(input,COMMA,FOLLOW_COMMA_in_conditionNear4225); if (state.failed) return cond;
            pushFollow(FOLLOW_numberExpression_in_conditionNear4231);
            max=numberExpression();

            state._fsp--;
            if (state.failed) return cond;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:836:97: ( COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )? )?
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( (LA91_0==COMMA) ) {
                alt91=1;
            }
            switch (alt91) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:836:98: COMMA direction= booleanExpression ( COMMA filtered= booleanExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionNear4234); if (state.failed) return cond;
                    pushFollow(FOLLOW_booleanExpression_in_conditionNear4240);
                    direction=booleanExpression();

                    state._fsp--;
                    if (state.failed) return cond;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:836:134: ( COMMA filtered= booleanExpression )?
                    int alt90=2;
                    int LA90_0 = input.LA(1);

                    if ( (LA90_0==COMMA) ) {
                        alt90=1;
                    }
                    switch (alt90) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:836:135: COMMA filtered= booleanExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionNear4243); if (state.failed) return cond;
                            pushFollow(FOLLOW_booleanExpression_in_conditionNear4249);
                            filtered=booleanExpression();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    }
                    break;

            }

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionNear4255); if (state.failed) return cond;
            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createConditionNear(type, min, max, direction, filtered, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return cond;
    }
    // $ANTLR end "conditionNear"


    // $ANTLR start "conditionNot"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:839:1: conditionNot returns [AbstractTextMarkerCondition cond = null] : ( ( MINUS c= condition ) | ( NOT LPAREN c= condition RPAREN ) ) ;
    public final AbstractTextMarkerCondition conditionNot() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;

        AbstractTextMarkerCondition c = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:840:5: ( ( ( MINUS c= condition ) | ( NOT LPAREN c= condition RPAREN ) ) )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:841:5: ( ( MINUS c= condition ) | ( NOT LPAREN c= condition RPAREN ) )
            {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:841:5: ( ( MINUS c= condition ) | ( NOT LPAREN c= condition RPAREN ) )
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:841:6: ( MINUS c= condition )
                    {
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:841:6: ( MINUS c= condition )
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:841:7: MINUS c= condition
                    {
                    match(input,MINUS,FOLLOW_MINUS_in_conditionNot4291); if (state.failed) return cond;
                    pushFollow(FOLLOW_condition_in_conditionNot4297);
                    c=condition();

                    state._fsp--;
                    if (state.failed) return cond;

                    }


                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:841:31: ( NOT LPAREN c= condition RPAREN )
                    {
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:841:31: ( NOT LPAREN c= condition RPAREN )
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:841:32: NOT LPAREN c= condition RPAREN
                    {
                    match(input,NOT,FOLLOW_NOT_in_conditionNot4304); if (state.failed) return cond;
                    match(input,LPAREN,FOLLOW_LPAREN_in_conditionNot4306); if (state.failed) return cond;
                    pushFollow(FOLLOW_condition_in_conditionNot4312);
                    c=condition();

                    state._fsp--;
                    if (state.failed) return cond;
                    match(input,RPAREN,FOLLOW_RPAREN_in_conditionNot4314); if (state.failed) return cond;

                    }


                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createConditionNot(c, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return cond;
    }
    // $ANTLR end "conditionNot"


    // $ANTLR start "conditionOr"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:844:1: conditionOr returns [AbstractTextMarkerCondition cond = null] : OR LPAREN conds= conditions RPAREN ;
    public final AbstractTextMarkerCondition conditionOr() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;

        List<AbstractTextMarkerCondition> conds = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:845:5: ( OR LPAREN conds= conditions RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:846:5: OR LPAREN conds= conditions RPAREN
            {
            match(input,OR,FOLLOW_OR_in_conditionOr4353); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionOr4355); if (state.failed) return cond;
            pushFollow(FOLLOW_conditions_in_conditionOr4361);
            conds=conditions();

            state._fsp--;
            if (state.failed) return cond;
            match(input,RPAREN,FOLLOW_RPAREN_in_conditionOr4363); if (state.failed) return cond;
            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createConditionOr(conds, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return cond;
    }
    // $ANTLR end "conditionOr"


    // $ANTLR start "conditionPartOf"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:849:1: conditionPartOf returns [AbstractTextMarkerCondition cond = null] : PARTOF LPAREN (type1= typeExpression | type2= typeListExpression ) RPAREN ;
    public final AbstractTextMarkerCondition conditionPartOf() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;

        TypeExpression type1 = null;

        TypeListExpression type2 = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:850:5: ( PARTOF LPAREN (type1= typeExpression | type2= typeListExpression ) RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:851:5: PARTOF LPAREN (type1= typeExpression | type2= typeListExpression ) RPAREN
            {
            match(input,PARTOF,FOLLOW_PARTOF_in_conditionPartOf4393); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionPartOf4395); if (state.failed) return cond;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:851:19: (type1= typeExpression | type2= typeListExpression )
            int alt93=2;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA93_1 = input.LA(2);

                if ( (!(((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))))) ) {
                    alt93=1;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))) ) {
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:851:20: type1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionPartOf4402);
                    type1=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:851:43: type2= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionPartOf4408);
                    type2=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionPartOf4411); if (state.failed) return cond;
            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createConditionPartOf(type1, type2, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return cond;
    }
    // $ANTLR end "conditionPartOf"


    // $ANTLR start "conditionPartOfNeq"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:854:1: conditionPartOfNeq returns [AbstractTextMarkerCondition cond = null] : PARTOFNEQ LPAREN (type1= typeExpression | type2= typeListExpression ) RPAREN ;
    public final AbstractTextMarkerCondition conditionPartOfNeq() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;

        TypeExpression type1 = null;

        TypeListExpression type2 = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:855:5: ( PARTOFNEQ LPAREN (type1= typeExpression | type2= typeListExpression ) RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:856:5: PARTOFNEQ LPAREN (type1= typeExpression | type2= typeListExpression ) RPAREN
            {
            match(input,PARTOFNEQ,FOLLOW_PARTOFNEQ_in_conditionPartOfNeq4441); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionPartOfNeq4443); if (state.failed) return cond;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:856:22: (type1= typeExpression | type2= typeListExpression )
            int alt94=2;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA94_1 = input.LA(2);

                if ( (!(((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))))) ) {
                    alt94=1;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))) ) {
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:856:23: type1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionPartOfNeq4450);
                    type1=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:856:46: type2= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionPartOfNeq4456);
                    type2=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionPartOfNeq4459); if (state.failed) return cond;
            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createConditionPartOfNeq(type1, type2, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return cond;
    }
    // $ANTLR end "conditionPartOfNeq"


    // $ANTLR start "conditionPosition"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:860:1: conditionPosition returns [AbstractTextMarkerCondition cond = null] : POSITION LPAREN type= typeExpression COMMA pos= numberExpression RPAREN ;
    public final AbstractTextMarkerCondition conditionPosition() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;

        TypeExpression type = null;

        NumberExpression pos = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:861:5: ( POSITION LPAREN type= typeExpression COMMA pos= numberExpression RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:862:5: POSITION LPAREN type= typeExpression COMMA pos= numberExpression RPAREN
            {
            match(input,POSITION,FOLLOW_POSITION_in_conditionPosition4493); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionPosition4495); if (state.failed) return cond;
            pushFollow(FOLLOW_typeExpression_in_conditionPosition4501);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return cond;
            match(input,COMMA,FOLLOW_COMMA_in_conditionPosition4503); if (state.failed) return cond;
            pushFollow(FOLLOW_numberExpression_in_conditionPosition4509);
            pos=numberExpression();

            state._fsp--;
            if (state.failed) return cond;
            match(input,RPAREN,FOLLOW_RPAREN_in_conditionPosition4511); if (state.failed) return cond;
            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createConditionPosition(type, pos, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return cond;
    }
    // $ANTLR end "conditionPosition"


    // $ANTLR start "conditionRegExp"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:865:1: conditionRegExp returns [AbstractTextMarkerCondition cond = null] : REGEXP LPAREN pattern= stringExpression ( COMMA caseSensitive= booleanExpression )? RPAREN ;
    public final AbstractTextMarkerCondition conditionRegExp() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;

        StringExpression pattern = null;

        BooleanExpression caseSensitive = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:866:5: ( REGEXP LPAREN pattern= stringExpression ( COMMA caseSensitive= booleanExpression )? RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:867:5: REGEXP LPAREN pattern= stringExpression ( COMMA caseSensitive= booleanExpression )? RPAREN
            {
            match(input,REGEXP,FOLLOW_REGEXP_in_conditionRegExp4541); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionRegExp4543); if (state.failed) return cond;
            pushFollow(FOLLOW_stringExpression_in_conditionRegExp4549);
            pattern=stringExpression();

            state._fsp--;
            if (state.failed) return cond;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:867:46: ( COMMA caseSensitive= booleanExpression )?
            int alt95=2;
            int LA95_0 = input.LA(1);

            if ( (LA95_0==COMMA) ) {
                alt95=1;
            }
            switch (alt95) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:867:47: COMMA caseSensitive= booleanExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionRegExp4552); if (state.failed) return cond;
                    pushFollow(FOLLOW_booleanExpression_in_conditionRegExp4558);
                    caseSensitive=booleanExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionRegExp4562); if (state.failed) return cond;
            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createConditionRegExp(pattern, caseSensitive, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return cond;
    }
    // $ANTLR end "conditionRegExp"


    // $ANTLR start "conditionScore"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:871:1: conditionScore returns [AbstractTextMarkerCondition cond = null] : SCORE LPAREN min= numberExpression ( COMMA max= numberExpression ( COMMA var= numberVariable )? )? RPAREN ;
    public final AbstractTextMarkerCondition conditionScore() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;

        NumberExpression min = null;

        NumberExpression max = null;

        Token var = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:872:5: ( SCORE LPAREN min= numberExpression ( COMMA max= numberExpression ( COMMA var= numberVariable )? )? RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:873:5: SCORE LPAREN min= numberExpression ( COMMA max= numberExpression ( COMMA var= numberVariable )? )? RPAREN
            {
            match(input,SCORE,FOLLOW_SCORE_in_conditionScore4597); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionScore4599); if (state.failed) return cond;
            pushFollow(FOLLOW_numberExpression_in_conditionScore4605);
            min=numberExpression();

            state._fsp--;
            if (state.failed) return cond;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:873:41: ( COMMA max= numberExpression ( COMMA var= numberVariable )? )?
            int alt97=2;
            int LA97_0 = input.LA(1);

            if ( (LA97_0==COMMA) ) {
                alt97=1;
            }
            switch (alt97) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:873:42: COMMA max= numberExpression ( COMMA var= numberVariable )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionScore4608); if (state.failed) return cond;
                    pushFollow(FOLLOW_numberExpression_in_conditionScore4614);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:874:5: ( COMMA var= numberVariable )?
                    int alt96=2;
                    int LA96_0 = input.LA(1);

                    if ( (LA96_0==COMMA) ) {
                        alt96=1;
                    }
                    switch (alt96) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:874:6: COMMA var= numberVariable
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_conditionScore4621); if (state.failed) return cond;
                            pushFollow(FOLLOW_numberVariable_in_conditionScore4627);
                            var=numberVariable();

                            state._fsp--;
                            if (state.failed) return cond;

                            }
                            break;

                    }


                    }
                    break;

            }

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionScore4634); if (state.failed) return cond;
            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createConditionScore(min, max, var, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return cond;
    }
    // $ANTLR end "conditionScore"


    // $ANTLR start "conditionVote"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:879:1: conditionVote returns [AbstractTextMarkerCondition cond = null] : VOTE LPAREN type1= typeExpression COMMA type2= typeExpression RPAREN ;
    public final AbstractTextMarkerCondition conditionVote() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;

        TypeExpression type1 = null;

        TypeExpression type2 = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:880:5: ( VOTE LPAREN type1= typeExpression COMMA type2= typeExpression RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:881:5: VOTE LPAREN type1= typeExpression COMMA type2= typeExpression RPAREN
            {
            match(input,VOTE,FOLLOW_VOTE_in_conditionVote4669); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionVote4671); if (state.failed) return cond;
            pushFollow(FOLLOW_typeExpression_in_conditionVote4677);
            type1=typeExpression();

            state._fsp--;
            if (state.failed) return cond;
            match(input,COMMA,FOLLOW_COMMA_in_conditionVote4679); if (state.failed) return cond;
            pushFollow(FOLLOW_typeExpression_in_conditionVote4685);
            type2=typeExpression();

            state._fsp--;
            if (state.failed) return cond;
            match(input,RPAREN,FOLLOW_RPAREN_in_conditionVote4687); if (state.failed) return cond;
            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createConditionVote(type1, type2, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return cond;
    }
    // $ANTLR end "conditionVote"


    // $ANTLR start "conditionIf"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:885:1: conditionIf returns [AbstractTextMarkerCondition cond = null] : IF LPAREN e= booleanExpression RPAREN ;
    public final AbstractTextMarkerCondition conditionIf() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;

        BooleanExpression e = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:886:5: ( IF LPAREN e= booleanExpression RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:887:5: IF LPAREN e= booleanExpression RPAREN
            {
            match(input,IF,FOLLOW_IF_in_conditionIf4725); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionIf4727); if (state.failed) return cond;
            pushFollow(FOLLOW_booleanExpression_in_conditionIf4733);
            e=booleanExpression();

            state._fsp--;
            if (state.failed) return cond;
            match(input,RPAREN,FOLLOW_RPAREN_in_conditionIf4735); if (state.failed) return cond;
            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createConditionIf(e, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return cond;
    }
    // $ANTLR end "conditionIf"


    // $ANTLR start "conditionFeature"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:891:1: conditionFeature returns [AbstractTextMarkerCondition cond = null] : FEATURE LPAREN se= stringExpression COMMA v= argument RPAREN ;
    public final AbstractTextMarkerCondition conditionFeature() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;

        StringExpression se = null;

        TextMarkerExpression v = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:892:5: ( FEATURE LPAREN se= stringExpression COMMA v= argument RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:893:5: FEATURE LPAREN se= stringExpression COMMA v= argument RPAREN
            {
            match(input,FEATURE,FOLLOW_FEATURE_in_conditionFeature4769); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionFeature4771); if (state.failed) return cond;
            pushFollow(FOLLOW_stringExpression_in_conditionFeature4777);
            se=stringExpression();

            state._fsp--;
            if (state.failed) return cond;
            match(input,COMMA,FOLLOW_COMMA_in_conditionFeature4779); if (state.failed) return cond;
            pushFollow(FOLLOW_argument_in_conditionFeature4785);
            v=argument();

            state._fsp--;
            if (state.failed) return cond;
            match(input,RPAREN,FOLLOW_RPAREN_in_conditionFeature4787); if (state.failed) return cond;
            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createConditionFeature(se, v, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return cond;
    }
    // $ANTLR end "conditionFeature"


    // $ANTLR start "conditionParse"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:897:1: conditionParse returns [AbstractTextMarkerCondition cond = null] : PARSE LPAREN {...}?id= Identifier RPAREN ;
    public final AbstractTextMarkerCondition conditionParse() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;

        Token id=null;

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:898:5: ( PARSE LPAREN {...}?id= Identifier RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:899:5: PARSE LPAREN {...}?id= Identifier RPAREN
            {
            match(input,PARSE,FOLLOW_PARSE_in_conditionParse4821); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionParse4823); if (state.failed) return cond;
            if ( !((isVariable(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText()))) ) {
                if (state.backtracking>0) {state.failed=true; return cond;}
                throw new FailedPredicateException(input, "conditionParse", "isVariable($blockDeclaration::env,input.LT(1).getText())");
            }
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_conditionParse4831); if (state.failed) return cond;
            match(input,RPAREN,FOLLOW_RPAREN_in_conditionParse4833); if (state.failed) return cond;
            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createConditionParse(id, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return cond;
    }
    // $ANTLR end "conditionParse"


    // $ANTLR start "conditionIs"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:903:1: conditionIs returns [AbstractTextMarkerCondition cond = null] : IS LPAREN (type1= typeExpression | type2= typeListExpression ) RPAREN ;
    public final AbstractTextMarkerCondition conditionIs() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;

        TypeExpression type1 = null;

        TypeListExpression type2 = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:904:5: ( IS LPAREN (type1= typeExpression | type2= typeListExpression ) RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:905:5: IS LPAREN (type1= typeExpression | type2= typeListExpression ) RPAREN
            {
            match(input,IS,FOLLOW_IS_in_conditionIs4864); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionIs4866); if (state.failed) return cond;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:905:15: (type1= typeExpression | type2= typeListExpression )
            int alt98=2;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA98_1 = input.LA(2);

                if ( (!(((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))))) ) {
                    alt98=1;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))) ) {
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:905:16: type1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionIs4873);
                    type1=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:905:39: type2= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionIs4879);
                    type2=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionIs4882); if (state.failed) return cond;
            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createConditionIs(type1, type2, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return cond;
    }
    // $ANTLR end "conditionIs"


    // $ANTLR start "conditionBefore"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:909:1: conditionBefore returns [AbstractTextMarkerCondition cond = null] : BEFORE LPAREN (type1= typeExpression | type2= typeListExpression ) RPAREN ;
    public final AbstractTextMarkerCondition conditionBefore() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;

        TypeExpression type1 = null;

        TypeListExpression type2 = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:910:5: ( BEFORE LPAREN (type1= typeExpression | type2= typeListExpression ) RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:911:5: BEFORE LPAREN (type1= typeExpression | type2= typeListExpression ) RPAREN
            {
            match(input,BEFORE,FOLLOW_BEFORE_in_conditionBefore4913); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionBefore4915); if (state.failed) return cond;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:911:19: (type1= typeExpression | type2= typeListExpression )
            int alt99=2;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA99_1 = input.LA(2);

                if ( (!(((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))))) ) {
                    alt99=1;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))) ) {
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:911:20: type1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionBefore4922);
                    type1=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:911:43: type2= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionBefore4928);
                    type2=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionBefore4931); if (state.failed) return cond;
            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createConditionBefore(type1,type2, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return cond;
    }
    // $ANTLR end "conditionBefore"


    // $ANTLR start "conditionAfter"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:915:1: conditionAfter returns [AbstractTextMarkerCondition cond = null] : AFTER LPAREN (type1= typeExpression | type2= typeListExpression ) RPAREN ;
    public final AbstractTextMarkerCondition conditionAfter() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;

        TypeExpression type1 = null;

        TypeListExpression type2 = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:916:5: ( AFTER LPAREN (type1= typeExpression | type2= typeListExpression ) RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:917:5: AFTER LPAREN (type1= typeExpression | type2= typeListExpression ) RPAREN
            {
            match(input,AFTER,FOLLOW_AFTER_in_conditionAfter4962); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionAfter4964); if (state.failed) return cond;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:917:18: (type1= typeExpression | type2= typeListExpression )
            int alt100=2;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                int LA100_1 = input.LA(2);

                if ( (!(((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))))) ) {
                    alt100=1;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))) ) {
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:917:19: type1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionAfter4971);
                    type1=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:917:42: type2= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionAfter4977);
                    type2=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionAfter4980); if (state.failed) return cond;
            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createConditionAfter(type1,type2, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return cond;
    }
    // $ANTLR end "conditionAfter"


    // $ANTLR start "conditionStartsWith"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:921:1: conditionStartsWith returns [AbstractTextMarkerCondition cond = null] : STARTSWITH LPAREN (type1= typeExpression | type2= typeListExpression ) RPAREN ;
    public final AbstractTextMarkerCondition conditionStartsWith() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;

        TypeExpression type1 = null;

        TypeListExpression type2 = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:922:5: ( STARTSWITH LPAREN (type1= typeExpression | type2= typeListExpression ) RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:923:5: STARTSWITH LPAREN (type1= typeExpression | type2= typeListExpression ) RPAREN
            {
            match(input,STARTSWITH,FOLLOW_STARTSWITH_in_conditionStartsWith5011); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionStartsWith5013); if (state.failed) return cond;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:923:23: (type1= typeExpression | type2= typeListExpression )
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:923:24: type1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionStartsWith5020);
                    type1=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:923:47: type2= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionStartsWith5026);
                    type2=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionStartsWith5029); if (state.failed) return cond;
            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createConditionStartsWith(type1,type2, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return cond;
    }
    // $ANTLR end "conditionStartsWith"


    // $ANTLR start "conditionEndsWith"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:927:1: conditionEndsWith returns [AbstractTextMarkerCondition cond = null] : ENDSWITH LPAREN (type1= typeExpression | type2= typeListExpression ) RPAREN ;
    public final AbstractTextMarkerCondition conditionEndsWith() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;

        TypeExpression type1 = null;

        TypeListExpression type2 = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:928:5: ( ENDSWITH LPAREN (type1= typeExpression | type2= typeListExpression ) RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:929:5: ENDSWITH LPAREN (type1= typeExpression | type2= typeListExpression ) RPAREN
            {
            match(input,ENDSWITH,FOLLOW_ENDSWITH_in_conditionEndsWith5060); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionEndsWith5062); if (state.failed) return cond;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:929:21: (type1= typeExpression | type2= typeListExpression )
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:929:22: type1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_conditionEndsWith5069);
                    type1=typeExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:929:45: type2= typeListExpression
                    {
                    pushFollow(FOLLOW_typeListExpression_in_conditionEndsWith5075);
                    type2=typeListExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionEndsWith5078); if (state.failed) return cond;
            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createConditionEndsWith(type1,type2, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return cond;
    }
    // $ANTLR end "conditionEndsWith"


    // $ANTLR start "conditionSize"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:933:1: conditionSize returns [AbstractTextMarkerCondition cond = null] : SIZE LPAREN list= listExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN ;
    public final AbstractTextMarkerCondition conditionSize() throws RecognitionException {
        AbstractTextMarkerCondition cond =  null;

        ListExpression list = null;

        NumberExpression min = null;

        NumberExpression max = null;

        Token var = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:934:5: ( SIZE LPAREN list= listExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:935:5: SIZE LPAREN list= listExpression ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
            {
            match(input,SIZE,FOLLOW_SIZE_in_conditionSize5109); if (state.failed) return cond;
            match(input,LPAREN,FOLLOW_LPAREN_in_conditionSize5111); if (state.failed) return cond;
            pushFollow(FOLLOW_listExpression_in_conditionSize5117);
            list=listExpression();

            state._fsp--;
            if (state.failed) return cond;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:935:39: ( COMMA min= numberExpression COMMA max= numberExpression )?
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:935:40: COMMA min= numberExpression COMMA max= numberExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionSize5120); if (state.failed) return cond;
                    pushFollow(FOLLOW_numberExpression_in_conditionSize5126);
                    min=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;
                    match(input,COMMA,FOLLOW_COMMA_in_conditionSize5128); if (state.failed) return cond;
                    pushFollow(FOLLOW_numberExpression_in_conditionSize5134);
                    max=numberExpression();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }

            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:935:100: ( COMMA var= numberVariable )?
            int alt104=2;
            int LA104_0 = input.LA(1);

            if ( (LA104_0==COMMA) ) {
                alt104=1;
            }
            switch (alt104) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:935:101: COMMA var= numberVariable
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_conditionSize5139); if (state.failed) return cond;
                    pushFollow(FOLLOW_numberVariable_in_conditionSize5145);
                    var=numberVariable();

                    state._fsp--;
                    if (state.failed) return cond;

                    }
                    break;

            }

            match(input,RPAREN,FOLLOW_RPAREN_in_conditionSize5149); if (state.failed) return cond;
            if ( state.backtracking==0 ) {
              cond = ConditionFactory.createConditionSize(list, min, max, var, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return cond;
    }
    // $ANTLR end "conditionSize"


    // $ANTLR start "action"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:939:1: action returns [AbstractTextMarkerAction result = null] : (a= actionColor | a= actionDel | a= actionLog | a= actionMark | a= actionMarkScore | a= actionMarkFast | a= actionMarkLast | a= actionReplace | a= actionFilterMarkup | a= actionFilterType | a= actionRetainMarkup | a= actionRetainType | a= actionCreate | a= actionFill | a= actionCall | a= actionAssign | a= actionSetFeature | a= actionGetFeature | a= actionUnmark | a= actionUnmarkAll | a= actionTransfer | a= actionMarkOnce | a= actionTrie | a= actionGather | a= actionExec | a= actionMarkTable | a= actionAdd | a= actionRemove | a= actionRemoveDuplicate | a= actionMerge | a= actionGet | a= actionGetList | a= actionMatchedText | a= actionClear | a= actionExpand | (a= externalAction )=>a= externalAction | a= variableAction ) ;
    public final AbstractTextMarkerAction action() throws RecognitionException {
        AbstractTextMarkerAction result =  null;

        AbstractTextMarkerAction a = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:940:2: ( (a= actionColor | a= actionDel | a= actionLog | a= actionMark | a= actionMarkScore | a= actionMarkFast | a= actionMarkLast | a= actionReplace | a= actionFilterMarkup | a= actionFilterType | a= actionRetainMarkup | a= actionRetainType | a= actionCreate | a= actionFill | a= actionCall | a= actionAssign | a= actionSetFeature | a= actionGetFeature | a= actionUnmark | a= actionUnmarkAll | a= actionTransfer | a= actionMarkOnce | a= actionTrie | a= actionGather | a= actionExec | a= actionMarkTable | a= actionAdd | a= actionRemove | a= actionRemoveDuplicate | a= actionMerge | a= actionGet | a= actionGetList | a= actionMatchedText | a= actionClear | a= actionExpand | (a= externalAction )=>a= externalAction | a= variableAction ) )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:941:2: (a= actionColor | a= actionDel | a= actionLog | a= actionMark | a= actionMarkScore | a= actionMarkFast | a= actionMarkLast | a= actionReplace | a= actionFilterMarkup | a= actionFilterType | a= actionRetainMarkup | a= actionRetainType | a= actionCreate | a= actionFill | a= actionCall | a= actionAssign | a= actionSetFeature | a= actionGetFeature | a= actionUnmark | a= actionUnmarkAll | a= actionTransfer | a= actionMarkOnce | a= actionTrie | a= actionGather | a= actionExec | a= actionMarkTable | a= actionAdd | a= actionRemove | a= actionRemoveDuplicate | a= actionMerge | a= actionGet | a= actionGetList | a= actionMatchedText | a= actionClear | a= actionExpand | (a= externalAction )=>a= externalAction | a= variableAction )
            {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:941:2: (a= actionColor | a= actionDel | a= actionLog | a= actionMark | a= actionMarkScore | a= actionMarkFast | a= actionMarkLast | a= actionReplace | a= actionFilterMarkup | a= actionFilterType | a= actionRetainMarkup | a= actionRetainType | a= actionCreate | a= actionFill | a= actionCall | a= actionAssign | a= actionSetFeature | a= actionGetFeature | a= actionUnmark | a= actionUnmarkAll | a= actionTransfer | a= actionMarkOnce | a= actionTrie | a= actionGather | a= actionExec | a= actionMarkTable | a= actionAdd | a= actionRemove | a= actionRemoveDuplicate | a= actionMerge | a= actionGet | a= actionGetList | a= actionMatchedText | a= actionClear | a= actionExpand | (a= externalAction )=>a= externalAction | a= variableAction )
            int alt105=37;
            alt105 = dfa105.predict(input);
            switch (alt105) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:942:2: a= actionColor
                    {
                    pushFollow(FOLLOW_actionColor_in_action5181);
                    a=actionColor();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:943:4: a= actionDel
                    {
                    pushFollow(FOLLOW_actionDel_in_action5190);
                    a=actionDel();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:944:4: a= actionLog
                    {
                    pushFollow(FOLLOW_actionLog_in_action5199);
                    a=actionLog();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:945:4: a= actionMark
                    {
                    pushFollow(FOLLOW_actionMark_in_action5208);
                    a=actionMark();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:946:4: a= actionMarkScore
                    {
                    pushFollow(FOLLOW_actionMarkScore_in_action5217);
                    a=actionMarkScore();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 6 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:947:4: a= actionMarkFast
                    {
                    pushFollow(FOLLOW_actionMarkFast_in_action5226);
                    a=actionMarkFast();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 7 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:948:4: a= actionMarkLast
                    {
                    pushFollow(FOLLOW_actionMarkLast_in_action5235);
                    a=actionMarkLast();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 8 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:949:4: a= actionReplace
                    {
                    pushFollow(FOLLOW_actionReplace_in_action5244);
                    a=actionReplace();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 9 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:950:4: a= actionFilterMarkup
                    {
                    pushFollow(FOLLOW_actionFilterMarkup_in_action5253);
                    a=actionFilterMarkup();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 10 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:951:4: a= actionFilterType
                    {
                    pushFollow(FOLLOW_actionFilterType_in_action5262);
                    a=actionFilterType();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 11 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:952:4: a= actionRetainMarkup
                    {
                    pushFollow(FOLLOW_actionRetainMarkup_in_action5271);
                    a=actionRetainMarkup();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 12 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:953:4: a= actionRetainType
                    {
                    pushFollow(FOLLOW_actionRetainType_in_action5280);
                    a=actionRetainType();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 13 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:954:4: a= actionCreate
                    {
                    pushFollow(FOLLOW_actionCreate_in_action5289);
                    a=actionCreate();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 14 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:955:4: a= actionFill
                    {
                    pushFollow(FOLLOW_actionFill_in_action5298);
                    a=actionFill();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 15 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:956:4: a= actionCall
                    {
                    pushFollow(FOLLOW_actionCall_in_action5307);
                    a=actionCall();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 16 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:957:4: a= actionAssign
                    {
                    pushFollow(FOLLOW_actionAssign_in_action5316);
                    a=actionAssign();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 17 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:958:4: a= actionSetFeature
                    {
                    pushFollow(FOLLOW_actionSetFeature_in_action5325);
                    a=actionSetFeature();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 18 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:959:4: a= actionGetFeature
                    {
                    pushFollow(FOLLOW_actionGetFeature_in_action5334);
                    a=actionGetFeature();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 19 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:960:4: a= actionUnmark
                    {
                    pushFollow(FOLLOW_actionUnmark_in_action5343);
                    a=actionUnmark();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 20 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:961:4: a= actionUnmarkAll
                    {
                    pushFollow(FOLLOW_actionUnmarkAll_in_action5352);
                    a=actionUnmarkAll();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 21 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:962:4: a= actionTransfer
                    {
                    pushFollow(FOLLOW_actionTransfer_in_action5361);
                    a=actionTransfer();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 22 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:963:4: a= actionMarkOnce
                    {
                    pushFollow(FOLLOW_actionMarkOnce_in_action5370);
                    a=actionMarkOnce();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 23 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:964:4: a= actionTrie
                    {
                    pushFollow(FOLLOW_actionTrie_in_action5379);
                    a=actionTrie();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 24 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:965:4: a= actionGather
                    {
                    pushFollow(FOLLOW_actionGather_in_action5388);
                    a=actionGather();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 25 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:966:4: a= actionExec
                    {
                    pushFollow(FOLLOW_actionExec_in_action5398);
                    a=actionExec();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 26 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:967:4: a= actionMarkTable
                    {
                    pushFollow(FOLLOW_actionMarkTable_in_action5407);
                    a=actionMarkTable();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 27 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:968:4: a= actionAdd
                    {
                    pushFollow(FOLLOW_actionAdd_in_action5417);
                    a=actionAdd();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 28 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:969:4: a= actionRemove
                    {
                    pushFollow(FOLLOW_actionRemove_in_action5426);
                    a=actionRemove();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 29 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:970:4: a= actionRemoveDuplicate
                    {
                    pushFollow(FOLLOW_actionRemoveDuplicate_in_action5435);
                    a=actionRemoveDuplicate();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 30 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:971:4: a= actionMerge
                    {
                    pushFollow(FOLLOW_actionMerge_in_action5444);
                    a=actionMerge();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 31 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:972:4: a= actionGet
                    {
                    pushFollow(FOLLOW_actionGet_in_action5453);
                    a=actionGet();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 32 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:973:4: a= actionGetList
                    {
                    pushFollow(FOLLOW_actionGetList_in_action5462);
                    a=actionGetList();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 33 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:974:4: a= actionMatchedText
                    {
                    pushFollow(FOLLOW_actionMatchedText_in_action5471);
                    a=actionMatchedText();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 34 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:975:4: a= actionClear
                    {
                    pushFollow(FOLLOW_actionClear_in_action5480);
                    a=actionClear();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 35 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:976:4: a= actionExpand
                    {
                    pushFollow(FOLLOW_actionExpand_in_action5489);
                    a=actionExpand();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 36 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:977:4: (a= externalAction )=>a= externalAction
                    {
                    pushFollow(FOLLOW_externalAction_in_action5507);
                    a=externalAction();

                    state._fsp--;
                    if (state.failed) return result;

                    }
                    break;
                case 37 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:978:4: a= variableAction
                    {
                    pushFollow(FOLLOW_variableAction_in_action5516);
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
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}
        finally {
        }
        return result;
    }
    // $ANTLR end "action"


    // $ANTLR start "variableAction"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:982:1: variableAction returns [AbstractTextMarkerAction action = null] : id= Identifier ;
    public final AbstractTextMarkerAction variableAction() throws RecognitionException {
        AbstractTextMarkerAction action =  null;

        Token id=null;

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:983:2: (id= Identifier )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:985:2: id= Identifier
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableAction5545); if (state.failed) return action;
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
        }
        return action;
    }
    // $ANTLR end "variableAction"


    // $ANTLR start "externalAction"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:992:1: externalAction returns [AbstractTextMarkerAction action = null] : id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final AbstractTextMarkerAction externalAction() throws RecognitionException {
        AbstractTextMarkerAction action =  null;

        Token id=null;
        List args = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:993:2: (id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:995:2: id= Identifier LPAREN args= varArgumentList RPAREN
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalAction5573); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_externalAction5575); if (state.failed) return action;
            pushFollow(FOLLOW_varArgumentList_in_externalAction5581);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return action;
            match(input,RPAREN,FOLLOW_RPAREN_in_externalAction5583); if (state.failed) return action;
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
        }
        return action;
    }
    // $ANTLR end "externalAction"


    // $ANTLR start "actionCreate"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1003:1: actionCreate returns [AbstractTextMarkerAction action = null] : name= CREATE LPAREN structure= typeExpression ( COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? )? RPAREN ;
    public final AbstractTextMarkerAction actionCreate() throws RecognitionException {
        AbstractTextMarkerAction action =  null;

        Token name=null;
        TypeExpression structure = null;

        NumberExpression index = null;

        StringExpression fname = null;

        TextMarkerExpression obj1 = null;



        	Map<StringExpression, TextMarkerExpression> map = new HashMap<StringExpression, TextMarkerExpression>();
            	List<NumberExpression> indexes = new ArrayList<NumberExpression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1008:5: (name= CREATE LPAREN structure= typeExpression ( COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? )? RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1009:5: name= CREATE LPAREN structure= typeExpression ( COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? )? RPAREN
            {
            name=(Token)match(input,CREATE,FOLLOW_CREATE_in_actionCreate5619); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionCreate5621); if (state.failed) return action;
            pushFollow(FOLLOW_typeExpression_in_actionCreate5627);
            structure=typeExpression();

            state._fsp--;
            if (state.failed) return action;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1010:4: ( COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )? )?
            int alt110=2;
            int LA110_0 = input.LA(1);

            if ( (LA110_0==COMMA) ) {
                alt110=1;
            }
            switch (alt110) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1010:5: COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionCreate5634); if (state.failed) return action;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1011:5: ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )?
                    int alt107=2;
                    alt107 = dfa107.predict(input);
                    switch (alt107) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1012:5: (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA
                            {
                            pushFollow(FOLLOW_numberExpression_in_actionCreate5659);
                            index=numberExpression();

                            state._fsp--;
                            if (state.failed) return action;
                            if ( state.backtracking==0 ) {
                              indexes.add(index);
                            }
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1012:80: ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )*
                            loop106:
                            do {
                                int alt106=2;
                                int LA106_0 = input.LA(1);

                                if ( (LA106_0==COMMA) ) {
                                    int LA106_1 = input.LA(2);

                                    if ( (synpred16_TextMarkerParser()) ) {
                                        alt106=1;
                                    }


                                }


                                switch (alt106) {
                            	case 1 :
                            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1012:81: ( COMMA index= numberExpression )=> ( COMMA index= numberExpression )
                            	    {
                            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1012:116: ( COMMA index= numberExpression )
                            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1012:117: COMMA index= numberExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_actionCreate5676); if (state.failed) return action;
                            	    pushFollow(FOLLOW_numberExpression_in_actionCreate5682);
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

                            match(input,COMMA,FOLLOW_COMMA_in_actionCreate5689); if (state.failed) return action;

                            }
                            break;

                    }

                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1013:5: (fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )* )?
                    int alt109=2;
                    int LA109_0 = input.LA(1);

                    if ( (LA109_0==REMOVESTRING||LA109_0==StringLiteral||LA109_0==Identifier) ) {
                        alt109=1;
                    }
                    switch (alt109) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1013:6: fname= stringExpression ASSIGN_EQUAL obj1= argument ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )*
                            {
                            pushFollow(FOLLOW_stringExpression_in_actionCreate5702);
                            fname=stringExpression();

                            state._fsp--;
                            if (state.failed) return action;
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionCreate5704); if (state.failed) return action;
                            pushFollow(FOLLOW_argument_in_actionCreate5710);
                            obj1=argument();

                            state._fsp--;
                            if (state.failed) return action;
                            if ( state.backtracking==0 ) {
                              map.put(fname,obj1);
                            }
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1014:5: ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )*
                            loop108:
                            do {
                                int alt108=2;
                                int LA108_0 = input.LA(1);

                                if ( (LA108_0==COMMA) ) {
                                    alt108=1;
                                }


                                switch (alt108) {
                            	case 1 :
                            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1014:6: COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_actionCreate5720); if (state.failed) return action;
                            	    pushFollow(FOLLOW_stringExpression_in_actionCreate5726);
                            	    fname=stringExpression();

                            	    state._fsp--;
                            	    if (state.failed) return action;
                            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionCreate5728); if (state.failed) return action;
                            	    pushFollow(FOLLOW_argument_in_actionCreate5734);
                            	    obj1=argument();

                            	    state._fsp--;
                            	    if (state.failed) return action;
                            	    if ( state.backtracking==0 ) {
                            	      map.put(fname,obj1);
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

            match(input,RPAREN,FOLLOW_RPAREN_in_actionCreate5749); if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createCreateAction(structure, map, indexes, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return action;
    }
    // $ANTLR end "actionCreate"


    // $ANTLR start "actionMarkTable"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1020:1: actionMarkTable returns [AbstractTextMarkerAction action = null] : name= MARKTABLE LPAREN structure= typeExpression COMMA index= numberExpression COMMA table= wordTableExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )* )? RPAREN ;
    public final AbstractTextMarkerAction actionMarkTable() throws RecognitionException {
        AbstractTextMarkerAction action =  null;

        Token name=null;
        TypeExpression structure = null;

        NumberExpression index = null;

        WordTableExpression table = null;

        StringExpression fname = null;

        NumberExpression obj1 = null;



        	Map<StringExpression, NumberExpression> map = new HashMap<StringExpression, NumberExpression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1024:5: (name= MARKTABLE LPAREN structure= typeExpression COMMA index= numberExpression COMMA table= wordTableExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )* )? RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1025:5: name= MARKTABLE LPAREN structure= typeExpression COMMA index= numberExpression COMMA table= wordTableExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )* )? RPAREN
            {
            name=(Token)match(input,MARKTABLE,FOLLOW_MARKTABLE_in_actionMarkTable5790); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkTable5792); if (state.failed) return action;
            pushFollow(FOLLOW_typeExpression_in_actionMarkTable5803);
            structure=typeExpression();

            state._fsp--;
            if (state.failed) return action;
            match(input,COMMA,FOLLOW_COMMA_in_actionMarkTable5805); if (state.failed) return action;
            pushFollow(FOLLOW_numberExpression_in_actionMarkTable5816);
            index=numberExpression();

            state._fsp--;
            if (state.failed) return action;
            match(input,COMMA,FOLLOW_COMMA_in_actionMarkTable5818); if (state.failed) return action;
            pushFollow(FOLLOW_wordTableExpression_in_actionMarkTable5828);
            table=wordTableExpression();

            state._fsp--;
            if (state.failed) return action;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1029:5: ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )* )?
            int alt112=2;
            int LA112_0 = input.LA(1);

            if ( (LA112_0==COMMA) ) {
                alt112=1;
            }
            switch (alt112) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1029:6: COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )*
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionMarkTable5836); if (state.failed) return action;
                    pushFollow(FOLLOW_stringExpression_in_actionMarkTable5850);
                    fname=stringExpression();

                    state._fsp--;
                    if (state.failed) return action;
                    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionMarkTable5852); if (state.failed) return action;
                    pushFollow(FOLLOW_numberExpression_in_actionMarkTable5858);
                    obj1=numberExpression();

                    state._fsp--;
                    if (state.failed) return action;
                    if ( state.backtracking==0 ) {
                      map.put(fname,obj1);
                    }
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1031:5: ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression )*
                    loop111:
                    do {
                        int alt111=2;
                        int LA111_0 = input.LA(1);

                        if ( (LA111_0==COMMA) ) {
                            alt111=1;
                        }


                        switch (alt111) {
                    	case 1 :
                    	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1031:6: COMMA fname= stringExpression ASSIGN_EQUAL obj1= numberExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionMarkTable5868); if (state.failed) return action;
                    	    pushFollow(FOLLOW_stringExpression_in_actionMarkTable5874);
                    	    fname=stringExpression();

                    	    state._fsp--;
                    	    if (state.failed) return action;
                    	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionMarkTable5876); if (state.failed) return action;
                    	    pushFollow(FOLLOW_numberExpression_in_actionMarkTable5882);
                    	    obj1=numberExpression();

                    	    state._fsp--;
                    	    if (state.failed) return action;
                    	    if ( state.backtracking==0 ) {
                    	      map.put(fname,obj1);
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

            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkTable5895); if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createMarkTableAction(structure, index, table, map, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return action;
    }
    // $ANTLR end "actionMarkTable"


    // $ANTLR start "actionGather"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1036:1: actionGather returns [AbstractTextMarkerAction action = null] : name= GATHER LPAREN structure= typeExpression ( COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression | obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression | obj2= numberListExpression ) )* )? )? RPAREN ;
    public final AbstractTextMarkerAction actionGather() throws RecognitionException {
        AbstractTextMarkerAction action =  null;

        Token name=null;
        TypeExpression structure = null;

        NumberExpression index = null;

        StringExpression fname = null;

        NumberExpression obj1 = null;

        NumberListExpression obj2 = null;



        	Map<StringExpression, TextMarkerExpression> map = new HashMap<StringExpression, TextMarkerExpression>();
            	List<NumberExpression> indexes = new ArrayList<NumberExpression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1041:5: (name= GATHER LPAREN structure= typeExpression ( COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression | obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression | obj2= numberListExpression ) )* )? )? RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1042:5: name= GATHER LPAREN structure= typeExpression ( COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression | obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression | obj2= numberListExpression ) )* )? )? RPAREN
            {
            name=(Token)match(input,GATHER,FOLLOW_GATHER_in_actionGather5936); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionGather5938); if (state.failed) return action;
            pushFollow(FOLLOW_typeExpression_in_actionGather5944);
            structure=typeExpression();

            state._fsp--;
            if (state.failed) return action;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1043:4: ( COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression | obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression | obj2= numberListExpression ) )* )? )?
            int alt119=2;
            int LA119_0 = input.LA(1);

            if ( (LA119_0==COMMA) ) {
                alt119=1;
            }
            switch (alt119) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1043:5: COMMA ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )? (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression | obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression | obj2= numberListExpression ) )* )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionGather5951); if (state.failed) return action;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1044:5: ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )?
                    int alt114=2;
                    alt114 = dfa114.predict(input);
                    switch (alt114) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1044:6: (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA
                            {
                            pushFollow(FOLLOW_numberExpression_in_actionGather5971);
                            index=numberExpression();

                            state._fsp--;
                            if (state.failed) return action;
                            if ( state.backtracking==0 ) {
                              indexes.add(index);
                            }
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1044:81: ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )*
                            loop113:
                            do {
                                int alt113=2;
                                int LA113_0 = input.LA(1);

                                if ( (LA113_0==COMMA) ) {
                                    int LA113_1 = input.LA(2);

                                    if ( (synpred18_TextMarkerParser()) ) {
                                        alt113=1;
                                    }


                                }


                                switch (alt113) {
                            	case 1 :
                            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1044:82: ( COMMA index= numberExpression )=> ( COMMA index= numberExpression )
                            	    {
                            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1044:116: ( COMMA index= numberExpression )
                            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1044:117: COMMA index= numberExpression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_actionGather5987); if (state.failed) return action;
                            	    pushFollow(FOLLOW_numberExpression_in_actionGather5993);
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

                            match(input,COMMA,FOLLOW_COMMA_in_actionGather6000); if (state.failed) return action;

                            }
                            break;

                    }

                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1045:5: (fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression | obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression | obj2= numberListExpression ) )* )?
                    int alt118=2;
                    int LA118_0 = input.LA(1);

                    if ( (LA118_0==REMOVESTRING||LA118_0==StringLiteral||LA118_0==Identifier) ) {
                        alt118=1;
                    }
                    switch (alt118) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1045:6: fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression | obj2= numberListExpression ) ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression | obj2= numberListExpression ) )*
                            {
                            pushFollow(FOLLOW_stringExpression_in_actionGather6013);
                            fname=stringExpression();

                            state._fsp--;
                            if (state.failed) return action;
                            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionGather6015); if (state.failed) return action;
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1045:44: (obj1= numberExpression | obj2= numberListExpression )
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

                                if ( (!((((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLELIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INTLIST")))))) ) {
                                    alt115=1;
                                }
                                else if ( (((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLELIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INTLIST")))) ) {
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
                                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1045:45: obj1= numberExpression
                                    {
                                    pushFollow(FOLLOW_numberExpression_in_actionGather6022);
                                    obj1=numberExpression();

                                    state._fsp--;
                                    if (state.failed) return action;

                                    }
                                    break;
                                case 2 :
                                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1045:71: obj2= numberListExpression
                                    {
                                    pushFollow(FOLLOW_numberListExpression_in_actionGather6030);
                                    obj2=numberListExpression();

                                    state._fsp--;
                                    if (state.failed) return action;

                                    }
                                    break;

                            }

                            if ( state.backtracking==0 ) {
                              map.put(fname,obj1 != null? obj1 : obj2);
                            }
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1046:5: ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression | obj2= numberListExpression ) )*
                            loop117:
                            do {
                                int alt117=2;
                                int LA117_0 = input.LA(1);

                                if ( (LA117_0==COMMA) ) {
                                    alt117=1;
                                }


                                switch (alt117) {
                            	case 1 :
                            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1046:6: COMMA fname= stringExpression ASSIGN_EQUAL (obj1= numberExpression | obj2= numberListExpression )
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_actionGather6041); if (state.failed) return action;
                            	    pushFollow(FOLLOW_stringExpression_in_actionGather6047);
                            	    fname=stringExpression();

                            	    state._fsp--;
                            	    if (state.failed) return action;
                            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionGather6049); if (state.failed) return action;
                            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1046:50: (obj1= numberExpression | obj2= numberListExpression )
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

                            	        if ( (!((((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLELIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INTLIST")))))) ) {
                            	            alt116=1;
                            	        }
                            	        else if ( (((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLELIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INTLIST")))) ) {
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
                            	            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1046:51: obj1= numberExpression
                            	            {
                            	            pushFollow(FOLLOW_numberExpression_in_actionGather6056);
                            	            obj1=numberExpression();

                            	            state._fsp--;
                            	            if (state.failed) return action;

                            	            }
                            	            break;
                            	        case 2 :
                            	            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1046:77: obj2= numberListExpression
                            	            {
                            	            pushFollow(FOLLOW_numberListExpression_in_actionGather6064);
                            	            obj2=numberListExpression();

                            	            state._fsp--;
                            	            if (state.failed) return action;

                            	            }
                            	            break;

                            	    }

                            	    if ( state.backtracking==0 ) {
                            	      map.put(fname,obj1 != null? obj1 : obj2);
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

            match(input,RPAREN,FOLLOW_RPAREN_in_actionGather6080); if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createGatherAction(structure, map, indexes, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return action;
    }
    // $ANTLR end "actionGather"


    // $ANTLR start "actionFill"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1053:1: actionFill returns [AbstractTextMarkerAction action = null] : FILL LPAREN type= typeExpression ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= argument ) )+ RPAREN ;
    public final AbstractTextMarkerAction actionFill() throws RecognitionException {
        AbstractTextMarkerAction action =  null;

        TypeExpression type = null;

        StringExpression fname = null;

        TextMarkerExpression obj1 = null;



        Map<StringExpression, TextMarkerExpression> map = new HashMap<StringExpression, TextMarkerExpression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1057:5: ( FILL LPAREN type= typeExpression ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= argument ) )+ RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1058:5: FILL LPAREN type= typeExpression ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= argument ) )+ RPAREN
            {
            match(input,FILL,FOLLOW_FILL_in_actionFill6122); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionFill6124); if (state.failed) return action;
            pushFollow(FOLLOW_typeExpression_in_actionFill6130);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1058:39: ( COMMA fname= stringExpression ASSIGN_EQUAL (obj1= argument ) )+
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
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1058:40: COMMA fname= stringExpression ASSIGN_EQUAL (obj1= argument )
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionFill6133); if (state.failed) return action;
            	    pushFollow(FOLLOW_stringExpression_in_actionFill6139);
            	    fname=stringExpression();

            	    state._fsp--;
            	    if (state.failed) return action;
            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionFill6141); if (state.failed) return action;
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1059:5: (obj1= argument )
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1060:5: obj1= argument
            	    {
            	    pushFollow(FOLLOW_argument_in_actionFill6158);
            	    obj1=argument();

            	    state._fsp--;
            	    if (state.failed) return action;
            	    if ( state.backtracking==0 ) {
            	      map.put(fname,obj1);
            	    }

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

            match(input,RPAREN,FOLLOW_RPAREN_in_actionFill6175); if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createFillAction(type, map, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return action;
    }
    // $ANTLR end "actionFill"


    // $ANTLR start "actionColor"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1066:1: actionColor returns [AbstractTextMarkerAction action = null] : COLOR LPAREN type= typeExpression COMMA bgcolor= stringExpression ( COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )? )? RPAREN ;
    public final AbstractTextMarkerAction actionColor() throws RecognitionException {
        AbstractTextMarkerAction action =  null;

        TypeExpression type = null;

        StringExpression bgcolor = null;

        StringExpression fgcolor = null;

        BooleanExpression selected = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1067:5: ( COLOR LPAREN type= typeExpression COMMA bgcolor= stringExpression ( COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )? )? RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1068:5: COLOR LPAREN type= typeExpression COMMA bgcolor= stringExpression ( COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )? )? RPAREN
            {
            match(input,COLOR,FOLLOW_COLOR_in_actionColor6213); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionColor6215); if (state.failed) return action;
            pushFollow(FOLLOW_typeExpression_in_actionColor6221);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;
            match(input,COMMA,FOLLOW_COMMA_in_actionColor6233); if (state.failed) return action;
            pushFollow(FOLLOW_stringExpression_in_actionColor6244);
            bgcolor=stringExpression();

            state._fsp--;
            if (state.failed) return action;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1072:5: ( COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )? )?
            int alt122=2;
            int LA122_0 = input.LA(1);

            if ( (LA122_0==COMMA) ) {
                alt122=1;
            }
            switch (alt122) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1072:6: COMMA fgcolor= stringExpression ( COMMA selected= booleanExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionColor6252); if (state.failed) return action;
                    pushFollow(FOLLOW_stringExpression_in_actionColor6262);
                    fgcolor=stringExpression();

                    state._fsp--;
                    if (state.failed) return action;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1074:5: ( COMMA selected= booleanExpression )?
                    int alt121=2;
                    int LA121_0 = input.LA(1);

                    if ( (LA121_0==COMMA) ) {
                        alt121=1;
                    }
                    switch (alt121) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1074:6: COMMA selected= booleanExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_actionColor6270); if (state.failed) return action;
                            pushFollow(FOLLOW_booleanExpression_in_actionColor6280);
                            selected=booleanExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            }
                            break;

                    }


                    }
                    break;

            }

            match(input,RPAREN,FOLLOW_RPAREN_in_actionColor6290); if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createColorAction(type, bgcolor, fgcolor, selected, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return action;
    }
    // $ANTLR end "actionColor"


    // $ANTLR start "actionDel"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1080:1: actionDel returns [AbstractTextMarkerAction action = null] : DEL ;
    public final AbstractTextMarkerAction actionDel() throws RecognitionException {
        AbstractTextMarkerAction action =  null;

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1081:5: ( DEL )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1082:5: DEL
            {
            match(input,DEL,FOLLOW_DEL_in_actionDel6324); if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createDelAction(((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return action;
    }
    // $ANTLR end "actionDel"


    // $ANTLR start "actionLog"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1086:1: actionLog returns [AbstractTextMarkerAction action = null] : LOG LPAREN lit= stringExpression ( COMMA log= LogLevel )? RPAREN ;
    public final AbstractTextMarkerAction actionLog() throws RecognitionException {
        AbstractTextMarkerAction action =  null;

        Token log=null;
        StringExpression lit = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1087:5: ( LOG LPAREN lit= stringExpression ( COMMA log= LogLevel )? RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1088:5: LOG LPAREN lit= stringExpression ( COMMA log= LogLevel )? RPAREN
            {
            match(input,LOG,FOLLOW_LOG_in_actionLog6366); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionLog6368); if (state.failed) return action;
            pushFollow(FOLLOW_stringExpression_in_actionLog6374);
            lit=stringExpression();

            state._fsp--;
            if (state.failed) return action;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1088:39: ( COMMA log= LogLevel )?
            int alt123=2;
            int LA123_0 = input.LA(1);

            if ( (LA123_0==COMMA) ) {
                alt123=1;
            }
            switch (alt123) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1088:40: COMMA log= LogLevel
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionLog6377); if (state.failed) return action;
                    log=(Token)match(input,LogLevel,FOLLOW_LogLevel_in_actionLog6383); if (state.failed) return action;

                    }
                    break;

            }

            match(input,RPAREN,FOLLOW_RPAREN_in_actionLog6387); if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createLogAction(lit, log, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return action;
    }
    // $ANTLR end "actionLog"


    // $ANTLR start "actionMark"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1092:1: actionMark returns [AbstractTextMarkerAction action = null] : MARK LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN ;
    public final AbstractTextMarkerAction actionMark() throws RecognitionException {
        AbstractTextMarkerAction action =  null;

        TypeExpression type = null;

        NumberExpression index = null;



        List<NumberExpression> list = new ArrayList<NumberExpression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1096:5: ( MARK LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1097:5: MARK LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN
            {
            match(input,MARK,FOLLOW_MARK_in_actionMark6426); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionMark6428); if (state.failed) return action;
            pushFollow(FOLLOW_typeExpression_in_actionMark6439);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1099:5: ( COMMA (index= numberExpression )=>index= numberExpression )*
            loop124:
            do {
                int alt124=2;
                int LA124_0 = input.LA(1);

                if ( (LA124_0==COMMA) ) {
                    alt124=1;
                }


                switch (alt124) {
            	case 1 :
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1100:9: COMMA (index= numberExpression )=>index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMark6455); if (state.failed) return action;
            	    pushFollow(FOLLOW_numberExpression_in_actionMark6471);
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

            match(input,RPAREN,FOLLOW_RPAREN_in_actionMark6495); if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createMarkAction(null, type, list, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return action;
    }
    // $ANTLR end "actionMark"


    // $ANTLR start "actionExpand"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1108:1: actionExpand returns [AbstractTextMarkerAction action = null] : EXPAND LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN ;
    public final AbstractTextMarkerAction actionExpand() throws RecognitionException {
        AbstractTextMarkerAction action =  null;

        TypeExpression type = null;

        NumberExpression index = null;



        List<NumberExpression> list = new ArrayList<NumberExpression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1112:5: ( EXPAND LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1113:5: EXPAND LPAREN type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN
            {
            match(input,EXPAND,FOLLOW_EXPAND_in_actionExpand6539); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionExpand6541); if (state.failed) return action;
            pushFollow(FOLLOW_typeExpression_in_actionExpand6552);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1115:5: ( COMMA (index= numberExpression )=>index= numberExpression )*
            loop125:
            do {
                int alt125=2;
                int LA125_0 = input.LA(1);

                if ( (LA125_0==COMMA) ) {
                    alt125=1;
                }


                switch (alt125) {
            	case 1 :
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1116:9: COMMA (index= numberExpression )=>index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionExpand6568); if (state.failed) return action;
            	    pushFollow(FOLLOW_numberExpression_in_actionExpand6584);
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

            match(input,RPAREN,FOLLOW_RPAREN_in_actionExpand6608); if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createExpandAction(type, list, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return action;
    }
    // $ANTLR end "actionExpand"


    // $ANTLR start "actionMarkScore"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1125:1: actionMarkScore returns [AbstractTextMarkerAction action = null] : MARKSCORE LPAREN score= numberExpression COMMA type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN ;
    public final AbstractTextMarkerAction actionMarkScore() throws RecognitionException {
        AbstractTextMarkerAction action =  null;

        NumberExpression score = null;

        TypeExpression type = null;

        NumberExpression index = null;



        List<NumberExpression> list = new ArrayList<NumberExpression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1129:5: ( MARKSCORE LPAREN score= numberExpression COMMA type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1130:5: MARKSCORE LPAREN score= numberExpression COMMA type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN
            {
            match(input,MARKSCORE,FOLLOW_MARKSCORE_in_actionMarkScore6653); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkScore6655); if (state.failed) return action;
            pushFollow(FOLLOW_numberExpression_in_actionMarkScore6666);
            score=numberExpression();

            state._fsp--;
            if (state.failed) return action;
            match(input,COMMA,FOLLOW_COMMA_in_actionMarkScore6668); if (state.failed) return action;
            pushFollow(FOLLOW_typeExpression_in_actionMarkScore6678);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1133:5: ( COMMA (index= numberExpression )=>index= numberExpression )*
            loop126:
            do {
                int alt126=2;
                int LA126_0 = input.LA(1);

                if ( (LA126_0==COMMA) ) {
                    alt126=1;
                }


                switch (alt126) {
            	case 1 :
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1134:9: COMMA (index= numberExpression )=>index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMarkScore6694); if (state.failed) return action;
            	    pushFollow(FOLLOW_numberExpression_in_actionMarkScore6710);
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

            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkScore6734); if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createMarkAction(score, type, list, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return action;
    }
    // $ANTLR end "actionMarkScore"


    // $ANTLR start "actionMarkOnce"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1142:1: actionMarkOnce returns [AbstractTextMarkerAction action = null] : MARKONCE LPAREN ( (score= numberExpression )=>score= numberExpression COMMA )? (type= typeExpression )=>type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN ;
    public final AbstractTextMarkerAction actionMarkOnce() throws RecognitionException {
        AbstractTextMarkerAction action =  null;

        NumberExpression score = null;

        TypeExpression type = null;

        NumberExpression index = null;



        List<NumberExpression> list = new ArrayList<NumberExpression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1146:5: ( MARKONCE LPAREN ( (score= numberExpression )=>score= numberExpression COMMA )? (type= typeExpression )=>type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1147:5: MARKONCE LPAREN ( (score= numberExpression )=>score= numberExpression COMMA )? (type= typeExpression )=>type= typeExpression ( COMMA (index= numberExpression )=>index= numberExpression )* RPAREN
            {
            match(input,MARKONCE,FOLLOW_MARKONCE_in_actionMarkOnce6778); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkOnce6780); if (state.failed) return action;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1147:21: ( (score= numberExpression )=>score= numberExpression COMMA )?
            int alt127=2;
            int LA127_0 = input.LA(1);

            if ( (LA127_0==MINUS) && (synpred22_TextMarkerParser())) {
                alt127=1;
            }
            else if ( (LA127_0==DecimalLiteral) && (synpred22_TextMarkerParser())) {
                alt127=1;
            }
            else if ( (LA127_0==FloatingPointLiteral) && (synpred22_TextMarkerParser())) {
                alt127=1;
            }
            else if ( (LA127_0==Identifier) ) {
                int LA127_4 = input.LA(2);

                if ( (((synpred22_TextMarkerParser()&&(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INT")))||(synpred22_TextMarkerParser()&&(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLE")))||synpred22_TextMarkerParser())) ) {
                    alt127=1;
                }
            }
            else if ( (LA127_0==LPAREN) && (synpred22_TextMarkerParser())) {
                alt127=1;
            }
            else if ( ((LA127_0>=EXP && LA127_0<=TAN)) && (synpred22_TextMarkerParser())) {
                alt127=1;
            }
            switch (alt127) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1147:22: (score= numberExpression )=>score= numberExpression COMMA
                    {
                    pushFollow(FOLLOW_numberExpression_in_actionMarkOnce6797);
                    score=numberExpression();

                    state._fsp--;
                    if (state.failed) return action;
                    match(input,COMMA,FOLLOW_COMMA_in_actionMarkOnce6799); if (state.failed) return action;

                    }
                    break;

            }

            pushFollow(FOLLOW_typeExpression_in_actionMarkOnce6817);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1148:5: ( COMMA (index= numberExpression )=>index= numberExpression )*
            loop128:
            do {
                int alt128=2;
                int LA128_0 = input.LA(1);

                if ( (LA128_0==COMMA) ) {
                    alt128=1;
                }


                switch (alt128) {
            	case 1 :
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1149:9: COMMA (index= numberExpression )=>index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMarkOnce6833); if (state.failed) return action;
            	    pushFollow(FOLLOW_numberExpression_in_actionMarkOnce6849);
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

            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkOnce6868); if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createMarkOnceAction(score, type, list, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return action;
    }
    // $ANTLR end "actionMarkOnce"


    // $ANTLR start "actionMarkFast"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1156:1: actionMarkFast returns [AbstractTextMarkerAction action = null] : MARKFAST LPAREN type= typeExpression COMMA list= wordListExpression ( COMMA ignore= booleanExpression ( COMMA ignoreLength= numberExpression )? )? RPAREN ;
    public final AbstractTextMarkerAction actionMarkFast() throws RecognitionException {
        AbstractTextMarkerAction action =  null;

        TypeExpression type = null;

        WordListExpression list = null;

        BooleanExpression ignore = null;

        NumberExpression ignoreLength = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1157:5: ( MARKFAST LPAREN type= typeExpression COMMA list= wordListExpression ( COMMA ignore= booleanExpression ( COMMA ignoreLength= numberExpression )? )? RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1158:5: MARKFAST LPAREN type= typeExpression COMMA list= wordListExpression ( COMMA ignore= booleanExpression ( COMMA ignoreLength= numberExpression )? )? RPAREN
            {
            match(input,MARKFAST,FOLLOW_MARKFAST_in_actionMarkFast6907); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkFast6909); if (state.failed) return action;
            pushFollow(FOLLOW_typeExpression_in_actionMarkFast6915);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;
            match(input,COMMA,FOLLOW_COMMA_in_actionMarkFast6917); if (state.failed) return action;
            pushFollow(FOLLOW_wordListExpression_in_actionMarkFast6923);
            list=wordListExpression();

            state._fsp--;
            if (state.failed) return action;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1158:75: ( COMMA ignore= booleanExpression ( COMMA ignoreLength= numberExpression )? )?
            int alt130=2;
            int LA130_0 = input.LA(1);

            if ( (LA130_0==COMMA) ) {
                alt130=1;
            }
            switch (alt130) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1158:76: COMMA ignore= booleanExpression ( COMMA ignoreLength= numberExpression )?
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionMarkFast6926); if (state.failed) return action;
                    pushFollow(FOLLOW_booleanExpression_in_actionMarkFast6932);
                    ignore=booleanExpression();

                    state._fsp--;
                    if (state.failed) return action;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1158:109: ( COMMA ignoreLength= numberExpression )?
                    int alt129=2;
                    int LA129_0 = input.LA(1);

                    if ( (LA129_0==COMMA) ) {
                        alt129=1;
                    }
                    switch (alt129) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1158:110: COMMA ignoreLength= numberExpression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_actionMarkFast6935); if (state.failed) return action;
                            pushFollow(FOLLOW_numberExpression_in_actionMarkFast6941);
                            ignoreLength=numberExpression();

                            state._fsp--;
                            if (state.failed) return action;

                            }
                            break;

                    }


                    }
                    break;

            }

            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkFast6947); if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createMarkFastAction(type, list, ignore, ignoreLength, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return action;
    }
    // $ANTLR end "actionMarkFast"


    // $ANTLR start "actionMarkLast"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1162:1: actionMarkLast returns [AbstractTextMarkerAction action = null] : MARKLAST LPAREN type= typeExpression RPAREN ;
    public final AbstractTextMarkerAction actionMarkLast() throws RecognitionException {
        AbstractTextMarkerAction action =  null;

        TypeExpression type = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1163:5: ( MARKLAST LPAREN type= typeExpression RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1164:5: MARKLAST LPAREN type= typeExpression RPAREN
            {
            match(input,MARKLAST,FOLLOW_MARKLAST_in_actionMarkLast6981); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionMarkLast6983); if (state.failed) return action;
            pushFollow(FOLLOW_typeExpression_in_actionMarkLast6989);
            type=typeExpression();

            state._fsp--;
            if (state.failed) return action;
            match(input,RPAREN,FOLLOW_RPAREN_in_actionMarkLast6991); if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createMarkLastAction(type, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return action;
    }
    // $ANTLR end "actionMarkLast"


    // $ANTLR start "actionReplace"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1168:1: actionReplace returns [AbstractTextMarkerAction action = null] : REPLACE LPAREN lit= stringExpression RPAREN ;
    public final AbstractTextMarkerAction actionReplace() throws RecognitionException {
        AbstractTextMarkerAction action =  null;

        StringExpression lit = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1169:5: ( REPLACE LPAREN lit= stringExpression RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1170:5: REPLACE LPAREN lit= stringExpression RPAREN
            {
            match(input,REPLACE,FOLLOW_REPLACE_in_actionReplace7025); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionReplace7027); if (state.failed) return action;
            pushFollow(FOLLOW_stringExpression_in_actionReplace7033);
            lit=stringExpression();

            state._fsp--;
            if (state.failed) return action;
            match(input,RPAREN,FOLLOW_RPAREN_in_actionReplace7035); if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createReplaceAction(lit, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return action;
    }
    // $ANTLR end "actionReplace"


    // $ANTLR start "actionRetainMarkup"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1174:1: actionRetainMarkup returns [AbstractTextMarkerAction action = null] : RETAINMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )? ;
    public final AbstractTextMarkerAction actionRetainMarkup() throws RecognitionException {
        AbstractTextMarkerAction action =  null;

        StringExpression id = null;



        List<StringExpression> list = new ArrayList<StringExpression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1178:5: ( RETAINMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )? )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1179:5: RETAINMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )?
            {
            match(input,RETAINMARKUP,FOLLOW_RETAINMARKUP_in_actionRetainMarkup7078); if (state.failed) return action;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1179:18: ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )?
            int alt132=2;
            int LA132_0 = input.LA(1);

            if ( (LA132_0==LPAREN) ) {
                alt132=1;
            }
            switch (alt132) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1179:19: LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_actionRetainMarkup7081); if (state.failed) return action;
                    pushFollow(FOLLOW_stringExpression_in_actionRetainMarkup7087);
                    id=stringExpression();

                    state._fsp--;
                    if (state.failed) return action;
                    if ( state.backtracking==0 ) {
                      list.add(id);
                    }
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1179:64: ( COMMA id= stringExpression )*
                    loop131:
                    do {
                        int alt131=2;
                        int LA131_0 = input.LA(1);

                        if ( (LA131_0==COMMA) ) {
                            alt131=1;
                        }


                        switch (alt131) {
                    	case 1 :
                    	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1179:65: COMMA id= stringExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionRetainMarkup7092); if (state.failed) return action;
                    	    pushFollow(FOLLOW_stringExpression_in_actionRetainMarkup7098);
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

                    match(input,RPAREN,FOLLOW_RPAREN_in_actionRetainMarkup7104); if (state.failed) return action;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              action = ActionFactory.createRetainMarkupAction(list, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return action;
    }
    // $ANTLR end "actionRetainMarkup"


    // $ANTLR start "actionRetainType"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1184:1: actionRetainType returns [AbstractTextMarkerAction action = null] : RETAINTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )? ;
    public final AbstractTextMarkerAction actionRetainType() throws RecognitionException {
        AbstractTextMarkerAction action =  null;

        TypeExpression id = null;



        List<TypeExpression> list = new ArrayList<TypeExpression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1188:5: ( RETAINTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )? )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1189:5: RETAINTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )?
            {
            match(input,RETAINTYPE,FOLLOW_RETAINTYPE_in_actionRetainType7150); if (state.failed) return action;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1189:16: ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )?
            int alt134=2;
            int LA134_0 = input.LA(1);

            if ( (LA134_0==LPAREN) ) {
                alt134=1;
            }
            switch (alt134) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1189:17: LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_actionRetainType7153); if (state.failed) return action;
                    pushFollow(FOLLOW_typeExpression_in_actionRetainType7159);
                    id=typeExpression();

                    state._fsp--;
                    if (state.failed) return action;
                    if ( state.backtracking==0 ) {
                      list.add(id);
                    }
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1189:60: ( COMMA id= typeExpression )*
                    loop133:
                    do {
                        int alt133=2;
                        int LA133_0 = input.LA(1);

                        if ( (LA133_0==COMMA) ) {
                            alt133=1;
                        }


                        switch (alt133) {
                    	case 1 :
                    	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1189:61: COMMA id= typeExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionRetainType7164); if (state.failed) return action;
                    	    pushFollow(FOLLOW_typeExpression_in_actionRetainType7170);
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

                    match(input,RPAREN,FOLLOW_RPAREN_in_actionRetainType7176); if (state.failed) return action;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              action = ActionFactory.createRetainTypeAction(list, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return action;
    }
    // $ANTLR end "actionRetainType"


    // $ANTLR start "actionFilterMarkup"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1193:1: actionFilterMarkup returns [AbstractTextMarkerAction action = null] : FILTERMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )? ;
    public final AbstractTextMarkerAction actionFilterMarkup() throws RecognitionException {
        AbstractTextMarkerAction action =  null;

        StringExpression id = null;



        List<StringExpression> list = new ArrayList<StringExpression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1197:5: ( FILTERMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )? )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1198:5: FILTERMARKUP ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )?
            {
            match(input,FILTERMARKUP,FOLLOW_FILTERMARKUP_in_actionFilterMarkup7224); if (state.failed) return action;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1198:18: ( LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN )?
            int alt136=2;
            int LA136_0 = input.LA(1);

            if ( (LA136_0==LPAREN) ) {
                alt136=1;
            }
            switch (alt136) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1198:19: LPAREN id= stringExpression ( COMMA id= stringExpression )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_actionFilterMarkup7227); if (state.failed) return action;
                    pushFollow(FOLLOW_stringExpression_in_actionFilterMarkup7233);
                    id=stringExpression();

                    state._fsp--;
                    if (state.failed) return action;
                    if ( state.backtracking==0 ) {
                      list.add(id);
                    }
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1198:64: ( COMMA id= stringExpression )*
                    loop135:
                    do {
                        int alt135=2;
                        int LA135_0 = input.LA(1);

                        if ( (LA135_0==COMMA) ) {
                            alt135=1;
                        }


                        switch (alt135) {
                    	case 1 :
                    	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1198:65: COMMA id= stringExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionFilterMarkup7238); if (state.failed) return action;
                    	    pushFollow(FOLLOW_stringExpression_in_actionFilterMarkup7244);
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

                    match(input,RPAREN,FOLLOW_RPAREN_in_actionFilterMarkup7250); if (state.failed) return action;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              action = ActionFactory.createFilterMarkupAction(list, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return action;
    }
    // $ANTLR end "actionFilterMarkup"


    // $ANTLR start "actionFilterType"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1203:1: actionFilterType returns [AbstractTextMarkerAction action = null] : FILTERTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )? ;
    public final AbstractTextMarkerAction actionFilterType() throws RecognitionException {
        AbstractTextMarkerAction action =  null;

        TypeExpression id = null;



        List<TypeExpression> list = new ArrayList<TypeExpression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1207:5: ( FILTERTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )? )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1208:5: FILTERTYPE ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )?
            {
            match(input,FILTERTYPE,FOLLOW_FILTERTYPE_in_actionFilterType7296); if (state.failed) return action;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1208:16: ( LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN )?
            int alt138=2;
            int LA138_0 = input.LA(1);

            if ( (LA138_0==LPAREN) ) {
                alt138=1;
            }
            switch (alt138) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1208:17: LPAREN id= typeExpression ( COMMA id= typeExpression )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_actionFilterType7299); if (state.failed) return action;
                    pushFollow(FOLLOW_typeExpression_in_actionFilterType7305);
                    id=typeExpression();

                    state._fsp--;
                    if (state.failed) return action;
                    if ( state.backtracking==0 ) {
                      list.add(id);
                    }
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1208:60: ( COMMA id= typeExpression )*
                    loop137:
                    do {
                        int alt137=2;
                        int LA137_0 = input.LA(1);

                        if ( (LA137_0==COMMA) ) {
                            alt137=1;
                        }


                        switch (alt137) {
                    	case 1 :
                    	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1208:61: COMMA id= typeExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_actionFilterType7310); if (state.failed) return action;
                    	    pushFollow(FOLLOW_typeExpression_in_actionFilterType7316);
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

                    match(input,RPAREN,FOLLOW_RPAREN_in_actionFilterType7322); if (state.failed) return action;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              action = ActionFactory.createFilterTypeAction(list, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return action;
    }
    // $ANTLR end "actionFilterType"


    // $ANTLR start "actionCall"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1212:1: actionCall returns [AbstractTextMarkerAction action = null] : CALL LPAREN ns= dottedIdentifier RPAREN ;
    public final AbstractTextMarkerAction actionCall() throws RecognitionException {
        AbstractTextMarkerAction action =  null;

        String ns = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1213:5: ( CALL LPAREN ns= dottedIdentifier RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1214:5: CALL LPAREN ns= dottedIdentifier RPAREN
            {
            match(input,CALL,FOLLOW_CALL_in_actionCall7362); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionCall7364); if (state.failed) return action;
            pushFollow(FOLLOW_dottedIdentifier_in_actionCall7370);
            ns=dottedIdentifier();

            state._fsp--;
            if (state.failed) return action;
            match(input,RPAREN,FOLLOW_RPAREN_in_actionCall7372); if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createCallAction(ns, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return action;
    }
    // $ANTLR end "actionCall"


    // $ANTLR start "actionExec"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1218:1: actionExec returns [AbstractTextMarkerAction action = null] : EXEC LPAREN ns= dottedIdentifier ( COMMA tl= typeListExpression )? RPAREN ;
    public final AbstractTextMarkerAction actionExec() throws RecognitionException {
        AbstractTextMarkerAction action =  null;

        String ns = null;

        TypeListExpression tl = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1219:5: ( EXEC LPAREN ns= dottedIdentifier ( COMMA tl= typeListExpression )? RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1220:5: EXEC LPAREN ns= dottedIdentifier ( COMMA tl= typeListExpression )? RPAREN
            {
            match(input,EXEC,FOLLOW_EXEC_in_actionExec7403); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionExec7405); if (state.failed) return action;
            pushFollow(FOLLOW_dottedIdentifier_in_actionExec7411);
            ns=dottedIdentifier();

            state._fsp--;
            if (state.failed) return action;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1220:39: ( COMMA tl= typeListExpression )?
            int alt139=2;
            int LA139_0 = input.LA(1);

            if ( (LA139_0==COMMA) ) {
                alt139=1;
            }
            switch (alt139) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1220:40: COMMA tl= typeListExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionExec7414); if (state.failed) return action;
                    pushFollow(FOLLOW_typeListExpression_in_actionExec7420);
                    tl=typeListExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    }
                    break;

            }

            match(input,RPAREN,FOLLOW_RPAREN_in_actionExec7424); if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createExecAction(ns, tl, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return action;
    }
    // $ANTLR end "actionExec"


    // $ANTLR start "actionAssign"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1224:1: actionAssign returns [AbstractTextMarkerAction action = null] : name= ASSIGN LPAREN ({...}?nv= Identifier COMMA e1= typeExpression | {...}?nv= Identifier COMMA e2= booleanExpression | {...}?nv= Identifier COMMA e3= stringExpression | {...}?nv= Identifier COMMA e4= numberExpression | {...}?nv= Identifier COMMA e5= numberExpression ) RPAREN ;
    public final AbstractTextMarkerAction actionAssign() throws RecognitionException {
        AbstractTextMarkerAction action =  null;

        Token name=null;
        Token nv=null;
        TypeExpression e1 = null;

        BooleanExpression e2 = null;

        StringExpression e3 = null;

        NumberExpression e4 = null;

        NumberExpression e5 = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1225:5: (name= ASSIGN LPAREN ({...}?nv= Identifier COMMA e1= typeExpression | {...}?nv= Identifier COMMA e2= booleanExpression | {...}?nv= Identifier COMMA e3= stringExpression | {...}?nv= Identifier COMMA e4= numberExpression | {...}?nv= Identifier COMMA e5= numberExpression ) RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1226:5: name= ASSIGN LPAREN ({...}?nv= Identifier COMMA e1= typeExpression | {...}?nv= Identifier COMMA e2= booleanExpression | {...}?nv= Identifier COMMA e3= stringExpression | {...}?nv= Identifier COMMA e4= numberExpression | {...}?nv= Identifier COMMA e5= numberExpression ) RPAREN
            {
            name=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_actionAssign7467); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionAssign7469); if (state.failed) return action;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1227:5: ({...}?nv= Identifier COMMA e1= typeExpression | {...}?nv= Identifier COMMA e2= booleanExpression | {...}?nv= Identifier COMMA e3= stringExpression | {...}?nv= Identifier COMMA e4= numberExpression | {...}?nv= Identifier COMMA e5= numberExpression )
            int alt140=5;
            int LA140_0 = input.LA(1);

            if ( (LA140_0==Identifier) ) {
                int LA140_1 = input.LA(2);

                if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "TYPE"))) ) {
                    alt140=1;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "BOOLEAN"))) ) {
                    alt140=2;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "STRING"))) ) {
                    alt140=3;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "INT"))) ) {
                    alt140=4;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "DOUBLE"))) ) {
                    alt140=5;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return action;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 140, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return action;}
                NoViableAltException nvae =
                    new NoViableAltException("", 140, 0, input);

                throw nvae;
            }
            switch (alt140) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1228:5: {...}?nv= Identifier COMMA e1= typeExpression
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "TYPE"))) ) {
                        if (state.backtracking>0) {state.failed=true; return action;}
                        throw new FailedPredicateException(input, "actionAssign", "isVariableOfType($blockDeclaration::env, input.LT(1).getText(), \"TYPE\")");
                    }
                    nv=(Token)match(input,Identifier,FOLLOW_Identifier_in_actionAssign7496); if (state.failed) return action;
                    match(input,COMMA,FOLLOW_COMMA_in_actionAssign7498); if (state.failed) return action;
                    pushFollow(FOLLOW_typeExpression_in_actionAssign7504);
                    e1=typeExpression();

                    state._fsp--;
                    if (state.failed) return action;
                    if ( state.backtracking==0 ) {
                      action = ActionFactory.createAssignAction(nv, e1, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1232:5: {...}?nv= Identifier COMMA e2= booleanExpression
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "BOOLEAN"))) ) {
                        if (state.backtracking>0) {state.failed=true; return action;}
                        throw new FailedPredicateException(input, "actionAssign", "isVariableOfType($blockDeclaration::env, input.LT(1).getText(), \"BOOLEAN\")");
                    }
                    nv=(Token)match(input,Identifier,FOLLOW_Identifier_in_actionAssign7542); if (state.failed) return action;
                    match(input,COMMA,FOLLOW_COMMA_in_actionAssign7544); if (state.failed) return action;
                    pushFollow(FOLLOW_booleanExpression_in_actionAssign7550);
                    e2=booleanExpression();

                    state._fsp--;
                    if (state.failed) return action;
                    if ( state.backtracking==0 ) {
                      action = ActionFactory.createAssignAction(nv, e2, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
                    }

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1236:5: {...}?nv= Identifier COMMA e3= stringExpression
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "STRING"))) ) {
                        if (state.backtracking>0) {state.failed=true; return action;}
                        throw new FailedPredicateException(input, "actionAssign", "isVariableOfType($blockDeclaration::env, input.LT(1).getText(), \"STRING\")");
                    }
                    nv=(Token)match(input,Identifier,FOLLOW_Identifier_in_actionAssign7588); if (state.failed) return action;
                    match(input,COMMA,FOLLOW_COMMA_in_actionAssign7590); if (state.failed) return action;
                    pushFollow(FOLLOW_stringExpression_in_actionAssign7596);
                    e3=stringExpression();

                    state._fsp--;
                    if (state.failed) return action;
                    if ( state.backtracking==0 ) {
                      action = ActionFactory.createAssignAction(nv, e3, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
                    }

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1240:5: {...}?nv= Identifier COMMA e4= numberExpression
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "INT"))) ) {
                        if (state.backtracking>0) {state.failed=true; return action;}
                        throw new FailedPredicateException(input, "actionAssign", "isVariableOfType($blockDeclaration::env, input.LT(1).getText(), \"INT\")");
                    }
                    nv=(Token)match(input,Identifier,FOLLOW_Identifier_in_actionAssign7634); if (state.failed) return action;
                    match(input,COMMA,FOLLOW_COMMA_in_actionAssign7636); if (state.failed) return action;
                    pushFollow(FOLLOW_numberExpression_in_actionAssign7642);
                    e4=numberExpression();

                    state._fsp--;
                    if (state.failed) return action;
                    if ( state.backtracking==0 ) {
                      action = ActionFactory.createAssignAction(nv, e4, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
                    }

                    }
                    break;
                case 5 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1244:5: {...}?nv= Identifier COMMA e5= numberExpression
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env, input.LT(1).getText(), "DOUBLE"))) ) {
                        if (state.backtracking>0) {state.failed=true; return action;}
                        throw new FailedPredicateException(input, "actionAssign", "isVariableOfType($blockDeclaration::env, input.LT(1).getText(), \"DOUBLE\")");
                    }
                    nv=(Token)match(input,Identifier,FOLLOW_Identifier_in_actionAssign7680); if (state.failed) return action;
                    match(input,COMMA,FOLLOW_COMMA_in_actionAssign7682); if (state.failed) return action;
                    pushFollow(FOLLOW_numberExpression_in_actionAssign7688);
                    e5=numberExpression();

                    state._fsp--;
                    if (state.failed) return action;
                    if ( state.backtracking==0 ) {
                      action = ActionFactory.createAssignAction(nv, e5, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
                    }

                    }
                    break;

            }

            match(input,RPAREN,FOLLOW_RPAREN_in_actionAssign7707); if (state.failed) return action;

            }

        }

        	catch (RecognitionException exception1) {
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}
        finally {
        }
        return action;
    }
    // $ANTLR end "actionAssign"


    // $ANTLR start "actionSetFeature"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1250:1: actionSetFeature returns [AbstractTextMarkerAction action = null] : name= SETFEATURE LPAREN f= stringExpression COMMA v= argument RPAREN ;
    public final AbstractTextMarkerAction actionSetFeature() throws RecognitionException {
        AbstractTextMarkerAction action =  null;

        Token name=null;
        StringExpression f = null;

        TextMarkerExpression v = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1251:5: (name= SETFEATURE LPAREN f= stringExpression COMMA v= argument RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1252:5: name= SETFEATURE LPAREN f= stringExpression COMMA v= argument RPAREN
            {
            name=(Token)match(input,SETFEATURE,FOLLOW_SETFEATURE_in_actionSetFeature7739); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionSetFeature7741); if (state.failed) return action;
            pushFollow(FOLLOW_stringExpression_in_actionSetFeature7747);
            f=stringExpression();

            state._fsp--;
            if (state.failed) return action;
            match(input,COMMA,FOLLOW_COMMA_in_actionSetFeature7749); if (state.failed) return action;
            pushFollow(FOLLOW_argument_in_actionSetFeature7755);
            v=argument();

            state._fsp--;
            if (state.failed) return action;
            match(input,RPAREN,FOLLOW_RPAREN_in_actionSetFeature7757); if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createSetFeatureAction(f, v, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return action;
    }
    // $ANTLR end "actionSetFeature"


    // $ANTLR start "actionGetFeature"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1257:1: actionGetFeature returns [AbstractTextMarkerAction action = null] : name= GETFEATURE LPAREN f= stringExpression COMMA v= variable RPAREN ;
    public final AbstractTextMarkerAction actionGetFeature() throws RecognitionException {
        AbstractTextMarkerAction action =  null;

        Token name=null;
        StringExpression f = null;

        Token v = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1258:5: (name= GETFEATURE LPAREN f= stringExpression COMMA v= variable RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1259:5: name= GETFEATURE LPAREN f= stringExpression COMMA v= variable RPAREN
            {
            name=(Token)match(input,GETFEATURE,FOLLOW_GETFEATURE_in_actionGetFeature7796); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionGetFeature7798); if (state.failed) return action;
            pushFollow(FOLLOW_stringExpression_in_actionGetFeature7804);
            f=stringExpression();

            state._fsp--;
            if (state.failed) return action;
            match(input,COMMA,FOLLOW_COMMA_in_actionGetFeature7806); if (state.failed) return action;
            pushFollow(FOLLOW_variable_in_actionGetFeature7812);
            v=variable();

            state._fsp--;
            if (state.failed) return action;
            match(input,RPAREN,FOLLOW_RPAREN_in_actionGetFeature7814); if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createGetFeatureAction(f, v, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return action;
    }
    // $ANTLR end "actionGetFeature"


    // $ANTLR start "actionUnmark"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1264:1: actionUnmark returns [AbstractTextMarkerAction action = null] : name= UNMARK LPAREN f= typeExpression RPAREN ;
    public final AbstractTextMarkerAction actionUnmark() throws RecognitionException {
        AbstractTextMarkerAction action =  null;

        Token name=null;
        TypeExpression f = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1265:5: (name= UNMARK LPAREN f= typeExpression RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1266:5: name= UNMARK LPAREN f= typeExpression RPAREN
            {
            name=(Token)match(input,UNMARK,FOLLOW_UNMARK_in_actionUnmark7850); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionUnmark7852); if (state.failed) return action;
            pushFollow(FOLLOW_typeExpression_in_actionUnmark7858);
            f=typeExpression();

            state._fsp--;
            if (state.failed) return action;
            match(input,RPAREN,FOLLOW_RPAREN_in_actionUnmark7860); if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createUnmarkAction(f, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return action;
    }
    // $ANTLR end "actionUnmark"


    // $ANTLR start "actionUnmarkAll"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1271:1: actionUnmarkAll returns [AbstractTextMarkerAction action = null] : name= UNMARKALL LPAREN f= typeExpression ( COMMA list= typeListExpression )? RPAREN ;
    public final AbstractTextMarkerAction actionUnmarkAll() throws RecognitionException {
        AbstractTextMarkerAction action =  null;

        Token name=null;
        TypeExpression f = null;

        TypeListExpression list = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1272:5: (name= UNMARKALL LPAREN f= typeExpression ( COMMA list= typeListExpression )? RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1273:5: name= UNMARKALL LPAREN f= typeExpression ( COMMA list= typeListExpression )? RPAREN
            {
            name=(Token)match(input,UNMARKALL,FOLLOW_UNMARKALL_in_actionUnmarkAll7896); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionUnmarkAll7898); if (state.failed) return action;
            pushFollow(FOLLOW_typeExpression_in_actionUnmarkAll7904);
            f=typeExpression();

            state._fsp--;
            if (state.failed) return action;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1274:5: ( COMMA list= typeListExpression )?
            int alt141=2;
            int LA141_0 = input.LA(1);

            if ( (LA141_0==COMMA) ) {
                alt141=1;
            }
            switch (alt141) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1274:6: COMMA list= typeListExpression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_actionUnmarkAll7912); if (state.failed) return action;
                    pushFollow(FOLLOW_typeListExpression_in_actionUnmarkAll7918);
                    list=typeListExpression();

                    state._fsp--;
                    if (state.failed) return action;

                    }
                    break;

            }

            match(input,RPAREN,FOLLOW_RPAREN_in_actionUnmarkAll7922); if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createUnmarkAllAction(f, list, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return action;
    }
    // $ANTLR end "actionUnmarkAll"


    // $ANTLR start "actionTransfer"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1278:1: actionTransfer returns [AbstractTextMarkerAction action = null] : name= TRANSFER LPAREN f= typeExpression RPAREN ;
    public final AbstractTextMarkerAction actionTransfer() throws RecognitionException {
        AbstractTextMarkerAction action =  null;

        Token name=null;
        TypeExpression f = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1279:5: (name= TRANSFER LPAREN f= typeExpression RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1280:5: name= TRANSFER LPAREN f= typeExpression RPAREN
            {
            name=(Token)match(input,TRANSFER,FOLLOW_TRANSFER_in_actionTransfer7957); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionTransfer7959); if (state.failed) return action;
            pushFollow(FOLLOW_typeExpression_in_actionTransfer7965);
            f=typeExpression();

            state._fsp--;
            if (state.failed) return action;
            match(input,RPAREN,FOLLOW_RPAREN_in_actionTransfer7967); if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createTransferAction(f, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return action;
    }
    // $ANTLR end "actionTransfer"


    // $ANTLR start "actionTrie"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1284:1: actionTrie returns [AbstractTextMarkerAction action = null] : name= TRIE LPAREN key= stringExpression ASSIGN_EQUAL value= typeExpression ( COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression )* COMMA list= wordListExpression COMMA ignoreCase= booleanExpression COMMA ignoreLength= numberExpression COMMA edit= booleanExpression COMMA distance= numberExpression COMMA ignoreChar= stringExpression RPAREN ;
    public final AbstractTextMarkerAction actionTrie() throws RecognitionException {
        AbstractTextMarkerAction action =  null;

        Token name=null;
        StringExpression key = null;

        TypeExpression value = null;

        WordListExpression list = null;

        BooleanExpression ignoreCase = null;

        NumberExpression ignoreLength = null;

        BooleanExpression edit = null;

        NumberExpression distance = null;

        StringExpression ignoreChar = null;



        Map<StringExpression, TypeExpression> map = new HashMap<StringExpression, TypeExpression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1288:5: (name= TRIE LPAREN key= stringExpression ASSIGN_EQUAL value= typeExpression ( COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression )* COMMA list= wordListExpression COMMA ignoreCase= booleanExpression COMMA ignoreLength= numberExpression COMMA edit= booleanExpression COMMA distance= numberExpression COMMA ignoreChar= stringExpression RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1289:5: name= TRIE LPAREN key= stringExpression ASSIGN_EQUAL value= typeExpression ( COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression )* COMMA list= wordListExpression COMMA ignoreCase= booleanExpression COMMA ignoreLength= numberExpression COMMA edit= booleanExpression COMMA distance= numberExpression COMMA ignoreChar= stringExpression RPAREN
            {
            name=(Token)match(input,TRIE,FOLLOW_TRIE_in_actionTrie8007); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionTrie8009); if (state.failed) return action;
            pushFollow(FOLLOW_stringExpression_in_actionTrie8019);
            key=stringExpression();

            state._fsp--;
            if (state.failed) return action;
            match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionTrie8021); if (state.failed) return action;
            pushFollow(FOLLOW_typeExpression_in_actionTrie8027);
            value=typeExpression();

            state._fsp--;
            if (state.failed) return action;
            if ( state.backtracking==0 ) {
              map.put(key,value);
            }
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1291:5: ( COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression )*
            loop142:
            do {
                int alt142=2;
                int LA142_0 = input.LA(1);

                if ( (LA142_0==COMMA) ) {
                    int LA142_1 = input.LA(2);

                    if ( (LA142_1==REMOVESTRING||LA142_1==StringLiteral) ) {
                        alt142=1;
                    }
                    else if ( (LA142_1==Identifier) ) {
                        int LA142_3 = input.LA(3);

                        if ( (LA142_3==LPAREN||LA142_3==PLUS||LA142_3==ASSIGN_EQUAL) ) {
                            alt142=1;
                        }


                    }


                }


                switch (alt142) {
            	case 1 :
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1291:6: COMMA key= stringExpression ASSIGN_EQUAL value= typeExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionTrie8035); if (state.failed) return action;
            	    pushFollow(FOLLOW_stringExpression_in_actionTrie8041);
            	    key=stringExpression();

            	    state._fsp--;
            	    if (state.failed) return action;
            	    match(input,ASSIGN_EQUAL,FOLLOW_ASSIGN_EQUAL_in_actionTrie8043); if (state.failed) return action;
            	    pushFollow(FOLLOW_typeExpression_in_actionTrie8049);
            	    value=typeExpression();

            	    state._fsp--;
            	    if (state.failed) return action;
            	    if ( state.backtracking==0 ) {
            	      map.put(key,value);
            	    }

            	    }
            	    break;

            	default :
            	    break loop142;
                }
            } while (true);

            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8059); if (state.failed) return action;
            pushFollow(FOLLOW_wordListExpression_in_actionTrie8065);
            list=wordListExpression();

            state._fsp--;
            if (state.failed) return action;
            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8072); if (state.failed) return action;
            pushFollow(FOLLOW_booleanExpression_in_actionTrie8078);
            ignoreCase=booleanExpression();

            state._fsp--;
            if (state.failed) return action;
            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8085); if (state.failed) return action;
            pushFollow(FOLLOW_numberExpression_in_actionTrie8091);
            ignoreLength=numberExpression();

            state._fsp--;
            if (state.failed) return action;
            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8098); if (state.failed) return action;
            pushFollow(FOLLOW_booleanExpression_in_actionTrie8104);
            edit=booleanExpression();

            state._fsp--;
            if (state.failed) return action;
            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8111); if (state.failed) return action;
            pushFollow(FOLLOW_numberExpression_in_actionTrie8117);
            distance=numberExpression();

            state._fsp--;
            if (state.failed) return action;
            match(input,COMMA,FOLLOW_COMMA_in_actionTrie8124); if (state.failed) return action;
            pushFollow(FOLLOW_stringExpression_in_actionTrie8130);
            ignoreChar=stringExpression();

            state._fsp--;
            if (state.failed) return action;
            match(input,RPAREN,FOLLOW_RPAREN_in_actionTrie8136); if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createTrieAction(list, map, ignoreCase, ignoreLength, edit, distance, ignoreChar, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return action;
    }
    // $ANTLR end "actionTrie"


    // $ANTLR start "actionAdd"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1303:1: actionAdd returns [AbstractTextMarkerAction action = null] : name= ADD LPAREN f= listVariable ( COMMA a= argument )+ RPAREN ;
    public final AbstractTextMarkerAction actionAdd() throws RecognitionException {
        AbstractTextMarkerAction action =  null;

        Token name=null;
        Token f = null;

        TextMarkerExpression a = null;



        	List<TextMarkerExpression> list = new ArrayList<TextMarkerExpression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1307:5: (name= ADD LPAREN f= listVariable ( COMMA a= argument )+ RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1308:5: name= ADD LPAREN f= listVariable ( COMMA a= argument )+ RPAREN
            {
            name=(Token)match(input,ADD,FOLLOW_ADD_in_actionAdd8181); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionAdd8183); if (state.failed) return action;
            pushFollow(FOLLOW_listVariable_in_actionAdd8189);
            f=listVariable();

            state._fsp--;
            if (state.failed) return action;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1308:40: ( COMMA a= argument )+
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
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1308:41: COMMA a= argument
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionAdd8192); if (state.failed) return action;
            	    pushFollow(FOLLOW_argument_in_actionAdd8198);
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

            match(input,RPAREN,FOLLOW_RPAREN_in_actionAdd8204); if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createAddAction(f, list, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return action;
    }
    // $ANTLR end "actionAdd"


    // $ANTLR start "actionRemove"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1312:1: actionRemove returns [AbstractTextMarkerAction action = null] : name= REMOVE LPAREN f= listVariable ( COMMA a= argument )+ RPAREN ;
    public final AbstractTextMarkerAction actionRemove() throws RecognitionException {
        AbstractTextMarkerAction action =  null;

        Token name=null;
        Token f = null;

        TextMarkerExpression a = null;



        	List<TextMarkerExpression> list = new ArrayList<TextMarkerExpression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1316:5: (name= REMOVE LPAREN f= listVariable ( COMMA a= argument )+ RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1317:5: name= REMOVE LPAREN f= listVariable ( COMMA a= argument )+ RPAREN
            {
            name=(Token)match(input,REMOVE,FOLLOW_REMOVE_in_actionRemove8244); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionRemove8246); if (state.failed) return action;
            pushFollow(FOLLOW_listVariable_in_actionRemove8252);
            f=listVariable();

            state._fsp--;
            if (state.failed) return action;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1317:43: ( COMMA a= argument )+
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
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1317:44: COMMA a= argument
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionRemove8255); if (state.failed) return action;
            	    pushFollow(FOLLOW_argument_in_actionRemove8261);
            	    a=argument();

            	    state._fsp--;
            	    if (state.failed) return action;
            	    if ( state.backtracking==0 ) {
            	      list.add(a);
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

            match(input,RPAREN,FOLLOW_RPAREN_in_actionRemove8267); if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createRemoveAction(f, list, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return action;
    }
    // $ANTLR end "actionRemove"


    // $ANTLR start "actionRemoveDuplicate"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1321:1: actionRemoveDuplicate returns [AbstractTextMarkerAction action = null] : name= REMOVEDUPLICATE LPAREN f= listVariable RPAREN ;
    public final AbstractTextMarkerAction actionRemoveDuplicate() throws RecognitionException {
        AbstractTextMarkerAction action =  null;

        Token name=null;
        Token f = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1322:5: (name= REMOVEDUPLICATE LPAREN f= listVariable RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1323:5: name= REMOVEDUPLICATE LPAREN f= listVariable RPAREN
            {
            name=(Token)match(input,REMOVEDUPLICATE,FOLLOW_REMOVEDUPLICATE_in_actionRemoveDuplicate8303); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionRemoveDuplicate8305); if (state.failed) return action;
            pushFollow(FOLLOW_listVariable_in_actionRemoveDuplicate8311);
            f=listVariable();

            state._fsp--;
            if (state.failed) return action;
            match(input,RPAREN,FOLLOW_RPAREN_in_actionRemoveDuplicate8313); if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createRemoveDuplicateAction(f, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return action;
    }
    // $ANTLR end "actionRemoveDuplicate"


    // $ANTLR start "actionMerge"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1327:1: actionMerge returns [AbstractTextMarkerAction action = null] : name= MERGE LPAREN join= booleanExpression COMMA t= listVariable COMMA f= listExpression ( COMMA f= listExpression )+ RPAREN ;
    public final AbstractTextMarkerAction actionMerge() throws RecognitionException {
        AbstractTextMarkerAction action =  null;

        Token name=null;
        BooleanExpression join = null;

        Token t = null;

        ListExpression f = null;



        	List<ListExpression> list = new ArrayList<ListExpression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1331:5: (name= MERGE LPAREN join= booleanExpression COMMA t= listVariable COMMA f= listExpression ( COMMA f= listExpression )+ RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1332:5: name= MERGE LPAREN join= booleanExpression COMMA t= listVariable COMMA f= listExpression ( COMMA f= listExpression )+ RPAREN
            {
            name=(Token)match(input,MERGE,FOLLOW_MERGE_in_actionMerge8358); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionMerge8360); if (state.failed) return action;
            pushFollow(FOLLOW_booleanExpression_in_actionMerge8366);
            join=booleanExpression();

            state._fsp--;
            if (state.failed) return action;
            match(input,COMMA,FOLLOW_COMMA_in_actionMerge8368); if (state.failed) return action;
            pushFollow(FOLLOW_listVariable_in_actionMerge8374);
            t=listVariable();

            state._fsp--;
            if (state.failed) return action;
            match(input,COMMA,FOLLOW_COMMA_in_actionMerge8376); if (state.failed) return action;
            pushFollow(FOLLOW_listExpression_in_actionMerge8382);
            f=listExpression();

            state._fsp--;
            if (state.failed) return action;
            if ( state.backtracking==0 ) {
              list.add(f);
            }
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1332:113: ( COMMA f= listExpression )+
            int cnt145=0;
            loop145:
            do {
                int alt145=2;
                int LA145_0 = input.LA(1);

                if ( (LA145_0==COMMA) ) {
                    alt145=1;
                }


                switch (alt145) {
            	case 1 :
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1332:114: COMMA f= listExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMerge8387); if (state.failed) return action;
            	    pushFollow(FOLLOW_listExpression_in_actionMerge8393);
            	    f=listExpression();

            	    state._fsp--;
            	    if (state.failed) return action;
            	    if ( state.backtracking==0 ) {
            	      list.add(f);
            	    }

            	    }
            	    break;

            	default :
            	    if ( cnt145 >= 1 ) break loop145;
            	    if (state.backtracking>0) {state.failed=true; return action;}
                        EarlyExitException eee =
                            new EarlyExitException(145, input);
                        throw eee;
                }
                cnt145++;
            } while (true);

            match(input,RPAREN,FOLLOW_RPAREN_in_actionMerge8399); if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createMergeAction(join, t, list, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return action;
    }
    // $ANTLR end "actionMerge"


    // $ANTLR start "actionGet"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1336:1: actionGet returns [AbstractTextMarkerAction action = null] : name= GET LPAREN f= listExpression COMMA var= variable COMMA op= stringExpression RPAREN ;
    public final AbstractTextMarkerAction actionGet() throws RecognitionException {
        AbstractTextMarkerAction action =  null;

        Token name=null;
        ListExpression f = null;

        Token var = null;

        StringExpression op = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1337:5: (name= GET LPAREN f= listExpression COMMA var= variable COMMA op= stringExpression RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1338:5: name= GET LPAREN f= listExpression COMMA var= variable COMMA op= stringExpression RPAREN
            {
            name=(Token)match(input,GET,FOLLOW_GET_in_actionGet8434); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionGet8436); if (state.failed) return action;
            pushFollow(FOLLOW_listExpression_in_actionGet8442);
            f=listExpression();

            state._fsp--;
            if (state.failed) return action;
            match(input,COMMA,FOLLOW_COMMA_in_actionGet8444); if (state.failed) return action;
            pushFollow(FOLLOW_variable_in_actionGet8450);
            var=variable();

            state._fsp--;
            if (state.failed) return action;
            match(input,COMMA,FOLLOW_COMMA_in_actionGet8452); if (state.failed) return action;
            pushFollow(FOLLOW_stringExpression_in_actionGet8458);
            op=stringExpression();

            state._fsp--;
            if (state.failed) return action;
            match(input,RPAREN,FOLLOW_RPAREN_in_actionGet8460); if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createGetAction(f, var, op, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return action;
    }
    // $ANTLR end "actionGet"


    // $ANTLR start "actionGetList"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1342:1: actionGetList returns [AbstractTextMarkerAction action = null] : name= GETLIST LPAREN var= listVariable COMMA op= stringExpression RPAREN ;
    public final AbstractTextMarkerAction actionGetList() throws RecognitionException {
        AbstractTextMarkerAction action =  null;

        Token name=null;
        Token var = null;

        StringExpression op = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1343:5: (name= GETLIST LPAREN var= listVariable COMMA op= stringExpression RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1344:5: name= GETLIST LPAREN var= listVariable COMMA op= stringExpression RPAREN
            {
            name=(Token)match(input,GETLIST,FOLLOW_GETLIST_in_actionGetList8495); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionGetList8497); if (state.failed) return action;
            pushFollow(FOLLOW_listVariable_in_actionGetList8503);
            var=listVariable();

            state._fsp--;
            if (state.failed) return action;
            match(input,COMMA,FOLLOW_COMMA_in_actionGetList8505); if (state.failed) return action;
            pushFollow(FOLLOW_stringExpression_in_actionGetList8511);
            op=stringExpression();

            state._fsp--;
            if (state.failed) return action;
            match(input,RPAREN,FOLLOW_RPAREN_in_actionGetList8513); if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createGetListAction(var, op, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return action;
    }
    // $ANTLR end "actionGetList"


    // $ANTLR start "actionMatchedText"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1348:1: actionMatchedText returns [AbstractTextMarkerAction action = null] : MATCHEDTEXT LPAREN var= variable ( COMMA index= numberExpression )* RPAREN ;
    public final AbstractTextMarkerAction actionMatchedText() throws RecognitionException {
        AbstractTextMarkerAction action =  null;

        Token var = null;

        NumberExpression index = null;



        List<NumberExpression> list = new ArrayList<NumberExpression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1352:5: ( MATCHEDTEXT LPAREN var= variable ( COMMA index= numberExpression )* RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1353:5: MATCHEDTEXT LPAREN var= variable ( COMMA index= numberExpression )* RPAREN
            {
            match(input,MATCHEDTEXT,FOLLOW_MATCHEDTEXT_in_actionMatchedText8552); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionMatchedText8554); if (state.failed) return action;
            pushFollow(FOLLOW_variable_in_actionMatchedText8565);
            var=variable();

            state._fsp--;
            if (state.failed) return action;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1355:5: ( COMMA index= numberExpression )*
            loop146:
            do {
                int alt146=2;
                int LA146_0 = input.LA(1);

                if ( (LA146_0==COMMA) ) {
                    alt146=1;
                }


                switch (alt146) {
            	case 1 :
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1356:9: COMMA index= numberExpression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_actionMatchedText8581); if (state.failed) return action;
            	    pushFollow(FOLLOW_numberExpression_in_actionMatchedText8587);
            	    index=numberExpression();

            	    state._fsp--;
            	    if (state.failed) return action;
            	    if ( state.backtracking==0 ) {
            	      list.add(index);
            	    }

            	    }
            	    break;

            	default :
            	    break loop146;
                }
            } while (true);

            match(input,RPAREN,FOLLOW_RPAREN_in_actionMatchedText8611); if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createMatchedTextAction(var, list, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return action;
    }
    // $ANTLR end "actionMatchedText"


    // $ANTLR start "actionClear"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1364:1: actionClear returns [AbstractTextMarkerAction action = null] : name= CLEAR LPAREN var= listVariable RPAREN ;
    public final AbstractTextMarkerAction actionClear() throws RecognitionException {
        AbstractTextMarkerAction action =  null;

        Token name=null;
        Token var = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1365:5: (name= CLEAR LPAREN var= listVariable RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1366:5: name= CLEAR LPAREN var= listVariable RPAREN
            {
            name=(Token)match(input,CLEAR,FOLLOW_CLEAR_in_actionClear8651); if (state.failed) return action;
            match(input,LPAREN,FOLLOW_LPAREN_in_actionClear8653); if (state.failed) return action;
            pushFollow(FOLLOW_listVariable_in_actionClear8659);
            var=listVariable();

            state._fsp--;
            if (state.failed) return action;
            match(input,RPAREN,FOLLOW_RPAREN_in_actionClear8661); if (state.failed) return action;
            if ( state.backtracking==0 ) {
              action = ActionFactory.createClearAction(var, ((blockDeclaration_scope)blockDeclaration_stack.peek()).env);
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
        }
        return action;
    }
    // $ANTLR end "actionClear"


    // $ANTLR start "varArgumentList"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1371:1: varArgumentList returns [List args = new ArrayList()] : ( LPAREN arg= argument ( COMMA arg= argument )* RPAREN )? ;
    public final List varArgumentList() throws RecognitionException {
        List args =  new ArrayList();

        TextMarkerExpression arg = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1372:2: ( ( LPAREN arg= argument ( COMMA arg= argument )* RPAREN )? )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1373:2: ( LPAREN arg= argument ( COMMA arg= argument )* RPAREN )?
            {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1373:2: ( LPAREN arg= argument ( COMMA arg= argument )* RPAREN )?
            int alt148=2;
            int LA148_0 = input.LA(1);

            if ( (LA148_0==LPAREN) ) {
                alt148=1;
            }
            switch (alt148) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1373:3: LPAREN arg= argument ( COMMA arg= argument )* RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_varArgumentList8688); if (state.failed) return args;
                    pushFollow(FOLLOW_argument_in_varArgumentList8694);
                    arg=argument();

                    state._fsp--;
                    if (state.failed) return args;
                    if ( state.backtracking==0 ) {
                      args.add(arg);
                    }
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1373:41: ( COMMA arg= argument )*
                    loop147:
                    do {
                        int alt147=2;
                        int LA147_0 = input.LA(1);

                        if ( (LA147_0==COMMA) ) {
                            alt147=1;
                        }


                        switch (alt147) {
                    	case 1 :
                    	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1373:42: COMMA arg= argument
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_varArgumentList8698); if (state.failed) return args;
                    	    pushFollow(FOLLOW_argument_in_varArgumentList8704);
                    	    arg=argument();

                    	    state._fsp--;
                    	    if (state.failed) return args;
                    	    if ( state.backtracking==0 ) {
                    	      args.add(arg);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop147;
                        }
                    } while (true);

                    match(input,RPAREN,FOLLOW_RPAREN_in_varArgumentList8710); if (state.failed) return args;

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
        }
        return args;
    }
    // $ANTLR end "varArgumentList"


    // $ANTLR start "argument"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1376:1: argument returns [TextMarkerExpression expr = null] options {backtrack=true; } : (a4= stringExpression | a2= booleanExpression | a3= numberExpression | a1= typeExpression );
    public final TextMarkerExpression argument() throws RecognitionException {
        TextMarkerExpression expr =  null;

        StringExpression a4 = null;

        BooleanExpression a2 = null;

        NumberExpression a3 = null;

        TypeExpression a1 = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1380:2: (a4= stringExpression | a2= booleanExpression | a3= numberExpression | a1= typeExpression )
            int alt149=4;
            alt149 = dfa149.predict(input);
            switch (alt149) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1381:2: a4= stringExpression
                    {
                    pushFollow(FOLLOW_stringExpression_in_argument8744);
                    a4=stringExpression();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = a4;
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1382:4: a2= booleanExpression
                    {
                    pushFollow(FOLLOW_booleanExpression_in_argument8755);
                    a2=booleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = a2;
                    }

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1383:4: a3= numberExpression
                    {
                    pushFollow(FOLLOW_numberExpression_in_argument8766);
                    a3=numberExpression();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = a3;
                    }

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1384:4: a1= typeExpression
                    {
                    pushFollow(FOLLOW_typeExpression_in_argument8777);
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
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}
        finally {
        }
        return expr;
    }
    // $ANTLR end "argument"


    // $ANTLR start "dottedIdentifier"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1393:1: dottedIdentifier returns [String idString = \"\"] : id= Identifier (dot= DOT idn= Identifier )* ;
    public final String dottedIdentifier() throws RecognitionException {
        String idString =  "";

        Token id=null;
        Token dot=null;
        Token idn=null;

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1394:2: (id= Identifier (dot= DOT idn= Identifier )* )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1395:2: id= Identifier (dot= DOT idn= Identifier )*
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedIdentifier8811); if (state.failed) return idString;
            if ( state.backtracking==0 ) {
              idString += id.getText();
            }
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1396:2: (dot= DOT idn= Identifier )*
            loop150:
            do {
                int alt150=2;
                int LA150_0 = input.LA(1);

                if ( (LA150_0==DOT) ) {
                    alt150=1;
                }


                switch (alt150) {
            	case 1 :
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1397:3: dot= DOT idn= Identifier
            	    {
            	    dot=(Token)match(input,DOT,FOLLOW_DOT_in_dottedIdentifier8824); if (state.failed) return idString;
            	    if ( state.backtracking==0 ) {
            	      idString += dot.getText();
            	    }
            	    idn=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedIdentifier8834); if (state.failed) return idString;
            	    if ( state.backtracking==0 ) {
            	      idString += idn.getText();
            	    }

            	    }
            	    break;

            	default :
            	    break loop150;
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
        }
        return idString;
    }
    // $ANTLR end "dottedIdentifier"


    // $ANTLR start "dottedIdentifier2"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1402:1: dottedIdentifier2 returns [String idString = \"\"] : id= Identifier (dot= ( DOT | MINUS ) idn= Identifier )* ;
    public final String dottedIdentifier2() throws RecognitionException {
        String idString =  "";

        Token id=null;
        Token dot=null;
        Token idn=null;

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1403:2: (id= Identifier (dot= ( DOT | MINUS ) idn= Identifier )* )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1404:2: id= Identifier (dot= ( DOT | MINUS ) idn= Identifier )*
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedIdentifier28860); if (state.failed) return idString;
            if ( state.backtracking==0 ) {
              idString += id.getText();
            }
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1405:2: (dot= ( DOT | MINUS ) idn= Identifier )*
            loop151:
            do {
                int alt151=2;
                int LA151_0 = input.LA(1);

                if ( (LA151_0==DOT||LA151_0==MINUS) ) {
                    alt151=1;
                }


                switch (alt151) {
            	case 1 :
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1406:3: dot= ( DOT | MINUS ) idn= Identifier
            	    {
            	    dot=(Token)input.LT(1);
            	    if ( input.LA(1)==DOT||input.LA(1)==MINUS ) {
            	        input.consume();
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return idString;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    if ( state.backtracking==0 ) {
            	      idString += dot.getText();
            	    }
            	    idn=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedIdentifier28887); if (state.failed) return idString;
            	    if ( state.backtracking==0 ) {
            	      idString += idn.getText();
            	    }

            	    }
            	    break;

            	default :
            	    break loop151;
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
        }
        return idString;
    }
    // $ANTLR end "dottedIdentifier2"


    // $ANTLR start "dottedId"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1412:1: dottedId returns [Token token = null ] : id= Identifier (dot= DOT id= Identifier )* ;
    public final Token dottedId() throws RecognitionException {
        Token token =  null;

        Token id=null;
        Token dot=null;

        CommonToken ct = null;
        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1414:2: (id= Identifier (dot= DOT id= Identifier )* )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1415:2: id= Identifier (dot= DOT id= Identifier )*
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedId8919); if (state.failed) return token;
            if ( state.backtracking==0 ) {

              		ct = new CommonToken(id);
              		
            }
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1418:2: (dot= DOT id= Identifier )*
            loop152:
            do {
                int alt152=2;
                int LA152_0 = input.LA(1);

                if ( (LA152_0==DOT) ) {
                    alt152=1;
                }


                switch (alt152) {
            	case 1 :
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1419:3: dot= DOT id= Identifier
            	    {
            	    dot=(Token)match(input,DOT,FOLLOW_DOT_in_dottedId8932); if (state.failed) return token;
            	    if ( state.backtracking==0 ) {
            	      ct.setText(ct.getText() + dot.getText());
            	    }
            	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_dottedId8942); if (state.failed) return token;
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
              token = ct;
              	 return token;
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
        }
        return token;
    }
    // $ANTLR end "dottedId"


    // $ANTLR start "annotationType"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1427:1: annotationType returns [Token ref = null] : (token= BasicAnnotationType | did= dottedId ) ;
    public final Token annotationType() throws RecognitionException {
        Token ref =  null;

        Token token=null;
        Token did = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1428:2: ( (token= BasicAnnotationType | did= dottedId ) )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1429:2: (token= BasicAnnotationType | did= dottedId )
            {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1429:2: (token= BasicAnnotationType | did= dottedId )
            int alt153=2;
            int LA153_0 = input.LA(1);

            if ( (LA153_0==BasicAnnotationType) ) {
                alt153=1;
            }
            else if ( (LA153_0==Identifier) ) {
                alt153=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ref;}
                NoViableAltException nvae =
                    new NoViableAltException("", 153, 0, input);

                throw nvae;
            }
            switch (alt153) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1431:2: token= BasicAnnotationType
                    {
                    token=(Token)match(input,BasicAnnotationType,FOLLOW_BasicAnnotationType_in_annotationType8977); if (state.failed) return ref;
                    if ( state.backtracking==0 ) {
                      ref = token;
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1433:2: did= dottedId
                    {
                    pushFollow(FOLLOW_dottedId_in_annotationType8989);
                    did=dottedId();

                    state._fsp--;
                    if (state.failed) return ref;
                    if ( state.backtracking==0 ) {
                      ref = did;
                    }

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
        }
        return ref;
    }
    // $ANTLR end "annotationType"


    // $ANTLR start "wordListExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1437:1: wordListExpression returns [WordListExpression expr = null] : (id= Identifier | path= RessourceLiteral );
    public final WordListExpression wordListExpression() throws RecognitionException {
        WordListExpression expr =  null;

        Token id=null;
        Token path=null;

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1438:2: (id= Identifier | path= RessourceLiteral )
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1439:2: id= Identifier
                    {
                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_wordListExpression9014); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createReferenceWordListExpression(id);
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1442:2: path= RessourceLiteral
                    {
                    path=(Token)match(input,RessourceLiteral,FOLLOW_RessourceLiteral_in_wordListExpression9027); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createLiteralWordListExpression(path);
                    }

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
        }
        return expr;
    }
    // $ANTLR end "wordListExpression"


    // $ANTLR start "wordTableExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1447:1: wordTableExpression returns [WordTableExpression expr = null] : (id= Identifier | path= RessourceLiteral );
    public final WordTableExpression wordTableExpression() throws RecognitionException {
        WordTableExpression expr =  null;

        Token id=null;
        Token path=null;

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1448:2: (id= Identifier | path= RessourceLiteral )
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1449:2: id= Identifier
                    {
                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_wordTableExpression9051); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createReferenceWordTableExpression(id);
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1452:2: path= RessourceLiteral
                    {
                    path=(Token)match(input,RessourceLiteral,FOLLOW_RessourceLiteral_in_wordTableExpression9064); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createLiteralWordTableExpression(path);
                    }

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
        }
        return expr;
    }
    // $ANTLR end "wordTableExpression"


    // $ANTLR start "numberFunction"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1457:1: numberFunction returns [NumberExpression expr = null] : ( (op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar ) | (e= externalNumberFunction )=>e= externalNumberFunction );
    public final NumberExpression numberFunction() throws RecognitionException {
        NumberExpression expr =  null;

        Token op=null;
        NumberExpression numExprP = null;

        NumberExpression e = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1458:2: ( (op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar ) | (e= externalNumberFunction )=>e= externalNumberFunction )
            int alt156=2;
            int LA156_0 = input.LA(1);

            if ( ((LA156_0>=EXP && LA156_0<=TAN)) ) {
                alt156=1;
            }
            else if ( (LA156_0==Identifier) && (synpred28_TextMarkerParser())) {
                alt156=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 156, 0, input);

                throw nvae;
            }
            switch (alt156) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1459:2: (op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar )
                    {
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1459:2: (op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar )
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1459:3: op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar
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

                    pushFollow(FOLLOW_numberExpressionInPar_in_numberFunction9109);
                    numExprP=numberExpressionInPar();

                    state._fsp--;
                    if (state.failed) return expr;

                    }

                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createComposedNumberExpression(numExprP,op);
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1462:4: (e= externalNumberFunction )=>e= externalNumberFunction
                    {
                    pushFollow(FOLLOW_externalNumberFunction_in_numberFunction9133);
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
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}
        finally {
        }
        return expr;
    }
    // $ANTLR end "numberFunction"


    // $ANTLR start "externalNumberFunction"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1467:1: externalNumberFunction returns [NumberExpression expr = null] : id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final NumberExpression externalNumberFunction() throws RecognitionException {
        NumberExpression expr =  null;

        Token id=null;
        List args = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1468:2: (id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1470:2: id= Identifier LPAREN args= varArgumentList RPAREN
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalNumberFunction9159); if (state.failed) return expr;
            match(input,LPAREN,FOLLOW_LPAREN_in_externalNumberFunction9161); if (state.failed) return expr;
            pushFollow(FOLLOW_varArgumentList_in_externalNumberFunction9168);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return expr;
            match(input,RPAREN,FOLLOW_RPAREN_in_externalNumberFunction9170); if (state.failed) return expr;
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
        }
        return expr;
    }
    // $ANTLR end "externalNumberFunction"


    // $ANTLR start "numberVariable"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1477:1: numberVariable returns [Token ref = null] : ({...}?token1= Identifier | {...}?token2= Identifier );
    public final Token numberVariable() throws RecognitionException {
        Token ref =  null;

        Token token1=null;
        Token token2=null;

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1478:2: ({...}?token1= Identifier | {...}?token2= Identifier )
            int alt157=2;
            int LA157_0 = input.LA(1);

            if ( (LA157_0==Identifier) ) {
                int LA157_1 = input.LA(2);

                if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INT"))) ) {
                    alt157=1;
                }
                else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLE"))) ) {
                    alt157=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ref;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 157, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ref;}
                NoViableAltException nvae =
                    new NoViableAltException("", 157, 0, input);

                throw nvae;
            }
            switch (alt157) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1479:2: {...}?token1= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INT"))) ) {
                        if (state.backtracking>0) {state.failed=true; return ref;}
                        throw new FailedPredicateException(input, "numberVariable", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"INT\")");
                    }
                    token1=(Token)match(input,Identifier,FOLLOW_Identifier_in_numberVariable9195); if (state.failed) return ref;
                    if ( state.backtracking==0 ) {
                      ref = token1;
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1480:4: {...}?token2= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLE"))) ) {
                        if (state.backtracking>0) {state.failed=true; return ref;}
                        throw new FailedPredicateException(input, "numberVariable", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"DOUBLE\")");
                    }
                    token2=(Token)match(input,Identifier,FOLLOW_Identifier_in_numberVariable9208); if (state.failed) return ref;
                    if ( state.backtracking==0 ) {
                      ref = token2;
                    }

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
        }
        return ref;
    }
    // $ANTLR end "numberVariable"


    // $ANTLR start "additiveExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1484:1: additiveExpression returns [NumberExpression expr = null] : e= multiplicativeExpression (op= ( PLUS | MINUS ) e= multiplicativeExpression )* ;
    public final NumberExpression additiveExpression() throws RecognitionException {
        NumberExpression expr =  null;

        Token op=null;
        NumberExpression e = null;


        List<NumberExpression> exprs = new ArrayList<NumberExpression>();
        	List<Token> ops = new ArrayList<Token>();
        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1487:2: (e= multiplicativeExpression (op= ( PLUS | MINUS ) e= multiplicativeExpression )* )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1488:2: e= multiplicativeExpression (op= ( PLUS | MINUS ) e= multiplicativeExpression )*
            {
            pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression9238);
            e=multiplicativeExpression();

            state._fsp--;
            if (state.failed) return expr;
            if ( state.backtracking==0 ) {
              exprs.add(e);
            }
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1488:46: (op= ( PLUS | MINUS ) e= multiplicativeExpression )*
            loop158:
            do {
                int alt158=2;
                int LA158_0 = input.LA(1);

                if ( ((LA158_0>=PLUS && LA158_0<=MINUS)) ) {
                    alt158=1;
                }


                switch (alt158) {
            	case 1 :
            	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1488:48: op= ( PLUS | MINUS ) e= multiplicativeExpression
            	    {
            	    op=(Token)input.LT(1);
            	    if ( (input.LA(1)>=PLUS && input.LA(1)<=MINUS) ) {
            	        input.consume();
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return expr;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    if ( state.backtracking==0 ) {
            	      ops.add(op);
            	    }
            	    pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression9260);
            	    e=multiplicativeExpression();

            	    state._fsp--;
            	    if (state.failed) return expr;
            	    if ( state.backtracking==0 ) {
            	      exprs.add(e);
            	    }

            	    }
            	    break;

            	default :
            	    break loop158;
                }
            } while (true);

            if ( state.backtracking==0 ) {
              expr = ExpressionFactory.createComposedNumberExpression(exprs,ops);
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
        }
        return expr;
    }
    // $ANTLR end "additiveExpression"


    // $ANTLR start "multiplicativeExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1492:1: multiplicativeExpression returns [NumberExpression expr = null] : (e= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) e= simpleNumberExpression )* | e1= numberFunction ) ;
    public final NumberExpression multiplicativeExpression() throws RecognitionException {
        NumberExpression expr =  null;

        Token op=null;
        NumberExpression e = null;

        NumberExpression e1 = null;


        List<NumberExpression> exprs = new ArrayList<NumberExpression>();
        	List<Token> ops = new ArrayList<Token>();
        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1495:2: ( (e= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) e= simpleNumberExpression )* | e1= numberFunction ) )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1496:2: (e= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) e= simpleNumberExpression )* | e1= numberFunction )
            {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1496:2: (e= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) e= simpleNumberExpression )* | e1= numberFunction )
            int alt160=2;
            switch ( input.LA(1) ) {
            case DecimalLiteral:
            case FloatingPointLiteral:
            case LPAREN:
            case MINUS:
                {
                alt160=1;
                }
                break;
            case Identifier:
                {
                int LA160_2 = input.LA(2);

                if ( (LA160_2==LPAREN) ) {
                    alt160=2;
                }
                else if ( (LA160_2==EOF||LA160_2==RPAREN||LA160_2==RBRACK||(LA160_2>=COMMA && LA160_2<=SLASH)||(LA160_2>=LESS && LA160_2<=GREATER)||LA160_2==PERCENT||(LA160_2>=EQUAL && LA160_2<=NOTEQUAL)||(LA160_2>=LESSEQUAL && LA160_2<=GREATEREQUAL)) ) {
                    alt160=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 160, 2, input);

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
                alt160=2;
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1496:3: e= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) e= simpleNumberExpression )*
                    {
                    pushFollow(FOLLOW_simpleNumberExpression_in_multiplicativeExpression9293);
                    e=simpleNumberExpression();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      exprs.add(e);
                    }
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1496:45: (op= ( STAR | SLASH | PERCENT ) e= simpleNumberExpression )*
                    loop159:
                    do {
                        int alt159=2;
                        int LA159_0 = input.LA(1);

                        if ( ((LA159_0>=STAR && LA159_0<=SLASH)||LA159_0==PERCENT) ) {
                            alt159=1;
                        }


                        switch (alt159) {
                    	case 1 :
                    	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1496:47: op= ( STAR | SLASH | PERCENT ) e= simpleNumberExpression
                    	    {
                    	    op=(Token)input.LT(1);
                    	    if ( (input.LA(1)>=STAR && input.LA(1)<=SLASH)||input.LA(1)==PERCENT ) {
                    	        input.consume();
                    	        state.errorRecovery=false;state.failed=false;
                    	    }
                    	    else {
                    	        if (state.backtracking>0) {state.failed=true; return expr;}
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        throw mse;
                    	    }

                    	    if ( state.backtracking==0 ) {
                    	      ops.add(op);
                    	    }
                    	    pushFollow(FOLLOW_simpleNumberExpression_in_multiplicativeExpression9321);
                    	    e=simpleNumberExpression();

                    	    state._fsp--;
                    	    if (state.failed) return expr;
                    	    if ( state.backtracking==0 ) {
                    	      exprs.add(e);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop159;
                        }
                    } while (true);

                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createComposedNumberExpression(exprs,ops);
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1498:6: e1= numberFunction
                    {
                    pushFollow(FOLLOW_numberFunction_in_multiplicativeExpression9339);
                    e1=numberFunction();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = e1;
                    }

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
        }
        return expr;
    }
    // $ANTLR end "multiplicativeExpression"


    // $ANTLR start "numberExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1501:1: numberExpression returns [NumberExpression expr = null] : e= additiveExpression ;
    public final NumberExpression numberExpression() throws RecognitionException {
        NumberExpression expr =  null;

        NumberExpression e = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1502:2: (e= additiveExpression )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1503:2: e= additiveExpression
            {
            pushFollow(FOLLOW_additiveExpression_in_numberExpression9362);
            e=additiveExpression();

            state._fsp--;
            if (state.failed) return expr;
            if ( state.backtracking==0 ) {
              expr = e;
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
        }
        return expr;
    }
    // $ANTLR end "numberExpression"


    // $ANTLR start "numberExpressionInPar"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1506:1: numberExpressionInPar returns [NumberExpression expr = null] : LPAREN e= additiveExpression RPAREN ;
    public final NumberExpression numberExpressionInPar() throws RecognitionException {
        NumberExpression expr =  null;

        NumberExpression e = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1507:2: ( LPAREN e= additiveExpression RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1508:2: LPAREN e= additiveExpression RPAREN
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_numberExpressionInPar9380); if (state.failed) return expr;
            pushFollow(FOLLOW_additiveExpression_in_numberExpressionInPar9387);
            e=additiveExpression();

            state._fsp--;
            if (state.failed) return expr;
            match(input,RPAREN,FOLLOW_RPAREN_in_numberExpressionInPar9389); if (state.failed) return expr;
            if ( state.backtracking==0 ) {
              expr = e;
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
        }
        return expr;
    }
    // $ANTLR end "numberExpressionInPar"


    // $ANTLR start "simpleNumberExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1511:1: simpleNumberExpression returns [NumberExpression expr = null] : ( (m= MINUS )? lit= DecimalLiteral | (m= MINUS )? lit= FloatingPointLiteral | (m= MINUS )? var= numberVariable | e= numberExpressionInPar );
    public final NumberExpression simpleNumberExpression() throws RecognitionException {
        NumberExpression expr =  null;

        Token m=null;
        Token lit=null;
        Token var = null;

        NumberExpression e = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1512:2: ( (m= MINUS )? lit= DecimalLiteral | (m= MINUS )? lit= FloatingPointLiteral | (m= MINUS )? var= numberVariable | e= numberExpressionInPar )
            int alt164=4;
            switch ( input.LA(1) ) {
            case MINUS:
                {
                switch ( input.LA(2) ) {
                case FloatingPointLiteral:
                    {
                    alt164=2;
                    }
                    break;
                case DecimalLiteral:
                    {
                    alt164=1;
                    }
                    break;
                case Identifier:
                    {
                    alt164=3;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 164, 1, input);

                    throw nvae;
                }

                }
                break;
            case DecimalLiteral:
                {
                alt164=1;
                }
                break;
            case FloatingPointLiteral:
                {
                alt164=2;
                }
                break;
            case Identifier:
                {
                alt164=3;
                }
                break;
            case LPAREN:
                {
                alt164=4;
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1513:2: (m= MINUS )? lit= DecimalLiteral
                    {
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1513:4: (m= MINUS )?
                    int alt161=2;
                    int LA161_0 = input.LA(1);

                    if ( (LA161_0==MINUS) ) {
                        alt161=1;
                    }
                    switch (alt161) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1513:4: m= MINUS
                            {
                            m=(Token)match(input,MINUS,FOLLOW_MINUS_in_simpleNumberExpression9412); if (state.failed) return expr;

                            }
                            break;

                    }

                    lit=(Token)match(input,DecimalLiteral,FOLLOW_DecimalLiteral_in_simpleNumberExpression9419); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createIntegerExpression(lit,m);
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1514:4: (m= MINUS )? lit= FloatingPointLiteral
                    {
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1514:6: (m= MINUS )?
                    int alt162=2;
                    int LA162_0 = input.LA(1);

                    if ( (LA162_0==MINUS) ) {
                        alt162=1;
                    }
                    switch (alt162) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1514:6: m= MINUS
                            {
                            m=(Token)match(input,MINUS,FOLLOW_MINUS_in_simpleNumberExpression9431); if (state.failed) return expr;

                            }
                            break;

                    }

                    lit=(Token)match(input,FloatingPointLiteral,FOLLOW_FloatingPointLiteral_in_simpleNumberExpression9438); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createDoubleExpression(lit,m);
                    }

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1515:4: (m= MINUS )? var= numberVariable
                    {
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1515:6: (m= MINUS )?
                    int alt163=2;
                    int LA163_0 = input.LA(1);

                    if ( (LA163_0==MINUS) ) {
                        alt163=1;
                    }
                    switch (alt163) {
                        case 1 :
                            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1515:6: m= MINUS
                            {
                            m=(Token)match(input,MINUS,FOLLOW_MINUS_in_simpleNumberExpression9449); if (state.failed) return expr;

                            }
                            break;

                    }

                    pushFollow(FOLLOW_numberVariable_in_simpleNumberExpression9456);
                    var=numberVariable();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createReferenceNumberExpression(var,m);
                    }

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1516:4: e= numberExpressionInPar
                    {
                    pushFollow(FOLLOW_numberExpressionInPar_in_simpleNumberExpression9467);
                    e=numberExpressionInPar();

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
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}
        finally {
        }
        return expr;
    }
    // $ANTLR end "simpleNumberExpression"


    // $ANTLR start "stringExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1519:1: stringExpression returns [StringExpression expr = null] options {backtrack=true; } : (e= simpleStringExpression ( PLUS (e1= simpleStringExpression | e2= numberExpressionInPar | be= simpleBooleanExpression | te= typeExpression | le= listExpression ) )* | (e= stringFunction )=>e= stringFunction );
    public final StringExpression stringExpression() throws RecognitionException {
        StringExpression expr =  null;

        StringExpression e = null;

        StringExpression e1 = null;

        NumberExpression e2 = null;

        BooleanExpression be = null;

        TypeExpression te = null;

        ListExpression le = null;



        List<StringExpression> exprs = new ArrayList<StringExpression>();

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1526:2: (e= simpleStringExpression ( PLUS (e1= simpleStringExpression | e2= numberExpressionInPar | be= simpleBooleanExpression | te= typeExpression | le= listExpression ) )* | (e= stringFunction )=>e= stringFunction )
            int alt167=2;
            int LA167_0 = input.LA(1);

            if ( (LA167_0==StringLiteral) ) {
                alt167=1;
            }
            else if ( (LA167_0==Identifier) ) {
                int LA167_2 = input.LA(2);

                if ( (LA167_2==LPAREN) && (synpred30_TextMarkerParser())) {
                    alt167=2;
                }
                else if ( (LA167_2==EOF||LA167_2==RPAREN||(LA167_2>=COMMA && LA167_2<=PLUS)||LA167_2==ASSIGN_EQUAL) ) {
                    alt167=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 167, 2, input);

                    throw nvae;
                }
            }
            else if ( (LA167_0==REMOVESTRING) && (synpred30_TextMarkerParser())) {
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1527:2: e= simpleStringExpression ( PLUS (e1= simpleStringExpression | e2= numberExpressionInPar | be= simpleBooleanExpression | te= typeExpression | le= listExpression ) )*
                    {
                    pushFollow(FOLLOW_simpleStringExpression_in_stringExpression9507);
                    e=simpleStringExpression();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      exprs.add(e);
                    }
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1528:2: ( PLUS (e1= simpleStringExpression | e2= numberExpressionInPar | be= simpleBooleanExpression | te= typeExpression | le= listExpression ) )*
                    loop166:
                    do {
                        int alt166=2;
                        int LA166_0 = input.LA(1);

                        if ( (LA166_0==PLUS) ) {
                            alt166=1;
                        }


                        switch (alt166) {
                    	case 1 :
                    	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1528:3: PLUS (e1= simpleStringExpression | e2= numberExpressionInPar | be= simpleBooleanExpression | te= typeExpression | le= listExpression )
                    	    {
                    	    match(input,PLUS,FOLLOW_PLUS_in_stringExpression9514); if (state.failed) return expr;
                    	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1528:8: (e1= simpleStringExpression | e2= numberExpressionInPar | be= simpleBooleanExpression | te= typeExpression | le= listExpression )
                    	    int alt165=5;
                    	    switch ( input.LA(1) ) {
                    	    case StringLiteral:
                    	        {
                    	        alt165=1;
                    	        }
                    	        break;
                    	    case Identifier:
                    	        {
                    	        int LA165_2 = input.LA(2);

                    	        if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRING"))) ) {
                    	            alt165=1;
                    	        }
                    	        else if ( ((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "BOOLEAN"))) ) {
                    	            alt165=3;
                    	        }
                    	        else if ( (!((((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRING"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLELIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRINGLIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INTLIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "BOOLEANLIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "BOOLEAN")))))) ) {
                    	            alt165=4;
                    	        }
                    	        else if ( (((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPELIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLELIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRINGLIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "BOOLEANLIST"))||(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INTLIST")))) ) {
                    	            alt165=5;
                    	        }
                    	        else {
                    	            if (state.backtracking>0) {state.failed=true; return expr;}
                    	            NoViableAltException nvae =
                    	                new NoViableAltException("", 165, 2, input);

                    	            throw nvae;
                    	        }
                    	        }
                    	        break;
                    	    case LPAREN:
                    	        {
                    	        alt165=2;
                    	        }
                    	        break;
                    	    case TRUE:
                    	    case FALSE:
                    	        {
                    	        alt165=3;
                    	        }
                    	        break;
                    	    case BasicAnnotationType:
                    	        {
                    	        alt165=4;
                    	        }
                    	        break;
                    	    case LCURLY:
                    	        {
                    	        alt165=5;
                    	        }
                    	        break;
                    	    default:
                    	        if (state.backtracking>0) {state.failed=true; return expr;}
                    	        NoViableAltException nvae =
                    	            new NoViableAltException("", 165, 0, input);

                    	        throw nvae;
                    	    }

                    	    switch (alt165) {
                    	        case 1 :
                    	            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1528:9: e1= simpleStringExpression
                    	            {
                    	            pushFollow(FOLLOW_simpleStringExpression_in_stringExpression9521);
                    	            e1=simpleStringExpression();

                    	            state._fsp--;
                    	            if (state.failed) return expr;
                    	            if ( state.backtracking==0 ) {
                    	              exprs.add(e1);
                    	            }

                    	            }
                    	            break;
                    	        case 2 :
                    	            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1529:5: e2= numberExpressionInPar
                    	            {
                    	            pushFollow(FOLLOW_numberExpressionInPar_in_stringExpression9534);
                    	            e2=numberExpressionInPar();

                    	            state._fsp--;
                    	            if (state.failed) return expr;
                    	            if ( state.backtracking==0 ) {
                    	              exprs.add(e2);
                    	            }

                    	            }
                    	            break;
                    	        case 3 :
                    	            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1530:5: be= simpleBooleanExpression
                    	            {
                    	            pushFollow(FOLLOW_simpleBooleanExpression_in_stringExpression9546);
                    	            be=simpleBooleanExpression();

                    	            state._fsp--;
                    	            if (state.failed) return expr;
                    	            if ( state.backtracking==0 ) {
                    	              exprs.add(be);
                    	            }

                    	            }
                    	            break;
                    	        case 4 :
                    	            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1531:5: te= typeExpression
                    	            {
                    	            pushFollow(FOLLOW_typeExpression_in_stringExpression9558);
                    	            te=typeExpression();

                    	            state._fsp--;
                    	            if (state.failed) return expr;
                    	            if ( state.backtracking==0 ) {
                    	              exprs.add(te);
                    	            }

                    	            }
                    	            break;
                    	        case 5 :
                    	            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1532:5: le= listExpression
                    	            {
                    	            pushFollow(FOLLOW_listExpression_in_stringExpression9570);
                    	            le=listExpression();

                    	            state._fsp--;
                    	            if (state.failed) return expr;
                    	            if ( state.backtracking==0 ) {
                    	              exprs.add(le);
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
                      expr = ExpressionFactory.createComposedStringExpression(exprs);
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1535:3: (e= stringFunction )=>e= stringFunction
                    {
                    pushFollow(FOLLOW_stringFunction_in_stringExpression9598);
                    e=stringFunction();

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
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}
        finally {
        }
        return expr;
    }
    // $ANTLR end "stringExpression"


    // $ANTLR start "stringFunction"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1539:1: stringFunction returns [StringExpression expr = null] : (name= REMOVESTRING LPAREN var= variable ( COMMA t= stringExpression )+ RPAREN | (e= externalStringFunction )=>e= externalStringFunction );
    public final StringExpression stringFunction() throws RecognitionException {
        StringExpression expr =  null;

        Token name=null;
        Token var = null;

        StringExpression t = null;

        StringExpression e = null;


        List<StringExpression> list = new ArrayList<StringExpression>();
        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1541:1: (name= REMOVESTRING LPAREN var= variable ( COMMA t= stringExpression )+ RPAREN | (e= externalStringFunction )=>e= externalStringFunction )
            int alt169=2;
            int LA169_0 = input.LA(1);

            if ( (LA169_0==REMOVESTRING) ) {
                alt169=1;
            }
            else if ( (LA169_0==Identifier) && (synpred31_TextMarkerParser())) {
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1542:2: name= REMOVESTRING LPAREN var= variable ( COMMA t= stringExpression )+ RPAREN
                    {
                    name=(Token)match(input,REMOVESTRING,FOLLOW_REMOVESTRING_in_stringFunction9625); if (state.failed) return expr;
                    match(input,LPAREN,FOLLOW_LPAREN_in_stringFunction9627); if (state.failed) return expr;
                    pushFollow(FOLLOW_variable_in_stringFunction9633);
                    var=variable();

                    state._fsp--;
                    if (state.failed) return expr;
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1542:44: ( COMMA t= stringExpression )+
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
                    	    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1542:45: COMMA t= stringExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_stringFunction9636); if (state.failed) return expr;
                    	    pushFollow(FOLLOW_stringExpression_in_stringFunction9642);
                    	    t=stringExpression();

                    	    state._fsp--;
                    	    if (state.failed) return expr;
                    	    if ( state.backtracking==0 ) {
                    	      list.add(t);
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

                    match(input,RPAREN,FOLLOW_RPAREN_in_stringFunction9648); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = StringFunctionFactory.createRemoveFunction(var,list);
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1545:2: (e= externalStringFunction )=>e= externalStringFunction
                    {
                    pushFollow(FOLLOW_externalStringFunction_in_stringFunction9670);
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
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}
        finally {
        }
        return expr;
    }
    // $ANTLR end "stringFunction"


    // $ANTLR start "externalStringFunction"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1549:1: externalStringFunction returns [StringExpression expr = null] : id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final StringExpression externalStringFunction() throws RecognitionException {
        StringExpression expr =  null;

        Token id=null;
        List args = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1550:2: (id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1552:2: id= Identifier LPAREN args= varArgumentList RPAREN
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalStringFunction9695); if (state.failed) return expr;
            match(input,LPAREN,FOLLOW_LPAREN_in_externalStringFunction9697); if (state.failed) return expr;
            pushFollow(FOLLOW_varArgumentList_in_externalStringFunction9704);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return expr;
            match(input,RPAREN,FOLLOW_RPAREN_in_externalStringFunction9706); if (state.failed) return expr;
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
        }
        return expr;
    }
    // $ANTLR end "externalStringFunction"


    // $ANTLR start "simpleStringExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1559:1: simpleStringExpression returns [StringExpression expr = null] : (lit= StringLiteral | {...}?id= Identifier );
    public final StringExpression simpleStringExpression() throws RecognitionException {
        StringExpression expr =  null;

        Token lit=null;
        Token id=null;

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1560:2: (lit= StringLiteral | {...}?id= Identifier )
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1561:2: lit= StringLiteral
                    {
                    lit=(Token)match(input,StringLiteral,FOLLOW_StringLiteral_in_simpleStringExpression9730); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createSimpleStringExpression(lit);
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1562:4: {...}?id= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRING"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleStringExpression", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"STRING\")");
                    }
                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleStringExpression9744); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createReferenceStringExpression(id);
                    }

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
        }
        return expr;
    }
    // $ANTLR end "simpleStringExpression"


    // $ANTLR start "booleanExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1566:1: booleanExpression returns [BooleanExpression expr = null] : ( (e= composedBooleanExpression )=>e= composedBooleanExpression | sbE= simpleBooleanExpression );
    public final BooleanExpression booleanExpression() throws RecognitionException {
        BooleanExpression expr =  null;

        BooleanExpression e = null;

        BooleanExpression sbE = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1567:2: ( (e= composedBooleanExpression )=>e= composedBooleanExpression | sbE= simpleBooleanExpression )
            int alt171=2;
            alt171 = dfa171.predict(input);
            switch (alt171) {
                case 1 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1568:2: (e= composedBooleanExpression )=>e= composedBooleanExpression
                    {
                    pushFollow(FOLLOW_composedBooleanExpression_in_booleanExpression9777);
                    e=composedBooleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = e;
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1569:3: sbE= simpleBooleanExpression
                    {
                    pushFollow(FOLLOW_simpleBooleanExpression_in_booleanExpression9788);
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
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}
        finally {
        }
        return expr;
    }
    // $ANTLR end "booleanExpression"


    // $ANTLR start "simpleBooleanExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1572:1: simpleBooleanExpression returns [BooleanExpression expr = null] : (e= literalBooleanExpression | {...}?id= Identifier );
    public final BooleanExpression simpleBooleanExpression() throws RecognitionException {
        BooleanExpression expr =  null;

        Token id=null;
        BooleanExpression e = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1573:2: (e= literalBooleanExpression | {...}?id= Identifier )
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1574:3: e= literalBooleanExpression
                    {
                    pushFollow(FOLLOW_literalBooleanExpression_in_simpleBooleanExpression9811);
                    e=literalBooleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = e;
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1575:4: {...}?id= Identifier
                    {
                    if ( !((isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "BOOLEAN"))) ) {
                        if (state.backtracking>0) {state.failed=true; return expr;}
                        throw new FailedPredicateException(input, "simpleBooleanExpression", "isVariableOfType($blockDeclaration::env,input.LT(1).getText(), \"BOOLEAN\")");
                    }
                    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleBooleanExpression9826); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createReferenceBooleanExpression(id);
                    }

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
        }
        return expr;
    }
    // $ANTLR end "simpleBooleanExpression"


    // $ANTLR start "composedBooleanExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1580:1: composedBooleanExpression returns [BooleanExpression expr = null] : ( (e2= booleanCompare )=>e2= booleanCompare | (bte= booleanTypeExpression )=>bte= booleanTypeExpression | (bne= booleanNumberExpression )=>bne= booleanNumberExpression | e1= booleanFunction );
    public final BooleanExpression composedBooleanExpression() throws RecognitionException {
        BooleanExpression expr =  null;

        BooleanExpression e2 = null;

        BooleanExpression bte = null;

        BooleanExpression bne = null;

        BooleanExpression e1 = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1582:2: ( (e2= booleanCompare )=>e2= booleanCompare | (bte= booleanTypeExpression )=>bte= booleanTypeExpression | (bne= booleanNumberExpression )=>bne= booleanNumberExpression | e1= booleanFunction )
            int alt173=4;
            int LA173_0 = input.LA(1);

            if ( (LA173_0==TRUE) && (synpred33_TextMarkerParser())) {
                alt173=1;
            }
            else if ( (LA173_0==FALSE) && (synpred33_TextMarkerParser())) {
                alt173=1;
            }
            else if ( (LA173_0==Identifier) ) {
                int LA173_3 = input.LA(2);

                if ( ((synpred33_TextMarkerParser()&&(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "BOOLEAN")))) ) {
                    alt173=1;
                }
                else if ( (((synpred34_TextMarkerParser()&&(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPE")))||synpred34_TextMarkerParser())) ) {
                    alt173=2;
                }
                else if ( (true) ) {
                    alt173=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 173, 3, input);

                    throw nvae;
                }
            }
            else if ( (LA173_0==BasicAnnotationType) && (synpred34_TextMarkerParser())) {
                alt173=2;
            }
            else if ( (LA173_0==LPAREN) && (synpred35_TextMarkerParser())) {
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1583:2: (e2= booleanCompare )=>e2= booleanCompare
                    {
                    pushFollow(FOLLOW_booleanCompare_in_composedBooleanExpression9860);
                    e2=booleanCompare();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = e2;
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1584:4: (bte= booleanTypeExpression )=>bte= booleanTypeExpression
                    {
                    pushFollow(FOLLOW_booleanTypeExpression_in_composedBooleanExpression9880);
                    bte=booleanTypeExpression();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = bte;
                    }

                    }
                    break;
                case 3 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1585:4: (bne= booleanNumberExpression )=>bne= booleanNumberExpression
                    {
                    pushFollow(FOLLOW_booleanNumberExpression_in_composedBooleanExpression9899);
                    bne=booleanNumberExpression();

                    state._fsp--;
                    if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = bne;
                    }

                    }
                    break;
                case 4 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1586:4: e1= booleanFunction
                    {
                    pushFollow(FOLLOW_booleanFunction_in_composedBooleanExpression9909);
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
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}
        finally {
        }
        return expr;
    }
    // $ANTLR end "composedBooleanExpression"


    // $ANTLR start "booleanFunction"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1590:1: booleanFunction returns [BooleanExpression expr = null] : ( (op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN ) | (e= externalBooleanFunction )=>e= externalBooleanFunction );
    public final BooleanExpression booleanFunction() throws RecognitionException {
        BooleanExpression expr =  null;

        Token op=null;
        BooleanExpression e1 = null;

        BooleanExpression e2 = null;

        BooleanExpression e = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1592:2: ( (op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN ) | (e= externalBooleanFunction )=>e= externalBooleanFunction )
            int alt174=2;
            int LA174_0 = input.LA(1);

            if ( (LA174_0==XOR) ) {
                alt174=1;
            }
            else if ( (LA174_0==Identifier) && (synpred36_TextMarkerParser())) {
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1593:2: (op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN )
                    {
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1593:2: (op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN )
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1593:3: op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN
                    {
                    op=(Token)match(input,XOR,FOLLOW_XOR_in_booleanFunction9934); if (state.failed) return expr;
                    match(input,LPAREN,FOLLOW_LPAREN_in_booleanFunction9936); if (state.failed) return expr;
                    pushFollow(FOLLOW_booleanExpression_in_booleanFunction9942);
                    e1=booleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;
                    match(input,COMMA,FOLLOW_COMMA_in_booleanFunction9944); if (state.failed) return expr;
                    pushFollow(FOLLOW_booleanExpression_in_booleanFunction9950);
                    e2=booleanExpression();

                    state._fsp--;
                    if (state.failed) return expr;
                    match(input,RPAREN,FOLLOW_RPAREN_in_booleanFunction9952); if (state.failed) return expr;

                    }

                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createBooleanFunction(op,e1,e2);
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1595:4: (e= externalBooleanFunction )=>e= externalBooleanFunction
                    {
                    pushFollow(FOLLOW_externalBooleanFunction_in_booleanFunction9974);
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
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}
        finally {
        }
        return expr;
    }
    // $ANTLR end "booleanFunction"


    // $ANTLR start "externalBooleanFunction"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1599:1: externalBooleanFunction returns [BooleanExpression expr = null] : id= Identifier LPAREN args= varArgumentList RPAREN ;
    public final BooleanExpression externalBooleanFunction() throws RecognitionException {
        BooleanExpression expr =  null;

        Token id=null;
        List args = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1600:2: (id= Identifier LPAREN args= varArgumentList RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1602:2: id= Identifier LPAREN args= varArgumentList RPAREN
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_externalBooleanFunction9999); if (state.failed) return expr;
            match(input,LPAREN,FOLLOW_LPAREN_in_externalBooleanFunction10001); if (state.failed) return expr;
            pushFollow(FOLLOW_varArgumentList_in_externalBooleanFunction10008);
            args=varArgumentList();

            state._fsp--;
            if (state.failed) return expr;
            match(input,RPAREN,FOLLOW_RPAREN_in_externalBooleanFunction10010); if (state.failed) return expr;
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
        }
        return expr;
    }
    // $ANTLR end "externalBooleanFunction"


    // $ANTLR start "booleanCompare"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1610:1: booleanCompare returns [BooleanExpression expr = null] : (e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression ) ;
    public final BooleanExpression booleanCompare() throws RecognitionException {
        BooleanExpression expr =  null;

        Token op=null;
        BooleanExpression e1 = null;

        BooleanExpression e2 = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1611:2: ( (e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression ) )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1612:2: (e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression )
            {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1612:2: (e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1612:3: e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression
            {
            pushFollow(FOLLOW_simpleBooleanExpression_in_booleanCompare10035);
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

            pushFollow(FOLLOW_booleanExpression_in_booleanCompare10053);
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
        		emitErrorMessage(exception1.toString());
        	}
        	catch (Throwable exception2) {
        		emitErrorMessage(exception2.toString());
        	}
        finally {
        }
        return expr;
    }
    // $ANTLR end "booleanCompare"


    // $ANTLR start "literalBooleanExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1617:1: literalBooleanExpression returns [BooleanExpression expr = null] : (v= TRUE | v= FALSE );
    public final BooleanExpression literalBooleanExpression() throws RecognitionException {
        BooleanExpression expr =  null;

        Token v=null;

        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1618:2: (v= TRUE | v= FALSE )
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
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1619:2: v= TRUE
                    {
                    v=(Token)match(input,TRUE,FOLLOW_TRUE_in_literalBooleanExpression10079); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createSimpleBooleanExpression(v);
                    }

                    }
                    break;
                case 2 :
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1620:4: v= FALSE
                    {
                    v=(Token)match(input,FALSE,FOLLOW_FALSE_in_literalBooleanExpression10091); if (state.failed) return expr;
                    if ( state.backtracking==0 ) {
                      expr = ExpressionFactory.createSimpleBooleanExpression(v);
                    }

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
        }
        return expr;
    }
    // $ANTLR end "literalBooleanExpression"


    // $ANTLR start "booleanTypeExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1625:1: booleanTypeExpression returns [BooleanExpression expr = null] : e1= typeExpression op= ( EQUAL | NOTEQUAL ) e2= typeExpression ;
    public final BooleanExpression booleanTypeExpression() throws RecognitionException {
        BooleanExpression expr =  null;

        Token op=null;
        TypeExpression e1 = null;

        TypeExpression e2 = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1626:2: (e1= typeExpression op= ( EQUAL | NOTEQUAL ) e2= typeExpression )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1627:2: e1= typeExpression op= ( EQUAL | NOTEQUAL ) e2= typeExpression
            {
            pushFollow(FOLLOW_typeExpression_in_booleanTypeExpression10117);
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

            pushFollow(FOLLOW_typeExpression_in_booleanTypeExpression10137);
            e2=typeExpression();

            state._fsp--;
            if (state.failed) return expr;
            if ( state.backtracking==0 ) {
              expr = ExpressionFactory.createBooleanTypeExpression(e1,op,e2);
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
        }
        return expr;
    }
    // $ANTLR end "booleanTypeExpression"


    // $ANTLR start "booleanNumberExpression"
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1633:1: booleanNumberExpression returns [BooleanExpression expr = null] : LPAREN e1= numberExpression op= ( LESS | GREATER | GREATEREQUAL | LESSEQUAL | EQUAL | NOTEQUAL ) e2= numberExpression RPAREN ;
    public final BooleanExpression booleanNumberExpression() throws RecognitionException {
        BooleanExpression expr =  null;

        Token op=null;
        NumberExpression e1 = null;

        NumberExpression e2 = null;


        try {
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1634:2: ( LPAREN e1= numberExpression op= ( LESS | GREATER | GREATEREQUAL | LESSEQUAL | EQUAL | NOTEQUAL ) e2= numberExpression RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1635:2: LPAREN e1= numberExpression op= ( LESS | GREATER | GREATEREQUAL | LESSEQUAL | EQUAL | NOTEQUAL ) e2= numberExpression RPAREN
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_booleanNumberExpression10159); if (state.failed) return expr;
            pushFollow(FOLLOW_numberExpression_in_booleanNumberExpression10166);
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

            pushFollow(FOLLOW_numberExpression_in_booleanNumberExpression10202);
            e2=numberExpression();

            state._fsp--;
            if (state.failed) return expr;
            match(input,RPAREN,FOLLOW_RPAREN_in_booleanNumberExpression10205); if (state.failed) return expr;
            if ( state.backtracking==0 ) {
              expr = ExpressionFactory.createBooleanNumberExpression(e1,op,e2);
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
        }
        return expr;
    }
    // $ANTLR end "booleanNumberExpression"

    // $ANTLR start synpred3_TextMarkerParser
    public final void synpred3_TextMarkerParser_fragment() throws RecognitionException {   
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:518:2: ( booleanListExpression )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:518:3: booleanListExpression
        {
        pushFollow(FOLLOW_booleanListExpression_in_synpred3_TextMarkerParser2098);
        booleanListExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred3_TextMarkerParser

    // $ANTLR start synpred4_TextMarkerParser
    public final void synpred4_TextMarkerParser_fragment() throws RecognitionException {   
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:519:4: ( intListExpression )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:519:5: intListExpression
        {
        pushFollow(FOLLOW_intListExpression_in_synpred4_TextMarkerParser2114);
        intListExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred4_TextMarkerParser

    // $ANTLR start synpred5_TextMarkerParser
    public final void synpred5_TextMarkerParser_fragment() throws RecognitionException {   
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:520:4: ( doubleListExpression )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:520:5: doubleListExpression
        {
        pushFollow(FOLLOW_doubleListExpression_in_synpred5_TextMarkerParser2130);
        doubleListExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred5_TextMarkerParser

    // $ANTLR start synpred6_TextMarkerParser
    public final void synpred6_TextMarkerParser_fragment() throws RecognitionException {   
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:521:4: ( stringListExpression )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:521:5: stringListExpression
        {
        pushFollow(FOLLOW_stringListExpression_in_synpred6_TextMarkerParser2146);
        stringListExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred6_TextMarkerParser

    // $ANTLR start synpred7_TextMarkerParser
    public final void synpred7_TextMarkerParser_fragment() throws RecognitionException {   
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:522:4: ( typeListExpression )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:522:5: typeListExpression
        {
        pushFollow(FOLLOW_typeListExpression_in_synpred7_TextMarkerParser2162);
        typeListExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred7_TextMarkerParser

    // $ANTLR start synpred8_TextMarkerParser
    public final void synpred8_TextMarkerParser_fragment() throws RecognitionException {   
        NumberListExpression e1 = null;


        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:561:2: (e1= doubleListExpression )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:561:3: e1= doubleListExpression
        {
        pushFollow(FOLLOW_doubleListExpression_in_synpred8_TextMarkerParser2369);
        e1=doubleListExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred8_TextMarkerParser

    // $ANTLR start synpred9_TextMarkerParser
    public final void synpred9_TextMarkerParser_fragment() throws RecognitionException {   
        TypeExpression tf = null;


        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:622:2: (tf= typeFunction )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:622:2: tf= typeFunction
        {
        pushFollow(FOLLOW_typeFunction_in_synpred9_TextMarkerParser2689);
        tf=typeFunction();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred9_TextMarkerParser

    // $ANTLR start synpred11_TextMarkerParser
    public final void synpred11_TextMarkerParser_fragment() throws RecognitionException {   
        AbstractTextMarkerCondition c = null;


        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:732:4: (c= externalCondition )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:732:5: c= externalCondition
        {
        pushFollow(FOLLOW_externalCondition_in_synpred11_TextMarkerParser3260);
        c=externalCondition();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred11_TextMarkerParser

    // $ANTLR start synpred12_TextMarkerParser
    public final void synpred12_TextMarkerParser_fragment() throws RecognitionException {   
        ListExpression type = null;

        TextMarkerExpression a = null;

        NumberExpression min = null;

        NumberExpression max = null;

        Token var = null;


        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:782:5: ( COUNT LPAREN type= listExpression COMMA a= argument ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:782:5: COUNT LPAREN type= listExpression COMMA a= argument ( COMMA min= numberExpression COMMA max= numberExpression )? ( COMMA var= numberVariable )? RPAREN
        {
        match(input,COUNT,FOLLOW_COUNT_in_synpred12_TextMarkerParser3617); if (state.failed) return ;
        match(input,LPAREN,FOLLOW_LPAREN_in_synpred12_TextMarkerParser3619); if (state.failed) return ;
        pushFollow(FOLLOW_listExpression_in_synpred12_TextMarkerParser3625);
        type=listExpression();

        state._fsp--;
        if (state.failed) return ;
        match(input,COMMA,FOLLOW_COMMA_in_synpred12_TextMarkerParser3627); if (state.failed) return ;
        pushFollow(FOLLOW_argument_in_synpred12_TextMarkerParser3633);
        a=argument();

        state._fsp--;
        if (state.failed) return ;
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:782:59: ( COMMA min= numberExpression COMMA max= numberExpression )?
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
                // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:782:60: COMMA min= numberExpression COMMA max= numberExpression
                {
                match(input,COMMA,FOLLOW_COMMA_in_synpred12_TextMarkerParser3636); if (state.failed) return ;
                pushFollow(FOLLOW_numberExpression_in_synpred12_TextMarkerParser3642);
                min=numberExpression();

                state._fsp--;
                if (state.failed) return ;
                match(input,COMMA,FOLLOW_COMMA_in_synpred12_TextMarkerParser3644); if (state.failed) return ;
                pushFollow(FOLLOW_numberExpression_in_synpred12_TextMarkerParser3650);
                max=numberExpression();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:783:5: ( COMMA var= numberVariable )?
        int alt177=2;
        int LA177_0 = input.LA(1);

        if ( (LA177_0==COMMA) ) {
            alt177=1;
        }
        switch (alt177) {
            case 1 :
                // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:783:6: COMMA var= numberVariable
                {
                match(input,COMMA,FOLLOW_COMMA_in_synpred12_TextMarkerParser3660); if (state.failed) return ;
                pushFollow(FOLLOW_numberVariable_in_synpred12_TextMarkerParser3666);
                var=numberVariable();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        match(input,RPAREN,FOLLOW_RPAREN_in_synpred12_TextMarkerParser3670); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred12_TextMarkerParser

    // $ANTLR start synpred13_TextMarkerParser
    public final void synpred13_TextMarkerParser_fragment() throws RecognitionException {   
        StringListExpression list2 = null;


        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:807:20: (list2= stringListExpression )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:807:21: list2= stringListExpression
        {
        pushFollow(FOLLOW_stringListExpression_in_synpred13_TextMarkerParser3944);
        list2=stringListExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred13_TextMarkerParser

    // $ANTLR start synpred14_TextMarkerParser
    public final void synpred14_TextMarkerParser_fragment() throws RecognitionException {   
        AbstractTextMarkerAction a = null;


        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:977:4: (a= externalAction )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:977:5: a= externalAction
        {
        pushFollow(FOLLOW_externalAction_in_synpred14_TextMarkerParser5499);
        a=externalAction();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred14_TextMarkerParser

    // $ANTLR start synpred15_TextMarkerParser
    public final void synpred15_TextMarkerParser_fragment() throws RecognitionException {   
        NumberExpression index = null;


        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1012:5: (index= numberExpression )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1012:6: index= numberExpression
        {
        pushFollow(FOLLOW_numberExpression_in_synpred15_TextMarkerParser5652);
        index=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred15_TextMarkerParser

    // $ANTLR start synpred16_TextMarkerParser
    public final void synpred16_TextMarkerParser_fragment() throws RecognitionException {   
        NumberExpression index = null;


        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1012:81: ( COMMA index= numberExpression )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1012:82: COMMA index= numberExpression
        {
        match(input,COMMA,FOLLOW_COMMA_in_synpred16_TextMarkerParser5665); if (state.failed) return ;
        pushFollow(FOLLOW_numberExpression_in_synpred16_TextMarkerParser5671);
        index=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred16_TextMarkerParser

    // $ANTLR start synpred17_TextMarkerParser
    public final void synpred17_TextMarkerParser_fragment() throws RecognitionException {   
        NumberExpression index = null;


        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1044:6: (index= numberExpression )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1044:7: index= numberExpression
        {
        pushFollow(FOLLOW_numberExpression_in_synpred17_TextMarkerParser5964);
        index=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred17_TextMarkerParser

    // $ANTLR start synpred18_TextMarkerParser
    public final void synpred18_TextMarkerParser_fragment() throws RecognitionException {   
        NumberExpression index = null;


        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1044:82: ( COMMA index= numberExpression )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1044:83: COMMA index= numberExpression
        {
        match(input,COMMA,FOLLOW_COMMA_in_synpred18_TextMarkerParser5977); if (state.failed) return ;
        pushFollow(FOLLOW_numberExpression_in_synpred18_TextMarkerParser5983);
        index=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred18_TextMarkerParser

    // $ANTLR start synpred22_TextMarkerParser
    public final void synpred22_TextMarkerParser_fragment() throws RecognitionException {   
        NumberExpression score = null;


        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1147:22: (score= numberExpression )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1147:23: score= numberExpression
        {
        pushFollow(FOLLOW_numberExpression_in_synpred22_TextMarkerParser6788);
        score=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred22_TextMarkerParser

    // $ANTLR start synpred23_TextMarkerParser
    public final void synpred23_TextMarkerParser_fragment() throws RecognitionException {   
        TypeExpression type = null;


        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1147:85: (type= typeExpression )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1147:86: type= typeExpression
        {
        pushFollow(FOLLOW_typeExpression_in_synpred23_TextMarkerParser6808);
        type=typeExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred23_TextMarkerParser

    // $ANTLR start synpred25_TextMarkerParser
    public final void synpred25_TextMarkerParser_fragment() throws RecognitionException {   
        StringExpression a4 = null;


        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1381:2: (a4= stringExpression )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1381:2: a4= stringExpression
        {
        pushFollow(FOLLOW_stringExpression_in_synpred25_TextMarkerParser8744);
        a4=stringExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred25_TextMarkerParser

    // $ANTLR start synpred26_TextMarkerParser
    public final void synpred26_TextMarkerParser_fragment() throws RecognitionException {   
        BooleanExpression a2 = null;


        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1382:4: (a2= booleanExpression )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1382:4: a2= booleanExpression
        {
        pushFollow(FOLLOW_booleanExpression_in_synpred26_TextMarkerParser8755);
        a2=booleanExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred26_TextMarkerParser

    // $ANTLR start synpred27_TextMarkerParser
    public final void synpred27_TextMarkerParser_fragment() throws RecognitionException {   
        NumberExpression a3 = null;


        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1383:4: (a3= numberExpression )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1383:4: a3= numberExpression
        {
        pushFollow(FOLLOW_numberExpression_in_synpred27_TextMarkerParser8766);
        a3=numberExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred27_TextMarkerParser

    // $ANTLR start synpred28_TextMarkerParser
    public final void synpred28_TextMarkerParser_fragment() throws RecognitionException {   
        NumberExpression e = null;


        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1462:4: (e= externalNumberFunction )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1462:5: e= externalNumberFunction
        {
        pushFollow(FOLLOW_externalNumberFunction_in_synpred28_TextMarkerParser9125);
        e=externalNumberFunction();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred28_TextMarkerParser

    // $ANTLR start synpred30_TextMarkerParser
    public final void synpred30_TextMarkerParser_fragment() throws RecognitionException {   
        StringExpression e = null;


        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1535:3: (e= stringFunction )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1535:4: e= stringFunction
        {
        pushFollow(FOLLOW_stringFunction_in_synpred30_TextMarkerParser9590);
        e=stringFunction();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred30_TextMarkerParser

    // $ANTLR start synpred31_TextMarkerParser
    public final void synpred31_TextMarkerParser_fragment() throws RecognitionException {   
        StringExpression e = null;


        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1545:2: (e= externalStringFunction )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1545:3: e= externalStringFunction
        {
        pushFollow(FOLLOW_externalStringFunction_in_synpred31_TextMarkerParser9662);
        e=externalStringFunction();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred31_TextMarkerParser

    // $ANTLR start synpred32_TextMarkerParser
    public final void synpred32_TextMarkerParser_fragment() throws RecognitionException {   
        BooleanExpression e = null;


        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1568:2: (e= composedBooleanExpression )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1568:3: e= composedBooleanExpression
        {
        pushFollow(FOLLOW_composedBooleanExpression_in_synpred32_TextMarkerParser9769);
        e=composedBooleanExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred32_TextMarkerParser

    // $ANTLR start synpred33_TextMarkerParser
    public final void synpred33_TextMarkerParser_fragment() throws RecognitionException {   
        BooleanExpression e2 = null;


        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1583:2: (e2= booleanCompare )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1583:3: e2= booleanCompare
        {
        pushFollow(FOLLOW_booleanCompare_in_synpred33_TextMarkerParser9852);
        e2=booleanCompare();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred33_TextMarkerParser

    // $ANTLR start synpred34_TextMarkerParser
    public final void synpred34_TextMarkerParser_fragment() throws RecognitionException {   
        BooleanExpression bte = null;


        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1584:4: (bte= booleanTypeExpression )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1584:5: bte= booleanTypeExpression
        {
        pushFollow(FOLLOW_booleanTypeExpression_in_synpred34_TextMarkerParser9872);
        bte=booleanTypeExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred34_TextMarkerParser

    // $ANTLR start synpred35_TextMarkerParser
    public final void synpred35_TextMarkerParser_fragment() throws RecognitionException {   
        BooleanExpression bne = null;


        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1585:4: (bne= booleanNumberExpression )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1585:5: bne= booleanNumberExpression
        {
        pushFollow(FOLLOW_booleanNumberExpression_in_synpred35_TextMarkerParser9891);
        bne=booleanNumberExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred35_TextMarkerParser

    // $ANTLR start synpred36_TextMarkerParser
    public final void synpred36_TextMarkerParser_fragment() throws RecognitionException {   
        BooleanExpression e = null;


        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1595:4: (e= externalBooleanFunction )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.engine\\src\\de\\uniwue\\tm\\textmarker\\parser\\TextMarkerParser.g:1595:5: e= externalBooleanFunction
        {
        pushFollow(FOLLOW_externalBooleanFunction_in_synpred36_TextMarkerParser9966);
        e=externalBooleanFunction();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred36_TextMarkerParser

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
    protected DFA71 dfa71 = new DFA71(this);
    protected DFA105 dfa105 = new DFA105(this);
    protected DFA107 dfa107 = new DFA107(this);
    protected DFA114 dfa114 = new DFA114(this);
    protected DFA149 dfa149 = new DFA149(this);
    protected DFA171 dfa171 = new DFA171(this);
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
            return "336:2: ( DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* SEMI | DECLARE type= annotationType newName= Identifier ( LPAREN (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString ) fname= Identifier )* RPAREN ) SEMI )";
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
            return "703:2: (c= conditionAnd | c= conditionContains | c= conditionContextCount | c= conditionCount | c= conditionCurrentCount | c= conditionInList | c= conditionIsInTag | c= conditionLast | c= conditionMofN | c= conditionNear | c= conditionNot | c= conditionOr | c= conditionPartOf | c= conditionPosition | c= conditionRegExp | c= conditionScore | c= conditionTotalCount | c= conditionVote | c= conditionIf | c= conditionFeature | c= conditionParse | c= conditionIs | c= conditionBefore | c= conditionAfter | c= conditionStartsWith | c= conditionEndsWith | c= conditionPartOfNeq | c= conditionSize | (c= externalCondition )=>c= externalCondition | c= variableCondition )";
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
                        if ( (LA71_29==LPAREN) && (synpred11_TextMarkerParser())) {s = 30;}

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
            "\26\1\6\1\32\1\7\1\10\1\13\1\14\1\11\1\12\1\17\1\31\1\20\1\21"+
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
            return "941:2: (a= actionColor | a= actionDel | a= actionLog | a= actionMark | a= actionMarkScore | a= actionMarkFast | a= actionMarkLast | a= actionReplace | a= actionFilterMarkup | a= actionFilterType | a= actionRetainMarkup | a= actionRetainType | a= actionCreate | a= actionFill | a= actionCall | a= actionAssign | a= actionSetFeature | a= actionGetFeature | a= actionUnmark | a= actionUnmarkAll | a= actionTransfer | a= actionMarkOnce | a= actionTrie | a= actionGather | a= actionExec | a= actionMarkTable | a= actionAdd | a= actionRemove | a= actionRemoveDuplicate | a= actionMerge | a= actionGet | a= actionGetList | a= actionMatchedText | a= actionClear | a= actionExpand | (a= externalAction )=>a= externalAction | a= variableAction )";
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
                        if ( (LA105_36==LPAREN) && (synpred14_TextMarkerParser())) {s = 37;}

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
        "\1\111\3\uffff\1\0\5\uffff";
    static final String DFA107_maxS =
        "\1\u0088\3\uffff\1\0\5\uffff";
    static final String DFA107_acceptS =
        "\1\uffff\3\1\1\uffff\2\1\1\2\2\uffff";
    static final String DFA107_specialS =
        "\1\0\3\uffff\1\1\5\uffff}>";
    static final String[] DFA107_transitionS = {
            "\1\7\30\uffff\5\6\6\uffff\1\2\3\uffff\1\3\2\uffff\1\7\5\uffff"+
            "\1\4\1\5\1\7\13\uffff\1\1",
            "",
            "",
            "",
            "\1\uffff",
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
            return "1011:5: ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA107_0 = input.LA(1);

                         
                        int index107_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA107_0==MINUS) && (synpred15_TextMarkerParser())) {s = 1;}

                        else if ( (LA107_0==DecimalLiteral) && (synpred15_TextMarkerParser())) {s = 2;}

                        else if ( (LA107_0==FloatingPointLiteral) && (synpred15_TextMarkerParser())) {s = 3;}

                        else if ( (LA107_0==Identifier) ) {s = 4;}

                        else if ( (LA107_0==LPAREN) && (synpred15_TextMarkerParser())) {s = 5;}

                        else if ( ((LA107_0>=EXP && LA107_0<=TAN)) && (synpred15_TextMarkerParser())) {s = 6;}

                        else if ( (LA107_0==REMOVESTRING||LA107_0==StringLiteral||LA107_0==RPAREN) ) {s = 7;}

                         
                        input.seek(index107_0);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA107_4 = input.LA(1);

                         
                        int index107_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (((synpred15_TextMarkerParser()&&(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INT")))||synpred15_TextMarkerParser()||(synpred15_TextMarkerParser()&&(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLE"))))) ) {s = 6;}

                        else if ( (true) ) {s = 7;}

                         
                        input.seek(index107_4);
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
        "\1\111\3\uffff\1\0\5\uffff";
    static final String DFA114_maxS =
        "\1\u0088\3\uffff\1\0\5\uffff";
    static final String DFA114_acceptS =
        "\1\uffff\3\1\1\uffff\2\1\1\2\2\uffff";
    static final String DFA114_specialS =
        "\1\0\3\uffff\1\1\5\uffff}>";
    static final String[] DFA114_transitionS = {
            "\1\7\30\uffff\5\6\6\uffff\1\2\3\uffff\1\3\2\uffff\1\7\5\uffff"+
            "\1\4\1\5\1\7\13\uffff\1\1",
            "",
            "",
            "",
            "\1\uffff",
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
            return "1044:5: ( (index= numberExpression )=>index= numberExpression ( ( COMMA index= numberExpression )=> ( COMMA index= numberExpression ) )* COMMA )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA114_0 = input.LA(1);

                         
                        int index114_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA114_0==MINUS) && (synpred17_TextMarkerParser())) {s = 1;}

                        else if ( (LA114_0==DecimalLiteral) && (synpred17_TextMarkerParser())) {s = 2;}

                        else if ( (LA114_0==FloatingPointLiteral) && (synpred17_TextMarkerParser())) {s = 3;}

                        else if ( (LA114_0==Identifier) ) {s = 4;}

                        else if ( (LA114_0==LPAREN) && (synpred17_TextMarkerParser())) {s = 5;}

                        else if ( ((LA114_0>=EXP && LA114_0<=TAN)) && (synpred17_TextMarkerParser())) {s = 6;}

                        else if ( (LA114_0==REMOVESTRING||LA114_0==StringLiteral||LA114_0==RPAREN) ) {s = 7;}

                         
                        input.seek(index114_0);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA114_4 = input.LA(1);

                         
                        int index114_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((synpred17_TextMarkerParser()||(synpred17_TextMarkerParser()&&(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLE")))||(synpred17_TextMarkerParser()&&(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INT"))))) ) {s = 6;}

                        else if ( (true) ) {s = 7;}

                         
                        input.seek(index114_4);
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
    static final String DFA149_eotS =
        "\16\uffff";
    static final String DFA149_eofS =
        "\16\uffff";
    static final String DFA149_minS =
        "\1\111\1\uffff\1\0\3\uffff\2\0\6\uffff";
    static final String DFA149_maxS =
        "\1\u0088\1\uffff\1\0\3\uffff\2\0\6\uffff";
    static final String DFA149_acceptS =
        "\1\uffff\1\1\2\uffff\1\2\4\uffff\1\3\3\uffff\1\4";
    static final String DFA149_specialS =
        "\2\uffff\1\0\3\uffff\1\1\1\2\6\uffff}>";
    static final String[] DFA149_transitionS = {
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

    static final short[] DFA149_eot = DFA.unpackEncodedString(DFA149_eotS);
    static final short[] DFA149_eof = DFA.unpackEncodedString(DFA149_eofS);
    static final char[] DFA149_min = DFA.unpackEncodedStringToUnsignedChars(DFA149_minS);
    static final char[] DFA149_max = DFA.unpackEncodedStringToUnsignedChars(DFA149_maxS);
    static final short[] DFA149_accept = DFA.unpackEncodedString(DFA149_acceptS);
    static final short[] DFA149_special = DFA.unpackEncodedString(DFA149_specialS);
    static final short[][] DFA149_transition;

    static {
        int numStates = DFA149_transitionS.length;
        DFA149_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA149_transition[i] = DFA.unpackEncodedString(DFA149_transitionS[i]);
        }
    }

    class DFA149 extends DFA {

        public DFA149(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 149;
            this.eot = DFA149_eot;
            this.eof = DFA149_eof;
            this.min = DFA149_min;
            this.max = DFA149_max;
            this.accept = DFA149_accept;
            this.special = DFA149_special;
            this.transition = DFA149_transition;
        }
        public String getDescription() {
            return "1376:1: argument returns [TextMarkerExpression expr = null] options {backtrack=true; } : (a4= stringExpression | a2= booleanExpression | a3= numberExpression | a1= typeExpression );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA149_2 = input.LA(1);

                         
                        int index149_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((synpred25_TextMarkerParser()||(synpred25_TextMarkerParser()&&(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "STRING"))))) ) {s = 1;}

                        else if ( ((synpred26_TextMarkerParser()||(synpred26_TextMarkerParser()&&(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "BOOLEAN")))||(synpred26_TextMarkerParser()&&(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "TYPE")))||(synpred26_TextMarkerParser()&&(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "BOOLEAN"))))) ) {s = 4;}

                        else if ( (((synpred27_TextMarkerParser()&&(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "DOUBLE")))||synpred27_TextMarkerParser()||(synpred27_TextMarkerParser()&&(isVariableOfType(((blockDeclaration_scope)blockDeclaration_stack.peek()).env,input.LT(1).getText(), "INT"))))) ) {s = 9;}

                        else if ( (true) ) {s = 13;}

                         
                        input.seek(index149_2);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA149_6 = input.LA(1);

                         
                        int index149_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred26_TextMarkerParser()) ) {s = 4;}

                        else if ( (true) ) {s = 13;}

                         
                        input.seek(index149_6);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA149_7 = input.LA(1);

                         
                        int index149_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred26_TextMarkerParser()) ) {s = 4;}

                        else if ( (synpred27_TextMarkerParser()) ) {s = 9;}

                         
                        input.seek(index149_7);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 149, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA171_eotS =
        "\14\uffff";
    static final String DFA171_eofS =
        "\1\uffff\3\10\10\uffff";
    static final String DFA171_minS =
        "\1\114\2\174\1\173\10\uffff";
    static final String DFA171_maxS =
        "\1\173\3\u0093\10\uffff";
    static final String DFA171_acceptS =
        "\4\uffff\4\1\1\2\3\1";
    static final String DFA171_specialS =
        "\1\3\1\0\1\2\1\1\10\uffff}>";
    static final String[] DFA171_transitionS = {
            "\1\4\32\uffff\1\6\1\1\1\2\20\uffff\1\3\1\5",
            "\1\10\10\uffff\2\10\13\uffff\2\7",
            "\1\10\10\uffff\2\10\13\uffff\2\7",
            "\1\11\1\10\6\uffff\1\12\1\uffff\2\10\13\uffff\2\13",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA171_eot = DFA.unpackEncodedString(DFA171_eotS);
    static final short[] DFA171_eof = DFA.unpackEncodedString(DFA171_eofS);
    static final char[] DFA171_min = DFA.unpackEncodedStringToUnsignedChars(DFA171_minS);
    static final char[] DFA171_max = DFA.unpackEncodedStringToUnsignedChars(DFA171_maxS);
    static final short[] DFA171_accept = DFA.unpackEncodedString(DFA171_acceptS);
    static final short[] DFA171_special = DFA.unpackEncodedString(DFA171_specialS);
    static final short[][] DFA171_transition;

    static {
        int numStates = DFA171_transitionS.length;
        DFA171_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA171_transition[i] = DFA.unpackEncodedString(DFA171_transitionS[i]);
        }
    }

    class DFA171 extends DFA {

        public DFA171(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 171;
            this.eot = DFA171_eot;
            this.eof = DFA171_eof;
            this.min = DFA171_min;
            this.max = DFA171_max;
            this.accept = DFA171_accept;
            this.special = DFA171_special;
            this.transition = DFA171_transition;
        }
        public String getDescription() {
            return "1566:1: booleanExpression returns [BooleanExpression expr = null] : ( (e= composedBooleanExpression )=>e= composedBooleanExpression | sbE= simpleBooleanExpression );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA171_1 = input.LA(1);

                         
                        int index171_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA171_1>=EQUAL && LA171_1<=NOTEQUAL)) && (synpred32_TextMarkerParser())) {s = 7;}

                        else if ( (LA171_1==EOF||LA171_1==RPAREN||(LA171_1>=COMMA && LA171_1<=SEMI)) ) {s = 8;}

                         
                        input.seek(index171_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA171_3 = input.LA(1);

                         
                        int index171_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA171_3==LPAREN) && (synpred32_TextMarkerParser())) {s = 9;}

                        else if ( (LA171_3==DOT) && (synpred32_TextMarkerParser())) {s = 10;}

                        else if ( ((LA171_3>=EQUAL && LA171_3<=NOTEQUAL)) && (synpred32_TextMarkerParser())) {s = 11;}

                        else if ( (LA171_3==EOF||LA171_3==RPAREN||(LA171_3>=COMMA && LA171_3<=SEMI)) ) {s = 8;}

                         
                        input.seek(index171_3);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA171_2 = input.LA(1);

                         
                        int index171_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((LA171_2>=EQUAL && LA171_2<=NOTEQUAL)) && (synpred32_TextMarkerParser())) {s = 7;}

                        else if ( (LA171_2==EOF||LA171_2==RPAREN||(LA171_2>=COMMA && LA171_2<=SEMI)) ) {s = 8;}

                         
                        input.seek(index171_2);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA171_0 = input.LA(1);

                         
                        int index171_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA171_0==TRUE) ) {s = 1;}

                        else if ( (LA171_0==FALSE) ) {s = 2;}

                        else if ( (LA171_0==Identifier) ) {s = 3;}

                        else if ( (LA171_0==BasicAnnotationType) && (synpred32_TextMarkerParser())) {s = 4;}

                        else if ( (LA171_0==LPAREN) && (synpred32_TextMarkerParser())) {s = 5;}

                        else if ( (LA171_0==XOR) && (synpred32_TextMarkerParser())) {s = 6;}

                         
                        input.seek(index171_0);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 171, _s, input);
            error(nvae);
            throw nvae;
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
    public static final BitSet FOLLOW_LCURLY_in_blockDeclaration1406 = new BitSet(new long[]{0x0000000000000E00L,0x0C100003F9FC1000L,0x0000000000000001L});
    public static final BitSet FOLLOW_statements_in_blockDeclaration1412 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RCURLY_in_blockDeclaration1414 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AutomataBlockString_in_automataDeclaration1466 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_automataDeclaration1470 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_automataDeclaration1477 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_automataDeclaration1481 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_ruleElementWithCA_in_automataDeclaration1494 = new BitSet(new long[]{0x0000000000000000L,0x8000000000000000L});
    public static final BitSet FOLLOW_LCURLY_in_automataDeclaration1502 = new BitSet(new long[]{0x0000000000000E00L,0x0C100003F9FC1000L,0x0000000000000001L});
    public static final BitSet FOLLOW_statements_in_automataDeclaration1508 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RCURLY_in_automataDeclaration1510 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_ruleElementWithCA1541 = new BitSet(new long[]{0x0000000000000000L,0xA000000000000000L,0x0000000000020280L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementWithCA1547 = new BitSet(new long[]{0x0000000000000000L,0x8000000000000000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleElementWithCA1559 = new BitSet(new long[]{0xF8000000FFFFF100L,0x0400000000000881L,0x0000000000000101L});
    public static final BitSet FOLLOW_conditions_in_ruleElementWithCA1565 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L,0x0000000000000001L});
    public static final BitSet FOLLOW_THEN_in_ruleElementWithCA1569 = new BitSet(new long[]{0x07FFFFF700000080L,0x040000000000057EL});
    public static final BitSet FOLLOW_actions_in_ruleElementWithCA1575 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RCURLY_in_ruleElementWithCA1579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_ruleElementWithoutCA1619 = new BitSet(new long[]{0x0000000000000002L,0x2000000000000000L,0x0000000000020280L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementWithoutCA1625 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElements_in_simpleStatement1669 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_SEMI_in_simpleStatement1671 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElement_in_ruleElements1697 = new BitSet(new long[]{0x0000000000000002L,0x0C10000000001000L});
    public static final BitSet FOLLOW_ruleElement_in_ruleElements1706 = new BitSet(new long[]{0x0000000000000002L,0x0C10000000001000L});
    public static final BitSet FOLLOW_ruleElementType_in_blockRuleElement1731 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementType_in_ruleElement1755 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementLiteral_in_ruleElement1766 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementComposed_in_ruleElement1777 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_ruleElementComposed1797 = new BitSet(new long[]{0x0000000000000000L,0x0C10000000001000L});
    public static final BitSet FOLLOW_ruleElements_in_ruleElementComposed1803 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_ruleElementComposed1805 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L,0x0000000000020280L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementComposed1811 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_ruleElementType1849 = new BitSet(new long[]{0x0000000000000002L,0xA000000000000000L,0x0000000000020280L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementType1855 = new BitSet(new long[]{0x0000000000000002L,0x8000000000000000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleElementType1868 = new BitSet(new long[]{0xF8000000FFFFF100L,0x0400000000000881L,0x0000000000000101L});
    public static final BitSet FOLLOW_conditions_in_ruleElementType1874 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L,0x0000000000000001L});
    public static final BitSet FOLLOW_THEN_in_ruleElementType1878 = new BitSet(new long[]{0x07FFFFF700000080L,0x040000000000057EL});
    public static final BitSet FOLLOW_actions_in_ruleElementType1884 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RCURLY_in_ruleElementType1888 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleStringExpression_in_ruleElementLiteral1932 = new BitSet(new long[]{0x0000000000000002L,0xA000000000000000L,0x0000000000020280L});
    public static final BitSet FOLLOW_quantifierPart_in_ruleElementLiteral1938 = new BitSet(new long[]{0x0000000000000002L,0x8000000000000000L});
    public static final BitSet FOLLOW_LCURLY_in_ruleElementLiteral1951 = new BitSet(new long[]{0xF8000000FFFFF100L,0x0400000000000881L,0x0000000000000101L});
    public static final BitSet FOLLOW_conditions_in_ruleElementLiteral1957 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L,0x0000000000000001L});
    public static final BitSet FOLLOW_THEN_in_ruleElementLiteral1961 = new BitSet(new long[]{0x07FFFFF700000080L,0x040000000000057EL});
    public static final BitSet FOLLOW_actions_in_ruleElementLiteral1967 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_RCURLY_in_ruleElementLiteral1971 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_condition_in_conditions2010 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditions2015 = new BitSet(new long[]{0xF8000000FFFFF100L,0x0400000000000081L,0x0000000000000100L});
    public static final BitSet FOLLOW_condition_in_conditions2021 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_action_in_actions2059 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actions2064 = new BitSet(new long[]{0x07FFFFF700000080L,0x040000000000057EL});
    public static final BitSet FOLLOW_action_in_actions2070 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_booleanListExpression_in_listExpression2106 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_intListExpression_in_listExpression2122 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_doubleListExpression_in_listExpression2138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringListExpression_in_listExpression2154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeListExpression_in_listExpression2170 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleBooleanListExpression_in_booleanListExpression2192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleBooleanListExpression2213 = new BitSet(new long[]{0x0000000000000000L,0x0400030000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_simpleBooleanListExpression2220 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_COMMA_in_simpleBooleanListExpression2225 = new BitSet(new long[]{0x0000000000000000L,0x0400030000000000L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_simpleBooleanListExpression2231 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_RCURLY_in_simpleBooleanListExpression2240 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleBooleanListExpression2255 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleIntListExpression_in_intListExpression2280 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleIntListExpression2301 = new BitSet(new long[]{0x0000000000000000L,0x0C02200000000000L,0x0000000000000101L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleIntListExpression2308 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_COMMA_in_simpleIntListExpression2313 = new BitSet(new long[]{0x0000000000000000L,0x0C02200000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleIntListExpression2319 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_RCURLY_in_simpleIntListExpression2328 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleIntListExpression2343 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_doubleListExpression_in_numberListExpression2377 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_intListExpression_in_numberListExpression2389 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleDoubleListExpression_in_doubleListExpression2412 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleDoubleListExpression2433 = new BitSet(new long[]{0x0000000000000000L,0x0C02200000000000L,0x0000000000000101L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleDoubleListExpression2440 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_COMMA_in_simpleDoubleListExpression2445 = new BitSet(new long[]{0x0000000000000000L,0x0C02200000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_simpleDoubleListExpression2451 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_RCURLY_in_simpleDoubleListExpression2460 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleDoubleListExpression2475 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleStringListExpression_in_stringListExpression2500 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleStringListExpression2521 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_simpleStringExpression_in_simpleStringListExpression2528 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_COMMA_in_simpleStringListExpression2533 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000000L});
    public static final BitSet FOLLOW_simpleStringExpression_in_simpleStringListExpression2539 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_RCURLY_in_simpleStringListExpression2548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleStringListExpression2564 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleTypeListExpression_in_typeListExpression2589 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_simpleTypeListExpression2610 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L,0x0000000000000001L});
    public static final BitSet FOLLOW_simpleTypeExpression_in_simpleTypeListExpression2617 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_COMMA_in_simpleTypeListExpression2622 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_simpleTypeExpression_in_simpleTypeListExpression2628 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000021L});
    public static final BitSet FOLLOW_RCURLY_in_simpleTypeListExpression2637 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleTypeListExpression2652 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeFunction_in_typeExpression2689 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleTypeExpression_in_typeExpression2700 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalTypeFunction_in_typeFunction2734 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalTypeFunction2759 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_externalTypeFunction2761 = new BitSet(new long[]{0x0000000000000000L,0x1800000000000000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalTypeFunction2768 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalTypeFunction2770 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleTypeExpression2795 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotationType_in_simpleTypeExpression2809 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_variable2835 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_listVariable2859 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAR_in_quantifierPart2893 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart2899 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_quantifierPart2910 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart2916 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart2926 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart2932 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACK_in_quantifierPart2943 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_quantifierPart2949 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_quantifierPart2956 = new BitSet(new long[]{0x0000000000000000L,0x4C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_quantifierPart2963 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000000L});
    public static final BitSet FOLLOW_RBRACK_in_quantifierPart2969 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_QUESTION_in_quantifierPart2975 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionAnd_in_condition3005 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionContains_in_condition3014 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionContextCount_in_condition3023 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionCount_in_condition3032 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionCurrentCount_in_condition3041 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionInList_in_condition3050 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionIsInTag_in_condition3059 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionLast_in_condition3068 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionMofN_in_condition3077 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionNear_in_condition3086 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionNot_in_condition3095 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionOr_in_condition3104 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionPartOf_in_condition3113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionPosition_in_condition3122 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionRegExp_in_condition3131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionScore_in_condition3140 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionTotalCount_in_condition3149 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionVote_in_condition3158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionIf_in_condition3167 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionFeature_in_condition3176 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionParse_in_condition3185 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionIs_in_condition3194 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionBefore_in_condition3203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionAfter_in_condition3212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionStartsWith_in_condition3222 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionEndsWith_in_condition3231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionPartOfNeq_in_condition3240 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionSize_in_condition3249 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalCondition_in_condition3268 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableCondition_in_condition3277 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_variableCondition3307 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalCondition3334 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_externalCondition3336 = new BitSet(new long[]{0x0000000000000000L,0x1800000000000000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalCondition3342 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalCondition3344 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AND_in_conditionAnd3373 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionAnd3375 = new BitSet(new long[]{0xF8000000FFFFF100L,0x0400000000000081L,0x0000000000000100L});
    public static final BitSet FOLLOW_conditions_in_conditionAnd3381 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionAnd3383 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONTAINS_in_conditionContains3430 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionContains3432 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionContains3439 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_listExpression_in_conditionContains3447 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionContains3449 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_conditionContains3455 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionContains3464 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionContains3470 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionContains3472 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionContains3478 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionContains3481 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionContains3487 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionContains3493 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONTEXTCOUNT_in_conditionContextCount3526 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionContextCount3528 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionContextCount3534 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionContextCount3537 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionContextCount3543 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionContextCount3545 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionContextCount3551 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionContextCount3561 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberVariable_in_conditionContextCount3567 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionContextCount3571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COUNT_in_conditionCount3617 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionCount3619 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_listExpression_in_conditionCount3625 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount3627 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_conditionCount3633 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount3636 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCount3642 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount3644 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCount3650 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount3660 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberVariable_in_conditionCount3666 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionCount3670 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COUNT_in_conditionCount3688 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionCount3690 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionCount3696 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount3699 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCount3705 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount3707 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCount3713 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionCount3723 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberVariable_in_conditionCount3729 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionCount3733 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOTALCOUNT_in_conditionTotalCount3769 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionTotalCount3771 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionTotalCount3777 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionTotalCount3780 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionTotalCount3786 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionTotalCount3788 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionTotalCount3794 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionTotalCount3803 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberVariable_in_conditionTotalCount3809 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionTotalCount3813 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CURRENTCOUNT_in_conditionCurrentCount3846 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionCurrentCount3848 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionCurrentCount3854 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionCurrentCount3857 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCurrentCount3863 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionCurrentCount3865 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionCurrentCount3871 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionCurrentCount3881 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberVariable_in_conditionCurrentCount3887 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionCurrentCount3891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INLIST_in_conditionInList3934 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionInList3936 = new BitSet(new long[]{0x0000000000000000L,0x8420000000000000L});
    public static final BitSet FOLLOW_stringListExpression_in_conditionInList3951 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_wordListExpression_in_conditionInList3959 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionInList3963 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionInList3969 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionInList3972 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionInList3978 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionInList3984 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ISINTAG_in_conditionIsInTag4019 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionIsInTag4021 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_conditionIsInTag4027 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionIsInTag4030 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_conditionIsInTag4036 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_conditionIsInTag4038 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_conditionIsInTag4044 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_conditionIsInTag4050 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LAST_in_conditionLast4089 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionLast4091 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionLast4097 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionLast4099 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MOFN_in_conditionMofN4146 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionMofN4148 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionMofN4154 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionMofN4156 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionMofN4162 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionMofN4164 = new BitSet(new long[]{0xF8000000FFFFF100L,0x0400000000000081L,0x0000000000000100L});
    public static final BitSet FOLLOW_conditions_in_conditionMofN4170 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionMofN4172 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEAR_in_conditionNear4207 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionNear4209 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionNear4215 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionNear4217 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionNear4223 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionNear4225 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionNear4231 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionNear4234 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionNear4240 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionNear4243 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionNear4249 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionNear4255 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_conditionNot4291 = new BitSet(new long[]{0xF8000000FFFFF100L,0x0400000000000081L,0x0000000000000100L});
    public static final BitSet FOLLOW_condition_in_conditionNot4297 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_conditionNot4304 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionNot4306 = new BitSet(new long[]{0xF8000000FFFFF100L,0x0400000000000081L,0x0000000000000100L});
    public static final BitSet FOLLOW_condition_in_conditionNot4312 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionNot4314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OR_in_conditionOr4353 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionOr4355 = new BitSet(new long[]{0xF8000000FFFFF100L,0x0400000000000081L,0x0000000000000100L});
    public static final BitSet FOLLOW_conditions_in_conditionOr4361 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionOr4363 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARTOF_in_conditionPartOf4393 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionPartOf4395 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionPartOf4402 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionPartOf4408 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionPartOf4411 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARTOFNEQ_in_conditionPartOfNeq4441 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionPartOfNeq4443 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionPartOfNeq4450 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionPartOfNeq4456 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionPartOfNeq4459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_POSITION_in_conditionPosition4493 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionPosition4495 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionPosition4501 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionPosition4503 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionPosition4509 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionPosition4511 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REGEXP_in_conditionRegExp4541 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionRegExp4543 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_conditionRegExp4549 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionRegExp4552 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionRegExp4558 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionRegExp4562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SCORE_in_conditionScore4597 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionScore4599 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionScore4605 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionScore4608 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionScore4614 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionScore4621 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberVariable_in_conditionScore4627 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionScore4634 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VOTE_in_conditionVote4669 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionVote4671 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionVote4677 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionVote4679 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionVote4685 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionVote4687 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_conditionIf4725 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionIf4727 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_conditionIf4733 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionIf4735 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FEATURE_in_conditionFeature4769 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionFeature4771 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_conditionFeature4777 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionFeature4779 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_conditionFeature4785 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionFeature4787 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARSE_in_conditionParse4821 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionParse4823 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_conditionParse4831 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionParse4833 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IS_in_conditionIs4864 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionIs4866 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionIs4873 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionIs4879 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionIs4882 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BEFORE_in_conditionBefore4913 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionBefore4915 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionBefore4922 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionBefore4928 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionBefore4931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AFTER_in_conditionAfter4962 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionAfter4964 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionAfter4971 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionAfter4977 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionAfter4980 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STARTSWITH_in_conditionStartsWith5011 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionStartsWith5013 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionStartsWith5020 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionStartsWith5026 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionStartsWith5029 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ENDSWITH_in_conditionEndsWith5060 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionEndsWith5062 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_conditionEndsWith5069 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_conditionEndsWith5075 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionEndsWith5078 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SIZE_in_conditionSize5109 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditionSize5111 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_listExpression_in_conditionSize5117 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionSize5120 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionSize5126 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionSize5128 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_conditionSize5134 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_conditionSize5139 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberVariable_in_conditionSize5145 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditionSize5149 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionColor_in_action5181 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionDel_in_action5190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionLog_in_action5199 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMark_in_action5208 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMarkScore_in_action5217 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMarkFast_in_action5226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMarkLast_in_action5235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionReplace_in_action5244 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionFilterMarkup_in_action5253 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionFilterType_in_action5262 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionRetainMarkup_in_action5271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionRetainType_in_action5280 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionCreate_in_action5289 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionFill_in_action5298 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionCall_in_action5307 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionAssign_in_action5316 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionSetFeature_in_action5325 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionGetFeature_in_action5334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionUnmark_in_action5343 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionUnmarkAll_in_action5352 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionTransfer_in_action5361 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMarkOnce_in_action5370 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionTrie_in_action5379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionGather_in_action5388 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionExec_in_action5398 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMarkTable_in_action5407 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionAdd_in_action5417 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionRemove_in_action5426 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionRemoveDuplicate_in_action5435 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMerge_in_action5444 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionGet_in_action5453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionGetList_in_action5462 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionMatchedText_in_action5471 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionClear_in_action5480 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionExpand_in_action5489 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalAction_in_action5507 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableAction_in_action5516 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_variableAction5545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalAction5573 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_externalAction5575 = new BitSet(new long[]{0x0000000000000000L,0x1800000000000000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalAction5581 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalAction5583 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CREATE_in_actionCreate5619 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionCreate5621 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionCreate5627 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionCreate5634 = new BitSet(new long[]{0x0000000000000000L,0x1C12207C00000200L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionCreate5659 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionCreate5676 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionCreate5682 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionCreate5689 = new BitSet(new long[]{0x0000000000000000L,0x1410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionCreate5702 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionCreate5704 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_actionCreate5710 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionCreate5720 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionCreate5726 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionCreate5728 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_actionCreate5734 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionCreate5749 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKTABLE_in_actionMarkTable5790 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkTable5792 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkTable5803 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkTable5805 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkTable5816 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkTable5818 = new BitSet(new long[]{0x0000000000000000L,0x0420000000000000L});
    public static final BitSet FOLLOW_wordTableExpression_in_actionMarkTable5828 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkTable5836 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionMarkTable5850 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionMarkTable5852 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkTable5858 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkTable5868 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionMarkTable5874 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionMarkTable5876 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkTable5882 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkTable5895 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GATHER_in_actionGather5936 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionGather5938 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionGather5944 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionGather5951 = new BitSet(new long[]{0x0000000000000000L,0x1C12207C00000200L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionGather5971 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionGather5987 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionGather5993 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionGather6000 = new BitSet(new long[]{0x0000000000000000L,0x1410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionGather6013 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionGather6015 = new BitSet(new long[]{0x0000000000000000L,0x8C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionGather6022 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_numberListExpression_in_actionGather6030 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionGather6041 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionGather6047 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionGather6049 = new BitSet(new long[]{0x0000000000000000L,0x8C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionGather6056 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_numberListExpression_in_actionGather6064 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionGather6080 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILL_in_actionFill6122 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionFill6124 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionFill6130 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionFill6133 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionFill6139 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionFill6141 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_actionFill6158 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionFill6175 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLOR_in_actionColor6213 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionColor6215 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionColor6221 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionColor6233 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionColor6244 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionColor6252 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionColor6262 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionColor6270 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionColor6280 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionColor6290 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEL_in_actionDel6324 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LOG_in_actionLog6366 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionLog6368 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionLog6374 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionLog6377 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_LogLevel_in_actionLog6383 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionLog6387 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARK_in_actionMark6426 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMark6428 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionMark6439 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMark6455 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionMark6471 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionMark6495 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXPAND_in_actionExpand6539 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionExpand6541 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionExpand6552 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionExpand6568 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionExpand6584 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionExpand6608 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKSCORE_in_actionMarkScore6653 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkScore6655 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkScore6666 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkScore6668 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkScore6678 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkScore6694 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkScore6710 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkScore6734 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKONCE_in_actionMarkOnce6778 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkOnce6780 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00001000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkOnce6797 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkOnce6799 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkOnce6817 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkOnce6833 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkOnce6849 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkOnce6868 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKFAST_in_actionMarkFast6907 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkFast6909 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkFast6915 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkFast6917 = new BitSet(new long[]{0x0000000000000000L,0x0420000000000000L});
    public static final BitSet FOLLOW_wordListExpression_in_actionMarkFast6923 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkFast6926 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionMarkFast6932 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMarkFast6935 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionMarkFast6941 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkFast6947 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MARKLAST_in_actionMarkLast6981 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMarkLast6983 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionMarkLast6989 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionMarkLast6991 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REPLACE_in_actionReplace7025 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionReplace7027 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionReplace7033 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionReplace7035 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RETAINMARKUP_in_actionRetainMarkup7078 = new BitSet(new long[]{0x0000000000000002L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionRetainMarkup7081 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionRetainMarkup7087 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionRetainMarkup7092 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionRetainMarkup7098 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionRetainMarkup7104 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RETAINTYPE_in_actionRetainType7150 = new BitSet(new long[]{0x0000000000000002L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionRetainType7153 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionRetainType7159 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionRetainType7164 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionRetainType7170 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionRetainType7176 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILTERMARKUP_in_actionFilterMarkup7224 = new BitSet(new long[]{0x0000000000000002L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionFilterMarkup7227 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionFilterMarkup7233 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionFilterMarkup7238 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionFilterMarkup7244 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionFilterMarkup7250 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILTERTYPE_in_actionFilterType7296 = new BitSet(new long[]{0x0000000000000002L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionFilterType7299 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionFilterType7305 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionFilterType7310 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionFilterType7316 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionFilterType7322 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CALL_in_actionCall7362 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionCall7364 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_dottedIdentifier_in_actionCall7370 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionCall7372 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXEC_in_actionExec7403 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionExec7405 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_dottedIdentifier_in_actionExec7411 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionExec7414 = new BitSet(new long[]{0x0000000000000000L,0x8400000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_actionExec7420 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionExec7424 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSIGN_in_actionAssign7467 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionAssign7469 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_actionAssign7496 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionAssign7498 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionAssign7504 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_Identifier_in_actionAssign7542 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionAssign7544 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionAssign7550 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_Identifier_in_actionAssign7588 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionAssign7590 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionAssign7596 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_Identifier_in_actionAssign7634 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionAssign7636 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionAssign7642 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_Identifier_in_actionAssign7680 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionAssign7682 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionAssign7688 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionAssign7707 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SETFEATURE_in_actionSetFeature7739 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionSetFeature7741 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionSetFeature7747 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionSetFeature7749 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_actionSetFeature7755 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionSetFeature7757 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GETFEATURE_in_actionGetFeature7796 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionGetFeature7798 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionGetFeature7804 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionGetFeature7806 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_variable_in_actionGetFeature7812 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionGetFeature7814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNMARK_in_actionUnmark7850 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionUnmark7852 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionUnmark7858 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionUnmark7860 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNMARKALL_in_actionUnmarkAll7896 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionUnmarkAll7898 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionUnmarkAll7904 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionUnmarkAll7912 = new BitSet(new long[]{0x0000000000000000L,0x8400000000000000L});
    public static final BitSet FOLLOW_typeListExpression_in_actionUnmarkAll7918 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionUnmarkAll7922 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRANSFER_in_actionTransfer7957 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionTransfer7959 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionTransfer7965 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionTransfer7967 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRIE_in_actionTrie8007 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionTrie8009 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionTrie8019 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionTrie8021 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionTrie8027 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8035 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionTrie8041 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionTrie8043 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_actionTrie8049 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8059 = new BitSet(new long[]{0x0000000000000000L,0x0420000000000000L});
    public static final BitSet FOLLOW_wordListExpression_in_actionTrie8065 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8072 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionTrie8078 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8085 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionTrie8091 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8098 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionTrie8104 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8111 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionTrie8117 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionTrie8124 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionTrie8130 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionTrie8136 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ADD_in_actionAdd8181 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionAdd8183 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_listVariable_in_actionAdd8189 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionAdd8192 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_actionAdd8198 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionAdd8204 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REMOVE_in_actionRemove8244 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionRemove8246 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_listVariable_in_actionRemove8252 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionRemove8255 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_actionRemove8261 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionRemove8267 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REMOVEDUPLICATE_in_actionRemoveDuplicate8303 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionRemoveDuplicate8305 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_listVariable_in_actionRemoveDuplicate8311 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionRemoveDuplicate8313 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MERGE_in_actionMerge8358 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMerge8360 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_actionMerge8366 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMerge8368 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_listVariable_in_actionMerge8374 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMerge8376 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_listExpression_in_actionMerge8382 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMerge8387 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_listExpression_in_actionMerge8393 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionMerge8399 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GET_in_actionGet8434 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionGet8436 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_listExpression_in_actionGet8442 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionGet8444 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_variable_in_actionGet8450 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionGet8452 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionGet8458 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionGet8460 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GETLIST_in_actionGetList8495 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionGetList8497 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_listVariable_in_actionGetList8503 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionGetList8505 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_actionGetList8511 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionGetList8513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MATCHEDTEXT_in_actionMatchedText8552 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionMatchedText8554 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_variable_in_actionMatchedText8565 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_actionMatchedText8581 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_actionMatchedText8587 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_actionMatchedText8611 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLEAR_in_actionClear8651 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actionClear8653 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_listVariable_in_actionClear8659 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actionClear8661 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_varArgumentList8688 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_varArgumentList8694 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_varArgumentList8698 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_varArgumentList8704 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_varArgumentList8710 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringExpression_in_argument8744 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanExpression_in_argument8755 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpression_in_argument8766 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_argument8777 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_dottedIdentifier8811 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_DOT_in_dottedIdentifier8824 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_dottedIdentifier8834 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_Identifier_in_dottedIdentifier28860 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000108L});
    public static final BitSet FOLLOW_set_in_dottedIdentifier28873 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_dottedIdentifier28887 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000108L});
    public static final BitSet FOLLOW_Identifier_in_dottedId8919 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_DOT_in_dottedId8932 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_Identifier_in_dottedId8942 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_BasicAnnotationType_in_annotationType8977 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dottedId_in_annotationType8989 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_wordListExpression9014 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RessourceLiteral_in_wordListExpression9027 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_wordTableExpression9051 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RessourceLiteral_in_wordTableExpression9064 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_numberFunction9087 = new BitSet(new long[]{0x0000000000000000L,0x0C02200000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpressionInPar_in_numberFunction9109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalNumberFunction_in_numberFunction9133 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalNumberFunction9159 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_externalNumberFunction9161 = new BitSet(new long[]{0x0000000000000000L,0x1800000000000000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalNumberFunction9168 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalNumberFunction9170 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_numberVariable9195 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_numberVariable9208 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression9238 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000180L});
    public static final BitSet FOLLOW_set_in_additiveExpression9247 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression9260 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000180L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_multiplicativeExpression9293 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000010600L});
    public static final BitSet FOLLOW_set_in_multiplicativeExpression9302 = new BitSet(new long[]{0x0000000000000000L,0x0C02200000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_simpleNumberExpression_in_multiplicativeExpression9321 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000010600L});
    public static final BitSet FOLLOW_numberFunction_in_multiplicativeExpression9339 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_additiveExpression_in_numberExpression9362 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_numberExpressionInPar9380 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_additiveExpression_in_numberExpressionInPar9387 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_numberExpressionInPar9389 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_simpleNumberExpression9412 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
    public static final BitSet FOLLOW_DecimalLiteral_in_simpleNumberExpression9419 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_simpleNumberExpression9431 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
    public static final BitSet FOLLOW_FloatingPointLiteral_in_simpleNumberExpression9438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_simpleNumberExpression9449 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberVariable_in_simpleNumberExpression9456 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpressionInPar_in_simpleNumberExpression9467 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleStringExpression_in_stringExpression9507 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_PLUS_in_stringExpression9514 = new BitSet(new long[]{0x0000000000000000L,0x8C12230000001000L,0x0000000000000100L});
    public static final BitSet FOLLOW_simpleStringExpression_in_stringExpression9521 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_numberExpressionInPar_in_stringExpression9534 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_stringExpression9546 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_typeExpression_in_stringExpression9558 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_listExpression_in_stringExpression9570 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_stringFunction_in_stringExpression9598 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REMOVESTRING_in_stringFunction9625 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_stringFunction9627 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_variable_in_stringFunction9633 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_stringFunction9636 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000200L});
    public static final BitSet FOLLOW_stringExpression_in_stringFunction9642 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_stringFunction9648 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalStringFunction_in_stringFunction9670 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalStringFunction9695 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_externalStringFunction9697 = new BitSet(new long[]{0x0000000000000000L,0x1800000000000000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalStringFunction9704 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalStringFunction9706 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_StringLiteral_in_simpleStringExpression9730 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleStringExpression9744 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_composedBooleanExpression_in_booleanExpression9777 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_booleanExpression9788 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_literalBooleanExpression_in_simpleBooleanExpression9811 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleBooleanExpression9826 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanCompare_in_composedBooleanExpression9860 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanTypeExpression_in_composedBooleanExpression9880 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanNumberExpression_in_composedBooleanExpression9899 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanFunction_in_composedBooleanExpression9909 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_XOR_in_booleanFunction9934 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_booleanFunction9936 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_booleanFunction9942 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_booleanFunction9944 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_booleanFunction9950 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_booleanFunction9952 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalBooleanFunction_in_booleanFunction9974 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_externalBooleanFunction9999 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_externalBooleanFunction10001 = new BitSet(new long[]{0x0000000000000000L,0x1800000000000000L});
    public static final BitSet FOLLOW_varArgumentList_in_externalBooleanFunction10008 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_externalBooleanFunction10010 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleBooleanExpression_in_booleanCompare10035 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x00000000000C0000L});
    public static final BitSet FOLLOW_set_in_booleanCompare10041 = new BitSet(new long[]{0x0000000000000000L,0x0C00038000001000L});
    public static final BitSet FOLLOW_booleanExpression_in_booleanCompare10053 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_literalBooleanExpression10079 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_literalBooleanExpression10091 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_booleanTypeExpression10117 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x00000000000C0000L});
    public static final BitSet FOLLOW_set_in_booleanTypeExpression10124 = new BitSet(new long[]{0x0000000000000000L,0x0400000000001000L});
    public static final BitSet FOLLOW_typeExpression_in_booleanTypeExpression10137 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_booleanNumberExpression10159 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_booleanNumberExpression10166 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x00000000006C6000L});
    public static final BitSet FOLLOW_set_in_booleanNumberExpression10173 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_booleanNumberExpression10202 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_booleanNumberExpression10205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanListExpression_in_synpred3_TextMarkerParser2098 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_intListExpression_in_synpred4_TextMarkerParser2114 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_doubleListExpression_in_synpred5_TextMarkerParser2130 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringListExpression_in_synpred6_TextMarkerParser2146 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeListExpression_in_synpred7_TextMarkerParser2162 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_doubleListExpression_in_synpred8_TextMarkerParser2369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeFunction_in_synpred9_TextMarkerParser2689 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalCondition_in_synpred11_TextMarkerParser3260 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COUNT_in_synpred12_TextMarkerParser3617 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_synpred12_TextMarkerParser3619 = new BitSet(new long[]{0x0000000000000000L,0x8400000000001000L});
    public static final BitSet FOLLOW_listExpression_in_synpred12_TextMarkerParser3625 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_synpred12_TextMarkerParser3627 = new BitSet(new long[]{0x0000000000000000L,0x0C1223FC00001200L,0x0000000000000100L});
    public static final BitSet FOLLOW_argument_in_synpred12_TextMarkerParser3633 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_synpred12_TextMarkerParser3636 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_synpred12_TextMarkerParser3642 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_synpred12_TextMarkerParser3644 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_synpred12_TextMarkerParser3650 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_synpred12_TextMarkerParser3660 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberVariable_in_synpred12_TextMarkerParser3666 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_RPAREN_in_synpred12_TextMarkerParser3670 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringListExpression_in_synpred13_TextMarkerParser3944 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalAction_in_synpred14_TextMarkerParser5499 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpression_in_synpred15_TextMarkerParser5652 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_synpred16_TextMarkerParser5665 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_synpred16_TextMarkerParser5671 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpression_in_synpred17_TextMarkerParser5964 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMA_in_synpred18_TextMarkerParser5977 = new BitSet(new long[]{0x0000000000000000L,0x0C02207C00000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_numberExpression_in_synpred18_TextMarkerParser5983 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpression_in_synpred22_TextMarkerParser6788 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeExpression_in_synpred23_TextMarkerParser6808 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringExpression_in_synpred25_TextMarkerParser8744 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanExpression_in_synpred26_TextMarkerParser8755 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberExpression_in_synpred27_TextMarkerParser8766 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalNumberFunction_in_synpred28_TextMarkerParser9125 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stringFunction_in_synpred30_TextMarkerParser9590 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalStringFunction_in_synpred31_TextMarkerParser9662 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_composedBooleanExpression_in_synpred32_TextMarkerParser9769 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanCompare_in_synpred33_TextMarkerParser9852 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanTypeExpression_in_synpred34_TextMarkerParser9872 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanNumberExpression_in_synpred35_TextMarkerParser9891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_externalBooleanFunction_in_synpred36_TextMarkerParser9966 = new BitSet(new long[]{0x0000000000000002L});

}