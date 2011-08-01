package org.apache.uima.tm.textmarker.condition;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.expression.bool.BooleanExpression;
import org.apache.uima.tm.textmarker.kernel.expression.number.NumberExpression;
import org.apache.uima.tm.textmarker.kernel.expression.string.StringExpression;
import org.apache.uima.tm.textmarker.kernel.expression.type.TypeExpression;
import org.apache.uima.tm.textmarker.kernel.rule.EvaluatedCondition;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class FeatureCondition extends AbstractTextMarkerCondition {

  private final StringExpression featureStringExpression;

  private StringExpression stringExpr;

  private NumberExpression numberExpr;

  private BooleanExpression booleanExpr;

  private TypeExpression typeExpr;

  protected FeatureCondition(StringExpression feature) {
    super();
    this.featureStringExpression = feature;
  }

  public FeatureCondition(StringExpression feature, StringExpression stringExpr) {
    this(feature);
    this.stringExpr = stringExpr;
  }

  public FeatureCondition(StringExpression feature, NumberExpression numberExpr) {
    this(feature);
    this.numberExpr = numberExpr;
  }

  public FeatureCondition(StringExpression feature, BooleanExpression booleanExpr) {
    this(feature);
    this.booleanExpr = booleanExpr;
  }

  public FeatureCondition(StringExpression feature, TypeExpression typeExpr, String variable) {
    this(feature);
    this.typeExpr = typeExpr;
  }

  @Override
  public EvaluatedCondition eval(TextMarkerBasic basic, Type matchedType,
          TextMarkerRuleElement element, TextMarkerStream stream, InferenceCrowd crowd) {
    String stringValue = featureStringExpression.getStringValue(element.getParent());
    Feature featureByBaseName = matchedType.getFeatureByBaseName(stringValue);
    AnnotationFS expandAnchor = stream.expandAnchor(basic, matchedType);

    if (stringExpr != null) {
      String value = expandAnchor.getStringValue(featureByBaseName);
      String string = stringExpr.getStringValue(element.getParent());
      boolean result = string != null && string.equals(value);
      return new EvaluatedCondition(this, result);
    } else if (numberExpr != null) {
      String range = featureByBaseName.getRange().getName();
      boolean result = false;
      if (range.equals("uima.cas.Integer")) {
        int value = expandAnchor.getIntValue(featureByBaseName);
        int v = numberExpr.getIntegerValue(element.getParent());
        result = value == v;
      } else if (range.equals("uima.cas.Double")) {
        double value = expandAnchor.getDoubleValue(featureByBaseName);
        double v = numberExpr.getDoubleValue(element.getParent());
        result = value == v;
      }
      return new EvaluatedCondition(this, result);
    } else if (booleanExpr != null) {
      boolean value = expandAnchor.getBooleanValue(featureByBaseName);
      boolean v = booleanExpr.getBooleanValue(element.getParent());
      boolean result = value == v;
      return new EvaluatedCondition(this, result);
    } else if (typeExpr != null) {
      // String value = expandAnchor.getStringValue(featureByBaseName);
      // String string = stringExpr.getStringValue(element.getParent());
      // boolean result = string != null && string.equals(value);
      // return new EvaluatedCondition(this, result);
    }
    return new EvaluatedCondition(this, false);
  }

  public StringExpression getFeatureStringExpression() {
    return featureStringExpression;
  }

  public StringExpression getStringExpr() {
    return stringExpr;
  }

  public NumberExpression getNumberExpr() {
    return numberExpr;
  }

  public BooleanExpression getBooleanExpr() {
    return booleanExpr;
  }

  public TypeExpression getTypeExpr() {
    return typeExpr;
  }

}
