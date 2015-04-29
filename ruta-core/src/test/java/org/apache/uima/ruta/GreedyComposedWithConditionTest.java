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
import org.junit.Test;

public class GreedyComposedWithConditionTest {

  @Test
  public void test() {
    String document = "Cw 1: sw 2 Cw, sw 3 sw, sw 4 Cw, sw 5 sw, Cw 6 sw, Cw 7 sw, 8 sw.";
    String script = "";
    script += "((#{-> T1} (COMMA | PERIOD)))+;";
    script += "T1 (COMMA T1)+{-CONTAINS(CW) -> MARK(T2,1,2)};";
    
    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document);
      Ruta.apply(cas, script);
    } catch (Exception e) {
      e.printStackTrace();
    }

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 7, "Cw 1: sw 2 Cw", "sw 3 sw", "sw 4 Cw", "sw 5 sw", "Cw 6 sw",
            "Cw 7 sw", "8 sw");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 3, "Cw 1: sw 2 Cw, sw 3 sw", "sw 4 Cw, sw 5 sw", "Cw 7 sw, 8 sw");

    cas.release();
  }
}
