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
import java.net.URISyntaxException;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.seed.TextSeeder;
import org.apache.uima.util.InvalidXMLException;
import org.junit.Ignore;
import org.junit.Test;

public class ForEachBlockTest {

  private String text = "Some text 4 more text.";

  @Test
  public void testDefault() {

    String script = getForEachScript();

    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(text);
      Ruta.apply(cas, script);
    } catch (Exception e) {
      e.printStackTrace();
    }

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "4");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "4");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "4");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 1, ".");
    RutaTestUtils.assertAnnotationsEquals(cas, 5, 1, "text");
    RutaTestUtils.assertAnnotationsEquals(cas, 6, 1, "4");
    RutaTestUtils.assertAnnotationsEquals(cas, 7, 1, "Some text");
    RutaTestUtils.assertAnnotationsEquals(cas, 8, 1, "4");
    RutaTestUtils.assertAnnotationsEquals(cas, 9, 1, "Some");
    RutaTestUtils.assertAnnotationsEquals(cas, 10, 1, "4");

    cas.release();
  }

  @Test
  @Ignore
  public void testPerformance()
          throws ResourceInitializationException, InvalidXMLException, IOException,
          AnalysisEngineProcessException, ResourceConfigurationException, URISyntaxException {

    int lines = 10000;
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < lines; i++) {
      sb.append(text);
      sb.append("\n");
    }

    TextSeeder textSeeder = new TextSeeder();
    CAS cas = RutaTestUtils.getCAS(sb.toString());
    textSeeder.seed(cas.getDocumentText(), cas);
    
    long startOriginal = System.currentTimeMillis();
    Ruta.apply(cas, getOriginalScript());
    long endOriginal = System.currentTimeMillis();
    System.out.println("BLOCK: " + (endOriginal - startOriginal)+"ms");
    
    cas.reset();
    cas.setDocumentText(sb.toString());
    textSeeder.seed(cas.getDocumentText(), cas);
    
    long startForEach = System.currentTimeMillis();
    Ruta.apply(cas, getForEachScript());
    long endForEach = System.currentTimeMillis();
    System.out.println("FOREACH: " + (endForEach - startForEach)+"ms");
    
    cas.release();
    
  }

  private String getForEachScript() {
    String script = "";
    script += "FOREACH(num) NUM{}{\n";
    script += "num{-> T1};\n";
    script += "num{-> T2} SW;\n";
    script += "num{-> T3} # PERIOD{-> T4};\n";
    script += "SW{-> T5} num{-> T6};\n";
    script += "W+{-> T7} num{-> T8};\n";
    script += "CW{-> T9} # num{-> T10};\n";
    script += "}";
    return script;
  }

  private String getOriginalScript() {
    String script = "";
    script += "@NUM{-> T1};\n";
    script += "@NUM{-> T2} SW;\n";
    script += "@NUM{-> T3} # PERIOD{-> T4};\n";
    script += "SW{-> T5} @NUM{-> T6};\n";
    script += "W+{-> T7} @NUM{-> T8};\n";
    script += "CW{-> T9} # @NUM{-> T10};\n";
    return script;
  }

}
