/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.uima.ruta.maven;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.maven.model.FileSet;
import org.codehaus.plexus.util.FileUtils;

public class Utils {

  @SuppressWarnings("unchecked")
  public static List<File> getFiles(FileSet fileSet) throws IOException {
    File directory = new File(fileSet.getDirectory());
    String includes = toString(fileSet.getIncludes());
    String excludes = toString(fileSet.getExcludes());
    return (List<File>) FileUtils.getFiles(directory, includes, excludes);
  }

  private static String toString(List<String> strings) {
    StringBuilder sb = new StringBuilder();
    for (String string : strings) {
      if (sb.length() > 0) {
        sb.append(", ");
      }
      sb.append(string);
    }
    return sb.toString();
  }

}
