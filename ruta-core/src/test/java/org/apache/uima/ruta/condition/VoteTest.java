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

package org.apache.uima.ruta.condition;

import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.junit.Test;

public class VoteTest {

  @Test
  public void test() {

    CAS cas = RutaTestUtils.processTestScript(this.getClass());

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1,
            "Use the VOTE Condition, to compare the number of occurrences"
                    + " of two different annotations.");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 0);
  }

  @Test
  public void testInWindowOnly() throws Exception {

    String document = "1 2 . A b C d. 3 4";

    String script = "PERIOD #{-> T1} PERIOD;";
    script += "(CW ANY ANY){->T2};";
    script += "SW{->T3};";
    script += "T1{VOTE(T2,T3)->T4};";
    script += "T1{-VOTE(T2,T3)->T5};";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 4, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 5, 1, "A b C d");

  }

}
