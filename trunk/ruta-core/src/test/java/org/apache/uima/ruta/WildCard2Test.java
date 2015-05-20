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

public class WildCard2Test {

  @Test
  public void test() {
    String document = "Ogren, P.V., Wetzler, P.G., Bethard, S.: ClearTK: A UIMA Toolkit for Statistical Natural Language Processing. In: UIMA for NLP workshop at LREC 08. (2008)";
    document +="\n";
    document += "Stephen Soderland, Claire Cardie, and Raymond Mooney. Learning Information Extraction Rules for Semi-Structured and Free Text. In Machine Learning, volume 34, pages 233–272, 1999.";
    String script = "";
    script += "RETAINTYPE(BREAK, SPACE);\n";
    script += "#{-> T6} BREAK #{-> T6};\n";
    script += "T6{-> TRIM(BREAK, SPACE)};\n";
    script += "CW{REGEXP(\".\")} PERIOD{->T7};\n";
    script += "RETAINTYPE;\n";
    script += "BLOCK(forEach) T6 {}{\n";
    script += "(# COLON){-> T1} (# PERIOD){-> T2} # \"(\" NUM{REGEXP(\"....\")-> T3} \")\";\n";
    script += "(#{-CONTAINS(COLON)} PERIOD{-PARTOF(T7)}){-> T1} (# PERIOD){-> T2} # NUM{REGEXP(\"....\")-> T3};\n";
    script += "}\n";

    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document);
      Ruta.apply(cas, script);
    } catch (Exception e) {
      e.printStackTrace();
    }

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 2, "Ogren, P.V., Wetzler, P.G., Bethard, S.:",
            "Stephen Soderland, Claire Cardie, and Raymond Mooney.");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 2,
            "ClearTK: A UIMA Toolkit for Statistical Natural Language Processing.",
            "Learning Information Extraction Rules for Semi-Structured and Free Text.");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 2, "2008", "1999");

    cas.release();
  }
}
