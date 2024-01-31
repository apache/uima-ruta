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
package org.apache.uima.ruta.expression.number;

import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.junit.jupiter.api.Test;

public class NumberExpressionTest {

  @Test
  public void testComposedAssignment() throws Exception {
    String script = "INT i = 1;";
    script += "Document{i==2 -> T1};";
    script += "Document{-> i = i + 1};";
    script += "Document{i==2 -> T2};";
    script += "Document{-> i = 1 + 1 + 1};";
    script += "Document{i==3 -> T3};";
    script += "Document{i== 1+1+1 -> T4};";
    script += "Document{i== 1+i-1 -> T5};";
    script += "Document{-> i = (1 + 1 * 3) / 2};";
    script += "Document{i== 8 / 4 -> T6};";
    CAS cas = RutaTestUtils.getCAS("This is a test.");
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "This is a test.");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "This is a test.");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 1, "This is a test.");
    RutaTestUtils.assertAnnotationsEquals(cas, 5, 1, "This is a test.");
    RutaTestUtils.assertAnnotationsEquals(cas, 6, 1, "This is a test.");
  }

}
