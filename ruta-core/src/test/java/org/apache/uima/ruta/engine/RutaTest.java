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

import java.net.URL;
import java.util.List;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.metadata.FsIndexDescription;
import org.apache.uima.resource.metadata.TypeDescription;
import org.apache.uima.resource.metadata.TypePriorities;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.resource.metadata.impl.TypeDescription_impl;
import org.apache.uima.resource.metadata.impl.TypeSystemDescription_impl;
import org.apache.uima.util.CasCreationUtils;
import org.apache.uima.util.XMLInputSource;
import org.junit.jupiter.api.Test;

public class RutaTest {

  @Test
  public void testApply() throws Exception {
    URL typePrioritiesUrl = RutaTestUtils.class.getResource("TypePriorities.xml");
    URL tsUrl = RutaTestUtils.class.getResource("BasicTypeSystem.xml");
    Object descriptor = UIMAFramework.getXMLParser().parse(new XMLInputSource(tsUrl));
    TypeSystemDescription tsDesc = (TypeSystemDescription) descriptor;
    tsDesc.addType("uima.ruta.T1", "", "uima.tcas.Annotation");
    tsDesc.addType("uima.ruta.T2", "", "uima.tcas.Annotation");
    tsDesc.addType("uima.ruta.T3", "", "uima.tcas.Annotation");
    tsDesc.resolveImports();
    TypePriorities typePriorities = UIMAFramework.getXMLParser()
            .parseTypePriorities(new XMLInputSource(typePrioritiesUrl));
    CAS cas = CasCreationUtils.createCas(tsDesc, typePriorities, new FsIndexDescription[0]);

    cas.setDocumentText("Some document.");

    Ruta.apply(cas, "CW{-> MARK(T1)} SW;");
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;
    ai = cas.getAnnotationIndex(cas.getTypeSystem().getType("uima.ruta.T1"));
    iterator = ai.iterator();
    assertThat(ai.size()).isEqualTo(1);
    assertThat(iterator.next().getCoveredText()).isEqualTo("Some");

    Ruta.apply(cas, "T1 SW{-> MARK(T2)};");
    ai = cas.getAnnotationIndex(cas.getTypeSystem().getType("uima.ruta.T2"));
    iterator = ai.iterator();
    assertThat(ai.size()).isEqualTo(1);
    assertThat(iterator.next().getCoveredText()).isEqualTo("document");

    Ruta.apply(cas, "T1{-> MARK(T3,1,2,3)} T2 PERIOD;");
    ai = cas.getAnnotationIndex(cas.getTypeSystem().getType("uima.ruta.T3"));
    iterator = ai.iterator();
    assertThat(ai.size()).isEqualTo(1);
    assertThat(iterator.next().getCoveredText()).isEqualTo("Some document.");

    cas.release();
  }

  @Test
  public void testCreateAnalysisEngineDescription() throws Exception {
    TypeSystemDescription_impl tsdi = new TypeSystemDescription_impl();
    String t1 = "some.type.Test1";
    String t2 = "some.type.Test2";
    TypeDescription_impl ti1 = new TypeDescription_impl(t1, "", "uima.tcas.Annotation");
    TypeDescription_impl ti2 = new TypeDescription_impl(t2, "", "uima.tcas.Annotation");
    tsdi.setTypes(new TypeDescription[] { ti1, ti2 });

    String script = "CW SW{-> MARK(Test1)};\n Test1 SW{-> MARK(Test2)};";
    @SuppressWarnings("deprecation")
    AnalysisEngineDescription aed = Ruta.createAnalysisEngineDescription(script, tsdi);
    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(aed);
    CAS cas = ae.newCAS();
    cas.setDocumentText("Only some text.");
    ae.process(cas);

    Type type1 = cas.getTypeSystem().getType(t1);
    AnnotationIndex<AnnotationFS> ai1 = cas.getAnnotationIndex(type1);
    assertThat(ai1.size()).isEqualTo(1);
    assertThat(ai1.iterator().get().getCoveredText()).isEqualTo("some");

    Type type2 = cas.getTypeSystem().getType(t2);
    AnnotationIndex<AnnotationFS> ai2 = cas.getAnnotationIndex(type2);
    assertThat(ai2.size()).isEqualTo(1);
    assertThat(ai2.iterator().get().getCoveredText()).isEqualTo("text");
  }

  @Test
  public void testApplyRule() throws Exception {
    CAS cas = RutaTestUtils.getCAS("Some document.");
    JCas jcas = cas.getJCas();

    Ruta.applyRule(jcas, "CW{-> MARK(T1)} SW;");
    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "Some");

    Ruta.applyRule(jcas, "T1 SW{-> MARK(T2)};");
    RutaTestUtils.assertAnnotationsEquals(cas, 2, 1, "document");

    Ruta.applyRule(jcas, "T1{-> MARK(T3,1,2,3)} T2 PERIOD;");
    RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "Some document.");
  }

  @Test
  public void testSelect() throws Exception {
    String document = "Some text.";
    CAS cas = RutaTestUtils.getCAS(document);
    JCas jcas = cas.getJCas();
    List<Annotation> select1 = Ruta.select(jcas, "W{REGEXP(\"t.*\")};");
    assertThat(select1).hasSize(1);
    assertThat(select1.get(0).getCoveredText()).isEqualTo("text");

    List<Annotation> select2 = Ruta.select(jcas, "Document<-{ANY @PERIOD;};");
    assertThat(select2).hasSize(1);
    assertThat(select2.get(0).getCoveredText()).isEqualTo("Some text.");

    List<Annotation> select3 = Ruta.select(jcas, "ANY @ANY;");
    assertThat(select3).hasSize(2);
    assertThat(select3.get(0).getCoveredText()).isEqualTo("Some text");
    assertThat(select3.get(1).getCoveredText()).isEqualTo("text.");
  }

  @Test
  public void testSelectWithInjection() throws Exception {
    String document = "Some text.";
    CAS cas = RutaTestUtils.getCAS(document);
    Annotation t1 = (Annotation) cas.createAnnotation(RutaTestUtils.getTestType(cas, 1), 5, 9);
    cas.addFsToIndexes(t1);
    JCas jcas = cas.getJCas();
    List<Annotation> select1 = Ruta.select(jcas, Ruta.inject("W $;", t1));
    assertThat(select1).hasSize(1);
    assertThat(select1.get(0).getCoveredText()).isEqualTo("Some text");

    // List<Annotation> select2 = Ruta.select(jcas, Ruta.inject("W{W.begin == $.begin};", t1));
    // assertThat(select2).hasSize(1);
    // assertThat(select2.get(0).getCoveredText()).isEqualTo("text");
  }

  @Test
  public void testMatches() throws Exception {
    JCas jcas = RutaTestUtils.getCAS("Some text.").getJCas();
    assertThat(Ruta.matches(jcas, "CW # @PERIOD;")).isTrue();
    assertThat(Ruta.matches(jcas, "W{IS(CW)} \"text\";")).isTrue();
  }

  @Test
  public void testMatchesWithInjection() throws Exception {
    String document = "Some text.";
    CAS cas = RutaTestUtils.getCAS(document);
    Annotation t1 = (Annotation) cas.createAnnotation(RutaTestUtils.getTestType(cas, 1), 5, 9);
    cas.addFsToIndexes(t1);
    JCas jcas = cas.getJCas();
    assertThat(Ruta.matches(jcas, Ruta.inject("W $;", t1))).isTrue();
    assertThat(Ruta.matches(jcas, Ruta.inject("${REGEXP(\"te.*\"), PARTOF(SW)} PERIOD;", t1)))
            .isTrue();
  }

}
