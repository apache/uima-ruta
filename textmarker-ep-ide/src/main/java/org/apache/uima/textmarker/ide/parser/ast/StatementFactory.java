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

package org.apache.uima.textmarker.ide.parser.ast;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.Declaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Statement;


public class StatementFactory extends AbstractFactory {
  /**
   * @param impString
   * @param dottedId
   *          Antlr-Token (dotted-identifier/id)
   * @return new Import-Statement
   */
  public static TextMarkerImportStatement createImport(ComponentDeclaration dottedId,
          Token impString, int type) {
    int bounds[] = getBounds(impString, dottedId);
    return new TextMarkerImportStatement(bounds[0], bounds[1], dottedId, type);
  }

  public static ComponentDeclaration createEmptyComponentDeclaration(Token t) {
    int bounds[] = getBounds(t);
    bounds[0] = bounds[1] + 1;
    bounds[1] = bounds[0];
    return new ComponentDeclaration(bounds[0], bounds[1], "");
  }

  public static TextMarkerImportStatement createImportTypeSystem(ComponentDeclaration dottedId,
          Token impString) {
    dottedId.setType(ComponentDeclaration.TYPESYSTEM);
    return createImport(dottedId, impString, TMStatementConstants.S_IMPORT_TYPESYSTEM);
  }

  public static TextMarkerImportStatement createImportScript(ComponentDeclaration dottedId,
          Token impString) {
    if (dottedId != null) {
      dottedId.setType(ComponentDeclaration.SCRIPT);
    }
    return createImport(dottedId, impString, TMStatementConstants.S_IMPORT_SCRIPT);
  }

  public static TextMarkerImportStatement createImportEngine(ComponentDeclaration dottedId,
          Token impString) {
    dottedId.setType(ComponentDeclaration.ENGINE);
    return createImport(dottedId, impString, TMStatementConstants.S_IMPORT_ENGINE);
  }

  /**
   * @param pString
   *          Antlr-Token "PACKAGE"
   * @param dottedId
   *          Antlr-Token (dotted-identifier/id)
   * @return
   */
  public static TextMarkerPackageDeclaration createPkgDeclaration(Token dottedId, Token pString) {
    int bounds[] = getBounds(pString, dottedId);
    int nameBounds[] = new int[2];
    if (dottedId != null) {
      nameBounds = getBounds(dottedId);
    }
    SimpleReference ref = new SimpleReference(nameBounds[0], nameBounds[1], dottedId == null ? ""
            : dottedId.getText());
    return new TextMarkerPackageDeclaration(bounds[0], bounds[1], ref);
  }

  /**
   * @param id
   * @param type
   * @return
   */
  private static TextMarkerVariableDeclaration createVariable(Token id, Token typeToken, int type) {
    return createVariable(id, typeToken, type, null);
  }

  private static TextMarkerVariableDeclaration createVariable(Token id, Token typeToken, int type,
          Expression expr) {
    int declBounds[] = getBounds(typeToken, id);
    int nameBounds[] = getBounds(id);
    // FieldDeclaration
    SimpleReference ref = new TextMarkerVariableReference(nameBounds[0], nameBounds[1], id
            .getText(), type);
    return new TextMarkerVariableDeclaration(id.getText(), nameBounds[0], nameBounds[1],
            declBounds[0], declBounds[1], ref, type, expr);
  }

  /**
   * @param id
   * @param type
   * @return
   */
  public static TextMarkerVariableDeclaration createIntVariable(Token id, Token type) {
    return createVariable(id, type, TMTypeConstants.TM_TYPE_I);
  }

  /**
   * @param id
   * @param type
   * @return
   */
  public static Object createFloatVariable(Token id, Token type) {
    return createVariable(id, type, TMTypeConstants.TM_TYPE_F);
  }
  
  /**
   * @param id
   * @param type
   * @return
   */
  public static TextMarkerVariableDeclaration createDoubleVariable(Token id, Token type) {
    return createVariable(id, type, TMTypeConstants.TM_TYPE_D);
  }

  /**
   * @param id
   * @param type
   * @return
   */
  public static TextMarkerVariableDeclaration createStringVariable(Token id, Token type) {
    return createVariable(id, type, TMTypeConstants.TM_TYPE_S);
  }

  /**
   * @param id
   * @param type
   * @return
   */
  public static TextMarkerVariableDeclaration createBooleanVariable(Token id, Token type) {
    return createVariable(id, type, TMTypeConstants.TM_TYPE_B);
  }

  /**
   * @param id
   * @param type
   * @return
   */
  public static TextMarkerVariableDeclaration createTypeVariable(Token id, Token type) {
    return createVariable(id, type, TMTypeConstants.TM_TYPE_AT);
  }

  /**
   * @param id
   * @param type
   * @return
   */
  public static TextMarkerVariableDeclaration createListVariable(Token id, Token type,
          Expression expr) {
    return createVariable(id, type, TMTypeConstants.TM_TYPE_WL, expr);
  }

  /**
   * @param id
   * @param type
   * @return
   */
  public static TextMarkerVariableDeclaration createTableVariable(Token id, Token type,
          Expression expr) {
    return createVariable(id, type, TMTypeConstants.TM_TYPE_WT, expr);
  }

  /**
   * @param id
   * @param declareToken
   * @param featureNames
   * @param featureTypes
   * @param type
   * @return
   */

