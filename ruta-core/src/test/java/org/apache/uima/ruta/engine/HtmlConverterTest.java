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
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.XMLInputSource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class HtmlConverterTest {

  private static String htmlWin;

  private static String htmlWithComments;

  private static String htmlWithCommentsAndScript;

  private static String htmlUnix;

  private static String htmlDecoding;

  private static String htmlDecodingAnnotations;

  private String outputViewName = "raw_testing";

  @BeforeAll
  public static void setUpClass() throws Exception {
    // get resource
    String namespace = HtmlConverterTest.class.getPackage().getName().replaceAll("\\.", "/");
    String testFilename = namespace + "/" + "testWin.html";
    URL textURL = HtmlConverterTest.class.getClassLoader().getResource(testFilename);
    File textFile = new File(textURL.toURI());
    HtmlConverterTest.htmlWin = FileUtils.file2String(textFile, "UTF-8");
    //
    testFilename = namespace + "/" + "testUnix.html";
    textURL = HtmlConverterTest.class.getClassLoader().getResource(testFilename);
    textFile = new File(textURL.toURI());
    HtmlConverterTest.htmlUnix = FileUtils.file2String(textFile, "UTF-8");
    //
    testFilename = namespace + "/" + "testHtmlDecoding.html";
    textURL = HtmlConverterTest.class.getClassLoader().getResource(testFilename);
    textFile = new File(textURL.toURI());
    HtmlConverterTest.htmlDecoding = FileUtils.file2String(textFile, "UTF-8");
    //
    testFilename = namespace + "/" + "testHtmlDecodingAnnotations.html";
    textURL = HtmlConverterTest.class.getClassLoader().getResource(testFilename);
    textFile = new File(textURL.toURI());
    HtmlConverterTest.htmlDecodingAnnotations = FileUtils.file2String(textFile, "UTF-8");
    //
    testFilename = namespace + "/" + "testWithComments.html";
    textURL = HtmlConverterTest.class.getClassLoader().getResource(testFilename);
    textFile = new File(textURL.toURI());
    HtmlConverterTest.htmlWithComments = FileUtils.file2String(textFile, "UTF-8");
    //
    testFilename = namespace + "/" + "testWithCommentsAndScript.html";
    textURL = HtmlConverterTest.class.getClassLoader().getResource(testFilename);
    textFile = new File(textURL.toURI());
    HtmlConverterTest.htmlWithCommentsAndScript = FileUtils.file2String(textFile, "UTF-8");
  }

  @Test
  public void htmlBodyContentHtmlDecodingExplicitPolicyTest() throws Exception {
    // configure annotator and create AE:
    URL url = HtmlConverter.class.getClassLoader().getResource("HtmlConverter.xml");
    if (url == null) {
      url = HtmlConverter.class.getClassLoader()
              .getResource("org/apache/uima/ruta/engine/HtmlConverter.xml");
    }
    XMLInputSource in = new XMLInputSource(url);
    ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);
    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(specifier);
    CAS cas = ae.newCAS();

    ae.setConfigParameterValue(HtmlConverter.PARAM_OUTPUT_VIEW, outputViewName);
    ae.setConfigParameterValue(HtmlConverter.PARAM_CONVERSION_POLICY, "explicit");
    ae.setConfigParameterValue(HtmlConverter.PARAM_CONVERSION_PATTERNS, new String[] { "&nbsp;" });
    ae.setConfigParameterValue(HtmlConverter.PARAM_CONVERSION_REPLACEMENTS, new String[] { " " });
    ae.reconfigure();
    cas.reset();
    cas.setDocumentText(htmlDecoding);

    // go:
    ae.process(cas);

    CAS modifiedView = cas.getView(outputViewName);
    String text = modifiedView.getDocumentText();

    String expectedText = "start of body\nnormal normal bold\nend of body 3&#8364;&#160;&auml;&ouml;&uuml;";
    AnnotationFS documentAnnotation = modifiedView.getDocumentAnnotation();
    assertThat(documentAnnotation.getBegin())
            .as("begin of annotation is %d, %d expected.", documentAnnotation.getBegin(), 0)
            .isZero();
    assertThat(text.length()).isEqualTo(documentAnnotation.getEnd());
    assertThat(text).isEqualTo(expectedText);

    // fini
    cas.release();
  }

  @Test
  public void htmlBodyContentHtmlDecodingHeuristicPolicyTest() throws Exception {
    // configure annotator and create AE:
    URL url = HtmlConverter.class.getClassLoader().getResource("HtmlConverter.xml");
    if (url == null) {
      url = HtmlConverter.class.getClassLoader()
              .getResource("org/apache/uima/ruta/engine/HtmlConverter.xml");
    }
    XMLInputSource in = new XMLInputSource(url);
    ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);
    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(specifier);
    CAS cas = ae.newCAS();

    ae.setConfigParameterValue(HtmlConverter.PARAM_OUTPUT_VIEW, outputViewName);
    ae.reconfigure();
    cas.reset();
    cas.setDocumentText(htmlDecoding);

    // go:
    ae.process(cas);

    CAS modifiedView = cas.getView(outputViewName);
    String text = modifiedView.getDocumentText();

    String expectedText = "start of body\nnormal\u00A0normal bold\nend of body 3\u20AC\u00A0äöü";
    AnnotationFS documentAnnotation = modifiedView.getDocumentAnnotation();
    assertThat(documentAnnotation.getBegin())
            .as("begin of annotation is %d, %d expected.", documentAnnotation.getBegin(), 0)
            .isZero();
    assertThat(text.length()).isEqualTo(documentAnnotation.getEnd());
    assertThat(text).isEqualTo(expectedText);

    // fini
    cas.release();
  }

  @Test
  public void htmlBodyContentUnixTest() throws Exception {
    // configure annotator and create AE:
    URL url = HtmlConverter.class.getClassLoader().getResource("HtmlConverter.xml");
    if (url == null) {
      url = HtmlConverter.class.getClassLoader()
              .getResource("org/apache/uima/ruta/engine/HtmlConverter.xml");
    }
    XMLInputSource in = new XMLInputSource(url);
    ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);
    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(specifier);
    CAS cas = ae.newCAS();

    ae.setConfigParameterValue(HtmlConverter.PARAM_OUTPUT_VIEW, outputViewName);
    ae.reconfigure();
    cas.reset();
    cas.setDocumentText(htmlUnix);

    // go:
    ae.process(cas);

    CAS modifiedView = cas.getView(outputViewName);
    String text = modifiedView.getDocumentText();

    String expectedText = "start of body\nnormal bold\nend of body";
    AnnotationFS documentAnnotation = modifiedView.getDocumentAnnotation();
    assertThat(documentAnnotation.getBegin())
            .as("begin of annotation is %d, %d expected.", documentAnnotation.getBegin(), 0)
            .isZero();
    assertThat(text.length()).isEqualTo(documentAnnotation.getEnd());
    assertThat(text).isEqualTo(expectedText);

    // fini
    cas.release();
  }

  @Test
  public void htmlBodyContentWinTest() throws Exception {
    // configure annotator and create AE:
    URL url = HtmlConverter.class.getClassLoader().getResource("HtmlConverter.xml");
    if (url == null) {
      url = HtmlConverter.class.getClassLoader()
              .getResource("org/apache/uima/ruta/engine/HtmlConverter.xml");
    }
    XMLInputSource in = new XMLInputSource(url);
    ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);
    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(specifier);
    CAS cas = ae.newCAS();

    ae.setConfigParameterValue(HtmlConverter.PARAM_OUTPUT_VIEW, outputViewName);
    ae.reconfigure();
    cas.reset();
    cas.setDocumentText(htmlWin);

    // go:
    ae.process(cas);

    CAS modifiedView = cas.getView(outputViewName);
    String text = modifiedView.getDocumentText();

    String expectedText = "start of body\nnormal bold\nend of body";
    AnnotationFS documentAnnotation = modifiedView.getDocumentAnnotation();
    assertThat(documentAnnotation.getBegin())
            .as("begin of annotation is %d, %d expected.", documentAnnotation.getBegin(), 0)
            .isZero();
    assertThat(text.length()).isEqualTo(documentAnnotation.getEnd());
    assertThat(text).isEqualTo(expectedText);

    // fini
    cas.release();
  }

  @Test
  public void htmlBodyContentWithCommentsTest() throws Exception {
    // configure annotator and create AE:
    URL url = HtmlConverter.class.getClassLoader().getResource("HtmlConverter.xml");
    if (url == null) {
      url = HtmlConverter.class.getClassLoader()
              .getResource("org/apache/uima/ruta/engine/HtmlConverter.xml");
    }
    XMLInputSource in = new XMLInputSource(url);
    ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);
    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(specifier);
    CAS cas = ae.newCAS();

    ae.setConfigParameterValue(HtmlConverter.PARAM_OUTPUT_VIEW, outputViewName);
    ae.reconfigure();
    cas.reset();
    cas.setDocumentText(htmlWithComments);

    // go:
    ae.process(cas);

    CAS modifiedView = cas.getView(outputViewName);
    String text = modifiedView.getDocumentText();

    String expectedText = "start of body\nnormal bold\nend of body";
    AnnotationFS documentAnnotation = modifiedView.getDocumentAnnotation();
    assertThat(documentAnnotation.getBegin())
            .as("begin of annotation is %d, %d expected.", documentAnnotation.getBegin(), 0)
            .isZero();
    assertThat(text.length()).isEqualTo(documentAnnotation.getEnd());
    assertThat(text).isEqualTo(expectedText);

    // fini
    cas.release();
  }

  @Test
  public void htmlBodyContentNLTagsTest() throws Exception {
    // configure annotator and create AE:
    URL url = HtmlConverter.class.getClassLoader().getResource("HtmlConverter.xml");
    if (url == null) {
      url = HtmlConverter.class.getClassLoader()
              .getResource("org/apache/uima/ruta/engine/HtmlConverter.xml");
    }
    XMLInputSource in = new XMLInputSource(url);
    ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);
    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(specifier);
    CAS cas = ae.newCAS();

    ae.setConfigParameterValue(HtmlConverter.PARAM_OUTPUT_VIEW, outputViewName);
    ae.setConfigParameterValue(HtmlConverter.PARAM_NEWLINE_INDUCING_TAGS, new String[] { "br" });
    ae.reconfigure();
    cas.reset();
    cas.setDocumentText(htmlWin);

    // go:
    ae.process(cas);

    CAS modifiedView = cas.getView(outputViewName);
    String text = modifiedView.getDocumentText();

    String expectedText = "start of body\nnormal bold\nend of body";
    AnnotationFS documentAnnotation = modifiedView.getDocumentAnnotation();
    assertThat(documentAnnotation.getBegin())
            .as("begin of annotation is %d, %d expected.", documentAnnotation.getBegin(), 0)
            .isZero();
    assertThat(text.length()).isEqualTo(documentAnnotation.getEnd());
    assertThat(text).isEqualTo(expectedText);

    // fini
    cas.release();
  }

  @Test
  public void htmlBodyContentWithCommentsAndScriptTest() throws Exception {
    // configure annotator and create AE:
    URL url = HtmlConverter.class.getClassLoader().getResource("HtmlConverter.xml");
    if (url == null) {
      url = HtmlConverter.class.getClassLoader()
              .getResource("org/apache/uima/ruta/engine/HtmlConverter.xml");
    }
    XMLInputSource in = new XMLInputSource(url);
    ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);
    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(specifier);
    CAS cas = ae.newCAS();

    ae.setConfigParameterValue(HtmlConverter.PARAM_OUTPUT_VIEW, outputViewName);
    ae.reconfigure();
    cas.reset();
    cas.setDocumentText(htmlWithCommentsAndScript);

    // go:
    ae.process(cas);

    CAS modifiedView = cas.getView(outputViewName);
    String text = modifiedView.getDocumentText();

    String expectedText = "start of body\nnormal bold\nend of body";
    AnnotationFS documentAnnotation = modifiedView.getDocumentAnnotation();
    assertThat(documentAnnotation.getBegin())
            .as("begin of annotation is %d, %d expected.", documentAnnotation.getBegin(), 0)
            .isZero();
    assertThat(text.length()).isEqualTo(documentAnnotation.getEnd());
    assertThat(text).isEqualTo(expectedText);

    // fini
    cas.release();
  }

  @Test
  public void annotationPropagationTest() throws Exception {
    JCas jcas = JCasFactory.createJCas();
    for (String htmlContent : new String[] { htmlWin }) {
      // configure annotator and create AE:
      URL url = HtmlConverter.class.getClassLoader().getResource("HtmlConverter.xml");
      if (url == null) {
        url = HtmlConverter.class.getClassLoader()
                .getResource("org/apache/uima/ruta/engine/HtmlConverter.xml");
      }
      XMLInputSource in = new XMLInputSource(url);
      AnalysisEngineDescription specifier = (AnalysisEngineDescription) UIMAFramework.getXMLParser()
              .parseResourceSpecifier(in);

      AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(specifier);
      ae.setConfigParameterValue("outputView", outputViewName);
      ae.reconfigure();

      // create the cas and input annotation
      CAS cas = jcas.getCas();
      cas.reset();
      cas.setDocumentText(htmlContent);
      Type boldType = cas.getTypeSystem().getType(HtmlAnnotator.NAMESPACE + "B");
      AnnotationFS fs = cas.createAnnotation(boldType, 78, 89);
      cas.addFsToIndexes(fs);

      // go:
      ae.process(cas);

      // test:
      CAS modifiedView = cas.getView(outputViewName);
      AnnotationIndex<AnnotationFS> ai = modifiedView.getAnnotationIndex(boldType);
      FSIterator<AnnotationFS> iterator = ai.iterator();
      assertThat(iterator.hasNext()).isEqualTo(true);
      AnnotationFS next = iterator.next();
      assertThat(next.getType().getShortName()).isEqualTo("B");
      assertThat(next.getBegin()).isEqualTo(21);
      assertThat(next.getEnd()).isEqualTo(25);
      assertThat("bold").isEqualTo(next.getCoveredText());
      assertThat(4).isEqualTo(next.getCoveredText().length());

      // fini
      cas.release();

    }

  }

  @Test
  public void annotationPropagationAndDecodingTest() throws Exception {
    JCas jcas = JCasFactory.createJCas();
    // configure annotator and create AE:
    URL url = HtmlConverter.class.getClassLoader().getResource("HtmlConverter.xml");
    if (url == null) {
      url = HtmlConverter.class.getClassLoader()
              .getResource("org/apache/uima/ruta/engine/HtmlConverter.xml");
    }
    XMLInputSource in = new XMLInputSource(url);
    AnalysisEngineDescription specifier = (AnalysisEngineDescription) UIMAFramework.getXMLParser()
            .parseResourceSpecifier(in);

    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(specifier);
    ae.setConfigParameterValue("outputView", outputViewName);
    ae.reconfigure();

    // create the cas and input annotation
    CAS cas = jcas.getCas();
    cas.reset();
    cas.setDocumentText(htmlDecodingAnnotations);
    Type boldType = cas.getTypeSystem().getType(HtmlAnnotator.NAMESPACE + "B");
    AnnotationFS fs1 = cas.createAnnotation(boldType, 210, 221);
    AnnotationFS fs2 = cas.createAnnotation(boldType, 279, 290);
    cas.addFsToIndexes(fs1);
    cas.addFsToIndexes(fs2);

    // go:
    ae.process(cas);

    // test: should be:
    //
    // start of body\nnormal normal bold\nend of body 3€ äöü and bold.
    // 01234567890123 4567890123456789012 34567890123456789012345678901234567890
    // 0 ------- 1 -------- 2 ------- 3 -------- 4 ------- 5 ------- 6 ------- 7
    // ____________________________ <--> _______________________ <--> __________
    // _____________________________ BB _________________________ BB ___________
    //
    CAS modifiedView = cas.getView(outputViewName);
    // String modifiedText = modifiedView.getDocumentText();
    // int modLength = modifiedText.length();
    // int modLengthCodepoints = modifiedText.codePointCount(0, modLength);
    AnnotationIndex<AnnotationFS> ai = modifiedView.getAnnotationIndex(boldType);
    FSIterator<AnnotationFS> iterator = ai.iterator();
    assertThat(iterator.hasNext()).isEqualTo(true);
    // check first bold annotation
    AnnotationFS next = iterator.next();
    assertThat(next.getType().getShortName()).isEqualTo("B");
    assertThat(next.getBegin()).isEqualTo(28);
    assertThat(next.getEnd()).isEqualTo(32);
    assertThat("bold").isEqualTo(next.getCoveredText());
    assertThat(4).isEqualTo(next.getCoveredText().length());
    // check second bold annotation
    next = iterator.next();
    int begin = next.getBegin();
    int end = next.getEnd();
    assertThat(next.getType().getShortName()).isEqualTo("B");
    assertThat(begin).isEqualTo(56); // map[279] == 56
    assertThat(end).isEqualTo(60); // map[290] == 60
    assertThat("bold").isEqualTo(next.getCoveredText());
    assertThat(4).isEqualTo(next.getCoveredText().length());

    // fini
    cas.release();

  }

  @Test
  public void parameterTestInputView() throws Exception {
    // configure annotator and create AE:
    URL url = HtmlConverter.class.getClassLoader().getResource("HtmlConverter.xml");
    if (url == null) {
      url = HtmlConverter.class.getClassLoader()
              .getResource("org/apache/uima/ruta/engine/HtmlConverter.xml");
    }
    XMLInputSource in = new XMLInputSource(url);
    ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);
    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(specifier);
    CAS cas = ae.newCAS();

    String inputViewName = "inview";

    ae.setConfigParameterValue(HtmlConverter.PARAM_INPUT_VIEW, inputViewName);
    ae.setConfigParameterValue(HtmlConverter.PARAM_OUTPUT_VIEW, outputViewName);
    ae.reconfigure();
    cas.reset();
    CAS inview = cas.createView(inputViewName);
    inview.setDocumentText(htmlUnix);

    // go:
    ae.process(cas);

    CAS modifiedView = cas.getView(outputViewName);
    String text = modifiedView.getDocumentText();

    String expectedText = "start of body\nnormal bold\nend of body";
    AnnotationFS documentAnnotation = modifiedView.getDocumentAnnotation();
    assertThat(documentAnnotation.getBegin())
            .as("begin of annotation is %d, %d expected.", documentAnnotation.getBegin(), 0)
            .isZero();
    assertThat(text.length()).isEqualTo(documentAnnotation.getEnd());
    assertThat(text).isEqualTo(expectedText);

    // fini
    cas.release();
  }

  @Test
  public void testStyle() throws Exception {
    String html = "<html><head>\n" + "<style>\n" + "/*  */\n" + ".test {\n"
            + "   text-align: left;\n" + "}\n" + "</style>\n"
            + "</head><body>Hello world</body></html>";
    URL url = HtmlConverter.class.getClassLoader().getResource("HtmlConverter.xml");
    if (url == null) {
      url = HtmlConverter.class.getClassLoader()
              .getResource("org/apache/uima/ruta/engine/HtmlConverter.xml");
    }
    XMLInputSource in = new XMLInputSource(url);
    ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);
    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(specifier);
    CAS cas = ae.newCAS();

    ae.setConfigParameterValue(HtmlConverter.PARAM_OUTPUT_VIEW, outputViewName);
    ae.reconfigure();
    cas.reset();
    cas.setDocumentText(html);

    ae.process(cas);

    CAS modifiedView = cas.getView(outputViewName);
    String text = modifiedView.getDocumentText();

    String expectedText = "Hello world";
    assertThat(text).isEqualTo(expectedText);

    cas.release();
  }
}
