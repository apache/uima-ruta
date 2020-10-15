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

import java.util.Arrays;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.jcas.JCas;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.type.CW;
import org.apache.uima.ruta.type.RutaBasic;
import org.junit.Before;
import org.junit.Test;

public class RutaBasicUtilsTest {

  // TODO use uimafit when released instead
  // public @Rule ManagedJCas managedJCas = new ManagedJCas();

  private static ThreadLocal<JCas> managedJCas = new ThreadLocal<>();

  static {

    try {
      JCas jCas = JCasFactory.createJCas();
      managedJCas.set(jCas);
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

  @Before
  public void setup() {
    managedJCas.get().reset();
  }

  @Test(expected = AnalysisEngineProcessException.class)
  public void testBreakOnNoBasics() throws AnalysisEngineProcessException {

    RutaBasicUtils.validateInternalIndexing(managedJCas.get(), null);
  }

  @Test(expected = AnalysisEngineProcessException.class)
  public void testBreakOnDuplicateBasics() throws AnalysisEngineProcessException {
    JCas jcas = managedJCas.get();
    new RutaBasic(jcas, 0, 1).addToIndexes();
    new RutaBasic(jcas, 0, 1).addToIndexes();
    RutaBasicUtils.validateInternalIndexing(jcas, null);
  }

  @Test(expected = AnalysisEngineProcessException.class)
  public void testBreakOnMissingBasicAtBegin() throws AnalysisEngineProcessException {
    JCas jcas = managedJCas.get();
    new RutaBasic(jcas, 1, 2).addToIndexes();
    new CW(jcas, 0, 2).addToIndexes();
    RutaBasicUtils.validateInternalIndexing(jcas, null);
  }

  @Test(expected = AnalysisEngineProcessException.class)
  public void testBreakOnMissingBasicAtEnd() throws AnalysisEngineProcessException {
    JCas jcas = managedJCas.get();
    new RutaBasic(jcas, 0, 1).addToIndexes();
    new CW(jcas, 0, 2).addToIndexes();
    RutaBasicUtils.validateInternalIndexing(jcas, null);
  }

  @Test(expected = AnalysisEngineProcessException.class)
  public void testBreakOnMissingAnnotationAtBegin() throws AnalysisEngineProcessException {
    JCas jcas = managedJCas.get();
    CW cw = new CW(jcas, 0, 1);
    cw.addToIndexes();
    RutaBasic rb = new RutaBasic(jcas, 0, 1);
    rb.addEnd(cw, cw.getType());
    rb.addToIndexes();
    RutaBasicUtils.validateInternalIndexing(jcas, null);
  }

  @Test(expected = AnalysisEngineProcessException.class)
  public void testBreakOnMissingAnnotationAtEnd() throws AnalysisEngineProcessException {
    JCas jcas = managedJCas.get();
    CW cw = new CW(jcas, 0, 1);
    cw.addToIndexes();
    RutaBasic rb = new RutaBasic(jcas, 0, 1);
    rb.addBegin(cw, cw.getType());
    rb.addToIndexes();
    RutaBasicUtils.validateInternalIndexing(jcas, null);
  }

  @Test(expected = AnalysisEngineProcessException.class)
  public void testBreakOnMissingPartof() throws AnalysisEngineProcessException {
    JCas jcas = managedJCas.get();
    CW cw = new CW(jcas, 0, 1);
    cw.addToIndexes();
    RutaBasic rb = new RutaBasic(jcas, 0, 1);
    rb.addBegin(cw, cw.getType());
    rb.addEnd(cw, cw.getType());
    rb.addToIndexes();
    RutaBasicUtils.validateInternalIndexing(jcas, null);
  }

  @Test
  public void testIgnoreTypeNames() throws AnalysisEngineProcessException {
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
