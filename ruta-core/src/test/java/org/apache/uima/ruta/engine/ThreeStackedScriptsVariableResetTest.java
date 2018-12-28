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
import java.nio.file.Paths;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.fit.util.CasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.ruta.descriptor.RutaBuildOptions;
import org.apache.uima.ruta.descriptor.RutaDescriptorFactory;
import org.apache.uima.ruta.descriptor.RutaDescriptorInformation;
import org.junit.Assert;
import org.junit.Test;

public class ThreeStackedScriptsVariableResetTest {

  @Test
  public void test() throws Exception {
    String input1 = "snark";
    String input2 = "cat";
    String input3 = "bug";

    String path = "src/test/resources/org/apache/uima/ruta/engine/";
    RutaDescriptorFactory factory = new RutaDescriptorFactory();
    RutaBuildOptions options = new RutaBuildOptions();
    RutaDescriptorInformation rdi = factory
            .parseDescriptorInformation(new File(path + "script1.ruta"), options);
    Pair<AnalysisEngineDescription, TypeSystemDescription> descriptions = factory
            .createDescriptions(null, Paths.get(path).toFile().getAbsolutePath(), rdi, options,
                    new String[] { path }, new String[] { path }, null);

    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(descriptions.getKey());
    JCas jcas = JCasFactory.createJCas(descriptions.getValue());

    jcas.setDocumentText(input1);
    ae.process(jcas);
    checkFeatureValue(jcas);

    jcas.reset();
    jcas.setDocumentText(input2);
    ae.process(jcas);
    checkFeatureValue(jcas);

    jcas.reset();
    jcas.setDocumentText(input3);
    ae.process(jcas);
    checkFeatureValue(jcas);

  }

  private void checkFeatureValue(JCas jcas) {
    CAS cas = jcas.getCas();
    Type type = cas.getTypeSystem().getType("script3.Value");
    Feature feature = type.getFeatureByBaseName("value");
    FeatureStructure fs = CasUtil.selectSingle(cas, type);
    String stringValue = fs.getStringValue(feature);
    Assert.assertNull(stringValue);
  }

}
