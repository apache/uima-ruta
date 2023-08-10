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
public class MarkInGreedyComposedTest {

  @Test
  public void testWildCardFollowedByComposedReversed() throws Exception {
    String document = "1 x f B e d B x c A b a A 1";
    String script = "";
    script += "( ( (SW{REGEXP(\"x\")} SW ) #) {-> T1} )+ @NUM;";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 2, "x f B e d B", "x c A b a A");
  }

  @Test
  public void testSimpleFollowedByComposedReversed() throws Exception {
    String document = "1 x f B x c A 1";
    String script = "";
    script += "( ( (SW{REGEXP(\"x\")} SW ) CW) {-> T1} )+ @NUM;";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 2, "x f B", "x c A");
  }

  @Test
  public void testWildCardFollowedByComposed() throws Exception {
    String document = "A c b A c x A c b A c x";
    String script = "";
    script += "( ( # (SW SW{REGEXP(\"x\")}) ) {-> T1} )+;";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 2, "A c b A c x", "A c b A c x");
  }

  @Test
  public void testWildCardFollowedByCondition() throws Exception {
    String document = "A A b A c X X b X c";
    String script = "";
    script += "((# SW{REGEXP(\"c\")}){-> T1})+; ";
    script += "((# SW{REGEXP(\"c\")}){-> MARKONCE(T2)})+;";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 2, "A A b A c", "X X b X c");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 2, "A A b A c", "X X b X c");
  }

  @Test
  public void testWithWildCard() throws Exception {
    String document = "1 . 2 .";
    String script = "";
    script += "((# PERIOD){-> T1})+; ";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 2, "1 .", "2 .");
  }

  @Test
  public void test() throws Exception {
    String document = "1 . 2 .";
    String script = "";
    script += "((NUM PERIOD){-> MARKONCE(T1)})+; ";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 2, "1 .", "2 .");
  }
}
