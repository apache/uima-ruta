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

public class IncrementalPartitioningTest {

  @Test
  public void test() {
    String document = "fuenfundzwanzig";
    String script = "";
    script += "\"fuenf\"->T1;";
    script += "\"und\"->T2;";
    script += "\"zwanzig\"->T1;";
    script += "T1{PARTOF(W)-> T4};";
    script += "T1{STARTSWITH(W)-> T5};";
    script += "T1{ENDSWITH(W)-> T6};";
    
    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document);
      Ruta.apply(cas, script);
    } catch (Exception e) {
      e.printStackTrace();
    }

    RutaTestUtils.assertAnnotationsEquals(cas, 4, 2, "fuenf", "zwanzig");
    RutaTestUtils.assertAnnotationsEquals(cas, 5, 1, "fuenf");
    RutaTestUtils.assertAnnotationsEquals(cas, 6, 1, "zwanzig");
    
    if (cas != null) {
      cas.release();
    }

  }
}
