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
package org.apache.uima.ruta.engine;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.junit.Assert.assertEquals;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.fit.factory.JCasBuilder;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.ruta.type.FalsePositive;
import org.apache.uima.ruta.type.TruePositive;
import org.junit.Test;

public class UimafitTest {
  @Test
  public void test() throws Exception {
    AnalysisEngine ae = createEngine(RutaEngine.class,
    // Load script in "Java" notation, with "." as package separator and no extension.
    // File needs to be located in the path specified below with ending ".ruta".
            RutaEngine.PARAM_MAIN_SCRIPT, "org.apache.uima.ruta.engine.UimafitTest",
            // Path(s) where the scripts are located
            RutaEngine.PARAM_SCRIPT_PATHS, new String[] { "src/test/resources" });

    // Create a CAS from the AE so it has the required type priorities
    JCas jcas = ae.newJCas();

    // Fill the CAS with some tokens
    JCasBuilder builder = new JCasBuilder(jcas);
    builder.add("This", TruePositive.class);
    builder.add(" ");
    builder.add("is", TruePositive.class);
    builder.add(" ");
    builder.add("a", TruePositive.class);
    builder.add(" ");
    builder.add("test", TruePositive.class);
    builder.add(".", TruePositive.class);
    builder.close();

    // Apply the script
    ae.process(jcas);

    // Test the result
    AnnotationIndex<Annotation> ai = jcas.getAnnotationIndex(FalsePositive.type);
    FSIterator<Annotation> iterator = ai.iterator();
    assertEquals(1, ai.size());
    assertEquals("This is a test.", iterator.next().getCoveredText());
    
  }
}
