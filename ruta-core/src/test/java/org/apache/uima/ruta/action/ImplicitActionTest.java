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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.FeatureMatch1Test;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.engine.RutaTestUtils.TestFeature;
import org.junit.jupiter.api.Test;

public class ImplicitActionTest {

  @Test
  public void test() throws Exception {
    String name = this.getClass().getSimpleName();
    String namespace = this.getClass().getPackage().getName().replaceAll("\\.", "/");

    Map<String, String> complexTypes = new HashMap<String, String>();
    Map<String, List<TestFeature>> features = new TreeMap<String, List<TestFeature>>();
    String typeNameA = "org.apache.uima.ruta.FeatureMatchTest.A";
    String typeNameB = "org.apache.uima.ruta.FeatureMatchTest.B";
    String typeNameC = "org.apache.uima.ruta.FeatureMatchTest.C";
    String typeNameD = "org.apache.uima.ruta.FeatureMatchTest.D";
    complexTypes.put(typeNameA, "uima.tcas.Annotation");
    complexTypes.put(typeNameB, typeNameD);
    complexTypes.put(typeNameC, typeNameD);
    complexTypes.put(typeNameD, "uima.tcas.Annotation");
    List<TestFeature> listA = new ArrayList<RutaTestUtils.TestFeature>();
    features.put(typeNameA, listA);
    String fnab = "ab";
    listA.add(new TestFeature(fnab, "", typeNameB));
    String fnac = "ac";
    listA.add(new TestFeature(fnac, "", typeNameC));
    List<TestFeature> listB = new ArrayList<RutaTestUtils.TestFeature>();
    features.put(typeNameB, listB);
    String fnbc = "bc";
    listB.add(new TestFeature(fnbc, "", typeNameC));
    List<TestFeature> listC = new ArrayList<RutaTestUtils.TestFeature>();
    features.put(typeNameC, listC);
    String fnci = "ci";
    listC.add(new TestFeature(fnci, "", "uima.cas.Integer"));
    String fncb = "cb";
    listC.add(new TestFeature(fncb, "", "uima.cas.Boolean"));
    List<TestFeature> listD = new ArrayList<RutaTestUtils.TestFeature>();
    features.put(typeNameD, listD);
    String fnds = "ds";
    listD.add(new TestFeature(fnds, "", "uima.cas.String"));

    CAS cas = RutaTestUtils.process(namespace + "/" + name + RutaEngine.SCRIPT_FILE_EXTENSION,
            FeatureMatch1Test.class.getName().replaceAll("\\.", "/") + ".txt", 50, false, false,
            complexTypes, features, null);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 3, "Peter Kluegl", "Joern Kottmann",
            "Marshall Schor");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 3, "Peter", "Joern", "Marshall");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 3, "Peter", "Joern", "Marshall");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 3, "Peter", "Joern", "Marshall");

    cas.release();
  }

  @Test
  public void testChangeOffsets() throws Exception {
    String text = "text 2 3 x 4 1";
    String script = "";
    script += "NUM{->T1};";
    script += "first:T1{-> UNMARK(T1)} T1{-> T1.begin = first.begin};";
    script += "T1{-> UNMARK(T1)} SW T1{->T2};";
    CAS cas = RutaTestUtils.getCAS(text);
    Ruta.apply(cas, script);
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "4 1");
    cas.release();
  }

  @Test
  public void testBooleanListAssignment() throws Exception {
    String document = "This is a test.";

    String script = "";
    script += "BOOLEANLIST bl;";
    script += "Document { -> bl = {true}};";
    script += "Document{CONTAINS(bl, true) -> T1};";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "This is a test.");
  }

  @Test
  public void testIntListAssignment() throws Exception {
    String document = "This is a test.";

    String script = "";
    script += "INTLIST il;";
    script += "Document { -> il = {1,2,3}};";
    script += "Document{CONTAINS(il, 2) -> T1};";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "This is a test.");
  }

  @Test
  public void testStringListAssignment() throws Exception {
    String document = "This is a test.";

    String script = "";
    script += "STRINGLIST sl;";
    script += "Document { -> sl = {\"a\",\"b\"}};";
    script += "Document{CONTAINS(sl, \"a\") -> T1};";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "This is a test.");
  }

  @Test
  public void testTypeListAssignment() throws Exception {
    String document = "This is a test.";

    String script = "";
    script += "TYPELIST tl;";
    script += "Document { -> tl = {CW, CAP}};";
    script += "Document{CONTAINS(tl, CW) -> T1};";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "This is a test.");
  }
}
