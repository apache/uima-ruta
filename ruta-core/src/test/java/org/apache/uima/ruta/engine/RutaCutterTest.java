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

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.ruta.type.TruePositive;
import org.junit.jupiter.api.Test;

public class RutaCutterTest {

  @Test
  public void test() throws Exception {

    JCas jcas = JCasFactory.createJCas();
    AnalysisEngine cutter = AnalysisEngineFactory.createEngine(RutaCutter.class,
            RutaCutter.PARAM_KEEP, TruePositive.class.getName());
    jcas.setDocumentText("Some text.");
    Ruta.apply(jcas.getCas(), "SW{-> TruePositive};");
    cutter.process(jcas);

    Collection<TruePositive> select = JCasUtil.select(jcas, TruePositive.class);
    assertThat(select).hasSize(1);
    TruePositive truePositive = select.iterator().next();
    assertThat(truePositive.getBegin()).isEqualTo(5);

    JCas cuttedView = jcas.getView("cutted");
    assertThat(cuttedView.getDocumentText()).isEqualTo("text");
    select = JCasUtil.select(cuttedView, TruePositive.class);
    assertThat(select).hasSize(1);
    truePositive = select.iterator().next();
    assertThat(truePositive.getBegin()).isEqualTo(0);

    jcas.release();
    cutter.destroy();
  }

}
