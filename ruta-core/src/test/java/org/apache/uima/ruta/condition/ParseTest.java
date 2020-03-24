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

package org.apache.uima.ruta.condition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.engine.RutaTestUtils.TestFeature;
import org.junit.Test;

public class ParseTest {

  @Test
  public void test() {

    CAS cas = RutaTestUtils.processTestScript(this.getClass());

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 5, "42", "2", "1", "2", "3");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 2, "2,1", "2.3");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "true");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 5, "A Boolean b that is true", "b = false",
            "The Number 42", "The Double d = 2,1", "Another Double that is 2.3");

    cas.release();
  }

  @Test
  public void testDecimal() throws Exception {

    CAS cas = RutaTestUtils.getCAS("text 2.3 text 2,3 text");
    Ruta.apply(cas, "DOUBLE d; (NUM PM NUM){PARSE(d),d==2.3 -> T1};");
    Ruta.apply(cas, "DOUBLE d; (NUM PM NUM){PARSE(d, \"en\"),d==2.3 -> T2};");
    Ruta.apply(cas, "DOUBLE d; (NUM PM NUM){PARSE(d, \"de\"),d==2.3 -> T3};");
    Ruta.apply(cas, "DOUBLE d; (NUM PM NUM){PARSE(d, \"en\"),d!=2.3 -> T4};");
    Ruta.apply(cas, "DOUBLE d; (NUM PM NUM){PARSE(d, \"de\"),d!=2.3 -> T5};");

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "2.3");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "2.3");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "2,3");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 1, "2,3");
    RutaTestUtils.assertAnnotationsEquals(cas, 5, 1, "2.3");
  }

  @Test
  public void testParseStringExpression() throws Exception {

    String script = "";
    script += "DOUBLE d;\n";

    script += "Document{PARSE(\"1\", d)};\n";
    script += "Document{d == 1 -> T1};\n";

    script += "Document{PARSE(\"1\" + \"1\", d)};\n";
    script += "Document{d == 11 -> T2};\n";

    script += "Document{-> s:Struct, s.s=\"3\"};\n";
    script += "s:Struct{PARSE(s.s, d)};\n";
    script += "Document{d == 3 -> T3};\n";

    script += "n:NUM{PARSE(n.ct, d)};\n";
    script += "Document{d == 5 -> T4};\n";

    Map<String, String> complexTypes = new HashMap<String, String>();
    Map<String, List<TestFeature>> features = new TreeMap<String, List<TestFeature>>();
    String typeName = "Struct";
    complexTypes.put(typeName, "uima.tcas.Annotation");
    List<TestFeature> list = new ArrayList<RutaTestUtils.TestFeature>();
    features.put(typeName, list);
    list.add(new TestFeature("s", "", CAS.TYPE_NAME_STRING));
    CAS cas = RutaTestUtils.getCAS("test 5", complexTypes, features);

    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "test 5");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "test 5");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "test 5");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 1, "test 5");

  }

}
