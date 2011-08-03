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

package org.apache.uima.tm.dltk.internal.ui.documentation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.Reader;

public class HtmlTextMarkerPagesLocation implements ITextMarkerPagesLocation {

  private File fLocation;

  /**
   * @param location
   *          directory(!)
   */
  public HtmlTextMarkerPagesLocation(File location) {
    if (!location.isDirectory()) {
      throw new IllegalArgumentException();
    }

    fLocation = location;
  }

  public Reader getHtmlInfo(String keyword) {
    final String pattern = keyword + ".htm";
    File[] result = fLocation.listFiles(new FilenameFilter() {

      public boolean accept(File dir, String name) {
        if (name.equals(pattern))
          return true;
        return false;
      }

    });
    if (result != null && result.length >= 1 && result[0] != null) {
      try {
        FileReader reader = new FileReader(result[0]);
        return reader;
      } catch (FileNotFoundException e) {
        // hmmm! but nothing to do.
      }
    }
    return null;
  }

  public File getLocation() {
    return fLocation;
  }

  public void setLocation(File location) {
    if (!location.isDirectory())
      return;
    fLocation = location;
  }

}
