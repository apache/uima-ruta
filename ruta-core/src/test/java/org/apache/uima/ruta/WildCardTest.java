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
import org.apache.uima.ruta.engine.RutaEngine;
import org.junit.Test;

public class WildCardTest {

  @Test
  public void test() {
    String name = this.getClass().getSimpleName();
    String namespace = this.getClass().getPackage().getName().replaceAll("\\.", "/");
    CAS cas = null;
    try {
      cas = RutaTestUtils.process(namespace + "/" + name + RutaEngine.SCRIPT_FILE_EXTENSION, namespace + "/" + name
              + ".txt", 50);
    } catch (Exception e) {
      e.printStackTrace();
      assert (false);
    }
    Type t = null;
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;

    t = RutaTestUtils.getTestType(cas, 1);
    ai = cas.getAnnotationIndex(t);
    assertEquals(1, ai.size());
    iterator = ai.iterator();
    assertEquals(cas.getDocumentText().length(), iterator.next().getCoveredText().length());
   
    t = RutaTestUtils.getTestType(cas, 2);
    ai = cas.getAnnotationIndex(t);
    assertEquals(1, ai.size());
    iterator = ai.iterator();
    assertEquals("The", iterator.next().getCoveredText());
   
    t = RutaTestUtils.getTestType(cas, 3);
    ai = cas.getAnnotationIndex(t);
    assertEquals(1, ai.size());
    iterator = ai.iterator();
    assertEquals("The Ruta language is an imperative rule language extended with scripting elements.", iterator.next().getCoveredText());
    
    t = RutaTestUtils.getTestType(cas, 4);
    ai = cas.getAnnotationIndex(t);
    assertEquals(8, ai.size());
    
    t = RutaTestUtils.getTestType(cas, 5);
    ai = cas.getAnnotationIndex(t);
    assertEquals(1, ai.size());
    iterator = ai.iterator();
    assertEquals("The Ruta language is an imperative rule language extended with scripting elements", iterator.next().getCoveredText());
    
    t = RutaTestUtils.getTestType(cas, 6);
    ai = cas.getAnnotationIndex(t);
    assertEquals(1, ai.size());
    iterator = ai.iterator();
    assertEquals("The Ruta language is an imperative rule language extended with scripting elements", iterator.next().getCoveredText());
    
    t = RutaTestUtils.getTestType(cas, 7);
    ai = cas.getAnnotationIndex(t);
    assertEquals(1, ai.size());
    iterator = ai.iterator();
    assertEquals(cas.getDocumentText(), iterator.next().getCoveredText());
    
    t = RutaTestUtils.getTestType(cas, 8);
    ai = cas.getAnnotationIndex(t);
    assertEquals(1, ai.size());
    iterator = ai.iterator();
    assertEquals(cas.getDocumentText(), iterator.next().getCoveredText());
    
    t = RutaTestUtils.getTestType(cas, 9);
    ai = cas.getAnnotationIndex(t);
    assertEquals(2, ai.size());
    
    t = RutaTestUtils.getTestType(cas, 10);
    ai = cas.getAnnotationIndex(t);
    assertEquals(1, ai.size());
    iterator = ai.iterator();
    assertEquals("The Ruta language is an imperative rule language extended with scripting elements", iterator.next().getCoveredText());
    
    t = RutaTestUtils.getTestType(cas, 11);
    ai = cas.getAnnotationIndex(t);
    assertEquals(9, ai.size());
    
    t = RutaTestUtils.getTestType(cas, 12);
    ai = cas.getAnnotationIndex(t);
    assertEquals(1, ai.size());
    iterator = ai.iterator();
    assertEquals("The Ruta language is an imperative rule language extended with scripting elements.", iterator.next().getCoveredText());
    
    t = RutaTestUtils.getTestType(cas, 13);
    ai = cas.getAnnotationIndex(t);
    assertEquals(8, ai.size());
    
    t = RutaTestUtils.getTestType(cas, 14);
    ai = cas.getAnnotationIndex(t);
    assertEquals(1, ai.size());
    iterator = ai.iterator();
    assertEquals("The Ruta language is an imperative rule language extended with scripting elements.", iterator.next().getCoveredText());
    
    t = RutaTestUtils.getTestType(cas, 16);
    ai = cas.getAnnotationIndex(t);
    assertEquals(4, ai.size());
    
    t = RutaTestUtils.getTestType(cas, 17);
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertEquals(4, ai.size());
    assertEquals("The", iterator.next().getCoveredText());
    assertEquals("If", iterator.next().getCoveredText());
    assertEquals("The", iterator.next().getCoveredText());
    assertEquals("The", iterator.next().getCoveredText());
    
    t = RutaTestUtils.getTestType(cas, 18);
    ai = cas.getAnnotationIndex(t);
    assertEquals(4, ai.size());
    
    t = RutaTestUtils.getTestType(cas, 19);
    ai = cas.getAnnotationIndex(t);
    assertEquals(8, ai.size());
    
    t = RutaTestUtils.getTestType(cas, 20);
    ai = cas.getAnnotationIndex(t);
    assertEquals(4, ai.size());
    
    t = RutaTestUtils.getTestType(cas, 21);
    ai = cas.getAnnotationIndex(t);
    assertEquals(4, ai.size());
    
    t = RutaTestUtils.getTestType(cas, 22);
    ai = cas.getAnnotationIndex(t);
    assertEquals(4, ai.size());
    
    t = RutaTestUtils.getTestType(cas, 23);
    ai = cas.getAnnotationIndex(t);
    assertEquals(4, ai.size());
    
    t = RutaTestUtils.getTestType(cas, 24);
    ai = cas.getAnnotationIndex(t);
    assertEquals(2, ai.size());

    t = RutaTestUtils.getTestType(cas, 25);
    ai = cas.getAnnotationIndex(t);
    assertEquals(4, ai.size());
    
    t = RutaTestUtils.getTestType(cas, 26);
    ai = cas.getAnnotationIndex(t);
    assertEquals(9, ai.size());
    
    t = RutaTestUtils.getTestType(cas, 27);
    ai = cas.getAnnotationIndex(t);
    assertEquals(4, ai.size());
    
    t = RutaTestUtils.getTestType(cas, 28);
    ai = cas.getAnnotationIndex(t);
    assertEquals(8, ai.size());
    
    t = RutaTestUtils.getTestType(cas, 29);
    ai = cas.getAnnotationIndex(t);
    assertEquals(1, ai.size());
    
    if (cas != null) {
      cas.release();
    }

  }
}
