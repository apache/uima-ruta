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

import java.io.File;
import java.io.FilenameFilter;

import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.core.AbstractLanguageToolkit;

public class TextMarkerLanguageToolkit extends AbstractLanguageToolkit {
  private static final String[] languageExtensions = new String[] { "tm" };

  private static TextMarkerLanguageToolkit sInstance = new TextMarkerLanguageToolkit();

  public TextMarkerLanguageToolkit() {
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
          if (name.toLowerCase().equals("__init__.tm")) {
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
    return TextMarkerNature.NATURE_ID;
  }

  public String[] getLanguageFileExtensions() {
    return languageExtensions;
  }

  public String getLanguageName() {
    return "TextMarker";
  }

  public String getLanguageContentType() {
    return "de.uniwue.dltk.tmContentType";
  }

  public static TextMarkerLanguageToolkit getDefault() {
    return sInstance;
  }

  @Override
  public String getPreferenceQualifier() {
    return TextMarkerPlugin.PLUGIN_ID;
  }

}
