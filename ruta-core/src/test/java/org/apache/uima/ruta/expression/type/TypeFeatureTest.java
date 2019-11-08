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
package org.apache.uima.ruta.expression.type;

import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.junit.Test;

public class TypeFeatureTest {

  @Test
  public void test() throws Exception {

    String document = "This is a test.";

    String script = "CW{-> T1};\n";
    script += "SW{-> T2};\n";
    script += "T1{-> MARK(T1.type)};\n";
    script += "t2:T2{-> t2.type};\n";
    script += "(a1:ANY a2:ANY){a1.type==a2.type -> T3};\n";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 2, "This", "This");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 6, "is", "is", "a", "a", "test", "test");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 2, "is a", "a test");

  }

}
