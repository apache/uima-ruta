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

package org.apache.uima.ruta.ide.validator;

import org.apache.uima.ruta.ide.parser.ast.ComponentDeclaration;
import org.apache.uima.ruta.ide.parser.ast.ComposedRuleElement;
import org.apache.uima.ruta.ide.parser.ast.FeatureMatchExpression;
import org.apache.uima.ruta.ide.parser.ast.RutaAction;
import org.apache.uima.ruta.ide.parser.ast.RutaCondition;
import org.apache.uima.ruta.ide.parser.ast.RutaPackageDeclaration;
import org.apache.uima.ruta.ide.parser.ast.RutaQuantifierLiteralExpression;
import org.apache.uima.ruta.ide.parser.ast.RutaRule;
import org.apache.uima.ruta.ide.parser.ast.RutaVariableReference;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.NumericLiteral;
import org.eclipse.dltk.ast.expressions.StringLiteral;
import org.eclipse.dltk.ast.references.SimpleReference;

public class XMLOutputVisitor extends ASTVisitor {

  private String document;

  public XMLOutputVisitor(String document) {
    super();
    this.document = document;
  }

  private StringBuilder sb = new StringBuilder();

  public boolean visitGeneral(ASTNode node) throws Exception {
    if (node instanceof RutaRule) {
      sb.append("<!-- " + document.substring(node.sourceStart(), node.sourceEnd()) + "-->\n");
    }
    sb.append("<");
    sb.append(node.getClass().getSimpleName());
    addAttributes(node);
    sb.append(">");
    sb.append("\n");
    return true;
  }

  private void addAttributes(ASTNode node) {

    if (node instanceof RutaQuantifierLiteralExpression) {
      RutaQuantifierLiteralExpression s = (RutaQuantifierLiteralExpression) node;
      sb.append(" value='");
      sb.append(s.getOperator());
      sb.append("'");
    }

    if (node instanceof ComposedRuleElement) {
      ComposedRuleElement s = (ComposedRuleElement) node;
      Boolean disjunctive = s.isDisjunctive();
      if(disjunctive != null) {
        sb.append(" value='");
        if(disjunctive) {
          sb.append("Disjunctive");
        } else {
          sb.append("Conjunctive");
        }
        sb.append("'");
      }
    }

    
    if (node instanceof FeatureMatchExpression) {
      FeatureMatchExpression s = (FeatureMatchExpression) node;
      sb.append(" feature='");
      sb.append(s.getFeature().getText());
      sb.append("'");
      if (s.getComparator() != null) {
        sb.append(" comparator='");
        sb.append(s.getComparator().getText());
        sb.append("'");
      }
    }

    if (node instanceof SimpleReference) {
      SimpleReference s = (SimpleReference) node;
      sb.append(" value='");
      sb.append(s.getStringRepresentation());
      sb.append("'");
    }
    if (node instanceof RutaPackageDeclaration) {

    }
    if (node instanceof ComponentDeclaration) {
      ComponentDeclaration s = (ComponentDeclaration) node;
      sb.append(" type='");
      sb.append(s.getType());
      sb.append("'");
    }
    if (node instanceof RutaVariableReference) {
      RutaVariableReference s = (RutaVariableReference) node;
      sb.append(" type='");
      sb.append(s.getType());
      sb.append("'");
    }
    if (node instanceof RutaAction) {
      RutaAction s = (RutaAction) node;
      sb.append(" type='");
      sb.append(s.getName());
      sb.append("'");
    }
    if (node instanceof RutaCondition) {
      RutaCondition s = (RutaCondition) node;
      sb.append(" type='");
      sb.append(s.getName());
      sb.append("'");
    }
    if (node instanceof StringLiteral) {
      StringLiteral s = (StringLiteral) node;
      sb.append(" value='");
      sb.append(s.getValue());
      sb.append("'");
    }
    if (node instanceof NumericLiteral) {
      NumericLiteral s = (NumericLiteral) node;
      sb.append(" value='");
      sb.append(s.getValue());
      sb.append("'");
    }

  }

  public void endvisitGeneral(ASTNode node) throws Exception {
    sb.append("</");
    sb.append(node.getClass().getSimpleName());
    sb.append(">");
    sb.append("\n");
  }

  public String getXML() {
    return sb.toString();
  }

}
