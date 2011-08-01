package org.apache.uima.tm.textmarker.kernel.expression.string;

import java.util.List;

import org.antlr.runtime.Token;

public class StringFunctionFactory {

  public static StringExpression createRemoveFunction(Token var, List<StringExpression> list) {
    String v = var == null ? "" : var.getText();
    return new RemoveFunction(v, list);
  }
}
