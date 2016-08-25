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

package org.apache.uima.ruta.block;

import java.io.IOException;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.util.InvalidXMLException;
import org.junit.Test;

public class RutaScriptBlockTest {

  @Test
  public void testInnerDocumentMatch() {
    String document = "Some text";
    String script = "";
    script += "CW{ -> CREATE(RutaAnnotation, \"score\"=1)};";
    script += "CW{ -> CREATE(RutaAnnotation, \"score\"=2)};";
    script += "BLOCK(forEach) RutaAnnotation.score==1{}{";
    script += "Document{-> T1};";
    script += "MARK(T2);";
    script += "RutaAnnotation{-> T3};";
    script += "}";

    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document);
      Ruta.apply(cas, script);
    } catch (Exception e) {
      e.printStackTrace();
    }

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Some");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "Some");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 2, "Some", "Some");

    cas.release();
  }

  @Test
  public void testExternalBlockCall() throws ResourceInitializationException, InvalidXMLException,
          IOException, AnalysisEngineProcessException, CASException {
    String script = "SCRIPT org.apache.uima.ruta.ScriptWithStackedBlocks;";
    script += "(# PERIOD){-> T3} (# PERIOD){->T4};";
    script += "T3{-> CALL(ScriptWithStackedBlocks.First)};";
    script += "T4{-> CALL(ScriptWithStackedBlocks.Second)};";
    CAS cas = RutaTestUtils.getCAS("Some text. More stuff.");
    Ruta.applyRule(cas.getJCas(), script, RutaEngine.PARAM_ADDITIONAL_SCRIPTS,
            new String[] { "org.apache.uima.ruta.ScriptWithStackedBlocks" });
    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Some");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 2, "text", "stuff");
  }

  @Test
  public void testConditionMacro() throws Exception {
    String script = "CONDITION isSmall() = REGEXP(\".\");\n";
    script += "BLOCK(num) NUM{} {\n";
    script += "Document{isSmall()-> T1};";
    script += "}";

    CAS cas = RutaTestUtils.getCAS("1 22 333");
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "1");
    cas.release();
  }
  
}
