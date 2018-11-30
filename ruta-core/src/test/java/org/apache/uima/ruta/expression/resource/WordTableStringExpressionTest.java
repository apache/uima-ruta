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

import static org.junit.Assert.assertEquals;

import java.util.*;

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

public class WordTableStringExpressionTest {


  @Test
  public void test() throws Exception {
    String document = "Kluegl Schor Kottmann";

    String script="STRING s = \"org/apache/uima/ruta/action/\";\n";
    script +="WORDTABLE table = s + \"table.csv\";\n";
    script +="MARKTABLE(Person, 1, table, true, 0, \"-.,\", 10, \"firstname\" = 2, \"system\" = 3);\n";
    
    Map<String, String> complexTypes = new TreeMap<String, String>();
    String typeName = "org.apache.uima.Person";
    complexTypes.put(typeName, "uima.tcas.Annotation");
    
    Map<String, List<TestFeature>> features = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    features.put(typeName, list);
    String fn1 = "firstname";
    list.add(new TestFeature(fn1, "", "uima.cas.String"));
    String fn2 = "system";
    list.add(new TestFeature(fn2, "", "uima.cas.String"));

    Map<String, Object> map = new HashMap();
    map.put(RutaEngine.PARAM_DICT_REMOVE_WS, true);

    CAS cas = RutaTestUtils.getCAS(document, complexTypes, features);
    Ruta.apply(cas, script, map);
    
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
    
    assertEquals(3, ai.size());
    iterator = ai.iterator();
    
    next = iterator.next();
    v1 = next.getStringValue(f1);
    v2 = next.getStringValue(f2);
    assertEquals("Peter", v1);
    assertEquals("Ruta", v2);
    
    next = iterator.next();
    v1 = next.getStringValue(f1);
    v2 = next.getStringValue(f2);
    assertEquals("Marshall", v1);
    assertEquals("UIMA", v2);
    
    next = iterator.next();
    v1 = next.getStringValue(f1);
    v2 = next.getStringValue(f2);
    assertEquals("Joern", v1);
    assertEquals("CAS Editor", v2);
    
    cas.release();    

  }
  
  @Test
  public void testInBlock() throws Exception {
    String document = "Kluegl Schor Kottmann";

    String script="STRING s = \"org/apache/uima/ruta/action/\";\n";
    script +="BLOCK(block) Document{}{\n";
    script +="WORDTABLE table = s + \"table.csv\";\n";
    script +="MARKTABLE(Person, 1, table, true, 0, \"-.,\", 10, \"firstname\" = 2, \"system\" = 3);\n";
    script +="}\n";
    
    Map<String, String> complexTypes = new TreeMap<String, String>();
    String typeName = "org.apache.uima.Person";
    complexTypes.put(typeName, "uima.tcas.Annotation");
    
    Map<String, List<TestFeature>> features = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    features.put(typeName, list);
    String fn1 = "firstname";
    list.add(new TestFeature(fn1, "", "uima.cas.String"));
    String fn2 = "system";
    list.add(new TestFeature(fn2, "", "uima.cas.String"));

    Map<String, Object> map = new HashMap<>();
    map.put(RutaEngine.PARAM_DICT_REMOVE_WS, true);

    CAS cas = RutaTestUtils.getCAS(document, complexTypes, features);
    Ruta.apply(cas, script, map);
    
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
    
    assertEquals(3, ai.size());
    iterator = ai.iterator();
    
    next = iterator.next();
    v1 = next.getStringValue(f1);
    v2 = next.getStringValue(f2);
    assertEquals("Peter", v1);
    assertEquals("Ruta", v2);
    
    next = iterator.next();
    v1 = next.getStringValue(f1);
    v2 = next.getStringValue(f2);
    assertEquals("Marshall", v1);
    assertEquals("UIMA", v2);
    
    next = iterator.next();
    v1 = next.getStringValue(f1);
    v2 = next.getStringValue(f2);
    assertEquals("Joern", v1);
    assertEquals("CAS Editor", v2);
    
    cas.release();    

  }
  
}
