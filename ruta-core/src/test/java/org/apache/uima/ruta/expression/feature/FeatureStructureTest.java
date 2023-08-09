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

package org.apache.uima.ruta.expression.feature;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.fit.util.FSCollectionFactory;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.engine.RutaTestUtils.TestFeature;
import org.junit.jupiter.api.Test;
public class FeatureStructureTest {

  @Test
  public void testFeatureStructureFeature() throws Exception {

    String document = "Some text.";

    Map<String, String> typeMap = new TreeMap<String, String>();
    String typeName1 = "A1";
    typeMap.put(typeName1, "uima.tcas.Annotation");
    String typeName2 = "FS1";
    typeMap.put(typeName2, "uima.cas.TOP");

    Map<String, List<TestFeature>> featureMap = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    featureMap.put(typeName1, list);
    String fn1 = "fss";
    list.add(new TestFeature(fn1, "", "uima.cas.FSArray"));
    String fn2 = "fs";
    list.add(new TestFeature(fn2, "", "uima.cas.TOP"));

    list = new ArrayList<RutaTestUtils.TestFeature>();
    featureMap.put(typeName2, list);
    String fn3 = "fss";
    list.add(new TestFeature(fn3, "", "uima.cas.FSArray"));
    String fn4 = "fs";
    list.add(new TestFeature(fn4, "", "uima.cas.TOP"));
    String fn5 = "s";
    list.add(new TestFeature(fn5, "", "uima.cas.String"));

    CAS cas = RutaTestUtils.getCAS(document, typeMap, featureMap);
    Type type1 = cas.getTypeSystem().getType(typeName1);
    Feature type1fss = type1.getFeatureByBaseName(fn1);
    Feature type1fs = type1.getFeatureByBaseName(fn2);
    Type type2 = cas.getTypeSystem().getType(typeName2);
    Feature type2fss = type2.getFeatureByBaseName(fn3);
    Feature type2fs = type2.getFeatureByBaseName(fn4);
    Feature type2s = type2.getFeatureByBaseName(fn5);
    AnnotationFS a1 = cas.createAnnotation(type1, 0, 4);
    AnnotationFS a2 = cas.createAnnotation(type1, 5, 9);
    FeatureStructure fs1 = cas.createFS(type2);
    FeatureStructure fs2 = cas.createFS(type2);
    FSArray<FeatureStructure> fsArray1 = FSCollectionFactory.createFSArray(cas.getJCas(),
            new FeatureStructure[] { fs1, fs2 });
    fs1.setStringValue(type2s, "1");
    fs2.setStringValue(type2s, "2");
    fs1.setFeatureValue(type2fs, a2);
    fs2.setFeatureValue(type2fs, a1);
    fs1.setFeatureValue(type2fss, fsArray1);
    fs2.setFeatureValue(type2fss, fsArray1);
    a1.setFeatureValue(type1fs, fs1);
    a1.setFeatureValue(type1fss, fsArray1);
    a2.setFeatureValue(type1fs, fs2);
    a2.setFeatureValue(type1fss, fsArray1);

    cas.addFsToIndexes(a1);
    cas.addFsToIndexes(a2);

    StringBuilder script = new StringBuilder();
    script.append("Document{-> A1, A1.fs = i.fs}<-{i:A1 PERIOD;};");
    script.append("a:A1{IS(Document), a.fs.s == \"1\" -> T1};");
    script.append("a:A1{IS(Document), a.fs.s == \"2\" -> T2};");
    Ruta.apply(cas, script.toString());

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "Some text.");

  }

}
