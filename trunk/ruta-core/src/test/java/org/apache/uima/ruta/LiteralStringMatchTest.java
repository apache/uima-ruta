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

package org.apache.uima.ruta;

import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.junit.Test;

public class LiteralStringMatchTest {

  @Test
  public void test() {

    CAS cas = RutaTestUtils.processTestScript(this.getClass());

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 4, "CW", "CW", "CW", "CW");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "SW");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 0);
    RutaTestUtils.assertAnnotationsEquals(cas, 6, 2, "CW COMMA CW COMMA", "CW COMMA");
    RutaTestUtils.assertAnnotationsEquals(cas, 7, 5, "CW", "CW", "CW", "SW", "CW");
    RutaTestUtils.assertAnnotationsEquals(cas, 8, 5, "CW", "CW", "CW SW CW", "SW CW", "CW");
    RutaTestUtils.assertAnnotationsEquals(cas, 9, 2, "CW COMMA CW COMMA CW", "CW COMMA CW");
    RutaTestUtils.assertAnnotationsEquals(cas, 10, 3, "CW COMMA CW COMMA CW SW", "CW COMMA CW SW", "CW SW");
    RutaTestUtils.assertAnnotationsEquals(cas, 11, 3, "CW COMMA CW COMMA CW SW CW PERIOD", "CW COMMA CW SW CW PERIOD",
            "CW SW CW PERIOD");
    RutaTestUtils.assertAnnotationsEquals(cas, 13, 6, "CW COMMA", "CW COMMA", "CW COMMA", "CW SW", "CW SW", "CW SW");
    RutaTestUtils.assertAnnotationsEquals(cas, 14, 3, "CW COMMA CW COMMA CW SW", "CW COMMA CW SW", "CW SW");

    cas.release();
  }
}
