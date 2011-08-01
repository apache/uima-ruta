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
