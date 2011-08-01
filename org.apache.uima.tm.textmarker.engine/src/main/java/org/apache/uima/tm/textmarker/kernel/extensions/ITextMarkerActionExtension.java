package org.apache.uima.tm.textmarker.kernel.extensions;

import java.util.List;

import org.apache.uima.tm.textmarker.action.AbstractTextMarkerAction;
import org.apache.uima.tm.textmarker.kernel.expression.TextMarkerExpression;

import antlr.ANTLRException;

public interface ITextMarkerActionExtension extends ITextMarkerExtension {

  AbstractTextMarkerAction createAction(String name, List<TextMarkerExpression> args)
          throws ANTLRException;

}
