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

package org.apache.uima.textmarker.resource;

import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class XMLEventHandler extends DefaultHandler {

  Stack<TextNode> stack;

  public XMLEventHandler(TextNode root) {
    super();
    stack = new Stack<TextNode>();
    stack.add(root);
  }

  @Override
  public void startDocument() {
  }

  @Override
  public void endDocument() {
  }

  @Override
  public void startElement(String namespaceURI, String localName, String qualifiedName,
          Attributes atts) {
    if ("node".equals(localName) || "node".equals(qualifiedName)) {
      char c = atts.getValue("char").charAt(0);
      boolean isWordEnd = Boolean.valueOf(atts.getValue("isWordEnd"));
      TextNode newNode = new TextNode(c, isWordEnd);
      stack.peek().addChild(newNode);
      stack.add(newNode);
    }

  }

  @Override
  public void endElement(String namespaceURI, String localName, String qualifiedName) {
    if ("node".equals(localName) || "node".equals(qualifiedName)) {
      stack.pop();
    }
  }

}
