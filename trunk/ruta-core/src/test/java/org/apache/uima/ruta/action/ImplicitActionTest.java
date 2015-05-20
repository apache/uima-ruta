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
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.engine.RutaTestUtils.TestFeature;
import org.junit.Test;

public class ImplicitActionTest {

  @Test
  public void test() {
    String name = this.getClass().getSimpleName();
    String namespace = this.getClass().getPackage().getName().replaceAll("\\.", "/");
    CAS cas = null;
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

    try {
      cas = RutaTestUtils.process(namespace + "/" + name + RutaEngine.SCRIPT_FILE_EXTENSION,
              FeatureMatch1Test.class.getName().replaceAll("\\.", "/") +".txt", 50, false, false, complexTypes, features, null);
    } catch (Exception e) {
      e.printStackTrace();
      assert (false);
    }    
    
    RutaTestUtils.assertAnnotationsEquals(cas, 1, 3, "Peter Kluegl", "Joern Kottmann", "Marshall Schor");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 3, "Peter", "Joern", "Marshall");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 3, "Peter", "Joern", "Marshall");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 3, "Peter", "Joern", "Marshall");
    
    cas.release();
  }
}
