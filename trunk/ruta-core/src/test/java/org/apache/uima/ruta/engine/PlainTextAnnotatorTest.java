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

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URL;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.XMLInputSource;
import org.junit.Test;

public class PlainTextAnnotatorTest {

  @Test
  public void test() throws Exception {
    String namespace = this.getClass().getPackage().getName().replaceAll("\\.", "/");
    String name = namespace + "/" + "PlainTextAnnotatorTest.txt";
    URL textURL = PlainTextAnnotatorTest.class.getClassLoader().getResource(name);
    File textFile = new File(textURL.toURI());
    String text = FileUtils.file2String(textFile, "UTF-8");
    URL url = PlainTextAnnotator.class.getClassLoader().getResource("PlainTextAnnotator.xml");
    if (url == null) {
      url = HtmlAnnotator.class.getClassLoader().getResource(
              "org/apache/uima/ruta/engine/PlainTextAnnotator.xml");
    }
    XMLInputSource in = new XMLInputSource(url);
    ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);
    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(specifier);
    CAS cas = ae.newCAS();
    AnnotationIndex<AnnotationFS> ai = null;

    cas.setDocumentText(text);
    ae.process(cas);
    
    ai = cas.getAnnotationIndex(cas.getTypeSystem().getType("org.apache.uima.ruta.type.AnyLine"));
    assertEquals(18, ai.size());

    ai = cas.getAnnotationIndex(cas.getTypeSystem().getType("org.apache.uima.ruta.type.Line"));
    assertEquals(10, ai.size());

    ai = cas.getAnnotationIndex(cas.getTypeSystem().getType("org.apache.uima.ruta.type.EmptyLine"));
    assertEquals(8, ai.size());

    ai = cas.getAnnotationIndex(cas.getTypeSystem().getType("org.apache.uima.ruta.type.WSLine"));
    assertEquals(5, ai.size());

    ai = cas.getAnnotationIndex(cas.getTypeSystem().getType("org.apache.uima.ruta.type.Paragraph"));
    assertEquals(4, ai.size());

    ae.destroy();
    cas.release();
  }
}
