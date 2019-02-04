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

package org.apache.uima.ruta.action;

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
import org.xml.sax.SAXException;

public class MacroActionTest {

  @Test
  public void test() {
    String document = "Test";
    String script = "INT j;\n";
    script += "ACTION macro(TYPE t, INT inc) = MARK(t),ASSIGN(j,j+inc);\n";
    script += "Document{-> macro(T1,1)};\n";
    script += "Document{(j>0)->T2};\n";

    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document);
      Ruta.apply(cas, script);
    } catch (Exception e) {
      e.printStackTrace();
    }

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Test");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "Test");

    cas.release();
  }

  @Test
  public void testNoArgs() {
    String document = "Test.";
    String script = "INT j;\n";
    script += "ACTION macro() = MARK(T1), MARK(T2);\n";
    script += "W{-> macro()};\n";

    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document);
      Ruta.apply(cas, script);
    } catch (Exception e) {
      e.printStackTrace();
    }

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Test");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "Test");

    cas.release();
  }

  @Test
  public void testVariable() throws ResourceInitializationException, InvalidXMLException,
          IOException, AnalysisEngineProcessException, ResourceConfigurationException,
          URISyntaxException, SAXException {
    String document = "Test";
    String script = "INT j;\n";
    script += "ACTION inc(VAR INT var, INT i) = ASSIGN(var,var+i);\n";
    script += "Document{-> inc(j,5)};\n";
    script += "Document{(j==5)->T1};\n";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Test");

    cas.release();
  }

}
