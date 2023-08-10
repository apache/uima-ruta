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

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.uima.cas.CAS;
import org.apache.uima.fit.util.CasUtil;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.type.CW;
import org.apache.uima.ruta.type.SW;
import org.apache.uima.ruta.type.TokenSeed;
import org.junit.jupiter.api.Test;
public class ReindexSkipTypesTest {

  @Test
  public void test() throws Exception {

    Map<String, Object> params = new LinkedHashMap<>();
    params.put(RutaEngine.PARAM_REINDEX_UPDATE_MODE, ReindexUpdateMode.COMPLETE);
    params.put(RutaEngine.PARAM_REINDEX_SKIP_TYPES, new String[] { TokenSeed.class.getName() });

    CAS cas = RutaTestUtils.getCAS("Test this");

    // index and add some annotations base on internal indexing
    Ruta.apply(cas, "ANY{PARTOF(CW)->T1};ANY{PARTOF(SW)->T2};", params);

    // remove CW and SW
    CasUtil.select(cas, cas.getTypeSystem().getType(CW.class.getName())).forEach(a -> {
      cas.removeFsFromIndexes(a);
    });
    CasUtil.select(cas, cas.getTypeSystem().getType(SW.class.getName())).forEach(a -> {
      cas.removeFsFromIndexes(a);
    });

    // redo. Cannot use ANY, because its gone with CW/SW
    Ruta.apply(cas, "T1{PARTOF(CW)->T3};T2{PARTOF(SW)->T4};", params);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Test");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "this");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "Test");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 1, "this");

  }

}
