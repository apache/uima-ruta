package org.apache.uima.ruta.string.bool;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.bool.BooleanFunctionExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;

public class EndsWithBooleanFunction extends BooleanFunctionExpression {
  private IStringExpression text;
  private IStringExpression end;
  
  public EndsWithBooleanFunction(IStringExpression text,
          IStringExpression end) {
    this.text = text;
    this.end = end;
    
  }

  public IStringExpression getExpr() {
    return text;
  }

  @Override
  public boolean getBooleanValue(RutaBlock parent, AnnotationFS annotation, RutaStream stream) {
    return text.getStringValue(parent, annotation, stream).endsWith(end.getStringValue(parent, annotation, stream));
  }

  @Override
  public String getStringValue(RutaBlock parent, AnnotationFS annotation, RutaStream stream) {
    Boolean endsWith = text.getStringValue(parent, annotation, stream).endsWith(end.getStringValue(parent, annotation, stream)); 
    return endsWith.toString();
  }
}
