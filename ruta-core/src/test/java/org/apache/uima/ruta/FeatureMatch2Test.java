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

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.junit.Test;

public class FeatureMatch2Test {

  @Test
  public void test() {
    String document = "Peter Kluegl, Joern Kottmann, Marshall Schor.";
    String script = "";
    script += "CW.begin<=14{-> T1};\n";
    script += "CW.begin<14{-> T2};\n";
    script += "CW.begin==0{-> T3};\n";
    script += "CW.begin!=0{-> T4};\n";
    script += "CW.begin>=20{-> T5};\n";
    script += "CW.begin>20{-> T6};\n";
    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document);
      Ruta.apply(cas, script);
    } catch (Exception e) {
      e.printStackTrace();
    }

    Type t = null;
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;

    t = RutaTestUtils.getTestType(cas, 1);
    ai = cas.getAnnotationIndex(t);
    assertEquals(3, ai.size());
    iterator = ai.iterator();
    assertEquals("Peter", iterator.next().getCoveredText());
    assertEquals("Kluegl", iterator.next().getCoveredText());
    assertEquals("Joern", iterator.next().getCoveredText());
    
    t = RutaTestUtils.getTestType(cas, 2);
    ai = cas.getAnnotationIndex(t);
    assertEquals(2, ai.size());
    iterator = ai.iterator();
    assertEquals("Peter", iterator.next().getCoveredText());
    assertEquals("Kluegl", iterator.next().getCoveredText());
    
    t = RutaTestUtils.getTestType(cas, 3);
    ai = cas.getAnnotationIndex(t);
    assertEquals(1, ai.size());
    iterator = ai.iterator();
    assertEquals("Peter", iterator.next().getCoveredText());

    t = RutaTestUtils.getTestType(cas, 4);
    ai = cas.getAnnotationIndex(t);
    assertEquals(5, ai.size());
    iterator = ai.iterator();
    assertEquals("Kluegl", iterator.next().getCoveredText());
    assertEquals("Joern", iterator.next().getCoveredText());
    assertEquals("Kottmann", iterator.next().getCoveredText());
    assertEquals("Marshall", iterator.next().getCoveredText());
    assertEquals("Schor", iterator.next().getCoveredText());
    
    t = RutaTestUtils.getTestType(cas, 5);
    ai = cas.getAnnotationIndex(t);
    assertEquals(3, ai.size());
    iterator = ai.iterator();
    assertEquals("Kottmann", iterator.next().getCoveredText());
    assertEquals("Marshall", iterator.next().getCoveredText());
    assertEquals("Schor", iterator.next().getCoveredText());
    
    t = RutaTestUtils.getTestType(cas, 6);
    ai = cas.getAnnotationIndex(t);
    assertEquals(2, ai.size());
    iterator = ai.iterator();
    assertEquals("Marshall", iterator.next().getCoveredText());
    assertEquals("Schor", iterator.next().getCoveredText());
    
    if (cas != null) {
      cas.release();
    }

  }
}
