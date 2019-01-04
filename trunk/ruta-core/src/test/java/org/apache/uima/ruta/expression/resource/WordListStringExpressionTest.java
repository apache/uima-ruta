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

package org.apache.uima.ruta.expression.resource;

import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.junit.Test;

public class WordListStringExpressionTest {


  @Test
  public void test() throws Exception {
    String document = "1 0 0 text 2 0 0.";

    String script="STRING s = \"org/apache/uima/ruta/action/\";\n";
    script +="WORDLIST wl = s + \"MarkFastTestList.txt\";\n";
    script +="MARKFAST(T1,wl);\n";
    
    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);
    
    RutaTestUtils.assertAnnotationsEquals(cas, 1, 2, "1 0 0", "2 0 0");

  }
  
  @Test
  public void testInBlock() throws Exception {
    String document = "1 0 0 text 2 0 0.";

    String script="STRING s = \"org/apache/uima/ruta/action/\";\n";
    script +="BLOCK(block) Document{}{\n";
    script +="WORDLIST wl = s + \"MarkFastTestList.txt\";\n";
    script +="MARKFAST(T1,wl);\n";
    script +="}\n";
    
    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script);
    
    RutaTestUtils.assertAnnotationsEquals(cas, 1, 2, "1 0 0", "2 0 0");

  }
  
}
