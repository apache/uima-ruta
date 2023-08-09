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

package org.apache.uima.ruta.block;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.junit.jupiter.api.Test;

public class OnlyOnceBlockTest {

  @Test
  public void test() {
    String document = "some text with numbers: 1 2 3 4";
    String script = "ONLYONCE Document{}{\n";
    script += "SW{-> T1};\n";
    script += "NUM{-> T2};\n";
    script += "SW+{-> T3};\n";
    script += "NUM+{-> T4};\n";
    script += "(SW | CW | NUM)+{-> T5};\n";
    script += "(ALL & ANY)+{-> T6};\n";
    script += "}";

    Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put(RutaEngine.PARAM_ADDITIONAL_EXTENSIONS,
            new String[] { OnlyOnceBlockExtension.class.getName() });
    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document);
      Ruta.apply(cas, script, parameters);
    } catch (Exception e) {
      e.printStackTrace();
    }

    Type t = null;
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;

    t = RutaTestUtils.getTestType(cas, 1);
    ai = cas.getAnnotationIndex(t);
    assertThat(ai.size()).isEqualTo(1);
    iterator = ai.iterator();
    assertThat(iterator.next().getCoveredText()).isEqualTo("some");

    t = RutaTestUtils.getTestType(cas, 2);
    ai = cas.getAnnotationIndex(t);
    assertThat(ai.size()).isEqualTo(1);
    iterator = ai.iterator();
    assertThat(iterator.next().getCoveredText()).isEqualTo("1");

    t = RutaTestUtils.getTestType(cas, 3);
    ai = cas.getAnnotationIndex(t);
    assertThat(ai.size()).isEqualTo(1);
    iterator = ai.iterator();
    assertThat(iterator.next().getCoveredText()).isEqualTo("some text with numbers");

    t = RutaTestUtils.getTestType(cas, 4);
    ai = cas.getAnnotationIndex(t);
    assertThat(ai.size()).isEqualTo(1);
    iterator = ai.iterator();
    assertThat(iterator.next().getCoveredText()).isEqualTo("1 2 3 4");

    t = RutaTestUtils.getTestType(cas, 5);
    ai = cas.getAnnotationIndex(t);
    assertThat(ai.size()).isEqualTo(1);
    iterator = ai.iterator();
    assertThat(iterator.next().getCoveredText()).isEqualTo("some text with numbers");

    t = RutaTestUtils.getTestType(cas, 6);
    ai = cas.getAnnotationIndex(t);
    assertThat(ai.size()).isEqualTo(1);
    iterator = ai.iterator();
    assertThat(iterator.next().getCoveredText()).isEqualTo("some text with numbers: 1 2 3 4");

    if (cas != null) {
      cas.release();
    }

  }
}
