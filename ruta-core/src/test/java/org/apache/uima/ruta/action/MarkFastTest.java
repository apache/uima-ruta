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
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.engine.RutaTestUtils.TestFeature;
import org.junit.Test;

public class MarkFastTest {

  @Test
  public void test() throws Exception {
    String name = this.getClass().getSimpleName();
    String namespace = this.getClass().getPackage().getName().replaceAll("\\.", "/");

    CAS cas = RutaTestUtils.process(namespace + "/" + name + RutaEngine.SCRIPT_FILE_EXTENSION,
            namespace + "/" + name + ".txt", 50, false, false, null, namespace + "/");

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 3, "1 0 0", "100", "2 0 0");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 3, "1 0 0", "100", "2 0 0");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 2, "1 0 0", "2 0 0");

  }

  @Test
  public void testWithNullStringList() throws Exception {

    String text = "Some text.";
    String script = "";
    script += "STRINGLIST list;\n";
    script += "Document{-> ADD(list, null)};\n";
    script += "MARKFAST(T1,list);\n";

    Map<String, String> complexTypes = new TreeMap<String, String>();
    String typeName = "org.apache.uima.Struct";
    complexTypes.put(typeName, "uima.tcas.Annotation");

    Map<String, List<TestFeature>> features = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    features.put(typeName, list);
    String fn1 = "s";
    list.add(new TestFeature(fn1, "", "uima.cas.String"));
    CAS cas = RutaTestUtils.getCAS(text, complexTypes, features);
    Ruta.apply(cas, script);
    RutaTestUtils.assertAnnotationsEquals(cas, 1, 0);
  }

}
