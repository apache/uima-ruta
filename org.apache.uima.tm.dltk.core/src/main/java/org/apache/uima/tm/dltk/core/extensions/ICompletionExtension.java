package org.apache.uima.tm.dltk.core.extensions;

import java.util.Set;

import org.apache.uima.tm.dltk.internal.core.codeassist.TextMarkerCompletionEngine;
import org.apache.uima.tm.dltk.internal.core.codeassist.completion.CompletionOnKeywordArgumentOrFunctionArgument;
import org.apache.uima.tm.dltk.internal.core.codeassist.completion.CompletionOnKeywordOrFunction;
import org.apache.uima.tm.dltk.internal.core.codeassist.completion.TextMarkerCompletionParser;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.core.CompletionRequestor;


public interface ICompletionExtension {
  boolean visit(Expression s, TextMarkerCompletionParser parser, int position);

  boolean visit(Statement s, TextMarkerCompletionParser parser, int position);

  void completeOnKeywordOrFunction(CompletionOnKeywordOrFunction key, ASTNode astNodeParent,
          TextMarkerCompletionEngine engine);

  void completeOnKeywordArgumentsOne(String name,
          CompletionOnKeywordArgumentOrFunctionArgument compl, Set methodNames, Statement st,
          TextMarkerCompletionEngine tmCompletionEngine);

  void setRequestor(CompletionRequestor requestor);
}
