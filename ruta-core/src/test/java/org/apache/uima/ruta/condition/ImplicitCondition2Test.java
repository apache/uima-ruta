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

import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.junit.Test;

public class ImplicitCondition2Test {

  @Test
  public void test() {
    String document = "Peter Kluegl, Joern Kottmann, Marshall Schor.";
    String script = "";
    script += "CW{CW.begin<=14 -> T1};\n";
    script += "CW{CW.begin<14-> T2};\n";
    script += "CW{CW.begin==0-> T3};\n";
    script += "CW{CW.begin!=0-> T4};\n";
    script += "CW{CW.begin>=20-> T5};\n";
    script += "CW{CW.begin>20-> T6};\n";
    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document);
      Ruta.apply(cas, script);
    } catch (Exception e) {
      e.printStackTrace();
    }

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 3, "Peter", "Kluegl", "Joern");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 2, "Peter", "Kluegl");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "Peter");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 5, "Kluegl", "Joern", "Kottmann", "Marshall", "Schor");
    RutaTestUtils.assertAnnotationsEquals(cas, 5, 3, "Kottmann", "Marshall", "Schor");
    RutaTestUtils.assertAnnotationsEquals(cas, 6, 2, "Marshall", "Schor");

    cas.release();
  }
}
