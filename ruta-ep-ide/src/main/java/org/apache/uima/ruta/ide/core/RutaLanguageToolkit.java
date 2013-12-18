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

import java.io.File;
import java.io.FilenameFilter;

import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.ide.RutaIdeCorePlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.core.AbstractLanguageToolkit;

public class RutaLanguageToolkit extends AbstractLanguageToolkit {
  private static final String[] languageExtensions = new String[] { "ruta" };

  private static RutaLanguageToolkit sInstance = new RutaLanguageToolkit();

  public RutaLanguageToolkit() {
  }

  @Override
  public boolean languageSupportZIPBuildpath() {
    return true;
  }

  public boolean validateSourcePackage(IPath path) {
    File file = new File(path.toOSString());
    if (file != null) {
      String members[] = file.list(new FilenameFilter() {

        public boolean accept(File dir, String name) {
          if (name.toLowerCase().equals("__init__" + RutaEngine.SCRIPT_FILE_EXTENSION)) {
            return true;
          }
          return false;
        }
      });
      if (members.length > 0) {
        return true;
      }
    }
    return false;
  }

  public String getNatureId() {
    return RutaNature.NATURE_ID;
  }

  public String[] getLanguageFileExtensions() {
    return languageExtensions;
  }

  public String getLanguageName() {
    return "Ruta";
  }

  public String getLanguageContentType() {
    return "org.apache.uima.ruta.ide.rutaContentType";
  }

  public static RutaLanguageToolkit getDefault() {
    return sInstance;
  }

  @Override
  public String getPreferenceQualifier() {
    return RutaIdeCorePlugin.PLUGIN_ID;
  }

}
