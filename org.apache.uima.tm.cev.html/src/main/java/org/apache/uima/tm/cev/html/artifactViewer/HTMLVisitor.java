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

package org.apache.uima.tm.cev.html.artifactViewer;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import org.htmlparser.visitors.NodeVisitor;

public class HTMLVisitor extends NodeVisitor {

  // Map with HTML-ID and positions of the HTML-Tags
  // int[0] startpos of the opening tag
  // int[1] endpos of the opening tag
  // int[3] startpos of the end tag
  // int[4] endpos of the end tag
  private LinkedHashMap<String, int[]> idPosMap;

  private int idCount;

  private String idName = "CAS_VIEWER_ID";

  private LinkedList<String> tagStack;

  private LinkedList<String> idStack;

  public HTMLVisitor(String html) {
    idPosMap = new LinkedHashMap<String, int[]>();
    idCount = 0;

    while (html.indexOf(idName) > -1)
      idName += "_";
  }

  public LinkedHashMap<String, int[]> getIDPosMap() {
    return idPosMap;
  }

  public String getIDName() {
    return idName;
  }

  public int getIdCount() {
    return idCount;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.htmlparser.visitors.NodeVisitor#beginParsing()
   */
  @Override
  public void beginParsing() {
    tagStack = new LinkedList<String>();
    idStack = new LinkedList<String>();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.htmlparser.visitors.NodeVisitor#visitTag(org.htmlparser.Tag)
   */
  @Override
  public void visitTag(org.htmlparser.Tag tag) {
    String id = tag.getAttribute("ID");

    String tagName = tag.getTagName();
    // TODO: added isIdTag
    if (id == null && isIdTag(tagName)) {
      id = idName + Integer.toString(idCount++);
      tag.setAttribute("ID", id);
    }

    if (id != null) {
      idPosMap.put(id, new int[] { tag.getStartPosition(), tag.getEndPosition(), 0, 0 });

      tagStack.addFirst(tag.getTagName());
      idStack.addFirst(id);
    }
  }

  private boolean isIdTag(String tagName) {
    if (tagName.startsWith("!"))
      return false;
    String lowerCase = tagName.toLowerCase();
    if (lowerCase.equals("base") || lowerCase.equals("head") || lowerCase.equals("html")
            || lowerCase.equals("meta") || lowerCase.equals("script") || lowerCase.equals("style")
            || lowerCase.equals("title"))
      return false;
    return true;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.htmlparser.visitors.NodeVisitor#visitEndTag(org.htmlparser.Tag)
   */
  @Override
  public void visitEndTag(org.htmlparser.Tag tag) {

    if (isIdTag(tag.getTagName())) {
      while (true) {
        if (tagStack.isEmpty()) {
          System.out.println(tag.getTagName() + " : " + tag.getText() + " : " + tag.toString());
        }
        if (tagStack.getFirst().equals(tag.getTagName())) {
          break;
        }
        tagStack.removeFirst();
        idStack.removeFirst();
      }
      tagStack.removeFirst();
      int[] pos = idPosMap.get(idStack.removeFirst());
      pos[2] = tag.getStartPosition();
      pos[3] = tag.getEndPosition();
    }

  }

}
