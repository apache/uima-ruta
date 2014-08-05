package org.apache.uima.ruta.string.bool;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.bool.BooleanFunctionExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;

public class EqualsIgnoreCaseBooleanFunction extends BooleanFunctionExpression {
  
  private IStringExpression text;
  private IStringExpression compare;
  
  
  public EqualsIgnoreCaseBooleanFunction(IStringExpression text,
          IStringExpression compare) {
    this.text = text;
    this.compare = compare;
  }

  public IStringExpression getExpr() {
    return text;
  }

  @Override
  public boolean getBooleanValue(RutaBlock parent, AnnotationFS annotation, RutaStream stream) {
    return text.getStringValue(parent, annotation, stream).equalsIgnoreCase(compare.getStringValue(parent, annotation, stream));
  }

  @Override
  public String getStringValue(RutaBlock parent, AnnotationFS annotation, RutaStream stream) {
    Boolean equals = text.getStringValue(parent, annotation, stream).equalsIgnoreCase(compare.getStringValue(parent, annotation, stream)); 
    return equals.toString();
  }
}
