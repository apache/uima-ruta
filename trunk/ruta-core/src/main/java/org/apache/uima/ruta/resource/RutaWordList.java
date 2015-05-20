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

package org.apache.uima.ruta.resource;

import java.util.Collection;
import java.util.Map;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;

public interface RutaWordList {

  boolean contains(String string, boolean ignoreCase, int size, char[] ignoreChars,
          int maxIgnoreChars, boolean ignoreWS);

  boolean containsFragment(String string, boolean ignoreCase, int size, char[] ignoreChars,
          int maxIgnoreChars, boolean ignoreWS);

  Collection<String> contains(String string, boolean ignoreCase, int ignoreLength, boolean edit,
          double distance, String ignoreToken);

  Collection<String> containsFragment(String string, boolean ignoreCase, int ignoreLength,
          boolean edit, double distance, String ignoreToken);

  Collection<AnnotationFS> find(RutaStream stream, boolean ignoreCase, int size,
          char[] ignoreToken, int maxIgnoredTokens, boolean ingoreWS);

  Collection<AnnotationFS> find(RutaStream stream, Map<String, Object> typeMap, boolean ignoreCase,
          int ignoreLength, boolean edit, double distance, String ignoreToken);

}
