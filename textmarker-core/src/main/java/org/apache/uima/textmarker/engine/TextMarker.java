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

package org.apache.uima.textmarker.engine;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;

public class TextMarker {

  public static void apply(CAS cas, String script) throws IOException, InvalidXMLException,
          ResourceInitializationException, ResourceConfigurationException,
          AnalysisEngineProcessException {
    URL aedesc = TextMarkerEngine.class.getResource("BasicEngine.xml");
    XMLInputSource inae = new XMLInputSource(aedesc);
    ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(inae);
    ResourceManager resMgr = UIMAFramework.newDefaultResourceManager();
    AnalysisEngineDescription aed = (AnalysisEngineDescription) specifier;
    
//    TypeSystemDescription basicTypeSystem = aed.getAnalysisEngineMetaData().getTypeSystem();
//    Collection<TypeSystemDescription> tsds = new ArrayList<TypeSystemDescription>();
//    tsds.add(basicTypeSystem);
//    TypeSystemDescription mergeTypeSystems = CasCreationUtils.mergeTypeSystems(tsds);
//    aed.getAnalysisEngineMetaData().setTypeSystem(mergeTypeSystems);
//    aed.resolveImports(resMgr);

    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(aed, resMgr, null);
    File scriptFile = File.createTempFile("TextMarker", ".tm");
    scriptFile.deleteOnExit();
    if (!script.startsWith("PACKAGE")) {
      script = "PACKAGE org.apache.uima.textmarker;\n" + script;
    }
    FileUtils.saveString2File(script, scriptFile);
    ae.setConfigParameterValue(TextMarkerEngine.SCRIPT_PATHS, new String[] { scriptFile
            .getParentFile().getAbsolutePath() });
    String name = scriptFile.getName().substring(0, scriptFile.getName().length() - 3);
    ae.setConfigParameterValue(TextMarkerEngine.MAIN_SCRIPT, name);
    ae.reconfigure();
    ae.process(cas);
    scriptFile.delete();
    ae.destroy();
  }

}
