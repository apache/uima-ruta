package org.apache.uima.tm.dltk.core.extensions;

import java.util.List;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.apache.uima.tm.dltk.parser.ast.TextMarkerExpression;
import org.eclipse.dltk.ast.expressions.Expression;


public abstract class AbstractIDEBooleanFunctionExtension implements IIDEBooleanFunctionExtension {

  public TextMarkerExpression createBooleanFunction(Token type, List<Expression> args)
          throws RecognitionException {
    return new TextMarkerExpression(0, 0, args.get(0), 0);
  }

}
