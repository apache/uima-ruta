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

package org.apache.uima.ruta.action;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.apache.uima.cas.CAS;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.type.Statistics;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class ConfigureTest {

  @Test
  public void test() throws Exception {

    String script = "ENGINE org.apache.uima.ruta.engine.CWEngine;";
    script += "CONFIGURE(CWEngine, \"profile\"= true, \"statistics\"=true);";
    script += "EXEC(CWEngine);";
    CAS cas = RutaTestUtils.getCAS("Some test.");
    Ruta.apply(cas, script);

    JCas jcas = cas.getJCas();
    Collection<Statistics> statistics = JCasUtil.select(jcas, Statistics.class);

    assertThat(statistics.isEmpty()).isFalse();
  }

  @Test
  @Disabled
  public void testSelfConfigure() throws Exception {

    RutaTestUtils.processTestScript(this.getClass());
  }

}
