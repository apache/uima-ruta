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

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.HashMap;
import java.util.Map;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.type.CW;
import org.apache.uima.ruta.type.SW;
import org.junit.jupiter.api.Test;

public class ParamVarTest {

  @Test
  public void test() throws Exception {
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
    script += "TYPE type1 = CW;\n";
    script += "type1{-> T6};\n";
    script += "TYPE type2 = SW;\n";
    script += "type2{-> T7};\n";
    CAS cas = null;
    Map<String, Object> params = new HashMap<String, Object>();
    params.put(RutaEngine.PARAM_VAR_NAMES,
            new String[] { "First.s", "Second.s", "s", "i", "b", "type1", "type2" });
    params.put(RutaEngine.PARAM_VAR_VALUES,
            new String[] { "Some", "text", "Some", "0", "true", SW.class.getName(), "CW" });

    cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script, params);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Some");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "text");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "Some");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 1, "Some");
    RutaTestUtils.assertAnnotationsEquals(cas, 5, 1, "Some text.");
    RutaTestUtils.assertAnnotationsEquals(cas, 6, 1, "text");
    RutaTestUtils.assertAnnotationsEquals(cas, 7, 1, "Some");

    cas.release();
  }

  @Test
  public void testMultiValue() throws Exception {
    String document = "Some text.";
    String script = "";
    script += "TYPELIST tl;";
    script += "ANY{PARTOF(tl) -> T1};";
    Map<String, Object> params = new HashMap<String, Object>();
    params.put(RutaEngine.PARAM_VAR_NAMES, new String[] { "tl" });
    params.put(RutaEngine.PARAM_VAR_VALUES, new String[] {
        SW.class.getName() + RutaEngine.SEPARATOR_VAR_VALUES + CW.class.getName() });

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script, params);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 2, "Some", "text");

    cas.release();
  }

  @Test
  public void testWithStrictImport() throws Exception {
    var document = "Some text.";
    var script = """
            TYPE t1;
            TYPE t2;
            TYPELIST tl;
            CW{ -> t1};
            SW{ -> t2};
            ANY{PARTOF(tl) -> T3};
            """;

    Map<String, Object> params = Map.of( //
            RutaEngine.PARAM_STRICT_IMPORTS, true, //
            RutaEngine.PARAM_VAR_NAMES, new String[] { "t1", "t2", "tl" }, //
            RutaEngine.PARAM_VAR_VALUES, new String[] { "org.apache.uima.T1", "org.apache.uima.T2",
                "org.apache.uima.T1,org.apache.uima.T2" });

    CAS cas = RutaTestUtils.getCAS(document);

    assertThatExceptionOfType(AnalysisEngineProcessException.class)
            .isThrownBy(() -> Ruta.apply(cas, script, params));
  }

  @Test
  public void testKnownVariableValue() throws Exception {
    String document = "Some text.";
    String script = "";
    script += "TYPE tvar;";
    script += "tvar{ -> T1};";
    Map<String, Object> params = new HashMap<String, Object>();
    params.put(RutaEngine.PARAM_VAR_NAMES, new String[] { "tvar" });
    params.put(RutaEngine.PARAM_VAR_VALUES, new String[] { "some.UnknownType" });

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script, params);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 0);
  }

  @Test
  public void testMissingVariableValue() throws Exception {
    String document = "Some text.";
    String script = "";
    script += "TYPE tvar;";
    script += "tvar{ -> T1};";
    Map<String, Object> params = new HashMap<String, Object>();

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script, params);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 0);
  }
}
