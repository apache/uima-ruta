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

public class InlinedRulesTest {

  @Test
  public void test() {
    String document = "The Ruta language is an imperative rule language extended with scripting elements. A Ruta rule defines a pattern of annotations with additional conditions. If this pattern applies, then the actions of the rule are performed  on the matched annotations. A rule is composed of a sequence of rule elements and a rule element essentially consists of four parts: A matching condition, an optional quantifier, a list of conditions and a list of actions. The matching condition is typically a type of an annotation by which the rule element matches on the covered text of one of those annotations. The quantifier specifies, whether it is necessary that the rule element successfully matches and how often the rule element may match. The list of conditions specifies additional constraints that the matched text or annotations need to fulfill. The list of actions defines the consequences of the rule and often creates new annotations or modifies existing annotations.";
    String script = "";
    script += "PERIOD #{-> T1} @PERIOD;\n";
    script += "#{-> T1} PERIOD;\n";
     // inlined as block
     script += "T1{STARTSWITH(Document)}->{CW{->T2};};\n";
     script += "(T1 PERIOD T1){CONTAINS(COLON)}->{COMMA #{->T3} COMMA; COLON ANY{-> T4};};\n";
     script += "(COLON # COMMA)->{ANY{REGEXP(\"a.*\", true)-> T5};};";
     // inlined as condition
     script += "T1{->T6}<-{ANY COLON ANY{->T6};};\n";
     script += "T1{->T7}<-{CW COLON CW{->T7};};\n";
     script +=
     "(T1 PERIOD{ -> T8} T1){CONTAINS(COLON)}<-{CW COLON CW{->T9}; COLON ANY{-> T10};};\n";
     script += "(T1 PERIOD{ -> T11} T1){CONTAINS(COLON)}<-{CW COLON CW{->T11};};\n";
     script +=
     "(T1 PERIOD T1{-> T12}){CONTAINS(COLON)}<-{W COLON (W W)<-{ANY{REGEXP(\"match.*\")};};};\n";
    // both types
    script += "T1<-{W COLON W W;}->{COLON{->T13};};\n";
    script += "(T1 PERIOD T1){CONTAINS(COLON)}<-{W COLON (W W)<-{ANY{REGEXP(\"match.*\")};}->{W{->T14};};};\n";
    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document);
      Ruta.apply(cas, script);
    } catch (Exception e) {
    }

    RutaTestUtils.assertAnnotationsEquals(cas, 2, 2, "The", "Ruta");
    RutaTestUtils
            .assertAnnotationsEquals(
                    cas,
                    3,
                    3,//
                    "then the actions of the rule are performed  on the matched annotations. A rule is composed of a sequence of rule elements and a rule element essentially consists of four parts: A matching condition",
                    "an optional quantifier", "an optional quantifier");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 2, "A", "A");
    RutaTestUtils.assertAnnotationsEquals(cas, 5, 1, "A");
    RutaTestUtils
            .assertAnnotationsEquals(
                    cas,
                    6,
                    2, //
                    "A rule is composed of a sequence of rule elements and a rule element essentially consists of four parts: A matching condition, an optional quantifier, a list of conditions and a list of actions",
                    "A");
    RutaTestUtils.assertAnnotationsEquals(cas, 7, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 8, 2, ".", ".");
    RutaTestUtils.assertAnnotationsEquals(cas, 9, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 10, 2, "A", "A");
    RutaTestUtils.assertAnnotationsEquals(cas, 11, 0);
    RutaTestUtils
            .assertAnnotationsEquals(
                    cas,
                    12,
                    2, //
                    "A rule is composed of a sequence of rule elements and a rule element essentially consists of four parts: A matching condition, an optional quantifier, a list of conditions and a list of actions",
                    "The matching condition is typically a type of an annotation by which the rule element matches on the covered text of one of those annotations");
    RutaTestUtils.assertAnnotationsEquals(cas, 13, 1, ":");
    RutaTestUtils.assertAnnotationsEquals(cas, 14, 4, "A", "A", "matching", "matching");

    cas.release();
  }
}
