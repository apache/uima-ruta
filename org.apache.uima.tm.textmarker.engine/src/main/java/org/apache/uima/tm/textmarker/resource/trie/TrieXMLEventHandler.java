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

package org.apache.uima.tm.textmarker.resource.trie;

import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class TrieXMLEventHandler extends DefaultHandler {

  private Stack<MultiTextNode> stack;

  // boolean listeningState;

  boolean inContent;

  boolean inType;

  public TrieXMLEventHandler(MultiTextNode root) {
    super();
    this.stack = new Stack<MultiTextNode>();
    stack.add(root);
    // this.listeningState = false;
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
    if ("n".equals(localName) || "n".equals(qualifiedName)) {
      // char c = atts.getValue("c").charAt(0);
      // boolean e = Boolean.valueOf(atts.getValue("e"));
      MultiTextNode newNode = new MultiTextNode();
      // newNode.setWordEnd(e);
      // stack.peek().addChild(newNode);
      stack.add(newNode);
      inContent = false;
      inType = false;
    }
    if ("t".equals(localName) || "t".equals(qualifiedName)) {
      inType = true;
      inContent = false;
    }
    if ("c".equals(localName) || "c".equals(qualifiedName)) {
      // listeningState = true;
      inType = false;
      inContent = true;
    }
  }

  @Override
  public void endElement(String namespaceURI, String localName, String qualifiedName) {
    if ("n".equals(localName) || "n".equals(qualifiedName)) {
      MultiTextNode pop = stack.pop();
      stack.peek().addChild(pop);
    }
    if ("t".equals(localName) || "t".equals(qualifiedName)) {
      inType = false;
    }
    if ("c".equals(localName) || "c".equals(qualifiedName)) {
      inContent = false;
    }
  }

  @Override
  public void characters(char ch[], int start, int length) {
    // if (listeningState == true) {
    if (stack.isEmpty()) {
      return;
    }
    MultiTextNode peek = stack.peek();
    if (inType) {
      StringBuilder type = new StringBuilder();
      for (int i = start; i < start + length; i++) {
        type.append(String.valueOf(ch[i]));
      }
      peek.addType(type.toString());
    } else if (inContent) {
      if (ch.length > 0) {
        peek.setValue(ch[0]);
      } else {
        peek.setValue(' ');
      }
    }
    // }

  }
}
