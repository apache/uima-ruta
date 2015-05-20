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

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class CheckDocumentsContentHandler extends DefaultHandler {

  private List<CheckDocument> checkDocuments = new ArrayList<CheckDocument>();
  

  private CheckDocument current = null;

  private String currentString;

  public void startElement(String uri, String localName, String qName, Attributes attributes) {
    if (localName.equals("document")) {
      String source = attributes.getValue("source");
      current = new CheckDocument(source);
    } 
  }

  public void characters(char[] ch, int start, int length) {
    currentString = new String(ch, start, length);
  }

  public void endElement(String uri, String localName, String qName) {
    if (localName.equals("document")) {
      checkDocuments.add(current);
    } else if (localName.equals("type")) {
      current.checkedTypes.add(currentString);
    }
  }
  
  public List<CheckDocument> getCheckDocuments() {
    return checkDocuments;
  }
}
