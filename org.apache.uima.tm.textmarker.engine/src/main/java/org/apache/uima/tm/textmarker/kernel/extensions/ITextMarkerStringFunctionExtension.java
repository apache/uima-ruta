package org.apache.uima.tm.textmarker.kernel.extensions;

import java.util.List;

import org.apache.uima.tm.textmarker.kernel.expression.TextMarkerExpression;
import org.apache.uima.tm.textmarker.kernel.expression.string.StringFunctionExpression;

import antlr.ANTLRException;

public interface ITextMarkerStringFunctionExtension extends ITextMarkerExtension {

  StringFunctionExpression createStringFunction(String name, List<TextMarkerExpression> args)
          throws ANTLRException;

}
