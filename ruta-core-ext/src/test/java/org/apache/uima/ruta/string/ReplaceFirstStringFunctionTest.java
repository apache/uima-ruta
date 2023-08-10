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

package org.apache.uima.ruta.string;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.junit.jupiter.api.Test;

public class ReplaceFirstStringFunctionTest {
  @Test
  public void test() throws Exception {
    String document = "Berserker Barserker Barsarkar Barserkar";
    String script = "STRING s;\n";
    script += "STRINGLIST sl;\n";
    script += "CW{-> MATCHEDTEXT(s), ADD(sl, replaceFirst(s,\"er\",\"ar\"))};\n";
    script += "CW{INLIST(sl) -> T1};";

    Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put(RutaEngine.PARAM_ADDITIONAL_EXTENSIONS,
            new String[] { StringOperationsExtension.class.getName() });
    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script, parameters);

    Type t = RutaTestUtils.getTestType(cas, 1);
    assertThat(cas.<AnnotationFS> getAnnotationIndex(t)) //
            .extracting(AnnotationFS::getCoveredText) //
            .containsExactly("Barserker", "Barsarkar");

    if (cas != null) {
      cas.release();
    }
  }
}
