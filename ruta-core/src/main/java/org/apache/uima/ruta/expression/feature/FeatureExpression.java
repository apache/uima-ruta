package org.apache.uima.ruta.expression.feature;

import java.util.Collection;
import java.util.List;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStatement;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.RutaExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;

public abstract class FeatureExpression extends RutaExpression {

  public abstract Feature getFeature(RutaStatement parent);

  public abstract List<Feature> getFeatures(RutaStatement parent);

  public abstract List<String> getFeatureStringList();

  public abstract TypeExpression getTypeExpr();

  public abstract Collection<AnnotationFS> getFeatureAnnotations(
          Collection<AnnotationFS> annotations, RutaStream stream, RutaBlock parent,
          boolean checkOnFeatureValue);

}
