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

import org.apache.uima.textmarker.ide.parser.ast.TextMarkerStatement;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.codeassist.complete.ICompletionOnKeyword;
import org.eclipse.dltk.utils.CorePrinter;

public class CompletionOnKeywordArgumentOrFunctionArgument extends SimpleReference implements
        ICompletionOnKeyword {

  private String[] possibleKeywords;

  private TextMarkerStatement statement;

  private ASTNode completionNode;

  public CompletionOnKeywordArgumentOrFunctionArgument(String token, ASTNode completionNode,
          TextMarkerStatement node, String[] KeywordspossibleKeywords) {
    super(completionNode.sourceStart(), completionNode.sourceEnd(), token);
    this.possibleKeywords = KeywordspossibleKeywords;
    this.statement = node;
    this.completionNode = completionNode;
  }

  public CompletionOnKeywordArgumentOrFunctionArgument(String token, TextMarkerStatement node,
          String[] possibleKeywords, int position) {
    super(position, position, token);
    this.possibleKeywords = possibleKeywords;
    this.statement = node;
    this.completionNode = null;
  }

  public char[] getToken() {
    if (getName() != null) {
      return getName().toCharArray();
    }
    return "".toCharArray();
  }

  public String[] getPossibleKeywords() {
    return this.possibleKeywords;
  }

  @Override
  public void printNode(CorePrinter output) {
  }

  @Override
  public void traverse(ASTVisitor pVisitor) throws Exception {
  }

  public boolean canCompleteEmptyToken() {
    return true;
  }

  public TextMarkerStatement getStatement() {
    return this.statement;
  }

  public int argumentIndex() {
    if (this.completionNode == null) {
      if (this.statement.getCount() == 1) {
        return 1;
      }
      if (statement.getCount() > 2 && statement.getAt(0).sourceEnd() <= sourceStart()
              && sourceEnd() <= statement.getAt(1).sourceStart()) {
        return 1;
      }
      return -1;
    }
    for (int i = 0; i < this.statement.getCount(); ++i) {
      if (this.statement.getAt(i).equals(this.completionNode)) {
        return i;
      }
    }
    return -1;
  }

  public ASTNode getCompletionNode() {
    return this.completionNode;
  }
}
