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

package org.apache.uima.ruta.block.fst;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.junit.jupiter.api.Test;

public class SimpleTest {

  @Test
  public void test() throws Exception {
    String document = "Informatik ist die Wissenschaft der systematischen Verarbeitung von Informationen,"
            + " insbesondere der automatischen Verarbeitung mit Hilfe von Digitalrechnern.";
    String script = "";
    script += "FST Document{}{\n";
    script += "CW CW{-> T1};\n";
    script += "CW SW{-> T2};\n";
    script += "CW PERIOD{-> T3};\n";
    script += "CW COMMA{-> T4};\n";
    script += "}\n";
    Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put(RutaEngine.PARAM_ADDITIONAL_EXTENSIONS,
            new String[] { FSTBlockExtension.class.getName() });
    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script, parameters);

    Type t = null;

    t = RutaTestUtils.getTestType(cas, 1);
    assertThat(cas.getAnnotationIndex(t)).isEmpty();

    t = RutaTestUtils.getTestType(cas, 2);
    assertThat(cas.getAnnotationIndex(t)).hasSize(5);

    if (cas != null) {
      cas.release();
    }
  }
}
