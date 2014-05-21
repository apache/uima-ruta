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

public class MarkInGreedyComposedTest {

  
//  @Test
//  public void testWildCardFollowedByComposedReversed() {
//    String document = "1 x f B e d B x c A b a A 1";
//    String script = "";
//    script += "( ( (SW{REGEXP(\"x\")} SW ) #) {-> T1} )+ @NUM;";
//    CAS cas = null;
//    try {
//      cas = RutaTestUtils.getCAS(document);
//      Ruta.apply(cas, script);
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//
//    Type t = null;
//    AnnotationIndex<AnnotationFS> ai = null;
//    FSIterator<AnnotationFS> iterator = null;
//
//    t = RutaTestUtils.getTestType(cas, 1);
//    ai = cas.getAnnotationIndex(t);
//    iterator = ai.iterator();
////    for (AnnotationFS a : ai) {
////      System.out.println(a.getCoveredText());
////    }
//    assertEquals(2, ai.size());
//    assertEquals("x f B e d B", iterator.next().getCoveredText());
//    assertEquals("x c A b a A", iterator.next().getCoveredText());
//    
//    if (cas != null) {
//      cas.release();
//    }
//  }
  
  @Test
  public void testWildCardFollowedByComposed() {
    String document = "A c b A c x A c b A c x";
    String script = "";
    script += "( ( # (SW SW{REGEXP(\"x\")}) ) {-> T1} )+;";
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
    iterator = ai.iterator();
    assertEquals(2, ai.size());
    assertEquals("A c b A c x", iterator.next().getCoveredText());
    assertEquals("A c b A c x", iterator.next().getCoveredText());
    
    if (cas != null) {
      cas.release();
    }
  }
  
  
  
  
  
  @Test
  public void testWildCardFollowedByCondition() {
    String document = "A A b A c X X b X c";
    String script = "";
    script += "((# SW{REGEXP(\"c\")}){-> T1})+; ";
    script += "((# SW{REGEXP(\"c\")}){-> MARKONCE(T2)})+; ";
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
    iterator = ai.iterator();
    assertEquals(2, ai.size());
    assertEquals("A A b A c", iterator.next().getCoveredText());
    assertEquals("X X b X c", iterator.next().getCoveredText());

    t = RutaTestUtils.getTestType(cas, 2);
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertEquals(2, ai.size());
    assertEquals("A A b A c", iterator.next().getCoveredText());
    assertEquals("X X b X c", iterator.next().getCoveredText());
    
    if (cas != null) {
      cas.release();
    }

  }
  
  @Test
  public void testWithWildCard() {
    String document = "1 . 2 .";
    String script = "";
    script += "((# PERIOD){-> T1})+; ";
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
    iterator = ai.iterator();
    assertEquals(2, ai.size());
    assertEquals("1 .", iterator.next().getCoveredText());
    assertEquals("2 .", iterator.next().getCoveredText());

    if (cas != null) {
      cas.release();
    }

  }
  
  @Test
  public void test() {
    String document = "1 . 2 .";
    String script = "";
    script += "((NUM PERIOD){-> MARKONCE(T1)})+; ";
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
    iterator = ai.iterator();
    assertEquals(2, ai.size());
    assertEquals("1 .", iterator.next().getCoveredText());
    assertEquals("2 .", iterator.next().getCoveredText());

    if (cas != null) {
      cas.release();
    }

  }
  
  
  
}
