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

import static org.junit.Assert.assertEquals;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
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
    script += "Document{-> RETAINTYPE(BREAK, SPACE)};\n";
    script += "#{-> T6} BREAK #{-> T6};\n";
    script += "T6{-> TRIM(BREAK, SPACE)};\n";
    script += "CW{REGEXP(\".\")} PERIOD{->T7};\n";
    script += "Document{-> RETAINTYPE};\n";
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
    
    Type t = null;
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;

    t = RutaTestUtils.getTestType(cas, 1);
    ai = cas.getAnnotationIndex(t);
    assertEquals(2, ai.size());
    iterator = ai.iterator();
    assertEquals("Ogren, P.V., Wetzler, P.G., Bethard, S.:", iterator.next().getCoveredText());
    assertEquals("Stephen Soderland, Claire Cardie, and Raymond Mooney.", iterator.next()
            .getCoveredText());

    t = RutaTestUtils.getTestType(cas, 2);
    ai = cas.getAnnotationIndex(t);
    assertEquals(2, ai.size());
    iterator = ai.iterator();
    assertEquals("ClearTK: A UIMA Toolkit for Statistical Natural Language Processing.", iterator
            .next().getCoveredText());
    assertEquals("Learning Information Extraction Rules for Semi-Structured and Free Text.",
            iterator.next().getCoveredText());

    t = RutaTestUtils.getTestType(cas, 3);
    ai = cas.getAnnotationIndex(t);
    assertEquals(2, ai.size());
    iterator = ai.iterator();
    assertEquals("2008", iterator.next().getCoveredText());
    assertEquals("1999", iterator.next().getCoveredText());

    if (cas != null) {
      cas.release();
    }

  }
}
