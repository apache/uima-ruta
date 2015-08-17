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
import java.net.URL;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;

public abstract class DefaultEngineLoader implements IEngineLoader {

  public AnalysisEngine loadEngine(String location, String viewName) throws InvalidXMLException,
          ResourceInitializationException, ResourceConfigurationException, IOException,
          URISyntaxException {
    return loadEngineMyself(location, viewName);
  }

  protected AnalysisEngine loadEngineMyself(String location, String viewName) throws IOException,
          InvalidXMLException, ResourceInitializationException, ResourceConfigurationException,
          URISyntaxException {
    URL url = new File(location).toURI().toURL();
    AnalysisEngine ae = Ruta.wrapAnalysisEngine(url, viewName);
    return ae;
  }

  protected AnalysisEngine loadEngineMyselfIS(String location) throws IOException,
          InvalidXMLException, ResourceInitializationException {
    // TODO handle multi-view CASs
    URL resource = getClass().getClassLoader().getResource(location);
    XMLInputSource in = new XMLInputSource(resource);
    ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);
    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(specifier);
    return ae;
  }
}
