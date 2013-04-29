/*
 * Licensed to the Apache Software FoSWation (ASF) SWer one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you SWer the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed SWer the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * SWer the License.
 */

package org.apache.uima.textmarker;

import static org.junit.Assert.assertEquals;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.junit.Test;

public class LiteralStringMatchTest {

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
    assertEquals("CW", iterator.next().getCoveredText());
    assertEquals("CW", iterator.next().getCoveredText());
    assertEquals("CW", iterator.next().getCoveredText());
    assertEquals("CW", iterator.next().getCoveredText());

    t = TextMarkerTestUtils.getTestType(cas, 2);
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertEquals(1, ai.size());
    assertEquals("SW", iterator.next().getCoveredText());

    t = TextMarkerTestUtils.getTestType(cas, 3);
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertEquals(0, ai.size());

   
    t = TextMarkerTestUtils.getTestType(cas, 6);
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertEquals(2, ai.size());
    assertEquals("CW COMMA CW COMMA", iterator.next().getCoveredText());
    assertEquals("CW COMMA", iterator.next().getCoveredText());

    t = TextMarkerTestUtils.getTestType(cas, 7);
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertEquals(5, ai.size());
    assertEquals("CW", iterator.next().getCoveredText());
    assertEquals("CW", iterator.next().getCoveredText());
    assertEquals("CW", iterator.next().getCoveredText());
    assertEquals("SW", iterator.next().getCoveredText());
    assertEquals("CW", iterator.next().getCoveredText());

    t = TextMarkerTestUtils.getTestType(cas, 8);
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertEquals(5, ai.size());
    assertEquals("CW", iterator.next().getCoveredText());
    assertEquals("CW", iterator.next().getCoveredText());
    assertEquals("CW SW CW", iterator.next().getCoveredText());
    assertEquals("SW CW", iterator.next().getCoveredText());
    assertEquals("CW", iterator.next().getCoveredText());

    t = TextMarkerTestUtils.getTestType(cas, 9);
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertEquals(2, ai.size());
    assertEquals("CW COMMA CW COMMA CW", iterator.next().getCoveredText());
    assertEquals("CW COMMA CW", iterator.next().getCoveredText());

    t = TextMarkerTestUtils.getTestType(cas, 10);
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertEquals(3, ai.size());
    assertEquals("CW COMMA CW COMMA CW SW", iterator.next().getCoveredText());
    assertEquals("CW COMMA CW SW", iterator.next().getCoveredText());
    assertEquals("CW SW", iterator.next().getCoveredText());

    t = TextMarkerTestUtils.getTestType(cas, 11);
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertEquals(3, ai.size());
    assertEquals("CW COMMA CW COMMA CW SW CW PERIOD", iterator.next().getCoveredText());
    assertEquals("CW COMMA CW SW CW PERIOD", iterator.next().getCoveredText());
    assertEquals("CW SW CW PERIOD", iterator.next().getCoveredText());

    t = TextMarkerTestUtils.getTestType(cas, 13);
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertEquals(6, ai.size());
    assertEquals("CW COMMA", iterator.next().getCoveredText());
    assertEquals("CW COMMA", iterator.next().getCoveredText());
    assertEquals("CW COMMA", iterator.next().getCoveredText());
    assertEquals("CW SW", iterator.next().getCoveredText());
    assertEquals("CW SW", iterator.next().getCoveredText());
    assertEquals("CW SW", iterator.next().getCoveredText());

    t = TextMarkerTestUtils.getTestType(cas, 14);
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertEquals(3, ai.size());
    assertEquals("CW COMMA CW COMMA CW SW", iterator.next().getCoveredText());
    assertEquals("CW COMMA CW SW", iterator.next().getCoveredText());
    assertEquals("CW SW", iterator.next().getCoveredText());


    if (cas != null) {
      cas.release();
    }

  }
}
