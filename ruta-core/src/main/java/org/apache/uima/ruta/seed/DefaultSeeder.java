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

package org.apache.uima.ruta.seed;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.fit.util.CasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.ruta.type.MARKUP;

public class DefaultSeeder extends TextSeeder {

  public static final String seedType = "org.apache.uima.ruta.type.TokenSeed";

  private final Pattern markupPattern = Pattern.compile(
          "</?\\w[\\w-]*((\\s+[\\w-]+(\\s*=\\s*(?:\".*?\"|'.*?'|[^'\">\\s]+))?)+\\s*|\\s*)/?>");

  private final Pattern xmlCommentPattern = Pattern.compile("<!--[\\s\\S\n]*?-->");

  @Override
  public Type seed(String text, CAS cas) {
    Type result = super.seed(text, cas);
    JCas jCas = null;
    try {
      jCas = cas.getJCas();
    } catch (CASException e) {
      throw new RuntimeException(e);
    }

    // FIXME: lexer rules for html markup won't work. Therefore, those rules where removed in the
    // grammar and the functionality is included directly with regex
    if (text != null) {
      Collection<AnnotationFS> toRemove = new LinkedList<AnnotationFS>();
      addMarkupAnnotations(text, result, xmlCommentPattern, jCas, toRemove);
      addMarkupAnnotations(text, result, markupPattern, jCas, toRemove);
      for (AnnotationFS each : toRemove) {
        cas.removeFsFromIndexes(each);
      }
    }
    return result;
  }

  private void addMarkupAnnotations(String text, Type result, Pattern pattern, JCas jCas,
          Collection<AnnotationFS> toRemove) {
    Matcher matcher = pattern.matcher(text);
    while (matcher.find()) {
      int begin = matcher.start();
      int end = matcher.end();
      MARKUP markup = new MARKUP(jCas, begin, end);
      markup.addToIndexes();
      List<AnnotationFS> selectCovered = CasUtil.selectCovered(result, markup);
      toRemove.addAll(selectCovered);
    }
  }
}
