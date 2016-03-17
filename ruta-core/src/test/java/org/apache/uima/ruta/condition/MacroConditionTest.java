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

package org.apache.uima.ruta.condition;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.util.InvalidXMLException;
import org.junit.Test;

public class MacroConditionTest {

  @Test
  public void test() {
    String document = "Test";
    String script = "INT j;\n";
    script += "CONDITION macro(TYPE t, INT i) = IS(t),(j==i);\n";
    script += "Document{-> T1, j=1};\n";
    script += "Document{macro(T1,0)-> T2};\n";
    script += "Document{macro(T1,1)-> T3};\n";

    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document);
      Ruta.apply(cas, script);
    } catch (Exception e) {
      e.printStackTrace();
    }

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Test");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "Test");

    cas.release();
  }

  @Test
  public void testNoArgs() {
    String document = "This is a Test.";
    String script = "INT j;\n";
    script += "CONDITION macro() = OR(IS(CW),IS(PERIOD));\n";
    script += "ANY{macro()-> T1};\n";

    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document);
      Ruta.apply(cas, script);
    } catch (Exception e) {
      e.printStackTrace();
    }

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 3, "This", "Test", ".");

    cas.release();
  }

  @Test
  public void testVariable()
          throws ResourceInitializationException, InvalidXMLException, IOException,
          AnalysisEngineProcessException, ResourceConfigurationException, URISyntaxException {
    String document = "This is a Test.";
    String script = "INT j;\n";
    script += "CONDITION cc(VAR INT var) = TOTALCOUNT(W,1,1000,var);\n";
    script += "Document{cc(j), (j>2) ->T1};\n";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "This is a Test.");

    cas.release();
  }
}
