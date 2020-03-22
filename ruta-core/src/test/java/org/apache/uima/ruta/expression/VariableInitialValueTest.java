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

package org.apache.uima.ruta.expression;

import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.junit.Ignore;
import org.junit.Test;

public class VariableInitialValueTest {

  @Test
  public void testStringVariableAsInitialValueOfList() throws Exception {
    String document = "This is a test.";

    String script = "";
    script += "STRING s = \"a\";";
    script += "STRINGLIST sl = {s};";
    script += "Document{CONTAINS(sl, \"a\") -> T1};";
    script += "Document{ -> s = \"b\"};";
    script += "Document{CONTAINS(sl, \"a\") -> T2};";
    script += "Document{CONTAINS(sl, \"b\") -> T3};";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "This is a test.");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "This is a test.");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 0);

  }

  @Test
  @Ignore
  public void testIntVariableAsInitialValueOfList() throws Exception {
    String document = "This is a test.";

    String script = "";
    script += "INT i = 1;";
    script += "INTLIST il = {i};";
    script += "Document{CONTAINS(il, 1) -> T1};";
    script += "Document{ -> i = 2};";
    script += "Document{CONTAINS(il, 1) -> T2};";
    script += "Document{CONTAINS(il, 2) -> T3};";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "This is a test.");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "This is a test.");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 0);

  }

}
