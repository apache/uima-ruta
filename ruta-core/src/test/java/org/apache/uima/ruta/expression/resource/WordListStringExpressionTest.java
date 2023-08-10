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

package org.apache.uima.ruta.expression.resource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.util.InvalidXMLException;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

public class WordListStringExpressionTest {


  @Test
  public void test() throws Exception {
    String document = "1 0 0 text 2 0 0.";

    String script="STRING s = \"org/apache/uima/ruta/action/\";\n";
    script +="WORDLIST wl = s + \"MarkFastTestList.txt\";\n";
    script +="MARKFAST(T1,wl);\n";

    CAS cas = executeAnalysis(document, script);
    
    RutaTestUtils.assertAnnotationsEquals(cas, 1, 2, "1 0 0", "2 0 0");

  }
  
  @Test
  public void testInBlock() throws Exception {
    String document = "1 0 0 text 2 0 0.";

    String script="STRING s = \"org/apache/uima/ruta/action/\";\n";
    script +="BLOCK(block) Document{}{\n";
    script +="WORDLIST wl = s + \"MarkFastTestList.txt\";\n";
    script +="MARKFAST(T1,wl);\n";
    script +="}\n";

    CAS cas = executeAnalysis(document, script);
    
    RutaTestUtils.assertAnnotationsEquals(cas, 1, 2, "1 0 0", "2 0 0");

  }

  private CAS executeAnalysis(String document, String script)
          throws ResourceInitializationException, IOException, InvalidXMLException, ResourceConfigurationException, AnalysisEngineProcessException, URISyntaxException, SAXException {

    Map<String, Object> paramMap = new HashMap<>();
    paramMap.put(RutaEngine.PARAM_DICT_REMOVE_WS, true);

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script, paramMap);

    return cas;
  }

}
