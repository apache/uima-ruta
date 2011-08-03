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

// $ANTLR 3.1.2 D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g 2009-08-17 15:43:13

package org.apache.uima.tm.textmarker.ui.convert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.DFA;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.apache.uima.tm.dltk.internal.core.builder.DescriptorManager;
import org.apache.uima.tm.dltk.parser.ast.TextMarkerBlock;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;


public class ConvertSyntaxParser extends Parser {
  public static final String[] tokenNames = new String[] { "<invalid>", "<EOR>", "<DOWN>", "<UP>",
      "DocComment", "Annotation", "ListIdentifier", "PackageString", "SEMI", "TypeSystemString",
      "ScriptString", "EngineString", "IntString", "Identifier", "COMMA", "ASSIGN_EQUAL",
      "DoubleString", "StringString", "BooleanString", "TypeString", "ConditionString", "ACTION",
      "DECLARE", "LIST", "TABLE", "LPAREN", "RPAREN", "BlockString", "LCURLY", "RCURLY",
      "StringLiteral", "STAR", "QUESTION", "PLUS", "LBRACK", "RBRACK", "AND", "INLIST", "MOFN",
      "MINUS", "OR", "IF", "CREATE", "FILL", "CALL", "DOT", "BasicAnnotationType", "SLASH",
      "PERCENT", "DecimalLiteral", "FloatingPointLiteral", "EXP", "LOGN", "SIN", "COS", "TAN",
      "XOR", "EQUAL", "NOTEQUAL", "TRUE", "FALSE", "LESS", "GREATER", "GREATEREQUAL", "LESSEQUAL",
      "IntegerTypeSuffix", "HexDigit", "Exponent", "FloatTypeSuffix", "EscapeSequence",
      "UnicodeEscape", "OctalEscape", "Letter", "JavaIDDigit", "WS", "COMMENT", "LINE_COMMENT",
      "'|'" };

  public static final int STAR = 31;

  public static final int LBRACK = 34;

  public static final int FloatTypeSuffix = 68;

  public static final int TypeString = 19;

  public static final int TABLE = 24;

  public static final int Exponent = 67;

  public static final int GREATEREQUAL = 63;

  public static final int LOGN = 52;

  public static final int ASSIGN_EQUAL = 15;

  public static final int TypeSystemString = 9;

  public static final int AND = 36;

  public static final int IntString = 12;

  public static final int EOF = -1;

  public static final int BlockString = 27;

  public static final int HexDigit = 66;

  public static final int Identifier = 13;

  public static final int ACTION = 21;

  public static final int LPAREN = 25;

  public static final int IF = 41;

  public static final int NOTEQUAL = 58;

  public static final int RPAREN = 26;

  public static final int CREATE = 42;

  public static final int GREATER = 62;

  public static final int SLASH = 47;

  public static final int SIN = 53;

  public static final int EXP = 51;

  public static final int FILL = 43;

  public static final int COMMA = 14;

  public static final int COS = 54;

  public static final int TAN = 55;

  public static final int EQUAL = 57;

  public static final int LESS = 61;

  public static final int INLIST = 37;

  public static final int PLUS = 33;

  public static final int BooleanString = 18;

  public static final int COMMENT = 75;

  public static final int DOT = 45;

  public static final int ListIdentifier = 6;

  public static final int RBRACK = 35;

  public static final int XOR = 56;

  public static final int PERCENT = 48;

  public static final int PackageString = 7;

  public static final int LINE_COMMENT = 76;

  public static final int IntegerTypeSuffix = 65;

  public static final int LCURLY = 28;

  public static final int ConditionString = 20;

  public static final int MINUS = 39;

  public static final int DecimalLiteral = 49;

  public static final int LIST = 23;

  public static final int TRUE = 59;

  public static final int SEMI = 8;

  public static final int StringString = 17;

  public static final int StringLiteral = 30;

  public static final int EngineString = 11;

  public static final int ScriptString = 10;

  public static final int WS = 74;

  public static final int QUESTION = 32;

  public static final int UnicodeEscape = 70;

  public static final int FloatingPointLiteral = 50;

  public static final int RCURLY = 29;

  public static final int OR = 40;

  public static final int JavaIDDigit = 73;

  public static final int DECLARE = 22;

  public static final int CALL = 44;

  public static final int DocComment = 4;

  public static final int MOFN = 38;

  public static final int Annotation = 5;

  public static final int FALSE = 60;

  public static final int LESSEQUAL = 64;

  public static final int Letter = 72;

  public static final int OctalEscape = 71;

  public static final int EscapeSequence = 69;

  public static final int DoubleString = 16;

  public static final int BasicAnnotationType = 46;

  public static final int T__77 = 77;

  // delegates
  // delegators

  public ConvertSyntaxParser(TokenStream input) {
    this(input, new RecognizerSharedState());
  }

  public ConvertSyntaxParser(TokenStream input, RecognizerSharedState state) {
    super(input, state);

  }

  public String[] getTokenNames() {
    return ConvertSyntaxParser.tokenNames;
  }

  public String getGrammarFileName() {
    return "D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g";
  }

  public ModuleDeclaration md;

  private List<String> vars = new ArrayList<String>();

  private Map<String, String> varTypeMap = new HashMap<String, String>();

  private List lists = new ArrayList();

  private List tables = new ArrayList();

  public int length;

  public DescriptorManager descriptor;

  private int level = 0;

  private String module;

  private String packageString;

  public StringBuilder builder = new StringBuilder();

  public List<String> getVariables() {
    return vars;
  }

  public Map<String, String> getVariableTypes() {
    return varTypeMap;
  }

  public void addType(TextMarkerBlock parent, String type, String parentType) {
    vars.add(type);
    if (parentType == null || parentType.trim().equals("") || parentType.equals("Annotation")) {
      parentType = "uima.tcas.Annotation";
      // } else if(parentType.split(".").length > 1) {

    } else {
      parentType = parent.getNamespace() + "." + parentType.trim();
    }
    descriptor.addType(parent.getNamespace() + "." + type.trim(), "Type defined in "
            + packageString + "." + module, parentType);
  }

  public void addPredefinedType(String type) {
    vars.add(type);
    varTypeMap.put(type, "TYPE");

  }

  public void addType(TextMarkerBlock parent, String name, String parentType, List featuresTypes,
          List featuresNames) {
    vars.add(name);
    name = parent.getNamespace() + "." + name.trim();
    if (parentType.equals("Annotation")) {
      parentType = "uima.tcas.Annotation";
      // } else if(parentType.split(".").length > 1) {

    } else {
      parentType = parent.getNamespace() + "." + parentType.trim();
    }
    descriptor.addType(name, "Type defined in " + packageString + "." + module, parentType);

    for (int i = 0; i < featuresTypes.size(); i++) {
      String ftype = (String) featuresTypes.get(i);
      if (ftype.equals("Annotation")) {
        ftype = "uima.tcas.Annotation";
      } else if (ftype.equals("STRING")) {
        ftype = "uima.cas.String";
      } else if (ftype.equals("INT")) {
        ftype = "uima.cas.Integer";
      } else if (ftype.equals("DOUBLE")) {
        ftype = "uima.cas.Double";
      } else if (ftype.equals("BOOLEAN")) {
        ftype = "uima.cas.Boolean";
      } else if (ftype.equals("TYPE")) {
        ftype = "uima.cas.String";
      } else {
        ftype = parent.getNamespace() + "." + ftype;
      }
      String fname = (String) featuresNames.get(i);
      descriptor.addFeature(name, fname, fname, ftype);
    }
  }

  public void addWordList(TextMarkerBlock parent, String list) {
    lists.add(list);
  }

  public void addCSVTable(TextMarkerBlock parent, String table) {
    tables.add(table);
  }

  public boolean isType(TextMarkerBlock parent, String type) {
    return vars.contains(type);
  }

  public boolean isWordList(TextMarkerBlock parent, String list) {
    return lists.contains(list);
  }

  public boolean isCSVTable(TextMarkerBlock parent, String table) {
    return tables.contains(table);
  }

  public void addVariable(String var, String type, IntStream input) throws NoViableAltException {
    if (!vars.contains(var)) {
      vars.add(var);
      varTypeMap.put(var, type);
    }
  }

