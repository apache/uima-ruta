/**
 * 
 */
package org.apache.uima.tm.dltk.validator;

import org.apache.uima.tm.dltk.parser.ast.declarations.TextMarkerAbstractDeclaration;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.compiler.problem.DefaultProblem;
import org.eclipse.dltk.compiler.problem.ProblemSeverities;


/**
 * @author Martin Toepfer
 * 
 */
public class TextMarkerCheckerDefaultProblem extends DefaultProblem {
  public static final int WARNING = ProblemSeverities.Warning;

  public static final int ERROR = ProblemSeverities.Error;

  // public TextMarkerCheckerDefaultProblem(String message, int id, String[]
  // stringArguments,
  // int severity, int start, int end) {
  // super(message, id, stringArguments, severity, start,
  // end, 10);
  // }

  public TextMarkerCheckerDefaultProblem(String fileName, String message,
          TextMarkerAbstractDeclaration node, int line, int severity) {
    super(fileName, message, 0, new String[] {}, severity, node.getNameStart(), node.getNameEnd(),
            line);
  }

  public TextMarkerCheckerDefaultProblem(String fileName, String message,
          TextMarkerAbstractDeclaration node, int line) {
    super(fileName, message, 0, new String[] {}, ERROR, node.getNameStart(), node.getNameEnd(),
            line);
  }

  public TextMarkerCheckerDefaultProblem(String fileName, String message, ASTNode node, int line) {
    this(fileName, message, node, line, ERROR);
  }

  public TextMarkerCheckerDefaultProblem(String fileName, String message, ASTNode node, int line,
          int severity) {
    super(fileName, message, 0, new String[] {}, severity, node.sourceStart(), node.sourceEnd(),
            line);
  }

}
