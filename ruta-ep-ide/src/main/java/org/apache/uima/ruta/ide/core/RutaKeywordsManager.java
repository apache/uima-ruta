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

package org.apache.uima.ruta.ide.core;

import org.apache.uima.ruta.extensions.IRutaActionExtension;
import org.apache.uima.ruta.extensions.IRutaBlockExtension;
import org.apache.uima.ruta.extensions.IRutaBooleanFunctionExtension;
import org.apache.uima.ruta.extensions.IRutaConditionExtension;
import org.apache.uima.ruta.extensions.IRutaNumberFunctionExtension;
import org.apache.uima.ruta.extensions.IRutaStringFunctionExtension;
import org.apache.uima.ruta.extensions.IRutaTypeFunctionExtension;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.dltk.core.DLTKCore;

public final class RutaKeywordsManager {
  private static final String EXTENSION_POINT = "org.apache.uima.ruta.ide.rutakeywords";

  private static final String CLASS = "class";

  private static String[][] all = new String[IRutaKeywords.END_INDEX][];

  private static boolean initialized = false;

  private static void initialize() {
    if (initialized) {
      return;
    }
    initialized = true;
    IConfigurationElement[] cfg = Platform.getExtensionRegistry().getConfigurationElementsFor(
            EXTENSION_POINT);
    for (int i = 0; i < IRutaKeywords.END_INDEX; i++) {
      all[i] = new String[0];
    }
    for (int i = 0; i < cfg.length; i++) {
      if (cfg[i].getName().equals("keywords")) {
        try {
          IRutaKeywords keywords = (IRutaKeywords) cfg[i].createExecutableExtension(CLASS);
          if (keywords != null) {
            for (int q = 0; q < IRutaKeywords.END_INDEX; ++q) {
              String[] kw2 = keywords.getKeywords(q);
              all[q] = RutaKeywords.append(all[q], kw2);
            }
          }
        } catch (CoreException e) {
          if (DLTKCore.DEBUG) {
            e.printStackTrace();
          }
        }
      }
    }
    IRutaActionExtension[] actionExtensions = RutaExtensionManager.getDefault()
            .getRutaActionExtensions();
    for (IRutaActionExtension each : actionExtensions) {
      String[] knownExtensions = each.getKnownExtensions();
      all[IRutaKeywords.ACTION] = RutaKeywords.append(all[IRutaKeywords.ACTION], knownExtensions);
    }
    IRutaConditionExtension[] conditionExtensions = RutaExtensionManager.getDefault()
            .getRutaConditionExtensions();
    for (IRutaConditionExtension each : conditionExtensions) {
      String[] knownExtensions = each.getKnownExtensions();
      all[IRutaKeywords.CONDITION] = RutaKeywords.append(all[IRutaKeywords.CONDITION],
              knownExtensions);
    }
    IRutaBooleanFunctionExtension[] booleanFunctionExtensions = RutaExtensionManager.getDefault()
            .getRutaBooleanFunctionExtensions();
    for (IRutaBooleanFunctionExtension each : booleanFunctionExtensions) {
      String[] knownExtensions = each.getKnownExtensions();
      all[IRutaKeywords.BOOLEANFUNCTION] = RutaKeywords.append(all[IRutaKeywords.BOOLEANFUNCTION],
              knownExtensions);
    }
    IRutaNumberFunctionExtension[] numberFunctionExtensions = RutaExtensionManager.getDefault()
            .getRutaNumberFunctionExtensions();
    for (IRutaNumberFunctionExtension each : numberFunctionExtensions) {
      String[] knownExtensions = each.getKnownExtensions();
      all[IRutaKeywords.NUMBERFUNCTION] = RutaKeywords.append(all[IRutaKeywords.NUMBERFUNCTION],
              knownExtensions);
    }
    IRutaStringFunctionExtension[] stringFunctionExtensions = RutaExtensionManager.getDefault()
            .getRutaStringFunctionExtensions();
    for (IRutaStringFunctionExtension each : stringFunctionExtensions) {
      String[] knownExtensions = each.getKnownExtensions();
      all[IRutaKeywords.STRINGFUNCTION] = RutaKeywords.append(all[IRutaKeywords.STRINGFUNCTION],
              knownExtensions);
    }
    IRutaTypeFunctionExtension[] typeFunctionExtensions = RutaExtensionManager.getDefault()
            .getRutaTypeFunctionExtensions();
    for (IRutaTypeFunctionExtension each : typeFunctionExtensions) {
      String[] knownExtensions = each.getKnownExtensions();
      all[IRutaKeywords.TYPEFUNCTION] = RutaKeywords.append(all[IRutaKeywords.TYPEFUNCTION],
              knownExtensions);
    }
    IRutaBlockExtension[] blockExtensions = RutaExtensionManager.getDefault()
            .getRutaBlockExtensions();
    for (IRutaBlockExtension each : blockExtensions) {
      String[] knownExtensions = each.getKnownExtensions();
      all[IRutaKeywords.DECLARATION] = RutaKeywords.append(all[IRutaKeywords.DECLARATION],
              knownExtensions);
    }
  }

  public static String[] getKeywords() {
    initialize();
    return all[IRutaKeywords.ALL];
  }

  public static String[] getKeywords(int type) {
    initialize();
    if (type >= 0 && type < all.length) {
      return all[type];
    }
    return new String[0];
  }
}
