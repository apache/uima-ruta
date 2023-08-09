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

import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.junit.jupiter.api.Test;
public class DisjunctiveRuleElementTest {

  @Test
  public void testOptionalDisjunctiveInMinMax() {
    String document = ". Cw 1 2:3,4 sw . Cw 1 sw . Cw 1:2, sw . Cw 1,2,3,4,5,6,7 sw";
    
    String script = "PERIOD CW (NUM{-> T1} (COMMA | SEMICOLON | COLON)?)[1,5] SW{-> T2};";
    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document);
      Ruta.apply(cas, script);
    } catch (Exception e) {
      e.printStackTrace();
    }

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 7, "1", "2", "3", "4", "1", "1", "2");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 3, "sw", "sw", "sw");

    cas.release();
  }
}
