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

package org.apache.uima.tm.textmarker.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;


public class AnonymousWordList implements TextMarkerWordList {

  private final List<String> list;

  public AnonymousWordList(List<String> strings) {
    super();
    this.list = strings;
  }

  public boolean contains(String s, boolean ignoreCase, int size, char[] ignoreChars,
          int maxIgnoreChars) {
    return list.contains(s);
  }

  public boolean containsFragment(String s, boolean ignoreCase, int size, char[] ignoreChars,
          int maxIgnoreChars) {
    return contains(s, ignoreCase, size, ignoreChars, maxIgnoreChars);
  }

  public List<AnnotationFS> find(TextMarkerStream stream, boolean ignoreCase, int size,
          char[] ignoreToken, int maxIgnoredTokens) {
    List<AnnotationFS> result = new ArrayList<AnnotationFS>();
    for (String each : list) {
      stream.moveToFirst();
      while (stream.hasNext()) {
        TextMarkerBasic next = (TextMarkerBasic) stream.next();
        if (each.equals(next.getCoveredText())) {
          result.add(next);
        }
      }
    }
    return result;
  }

  public List<AnnotationFS> find(TextMarkerStream stream, Map<String, Type> typeMap,
          boolean ignoreCase, int ignoreLength, boolean edit, double distance, String ignoreToken) {
    return new ArrayList<AnnotationFS>();
  }

  public List<String> contains(String string, boolean ignoreCase, int ignoreLength, boolean edit,
          double distance, String ignoreToken) {
    // TODO Auto-generated method stub
    return null;
  }

  public List<String> containsFragment(String string, boolean ignoreCase, int ignoreLength,
          boolean edit, double distance, String ignoreToken) {
    // TODO Auto-generated method stub
    return null;
  }

}
