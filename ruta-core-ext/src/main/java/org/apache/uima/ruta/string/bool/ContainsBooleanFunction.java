package org.apache.uima.ruta.string.bool;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.bool.BooleanFunctionExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;

public class ContainsBooleanFunction extends BooleanFunctionExpression {
  
  private IStringExpression text;
  private IStringExpression contains;
  
  public ContainsBooleanFunction(IStringExpression text,
          IStringExpression contains) {
    this.text = text;
    this.contains =contains;
  }

  public IStringExpression getExpr() {
    return text;
  }

  @Override
  public boolean getBooleanValue(RutaBlock parent, AnnotationFS annotation, RutaStream stream) {
    
return text.getStringValue(parent, annotation, stream).contains(contains.getStringValue(parent, annotation, stream));
  }

  @Override
  public String getStringValue(RutaBlock parent, AnnotationFS annotation, RutaStream stream) {
    Boolean isContained = text.getStringValue(parent, annotation, stream).contains(contains.getStringValue(parent, annotation, stream)); 
    return isContained.toString();
  }

}
