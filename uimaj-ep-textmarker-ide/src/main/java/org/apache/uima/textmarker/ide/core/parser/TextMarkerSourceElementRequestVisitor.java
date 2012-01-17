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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.uima.textmarker.ide.parser.ast.TMTypeConstants;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerBasicAnnotationType;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerImportStatement;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerPackageDeclaration;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerTypeDeclaration;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerVariableDeclaration;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.Modifiers;
import org.eclipse.dltk.ast.PositionInformation;
import org.eclipse.dltk.ast.declarations.Argument;
import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.references.VariableReference;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.compiler.ISourceElementRequestor;
import org.eclipse.dltk.compiler.SourceElementRequestVisitor;

public class TextMarkerSourceElementRequestVisitor extends SourceElementRequestVisitor {

  private static class TypeField {
    private String fName;

    private String fInitValue;

    private PositionInformation fPos;

    private Expression fExpression;

    private ASTNode fToNode;

    private ASTNode declaredIn; // The node where the declaration was found

    // (should be either class or method node)
    TypeField(String name, String initValue, PositionInformation pos, Expression expression,
            ASTNode toNode, ASTNode declaredIn) {

      this.fName = name;
      this.fInitValue = initValue;
      this.fPos = pos;
      this.fExpression = expression;
      this.fToNode = toNode;
      this.declaredIn = declaredIn;
    }

    String getName() {

      return this.fName;
    }

    String getInitValue() {

      return this.fInitValue;
    }

    PositionInformation getPos() {

      return this.fPos;
    }

    Expression getExpression() {

      return this.fExpression;
    }

    ASTNode getToNode() {

      return this.fToNode;
    }

    @Override
    public boolean equals(Object obj) {

      if (obj instanceof TypeField) {
        TypeField second = (TypeField) obj;
        return second.fName.equals(this.fName) && second.fToNode.equals(this.fToNode);
      }
      return super.equals(obj);
    }

    @Override
    public String toString() {

      return this.fName;
    }

    public ASTNode getDeclaredIn() {
      return declaredIn;
    }

  }

  private static String ANONYMOUS_LAMBDA_FORM_MARKER = "<anonymous>";

  // Used to prehold fields if adding in methods.
  private List fNotAddedFields = new ArrayList();

  private String lastLambdaFormName = ANONYMOUS_LAMBDA_FORM_MARKER;

  /**
   * Used to determine duplicate names.
   */
  private Map fTypeVariables = new HashMap();

  //
  public TextMarkerSourceElementRequestVisitor(ISourceElementRequestor requestor) {
    super(requestor);
  }

  protected String makeLanguageDependentValue(Expression value) {
    String outValue = "";
    return outValue;
  }

  @Override
  protected void onEndVisitMethod(MethodDeclaration method) {
    if (fNotAddedFields.size() >= 1) {
      TypeField typeField = (TypeField) fNotAddedFields.get(0);
      if (null != typeField && typeField.getDeclaredIn().equals(method)) {
        Iterator i = this.fNotAddedFields.iterator();
        while (i.hasNext()) {
          TypeField field = (TypeField) i.next();
          if (canAddVariables(field.getToNode(), field.getName())) {

            PositionInformation pos = field.getPos();

            ISourceElementRequestor.FieldInfo info = new ISourceElementRequestor.FieldInfo();
            info.modifiers = Modifiers.AccStatic;
            info.name = field.getName();
            info.nameSourceEnd = pos.nameEnd - 2;
            info.nameSourceStart = pos.nameStart;
            info.declarationStart = pos.sourceStart;
            this.fRequestor.enterField(info);
            this.fRequestor.exitField(pos.sourceEnd);

          }
        }
        this.fNotAddedFields.clear();
      }
    }
  }

