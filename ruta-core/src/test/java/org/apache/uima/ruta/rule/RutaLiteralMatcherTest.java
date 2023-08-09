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
import org.junit.jupiter.api.Test;

public class RutaLiteralMatcherTest {

  @Test
  public void test() throws Exception {

    String text = "This is a test.";
    String script = "";
//     script += "\"" + text + "\" {-> T1};\n";
//    script += "\"is a\" {-> T2} \"test.\";\n";
//    script += "\"is a test\" {-> T3} @PERIOD;\n";
//    script += "\" \" {-> T4};\n";
//    script += "ADDRETAINTYPE(SPACE);\n\" \" {-> T5};\nREMOVERETAINTYPE(SPACE);\n";
    script += "\" is a test\" {-> T6} @PERIOD;\n";

    CAS cas = RutaTestUtils.getCAS(text);
    Ruta.apply(cas, script);

//    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, text);
//    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "is a");
//    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "is a test");
//    RutaTestUtils.assertAnnotationsEquals(cas, 4, 0);
//    RutaTestUtils.assertAnnotationsEquals(cas, 5, 3, " ", " ", " ");
    RutaTestUtils.assertAnnotationsEquals(cas, 6, 0);
  }
}
