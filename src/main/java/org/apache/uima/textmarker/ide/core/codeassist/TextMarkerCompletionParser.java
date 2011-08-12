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

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.textmarker.ide.core.extensions.ICompletionExtension;
import org.apache.uima.textmarker.ide.core.parser.TextMarkerParseUtils;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerStatement;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.codeassist.complete.CompletionNodeFound;

public class TextMarkerCompletionParser extends TextMarkerAssistParser {
  private static class TextMarkerEmptyCompleteStatement extends TextMarkerStatement {
    public TextMarkerEmptyCompleteStatement(List expressions) {
      super(expressions);
    }
  }

  private ICompletionExtension[] extensions;

  public TextMarkerCompletionParser(ICompletionExtension[] extensions) {
    this.extensions = extensions;
  }

  /**
   * Called then element could not be found.
   */
  public void handleNotInElement(ASTNode node, int position) {
    if (node != null && node.sourceStart() <= position && position <= node.sourceEnd()) {
      // this is empty module case
      if (node instanceof ModuleDeclaration) {
        ModuleDeclaration unit = (ModuleDeclaration) node;
        List exprs = new ArrayList();
        exprs.add(new SimpleReference(position, position, ""));
        TextMarkerEmptyCompleteStatement statement = new TextMarkerEmptyCompleteStatement(exprs);
        unit.addStatement(statement);
        this.parseBlockStatements(statement, unit, position);
      } else if (node instanceof MethodDeclaration) {
        // empty keyword like completion.
        MethodDeclaration method = (MethodDeclaration) node;
        List exprs = new ArrayList();
        exprs.add(new SimpleReference(position, position, ""));
        TextMarkerEmptyCompleteStatement statement = new TextMarkerEmptyCompleteStatement(exprs);
        method.getStatements().add(statement);
        this.parseBlockStatements(statement, method, position);
      } else if (node instanceof TypeDeclaration) {
        // empty keyword like completion.
        TypeDeclaration type = (TypeDeclaration) node;
        List exprs = new ArrayList();
        exprs.add(new SimpleReference(position, position, ""));
        TextMarkerEmptyCompleteStatement statement = new TextMarkerEmptyCompleteStatement(exprs);
        type.getStatements().add(statement);
        // this.assistNodeParent = node;
        this.parseBlockStatements(statement, type, position);
        // } else if (node instanceof TextMarkerExecuteExpression) {
        // // empty keyword like completion.
        // List exprs = new ArrayList();
        // exprs.add(new SimpleReference(position, position, ""));
        // TextMarkerEmptyCompleteStatement statement = new
        // TextMarkerEmptyCompleteStatement(
        // exprs);
        // this.parseBlockStatements(statement, node, position);
      }
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.dltk.codeassist.IAssistParser#parseBlockStatements(org.eclipse.dltk.ast.ASTNode,
   * org.eclipse.dltk.ast.ASTNode, int)
   */
  public void parseBlockStatements(ASTNode node, ASTNode inNode, int position) {
    // if (node instanceof TextMarkerStatement) {
    // TextMarkerStatement statement = (TextMarkerStatement) node;
    // List expressions = statement.getExpressions();
    // int len = expressions.size();
    // boolean first = false;
    // String completionToken = null;
    // ASTNode completionNode = null;
    // for (int i = 0; i < len; ++i) {
    // ASTNode n = (ASTNode) expressions.get(i);
    // if (n.sourceStart() <= position && n.sourceEnd() >= position
    // || (node instanceof TextMarkerEmptyCompleteStatement)) {
    // if (i == 0) {
    // first = true;
    // }
    // completionNode = n;
    // }
    // }
    // if (completionNode == null) {
    // // TODO: Add inner completion here.
    // if (len > 0) {
    // // ASTNode firstNode = (ASTNode) expressions.get(0);
    // // if (position > firstNode.sourceEnd()) {
    // // // This could be variable completion.
    // // boolean provideDollar =
    // // !checkVariableWithoutDollarCompletion(
    // // statement, position);
    // // this.assistNodeParent = inNode;
    // // SimpleReference ref = new SimpleReference(position,
    // // position, "");
    // // ASTNode nde = new CompletionOnVariable("", ref, node,
    // // inNode, true, provideDollar);
    // // throw new CompletionNodeFound(nde, null);
    // // }
    // String[] keywords = checkKeywords(completionToken, MODULE);
    // ASTNode nde = new CompletionOnKeywordArgumentOrFunctionArgument("",
    // (TextMarkerStatement) node, keywords, position);
    // this.assistNodeParent = inNode;
    // throw new CompletionNodeFound(nde, null/* ((TypeDeclaration)inNode).scope */);
    //
    // } else {
    // completionToken = "";
    // }
    // } else if (completionNode instanceof SimpleReference) {
    // int maxLen = position - completionNode.sourceStart();
    // completionToken = ((SimpleReference) completionNode).getName();
    // // We need to cut some sumbols if node is begger then position.
    // if (completionToken.length() > maxLen && maxLen > 0) {
    // completionToken = completionToken.substring(0, maxLen);
    // }
    // } /*
    // * else if (completionNode instanceof TextMarkerBlockExpression) { TextMarkerBlockExpression
    // * block = (TextMarkerBlockExpression) completionNode;
    // *
    // * List s = block.parseBlock(); if (s != null) { int slen = s.size(); for (int u = 0; u <
    // * slen; ++u) { ASTNode n = (ASTNode) s.get(u); n.setStart(n.sourceStart() -
    // * block.sourceStart()); n.setEnd(n.sourceEnd() - block.sourceStart());
    // * TextMarkerASTUtil.extendStatement(n, block.getBlock()); n.setStart(n.sourceStart() +
    // * block.sourceStart()); n.setEnd(n.sourceEnd() + block.sourceStart()); if (n != null &&
    // * n.sourceStart() <= position && n.sourceEnd() >= position) { parseBlockStatements(n,
    // * inNode, position); } } } handleNotInElement(inNode, position); }
    // */
    // if (completionNode instanceof StringLiteral) {
    // int maxLen = position - completionNode.sourceStart();
    // int pos = maxLen;
    // SimpleReference tok = TextMarkerParseUtils.extractVariableFromString(
    // (StringLiteral) completionNode, pos);
    // if (tok != null) {
    // this.assistNodeParent = inNode;
    // ASTNode nde = new CompletionOnVariable(tok.getName(), tok, node, inNode, false);
    // throw new CompletionNodeFound(nde, null);
    // } else {
    // this.assistNodeParent = inNode;
    // SimpleReference ref = new SimpleReference(position, position, "");
    // ASTNode nde = new CompletionOnVariable("", ref, node, inNode, true);
    // throw new CompletionNodeFound(nde, null);
    // }
    // }
    // // if (completionNode instanceof TextMarkerExecuteExpression) {
    // // TextMarkerExecuteExpression expr = (TextMarkerExecuteExpression)
    // // completionNode;
    // // List exprs = expr.parseExpression();
    // // for (int i = 0; i < exprs.size(); ++i) {
    // // ASTNode n = (ASTNode) exprs.get(i);
    // // if (n.sourceStart() <= position
    // // && n.sourceEnd() >= position) {
    // // parseBlockStatements(n, expr, position);
    // // }
    // // }
    // // handleNotInElement(expr, position);
    // // }
    // if (completionToken != null && completionToken.startsWith("$")) {
    // // Argument name completion...
    // this.assistNodeParent = inNode;
    // ASTNode nde = new CompletionOnVariable(completionToken, completionNode, node, inNode, false);
    // throw new CompletionNodeFound(nde, null);
    // } else {
    // // This is keyword or function completion.
    // if (inNode instanceof ModuleDeclaration && completionNode != null && first) {
    // String[] keywords = checkKeywords(completionToken, MODULE);
    // ASTNode nde = new CompletionOnKeywordOrFunction(completionToken, completionNode, node,
    // keywords);
    // this.assistNodeParent = inNode;
    // throw new CompletionNodeFound(nde, ((ModuleDeclaration) inNode).scope);
    // } else if (inNode instanceof MethodDeclaration && completionNode != null && first) {
    // String[] keywords = checkKeywords(completionToken, FUNCTION);
    // ASTNode nde = new CompletionOnKeywordOrFunction(completionToken, completionNode, node,
    // keywords);
    // this.assistNodeParent = inNode;
    // throw new CompletionNodeFound(nde, ((MethodDeclaration) inNode).scope);
    // } else if (inNode instanceof TypeDeclaration && completionNode != null && first) {
    // String[] keywords = checkKeywords(completionToken, NAMESPACE);
    // ASTNode nde = new CompletionOnKeywordOrFunction(completionToken, completionNode, node,
    // keywords);
    // this.assistNodeParent = inNode;
    // throw new CompletionNodeFound(nde, null/* ((TypeDeclaration)inNode).scope */);
    // // } else if (inNode instanceof TextMarkerExecuteExpression
    // // && completionNode != null && first) {
    // // String[] keywords = checkKeywords(completionToken,
    // // EXEC_EXPRESSION);
    // // ASTNode nde = new CompletionOnKeywordOrFunction(
    // // completionToken, completionNode, node, keywords);
    // // this.assistNodeParent = inNode;
    // // throw new CompletionNodeFound(nde, null/*
    // // ((TypeDeclaration)inNode).scope */);
    // } else {
    // if (completionNode != null) {
    // String[] keywords = checkKeywords(completionToken, MODULE);
    //
    // ASTNode nde = new CompletionOnKeywordArgumentOrFunctionArgument(completionToken,
    // completionNode, (TextMarkerStatement) node, keywords);
    // this.assistNodeParent = inNode;
    // throw new CompletionNodeFound(nde, null/* ((TypeDeclaration)inNode).scope */);
    // } else {
    // String[] keywords = checkKeywords(completionToken, MODULE);
    // if (completionToken == null) {
    // completionToken = "";
    // }
    // ASTNode nde = new CompletionOnKeywordArgumentOrFunctionArgument(completionToken,
    // (TextMarkerStatement) node, keywords, position);
    // this.assistNodeParent = inNode;
    // throw new CompletionNodeFound(nde, null/* ((TypeDeclaration)inNode).scope */);
    // }
    // }
    // }
    // // if (checkVariableWithoutDollarCompletion(statement, position)
    // // && completionToken != null) {
    // // this.assistNodeParent = inNode;
    // // SimpleReference ref = new SimpleReference(completionNode
    // // .sourceStart(), completionNode.sourceEnd(),
    // // completionToken);
    // // ASTNode nde = new CompletionOnVariable(completionToken, ref,
    // // node, inNode, true);
    // // throw new CompletionNodeFound(nde, null);
    // // }
    //
    // } else if (node instanceof MethodDeclaration) {
    // MethodDeclaration method = (MethodDeclaration) node;
    // List statements = method.getStatements();
    // boolean inStatement = false;
    // if (statements != null) {
    // int length = statements.size();
    // for (int i = 0; i < length; i++) {
    // ASTNode nde = (ASTNode) statements.get(i);
    // if (nde.sourceStart() <= position && nde.sourceEnd() >= position) {
    // inStatement = true;
    // parseBlockStatements(nde, method, position);
    // }
    // }
    // }
    // if (!inStatement) {
    // this.handleNotInElement(method, position);
    // }
    // } else {
    // visitElements(node, position);
    // }
  }

  public class CompletionVisitor extends ASTVisitor {
    protected int position;

    protected ModuleDeclaration module;

    public CompletionVisitor(int position, ModuleDeclaration module) {
      this.position = position;
      this.module = module;
    }

    @Override
    public boolean visit(Statement s) throws Exception {
      // if (s.sourceStart() <= position && s.sourceEnd() >= position) {
      // for (int i = 0; i < extensions.length; i++) {
      // extensions[i].visit(s, TextMarkerCompletionParser.this,
      // position);
      // }
      // if (s instanceof TextMarkerStatement) {
      // ASTNode inNode = TextMarkerParseUtil.getScopeParent(module, s);
      // TextMarkerCompletionParser.this.parseBlockStatements(s, inNode,
      // position);
      // }
      // if (s instanceof TextMarkerPackageDeclaration) {
      // TextMarkerPackageDeclaration decl =
      // (TextMarkerPackageDeclaration) s;
      // if (decl.getNameStart() <= position
      // && position <= decl.getNameEnd()) {
      // ASTNode inNode = TextMarkerParseUtil.getScopeParent(module, s);
      // assistNodeParent = inNode;
      // throw new CompletionNodeFound(decl, null/*
      // ((TypeDeclaration)inNode).scope */);
      // }
      // }
      // }
      return super.visit(s);
    }

    @Override
    public boolean visit(Expression s) throws Exception {
      if (s.sourceStart() <= position && s.sourceEnd() >= position) {
        for (int i = 0; i < extensions.length; i++) {
          extensions[i].visit(s, TextMarkerCompletionParser.this, position);
        }
      }
      return super.visit(s);
    }

    @Override
    public boolean endvisit(Expression s) throws Exception {
      if (s instanceof Block && s.sourceStart() <= position && s.sourceEnd() >= position) {
        // We are in block, and no in node completion are done.
        String[] keywords = checkKeywords("", MODULE);
        ASTNode inNode = TextMarkerParseUtils.getScopeParent(module, s);
        ASTNode nde = new CompletionOnKeywordOrFunction("", inNode, s, keywords);
        assistNodeParent = inNode;
        throw new CompletionNodeFound(nde, null);
      }
      return super.endvisit(s);
    }
  };

  protected CompletionVisitor createCompletionVisitor(int position) {
    return new CompletionVisitor(position, this.getModule());
  }

  private void visitElements(ASTNode node, int position) {
    if (!(node instanceof TextMarkerStatement)) {
      CompletionVisitor visitor = createCompletionVisitor(position);
      try {
        node.traverse(visitor);
      } catch (CompletionNodeFound e) {
        throw e;
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  private boolean checkVariableWithoutDollarCompletion(TextMarkerStatement statement, int position) {
    // TODO: Add more compecated check.
    Expression e = statement.getAt(0);
    if (e instanceof SimpleReference) {
      SimpleReference ref = (SimpleReference) e;
      String name = ref.getName();
      if (name.equals("set")) {
        return true;
      }
    }
    return false;
  }

  public String[] checkKeywords(String completionToken, int type) {
    // String[] keywords = TextMarkerKeywordsManager.getKeywords(type);
    // // TODO: Possible require cases.
    // if (type == MODULE || type == FUNCTION || type == NAMESPACE
    // || type == EXEC_EXPRESSION) {
    // // Suppose we can handle all keywords.
    // String[] kw = new String[keywords.length];
    // for (int i = 0; i < keywords.length; ++i) {
    // kw[i] = keywords[i];
    // }
    // return kw;
    // }
    return null;
  }

  public void setAssistNodeParent(ASTNode prevParent) {
    this.assistNodeParent = prevParent;
  }
}
