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

package org.apache.uima.ruta.engine;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.junit.Test;

public class ParamVarTest {

  @Test
  public void test() {
    String document = "Some text.";
    String script = "";
    script += "BLOCK(First) Document{}{\n";
    script += "STRING s = \"a\";\n";
    script += "W.ct==s{-> T1};\n";
    script += "}\n";
    script += "BLOCK(Second) Document{}{\n";
    script += "STRING s = \"b\";\n";
    script += "W.ct==s{-> T2};\n";
    script += "}\n";
    script += "STRING s = \"x\";\n";
    script += "INT i = 100;\n";
    script += "BOOLEAN b = false;\n";
    script += "W.ct==s{-> T3};\n";
    script += "W.begin==i{-> T4};\n";
    script += "Document{b -> T5};\n";
    CAS cas = null;
    Map<String, Object> params = new HashMap<String, Object>();
    params.put(RutaEngine.PARAM_VAR_NAMES, new String[] {"First.s", "Second.s", "s", "i", "b"});
    params.put(RutaEngine.PARAM_VAR_VALUES, new String[] {"Some", "text", "Some", "0", "true"});
    
    try {
      cas = RutaTestUtils.getCAS(document);
      Ruta.apply(cas, script, params);
    } catch (Exception e) {
      e.printStackTrace();
    }

    Type t = null;
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;

    t = RutaTestUtils.getTestType(cas, 1);
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertEquals(1, ai.size());
    assertEquals("Some", iterator.next().getCoveredText());
    
    t = RutaTestUtils.getTestType(cas, 2);
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertEquals(1, ai.size());
    assertEquals("text", iterator.next().getCoveredText());
    
    t = RutaTestUtils.getTestType(cas, 3);
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertEquals(1, ai.size());
    assertEquals("Some", iterator.next().getCoveredText());
    
    t = RutaTestUtils.getTestType(cas, 4);
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertEquals(1, ai.size());
    assertEquals("Some", iterator.next().getCoveredText());
    
    t = RutaTestUtils.getTestType(cas, 5);
    ai = cas.getAnnotationIndex(t);
    iterator = ai.iterator();
    assertEquals(1, ai.size());
    assertEquals("Some text.", iterator.next().getCoveredText());
    
    if (cas != null) {
      cas.release();
    }

  }
}
