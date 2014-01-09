package org.apache.uima.ruta;

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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
        "org.apache.uima.ruta.ImportStatementsTestTypeSystem");
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

  private List<String> selectText(CAS cas, String type) {
    List<String> values = new ArrayList<String>();
    for (AnnotationFS annotation : CasUtil.select(cas, cas.getTypeSystem().getType(type))) {
      values.add(annotation.getCoveredText());
    }

    return values;
  }
}
