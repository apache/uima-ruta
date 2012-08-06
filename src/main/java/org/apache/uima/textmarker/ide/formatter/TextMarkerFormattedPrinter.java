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

package org.apache.uima.textmarker.ide.formatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.antlr.runtime.CommonToken;
import org.apache.uima.textmarker.ide.parser.ast.ComposedRuleElement;
import org.apache.uima.textmarker.ide.parser.ast.TMConditionConstants;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerAbstractDeclaration;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerAction;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerBinaryArithmeticExpression;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerBlock;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerBooleanNumberExpression;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerCondition;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerDeclarationsStatement;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerDeclareDeclarationsStatement;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerExpression;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerFeatureDeclaration;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerListExpression;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerLogAction;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerPackageDeclaration;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerRule;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerRuleElement;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerStringExpression;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerStructureAction;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerTypeDeclaration;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerVariableReference;
import org.eclipse.dltk.ast.ASTListNode;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.Declaration;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.expressions.StringLiteral;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.formatter.FormatterDocument;
import org.eclipse.dltk.formatter.IFormatterIndentGenerator;

/**
 * Formats a document.<br>
 * Use the append methods within the visit methods to preserve the comments.
 * 
 * 
 */
public class TextMarkerFormattedPrinter extends ASTVisitor {

  private FormatterDocument document;

  private String lineDelimiter;

  private static final String SEMI = ";";

  private static final String CONCAT = " + ";

  private static final String COMMA = ",";

  private static final int NL_DECLS_COUNT = 2;

  private static final String CURLY_OPEN = "{";

  private static final String CURLY_CLOSE = "}";

  private static final String BRACK_OPEN = "[";

  private static final String BRACK_CLOSE = "]";

  private static final String THEN = "->";

  private static final String PAR_OPEN = "(";

  private static final String PAR_CLOSE = ")";

  private static final String EQUALS = " = ";

  private IFormatterIndentGenerator indentGenerator;

  private StringBuilder output;

  private List<CommonToken> comments;

  private Iterator<CommonToken> iterator;

  private CommonToken currentComment;

  private int indentLevel = 0;

  private int lines_before_long_declarations;

  private int maxLineLength;

  private boolean inBlockDeclaration = false;

  private Map<Integer, Object> lastStatements = new HashMap<Integer, Object>();

  private int commentLineSince = 0;

  // hotfix: 0 = false, 1 = first rule element, 3 = not first rule element
  private int inLargeRule = 0;

  private boolean retainLB = true;

  public TextMarkerFormattedPrinter(FormatterDocument document, String lineDelimiter,
          IFormatterIndentGenerator indentGenerator, List<CommonToken> comments,
          TextMarkerFormatter tmf) {
    this.document = document;
    this.lineDelimiter = lineDelimiter;
    this.indentGenerator = indentGenerator;
    this.output = new StringBuilder();

    // read format preferences
    this.lines_before_long_declarations = tmf
            .getInt(TextMarkerFormatterConstants.LINES_BEFORE_LONG_DECLARATIONS);
    this.maxLineLength = tmf.getInt(TextMarkerFormatterConstants.MAX_LINE_LENGTH);

    // comments
    this.comments = comments == null ? new ArrayList<CommonToken>() : comments;
    iterator = this.comments.iterator();
    this.currentComment = iterator.hasNext() ? iterator.next() : null;
  }

  public String getOutput() {
    appendLeftComments();
    return this.output.toString();
  }

