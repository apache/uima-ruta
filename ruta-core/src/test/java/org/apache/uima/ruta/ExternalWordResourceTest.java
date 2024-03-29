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
import org.junit.jupiter.api.Test;
public class ExternalWordResourceTest {

  @Test
  public void test() {
    String document = "Some text.";
    String script = "";
    script += "WORDTABLE table = RESOURCE(my.package.Class, \"parameter\", \"value\");";
    script += "WORDLIST list = RESOURCE(my.package.Class, \"parameter\", \"value\");";
    
    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document);
      Ruta.apply(cas, script);
    } catch (Exception e) {
      e.printStackTrace();
    }

//    Type t = null;
//    AnnotationIndex<AnnotationFS> ai = null;
//    FSIterator<AnnotationFS> iterator = null;
//
//    t = RutaTestUtils.getTestType(cas, 1);
//    ai = cas.getAnnotationIndex(t);
//    assertThat(ai.size()).isEqualTo(1);
//    iterator = ai.iterator();
//    assertThat(iterator.next().getCoveredText()).isEqualTo("Some");

    if (cas != null) {
      cas.release();
    }

  }
}
