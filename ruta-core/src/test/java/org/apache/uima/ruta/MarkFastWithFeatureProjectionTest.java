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

import static org.junit.Assert.assertEquals;

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
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.engine.RutaTestUtils.TestFeature;
import org.junit.Test;

public class MarkFastWithFeatureProjectionTest {

  @Test
  public void testDefault() {
    String document = "some text Concept (c) some text c some text c some text c";

    String script = "DECLARE AbbrConcept (STRING uniqueID);";
    script += "STRING id;";
    script += "STRING matchedText;";
    script += "\"Concept\" -> T1;";
    script += "STRINGLIST abbrConcepts;";
    script += "T1 SPECIAL SW{-> CREATE(AbbrConcept, \"uniqueID\" = \"X\"), ADD(abbrConcepts, AbbrConcept.ct),ASSIGN(matchedText, AbbrConcept.ct)} SPECIAL;";
    script += "MARKFAST(AbbrConcept, abbrConcepts);";
    script += "AbbrConcept.uniqueID==null{CONTAINS(AbbrConcept,2,100) -> UNMARK(AbbrConcept)};";
    script += "@AbbrConcept.uniqueID!=null{-> ASSIGN(id, AbbrConcept.uniqueID)} ";
    script += "    # AbbrConcept.uniqueID == null{AbbrConcept.ct == matchedText -> AbbrConcept.uniqueID = id};";

    Map<String, String> typeMap = new TreeMap<String, String>();
    String typeName = "AbbrConcept";
    typeMap.put(typeName, "uima.tcas.Annotation");

    Map<String, List<TestFeature>> featureMap = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    featureMap.put(typeName, list);
    String fn = "uniqueID";
    list.add(new TestFeature(fn, "", "uima.cas.String"));

    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document, typeMap, featureMap);
      Ruta.apply(cas, script);
    } catch (Exception e) {
      e.printStackTrace();
    }

    Type t = null;
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;

    t = cas.getTypeSystem().getType(typeName);
    Feature f1 = t.getFeatureByBaseName(fn);
    ai = cas.getAnnotationIndex(t);

    assertEquals(4, ai.size());
    iterator = ai.iterator();
    AnnotationFS next = iterator.next();
    assertEquals("c", next.getCoveredText());
    String featureValue = next.getStringValue(f1);
    assertEquals("X", featureValue);

    next = iterator.next();
    assertEquals("c", next.getCoveredText());
    featureValue = next.getStringValue(f1);
    assertEquals("X", featureValue);

    next = iterator.next();
    assertEquals("c", next.getCoveredText());
    featureValue = next.getStringValue(f1);
    assertEquals("X", featureValue);
    
    next = iterator.next();
    assertEquals("c", next.getCoveredText());
    featureValue = next.getStringValue(f1);
    assertEquals("X", featureValue);

    if (cas != null) {
      cas.release();
    }

  }

}
