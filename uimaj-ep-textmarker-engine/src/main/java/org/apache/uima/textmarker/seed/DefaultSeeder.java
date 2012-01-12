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

package org.apache.uima.textmarker.seed;

import java.io.BufferedReader;
import java.io.StringReader;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.JCas;
import org.apache.uima.textmarker.type.TokenSeed;

public class DefaultSeeder implements TextMarkerAnnotationSeeder {

  public static final String seedType =  "org.apache.uima.textmarker.type.TokenSeed";
  
  public Type seed(String text, CAS cas) {
    Type result = null;
    JCas jCas = null;
    int size = 0;
    try {
      jCas = cas.getJCas();
      size = jCas.getAnnotationIndex(TokenSeed.type).size();
      result =  jCas.getTypeSystem().getType(seedType);
    } catch (CASException e1) {
    }
    // do not apply seeding if there are already annotations of this seed type
    if (jCas == null || size != 0) {
      return result;
    }
    BufferedReader reader = new BufferedReader(new StringReader(text));
    final SeedLexer sourceLexer = new SeedLexer(reader);
    sourceLexer.setJCas(jCas);
    AnnotationFS a = null;

    try {
      a = sourceLexer.yylex();
    } catch (Exception e) {
    }
    while (a != null) {
      cas.addFsToIndexes(a);
      try {
        a = sourceLexer.yylex();
      } catch (Exception e) {
      }
    }
    return result;
  }
}
