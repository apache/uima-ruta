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

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.util.XMLInputSource;
import org.junit.jupiter.api.Test;

public class OneEngineTest {
  
  @Test
  public void test() throws Exception{
    File descDirectory = new File("target/generated-sources/ruta/descriptor");
    File aeFile1 = new File(descDirectory, "my/package/one/OneEngine.xml");
    assertThat(aeFile1).exists();
    File tsFile1 = new File(descDirectory, "my/package/one/OneTypeSystem.xml");
    assertThat(tsFile1).exists();
    
    AnalysisEngineDescription aed = UIMAFramework.getXMLParser().parseAnalysisEngineDescription(new XMLInputSource(aeFile1));
    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(aed);
    CAS cas = ae.newCAS();
    cas.setDocumentText("This is a test.");
    ae.process(cas);
    
    Type type1 = cas.getTypeSystem().getType("my.package.one.One.Type1");
    assertThat(cas.<AnnotationFS> getAnnotationIndex(type1)) //
      .extracting(AnnotationFS::getCoveredText) //
      .containsExactly("This");
    
    Type type2 = cas.getTypeSystem().getType("my.package.two.Two.Type2");
    assertThat(cas.<AnnotationFS> getAnnotationIndex(type2)) //
      .extracting(AnnotationFS::getCoveredText) //
      .containsExactly("This");
    
    Type type3 = cas.getTypeSystem().getType("my.package.three.Three.Type3");
    assertThat(cas.<AnnotationFS> getAnnotationIndex(type3)) //
      .extracting(AnnotationFS::getCoveredText) //
      .containsExactly("This");
    
    cas.release();
  }
  
}
