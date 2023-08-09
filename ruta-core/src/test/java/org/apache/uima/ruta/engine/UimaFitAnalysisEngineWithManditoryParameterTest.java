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

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.cas.CAS;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.ruta.descriptor.RutaBuildOptions;
import org.apache.uima.ruta.descriptor.RutaDescriptorFactory;
import org.apache.uima.ruta.descriptor.RutaDescriptorInformation;
import org.junit.jupiter.api.Test;
public class UimaFitAnalysisEngineWithManditoryParameterTest {

  @Test
  public void test() throws Exception {

    String document = "Some text.";
    String script = "UIMAFIT org.apache.uima.ruta.engine.UimaFitAnalysisEngineWithManditoryParameter (type, "
            + RutaTestUtils.TYPE + "1);";
    script += "EXEC(UimaFitAnalysisEngineWithManditoryParameter);";

    RutaDescriptorFactory factory = new RutaDescriptorFactory();
    RutaDescriptorInformation rdi = factory.parseDescriptorInformation(script);
    AnalysisEngineDescription aed = factory.createAnalysisEngineDescription(null, rdi,
            new RutaBuildOptions(), null, null, null, this.getClass().getClassLoader());
    aed.getAnalysisEngineMetaData().getConfigurationParameterSettings()
            .setParameterValue(RutaEngine.PARAM_RULES, script);
    ResourceManager resourceManager = UIMAFramework.newDefaultResourceManager();
    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(aed, resourceManager, null);
    CAS cas = RutaTestUtils.getCAS(document);
    ae.process(cas);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Some text.");
  }

  @Test
  public void testScriptOnly() throws Exception {

    String document = "Some text.";
    String script = "UIMAFIT org.apache.uima.ruta.engine.UimaFitAnalysisEngineWithManditoryParameter (type, "
            + RutaTestUtils.TYPE + "1);";
    script += "EXEC(UimaFitAnalysisEngineWithManditoryParameter);";

    AnalysisEngine ae = AnalysisEngineFactory.createEngine(RutaEngine.class, RutaEngine.PARAM_RULES,
            script);
    CAS cas = RutaTestUtils.getCAS(document);
    ae.process(cas);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Some text.");
  }
}
