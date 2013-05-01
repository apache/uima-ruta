package org.apache.uima.ruta.example.extensions;

import java.util.List;

import org.antlr.runtime.RecognitionException;
import org.apache.uima.ruta.ide.core.extensions.IIDEActionExtension;
import org.apache.uima.ruta.ide.parser.ast.RutaAction;
import org.apache.uima.ruta.ide.parser.ast.RutaTypeConstants;
import org.apache.uima.ruta.ide.validator.RutaCheckerProblemFactory;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.compiler.problem.IProblem;
import org.eclipse.dltk.compiler.problem.IProblemReporter;

public class ExampleActionIDEExtension implements IIDEActionExtension {
  private final String[] strings = new String[] { "ExampleAction" };

  public String[] getKnownExtensions() {
    return strings;
  }

  public boolean checkSyntax(Expression element, RutaCheckerProblemFactory problemFactory,
          IProblemReporter rep) throws RecognitionException {
    if (element instanceof RutaAction) {
      RutaAction a = (RutaAction) element;
      String name = a.getName();
      if (!name.equals(strings[0])) {
        IProblem problem = problemFactory.createUnknownActionProblem(a);
        rep.reportProblem(problem);
        return false;
      }
      boolean ok = true;
      List<Expression> childs = a.getChilds();
      for (Expression expression : childs) {
        if (expression.getKind() != RutaTypeConstants.RUTA_TYPE_N) {
          IProblem problem = problemFactory.createWrongArgumentTypeProblem(expression, "NumberExpression");
          rep.reportProblem(problem);
          ok = false;
        }
      }
      return ok;
    }
    return false;
  }

}