  public static Declaration createAnnotationType(Token id, Token declareToken, Expression type,
          List<TextMarkerFeatureDeclaration> features) {

    int declBounds[] = getBounds(declareToken, id);
    if (features != null && !features.isEmpty()) {
      declBounds = getBounds(declareToken, features.get(features.size() - 1));
    }
    int nameBounds[] = getBounds(id);
    // FieldDeclarartion

    SimpleReference ref = new TextMarkerVariableReference(nameBounds[0], nameBounds[1], id
            .getText(), TMTypeConstants.TM_TYPE_AT);
    return new TextMarkerTypeDeclaration(id.getText(), nameBounds[0], nameBounds[1], declBounds[0],
            declBounds[1], ref, features);
  }

  public static Declaration createAnnotationType(Token id, Token declareToken) {
    int declBounds[] = getBounds(declareToken, id);
    int nameBounds[] = getBounds(id);

    SimpleReference ref = new TextMarkerVariableReference(nameBounds[0], nameBounds[1], id
            .getText(), TMTypeConstants.TM_TYPE_AT);
    return new TextMarkerTypeDeclaration(id.getText(), nameBounds[0], nameBounds[1], declBounds[0],
            declBounds[1], ref);
  }

  public static TextMarkerFeatureDeclaration createFeatureDeclaration(Object eachTO, Token eachName) {
    int declBounds[] = { 0, 0 };
    String type = "";
    if (eachTO instanceof ASTNode) {
      ASTNode node = (ASTNode) eachTO;
      type = node.toString();
      declBounds = getBounds(eachName, node);
    } else if (eachTO instanceof Token) {
      Token token = (Token) eachTO;
      type = token.getText();
      declBounds = getBounds(eachName, token);
    }
    int nameBounds[] = getBounds(eachName);
    SimpleReference ref = new SimpleReference(nameBounds[0], nameBounds[1], eachName.getText());
    return new TextMarkerFeatureDeclaration(eachName.getText(), type, nameBounds[0], nameBounds[1],
            declBounds[0], declBounds[1], ref);
  }

  @SuppressWarnings("unchecked")
  public static Statement createDeclareDeclarationsStatement(Token declareToken, List declarations,
          ASTNode parent) {
    List<TextMarkerAbstractDeclaration> decls = declarations;
    for (TextMarkerAbstractDeclaration d : decls) {
      if (d == null) {
        decls.remove(d);
      }
    }
    int declBounds[] = getBounds(declareToken);
    int statementBounds[] = getBounds(declareToken);
    if (parent != null) {
      statementBounds[1] = Math.max(statementBounds[1], parent.sourceEnd());
    } else {
      int end = decls.get(decls.size() - 1).sourceEnd();
      statementBounds[1] = Math.max(statementBounds[1], end);
    }
    return new TextMarkerDeclareDeclarationsStatement(statementBounds[0], statementBounds[1],
            declarations, parent, declBounds[0], declBounds[1]);
  }

  @SuppressWarnings("unchecked")
  public static Statement createDeclarationsStatement(Token declareToken, List declarations,
          Expression init) {
    List<TextMarkerAbstractDeclaration> decls = declarations;
    for (TextMarkerAbstractDeclaration d : decls) {
      if (d == null) {
        decls.remove(d);
      }
    }
    int declBounds[] = getBounds(declareToken);
    int statementBounds[] = getBounds(declareToken);
    if (init != null) {
      statementBounds[1] = Math.max(statementBounds[1], init.sourceEnd());
    } else {
      int end = decls.get(decls.size() - 1).sourceEnd();
      statementBounds[1] = Math.max(statementBounds[1], end);
    }
    return new TextMarkerDeclarationsStatement(statementBounds[0], statementBounds[1],
            declarations, init, declBounds[0], declBounds[1]);
  }

  @SuppressWarnings("unchecked")
  public static Statement createDeclarationsStatement(Token declareToken, List declarations) {
    return createDeclarationsStatement(declareToken, declarations, null);
  }

  @SuppressWarnings("unchecked")
  public static Statement createDeclarationsStatement(Token declareToken,
          TextMarkerAbstractDeclaration declaration, Expression init) {
    List decl = new ArrayList<TextMarkerAbstractDeclaration>();
    return createDeclarationsStatement(declareToken, decl, init);
  }

  public static Statement createComposedVariableConditionDeclaration(Token id,
          List<TextMarkerCondition> conditions) {
    return null;
  }

  public static Statement createComposedVariableActionDeclaration(Token id,
          List<TextMarkerAction> actions) {
    return null;
  }

  public static Object createVarListVariable(Token id, Token token, Expression expr, int type) {
    return createVariable(id, token, type, expr);
  }

  public static ComponentReference createComponentReference(CommonToken ct) {
    int nameBounds[] = getBounds(ct);
    return new ComponentReference(nameBounds[0], nameBounds[1], ct.getText());
  }

  public static ComponentReference createEmtpyComponentReference(Token t) {
    int nameBounds[] = getBounds(t);
    nameBounds[0] = nameBounds[1];
    nameBounds[1] = nameBounds[0];
    return new ComponentReference(nameBounds[0], nameBounds[1], "");
  }

  public static ComponentDeclaration createComponentDeclaration(CommonToken ct) {
    int nameBounds[] = getBounds(ct);
    return new ComponentDeclaration(nameBounds[0], nameBounds[1], ct.getText());
  }



}
