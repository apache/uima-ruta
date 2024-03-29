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
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.junit.jupiter.api.Test;

public class LongGreedy1Test {

  @SuppressWarnings("unused")
  @Test
  public void test() {
    String name = this.getClass().getSimpleName();
    String namespace = this.getClass().getPackage().getName().replaceAll("\\.", "/");
    CAS cas = null;
    try {
//      cas = RutaTestUtils.process(namespace + "/" + name + RutaEngine.SCRIPT_FILE_EXTENSION, namespace + "/" + name
//              + ".txt", 50);
    } catch (Exception e) {
      e.printStackTrace();
      assert (false);
    }
    Type t = null;
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;

//    t = RutaTestUtils.getTestType(cas, 1);
//    ai = cas.getAnnotationIndex(t);
//    assertThat(ai.size()).isEqualTo(1);
//    iterator = ai.iterator();
//    String coveredText = iterator.next().getCoveredText();
//    assertThat(coveredText.length()).isEqualTo(64998);
    
    if (cas != null) {
      cas.release();
    }

  }
}
