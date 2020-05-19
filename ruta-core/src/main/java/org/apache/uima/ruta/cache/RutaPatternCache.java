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

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

/**
 * Cache and centralize creation of regex patterns in order to reuse them in different language
 * elements
 *
 */
public class RutaPatternCache {

  private static final LoadingCache<PatternCacheKey, Pattern> CACHE = Caffeine.newBuilder()
          .expireAfterAccess(6, TimeUnit.HOURS).expireAfterWrite(12, TimeUnit.HOURS)
          .maximumSize(10_000).build(k -> createPattern(k));

  /**
   * 
   * @param patternString
   *          - string of the pattern
   * @param ignore
   *          - additional option to compile the pattern with case ignore option activated
   * @return compiled pattern for the given arguments
   */
  public static Pattern getPattern(String patternString, boolean ignore) {

    return CACHE.get(new PatternCacheKey(patternString, ignore));
  }

  private static synchronized Pattern createPattern(PatternCacheKey key) {
    if (key.isIgnoreCase()) {
      return Pattern.compile(key.getPatternString(),
              Pattern.MULTILINE + Pattern.DOTALL + Pattern.CASE_INSENSITIVE + Pattern.UNICODE_CASE);
    }
    return Pattern.compile(key.getPatternString(), Pattern.MULTILINE + Pattern.DOTALL);
  }

  /**
   * 
   * @return view on cache map
   */
  public static ConcurrentMap<PatternCacheKey, Pattern> getChacheMap() {
    return CACHE.asMap();
  }

  /**
   * Invalidate and cleanup cache
   */
  public static void clearCache() {
    CACHE.invalidateAll();
    CACHE.cleanUp();
  }

}
