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

package org.apache.uima.ruta.rule;

import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.junit.Test;

public class ManualAnchoringTest {

  @Test
  public void test() throws Exception {
    String document = "A, B and C.";
    String script = "";
    script += "CW{-> T1};\n";
    script += "\"and\"{-> T2};\n";
    script += "T1 (COMMA T1)* @T2 T1 {->MARK(T3,1,4)};\n";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "A, B and C");
  }

  @Test
  public void testInWindow() throws Exception {
    String document = "A, B and C. A, B and C.";
    String script = "";
    script += "ANY+{-PARTOF({PERIOD,T1}) -> T1};\n";
    script += "BLOCK(block) T1{}{\n";
    script += "SW CW{-> T2};\n";
    script += "SW @CW{-> T3};\n";
    script += "}\n";
    script += "T1->{\n";
    script += "SW CW{-> T4};\n";
    script += "SW @CW{-> T5};\n";
    script += "};\n";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 2, 2, "C", "C");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 2, "C", "C");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 2, "C", "C");
    RutaTestUtils.assertAnnotationsEquals(cas, 5, 2, "C", "C");
  }

}
