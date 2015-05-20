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

package org.apache.uima.ruta.cde.utils;

import java.io.File;
import java.io.FileReader;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class ConstraintXMLUtils {

  public static void writeConstraints(String location, List<ConstraintData> constraints)
          throws Exception {
    StringBuilder sb = new StringBuilder();
    sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    sb.append("\n");
    sb.append("<constraints>");
    sb.append("\n");
    for (ConstraintData eachConstraint : constraints) {
      sb.append(eachConstraint.toXML());
      sb.append("\n");
    }
    sb.append("</constraints>");
    FileUtils.writeStringToFile(new File(location), sb.toString(), "UTF-8");
  }

  public static List<ConstraintData> readConstraints(String location) throws Exception {
    XMLReader xmlReader = XMLReaderFactory.createXMLReader();
    FileReader reader = new FileReader(location);
    InputSource inputSource = new InputSource(reader);
    ConstraintContentHandler handler = new ConstraintContentHandler();
    xmlReader.setContentHandler(handler);
    xmlReader.parse(inputSource);
    return handler.getConstraints();
  }
}
