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

package org.apache.uima.ruta.ide.ui.documentation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.ruta.ide.RutaIdeUIPlugin;
import org.apache.uima.ruta.ide.ui.RutaPreferenceConstants;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.ui.documentation.IScriptDocumentationProvider;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;

public class RutaPagesDocumentationProvider implements IScriptDocumentationProvider {

  private List folders = null;

  public Reader getInfo(IMember element, boolean lookIntoParents, boolean lookIntoExternal) {
    return null;
  }

  public Reader getInfo(String content) {
    initalizeLocations(false);

    if (folders != null) {
      for (Iterator iterator = folders.iterator(); iterator.hasNext();) {
        RutaPageFolder f = (RutaPageFolder) iterator.next();
        HashMap pages = f.getPages();
        String ans = (String) pages.get(content);
        if (ans != null) {
          IPath filePath = new Path(f.getPath()).append(ans);
          File file = filePath.toFile();
          if (file != null && file.isFile()) {
            try {
              return new FileReader(file);
            } catch (FileNotFoundException e) {
              e.printStackTrace();
            }
          }
          break;
        }
      }
    }

    return null;
  }

  private void initalizeLocations(boolean force) {
    if (!force && this.folders != null)
      return;

    RutaIdeUIPlugin.getDefault().getPreferenceStore()
            .addPropertyChangeListener(new IPropertyChangeListener() {

              public void propertyChange(PropertyChangeEvent event) {
                initalizeLocations(true);
              }

            });

    String value = RutaIdeUIPlugin.getDefault().getPreferenceStore()
            .getString(RutaPreferenceConstants.DOC_RUTA_PAGES_LOCATIONS);

    try {
      if (!StringUtils.isEmpty(value)) {
        // value = "<Pages></Pages>";
        this.folders = RutaPageFolder.readXML(value);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
