/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.uima.ruta.extensions;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.InvalidXMLException;

public class RutaEngineLoader extends DefaultEngineLoader {

  private Map<String, IEngineLoader> loaders;

  public RutaEngineLoader() {
    super();
    loaders = new HashMap<String, IEngineLoader>();
  }

  @Override
  public AnalysisEngine loadEngine(String location, String viewName) throws InvalidXMLException,
          ResourceInitializationException, ResourceConfigurationException, IOException,
          URISyntaxException {
    String name = getEngineName(location);
    AnalysisEngine result = null;
    IEngineLoader engineLoader = loaders.get(name);
    if (engineLoader != null) {
      result = engineLoader.loadEngine(location, viewName);
    } else {
      result = loadEngineMyself(location, viewName);
    }
    return result;
  }

  public AnalysisEngine loadEngineIS(String location, String viewName) throws InvalidXMLException,
          ResourceInitializationException, IOException, ResourceConfigurationException,
          URISyntaxException {
    String name = getEngineNameIS(location);
    AnalysisEngine result = null;
    IEngineLoader engineLoader = loaders.get(name);
    if (engineLoader != null) {
      result = engineLoader.loadEngine(location, viewName);
    } else {
      result = loadEngineMyselfIS(location);
    }
    return result;
  }

  public void addLoader(String engine, IEngineLoader loader) {
    loaders.put(engine, loader);
  }

  private String getEngineName(String location) {
    File file;
    try {
      file = new File(location);
    } catch (java.lang.NullPointerException e) {
      throw new java.lang.NullPointerException("File[" + location + "] cannot be opened.");
    }
    location = file.getName();
    String[] split = location.split("[.]");
    return split[split.length - 2];
  }

  private String getEngineNameIS(String location) {
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
