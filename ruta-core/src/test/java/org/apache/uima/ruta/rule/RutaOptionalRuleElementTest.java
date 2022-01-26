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

public class RutaOptionalRuleElementTest {

  @Test
  public void test() throws Exception {
    String document = "This is a Test";
    String script = "_{-PARTOF(CW)} @W{-> T1};\n";
    script += "@W{-> T2} _{-PARTOF(CW)};\n";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 3, "This", "a", "Test");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 3, "This", "is", "Test");

  }

  @Test
  public void testOptionalBeforeComposed() throws Exception {
    String document = "test 05/05 test\n";
    document += "test 06/06 . test\n";
    document += "test . 07/07 test\n";
    String script = "ADDRETAINTYPE(WS);";
    script += "_{PARTOF(PM)} (NUM SPECIAL @NUM){-> T1} _{PARTOF({PM})};\n";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "05/05");

  }

}
