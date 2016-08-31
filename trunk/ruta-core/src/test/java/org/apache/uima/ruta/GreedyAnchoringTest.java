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

import java.util.HashMap;
import java.util.Map;

import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.junit.Test;

public class GreedyAnchoringTest {

  @Test
  public void testGreedyRuleElement() {
    String document = "Peter Kluegl, Joern Kottmann, Marshall Schor.";
    String script = "";
    script += "ANY+{->T1};";
    script += "ANY*{->T2};";
    script += "((SW | CW) (W & CW) PM)+{->T3};";
    
    CAS cas = null;
    Map<String, Object> params = new HashMap<String, Object>();
    params.put(RutaEngine.PARAM_GREEDY_RULE_ELEMENT, true);
    try {
      cas = RutaTestUtils.getCAS(document);
      Ruta.apply(cas, script, params);
    } catch (Exception e) {
      e.printStackTrace();
    }

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, document);
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, document);
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, document);

    cas.release();
  }
  
  @Test
  public void testGreedyRule() {
    String document = "Peter Kluegl Joern Kottmann Marshall Schor.";
    String script = "";
    script += "(CW CW){->T1};";
    script += "((SW | CW) (W & CW)){->T2};";
    
    CAS cas = null;
    Map<String, Object> params = new HashMap<String, Object>();
    params.put(RutaEngine.PARAM_GREEDY_RULE, true);
    try {
      cas = RutaTestUtils.getCAS(document);
      Ruta.apply(cas, script, params);
    } catch (Exception e) {
      e.printStackTrace();
    }

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 3, "Peter Kluegl", "Joern Kottmann", "Marshall Schor");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 3, "Peter Kluegl", "Joern Kottmann", "Marshall Schor");

    cas.release();
  }
}
