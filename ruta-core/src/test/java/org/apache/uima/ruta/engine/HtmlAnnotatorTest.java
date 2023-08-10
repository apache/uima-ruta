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

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.net.URL;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.XMLInputSource;
import org.junit.jupiter.api.Test;

public class HtmlAnnotatorTest {

  @Test
  public void test() throws Exception {
    String namespace = this.getClass().getPackage().getName().replaceAll("\\.", "/");
    String name = namespace + "/" + "test.html";
    URL textURL = HtmlAnnotatorTest.class.getClassLoader().getResource(name);
    File textFile = new File(textURL.toURI());
    String html = FileUtils.file2String(textFile, "UTF-8");
    URL url = HtmlAnnotator.class.getClassLoader().getResource("HtmlAnnotator.xml");
    if (url == null) {
      url = HtmlAnnotator.class.getClassLoader()
              .getResource("org/apache/uima/ruta/engine/HtmlAnnotator.xml");
    }
    XMLInputSource in = new XMLInputSource(url);
    ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);
    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(specifier);
    CAS cas = ae.newCAS();
    Type tagType = cas.getTypeSystem().getType(HtmlAnnotator.NAMESPACE + "TAG");
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;

    ae.setConfigParameterValue("onlyContent", false);
    ae.reconfigure();
    cas.reset();
    cas.setDocumentText(html);
    ae.process(cas);
    ai = cas.getAnnotationIndex(tagType);
    iterator = ai.iterator();
    assertThat(ai.size()).isEqualTo(7);
    AnnotationFS a = iterator.next();
    assertThat(a.getType().getShortName()).isEqualTo("HTML");
    assertThat(a.getBegin()).isEqualTo(0);
    assertThat(a.getEnd()).isEqualTo(127);
    a = iterator.next();
    assertThat(a.getType().getShortName()).isEqualTo("HEAD");
    assertThat(a.getBegin()).isEqualTo(8);
    assertThat(a.getEnd()).isEqualTo(39);
    a = iterator.next();
    assertThat(a.getType().getShortName()).isEqualTo("REMARK");
    assertThat(a.getBegin()).isEqualTo(16);
    assertThat(a.getEnd()).isEqualTo(30);
    a = iterator.next();
    assertThat(a.getType().getShortName()).isEqualTo("BODY");
    assertThat(a.getBegin()).isEqualTo(41);
    assertThat(a.getEnd()).isEqualTo(118);
    a = iterator.next();
    assertThat(a.getType().getShortName()).isEqualTo("BR");
    assertThat(a.getBegin()).isEqualTo(64);
    assertThat(a.getEnd()).isEqualTo(69);
    a = iterator.next();
    assertThat(a.getType().getShortName()).isEqualTo("B");
    assertThat(a.getBegin()).isEqualTo(78);
    assertThat(a.getEnd()).isEqualTo(89);
    a = iterator.next();
    assertThat(a.getType().getShortName()).isEqualTo("BR");
    assertThat(a.getBegin()).isEqualTo(91);
    assertThat(a.getEnd()).isEqualTo(96);

    ae.setConfigParameterValue("onlyContent", true);
    ae.reconfigure();
    cas.reset();
    cas.setDocumentText(html);
    ae.process(cas);
    ai = cas.getAnnotationIndex(tagType);
    iterator = ai.iterator();
    assertThat(ai.size()).isEqualTo(4);
    a = iterator.next();
    assertThat(a.getType().getShortName()).isEqualTo("HTML");
    assertThat(a.getBegin()).isEqualTo(6);
    assertThat(a.getEnd()).isEqualTo(120);
    a = iterator.next();
    assertThat(a.getType().getShortName()).isEqualTo("HEAD");
    assertThat(a.getBegin()).isEqualTo(14);
    assertThat(a.getEnd()).isEqualTo(32);
    a = iterator.next();
    assertThat(a.getType().getShortName()).isEqualTo("BODY");
    assertThat(a.getBegin()).isEqualTo(47);
    assertThat(a.getEnd()).isEqualTo(111);
    a = iterator.next();
    assertThat(a.getType().getShortName()).isEqualTo("B");
    assertThat(a.getBegin()).isEqualTo(81);
    assertThat(a.getEnd()).isEqualTo(85);

    cas.release();
  }
}
