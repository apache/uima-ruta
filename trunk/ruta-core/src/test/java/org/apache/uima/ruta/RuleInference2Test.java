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

public class RuleInference2Test {

  
  @Test
  public void test() {

    CAS cas = RutaTestUtils.processTestScript(this.getClass());

    RutaTestUtils.assertAnnotationsEquals(cas, 2, 8, "References",
            "Bergmark, D. (2000). Automatic extraction of reference linking information from online docu-",
            "ments. Technical Report CSTR2000-1821, Cornell Digital Library Research Group.",
            "Bergmark, D., Phempoonpanich, P., and Zhao, S. (2001). Scraping the ACM digital library.",
            "SIGIR Forum, 35(2):1–7.",
            "Berkowitz, E. and Elkhadiri, M. R. (2004). Creation of a style independent intelligent au-",
            "tonomous citation indexer to support academic research. In Proceedings of the the Fifteenth",
            "Midwest Artificial Intelligence and Cognitive Science conference MAICS 2004, pages 68–73.");

    cas.release();
  }
}
