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

import static org.assertj.core.api.Assertions.assertThat;

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
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.engine.RutaTestUtils.TestFeature;
import org.junit.jupiter.api.Test;

public class GatherTest {

  @Test
  public void test() throws Exception {
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

    CAS cas = RutaTestUtils.process(namespace + "/" + name + RutaEngine.SCRIPT_FILE_EXTENSION,
            namespace + "/" + name + ".txt", 50, false, false, complexTypes, features,
            namespace + "/");
    Type t = null;
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;
    AnnotationFS next = null;
    AnnotationFS v1 = null;
    AnnotationFS v2 = null;
    t = cas.getTypeSystem().getType(typeName);
    Feature f1 = t.getFeatureByBaseName(fn1);
    Feature f2 = t.getFeatureByBaseName(fn2);
    ai = cas.getAnnotationIndex(t);
    assertThat(ai.size()).isEqualTo(1);
    iterator = ai.iterator();
    next = iterator.next();
    v1 = (AnnotationFS) next.getFeatureValue(f1);
    v2 = (AnnotationFS) next.getFeatureValue(f2);
    assertThat(v1.getCoveredText()).isEqualTo("A");
    assertThat(v2.getCoveredText()).isEqualTo("B");

  }

  @Test
  public void testOptionalMatch() throws Exception {
    String document = "A X C";
    String script = "";
    script += "W{REGEXP(\"A\")->MARK(T1)};";
    script += "W{REGEXP(\"B\")->MARK(T2)};";
    script += "T1 T2?{-> GATHER(C, 1, 2, \"a\" = 1, \"b\" = 2)};";
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
    CAS cas = RutaTestUtils.getCAS(document, complexTypes, features);
    Ruta.apply(cas, script);

    Type t = null;
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;
    AnnotationFS next = null;
    AnnotationFS v1 = null;
    AnnotationFS v2 = null;
    t = cas.getTypeSystem().getType(typeName);
    Feature f1 = t.getFeatureByBaseName(fn1);
    Feature f2 = t.getFeatureByBaseName(fn2);
    ai = cas.getAnnotationIndex(t);
    assertThat(ai.size()).isEqualTo(1);
    iterator = ai.iterator();
    next = iterator.next();
    v1 = (AnnotationFS) next.getFeatureValue(f1);
    v2 = (AnnotationFS) next.getFeatureValue(f2);
    assertThat(v1.getCoveredText()).isEqualTo("A");
    assertThat(v2).isEqualTo(null);

    cas.release();
  }
}
