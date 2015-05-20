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

import static org.junit.Assert.assertEquals;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.junit.Test;

public class ContextCountTest {

  @Test
  public void test() {

    CAS cas = RutaTestUtils.processTestScript(this.getClass());

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 3, "A single sentence", "And here is another one",
            "Testing the CONTEXTCOUNT condition of Ruta System");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 4, "A", "And", "Testing", "Ruta");

    Type t = RutaTestUtils.getTestType(cas, 3);
    AnnotationIndex<AnnotationFS> ai = cas.getAnnotationIndex(t);
    assertEquals(1, ai.size());
    FSIterator<AnnotationFS> iterator = ai.iterator();
    assertEquals("A single sentence." + "And here is another one."
            + "Testing the CONTEXTCOUNT condition of Ruta System.",
            iterator.next().getCoveredText().replaceAll("[\n\r]", ""));

    cas.release();
  }
}
