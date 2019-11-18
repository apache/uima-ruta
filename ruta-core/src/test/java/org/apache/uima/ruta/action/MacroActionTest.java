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
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.engine.RutaTestUtils.TestFeature;
import org.junit.Ignore;
import org.junit.Test;

public class MacroActionTest {

  @Test
  public void test() throws Exception {
    String document = "Test";
    String script = "INT j;\n";
    script += "ACTION macro(TYPE t, INT inc) = MARK(t),ASSIGN(j,j+inc);\n";
    script += "Document{-> macro(T1,1)};\n";
    script += "Document{(j>0)->T2};\n";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Test");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "Test");
  }

  @Test
  public void testNoArgs() throws Exception {
    String document = "Test.";
    String script = "INT j;\n";
    script += "ACTION macro() = MARK(T1), MARK(T2);\n";
    script += "W{-> macro()};\n";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Test");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "Test");
  }

  @Test
  public void testVariable() throws Exception {
    String document = "Test";
    String script = "INT j;\n";
    script += "ACTION inc(VAR INT var, INT i) = ASSIGN(var,var+i);\n";
    script += "Document{-> inc(j,5)};\n";
    script += "Document{(j==5)->T1};\n";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Test");
  }

  @Test
  public void testLabel() throws Exception {
    String document = "Test";
    String script = "";
    script += "ACTION doit() = MARK(T1);\n";
    script += "CW{-> t:doit()}-> {t{->T2};};\n";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Test");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "Test");
  }

  @Test
  @Ignore
  public void testShareSameNameArgumentAndLabel() throws Exception {
    String document = "Day 5";
    String script = "ACTION CreateDate(ANNOTATION day) = CREATE(Date, \"day\"=day);\n";
    script += "(CW day:NUM){-> CreateDate(day)};\n";

    Map<String, String> typeMap = new TreeMap<String, String>();
    String typeName1 = "Date";
    typeMap.put(typeName1, "uima.tcas.Annotation");

    Map<String, List<TestFeature>> featureMap = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    featureMap.put(typeName1, list);
    String fn1 = "day";
    list.add(new TestFeature(fn1, "", "uima.tcas.Annotation"));

    CAS cas = RutaTestUtils.getCAS(document, typeMap, featureMap);
    Ruta.apply(cas, script);

    Type t = null;
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;

    t = cas.getTypeSystem().getType(typeName1);
    Feature feat = t.getFeatureByBaseName(fn1);

    ai = cas.getAnnotationIndex(t);

    iterator = ai.iterator();

    AnnotationFS nextFS = iterator.next();

    FeatureStructure fv1 = nextFS.getFeatureValue(feat);
    assertNotNull(fv1);

    assertEquals("5", ((AnnotationFS) fv1).getCoveredText());
  }

}
