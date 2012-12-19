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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.StringArray;
import org.htmlparser.Attribute;
import org.htmlparser.Node;
import org.htmlparser.Remark;
import org.htmlparser.Tag;
import org.htmlparser.visitors.NodeVisitor;

public class HtmlVisitor extends NodeVisitor {

  private CAS cas;

  private List<AnnotationFS> annotations = new ArrayList<AnnotationFS>();

  private LinkedList<AnnotationFS> annotationStack = new LinkedList<AnnotationFS>();

  private JCas jcas;

  private boolean onlyContent;

  public HtmlVisitor(JCas jcas, boolean onlyContent) {
    super();
    this.jcas = jcas;
    this.cas = jcas.getCas();
    this.onlyContent = onlyContent;
  }

  public void visitTag(Tag tag) {
    String name = getName(tag);
    Type type = getType(name, cas);
    boolean tagClosed = isTagClosed(tag);
    boolean tagStillOpen = false;
    int begin = getBeginOffset(tag);
    int end = begin;
    if (tagClosed) {
      end = getEndOffset(tag);
    } else {
      Tag endTag = tag.getEndTag();
      if (endTag != null) {
        end = getEndOffset(endTag);
      } else {
        end = getEndOffset(tag);
        tagStillOpen = true;
      }
    }
    AnnotationFS annotation = cas.createAnnotation(type, begin, end);
    processAttributes(annotation, tag);
    Feature nameFeature = annotation.getType().getFeatureByBaseName("name");
    annotation.setStringValue(nameFeature, name);
    if (tagStillOpen) {
      annotationStack.add(annotation);
    } else {
      annotations.add(annotation);
    }
  }

  public void visitRemarkNode(Remark node) {
    Type type = getType("REMARK", cas);
    int begin = getBeginOffset(node);
    int end = getEndOffset(node);
    AnnotationFS annotation = cas.createAnnotation(type, begin, end);
    Feature feature = type.getFeatureByBaseName("comment");
    annotation.setStringValue(feature, node.getText());
    annotations.add(annotation);
  }

  public void visitEndTag(Tag tag) {
    String name = getName(tag);
    Iterator<AnnotationFS> descendingIterator = annotationStack.descendingIterator();
    AnnotationFS found = null;
    while (descendingIterator.hasNext()) {
      AnnotationFS each = (AnnotationFS) descendingIterator.next();
      Feature nameFeature = each.getType().getFeatureByBaseName("name");
      String eachName = each.getStringValue(nameFeature);
      if (name.equals(eachName)) {
        int endOffset = getEndOffset(tag);
        Feature endFeature = each.getType().getFeatureByBaseName("end");
        each.setIntValue(endFeature, endOffset);
        found = each;
        break;
      }
    }
    if (found != null) {
      annotationStack.remove(found);
      annotations.add(found);
    }

  }

  private boolean isTagClosed(Tag tag) {
    return tag.getRawTagName().endsWith("/");
  }

  private String getName(Tag tag) {
    String result = tag.getRawTagName().toUpperCase();
    if (result.endsWith("/")) {
      result = result.substring(0, result.length() - 1);
    }
    if (result.startsWith("/")) {
      result = result.substring(1);
    }
    return result;
  }

  private Type getType(String name, CAS cas) {
    String typeName = HtmlAnnotator.NAMESPACE + name;
    Type type = cas.getTypeSystem().getType(typeName);
    if (type == null) {
      type = cas.getTypeSystem().getType(HtmlAnnotator.NAMESPACE + "TAG");
    }
    return type;
  }

  private int getBeginOffset(Node tag) {
    if (onlyContent) {
      return tag.getEndPosition();
    } else {
      return tag.getStartPosition();
    }

  }

  private int getEndOffset(Node tag) {
    if (onlyContent) {
      return tag.getStartPosition();
    } else {
      return tag.getEndPosition();
    }
  }

  private void processAttributes(AnnotationFS annotation, Tag tag) {
    int size = tag.getAttributesEx().size() - 1;
    StringArray attributeName = new StringArray(jcas, size);
    StringArray attributeValue = new StringArray(jcas, size);
    for (int i = 0; i < size; i++) {
      Attribute attribute = (Attribute) tag.getAttributesEx().elementAt(i + 1);
      attributeName.set(i, attribute.getName());
      attributeValue.set(i, attribute.getValue());
    }
    Feature feature1 = annotation.getType().getFeatureByBaseName("attributeName");
    annotation.setFeatureValue(feature1, attributeName);
    Feature feature2 = annotation.getType().getFeatureByBaseName("attributeValue");
    annotation.setFeatureValue(feature2, attributeValue);
  }

