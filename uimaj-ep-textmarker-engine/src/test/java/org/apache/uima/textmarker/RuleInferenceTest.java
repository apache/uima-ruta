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

package org.apache.uima.textmarker;

import static org.junit.Assert.assertEquals;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.junit.Test;

public class RuleInferenceTest {

  @Test
  public void test() {
    String name = this.getClass().getSimpleName();
    String namespace = this.getClass().getPackage().getName().replaceAll("\\.", "/");
    CAS cas = null;
    try {
      cas = TextMarkerTestUtils.process(namespace + "/" + name + ".tm", namespace + "/" + name
              + ".txt", 50);
    } catch (Exception e) {
      e.printStackTrace();
      assert (false);
    }
    Type t = null;
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;

    t = TextMarkerTestUtils.getTestType(cas, 1);
    ai = cas.getAnnotationIndex(t);
    assertEquals(4, ai.size());
    iterator = ai.iterator();
    assertEquals("Peter", iterator.next().getCoveredText());
    assertEquals("Jochen", iterator.next().getCoveredText());
    assertEquals("Flo", iterator.next().getCoveredText());
    assertEquals("Georg", iterator.next().getCoveredText());

    t = TextMarkerTestUtils.getTestType(cas, 2);
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertEquals(1, ai.size());
    assertEquals("und", iterator.next().getCoveredText());

    t = TextMarkerTestUtils.getTestType(cas, 3);
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertEquals(0, ai.size());

    t = TextMarkerTestUtils.getTestType(cas, 4);
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertEquals(5, ai.size());
    assertEquals("Peter", iterator.next().getCoveredText());
    assertEquals("Jochen", iterator.next().getCoveredText());
    assertEquals("Flo und Georg", iterator.next().getCoveredText());
    assertEquals("und Georg", iterator.next().getCoveredText());
    assertEquals("Georg", iterator.next().getCoveredText());

    t = TextMarkerTestUtils.getTestType(cas, 5);
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertEquals(2, ai.size());
    assertEquals(",", iterator.next().getCoveredText());
    assertEquals(",", iterator.next().getCoveredText());

    t = TextMarkerTestUtils.getTestType(cas, 6);
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertEquals(2, ai.size());
    assertEquals("Peter, Jochen,", iterator.next().getCoveredText());
    assertEquals("Jochen,", iterator.next().getCoveredText());

    t = TextMarkerTestUtils.getTestType(cas, 7);
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertEquals(5, ai.size());
    assertEquals("Peter", iterator.next().getCoveredText());
    assertEquals("Jochen", iterator.next().getCoveredText());
    assertEquals("Flo", iterator.next().getCoveredText());
    assertEquals("und", iterator.next().getCoveredText());
    assertEquals("Georg", iterator.next().getCoveredText());

    t = TextMarkerTestUtils.getTestType(cas, 8);
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertEquals(5, ai.size());
    assertEquals("Peter", iterator.next().getCoveredText());
    assertEquals("Jochen", iterator.next().getCoveredText());
    assertEquals("Flo und Georg", iterator.next().getCoveredText());
    assertEquals("und Georg", iterator.next().getCoveredText());
    assertEquals("Georg", iterator.next().getCoveredText());

    t = TextMarkerTestUtils.getTestType(cas, 9);
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertEquals(2, ai.size());
    assertEquals("Peter, Jochen, Flo", iterator.next().getCoveredText());
    assertEquals("Jochen, Flo", iterator.next().getCoveredText());

    t = TextMarkerTestUtils.getTestType(cas, 10);
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertEquals(3, ai.size());
    assertEquals("Peter, Jochen, Flo und", iterator.next().getCoveredText());
    assertEquals("Jochen, Flo und", iterator.next().getCoveredText());
    assertEquals("Flo und", iterator.next().getCoveredText());

    t = TextMarkerTestUtils.getTestType(cas, 11);
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertEquals(3, ai.size());
    assertEquals("Peter, Jochen, Flo und Georg.", iterator.next().getCoveredText());
    assertEquals("Jochen, Flo und Georg.", iterator.next().getCoveredText());
    assertEquals("Flo und Georg.", iterator.next().getCoveredText());

    t = TextMarkerTestUtils.getTestType(cas, 12);
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertEquals(6, ai.size());
    assertEquals(",", iterator.next().getCoveredText());
    assertEquals(",", iterator.next().getCoveredText());
    assertEquals(",", iterator.next().getCoveredText());
    assertEquals("und", iterator.next().getCoveredText());
    assertEquals("und", iterator.next().getCoveredText());
    assertEquals("und", iterator.next().getCoveredText());

    t = TextMarkerTestUtils.getTestType(cas, 13);
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertEquals(6, ai.size());
    assertEquals("Peter,", iterator.next().getCoveredText());
    assertEquals("Jochen,", iterator.next().getCoveredText());
    assertEquals("Jochen,", iterator.next().getCoveredText());
    assertEquals("Flo und", iterator.next().getCoveredText());
    assertEquals("Flo und", iterator.next().getCoveredText());
    assertEquals("Flo und", iterator.next().getCoveredText());

    t = TextMarkerTestUtils.getTestType(cas, 14);
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertEquals(3, ai.size());
    assertEquals("Peter, Jochen, Flo und", iterator.next().getCoveredText());
    assertEquals("Jochen, Flo und", iterator.next().getCoveredText());
    assertEquals("Flo und", iterator.next().getCoveredText());

    t = TextMarkerTestUtils.getTestType(cas, 15);
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertEquals(1, ai.size());
    assertEquals("Peter, Jochen", iterator.next().getCoveredText());

    t = TextMarkerTestUtils.getTestType(cas, 16);
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertEquals(1, ai.size());
    assertEquals("Georg.", iterator.next().getCoveredText());

    t = TextMarkerTestUtils.getTestType(cas, 17);
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertEquals(1, ai.size());
    assertEquals("Flo und Georg.", iterator.next().getCoveredText());

    t = TextMarkerTestUtils.getTestType(cas, 18);
    ai = cas.getAnnotationIndex(t);
    assertEquals(3, ai.size());
    iterator = ai.iterator();
    assertEquals("Peter,", iterator.next().getCoveredText());
    assertEquals("Jochen,", iterator.next().getCoveredText());
    assertEquals("Georg.", iterator.next().getCoveredText());

    if (cas != null) {
      cas.release();
    }

  }
}
