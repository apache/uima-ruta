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

import java.util.HashMap;
import java.util.Map;

import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.junit.jupiter.api.Test;
public class DocumentBlockTest {

  @Test
  public void test() {
    String document = "some text 1 some text 2 some text";
    String script = "";
    script += "BLOCK(B) NUM{REGEXP(\"1\")}{\n";
    script += "Document{-> T1};\n";
    script += "DOCUMENTBLOCK SW{REGEXP(\"text\")}{\n";
    script += "Document{-> T2};\n";
    script += "}";
    script += "}";

    Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put(RutaEngine.PARAM_ADDITIONAL_EXTENSIONS,
            new String[] { DocumentBlockExtension.class.getName() });
    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document);
      Ruta.apply(cas, script, parameters);
    } catch (Exception e) {
      e.printStackTrace();
    }

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "1");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 3, "text", "text", "text");

    if (cas != null) {
      cas.release();
    }

  }
}
