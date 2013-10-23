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

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.TypeSystemDescriptionFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.metadata.ResourceMetaData;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.util.CasCreationUtils;
import org.apache.uima.util.InvalidXMLException;
import org.junit.Test;


/**
 * Tests if the script is correctly applied in a pipeline if a new CAS is used.
 *
 */
public class NewCASPipelineTest {
  private static final String TEST_TYPE = "org.apache.uima.T1";

  /**
   * Create an analysis engine for a Ruta script.
   * 
   * @param script
   *          Script path.
   * @return Analysis engine.
   */
  private AnalysisEngine createAE(String script) throws ResourceInitializationException,
          IOException, InvalidXMLException {
    final TypeSystemDescription tsd = TypeSystemDescriptionFactory
            .createTypeSystemDescription("org.apache.uima.ruta.BasicTypeSystem");
    final AnalysisEngineDescription ruta = AnalysisEngineFactory.createEngineDescription(
            "org.apache.uima.ruta.engine.BasicEngine", RutaEngine.PARAM_MAIN_SCRIPT, script);

    ruta.getAnalysisEngineMetaData().setTypeSystem(tsd);
    ruta.getAnalysisEngineMetaData().getTypeSystem()
            .addType(TEST_TYPE, "Type for Testing", "uima.tcas.Annotation");

    return AnalysisEngineFactory.createEngine(ruta);
  }

  @Test
  public void test() throws ResourceInitializationException, IOException, InvalidXMLException,
          AnalysisEngineProcessException, ResourceConfigurationException {
    final String name = getClass().getSimpleName();
    final String namespace = getClass().getPackage().getName();
    final AnalysisEngine ae = createAE(namespace + "." + name);
    List<ResourceMetaData> metaData = new ArrayList<ResourceMetaData>();
    metaData.add(ae.getMetaData());
    String input = "This is a test.";
    Type t1 = null;
    
    
    // create a cas and apply rules
    final CAS cas = CasCreationUtils.createCas(metaData);
    cas.setDocumentText(input);
    SimplePipeline.runPipeline(cas, ae);

    t1 = cas.getTypeSystem().getType(TEST_TYPE);
    assertEquals(1, cas.getAnnotationIndex(t1).size());

    // reset the cas and do it again
    cas.reset();
    cas.setDocumentText(input);
    SimplePipeline.runPipeline(cas, ae);

    t1 = cas.getTypeSystem().getType(TEST_TYPE);
    assertEquals(1, cas.getAnnotationIndex(t1).size());
    
    // create a new cas and force the ae to update its types
    ae.setConfigParameterValue(RutaEngine.PARAM_RELOAD_SCRIPT, true);
    ae.reconfigure();
    
    final CAS cas2 = CasCreationUtils.createCas(metaData);
    cas2.setDocumentText(input);
    SimplePipeline.runPipeline(cas2, ae);

    t1 = cas2.getTypeSystem().getType(TEST_TYPE);
    assertEquals(1, cas2.getAnnotationIndex(t1).size());
    
    // create a new cas and do not force the ae to update its types
    ae.setConfigParameterValue(RutaEngine.PARAM_RELOAD_SCRIPT, false);
    ae.reconfigure();
    final CAS cas3 = CasCreationUtils.createCas(metaData);
    cas3.setDocumentText(input);
    SimplePipeline.runPipeline(cas3, ae);

    t1 = cas3.getTypeSystem().getType(TEST_TYPE);
    assertEquals(1, cas3.getAnnotationIndex(t1).size());
    
    // create a new cas and do not reconfigure the engine
    final CAS cas4 = CasCreationUtils.createCas(metaData);
    cas4.setDocumentText(input);
    SimplePipeline.runPipeline(cas4, ae);

    t1 = cas4.getTypeSystem().getType(TEST_TYPE);
    assertEquals(1, cas4.getAnnotationIndex(t1).size());
    
    if (ae != null) {
      ae.destroy();
    }
    if (cas != null) {
      cas.release();
    }
    if (cas2 != null) {
      cas2.release();
    }
    if (cas3 != null) {
      cas3.release();
    }
    if (cas4 != null) {
      cas4.release();
    }

  }
}
