package org.apache.uima.tm.dltk.core.extensions;

import java.util.List;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.apache.uima.tm.dltk.parser.ast.actions.TextMarkerAction;
import org.eclipse.dltk.ast.expressions.Expression;


public abstract class AbstractIDEActionExtension implements IIDEActionExtension {

  public TextMarkerAction createAction(Token type, List<Expression> args)
          throws RecognitionException {
    // TODO set boundaries
    return new TextMarkerAction(0, 0, args, 0, "", 0, 0);
  }

}
