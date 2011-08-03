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

package org.apache.uima.tm.dltk.internal.debug;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

public class TextMarkerDebugPreferences {
  private static final String DEBUGGING_ENGINE_PATH = "debugging_engine";

  private static final String DEBUGGING_ENGINE_PATH_DEFAULT = "";

  private static Preferences getNode() {
    String id = TextMarkerDebugPlugin.getDefault().getBundle().getSymbolicName();
    return Platform.getPreferencesService().getRootNode().node(InstanceScope.SCOPE).node(id);
  }

  public static void save() {
    try {
      getNode().flush();
    } catch (BackingStoreException e) {
      // TODO: add logging
    }
  }

  public static String getDebuggingEnginePath() {
    return getNode().get(DEBUGGING_ENGINE_PATH, DEBUGGING_ENGINE_PATH_DEFAULT);
  }

  public static void setDebuggingEnginePath(String path) {
    getNode().put(DEBUGGING_ENGINE_PATH, path);
  }
}
