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

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.junit.Assert.assertEquals;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.ruta.engine.RutaEngine;
import org.junit.Test;

public class AdditionalScriptUimaFitTest {

  @Test
  public void test() throws Exception {
    
    AnalysisEngineDescription aed = createEngineDescription(RutaEngine.class,
            RutaEngine.PARAM_MAIN_SCRIPT, "org.apache.uima.ruta.Main", RutaEngine.PARAM_DEBUG, false,
            RutaEngine.PARAM_ADDITIONAL_SCRIPTS, new String[] {"org.apache.uima.ruta.Additional"}
//            , RutaEngine.PARAM_STRICT_IMPORTS, true
            );
    
    
    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(aed);
    CAS cas = ae.newCAS();
    cas.setDocumentText("Some text 10.");
    
    ae.process(cas);
  
    Type t = null;
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;

    t = cas.getTypeSystem().getType("org.apache.uima.ruta.Additional.NewType1");
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertEquals(1, ai.size());
    assertEquals("10", iterator.next().getCoveredText());
    
    t = cas.getTypeSystem().getType("org.apache.uima.ruta.Additional.NewType2");
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertEquals(1, ai.size());
    assertEquals("Some", iterator.next().getCoveredText());

    cas.release();
  }
  
  @Test
  public void testStrictImport() throws Exception {
    
    AnalysisEngineDescription aed = createEngineDescription(RutaEngine.class,
            RutaEngine.PARAM_MAIN_SCRIPT, "org.apache.uima.ruta.Main", RutaEngine.PARAM_DEBUG, false,
            RutaEngine.PARAM_ADDITIONAL_SCRIPTS, new String[] {"org.apache.uima.ruta.Additional"}
            , RutaEngine.PARAM_STRICT_IMPORTS, true
            );
    
    
    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(aed);
    CAS cas = ae.newCAS();
    cas.setDocumentText("Some text 10.");
    
    ae.process(cas);
  
    Type t = null;
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;

    t = cas.getTypeSystem().getType("org.apache.uima.ruta.Additional.NewType1");
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertEquals(1, ai.size());
    assertEquals("10", iterator.next().getCoveredText());
    
    t = cas.getTypeSystem().getType("org.apache.uima.ruta.Additional.NewType2");
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertEquals(1, ai.size());
    assertEquals("Some", iterator.next().getCoveredText());

    cas.release();
  }
}
