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
import org.junit.Test;

public class ConjunctRulesTest {

  @Test
  public void test() {
    String document = "Peter Kluegl, Joern Kottmann, Marshall Schor.";
    String script = "DECLARE T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T14, T15, T16, T17, T18;\n";
    script += "W{REGEXP(\"Peter\")} ANY{-> T1} % ANY{REGEXP(\"J.*\")} CW PM{->MARK(T2)} % (COMMA # PM){->MARK(T3)};";
    script += "W{REGEXP(\"Peter\")} ANY{-> T4} % ANY{REGEXP(\"J.*\")} CW PM{->MARK(T5)} % (COMMA # QUESTION){->MARK(T6)};";
    
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
    assertEquals(1, ai.size());
    iterator = ai.iterator();
    assertEquals("Kluegl", iterator.next().getCoveredText());
    
    t = RutaTestUtils.getTestType(cas, 2);
    ai = cas.getAnnotationIndex(t);
    assertEquals(1, ai.size());
    iterator = ai.iterator();
    assertEquals(",", iterator.next().getCoveredText());
    
    t = RutaTestUtils.getTestType(cas, 3);
    ai = cas.getAnnotationIndex(t);
    assertEquals(2, ai.size());
    iterator = ai.iterator();
    assertEquals(", Joern Kottmann,", iterator.next().getCoveredText());
    assertEquals(", Marshall Schor.", iterator.next().getCoveredText());
    
    t = RutaTestUtils.getTestType(cas, 4);
    ai = cas.getAnnotationIndex(t);
    assertEquals(0, ai.size());
    
    t = RutaTestUtils.getTestType(cas, 5);
    ai = cas.getAnnotationIndex(t);
    assertEquals(0, ai.size());
    
    t = RutaTestUtils.getTestType(cas, 6);
    ai = cas.getAnnotationIndex(t);
    assertEquals(0, ai.size());
    
    
    
    if (cas != null) {
      cas.release();
    }

  }
}
