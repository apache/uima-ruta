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

package org.apache.uima.tm.textmarker.resource;

import java.util.HashMap;
import java.util.Map;

public class TextNode {

  private char value;

  private boolean isWordEnd;

  private Map<Character, TextNode> children;

  public TextNode() {
    this.value = ' ';
    this.isWordEnd = false;
    this.children = new HashMap<Character, TextNode>();
  }

  public TextNode(char value, boolean isWordEnd) {
    this.value = value;
    this.isWordEnd = isWordEnd;
    this.children = new HashMap<Character, TextNode>(5);
  }

  public boolean contains(char c) {
    if (value == c) {
      return true;
    }
    return false;
  }

  public void addChild(char c, boolean isWordEnd) {
    children.put(c, new TextNode(c, isWordEnd));
  }

  public void addChild(TextNode n) {
    children.put(n.getValue(), n);
  }

  public void setWordEnd(boolean b) {
    this.isWordEnd = b;
  }

  public TextNode getChildNode(char c) {
    return children.get(c);
  }

  public char getValue() {
    return this.value;
  }

  public boolean isWordEnd() {
    return this.isWordEnd;
  }

  public Map<Character, TextNode> getChildren() {
    return this.children;
  }
}
