package org.apache.uima.tm.textmarker.kernel.extensions;

import java.io.IOException;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;

public abstract class DefaultEngineLoader implements IEngineLoader {

  public AnalysisEngine loadEngine(String location) throws InvalidXMLException,
          ResourceInitializationException, IOException {
    return loadEngineMyself(location);
  }

  protected AnalysisEngine loadEngineMyself(String location) throws IOException,
          InvalidXMLException, ResourceInitializationException {
    XMLInputSource in = new XMLInputSource(location);
    ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);
    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(specifier);
    return ae;
  }
}
