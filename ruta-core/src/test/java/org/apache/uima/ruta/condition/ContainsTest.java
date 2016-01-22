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
import java.util.Map;
import java.util.TreeMap;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.util.InvalidXMLException;
import org.junit.Ignore;
import org.junit.Test;

public class ContainsTest {

  @Test
  @Ignore
  public void test() {

    CAS cas = RutaTestUtils.processTestScript(this.getClass());

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 3, "A single sentence", "And here is another one",
            "Testing the CONTAINS condition of Ruta");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 3, "A single sentence", "And here is another one",
            "Testing the CONTAINS condition of Ruta");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "Testing the CONTAINS condition of Ruta");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 2, "A single sentence", "Testing the CONTAINS condition of Ruta");

    cas.release();
  }
  
  @Test
  public void testAlias() throws ResourceInitializationException, InvalidXMLException, IOException, AnalysisEngineProcessException, ResourceConfigurationException, URISyntaxException {
    String document = "1 some text; 2 some text; 3 some text;";
    
    String script = "IMPORT PACKAGE * FROM org.apache.uima.ruta.ImportStatementsTestTypeSystemWithManyPackages AS ruta;";
    script += "(NUM # PM){-> ruta.Type1, T1};";
    script += "Document{CONTAINS(ruta.Type1) -> T2};";
    
    Map<String, String> typeMap = new TreeMap<String, String>();
    String typeName = "org.apache.uima.ruta.other3.Type1";
    typeMap.put(typeName, "uima.tcas.Annotation");
    typeName = "org.apache.uima.ruta.other4.Type2";
    typeMap.put(typeName, "uima.tcas.Annotation");

    CAS cas = RutaTestUtils.getCAS(document, typeMap, null);
    Ruta.apply(cas, script);
    
    RutaTestUtils.assertAnnotationsEquals(cas, 1, 3, "1 some text;", "2 some text;", "3 some text;");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, document);
    
  }
  
  
}
