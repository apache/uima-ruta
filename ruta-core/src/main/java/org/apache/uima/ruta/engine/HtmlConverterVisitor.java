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

package org.apache.uima.textmarker.engine;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.htmlparser.Tag;
import org.htmlparser.Text;
import org.htmlparser.tags.ScriptTag;
import org.htmlparser.visitors.TextExtractingVisitor;

/**
 * TODO comment / describe <br>
 * TODO write test(s)
 * 
 */
public class HtmlConverterVisitor extends TextExtractingVisitor {

  private boolean inBody = false;

  private boolean inScript = false;

  private boolean skipWhitespace = true;

  private SortedSet<HtmlConverterPSpan> textSpans = new TreeSet<HtmlConverterPSpan>();

  private SortedSet<HtmlConverterPSpan> linebreaksFromHtmlTags = new TreeSet<HtmlConverterPSpan>();

  private Set<String> newlineInducingTags;

  public HtmlConverterVisitor(Set<String> newlineInducingTags) {
    this.newlineInducingTags = newlineInducingTags;
  }

  @Override
  public void visitStringNode(Text node) {
    super.visitStringNode(node);
    if (this.inBody && !this.inScript && (!skipWhitespace || !StringUtils.isBlank(node.getText()))) {
      int from = node.getStartPosition();
      int to = node.getEndPosition();
      textSpans.add(new HtmlConverterPSpan(from, to, node.getText()));
    }
  }

  @Override
  public void visitTag(Tag tag) {
    super.visitTag(tag);
    String trimmedTagnameLowercase = tag.getTagName().toLowerCase().trim();
    if (trimmedTagnameLowercase.equals("body")) {
      this.inBody = true;
    } else if (trimmedTagnameLowercase.equals("script")) {
      this.inScript = true;
    }
    if (this.newlineInducingTags.contains(trimmedTagnameLowercase)) {
      int begin = tag.getStartPosition();
      this.linebreaksFromHtmlTags.add(new HtmlConverterPSpanReplacement(begin, begin + 1,
              HtmlConverter.LINEBREAK));
    }
  }

  @Override
  public void visitEndTag(Tag tag) {
    String tagname = tag.getTagName().toLowerCase().trim();
    if (tagname.equals("body")) {
      this.inBody = false;
    } else if (tagname.equals("script") || tag instanceof ScriptTag) {
      this.inScript = false;
    }
  }

  public SortedSet<HtmlConverterPSpan> getTextSpans() {
    return textSpans;
  }

  public SortedSet<HtmlConverterPSpan> getLinebreaksFromHtmlTags() {
    return linebreaksFromHtmlTags;
  }
}