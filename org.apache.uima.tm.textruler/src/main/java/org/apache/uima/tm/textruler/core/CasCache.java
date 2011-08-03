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

package org.apache.uima.tm.textruler.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.apache.uima.cas.CAS;

/**
 * 
 * CasCache works as a "most recently used" cache since the input documents CASes (usually loaded
 * from XMI files) are too big for the heap memory if there are too many of them.
 * 
 * MLExmapleDocumentSet creates one for you, so you should not have to use this class manually.
 * 
 * The key of a cache entry is usually the file path of the XMI file.
 */
public class CasCache {

  private static final boolean DISABLE_CACHE = false; // for test purposes.

  // every CAS gets loaded
  // every time if this is
  // TRUE!
  private static CAS reuseCASforDisabledCache = null;

  private static class MLCacheEntry {

    public String key;

    public CAS cas;

    public MLCacheEntry prev;

    public MLCacheEntry next;

    @Override
    public String toString() {
      return key;
    }
  }

  // private long misses = 0; for test purposes;

  private CasCacheLoader casLoader;

  private int cacheSize;

  // a linked list for the "most recently used" policy
  private MLCacheEntry tail = null;

  private MLCacheEntry head = null;

  // a hashMap for fast cache access
  private HashMap<String, MLCacheEntry> map;

  public CasCache(int size, CasCacheLoader casLoader) {
    this.cacheSize = size;
    this.map = new HashMap<String, MLCacheEntry>(size);
    this.casLoader = casLoader;
  }

  public void logDebugInfo(String str) {
    if (!TextRulerToolkit.DEBUG)
      return;
    TextRulerToolkit.log("--- " + str);
    MLCacheEntry e = tail;
    while (e != null) {
      TextRulerToolkit.log(e.key);
      e = e.next;
    }
    TextRulerToolkit.log("---");

  }

  public boolean containsElementWithKey(String key) {
    return map.containsKey(key);
  }

  public CAS getCAS(String fileName) {
    if (DISABLE_CACHE) {
      reuseCASforDisabledCache = casLoader.loadCAS(fileName, reuseCASforDisabledCache);
      return reuseCASforDisabledCache;
    }
    MLCacheEntry entry = map.get(fileName);
    if (entry != null) // cache HIT
    {
      if (entry != head) {
        // move entry from its current location to head:

        MLCacheEntry newTail = entry == tail ? tail.next : tail;

        if (entry.prev != null)
          entry.prev.next = entry.next;
        if (entry.next != null)
          entry.next.prev = entry == tail ? null : entry.prev;
        tail = newTail;
        head.next = entry;
        entry.next = null;
        entry.prev = head;
        head = entry;
      }
      if (TextRulerToolkit.DEBUG) {
        debugList();
      }
    } else // cache MISS
    {
      // misses++; test

      // if (TextRulerToolkit.DEBUG)
      // debugList();

      entry = new MLCacheEntry();
      entry.key = fileName;
      entry.prev = head;
      entry.next = null;
      if (head != null)
        head.next = entry;
      head = entry;
      CAS reuseCAS = null;
      if (map.size() >= cacheSize) {
        // remove oldest entry (the tail)
        tail.cas.reset(); // TODO loadCAS below also does a reset. are
        // subsequent reset calls expensive ?
        reuseCAS = tail.cas;
        tail.cas = null;
        map.remove(tail.key);
        tail = tail.next;
        tail.prev = null;
      } else {
        if (tail == null)
          tail = head;
      }

      entry.cas = casLoader.loadCAS(fileName, reuseCAS); // if
      // reuseCAS==null,
      // loadCAS
      // creates a new
      // one!

      map.put(fileName, entry);
      if (map.size() > cacheSize) {
        TextRulerToolkit.logIfDebug("[CASCACHE] ERROR, CACHE SIZE EXCEEDED!");
      }
      if (TextRulerToolkit.DEBUG) {
        debugList();
      }
    }
    return entry.cas;
  }

  private void debugList() {
    if (tail == null)
      return;
    MLCacheEntry e = tail;
    int i = 1;
    while (e.next != null) {
      e = e.next;
      i++;
      if (i > map.size()) {
        TextRulerToolkit.log("[CASCACHE] ERROR, INNER LENGTH INCOSISTENCY! " + i + "   vs. "
                + map.size());
      }
    }
    if (i != map.size())
      TextRulerToolkit.log("[CASCACHE] ERROR, LENGTH INCOSISTENCY! " + i + "   vs. " + map.size());
  }

  public Collection<CAS> getCachedCASes() {
    ArrayList<CAS> result = new ArrayList<CAS>();
    for (MLCacheEntry e : map.values())
      result.add(e.cas);
    return result;
  }

  public void clear() {
    tail = null;
    head = null;
    for (MLCacheEntry ce : map.values()) {
      ce.cas.reset();
      GlobalCASSource.releaseCAS(ce.cas); // ce.cas.release();
    }
    map.clear();
  }

}
