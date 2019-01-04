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

package org.apache.uima.ruta.condition;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.engine.RutaTestUtils.TestFeature;
import org.apache.uima.util.InvalidXMLException;
import org.junit.Test;

public class FeatureTest {

  @Test
  public void test() throws AnalysisEngineProcessException, InvalidXMLException,
          ResourceInitializationException, ResourceConfigurationException, URISyntaxException,
          IOException {
    String name = this.getClass().getSimpleName();
    String namespace = this.getClass().getPackage().getName().replaceAll("\\.", "/");

    Map<String, String> complexTypes = new TreeMap<String, String>();
    String typeName = "org.apache.uima.FS";
    complexTypes.put(typeName, "uima.tcas.Annotation");

    Map<String, List<TestFeature>> features = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    features.put(typeName, list);
    String fn1 = "string";
    list.add(new TestFeature(fn1, "", "uima.cas.String"));
    String fn2 = "double";
    list.add(new TestFeature(fn2, "", "uima.cas.Double"));
    String fn3 = "int";
    list.add(new TestFeature(fn3, "", "uima.cas.Integer"));
    String fn4 = "boolean";
    list.add(new TestFeature(fn4, "", "uima.cas.Boolean"));

    CAS cas = RutaTestUtils.process(namespace + "/" + name + RutaEngine.SCRIPT_FILE_EXTENSION,
            namespace + "/" + name + ".txt", 50, false, false, complexTypes, features,
            namespace + "/");

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Testing FEATURE condition.");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "Testing FEATURE condition.");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "Testing FEATURE condition.");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 1, "Testing FEATURE condition.");
    RutaTestUtils.assertAnnotationsEquals(cas, 5, 0);

    cas.release();
  }
}
