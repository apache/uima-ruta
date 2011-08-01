package org.apache.uima.tm.textmarker.kernel.extensions;

import java.util.List;

import org.apache.uima.tm.textmarker.kernel.expression.TextMarkerExpression;
import org.apache.uima.tm.textmarker.kernel.expression.type.TypeFunctionExpression;

import antlr.ANTLRException;

public interface ITextMarkerTypeFunctionExtension extends ITextMarkerExtension {

  TypeFunctionExpression createTypeFunction(String name, List<TextMarkerExpression> args)
          throws ANTLRException;

}
