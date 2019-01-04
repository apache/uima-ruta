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

package org.apache.uima.ruta.engine;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.htmlparser.Tag;
import org.htmlparser.Text;
import org.htmlparser.tags.ScriptTag;
import org.htmlparser.visitors.TextExtractingVisitor;

public class HtmlConverterVisitor extends TextExtractingVisitor {

  private boolean inBody = false;

  private boolean inScript = false;

  private boolean skipWhitespace = true;

  private SortedSet<HtmlConverterPSpan> textSpans = new TreeSet<HtmlConverterPSpan>();

  private SortedSet<HtmlConverterPSpan> linebreaksFromHtmlTags = new TreeSet<HtmlConverterPSpan>();

  private SortedSet<HtmlConverterPSpan> gapsFromHtmlTags = new TreeSet<HtmlConverterPSpan>();

  private Collection<String> newlineInducingTags;

  private boolean processAll = true;

  private List<String> gapInducingTags;

  private String gapText;

  private Pattern newlineInducingTagPattern;

  public HtmlConverterVisitor(String[] newlineInducingTags, String newlineInducingTagRegExp,
          String[] gapInducingTags, String gapText, boolean skipWhitespace, boolean processAll) {
    if (newlineInducingTags != null) {
      this.newlineInducingTags = Arrays.asList(newlineInducingTags);
    }
    if (gapInducingTags != null) {
      this.gapInducingTags = Arrays.asList(gapInducingTags);
    }
    this.gapText = gapText;
    this.skipWhitespace = skipWhitespace;
    this.processAll = processAll;
    if (newlineInducingTagRegExp != null) {
      newlineInducingTagPattern = Pattern.compile(newlineInducingTagRegExp);
    }
  }

  @Override
  public void visitStringNode(Text node) {
    super.visitStringNode(node);
    if ((processAll || this.inBody) && !this.inScript
            && (!skipWhitespace || !StringUtils.isBlank(node.getText()))) {
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
      inBody = true;
    } else if (trimmedTagnameLowercase.equals("script")) {
      inScript = true;
    }
    boolean matchedByPattern = false;
    if (newlineInducingTagPattern != null) {
      Matcher matcher = newlineInducingTagPattern.matcher(trimmedTagnameLowercase);
      if (matcher.matches()) {
        matchedByPattern = true;
      }
    }
    if (matchedByPattern
            || (newlineInducingTags != null && newlineInducingTags
                    .contains(trimmedTagnameLowercase))) {
      int begin = tag.getStartPosition();
      linebreaksFromHtmlTags.add(new HtmlConverterPSpanReplacement(begin, begin + 1,
              HtmlConverter.LINEBREAK));
    }
    if (gapInducingTags != null && gapInducingTags.contains(trimmedTagnameLowercase)) {
      int begin = tag.getStartPosition();
      gapsFromHtmlTags.add(new HtmlConverterPSpanReplacement(begin, begin + gapText.length(),
              gapText));
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

  public SortedSet<HtmlConverterPSpan> getGapsFromHtmlTags() {
    return gapsFromHtmlTags;
  }
}