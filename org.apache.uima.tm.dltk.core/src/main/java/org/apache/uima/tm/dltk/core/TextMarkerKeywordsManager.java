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

package org.apache.uima.tm.dltk.core;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.dltk.core.DLTKCore;

public final class TextMarkerKeywordsManager {
  private static final String EXTENSION_POINT = "org.apache.uima.tm.dltk.core.tmkeywords";

  private static final String CLASS = "class";

  private static String[][] all = new String[ITextMarkerKeywords.END_INDEX][];

  private static boolean initialized = false;

  private static void initialize() {
    if (initialized) {
      return;
    }
    initialized = true;
    IConfigurationElement[] cfg = Platform.getExtensionRegistry().getConfigurationElementsFor(
            EXTENSION_POINT);
    for (int i = 0; i < ITextMarkerKeywords.END_INDEX; i++) {
      all[i] = new String[0];
    }
    for (int i = 0; i < cfg.length; i++) {
      if (cfg[i].getName().equals("keywords")) {
        try {
          ITextMarkerKeywords keywords = (ITextMarkerKeywords) cfg[i]
                  .createExecutableExtension(CLASS);
          if (keywords != null) {
            for (int q = 0; q < ITextMarkerKeywords.END_INDEX; ++q) {
              String[] kw2 = keywords.getKeywords(q);
              all[q] = TextMarkerKeywords.append(all[q], kw2);
            }
          }
        } catch (CoreException e) {
          if (DLTKCore.DEBUG) {
            e.printStackTrace();
          }
        }
      }
    }
  }

  public static String[] getKeywords() {
    initialize();
    return all[ITextMarkerKeywords.ALL];
  }

  public static String[] getKeywords(int type) {
    initialize();
    if (type >= 0 && type < all.length) {
      return all[type];
    }
    return new String[0];
  }
}
