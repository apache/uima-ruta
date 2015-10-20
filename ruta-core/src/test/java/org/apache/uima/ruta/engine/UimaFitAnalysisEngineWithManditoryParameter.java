package org.apache.uima.ruta.engine;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;

public class UimaFitAnalysisEngineWithManditoryParameter extends JCasAnnotator_ImplBase {

  public static final String PARAM_TYPE = "type";

  @ConfigurationParameter(name = PARAM_TYPE, mandatory = true)
  private String type;
  
  @Override
  public void process(JCas jcas) throws AnalysisEngineProcessException {
    CAS cas = jcas.getCas();
    AnnotationFS da = cas.getDocumentAnnotation();
    Type t = cas.getTypeSystem().getType(type);
    AnnotationFS a = cas.createAnnotation(t, da.getBegin(), da.getEnd());
    cas.addFsToIndexes(a);
  }

}
