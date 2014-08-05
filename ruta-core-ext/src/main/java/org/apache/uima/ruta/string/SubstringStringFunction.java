package org.apache.uima.ruta.string;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.string.StringFunctionExpression;

public class SubstringStringFunction extends StringFunctionExpression {

  private final IStringExpression expr;

  private final INumberExpression from;

  private final INumberExpression to; // excluded

  public SubstringStringFunction(IStringExpression expr, INumberExpression from,
          INumberExpression to) {
    super();
    this.expr = expr;
    this.from = from;
    this.to = to;
  }

  public IStringExpression getExpr() {
    return expr;
  }

  public String getStringValue(RutaBlock parent, AnnotationFS annotation, RutaStream stream) {
    String text = expr.getStringValue(parent, annotation, stream);
    if (text.length() >= this.to.getIntegerValue(parent, annotation, stream)){
      return text.substring(from.getIntegerValue(parent, annotation, stream),
              to.getIntegerValue(parent, annotation, stream));
    }

    else
      return null;
  }

}
