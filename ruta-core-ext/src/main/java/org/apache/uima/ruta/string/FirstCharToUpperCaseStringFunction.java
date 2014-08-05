package org.apache.uima.ruta.string;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.string.StringFunctionExpression;

public class FirstCharToUpperCaseStringFunction extends StringFunctionExpression {

  private final IStringExpression expr;

  public FirstCharToUpperCaseStringFunction(IStringExpression expr) {
    super();
    this.expr = expr;
  }

  public IStringExpression getExpr() {
    return expr;
  }

  public String getStringValue(RutaBlock parent, AnnotationFS annotation, RutaStream stream) {
    StringBuilder sb = new StringBuilder(expr.getStringValue(parent, annotation, stream));
    sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
    
    return sb.toString();
  }
}
