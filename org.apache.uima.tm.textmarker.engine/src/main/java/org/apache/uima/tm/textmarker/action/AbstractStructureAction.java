package org.apache.uima.tm.textmarker.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.expression.TextMarkerExpression;
import org.apache.uima.tm.textmarker.kernel.expression.bool.BooleanExpression;
import org.apache.uima.tm.textmarker.kernel.expression.number.NumberExpression;
import org.apache.uima.tm.textmarker.kernel.expression.string.StringExpression;
import org.apache.uima.tm.textmarker.kernel.expression.type.TypeExpression;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.utils.UIMAUtils;


public abstract class AbstractStructureAction extends AbstractTextMarkerAction {

  protected void fillFeatures(TOP structure, Map<StringExpression, TextMarkerExpression> features,
          AnnotationFS matchedAnnotation, TextMarkerRuleElement element, TextMarkerStream stream) {
    Map<String, TextMarkerExpression> map = new HashMap<String, TextMarkerExpression>();
    for (Entry<StringExpression, TextMarkerExpression> each : features.entrySet()) {
      String value = each.getKey().getStringValue(element.getParent());
      map.put(value, each.getValue());
    }

    TypeSystem typeSystem = stream.getCas().getTypeSystem();
    JCas jcas = stream.getJCas();
    List<?> featuresList = structure.getType().getFeatures();
    for (int i = 0; i < featuresList.size(); i++) {
      Feature targetFeature = (Feature) featuresList.get(i);
      String name = targetFeature.getName();
      String shortFName = name.substring(name.indexOf(":") + 1, name.length());
      Object valueObject = map.get(shortFName);
      Type range = targetFeature.getRange();
      if (valueObject != null) {
        if (valueObject instanceof StringExpression && range.getName().equals("uima.cas.String")) {
          structure.setStringValue(targetFeature, ((StringExpression) valueObject)
                  .getStringValue(element.getParent()));
        } else if (valueObject instanceof TypeExpression
                && range.getName().equals("uima.cas.String")) {
          TypeExpression type = (TypeExpression) valueObject;
          List<AnnotationFS> annotationsInWindow = stream.getAnnotationsInWindow(matchedAnnotation,
                  type.getType(element.getParent()));
          if (annotationsInWindow != null && !annotationsInWindow.isEmpty()) {
            AnnotationFS annotation = annotationsInWindow.get(0);
            structure.setStringValue(targetFeature, annotation.getCoveredText());
          }
        } else if (valueObject instanceof NumberExpression
                && range.getName().equals("uima.cas.Integer")) {
          structure.setIntValue(targetFeature, ((NumberExpression) valueObject)
                  .getIntegerValue(element.getParent()));
        } else if (valueObject instanceof NumberExpression
                && range.getName().equals("uima.cas.Double")) {
          structure.setDoubleValue(targetFeature, ((NumberExpression) valueObject)
                  .getDoubleValue(element.getParent()));
        } else if (valueObject instanceof BooleanExpression
                && range.getName().equals("uima.cas.Boolean")) {
          structure.setBooleanValue(targetFeature, ((BooleanExpression) valueObject)
                  .getBooleanValue(element.getParent()));
        } else if (valueObject instanceof TypeExpression) {
          TypeExpression type = (TypeExpression) valueObject;
          List<AnnotationFS> annotationsInWindow = stream.getAnnotationsInWindow(matchedAnnotation,
                  type.getType(element.getParent()));
          if (typeSystem.subsumes(jcas.getCasType(FSArray.type), range)) {
            structure
                    .setFeatureValue(targetFeature, UIMAUtils.toFSArray(jcas, annotationsInWindow));
          } else if (typeSystem.subsumes(range, type.getType(element.getParent()))
                  && !annotationsInWindow.isEmpty()) {
            AnnotationFS annotation = annotationsInWindow.get(0);
            structure.setFeatureValue(targetFeature, annotation);
          }
        }
      }

    }
  }
}
