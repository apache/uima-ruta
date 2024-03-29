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

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.fit.factory.AggregateBuilder;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.type.W;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests Ruta when it is runned on another view than _InitialView.
 */
public class CustomViewTest {
  private AnalysisEngine engine;

  /**
   * Create an analysis engine for a Ruta script.
   *
   * @param script
   *          Script path.
   * @return Analysis engine.
   */
  private AnalysisEngine createAE(String script) throws Exception {
    final AnalysisEngineDescription ruta = AnalysisEngineFactory.createEngineDescription(
            "org.apache.uima.ruta.engine.BasicEngine", RutaEngine.PARAM_MAIN_SCRIPT, script);

    ruta.getAnalysisEngineMetaData().getTypeSystem().addType("org.apache.uima.T1",
            "Type for Testing", "uima.tcas.Annotation");

    final AggregateBuilder builder = new AggregateBuilder();
    builder.add(ruta, "_InitialView", "text");

    return builder.createAggregate();
  }

  @BeforeEach
  public void setUp() throws Exception {
    final String name = this.getClass().getSimpleName();
    final String namespace = this.getClass().getPackage().getName().replaceAll("\\.", "/");
    engine = createAE(String.format("%s/%s", namespace, name));
  }

  @AfterEach
  public void tearDown() throws Exception {
    engine.destroy();
  }

  @Test
  public void testManyExecutionsOnCustomView() throws Exception {
    for (int i = 0; i < 5; ++i) {
      final JCas jcas = engine.newJCas().createView("text");
      jcas.setDocumentText("This is a short document.");

      engine.process(jcas);
      final List<String> words = new ArrayList<String>();

      for (Annotation a : JCasUtil.select(jcas, W.class)) {
        words.add(a.getCoveredText());
      }

      assertThat(words).as("Checking run " + i).containsExactly("This", "is", "a", "short",
              "document");
    }
  }
}
