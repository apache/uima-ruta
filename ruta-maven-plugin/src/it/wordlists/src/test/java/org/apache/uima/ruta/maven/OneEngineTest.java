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

import java.io.File;

import static org.junit.Assert.*;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.util.XMLInputSource;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
public class OneEngineTest {
  
  @Test
  public void test() throws Exception{
    File descDirectory = new File("target/generated-sources/ruta/descriptor");
    File aeFile1 = new File(descDirectory, "my/package/one/OneEngine.xml");
    assertTrue(aeFile1.exists());
    File tsFile1 = new File(descDirectory, "my/package/one/OneTypeSystem.xml");
    assertTrue(tsFile1.exists());
    File resourcesDirectory = new File("target/generated-sources/ruta/resources");
    File twlFile1 = new File(resourcesDirectory, "twl1.twl");
    assertTrue(twlFile1.getName() + " is missing!", twlFile1.exists());
    File twlFile2 = new File(resourcesDirectory, "my/package/one/twl2.twl");
    assertTrue(twlFile2.getName() + " is missing!", twlFile2.exists());
    File mtwlFile1 = new File(resourcesDirectory, "generated.mtwl");
    assertTrue(mtwlFile1.getName() + " is missing!", mtwlFile1.exists());
    
    
    AnalysisEngineDescription aed = UIMAFramework.getXMLParser().parseAnalysisEngineDescription(new XMLInputSource(aeFile1));
    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(aed);
    CAS cas = ae.newCAS();
    cas.setDocumentText("This is a test 1, 2, 3.");
    ae.process(cas);
    
    Type type1 = cas.getTypeSystem().getType("my.package.one.One.Twl1");
    AnnotationIndex<AnnotationFS> ai1 = cas.getAnnotationIndex(type1);
    assertEquals(1, ai1.size());
    assertEquals("1", ai1.iterator().next().getCoveredText());
    
    Type type2 = cas.getTypeSystem().getType("my.package.one.One.Twl2");
    AnnotationIndex<AnnotationFS> ai2 = cas.getAnnotationIndex(type2);
    assertEquals(1, ai2.size());
    assertEquals("2", ai2.iterator().next().getCoveredText());
    
    Type type3 = cas.getTypeSystem().getType("my.package.one.One.Mtwl1");
    AnnotationIndex<AnnotationFS> ai3 = cas.getAnnotationIndex(type3);
    assertEquals(1, ai3.size());
    assertEquals("1", ai3.iterator().next().getCoveredText());
    
    Type type4 = cas.getTypeSystem().getType("my.package.one.One.Mtwl2");
    AnnotationIndex<AnnotationFS> ai4 = cas.getAnnotationIndex(type4);
    assertEquals(1, ai4.size());
    assertEquals("2", ai4.iterator().next().getCoveredText());
    
    cas.release();
  }
  
}
