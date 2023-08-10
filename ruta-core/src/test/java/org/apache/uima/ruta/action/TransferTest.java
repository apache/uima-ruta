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

import java.util.Arrays;
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
import org.junit.jupiter.api.Test;

public class TransferTest {

  @Test
  public void test() {
    String name = this.getClass().getSimpleName();
    String namespace = this.getClass().getPackage().getName().replaceAll("\\.", "/");
    Map<String, String> complexTypes = new TreeMap<String, String>();
    String type = "org.apache.uima.LanguageStorage";
    complexTypes.put(type, CAS.TYPE_NAME_DOCUMENT_ANNOTATION);
    CAS cas = null;
    try {
      cas = RutaTestUtils.process(namespace + "/" + name + RutaEngine.SCRIPT_FILE_EXTENSION,
              namespace + "/" + name + ".txt", 50, false, false, complexTypes, null);
    } catch (Exception e) {
      e.printStackTrace();
      assert (false);
    }
    Type t = null;
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;

    t = cas.getTypeSystem().getType(type);
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertThat(ai.size()).isEqualTo(1);
    AnnotationFS afs = iterator.next();
    Feature featureByBaseName = t.getFeatureByBaseName("language");
    String stringValue = afs.getStringValue(featureByBaseName);
    assertThat(stringValue).isEqualTo("x-unspecified");

    cas.release();
  }

  @Test
  public void testIncompatibleFeatureRanges() throws Exception {

    Map<String, String> typeMap = new LinkedHashMap<String, String>();
    typeMap.put("Struct11", "uima.tcas.Annotation");
    typeMap.put("Struct12", "uima.tcas.Annotation");
    typeMap.put("Struct21", "uima.tcas.Annotation");
    typeMap.put("Struct22", "uima.tcas.Annotation");
    Map<String, List<TestFeature>> featureMap = new TreeMap<String, List<TestFeature>>();
    featureMap.put("Struct11", Arrays.asList(new TestFeature("f", "", CAS.TYPE_NAME_ANNOTATION)));
    featureMap.put("Struct12", Arrays.asList(new TestFeature("f", "", CAS.TYPE_NAME_STRING)));
    featureMap.put("Struct21",
            Arrays.asList(new TestFeature("array", "", CAS.TYPE_NAME_STRING_ARRAY)));
    featureMap.put("Struct22",
            Arrays.asList(new TestFeature("array", "", CAS.TYPE_NAME_BOOLEAN_ARRAY)));

    CAS cas = RutaTestUtils.getCAS("This is a test.", typeMap, featureMap);
    String script = "CW{->s:Struct11,s.f=CW};";
    script += "CW{->s:Struct21,s.array={true, false, true}};";
    script += "Struct11{-> TRANSFER(Struct12)};";
    script += "Struct21{-> TRANSFER(Struct22)};";

    Ruta.apply(cas, script, RutaTestUtils.getDebugParams());

    if (RutaTestUtils.DEBUG_MODE) {
      RutaTestUtils.storeTypeSystem(typeMap, featureMap);
      RutaTestUtils.storeCas(cas, "testIncompatibleFeatureRanges");
    }

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 0);

  }
}
