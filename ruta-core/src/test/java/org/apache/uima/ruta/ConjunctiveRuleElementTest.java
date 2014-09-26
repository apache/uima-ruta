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
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.engine.RutaTestUtils.TestFeature;
import org.junit.Test;

public class ConjunctiveRuleElementTest {

  /*
  @Test
  public void test() {
    String document = "Peter Kluegl, Joern Kottmann, Marshall Schor.";
    String script = "DECLARE T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T14, T15, T16, T17, T18;\n";
    script += "(W & SW & ANY){->MARK(T1)};\n";
    script += "((CW ANY{REGEXP(\"K.*\")}) & ANY{REGEXP(\"P.*\")}){->MARK(T2)};\n";
    script += "(ALL & ANY & W & CW{REGEXP(\"Schor\")}){->T3};\n";
    script += "((ALL ALL) & ANY & (W PERIOD) & CW{REGEXP(\"Schor\")}){->T4};\n";
    script += "((ALL ALL) & ANY & (W W) & (ANY CW{REGEXP(\"Schor\")})){->T5};\n";
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
    assertEquals(0, ai.size());

    t = RutaTestUtils.getTestType(cas, 2);
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertEquals(1, ai.size());
    assertEquals("Peter Kluegl", iterator.next().getCoveredText());
    
    t = RutaTestUtils.getTestType(cas, 3);
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertEquals(1, ai.size());
    assertEquals("Schor", iterator.next().getCoveredText());
    
    t = RutaTestUtils.getTestType(cas, 4);
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertEquals(1, ai.size());
    assertEquals("Schor.", iterator.next().getCoveredText());
    
    t = RutaTestUtils.getTestType(cas, 5);
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertEquals(1, ai.size());
    assertEquals("Marshall Schor", iterator.next().getCoveredText());
    
    if (cas != null) {
      cas.release();
    }

  }
  */
  
  @Test
  public void testWithFeatureMatch() {
    
//    DECLARE Annotation Token (STRING posTag, STRING mood, STRING tense);    
    String document = "Peter did something.";
    String script = "";
    script += "CW{-> CREATE(Token, \"posTag\" = \"noun\")} SW{-> CREATE(Token, \"posTag\" = \"verb\", \"mood\" = \"p\", \"tense\" = \"p\")} SW;\n";
    script += "(Token.posTag == \"verb\" & Token.mood==\"p\" & Token.tense==\"p\" ){-> T1};\n";
//    script += "(Token.posTag == \"noun\" @( Token.posTag == \"verb\" & Token.mood==\"p\" & Token.tense==\"p\" )){-> T2};\n";
    script += "(Token.posTag == \"noun\" ( Token.posTag == \"verb\" & Token.mood==\"p\" & Token.tense==\"p\" )){-> T3};\n";
    
    
    Map<String, String> typeMap = new TreeMap<String, String>();
    String typeName1 = "Token";
    typeMap.put(typeName1, "uima.tcas.Annotation");

    Map<String, List<TestFeature>> featureMap = new TreeMap<String, List<TestFeature>>();
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    featureMap.put(typeName1, list);
    String fn1 = "posTag";
    list.add(new TestFeature(fn1, "", "uima.cas.String"));
    String fn2 = "mood";
    list.add(new TestFeature(fn2, "", "uima.cas.String"));
    String fn3 = "tense";
    list.add(new TestFeature(fn3, "", "uima.cas.String"));
    
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

    t = RutaTestUtils.getTestType(cas, 1);
    ai = cas.getAnnotationIndex(t);
    assertEquals(1, ai.size());
    iterator = ai.iterator();
    assertEquals("did", iterator.next().getCoveredText());

//    t = RutaTestUtils.getTestType(cas, 2);
//    ai = cas.getAnnotationIndex(t);
//    assertEquals(1, ai.size());
//    iterator = ai.iterator();
//    assertEquals("Peter did", iterator.next().getCoveredText());
    
    t = RutaTestUtils.getTestType(cas, 3);
    ai = cas.getAnnotationIndex(t);
    assertEquals(1, ai.size());
    iterator = ai.iterator();
    assertEquals("Peter did", iterator.next().getCoveredText());
    
    
    if (cas != null) {
      cas.release();
    }

  }
  
}