  @Override
  public boolean visit(Statement s) throws Exception {

    appendComments(s);
    fillNewLines(s);

    if (s instanceof TextMarkerDeclarationsStatement) {
      // append new lines before LONG DECLARATIONS
      TextMarkerDeclarationsStatement decls = (TextMarkerDeclarationsStatement) s;
      // if (decls.getDeclarationsCount() > NL_DECLS_COUNT) {
      // for (int i = 0; i < lines_before_long_declarations; i++) {
      // appendNewLine();
      // }
      // }
      // format declarations:
      // print type token
      appendIntoNewLine(document.get(decls.getTypeTokenStart(), decls.getTypeTokenEnd()) + " ");
      // print parent if available
      if (s instanceof TextMarkerDeclareDeclarationsStatement) {
        TextMarkerDeclareDeclarationsStatement dds = (TextMarkerDeclareDeclarationsStatement) s;
        ASTNode p = dds.getParent();
        if (p != null) {
          append(p);
          append(" ");
        }
      }
      // print identifiers
      List<TextMarkerAbstractDeclaration> declarations = decls.getDeclarations();
      traverseAstNodes(declarations);
      // print init expr
      if (decls.getInitExpr() != null) {
        append(EQUALS);
        decls.getInitExpr().traverse(this);
      }
      appendStatementEnd();
      return false;
    }
    if (s instanceof TextMarkerRule) {
      // traverse into container TextMarkerRule to format RuleElements
      if(!inBlockDeclaration) {
        appendNewLine();
      }
      // Rules always just consists of RuleElements: whitespace separation
      if (s.sourceEnd() - s.sourceStart() > 2 * maxLineLength) {
        inLargeRule = 1;
        indentLevel++;
      }
      TextMarkerRule rule = (TextMarkerRule) s;
      List<Expression> expressions = rule.getExpressions();
      traverseAstNodes(expressions, "");
      if(!inBlockDeclaration) {
        appendStatementEnd();
      }
      if (inLargeRule > 0) {
        indentLevel--;
        inLargeRule = 0;
      }
      return false;
      // return true;
    }
    // if (s instanceof TextMarkerVariableDeclaration && s.getKind() ==
    // TMTypeConstants.TM_TYPE_WL ||
    // s.getKind() == TMTC)
    // special format for declaration statements (they're wrapped in a
    // declarations-statement)
    if (s instanceof TextMarkerTypeDeclaration) {
      append(s);
      List<TextMarkerFeatureDeclaration> features = ((TextMarkerTypeDeclaration) s).getFeatures();
      if (features != null && !features.isEmpty()) {
        append(PAR_OPEN);
        for (TextMarkerFeatureDeclaration each : features) {
          append(each.getType());
          append(" ");
          append(each.getName());
          if (features.indexOf(each) < features.size() - 1) {
            append(COMMA + " ");
          }
        }
        append(PAR_CLOSE);
      }
      return false;
    }

    if (s instanceof Declaration && !(s instanceof TextMarkerPackageDeclaration)) {
      append(s);
      return false;
    }
    if (s instanceof TextMarkerPackageDeclaration) {
      append(s);
      appendStatementEnd();
      return false;
    }
    // append SEMIs for all other statements
    appendIntoNewLine(s);
    appendStatementEnd();
    return false;
  }

  private void fillNewLines(Object s) {
    Object last = lastStatements.get(indentLevel);
    // if (last == null && indentLevel > 0) {
    // last = lastStatements.get(indentLevel - 1);
    // }
    if (last != null && retainLB) {
      int start = 0;
      int end = 0;
      if (s instanceof Statement) {
        end = ((ASTNode) s).sourceStart();
      } else if (s instanceof CommonToken) {
        end = ((CommonToken) s).getStartIndex();
      }
      if (last instanceof Statement) {
        start = ((ASTNode) last).sourceEnd() + 1;
      } else if (last instanceof CommonToken) {
        start = ((CommonToken) last).getStopIndex();
      }

      if (start < end) {
        String string = document.get(start, end);
        String replaceAll = string.replaceAll(lineDelimiter, "");
        double d = (string.length() - replaceAll.length()) / lineDelimiter.length()
                - commentLineSince;
        if (s instanceof CommonToken && commentLineSince == 0) {
          d++;
        }
        for (int i = 1; i < d; i++) {
          appendNewLine();
        }
        if (d < 2 && s instanceof TextMarkerDeclarationsStatement && !(last instanceof Declaration)) {
          appendNewLine();
        }
        commentLineSince = 0;
      }
    } else if(inBlockDeclaration || s instanceof CommonToken){
      appendNewLine();
    }
    lastStatements.put(indentLevel, s);
  }