  @Override
  public boolean visit(Statement statement) throws Exception {
    super.visit(statement);
    if (statement instanceof TextMarkerPackageDeclaration) {
      this.processPackage(statement);
      super.fNodes.pop();
      return false;
    }
    if (statement instanceof FieldDeclaration) {
      FieldDeclaration fieldDecl = (FieldDeclaration) statement;
      processFieldDeclaration(fieldDecl.getRef(), fieldDecl);
      super.fNodes.pop();
      return false;
    }
    // TODO handle tm-import statement
    if (statement instanceof TextMarkerImportStatement) {
      TextMarkerImportStatement tmImport = (TextMarkerImportStatement) statement;
      super.fNodes.pop();
      return false;
    }
    return true;
  }

  @Override
  public boolean visit(Expression expression) throws Exception {
    if (expression instanceof VariableReference) {
      VariableReference varRef = (VariableReference) expression;
      this.fRequestor.acceptFieldReference(varRef.getName(), varRef.sourceStart());
    }
    return super.visit(expression);
  }

  private void processPackage(Statement statement) {
    TextMarkerPackageDeclaration pack = (TextMarkerPackageDeclaration) statement;
    this.fRequestor.acceptPackage(pack.getNameStart(), pack.getNameEnd(),
           pack.getName());
  }

  private void processFieldDeclaration(SimpleReference variableIDRef, Statement fullDeclaration) {
    int modifier = Modifiers.AccDefault;
    if (fullDeclaration instanceof TextMarkerVariableDeclaration) {
      modifier = Modifiers.AccPrivate;
      modifier |= ((TextMarkerVariableDeclaration) fullDeclaration).getType();
    } else if (fullDeclaration instanceof TextMarkerBasicAnnotationType) {
      modifier = Modifiers.AccConstant;
      modifier |= TMTypeConstants.TM_TYPE_AT;
    } else if (fullDeclaration instanceof TextMarkerTypeDeclaration) {
      modifier = Modifiers.AccPublic;
      modifier |= TMTypeConstants.TM_TYPE_AT;
    }
    if (canAddVariables((ASTNode) this.fNodes.peek(), variableIDRef.getName())) {
      ISourceElementRequestor.FieldInfo info = new ISourceElementRequestor.FieldInfo();
      info.modifiers = modifier;
      info.name = variableIDRef.getName();
      info.nameSourceEnd = variableIDRef.sourceEnd() - 1;
      info.nameSourceStart = variableIDRef.sourceStart();
      info.declarationStart = variableIDRef.sourceStart();
      this.fRequestor.enterField(info);
      if (fullDeclaration != null) {
        this.fRequestor.exitField(fullDeclaration.sourceEnd());
      } else {
        this.fRequestor.exitField(variableIDRef.sourceEnd());
      }
    }
  }

  private boolean canAddVariables(ASTNode type, String name) {
    if (this.fTypeVariables.containsKey(type)) {
      List variables = (List) this.fTypeVariables.get(type);
      if (variables.contains(name)) {
        return false;
      }
      variables.add(name);
      return true;
    } else {
      List variables = new ArrayList();
      variables.add(name);
      this.fTypeVariables.put(type, variables);
      return true;
    }
  }

  @Override
  public boolean endvisit(Statement s) throws Exception {
    return true;
  }

  @Override
  public boolean visit(MethodDeclaration method) throws Exception {
    this.fNodes.push(method);
    List<Argument> args = method.getArguments();
    String[] parameter = new String[args.size()];
    for (int a = 0; a < args.size(); a++) {
      Argument arg = args.get(a);
      parameter[a] = arg.getName();
    }

    ISourceElementRequestor.MethodInfo mi = new ISourceElementRequestor.MethodInfo();
    mi.parameterNames = parameter;
    mi.name = method.getName();
    mi.modifiers = method.getModifiers();
    mi.nameSourceStart = method.getNameStart();
    mi.nameSourceEnd = method.getNameEnd() - 1;
    mi.declarationStart = method.sourceStart();

    this.fInMethod = true;
    this.fCurrentMethod = method;
    this.fRequestor.enterMethod(mi);
    return true;
  }

  @Override
  public boolean visit(ModuleDeclaration declaration) throws Exception {
    return super.visit(declaration);
  }
}
