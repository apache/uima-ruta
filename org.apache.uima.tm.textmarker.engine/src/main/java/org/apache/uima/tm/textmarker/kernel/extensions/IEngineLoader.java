package org.apache.uima.tm.textmarker.kernel.extensions;

import java.io.IOException;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.InvalidXMLException;

public interface IEngineLoader {

  String[] getKnownEngines();

  AnalysisEngine loadEngine(String location) throws InvalidXMLException,
          ResourceInitializationException, IOException;

}