  @Override
  public boolean endvisit(Statement s) throws Exception {
    // if (s instanceof TextMarkerRule) {
    // append(SEMI);
    // return true;
    // }
    return super.endvisit(s);
  }

  @Override
  public boolean visit(Expression s) throws Exception {
    // traverse Block (first child of root element:
    if (s instanceof Block) {
      return true;
    }
    if (s instanceof ComposedRuleElement) {
      ComposedRuleElement cre = (ComposedRuleElement) s;
      List<Expression> elements = cre.getElements();
      // int length = cre.sourceEnd() - cre.sourceStart();
      if (inLargeRule == 2) {
        inLargeRule = 4;
      }
      append(PAR_OPEN);
      traverseAstNodes(elements, cre.isDisjunctive() ? " |" : "");
      append(PAR_CLOSE);
      appendRuleElement(cre);
      if (inLargeRule == 4) {
        inLargeRule = 1;
      }
      return false;
    }
    // special format for RuleElements:
    if (s instanceof TextMarkerRuleElement) {
      TextMarkerRuleElement ruleEl = (TextMarkerRuleElement) s;
      if (inLargeRule == 2) {
        appendNewLine();
      } else if (inLargeRule == 1) {
        inLargeRule = 2;
      }
      appendRuleElement(ruleEl);
      return false;
    }
    // special format for actions
    if (s instanceof TextMarkerAction) {
      TextMarkerAction a = (TextMarkerAction) s;
      String name = document.get(a.getNameStart(), a.getNameEnd());
      append(name);
      List<? extends ASTNode> childs = a.getChilds();
      if (childs != null && !childs.isEmpty()) {
        append(PAR_OPEN);
        // special format for create
        if (a instanceof TextMarkerStructureAction) {
          if (name.equals("TRIE")) {
            printStructureAction2(a);
          } else {
            printStructureAction(a);
          }
        } else {
          traverseAstNodes(childs);
        }
        // special format for log
        if (a instanceof TextMarkerLogAction && ((TextMarkerLogAction) a).isLogLevelAssigned()) {
          appendSeparator(COMMA);
          TextMarkerLogAction logAction = (TextMarkerLogAction) a;
          append(logAction.getLogLevelStart(), logAction.getLogLevelEnd());
        }
        append(PAR_CLOSE);
      }
      return false;
    }
    // special format for conditions
    if (s instanceof TextMarkerCondition) {
      TextMarkerCondition c = (TextMarkerCondition) s;
      append(document.get(c.getNameStart(), c.getNameEnd()));
      List<? extends ASTNode> childs = c.getChilds();
      // minus is a condition without parameter parantheses:
      if (s.getKind() != TMConditionConstants.COND_MINUS && childs != null && !childs.isEmpty()) {
        append(PAR_OPEN);
      }
      traverseAstNodes(childs);
      if (s.getKind() != TMConditionConstants.COND_MINUS && childs != null && !childs.isEmpty()) {
        append(PAR_CLOSE);
      }
      return false;
    }
    // special format for boolean number expressions
    if (s instanceof TextMarkerBooleanNumberExpression) {
      TextMarkerBooleanNumberExpression tmbne = (TextMarkerBooleanNumberExpression) s;
      append(PAR_OPEN);
      if (tmbne.getE1() != null) {
        tmbne.getE1().traverse(this);
      }
      append(tmbne.getOperator());
      if (tmbne.getE2() != null) {
        tmbne.getE2().traverse(this);
      }
      append(PAR_CLOSE);
      return false;
    }
    // special format for string expressions
    if (s instanceof TextMarkerStringExpression
            && ((TextMarkerExpression) s).getExpression() != null) {
      TextMarkerStringExpression tmse = (TextMarkerStringExpression) s;
      List<?> childs = tmse.getExpression().getChilds();
      Object object2 = childs.get(0);
      if (object2 instanceof ASTNode) {
        ASTNode astnode = (ASTNode) object2;
        List<?> childs2 = astnode.getChilds();
        for (Object object : childs2) {
          if (object instanceof TextMarkerExpression) {
            TextMarkerExpression expr = (TextMarkerExpression) object;
            // if (expr.isInParantheses()) {
            // append(PAR_OPEN);
            // append(expr);
            // append(PAR_CLOSE);
            // } else {
            append(expr);
            // }
          } else if (object instanceof StringLiteral) {
            StringLiteral sl = (StringLiteral) object;
            String value = sl.getValue();
            append(value);
          } else if (object instanceof TextMarkerVariableReference) {
            TextMarkerVariableReference vr = (TextMarkerVariableReference) object;
            append(vr.getName());
          }
          if (childs2.indexOf(object) < childs2.size() - 1) {
            append(CONCAT);
          }
        }
      } else {
        append(s);
      }
      return false;
    }
    if (s instanceof TextMarkerListExpression) {
      TextMarkerListExpression le = (TextMarkerListExpression) s;
      append(CURLY_OPEN);
      ASTListNode exprs = le.getExprs();
      traverseAstNodes(exprs.getChilds());
      append(CURLY_CLOSE);
      return false;
    }

    // special format for paranthesed expressions: (expression)
    // if (s instanceof TextMarkerExpression && ((TextMarkerExpression) s).isInParantheses()) {
    // append(PAR_OPEN);
    // append(s);
    // append(PAR_CLOSE);
    // return false;
    // }
    if (s instanceof TextMarkerBinaryArithmeticExpression) {
      TextMarkerBinaryArithmeticExpression ba = (TextMarkerBinaryArithmeticExpression) s;
      String operator = ba.getOperator();
      append(operator);
      append(PAR_OPEN);
      traverseAstNodes(ba.getChilds());
      append(PAR_CLOSE);
    }
    append(s);
    return false;
  }

