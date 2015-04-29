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
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.junit.Test;

public class RuleInference1Test {

  @Test
  public void test() {

    CAS cas = RutaTestUtils.processTestScript(this.getClass());

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 4, "Peter", "Jochen", "Flo", "Georg");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "und");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 5, "Peter", "Jochen", "Flo und Georg", "und Georg", "Georg");
    RutaTestUtils.assertAnnotationsEquals(cas, 5, 2, ",", ",");
    RutaTestUtils.assertAnnotationsEquals(cas, 6, 2, "Peter, Jochen,", "Jochen,");
    RutaTestUtils.assertAnnotationsEquals(cas, 7, 5, "Peter", "Jochen", "Flo", "und", "Georg");
    RutaTestUtils.assertAnnotationsEquals(cas, 8, 5, "Peter", "Jochen", "Flo und Georg", "und Georg", "Georg");
    RutaTestUtils.assertAnnotationsEquals(cas, 9, 2, "Peter, Jochen, Flo", "Jochen, Flo");
    RutaTestUtils.assertAnnotationsEquals(cas, 10, 3, "Peter, Jochen, Flo und", "Jochen, Flo und", "Flo und");
    RutaTestUtils.assertAnnotationsEquals(cas, 11, 3, "Peter, Jochen, Flo und Georg.", "Jochen, Flo und Georg.",
            "Flo und Georg.");
    RutaTestUtils.assertAnnotationsEquals(cas, 12, 6, ",", ",", ",", "und", "und", "und");
    RutaTestUtils.assertAnnotationsEquals(cas, 13, 6, "Peter,", "Jochen,", "Jochen,", "Flo und", "Flo und", "Flo und");
    RutaTestUtils.assertAnnotationsEquals(cas, 14, 3, "Peter, Jochen, Flo und", "Jochen, Flo und", "Flo und");
    RutaTestUtils.assertAnnotationsEquals(cas, 15, 1, "Peter, Jochen");
    RutaTestUtils.assertAnnotationsEquals(cas, 16, 1, "Georg.");
    RutaTestUtils.assertAnnotationsEquals(cas, 17, 1, "Flo und Georg.");
    RutaTestUtils.assertAnnotationsEquals(cas, 18, 3, "Peter,", "Jochen,", "Georg.");
    RutaTestUtils.assertAnnotationsEquals(cas, 20, 1, "Peter, Jochen");
    RutaTestUtils.assertAnnotationsEquals(cas, 21, 1, "Georg.");
    RutaTestUtils.assertAnnotationsEquals(cas, 22, 1, "Flo und Georg.");

    cas.release();
  }
}
