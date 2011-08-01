package org.apache.uima.tm.dltk.core.extensions;

import java.util.List;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.apache.uima.tm.dltk.parser.ast.actions.TextMarkerAction;
import org.eclipse.dltk.ast.expressions.Expression;


public interface IIDEActionExtension extends ITextMarkerExtension {

  TextMarkerAction createAction(Token type, List<Expression> args) throws RecognitionException;

}
