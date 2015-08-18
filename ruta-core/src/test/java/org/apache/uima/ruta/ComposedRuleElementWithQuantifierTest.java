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
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.junit.Test;

public class ComposedRuleElementWithQuantifierTest {

  @Test
  public void test() {
    String document = "Bla DDD, Bla, DDD,DDD, Bla, DDD,DDD Bla.";
    String script = "";
    script += "\"DDD\" -> T1;\n";
    script += "(T1 COMMA)+? (T1 W){->MARKONCE(T2,1,2)};\n";
    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document);
      Ruta.apply(cas, script);
    } catch (Exception e) {
      e.printStackTrace();
    }

    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "DDD,DDD Bla");

    cas.release();
  }
  
  @Test
  public void testStackedWithWildCard() {
    String document = "some text\n 1 HEADLINE\n some text";
    String script = "";
    
    script +="\"1 HEADLINE\"-> T1;\n";
    script +="DOUBLE n;\n";
    script +="BLOCK(eachTag) T1{} {\n";
    script +="((NUM{STARTSWITH(T1)} (PERIOD NUM)?){PARSE(n)} (W #){-> T2})\n";
    script +=" {-> T3};\n";
    script +="}\n";
    
    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document);
      Ruta.apply(cas, script);
    } catch (Exception e) {
      e.printStackTrace();
    }

    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "HEADLINE");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "1 HEADLINE");

    cas.release();
    
  }
  
  
  
}
