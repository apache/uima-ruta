package org.apache.uima.ruta.string;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.string.StringFunctionExpression;

public class ReplaceAllStringFunction extends StringFunctionExpression {

  private final IStringExpression text;
  private final IStringExpression searchTerm;
  private final IStringExpression replacement;

  public ReplaceAllStringFunction(IStringExpression expr, IStringExpression searchTerm,
          IStringExpression replacement) {
    super();
    this.text = expr;
    this.searchTerm = searchTerm;
    this.replacement = replacement;
  }

  public IStringExpression getExpr() {
    return text;
  }

  public String getStringValue(RutaBlock parent, AnnotationFS annotation, RutaStream stream) {
    return text.getStringValue(parent, annotation, stream).replaceAll(
            searchTerm.getStringValue(parent, annotation, stream),
            replacement.getStringValue(parent, annotation, stream));
  }
}