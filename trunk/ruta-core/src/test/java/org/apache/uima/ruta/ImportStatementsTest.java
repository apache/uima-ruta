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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.TypeSystemDescriptionFactory;
import org.apache.uima.fit.util.CasUtil;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.util.InvalidXMLException;
import org.junit.Test;

/**
 * Test various import statements syntaxes (see UIMA-3303).
 */
public class ImportStatementsTest {
  private final String NAME = this.getClass().getSimpleName();
  private final String NAMESPACE = this.getClass().getPackage().getName().replaceAll("\\.", "/");

  /**
   * Create an analysis engine for a Ruta script.
   *
   * @param script       Script path.
   * @param strictImport {@link org.apache.uima.ruta.engine.RutaEngine#PARAM_STRICT_IMPORTS} value.
   * @return Analysis engine.
   */
  private AnalysisEngine createAE(String script, boolean strictImport) throws ResourceInitializationException, IOException, InvalidXMLException {
    final TypeSystemDescription tsd = TypeSystemDescriptionFactory.createTypeSystemDescription(
        "org.apache.uima.ruta.BasicTypeSystem",
        "org.apache.uima.ruta.ImportStatementsTestTypeSystem",
        "org.apache.uima.ruta.ImportStatementsTestTypeSystemWithAmbiguousShortNames",
        "org.apache.uima.ruta.ImportStatementsTestTypeSystemWithManyPackages");
    final AnalysisEngineDescription ruta = AnalysisEngineFactory.createEngineDescription(
        "org.apache.uima.ruta.engine.BasicEngine",
        RutaEngine.PARAM_MAIN_SCRIPT, script,
        RutaEngine.PARAM_STRICT_IMPORTS, strictImport);

    ruta.getAnalysisEngineMetaData().setTypeSystem(tsd);

    return AnalysisEngineFactory.createEngine(ruta);
  }

  @Test
  public void testImportType() throws Exception {
    AnalysisEngine ae = createAE(NAMESPACE + "/" + NAME + "ImportType", true);
    try {
      CAS cas = ae.newCAS();
      cas.setDocumentText("Word");
      ae.process(cas);

      assertEquals(Arrays.asList("Word"), selectText(cas, "org.apache.uima.ruta.Type1"));
    } finally {
      ae.destroy();
    }
  }

  @Test
  public void testImportTypeFromDefaultTypesystem() throws Exception {
    AnalysisEngine ae = createAE(NAMESPACE + "/" + NAME + "ImportTypeFromDefaultTypeSystem", true);
    try {
      CAS cas = ae.newCAS();
      cas.setDocumentText("Word");
      ae.process(cas);

      assertEquals(Arrays.asList("Word"), selectText(cas, "org.apache.uima.ruta.Type1"));
    } finally {
      ae.destroy();
    }
  }

  @Test
  public void testUnimportedTypesAreNotResolved() throws Exception {
    AnalysisEngine ae = createAE(NAMESPACE + "/" + NAME + "UnimportedTypesAreNotResolved", true);
    try {
      CAS cas = ae.newCAS();
      cas.setDocumentText("Word");
      ae.process(cas);

      fail("Type2 should not be resolved because it is not imported.");
    } catch (AnalysisEngineProcessException e) {
      // success, Type2 should not be resolved
    } finally {
      ae.destroy();
    }
  }

  @Test
  public void testImportTypeAs() throws Exception {
    AnalysisEngine ae = createAE(NAMESPACE + "/" + NAME + "ImportTypeAs", true);
    try {
      CAS cas = ae.newCAS();
      cas.setDocumentText("First Second");
      ae.process(cas);

      assertEquals(Arrays.asList("First"), selectText(cas, "org.apache.uima.ruta.Type1"));
      assertEquals(Arrays.asList("Second"), selectText(cas, "org.apache.uima.ruta.Type2"));
    } finally {
      ae.destroy();
    }

  }

  @Test
  public void testImportTypeAsStrictModeOff() throws Exception {
    AnalysisEngine ae = createAE(NAMESPACE + "/" + NAME + "ImportTypeAs", false);
    try {
      CAS cas = ae.newCAS();
      cas.setDocumentText("First Second");
      ae.process(cas);

      assertEquals(Arrays.asList("First"), selectText(cas, "org.apache.uima.ruta.Type1"));
      assertEquals(Arrays.asList("Second"), selectText(cas, "org.apache.uima.ruta.Type2"));
    } finally {
      ae.destroy();
    }
  }

  @Test
  public void testImportStarFromTypeSystem() throws Exception {
    AnalysisEngine ae = createAE(NAMESPACE + "/" + NAME + "ImportStarFromTypeSystem", true);
    try {
      CAS cas = ae.newCAS();
      cas.setDocumentText("First Second");
      ae.process(cas);

      assertEquals(Arrays.asList("First"), selectText(cas, "org.apache.uima.ruta.Type1"));
      assertEquals(Arrays.asList("Second"), selectText(cas, "org.apache.uima.ruta.Type2"));
    } finally {
      ae.destroy();
    }

  }

