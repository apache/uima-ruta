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
package org.apache.uima.ruta.engine;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.seed.RutaAnnotationSeeder;

public class DifferentSeeder implements RutaAnnotationSeeder {

  @Override
  public Type seed(String text, CAS cas) {
    Type t1 = RutaTestUtils.getTestType(cas, 1);
    Type t2 = RutaTestUtils.getTestType(cas, 2);
    Type t3 = RutaTestUtils.getTestType(cas, 3);
    Type t4 = RutaTestUtils.getTestType(cas, 4);
    String documentText = cas.getDocumentText();

    annotate(cas, "This", t1, documentText);
    annotate(cas, "is", t2, documentText);
    annotate(cas, "a", t3, documentText);
    annotate(cas, "test", t4, documentText);

    return cas.getAnnotationType();
  }

  private void annotate(CAS cas, String regex, Type type, String documentText) {
    Matcher matcher = Pattern.compile(regex).matcher(documentText);
    while (matcher.find()) {
      AnnotationFS seed = cas.createAnnotation(type, matcher.start(), matcher.end());
      cas.addFsToIndexes(seed);
    }
  }

}
