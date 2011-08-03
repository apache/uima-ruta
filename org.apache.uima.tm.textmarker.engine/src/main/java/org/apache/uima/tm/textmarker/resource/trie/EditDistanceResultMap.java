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

package org.apache.uima.tm.textmarker.resource.trie;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * Class EditDistanceResultMap.
 * 
 * @author Daniel Wieth 1570292.
 * 
 *         Contains the result of an edit distance query in the class MultiTreeWordList. The keys
 *         are the strings in the trie, the values are the types of the query.
 * 
 */
public class EditDistanceResultMap extends HashMap<String, Set<String>> {

  private static final long serialVersionUID = 1L;

  /**
   * Adds the string file to the entry query without removing the current value.
   * 
   * @param query
   *          the key to whose set we add.
   * @param file
   *          the value which is added to the key query.
   * @return true if this set did not already contain the specified element, false otherwise.
   */
  public boolean put(String query, String file) {

    if (!containsKey(query)) {
      put(query, new HashSet<String>());
    }

    return get(query).add(file);
  }

  /**
   * Adds all elements of the map m to this map, using the overloaded put function.
   * 
   * @param m
   *          the map which is completely added.
   */
  @Override
  public void putAll(Map<? extends String, ? extends Set<String>> m) {

    for (String query : m.keySet()) {
      for (String file : m.get(query)) {
        put(query, file);
      }
    }
  }

  /**
   * Returns whether a string is contained by the map or not, ignoring certain characters in the
   * keys of the map.
   * 
   * @param query
   *          The string which is contained or not as a key in the map.
   * @param ignoreToken
   *          The characters which are ignored when looking at the keys.
   * @return true, if the query is contained (ignoring the characters of ignoreToken), false
   *         otherwise.
   */
  public boolean containsKey(Object query, String ignoreToken) {

    for (String s : keySet()) {

      String temp = s;

      for (char c : ignoreToken.toCharArray()) {
        temp = temp.replaceAll("\\" + String.valueOf(c), "");
      }

      if (temp.equals(query)) {
        return true;
      }

    }

    return false;
  }

  /**
   * Returns whether a key is contained by the map or not, ignoring the case of the keys (or not).
   * 
   * @param key
   *          The key which is contained by the map or not.
   * @param ignoreCase
   *          Indicates whether the comparison is case sensitive or not.
   * @return true, if the key is contained by the map (ignoring the case or not), false otherwise.
   */
  public boolean containsKey(Object key, boolean ignoreCase) {

    if (ignoreCase) {

      for (String s : keySet()) {
        if (s.toLowerCase().equals(key)) {
          return true;
        }
      }

      return false;

    } else {
      return super.containsKey(key);
    }
  }

  /**
   * Returns a string representation of the map.
   * 
   * @return a string representation of the map.
   */
  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    LinkedList<String> keys = new LinkedList<String>();
    keys.addAll(keySet());
    Collections.sort(keys);

    for (String s : keys) {
      result.append(s + ", [" + get(s) + "] | ");
    }

    return result.toString();
  }
}