  @Test
  public void testImportPackageFromTypeSystem() throws Exception {
    AnalysisEngine ae = createAE(NAMESPACE + "/" + NAME + "ImportPackageFromTypeSystem", true);
    try {
      CAS cas = ae.newCAS();
      cas.setDocumentText("First Second");
      ae.process(cas);

      assertEquals(Arrays.asList("First"), selectText(cas, "org.apache.uima.ruta.other.Type1"));
      assertEquals(Arrays.asList("Second"), selectText(cas, "org.apache.uima.ruta.other.Type2"));
    } finally {
      ae.destroy();
    }

  }

  @Test
  public void testImportPackageAliasFromTypeSystem() throws Exception {
    AnalysisEngine ae = createAE(NAMESPACE + "/" + NAME + "ImportPackageAliasFromTypeSystem", true);
    try {
      CAS cas = ae.newCAS();
      cas.setDocumentText("First Second");
      ae.process(cas);

      assertEquals(Arrays.asList("First"), selectText(cas, "org.apache.uima.ruta.other.Type1"));
      assertEquals(Arrays.asList("Second"), selectText(cas, "org.apache.uima.ruta.other2.Type1"));
    } finally {
      ae.destroy();
    }

  }

  @Test
  public void testImportPackageAsWhenStrictModeIsOff() throws Exception {
    AnalysisEngine ae = createAE(NAMESPACE + "/" + NAME + "ImportPackageAs", false);
    try {
      CAS cas = ae.newCAS();
      cas.setDocumentText("First Second");
      ae.process(cas);

      assertEquals(Arrays.asList("First"), selectText(cas, "org.apache.uima.ruta.Type1"));
      assertEquals(Arrays.asList("Second"), selectText(cas, "org.apache.uima.ruta.Type2"));
    } finally {
      ae.destroy();
    }
  }

  @Test
  public void testImportPackageFromCasTypeSystem() throws Exception {
    AnalysisEngine ae = createAE(NAMESPACE + "/" + NAME + "ImportPackageFromCasTypeSystem", true);
    try {
      CAS cas = ae.newCAS();
      cas.setDocumentText("First Second");
      ae.process(cas);

      assertEquals(Arrays.asList("First"), selectText(cas, "org.apache.uima.ruta.other.Type1"));
      assertEquals(Arrays.asList("Second"), selectText(cas, "org.apache.uima.ruta.other2.Type1"));
    } finally {
      ae.destroy();
    }
  }

  @Test
  public void testImportAllPackagesFromTypeSystem() throws Exception {
    AnalysisEngine ae = createAE(NAMESPACE + "/" + NAME + "ImportAllPackagesFromTypeSystem", true);
    try {
      CAS cas = ae.newCAS();
      cas.setDocumentText("First Second");
      ae.process(cas);

      assertEquals(Arrays.asList("First"), selectText(cas, "org.apache.uima.ruta.other3.Type1"));
      assertEquals(Arrays.asList("Second"), selectText(cas, "org.apache.uima.ruta.other4.Type2"));
    } finally {
      ae.destroy();
    }
  }

  @Test
  public void testImportAllPackagesWithAliasFromTypeSystem() throws Exception {
    AnalysisEngine ae = createAE(NAMESPACE + "/" + NAME + "ImportAllPackagesWithAliasFromTypeSystem", true);
    try {
      CAS cas = ae.newCAS();
      cas.setDocumentText("First Second");
      ae.process(cas);

      assertEquals(Arrays.asList("First"), selectText(cas, "org.apache.uima.ruta.other3.Type1"));
      assertEquals(Arrays.asList("Second"), selectText(cas, "org.apache.uima.ruta.other4.Type2"));
    } finally {
      ae.destroy();
    }
  }

  @Test
  public void testImportAllPackagesFromTypeSystemWithAmbiguousShortNames() throws Exception {
    AnalysisEngine ae = createAE(NAMESPACE + "/" + NAME + "ImportAllPackagesFromTypeSystemWithAmbiguousShortNames", true);
    try {
      CAS cas = ae.newCAS();
      cas.setDocumentText("First Second");
      ae.process(cas);

      fail("Engine should fail because Type1 and Type2 are ambiguous");
    } catch (AnalysisEngineProcessException e) {
      if (e.getCause() instanceof IllegalArgumentException) {
        // success, Type1 and Type2 are ambiguous and this exception should be raised.
      } else {
        throw e;
      }
    } finally {
      ae.destroy();
    }
  }

  private List<String> selectText(CAS cas, String type) {
    List<String> values = new ArrayList<String>();
    for (AnnotationFS annotation : CasUtil.select(cas, cas.getTypeSystem().getType(type))) {
      values.add(annotation.getCoveredText());
    }

    return values;
  }
}