  public void addVariable(String var, String type) {
    if (!vars.contains(var)) {
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
    if (!vars.contains(var)) {
      throw new NoViableAltException("not declared \"" + var + "\"", 3, 0, input);
    }
  }

  public void addImportTypeSystem(String descriptorLocation) {
    descriptor.addTypeSystem(descriptorLocation);
  }

  public void addImportScript(Token module) {
    descriptor.addScript(module.getText());
  }

  public void addImportEngine(Token module) {
    descriptor.addEngine(module.getText());
  }

  protected static final int[] getBounds(Token t) {
    if (t instanceof CommonToken) {
      CommonToken ct = (CommonToken) t;
      int[] bounds = { ct.getStartIndex(), ct.getStopIndex() };
      return bounds;
    }
    return null;
  }

  // public String getTypeOf(String varName) {
  // String vn = varTypeMap.get(varName);
  // return vn != null? vn : "";
  // }

  // $ANTLR start "file_input"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:213:1:
  // file_input : p= packageDeclaration gs= globalStatements s= statements EOF ;
  public final void file_input() throws RecognitionException {
    StringBuilder p = null;

    StringBuilder gs = null;

    StringBuilder s = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:219:2:
      // (p= packageDeclaration gs= globalStatements s= statements EOF )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:220:2:
      // p= packageDeclaration gs= globalStatements s= statements EOF
      {
        pushFollow(FOLLOW_packageDeclaration_in_file_input91);
        p = packageDeclaration();

        state._fsp--;
        if (state.failed)
          return;
        if (state.backtracking == 0) {
          if (p != null)
            builder.append(p).append("\n");
        }
        pushFollow(FOLLOW_globalStatements_in_file_input106);
        gs = globalStatements();

        state._fsp--;
        if (state.failed)
          return;
        if (state.backtracking == 0) {
          if (gs != null)
            builder.append(gs).append("\n");
        }
        pushFollow(FOLLOW_statements_in_file_input115);
        s = statements();

        state._fsp--;
        if (state.failed)
          return;
        if (state.backtracking == 0) {
          builder.append(s);
        }
        match(input, EOF, FOLLOW_EOF_in_file_input127);
        if (state.failed)
          return;

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return;
  }

  // $ANTLR end "file_input"

  // $ANTLR start "packageDeclaration"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:246:1:
  // packageDeclaration returns [StringBuilder pack = new StringBuilder()] : pString= PackageString
  // p= dottedId SEMI ;
  public final StringBuilder packageDeclaration() throws RecognitionException {
    StringBuilder pack = new StringBuilder();

    Token pString = null;
    StringBuilder p = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:247:2:
      // (pString= PackageString p= dottedId SEMI )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:247:4:
      // pString= PackageString p= dottedId SEMI
      {
        pString = (Token) match(input, PackageString, FOLLOW_PackageString_in_packageDeclaration148);
        if (state.failed)
          return pack;
        pushFollow(FOLLOW_dottedId_in_packageDeclaration154);
        p = dottedId();

        state._fsp--;
        if (state.failed)
          return pack;
        match(input, SEMI, FOLLOW_SEMI_in_packageDeclaration156);
        if (state.failed)
          return pack;
        if (state.backtracking == 0) {

          // pack = StatementFactory.createPkgDeclaration(p, pString);
          pack.append(pString.getText()).append(" ").append(p).append(";");

        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return pack;
  }

  // $ANTLR end "packageDeclaration"

  // $ANTLR start "statements"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:254:1:
  // statements returns [StringBuilder stmts = new StringBuilder()] : (morestmts= statement )* ;
  public final StringBuilder statements() throws RecognitionException {
    StringBuilder stmts = new StringBuilder();

    StringBuilder morestmts = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:255:2:
      // ( (morestmts= statement )* )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:256:2:
      // (morestmts= statement )*
      {
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:256:2:
        // (morestmts= statement )*
        loop1: do {
          int alt1 = 2;
          int LA1_0 = input.LA(1);

          if (((LA1_0 >= IntString && LA1_0 <= Identifier)
                  || (LA1_0 >= DoubleString && LA1_0 <= TABLE) || LA1_0 == BlockString
                  || LA1_0 == StringLiteral || LA1_0 == BasicAnnotationType)) {
            alt1 = 1;
          }

          switch (alt1) {
            case 1:
              // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:256:3:
              // morestmts= statement
            {
              pushFollow(FOLLOW_statement_in_statements180);
              morestmts = statement();

              state._fsp--;
              if (state.failed)
                return stmts;
              if (state.backtracking == 0) {
                stmts.append(morestmts);
              }

            }
              break;

            default:
              break loop1;
          }
        } while (true);

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return stmts;
  }

  // $ANTLR end "statements"

  // $ANTLR start "globalStatements"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:259:1:
  // globalStatements returns [StringBuilder stmts = new StringBuilder()] : (morestmts=
  // globalStatement )* ;
  public final StringBuilder globalStatements() throws RecognitionException {
    StringBuilder stmts = new StringBuilder();

    StringBuilder morestmts = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:260:2:
      // ( (morestmts= globalStatement )* )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:261:2:
      // (morestmts= globalStatement )*
      {
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:261:2:
        // (morestmts= globalStatement )*
        loop2: do {
          int alt2 = 2;
          int LA2_0 = input.LA(1);

          if (((LA2_0 >= TypeSystemString && LA2_0 <= EngineString))) {
            alt2 = 1;
          }

          switch (alt2) {
            case 1:
              // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:261:3:
              // morestmts= globalStatement
            {
              pushFollow(FOLLOW_globalStatement_in_globalStatements206);
              morestmts = globalStatement();

              state._fsp--;
              if (state.failed)
                return stmts;
              if (state.backtracking == 0) {
                stmts.append(morestmts).append("\n");
              }

            }
              break;

            default:
              break loop2;
          }
        } while (true);

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return stmts;
  }

  // $ANTLR end "globalStatements"

  // $ANTLR start "globalStatement"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:265:1:
  // globalStatement returns [StringBuilder stmts = new StringBuilder()] : stmtImport=
  // importStatement ;
  public final StringBuilder globalStatement() throws RecognitionException {
    StringBuilder stmts = new StringBuilder();

    StringBuilder stmtImport = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:266:2:
      // (stmtImport= importStatement )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:267:2:
      // stmtImport= importStatement
      {
        pushFollow(FOLLOW_importStatement_in_globalStatement232);
        stmtImport = importStatement();

        state._fsp--;
        if (state.failed)
          return stmts;
        if (state.backtracking == 0) {
          stmts.append(stmtImport);
        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return stmts;
  }

  // $ANTLR end "globalStatement"

  // $ANTLR start "statement"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:270:1:
  // statement returns [StringBuilder stmts = new StringBuilder()] : (stmts1= declaration |
  // stmtVariable= variableDeclaration | stmt3= blockDeclaration | stmt2= simpleStatement ) ;
  public final StringBuilder statement() throws RecognitionException {
    StringBuilder stmts = new StringBuilder();

    StringBuilder stmts1 = null;

    StringBuilder stmtVariable = null;

    StringBuilder stmt3 = null;

    StringBuilder stmt2 = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:271:2:
      // ( (stmts1= declaration | stmtVariable= variableDeclaration | stmt3= blockDeclaration |
      // stmt2= simpleStatement ) )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:272:2:
      // (stmts1= declaration | stmtVariable= variableDeclaration | stmt3= blockDeclaration | stmt2=
      // simpleStatement )
      {
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:272:2:
        // (stmts1= declaration | stmtVariable= variableDeclaration | stmt3= blockDeclaration |
        // stmt2= simpleStatement )
        int alt3 = 4;
        switch (input.LA(1)) {
          case DECLARE:
          case LIST:
          case TABLE: {
            alt3 = 1;
          }
            break;
          case IntString:
          case DoubleString:
          case StringString:
          case BooleanString:
          case TypeString:
          case ConditionString:
          case ACTION: {
            alt3 = 2;
          }
            break;
          case BlockString: {
            alt3 = 3;
          }
            break;
          case Identifier:
          case StringLiteral:
          case BasicAnnotationType: {
            alt3 = 4;
          }
            break;
          default:
            if (state.backtracking > 0) {
              state.failed = true;
              return stmts;
            }
            NoViableAltException nvae = new NoViableAltException("", 3, 0, input);

            throw nvae;
        }

        switch (alt3) {
          case 1:
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:272:4:
            // stmts1= declaration
          {
            pushFollow(FOLLOW_declaration_in_statement258);
            stmts1 = declaration();

            state._fsp--;
            if (state.failed)
              return stmts;
            if (state.backtracking == 0) {
              stmts.append(stmts1).append("\n");
            }

          }
            break;
          case 2:
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:273:4:
            // stmtVariable= variableDeclaration
          {
            pushFollow(FOLLOW_variableDeclaration_in_statement269);
            stmtVariable = variableDeclaration();

            state._fsp--;
            if (state.failed)
              return stmts;
            if (state.backtracking == 0) {
              stmts.append(stmtVariable).append("\n");
            }

          }
            break;
          case 3:
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:274:4:
            // stmt3= blockDeclaration
          {
            pushFollow(FOLLOW_blockDeclaration_in_statement280);
            stmt3 = blockDeclaration();

            state._fsp--;
            if (state.failed)
              return stmts;
            if (state.backtracking == 0) {
              stmts.append(stmt3).append("\n");
            }

          }
            break;
          case 4:
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:275:4:
            // stmt2= simpleStatement
          {
            pushFollow(FOLLOW_simpleStatement_in_statement291);
            stmt2 = simpleStatement();

            state._fsp--;
            if (state.failed)
              return stmts;
            if (state.backtracking == 0) {
              stmts.append(stmt2).append("\n");
            }

          }
            break;

        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return stmts;
  }

  // $ANTLR end "statement"

  // $ANTLR start "importStatement"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:279:1:
  // importStatement returns [StringBuilder stmt = new StringBuilder()] : (system= TypeSystemString
  // ts= dottedId SEMI | im= ScriptString name= dottedId SEMI | im= EngineString name= dottedId SEMI
  // );
  public final StringBuilder importStatement() throws RecognitionException {
    StringBuilder stmt = new StringBuilder();

    Token system = null;
    Token im = null;
    StringBuilder ts = null;

    StringBuilder name = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:280:2:
      // (system= TypeSystemString ts= dottedId SEMI | im= ScriptString name= dottedId SEMI | im=
      // EngineString name= dottedId SEMI )
      int alt4 = 3;
      switch (input.LA(1)) {
        case TypeSystemString: {
          alt4 = 1;
        }
          break;
        case ScriptString: {
          alt4 = 2;
        }
          break;
        case EngineString: {
          alt4 = 3;
        }
          break;
        default:
          if (state.backtracking > 0) {
            state.failed = true;
            return stmt;
          }
          NoViableAltException nvae = new NoViableAltException("", 4, 0, input);

          throw nvae;
      }

      switch (alt4) {
        case 1:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:281:2:
          // system= TypeSystemString ts= dottedId SEMI
        {
          system = (Token) match(input, TypeSystemString,
                  FOLLOW_TypeSystemString_in_importStatement317);
          if (state.failed)
            return stmt;
          pushFollow(FOLLOW_dottedId_in_importStatement323);
          ts = dottedId();

          state._fsp--;
          if (state.failed)
            return stmt;
          match(input, SEMI, FOLLOW_SEMI_in_importStatement325);
          if (state.failed)
            return stmt;
          if (state.backtracking == 0) {
            stmt.append(system.getText()).append(" ").append(ts).append(";");
          }

        }
          break;
        case 2:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:283:4:
          // im= ScriptString name= dottedId SEMI
        {
          im = (Token) match(input, ScriptString, FOLLOW_ScriptString_in_importStatement338);
          if (state.failed)
            return stmt;
          pushFollow(FOLLOW_dottedId_in_importStatement344);
          name = dottedId();

          state._fsp--;
          if (state.failed)
            return stmt;
          match(input, SEMI, FOLLOW_SEMI_in_importStatement346);
          if (state.failed)
            return stmt;
          if (state.backtracking == 0) {
            stmt.append(im.getText()).append(" ").append(name).append(";");
          }

        }
          break;
        case 3:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:285:4:
          // im= EngineString name= dottedId SEMI
        {
          im = (Token) match(input, EngineString, FOLLOW_EngineString_in_importStatement359);
          if (state.failed)
            return stmt;
          pushFollow(FOLLOW_dottedId_in_importStatement365);
          name = dottedId();

          state._fsp--;
          if (state.failed)
            return stmt;
          match(input, SEMI, FOLLOW_SEMI_in_importStatement367);
          if (state.failed)
            return stmt;
          if (state.backtracking == 0) {
            stmt.append(im.getText()).append(" ").append(name).append(";");
          }

        }
          break;

      }
    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return stmt;
  }

  // $ANTLR end "importStatement"

  // $ANTLR start "variableDeclaration"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:289:1:
  // variableDeclaration returns [StringBuilder stmt = new StringBuilder()] : (type= IntString id=
  // Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL value1= numberExpression )? SEMI | type=
  // DoubleString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL value2= numberExpression
  // )? SEMI | type= StringString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL value3=
  // stringExpression )? SEMI | type= BooleanString id= Identifier ( COMMA id= Identifier )* (
  // ASSIGN_EQUAL value4= booleanExpression )? SEMI | type= TypeString id= Identifier ( COMMA id=
  // Identifier )* ( ASSIGN_EQUAL value5= annotationType )? SEMI | stmt1= conditionDeclaration |
  // stmt2= actionDeclaration );
  public final StringBuilder variableDeclaration() throws RecognitionException {
    StringBuilder stmt = new StringBuilder();

    Token type = null;
    Token id = null;
    StringBuilder value1 = null;

    StringBuilder value2 = null;

    StringBuilder value3 = null;

    StringBuilder value4 = null;

    StringBuilder value5 = null;

    StringBuilder stmt1 = null;

    StringBuilder stmt2 = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:291:2:
      // (type= IntString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL value1=
      // numberExpression )? SEMI | type= DoubleString id= Identifier ( COMMA id= Identifier )* (
      // ASSIGN_EQUAL value2= numberExpression )? SEMI | type= StringString id= Identifier ( COMMA
      // id= Identifier )* ( ASSIGN_EQUAL value3= stringExpression )? SEMI | type= BooleanString id=
      // Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL value4= booleanExpression )? SEMI |
      // type= TypeString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL value5=
      // annotationType )? SEMI | stmt1= conditionDeclaration | stmt2= actionDeclaration )
      int alt15 = 7;
      switch (input.LA(1)) {
        case IntString: {
          alt15 = 1;
        }
          break;
        case DoubleString: {
          alt15 = 2;
        }
          break;
        case StringString: {
          alt15 = 3;
        }
          break;
        case BooleanString: {
          alt15 = 4;
        }
          break;
        case TypeString: {
          alt15 = 5;
        }
          break;
        case ConditionString: {
          alt15 = 6;
        }
          break;
        case ACTION: {
          alt15 = 7;
        }
          break;
        default:
          if (state.backtracking > 0) {
            state.failed = true;
            return stmt;
          }
          NoViableAltException nvae = new NoViableAltException("", 15, 0, input);

          throw nvae;
      }

      switch (alt15) {
        case 1:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:293:2:
          // type= IntString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL value1=
          // numberExpression )? SEMI
        {
          type = (Token) match(input, IntString, FOLLOW_IntString_in_variableDeclaration394);
          if (state.failed)
            return stmt;
          id = (Token) match(input, Identifier, FOLLOW_Identifier_in_variableDeclaration400);
          if (state.failed)
            return stmt;
          if (state.backtracking == 0) {
            stmt.append(type.getText()).append(" ").append(id.getText());
          }
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:294:3:
          // ( COMMA id= Identifier )*
          loop5: do {
            int alt5 = 2;
            int LA5_0 = input.LA(1);

            if ((LA5_0 == COMMA)) {
              alt5 = 1;
            }

            switch (alt5) {
              case 1:
                // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:294:4:
                // COMMA id= Identifier
              {
                match(input, COMMA, FOLLOW_COMMA_in_variableDeclaration407);
                if (state.failed)
                  return stmt;
                id = (Token) match(input, Identifier, FOLLOW_Identifier_in_variableDeclaration412);
                if (state.failed)
                  return stmt;
                if (state.backtracking == 0) {
                  stmt.append(",").append(id.getText());
                }

              }
                break;

              default:
                break loop5;
            }
          } while (true);

          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:295:6:
          // ( ASSIGN_EQUAL value1= numberExpression )?
          int alt6 = 2;
          int LA6_0 = input.LA(1);

          if ((LA6_0 == ASSIGN_EQUAL)) {
            alt6 = 1;
          }
          switch (alt6) {
            case 1:
              // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:295:7:
              // ASSIGN_EQUAL value1= numberExpression
            {
              match(input, ASSIGN_EQUAL, FOLLOW_ASSIGN_EQUAL_in_variableDeclaration422);
              if (state.failed)
                return stmt;
              pushFollow(FOLLOW_numberExpression_in_variableDeclaration428);
              value1 = numberExpression();

              state._fsp--;
              if (state.failed)
                return stmt;
              if (state.backtracking == 0) {
                stmt.append(" = ").append(value1);
              }

            }
              break;

          }

          match(input, SEMI, FOLLOW_SEMI_in_variableDeclaration436);
          if (state.failed)
            return stmt;
          if (state.backtracking == 0) {
            stmt.append(";");
          }

        }
          break;
        case 2:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:298:2:
          // type= DoubleString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL value2=
          // numberExpression )? SEMI
        {
          type = (Token) match(input, DoubleString, FOLLOW_DoubleString_in_variableDeclaration450);
          if (state.failed)
            return stmt;
          id = (Token) match(input, Identifier, FOLLOW_Identifier_in_variableDeclaration456);
          if (state.failed)
            return stmt;
          if (state.backtracking == 0) {
            stmt.append(type.getText()).append(" ").append(id.getText());
          }
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:299:4:
          // ( COMMA id= Identifier )*
          loop7: do {
            int alt7 = 2;
            int LA7_0 = input.LA(1);

            if ((LA7_0 == COMMA)) {
              alt7 = 1;
            }

            switch (alt7) {
              case 1:
                // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:299:5:
                // COMMA id= Identifier
              {
                match(input, COMMA, FOLLOW_COMMA_in_variableDeclaration464);
                if (state.failed)
                  return stmt;
                id = (Token) match(input, Identifier, FOLLOW_Identifier_in_variableDeclaration470);
                if (state.failed)
                  return stmt;
                if (state.backtracking == 0) {
                  stmt.append(",").append(id.getText());
                }

              }
                break;

              default:
                break loop7;
            }
          } while (true);

          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:300:7:
          // ( ASSIGN_EQUAL value2= numberExpression )?
          int alt8 = 2;
          int LA8_0 = input.LA(1);

          if ((LA8_0 == ASSIGN_EQUAL)) {
            alt8 = 1;
          }
          switch (alt8) {
            case 1:
              // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:300:8:
              // ASSIGN_EQUAL value2= numberExpression
            {
              match(input, ASSIGN_EQUAL, FOLLOW_ASSIGN_EQUAL_in_variableDeclaration481);
              if (state.failed)
                return stmt;
              pushFollow(FOLLOW_numberExpression_in_variableDeclaration487);
              value2 = numberExpression();

              state._fsp--;
              if (state.failed)
                return stmt;
              if (state.backtracking == 0) {
                stmt.append(" = ").append(value2);
              }

            }
              break;

          }

          match(input, SEMI, FOLLOW_SEMI_in_variableDeclaration494);
          if (state.failed)
            return stmt;
          if (state.backtracking == 0) {
            stmt.append(";");
          }

        }
          break;
        case 3:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:303:2:
          // type= StringString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL value3=
          // stringExpression )? SEMI
        {
          type = (Token) match(input, StringString, FOLLOW_StringString_in_variableDeclaration509);
          if (state.failed)
            return stmt;
          id = (Token) match(input, Identifier, FOLLOW_Identifier_in_variableDeclaration515);
          if (state.failed)
            return stmt;
          if (state.backtracking == 0) {
            stmt.append(type.getText()).append(" ").append(id.getText());
          }
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:304:4:
          // ( COMMA id= Identifier )*
          loop9: do {
            int alt9 = 2;
            int LA9_0 = input.LA(1);

            if ((LA9_0 == COMMA)) {
              alt9 = 1;
            }

            switch (alt9) {
              case 1:
                // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:304:5:
                // COMMA id= Identifier
              {
                match(input, COMMA, FOLLOW_COMMA_in_variableDeclaration523);
                if (state.failed)
                  return stmt;
                id = (Token) match(input, Identifier, FOLLOW_Identifier_in_variableDeclaration529);
                if (state.failed)
                  return stmt;
                if (state.backtracking == 0) {
                  stmt.append(",").append(id.getText());
                }

              }
                break;

              default:
                break loop9;
            }
          } while (true);

          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:305:7:
          // ( ASSIGN_EQUAL value3= stringExpression )?
          int alt10 = 2;
          int LA10_0 = input.LA(1);

          if ((LA10_0 == ASSIGN_EQUAL)) {
            alt10 = 1;
          }
          switch (alt10) {
            case 1:
              // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:305:8:
              // ASSIGN_EQUAL value3= stringExpression
            {
              match(input, ASSIGN_EQUAL, FOLLOW_ASSIGN_EQUAL_in_variableDeclaration540);
              if (state.failed)
                return stmt;
              pushFollow(FOLLOW_stringExpression_in_variableDeclaration546);
              value3 = stringExpression();

              state._fsp--;
              if (state.failed)
                return stmt;
              if (state.backtracking == 0) {
                stmt.append(" = ").append(value3);
              }

            }
              break;

          }

          match(input, SEMI, FOLLOW_SEMI_in_variableDeclaration553);
          if (state.failed)
            return stmt;
          if (state.backtracking == 0) {
            stmt.append(";");
          }

        }
          break;
        case 4:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:308:2:
          // type= BooleanString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL value4=
          // booleanExpression )? SEMI
        {
          type = (Token) match(input, BooleanString, FOLLOW_BooleanString_in_variableDeclaration568);
          if (state.failed)
            return stmt;
          id = (Token) match(input, Identifier, FOLLOW_Identifier_in_variableDeclaration574);
          if (state.failed)
            return stmt;
          if (state.backtracking == 0) {
            stmt.append(type.getText()).append(" ").append(id.getText());
          }
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:309:4:
          // ( COMMA id= Identifier )*
          loop11: do {
            int alt11 = 2;
            int LA11_0 = input.LA(1);

            if ((LA11_0 == COMMA)) {
              alt11 = 1;
            }

            switch (alt11) {
              case 1:
                // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:309:5:
                // COMMA id= Identifier
              {
                match(input, COMMA, FOLLOW_COMMA_in_variableDeclaration582);
                if (state.failed)
                  return stmt;
                id = (Token) match(input, Identifier, FOLLOW_Identifier_in_variableDeclaration588);
                if (state.failed)
                  return stmt;
                if (state.backtracking == 0) {
                  stmt.append(",").append(id.getText());
                }

              }
                break;

              default:
                break loop11;
            }
          } while (true);

          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:310:7:
          // ( ASSIGN_EQUAL value4= booleanExpression )?
          int alt12 = 2;
          int LA12_0 = input.LA(1);

          if ((LA12_0 == ASSIGN_EQUAL)) {
            alt12 = 1;
          }
          switch (alt12) {
            case 1:
              // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:310:8:
              // ASSIGN_EQUAL value4= booleanExpression
            {
              match(input, ASSIGN_EQUAL, FOLLOW_ASSIGN_EQUAL_in_variableDeclaration599);
              if (state.failed)
                return stmt;
              pushFollow(FOLLOW_booleanExpression_in_variableDeclaration605);
              value4 = booleanExpression();

              state._fsp--;
              if (state.failed)
                return stmt;
              if (state.backtracking == 0) {
                stmt.append(" = ").append(value4);
              }

            }
              break;

          }

          match(input, SEMI, FOLLOW_SEMI_in_variableDeclaration612);
          if (state.failed)
            return stmt;
          if (state.backtracking == 0) {
            stmt.append(";");
          }

        }
          break;
        case 5:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:313:2:
          // type= TypeString id= Identifier ( COMMA id= Identifier )* ( ASSIGN_EQUAL value5=
          // annotationType )? SEMI
        {
          type = (Token) match(input, TypeString, FOLLOW_TypeString_in_variableDeclaration627);
          if (state.failed)
            return stmt;
          id = (Token) match(input, Identifier, FOLLOW_Identifier_in_variableDeclaration633);
          if (state.failed)
            return stmt;
          if (state.backtracking == 0) {
            stmt.append(type.getText()).append(" ").append(id.getText());
          }
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:314:4:
          // ( COMMA id= Identifier )*
          loop13: do {
            int alt13 = 2;
            int LA13_0 = input.LA(1);

            if ((LA13_0 == COMMA)) {
              alt13 = 1;
            }

            switch (alt13) {
              case 1:
                // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:314:5:
                // COMMA id= Identifier
              {
                match(input, COMMA, FOLLOW_COMMA_in_variableDeclaration641);
                if (state.failed)
                  return stmt;
                id = (Token) match(input, Identifier, FOLLOW_Identifier_in_variableDeclaration647);
                if (state.failed)
                  return stmt;
                if (state.backtracking == 0) {
                  stmt.append(",").append(id.getText());
                }

              }
                break;

              default:
                break loop13;
            }
          } while (true);

          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:315:7:
          // ( ASSIGN_EQUAL value5= annotationType )?
          int alt14 = 2;
          int LA14_0 = input.LA(1);

          if ((LA14_0 == ASSIGN_EQUAL)) {
            alt14 = 1;
          }
          switch (alt14) {
            case 1:
              // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:315:8:
              // ASSIGN_EQUAL value5= annotationType
            {
              match(input, ASSIGN_EQUAL, FOLLOW_ASSIGN_EQUAL_in_variableDeclaration658);
              if (state.failed)
                return stmt;
              pushFollow(FOLLOW_annotationType_in_variableDeclaration664);
              value5 = annotationType();

              state._fsp--;
              if (state.failed)
                return stmt;
              if (state.backtracking == 0) {
                stmt.append(" = ").append(value5);
              }

            }
              break;

          }

          match(input, SEMI, FOLLOW_SEMI_in_variableDeclaration671);
          if (state.failed)
            return stmt;
          if (state.backtracking == 0) {
            stmt.append(";");
          }

        }
          break;
        case 6:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:318:2:
          // stmt1= conditionDeclaration
        {
          pushFollow(FOLLOW_conditionDeclaration_in_variableDeclaration686);
          stmt1 = conditionDeclaration();

          state._fsp--;
          if (state.failed)
            return stmt;
          if (state.backtracking == 0) {
            stmt.append(stmt1);
          }

        }
          break;
        case 7:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:320:2:
          // stmt2= actionDeclaration
        {
          pushFollow(FOLLOW_actionDeclaration_in_variableDeclaration698);
          stmt2 = actionDeclaration();

          state._fsp--;
          if (state.failed)
            return stmt;
          if (state.backtracking == 0) {
            stmt.append(stmt2);
          }

        }
          break;

      }
    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return stmt;
  }

  // $ANTLR end "variableDeclaration"

  // $ANTLR start "conditionDeclaration"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:324:1:
  // conditionDeclaration returns [StringBuilder stmt = new StringBuilder()] : declareToken=
  // ConditionString id= Identifier ASSIGN_EQUAL conditions= conditionPart SEMI ;
  public final StringBuilder conditionDeclaration() throws RecognitionException {
    StringBuilder stmt = new StringBuilder();

    Token declareToken = null;
    Token id = null;
    StringBuilder conditions = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:325:2:
      // (declareToken= ConditionString id= Identifier ASSIGN_EQUAL conditions= conditionPart SEMI )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:326:2:
      // declareToken= ConditionString id= Identifier ASSIGN_EQUAL conditions= conditionPart SEMI
      {
        declareToken = (Token) match(input, ConditionString,
                FOLLOW_ConditionString_in_conditionDeclaration721);
        if (state.failed)
          return stmt;
        id = (Token) match(input, Identifier, FOLLOW_Identifier_in_conditionDeclaration727);
        if (state.failed)
          return stmt;
        match(input, ASSIGN_EQUAL, FOLLOW_ASSIGN_EQUAL_in_conditionDeclaration730);
        if (state.failed)
          return stmt;
        pushFollow(FOLLOW_conditionPart_in_conditionDeclaration738);
        conditions = conditionPart();

        state._fsp--;
        if (state.failed)
          return stmt;
        match(input, SEMI, FOLLOW_SEMI_in_conditionDeclaration740);
        if (state.failed)
          return stmt;
        if (state.backtracking == 0) {
          stmt.append(declareToken.getText()).append(" ").append(id.getText()).append(" = ")
                  .append(conditions).append(";");
        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return stmt;
  }

  // $ANTLR end "conditionDeclaration"

  // $ANTLR start "actionDeclaration"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:335:1:
  // actionDeclaration returns [StringBuilder stmt = new StringBuilder()] : declareToken= ACTION id=
  // Identifier ASSIGN_EQUAL actions= actionPart SEMI ;
  public final StringBuilder actionDeclaration() throws RecognitionException {
    StringBuilder stmt = new StringBuilder();

    Token declareToken = null;
    Token id = null;
    StringBuilder actions = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:336:2:
      // (declareToken= ACTION id= Identifier ASSIGN_EQUAL actions= actionPart SEMI )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:337:2:
      // declareToken= ACTION id= Identifier ASSIGN_EQUAL actions= actionPart SEMI
      {
        declareToken = (Token) match(input, ACTION, FOLLOW_ACTION_in_actionDeclaration768);
        if (state.failed)
          return stmt;
        id = (Token) match(input, Identifier, FOLLOW_Identifier_in_actionDeclaration774);
        if (state.failed)
          return stmt;
        match(input, ASSIGN_EQUAL, FOLLOW_ASSIGN_EQUAL_in_actionDeclaration777);
        if (state.failed)
          return stmt;
        pushFollow(FOLLOW_actionPart_in_actionDeclaration784);
        actions = actionPart();

        state._fsp--;
        if (state.failed)
          return stmt;
        match(input, SEMI, FOLLOW_SEMI_in_actionDeclaration786);
        if (state.failed)
          return stmt;
        if (state.backtracking == 0) {
          stmt.append(declareToken.getText()).append(" ").append(id.getText()).append(" = ")
                  .append(actions).append(";");
        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return stmt;
  }

  // $ANTLR end "actionDeclaration"

  // $ANTLR start "declaration"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:345:1:
  // declaration returns [StringBuilder stmts = new StringBuilder()] : (declareToken= DECLARE
  // (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier )* SEMI | listToken= LIST
  // list= dottedIdentifier SEMI | tableToken= TABLE table= dottedIdentifier SEMI | declareToken=
  // DECLARE type= annotationType id= Identifier ( LPAREN (obj1= annotationType | obj2= StringString
  // | obj3= DoubleString | obj4= IntString | obj5= BooleanString ) fname= Identifier ( COMMA (obj1=
  // annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5=
  // BooleanString ) fname= Identifier )* RPAREN ) SEMI ) ;
  public final StringBuilder declaration() throws RecognitionException {
    StringBuilder stmts = new StringBuilder();

    Token declareToken = null;
    Token id = null;
    Token listToken = null;
    Token tableToken = null;
    Token obj2 = null;
    Token obj3 = null;
    Token obj4 = null;
    Token obj5 = null;
    Token fname = null;
    StringBuilder lazyParent = null;

    StringBuilder list = null;

    StringBuilder table = null;

    StringBuilder type = null;

    StringBuilder obj1 = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:351:2:
      // ( (declareToken= DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id=
      // Identifier )* SEMI | listToken= LIST list= dottedIdentifier SEMI | tableToken= TABLE table=
      // dottedIdentifier SEMI | declareToken= DECLARE type= annotationType id= Identifier ( LPAREN
      // (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5=
      // BooleanString ) fname= Identifier ( COMMA (obj1= annotationType | obj2= StringString |
      // obj3= DoubleString | obj4= IntString | obj5= BooleanString ) fname= Identifier )* RPAREN )
      // SEMI ) )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:353:2:
      // (declareToken= DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id= Identifier
      // )* SEMI | listToken= LIST list= dottedIdentifier SEMI | tableToken= TABLE table=
      // dottedIdentifier SEMI | declareToken= DECLARE type= annotationType id= Identifier ( LPAREN
      // (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5=
      // BooleanString ) fname= Identifier ( COMMA (obj1= annotationType | obj2= StringString |
      // obj3= DoubleString | obj4= IntString | obj5= BooleanString ) fname= Identifier )* RPAREN )
      // SEMI )
      {
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:353:2:
        // (declareToken= DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id=
        // Identifier )* SEMI | listToken= LIST list= dottedIdentifier SEMI | tableToken= TABLE
        // table= dottedIdentifier SEMI | declareToken= DECLARE type= annotationType id= Identifier
        // ( LPAREN (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4=
        // IntString | obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType | obj2=
        // StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString ) fname=
        // Identifier )* RPAREN ) SEMI )
        int alt21 = 4;
        switch (input.LA(1)) {
          case DECLARE: {
            int LA21_1 = input.LA(2);

            if ((LA21_1 == Identifier)) {
              int LA21_4 = input.LA(3);

              if ((LA21_4 == Identifier)) {
                int LA21_6 = input.LA(4);

                if ((LA21_6 == LPAREN)) {
                  alt21 = 4;
                } else if ((LA21_6 == SEMI || LA21_6 == COMMA)) {
                  alt21 = 1;
                } else {
                  if (state.backtracking > 0) {
                    state.failed = true;
                    return stmts;
                  }
                  NoViableAltException nvae = new NoViableAltException("", 21, 6, input);

                  throw nvae;
                }
              } else if ((LA21_4 == SEMI || LA21_4 == COMMA)) {
                alt21 = 1;
              } else {
                if (state.backtracking > 0) {
                  state.failed = true;
                  return stmts;
                }
                NoViableAltException nvae = new NoViableAltException("", 21, 4, input);

                throw nvae;
              }
            } else if ((LA21_1 == BasicAnnotationType)) {
              int LA21_5 = input.LA(3);

              if ((LA21_5 == Identifier)) {
                int LA21_6 = input.LA(4);

                if ((LA21_6 == LPAREN)) {
                  alt21 = 4;
                } else if ((LA21_6 == SEMI || LA21_6 == COMMA)) {
                  alt21 = 1;
                } else {
                  if (state.backtracking > 0) {
                    state.failed = true;
                    return stmts;
                  }
                  NoViableAltException nvae = new NoViableAltException("", 21, 6, input);

                  throw nvae;
                }
              } else {
                if (state.backtracking > 0) {
                  state.failed = true;
                  return stmts;
                }
                NoViableAltException nvae = new NoViableAltException("", 21, 5, input);

                throw nvae;
              }
            } else {
              if (state.backtracking > 0) {
                state.failed = true;
                return stmts;
              }
              NoViableAltException nvae = new NoViableAltException("", 21, 1, input);

              throw nvae;
            }
          }
            break;
          case LIST: {
            alt21 = 2;
          }
            break;
          case TABLE: {
            alt21 = 3;
          }
            break;
          default:
            if (state.backtracking > 0) {
              state.failed = true;
              return stmts;
            }
            NoViableAltException nvae = new NoViableAltException("", 21, 0, input);

            throw nvae;
        }

        switch (alt21) {
          case 1:
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:353:3:
            // declareToken= DECLARE (lazyParent= annotationType )? id= Identifier ( COMMA id=
            // Identifier )* SEMI
          {
            declareToken = (Token) match(input, DECLARE, FOLLOW_DECLARE_in_declaration816);
            if (state.failed)
              return stmts;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:353:35:
            // (lazyParent= annotationType )?
            int alt16 = 2;
            int LA16_0 = input.LA(1);

            if ((LA16_0 == Identifier)) {
              int LA16_1 = input.LA(2);

              if ((LA16_1 == Identifier)) {
                alt16 = 1;
              }
            } else if ((LA16_0 == BasicAnnotationType)) {
              alt16 = 1;
            }
            switch (alt16) {
              case 1:
                // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:353:35:
                // lazyParent= annotationType
              {
                pushFollow(FOLLOW_annotationType_in_declaration822);
                lazyParent = annotationType();

                state._fsp--;
                if (state.failed)
                  return stmts;

              }
                break;

            }

            id = (Token) match(input, Identifier, FOLLOW_Identifier_in_declaration832);
            if (state.failed)
              return stmts;
            if (state.backtracking == 0) {
              stmts.append(declareToken.getText()).append(" ");
              if (lazyParent != null)
                stmts.append(lazyParent).append(" ");
              stmts.append(id.getText());

            }
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:360:3:
            // ( COMMA id= Identifier )*
            loop17: do {
              int alt17 = 2;
              int LA17_0 = input.LA(1);

              if ((LA17_0 == COMMA)) {
                alt17 = 1;
              }

              switch (alt17) {
                case 1:
                  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:360:4:
                  // COMMA id= Identifier
                {
                  match(input, COMMA, FOLLOW_COMMA_in_declaration843);
                  if (state.failed)
                    return stmts;
                  id = (Token) match(input, Identifier, FOLLOW_Identifier_in_declaration853);
                  if (state.failed)
                    return stmts;
                  if (state.backtracking == 0) {
                    stmts.append(",").append(id.getText());
                  }

                }
                  break;

                default:
                  break loop17;
              }
            } while (true);

            match(input, SEMI, FOLLOW_SEMI_in_declaration863);
            if (state.failed)
              return stmts;
            if (state.backtracking == 0) {
              stmts.append(";");
            }

          }
            break;
          case 2:
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:365:4:
            // listToken= LIST list= dottedIdentifier SEMI
          {
            listToken = (Token) match(input, LIST, FOLLOW_LIST_in_declaration881);
            if (state.failed)
              return stmts;
            pushFollow(FOLLOW_dottedIdentifier_in_declaration891);
            list = dottedIdentifier();

            state._fsp--;
            if (state.failed)
              return stmts;
            match(input, SEMI, FOLLOW_SEMI_in_declaration896);
            if (state.failed)
              return stmts;
            if (state.backtracking == 0) {
              stmts.append(listToken.getText()).append(" ").append(list).append(";");
            }

          }
            break;
          case 3:
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:370:4:
            // tableToken= TABLE table= dottedIdentifier SEMI
          {
            tableToken = (Token) match(input, TABLE, FOLLOW_TABLE_in_declaration909);
            if (state.failed)
              return stmts;
            pushFollow(FOLLOW_dottedIdentifier_in_declaration919);
            table = dottedIdentifier();

            state._fsp--;
            if (state.failed)
              return stmts;
            match(input, SEMI, FOLLOW_SEMI_in_declaration924);
            if (state.failed)
              return stmts;
            if (state.backtracking == 0) {
              stmts.append(tableToken.getText()).append(" ").append(table).append(";");
            }

          }
            break;
          case 4:
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:377:2:
            // declareToken= DECLARE type= annotationType id= Identifier ( LPAREN (obj1=
            // annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString | obj5=
            // BooleanString ) fname= Identifier ( COMMA (obj1= annotationType | obj2= StringString
            // | obj3= DoubleString | obj4= IntString | obj5= BooleanString ) fname= Identifier )*
            // RPAREN ) SEMI
          {
            declareToken = (Token) match(input, DECLARE, FOLLOW_DECLARE_in_declaration939);
            if (state.failed)
              return stmts;
            pushFollow(FOLLOW_annotationType_in_declaration946);
            type = annotationType();

            state._fsp--;
            if (state.failed)
              return stmts;
            id = (Token) match(input, Identifier, FOLLOW_Identifier_in_declaration953);
            if (state.failed)
              return stmts;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:380:3:
            // ( LPAREN (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4=
            // IntString | obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |
            // obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString )
            // fname= Identifier )* RPAREN )
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:380:4:
            // LPAREN (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4=
            // IntString | obj5= BooleanString ) fname= Identifier ( COMMA (obj1= annotationType |
            // obj2= StringString | obj3= DoubleString | obj4= IntString | obj5= BooleanString )
            // fname= Identifier )* RPAREN
            {
              match(input, LPAREN, FOLLOW_LPAREN_in_declaration959);
              if (state.failed)
                return stmts;
              if (state.backtracking == 0) {
                stmts.append(declareToken.getText()).append(" ").append(type).append(" ").append(
                        id.getText()).append("(");
              }
              // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:382:4:
              // (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4= IntString |
              // obj5= BooleanString )
              int alt18 = 5;
              switch (input.LA(1)) {
                case Identifier:
                case BasicAnnotationType: {
                  alt18 = 1;
                }
                  break;
                case StringString: {
                  alt18 = 2;
                }
                  break;
                case DoubleString: {
                  alt18 = 3;
                }
                  break;
                case IntString: {
                  alt18 = 4;
                }
                  break;
                case BooleanString: {
                  alt18 = 5;
                }
                  break;
                default:
                  if (state.backtracking > 0) {
                    state.failed = true;
                    return stmts;
                  }
                  NoViableAltException nvae = new NoViableAltException("", 18, 0, input);

                  throw nvae;
              }

              switch (alt18) {
                case 1:
                  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:383:4:
                  // obj1= annotationType
                {
                  pushFollow(FOLLOW_annotationType_in_declaration979);
                  obj1 = annotationType();

                  state._fsp--;
                  if (state.failed)
                    return stmts;
                  if (state.backtracking == 0) {
                    stmts.append(obj1);
                  }

                }
                  break;
                case 2:
                  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:384:6:
                  // obj2= StringString
                {
                  obj2 = (Token) match(input, StringString, FOLLOW_StringString_in_declaration992);
                  if (state.failed)
                    return stmts;
                  if (state.backtracking == 0) {
                    stmts.append(obj2.getText());
                  }

                }
                  break;
                case 3:
                  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:385:6:
                  // obj3= DoubleString
                {
                  obj3 = (Token) match(input, DoubleString, FOLLOW_DoubleString_in_declaration1005);
                  if (state.failed)
                    return stmts;
                  if (state.backtracking == 0) {
                    stmts.append(obj3.getText());
                  }

                }
                  break;
                case 4:
                  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:386:6:
                  // obj4= IntString
                {
                  obj4 = (Token) match(input, IntString, FOLLOW_IntString_in_declaration1018);
                  if (state.failed)
                    return stmts;
                  if (state.backtracking == 0) {
                    stmts.append(obj4.getText());
                  }

                }
                  break;
                case 5:
                  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:387:6:
                  // obj5= BooleanString
                {
                  obj5 = (Token) match(input, BooleanString,
                          FOLLOW_BooleanString_in_declaration1031);
                  if (state.failed)
                    return stmts;
                  if (state.backtracking == 0) {
                    stmts.append(obj5.getText());
                  }

                }
                  break;

              }

              fname = (Token) match(input, Identifier, FOLLOW_Identifier_in_declaration1048);
              if (state.failed)
                return stmts;
              if (state.backtracking == 0) {
                stmts.append(" ").append(fname.getText());
              }
              // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:390:4:
              // ( COMMA (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4=
              // IntString | obj5= BooleanString ) fname= Identifier )*
              loop20: do {
                int alt20 = 2;
                int LA20_0 = input.LA(1);

                if ((LA20_0 == COMMA)) {
                  alt20 = 1;
                }

                switch (alt20) {
                  case 1:
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:391:4:
                    // COMMA (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4=
                    // IntString | obj5= BooleanString ) fname= Identifier
                  {
                    match(input, COMMA, FOLLOW_COMMA_in_declaration1060);
                    if (state.failed)
                      return stmts;
                    if (state.backtracking == 0) {
                      stmts.append(",");
                    }
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:392:4:
                    // (obj1= annotationType | obj2= StringString | obj3= DoubleString | obj4=
                    // IntString | obj5= BooleanString )
                    int alt19 = 5;
                    switch (input.LA(1)) {
                      case Identifier:
                      case BasicAnnotationType: {
                        alt19 = 1;
                      }
                        break;
                      case StringString: {
                        alt19 = 2;
                      }
                        break;
                      case DoubleString: {
                        alt19 = 3;
                      }
                        break;
                      case IntString: {
                        alt19 = 4;
                      }
                        break;
                      case BooleanString: {
                        alt19 = 5;
                      }
                        break;
                      default:
                        if (state.backtracking > 0) {
                          state.failed = true;
                          return stmts;
                        }
                        NoViableAltException nvae = new NoViableAltException("", 19, 0, input);

                        throw nvae;
                    }

                    switch (alt19) {
                      case 1:
                        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:393:4:
                        // obj1= annotationType
                      {
                        pushFollow(FOLLOW_annotationType_in_declaration1076);
                        obj1 = annotationType();

                        state._fsp--;
                        if (state.failed)
                          return stmts;
                        if (state.backtracking == 0) {
                          stmts.append(obj1);
                        }

                      }
                        break;
                      case 2:
                        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:394:6:
                        // obj2= StringString
                      {
                        obj2 = (Token) match(input, StringString,
                                FOLLOW_StringString_in_declaration1089);
                        if (state.failed)
                          return stmts;
                        if (state.backtracking == 0) {
                          stmts.append(obj2.getText());
                        }

                      }
                        break;
                      case 3:
                        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:395:6:
                        // obj3= DoubleString
                      {
                        obj3 = (Token) match(input, DoubleString,
                                FOLLOW_DoubleString_in_declaration1102);
                        if (state.failed)
                          return stmts;
                        if (state.backtracking == 0) {
                          stmts.append(obj3.getText());
                        }

                      }
                        break;
                      case 4:
                        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:396:6:
                        // obj4= IntString
                      {
                        obj4 = (Token) match(input, IntString, FOLLOW_IntString_in_declaration1115);
                        if (state.failed)
                          return stmts;
                        if (state.backtracking == 0) {
                          stmts.append(obj4.getText());
                        }

                      }
                        break;
                      case 5:
                        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:397:6:
                        // obj5= BooleanString
                      {
                        obj5 = (Token) match(input, BooleanString,
                                FOLLOW_BooleanString_in_declaration1128);
                        if (state.failed)
                          return stmts;
                        if (state.backtracking == 0) {
                          stmts.append(obj5.getText());
                        }

                      }
                        break;

                    }

                    fname = (Token) match(input, Identifier, FOLLOW_Identifier_in_declaration1145);
                    if (state.failed)
                      return stmts;
                    if (state.backtracking == 0) {
                      stmts.append(" ").append(fname.getText());
                    }

                  }
                    break;

                  default:
                    break loop20;
                }
              } while (true);

              match(input, RPAREN, FOLLOW_RPAREN_in_declaration1153);
              if (state.failed)
                return stmts;

            }

            match(input, SEMI, FOLLOW_SEMI_in_declaration1156);
            if (state.failed)
              return stmts;
            if (state.backtracking == 0) {

              stmts.append(")").append(";");

            }

          }
            break;

        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return stmts;
  }

  // $ANTLR end "declaration"

  // $ANTLR start "blockDeclaration"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:411:1:
  // blockDeclaration returns [StringBuilder block = new StringBuilder()] : declareToken=
  // BlockString LPAREN id= Identifier RPAREN ( typeExpression )=>idRef= typeExpression (quantifier=
  // quantifierPart )? ( (cp= conditionPart )=>cp= conditionPart )? (ap= actionPart )? LCURLY body=
  // statements rc= RCURLY ;
  public final StringBuilder blockDeclaration() throws RecognitionException {
    StringBuilder block = new StringBuilder();

    Token declareToken = null;
    Token id = null;
    Token rc = null;
    StringBuilder idRef = null;

    StringBuilder quantifier = null;

    StringBuilder cp = null;

    StringBuilder ap = null;

    StringBuilder body = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:422:2:
      // (declareToken= BlockString LPAREN id= Identifier RPAREN ( typeExpression )=>idRef=
      // typeExpression (quantifier= quantifierPart )? ( (cp= conditionPart )=>cp= conditionPart )?
      // (ap= actionPart )? LCURLY body= statements rc= RCURLY )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:423:2:
      // declareToken= BlockString LPAREN id= Identifier RPAREN ( typeExpression )=>idRef=
      // typeExpression (quantifier= quantifierPart )? ( (cp= conditionPart )=>cp= conditionPart )?
      // (ap= actionPart )? LCURLY body= statements rc= RCURLY
      {
        declareToken = (Token) match(input, BlockString, FOLLOW_BlockString_in_blockDeclaration1191);
        if (state.failed)
          return block;
        match(input, LPAREN, FOLLOW_LPAREN_in_blockDeclaration1195);
        if (state.failed)
          return block;
        id = (Token) match(input, Identifier, FOLLOW_Identifier_in_blockDeclaration1202);
        if (state.failed)
          return block;
        match(input, RPAREN, FOLLOW_RPAREN_in_blockDeclaration1209);
        if (state.failed)
          return block;
        pushFollow(FOLLOW_typeExpression_in_blockDeclaration1218);
        idRef = typeExpression();

        state._fsp--;
        if (state.failed)
          return block;
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:431:52:
        // (quantifier= quantifierPart )?
        int alt22 = 2;
        int LA22_0 = input.LA(1);

        if (((LA22_0 >= STAR && LA22_0 <= LBRACK))) {
          alt22 = 1;
        }
        switch (alt22) {
          case 1:
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:431:52:
            // quantifier= quantifierPart
          {
            pushFollow(FOLLOW_quantifierPart_in_blockDeclaration1224);
            quantifier = quantifierPart();

            state._fsp--;
            if (state.failed)
              return block;

          }
            break;

        }

        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:431:70:
        // ( (cp= conditionPart )=>cp= conditionPart )?
        int alt23 = 2;
        alt23 = dfa23.predict(input);
        switch (alt23) {
          case 1:
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:431:71:
            // (cp= conditionPart )=>cp= conditionPart
          {
            pushFollow(FOLLOW_conditionPart_in_blockDeclaration1240);
            cp = conditionPart();

            state._fsp--;
            if (state.failed)
              return block;

          }
            break;

        }

        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:431:117:
        // (ap= actionPart )?
        int alt24 = 2;
        int LA24_0 = input.LA(1);

        if ((LA24_0 == LPAREN)) {
          alt24 = 1;
        }
        switch (alt24) {
          case 1:
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:431:117:
            // ap= actionPart
          {
            pushFollow(FOLLOW_actionPart_in_blockDeclaration1248);
            ap = actionPart();

            state._fsp--;
            if (state.failed)
              return block;

          }
            break;

        }

        match(input, LCURLY, FOLLOW_LCURLY_in_blockDeclaration1256);
        if (state.failed)
          return block;
        pushFollow(FOLLOW_statements_in_blockDeclaration1262);
        body = statements();

        state._fsp--;
        if (state.failed)
          return block;
        rc = (Token) match(input, RCURLY, FOLLOW_RCURLY_in_blockDeclaration1268);
        if (state.failed)
          return block;
        if (state.backtracking == 0) {
          block.append(declareToken.getText()).append("(").append(id.getText()).append(") ");
          if (quantifier == null) {
            if (cp == null && ap == null)
              block.append(idRef);
            else if (cp == null)
              block.append(idRef).append("{").append(ap).append("} ");
            else if (ap == null)
              block.append(idRef).append("{").append(cp).append("} ");
            else
              block.append(idRef).append("{").append(cp).append(ap).append("} ");
          } else {
            if (cp == null && ap == null)
              block.append(idRef).append(quantifier);
            else if (cp == null)
              block.append(idRef).append(quantifier).append("{").append(ap).append("} ");
            else if (ap == null)
              block.append(idRef).append(quantifier).append("{").append(cp).append("} ");
            else
              block.append(idRef).append(quantifier).append("{").append(cp).append(ap).append("} ");
          }
          block.append("{\n").append(body).append("\n}");

        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return block;
  }

  // $ANTLR end "blockDeclaration"

  // $ANTLR start "simpleStatement"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:464:1:
  // simpleStatement returns [StringBuilder stmt = new StringBuilder()] : elements= ruleElements
  // SEMI ;
  public final StringBuilder simpleStatement() throws RecognitionException {
    StringBuilder stmt = new StringBuilder();

    StringBuilder elements = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:465:2:
      // (elements= ruleElements SEMI )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:466:2:
      // elements= ruleElements SEMI
      {
        pushFollow(FOLLOW_ruleElements_in_simpleStatement1298);
        elements = ruleElements();

        state._fsp--;
        if (state.failed)
          return stmt;
        match(input, SEMI, FOLLOW_SEMI_in_simpleStatement1300);
        if (state.failed)
          return stmt;
        if (state.backtracking == 0) {
          stmt.append(elements).append(";");
        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return stmt;
  }

  // $ANTLR end "simpleStatement"

  // $ANTLR start "ruleElements"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:470:1:
  // ruleElements returns [StringBuilder ruleElements = new StringBuilder()] : re= ruleElement (re=
  // ruleElement )* ;
  public final StringBuilder ruleElements() throws RecognitionException {
    StringBuilder ruleElements = new StringBuilder();

    StringBuilder re = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:471:2:
      // (re= ruleElement (re= ruleElement )* )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:472:2:
      // re= ruleElement (re= ruleElement )*
      {
        pushFollow(FOLLOW_ruleElement_in_ruleElements1326);
        re = ruleElement();

        state._fsp--;
        if (state.failed)
          return ruleElements;
        if (state.backtracking == 0) {
          ruleElements.append(re);
        }
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:472:46:
        // (re= ruleElement )*
        loop25: do {
          int alt25 = 2;
          int LA25_0 = input.LA(1);

          if ((LA25_0 == Identifier || LA25_0 == StringLiteral || LA25_0 == BasicAnnotationType)) {
            alt25 = 1;
          }

          switch (alt25) {
            case 1:
              // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:472:47:
              // re= ruleElement
            {
              pushFollow(FOLLOW_ruleElement_in_ruleElements1335);
              re = ruleElement();

              state._fsp--;
              if (state.failed)
                return ruleElements;
              if (state.backtracking == 0) {
                ruleElements.append(" ").append(re);
              }

            }
              break;

            default:
              break loop25;
          }
        } while (true);

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return ruleElements;
  }

  // $ANTLR end "ruleElements"

  // $ANTLR start "blockRuleElement"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:475:1:
  // blockRuleElement returns [StringBuilder rElement = new StringBuilder()] : re= ruleElementType ;
  public final StringBuilder blockRuleElement() throws RecognitionException {
    StringBuilder rElement = new StringBuilder();

    StringBuilder re = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:476:2:
      // (re= ruleElementType )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:477:2:
      // re= ruleElementType
      {
        pushFollow(FOLLOW_ruleElementType_in_blockRuleElement1362);
        re = ruleElementType();

        state._fsp--;
        if (state.failed)
          return rElement;
        if (state.backtracking == 0) {
          rElement.append(re);
        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return rElement;
  }

  // $ANTLR end "blockRuleElement"

  // $ANTLR start "ruleElement"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:480:1:
  // ruleElement returns [StringBuilder ruleElement = new StringBuilder()] : ( (re1= ruleElementType
  // ) | (re2= ruleElementLiteral ) );
  public final StringBuilder ruleElement() throws RecognitionException {
    StringBuilder ruleElement = new StringBuilder();

    StringBuilder re1 = null;

    StringBuilder re2 = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:481:2:
      // ( (re1= ruleElementType ) | (re2= ruleElementLiteral ) )
      int alt26 = 2;
      int LA26_0 = input.LA(1);

      if ((LA26_0 == Identifier || LA26_0 == BasicAnnotationType)) {
        alt26 = 1;
      } else if ((LA26_0 == StringLiteral)) {
        alt26 = 2;
      } else {
        if (state.backtracking > 0) {
          state.failed = true;
          return ruleElement;
        }
        NoViableAltException nvae = new NoViableAltException("", 26, 0, input);

        throw nvae;
      }
      switch (alt26) {
        case 1:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:482:2:
          // (re1= ruleElementType )
        {
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:482:2:
          // (re1= ruleElementType )
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:483:2:
          // re1= ruleElementType
          {
            pushFollow(FOLLOW_ruleElementType_in_ruleElement1389);
            re1 = ruleElementType();

            state._fsp--;
            if (state.failed)
              return ruleElement;
            if (state.backtracking == 0) {
              ruleElement.append(re1);
            }

          }

        }
          break;
        case 2:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:486:2:
          // (re2= ruleElementLiteral )
        {
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:486:2:
          // (re2= ruleElementLiteral )
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:487:2:
          // re2= ruleElementLiteral
          {
            pushFollow(FOLLOW_ruleElementLiteral_in_ruleElement1408);
            re2 = ruleElementLiteral();

            state._fsp--;
            if (state.failed)
              return ruleElement;
            if (state.backtracking == 0) {
              ruleElement.append(re2);
            }

          }

        }
          break;

      }
    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return ruleElement;
  }

  // $ANTLR end "ruleElement"

  // $ANTLR start "ruleElementType"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:491:1:
  // ruleElementType returns [StringBuilder re = new StringBuilder()] : ( typeExpression )=>idRef=
  // typeExpression (quantifier= quantifierPart )? (cp= conditionPart )? (ap= actionPart )? ;
  public final StringBuilder ruleElementType() throws RecognitionException {
    StringBuilder re = new StringBuilder();

    StringBuilder idRef = null;

    StringBuilder quantifier = null;

    StringBuilder cp = null;

    StringBuilder ap = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:492:2:
      // ( ( typeExpression )=>idRef= typeExpression (quantifier= quantifierPart )? (cp=
      // conditionPart )? (ap= actionPart )? )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:493:2:
      // ( typeExpression )=>idRef= typeExpression (quantifier= quantifierPart )? (cp= conditionPart
      // )? (ap= actionPart )?
      {
        pushFollow(FOLLOW_typeExpression_in_ruleElementType1437);
        idRef = typeExpression();

        state._fsp--;
        if (state.failed)
          return re;
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:493:52:
        // (quantifier= quantifierPart )?
        int alt27 = 2;
        int LA27_0 = input.LA(1);

        if (((LA27_0 >= STAR && LA27_0 <= LBRACK))) {
          alt27 = 1;
        }
        switch (alt27) {
          case 1:
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:493:52:
            // quantifier= quantifierPart
          {
            pushFollow(FOLLOW_quantifierPart_in_ruleElementType1443);
            quantifier = quantifierPart();

            state._fsp--;
            if (state.failed)
              return re;

          }
            break;

        }

        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:493:73:
        // (cp= conditionPart )?
        int alt28 = 2;
        int LA28_0 = input.LA(1);

        if ((LA28_0 == LCURLY)) {
          alt28 = 1;
        }
        switch (alt28) {
          case 1:
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:493:73:
            // cp= conditionPart
          {
            pushFollow(FOLLOW_conditionPart_in_ruleElementType1450);
            cp = conditionPart();

            state._fsp--;
            if (state.failed)
              return re;

          }
            break;

        }

        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:493:93:
        // (ap= actionPart )?
        int alt29 = 2;
        int LA29_0 = input.LA(1);

        if ((LA29_0 == LPAREN)) {
          alt29 = 1;
        }
        switch (alt29) {
          case 1:
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:493:93:
            // ap= actionPart
          {
            pushFollow(FOLLOW_actionPart_in_ruleElementType1457);
            ap = actionPart();

            state._fsp--;
            if (state.failed)
              return re;

          }
            break;

        }

        if (state.backtracking == 0) {

          if (quantifier == null) {
            if (cp == null && ap == null)
              re.append(idRef);
            else if (cp == null)
              re.append(idRef).append("{").append(ap).append("}");
            else if (ap == null)
              re.append(idRef).append("{").append(cp).append("}");
            else
              re.append(idRef).append("{").append(cp).append(ap).append("}");
          } else {
            if (cp == null && ap == null)
              re.append(idRef).append(quantifier);
            else if (cp == null)
              re.append(idRef).append(quantifier).append("{").append(ap).append("}");
            else if (ap == null)
              re.append(idRef).append(quantifier).append("{").append(cp).append("}");
            else
              re.append(idRef).append(quantifier).append("{").append(cp).append(ap).append("}");
          }

        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return re;
  }

  // $ANTLR end "ruleElementType"

  // $ANTLR start "ruleElementLiteral"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:518:1:
  // ruleElementLiteral returns [StringBuilder re = new StringBuilder()] : lit= StringLiteral
  // (quantifier= quantifierPart )? (cp= conditionPart )? (ap= actionPart )? ;
  public final StringBuilder ruleElementLiteral() throws RecognitionException {
    StringBuilder re = new StringBuilder();

    Token lit = null;
    StringBuilder quantifier = null;

    StringBuilder cp = null;

    StringBuilder ap = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:519:2:
      // (lit= StringLiteral (quantifier= quantifierPart )? (cp= conditionPart )? (ap= actionPart )?
      // )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:520:3:
      // lit= StringLiteral (quantifier= quantifierPart )? (cp= conditionPart )? (ap= actionPart )?
      {
        lit = (Token) match(input, StringLiteral, FOLLOW_StringLiteral_in_ruleElementLiteral1484);
        if (state.failed)
          return re;
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:520:34:
        // (quantifier= quantifierPart )?
        int alt30 = 2;
        int LA30_0 = input.LA(1);

        if (((LA30_0 >= STAR && LA30_0 <= LBRACK))) {
          alt30 = 1;
        }
        switch (alt30) {
          case 1:
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:520:34:
            // quantifier= quantifierPart
          {
            pushFollow(FOLLOW_quantifierPart_in_ruleElementLiteral1490);
            quantifier = quantifierPart();

            state._fsp--;
            if (state.failed)
              return re;

          }
            break;

        }

        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:520:55:
        // (cp= conditionPart )?
        int alt31 = 2;
        int LA31_0 = input.LA(1);

        if ((LA31_0 == LCURLY)) {
          alt31 = 1;
        }
        switch (alt31) {
          case 1:
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:520:55:
            // cp= conditionPart
          {
            pushFollow(FOLLOW_conditionPart_in_ruleElementLiteral1497);
            cp = conditionPart();

            state._fsp--;
            if (state.failed)
              return re;

          }
            break;

        }

        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:520:75:
        // (ap= actionPart )?
        int alt32 = 2;
        int LA32_0 = input.LA(1);

        if ((LA32_0 == LPAREN)) {
          alt32 = 1;
        }
        switch (alt32) {
          case 1:
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:520:75:
            // ap= actionPart
          {
            pushFollow(FOLLOW_actionPart_in_ruleElementLiteral1504);
            ap = actionPart();

            state._fsp--;
            if (state.failed)
              return re;

          }
            break;

        }

        if (state.backtracking == 0) {

          // TODO handle quantifierPart
          // re = ScriptFactory.createRuleElement(lit,null,cp,ap);
          if (quantifier == null) {
            if (cp == null && ap == null)
              re.append(lit.getText());
            else if (cp == null)
              re.append(lit.getText()).append("{").append(ap).append("}");
            else if (ap == null)
              re.append(lit.getText()).append("{").append(cp).append("}");
            else
              re.append(lit.getText()).append("{").append(cp).append(ap).append("}");
          } else {
            if (cp == null && ap == null)
              re.append(lit.getText()).append(quantifier);
            else if (cp == null)
              re.append(lit.getText()).append(quantifier).append("{").append(ap).append("}");
            else if (ap == null)
              re.append(lit.getText()).append(quantifier).append("{").append(cp).append("}");
            else
              re.append(lit.getText()).append(quantifier).append("{").append(cp).append(ap).append(
                      "}");
          }

        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return re;
  }

  // $ANTLR end "ruleElementLiteral"

  // $ANTLR start "typeExpression"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:547:1:
  // typeExpression returns [StringBuilder expr = new StringBuilder()] : st= simpleTypeExpression ;
  public final StringBuilder typeExpression() throws RecognitionException {
    StringBuilder expr = new StringBuilder();

    StringBuilder st = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:548:2:
      // (st= simpleTypeExpression )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:550:6:
      // st= simpleTypeExpression
      {
        pushFollow(FOLLOW_simpleTypeExpression_in_typeExpression1534);
        st = simpleTypeExpression();

        state._fsp--;
        if (state.failed)
          return expr;
        if (state.backtracking == 0) {
          expr.append(st);
        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return expr;
  }

  // $ANTLR end "typeExpression"

  // $ANTLR start "simpleTypeExpression"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:570:1:
  // simpleTypeExpression returns [StringBuilder type = new StringBuilder()] : at= annotationType ;
  public final StringBuilder simpleTypeExpression() throws RecognitionException {
    StringBuilder type = new StringBuilder();

    StringBuilder at = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:571:2:
      // (at= annotationType )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:572:2:
      // at= annotationType
      {
        pushFollow(FOLLOW_annotationType_in_simpleTypeExpression1564);
        at = annotationType();

        state._fsp--;
        if (state.failed)
          return type;
        if (state.backtracking == 0) {
          type.append(at);
        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return type;
  }

  // $ANTLR end "simpleTypeExpression"

  // $ANTLR start "quantifierPart"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:576:1:
  // quantifierPart returns [StringBuilder quantifier = new StringBuilder()] : ( STAR (q= QUESTION
  // )? | PLUS (q= QUESTION )? | QUESTION (q= QUESTION )? | ( LBRACK min= numberExpression COMMA
  // max= numberExpression RBRACK (q= QUESTION )? ) );
  public final StringBuilder quantifierPart() throws RecognitionException {
    StringBuilder quantifier = new StringBuilder();

    Token q = null;
    StringBuilder min = null;

    StringBuilder max = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:577:2:
      // ( STAR (q= QUESTION )? | PLUS (q= QUESTION )? | QUESTION (q= QUESTION )? | ( LBRACK min=
      // numberExpression COMMA max= numberExpression RBRACK (q= QUESTION )? ) )
      int alt37 = 4;
      switch (input.LA(1)) {
        case STAR: {
          alt37 = 1;
        }
          break;
        case PLUS: {
          alt37 = 2;
        }
          break;
        case QUESTION: {
          alt37 = 3;
        }
          break;
        case LBRACK: {
          alt37 = 4;
        }
          break;
        default:
          if (state.backtracking > 0) {
            state.failed = true;
            return quantifier;
          }
          NoViableAltException nvae = new NoViableAltException("", 37, 0, input);

          throw nvae;
      }

      switch (alt37) {
        case 1:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:578:3:
          // STAR (q= QUESTION )?
        {
          match(input, STAR, FOLLOW_STAR_in_quantifierPart1586);
          if (state.failed)
            return quantifier;
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:578:10:
          // (q= QUESTION )?
          int alt33 = 2;
          int LA33_0 = input.LA(1);

          if ((LA33_0 == QUESTION)) {
            alt33 = 1;
          }
          switch (alt33) {
            case 1:
              // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:578:10:
              // q= QUESTION
            {
              q = (Token) match(input, QUESTION, FOLLOW_QUESTION_in_quantifierPart1592);
              if (state.failed)
                return quantifier;

            }
              break;

          }

          if (state.backtracking == 0) {
            quantifier.append("*");
            if (q != null)
              quantifier.append(q.getText());
          }

        }
          break;
        case 2:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:579:4:
          // PLUS (q= QUESTION )?
        {
          match(input, PLUS, FOLLOW_PLUS_in_quantifierPart1600);
          if (state.failed)
            return quantifier;
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:579:11:
          // (q= QUESTION )?
          int alt34 = 2;
          int LA34_0 = input.LA(1);

          if ((LA34_0 == QUESTION)) {
            alt34 = 1;
          }
          switch (alt34) {
            case 1:
              // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:579:11:
              // q= QUESTION
            {
              q = (Token) match(input, QUESTION, FOLLOW_QUESTION_in_quantifierPart1606);
              if (state.failed)
                return quantifier;

            }
              break;

          }

          if (state.backtracking == 0) {
            quantifier.append("+");
            if (q != null)
              quantifier.append(q.getText());
          }

        }
          break;
        case 3:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:580:4:
          // QUESTION (q= QUESTION )?
        {
          match(input, QUESTION, FOLLOW_QUESTION_in_quantifierPart1614);
          if (state.failed)
            return quantifier;
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:580:15:
          // (q= QUESTION )?
          int alt35 = 2;
          int LA35_0 = input.LA(1);

          if ((LA35_0 == QUESTION)) {
            alt35 = 1;
          }
          switch (alt35) {
            case 1:
              // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:580:15:
              // q= QUESTION
            {
              q = (Token) match(input, QUESTION, FOLLOW_QUESTION_in_quantifierPart1620);
              if (state.failed)
                return quantifier;

            }
              break;

          }

          if (state.backtracking == 0) {
            quantifier.append("?");
            if (q != null)
              quantifier.append(q.getText());
          }

        }
          break;
        case 4:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:581:4:
          // ( LBRACK min= numberExpression COMMA max= numberExpression RBRACK (q= QUESTION )? )
        {
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:581:4:
          // ( LBRACK min= numberExpression COMMA max= numberExpression RBRACK (q= QUESTION )? )
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:581:5:
          // LBRACK min= numberExpression COMMA max= numberExpression RBRACK (q= QUESTION )?
          {
            match(input, LBRACK, FOLLOW_LBRACK_in_quantifierPart1629);
            if (state.failed)
              return quantifier;
            pushFollow(FOLLOW_numberExpression_in_quantifierPart1635);
            min = numberExpression();

            state._fsp--;
            if (state.failed)
              return quantifier;
            match(input, COMMA, FOLLOW_COMMA_in_quantifierPart1637);
            if (state.failed)
              return quantifier;
            pushFollow(FOLLOW_numberExpression_in_quantifierPart1643);
            max = numberExpression();

            state._fsp--;
            if (state.failed)
              return quantifier;
            match(input, RBRACK, FOLLOW_RBRACK_in_quantifierPart1645);
            if (state.failed)
              return quantifier;
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:581:73:
            // (q= QUESTION )?
            int alt36 = 2;
            int LA36_0 = input.LA(1);

            if ((LA36_0 == QUESTION)) {
              alt36 = 1;
            }
            switch (alt36) {
              case 1:
                // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:581:73:
                // q= QUESTION
              {
                q = (Token) match(input, QUESTION, FOLLOW_QUESTION_in_quantifierPart1651);
                if (state.failed)
                  return quantifier;

              }
                break;

            }

            if (state.backtracking == 0) {
              quantifier.append("[").append(min).append(",").append(max).append("]");
              if (q != null)
                quantifier.append(q.getText());
            }

          }

        }
          break;

      }
    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return quantifier;
  }

  // $ANTLR end "quantifierPart"

  // $ANTLR start "conditionPart"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:588:1:
  // conditionPart returns [StringBuilder conditions = new StringBuilder()] : LCURLY c= condition (
  // SEMI c= condition )* RCURLY ;
  public final StringBuilder conditionPart() throws RecognitionException {
    StringBuilder conditions = new StringBuilder();

    StringBuilder c = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:589:2:
      // ( LCURLY c= condition ( SEMI c= condition )* RCURLY )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:590:2:
      // LCURLY c= condition ( SEMI c= condition )* RCURLY
      {
        match(input, LCURLY, FOLLOW_LCURLY_in_conditionPart1681);
        if (state.failed)
          return conditions;
        pushFollow(FOLLOW_condition_in_conditionPart1687);
        c = condition();

        state._fsp--;
        if (state.failed)
          return conditions;
        if (state.backtracking == 0) {
          conditions.append(c);
        }
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:591:3:
        // ( SEMI c= condition )*
        loop38: do {
          int alt38 = 2;
          int LA38_0 = input.LA(1);

          if ((LA38_0 == SEMI)) {
            alt38 = 1;
          }

          switch (alt38) {
            case 1:
              // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:591:4:
              // SEMI c= condition
            {
              match(input, SEMI, FOLLOW_SEMI_in_conditionPart1694);
              if (state.failed)
                return conditions;
              pushFollow(FOLLOW_condition_in_conditionPart1700);
              c = condition();

              state._fsp--;
              if (state.failed)
                return conditions;
              if (state.backtracking == 0) {
                conditions.append(",").append(c);
              }

            }
              break;

            default:
              break loop38;
          }
        } while (true);

        match(input, RCURLY, FOLLOW_RCURLY_in_conditionPart1707);
        if (state.failed)
          return conditions;

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return conditions;
  }

  // $ANTLR end "conditionPart"

  // $ANTLR start "condition"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:594:1:
  // condition returns [StringBuilder condition = null] : (c= conditionAnd | c= conditionInList | c=
  // conditionMofN | c= conditionNot | c= conditionOr | c= conditionIf | (c= externalCondition )=>c=
  // externalCondition | c= variableCondition ) ;
  public final StringBuilder condition() throws RecognitionException {
    StringBuilder condition = null;

    StringBuilder c = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:595:2:
      // ( (c= conditionAnd | c= conditionInList | c= conditionMofN | c= conditionNot | c=
      // conditionOr | c= conditionIf | (c= externalCondition )=>c= externalCondition | c=
      // variableCondition ) )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:596:2:
      // (c= conditionAnd | c= conditionInList | c= conditionMofN | c= conditionNot | c= conditionOr
      // | c= conditionIf | (c= externalCondition )=>c= externalCondition | c= variableCondition )
      {
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:596:2:
        // (c= conditionAnd | c= conditionInList | c= conditionMofN | c= conditionNot | c=
        // conditionOr | c= conditionIf | (c= externalCondition )=>c= externalCondition | c=
        // variableCondition )
        int alt39 = 8;
        alt39 = dfa39.predict(input);
        switch (alt39) {
          case 1:
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:597:2:
            // c= conditionAnd
          {
            pushFollow(FOLLOW_conditionAnd_in_condition1733);
            c = conditionAnd();

            state._fsp--;
            if (state.failed)
              return condition;

          }
            break;
          case 2:
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:602:4:
            // c= conditionInList
          {
            pushFollow(FOLLOW_conditionInList_in_condition1745);
            c = conditionInList();

            state._fsp--;
            if (state.failed)
              return condition;

          }
            break;
          case 3:
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:606:4:
            // c= conditionMofN
          {
            pushFollow(FOLLOW_conditionMofN_in_condition1757);
            c = conditionMofN();

            state._fsp--;
            if (state.failed)
              return condition;

          }
            break;
          case 4:
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:608:4:
            // c= conditionNot
          {
            pushFollow(FOLLOW_conditionNot_in_condition1768);
            c = conditionNot();

            state._fsp--;
            if (state.failed)
              return condition;

          }
            break;
          case 5:
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:609:4:
            // c= conditionOr
          {
            pushFollow(FOLLOW_conditionOr_in_condition1777);
            c = conditionOr();

            state._fsp--;
            if (state.failed)
              return condition;

          }
            break;
          case 6:
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:616:4:
            // c= conditionIf
          {
            pushFollow(FOLLOW_conditionIf_in_condition1789);
            c = conditionIf();

            state._fsp--;
            if (state.failed)
              return condition;

          }
            break;
          case 7:
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:622:4:
            // (c= externalCondition )=>c= externalCondition
          {
            pushFollow(FOLLOW_externalCondition_in_condition1809);
            c = externalCondition();

            state._fsp--;
            if (state.failed)
              return condition;

          }
            break;
          case 8:
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:623:4:
            // c= variableCondition
          {
            pushFollow(FOLLOW_variableCondition_in_condition1818);
            c = variableCondition();

            state._fsp--;
            if (state.failed)
              return condition;

          }
            break;

        }

        if (state.backtracking == 0) {
          condition = c;
        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return condition;
  }

  // $ANTLR end "condition"

  // $ANTLR start "variableCondition"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:628:1:
  // variableCondition returns [StringBuilder condition = new StringBuilder()] : id= Identifier ;
  public final StringBuilder variableCondition() throws RecognitionException {
    StringBuilder condition = new StringBuilder();

    Token id = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:629:2:
      // (id= Identifier )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:631:3:
      // id= Identifier
      {
        id = (Token) match(input, Identifier, FOLLOW_Identifier_in_variableCondition1850);
        if (state.failed)
          return condition;
        if (state.backtracking == 0) {
          condition.append(id.getText());

        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return condition;
  }

  // $ANTLR end "variableCondition"

  // $ANTLR start "externalCondition"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:640:1:
  // externalCondition returns [StringBuilder condition = new StringBuilder()] : id= Identifier
  // COMMA args= varArgumentList ;
  public final StringBuilder externalCondition() throws RecognitionException {
    StringBuilder condition = new StringBuilder();

    Token id = null;
    StringBuilder args = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:641:2:
      // (id= Identifier COMMA args= varArgumentList )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:643:3:
      // id= Identifier COMMA args= varArgumentList
      {
        id = (Token) match(input, Identifier, FOLLOW_Identifier_in_externalCondition1883);
        if (state.failed)
          return condition;
        match(input, COMMA, FOLLOW_COMMA_in_externalCondition1885);
        if (state.failed)
          return condition;
        pushFollow(FOLLOW_varArgumentList_in_externalCondition1892);
        args = varArgumentList();

        state._fsp--;
        if (state.failed)
          return condition;
        if (state.backtracking == 0) {
          condition.append(id.getText()).append("(").append(args).append(")");

        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return condition;
  }

  // $ANTLR end "externalCondition"

  // $ANTLR start "conditionAnd"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:651:1:
  // conditionAnd returns [StringBuilder cond = new StringBuilder()] : name= AND conds=
  // conditionPart ;
  public final StringBuilder conditionAnd() throws RecognitionException {
    StringBuilder cond = new StringBuilder();

    Token name = null;
    StringBuilder conds = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:652:2:
      // (name= AND conds= conditionPart )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:653:2:
      // name= AND conds= conditionPart
      {
        name = (Token) match(input, AND, FOLLOW_AND_in_conditionAnd1919);
        if (state.failed)
          return cond;
        pushFollow(FOLLOW_conditionPart_in_conditionAnd1925);
        conds = conditionPart();

        state._fsp--;
        if (state.failed)
          return cond;
        if (state.backtracking == 0) {
          cond.append(name.getText()).append("(").append(conds).append(")");
        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return cond;
  }

  // $ANTLR end "conditionAnd"

  // $ANTLR start "conditionInList"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:686:1:
  // conditionInList returns [StringBuilder cond = new StringBuilder()] : (name= INLIST COMMA
  // wordList= dottedIdentifier ( COMMA dist= numberExpression ( COMMA rel= booleanExpression )? )?
  // | name= INLIST COMMA list= innerList ( COMMA dist= numberExpression ( COMMA rel=
  // booleanExpression )? )? );
  public final StringBuilder conditionInList() throws RecognitionException {
    StringBuilder cond = new StringBuilder();

    Token name = null;
    StringBuilder wordList = null;

    StringBuilder dist = null;

    StringBuilder rel = null;

    StringBuilder list = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:687:2:
      // (name= INLIST COMMA wordList= dottedIdentifier ( COMMA dist= numberExpression ( COMMA rel=
      // booleanExpression )? )? | name= INLIST COMMA list= innerList ( COMMA dist= numberExpression
      // ( COMMA rel= booleanExpression )? )? )
      int alt44 = 2;
      int LA44_0 = input.LA(1);

      if ((LA44_0 == INLIST)) {
        int LA44_1 = input.LA(2);

        if ((LA44_1 == COMMA)) {
          int LA44_2 = input.LA(3);

          if ((LA44_2 == LBRACK)) {
            alt44 = 2;
          } else if ((LA44_2 == Identifier)) {
            alt44 = 1;
          } else {
            if (state.backtracking > 0) {
              state.failed = true;
              return cond;
            }
            NoViableAltException nvae = new NoViableAltException("", 44, 2, input);

            throw nvae;
          }
        } else {
          if (state.backtracking > 0) {
            state.failed = true;
            return cond;
          }
          NoViableAltException nvae = new NoViableAltException("", 44, 1, input);

          throw nvae;
        }
      } else {
        if (state.backtracking > 0) {
          state.failed = true;
          return cond;
        }
        NoViableAltException nvae = new NoViableAltException("", 44, 0, input);

        throw nvae;
      }
      switch (alt44) {
        case 1:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:689:2:
          // name= INLIST COMMA wordList= dottedIdentifier ( COMMA dist= numberExpression ( COMMA
          // rel= booleanExpression )? )?
        {
          name = (Token) match(input, INLIST, FOLLOW_INLIST_in_conditionInList1952);
          if (state.failed)
            return cond;
          match(input, COMMA, FOLLOW_COMMA_in_conditionInList1954);
          if (state.failed)
            return cond;
          pushFollow(FOLLOW_dottedIdentifier_in_conditionInList1960);
          wordList = dottedIdentifier();

          state._fsp--;
          if (state.failed)
            return cond;
          if (state.backtracking == 0) {
            cond.append(name.getText()).append("(").append(wordList);
          }
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:690:3:
          // ( COMMA dist= numberExpression ( COMMA rel= booleanExpression )? )?
          int alt41 = 2;
          int LA41_0 = input.LA(1);

          if ((LA41_0 == COMMA)) {
            alt41 = 1;
          }
          switch (alt41) {
            case 1:
              // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:690:4:
              // COMMA dist= numberExpression ( COMMA rel= booleanExpression )?
            {
              match(input, COMMA, FOLLOW_COMMA_in_conditionInList1967);
              if (state.failed)
                return cond;
              pushFollow(FOLLOW_numberExpression_in_conditionInList1973);
              dist = numberExpression();

              state._fsp--;
              if (state.failed)
                return cond;
              if (state.backtracking == 0) {
                cond.append(",").append(dist);
              }
              // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:690:67:
              // ( COMMA rel= booleanExpression )?
              int alt40 = 2;
              int LA40_0 = input.LA(1);

              if ((LA40_0 == COMMA)) {
                alt40 = 1;
              }
              switch (alt40) {
                case 1:
                  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:690:68:
                  // COMMA rel= booleanExpression
                {
                  match(input, COMMA, FOLLOW_COMMA_in_conditionInList1978);
                  if (state.failed)
                    return cond;
                  pushFollow(FOLLOW_booleanExpression_in_conditionInList1984);
                  rel = booleanExpression();

                  state._fsp--;
                  if (state.failed)
                    return cond;
                  if (state.backtracking == 0) {
                    cond.append(",").append(rel);
                  }

                }
                  break;

              }

            }
              break;

          }

          if (state.backtracking == 0) {
            cond.append(")");
          }

        }
          break;
        case 2:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:692:4:
          // name= INLIST COMMA list= innerList ( COMMA dist= numberExpression ( COMMA rel=
          // booleanExpression )? )?
        {
          name = (Token) match(input, INLIST, FOLLOW_INLIST_in_conditionInList2003);
          if (state.failed)
            return cond;
          match(input, COMMA, FOLLOW_COMMA_in_conditionInList2005);
          if (state.failed)
            return cond;
          pushFollow(FOLLOW_innerList_in_conditionInList2011);
          list = innerList();

          state._fsp--;
          if (state.failed)
            return cond;
          if (state.backtracking == 0) {
            cond.append(name.getText()).append("(").append(list);
          }
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:693:3:
          // ( COMMA dist= numberExpression ( COMMA rel= booleanExpression )? )?
          int alt43 = 2;
          int LA43_0 = input.LA(1);

          if ((LA43_0 == COMMA)) {
            alt43 = 1;
          }
          switch (alt43) {
            case 1:
              // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:693:4:
              // COMMA dist= numberExpression ( COMMA rel= booleanExpression )?
            {
              match(input, COMMA, FOLLOW_COMMA_in_conditionInList2018);
              if (state.failed)
                return cond;
              pushFollow(FOLLOW_numberExpression_in_conditionInList2024);
              dist = numberExpression();

              state._fsp--;
              if (state.failed)
                return cond;
              if (state.backtracking == 0) {
                cond.append(",").append(dist);
              }
              // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:693:67:
              // ( COMMA rel= booleanExpression )?
              int alt42 = 2;
              int LA42_0 = input.LA(1);

              if ((LA42_0 == COMMA)) {
                alt42 = 1;
              }
              switch (alt42) {
                case 1:
                  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:693:68:
                  // COMMA rel= booleanExpression
                {
                  match(input, COMMA, FOLLOW_COMMA_in_conditionInList2029);
                  if (state.failed)
                    return cond;
                  pushFollow(FOLLOW_booleanExpression_in_conditionInList2035);
                  rel = booleanExpression();

                  state._fsp--;
                  if (state.failed)
                    return cond;
                  if (state.backtracking == 0) {
                    cond.append(",").append(rel);
                  }

                }
                  break;

              }

            }
              break;

          }

          if (state.backtracking == 0) {
            cond.append(")");
          }

        }
          break;

      }
    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return cond;
  }

  // $ANTLR end "conditionInList"

  // $ANTLR start "conditionMofN"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:718:1:
  // conditionMofN returns [StringBuilder cond = new StringBuilder()] : name= MOFN COMMA min=
  // numberExpression COMMA max= numberExpression conds= conditionPart ;
  public final StringBuilder conditionMofN() throws RecognitionException {
    StringBuilder cond = new StringBuilder();

    Token name = null;
    StringBuilder min = null;

    StringBuilder max = null;

    StringBuilder conds = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:719:2:
      // (name= MOFN COMMA min= numberExpression COMMA max= numberExpression conds= conditionPart )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:720:2:
      // name= MOFN COMMA min= numberExpression COMMA max= numberExpression conds= conditionPart
      {
        name = (Token) match(input, MOFN, FOLLOW_MOFN_in_conditionMofN2066);
        if (state.failed)
          return cond;
        match(input, COMMA, FOLLOW_COMMA_in_conditionMofN2068);
        if (state.failed)
          return cond;
        pushFollow(FOLLOW_numberExpression_in_conditionMofN2074);
        min = numberExpression();

        state._fsp--;
        if (state.failed)
          return cond;
        match(input, COMMA, FOLLOW_COMMA_in_conditionMofN2076);
        if (state.failed)
          return cond;
        pushFollow(FOLLOW_numberExpression_in_conditionMofN2082);
        max = numberExpression();

        state._fsp--;
        if (state.failed)
          return cond;
        pushFollow(FOLLOW_conditionPart_in_conditionMofN2088);
        conds = conditionPart();

        state._fsp--;
        if (state.failed)
          return cond;
        if (state.backtracking == 0) {
          cond.append(name.getText()).append("(").append(min).append(",").append(max).append("(")
                  .append(conds).append(")");
        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return cond;
  }

  // $ANTLR end "conditionMofN"

  // $ANTLR start "conditionNot"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:730:1:
  // conditionNot returns [StringBuilder cond = new StringBuilder()] : name= MINUS c= condition ;
  public final StringBuilder conditionNot() throws RecognitionException {
    StringBuilder cond = new StringBuilder();

    Token name = null;
    StringBuilder c = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:731:2:
      // (name= MINUS c= condition )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:732:2:
      // name= MINUS c= condition
      {
        name = (Token) match(input, MINUS, FOLLOW_MINUS_in_conditionNot2114);
        if (state.failed)
          return cond;
        pushFollow(FOLLOW_condition_in_conditionNot2120);
        c = condition();

        state._fsp--;
        if (state.failed)
          return cond;
        if (state.backtracking == 0) {
          cond.append(name.getText()).append(c);
        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return cond;
  }

  // $ANTLR end "conditionNot"

  // $ANTLR start "conditionOr"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:735:1:
  // conditionOr returns [StringBuilder cond = new StringBuilder()] : name= OR conds= conditionPart
  // ;
  public final StringBuilder conditionOr() throws RecognitionException {
    StringBuilder cond = new StringBuilder();

    Token name = null;
    StringBuilder conds = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:736:2:
      // (name= OR conds= conditionPart )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:737:2:
      // name= OR conds= conditionPart
      {
        name = (Token) match(input, OR, FOLLOW_OR_in_conditionOr2144);
        if (state.failed)
          return cond;
        pushFollow(FOLLOW_conditionPart_in_conditionOr2150);
        conds = conditionPart();

        state._fsp--;
        if (state.failed)
          return cond;
        if (state.backtracking == 0) {
          cond.append(name.getText()).append("(").append(conds).append(")");
        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return cond;
  }

  // $ANTLR end "conditionOr"

  // $ANTLR start "conditionIf"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:775:1:
  // conditionIf returns [StringBuilder cond = new StringBuilder()] : name= IF COMMA e=
  // booleanExpression ;
  public final StringBuilder conditionIf() throws RecognitionException {
    StringBuilder cond = new StringBuilder();

    Token name = null;
    StringBuilder e = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:776:2:
      // (name= IF COMMA e= booleanExpression )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:777:2:
      // name= IF COMMA e= booleanExpression
      {
        name = (Token) match(input, IF, FOLLOW_IF_in_conditionIf2176);
        if (state.failed)
          return cond;
        match(input, COMMA, FOLLOW_COMMA_in_conditionIf2178);
        if (state.failed)
          return cond;
        pushFollow(FOLLOW_booleanExpression_in_conditionIf2184);
        e = booleanExpression();

        state._fsp--;
        if (state.failed)
          return cond;
        if (state.backtracking == 0) {
          cond.append(name.getText()).append("(").append(e).append(")");
        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return cond;
  }

  // $ANTLR end "conditionIf"

  // $ANTLR start "actionPart"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:815:1:
  // actionPart returns [StringBuilder actions = null] : ( LPAREN action )=> LPAREN a= action ( SEMI
  // a= action )* RPAREN ;
  public final StringBuilder actionPart() throws RecognitionException {
    StringBuilder actions = null;

    StringBuilder a = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:816:2:
      // ( ( LPAREN action )=> LPAREN a= action ( SEMI a= action )* RPAREN )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:817:2:
      // ( LPAREN action )=> LPAREN a= action ( SEMI a= action )* RPAREN
      {
        match(input, LPAREN, FOLLOW_LPAREN_in_actionPart2216);
        if (state.failed)
          return actions;
        pushFollow(FOLLOW_action_in_actionPart2222);
        a = action();

        state._fsp--;
        if (state.failed)
          return actions;
        if (state.backtracking == 0) {
          actions = new StringBuilder("->");
          actions.append(a);
        }
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:818:3:
        // ( SEMI a= action )*
        loop45: do {
          int alt45 = 2;
          int LA45_0 = input.LA(1);

          if ((LA45_0 == SEMI)) {
            alt45 = 1;
          }

          switch (alt45) {
            case 1:
              // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:818:4:
              // SEMI a= action
            {
              match(input, SEMI, FOLLOW_SEMI_in_actionPart2229);
              if (state.failed)
                return actions;
              pushFollow(FOLLOW_action_in_actionPart2235);
              a = action();

              state._fsp--;
              if (state.failed)
                return actions;
              if (state.backtracking == 0) {
                actions.append(",").append(a);
              }

            }
              break;

            default:
              break loop45;
          }
        } while (true);

        match(input, RPAREN, FOLLOW_RPAREN_in_actionPart2242);
        if (state.failed)
          return actions;

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return actions;
  }

  // $ANTLR end "actionPart"

  // $ANTLR start "action"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:821:1:
  // action returns [StringBuilder result = new StringBuilder()] : (a= actionCreateStructure | a=
  // actionCall | (a= externalAction )=>a= externalAction | a= variableAction ) ;
  public final StringBuilder action() throws RecognitionException {
    StringBuilder result = new StringBuilder();

    StringBuilder a = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:822:2:
      // ( (a= actionCreateStructure | a= actionCall | (a= externalAction )=>a= externalAction | a=
      // variableAction ) )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:823:2:
      // (a= actionCreateStructure | a= actionCall | (a= externalAction )=>a= externalAction | a=
      // variableAction )
      {
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:823:2:
        // (a= actionCreateStructure | a= actionCall | (a= externalAction )=>a= externalAction | a=
        // variableAction )
        int alt46 = 4;
        switch (input.LA(1)) {
          case CREATE:
          case FILL: {
            alt46 = 1;
          }
            break;
          case CALL: {
            alt46 = 2;
          }
            break;
          case Identifier: {
            int LA46_3 = input.LA(2);

            if ((LA46_3 == COMMA) && (synpred6_ConvertSyntax())) {
              alt46 = 3;
            } else if ((LA46_3 == EOF || LA46_3 == SEMI || LA46_3 == RPAREN)) {
              alt46 = 4;
            } else {
              if (state.backtracking > 0) {
                state.failed = true;
                return result;
              }
              NoViableAltException nvae = new NoViableAltException("", 46, 3, input);

              throw nvae;
            }
          }
            break;
          default:
            if (state.backtracking > 0) {
              state.failed = true;
              return result;
            }
            NoViableAltException nvae = new NoViableAltException("", 46, 0, input);

            throw nvae;
        }

        switch (alt46) {
          case 1:
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:835:6:
            // a= actionCreateStructure
          {
            pushFollow(FOLLOW_actionCreateStructure_in_action2266);
            a = actionCreateStructure();

            state._fsp--;
            if (state.failed)
              return result;

          }
            break;
          case 2:
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:837:4:
            // a= actionCall
          {
            pushFollow(FOLLOW_actionCall_in_action2277);
            a = actionCall();

            state._fsp--;
            if (state.failed)
              return result;

          }
            break;
          case 3:
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:845:4:
            // (a= externalAction )=>a= externalAction
          {
            pushFollow(FOLLOW_externalAction_in_action2297);
            a = externalAction();

            state._fsp--;
            if (state.failed)
              return result;

          }
            break;
          case 4:
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:846:4:
            // a= variableAction
          {
            pushFollow(FOLLOW_variableAction_in_action2306);
            a = variableAction();

            state._fsp--;
            if (state.failed)
              return result;

          }
            break;

        }

        if (state.backtracking == 0) {
          result = a;
        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return result;
  }

  // $ANTLR end "action"

  // $ANTLR start "variableAction"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:851:1:
  // variableAction returns [StringBuilder action = new StringBuilder()] : id= Identifier ;
  public final StringBuilder variableAction() throws RecognitionException {
    StringBuilder action = new StringBuilder();

    Token id = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:852:2:
      // (id= Identifier )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:854:3:
      // id= Identifier
      {
        id = (Token) match(input, Identifier, FOLLOW_Identifier_in_variableAction2335);
        if (state.failed)
          return action;
        if (state.backtracking == 0) {
          action.append(id.getText());
        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return action;
  }

  // $ANTLR end "variableAction"

  // $ANTLR start "externalAction"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:860:1:
  // externalAction returns [StringBuilder action = new StringBuilder()] : id= Identifier COMMA
  // args= varArgumentList ;
  public final StringBuilder externalAction() throws RecognitionException {
    StringBuilder action = new StringBuilder();

    Token id = null;
    StringBuilder args = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:861:2:
      // (id= Identifier COMMA args= varArgumentList )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:863:3:
      // id= Identifier COMMA args= varArgumentList
      {
        id = (Token) match(input, Identifier, FOLLOW_Identifier_in_externalAction2364);
        if (state.failed)
          return action;
        match(input, COMMA, FOLLOW_COMMA_in_externalAction2366);
        if (state.failed)
          return action;
        pushFollow(FOLLOW_varArgumentList_in_externalAction2372);
        args = varArgumentList();

        state._fsp--;
        if (state.failed)
          return action;
        if (state.backtracking == 0) {
          action.append(id.getText()).append("(").append(args).append(")");
        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return action;
  }

  // $ANTLR end "externalAction"

  // $ANTLR start "actionCreateStructure"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:871:1:
  // actionCreateStructure returns [StringBuilder exprs = new StringBuilder()] : name= ( CREATE |
  // FILL ) COMMA structure= typeExpression ( COMMA fname= stringExpression ASSIGN_EQUAL obj1=
  // argument )+ ;
  public final StringBuilder actionCreateStructure() throws RecognitionException {
    StringBuilder exprs = new StringBuilder();

    Token name = null;
    StringBuilder structure = null;

    StringBuilder fname = null;

    StringBuilder obj1 = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:872:2:
      // (name= ( CREATE | FILL ) COMMA structure= typeExpression ( COMMA fname= stringExpression
      // ASSIGN_EQUAL obj1= argument )+ )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:873:2:
      // name= ( CREATE | FILL ) COMMA structure= typeExpression ( COMMA fname= stringExpression
      // ASSIGN_EQUAL obj1= argument )+
      {
        name = input.LT(1);
        if ((input.LA(1) >= CREATE && input.LA(1) <= FILL)) {
          input.consume();
          state.errorRecovery = false;
          state.failed = false;
        } else {
          if (state.backtracking > 0) {
            state.failed = true;
            return exprs;
          }
          MismatchedSetException mse = new MismatchedSetException(null, input);
          throw mse;
        }

        match(input, COMMA, FOLLOW_COMMA_in_actionCreateStructure2408);
        if (state.failed)
          return exprs;
        pushFollow(FOLLOW_typeExpression_in_actionCreateStructure2414);
        structure = typeExpression();

        state._fsp--;
        if (state.failed)
          return exprs;
        if (state.backtracking == 0) {
          exprs.append(name.getText()).append("(").append(structure);
        }
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:874:2:
        // ( COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument )+
        int cnt47 = 0;
        loop47: do {
          int alt47 = 2;
          int LA47_0 = input.LA(1);

          if ((LA47_0 == COMMA)) {
            alt47 = 1;
          }

          switch (alt47) {
            case 1:
              // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:875:2:
              // COMMA fname= stringExpression ASSIGN_EQUAL obj1= argument
            {
              match(input, COMMA, FOLLOW_COMMA_in_actionCreateStructure2422);
              if (state.failed)
                return exprs;
              pushFollow(FOLLOW_stringExpression_in_actionCreateStructure2428);
              fname = stringExpression();

              state._fsp--;
              if (state.failed)
                return exprs;
              match(input, ASSIGN_EQUAL, FOLLOW_ASSIGN_EQUAL_in_actionCreateStructure2430);
              if (state.failed)
                return exprs;
              pushFollow(FOLLOW_argument_in_actionCreateStructure2436);
              obj1 = argument();

              state._fsp--;
              if (state.failed)
                return exprs;
              if (state.backtracking == 0) {
                exprs.append(",").append(fname).append(" = ").append(obj1);
              }

            }
              break;

            default:
              if (cnt47 >= 1)
                break loop47;
              if (state.backtracking > 0) {
                state.failed = true;
                return exprs;
              }
              EarlyExitException eee = new EarlyExitException(47, input);
              throw eee;
          }
          cnt47++;
        } while (true);

        if (state.backtracking == 0) {
          exprs.append(")");
        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return exprs;
  }

  // $ANTLR end "actionCreateStructure"

  // $ANTLR start "actionCall"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:999:1:
  // actionCall returns [StringBuilder action = new StringBuilder()] : name= CALL COMMA ns= dottedId
  // ;
  public final StringBuilder actionCall() throws RecognitionException {
    StringBuilder action = new StringBuilder();

    Token name = null;
    StringBuilder ns = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1000:2:
      // (name= CALL COMMA ns= dottedId )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1001:2:
      // name= CALL COMMA ns= dottedId
      {
        name = (Token) match(input, CALL, FOLLOW_CALL_in_actionCall2467);
        if (state.failed)
          return action;
        match(input, COMMA, FOLLOW_COMMA_in_actionCall2469);
        if (state.failed)
          return action;
        pushFollow(FOLLOW_dottedId_in_actionCall2475);
        ns = dottedId();

        state._fsp--;
        if (state.failed)
          return action;
        if (state.backtracking == 0) {
          action.append(name.getText()).append("(").append(ns).append(")");
        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return action;
  }

  // $ANTLR end "actionCall"

  // $ANTLR start "varArgumentList"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1087:1:
  // varArgumentList returns [StringBuilder args = new StringBuilder()] : arg= argument ( COMMA arg=
  // argument )* ;
  public final StringBuilder varArgumentList() throws RecognitionException {
    StringBuilder args = new StringBuilder();

    StringBuilder arg = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1088:2:
      // (arg= argument ( COMMA arg= argument )* )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1089:2:
      // arg= argument ( COMMA arg= argument )*
      {
        pushFollow(FOLLOW_argument_in_varArgumentList2501);
        arg = argument();

        state._fsp--;
        if (state.failed)
          return args;
        if (state.backtracking == 0) {
          args.append(arg);
        }
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1089:37:
        // ( COMMA arg= argument )*
        loop48: do {
          int alt48 = 2;
          int LA48_0 = input.LA(1);

          if ((LA48_0 == COMMA)) {
            alt48 = 1;
          }

          switch (alt48) {
            case 1:
              // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1089:38:
              // COMMA arg= argument
            {
              match(input, COMMA, FOLLOW_COMMA_in_varArgumentList2506);
              if (state.failed)
                return args;
              pushFollow(FOLLOW_argument_in_varArgumentList2512);
              arg = argument();

              state._fsp--;
              if (state.failed)
                return args;
              if (state.backtracking == 0) {
                args.append(",").append(arg);
              }

            }
              break;

            default:
              break loop48;
          }
        } while (true);

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return args;
  }

  // $ANTLR end "varArgumentList"

  // $ANTLR start "argument"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1093:1:
  // argument returns [StringBuilder arg = new StringBuilder()] options {backtrack=true; } : (a4=
  // stringExpression | a2= booleanExpression | a3= numberExpression | a1= typeExpression );
  public final StringBuilder argument() throws RecognitionException {
    StringBuilder arg = new StringBuilder();

    StringBuilder a4 = null;

    StringBuilder a2 = null;

    StringBuilder a3 = null;

    StringBuilder a1 = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1097:2:
      // (a4= stringExpression | a2= booleanExpression | a3= numberExpression | a1= typeExpression )
      int alt49 = 4;
      alt49 = dfa49.predict(input);
      switch (alt49) {
        case 1:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1098:3:
          // a4= stringExpression
        {
          pushFollow(FOLLOW_stringExpression_in_argument2551);
          a4 = stringExpression();

          state._fsp--;
          if (state.failed)
            return arg;
          if (state.backtracking == 0) {
            arg.append(a4);
          }

        }
          break;
        case 2:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1099:4:
          // a2= booleanExpression
        {
          pushFollow(FOLLOW_booleanExpression_in_argument2562);
          a2 = booleanExpression();

          state._fsp--;
          if (state.failed)
            return arg;
          if (state.backtracking == 0) {
            arg.append(a2);
          }

        }
          break;
        case 3:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1100:4:
          // a3= numberExpression
        {
          pushFollow(FOLLOW_numberExpression_in_argument2573);
          a3 = numberExpression();

          state._fsp--;
          if (state.failed)
            return arg;
          if (state.backtracking == 0) {
            arg.append(a3);
          }

        }
          break;
        case 4:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1101:4:
          // a1= typeExpression
        {
          pushFollow(FOLLOW_typeExpression_in_argument2584);
          a1 = typeExpression();

          state._fsp--;
          if (state.failed)
            return arg;
          if (state.backtracking == 0) {
            arg.append(a1);
          }

        }
          break;

      }
    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return arg;
  }

  // $ANTLR end "argument"

  // $ANTLR start "innerList"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1113:1:
  // innerList returns [StringBuilder result = new StringBuilder()] : LBRACK id= Identifier ( '|'
  // id= Identifier )* RBRACK ;
  public final StringBuilder innerList() throws RecognitionException {
    StringBuilder result = new StringBuilder();

    Token id = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1114:2:
      // ( LBRACK id= Identifier ( '|' id= Identifier )* RBRACK )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1115:2:
      // LBRACK id= Identifier ( '|' id= Identifier )* RBRACK
      {
        match(input, LBRACK, FOLLOW_LBRACK_in_innerList2619);
        if (state.failed)
          return result;
        id = (Token) match(input, Identifier, FOLLOW_Identifier_in_innerList2625);
        if (state.failed)
          return result;
        if (state.backtracking == 0) {
          result.append("[").append(id.getText());
        }
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1117:2:
        // ( '|' id= Identifier )*
        loop50: do {
          int alt50 = 2;
          int LA50_0 = input.LA(1);

          if ((LA50_0 == 77)) {
            alt50 = 1;
          }

          switch (alt50) {
            case 1:
              // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1117:3:
              // '|' id= Identifier
            {
              match(input, 77, FOLLOW_77_in_innerList2633);
              if (state.failed)
                return result;
              id = (Token) match(input, Identifier, FOLLOW_Identifier_in_innerList2639);
              if (state.failed)
                return result;
              if (state.backtracking == 0) {
                result.append("|").append(id.getText());
              }

            }
              break;

            default:
              break loop50;
          }
        } while (true);

        match(input, RBRACK, FOLLOW_RBRACK_in_innerList2646);
        if (state.failed)
          return result;
        if (state.backtracking == 0) {
          result.append("]");
        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return result;
  }

  // $ANTLR end "innerList"

  // $ANTLR start "dottedIdentifier"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1122:1:
  // dottedIdentifier returns [StringBuilder token = new StringBuilder()] : id= Identifier (dot= DOT
  // idn= Identifier )* ;
  public final StringBuilder dottedIdentifier() throws RecognitionException {
    StringBuilder token = new StringBuilder();

    Token id = null;
    Token dot = null;
    Token idn = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1123:2:
      // (id= Identifier (dot= DOT idn= Identifier )* )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1124:2:
      // id= Identifier (dot= DOT idn= Identifier )*
      {
        id = (Token) match(input, Identifier, FOLLOW_Identifier_in_dottedIdentifier2671);
        if (state.failed)
          return token;
        if (state.backtracking == 0) {
          token.append(id.getText());
        }
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1125:2:
        // (dot= DOT idn= Identifier )*
        loop51: do {
          int alt51 = 2;
          int LA51_0 = input.LA(1);

          if ((LA51_0 == DOT)) {
            alt51 = 1;
          }

          switch (alt51) {
            case 1:
              // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1126:3:
              // dot= DOT idn= Identifier
            {
              dot = (Token) match(input, DOT, FOLLOW_DOT_in_dottedIdentifier2684);
              if (state.failed)
                return token;
              idn = (Token) match(input, Identifier, FOLLOW_Identifier_in_dottedIdentifier2692);
              if (state.failed)
                return token;
              if (state.backtracking == 0) {
                token.append(dot.getText()).append(id.getText());
              }

            }
              break;

            default:
              break loop51;
          }
        } while (true);

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return token;
  }

  // $ANTLR end "dottedIdentifier"

  // $ANTLR start "dottedId"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1133:1:
  // dottedId returns [StringBuilder token = new StringBuilder()] : id= Identifier (dot= DOT id=
  // Identifier )* ;
  public final StringBuilder dottedId() throws RecognitionException {
    StringBuilder token = new StringBuilder();

    Token id = null;
    Token dot = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1135:2:
      // (id= Identifier (dot= DOT id= Identifier )* )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1136:2:
      // id= Identifier (dot= DOT id= Identifier )*
      {
        id = (Token) match(input, Identifier, FOLLOW_Identifier_in_dottedId2722);
        if (state.failed)
          return token;
        if (state.backtracking == 0) {
          token.append(id.getText());
        }
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1139:2:
        // (dot= DOT id= Identifier )*
        loop52: do {
          int alt52 = 2;
          int LA52_0 = input.LA(1);

          if ((LA52_0 == DOT)) {
            alt52 = 1;
          }

          switch (alt52) {
            case 1:
              // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1143:3:
              // dot= DOT id= Identifier
            {
              dot = (Token) match(input, DOT, FOLLOW_DOT_in_dottedId2746);
              if (state.failed)
                return token;
              id = (Token) match(input, Identifier, FOLLOW_Identifier_in_dottedId2755);
              if (state.failed)
                return token;
              if (state.backtracking == 0) {
                token.append(dot.getText()).append(id.getText());
              }

            }
              break;

            default:
              break loop52;
          }
        } while (true);

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return token;
  }

  // $ANTLR end "dottedId"

  // $ANTLR start "annotationType"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1152:1:
  // annotationType returns [StringBuilder at = new StringBuilder()] : (atRef=
  // annotationTypeVariableReference | basicAT= BasicAnnotationType ) ;
  public final StringBuilder annotationType() throws RecognitionException {
    StringBuilder at = new StringBuilder();

    Token basicAT = null;
    StringBuilder atRef = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1153:2:
      // ( (atRef= annotationTypeVariableReference | basicAT= BasicAnnotationType ) )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1154:2:
      // (atRef= annotationTypeVariableReference | basicAT= BasicAnnotationType )
      {
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1154:2:
        // (atRef= annotationTypeVariableReference | basicAT= BasicAnnotationType )
        int alt53 = 2;
        int LA53_0 = input.LA(1);

        if ((LA53_0 == Identifier)) {
          alt53 = 1;
        } else if ((LA53_0 == BasicAnnotationType)) {
          alt53 = 2;
        } else {
          if (state.backtracking > 0) {
            state.failed = true;
            return at;
          }
          NoViableAltException nvae = new NoViableAltException("", 53, 0, input);

          throw nvae;
        }
        switch (alt53) {
          case 1:
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1157:2:
            // atRef= annotationTypeVariableReference
          {
            pushFollow(FOLLOW_annotationTypeVariableReference_in_annotationType2793);
            atRef = annotationTypeVariableReference();

            state._fsp--;
            if (state.failed)
              return at;
            if (state.backtracking == 0) {
              at.append(atRef);
            }

          }
            break;
          case 2:
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1158:4:
            // basicAT= BasicAnnotationType
          {
            basicAT = (Token) match(input, BasicAnnotationType,
                    FOLLOW_BasicAnnotationType_in_annotationType2804);
            if (state.failed)
              return at;
            if (state.backtracking == 0) {
              at.append(basicAT.getText());
            }

          }
            break;

        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return at;
  }

  // $ANTLR end "annotationType"

  // $ANTLR start "annotationTypeVariableReference"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1162:1:
  // annotationTypeVariableReference returns [StringBuilder typeVar = new StringBuilder()] : atRef=
  // Identifier ;
  public final StringBuilder annotationTypeVariableReference() throws RecognitionException {
    StringBuilder typeVar = new StringBuilder();

    Token atRef = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1163:3:
      // (atRef= Identifier )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1165:3:
      // atRef= Identifier
      {
        atRef = (Token) match(input, Identifier,
                FOLLOW_Identifier_in_annotationTypeVariableReference2835);
        if (state.failed)
          return typeVar;
        if (state.backtracking == 0) {
          typeVar.append(atRef.getText());
        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return typeVar;
  }

  // $ANTLR end "annotationTypeVariableReference"

  // $ANTLR start "numberExpression"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1170:1:
  // numberExpression returns [StringBuilder expr = new StringBuilder()] : e= additiveExpression ;
  public final StringBuilder numberExpression() throws RecognitionException {
    StringBuilder expr = new StringBuilder();

    StringBuilder e = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1171:2:
      // (e= additiveExpression )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1172:2:
      // e= additiveExpression
      {
        pushFollow(FOLLOW_additiveExpression_in_numberExpression2860);
        e = additiveExpression();

        state._fsp--;
        if (state.failed)
          return expr;
        if (state.backtracking == 0) {
          /* if(e!=null) */expr.append(e);
        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return expr;
  }

  // $ANTLR end "numberExpression"

  // $ANTLR start "additiveExpression"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1177:1:
  // additiveExpression returns [StringBuilder root = new StringBuilder()] : expr1=
  // multiplicativeExpression (op= ( PLUS | MINUS ) expr2= multiplicativeExpression )* ;
  public final StringBuilder additiveExpression() throws RecognitionException {
    StringBuilder root = new StringBuilder();

    Token op = null;
    StringBuilder expr1 = null;

    StringBuilder expr2 = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1178:5:
      // (expr1= multiplicativeExpression (op= ( PLUS | MINUS ) expr2= multiplicativeExpression )* )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1178:9:
      // expr1= multiplicativeExpression (op= ( PLUS | MINUS ) expr2= multiplicativeExpression )*
      {
        pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression2886);
        expr1 = multiplicativeExpression();

        state._fsp--;
        if (state.failed)
          return root;
        if (state.backtracking == 0) {
          root.append(expr1);
        }
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1179:2:
        // (op= ( PLUS | MINUS ) expr2= multiplicativeExpression )*
        loop54: do {
          int alt54 = 2;
          int LA54_0 = input.LA(1);

          if ((LA54_0 == PLUS || LA54_0 == MINUS)) {
            alt54 = 1;
          }

          switch (alt54) {
            case 1:
              // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1179:4:
              // op= ( PLUS | MINUS ) expr2= multiplicativeExpression
            {
              op = input.LT(1);
              if (input.LA(1) == PLUS || input.LA(1) == MINUS) {
                input.consume();
                state.errorRecovery = false;
                state.failed = false;
              } else {
                if (state.backtracking > 0) {
                  state.failed = true;
                  return root;
                }
                MismatchedSetException mse = new MismatchedSetException(null, input);
                throw mse;
              }

              pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression2905);
              expr2 = multiplicativeExpression();

              state._fsp--;
              if (state.failed)
                return root;
              if (state.backtracking == 0) {
                root.append(op.getText()).append(expr2);
              }

            }
              break;

            default:
              break loop54;
          }
        } while (true);

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return root;
  }

  // $ANTLR end "additiveExpression"

  // $ANTLR start "multiplicativeExpression"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1183:1:
  // multiplicativeExpression returns [StringBuilder root = new StringBuilder()] : (expr1=
  // simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) sNE= simpleNumberExpression )* | e1=
  // numberFunction ) ;
  public final StringBuilder multiplicativeExpression() throws RecognitionException {
    StringBuilder root = new StringBuilder();

    Token op = null;
    StringBuilder expr1 = null;

    StringBuilder sNE = null;

    StringBuilder e1 = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1184:5:
      // ( (expr1= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) sNE=
      // simpleNumberExpression )* | e1= numberFunction ) )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1185:2:
      // (expr1= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) sNE= simpleNumberExpression
      // )* | e1= numberFunction )
      {
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1185:2:
        // (expr1= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) sNE=
        // simpleNumberExpression )* | e1= numberFunction )
        int alt56 = 2;
        switch (input.LA(1)) {
          case LPAREN:
          case MINUS:
          case DecimalLiteral:
          case FloatingPointLiteral: {
            alt56 = 1;
          }
            break;
          case Identifier: {
            int LA56_2 = input.LA(2);

            if ((LA56_2 == LPAREN)) {
              alt56 = 2;
            } else if ((LA56_2 == EOF || LA56_2 == SEMI || LA56_2 == COMMA || LA56_2 == RPAREN
                    || (LA56_2 >= LCURLY && LA56_2 <= RCURLY) || LA56_2 == STAR || LA56_2 == PLUS
                    || LA56_2 == RBRACK || LA56_2 == MINUS
                    || (LA56_2 >= SLASH && LA56_2 <= PERCENT)
                    || (LA56_2 >= EQUAL && LA56_2 <= NOTEQUAL) || (LA56_2 >= LESS && LA56_2 <= LESSEQUAL))) {
              alt56 = 1;
            } else {
              if (state.backtracking > 0) {
                state.failed = true;
                return root;
              }
              NoViableAltException nvae = new NoViableAltException("", 56, 2, input);

              throw nvae;
            }
          }
            break;
          case EXP:
          case LOGN:
          case SIN:
          case COS:
          case TAN: {
            alt56 = 2;
          }
            break;
          default:
            if (state.backtracking > 0) {
              state.failed = true;
              return root;
            }
            NoViableAltException nvae = new NoViableAltException("", 56, 0, input);

            throw nvae;
        }

        switch (alt56) {
          case 1:
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1185:3:
            // expr1= simpleNumberExpression (op= ( STAR | SLASH | PERCENT ) sNE=
            // simpleNumberExpression )*
          {
            pushFollow(FOLLOW_simpleNumberExpression_in_multiplicativeExpression2934);
            expr1 = simpleNumberExpression();

            state._fsp--;
            if (state.failed)
              return root;
            if (state.backtracking == 0) {
              root.append(expr1);
            }
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1186:2:
            // (op= ( STAR | SLASH | PERCENT ) sNE= simpleNumberExpression )*
            loop55: do {
              int alt55 = 2;
              int LA55_0 = input.LA(1);

              if ((LA55_0 == STAR || (LA55_0 >= SLASH && LA55_0 <= PERCENT))) {
                alt55 = 1;
              }

              switch (alt55) {
                case 1:
                  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1186:4:
                  // op= ( STAR | SLASH | PERCENT ) sNE= simpleNumberExpression
                {
                  op = input.LT(1);
                  if (input.LA(1) == STAR || (input.LA(1) >= SLASH && input.LA(1) <= PERCENT)) {
                    input.consume();
                    state.errorRecovery = false;
                    state.failed = false;
                  } else {
                    if (state.backtracking > 0) {
                      state.failed = true;
                      return root;
                    }
                    MismatchedSetException mse = new MismatchedSetException(null, input);
                    throw mse;
                  }

                  pushFollow(FOLLOW_simpleNumberExpression_in_multiplicativeExpression2961);
                  sNE = simpleNumberExpression();

                  state._fsp--;
                  if (state.failed)
                    return root;
                  if (state.backtracking == 0) {
                    root.append(op.getText()).append(sNE);
                  }

                }
                  break;

                default:
                  break loop55;
              }
            } while (true);

          }
            break;
          case 2:
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1187:6:
            // e1= numberFunction
          {
            pushFollow(FOLLOW_numberFunction_in_multiplicativeExpression2977);
            e1 = numberFunction();

            state._fsp--;
            if (state.failed)
              return root;
            if (state.backtracking == 0) {
              root.append(e1);
            }

          }
            break;

        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return root;
  }

  // $ANTLR end "multiplicativeExpression"

  // $ANTLR start "numberExpressionInPar"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1191:1:
  // numberExpressionInPar returns [StringBuilder expr = new StringBuilder()] : LPAREN numE=
  // numberExpression RPAREN ;
  public final StringBuilder numberExpressionInPar() throws RecognitionException {
    StringBuilder expr = new StringBuilder();

    StringBuilder numE = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1192:2:
      // ( LPAREN numE= numberExpression RPAREN )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1193:2:
      // LPAREN numE= numberExpression RPAREN
      {
        match(input, LPAREN, FOLLOW_LPAREN_in_numberExpressionInPar2997);
        if (state.failed)
          return expr;
        pushFollow(FOLLOW_numberExpression_in_numberExpressionInPar3003);
        numE = numberExpression();

        state._fsp--;
        if (state.failed)
          return expr;
        match(input, RPAREN, FOLLOW_RPAREN_in_numberExpressionInPar3005);
        if (state.failed)
          return expr;
        if (state.backtracking == 0) {
          expr.append("(").append(numE).append(")");
        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return expr;
  }

  // $ANTLR end "numberExpressionInPar"

  // $ANTLR start "simpleNumberExpression"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1197:1:
  // simpleNumberExpression returns [StringBuilder expr = new StringBuilder()] : ( (m= MINUS )?
  // decLit= DecimalLiteral | (m= MINUS )? fpLit= FloatingPointLiteral | (m= MINUS )? numVarRef=
  // numberVariable | numExprPar= numberExpressionInPar );
  public final StringBuilder simpleNumberExpression() throws RecognitionException {
    StringBuilder expr = new StringBuilder();

    Token m = null;
    Token decLit = null;
    Token fpLit = null;
    StringBuilder numVarRef = null;

    StringBuilder numExprPar = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1198:2:
      // ( (m= MINUS )? decLit= DecimalLiteral | (m= MINUS )? fpLit= FloatingPointLiteral | (m=
      // MINUS )? numVarRef= numberVariable | numExprPar= numberExpressionInPar )
      int alt60 = 4;
      switch (input.LA(1)) {
        case MINUS: {
          switch (input.LA(2)) {
            case Identifier: {
              alt60 = 3;
            }
              break;
            case FloatingPointLiteral: {
              alt60 = 2;
            }
              break;
            case DecimalLiteral: {
              alt60 = 1;
            }
              break;
            default:
              if (state.backtracking > 0) {
                state.failed = true;
                return expr;
              }
              NoViableAltException nvae = new NoViableAltException("", 60, 1, input);

              throw nvae;
          }

        }
          break;
        case DecimalLiteral: {
          alt60 = 1;
        }
          break;
        case FloatingPointLiteral: {
          alt60 = 2;
        }
          break;
        case Identifier: {
          alt60 = 3;
        }
          break;
        case LPAREN: {
          alt60 = 4;
        }
          break;
        default:
          if (state.backtracking > 0) {
            state.failed = true;
            return expr;
          }
          NoViableAltException nvae = new NoViableAltException("", 60, 0, input);

          throw nvae;
      }

      switch (alt60) {
        case 1:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1199:4:
          // (m= MINUS )? decLit= DecimalLiteral
        {
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1199:4:
          // (m= MINUS )?
          int alt57 = 2;
          int LA57_0 = input.LA(1);

          if ((LA57_0 == MINUS)) {
            alt57 = 1;
          }
          switch (alt57) {
            case 1:
              // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1199:5:
              // m= MINUS
            {
              m = (Token) match(input, MINUS, FOLLOW_MINUS_in_simpleNumberExpression3031);
              if (state.failed)
                return expr;

            }
              break;

          }

          decLit = (Token) match(input, DecimalLiteral,
                  FOLLOW_DecimalLiteral_in_simpleNumberExpression3039);
          if (state.failed)
            return expr;
          if (state.backtracking == 0) {

            if (m != null)
              expr.append(m.getText()).append(decLit.getText());
            else
              expr.append(decLit.getText());
          }

        }
          break;
        case 2:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1206:4:
          // (m= MINUS )? fpLit= FloatingPointLiteral
        {
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1206:6:
          // (m= MINUS )?
          int alt58 = 2;
          int LA58_0 = input.LA(1);

          if ((LA58_0 == MINUS)) {
            alt58 = 1;
          }
          switch (alt58) {
            case 1:
              // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1206:6:
              // m= MINUS
            {
              m = (Token) match(input, MINUS, FOLLOW_MINUS_in_simpleNumberExpression3057);
              if (state.failed)
                return expr;

            }
              break;

          }

          fpLit = (Token) match(input, FloatingPointLiteral,
                  FOLLOW_FloatingPointLiteral_in_simpleNumberExpression3064);
          if (state.failed)
            return expr;
          if (state.backtracking == 0) {

            if (m != null)
              expr.append(m.getText()).append(fpLit.getText());
            else
              expr.append(fpLit.getText());
          }

        }
          break;
        case 3:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1213:4:
          // (m= MINUS )? numVarRef= numberVariable
        {
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1213:6:
          // (m= MINUS )?
          int alt59 = 2;
          int LA59_0 = input.LA(1);

          if ((LA59_0 == MINUS)) {
            alt59 = 1;
          }
          switch (alt59) {
            case 1:
              // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1213:6:
              // m= MINUS
            {
              m = (Token) match(input, MINUS, FOLLOW_MINUS_in_simpleNumberExpression3081);
              if (state.failed)
                return expr;

            }
              break;

          }

          pushFollow(FOLLOW_numberVariable_in_simpleNumberExpression3088);
          numVarRef = numberVariable();

          state._fsp--;
          if (state.failed)
            return expr;
          if (state.backtracking == 0) {

            if (m != null)
              expr.append(m.getText()).append(numVarRef);
            else
              expr.append(numVarRef);
          }

        }
          break;
        case 4:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1220:4:
          // numExprPar= numberExpressionInPar
        {
          pushFollow(FOLLOW_numberExpressionInPar_in_simpleNumberExpression3106);
          numExprPar = numberExpressionInPar();

          state._fsp--;
          if (state.failed)
            return expr;
          if (state.backtracking == 0) {
            expr.append(numExprPar);
          }

        }
          break;

      }
    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return expr;
  }

  // $ANTLR end "simpleNumberExpression"

  // $ANTLR start "numberFunction"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1226:1:
  // numberFunction returns [StringBuilder expr = new StringBuilder()] : ( (op= ( EXP | LOGN | SIN |
  // COS | TAN ) numExprP= numberExpressionInPar ) | (e= externalNumberFunction )=>e=
  // externalNumberFunction );
  public final StringBuilder numberFunction() throws RecognitionException {
    StringBuilder expr = new StringBuilder();

    Token op = null;
    StringBuilder numExprP = null;

    StringBuilder e = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1227:2:
      // ( (op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar ) | (e=
      // externalNumberFunction )=>e= externalNumberFunction )
      int alt61 = 2;
      int LA61_0 = input.LA(1);

      if (((LA61_0 >= EXP && LA61_0 <= TAN))) {
        alt61 = 1;
      } else if ((LA61_0 == Identifier) && (synpred10_ConvertSyntax())) {
        alt61 = 2;
      } else {
        if (state.backtracking > 0) {
          state.failed = true;
          return expr;
        }
        NoViableAltException nvae = new NoViableAltException("", 61, 0, input);

        throw nvae;
      }
      switch (alt61) {
        case 1:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1228:2:
          // (op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar )
        {
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1228:2:
          // (op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar )
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1228:3:
          // op= ( EXP | LOGN | SIN | COS | TAN ) numExprP= numberExpressionInPar
          {
            op = input.LT(1);
            if ((input.LA(1) >= EXP && input.LA(1) <= TAN)) {
              input.consume();
              state.errorRecovery = false;
              state.failed = false;
            } else {
              if (state.backtracking > 0) {
                state.failed = true;
                return expr;
              }
              MismatchedSetException mse = new MismatchedSetException(null, input);
              throw mse;
            }

            pushFollow(FOLLOW_numberExpressionInPar_in_numberFunction3156);
            numExprP = numberExpressionInPar();

            state._fsp--;
            if (state.failed)
              return expr;

          }

          if (state.backtracking == 0) {
            expr.append(op.getText()).append(numExprP);
          }

        }
          break;
        case 2:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1232:4:
          // (e= externalNumberFunction )=>e= externalNumberFunction
        {
          pushFollow(FOLLOW_externalNumberFunction_in_numberFunction3182);
          e = externalNumberFunction();

          state._fsp--;
          if (state.failed)
            return expr;
          if (state.backtracking == 0) {
            expr.append(e);
          }

        }
          break;

      }
    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return expr;
  }

  // $ANTLR end "numberFunction"

  // $ANTLR start "externalNumberFunction"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1236:1:
  // externalNumberFunction returns [StringBuilder expr = new StringBuilder()] : id= Identifier
  // LPAREN args= varArgumentList RPAREN ;
  public final StringBuilder externalNumberFunction() throws RecognitionException {
    StringBuilder expr = new StringBuilder();

    Token id = null;
    StringBuilder args = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1237:2:
      // (id= Identifier LPAREN args= varArgumentList RPAREN )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1239:2:
      // id= Identifier LPAREN args= varArgumentList RPAREN
      {
        id = (Token) match(input, Identifier, FOLLOW_Identifier_in_externalNumberFunction3207);
        if (state.failed)
          return expr;
        match(input, LPAREN, FOLLOW_LPAREN_in_externalNumberFunction3209);
        if (state.failed)
          return expr;
        pushFollow(FOLLOW_varArgumentList_in_externalNumberFunction3216);
        args = varArgumentList();

        state._fsp--;
        if (state.failed)
          return expr;
        match(input, RPAREN, FOLLOW_RPAREN_in_externalNumberFunction3218);
        if (state.failed)
          return expr;
        if (state.backtracking == 0) {
          expr.append(id.getText()).append("(").append(args).append(")");
        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return expr;
  }

  // $ANTLR end "externalNumberFunction"

  // $ANTLR start "numberVariable"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1248:1:
  // numberVariable returns [StringBuilder expr = new StringBuilder()] : numVarRef= Identifier ;
  public final StringBuilder numberVariable() throws RecognitionException {
    StringBuilder expr = new StringBuilder();

    Token numVarRef = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1249:2:
      // (numVarRef= Identifier )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1252:3:
      // numVarRef= Identifier
      {
        numVarRef = (Token) match(input, Identifier, FOLLOW_Identifier_in_numberVariable3250);
        if (state.failed)
          return expr;
        if (state.backtracking == 0) {
          expr.append(numVarRef.getText());
        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return expr;
  }

  // $ANTLR end "numberVariable"

  // $ANTLR start "stringExpression"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1259:1:
  // stringExpression returns [StringBuilder expr = new StringBuilder()] : (e= stringFunction |
  // strExpr1= simpleStringExpression ( PLUS (nextstrExpr= simpleStringExpression | nextstrExpr=
  // numberExpressionInPar ) )* );
  public final StringBuilder stringExpression() throws RecognitionException {
    StringBuilder expr = new StringBuilder();

    StringBuilder e = null;

    StringBuilder strExpr1 = null;

    StringBuilder nextstrExpr = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1261:2:
      // (e= stringFunction | strExpr1= simpleStringExpression ( PLUS (nextstrExpr=
      // simpleStringExpression | nextstrExpr= numberExpressionInPar ) )* )
      int alt64 = 2;
      int LA64_0 = input.LA(1);

      if ((LA64_0 == Identifier)) {
        int LA64_1 = input.LA(2);

        if ((LA64_1 == LPAREN)) {
          alt64 = 1;
        } else if ((LA64_1 == EOF || LA64_1 == SEMI || (LA64_1 >= COMMA && LA64_1 <= ASSIGN_EQUAL)
                || LA64_1 == RPAREN || LA64_1 == RCURLY || LA64_1 == PLUS)) {
          alt64 = 2;
        } else {
          if (state.backtracking > 0) {
            state.failed = true;
            return expr;
          }
          NoViableAltException nvae = new NoViableAltException("", 64, 1, input);

          throw nvae;
        }
      } else if ((LA64_0 == StringLiteral)) {
        alt64 = 2;
      } else {
        if (state.backtracking > 0) {
          state.failed = true;
          return expr;
        }
        NoViableAltException nvae = new NoViableAltException("", 64, 0, input);

        throw nvae;
      }
      switch (alt64) {
        case 1:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1262:2:
          // e= stringFunction
        {
          pushFollow(FOLLOW_stringFunction_in_stringExpression3282);
          e = stringFunction();

          state._fsp--;
          if (state.failed)
            return expr;
          if (state.backtracking == 0) {
            expr.append(e);
          }

        }
          break;
        case 2:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1265:2:
          // strExpr1= simpleStringExpression ( PLUS (nextstrExpr= simpleStringExpression |
          // nextstrExpr= numberExpressionInPar ) )*
        {
          pushFollow(FOLLOW_simpleStringExpression_in_stringExpression3297);
          strExpr1 = simpleStringExpression();

          state._fsp--;
          if (state.failed)
            return expr;
          if (state.backtracking == 0) {
            expr.append(strExpr1);
          }
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1266:2:
          // ( PLUS (nextstrExpr= simpleStringExpression | nextstrExpr= numberExpressionInPar ) )*
          loop63: do {
            int alt63 = 2;
            int LA63_0 = input.LA(1);

            if ((LA63_0 == PLUS)) {
              alt63 = 1;
            }

            switch (alt63) {
              case 1:
                // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1266:3:
                // PLUS (nextstrExpr= simpleStringExpression | nextstrExpr= numberExpressionInPar )
              {
                match(input, PLUS, FOLLOW_PLUS_in_stringExpression3303);
                if (state.failed)
                  return expr;
                // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1266:8:
                // (nextstrExpr= simpleStringExpression | nextstrExpr= numberExpressionInPar )
                int alt62 = 2;
                int LA62_0 = input.LA(1);

                if ((LA62_0 == Identifier || LA62_0 == StringLiteral)) {
                  alt62 = 1;
                } else if ((LA62_0 == LPAREN)) {
                  alt62 = 2;
                } else {
                  if (state.backtracking > 0) {
                    state.failed = true;
                    return expr;
                  }
                  NoViableAltException nvae = new NoViableAltException("", 62, 0, input);

                  throw nvae;
                }
                switch (alt62) {
                  case 1:
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1266:9:
                    // nextstrExpr= simpleStringExpression
                  {
                    pushFollow(FOLLOW_simpleStringExpression_in_stringExpression3310);
                    nextstrExpr = simpleStringExpression();

                    state._fsp--;
                    if (state.failed)
                      return expr;

                  }
                    break;
                  case 2:
                    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1266:48:
                    // nextstrExpr= numberExpressionInPar
                  {
                    pushFollow(FOLLOW_numberExpressionInPar_in_stringExpression3318);
                    nextstrExpr = numberExpressionInPar();

                    state._fsp--;
                    if (state.failed)
                      return expr;

                  }
                    break;

                }

                if (state.backtracking == 0) {
                  expr.append(" + ").append(nextstrExpr);
                }

              }
                break;

              default:
                break loop63;
            }
          } while (true);

        }
          break;

      }
    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return expr;
  }

  // $ANTLR end "stringExpression"

  // $ANTLR start "stringFunction"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1273:1:
  // stringFunction returns [StringBuilder expr = new StringBuilder()] : (e= externalStringFunction
  // )=>e= externalStringFunction ;
  public final StringBuilder stringFunction() throws RecognitionException {
    StringBuilder expr = new StringBuilder();

    StringBuilder e = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1274:2:
      // ( (e= externalStringFunction )=>e= externalStringFunction )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1275:2:
      // (e= externalStringFunction )=>e= externalStringFunction
      {
        pushFollow(FOLLOW_externalStringFunction_in_stringFunction3360);
        e = externalStringFunction();

        state._fsp--;
        if (state.failed)
          return expr;
        if (state.backtracking == 0) {
          expr.append(e);
        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return expr;
  }

  // $ANTLR end "stringFunction"

  // $ANTLR start "externalStringFunction"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1279:1:
  // externalStringFunction returns [StringBuilder expr = new StringBuilder()] : id= Identifier
  // LPAREN args= varArgumentList RPAREN ;
  public final StringBuilder externalStringFunction() throws RecognitionException {
    StringBuilder expr = new StringBuilder();

    Token id = null;
    StringBuilder args = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1280:2:
      // (id= Identifier LPAREN args= varArgumentList RPAREN )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1282:3:
      // id= Identifier LPAREN args= varArgumentList RPAREN
      {
        id = (Token) match(input, Identifier, FOLLOW_Identifier_in_externalStringFunction3386);
        if (state.failed)
          return expr;
        match(input, LPAREN, FOLLOW_LPAREN_in_externalStringFunction3388);
        if (state.failed)
          return expr;
        pushFollow(FOLLOW_varArgumentList_in_externalStringFunction3395);
        args = varArgumentList();

        state._fsp--;
        if (state.failed)
          return expr;
        match(input, RPAREN, FOLLOW_RPAREN_in_externalStringFunction3397);
        if (state.failed)
          return expr;
        if (state.backtracking == 0) {
          expr.append(id.getText()).append("(").append(args).append(")");
        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return expr;
  }

  // $ANTLR end "externalStringFunction"

  // $ANTLR start "simpleStringExpression"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1292:1:
  // simpleStringExpression returns [StringBuilder expr = new StringBuilder()] : (lit= StringLiteral
  // | variableId= Identifier );
  public final StringBuilder simpleStringExpression() throws RecognitionException {
    StringBuilder expr = new StringBuilder();

    Token lit = null;
    Token variableId = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1293:2:
      // (lit= StringLiteral | variableId= Identifier )
      int alt65 = 2;
      int LA65_0 = input.LA(1);

      if ((LA65_0 == StringLiteral)) {
        alt65 = 1;
      } else if ((LA65_0 == Identifier)) {
        alt65 = 2;
      } else {
        if (state.backtracking > 0) {
          state.failed = true;
          return expr;
        }
        NoViableAltException nvae = new NoViableAltException("", 65, 0, input);

        throw nvae;
      }
      switch (alt65) {
        case 1:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1294:2:
          // lit= StringLiteral
        {
          lit = (Token) match(input, StringLiteral,
                  FOLLOW_StringLiteral_in_simpleStringExpression3427);
          if (state.failed)
            return expr;
          if (state.backtracking == 0) {
            expr.append(lit.getText());
          }

        }
          break;
        case 2:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1296:5:
          // variableId= Identifier
        {
          variableId = (Token) match(input, Identifier,
                  FOLLOW_Identifier_in_simpleStringExpression3446);
          if (state.failed)
            return expr;
          if (state.backtracking == 0) {
            expr.append(variableId.getText());
          }

        }
          break;

      }
    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return expr;
  }

  // $ANTLR end "simpleStringExpression"

  // $ANTLR start "booleanExpression"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1307:1:
  // booleanExpression returns [StringBuilder expr = new StringBuilder()] options {backtrack=true; }
  // : (bcE= composedBooleanExpression | sbE= simpleBooleanExpression | (variableId= Identifier ) );
  public final StringBuilder booleanExpression() throws RecognitionException {
    StringBuilder expr = new StringBuilder();

    Token variableId = null;
    StringBuilder bcE = null;

    StringBuilder sbE = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1311:2:
      // (bcE= composedBooleanExpression | sbE= simpleBooleanExpression | (variableId= Identifier )
      // )
      int alt66 = 3;
      switch (input.LA(1)) {
        case LPAREN:
        case MINUS:
        case DecimalLiteral:
        case FloatingPointLiteral:
        case EXP:
        case LOGN:
        case SIN:
        case COS:
        case TAN:
        case XOR: {
          alt66 = 1;
        }
          break;
        case Identifier: {
          int LA66_2 = input.LA(2);

          if ((LA66_2 == LPAREN || LA66_2 == STAR || LA66_2 == PLUS || LA66_2 == MINUS
                  || (LA66_2 >= SLASH && LA66_2 <= PERCENT)
                  || (LA66_2 >= EQUAL && LA66_2 <= NOTEQUAL) || (LA66_2 >= LESS && LA66_2 <= LESSEQUAL))) {
            alt66 = 1;
          } else if ((LA66_2 == EOF || LA66_2 == SEMI || LA66_2 == COMMA || LA66_2 == RPAREN || LA66_2 == RCURLY)) {
            alt66 = 3;
          } else {
            if (state.backtracking > 0) {
              state.failed = true;
              return expr;
            }
            NoViableAltException nvae = new NoViableAltException("", 66, 2, input);

            throw nvae;
          }
        }
          break;
        case TRUE: {
          int LA66_3 = input.LA(2);

          if ((LA66_3 == EOF || LA66_3 == SEMI || LA66_3 == COMMA || LA66_3 == RPAREN || LA66_3 == RCURLY)) {
            alt66 = 2;
          } else if (((LA66_3 >= EQUAL && LA66_3 <= NOTEQUAL))) {
            alt66 = 1;
          } else {
            if (state.backtracking > 0) {
              state.failed = true;
              return expr;
            }
            NoViableAltException nvae = new NoViableAltException("", 66, 3, input);

            throw nvae;
          }
        }
          break;
        case FALSE: {
          int LA66_4 = input.LA(2);

          if (((LA66_4 >= EQUAL && LA66_4 <= NOTEQUAL))) {
            alt66 = 1;
          } else if ((LA66_4 == EOF || LA66_4 == SEMI || LA66_4 == COMMA || LA66_4 == RPAREN || LA66_4 == RCURLY)) {
            alt66 = 2;
          } else {
            if (state.backtracking > 0) {
              state.failed = true;
              return expr;
            }
            NoViableAltException nvae = new NoViableAltException("", 66, 4, input);

            throw nvae;
          }
        }
          break;
        default:
          if (state.backtracking > 0) {
            state.failed = true;
            return expr;
          }
          NoViableAltException nvae = new NoViableAltException("", 66, 0, input);

          throw nvae;
      }

      switch (alt66) {
        case 1:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1312:2:
          // bcE= composedBooleanExpression
        {
          pushFollow(FOLLOW_composedBooleanExpression_in_booleanExpression3492);
          bcE = composedBooleanExpression();

          state._fsp--;
          if (state.failed)
            return expr;
          if (state.backtracking == 0) {
            expr.append(bcE);
          }

        }
          break;
        case 2:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1313:4:
          // sbE= simpleBooleanExpression
        {
          pushFollow(FOLLOW_simpleBooleanExpression_in_booleanExpression3503);
          sbE = simpleBooleanExpression();

          state._fsp--;
          if (state.failed)
            return expr;
          if (state.backtracking == 0) {
            expr.append(sbE);
          }

        }
          break;
        case 3:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1315:4:
          // (variableId= Identifier )
        {
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1315:4:
          // (variableId= Identifier )
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1315:5:
          // variableId= Identifier
          {
            variableId = (Token) match(input, Identifier,
                    FOLLOW_Identifier_in_booleanExpression3519);
            if (state.failed)
              return expr;
            if (state.backtracking == 0) {
              expr.append(variableId.getText());
            }

          }

        }
          break;

      }
    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return expr;
  }

  // $ANTLR end "booleanExpression"

  // $ANTLR start "composedBooleanExpression"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1323:1:
  // composedBooleanExpression returns [StringBuilder expr = new StringBuilder()] : (e1=
  // booleanFunction | bnE= booleanNumberExpression | e2= booleanCompare );
  public final StringBuilder composedBooleanExpression() throws RecognitionException {
    StringBuilder expr = new StringBuilder();

    StringBuilder e1 = null;

    StringBuilder bnE = null;

    StringBuilder e2 = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1325:2:
      // (e1= booleanFunction | bnE= booleanNumberExpression | e2= booleanCompare )
      int alt67 = 3;
      switch (input.LA(1)) {
        case XOR: {
          alt67 = 1;
        }
          break;
        case Identifier:
        case LPAREN:
        case MINUS:
        case DecimalLiteral:
        case FloatingPointLiteral:
        case EXP:
        case LOGN:
        case SIN:
        case COS:
        case TAN: {
          alt67 = 2;
        }
          break;
        case TRUE:
        case FALSE: {
          alt67 = 3;
        }
          break;
        default:
          if (state.backtracking > 0) {
            state.failed = true;
            return expr;
          }
          NoViableAltException nvae = new NoViableAltException("", 67, 0, input);

          throw nvae;
      }

      switch (alt67) {
        case 1:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1326:2:
          // e1= booleanFunction
        {
          pushFollow(FOLLOW_booleanFunction_in_composedBooleanExpression3559);
          e1 = booleanFunction();

          state._fsp--;
          if (state.failed)
            return expr;
          if (state.backtracking == 0) {
            expr.append(e1);
          }

        }
          break;
        case 2:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1327:4:
          // bnE= booleanNumberExpression
        {
          pushFollow(FOLLOW_booleanNumberExpression_in_composedBooleanExpression3570);
          bnE = booleanNumberExpression();

          state._fsp--;
          if (state.failed)
            return expr;
          if (state.backtracking == 0) {
            expr.append(bnE);
          }

        }
          break;
        case 3:
          // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1328:4:
          // e2= booleanCompare
        {
          pushFollow(FOLLOW_booleanCompare_in_composedBooleanExpression3581);
          e2 = booleanCompare();

          state._fsp--;
          if (state.failed)
            return expr;
          if (state.backtracking == 0) {
            expr.append(e2);
          }

        }
          break;

      }
    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return expr;
  }

  // $ANTLR end "composedBooleanExpression"

  // $ANTLR start "booleanFunction"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1332:1:
  // booleanFunction returns [StringBuilder expr = new StringBuilder()] : (op= XOR LPAREN e1=
  // booleanExpression COMMA e2= booleanExpression RPAREN ) ;
  public final StringBuilder booleanFunction() throws RecognitionException {
    StringBuilder expr = new StringBuilder();

    Token op = null;
    StringBuilder e1 = null;

    StringBuilder e2 = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1334:2:
      // ( (op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN ) )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1335:2:
      // (op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN )
      {
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1335:2:
        // (op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1335:3:
        // op= XOR LPAREN e1= booleanExpression COMMA e2= booleanExpression RPAREN
        {
          op = (Token) match(input, XOR, FOLLOW_XOR_in_booleanFunction3606);
          if (state.failed)
            return expr;
          match(input, LPAREN, FOLLOW_LPAREN_in_booleanFunction3608);
          if (state.failed)
            return expr;
          pushFollow(FOLLOW_booleanExpression_in_booleanFunction3614);
          e1 = booleanExpression();

          state._fsp--;
          if (state.failed)
            return expr;
          match(input, COMMA, FOLLOW_COMMA_in_booleanFunction3616);
          if (state.failed)
            return expr;
          pushFollow(FOLLOW_booleanExpression_in_booleanFunction3622);
          e2 = booleanExpression();

          state._fsp--;
          if (state.failed)
            return expr;
          match(input, RPAREN, FOLLOW_RPAREN_in_booleanFunction3624);
          if (state.failed)
            return expr;

        }

        if (state.backtracking == 0) {
          expr.append(op.getText()).append("(").append(e1).append(",").append(e2).append(")");
        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return expr;
  }

  // $ANTLR end "booleanFunction"

  // $ANTLR start "externalBooleanFunction"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1342:1:
  // externalBooleanFunction returns [StringBuilder expr = new StringBuilder()] : id= Identifier
  // LPAREN args= varArgumentList RPAREN ;
  public final StringBuilder externalBooleanFunction() throws RecognitionException {
    StringBuilder expr = new StringBuilder();

    Token id = null;
    StringBuilder args = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1343:2:
      // (id= Identifier LPAREN args= varArgumentList RPAREN )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1345:2:
      // id= Identifier LPAREN args= varArgumentList RPAREN
      {
        id = (Token) match(input, Identifier, FOLLOW_Identifier_in_externalBooleanFunction3656);
        if (state.failed)
          return expr;
        match(input, LPAREN, FOLLOW_LPAREN_in_externalBooleanFunction3658);
        if (state.failed)
          return expr;
        pushFollow(FOLLOW_varArgumentList_in_externalBooleanFunction3665);
        args = varArgumentList();

        state._fsp--;
        if (state.failed)
          return expr;
        match(input, RPAREN, FOLLOW_RPAREN_in_externalBooleanFunction3667);
        if (state.failed)
          return expr;
        if (state.backtracking == 0) {
          expr.append(id.getText()).append("(").append(args).append(")");
        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return expr;
  }

  // $ANTLR end "externalBooleanFunction"

  // $ANTLR start "booleanCompare"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1354:1:
  // booleanCompare returns [StringBuilder expr = new StringBuilder()] : (e1=
  // simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression ) ;
  public final StringBuilder booleanCompare() throws RecognitionException {
    StringBuilder expr = new StringBuilder();

    Token op = null;
    StringBuilder e1 = null;

    StringBuilder e2 = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1355:2:
      // ( (e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression ) )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1356:2:
      // (e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression )
      {
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1356:2:
        // (e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression )
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1356:3:
        // e1= simpleBooleanExpression op= ( EQUAL | NOTEQUAL ) e2= booleanExpression
        {
          pushFollow(FOLLOW_simpleBooleanExpression_in_booleanCompare3695);
          e1 = simpleBooleanExpression();

          state._fsp--;
          if (state.failed)
            return expr;
          op = input.LT(1);
          if ((input.LA(1) >= EQUAL && input.LA(1) <= NOTEQUAL)) {
            input.consume();
            state.errorRecovery = false;
            state.failed = false;
          } else {
            if (state.backtracking > 0) {
              state.failed = true;
              return expr;
            }
            MismatchedSetException mse = new MismatchedSetException(null, input);
            throw mse;
          }

          pushFollow(FOLLOW_booleanExpression_in_booleanCompare3713);
          e2 = booleanExpression();

          state._fsp--;
          if (state.failed)
            return expr;

        }

        if (state.backtracking == 0) {
          expr.append(e1).append(op.getText()).append(e2);
        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return expr;
  }

  // $ANTLR end "booleanCompare"

  // $ANTLR start "simpleBooleanExpression"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1362:1:
  // simpleBooleanExpression returns [StringBuilder expr = new StringBuilder()] : (value= TRUE |
  // value= FALSE ) ;
  public final StringBuilder simpleBooleanExpression() throws RecognitionException {
    StringBuilder expr = new StringBuilder();

    Token value = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1363:2:
      // ( (value= TRUE | value= FALSE ) )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1364:2:
      // (value= TRUE | value= FALSE )
      {
        // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1364:2:
        // (value= TRUE | value= FALSE )
        int alt68 = 2;
        int LA68_0 = input.LA(1);

        if ((LA68_0 == TRUE)) {
          alt68 = 1;
        } else if ((LA68_0 == FALSE)) {
          alt68 = 2;
        } else {
          if (state.backtracking > 0) {
            state.failed = true;
            return expr;
          }
          NoViableAltException nvae = new NoViableAltException("", 68, 0, input);

          throw nvae;
        }
        switch (alt68) {
          case 1:
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1364:3:
            // value= TRUE
          {
            value = (Token) match(input, TRUE, FOLLOW_TRUE_in_simpleBooleanExpression3742);
            if (state.failed)
              return expr;

          }
            break;
          case 2:
            // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1365:4:
            // value= FALSE
          {
            value = (Token) match(input, FALSE, FOLLOW_FALSE_in_simpleBooleanExpression3752);
            if (state.failed)
              return expr;

          }
            break;

        }

        if (state.backtracking == 0) {
          expr.append(value.getText());
        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return expr;
  }

  // $ANTLR end "simpleBooleanExpression"

  // $ANTLR start "booleanNumberExpression"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1372:1:
  // booleanNumberExpression returns [StringBuilder expr = new StringBuilder()] : e1=
  // numberExpression op= ( LESS | GREATER | GREATEREQUAL | LESSEQUAL | EQUAL | NOTEQUAL ) e2=
  // numberExpression ;
  public final StringBuilder booleanNumberExpression() throws RecognitionException {
    StringBuilder expr = new StringBuilder();

    Token op = null;
    StringBuilder e1 = null;

    StringBuilder e2 = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1373:2:
      // (e1= numberExpression op= ( LESS | GREATER | GREATEREQUAL | LESSEQUAL | EQUAL | NOTEQUAL )
      // e2= numberExpression )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1374:2:
      // e1= numberExpression op= ( LESS | GREATER | GREATEREQUAL | LESSEQUAL | EQUAL | NOTEQUAL )
      // e2= numberExpression
      {
        pushFollow(FOLLOW_numberExpression_in_booleanNumberExpression3782);
        e1 = numberExpression();

        state._fsp--;
        if (state.failed)
          return expr;
        op = input.LT(1);
        if ((input.LA(1) >= EQUAL && input.LA(1) <= NOTEQUAL)
                || (input.LA(1) >= LESS && input.LA(1) <= LESSEQUAL)) {
          input.consume();
          state.errorRecovery = false;
          state.failed = false;
        } else {
          if (state.backtracking > 0) {
            state.failed = true;
            return expr;
          }
          MismatchedSetException mse = new MismatchedSetException(null, input);
          throw mse;
        }

        pushFollow(FOLLOW_numberExpression_in_booleanNumberExpression3818);
        e2 = numberExpression();

        state._fsp--;
        if (state.failed)
          return expr;
        if (state.backtracking == 0) {
          expr.append(e1).append(op.getText()).append(e2);
        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return expr;
  }

  // $ANTLR end "booleanNumberExpression"

  // $ANTLR start "genericVariableReference"
  // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1381:1:
  // genericVariableReference returns [StringBuilder ref = new StringBuilder()] : id= Identifier ;
  public final StringBuilder genericVariableReference() throws RecognitionException {
    StringBuilder ref = new StringBuilder();

    Token id = null;

    try {
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1382:1:
      // (id= Identifier )
      // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1383:3:
      // id= Identifier
      {
        id = (Token) match(input, Identifier, FOLLOW_Identifier_in_genericVariableReference3840);
        if (state.failed)
          return ref;
        if (state.backtracking == 0) {
          ref.append(id.getText());
        }

      }

    }

    catch (RecognitionException exception1) {

      recover(input, exception1);
    } catch (Throwable exception2) {

    } finally {
    }
    return ref;
  }

  // $ANTLR end "genericVariableReference"

  // $ANTLR start synpred2_ConvertSyntax
  public final void synpred2_ConvertSyntax_fragment() throws RecognitionException {
    StringBuilder cp = null;

    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:431:71:
    // (cp= conditionPart )
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:431:72:
    // cp= conditionPart
    {
      pushFollow(FOLLOW_conditionPart_in_synpred2_ConvertSyntax1233);
      cp = conditionPart();

      state._fsp--;
      if (state.failed)
        return;

    }
  }

  // $ANTLR end synpred2_ConvertSyntax

  // $ANTLR start synpred4_ConvertSyntax
  public final void synpred4_ConvertSyntax_fragment() throws RecognitionException {
    StringBuilder c = null;

    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:622:4:
    // (c= externalCondition )
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:622:5:
    // c= externalCondition
    {
      pushFollow(FOLLOW_externalCondition_in_synpred4_ConvertSyntax1801);
      c = externalCondition();

      state._fsp--;
      if (state.failed)
        return;

    }
  }

  // $ANTLR end synpred4_ConvertSyntax

  // $ANTLR start synpred6_ConvertSyntax
  public final void synpred6_ConvertSyntax_fragment() throws RecognitionException {
    StringBuilder a = null;

    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:845:4:
    // (a= externalAction )
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:845:5:
    // a= externalAction
    {
      pushFollow(FOLLOW_externalAction_in_synpred6_ConvertSyntax2289);
      a = externalAction();

      state._fsp--;
      if (state.failed)
        return;

    }
  }

  // $ANTLR end synpred6_ConvertSyntax

  // $ANTLR start synpred7_ConvertSyntax
  public final void synpred7_ConvertSyntax_fragment() throws RecognitionException {
    StringBuilder a4 = null;

    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1098:3:
    // (a4= stringExpression )
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1098:3:
    // a4= stringExpression
    {
      pushFollow(FOLLOW_stringExpression_in_synpred7_ConvertSyntax2551);
      a4 = stringExpression();

      state._fsp--;
      if (state.failed)
        return;

    }
  }

  // $ANTLR end synpred7_ConvertSyntax

  // $ANTLR start synpred8_ConvertSyntax
  public final void synpred8_ConvertSyntax_fragment() throws RecognitionException {
    StringBuilder a2 = null;

    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1099:4:
    // (a2= booleanExpression )
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1099:4:
    // a2= booleanExpression
    {
      pushFollow(FOLLOW_booleanExpression_in_synpred8_ConvertSyntax2562);
      a2 = booleanExpression();

      state._fsp--;
      if (state.failed)
        return;

    }
  }

  // $ANTLR end synpred8_ConvertSyntax

  // $ANTLR start synpred9_ConvertSyntax
  public final void synpred9_ConvertSyntax_fragment() throws RecognitionException {
    StringBuilder a3 = null;

    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1100:4:
    // (a3= numberExpression )
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1100:4:
    // a3= numberExpression
    {
      pushFollow(FOLLOW_numberExpression_in_synpred9_ConvertSyntax2573);
      a3 = numberExpression();

      state._fsp--;
      if (state.failed)
        return;

    }
  }

  // $ANTLR end synpred9_ConvertSyntax

  // $ANTLR start synpred10_ConvertSyntax
  public final void synpred10_ConvertSyntax_fragment() throws RecognitionException {
    StringBuilder e = null;

    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1232:4:
    // (e= externalNumberFunction )
    // D:\\work\\workspace-tm\\org.apache.uima.tm.textmarker.ui.utils\\src\\de\\uniwue\\tm\\textmarker\\ui\\convert\\ConvertSyntax.g:1232:5:
    // e= externalNumberFunction
    {
      pushFollow(FOLLOW_externalNumberFunction_in_synpred10_ConvertSyntax3174);
      e = externalNumberFunction();

      state._fsp--;
      if (state.failed)
        return;

    }
  }

  // $ANTLR end synpred10_ConvertSyntax

  // Delegated rules

  public final boolean synpred7_ConvertSyntax() {
    state.backtracking++;
    int start = input.mark();
    try {
      synpred7_ConvertSyntax_fragment(); // can never throw exception
    } catch (RecognitionException re) {
      System.err.println("impossible: " + re);
    }
    boolean success = !state.failed;
    input.rewind(start);
    state.backtracking--;
    state.failed = false;
    return success;
  }

  public final boolean synpred8_ConvertSyntax() {
    state.backtracking++;
    int start = input.mark();
    try {
      synpred8_ConvertSyntax_fragment(); // can never throw exception
    } catch (RecognitionException re) {
      System.err.println("impossible: " + re);
    }
    boolean success = !state.failed;
    input.rewind(start);
    state.backtracking--;
    state.failed = false;
    return success;
  }

  public final boolean synpred4_ConvertSyntax() {
    state.backtracking++;
    int start = input.mark();
    try {
      synpred4_ConvertSyntax_fragment(); // can never throw exception
    } catch (RecognitionException re) {
      System.err.println("impossible: " + re);
    }
    boolean success = !state.failed;
    input.rewind(start);
    state.backtracking--;
    state.failed = false;
    return success;
  }

  public final boolean synpred2_ConvertSyntax() {
    state.backtracking++;
    int start = input.mark();
    try {
      synpred2_ConvertSyntax_fragment(); // can never throw exception
    } catch (RecognitionException re) {
      System.err.println("impossible: " + re);
    }
    boolean success = !state.failed;
    input.rewind(start);
    state.backtracking--;
    state.failed = false;
    return success;
  }

  public final boolean synpred10_ConvertSyntax() {
    state.backtracking++;
    int start = input.mark();
    try {
      synpred10_ConvertSyntax_fragment(); // can never throw exception
    } catch (RecognitionException re) {
      System.err.println("impossible: " + re);
    }
    boolean success = !state.failed;
    input.rewind(start);
    state.backtracking--;
    state.failed = false;
    return success;
  }

  public final boolean synpred9_ConvertSyntax() {
    state.backtracking++;
    int start = input.mark();
    try {
      synpred9_ConvertSyntax_fragment(); // can never throw exception
    } catch (RecognitionException re) {
      System.err.println("impossible: " + re);
    }
    boolean success = !state.failed;
    input.rewind(start);
    state.backtracking--;
    state.failed = false;
    return success;
  }

  public final boolean synpred6_ConvertSyntax() {
    state.backtracking++;
    int start = input.mark();
    try {
      synpred6_ConvertSyntax_fragment(); // can never throw exception
    } catch (RecognitionException re) {
      System.err.println("impossible: " + re);
    }
    boolean success = !state.failed;
    input.rewind(start);
    state.backtracking--;
    state.failed = false;
    return success;
  }

  protected DFA23 dfa23 = new DFA23(this);

  protected DFA39 dfa39 = new DFA39(this);

  protected DFA49 dfa49 = new DFA49(this);

  static final String DFA23_eotS = "\25\uffff";

  static final String DFA23_eofS = "\25\uffff";

  static final String DFA23_minS = "\1\31\1\14\7\uffff\1\10\1\uffff\1\14\1\uffff\1\10\7\uffff";

  static final String DFA23_maxS = "\1\34\1\56\7\uffff\1\56\1\uffff\1\56\1\uffff\1\56\7\uffff";

  static final String DFA23_acceptS = "\2\uffff\1\2\6\1\1\uffff\1\1\1\uffff\1\1\1\uffff\7\1";

  static final String DFA23_specialS = "\1\uffff\1\3\7\uffff\1\2\1\uffff\1\0\1\uffff\1\1\7\uffff}>";

  static final String[] DFA23_transitionS = {
      "\1\2\2\uffff\1\1",
      "\1\2\1\11\2\uffff\11\2\2\uffff\1\2\1\uffff\2\2\5\uffff\1\3"
              + "\1\4\1\5\1\6\1\7\1\10\4\uffff\1\2",
      "",
      "",
      "",
      "",
      "",
      "",
      "",
      "\1\13\4\uffff\1\2\1\12\12\uffff\1\2\2\uffff\1\2\1\14\5\2\13" + "\uffff\1\2",
      "",
      "\1\2\1\15\2\uffff\11\2\2\uffff\1\2\1\uffff\2\2\5\uffff\1\16"
              + "\1\17\1\20\1\21\1\22\1\23\4\uffff\1\2", "",
      "\1\13\4\uffff\1\2\1\24\12\uffff\1\2\2\uffff\1\2\1\14\5\2\13" + "\uffff\1\2", "", "", "", "",
      "", "", "" };

  static final short[] DFA23_eot = DFA.unpackEncodedString(DFA23_eotS);

  static final short[] DFA23_eof = DFA.unpackEncodedString(DFA23_eofS);

  static final char[] DFA23_min = DFA.unpackEncodedStringToUnsignedChars(DFA23_minS);

  static final char[] DFA23_max = DFA.unpackEncodedStringToUnsignedChars(DFA23_maxS);

  static final short[] DFA23_accept = DFA.unpackEncodedString(DFA23_acceptS);

  static final short[] DFA23_special = DFA.unpackEncodedString(DFA23_specialS);

  static final short[][] DFA23_transition;

  static {
    int numStates = DFA23_transitionS.length;
    DFA23_transition = new short[numStates][];
    for (int i = 0; i < numStates; i++) {
      DFA23_transition[i] = DFA.unpackEncodedString(DFA23_transitionS[i]);
    }
  }

  class DFA23 extends DFA {

    public DFA23(BaseRecognizer recognizer) {
      this.recognizer = recognizer;
      this.decisionNumber = 23;
      this.eot = DFA23_eot;
      this.eof = DFA23_eof;
      this.min = DFA23_min;
      this.max = DFA23_max;
      this.accept = DFA23_accept;
      this.special = DFA23_special;
      this.transition = DFA23_transition;
    }

    public String getDescription() {
      return "431:70: ( (cp= conditionPart )=>cp= conditionPart )?";
    }

    public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
      TokenStream input = (TokenStream) _input;
      int _s = s;
      switch (s) {
        case 0:
          int LA23_11 = input.LA(1);

          int index23_11 = input.index();
          input.rewind();
          s = -1;
          if ((LA23_11 == IntString || (LA23_11 >= DoubleString && LA23_11 <= TABLE)
                  || LA23_11 == BlockString || (LA23_11 >= RCURLY && LA23_11 <= StringLiteral) || LA23_11 == BasicAnnotationType)) {
            s = 2;
          }

          else if ((LA23_11 == Identifier)) {
            s = 13;
          }

          else if ((LA23_11 == AND) && (synpred2_ConvertSyntax())) {
            s = 14;
          }

          else if ((LA23_11 == INLIST) && (synpred2_ConvertSyntax())) {
            s = 15;
          }

          else if ((LA23_11 == MOFN) && (synpred2_ConvertSyntax())) {
            s = 16;
          }

          else if ((LA23_11 == MINUS) && (synpred2_ConvertSyntax())) {
            s = 17;
          }

          else if ((LA23_11 == OR) && (synpred2_ConvertSyntax())) {
            s = 18;
          }

          else if ((LA23_11 == IF) && (synpred2_ConvertSyntax())) {
            s = 19;
          }

          input.seek(index23_11);
          if (s >= 0)
            return s;
          break;
        case 1:
          int LA23_13 = input.LA(1);

          int index23_13 = input.index();
          input.rewind();
          s = -1;
          if ((LA23_13 == COMMA) && (synpred2_ConvertSyntax())) {
            s = 20;
          }

          else if ((LA23_13 == RCURLY) && (synpred2_ConvertSyntax())) {
            s = 12;
          }

          else if ((LA23_13 == SEMI)) {
            s = 11;
          }

          else if ((LA23_13 == Identifier || LA23_13 == LPAREN || LA23_13 == LCURLY
                  || (LA23_13 >= StringLiteral && LA23_13 <= LBRACK) || LA23_13 == BasicAnnotationType)) {
            s = 2;
          }

          input.seek(index23_13);
          if (s >= 0)
            return s;
          break;
        case 2:
          int LA23_9 = input.LA(1);

          int index23_9 = input.index();
          input.rewind();
          s = -1;
          if ((LA23_9 == COMMA) && (synpred2_ConvertSyntax())) {
            s = 10;
          }

          else if ((LA23_9 == SEMI)) {
            s = 11;
          }

          else if ((LA23_9 == RCURLY) && (synpred2_ConvertSyntax())) {
            s = 12;
          }

          else if ((LA23_9 == Identifier || LA23_9 == LPAREN || LA23_9 == LCURLY
                  || (LA23_9 >= StringLiteral && LA23_9 <= LBRACK) || LA23_9 == BasicAnnotationType)) {
            s = 2;
          }

          input.seek(index23_9);
          if (s >= 0)
            return s;
          break;
        case 3:
          int LA23_1 = input.LA(1);

          int index23_1 = input.index();
          input.rewind();
          s = -1;
          if ((LA23_1 == AND) && (synpred2_ConvertSyntax())) {
            s = 3;
          }

          else if ((LA23_1 == INLIST) && (synpred2_ConvertSyntax())) {
            s = 4;
          }

          else if ((LA23_1 == MOFN) && (synpred2_ConvertSyntax())) {
            s = 5;
          }

          else if ((LA23_1 == MINUS) && (synpred2_ConvertSyntax())) {
            s = 6;
          }

          else if ((LA23_1 == OR) && (synpred2_ConvertSyntax())) {
            s = 7;
          }

          else if ((LA23_1 == IF) && (synpred2_ConvertSyntax())) {
            s = 8;
          }

          else if ((LA23_1 == Identifier)) {
            s = 9;
          }

          else if ((LA23_1 == IntString || (LA23_1 >= DoubleString && LA23_1 <= TABLE)
                  || LA23_1 == BlockString || (LA23_1 >= RCURLY && LA23_1 <= StringLiteral) || LA23_1 == BasicAnnotationType)) {
            s = 2;
          }

          input.seek(index23_1);
          if (s >= 0)
            return s;
          break;
      }
      if (state.backtracking > 0) {
        state.failed = true;
        return -1;
      }
      NoViableAltException nvae = new NoViableAltException(getDescription(), 23, _s, input);
      error(nvae);
      throw nvae;
    }
  }

  static final String DFA39_eotS = "\12\uffff";

  static final String DFA39_eofS = "\12\uffff";

  static final String DFA39_minS = "\1\15\6\uffff\1\10\2\uffff";

  static final String DFA39_maxS = "\1\51\6\uffff\1\35\2\uffff";

  static final String DFA39_acceptS = "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\uffff\1\7\1\10";

  static final String DFA39_specialS = "\7\uffff\1\0\2\uffff}>";

  static final String[] DFA39_transitionS = { "\1\7\26\uffff\1\1\1\2\1\3\1\4\1\5\1\6", "", "", "",
      "", "", "", "\1\11\5\uffff\1\10\16\uffff\1\11", "", "" };

  static final short[] DFA39_eot = DFA.unpackEncodedString(DFA39_eotS);

  static final short[] DFA39_eof = DFA.unpackEncodedString(DFA39_eofS);

  static final char[] DFA39_min = DFA.unpackEncodedStringToUnsignedChars(DFA39_minS);

  static final char[] DFA39_max = DFA.unpackEncodedStringToUnsignedChars(DFA39_maxS);

  static final short[] DFA39_accept = DFA.unpackEncodedString(DFA39_acceptS);

  static final short[] DFA39_special = DFA.unpackEncodedString(DFA39_specialS);

  static final short[][] DFA39_transition;

  static {
    int numStates = DFA39_transitionS.length;
    DFA39_transition = new short[numStates][];
    for (int i = 0; i < numStates; i++) {
      DFA39_transition[i] = DFA.unpackEncodedString(DFA39_transitionS[i]);
    }
  }

  class DFA39 extends DFA {

    public DFA39(BaseRecognizer recognizer) {
      this.recognizer = recognizer;
      this.decisionNumber = 39;
      this.eot = DFA39_eot;
      this.eof = DFA39_eof;
      this.min = DFA39_min;
      this.max = DFA39_max;
      this.accept = DFA39_accept;
      this.special = DFA39_special;
      this.transition = DFA39_transition;
    }

    public String getDescription() {
      return "596:2: (c= conditionAnd | c= conditionInList | c= conditionMofN | c= conditionNot | c= conditionOr | c= conditionIf | (c= externalCondition )=>c= externalCondition | c= variableCondition )";
    }

    public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
      TokenStream input = (TokenStream) _input;
      int _s = s;
      switch (s) {
        case 0:
          int LA39_7 = input.LA(1);

          int index39_7 = input.index();
          input.rewind();
          s = -1;
          if ((LA39_7 == COMMA) && (synpred4_ConvertSyntax())) {
            s = 8;
          }

          else if ((LA39_7 == SEMI || LA39_7 == RCURLY)) {
            s = 9;
          }

          input.seek(index39_7);
          if (s >= 0)
            return s;
          break;
      }
      if (state.backtracking > 0) {
        state.failed = true;
        return -1;
      }
      NoViableAltException nvae = new NoViableAltException(getDescription(), 39, _s, input);
      error(nvae);
      throw nvae;
    }
  }

  static final String DFA49_eotS = "\15\uffff";

  static final String DFA49_eofS = "\15\uffff";

  static final String DFA49_minS = "\1\15\1\0\2\uffff\5\0\4\uffff";

  static final String DFA49_maxS = "\1\74\1\0\2\uffff\5\0\4\uffff";

  static final String DFA49_acceptS = "\2\uffff\1\1\1\2\7\uffff\1\4\1\3";

  static final String DFA49_specialS = "\1\uffff\1\0\2\uffff\1\1\1\2\1\3\1\4\1\5\4\uffff}>";

  static final String[] DFA49_transitionS = {
      "\1\1\13\uffff\1\7\4\uffff\1\2\10\uffff\1\4\6\uffff\1\13\2\uffff"
              + "\1\5\1\6\5\10\1\3\2\uffff\2\3", "\1\uffff", "", "", "\1\uffff", "\1\uffff",
      "\1\uffff", "\1\uffff", "\1\uffff", "", "", "", "" };

  static final short[] DFA49_eot = DFA.unpackEncodedString(DFA49_eotS);

  static final short[] DFA49_eof = DFA.unpackEncodedString(DFA49_eofS);

  static final char[] DFA49_min = DFA.unpackEncodedStringToUnsignedChars(DFA49_minS);

  static final char[] DFA49_max = DFA.unpackEncodedStringToUnsignedChars(DFA49_maxS);

  static final short[] DFA49_accept = DFA.unpackEncodedString(DFA49_acceptS);

  static final short[] DFA49_special = DFA.unpackEncodedString(DFA49_specialS);

  static final short[][] DFA49_transition;

  static {
    int numStates = DFA49_transitionS.length;
    DFA49_transition = new short[numStates][];
    for (int i = 0; i < numStates; i++) {
      DFA49_transition[i] = DFA.unpackEncodedString(DFA49_transitionS[i]);
    }
  }

  class DFA49 extends DFA {

    public DFA49(BaseRecognizer recognizer) {
      this.recognizer = recognizer;
      this.decisionNumber = 49;
      this.eot = DFA49_eot;
      this.eof = DFA49_eof;
      this.min = DFA49_min;
      this.max = DFA49_max;
      this.accept = DFA49_accept;
      this.special = DFA49_special;
      this.transition = DFA49_transition;
    }

    public String getDescription() {
      return "1093:1: argument returns [StringBuilder arg = new StringBuilder()] options {backtrack=true; } : (a4= stringExpression | a2= booleanExpression | a3= numberExpression | a1= typeExpression );";
    }

    public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
      TokenStream input = (TokenStream) _input;
      int _s = s;
      switch (s) {
        case 0:
          int LA49_1 = input.LA(1);

          int index49_1 = input.index();
          input.rewind();
          s = -1;
          if ((synpred7_ConvertSyntax())) {
            s = 2;
          }

          else if ((synpred8_ConvertSyntax())) {
            s = 3;
          }

          else if ((synpred9_ConvertSyntax())) {
            s = 12;
          }

          else if ((true)) {
            s = 11;
          }

          input.seek(index49_1);
          if (s >= 0)
            return s;
          break;
        case 1:
          int LA49_4 = input.LA(1);

          int index49_4 = input.index();
          input.rewind();
          s = -1;
          if ((synpred8_ConvertSyntax())) {
            s = 3;
          }

          else if ((synpred9_ConvertSyntax())) {
            s = 12;
          }

          input.seek(index49_4);
          if (s >= 0)
            return s;
          break;
        case 2:
          int LA49_5 = input.LA(1);

          int index49_5 = input.index();
          input.rewind();
          s = -1;
          if ((synpred8_ConvertSyntax())) {
            s = 3;
          }

          else if ((synpred9_ConvertSyntax())) {
            s = 12;
          }

          input.seek(index49_5);
          if (s >= 0)
            return s;
          break;
        case 3:
          int LA49_6 = input.LA(1);

          int index49_6 = input.index();
          input.rewind();
          s = -1;
          if ((synpred8_ConvertSyntax())) {
            s = 3;
          }

          else if ((synpred9_ConvertSyntax())) {
            s = 12;
          }

          input.seek(index49_6);
          if (s >= 0)
            return s;
          break;
        case 4:
          int LA49_7 = input.LA(1);

          int index49_7 = input.index();
          input.rewind();
          s = -1;
          if ((synpred8_ConvertSyntax())) {
            s = 3;
          }

          else if ((synpred9_ConvertSyntax())) {
            s = 12;
          }

          input.seek(index49_7);
          if (s >= 0)
            return s;
          break;
        case 5:
          int LA49_8 = input.LA(1);

          int index49_8 = input.index();
          input.rewind();
          s = -1;
          if ((synpred8_ConvertSyntax())) {
            s = 3;
          }

          else if ((synpred9_ConvertSyntax())) {
            s = 12;
          }

          input.seek(index49_8);
          if (s >= 0)
            return s;
          break;
      }
      if (state.backtracking > 0) {
        state.failed = true;
        return -1;
      }
      NoViableAltException nvae = new NoViableAltException(getDescription(), 49, _s, input);
      error(nvae);
      throw nvae;
    }
  }

  public static final BitSet FOLLOW_packageDeclaration_in_file_input91 = new BitSet(
          new long[] { 0x0000400049FF3E00L });

  public static final BitSet FOLLOW_globalStatements_in_file_input106 = new BitSet(
          new long[] { 0x0000400049FF3000L });

  public static final BitSet FOLLOW_statements_in_file_input115 = new BitSet(
          new long[] { 0x0000000000000000L });

  public static final BitSet FOLLOW_EOF_in_file_input127 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_PackageString_in_packageDeclaration148 = new BitSet(
          new long[] { 0x0000000000002000L });

  public static final BitSet FOLLOW_dottedId_in_packageDeclaration154 = new BitSet(
          new long[] { 0x0000000000000100L });

  public static final BitSet FOLLOW_SEMI_in_packageDeclaration156 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_statement_in_statements180 = new BitSet(
          new long[] { 0x0000400049FF3002L });

  public static final BitSet FOLLOW_globalStatement_in_globalStatements206 = new BitSet(
          new long[] { 0x0000000000000E02L });

  public static final BitSet FOLLOW_importStatement_in_globalStatement232 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_declaration_in_statement258 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_variableDeclaration_in_statement269 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_blockDeclaration_in_statement280 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_simpleStatement_in_statement291 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_TypeSystemString_in_importStatement317 = new BitSet(
          new long[] { 0x0000000000002000L });

  public static final BitSet FOLLOW_dottedId_in_importStatement323 = new BitSet(
          new long[] { 0x0000000000000100L });

  public static final BitSet FOLLOW_SEMI_in_importStatement325 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_ScriptString_in_importStatement338 = new BitSet(
          new long[] { 0x0000000000002000L });

  public static final BitSet FOLLOW_dottedId_in_importStatement344 = new BitSet(
          new long[] { 0x0000000000000100L });

  public static final BitSet FOLLOW_SEMI_in_importStatement346 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_EngineString_in_importStatement359 = new BitSet(
          new long[] { 0x0000000000002000L });

  public static final BitSet FOLLOW_dottedId_in_importStatement365 = new BitSet(
          new long[] { 0x0000000000000100L });

  public static final BitSet FOLLOW_SEMI_in_importStatement367 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_IntString_in_variableDeclaration394 = new BitSet(
          new long[] { 0x0000000000002000L });

  public static final BitSet FOLLOW_Identifier_in_variableDeclaration400 = new BitSet(
          new long[] { 0x000000000000C100L });

  public static final BitSet FOLLOW_COMMA_in_variableDeclaration407 = new BitSet(
          new long[] { 0x0000000000002000L });

  public static final BitSet FOLLOW_Identifier_in_variableDeclaration412 = new BitSet(
          new long[] { 0x000000000000C100L });

  public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration422 = new BitSet(
          new long[] { 0x00FE008002002000L });

  public static final BitSet FOLLOW_numberExpression_in_variableDeclaration428 = new BitSet(
          new long[] { 0x0000000000000100L });

  public static final BitSet FOLLOW_SEMI_in_variableDeclaration436 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_DoubleString_in_variableDeclaration450 = new BitSet(
          new long[] { 0x0000000000002000L });

  public static final BitSet FOLLOW_Identifier_in_variableDeclaration456 = new BitSet(
          new long[] { 0x000000000000C100L });

  public static final BitSet FOLLOW_COMMA_in_variableDeclaration464 = new BitSet(
          new long[] { 0x0000000000002000L });

  public static final BitSet FOLLOW_Identifier_in_variableDeclaration470 = new BitSet(
          new long[] { 0x000000000000C100L });

  public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration481 = new BitSet(
          new long[] { 0x00FE008002002000L });

  public static final BitSet FOLLOW_numberExpression_in_variableDeclaration487 = new BitSet(
          new long[] { 0x0000000000000100L });

  public static final BitSet FOLLOW_SEMI_in_variableDeclaration494 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_StringString_in_variableDeclaration509 = new BitSet(
          new long[] { 0x0000000000002000L });

  public static final BitSet FOLLOW_Identifier_in_variableDeclaration515 = new BitSet(
          new long[] { 0x000000000000C100L });

  public static final BitSet FOLLOW_COMMA_in_variableDeclaration523 = new BitSet(
          new long[] { 0x0000000000002000L });

  public static final BitSet FOLLOW_Identifier_in_variableDeclaration529 = new BitSet(
          new long[] { 0x000000000000C100L });

  public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration540 = new BitSet(
          new long[] { 0x0000000040002000L });

  public static final BitSet FOLLOW_stringExpression_in_variableDeclaration546 = new BitSet(
          new long[] { 0x0000000000000100L });

  public static final BitSet FOLLOW_SEMI_in_variableDeclaration553 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_BooleanString_in_variableDeclaration568 = new BitSet(
          new long[] { 0x0000000000002000L });

  public static final BitSet FOLLOW_Identifier_in_variableDeclaration574 = new BitSet(
          new long[] { 0x000000000000C100L });

  public static final BitSet FOLLOW_COMMA_in_variableDeclaration582 = new BitSet(
          new long[] { 0x0000000000002000L });

  public static final BitSet FOLLOW_Identifier_in_variableDeclaration588 = new BitSet(
          new long[] { 0x000000000000C100L });

  public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration599 = new BitSet(
          new long[] { 0x19FE008002002000L });

  public static final BitSet FOLLOW_booleanExpression_in_variableDeclaration605 = new BitSet(
          new long[] { 0x0000000000000100L });

  public static final BitSet FOLLOW_SEMI_in_variableDeclaration612 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_TypeString_in_variableDeclaration627 = new BitSet(
          new long[] { 0x0000000000002000L });

  public static final BitSet FOLLOW_Identifier_in_variableDeclaration633 = new BitSet(
          new long[] { 0x000000000000C100L });

  public static final BitSet FOLLOW_COMMA_in_variableDeclaration641 = new BitSet(
          new long[] { 0x0000000000002000L });

  public static final BitSet FOLLOW_Identifier_in_variableDeclaration647 = new BitSet(
          new long[] { 0x000000000000C100L });

  public static final BitSet FOLLOW_ASSIGN_EQUAL_in_variableDeclaration658 = new BitSet(
          new long[] { 0x0000400000002000L });

  public static final BitSet FOLLOW_annotationType_in_variableDeclaration664 = new BitSet(
          new long[] { 0x0000000000000100L });

  public static final BitSet FOLLOW_SEMI_in_variableDeclaration671 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_conditionDeclaration_in_variableDeclaration686 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_actionDeclaration_in_variableDeclaration698 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_ConditionString_in_conditionDeclaration721 = new BitSet(
          new long[] { 0x0000000000002000L });

  public static final BitSet FOLLOW_Identifier_in_conditionDeclaration727 = new BitSet(
          new long[] { 0x0000000000008000L });

  public static final BitSet FOLLOW_ASSIGN_EQUAL_in_conditionDeclaration730 = new BitSet(
          new long[] { 0x0000000010000000L });

  public static final BitSet FOLLOW_conditionPart_in_conditionDeclaration738 = new BitSet(
          new long[] { 0x0000000000000100L });

  public static final BitSet FOLLOW_SEMI_in_conditionDeclaration740 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_ACTION_in_actionDeclaration768 = new BitSet(
          new long[] { 0x0000000000002000L });

  public static final BitSet FOLLOW_Identifier_in_actionDeclaration774 = new BitSet(
          new long[] { 0x0000000000008000L });

  public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionDeclaration777 = new BitSet(
          new long[] { 0x0000000002000000L });

  public static final BitSet FOLLOW_actionPart_in_actionDeclaration784 = new BitSet(
          new long[] { 0x0000000000000100L });

  public static final BitSet FOLLOW_SEMI_in_actionDeclaration786 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_DECLARE_in_declaration816 = new BitSet(
          new long[] { 0x0000400000002000L });

  public static final BitSet FOLLOW_annotationType_in_declaration822 = new BitSet(
          new long[] { 0x0000000000002000L });

  public static final BitSet FOLLOW_Identifier_in_declaration832 = new BitSet(
          new long[] { 0x0000000000004100L });

  public static final BitSet FOLLOW_COMMA_in_declaration843 = new BitSet(
          new long[] { 0x0000000000002000L });

  public static final BitSet FOLLOW_Identifier_in_declaration853 = new BitSet(
          new long[] { 0x0000000000004100L });

  public static final BitSet FOLLOW_SEMI_in_declaration863 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_LIST_in_declaration881 = new BitSet(
          new long[] { 0x0000000000002000L });

  public static final BitSet FOLLOW_dottedIdentifier_in_declaration891 = new BitSet(
          new long[] { 0x0000000000000100L });

  public static final BitSet FOLLOW_SEMI_in_declaration896 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_TABLE_in_declaration909 = new BitSet(
          new long[] { 0x0000000000002000L });

  public static final BitSet FOLLOW_dottedIdentifier_in_declaration919 = new BitSet(
          new long[] { 0x0000000000000100L });

  public static final BitSet FOLLOW_SEMI_in_declaration924 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_DECLARE_in_declaration939 = new BitSet(
          new long[] { 0x0000400000002000L });

  public static final BitSet FOLLOW_annotationType_in_declaration946 = new BitSet(
          new long[] { 0x0000000000002000L });

  public static final BitSet FOLLOW_Identifier_in_declaration953 = new BitSet(
          new long[] { 0x0000000002000000L });

  public static final BitSet FOLLOW_LPAREN_in_declaration959 = new BitSet(
          new long[] { 0x0000400000073000L });

  public static final BitSet FOLLOW_annotationType_in_declaration979 = new BitSet(
          new long[] { 0x0000000000002000L });

  public static final BitSet FOLLOW_StringString_in_declaration992 = new BitSet(
          new long[] { 0x0000000000002000L });

  public static final BitSet FOLLOW_DoubleString_in_declaration1005 = new BitSet(
          new long[] { 0x0000000000002000L });

  public static final BitSet FOLLOW_IntString_in_declaration1018 = new BitSet(
          new long[] { 0x0000000000002000L });

  public static final BitSet FOLLOW_BooleanString_in_declaration1031 = new BitSet(
          new long[] { 0x0000000000002000L });

  public static final BitSet FOLLOW_Identifier_in_declaration1048 = new BitSet(
          new long[] { 0x0000000004004000L });

  public static final BitSet FOLLOW_COMMA_in_declaration1060 = new BitSet(
          new long[] { 0x0000400000073000L });

  public static final BitSet FOLLOW_annotationType_in_declaration1076 = new BitSet(
          new long[] { 0x0000000000002000L });

  public static final BitSet FOLLOW_StringString_in_declaration1089 = new BitSet(
          new long[] { 0x0000000000002000L });

  public static final BitSet FOLLOW_DoubleString_in_declaration1102 = new BitSet(
          new long[] { 0x0000000000002000L });

  public static final BitSet FOLLOW_IntString_in_declaration1115 = new BitSet(
          new long[] { 0x0000000000002000L });

  public static final BitSet FOLLOW_BooleanString_in_declaration1128 = new BitSet(
          new long[] { 0x0000000000002000L });

  public static final BitSet FOLLOW_Identifier_in_declaration1145 = new BitSet(
          new long[] { 0x0000000004004000L });

  public static final BitSet FOLLOW_RPAREN_in_declaration1153 = new BitSet(
          new long[] { 0x0000000000000100L });

  public static final BitSet FOLLOW_SEMI_in_declaration1156 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_BlockString_in_blockDeclaration1191 = new BitSet(
          new long[] { 0x0000000002000000L });

  public static final BitSet FOLLOW_LPAREN_in_blockDeclaration1195 = new BitSet(
          new long[] { 0x0000000000002000L });

  public static final BitSet FOLLOW_Identifier_in_blockDeclaration1202 = new BitSet(
          new long[] { 0x0000000004000000L });

  public static final BitSet FOLLOW_RPAREN_in_blockDeclaration1209 = new BitSet(
          new long[] { 0x0000400000002000L });

  public static final BitSet FOLLOW_typeExpression_in_blockDeclaration1218 = new BitSet(
          new long[] { 0x0000000792000000L });

  public static final BitSet FOLLOW_quantifierPart_in_blockDeclaration1224 = new BitSet(
          new long[] { 0x0000000012000000L });

  public static final BitSet FOLLOW_conditionPart_in_blockDeclaration1240 = new BitSet(
          new long[] { 0x0000000012000000L });

  public static final BitSet FOLLOW_actionPart_in_blockDeclaration1248 = new BitSet(
          new long[] { 0x0000000010000000L });

  public static final BitSet FOLLOW_LCURLY_in_blockDeclaration1256 = new BitSet(
          new long[] { 0x0000400069FF3000L });

  public static final BitSet FOLLOW_statements_in_blockDeclaration1262 = new BitSet(
          new long[] { 0x0000000020000000L });

  public static final BitSet FOLLOW_RCURLY_in_blockDeclaration1268 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_ruleElements_in_simpleStatement1298 = new BitSet(
          new long[] { 0x0000000000000100L });

  public static final BitSet FOLLOW_SEMI_in_simpleStatement1300 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_ruleElement_in_ruleElements1326 = new BitSet(
          new long[] { 0x0000400049FF3002L });

  public static final BitSet FOLLOW_ruleElement_in_ruleElements1335 = new BitSet(
          new long[] { 0x0000400049FF3002L });

  public static final BitSet FOLLOW_ruleElementType_in_blockRuleElement1362 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_ruleElementType_in_ruleElement1389 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_ruleElementLiteral_in_ruleElement1408 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_typeExpression_in_ruleElementType1437 = new BitSet(
          new long[] { 0x0000000792000002L });

  public static final BitSet FOLLOW_quantifierPart_in_ruleElementType1443 = new BitSet(
          new long[] { 0x0000000012000002L });

  public static final BitSet FOLLOW_conditionPart_in_ruleElementType1450 = new BitSet(
          new long[] { 0x0000000002000002L });

  public static final BitSet FOLLOW_actionPart_in_ruleElementType1457 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_StringLiteral_in_ruleElementLiteral1484 = new BitSet(
          new long[] { 0x0000000792000002L });

  public static final BitSet FOLLOW_quantifierPart_in_ruleElementLiteral1490 = new BitSet(
          new long[] { 0x0000000012000002L });

  public static final BitSet FOLLOW_conditionPart_in_ruleElementLiteral1497 = new BitSet(
          new long[] { 0x0000000002000002L });

  public static final BitSet FOLLOW_actionPart_in_ruleElementLiteral1504 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_simpleTypeExpression_in_typeExpression1534 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_annotationType_in_simpleTypeExpression1564 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_STAR_in_quantifierPart1586 = new BitSet(
          new long[] { 0x0000000100000002L });

  public static final BitSet FOLLOW_QUESTION_in_quantifierPart1592 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_PLUS_in_quantifierPart1600 = new BitSet(
          new long[] { 0x0000000100000002L });

  public static final BitSet FOLLOW_QUESTION_in_quantifierPart1606 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_QUESTION_in_quantifierPart1614 = new BitSet(
          new long[] { 0x0000000100000002L });

  public static final BitSet FOLLOW_QUESTION_in_quantifierPart1620 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_LBRACK_in_quantifierPart1629 = new BitSet(
          new long[] { 0x00FE008002002000L });

  public static final BitSet FOLLOW_numberExpression_in_quantifierPart1635 = new BitSet(
          new long[] { 0x0000000000004000L });

  public static final BitSet FOLLOW_COMMA_in_quantifierPart1637 = new BitSet(
          new long[] { 0x00FE008002002000L });

  public static final BitSet FOLLOW_numberExpression_in_quantifierPart1643 = new BitSet(
          new long[] { 0x0000000800000000L });

  public static final BitSet FOLLOW_RBRACK_in_quantifierPart1645 = new BitSet(
          new long[] { 0x0000000100000002L });

  public static final BitSet FOLLOW_QUESTION_in_quantifierPart1651 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_LCURLY_in_conditionPart1681 = new BitSet(
          new long[] { 0x000003F000002000L });

  public static final BitSet FOLLOW_condition_in_conditionPart1687 = new BitSet(
          new long[] { 0x0000000020000100L });

  public static final BitSet FOLLOW_SEMI_in_conditionPart1694 = new BitSet(
          new long[] { 0x000003F000002000L });

  public static final BitSet FOLLOW_condition_in_conditionPart1700 = new BitSet(
          new long[] { 0x0000000020000100L });

  public static final BitSet FOLLOW_RCURLY_in_conditionPart1707 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_conditionAnd_in_condition1733 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_conditionInList_in_condition1745 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_conditionMofN_in_condition1757 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_conditionNot_in_condition1768 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_conditionOr_in_condition1777 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_conditionIf_in_condition1789 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_externalCondition_in_condition1809 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_variableCondition_in_condition1818 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_Identifier_in_variableCondition1850 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_Identifier_in_externalCondition1883 = new BitSet(
          new long[] { 0x0000000000004000L });

  public static final BitSet FOLLOW_COMMA_in_externalCondition1885 = new BitSet(
          new long[] { 0x19FE408042002000L });

  public static final BitSet FOLLOW_varArgumentList_in_externalCondition1892 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_AND_in_conditionAnd1919 = new BitSet(
          new long[] { 0x0000000010000000L });

  public static final BitSet FOLLOW_conditionPart_in_conditionAnd1925 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_INLIST_in_conditionInList1952 = new BitSet(
          new long[] { 0x0000000000004000L });

  public static final BitSet FOLLOW_COMMA_in_conditionInList1954 = new BitSet(
          new long[] { 0x0000000000002000L });

  public static final BitSet FOLLOW_dottedIdentifier_in_conditionInList1960 = new BitSet(
          new long[] { 0x0000000000004002L });

  public static final BitSet FOLLOW_COMMA_in_conditionInList1967 = new BitSet(
          new long[] { 0x00FE008002002000L });

  public static final BitSet FOLLOW_numberExpression_in_conditionInList1973 = new BitSet(
          new long[] { 0x0000000000004002L });

  public static final BitSet FOLLOW_COMMA_in_conditionInList1978 = new BitSet(
          new long[] { 0x19FE008002002000L });

  public static final BitSet FOLLOW_booleanExpression_in_conditionInList1984 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_INLIST_in_conditionInList2003 = new BitSet(
          new long[] { 0x0000000000004000L });

  public static final BitSet FOLLOW_COMMA_in_conditionInList2005 = new BitSet(
          new long[] { 0x0000000400000000L });

  public static final BitSet FOLLOW_innerList_in_conditionInList2011 = new BitSet(
          new long[] { 0x0000000000004002L });

  public static final BitSet FOLLOW_COMMA_in_conditionInList2018 = new BitSet(
          new long[] { 0x00FE008002002000L });

  public static final BitSet FOLLOW_numberExpression_in_conditionInList2024 = new BitSet(
          new long[] { 0x0000000000004002L });

  public static final BitSet FOLLOW_COMMA_in_conditionInList2029 = new BitSet(
          new long[] { 0x19FE008002002000L });

  public static final BitSet FOLLOW_booleanExpression_in_conditionInList2035 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_MOFN_in_conditionMofN2066 = new BitSet(
          new long[] { 0x0000000000004000L });

  public static final BitSet FOLLOW_COMMA_in_conditionMofN2068 = new BitSet(
          new long[] { 0x00FE008002002000L });

  public static final BitSet FOLLOW_numberExpression_in_conditionMofN2074 = new BitSet(
          new long[] { 0x0000000000004000L });

  public static final BitSet FOLLOW_COMMA_in_conditionMofN2076 = new BitSet(
          new long[] { 0x00FE008002002000L });

  public static final BitSet FOLLOW_numberExpression_in_conditionMofN2082 = new BitSet(
          new long[] { 0x0000000010000000L });

  public static final BitSet FOLLOW_conditionPart_in_conditionMofN2088 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_MINUS_in_conditionNot2114 = new BitSet(
          new long[] { 0x000003F000002000L });

  public static final BitSet FOLLOW_condition_in_conditionNot2120 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_OR_in_conditionOr2144 = new BitSet(
          new long[] { 0x0000000010000000L });

  public static final BitSet FOLLOW_conditionPart_in_conditionOr2150 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_IF_in_conditionIf2176 = new BitSet(
          new long[] { 0x0000000000004000L });

  public static final BitSet FOLLOW_COMMA_in_conditionIf2178 = new BitSet(
          new long[] { 0x19FE008002002000L });

  public static final BitSet FOLLOW_booleanExpression_in_conditionIf2184 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_LPAREN_in_actionPart2216 = new BitSet(
          new long[] { 0x00001C0000002000L });

  public static final BitSet FOLLOW_action_in_actionPart2222 = new BitSet(
          new long[] { 0x0000000004000100L });

  public static final BitSet FOLLOW_SEMI_in_actionPart2229 = new BitSet(
          new long[] { 0x00001C0000002000L });

  public static final BitSet FOLLOW_action_in_actionPart2235 = new BitSet(
          new long[] { 0x0000000004000100L });

  public static final BitSet FOLLOW_RPAREN_in_actionPart2242 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_actionCreateStructure_in_action2266 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_actionCall_in_action2277 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_externalAction_in_action2297 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_variableAction_in_action2306 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_Identifier_in_variableAction2335 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_Identifier_in_externalAction2364 = new BitSet(
          new long[] { 0x0000000000004000L });

  public static final BitSet FOLLOW_COMMA_in_externalAction2366 = new BitSet(
          new long[] { 0x19FE408042002000L });

  public static final BitSet FOLLOW_varArgumentList_in_externalAction2372 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_set_in_actionCreateStructure2400 = new BitSet(
          new long[] { 0x0000000000004000L });

  public static final BitSet FOLLOW_COMMA_in_actionCreateStructure2408 = new BitSet(
          new long[] { 0x0000400000002000L });

  public static final BitSet FOLLOW_typeExpression_in_actionCreateStructure2414 = new BitSet(
          new long[] { 0x0000000000004000L });

  public static final BitSet FOLLOW_COMMA_in_actionCreateStructure2422 = new BitSet(
          new long[] { 0x0000000040002000L });

  public static final BitSet FOLLOW_stringExpression_in_actionCreateStructure2428 = new BitSet(
          new long[] { 0x0000000000008000L });

  public static final BitSet FOLLOW_ASSIGN_EQUAL_in_actionCreateStructure2430 = new BitSet(
          new long[] { 0x19FE408042002000L });

  public static final BitSet FOLLOW_argument_in_actionCreateStructure2436 = new BitSet(
          new long[] { 0x0000000000004002L });

  public static final BitSet FOLLOW_CALL_in_actionCall2467 = new BitSet(
          new long[] { 0x0000000000004000L });

  public static final BitSet FOLLOW_COMMA_in_actionCall2469 = new BitSet(
          new long[] { 0x0000000000002000L });

  public static final BitSet FOLLOW_dottedId_in_actionCall2475 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_argument_in_varArgumentList2501 = new BitSet(
          new long[] { 0x0000000000004002L });

  public static final BitSet FOLLOW_COMMA_in_varArgumentList2506 = new BitSet(
          new long[] { 0x19FE408042002000L });

  public static final BitSet FOLLOW_argument_in_varArgumentList2512 = new BitSet(
          new long[] { 0x0000000000004002L });

  public static final BitSet FOLLOW_stringExpression_in_argument2551 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_booleanExpression_in_argument2562 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_numberExpression_in_argument2573 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_typeExpression_in_argument2584 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_LBRACK_in_innerList2619 = new BitSet(
          new long[] { 0x0000000000002000L });

  public static final BitSet FOLLOW_Identifier_in_innerList2625 = new BitSet(new long[] {
      0x0000000800000000L, 0x0000000000002000L });

  public static final BitSet FOLLOW_77_in_innerList2633 = new BitSet(
          new long[] { 0x0000000000002000L });

  public static final BitSet FOLLOW_Identifier_in_innerList2639 = new BitSet(new long[] {
      0x0000000800000000L, 0x0000000000002000L });

  public static final BitSet FOLLOW_RBRACK_in_innerList2646 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_Identifier_in_dottedIdentifier2671 = new BitSet(
          new long[] { 0x0000200000000002L });

  public static final BitSet FOLLOW_DOT_in_dottedIdentifier2684 = new BitSet(
          new long[] { 0x0000000000002000L });

  public static final BitSet FOLLOW_Identifier_in_dottedIdentifier2692 = new BitSet(
          new long[] { 0x0000200000000002L });

  public static final BitSet FOLLOW_Identifier_in_dottedId2722 = new BitSet(
          new long[] { 0x0000200000000002L });

  public static final BitSet FOLLOW_DOT_in_dottedId2746 = new BitSet(
          new long[] { 0x0000000000002000L });

  public static final BitSet FOLLOW_Identifier_in_dottedId2755 = new BitSet(
          new long[] { 0x0000200000000002L });

  public static final BitSet FOLLOW_annotationTypeVariableReference_in_annotationType2793 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_BasicAnnotationType_in_annotationType2804 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_Identifier_in_annotationTypeVariableReference2835 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_additiveExpression_in_numberExpression2860 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression2886 = new BitSet(
          new long[] { 0x0000008200000002L });

  public static final BitSet FOLLOW_set_in_additiveExpression2895 = new BitSet(
          new long[] { 0x00FE008002002000L });

  public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression2905 = new BitSet(
          new long[] { 0x0000008200000002L });

  public static final BitSet FOLLOW_simpleNumberExpression_in_multiplicativeExpression2934 = new BitSet(
          new long[] { 0x0001800080000002L });

  public static final BitSet FOLLOW_set_in_multiplicativeExpression2943 = new BitSet(
          new long[] { 0x0006008002002000L });

  public static final BitSet FOLLOW_simpleNumberExpression_in_multiplicativeExpression2961 = new BitSet(
          new long[] { 0x0001800080000002L });

  public static final BitSet FOLLOW_numberFunction_in_multiplicativeExpression2977 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_LPAREN_in_numberExpressionInPar2997 = new BitSet(
          new long[] { 0x00FE008002002000L });

  public static final BitSet FOLLOW_numberExpression_in_numberExpressionInPar3003 = new BitSet(
          new long[] { 0x0000000004000000L });

  public static final BitSet FOLLOW_RPAREN_in_numberExpressionInPar3005 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_MINUS_in_simpleNumberExpression3031 = new BitSet(
          new long[] { 0x0002000000000000L });

  public static final BitSet FOLLOW_DecimalLiteral_in_simpleNumberExpression3039 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_MINUS_in_simpleNumberExpression3057 = new BitSet(
          new long[] { 0x0004000000000000L });

  public static final BitSet FOLLOW_FloatingPointLiteral_in_simpleNumberExpression3064 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_MINUS_in_simpleNumberExpression3081 = new BitSet(
          new long[] { 0x0000008000002000L });

  public static final BitSet FOLLOW_numberVariable_in_simpleNumberExpression3088 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_numberExpressionInPar_in_simpleNumberExpression3106 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_set_in_numberFunction3134 = new BitSet(
          new long[] { 0x0006008002002000L });

  public static final BitSet FOLLOW_numberExpressionInPar_in_numberFunction3156 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_externalNumberFunction_in_numberFunction3182 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_Identifier_in_externalNumberFunction3207 = new BitSet(
          new long[] { 0x0000000002000000L });

  public static final BitSet FOLLOW_LPAREN_in_externalNumberFunction3209 = new BitSet(
          new long[] { 0x19FE408042002000L });

  public static final BitSet FOLLOW_varArgumentList_in_externalNumberFunction3216 = new BitSet(
          new long[] { 0x0000000004000000L });

  public static final BitSet FOLLOW_RPAREN_in_externalNumberFunction3218 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_Identifier_in_numberVariable3250 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_stringFunction_in_stringExpression3282 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_simpleStringExpression_in_stringExpression3297 = new BitSet(
          new long[] { 0x0000000200000002L });

  public static final BitSet FOLLOW_PLUS_in_stringExpression3303 = new BitSet(
          new long[] { 0x0006008042002000L });

  public static final BitSet FOLLOW_simpleStringExpression_in_stringExpression3310 = new BitSet(
          new long[] { 0x0000000200000002L });

  public static final BitSet FOLLOW_numberExpressionInPar_in_stringExpression3318 = new BitSet(
          new long[] { 0x0000000200000002L });

  public static final BitSet FOLLOW_externalStringFunction_in_stringFunction3360 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_Identifier_in_externalStringFunction3386 = new BitSet(
          new long[] { 0x0000000002000000L });

  public static final BitSet FOLLOW_LPAREN_in_externalStringFunction3388 = new BitSet(
          new long[] { 0x19FE408042002000L });

  public static final BitSet FOLLOW_varArgumentList_in_externalStringFunction3395 = new BitSet(
          new long[] { 0x0000000004000000L });

  public static final BitSet FOLLOW_RPAREN_in_externalStringFunction3397 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_StringLiteral_in_simpleStringExpression3427 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_Identifier_in_simpleStringExpression3446 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_composedBooleanExpression_in_booleanExpression3492 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_simpleBooleanExpression_in_booleanExpression3503 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_Identifier_in_booleanExpression3519 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_booleanFunction_in_composedBooleanExpression3559 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_booleanNumberExpression_in_composedBooleanExpression3570 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_booleanCompare_in_composedBooleanExpression3581 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_XOR_in_booleanFunction3606 = new BitSet(
          new long[] { 0x0000000002000000L });

  public static final BitSet FOLLOW_LPAREN_in_booleanFunction3608 = new BitSet(
          new long[] { 0x19FE008002002000L });

  public static final BitSet FOLLOW_booleanExpression_in_booleanFunction3614 = new BitSet(
          new long[] { 0x0000000000004000L });

  public static final BitSet FOLLOW_COMMA_in_booleanFunction3616 = new BitSet(
          new long[] { 0x19FE008002002000L });

  public static final BitSet FOLLOW_booleanExpression_in_booleanFunction3622 = new BitSet(
          new long[] { 0x0000000004000000L });

  public static final BitSet FOLLOW_RPAREN_in_booleanFunction3624 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_Identifier_in_externalBooleanFunction3656 = new BitSet(
          new long[] { 0x0000000002000000L });

  public static final BitSet FOLLOW_LPAREN_in_externalBooleanFunction3658 = new BitSet(
          new long[] { 0x19FE408042002000L });

  public static final BitSet FOLLOW_varArgumentList_in_externalBooleanFunction3665 = new BitSet(
          new long[] { 0x0000000004000000L });

  public static final BitSet FOLLOW_RPAREN_in_externalBooleanFunction3667 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_simpleBooleanExpression_in_booleanCompare3695 = new BitSet(
          new long[] { 0x0600000000000000L });

  public static final BitSet FOLLOW_set_in_booleanCompare3701 = new BitSet(
          new long[] { 0x19FE008002002000L });

  public static final BitSet FOLLOW_booleanExpression_in_booleanCompare3713 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_TRUE_in_simpleBooleanExpression3742 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_FALSE_in_simpleBooleanExpression3752 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_numberExpression_in_booleanNumberExpression3782 = new BitSet(
          new long[] { 0xE600000000000000L, 0x0000000000000001L });

  public static final BitSet FOLLOW_set_in_booleanNumberExpression3789 = new BitSet(
          new long[] { 0x00FE008002002000L });

  public static final BitSet FOLLOW_numberExpression_in_booleanNumberExpression3818 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_Identifier_in_genericVariableReference3840 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_conditionPart_in_synpred2_ConvertSyntax1233 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_externalCondition_in_synpred4_ConvertSyntax1801 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_externalAction_in_synpred6_ConvertSyntax2289 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_stringExpression_in_synpred7_ConvertSyntax2551 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_booleanExpression_in_synpred8_ConvertSyntax2562 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_numberExpression_in_synpred9_ConvertSyntax2573 = new BitSet(
          new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_externalNumberFunction_in_synpred10_ConvertSyntax3174 = new BitSet(
          new long[] { 0x0000000000000002L });

}
