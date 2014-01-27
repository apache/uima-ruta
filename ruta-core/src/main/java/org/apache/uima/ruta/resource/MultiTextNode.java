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

package org.apache.uima.ruta.resource;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Class MultipleTextNode.
 * 
 * 
 * Represents a node of the data structure MultiTreeWordList.
 */
public class MultiTextNode {

  /** The Character represented by the node. */
  private char value;

  // /** Indicates whether this is a word end. */
  // private boolean isWordEnd;

  /** The Map of child nodes. */
  private Map<Character, MultiTextNode> children;

  /** Documents/Types that contain the value specified by the super class. */
  private Set<String> types;

  private boolean isWordEnd = false;
  
  private static final int DEFAUL_INITIAL_CAPACITY = 5;

  /** Default constructor uses a space as value. */
  public MultiTextNode() {
    this(' ', false);
  }

  /**
   * Constructs a MultiTextNode with a character and the information, whether this is a word end or
   * not.
   * 
   * @param value
   *          The Character represented by the node.
   * @param isWordEnd
   *          Indicates whether this is a word end.
   */
  public MultiTextNode(char value, boolean isWordEnd) {
    this(value, isWordEnd, DEFAUL_INITIAL_CAPACITY);
  }

  /**
   * Constructs a MultiTextNode with a character and the information, whether this is a word end or
   * not. Documents are the documents, where the word, represented by the path from the root to this
   * node, occurs.
   * 
   * @param value
   *          The Character represented by the node.
   * @param isWordEnd
   *          Indicates whether this is a word end.
   * @param capacity
   *          The initial capacity of the map of child nodes.
   */
  private MultiTextNode(char value, boolean isWordEnd, int capacity) {
    this.value = value;
    this.children = new HashMap<Character, MultiTextNode>(capacity);
    setWordEnd(isWordEnd);
  }

  /**
   * Adds the MultiTextNode n to the map of children.
   * 
   * @param n
   *          The MultiTextNode, which should be added.
   * @return The previous value associated with the value of n, if exists, null otherwise.
   */
  public MultiTextNode addChild(MultiTextNode n) {
    if (children == null) {
      children = new HashMap<Character, MultiTextNode>();
    }
    return children.put(n.getValue(), n);
  }

  
  /**
   * Returns the child, when you follow the c-edge, if exists. Returns null otherwise.
   * 
   * @param c
   *          The edge which is chosen.
   * @return The child of the c-edge, if exists, null otherwise.
   */
  public MultiTextNode getChildNode(char c) {

    // Remember Lazy Initialization.
    if (children == null) {
      return null;
    }

    return children.get(c);
  }

  /**
   * Returns the value of the MultiTextNode.
   * 
   * @return The value of the MultiTextNode.
   */
  public char getValue() {
    return this.value;
  }

  public void setValue(char c) {
    this.value = c;
  }

  /**
   * Returns a value which specifies, whether the word is finished or not.
   * 
   * @return A value which specifies, whether the word is finished or not.
   */
  public boolean isWordEnd() {
    return isWordEnd;
  }

  /**
   * Returns the map of children as an unmodifiable map.
   * 
   * @return the map of children as an unmodifiable map.
   */
  public Map<Character, MultiTextNode> getChildren() {

    // Remember Lazy Initialization.
    if (children == null) {
      return Collections.unmodifiableMap(new HashMap<Character, MultiTextNode>());
    }

    return Collections.unmodifiableMap(children);
  }

  /**
   * Adds the string that specifies a document to the list of sources.
   * 
   * @param type
   *          The string that specifies a document.
   * @return True, if the sources did not already contain the string document, false otherwise.
   */
  public boolean addType(String type) {
    if (types == null) {
      types = new HashSet<String>();
    }
    return types.add(type);
  }

  /**
   * Returns an unmodifiable Collection containing the types.
   * 
   * @return An unmodifiable Collection containing the types.
   */
  public Collection<String> getTypes() {

    // Remember Lazy Initialization.
    if (types == null) {
      return Collections.emptyList();
    }
    return types;
  }

  /**
   * Sets isWordEnd to the specified boolean.
   * 
   * @param b
   *          The boolean, isWordEnd is set to.
   */
  public void setWordEnd(boolean b) {
    isWordEnd = b;
  }

  /**
   * Returns a String representation of the MultiTextNode.
   * 
   * @return A String representation of the MultiTextNode.
   */
  @Override
  public String toString() {

    StringBuilder s = new StringBuilder(Character.toString(getValue()) + "\n");

    if (getChildren() != null) {

      List<Character> sortedList = new LinkedList<Character>();
      sortedList.addAll(getChildren().keySet());
      Collections.sort(sortedList);

      for (Character each : sortedList) {
        s.append(each + ", ");
      }
    }

    return s.toString();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((children == null) ? 0 : children.hashCode());
    // result = prime * result + (isWordEnd() ? 1231 : 1237);
    result = prime * result + ((types == null) ? 0 : getTypes().hashCode());
    result = prime * result + value;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    MultiTextNode other = (MultiTextNode) obj;
    if (children == null) {
      if (other.children != null)
        return false;
      // } else if (!childrenEquals(other))
    } else if (!children.equals(other.getChildren()))
      return false;
    // if (isWordEnd != other.isWordEnd())
    // return false;
    if (types == null) {
      if (other.types != null)
        return false;
    } else if (!typesEquals(other))
      // } else if (!sources.equals(other.getSources()))
      return false;
    if (value != other.getValue())
      return false;
    return true;
  }

  /**
   * Returns true, if the two MultiTextNodes have the same sources. That's true, when the sources
   * contain the same strings.
   * 
   * @param other
   *          The MultiTextNode, which is compared to this one.
   * @return true, if if the two MultiTextNodes have the same sources, false otherwise.
   */
  private boolean typesEquals(MultiTextNode other) {

    if (getTypes() == null) {
      return other.getTypes() == null;
    }

    for (String s : getTypes()) {
      if (!other.getTypes().contains(s)) {
        return false;
      }
    }

    for (String s : other.getTypes()) {
      if (!getTypes().contains(s)) {
        return false;
      }
    }

    return true;
  }

}
