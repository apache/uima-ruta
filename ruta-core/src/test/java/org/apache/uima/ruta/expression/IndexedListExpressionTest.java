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
import org.junit.Test;

public class IndexedListExpressionTest {
  @Test
  public void test() {
    String document = "Some text.";
    String script = "";
    script += "STRINGLIST sl = {\"Some\", \"text\"}";
    script += "W.ct == sl[0]{-> T1};";
    script += "W.ct == sl[1]{-> T2};";
    script += "W{REGEXP(sl[0]) -> T3};";
    script += "W{REGEXP(sl[1]) -> T4};";

    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document);
      Ruta.apply(cas, script);
    } catch (Exception e) {
      e.printStackTrace();
    }

//    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Some");
//    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "text");
//    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "Some");
//    RutaTestUtils.assertAnnotationsEquals(cas, 4, 1, "text");
    
  }
}
