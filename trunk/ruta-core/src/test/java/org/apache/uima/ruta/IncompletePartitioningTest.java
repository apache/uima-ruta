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

package org.apache.uima.ruta;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.util.CasCreationUtils;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.XMLInputSource;
import org.junit.Test;

public class IncompletePartitioningTest {

  @Test
  public void test() throws Exception, IOException {
    String className = this.getClass().getSimpleName();
    String namespace = this.getClass().getPackage().getName().replaceAll("\\.", "/");

    String ruleFileName = namespace + "/" + className + RutaEngine.SCRIPT_FILE_EXTENSION;
    String textFileName = namespace + "/" + className + ".txt";
    URL ruleURL = RutaTestUtils.class.getClassLoader().getResource(ruleFileName);
    File ruleFile = new File(ruleURL.toURI());
    URL textURL = RutaTestUtils.class.getClassLoader().getResource(textFileName);
    File textFile = new File(textURL.toURI());
    URL url = RutaEngine.class.getClassLoader().getResource("BasicEngine.xml");
    if (url == null) {
      url = RutaTestUtils.class.getClassLoader().getResource(
              "org/apache/uima/ruta/TestEngine.xml");
    }
    XMLInputSource in = new XMLInputSource(url);
    ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);
    AnalysisEngineDescription aed = (AnalysisEngineDescription) specifier;

    TypeSystemDescription basicTypeSystem = aed.getAnalysisEngineMetaData().getTypeSystem();
    for (int i = 1; i <= 50; i++) {
      basicTypeSystem.addType(RutaTestUtils.TYPE + i, "Type for Testing",
              "uima.tcas.Annotation");
    }
    Collection<TypeSystemDescription> tsds = new ArrayList<TypeSystemDescription>();
    tsds.add(basicTypeSystem);
    TypeSystemDescription mergeTypeSystems = CasCreationUtils.mergeTypeSystems(tsds);
    aed.getAnalysisEngineMetaData().setTypeSystem(mergeTypeSystems);

    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(specifier);
    ae.setConfigParameterValue(RutaEngine.PARAM_SCRIPT_PATHS, new String[] { ruleFile
            .getParentFile().getPath() });
    String name = ruleFile.getName();
    if (name.endsWith(RutaEngine.SCRIPT_FILE_EXTENSION)) {
      name = name.substring(0, name.length() - 5);
    }
    ae.setConfigParameterValue(RutaEngine.PARAM_MAIN_SCRIPT, name);
    ae.setConfigParameterValue(RutaEngine.PARAM_SEEDERS, new String[0]);
    ae.setConfigParameterValue(RutaEngine.PARAM_DEFAULT_FILTERED_TYPES, new String[0]);

    ae.reconfigure();
    CAS cas = ae.newCAS();
    cas.setDocumentText(FileUtils.file2String(textFile, "UTF-8"));
    
    Type typeCW = cas.getTypeSystem().getType("org.apache.uima.ruta.type.CW");
    Type typeSW = cas.getTypeSystem().getType("org.apache.uima.ruta.type.SW");
    cas.addFsToIndexes(cas.createAnnotation(typeCW, 0, 5));
    cas.addFsToIndexes(cas.createAnnotation(typeCW, 7, 13));
    cas.addFsToIndexes(cas.createAnnotation(typeCW, 15, 18));
    cas.addFsToIndexes(cas.createAnnotation(typeSW, 19, 22));
    cas.addFsToIndexes(cas.createAnnotation(typeCW, 23, 28));
    
    ae.process(cas);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 2, "Peter, Jochen", "Flo and");

    cas.release();
  }
}
