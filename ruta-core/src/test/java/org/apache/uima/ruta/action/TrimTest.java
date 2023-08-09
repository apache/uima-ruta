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

import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.junit.jupiter.api.Test;

public class TrimTest {

  @Test
  public void test() throws Exception {

    CAS cas = RutaTestUtils.processTestScriptWithDefaultSeeder(this.getClass());

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "some text");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "some text");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "some text");

    cas.release();
  }

  @Test
  public void testOverlappingBoundary() {

    String document = "text (in paren) text.";
    String script = "";
    script += "(SPECIAL # SPECIAL){-> T1};";
    script += "(SPECIAL.ct==\"(\" SW){->T2};";
    script += "(SW SPECIAL.ct==\")\"){->T2};";
    script += "T2{->TRIM(T1)};";
    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document);
      Ruta.apply(cas, script);
    } catch (Exception e) {
      e.printStackTrace();
    }

    RutaTestUtils.assertAnnotationsEquals(cas, 2, 0);

  }

}
