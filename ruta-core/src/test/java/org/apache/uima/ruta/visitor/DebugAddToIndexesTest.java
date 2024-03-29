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

package org.apache.uima.ruta.visitor;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.uima.cas.CAS;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.type.DebugRuleApply;
import org.junit.jupiter.api.Test;

public class DebugAddToIndexesTest {

  @Test
  public void test() throws Exception {

    String document = "This is a test.";
    String script = "";
    script += "CW;";

    CAS cas = RutaTestUtils.getCAS(document);
    Map<String, Object> parameters = new HashMap<>();
    parameters.put(RutaEngine.PARAM_DEBUG, Boolean.TRUE);
    parameters.put(RutaEngine.PARAM_DEBUG_WITH_MATCHES, Boolean.TRUE);
    parameters.put(RutaEngine.PARAM_DEBUG_ADD_TO_INDEXES, Boolean.TRUE);

    Ruta.apply(cas, script, parameters);

    JCas jcas = cas.getJCas();

    Collection<DebugRuleApply> debugRuleApplies = JCasUtil.select(jcas, DebugRuleApply.class);
    assertThat(debugRuleApplies).hasSize(3);
    Iterator<DebugRuleApply> iterator = debugRuleApplies.iterator();
    iterator.next();
    assertThat(iterator.next().getElement()).isEqualTo("Document;");
    assertThat(iterator.next().getElement()).isEqualTo("CW;");
  }
}
