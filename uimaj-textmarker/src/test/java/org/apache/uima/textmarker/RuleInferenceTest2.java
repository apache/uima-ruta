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

package org.apache.uima.textmarker;

import static org.junit.Assert.assertEquals;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.junit.Test;

public class RuleInferenceTest2 {

  @Test
  public void test() {
    String name = this.getClass().getSimpleName();
    String namespace = this.getClass().getPackage().getName().replaceAll("\\.", "/");
    CAS cas = null;
    try {
      cas = TextMarkerTestUtils.process(namespace + "/" + name + ".tm", namespace + "/" + name
              + ".txt", 50);
    } catch (Exception e) {
      e.printStackTrace();
      assert (false);
    }
    Type t = null;
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;

    t = TextMarkerTestUtils.getTestType(cas, 2);
    ai = cas.getAnnotationIndex(t);
    assertEquals(8, ai.size());
    iterator = ai.iterator();
    assertEquals("References", iterator.next().getCoveredText());
    assertEquals("Bergmark, D. (2000). Automatic extraction of reference linking information from online docu-", iterator.next().getCoveredText());
    assertEquals("ments. Technical Report CSTR2000-1821, Cornell Digital Library Research Group.", iterator.next().getCoveredText());
    assertEquals("Bergmark, D., Phempoonpanich, P., and Zhao, S. (2001). Scraping the ACM digital library.", iterator.next().getCoveredText());
    assertEquals("SIGIR Forum, 35(2):1–7.", iterator.next().getCoveredText());
    assertEquals("Berkowitz, E. and Elkhadiri, M. R. (2004). Creation of a style independent intelligent au-", iterator.next().getCoveredText());
    assertEquals("tonomous citation indexer to support academic research. In Proceedings of the the Fifteenth", iterator.next().getCoveredText());
    assertEquals("Midwest Artificial Intelligence and Cognitive Science conference MAICS 2004, pages 68–73.", iterator.next().getCoveredText());

   

    if (cas != null) {
      cas.release();
    }

  }
}
