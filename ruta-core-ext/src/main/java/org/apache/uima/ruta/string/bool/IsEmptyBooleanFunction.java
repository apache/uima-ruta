package org.apache.uima.ruta.string.bool;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.bool.BooleanFunctionExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;

public class IsEmptyBooleanFunction extends BooleanFunctionExpression {

  private IStringExpression text;

  public IsEmptyBooleanFunction(IStringExpression text) {
    this.text = text;
  }

  public IStringExpression getExpr() {
    return text;
  }

  @Override
  public boolean getBooleanValue(RutaBlock parent, AnnotationFS annotation, RutaStream stream) {
    return text.getStringValue(parent, annotation, stream).isEmpty();
  }

  @Override
  public String getStringValue(RutaBlock parent, AnnotationFS annotation, RutaStream stream) {
    Boolean isEmpty = text.getStringValue(parent, annotation, stream).isEmpty();
    return isEmpty.toString();
  }
}
