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

import java.net.URL;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.util.XMLInputSource;
import org.junit.Test;

public class TypeAliasTest {

  @Test
  public void test() throws Exception {
    URL url = this.getClass().getResource("TypeAliasTestEngine.xml");
    AnalysisEngineDescription aed = (AnalysisEngineDescription) UIMAFramework.getXMLParser().parse(
            new XMLInputSource(url));

    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(aed);
    CAS cas = ae.newCAS();
    cas.setDocumentText("Peter");
    ae.process(cas);
    

    Type t = null;
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;

    t = cas.getTypeSystem().getType("org.apache.uima.ruta.TypeAliasTest.T1");
    ai = cas.getAnnotationIndex(t);
    assertEquals(2, ai.size());
    iterator = ai.iterator();
    assertEquals("Peter", iterator.next().getCoveredText());
    assertEquals("Peter", iterator.next().getCoveredText());
    
    t = cas.getTypeSystem().getType("org.apache.uima.ruta.TypeAliasTest.T2");
    ai = cas.getAnnotationIndex(t);
    assertEquals(2, ai.size());
    iterator = ai.iterator();
    assertEquals("Peter", iterator.next().getCoveredText());
    assertEquals("Peter", iterator.next().getCoveredText());
    
    t = cas.getTypeSystem().getType("org.apache.uima.ruta.TypeAliasTest.T3");
    ai = cas.getAnnotationIndex(t);
    assertEquals(2, ai.size());
    iterator = ai.iterator();
    assertEquals("Peter", iterator.next().getCoveredText());
    assertEquals("Peter", iterator.next().getCoveredText());
    
    cas.release();
  }
}
