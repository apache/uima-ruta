package org.apache.uima.ruta.rule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.UIMAConstants;
import org.apache.uima.ruta.expression.RutaExpression;
import org.apache.uima.ruta.expression.bool.BooleanExpression;
import org.apache.uima.ruta.expression.feature.FeatureExpression;
import org.apache.uima.ruta.expression.feature.FeatureMatchExpression;
import org.apache.uima.ruta.expression.number.NumberExpression;
import org.apache.uima.ruta.expression.string.StringExpression;

public class RutaFeatureMatcher extends RutaTypeMatcher {

  private FeatureExpression featExpr;

  public RutaFeatureMatcher(FeatureExpression expression) {
    super(expression.getTypeExpr());
    this.featExpr = expression;
  }

  public Collection<AnnotationFS> getMatchingAnnotations(RutaStream stream, RutaBlock parent) {
    Collection<AnnotationFS> baseAnnotations = super.getMatchingAnnotations(stream, parent);
    Collection<AnnotationFS> result = new TreeSet<AnnotationFS>(comparator);
    List<Feature> features = featExpr.getFeatures(parent);
    for (AnnotationFS eachBase : baseAnnotations) {
      AnnotationFS afs = eachBase;
      for (Feature feature : features) {
        if(feature.getRange().isPrimitive()&& featExpr instanceof FeatureMatchExpression) {
          FeatureMatchExpression fme = (FeatureMatchExpression) featExpr;
          RutaExpression arg = fme.getArg();
          if(checkFeatureValue(afs, feature, arg, stream, parent)) {
            result.add(afs);
          }
          break;
        } else {
          FeatureStructure value = afs.getFeatureValue(feature);
          afs = (AnnotationFS) value; 
        }
      }
      if(!(featExpr instanceof FeatureMatchExpression)) {
        result.add(afs);
      }
    }
    return result;
  }

  private boolean checkFeatureValue(AnnotationFS afs, Feature feature, RutaExpression arg,
          RutaStream stream, RutaBlock parent) {
    String rn = feature.getRange().getName();
    if(rn.equals(UIMAConstants.TYPE_BOOLEAN)) {
      boolean v1 = afs.getBooleanValue(feature);
      if(arg instanceof BooleanExpression) {
        BooleanExpression expr = (BooleanExpression) arg;
        boolean v2 = expr.getBooleanValue(parent);
        return v1 == v2;
      }
    } else if(rn.equals(UIMAConstants.TYPE_INTEGER) || rn.equals(UIMAConstants.TYPE_BYTE) || rn.equals(UIMAConstants.TYPE_SHORT)|| rn.equals(UIMAConstants.TYPE_LONG)) {
      Integer v1 = afs.getIntValue(feature);
      if(arg instanceof NumberExpression) {
        NumberExpression expr = (NumberExpression) arg;
        Integer v2 = expr.getIntegerValue(parent);
        return v1.equals(v2);
      }
    } else if(rn.equals(UIMAConstants.TYPE_DOUBLE)) {
      Double v1 = afs.getDoubleValue(feature);
      if(arg instanceof NumberExpression) {
        NumberExpression expr = (NumberExpression) arg;
        Double v2 = expr.getDoubleValue(parent);
        return v1.equals(v2);
      }
    } else if(rn.equals(UIMAConstants.TYPE_FLOAT)) {
      Float v1 = afs.getFloatValue(feature);
      if(arg instanceof NumberExpression) {
        NumberExpression expr = (NumberExpression) arg;
        Float v2 = expr.getFloatValue(parent);
        return v1.equals(v2);
      }
    } else if(rn.equals(UIMAConstants.TYPE_STRING)) {
      String v1 = afs.getStringValue(feature);
      if(arg instanceof StringExpression) {
        StringExpression expr = (StringExpression) arg;
        String v2 = expr.getStringValue(parent);
        return v1.equals(v2);
      }
    }
    return false;
  }

  public boolean match(AnnotationFS annotation, RutaStream stream, RutaBlock parent) {
    Type baseType = getType(expression, parent, stream);
    return false;
  }

  public List<Type> getTypes(RutaBlock parent, RutaStream stream) {
    List<Type> result = new ArrayList<Type>(1);
    Type baseType = getType(expression, parent, stream);
    result.add(baseType);
    return result;
  }

  public RutaExpression getExpression() {
    return expression;
  }

  public Collection<AnnotationFS> getAnnotationsAfter(RutaRuleElement ruleElement,
          AnnotationFS annotation, RutaStream stream, RutaBlock parent) {
    // TODO Auto-generated method stub
    return null;
  }

  public Collection<AnnotationFS> getAnnotationsBefore(RutaRuleElement ruleElement,
          AnnotationFS annotation, RutaStream stream, RutaBlock parent) {
    // TODO Auto-generated method stub
    return null;
  }

  public FeatureExpression getFeatExpr() {
    return featExpr;
  }

  public void setFeatExpr(FeatureExpression featExpr) {
    this.featExpr = featExpr;
  }

}