  public List<AnnotationFS> getAnnotations() {
    return annotations;
  }

  public LinkedList<AnnotationFS> getAnnotationStack() {
    return annotationStack;
  }

  //
  //
  // public void tag2annotation(Tag tag, String name, CAS cas) {
  // Type type = getType(name, cas);
  // if (name.endsWith("/")) {
  // name = name.substring(0, name.length() - 1);
  // }
  // if (name.equals("STYLE")) {
  // tag.setEndPosition(tag.getEndTag().getStartPosition());
  // }
  // int begin = getOffset(tag.getStartPosition());
  //
  // delta += tag.getEndPosition() - tag.getStartPosition();
  // int i;
  // int end = tag.getEndPosition();
  // if (tag.getEndTag() != null) {
  // i = tag.getEndTag().getStartPosition();
  // end = tag.getEndTag().getEndPosition();
  // } else {
  // // TODO fix this
  // i = tag.getEndPosition();
  // tag.toString();
  // String startTag = "<" + tag.getRawTagName();
  // String endTag = "</" + tag.getRawTagName() + ">";
  // int j = i - 1;
  // int k = i - 2;
  // while (k < j && k > -1) {
  // k = cas.getDocumentText().indexOf(startTag, k + 1);
  // j = cas.getDocumentText().indexOf(endTag, j + 1);
  // }
  // if (i < j) {
  // i = j;
  // }
  //
  // }
  //
  // if (onlyContent) {
  // begin = getOffset(tag.getEndPosition());
  // end = i;
  // }
  // AnnotationFS annotation = cas.createAnnotation(type, begin, end);
  // for (int k = 0; k < tag.getAttributesEx().size(); k++) {
  // String test = tag.getAttributesEx().elementAt(k).toString();
  // if (test.startsWith(" ") | test.startsWith("/")) {
  // tag.getAttributesEx().remove(k);
  // k--;
  // }
  // }
  // if (name.equals("STYLE") || name.equals("TITLE") || name.equals("SCRIPT")) {
  // annotation.setFeatureValueFromString(annotation.getType().getFeatureByBaseName("name"),
  // tag.getText());
  // } else {
  // annotation.setFeatureValueFromString(annotation.getType().getFeatureByBaseName("name"), name);
  // }
  //
  // StringArray attributeName;
  // StringArray attributeValue;
  // int size = tag.getAttributesEx().size() - 1;
  // attributeName = new StringArray(jcas, size);
  // attributeValue = new StringArray(jcas, size);
  // for (int j = 0; j < size; j++) {
  // Attribute attribute = (Attribute) tag.getAttributesEx().elementAt(j + 1);
  // attributeName.set(j, attribute.getName());
  // attributeValue.set(j, attribute.getValue());
  // }
  // if (i <= tag.getEndPosition()) {
  // HtmlAnnotation annotationDelta = new HtmlAnnotation(annotation, delta, attributeName,
  // attributeValue, name, stripHtml);
  // annotationList.add(annotationDelta);
  // } else {
  // HtmlAnnotation annotationDelta = new HtmlAnnotation(annotation, delta, attributeName,
  // attributeValue, name, stripHtml);
  // annotations.add(annotationDelta);
  // }
  // }
  //
  // private int getOffset(int offset) {
  // if (stripHtml) {
  // return offset - delta;
  // }
  // return offset;
  // }
  //
  // public void setDelta(Tag tag, String name, CAS cas) {
  // delta = tag.getStartPosition();
  // plainTextDocument = plainTextDocument.substring(delta);
  // tag2annotation(tag, name, cas);
  // }
  //
  // public void head2annotation(Tag tag, String name, CAS cas) {
  // Type type = getType(name, cas);
  // if (name.equals("STYLE")) {
  // tag.setEndPosition(tag.getEndTag().getStartPosition());
  // }
  // int begin = getOffset(tag.getStartPosition());
  //
  // delta += tag.getEndPosition() - tag.getStartPosition();
  // int i;
  // int end = tag.getEndPosition();
  // if (tag.getEndTag() != null) {
  // i = tag.getEndTag().getStartPosition();
  // end = tag.getEndTag().getEndPosition();
  // } else {
  // // TODO fix this
  // i = tag.getEndPosition();
  // String startTag = "<" + tag.getRawTagName();
  // String endTag = "</" + tag.getRawTagName() + ">";
  // int j = i - 1;
  // int k = i - 2;
  // while (k < j && k > -1) {
  // k = cas.getDocumentText().indexOf(startTag, k + 1);
  // j = cas.getDocumentText().indexOf(endTag, j + 1);
  // }
  // if (i < j) {
  // i = j;
  // }
  //
  // }
  //
  // if (onlyContent) {
  // begin = getOffset(tag.getEndPosition());
  // end = i;
  // }
  //
  // AnnotationFS annotation = cas.createAnnotation(type, begin, end);
  // for (int k = 0; k < tag.getAttributesEx().size(); k++) {
  // String test = tag.getAttributesEx().elementAt(k).toString();
  // if (test.startsWith(" ") | test.startsWith("/")) {
  // tag.getAttributesEx().remove(k);
  // k--;
  // }
  // }
  // if (tag.getEndTag() != null && !name.equals("HEAD")) {
  // name = plainTextDocument.substring(tag.getEndPosition(), tag.getEndTag().getStartPosition());
  // }
  // StringArray attributeName;
  // StringArray attributeValue;
  // int size = tag.getAttributesEx().size() - 1;
  // attributeName = new StringArray(jcas, size);
  // attributeValue = new StringArray(jcas, size);
  // for (int j = 0; j < size; j++) {
  // Attribute attribute = (Attribute) tag.getAttributesEx().elementAt(j + 1);
  // attributeName.set(j, attribute.getName());
  // attributeValue.set(j, attribute.getValue());
  // }
  // HtmlAnnotation annotationDelta = new HtmlAnnotation(annotation, 0, attributeName,
  // attributeValue, name, stripHtml);
  // annotationList.add(annotationDelta);
  //
  // }
  //
  // public void node2annotation(Remark tag) {
  // Type type = getType("REMARK", cas);
  // int begin = getOffset(tag.getStartPosition());
  //
  // delta += tag.getEndPosition() - tag.getStartPosition();
  // int end = tag.getEndPosition();
  //
  // if (onlyContent) {
  // begin = getOffset(tag.getEndPosition());
  // }
  // AnnotationFS annotation = cas.createAnnotation(type, begin, end);
  // HtmlRemark remark = new HtmlRemark(annotation, 0, stripHtml);
  // String comment = tag.getText();
  // remark.setComment(comment);
  // remarks.add(remark);
  // }
  //
  // public void headNode2annotation(Remark tag) {
  // Type type = getType("REMARK", cas);
  // int begin = getOffset(tag.getStartPosition());
  //
  // delta += tag.getEndPosition() - tag.getStartPosition();
  // int end = tag.getEndPosition();
  // if (onlyContent) {
  // begin = getOffset(tag.getEndPosition());
  // }
  //
  // AnnotationFS annotation = cas.createAnnotation(type, begin, end);
  //
  // HtmlRemark remark = new HtmlRemark(annotation, 0, stripHtml);
  // String comment = tag.getText();
  // remark.setComment(comment);
  // remarks.add(remark);
  // }
  //
  // public void endTag(Tag endtag) {
  // int size = annotations.size();
  // if (size > 0) {
  // HtmlAnnotation annotationDelta = annotations.get(size - 1);
  // String test = endtag.getRawTagName().toUpperCase().substring(1);
  //
  // if (annotationDelta
  // .getAnnotation()
  // .getFeatureValueAsString(
  // annotationDelta.getAnnotation().getType().getFeatureByBaseName("name"))
  // .equals(test)) {
  // annotationDelta.changeDelta(delta);
  // annotationList.add(annotationDelta);
  // annotations.remove(size - 1);
  // }
  // }
  // delta += endtag.getEndPosition() - endtag.getStartPosition();
  // }
  //
  // public String getPlainTextDocument() {
  // return plainTextDocument;
  // }
  //
  // public List<HtmlAnnotation> getAnnotationList() {
  // return annotationList;
  // }
  //
  // public List<HtmlRemark> getRemarks() {
  // return remarks;
  // }

}
