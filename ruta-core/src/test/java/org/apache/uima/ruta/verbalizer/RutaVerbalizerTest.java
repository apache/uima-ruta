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

package org.apache.uima.ruta.verbalizer;

import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.ruta.RutaModule;
import org.apache.uima.ruta.RutaStatement;
import org.apache.uima.ruta.block.ForEachBlock;
import org.apache.uima.ruta.block.RutaScriptBlock;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.verbalize.RutaVerbalizer;
import org.junit.Assert;
import org.junit.Test;


public class RutaVerbalizerTest {
  
  @Test
  public void testSimpleScriptVerbalization() throws Exception{
    
    AnalysisEngine analysisEngine = AnalysisEngineFactory.createEngine(RutaEngine.class, RutaEngine.PARAM_MAIN_SCRIPT, "org.apache.uima.ruta.Verbalize");
    RutaEngine rutaEngine = (RutaEngine) FieldUtils.readField(analysisEngine, "mAnalysisComponent", true);
    RutaModule module = (RutaModule) FieldUtils.readField(rutaEngine, "script", true);
    RutaVerbalizer verbalizer = new RutaVerbalizer();
    Assert.assertNull(verbalizer.verbalize(module));
    Assert.assertEquals("BLOCK(Verbalize) Document;", verbalizer.verbalize(module.getRootBlock()));
    List<RutaStatement> elements = module.getRootBlock().getElements();
    Assert.assertEquals("Document{->CALL(Additional)};", verbalizer.verbalize(elements.get(1)));
    Assert.assertEquals("CW.ct==\"A\"{->TruePositive};", verbalizer.verbalize(elements.get(2)));
    RutaScriptBlock block = (RutaScriptBlock) elements.get(3);
    Assert.assertEquals("BLOCK(block) CW;", verbalizer.verbalize(block));
    ForEachBlock foreach = (ForEachBlock) elements.get(4);
    Assert.assertEquals("FOREACH(sw) SW;", verbalizer.verbalize(foreach));
  }
}
