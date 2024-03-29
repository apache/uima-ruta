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

import java.util.HashMap;
import java.util.Map;

import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.junit.jupiter.api.Test;
public class MarkFastReloadTest {

  @Test
  public void test() {
    String name = this.getClass().getSimpleName();
    String namespace = this.getClass().getPackage().getName().replaceAll("\\.", "/");

    CAS cas = null;
    try {
      String ruleFileName = namespace + "/" + name + RutaEngine.SCRIPT_FILE_EXTENSION;
      String textFileName = namespace + "/" + name + ".txt";
      Map<String, Object> parameters = new HashMap<>();
      parameters.put(RutaEngine.PARAM_ADDITIONAL_EXTENSIONS,
              new String[] { MarkReloadExtension.class.getName() });
      cas = RutaTestUtils.process(ruleFileName, textFileName, parameters, 50, null, null,
              namespace + "/", null);
    } catch (Exception e) {
      e.printStackTrace();
      assert (false);
    }

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 3, "1 0 0", "100", "2 0 0");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 3, "1 0 0", "100", "2 0 0");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 2, "1 0 0", "2 0 0");

    cas.release();
  }

}
