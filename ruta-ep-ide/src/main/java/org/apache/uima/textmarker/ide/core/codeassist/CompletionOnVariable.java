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

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.references.SimpleReference;

public class CompletionOnVariable extends SimpleReference {
  private ASTNode parentNode;

  private ASTNode inNode;

  private ASTNode completionNode;

  private boolean canHandleEmpty;

  private boolean provideDollar = true;

  public CompletionOnVariable(String completionToken, ASTNode completionNode, ASTNode node,
          ASTNode inNode, boolean canHandleEmpty) {
    super(completionNode.sourceStart(), completionNode.sourceEnd(), completionToken);
    this.parentNode = node;
    this.completionNode = completionNode;
    this.inNode = inNode;
    this.canHandleEmpty = canHandleEmpty;
  }

  public CompletionOnVariable(String completionToken, ASTNode completionNode, ASTNode node,
          ASTNode inNode, boolean canHandleEmpty, boolean provideDollar) {
    this(completionToken, completionNode, node, inNode, canHandleEmpty);
    this.provideDollar = provideDollar;
  }

  public ASTNode getParentNode() {
    return this.parentNode;
  }

  public ASTNode getInNode() {
    return this.inNode;
  }

  public ASTNode getCompletionNode() {
    return this.completionNode;
  }

  public char[] getToken() {
    return this.getName().toCharArray();
  }

  public boolean canHandleEmpty() {
    return this.canHandleEmpty;
  }

  public boolean getProvideDollar() {
    return this.provideDollar;
  }
}
