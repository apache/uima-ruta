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
package org.apache.uima.ruta.expression.number;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.engine.RutaTestUtils.TestFeature;
import org.junit.Test;

public class FloatVariableExpressionTest {

  @Test
  public void testAssignOnFloat() throws Exception {
    String script = "FLOAT a2,a1;";
    script += "(NUM{PARSE(a1)} \"%\") {-> CREATE (Percent, \"value\" = a1, \"class\" = \"Percent\", \"unit\" = \"%\")};";
    script += "(NUM{PARSE(a2)} \"‰\") {-> ASSIGN(a2, a2/10), CREATE (Percent, \"value\" = a2, \"class\" = \"Percent\", \"unit\" = \"‰\")};";
    script += "p:Percent{p.unit == \"%\", p.value==10 -> T1};";
    script += "p:Percent{p.unit == \"‰\", p.value==1 -> T2};";
    Map<String, String> complexTypes = new HashMap<>();
    complexTypes.put("Percent", CAS.TYPE_NAME_ANNOTATION);
    Map<String, List<TestFeature>> featureMap = new HashMap<>();
    List<TestFeature> list = new ArrayList<>();
    list.add(new TestFeature("class", "", CAS.TYPE_NAME_STRING));
    list.add(new TestFeature("value", "", CAS.TYPE_NAME_FLOAT));
    list.add(new TestFeature("unit", "", CAS.TYPE_NAME_STRING));
    featureMap.put("Percent", list);
    CAS cas = RutaTestUtils.getCAS("10 % , 10 ‰", complexTypes, featureMap);
    Ruta.apply(cas, script);
    
    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "10 %");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "10 ‰");
  }

}
