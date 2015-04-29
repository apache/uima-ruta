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

public class ReverseQuantifierTest {
  @Test
  public void test() {
    String document = "A B C. A C. B C. C";
    String script = "";
    script += "W??{REGEXP(\"A\")} W??{REGEXP(\"B\")} @W{REGEXP(\"C\") -> MARK(T2,1,3)};\n";
    script += "W?{REGEXP(\"A\")} W?{REGEXP(\"B\")} @W{REGEXP(\"C\") -> MARK(T1,1,3)};\n";
    
    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document);
      Ruta.apply(cas, script);
    } catch (Exception e) {
      e.printStackTrace();
    }

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 4, "A B C", "A C", "B C", "C");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 4, "B C", "C", "B C", "C");

    cas.release();
  }
}
