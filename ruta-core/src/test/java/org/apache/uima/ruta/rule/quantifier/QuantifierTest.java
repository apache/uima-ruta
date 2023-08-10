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

package org.apache.uima.ruta.rule.quantifier;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.junit.jupiter.api.Test;

public class QuantifierTest {

  @Test
  public void testRightToLeftMinMaxReluctantToLiteral() throws Exception {

    JCas jcas = RutaTestUtils.getCAS("a b c d e.").getJCas();
    assertThat(Ruta.select(jcas, "\"a\" W[0,5]? @PERIOD;").get(0).getCoveredText())
            .isEqualTo("a b c d e.");
    assertThat(Ruta.select(jcas, "\"b\" W[0,5]? @PERIOD;").get(0).getCoveredText())
            .isEqualTo("b c d e.");
    assertThat(Ruta.select(jcas, "\"c\" W[0,5]? @PERIOD;").get(0).getCoveredText())
            .isEqualTo("c d e.");
    assertThat(Ruta.select(jcas, "\"d\" W[0,5]? @PERIOD;").get(0).getCoveredText())
            .isEqualTo("d e.");
    assertThat(Ruta.select(jcas, "\"e\" W[0,5]? @PERIOD;").get(0).getCoveredText()).isEqualTo("e.");

    jcas.release();
  }

  @Test
  public void testRightToLeftMinMaxGreedyToLiteral() throws Exception {

    JCas jcas = RutaTestUtils.getCAS("a b c d e.").getJCas();
    assertThat(Ruta.select(jcas, "\"a\" W[0,4] @PERIOD;").get(0).getCoveredText())
            .isEqualTo("a b c d e.");
    assertThat(Ruta.select(jcas, "\"b\" W[0,3] @PERIOD;").get(0).getCoveredText())
            .isEqualTo("b c d e.");
    assertThat(Ruta.select(jcas, "\"c\" W[0,2] @PERIOD;").get(0).getCoveredText())
            .isEqualTo("c d e.");
    assertThat(Ruta.select(jcas, "\"d\" W[0,1] @PERIOD;").get(0).getCoveredText())
            .isEqualTo("d e.");
    assertThat(Ruta.select(jcas, "\"e\" W[0,0] @PERIOD;").get(0).getCoveredText()).isEqualTo("e.");

    jcas.release();
  }

  @Test
  public void testReluctantGreedyInComposed() throws Exception {
    JCas jcas = RutaTestUtils.getCAS("a B B . a B . a .").getJCas();
    List<Annotation> select = Ruta.select(jcas, "SW (CW+?) PERIOD;");
    assertThat(select).hasSize(2);
  }

}
