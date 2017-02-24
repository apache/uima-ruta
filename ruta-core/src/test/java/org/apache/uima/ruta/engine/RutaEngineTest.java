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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.CAS;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.jcas.JCas;
import org.apache.uima.ruta.TypeUsageInformation;
import org.junit.Assert;
import org.junit.Test;

public class RutaEngineTest {

  @Test
  public void testTypeUsageInformation() throws Exception {
    String script = "TYPE typeVar;\n";
    script += "CW{-> FalsePositive};\n";
    script += "RETAINTYPE(WS);\n";
    script += "DECLARE Type1;\n";
    script += "org.apache.uima.ruta.type.FalseNegative;\n";
    script += "BLOCK(block) Document{}{\n";
    script += "DECLARE Type2;\n";
    script += "SW.ct==\"a\";\n";
    script += "}\n";
    AnalysisEngine ae = AnalysisEngineFactory.createEngine(RutaEngine.class,
            RutaEngine.PARAM_VAR_NAMES, new String[] { "typeVar" }, RutaEngine.PARAM_VAR_VALUES,
            new String[] { "TruePositive" }, RutaEngine.PARAM_RULES, script);
    RutaEngine engine = (RutaEngine) FieldUtils.readField(ae, "mAnalysisComponent", true);

    
    TypeUsageInformation typeUsageInformation = (TypeUsageInformation) FieldUtils.readField(engine,
            "typeUsageInformation", true);

    List<String> mentionedTypes = typeUsageInformation.getMentionedTypes();
    Collections.sort(mentionedTypes);
    Assert.assertEquals(Arrays.asList("CW", "Document", "Document", "FalsePositive", "SW", "SW.ct",
            "TruePositive","WS", "org", "org.apache", "org.apache.uima", "org.apache.uima.ruta",
            "org.apache.uima.ruta.type", "org.apache.uima.ruta.type.FalseNegative", 
            "uima.tcas.DocumentAnnotation"), mentionedTypes);

    CAS cas = RutaTestUtils.getCAS("This is a test.");
    JCas jcas = cas.getJCas();
    ae.process(jcas);
    
    Collection<String> usedTypes = typeUsageInformation.getUsedTypes();
    List<String> usedTypesList = new ArrayList<>(usedTypes);
    Collections.sort(usedTypesList);;
    Assert.assertEquals(Arrays.asList("org.apache.uima.ruta.type.CW",
            "org.apache.uima.ruta.type.FalseNegative", "org.apache.uima.ruta.type.FalsePositive",
            "org.apache.uima.ruta.type.SW", "org.apache.uima.ruta.type.TruePositive",
            "org.apache.uima.ruta.type.WS", "uima.tcas.DocumentAnnotation"), usedTypesList);
    
  }

}
