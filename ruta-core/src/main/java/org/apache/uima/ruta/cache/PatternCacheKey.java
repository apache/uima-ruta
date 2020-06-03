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

public class PatternCacheKey {

  private final String patternString;

  private final int flags;

  public PatternCacheKey(String patternString, int flags) {
    super();

    this.patternString = patternString;
    this.flags = flags;
  }

  public String getPatternString() {
    return patternString;
  }

  public int getFlags() {
    return flags;
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + flags;
    result = prime * result + ((patternString == null) ? 0 : patternString.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    PatternCacheKey other = (PatternCacheKey) obj;
    if (flags != other.flags) {
      return false;
    }
    if (patternString == null) {
      if (other.patternString != null) {
        return false;
      }
    } else if (!patternString.equals(other.patternString)) {
      return false;
    }
    return true;
  }
}
