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
import java.util.HashMap;
import java.util.LinkedHashMap;
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

public class Shift2Test {

  @Test
  public void test() throws Exception {

    Map<String, String> complexTypes = new HashMap<String, String>();
    String typeName = "org.apache.uima.FS";
    complexTypes.put(typeName, "uima.tcas.Annotation");

    Map<String, List<TestFeature>> features = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    features.put(typeName, list);
    String fn1 = "doc";
    list.add(new TestFeature(fn1, "", "uima.tcas.Annotation"));
    String fn2 = "lang";
    list.add(new TestFeature(fn2, "", "uima.cas.String"));

    Map<String, Object> params = new LinkedHashMap<>();
    params.put(RutaEngine.PARAM_SEEDERS, new String[] { DefaultSeeder.class.getName() });

    CAS cas = RutaTestUtils.getCAS("only some text<br/>", complexTypes, features);
    String script = "";
    script += "CREATE(FS, \"doc\" = Document, \"lang\" = \"unknown\");\r\n";
    script += "RETAINTYPE(MARKUP);\r\n";
    script += "W{STARTSWITH(FS) -> SHIFT(FS, 1, 2, true)} W+ MARKUP;";
    Ruta.apply(cas, script, params);

    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;

    Type type = cas.getTypeSystem().getType(typeName);
    Feature f1 = type.getFeatureByBaseName(fn1);
    Feature f2 = type.getFeatureByBaseName(fn2);
    ai = cas.getAnnotationIndex(type);
    iterator = ai.iterator();
    assertThat(ai.size()).isEqualTo(1);
    AnnotationFS next = iterator.next();
    AnnotationFS v1 = (AnnotationFS) next.getFeatureValue(f1);
    String v2 = next.getStringValue(f2);
    assertThat(v1.getCoveredText()).isEqualTo("only some text<br/>");
    assertThat(v2).isEqualTo("unknown");
    assertThat(next.getCoveredText()).isEqualTo("only some text");

    cas.release();
  }
}
