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

public class SidestepInComposedTest {

  @Test
  public void test() throws Exception {
    String document = "15. Mai 2005";
    String script = "\"Mai\" -> T1;";
    script += "NUM{->T2} PERIOD @T1 NUM;\n";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "15");
  }

  @Test
  public void testAnchorAtDisjunct() throws Exception {
    String document = "15. Mai 2005";
    String script = "(NUM PERIOD @(SW | CW) NUM){-> T1};\n";
    script += "(NUM PERIOD (@((SW | CW))) NUM){-> T2};\n";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "15. Mai 2005");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "15. Mai 2005");

  }

  @Test
  public void testAnchorAtConjunct() throws Exception {
    String document = "15. Mai 2005";
    String script = "(NUM PERIOD @(W & CW) NUM){-> T1};\n";
    script += "(NUM PERIOD (@((CW & W))) NUM){-> T2};\n";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "15. Mai 2005");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "15. Mai 2005");

  }

  @Test
  public void testOptionalBeforeComposed() throws Exception {
    String document = "test 05/05 test\n";
    document += "test 06/06 . test\n";
    document += "test . 07/07 test\n";
    String script = "_{-PARTOF(PM)} (NUM SPECIAL @NUM){-> T1} _{-PARTOF({PM})};\n";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "05/05");

  }

}
