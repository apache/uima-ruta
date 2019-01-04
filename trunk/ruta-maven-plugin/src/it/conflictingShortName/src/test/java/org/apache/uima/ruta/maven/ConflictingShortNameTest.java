/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.uima.ruta.maven;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.util.XMLInputSource;
import org.junit.Test;

public class ConflictingShortNameTest {
  
  @Test
  public void test() throws Exception{
    File descDirectory = new File("target/generated-sources/ruta/descriptor");
    File aeFile1 = new File(descDirectory, "my/package/MainEngine.xml");
    assertTrue(aeFile1.exists());
    
    AnalysisEngineDescription aed = UIMAFramework.getXMLParser().parseAnalysisEngineDescription(new XMLInputSource(aeFile1));
    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(aed);
    ae.setConfigParameterValue(RutaEngine.PARAM_STRICT_IMPORTS, true);
    ae.reconfigure();
    CAS cas = ae.newCAS();
    cas.setDocumentText("Test");
    ae.process(cas);
    
    Type type1 = cas.getTypeSystem().getType("my.package.One.LocalType");
    AnnotationIndex<AnnotationFS> ai1 = cas.getAnnotationIndex(type1);
    assertEquals(1, ai1.size());
    assertEquals("Test", ai1.iterator().next().getCoveredText());
    
    Type type2 = cas.getTypeSystem().getType("my.package.Two.LocalType");
    AnnotationIndex<AnnotationFS> ai2 = cas.getAnnotationIndex(type2);
    assertEquals(1, ai2.size());
    assertEquals("Test", ai2.iterator().next().getCoveredText());
    
    Type type3 = cas.getTypeSystem().getType("my.package.one.ExternalType");
    AnnotationIndex<AnnotationFS> ai3 = cas.getAnnotationIndex(type3);
    assertEquals(1, ai3.size());
    assertEquals("Test", ai3.iterator().next().getCoveredText());
    
    Type type4 = cas.getTypeSystem().getType("my.package.two.ExternalType");
    AnnotationIndex<AnnotationFS> ai4 = cas.getAnnotationIndex(type4);
    assertEquals(1, ai4.size());
    assertEquals("Test", ai4.iterator().next().getCoveredText());
    
    cas.release();
  }
  
}
