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

import java.util.ArrayList;
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
    AnnotationFS found = null;
    for (int i = annotationStack.size() - 1; i >= 0; i--) {
      AnnotationFS each = (AnnotationFS) annotationStack.get(i);
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

}
