package org.apache.uima.tm.dltk.core.extensions;

import java.util.List;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.apache.uima.tm.dltk.parser.ast.conditions.TextMarkerCondition;
import org.eclipse.dltk.ast.expressions.Expression;


public abstract class AbstractIDEConditionExtension implements IIDEConditionExtension {

  public TextMarkerCondition createCondition(Token type, List<Expression> args)
          throws RecognitionException {
    return new TextMarkerCondition(0, 0, args, 0, "", 0, 0);
  }

}
