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
import org.apache.uima.fit.util.CasUtil;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
public class ReindexUpdateModeTest {

  @Test
  public void testComplete() throws Exception {

    Map<String, Object> params = new LinkedHashMap<>();
    params.put(RutaEngine.PARAM_REINDEX_UPDATE_MODE, ReindexUpdateMode.COMPLETE);

    CAS cas = RutaTestUtils.getCAS("This is 1 TEST.");

    // initial indexing and create some annotations
    Ruta.apply(cas, "NUM{-> T1}; CAP{-> T2};", params);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "1");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "TEST");

    // add T1 on CW, remove T2 on CAP
    AnnotationFS at3 = cas.createAnnotation(RutaTestUtils.getTestType(cas, 3), 0, 4);
    cas.addFsToIndexes(at3);
    CasUtil.select(cas, RutaTestUtils.getTestType(cas, 2)).forEach(a -> {
      cas.removeFsFromIndexes(a);
    });

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "1");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "This");

    // apply rules on modifications, 1x partof, 2x startswith, 3x endswith
    Ruta.apply(cas, "ANY{PARTOF(T1)->T11};ANY{PARTOF(T2)->T12};ANY{PARTOF(T3)->T13};", params);
    Ruta.apply(cas, "ANY{STARTSWITH(T1)->T21};ANY{STARTSWITH(T2)->T22};ANY{STARTSWITH(T3)->T23};",
            params);
    Ruta.apply(cas, "ANY{ENDSWITH(T1)->T31};ANY{ENDSWITH(T2)->T32};ANY{ENDSWITH(T3)->T33};",
            params);

    // partof
    RutaTestUtils.assertAnnotationsEquals(cas, 11, 1, "1");
    RutaTestUtils.assertAnnotationsEquals(cas, 12, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 13, 1, "This");

    // startswith
    RutaTestUtils.assertAnnotationsEquals(cas, 21, 1, "1");
    RutaTestUtils.assertAnnotationsEquals(cas, 22, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 23, 1, "This");

    // endswith
    RutaTestUtils.assertAnnotationsEquals(cas, 31, 1, "1");
    RutaTestUtils.assertAnnotationsEquals(cas, 32, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 33, 1, "This");

    // new round with multiple overlapping annotations
    Ruta.apply(cas, "PERIOD{-> T4, T4}; SW{-> T5};", params);

    // remove one T4, add another T5
    AnnotationFS at4 = CasUtil.select(cas, RutaTestUtils.getTestType(cas, 4)).iterator().next();
    cas.removeFsFromIndexes(at4);
    AnnotationFS at5 = cas.createAnnotation(RutaTestUtils.getTestType(cas, 5), 5, 7);
    cas.addFsToIndexes(at5);

    RutaTestUtils.assertAnnotationsEquals(cas, 4, 1, ".");
    RutaTestUtils.assertAnnotationsEquals(cas, 5, 2, "is", "is");

    // apply rules on modifications, 1x partof, 2x startswith, 3x endswith
    Ruta.apply(cas, "ANY{PARTOF(T4)->T14};ANY{PARTOF(T5)->T15};", params);
    Ruta.apply(cas, "ANY{STARTSWITH(T4)->T24};ANY{STARTSWITH(T5)->T25};", params);
    Ruta.apply(cas, "ANY{ENDSWITH(T4)->T34};ANY{ENDSWITH(T5)->T35};", params);

    // partof
    RutaTestUtils.assertAnnotationsEquals(cas, 14, 1, ".");
    RutaTestUtils.assertAnnotationsEquals(cas, 15, 1, "is");

    // startswith
    RutaTestUtils.assertAnnotationsEquals(cas, 24, 1, ".");
    RutaTestUtils.assertAnnotationsEquals(cas, 25, 1, "is");

    // endswith
    RutaTestUtils.assertAnnotationsEquals(cas, 34, 1, ".");
    RutaTestUtils.assertAnnotationsEquals(cas, 35, 1, "is");

    // further modify in ruta, remove T4 completely, remove one T5
    Ruta.apply(cas, "t4:T4{-> UNMARK(t4)};", params);
    Ruta.apply(cas, "t5:T5{CONTAINS(T5,2,10) -> UNMARK(t5)};", params);
    // T4 -> Tx6, T5 -> Tx7
    Ruta.apply(cas, "ANY{PARTOF(T4)->T16};ANY{PARTOF(T5)->T17};");
    Ruta.apply(cas, "ANY{STARTSWITH(T4)->T26};ANY{STARTSWITH(T5)->T27};", params);
    Ruta.apply(cas, "ANY{ENDSWITH(T4)->T36};ANY{ENDSWITH(T5)->T37};", params);

    // partof
    RutaTestUtils.assertAnnotationsEquals(cas, 16, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 17, 1, "is");

    // startswith
    RutaTestUtils.assertAnnotationsEquals(cas, 26, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 27, 1, "is");

    // endswith
    RutaTestUtils.assertAnnotationsEquals(cas, 36, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 37, 1, "is");
  }

  @Test
  public void testAdditiveWithExternalUpdate() throws Exception {

    Map<String, Object> params = new LinkedHashMap<>();
    params.put(RutaEngine.PARAM_REINDEX_UPDATE_MODE, ReindexUpdateMode.ADDITIVE);

    CAS cas = RutaTestUtils.getCAS("This is 1 TEST.");

    // initial indexing and create some annotations
    Ruta.apply(cas, "NUM{-> T1}; CAP{-> T2};", params);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "1");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "TEST");

    // add T1 on CW, remove T2 on CAP
    AnnotationFS at3 = cas.createAnnotation(RutaTestUtils.getTestType(cas, 3), 0, 4);
    cas.addFsToIndexes(at3);
    // RutaBasicUtils.addAnnotation(at3);
    CasUtil.select(cas, RutaTestUtils.getTestType(cas, 2)).forEach(a -> {
      cas.removeFsFromIndexes(a);
      RutaBasicUtils.removeAnnotation(a);
    });

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "1");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "This");

    // apply rules on modifications, 1x partof, 2x startswith, 3x endswith
    Ruta.apply(cas, "ANY{PARTOF(T1)->T11};ANY{PARTOF(T2)->T12};ANY{PARTOF(T3)->T13};", params);
    Ruta.apply(cas, "ANY{STARTSWITH(T1)->T21};ANY{STARTSWITH(T2)->T22};ANY{STARTSWITH(T3)->T23};",
            params);
    Ruta.apply(cas, "ANY{ENDSWITH(T1)->T31};ANY{ENDSWITH(T2)->T32};ANY{ENDSWITH(T3)->T33};",
            params);

    // partof
    RutaTestUtils.assertAnnotationsEquals(cas, 11, 1, "1");
    RutaTestUtils.assertAnnotationsEquals(cas, 12, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 13, 1, "This");

    // startswith
    RutaTestUtils.assertAnnotationsEquals(cas, 21, 1, "1");
    RutaTestUtils.assertAnnotationsEquals(cas, 22, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 23, 1, "This");

    // endswith
    RutaTestUtils.assertAnnotationsEquals(cas, 31, 1, "1");
    RutaTestUtils.assertAnnotationsEquals(cas, 32, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 33, 1, "This");

    // new round with multiple overlapping annotations
    Ruta.apply(cas, "PERIOD{-> T4, T4}; SW{-> T5};", params);

    // remove one T4, add another T5
    AnnotationFS at4 = CasUtil.select(cas, RutaTestUtils.getTestType(cas, 4)).iterator().next();
    cas.removeFsFromIndexes(at4);
    RutaBasicUtils.removeAnnotation(at4);
    AnnotationFS at5 = cas.createAnnotation(RutaTestUtils.getTestType(cas, 5), 5, 7);
    cas.addFsToIndexes(at5);
    // RutaBasicUtils.addAnnotation(at5);

    RutaTestUtils.assertAnnotationsEquals(cas, 4, 1, ".");
    RutaTestUtils.assertAnnotationsEquals(cas, 5, 2, "is", "is");

    // apply rules on modifications, 1x partof, 2x startswith, 3x endswith
    Ruta.apply(cas, "ANY{PARTOF(T4)->T14};ANY{PARTOF(T5)->T15};", params);
    Ruta.apply(cas, "ANY{STARTSWITH(T4)->T24};ANY{STARTSWITH(T5)->T25};", params);
    Ruta.apply(cas, "ANY{ENDSWITH(T4)->T34};ANY{ENDSWITH(T5)->T35};", params);

    // partof
    RutaTestUtils.assertAnnotationsEquals(cas, 14, 1, ".");
    RutaTestUtils.assertAnnotationsEquals(cas, 15, 1, "is");

    // startswith
    RutaTestUtils.assertAnnotationsEquals(cas, 24, 1, ".");
    RutaTestUtils.assertAnnotationsEquals(cas, 25, 1, "is");

    // endswith
    RutaTestUtils.assertAnnotationsEquals(cas, 34, 1, ".");
    RutaTestUtils.assertAnnotationsEquals(cas, 35, 1, "is");

    // further modify in ruta, remove T4 completely, remove one T5
    Ruta.apply(cas, "t4:T4{-> UNMARK(t4)};", params);
    Ruta.apply(cas, "t5:T5{CONTAINS(T5,2,10) -> UNMARK(t5)};", params);
    // T4 -> Tx6, T5 -> Tx7
    Ruta.apply(cas, "ANY{PARTOF(T4)->T16};ANY{PARTOF(T5)->T17};");
    Ruta.apply(cas, "ANY{STARTSWITH(T4)->T26};ANY{STARTSWITH(T5)->T27};", params);
    Ruta.apply(cas, "ANY{ENDSWITH(T4)->T36};ANY{ENDSWITH(T5)->T37};", params);

    // partof
    RutaTestUtils.assertAnnotationsEquals(cas, 16, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 17, 1, "is");

    // startswith
    RutaTestUtils.assertAnnotationsEquals(cas, 26, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 27, 1, "is");

    // endswith
    RutaTestUtils.assertAnnotationsEquals(cas, 36, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 37, 1, "is");
  }

  @Test
  @Disabled
  public void testSafeAdditive() throws Exception {

    Map<String, Object> params = new LinkedHashMap<>();
    params.put(RutaEngine.PARAM_REINDEX_UPDATE_MODE, ReindexUpdateMode.SAFE_ADDITIVE);

    CAS cas = RutaTestUtils.getCAS("This is 1 TEST.");

    // initial indexing and create some annotations
    Ruta.apply(cas, "NUM{-> T1}; CAP{-> T2};", params);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "1");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "TEST");

    // add T1 on CW, remove T2 on CAP
    AnnotationFS at3 = cas.createAnnotation(RutaTestUtils.getTestType(cas, 3), 0, 4);
    cas.addFsToIndexes(at3);
    CasUtil.select(cas, RutaTestUtils.getTestType(cas, 2)).forEach(a -> {
      cas.removeFsFromIndexes(a);
    });

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "1");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "This");

    // apply rules on modifications, 1x partof, 2x startswith, 3x endswith
    Ruta.apply(cas, "ANY{PARTOF(T1)->T11};ANY{PARTOF(T2)->T12};ANY{PARTOF(T3)->T13};", params);
    Ruta.apply(cas, "ANY{STARTSWITH(T1)->T21};ANY{STARTSWITH(T2)->T22};ANY{STARTSWITH(T3)->T23};",
            params);
    Ruta.apply(cas, "ANY{ENDSWITH(T1)->T31};ANY{ENDSWITH(T2)->T32};ANY{ENDSWITH(T3)->T33};",
            params);

    // partof
    RutaTestUtils.assertAnnotationsEquals(cas, 11, 1, "1");
    RutaTestUtils.assertAnnotationsEquals(cas, 12, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 13, 1, "This");

    // startswith
    RutaTestUtils.assertAnnotationsEquals(cas, 21, 1, "1");
    RutaTestUtils.assertAnnotationsEquals(cas, 22, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 23, 1, "This");

    // endswith
    RutaTestUtils.assertAnnotationsEquals(cas, 31, 1, "1");
    RutaTestUtils.assertAnnotationsEquals(cas, 32, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 33, 1, "This");

    // new round with multiple overlapping annotations
    Ruta.apply(cas, "PERIOD{-> T4, T4}; SW{-> T5};", params);

    // remove one T4, add another T5
    AnnotationFS at4 = CasUtil.select(cas, RutaTestUtils.getTestType(cas, 4)).iterator().next();
    cas.removeFsFromIndexes(at4);
    AnnotationFS at5 = cas.createAnnotation(RutaTestUtils.getTestType(cas, 5), 5, 7);
    cas.addFsToIndexes(at5);

    RutaTestUtils.assertAnnotationsEquals(cas, 4, 1, ".");
    RutaTestUtils.assertAnnotationsEquals(cas, 5, 2, "is", "is");

    // apply rules on modifications, 1x partof, 2x startswith, 3x endswith
    Ruta.apply(cas, "ANY{PARTOF(T4)->T14};ANY{PARTOF(T5)->T15};", params);
    Ruta.apply(cas, "ANY{STARTSWITH(T4)->T24};ANY{STARTSWITH(T5)->T25};", params);
    Ruta.apply(cas, "ANY{ENDSWITH(T4)->T34};ANY{ENDSWITH(T5)->T35};", params);

    // partof
    RutaTestUtils.assertAnnotationsEquals(cas, 14, 1, ".");
    RutaTestUtils.assertAnnotationsEquals(cas, 15, 1, "is");

    // startswith
    RutaTestUtils.assertAnnotationsEquals(cas, 24, 1, ".");
    RutaTestUtils.assertAnnotationsEquals(cas, 25, 1, "is");

    // endswith
    RutaTestUtils.assertAnnotationsEquals(cas, 34, 1, ".");
    RutaTestUtils.assertAnnotationsEquals(cas, 35, 1, "is");

    // further modify in ruta, remove T4 completely, remove one T5
    Ruta.apply(cas, "t4:T4{-> UNMARK(t4)};", params);
    Ruta.apply(cas, "t5:T5{CONTAINS(T5,2,10) -> UNMARK(t5)};", params);
    // T4 -> Tx6, T5 -> Tx7
    Ruta.apply(cas, "ANY{PARTOF(T4)->T16};ANY{PARTOF(T5)->T17};");
    Ruta.apply(cas, "ANY{STARTSWITH(T4)->T26};ANY{STARTSWITH(T5)->T27};", params);
    Ruta.apply(cas, "ANY{ENDSWITH(T4)->T36};ANY{ENDSWITH(T5)->T37};", params);

    // partof
    RutaTestUtils.assertAnnotationsEquals(cas, 16, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 17, 1, "is");

    // startswith
    RutaTestUtils.assertAnnotationsEquals(cas, 26, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 27, 1, "is");

    // endswith
    RutaTestUtils.assertAnnotationsEquals(cas, 36, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 37, 1, "is");
  }

  @Test
  public void testNoneWithExternalUpdate() throws Exception {

    Map<String, Object> params = new LinkedHashMap<>();
    params.put(RutaEngine.PARAM_REINDEX_UPDATE_MODE, ReindexUpdateMode.NONE);

    CAS cas = RutaTestUtils.getCAS("This is 1 TEST.");

    // initial indexing and create some annotations
    Ruta.apply(cas, "NUM{-> T1}; CAP{-> T2};", params);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "1");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "TEST");

    // add T1 on CW, remove T2 on CAP
    AnnotationFS at3 = cas.createAnnotation(RutaTestUtils.getTestType(cas, 3), 0, 4);
    cas.addFsToIndexes(at3);
    RutaBasicUtils.addAnnotation(at3);
    CasUtil.select(cas, RutaTestUtils.getTestType(cas, 2)).forEach(a -> {
      cas.removeFsFromIndexes(a);
      RutaBasicUtils.removeAnnotation(a);
    });

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "1");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "This");

    // apply rules on modifications, 1x partof, 2x startswith, 3x endswith
    Ruta.apply(cas, "ANY{PARTOF(T1)->T11};ANY{PARTOF(T2)->T12};ANY{PARTOF(T3)->T13};", params);
    Ruta.apply(cas, "ANY{STARTSWITH(T1)->T21};ANY{STARTSWITH(T2)->T22};ANY{STARTSWITH(T3)->T23};",
            params);
    Ruta.apply(cas, "ANY{ENDSWITH(T1)->T31};ANY{ENDSWITH(T2)->T32};ANY{ENDSWITH(T3)->T33};",
            params);

    // partof
    RutaTestUtils.assertAnnotationsEquals(cas, 11, 1, "1");
    RutaTestUtils.assertAnnotationsEquals(cas, 12, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 13, 1, "This");

    // startswith
    RutaTestUtils.assertAnnotationsEquals(cas, 21, 1, "1");
    RutaTestUtils.assertAnnotationsEquals(cas, 22, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 23, 1, "This");

    // endswith
    RutaTestUtils.assertAnnotationsEquals(cas, 31, 1, "1");
    RutaTestUtils.assertAnnotationsEquals(cas, 32, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 33, 1, "This");

    // new round with multiple overlapping annotations
    Ruta.apply(cas, "PERIOD{-> T4, T4}; SW{-> T5};", params);

    // remove one T4, add another T5
    AnnotationFS at4 = CasUtil.select(cas, RutaTestUtils.getTestType(cas, 4)).iterator().next();
    cas.removeFsFromIndexes(at4);
    RutaBasicUtils.removeAnnotation(at4);
    AnnotationFS at5 = cas.createAnnotation(RutaTestUtils.getTestType(cas, 5), 5, 7);
    cas.addFsToIndexes(at5);
    RutaBasicUtils.addAnnotation(at5);

    RutaTestUtils.assertAnnotationsEquals(cas, 4, 1, ".");
    RutaTestUtils.assertAnnotationsEquals(cas, 5, 2, "is", "is");

    // apply rules on modifications, 1x partof, 2x startswith, 3x endswith
    Ruta.apply(cas, "ANY{PARTOF(T4)->T14};ANY{PARTOF(T5)->T15};", params);
    Ruta.apply(cas, "ANY{STARTSWITH(T4)->T24};ANY{STARTSWITH(T5)->T25};", params);
    Ruta.apply(cas, "ANY{ENDSWITH(T4)->T34};ANY{ENDSWITH(T5)->T35};", params);

    // partof
    RutaTestUtils.assertAnnotationsEquals(cas, 14, 1, ".");
    RutaTestUtils.assertAnnotationsEquals(cas, 15, 1, "is");

    // startswith
    RutaTestUtils.assertAnnotationsEquals(cas, 24, 1, ".");
    RutaTestUtils.assertAnnotationsEquals(cas, 25, 1, "is");

    // endswith
    RutaTestUtils.assertAnnotationsEquals(cas, 34, 1, ".");
    RutaTestUtils.assertAnnotationsEquals(cas, 35, 1, "is");

    // further modify in ruta, remove T4 completely, remove one T5
    Ruta.apply(cas, "t4:T4{-> UNMARK(t4)};", params);
    Ruta.apply(cas, "t5:T5{CONTAINS(T5,2,10) -> UNMARK(t5)};", params);
    // T4 -> Tx6, T5 -> Tx7
    Ruta.apply(cas, "ANY{PARTOF(T4)->T16};ANY{PARTOF(T5)->T17};");
    Ruta.apply(cas, "ANY{STARTSWITH(T4)->T26};ANY{STARTSWITH(T5)->T27};", params);
    Ruta.apply(cas, "ANY{ENDSWITH(T4)->T36};ANY{ENDSWITH(T5)->T37};", params);

    // partof
    RutaTestUtils.assertAnnotationsEquals(cas, 16, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 17, 1, "is");

    // startswith
    RutaTestUtils.assertAnnotationsEquals(cas, 26, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 27, 1, "is");

    // endswith
    RutaTestUtils.assertAnnotationsEquals(cas, 36, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 37, 1, "is");
  }
}
