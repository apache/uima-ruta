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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.engine.RutaTestUtils.TestFeature;
import org.junit.Test;

public class OutOfWindowTest {
  @Test
  public void testSequentialInBlock() {

    CAS cas = RutaTestUtils.processTestScript(this.getClass());

    RutaTestUtils.assertAnnotationsEquals(cas, 3, 3, "Peter, Jochen", "Jochen, Flo", "Lena, Beate");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 1, "Jochen, Flo");

    cas.release();
  }
  
  @Test
  public void testFeatureMatch() {
    String document = "First Sentence. Second Sentence.";
    String script = "";
    script += "(# PERIOD){-> T1};";
    script += "T1 #{-> T1};";
    
    script += "CW.ct==\"First\"{-> GATHER(Struct, \"next\" = 3)} # CW.ct==\"Second\";";
    script += "T1->{Struct.next{REGEXP(\"Sec.*\") -> T2};};";
    script += "T1<-{Struct.next{PARTOF(CW) -> T3};};";
    
    Map<String, String> complexTypes = new TreeMap<String, String>();
    String typeName = "org.apache.uima.Struct";
    complexTypes.put(typeName, "uima.tcas.Annotation");

    Map<String, List<TestFeature>> features = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    features.put(typeName, list);
    String fn1 = "next";
    list.add(new TestFeature(fn1, "", "uima.tcas.Annotation"));
    
    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document, complexTypes, features);
      Ruta.apply(cas, script);
    } catch (Exception e) {
      throw new AssertionError("No exception is expected when applying the rules.", e);
    }
   
  
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "Second");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "Second");

    cas.release();
  }
  
  @Test
  public void testSequentialAfterOutOfWindowFeatureMatch() {
    String document = "First Sentence. Second one.";
    String script = "";
    script += "(# PERIOD){-> T1};";
    script += "T1 #{-> T1};";
    
    script += "CW.ct==\"First\"{-> GATHER(Struct, \"next\" = 3)} # CW.ct==\"Second\";";
    script += "T1->{Struct.next{REGEXP(\"Sec.*\")} ANY{-> T2};};";
    script += "T1<-{Struct.next{PARTOF(CW)} ANY{-> T3};};";
    
    Map<String, String> complexTypes = new TreeMap<String, String>();
    String typeName = "org.apache.uima.Struct";
    complexTypes.put(typeName, "uima.tcas.Annotation");

    Map<String, List<TestFeature>> features = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    features.put(typeName, list);
    String fn1 = "next";
    list.add(new TestFeature(fn1, "", "uima.tcas.Annotation"));
    
    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document, complexTypes, features);
      Ruta.apply(cas, script);
    } catch (Exception e) {
      throw new AssertionError("No exception is expected when applying the rules.", e);
    }
   
  
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 0);

    cas.release();
  }
  
}