  /**
   * structure actions like CREATE, FILL with assignments.
   * 
   * @param a
   */
  private void printStructureAction(TextMarkerAction a) {
    TextMarkerStructureAction tmca = (TextMarkerStructureAction) a;
    // structure
    if (tmca.getStructure() != null) {
      append(tmca.getStructure());
    }
    append(COMMA);
    append(" ");
    // number expressions
    List<Expression> indices = tmca.getExpressions();
    if (indices != null) {
      traverseAstNodes(indices);
    }
    if (!indices.isEmpty()) {
      append(COMMA);
      append(" ");
    }
    // assignments
    Map<Expression, Expression> assignments = tmca.getAssignments();
    Iterator it = assignments.entrySet().iterator();
    while (it.hasNext()) {
      if (assignments.size() > 3) {
        appendNewLineAndIndent();
      }
      Map.Entry pairs = (Map.Entry) it.next();
      try {
        ((Expression) pairs.getKey()).traverse(this);
      } catch (Exception e) {
        e.printStackTrace();
      }
      append(EQUALS);
      try {
        ((Expression) pairs.getValue()).traverse(this);
      } catch (Exception e) {
        e.printStackTrace();
      }
      if (it.hasNext()) {
        output.append(COMMA);
        append(" ");
      }
    }
  }

