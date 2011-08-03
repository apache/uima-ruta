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

package org.apache.uima.tm.textmarker.batch;

import java.io.File;

import org.apache.uima.tm.textmarker.resource.TreeWordList;


public class TextMarkerXMLConverter {

  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("Please add paths");
    } else {
      File dir = new File(args[0]);
      // TextMarkerVersionConverter converter = new
      // TextMarkerVersionConverter();
      // for (File file : dir.listFiles(new FilenameFilter() {
      //
      // @Override
      // public boolean accept(File dir, String name) {
      // return !name.endsWith("tm");
      // }
      //			
      // })) {
      // File output = new File(file.getParentFile(), file.getName() +
      // ".tm");
      // try {
      // converter.convertOldToNew(file, output);
      // } catch (IOException e) {
      // e.printStackTrace();
      // }
      // }

      for (File file : dir.listFiles()) {

        String path = file.getAbsolutePath();

        if (path.endsWith("txt")) {
          TreeWordList list = new TreeWordList(file.getAbsolutePath());
          String newFilePath = path.substring(0, path.length() - 3) + "twl";
          System.out.println(newFilePath);
          list.createXMLFile(newFilePath, "UTF-8");
        }
      }
    }
  }
}
