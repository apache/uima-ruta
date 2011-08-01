package org.apache.uima.tm.textmarker.kernel.extensions;

import java.util.List;

import org.apache.uima.tm.textmarker.condition.AbstractTextMarkerCondition;
import org.apache.uima.tm.textmarker.kernel.expression.TextMarkerExpression;

import antlr.ANTLRException;

public interface ITextMarkerConditionExtension extends ITextMarkerExtension {

  AbstractTextMarkerCondition createCondition(String name, List<TextMarkerExpression> args)
          throws ANTLRException;

}
