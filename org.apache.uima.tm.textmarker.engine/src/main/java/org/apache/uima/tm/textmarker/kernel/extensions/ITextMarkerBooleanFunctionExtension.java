package org.apache.uima.tm.textmarker.kernel.extensions;

import java.util.List;

import org.apache.uima.tm.textmarker.kernel.expression.TextMarkerExpression;
import org.apache.uima.tm.textmarker.kernel.expression.bool.BooleanFunctionExpression;

import antlr.ANTLRException;

public interface ITextMarkerBooleanFunctionExtension extends ITextMarkerExtension {

  BooleanFunctionExpression createBooleanFunction(String name, List<TextMarkerExpression> args)
          throws ANTLRException;

}
