package org.apache.uima.ruta.string;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.string.StringFunctionExpression;

public class ReplaceFirstStringFunction extends StringFunctionExpression {

  private final IStringExpression expr;

  private final IStringExpression searchTerm;

  private final IStringExpression replacement;

  public ReplaceFirstStringFunction(IStringExpression expr, IStringExpression searchTerm,
          IStringExpression replacement) {
    super();
    this.expr = expr;
    this.searchTerm = searchTerm;
    this.replacement = replacement;
  }

  public IStringExpression getExpr() {
    return expr;
  }

  public String getStringValue(RutaBlock parent, AnnotationFS annotation, RutaStream stream) {
    return expr.getStringValue(parent, annotation, stream).replaceFirst(
            searchTerm.getStringValue(parent, annotation, stream),
            replacement.getStringValue(parent, annotation, stream));
  }
}
