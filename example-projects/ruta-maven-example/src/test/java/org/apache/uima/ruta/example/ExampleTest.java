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

package org.apache.uima.ruta.example;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;
import org.junit.jupiter.api.Test;

import uima.ruta.example.Author;
import uima.ruta.example.Bibtex;

public class ExampleTest {

  @Test
  public void testMain() throws InvalidXMLException, IOException, ResourceInitializationException,
          AnalysisEngineProcessException, CASException {
    File aeFile = new File(
            "target/generated-sources/ruta/descriptor/uima/ruta/example/MainEngine.xml");

    AnalysisEngineDescription aed = UIMAFramework.getXMLParser()
            .parseAnalysisEngineDescription(new XMLInputSource(aeFile));
    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(aed);
    CAS cas = ae.newCAS();
    cas.setDocumentText(
            "Ogren, P.V., Wetzler, P.G., Bethard, S.: ClearTK: A UIMA Toolkit for Statistical Natural Language Processing. In: UIMA for NLP workshop at LREC 08. (2008)");
    ae.process(cas);

    JCas jCas = cas.getJCas();
    Collection<Bibtex> bibtexs = JCasUtil.select(jCas, Bibtex.class);
    assertThat(bibtexs.size()).isEqualTo(1);
    Bibtex bibtex = bibtexs.iterator().next();
    assertThat(bibtex.getCoveredText()).isEqualTo(
            "Ogren, P.V., Wetzler, P.G., Bethard, S.: ClearTK: A UIMA Toolkit for Statistical Natural Language Processing. In: UIMA for NLP workshop at LREC 08. (2008)");
    assertThat(bibtex.getAuthor().getCoveredText())
            .isEqualTo("Ogren, P.V., Wetzler, P.G., Bethard, S.:");
    assertThat(bibtex.getTitle().getCoveredText())
            .isEqualTo("ClearTK: A UIMA Toolkit for Statistical Natural Language Processing.");
    assertThat(bibtex.getYear().getCoveredText()).isEqualTo("(2008)");
  }

  @Test
  public void testAuthorWithMTWL() throws InvalidXMLException, IOException,
          ResourceInitializationException, AnalysisEngineProcessException, CASException {
    File aeFile = new File(
            "target/generated-sources/ruta/descriptor/uima/ruta/example/AuthorWithMTWLEngine.xml");

    AnalysisEngineDescription aed = UIMAFramework.getXMLParser()
            .parseAnalysisEngineDescription(new XMLInputSource(aeFile));
    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(aed);
    CAS cas = ae.newCAS();
    cas.setDocumentText(
            "Stephen Soderland, Claire Cardie, and Raymond Mooney. Learning Information Extraction Rules for Semi-Structured and Free Text. In Machine Learning, volume 34, pages 233–272, 1999.");
    ae.process(cas);

    JCas jCas = cas.getJCas();
    Collection<Author> authors = JCasUtil.select(jCas, Author.class);
    assertThat(authors).hasSize(1);
    Author author = authors.iterator().next();
    assertThat(author.getCoveredText())
            .isEqualTo("Stephen Soderland, Claire Cardie, and Raymond Mooney.");
  }
}
