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

package org.apache.uima.ruta.ide.parser.ast;

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
  public static RutaImportStatement createImport(ComponentDeclaration dottedId, Token impString,
          int type) {
    int bounds[] = getBounds(impString, dottedId);
    return new RutaImportStatement(bounds[0], bounds[1], dottedId, type);
  }

  public static ComponentDeclaration createEmptyComponentDeclaration(Token t) {
    int bounds[] = getBounds(t);
    bounds[0] = bounds[1] + 1;
    bounds[1] = bounds[0];
    return new ComponentDeclaration(bounds[0], bounds[1], "");
  }

  public static RutaImportStatement createImportTypeSystem(ComponentDeclaration dottedId,
          Token impString) {
    dottedId.setType(ComponentDeclaration.TYPESYSTEM);
    return createImport(dottedId, impString, RutaStatementConstants.S_IMPORT_TYPESYSTEM);
  }

  public static RutaImportStatement createImportScript(ComponentDeclaration dottedId,
          Token impString) {
    if (dottedId != null) {
      dottedId.setType(ComponentDeclaration.SCRIPT);
    }
    return createImport(dottedId, impString, RutaStatementConstants.S_IMPORT_SCRIPT);
  }

  public static RutaImportStatement createImportEngine(ComponentDeclaration dottedId,
          Token impString) {
    dottedId.setType(ComponentDeclaration.ENGINE);
    int kind = RutaStatementConstants.S_IMPORT_ENGINE;
    if (impString != null) {
      String text = impString.getText();
      if ("UIMAFIT".equals(text)) {
        kind = RutaStatementConstants.S_IMPORT_UIMAFIT_ENGINE;
        dottedId.setType(ComponentDeclaration.UIMAFIT_ENGINE);
      }
    }
    return createImport(dottedId, impString, kind);
  }

  /**
   * @param pString
   *          Antlr-Token "PACKAGE"
   * @param dottedId
   *          Antlr-Token (dotted-identifier/id)
   * @return
   */
  public static RutaPackageDeclaration createPkgDeclaration(Token dottedId, Token pString) {
    int bounds[] = getBounds(pString, dottedId);
    int nameBounds[] = new int[2];
    if (dottedId != null) {
      nameBounds = getBounds(dottedId);
    }
    SimpleReference ref = new SimpleReference(nameBounds[0], nameBounds[1], dottedId == null ? ""
            : dottedId.getText());
    return new RutaPackageDeclaration(bounds[0], bounds[1], ref);
  }

  /**
   * @param id
   * @param type
   * @return
   */
  private static RutaVariableDeclaration createVariable(Token id, Token typeToken, int type) {
    return createVariable(id, typeToken, type, null);
  }

  private static RutaVariableDeclaration createVariable(Token id, Token typeToken, int type,
          Expression expr) {
    int declBounds[] = getBounds(typeToken, id);
    int nameBounds[] = getBounds(id);
    // FieldDeclaration
    SimpleReference ref = new RutaVariableReference(nameBounds[0], nameBounds[1], id.getText(),
            type);
    return new RutaVariableDeclaration(id.getText(), nameBounds[0], nameBounds[1], declBounds[0],
            declBounds[1], ref, type, expr);
  }

  /**
   * @param id
   * @param type
   * @return
   */
  public static RutaVariableDeclaration createIntVariable(Token id, Token type) {
    return createVariable(id, type, RutaTypeConstants.RUTA_TYPE_I);
  }

  /**
   * @param id
   * @param type
   * @return
   */
  public static Object createFloatVariable(Token id, Token type) {
    return createVariable(id, type, RutaTypeConstants.RUTA_TYPE_F);
  }

  /**
   * @param id
   * @param type
   * @return
   */
  public static RutaVariableDeclaration createDoubleVariable(Token id, Token type) {
    return createVariable(id, type, RutaTypeConstants.RUTA_TYPE_D);
  }

  /**
   * @param id
   * @param type
   * @return
   */
  public static RutaVariableDeclaration createStringVariable(Token id, Token type) {
    return createVariable(id, type, RutaTypeConstants.RUTA_TYPE_S);
  }

  /**
   * @param id
   * @param type
   * @return
   */
  public static RutaVariableDeclaration createBooleanVariable(Token id, Token type) {
    return createVariable(id, type, RutaTypeConstants.RUTA_TYPE_B);
  }

  /**
   * @param id
   * @param type
   * @return
   */
  public static RutaVariableDeclaration createTypeVariable(Token id, Token type) {
    return createVariable(id, type, RutaTypeConstants.RUTA_TYPE_AT);
  }

  /**
   * @param id
   * @param type
   * @return
   */
  public static RutaVariableDeclaration createListVariable(Token id, Token type, Expression expr) {
    return createVariable(id, type, RutaTypeConstants.RUTA_TYPE_WL, expr);
  }

  /**
   * @param id
   * @param type
   * @return
   */
  public static RutaVariableDeclaration createTableVariable(Token id, Token type, Expression expr) {
    return createVariable(id, type, RutaTypeConstants.RUTA_TYPE_WT, expr);
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
          List<RutaFeatureDeclaration> features) {

    int declBounds[] = getBounds(declareToken, id);
    if (features != null && !features.isEmpty()) {
      declBounds = getBounds(declareToken, features.get(features.size() - 1));
    }
    int nameBounds[] = getBounds(id);
    // FieldDeclarartion

    SimpleReference ref = new RutaVariableReference(nameBounds[0], nameBounds[1], id.getText(),
            RutaTypeConstants.RUTA_TYPE_AT);
    return new RutaTypeDeclaration(id.getText(), nameBounds[0], nameBounds[1], declBounds[0],
            declBounds[1], ref, features);
  }

  public static Declaration createAnnotationType(Token id, Token declareToken) {
    int declBounds[] = getBounds(declareToken, id);
    int nameBounds[] = getBounds(id);

    SimpleReference ref = new RutaVariableReference(nameBounds[0], nameBounds[1], id.getText(),
            RutaTypeConstants.RUTA_TYPE_AT);
    return new RutaTypeDeclaration(id.getText(), nameBounds[0], nameBounds[1], declBounds[0],
            declBounds[1], ref);
  }

  public static RutaFeatureDeclaration createFeatureDeclaration(Object eachTO, Token eachName) {
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
    return new RutaFeatureDeclaration(eachName.getText(), type, nameBounds[0], nameBounds[1],
            declBounds[0], declBounds[1], ref);
  }

  @SuppressWarnings("unchecked")
  public static Statement createDeclareDeclarationsStatement(Token declareToken, List declarations,
          ASTNode parent) {
    List<RutaAbstractDeclaration> decls = declarations;
    for (RutaAbstractDeclaration d : decls) {
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
    return new RutaDeclareDeclarationsStatement(statementBounds[0], statementBounds[1],
            declarations, parent, declBounds[0], declBounds[1]);
  }

  @SuppressWarnings("unchecked")
  public static Statement createDeclarationsStatement(Token declareToken, List declarations,
          Expression init) {
    List<RutaAbstractDeclaration> decls = declarations;
    for (RutaAbstractDeclaration d : decls) {
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
    return new RutaDeclarationsStatement(statementBounds[0], statementBounds[1], declarations,
            init, declBounds[0], declBounds[1]);
  }

  @SuppressWarnings("unchecked")
  public static Statement createDeclarationsStatement(Token declareToken, List declarations) {
    return createDeclarationsStatement(declareToken, declarations, null);
  }

  @SuppressWarnings("unchecked")
  public static Statement createDeclarationsStatement(Token declareToken,
          RutaAbstractDeclaration declaration, Expression init) {
    List decl = new ArrayList<RutaAbstractDeclaration>();
    return createDeclarationsStatement(declareToken, decl, init);
  }

  public static Statement createComposedVariableConditionDeclaration(Token id,
          List<RutaCondition> conditions) {
    return null;
  }

  public static Statement createComposedVariableActionDeclaration(Token id, List<RutaAction> actions) {
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
    String text = ct.getText();
    return new ComponentDeclaration(nameBounds[0], nameBounds[0] + text.length(), text);
  }

}
