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

public class LabelAtActionTest {

  @Test
  public void test() throws Exception {

    String document = "This is a test.";
    String script = "CW{-> t:T1}->{t{->T2};};";
    script += "SW.ct == \"is\"{-> t:MARK(T3)}->{t{->T4};};";
    script += "SW.ct == \"a\"{-> t:CREATE(T5)}->{t{->T6};};";
    script += "SW.ct == \"test\"{-> t:GATHER(T7)}->{t{->T8};};";
    script += "Document{-> t:MARKFIRST(T9)}->{t{->T10};};";
    script += "Document{-> t:MARKLAST(T11)}->{t{->T12};};";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "This");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 1, "is");
    RutaTestUtils.assertAnnotationsEquals(cas, 6, 1, "a");
    RutaTestUtils.assertAnnotationsEquals(cas, 8, 1, "test");

  }

}
