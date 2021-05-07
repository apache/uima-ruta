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

package org.apache.uima.ruta.action;

import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.junit.Test;

public class AddTest {

  @Test
  public void testAnnotations() throws Exception {
    String document = "Some text.";
    String script = "";
    script += "ANNOTATION a;";
    script += "ANNOTATIONLIST as;";
    script += "b:CW{-> ADD(as,b)} c:SW{-> ASSIGN(a,c)};";
    script += "Document{SIZE(as,1,1)->T1};";
    script += "SW{-> ADD(as,a)};";
    script += "Document{SIZE(as,2,2)->T2};";
    script += "as{->T3};";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Some text.");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "Some text.");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 2, "Some", "text");
  }

  @Test
  public void testFloat() throws Exception {
    String document = "Some text.";
    String script = "";
    script += "FLOATLIST fs;";
    script += "CW{-> ADD(fs,3)};";
    script += "Document{SIZE(fs,1,1)->T1};";
    script += "SW{-> ADD(fs,4)};";
    script += "Document{SIZE(fs,2,2)->T2};";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Some text.");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "Some text.");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "Some");
  }

  @Test
  public void testVariable() throws Exception {
    String document = "This is a test.";

    String script = "";
    script += "STRING s = \"a\";";
    script += "STRINGLIST sl = {s};";
    script += "Document{CONTAINS(sl, \"a\") -> T1};";
    script += "Document{ -> ADD(sl, s)};";
    script += "Document{ -> s = \"b\"};";
    script += "Document{CONTAINS(sl, \"a\") -> T2};";
    script += "Document{CONTAINS(sl, \"b\") -> T3};";

    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "This is a test.");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "This is a test.");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 0);
  }
}
