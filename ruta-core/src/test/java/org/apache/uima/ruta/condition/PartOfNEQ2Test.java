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

import static org.junit.Assert.assertEquals;

import java.util.Map;
import java.util.TreeMap;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.junit.Test;

public class PartOfNEQ2Test {

  @Test
  public void test() {
    String document = "> 51%";
    String script = "";
    script += "\"> 51%\" -> Leaf1;\n";
    script += "\"51%\" -> Leaf2;\n";
    script += "Base{PARTOFNEQ(Base) -> UNMARK(Base)};\n";
    
    Map<String, String> complexTypes = new TreeMap<String, String>();
    String typeName1 = "uima.ruta.Base";
    complexTypes.put(typeName1, "uima.tcas.Annotation");
    String typeName2 = "uima.ruta.Leaf1";
    complexTypes.put(typeName2, typeName1);
    String typeName3 = "uima.ruta.Leaf2";
    complexTypes.put(typeName3, typeName1);
    
    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document, complexTypes, null);
      Ruta.apply(cas, script);
    } catch (Exception e) {
      e.printStackTrace();
    }

    Type t = null;
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;
    
    t = cas.getTypeSystem().getType(typeName1);
    ai = cas.getAnnotationIndex(t);
    assertEquals(1, ai.size());
    iterator = ai.iterator();
    assertEquals("> 51%", iterator.next().getCoveredText());

    cas.release();
  }
}
