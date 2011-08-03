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

package org.apache.uima.tm.textmarker.kernel.expression.resource;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;
import org.apache.uima.tm.textmarker.resource.TextMarkerWordList;

public class LiteralWordListExpression extends WordListExpression {

  private final String text;

  public LiteralWordListExpression(String text) {
    super();
    if (text.startsWith("\'") && text.endsWith("\'")) {
      text = text.substring(1, text.length() - 1);
    }
    this.text = stripEscapes(text); // hotfix for the escaping problem
  }

  public static String stripEscapes(String str) {
    String result = str.replaceAll("\\\\\\\\", "\\\\");
    return result.replaceAll("\\\\\\\"", "\\\"");
  }

  @Override
  public TextMarkerWordList getList(TextMarkerStatement element) {
    TextMarkerWordList list = element.getEnvironment().getWordList(getText());
    return list;
  }

  public String getText() {
    return text;
  }

}
