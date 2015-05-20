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

public class ConjunctRulesTest {

  @Test
  public void test() {
    String document = "Peter Kluegl, Joern Kottmann, Marshall Schor.";
    String script = "DECLARE T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T14, T15, T16, T17, T18;\n";
    script += "W{REGEXP(\"Peter\")} ANY{-> T1} % ANY{REGEXP(\"J.*\")} CW PM{->MARK(T2)} % (COMMA # PM){->MARK(T3)};";
    script += "W{REGEXP(\"Peter\")} ANY{-> T4} % ANY{REGEXP(\"J.*\")} CW PM{->MARK(T5)} % (COMMA # QUESTION){->MARK(T6)};";
    
    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document);
      Ruta.apply(cas, script);
    } catch (Exception e) {
      e.printStackTrace();
    }

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Kluegl");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, ",");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 2, ", Joern Kottmann,", ", Marshall Schor.");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 5, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 6, 0);

    cas.release();
  }
}