  private void printStructureAction2(TextMarkerAction a) {
    TextMarkerStructureAction tmca = (TextMarkerStructureAction) a;

    Map<Expression, Expression> assignments = tmca.getAssignments();
    Iterator it = assignments.entrySet().iterator();
    while (it.hasNext()) {
      if (assignments.size() > 3) {
        appendNewLineAndIndent();
      }
      Map.Entry pairs = (Map.Entry) it.next();
      try {
        ((Expression) pairs.getKey()).traverse(this);
      } catch (Exception e) {
        e.printStackTrace();
      }
      append(EQUALS);
      try {
        ((Expression) pairs.getValue()).traverse(this);
      } catch (Exception e) {
        e.printStackTrace();
      }
      output.append(COMMA);
      append(" ");
    }
    // structure
    if (tmca.getStructure() != null) {
      append(tmca.getStructure());
    }
    append(COMMA);
    append(" ");
    // number expressions
    List<Expression> indices = tmca.getExpressions();
    if (indices != null) {
      traverseAstNodes(indices);
    }
    // assignments

  }

  @Override
  public boolean endvisit(Expression s) throws Exception {
    return super.endvisit(s);
  }

  /**
   * @param ruleEl
   * @param conditions
   * @param actions
   */
  private void appendRuleElement(TextMarkerRuleElement ruleEl) {
    // if (ruleEl instanceof ComposedRuleElement) {
    // ComposedRuleElement cre = (ComposedRuleElement) ruleEl;
    // List<Expression> elements = cre.getElements();
    // append(PAR_OPEN);
    // for (Expression expression : elements) {
    //
    // if (expression instanceof TextMarkerRuleElement) {
    // TextMarkerRuleElement re = (TextMarkerRuleElement) expression;
    // appendRuleElement(re);
    // }
    // if (elements.indexOf(expression) < elements.size() - 1) {
    // append(" ");
    // }
    //
    // }
    // append(PAR_CLOSE);
    // } else
    if (ruleEl.getHead() != null) {
      append(ruleEl.getHead());
    }
    List<TextMarkerCondition> conditions = ruleEl.getConditions();
    List<TextMarkerAction> actions = ruleEl.getActions();
    // don't print {->} for empty rule elements

    // print Quantifiers
    List<? extends ASTNode> quantifierExpressions = ruleEl.getQuantifierExpressions();
    if (quantifierExpressions != null && !quantifierExpressions.isEmpty()) {
      if (quantifierExpressions.size() > 1) {
        append(BRACK_OPEN);
        for (ASTNode expression : quantifierExpressions) {
          append(expression);
          if (quantifierExpressions.indexOf(expression) < quantifierExpressions.size() - 1) {
            append(COMMA);
          }
        }
        append(BRACK_CLOSE);
      } else if (quantifierExpressions.size() == 1) {
        append(quantifierExpressions.get(0));
      }
    }
    if (!inBlockDeclaration && conditions == null && actions == null) {
      return;
    }

    if (!inBlockDeclaration
            && ((conditions == null && actions.isEmpty()) || actions == null
                    && (conditions != null) && conditions.isEmpty())) {
      return;
    }
    append(CURLY_OPEN);
    // print Conditions
    if (conditions != null && !conditions.isEmpty()) {
      traverseAstNodes(conditions);
    }
    if (conditions != null && !conditions.isEmpty()) {
    }
    // print Actions
    if (actions != null && !actions.isEmpty()) {
      if (conditions != null && !conditions.isEmpty()) {
        append(" " + THEN + " ");
      } else {
        append(THEN + " ");
      }
      traverseAstNodes(actions);
    }
    append(CURLY_CLOSE);
  }

  /**
   * @param astnodes
   */
  @SuppressWarnings("unchecked")
  private void traverseAstNodes(List<? extends ASTNode> astnodes) {
    traverseAstNodes(astnodes, COMMA);
  }

  /**
   * @param astnodes
   */
  @SuppressWarnings("unchecked")
  private void traverseAstNodes(List<? extends ASTNode> astnodes, String separator) {
    if (astnodes == null) {
      return;
    }
    Iterator iterator2 = astnodes.iterator();
    while (iterator2.hasNext()) {
      ASTNode node = (ASTNode) iterator2.next();
      try {
        node.traverse(this);
      } catch (Exception e) {
        e.printStackTrace();
      }
      if (iterator2.hasNext()) {
        appendSeparator(separator);
      }
    }
  }

