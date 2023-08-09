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
public class ComposedRuleElementTest {

  @Test
  public void testDisjunctiveOnDuplicates() throws Exception {
    // UIMA-6324
    String text = "A B A B";
    String script = "\"A\" {-> T1, T1};\n";
    script += "\"A\" {-> T2};\n";
    script += "T1{-> T3};\n";
    script += "(T1 | T2){-> T4};\n";

    CAS cas = RutaTestUtils.getCAS(text);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 3, 4, "A", "A", "A", "A");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 2, "A", "A");
  }

  @Test
  public void testDisjunctiveSequenceCombination() throws Exception {
    // UIMA-6324
    String text = "A B A B";

    String script = "\"A\" {-> T1};\n";
    script += "\"B\" {-> T2};\n";
    script += "((T2 T1) | ( \"B\" \"A\")){-> T3} W;\n";
    script += "((T2 T1) | ( \"B\" \"A\")){-> T4} @W;\n";

    CAS cas = RutaTestUtils.getCAS(text);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "B A");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 1, "B A");
  }

  @Test
  public void testConjunctiveSequenceCombination() throws Exception {
    // UIMA-6324
    String text = "A B A B";

    String script = "\"A\" {-> T1};\n";
    script += "\"B\" {-> T2};\n";
    script += "((T2 T1) & ( \"B\" \"A\")){-> T3} W;\n";
    script += "((T2 T1) & ( \"B\" \"A\")){-> T4} @W;\n";

    CAS cas = RutaTestUtils.getCAS(text);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "B A");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 1, "B A");
  }

}
