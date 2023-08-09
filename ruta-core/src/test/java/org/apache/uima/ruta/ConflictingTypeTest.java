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

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.TypeSystemDescriptionFactory;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.ruta.engine.RutaEngine;
import org.junit.jupiter.api.Test;

/**
 * There is no warning when short type names are ambiguous.
 */
public class ConflictingTypeTest {
  /**
   * Create an analysis engine for a Ruta script.
   *
   * @param script
   *          Script path.
   * @return Analysis engine.
   */
  private AnalysisEngine createAE(String script) throws Exception {
    final TypeSystemDescription tsd = TypeSystemDescriptionFactory.createTypeSystemDescription(
            "org.apache.uima.ruta.engine.BasicTypeSystem",
            "org.apache.uima.ruta.ConflictingTypeSystem");
    final AnalysisEngineDescription ruta = AnalysisEngineFactory.createEngineDescription(
            "org.apache.uima.ruta.engine.BasicEngine", RutaEngine.PARAM_MAIN_SCRIPT, script);

    ruta.getAnalysisEngineMetaData().setTypeSystem(tsd);
    ruta.getAnalysisEngineMetaData().getTypeSystem().addType("org.apache.uima.T1",
            "Type for Testing", "uima.tcas.Annotation");

    return AnalysisEngineFactory.createEngine(ruta);
  }

  @Test
  public void test() throws Exception {
    final String name = getClass().getSimpleName();
    final String namespace = getClass().getPackage().getName();
    final AnalysisEngine ae = createAE(namespace + "." + name);
    final CAS cas = ae.newCAS();

    cas.setDocumentText("text is not relevant here");

    try {
      assertThatExceptionOfType(AnalysisEngineProcessException.class)
              .as("An IllegalArgumentException should be triggered because W is ambiguous.")
              .isThrownBy(() -> ae.process(cas))
              .withCauseInstanceOf(IllegalArgumentException.class);
    } finally {
      cas.release();
      ae.destroy();
    }
  }
}
