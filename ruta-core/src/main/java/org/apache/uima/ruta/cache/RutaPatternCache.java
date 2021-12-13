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
package org.apache.uima.ruta.cache;

import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static java.util.regex.Pattern.DOTALL;
import static java.util.regex.Pattern.MULTILINE;
import static java.util.regex.Pattern.UNICODE_CASE;

import java.util.Collections;
import java.util.Map;
import java.util.regex.Pattern;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

/**
 * Cache and centralize creation of regex patterns in order to reuse them in different language
 * elements.
 */
public class RutaPatternCache {

  private static final String PROP_RUTA_PATTERN_CACHE_SIZE = "uima.ruta.pattern_cache_size";

  private static final int RUTA_PATTERN_CACHE_SIZE = Integer
          .parseInt(System.getProperty(PROP_RUTA_PATTERN_CACHE_SIZE, "10000"));

  private static final LoadingCache<PatternCacheKey, Pattern> CACHE = Caffeine.newBuilder() //
          .maximumSize(RUTA_PATTERN_CACHE_SIZE) //
          .build(k -> createPattern(k));

  /**
   *
   * @param patternString
   *          regular expression as a string
   * @param ignore
   *          whether to compile the pattern with case ignore option activated
   * @return compiled pattern for the given arguments
   */
  public static Pattern getPattern(String patternString, boolean ignore) {

    int flags = MULTILINE | DOTALL;

    if (ignore) {
      flags |= CASE_INSENSITIVE | UNICODE_CASE;
    }

    return CACHE.get(new PatternCacheKey(patternString, flags));
  }

  /**
   *
   * @param patternString
   *          regular expression as a string
   * @param flags
   *          flags controlling the regular expression.
   * @return compiled pattern for the given arguments
   */
  public static Pattern getPattern(String patternString, int flags) {

    return CACHE.get(new PatternCacheKey(patternString, flags));
  }

  private static synchronized Pattern createPattern(PatternCacheKey key) {
    return Pattern.compile(key.getPatternString(), key.getFlags());
  }

  /**
   * @return an unmodifiable view of the cache map.
   */
  public static Map<PatternCacheKey, Pattern> getCacheMap() {
    return Collections.unmodifiableMap(CACHE.asMap());
  }

  /**
   * Invalidate and cleanup cache
   */
  public static void clearCache() {
    CACHE.invalidateAll();
    CACHE.cleanUp();
  }
}
