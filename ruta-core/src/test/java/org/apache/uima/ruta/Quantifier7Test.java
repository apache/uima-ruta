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

public class Quantifier7Test {
  @Test
  public void test() {
    String document = "n 1 2 3, H 1 2 3, i 1 2 3, i 1 2 3, i 1 2 3, n 1 2 3, n 1 2 3";
    String script = "\"H\" -> T1;\n";
    script += "\"i\" -> T2;\n";
    script += "COMMA {-> T3};\n";
    script += "COMMA #{-> T5} COMMA;\n";
    script += "T5 { CONTAINS(T1)} (T3? T5 { CONTAINS(T2) } T3?)* { -> MARK(T4, 1, 2) };\n";
    
    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document);
      Ruta.apply(cas, script);
    } catch (Exception e) {
      e.printStackTrace();
    }

    RutaTestUtils.assertAnnotationsEquals(cas, 4, 1, "H 1 2 3, i 1 2 3, i 1 2 3, i 1 2 3,");

    cas.release();
  }
}
