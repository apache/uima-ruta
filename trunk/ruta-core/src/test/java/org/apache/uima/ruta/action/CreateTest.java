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
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.engine.RutaTestUtils.TestFeature;
import org.junit.Test;

public class CreateTest {

  @Test
  public void test() {
    String name = this.getClass().getSimpleName();
    String namespace = this.getClass().getPackage().getName().replaceAll("\\.", "/");

    Map<String, String> complexTypes = new TreeMap<String, String>();
    String typeName = "org.apache.uima.C";
    complexTypes.put(typeName, "uima.tcas.Annotation");

    Map<String, List<TestFeature>> features = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    features.put(typeName, list);
    String fn1 = "a";
    list.add(new TestFeature(fn1, "", "uima.tcas.Annotation"));
    String fn2 = "b";
    list.add(new TestFeature(fn2, "", "uima.tcas.Annotation"));
    String fn3 = "count";
    list.add(new TestFeature(fn3, "", "uima.cas.Integer"));

    CAS cas = null;
    try {
      cas = RutaTestUtils.process(namespace + "/" + name + RutaEngine.SCRIPT_FILE_EXTENSION,
              namespace + "/" + name + ".txt", 50, false, false, complexTypes, features, namespace
                      + "/");
    } catch (Exception e) {
      e.printStackTrace();
      assert (false);
    }
    Type t = null;
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;
    AnnotationFS next = null;
    AnnotationFS v1 = null;
    AnnotationFS v2 = null;
    t = cas.getTypeSystem().getType(typeName);
    Feature f1 = t.getFeatureByBaseName(fn1);
    Feature f2 = t.getFeatureByBaseName(fn2);
    Feature f3 = t.getFeatureByBaseName(fn3);
    ai = cas.getAnnotationIndex(t);
    assertEquals(1, ai.size());
    iterator = ai.iterator();
    next = iterator.next();
    v1 = (AnnotationFS) next.getFeatureValue(f1);
    v2 = (AnnotationFS) next.getFeatureValue(f2);
    int intValue = next.getIntValue(f3);
    assertEquals("A", v1.getCoveredText());
    assertEquals("B", v2.getCoveredText());
    assertEquals(2, intValue);

    cas.release();
  }

  @Test
  public void testFeature() {
    String document = "Test.";
    String script = "";
    script += "W{-> CREATE(Inner, \"word\" = W)};";
    script += "Inner{-> CREATE(Source, \"inner\" = Inner)};";
    script += "Source{-> CREATE(Target, \"word\" = Source.inner.word)};";

    Map<String, String> typeMap = new TreeMap<String, String>();
    String typeName1 = "Source";
    typeMap.put(typeName1, "uima.tcas.Annotation");
    String typeName2 = "Inner";
    typeMap.put(typeName2, "uima.tcas.Annotation");
    String typeName3 = "Target";
    typeMap.put(typeName3, "uima.tcas.Annotation");

    Map<String, List<TestFeature>> featureMap = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    featureMap.put(typeName1, list);
    String fn1 = "inner";
    list.add(new TestFeature(fn1, "", typeName2));

    list = new ArrayList<RutaTestUtils.TestFeature>();
    featureMap.put(typeName2, list);

    String fn2 = "word";
    list.add(new TestFeature(fn2, "", "uima.tcas.Annotation"));

    list = new ArrayList<RutaTestUtils.TestFeature>();
    featureMap.put(typeName3, list);

    String fn3 = "word";
    list.add(new TestFeature(fn3, "", "uima.tcas.Annotation"));

    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document, typeMap, featureMap);
      Ruta.apply(cas, script);
    } catch (Exception e) {
      e.printStackTrace();
    }

    Type t = null;
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;

    t = cas.getTypeSystem().getType(typeName3);
    Feature f3 = t.getFeatureByBaseName(fn3);
    ai = cas.getAnnotationIndex(t);
    assertEquals(1, ai.size());
    iterator = ai.iterator();
    AnnotationFS next = iterator.next();
    FeatureStructure fv1 = next.getFeatureValue(f3);
    assertNotNull(fv1);

    assertEquals("Test", ((AnnotationFS) fv1).getCoveredText());

    cas.release();
  }
}
