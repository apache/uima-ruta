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
public class WildCardInWindowTest {

  @Test
  public void testSingleBothDirections() throws Exception {
    String document = ". a 1 b . c 1 D . e 1 1 1 f . g 1 1 1 H .";
    String script = "";
    script += "PERIOD #{-> T1} PERIOD;";
    script += "T1 -> { SW #{-> T2} SW{-> T3};};";
    script += "T1 -> { SW #{-> T4} @SW{-> T5};};";
    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 2, 2, "1", "1 1 1");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 2, "b", "f");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 2, "1", "1 1 1");
    RutaTestUtils.assertAnnotationsEquals(cas, 5, 2, "b", "f");
  }

  @Test
  public void testMultiMidOut() throws Exception {
    String document = ". A x 1 1 : x 2 % 3 x ; . A x B 1 C 1 : x 4 % 5 x ; .";
    String script = "";
    script += "PERIOD ANY+{-PARTOF(PERIOD) -> T1} PERIOD;";
    script += "T1 -> {CW{-> T4} # @COLON;};";
    script += "T1 -> {CW{-> T5} # @COLON # NUM{-> T2} SPECIAL NUM{-> T3} # SEMICOLON;};";
    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 2, 2, "2", "4");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 2, "3", "5");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 2, "A", "C");
    RutaTestUtils.assertAnnotationsEquals(cas, 5, 2, "A", "C");

  }

  @Test
  public void testCombiMidOut() throws Exception {
    String document = ". some text. some A.B text C.D text. some text .";
    String script = "";

    script += "(CW PERIOD CW) {-> T1};";
    script += "PERIOD{-PARTOF(T1)} #{-> T2} PERIOD{-PARTOF(T1)};";
    script += "T2<-{CW PERIOD CW;}->{#{-> T3} @PERIOD #{-> T3};};";
    script += "T3<-{CW PERIOD CW;}->{#{-> T4} @PERIOD #{-> T4};};";
    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 3, 4, "some A.B text C", "some A", "B text C.D text",
            "D text");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 4, "some A", "B text C", "B text C", "D text");

  }

  @Test
  public void testSameRightToLeftInWindow() throws Exception {
    String document = ". a 1 b . c 1 D . e 1 1 1 f . g 1 1 1 H .";
    String script = "";
    script += "PERIOD #{-> T1} PERIOD;";
    script += "T1 ->{W{->T3} #{->T2} @W;};";
    script += "T1 ->{W #{-PARTOF(NUM)->T4} @W;};";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 2, 4, "1", "1", "1 1 1", "1 1 1");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 4, "a", "c", "e", "g");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 0);

  }

  @Test
  public void testCoveredRightToLeftNoWindow() throws Exception {
    String document = "CAP1 some text CAP2 more text";
    String script = "";
    script += "(CAP NUM){-> T1};";
    script += "SW (T1 W+){-> T2};";
    script += " T1 #{->T3} @T2;";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "some text");
  }

  @Test
  public void testRightToLeftCombination() throws Exception {
    String document = "bla . x x x A x x 1 : x x x 2 x x . bla";

    // Both NUM/T3 should be removed
    String script = "";
    script += "PERIOD ANY+{-PARTOF(PERIOD) -> T1} PERIOD;";
    script += "CW{->T2};";
    script += "NUM{->T3};";
    script += "T1->{";
    script += "T2 # n1:T3 ANY[0,5]{-PARTOF(T3)} COLON # n2:@T3{-> UNMARK(n2)};";
    script += "T2 # n:T3{ -> UNMARK(n)} COLON;";
    script += "};";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 3, 0);
  }

}
