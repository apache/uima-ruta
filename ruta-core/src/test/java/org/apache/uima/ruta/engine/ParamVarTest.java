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

package org.apache.uima.ruta.engine;

import java.util.HashMap;
import java.util.Map;

import org.apache.uima.cas.CAS;
import org.junit.Test;

public class ParamVarTest {

  @Test
  public void test() {
    String document = "Some text.";
    String script = "";
    script += "BLOCK(First) Document{}{\n";
    script += "STRING s = \"a\";\n";
    script += "W.ct==s{-> T1};\n";
    script += "}\n";
    script += "BLOCK(Second) Document{}{\n";
    script += "STRING s = \"b\";\n";
    script += "W.ct==s{-> T2};\n";
    script += "}\n";
    script += "STRING s = \"x\";\n";
    script += "INT i = 100;\n";
    script += "BOOLEAN b = false;\n";
    script += "W.ct==s{-> T3};\n";
    script += "W.begin==i{-> T4};\n";
    script += "Document{b -> T5};\n";
    CAS cas = null;
    Map<String, Object> params = new HashMap<String, Object>();
    params.put(RutaEngine.PARAM_VAR_NAMES, new String[] {"First.s", "Second.s", "s", "i", "b"});
    params.put(RutaEngine.PARAM_VAR_VALUES, new String[] {"Some", "text", "Some", "0", "true"});
    
    try {
      cas = RutaTestUtils.getCAS(document);
      Ruta.apply(cas, script, params);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Some");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "text");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "Some");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 1, "Some");
    RutaTestUtils.assertAnnotationsEquals(cas, 5, 1, "Some text.");

    cas.release();
  }
}
