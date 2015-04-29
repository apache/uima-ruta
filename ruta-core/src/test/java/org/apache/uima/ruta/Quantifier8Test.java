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

package org.apache.uima.ruta;

import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.junit.Test;

public class Quantifier8Test {
  @Test
  public void test() {
    String document = "A B C D. A B D.";
    String script = "";
    script += "W{REGEXP(\"A\")} W{REGEXP(\"B\")} W?{REGEXP(\"C\")} W{REGEXP(\"D\") -> MARK(T1,1,4)};\n";
    script += "W{REGEXP(\"A\")} W{REGEXP(\"B\")} W*{REGEXP(\"C\")} W{REGEXP(\"D\") -> MARK(T2,1,4)};\n";
    script += "W{REGEXP(\"A\")} W{REGEXP(\"B\")} W[0,2]{REGEXP(\"C\")} W{REGEXP(\"D\") -> MARK(T3,1,4)};\n";
    
    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document);
      Ruta.apply(cas, script);
    } catch (Exception e) {
      e.printStackTrace();
    }

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 2, "A B C D", "A B D");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 2, "A B C D", "A B D");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 2, "A B C D", "A B D");

    cas.release();
  }
}
