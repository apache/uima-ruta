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

public class WildCard1Test {

  @Test
  public void test() {

    CAS cas = RutaTestUtils.processTestScript(this.getClass());

    final String TEXT = "The Ruta language is an imperative rule language extended with scripting elements";

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, cas.getDocumentText());
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "The");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, TEXT + ".");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 8);
    RutaTestUtils.assertAnnotationsEquals(cas, 5, 1, TEXT);
    RutaTestUtils.assertAnnotationsEquals(cas, 6, 1, TEXT);
    RutaTestUtils.assertAnnotationsEquals(cas, 7, 1, cas.getDocumentText());
    RutaTestUtils.assertAnnotationsEquals(cas, 8, 1, cas.getDocumentText());
    RutaTestUtils.assertAnnotationsEquals(cas, 9, 2);

    RutaTestUtils.assertAnnotationsEquals(cas, 10, 1, TEXT);
    RutaTestUtils.assertAnnotationsEquals(cas, 11, 9);
    RutaTestUtils.assertAnnotationsEquals(cas, 12, 1, TEXT + ".");
    RutaTestUtils.assertAnnotationsEquals(cas, 13, 8);
    RutaTestUtils.assertAnnotationsEquals(cas, 14, 1, TEXT + ".");

    RutaTestUtils.assertAnnotationsEquals(cas, 16, 4);
    RutaTestUtils.assertAnnotationsEquals(cas, 17, 4, "The", "If", "The", "The");
    RutaTestUtils.assertAnnotationsEquals(cas, 18, 4);
    RutaTestUtils.assertAnnotationsEquals(cas, 19, 8);
    RutaTestUtils.assertAnnotationsEquals(cas, 20, 4);
    RutaTestUtils.assertAnnotationsEquals(cas, 21, 4);
    RutaTestUtils.assertAnnotationsEquals(cas, 22, 4);
    RutaTestUtils.assertAnnotationsEquals(cas, 23, 4);
    RutaTestUtils.assertAnnotationsEquals(cas, 24, 2);
    RutaTestUtils.assertAnnotationsEquals(cas, 25, 4);
    RutaTestUtils.assertAnnotationsEquals(cas, 26, 9);
    RutaTestUtils.assertAnnotationsEquals(cas, 27, 4);
    RutaTestUtils.assertAnnotationsEquals(cas, 28, 8);
    RutaTestUtils.assertAnnotationsEquals(cas, 29, 1);

    cas.release();
  }
}
