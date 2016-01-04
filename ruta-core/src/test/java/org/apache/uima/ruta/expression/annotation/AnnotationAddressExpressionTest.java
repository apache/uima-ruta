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

package org.apache.uima.ruta.expression.annotation;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.AnnotationImpl;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.junit.Test;

public class AnnotationAddressExpressionTest {

  @Test
  public void test() {
    String document = "Some text.";

    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document);
    } catch (Exception e) {
      e.printStackTrace();
    }
    Type t1 = RutaTestUtils.getTestType(cas, 1);
    AnnotationFS a1 = cas.createAnnotation(t1, 5, 9);
    cas.addFsToIndexes(a1);
    int ref = 0;
    if (a1 instanceof AnnotationImpl) {
      AnnotationImpl aImpl = (AnnotationImpl) a1;
      ref = aImpl.getAddress();
    }

    String script = "";
    script += "$" + ref + "{REGEXP(\".*ex.*\")-> T2};";
    script += "W $" + ref + "{REGEXP(\".*ex.*\")-> T3};";
    try {
      Ruta.apply(cas, script);
    } catch (Exception e) {
      e.printStackTrace();
    }

    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "text");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "text");

    if (cas != null) {
      cas.release();
    }

  }

}
