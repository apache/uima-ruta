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

package org.apache.uima.ruta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.CASRuntimeException;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.TypeSystemDescriptionFactory;
import org.apache.uima.fit.util.CasUtil;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.type.TruePositive;
import org.apache.uima.util.InvalidXMLException;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

/**
 * Test the strict import option of {@link org.apache.uima.ruta.engine.RutaEngine}.
 */
public class StrictImportTest {
  private final String NAME = this.getClass().getSimpleName();

  private final String NAMESPACE = this.getClass().getPackage().getName();

  /**
   * Create an analysis engine for a Ruta script.
   *
   * @param script
   *          Script path.
   * @param strictImport
   *          {@link RutaEngine#PARAM_STRICT_IMPORTS} value.
   * @return Analysis engine.
   */
  private AnalysisEngine createAE(String script, boolean strictImport)
          throws ResourceInitializationException, IOException, InvalidXMLException {
    final TypeSystemDescription tsd = TypeSystemDescriptionFactory.createTypeSystemDescription(
            "org.apache.uima.ruta.engine.BasicTypeSystem",
            "org.apache.uima.ruta.StrictImportTestTypeSystem");
    final AnalysisEngineDescription ruta = AnalysisEngineFactory.createEngineDescription(
            "org.apache.uima.ruta.engine.BasicEngine", RutaEngine.PARAM_MAIN_SCRIPT, script,
            RutaEngine.PARAM_STRICT_IMPORTS, strictImport);

    tsd.addType(script.replaceAll("/", ".") + ".T1", "Type for Testing", "uima.tcas.Annotation");
    ruta.getAnalysisEngineMetaData().setTypeSystem(tsd);

    return AnalysisEngineFactory.createEngine(ruta);
  }

  @Test
  public void testUndeclaredTypeSystem() throws Exception {
    CAS cas;
    AnalysisEngine ae = createAE(NAMESPACE + "." + NAME + "UndeclaredTypeSystem", true);
    try {
      cas = ae.newCAS();
      cas.setDocumentText("Word");
      ae.process(cas);
      fail("MyType should not be resolved in the ruta script because it is not imported.");
    } catch (AnalysisEngineProcessException e) {
      // success, the usage of MyType raised an exception because its type system is not imported.
    } finally {
      if (ae != null) {
        ae.destroy();
      }
    }
  }

  @Test
  public void testUndeclaredTypeSystemWithStrictImportOff() throws Exception {
    CAS cas;
    String script = NAMESPACE + "." + NAME + "UndeclaredTypeSystem";
    AnalysisEngine ae = createAE(script, false);
    try {
      cas = ae.newCAS();
      cas.setDocumentText("Word");
      ae.process(cas);
    } finally {
      if (ae != null) {
        ae.destroy();
      }
    }

    assertEquals(Arrays.asList("Word"), selectText(cas, "org.apache.uima.ruta.MyType"));
    assertEquals(Arrays.asList("Word"), selectText(cas, script.replaceAll("/", ".") + ".T1"));
  }

  @Test
  public void testDeclaredTypeSystem() throws Exception {
    CAS cas;
    String script = NAMESPACE + "." + NAME + "DeclaredTypeSystem";
    AnalysisEngine ae = createAE(script, true);
    try {
      cas = ae.newCAS();
      cas.setDocumentText("Word");
      ae.process(cas);
    } finally {
      if (ae != null) {
        ae.destroy();
      }
    }

    assertEquals(Arrays.asList("Word"), selectText(cas, "org.apache.uima.ruta.MyType"));
    assertEquals(Arrays.asList("Word"), selectText(cas, script.replaceAll("/", ".") + ".T1"));
  }

  private List<String> selectText(CAS cas, String type) {
    List<String> values = new ArrayList<String>();
    for (AnnotationFS annotation : CasUtil.select(cas, cas.getTypeSystem().getType(type))) {
      values.add(annotation.getCoveredText());
    }

    return values;
  }

  @Test
  public void testStrictScriptImport() throws ResourceInitializationException, InvalidXMLException,
          IOException, AnalysisEngineProcessException, SAXException {
    Map<String, String> complexTypes = new HashMap<>();
    String s1 = "org.apache.uima.ruta.StrictScript2.Type1";
    String s2 = "org.apache.uima.ruta.other.Type1";
    complexTypes.put(s1, "uima.tcas.Annotation");
    complexTypes.put(s2, "uima.tcas.Annotation");

    CAS cas = RutaTestUtils.getCAS("Some text.", complexTypes, null);

    AnalysisEngine ae = AnalysisEngineFactory.createEngine(RutaEngine.class,
            RutaEngine.PARAM_MAIN_SCRIPT, "org.apache.uima.ruta.StrictScript1",
            RutaEngine.PARAM_ADDITIONAL_SCRIPTS, "org.apache.uima.ruta.StrictScript2",
            RutaEngine.PARAM_STRICT_IMPORTS, true);
    ae.process(cas);

    Type t1 = cas.getTypeSystem().getType(s1);
    Type t2 = cas.getTypeSystem().getType(s2);

    Assert.assertEquals(1, cas.getAnnotationIndex(t1).size());
    Assert.assertEquals(1, cas.getAnnotationIndex(t2).size());

    cas.release();
  }

  @Test
  public void testDocumentAnnotation() throws ResourceInitializationException, InvalidXMLException,
          IOException, AnalysisEngineProcessException, ResourceConfigurationException,
          URISyntaxException, CASRuntimeException, CASException, SAXException {
    CAS cas = RutaTestUtils.getCAS("Some text.");
    Map<String, Object> params = new HashMap<>();
    params.put(RutaEngine.PARAM_STRICT_IMPORTS, true);
    Ruta.apply(cas, "DocumentAnnotation{->TruePositive};", params);
    Ruta.apply(cas, "Document{->TruePositive};", params);
    AnnotationIndex<Annotation> annotationIndex = cas.getJCas()
            .getAnnotationIndex(TruePositive.type);
    Assert.assertEquals(2, annotationIndex.size());
    cas.release();
  }

}