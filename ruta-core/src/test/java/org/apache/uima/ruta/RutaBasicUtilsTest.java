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

import java.util.Arrays;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.fit.testing.junit.ManagedJCas;
import org.apache.uima.jcas.JCas;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.type.CW;
import org.apache.uima.ruta.type.RutaBasic;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

public class RutaBasicUtilsTest {

  private static @RegisterExtension ManagedJCas managedJCas = new ManagedJCas();

  @Test
  public void testBreakOnNoBasics() throws Exception {
    assertThatExceptionOfType(AnalysisEngineProcessException.class)
            .isThrownBy(() -> RutaBasicUtils.validateInternalIndexing(managedJCas.get(), null));
  }

  @Test
  public void testBreakOnDuplicateBasics() throws Exception {
    JCas jcas = managedJCas.get();
    new RutaBasic(jcas, 0, 1).addToIndexes();
    new RutaBasic(jcas, 0, 1).addToIndexes();

    assertThatExceptionOfType(AnalysisEngineProcessException.class)
            .isThrownBy(() -> RutaBasicUtils.validateInternalIndexing(jcas, null));
  }

  @Test
  public void testBreakOnMissingBasicAtBegin() throws Exception {
    JCas jcas = managedJCas.get();
    new RutaBasic(jcas, 1, 2).addToIndexes();
    new CW(jcas, 0, 2).addToIndexes();

    assertThatExceptionOfType(AnalysisEngineProcessException.class)
            .isThrownBy(() -> RutaBasicUtils.validateInternalIndexing(jcas, null));
  }

  @Test
  public void testBreakOnMissingBasicAtEnd() throws Exception {
    JCas jcas = managedJCas.get();
    new RutaBasic(jcas, 0, 1).addToIndexes();
    new CW(jcas, 0, 2).addToIndexes();

    assertThatExceptionOfType(AnalysisEngineProcessException.class)
            .isThrownBy(() -> RutaBasicUtils.validateInternalIndexing(jcas, null));
  }

  @Test
  public void testBreakOnMissingAnnotationAtBegin() throws Exception {
    JCas jcas = managedJCas.get();
    CW cw = new CW(jcas, 0, 1);
    cw.addToIndexes();
    RutaBasic rb = new RutaBasic(jcas, 0, 1);
    rb.addEnd(cw, cw.getType());
    rb.addToIndexes();

    assertThatExceptionOfType(AnalysisEngineProcessException.class)
            .isThrownBy(() -> RutaBasicUtils.validateInternalIndexing(jcas, null));
  }

  @Test
  public void testBreakOnMissingAnnotationAtEnd() throws Exception {
    JCas jcas = managedJCas.get();
    CW cw = new CW(jcas, 0, 1);
    cw.addToIndexes();
    RutaBasic rb = new RutaBasic(jcas, 0, 1);
    rb.addBegin(cw, cw.getType());
    rb.addToIndexes();

    assertThatExceptionOfType(AnalysisEngineProcessException.class)
            .isThrownBy(() -> RutaBasicUtils.validateInternalIndexing(jcas, null));
  }

  @Test
  public void testBreakOnMissingPartof() throws Exception {
    JCas jcas = managedJCas.get();
    CW cw = new CW(jcas, 0, 1);
    cw.addToIndexes();
    RutaBasic rb = new RutaBasic(jcas, 0, 1);
    rb.addBegin(cw, cw.getType());
    rb.addEnd(cw, cw.getType());
    rb.addToIndexes();

    assertThatExceptionOfType(AnalysisEngineProcessException.class)
            .isThrownBy(() -> RutaBasicUtils.validateInternalIndexing(jcas, null));
  }

  @Test
  public void testIgnoreTypeNames() throws Exception {
    JCas jcas = managedJCas.get();
    new RutaBasic(jcas, 0, 1).addToIndexes();
    new CW(jcas, 0, 1).addToIndexes();
    RutaBasicUtils.validateInternalIndexing(jcas, Arrays.asList(CAS.TYPE_NAME_ANNOTATION));
  }

  @Test
  public void testAllGood() throws Exception {
    JCas jcas = managedJCas.get();
    jcas.setDocumentText("This is 1 TEST.");
    Ruta.apply(jcas.getCas(), "CW{-> TruePositive};");
    RutaBasicUtils.validateInternalIndexing(jcas, null);
  }
}
