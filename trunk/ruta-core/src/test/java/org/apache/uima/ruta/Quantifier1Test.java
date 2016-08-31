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
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.junit.Test;

public class Quantifier1Test {
  @Test
  public void test() {
    
    CAS cas = RutaTestUtils.processTestScript(this.getClass());

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 3, "A B", "A B", "A");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 3, "A", "A", "A");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 3, "A B", "A B B", "A");
    RutaTestUtils.assertAnnotationsEquals(cas, 4, 3, "A", "A", "A");
    RutaTestUtils.assertAnnotationsEquals(cas, 5, 2, "A B", "A B B");
    RutaTestUtils.assertAnnotationsEquals(cas, 6, 2, "A B", "A B");
    RutaTestUtils.assertAnnotationsEquals(cas, 7, 1, "A B B");
    RutaTestUtils.assertAnnotationsEquals(cas, 8, 1, "A B B");
    RutaTestUtils.assertAnnotationsEquals(cas, 9, 2, "A B", "A B B");
    RutaTestUtils.assertAnnotationsEquals(cas, 10, 3, "A B", "A B", "A");
    RutaTestUtils.assertAnnotationsEquals(cas, 11, 3, "A B", "A B B", "A");
    RutaTestUtils.assertAnnotationsEquals(cas, 12, 2, "A B C", "A C");
    RutaTestUtils.assertAnnotationsEquals(cas, 13, 2, "A B C", "A C");
    RutaTestUtils.assertAnnotationsEquals(cas, 14, 3, "A B C", "A B B C", "A C");
    RutaTestUtils.assertAnnotationsEquals(cas, 15, 3, "A B C", "A B B C", "A C");
    RutaTestUtils.assertAnnotationsEquals(cas, 16, 2, "A B C", "A B B C");
    RutaTestUtils.assertAnnotationsEquals(cas, 17, 2, "A B C", "A B B C");

    cas.release();
  }
}
