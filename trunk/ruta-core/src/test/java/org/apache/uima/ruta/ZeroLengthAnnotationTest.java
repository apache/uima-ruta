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

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.junit.Test;

public class ZeroLengthAnnotationTest {

  
  @Test
  public void testMatchType() {
    String document = "Some text.";
    String script = "";
    script += "W W{-> T1};";
    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document);
      Type type = cas.getTypeSystem().getType("org.apache.uima.ruta.type.W");
      // call for seeding
      Ruta.apply(cas, "");
      addZeroLengthAnnotations(cas, type);
      Ruta.apply(cas, script);
    } catch (Exception e) {
      e.printStackTrace();
    }

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "text");

    cas.release();
  }

  @Test
  public void testOtherType() {
    String document = "Some text.";
    String script = "";
    script += "W W{-> T1};";
    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document);
      Type type = cas.getTypeSystem().getType("org.apache.uima.ruta.type.TruePositive");
      addZeroLengthAnnotations(cas, type);
      
      Ruta.apply(cas, script);
    } catch (Exception e) {
      e.printStackTrace();
    }

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "text");

    cas.release();
  }
  
  private void addZeroLengthAnnotations(CAS cas, Type type) {
    AnnotationFS a0 = cas.createAnnotation(type, 0, 0);
    AnnotationFS a5 = cas.createAnnotation(type, 5, 5);
    AnnotationFS a10 = cas.createAnnotation(type, 10, 10);
    cas.addFsToIndexes(a0);
    cas.addFsToIndexes(a0);
    cas.addFsToIndexes(a5);
    cas.addFsToIndexes(a10);
  }
  
}
