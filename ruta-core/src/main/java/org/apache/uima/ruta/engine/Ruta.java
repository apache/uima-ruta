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

package org.apache.uima.ruta.engine;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.analysis_engine.metadata.AnalysisEngineMetaData;
import org.apache.uima.cas.CAS;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.resource.metadata.ConfigurationParameterSettings;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.util.CasCreationUtils;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;

public class Ruta {

  public static void apply(CAS cas, String script, Map<String, Object> parameters)
          throws IOException, InvalidXMLException, ResourceInitializationException,
          ResourceConfigurationException, AnalysisEngineProcessException, URISyntaxException {
    String viewName = cas.getViewName();
    URL aedesc = RutaEngine.class.getResource("BasicEngine.xml");
    AnalysisEngine ae = wrapAnalysisEngine(aedesc, viewName);

    File scriptFile = File.createTempFile("Ruta", RutaEngine.SCRIPT_FILE_EXTENSION);
    scriptFile.deleteOnExit();
    FileUtils.saveString2File(script, scriptFile);
    ae.setConfigParameterValue(RutaEngine.PARAM_SCRIPT_PATHS, new String[] { scriptFile.getParentFile()
            .getAbsolutePath() });
    String name = scriptFile.getName().substring(0, scriptFile.getName().length() - 5);
    ae.setConfigParameterValue(RutaEngine.PARAM_MAIN_SCRIPT, name);
    if (parameters != null) {
      for (Map.Entry<String, Object> parameter : parameters.entrySet()) {
        ae.setConfigParameterValue(parameter.getKey(), parameter.getValue());
      }
    }
    ae.reconfigure();
    ae.process(cas);
    scriptFile.delete();
    ae.destroy();
  }

  public static void apply(CAS cas, String script) throws IOException, InvalidXMLException,
          ResourceInitializationException, ResourceConfigurationException,
          AnalysisEngineProcessException, URISyntaxException {
    apply(cas, script, null);
  }

  public static AnalysisEngine wrapAnalysisEngine(URL descriptorUrl, String viewName)
          throws ResourceInitializationException, ResourceConfigurationException,
          InvalidXMLException, IOException, URISyntaxException {
    return wrapAnalysisEngine(descriptorUrl, viewName, false, Charset.defaultCharset().name());
  }

  public static AnalysisEngine wrapAnalysisEngine(URL descriptorUrl, String viewName,
          boolean rutaEngine, String encoding) throws ResourceInitializationException,
          ResourceConfigurationException, InvalidXMLException, IOException, URISyntaxException {
    if (viewName.equals(CAS.NAME_DEFAULT_SOFA)) {
      XMLInputSource in = new XMLInputSource(descriptorUrl);
      ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);
      AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(specifier);
      return ae;
    } else {
      InputStream inputStream = null;
      if (rutaEngine) {
        inputStream = Ruta.class.getResourceAsStream("AAEDBasicEngine.xml");
      } else {
        inputStream = Ruta.class.getResourceAsStream("AAED.xml");
      }
      String aaedString = IOUtils.toString(inputStream, encoding);
      String absolutePath = descriptorUrl.toExternalForm();
      aaedString = aaedString.replaceAll("\\$\\{sofaName\\}", viewName);
      aaedString = aaedString.replaceAll("\\$\\{descriptorLocation\\}", absolutePath);
      File tempFile = File.createTempFile("RutaAAED", ".xml");
      FileUtils.saveString2File(aaedString, tempFile);
      XMLInputSource in = new XMLInputSource(tempFile);
      ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);
      AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(specifier);
      tempFile.delete();
      return ae;
    }
  }

  public static AnalysisEngineDescription createAnalysisEngineDescription(String script)
          throws IOException, InvalidXMLException, ResourceInitializationException {
    return createAnalysisEngineDescription(script, (TypeSystemDescription[]) null);
  }

  public static AnalysisEngineDescription createAnalysisEngineDescription(String script,
          TypeSystemDescription... tsds) throws IOException, InvalidXMLException,
          ResourceInitializationException {
    File scriptFile = File.createTempFile("Ruta", RutaEngine.SCRIPT_FILE_EXTENSION);
    scriptFile.deleteOnExit();
    if (!script.startsWith("PACKAGE")) {
      script = "PACKAGE org.apache.uima.ruta;\n" + script;
    }
    FileUtils.saveString2File(script, scriptFile);
    URL url = RutaEngine.class.getResource("BasicEngine.xml");
    XMLInputSource in = new XMLInputSource(url);
    AnalysisEngineDescription aed = (AnalysisEngineDescription) UIMAFramework.getXMLParser()
            .parseResourceSpecifier(in);
    AnalysisEngineMetaData metaData = aed.getAnalysisEngineMetaData();
    ConfigurationParameterSettings settings = metaData.getConfigurationParameterSettings();
    settings.setParameterValue(RutaEngine.PARAM_SCRIPT_PATHS, new String[] { scriptFile.getParentFile()
            .getAbsolutePath() });
    String name = scriptFile.getName().substring(0, scriptFile.getName().length() - 5);
    settings.setParameterValue(RutaEngine.PARAM_MAIN_SCRIPT, name);
    if (tsds != null) {
      List<TypeSystemDescription> tsdList = new ArrayList<TypeSystemDescription>();
      tsdList.add(metaData.getTypeSystem());
      tsdList.addAll(Arrays.asList(tsds));
      TypeSystemDescription typeSystemDescription = CasCreationUtils.mergeTypeSystems(tsdList);
      metaData.setTypeSystem(typeSystemDescription);
    }
    return aed;
  }

}
