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

package org.apache.uima.ruta.check;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.uima.internal.util.XMLUtils;
import org.apache.uima.util.FileUtils;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class CheckDocumentXMLUtils {

  public static void write(List<CheckDocument> docs, File file) throws IOException {
    StringBuilder sb = new StringBuilder();
    sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    sb.append("\n");
    sb.append("<documents>");
    sb.append("\n");
    for (CheckDocument checkDocument : docs) {
      sb.append(checkDocument.toXML());
    }
    sb.append("</documents>");
    FileUtils.saveString2File(sb.toString(), file, "UTF-8");
  }

  public static List<CheckDocument> read(File file) throws SAXException, IOException {
    if (file == null || !file.exists()) {
      return new ArrayList<CheckDocument>();
    }

    try (FileReader reader = new FileReader(file)) {
      InputSource inputSource = new InputSource(reader);
      CheckDocumentsContentHandler handler = new CheckDocumentsContentHandler();
      XMLReader xmlReader = XMLUtils.createXMLReader();
      xmlReader.setContentHandler(handler);
      xmlReader.parse(inputSource);
      return handler.getCheckDocuments();
    }
  }

}
