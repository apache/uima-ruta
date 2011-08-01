package org.apache.uima.tm.textmarker.action;

import java.util.List;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.expression.type.TypeExpression;
import org.apache.uima.tm.textmarker.kernel.rule.RuleElementMatch;
import org.apache.uima.tm.textmarker.kernel.rule.RuleMatch;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class TransferAction extends TypeSensitiveAction {

  public TransferAction(TypeExpression type) {
    super(type);
  }

  @Override
  public void execute(RuleMatch match, TextMarkerRuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    List<RuleElementMatch> list = match.getMatchInfos().get(element);
    CAS cas = stream.getCas();
    Type t = type.getType(element.getParent());
    for (RuleElementMatch each : list) {
      List<AnnotationFS> matched = each.getTextsMatched();
      for (AnnotationFS annotationFS : matched) {
        FeatureStructure createFS = cas.createFS(t);
        copyFeatures(annotationFS, createFS, cas);
        if (createFS instanceof AnnotationFS) {
          TextMarkerBasic basic = stream.getFirstBasicInWindow(annotationFS);
          stream.addAnnotation(basic, (AnnotationFS) createFS);
        }
        cas.addFsToIndexes(createFS);
      }
    }

  }

  private void copyFeatures(AnnotationFS oldFS, FeatureStructure newFS, CAS cas) {
    List<?> features = oldFS.getType().getFeatures();
    Type newType = newFS.getType();
    for (Object object : features) {
      Feature feature = (Feature) object;
      String shortName = feature.getShortName();
      Feature newFeature = newType.getFeatureByBaseName(shortName);
      if (newFeature != null) {
        if (feature.getRange().isPrimitive()) {
          String value = oldFS.getFeatureValueAsString(newFeature);
          newFS.setFeatureValueFromString(newFeature, value);
        } else {
          FeatureStructure value = oldFS.getFeatureValue(feature);
          newFS.setFeatureValue(newFeature, value);
        }
      }
    }
  }

}
