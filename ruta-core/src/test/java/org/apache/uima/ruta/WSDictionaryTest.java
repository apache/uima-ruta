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

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.engine.RutaTestUtils.TestFeature;
import org.junit.Test;

public class WSDictionaryTest {

  @Test
  public void test() {
    String document = "Peter Kluegl, Marshall Schor, Joern Kottmann\n";
    document += "PeterKluegl, MarshallSchor, JoernKottmann\n";
    document += "Peter<x>Kluegl, Marshall<x>Schor, Joern<x>Kottmann\n";
    String script = "WORDLIST list = 'org/apache/uima/ruta/WSDictionaryTestList.txt';";
    script += "MARKFAST(T1, list);";
    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document);
      Ruta.apply(cas, script);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    RutaTestUtils.assertAnnotationsEquals(cas, 1, 9, "Peter Kluegl", "Marshall Schor", "Joern Kottmann", "PeterKluegl",
            "MarshallSchor", "JoernKottmann", "Peter<x>Kluegl", "Marshall<x>Schor", "Joern<x>Kottmann");

    cas.release();
  }
  
  @Test
  public void testDictRemoveWS() {
    String document = "Peter Kluegl, Marshall Schor, Joern Kottmann\n";
    document += "PeterKluegl, MarshallSchor, JoernKottmann\n";
    document += "Peter<x>Kluegl, Marshall<x>Schor, Joern<x>Kottmann\n";
    String script = "WORDLIST list = 'org/apache/uima/ruta/WSDictionaryTestList.txt';";
    script += "MARKFAST(T1, list, true, 0, false);";
    CAS cas = null;
    Map<String,Object> map = new HashMap<String, Object>();
    map.put(RutaEngine.PARAM_DICT_REMOVE_WS, true);
    try {
      cas = RutaTestUtils.getCAS(document);
      Ruta.apply(cas, script, map);
    } catch (Exception e) {
      e.printStackTrace();
    }

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 9, "Peter Kluegl", "Marshall Schor", "Joern Kottmann", "PeterKluegl",
            "MarshallSchor", "JoernKottmann", "Peter<x>Kluegl", "Marshall<x>Schor", "Joern<x>Kottmann");

    cas.release();
  }
  
  @Test
  public void testTableWithWS() {
    String document = "Peter Kluegl, Marshall Schor, Joern Kottmann\n";
    document += "PeterKluegl, MarshallSchor, JoernKottmann\n";
    document += "Peter<x>Kluegl, Marshall<x>Schor, Joern<x>Kottmann\n";
    String script = "WORDTABLE table = 'org/apache/uima/ruta/table2.csv';";
    script += "MARKTABLE(Struct, 1, table, true, 0, \"-.,\", 10, \"name\" = 1, \"system\" = 2);";
    
    Map<String, String> complexTypes = new TreeMap<String, String>();
    String typeName = "org.apache.uima.Struct";
    complexTypes.put(typeName, "uima.tcas.Annotation");
    
    Map<String, List<TestFeature>> features = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    features.put(typeName, list);
    String fn1 = "name";
    list.add(new TestFeature(fn1, "", "uima.cas.String"));
    String fn2 = "system";
    list.add(new TestFeature(fn2, "", "uima.cas.String"));
    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document, complexTypes, features);
      Ruta.apply(cas, script);
    } catch (Exception e) {
      e.printStackTrace();
    }

    Type t = null;
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;
    AnnotationFS next = null;
    String v1 = null;
    String v2 = null;
    t = cas.getTypeSystem().getType(typeName);
    Feature f1 = t.getFeatureByBaseName(fn1);
    Feature f2 = t.getFeatureByBaseName(fn2);
    ai = cas.getAnnotationIndex(t);
    
    assertEquals(9, ai.size());
    iterator = ai.iterator();
    
    next = iterator.next();
    v1 = next.getStringValue(f1);
    v2 = next.getStringValue(f2);
    assertEquals("Peter Kluegl", v1);
    assertEquals("UIMA Ruta", v2);
    
    next = iterator.next();
    v1 = next.getStringValue(f1);
    v2 = next.getStringValue(f2);
    assertEquals("Marshall Schor", v1);
    assertEquals("UIMA Core", v2);
    
    next = iterator.next();
    v1 = next.getStringValue(f1);
    v2 = next.getStringValue(f2);
    assertEquals("Joern Kottmann", v1);
    assertEquals("CAS Editor", v2);
    
    next = iterator.next();
    v1 = next.getStringValue(f1);
    v2 = next.getStringValue(f2);
    assertEquals("Peter Kluegl", v1);
    assertEquals("UIMA Ruta", v2);
    
    next = iterator.next();
    v1 = next.getStringValue(f1);
    v2 = next.getStringValue(f2);
    assertEquals("Marshall Schor", v1);
    assertEquals("UIMA Core", v2);
    
    next = iterator.next();
    v1 = next.getStringValue(f1);
    v2 = next.getStringValue(f2);
    assertEquals("Joern Kottmann", v1);
    assertEquals("CAS Editor", v2);
    
    next = iterator.next();
    v1 = next.getStringValue(f1);
    v2 = next.getStringValue(f2);
    assertEquals("Peter Kluegl", v1);
    assertEquals("UIMA Ruta", v2);
    
    next = iterator.next();
    v1 = next.getStringValue(f1);
    v2 = next.getStringValue(f2);
    assertEquals("Marshall Schor", v1);
    assertEquals("UIMA Core", v2);
    
    next = iterator.next();
    v1 = next.getStringValue(f1);
    v2 = next.getStringValue(f2);
    assertEquals("Joern Kottmann", v1);
    assertEquals("CAS Editor", v2);

    cas.release();
  }
}