  private void appendSeparator(String separator) {
    output.append(separator);
    append(" ");
  }

  @Override
  public boolean visit(MethodDeclaration s) throws Exception {
    appendComments(s);
    fillNewLines(s);

    // Statement last = lastStatements.get(indentLevel);
    // // if (last == null && indentLevel > 0) {
    // // last = lastStatements.get(indentLevel - 1);
    // // }
    // if (last != null && retainLB) {
    // int start = last.sourceEnd() + 1;
    // int end = s.sourceStart();
    // if (start < end) {
    // String string = document.get(start, end);
    // String replaceAll = string.replaceAll(lineDelimiter, "");
    // double d = (string.length() - replaceAll.length()) / lineDelimiter.length()
    // - commentLineSince;
    // for (int i = 1; i < d; i++) {
    // appendNewLine();
    // }
    // commentLineSince = 0;
    // }
    // }

    if (s instanceof TextMarkerBlock) {
      TextMarkerBlock b = (TextMarkerBlock) s;
      appendIntoNewLine("BLOCK(");
      append(b.getName());
      append(") ");
      this.inBlockDeclaration = true;
      if (b.getRule() != null) {
        b.getRule().traverse(this);
      }
      this.inBlockDeclaration = false;
      append(" {");
      indentLevel++;
      b.getBody().traverse(this);
      lastStatements.put(indentLevel, null);
      indentLevel--;
      lastStatements.put(indentLevel, s);
      appendIntoNewLine("}");
      appendNewLine();
      return false;
    }
    return super.visit(s);
  }

  @Override
  public boolean visit(ModuleDeclaration s) throws Exception {
    return true;
  }

  @Override
  public boolean visit(TypeDeclaration s) throws Exception {
    return super.visit(s);
  }

  @Override
  public boolean visitGeneral(ASTNode node) throws Exception {
    return super.visitGeneral(node);
  }

  private void append(int begin, int end) {
    append(document.get(begin, end));
  }

  private void append(String string) {
    if (outputPosInLine() + string.length() > maxLineLength) {
      appendNewLine();
      indentGenerator.generateIndent(1, output);
    }
    output.append(string);
  }

  private void appendIntoNewLine(String string) {
    appendNewLine();
    output.append(string);
  }

  private void append(ASTNode s) {
    appendComments(s);
    // this.output.
    append(document.get(s.sourceStart(), s.sourceEnd()));
  }

  private void appendIntoNewLine(ASTNode s) {
    appendComments(s);
    appendNewLine();
    append(s);
  }

  private void appendNewLine() {
    output.append(lineDelimiter);
    indentGenerator.generateIndent(indentLevel, output);
  }

  private void appendNewLineAndIndent() {
    output.append(lineDelimiter);
    indentGenerator.generateIndent(indentLevel + 1, output);
  }

  private void appendStatementEnd() {
    output.append(SEMI);
  }

  /**
   * @param s
   * @return
   */
  private int appendComments(ASTNode s) {
    return appendComments(s.sourceStart());
  }

  /**
   * @param start
   * @return
   */
  private int appendComments(int start) {
    while (currentComment != null && currentComment.getStartIndex() < start) {
      String text = currentComment.getText().trim();
      fillNewLines(currentComment);
      append("" + text);
      currentComment = iterator.hasNext() ? iterator.next() : null;
      commentLineSince++;
    }
    return start;
  }

  private void appendLeftComments() {
    while (currentComment != null) {
      append(currentComment.getText());
      currentComment = iterator.hasNext() ? iterator.next() : null;
    }
  }

  private int outputPosInLine() {
    String out = output.toString();
    int lastIndexOf = out.lastIndexOf("\n");
    return out.length() - lastIndexOf;
  }

}
