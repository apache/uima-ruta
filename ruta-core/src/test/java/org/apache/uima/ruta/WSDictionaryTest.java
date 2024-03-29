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

package org.apache.uima.ruta;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.apache.uima.ruta.seed.DefaultSeeder;
import org.junit.jupiter.api.Test;

public class WSDictionaryTest {

  @Test
  public void test() throws Exception {
    String document = "Peter Kluegl, Marshall Schor, Joern Kottmann\n";
    document += "PeterKluegl, MarshallSchor, JoernKottmann\n";
    document += "Peter<x>Kluegl, Marshall<x>Schor, Joern<x>Kottmann\n";
    String script = "WORDLIST list = 'org/apache/uima/ruta/WSDictionaryTestList.txt';";
    script += "MARKFAST(T1, list);";

    CAS cas = executeAnalysis(RutaTestUtils.getCAS(document), script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 9, "Peter Kluegl", "Marshall Schor",
            "Joern Kottmann", "PeterKluegl", "MarshallSchor", "JoernKottmann", "Peter<x>Kluegl",
            "Marshall<x>Schor", "Joern<x>Kottmann");

    cas.release();
  }

  @Test
  public void testDictRemoveWS() throws Exception {
    String document = "Peter Kluegl, Marshall Schor, Joern Kottmann\n";
    document += "PeterKluegl, MarshallSchor, JoernKottmann\n";
    document += "Peter<x>Kluegl, Marshall<x>Schor, Joern<x>Kottmann\n";
    String script = "WORDLIST list = 'org/apache/uima/ruta/WSDictionaryTestList.txt';";
    script += "MARKFAST(T1, list, true, 0, false);";

    CAS cas = executeAnalysis(RutaTestUtils.getCAS(document), script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 9, "Peter Kluegl", "Marshall Schor",
            "Joern Kottmann", "PeterKluegl", "MarshallSchor", "JoernKottmann", "Peter<x>Kluegl",
            "Marshall<x>Schor", "Joern<x>Kottmann");

    cas.release();
  }

  @Test
  public void testTableWithWS() throws Exception {
    String document = "Peter Kluegl, Marshall Schor, Joern Kottmann\n";
    document += "PeterKluegl, MarshallSchor, JoernKottmann\n";
    document += "Peter<x>Kluegl, Marshall<x>Schor, Joern<x>Kottmann\n";
    String script = "WORDTABLE table = 'org/apache/uima/ruta/table2.csv';";
    script += "MARKTABLE(Struct, 1, table, true, 0, \"-.,\", 10, \"name\" = 1, \"system\" = 2);";

    Map<String, String> complexTypes = new TreeMap<String, String>();
    String typeName = "org.apache.uima.Struct";
    complexTypes.put(typeName, "uima.tcas.Annotation");

    Map<String, List<TestFeature>> features = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    features.put(typeName, list);
    String fn1 = "name";
    list.add(new TestFeature(fn1, "", "uima.cas.String"));
    String fn2 = "system";
    list.add(new TestFeature(fn2, "", "uima.cas.String"));

    CAS cas = executeAnalysis(RutaTestUtils.getCAS(document, complexTypes, features), script);

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

    assertThat(ai.size()).isEqualTo(9);
    iterator = ai.iterator();

    next = iterator.next();
    v1 = next.getStringValue(f1);
    v2 = next.getStringValue(f2);
    assertThat(v1).isEqualTo("Peter Kluegl");
    assertThat(v2).isEqualTo("UIMA Ruta");

    next = iterator.next();
    v1 = next.getStringValue(f1);
    v2 = next.getStringValue(f2);
    assertThat(v1).isEqualTo("Marshall Schor");
    assertThat(v2).isEqualTo("UIMA Core");

    next = iterator.next();
    v1 = next.getStringValue(f1);
    v2 = next.getStringValue(f2);
    assertThat(v1).isEqualTo("Joern Kottmann");
    assertThat(v2).isEqualTo("CAS Editor");

    next = iterator.next();
    v1 = next.getStringValue(f1);
    v2 = next.getStringValue(f2);
    assertThat(v1).isEqualTo("Peter Kluegl");
    assertThat(v2).isEqualTo("UIMA Ruta");

    next = iterator.next();
    v1 = next.getStringValue(f1);
    v2 = next.getStringValue(f2);
    assertThat(v1).isEqualTo("Marshall Schor");
    assertThat(v2).isEqualTo("UIMA Core");

    next = iterator.next();
    v1 = next.getStringValue(f1);
    v2 = next.getStringValue(f2);
    assertThat(v1).isEqualTo("Joern Kottmann");
    assertThat(v2).isEqualTo("CAS Editor");

    next = iterator.next();
    v1 = next.getStringValue(f1);
    v2 = next.getStringValue(f2);
    assertThat(v1).isEqualTo("Peter Kluegl");
    assertThat(v2).isEqualTo("UIMA Ruta");

    next = iterator.next();
    v1 = next.getStringValue(f1);
    v2 = next.getStringValue(f2);
    assertThat(v1).isEqualTo("Marshall Schor");
    assertThat(v2).isEqualTo("UIMA Core");

    next = iterator.next();
    v1 = next.getStringValue(f1);
    v2 = next.getStringValue(f2);
    assertThat(v1).isEqualTo("Joern Kottmann");
    assertThat(v2).isEqualTo("CAS Editor");

    cas.release();
  }

  private CAS executeAnalysis(CAS cas, String script) throws Exception {
    Map<String, Object> map = new HashMap<>();
    map.put(RutaEngine.PARAM_DICT_REMOVE_WS, true);
    map.put(RutaEngine.PARAM_SEEDERS, new String[] { DefaultSeeder.class.getName() });
    Ruta.apply(cas, script, map);
    return cas;
  }

}
