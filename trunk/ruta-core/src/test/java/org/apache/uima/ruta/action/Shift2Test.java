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
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.engine.RutaTestUtils.TestFeature;
import org.junit.Test;

public class Shift2Test {

  @Test
  public void test() {
    String name = this.getClass().getSimpleName();
    String namespace = this.getClass().getPackage().getName().replaceAll("\\.", "/");
    
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
    
    CAS cas = null;
    try {
      cas = RutaTestUtils.process(namespace + "/" + name + RutaEngine.SCRIPT_FILE_EXTENSION, namespace + "/" + name
              + ".txt", 50, false, false, complexTypes, features, namespace + "/");
    } catch (Exception e) {
      e.printStackTrace();
      assert (false);
    }
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;

    Type type = cas.getTypeSystem().getType(typeName);
    Feature f1 = type.getFeatureByBaseName(fn1);
    Feature f2 = type.getFeatureByBaseName(fn2);
    ai = cas.getAnnotationIndex(type);
    iterator = ai.iterator();
    assertEquals(1, ai.size());
    AnnotationFS next = iterator.next();
    AnnotationFS v1 = (AnnotationFS) next.getFeatureValue(f1);
    String v2 = next.getStringValue(f2); 
    assertEquals("only some text<br/>", v1.getCoveredText());
    assertEquals("unknown", v2);
    assertEquals("only some text", next.getCoveredText());
    
    cas.release();
  }
}
