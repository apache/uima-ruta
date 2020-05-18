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

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Cache and centralize creation of regex patterns in order to reuse them in different language
 * elements
 *
 */
public class RutaPatternCache {

  protected static final Map<String, Pattern> CACHE = new HashMap<>();

  public static Pattern getPattern(String stringValue, boolean ignore) {

    // cache and centralize creation of regex patterns
    // in order to reuse them in different language elements

    String patternKey = stringValue + "#" + ignore;
    Pattern pattern = CACHE.get(patternKey);
    if (pattern == null) {

      pattern = createPattern(stringValue, ignore, patternKey);
    }

    return pattern;
  }

  private static synchronized Pattern createPattern(String stringValue, boolean ignore,
          String patternKey) {
    Pattern pattern;
    if (ignore) {
      pattern = Pattern.compile(stringValue,
              Pattern.MULTILINE + Pattern.DOTALL + Pattern.CASE_INSENSITIVE + Pattern.UNICODE_CASE);
    } else {
      pattern = Pattern.compile(stringValue, Pattern.MULTILINE + Pattern.DOTALL);
    }
    CACHE.put(patternKey, pattern);
    return pattern;
  }
}
