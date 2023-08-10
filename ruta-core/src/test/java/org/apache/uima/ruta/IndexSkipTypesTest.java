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
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.type.EvalAnnotation;
import org.apache.uima.ruta.type.FalseNegative;
import org.apache.uima.ruta.type.TruePositive;
import org.junit.jupiter.api.Test;
public class IndexSkipTypesTest {

  @Test
  public void test() throws Exception {

    Map<String, Object> params = new LinkedHashMap<>();
    params.put(RutaEngine.PARAM_REINDEX_UPDATE_MODE, ReindexUpdateMode.COMPLETE);
    params.put(RutaEngine.PARAM_INDEX_SKIP_TYPES, new String[] { EvalAnnotation.class.getName() });
    params.put(RutaEngine.PARAM_REINDEX_SKIP_TYPES,
            new String[] { EvalAnnotation.class.getName() });

    CAS cas = RutaTestUtils.getCAS("Test this");

    AnnotationFS fn = cas
            .createAnnotation(cas.getTypeSystem().getType(FalseNegative.class.getName()), 0, 4);
    cas.addFsToIndexes(fn);

    Ruta.apply(cas, "ANY{PARTOF(CW)->T1};", params);
    Ruta.apply(cas, "CW{->FalsePositive};", params);
    // index anyway if a rule creates the annotation
    Ruta.apply(cas, "CW{PARTOF(FalseNegative)->T2};", params);
    Ruta.apply(cas, "CW{PARTOF(FalsePositive)->T3};", params);

    AnnotationFS tp = cas
            .createAnnotation(cas.getTypeSystem().getType(TruePositive.class.getName()), 0, 4);
    cas.addFsToIndexes(tp);

    Ruta.apply(cas, "CW{PARTOF(TruePositive)-> T4};", params);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Test");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "Test");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 0);

  }

}
