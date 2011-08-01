package org.apache.uima.tm.textmarker.kernel.extensions;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.InvalidXMLException;

public class TextMarkerEngineLoader extends DefaultEngineLoader {

  private Map<String, IEngineLoader> loaders;

  public TextMarkerEngineLoader() {
    super();
    loaders = new HashMap<String, IEngineLoader>();
  }

  @Override
  public AnalysisEngine loadEngine(String location) throws InvalidXMLException,
          ResourceInitializationException, IOException {
    String name = getEngineName(location);
    AnalysisEngine result = null;
    IEngineLoader engineLoader = loaders.get(name);
    if (engineLoader != null) {
      result = engineLoader.loadEngine(location);
    } else {
      result = loadEngineMyself(location);
    }
    return result;
  }

  public void addLoader(String engine, IEngineLoader loader) {
    loaders.put(engine, loader);
  }

  private String getEngineName(String location) {
    File file = new File(location);
    location = file.getName();
    String[] split = location.split("[.]");
    return split[split.length - 2];
  }

  public boolean isInitialized() {
    return !loaders.isEmpty();
  }

  public String[] getKnownEngines() {
    return loaders.keySet().toArray(new String[0]);
  }

}
