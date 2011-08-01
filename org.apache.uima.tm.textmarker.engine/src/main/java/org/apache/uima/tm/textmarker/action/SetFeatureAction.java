package org.apache.uima.tm.textmarker.action;

import java.util.List;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.expression.bool.BooleanExpression;
import org.apache.uima.tm.textmarker.kernel.expression.number.NumberExpression;
import org.apache.uima.tm.textmarker.kernel.expression.string.StringExpression;
import org.apache.uima.tm.textmarker.kernel.expression.type.TypeExpression;
import org.apache.uima.tm.textmarker.kernel.rule.RuleMatch;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;
import org.apache.uima.tm.textmarker.utils.UIMAUtils;


public class SetFeatureAction extends AbstractTextMarkerAction {

  private final StringExpression featureStringExpression;

  private StringExpression stringExpr;

  private NumberExpression numberExpr;

  private BooleanExpression booleanExpr;

  private TypeExpression typeExpr;

  protected SetFeatureAction(StringExpression feature) {
    super();
    this.featureStringExpression = feature;
  }

  public SetFeatureAction(StringExpression feature, StringExpression stringExpr) {
    this(feature);
    this.stringExpr = stringExpr;
  }

  public SetFeatureAction(StringExpression feature, NumberExpression numberExpr) {
    this(feature);
    this.numberExpr = numberExpr;
  }

  public SetFeatureAction(StringExpression feature, BooleanExpression booleanExpr) {
    this(feature);
    this.booleanExpr = booleanExpr;
  }

  public SetFeatureAction(StringExpression feature, TypeExpression typeExpr) {
    this(feature);
    this.typeExpr = typeExpr;
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

  @Override
  public void execute(RuleMatch match, TextMarkerRuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    Type type = element.getMatcher().getType(element.getParent(), stream);
    if (type == null)
      return;
    String featureString = featureStringExpression.getStringValue(element.getParent());
    Feature featureByBaseName = type.getFeatureByBaseName(featureString);
    Annotation expandAnchor = (Annotation) stream.expandAnchor(match.getFirstBasic(), type);
    if (expandAnchor.getType().getFeatureByBaseName(featureString) == null) {
      System.out.println("Can't access feature " + featureString
              + ", because it's not defined in the matched type: " + expandAnchor.getType());
      return;
    }
    expandAnchor.removeFromIndexes();
    if (stringExpr != null) {
      String string = stringExpr.getStringValue(element.getParent());
      expandAnchor.setStringValue(featureByBaseName, string);
    } else if (numberExpr != null) {
      String range = featureByBaseName.getRange().getName();
      if (range.equals("uima.cas.Integer")) {
        int v = numberExpr.getIntegerValue(element.getParent());
        expandAnchor.setIntValue(featureByBaseName, v);
      } else if (range.equals("uima.cas.Double")) {
        double v = numberExpr.getDoubleValue(element.getParent());
        expandAnchor.setDoubleValue(featureByBaseName, v);
      }
    } else if (booleanExpr != null) {
      boolean v = booleanExpr.getBooleanValue(element.getParent());
      expandAnchor.setBooleanValue(featureByBaseName, v);
    } else if (typeExpr != null) {
      Type t = typeExpr.getType(element.getParent());
      List<AnnotationFS> inWindow = stream.getAnnotationsInWindow(expandAnchor, t);
      if (featureByBaseName.getRange().isArray()) {
        expandAnchor.setFeatureValue(featureByBaseName, UIMAUtils.toFSArray(stream.getJCas(),
                inWindow));
      } else {
        AnnotationFS annotation = inWindow.get(0);
        expandAnchor.setFeatureValue(featureByBaseName, annotation);
      }
    }
    expandAnchor.addToIndexes();
  }

}
