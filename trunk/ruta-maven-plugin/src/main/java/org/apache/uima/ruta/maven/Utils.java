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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.maven.model.FileSet;
import org.codehaus.plexus.util.FileUtils;
import org.sonatype.plexus.build.incremental.BuildContext;

public class Utils {

  public static List<File> getModifiedFiles(FileSet fileSet, BuildContext buildContext) throws IOException {
    List<File> result = new ArrayList<File>();

    File directory = new File(fileSet.getDirectory());
    String includes = toString(fileSet.getIncludes());
    String excludes = toString(fileSet.getExcludes());

    for (Object each : FileUtils.getFiles(directory, includes, excludes)) {
      if (each instanceof File) {
        File file = (File) each;
        if (buildContext.hasDelta(file)) {
          result.add(file);
        }
      }
    }
    return result;
  }
  
  public static List<File> getFilesIfModified(FileSet fileSet, BuildContext buildContext) throws IOException {
    List<File> result = new ArrayList<File>();

    File directory = new File(fileSet.getDirectory());
    String includes = toString(fileSet.getIncludes());
    String excludes = toString(fileSet.getExcludes());

    boolean modified = false;
    for (Object each : FileUtils.getFiles(directory, includes, excludes)) {
      if (each instanceof File) {
        File file = (File) each;
        result.add(file);
        if (buildContext.hasDelta(file)) {
          modified = true;
        }
      }
    }
    if(modified) {
      return result;
    } else {
      return Collections.emptyList();
    }
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
