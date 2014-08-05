package org.apache.uima.ruta.string;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.string.StringFunctionExpression;

public class ToUpperCaseStringFunction extends StringFunctionExpression {

  private final IStringExpression expr;

  public ToUpperCaseStringFunction(IStringExpression expr) {
    super();
    this.expr = expr;
  }

  public IStringExpression getExpr() {
    return expr;
  }

  public String getStringValue(RutaBlock parent, AnnotationFS annotation, RutaStream stream) {
    return expr.getStringValue(parent, annotation, stream).toUpperCase();
  }
}
