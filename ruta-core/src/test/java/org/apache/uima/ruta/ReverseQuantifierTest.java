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

import static org.junit.Assert.assertEquals;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.junit.Test;

public class ReverseQuantifierTest {
  @Test
  public void test() {
    String document = "A B C. A C. B C. C";
    String script = "";
    script += "W??{REGEXP(\"A\")} W??{REGEXP(\"B\")} @W{REGEXP(\"C\") -> MARK(T2,1,3)};\n";
    script += "W?{REGEXP(\"A\")} W?{REGEXP(\"B\")} @W{REGEXP(\"C\") -> MARK(T1,1,3)};\n";
    
    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document);
      Ruta.apply(cas, script);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    Type t = null;
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;

    t = RutaTestUtils.getTestType(cas, 1);
    ai = cas.getAnnotationIndex(t);
    assertEquals(4, ai.size());
    iterator = ai.iterator();
    assertEquals("A B C", iterator.next().getCoveredText());
    assertEquals("A C", iterator.next().getCoveredText());
    assertEquals("B C", iterator.next().getCoveredText());
    assertEquals("C", iterator.next().getCoveredText());
    
    t = RutaTestUtils.getTestType(cas, 2);
    ai = cas.getAnnotationIndex(t);
    assertEquals(4, ai.size());
    iterator = ai.iterator();
    // TODO: is this really correct? the rule elements should not match at all!
    assertEquals("B C", iterator.next().getCoveredText());
    assertEquals("C", iterator.next().getCoveredText());
    assertEquals("B C", iterator.next().getCoveredText());
    assertEquals("C", iterator.next().getCoveredText());
    
    if(cas != null) {
      cas.release();
    }
  }
}
