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

import static org.apache.uima.ruta.engine.Ruta.apply;
import static org.apache.uima.ruta.engine.RutaTestUtils.getCAS;

import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.extensions.RutaParseRuntimeException;
import org.junit.Test;

/** Tests for exceptions thrown when parsing a Ruta script */
public class RutaParsingErrorTest {

  @Test
  // TODO maybe this test is not necessary...
  public void test() throws Exception {
    CAS cas = getCAS("aaaa.");
    apply(cas, "\"aaaa.\" -> T1;");
    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "aaaa.");
  }

  @Test(expected = RutaParseRuntimeException.class)
  public void testEscapedDot() throws Exception {
    CAS cas = getCAS("aaaa");
    apply(cas, "\"aaaa\\.\" -> T1;");
  }
}
