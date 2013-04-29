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

package org.apache.uima.textmarker.ide.ui.documentation;

import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.Tag;
import org.htmlparser.visitors.NodeVisitor;

public class HtmlDocumentationVisitor extends NodeVisitor {

  private TreeMap<String, String> map;

  private int divDepth = 0;

  private int elementStart = 0;

  private String document;
  
  public HtmlDocumentationVisitor(String document) {
    super();
    this.document = document;
    this.map = new TreeMap<String, String>();
  }
  
  public void visitTag(Tag tag) {
    String name = tag.getTagName().toLowerCase();
    if("div".equals(name)) {
      divDepth++;
      if(divDepth == 1) {
        elementStart  = tag.getStartPosition();
      }
    }
    
  }
  public void visitEndTag(Tag tag) {
    String name = tag.getTagName().toLowerCase();
    if("div".equals(name)) {
      if(divDepth == 1) {
        String section = document.substring(elementStart, tag.getEndPosition());
        processSection(section);
      }
      divDepth--;
    }
    
  }
  
  private void processSection(String section) {
    Pattern pattern = Pattern.compile("title=\"\\d+\\.\\d+\\.\\d+\\.&nbsp;(\\p{Upper}+)\"");
    Matcher matcher = pattern.matcher(section);
    boolean found = matcher.find();
    if(found) {
      String group = matcher.group(1);
      section = section.trim();
      section = section.replaceAll("</?a.*>", "");
      section = section.replaceAll("\\d+\\.\\d+\\.\\d+.\\d+\\.&nbsp;", "");
      section = section.replaceAll("\\d+\\.\\d+\\.\\d+.&nbsp;", "");
      map.put(group, section);
    } 
  }

  public Map<String, String> getMap() {
    return map;
  }

}
