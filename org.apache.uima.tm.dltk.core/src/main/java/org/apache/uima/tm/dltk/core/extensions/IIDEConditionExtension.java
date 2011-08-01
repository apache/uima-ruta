package org.apache.uima.tm.dltk.core.extensions;

import java.util.List;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.apache.uima.tm.dltk.parser.ast.conditions.TextMarkerCondition;
import org.eclipse.dltk.ast.expressions.Expression;


public interface IIDEConditionExtension extends ITextMarkerExtension {

  TextMarkerCondition createCondition(Token type, List<Expression> args)
          throws RecognitionException;

}
