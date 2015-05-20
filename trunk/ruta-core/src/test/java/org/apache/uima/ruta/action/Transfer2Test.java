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

public class Transfer2Test {

  @Test
  public void test() {
    String document = "Visa.";
    String script = "";
    script += "DECLARE Annotation CardType(STRING cardName, STRING cardType);\n";
    script += "DECLARE Annotation BigAnnotation( STRING laonName, STRING loanType, STRING cardName, STRING cardType);\n";
    script += "\"Visa\" -> CardType (\"cardName\" = \"Peter\", \"cardType\" = \"Visa\");\n";
    script += "CardType{->TRANSFER(BigAnnotation)};";
    
    Map<String, String> typeMap = new TreeMap<String, String>();
    String typeName1 = "CardType";
    typeMap.put(typeName1, "uima.tcas.Annotation");
    String typeName2 = "BigAnnotation";
    typeMap.put(typeName2, "uima.tcas.Annotation");
    
    Map<String, List<TestFeature>> featureMap = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    featureMap.put(typeName1, list);
    String fn1 = "cardName";
    list.add(new TestFeature(fn1, "", "uima.cas.String"));
    String fn2 = "cardType";
    list.add(new TestFeature(fn2, "", "uima.cas.String"));
    
    list = new ArrayList<RutaTestUtils.TestFeature>();
    featureMap.put(typeName2, list);
    list.add(new TestFeature(fn1, "", "uima.cas.String"));
    list.add(new TestFeature(fn2, "", "uima.cas.String"));
    String fn3 = "laonName";
    list.add(new TestFeature(fn3, "", "uima.cas.String"));
    String fn4 = "loanType";
    list.add(new TestFeature(fn4, "", "uima.cas.String"));
    
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
    
    t = cas.getTypeSystem().getType(typeName2);
    Feature f1 = t.getFeatureByBaseName(fn1);
    Feature f2 = t.getFeatureByBaseName(fn2);
    ai = cas.getAnnotationIndex(t);
    assertEquals(1, ai.size());
    iterator = ai.iterator();
    AnnotationFS next = iterator.next();
    assertEquals("Peter", next.getStringValue(f1));
    assertEquals("Visa", next.getStringValue(f2));
    
    if (cas != null) {
      cas.release();
    }

  }
}
