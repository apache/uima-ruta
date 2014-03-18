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
import org.junit.Test;

public class FillTest {

  @Test
  public void test() {
    String name = this.getClass().getSimpleName();
    String namespace = this.getClass().getPackage().getName().replaceAll("\\.", "/");
    Map<String, String> complexTypes = new TreeMap<String, String>();
    String type = "org.apache.uima.LanguageStorage";
    complexTypes.put(type, "uima.tcas.DocumentAnnotation");
    CAS cas = null;
    try {
      cas = RutaTestUtils.process(namespace + "/" + name + RutaEngine.SCRIPT_FILE_EXTENSION, namespace + "/" + name
              + ".txt", 50, false, false, complexTypes, null);
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
    assertEquals(1, ai.size());
    AnnotationFS afs = iterator.next();
    Feature featureByBaseName = t.getFeatureByBaseName("language");
    String stringValue = afs.getStringValue(featureByBaseName);
    assertEquals("en", stringValue);
   
    cas.release();
  }
}
