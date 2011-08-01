package org.apache.uima.tm.textmarker.resource;

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
