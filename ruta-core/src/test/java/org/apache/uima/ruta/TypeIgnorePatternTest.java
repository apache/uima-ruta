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

import java.util.HashMap;
import java.util.Map;

import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.junit.jupiter.api.Test;
public class TypeIgnorePatternTest {

  @Test
  public void test() throws Exception {
    Map<String, String> complexTypes = new HashMap<>();
    complexTypes.put("uima.ruta.Type1", CAS.TYPE_NAME_ANNOTATION);
    complexTypes.put("bad.package.Type1", CAS.TYPE_NAME_ANNOTATION);

    CAS cas = RutaTestUtils.getCAS("text", complexTypes, null);
    Map<String, Object> params = new HashMap<String, Object>();
    params.put(RutaEngine.PARAM_TYPE_IGNORE_PATTERN, ".*bad.*");
    Ruta.apply(cas, "W{->Type1};Type1{->T1};", params);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "text");
  }

}