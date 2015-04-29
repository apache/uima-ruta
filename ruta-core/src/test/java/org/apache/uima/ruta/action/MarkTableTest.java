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

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.engine.RutaTestUtils.TestFeature;
import org.junit.Test;

public class MarkTableTest {

  @Test
  public void test() {
    String name = this.getClass().getSimpleName();
    String namespace = this.getClass().getPackage().getName().replaceAll("\\.", "/");

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
    
    CAS cas = null;
    try {
      cas = RutaTestUtils.process(namespace + "/" + name + RutaEngine.SCRIPT_FILE_EXTENSION, namespace + "/" + name
              + ".txt", 50, false, false, complexTypes, features, namespace + "/");
    } catch (Exception e) {
      e.printStackTrace();
      assert (false);
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
    
    assertEquals(6, ai.size());
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
