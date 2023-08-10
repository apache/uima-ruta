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

package org.apache.uima.ruta.condition;

import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.junit.jupiter.api.Test;

public class BeforeTest {

  @Test
  public void test() {

    CAS cas = RutaTestUtils.processTestScript(this.getClass());

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1,
            "To mark everything before a special annotation");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 2,
            "To mark everything before a special annotation",
            "you need the BEFORE condition of Ruta");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 2,
            "To mark everything before a special annotation",
            "you need the BEFORE condition of Ruta");

  }

  @Test
  public void testInWindow() throws Exception {
    String document = "x x . 1 b A . x x";
    String script = "";
    script += "PERIOD #{-> T1} PERIOD;";
    script += "T1->{NUM{BEFORE(CW)-> T2};};";
    script += "T1->{NUM{BEFORE(PERIOD)-> T3};};";
    script += "T1->{SW{BEFORE(CW)-> T4};};";
    script += "T1->{SW{BEFORE(SW)-> T5};};";
    script += "T1->{CW{BEFORE(PERIOD)-> T6};};";
    script += "T1->{CW{BEFORE(CW)-> T7};};";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "1");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 1, "b");
    RutaTestUtils.assertAnnotationsEquals(cas, 5, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 6, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 7, 0);

  }
}
