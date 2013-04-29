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

package org.apache.uima.textmarker.ide.core.codeassist;

import org.apache.uima.textmarker.ide.parser.ast.ComponentDeclaration;
import org.apache.uima.textmarker.ide.parser.ast.ComponentReference;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerAction;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerCondition;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerExpression;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerVariableReference;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;


public class TextMarkerReferenceVisitor extends ASTVisitor {
  private ASTNode result = null;

  private int start = 0;

  public TextMarkerReferenceVisitor(int start) {
    this.start = start;
  }

  @Override
  public boolean visitGeneral(ASTNode node) throws Exception {
    if (result == null && node.sourceStart() <= start && start <= node.sourceEnd()) {
      return true;
    }
    return false;
  }

  @Override
  public boolean visit(Expression s) throws Exception {
    if (result != null) {
      return false;
    }
    if (s instanceof TextMarkerVariableReference || s instanceof ComponentDeclaration
            || s instanceof ComponentReference) {
      result = s;
    } else if (s instanceof TextMarkerAction && ((TextMarkerAction) s).getNameStart() <= start
            && start <= ((TextMarkerAction) s).getNameEnd()) {
      result = s;
    } else if (s instanceof TextMarkerAction && ((TextMarkerAction) s).getNameStart()-1 == start
            && start == ((TextMarkerAction) s).getNameEnd()-1) {
      result = s;
    } else if (s instanceof TextMarkerCondition
            && ((TextMarkerCondition) s).getNameStart() <= start
            && start <= ((TextMarkerCondition) s).getNameEnd()) {
      result = s;
    } else if (s instanceof TextMarkerCondition
            && ((TextMarkerCondition) s).getNameStart()-1 == start
            && start == ((TextMarkerCondition) s).getNameEnd()-1) {
      result = s;
    } else if(s instanceof TextMarkerExpression && s.sourceStart() == start && s.sourceEnd() == start) {
      result = s;
    }
    return super.visit(s);
  }

  public ASTNode getResult() {
    return result;
  }
}
