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
 * Test the strict import option of {@link org.apache.uima.ruta.engine.RutaEngine}.
 */
public class StrictImportTest {
  private final String NAME = this.getClass().getSimpleName();
  private final String NAMESPACE = this.getClass().getPackage().getName().replaceAll("\\.", "/");

  /**
   * Create an analysis engine for a Ruta script.
   *
   * @param script       Script path.
   * @param strictImport {@link RutaEngine#PARAM_STRICT_IMPORTS} value.
   * @return Analysis engine.
   */
  private AnalysisEngine createAE(String script, boolean strictImport) throws ResourceInitializationException, IOException, InvalidXMLException {
    final TypeSystemDescription tsd = TypeSystemDescriptionFactory.createTypeSystemDescription(
            "org.apache.uima.ruta.BasicTypeSystem",
            "org.apache.uima.ruta.StrictImportTestTypeSystem");
    final AnalysisEngineDescription ruta = AnalysisEngineFactory.createEngineDescription(
            "org.apache.uima.ruta.engine.BasicEngine",
            RutaEngine.PARAM_MAIN_SCRIPT, script,
            RutaEngine.PARAM_STRICT_IMPORTS, strictImport);

    tsd.addType(script.replaceAll("/", ".") + ".T1", "Type for Testing", "uima.tcas.Annotation");
    ruta.getAnalysisEngineMetaData().setTypeSystem(tsd);

    return AnalysisEngineFactory.createEngine(ruta);
  }

  @Test
  public void testUndeclaredTypeSystem() throws Exception {
    CAS cas;
    AnalysisEngine ae = createAE(NAMESPACE + "/" + NAME + "UndeclaredTypeSystem", true);
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
    AnalysisEngine ae = createAE(NAMESPACE + "/" + NAME + "UndeclaredTypeSystem", false);
    try {
      cas = ae.newCAS();
      cas.setDocumentText("Word");
      ae.process(cas);
    } finally {
      if (ae != null) {
        ae.destroy();
      }
    }

    List<String> myTypes = new ArrayList<String>();
    for (AnnotationFS annotation : CasUtil.select(cas, cas.getTypeSystem().getType("org.apache.uima.ruta.MyType"))) {
      myTypes.add(annotation.getCoveredText());
    }

    assertEquals(Arrays.asList("Word"), selectText(cas, "org.apache.uima.ruta.MyType"));
    assertEquals(Arrays.asList("Word"), selectText(cas, NAME + ".T1"));
  }

  @Test
  public void testDeclaredTypeSystem() throws Exception {
    CAS cas;
    AnalysisEngine ae = createAE(NAMESPACE + "/" + NAME + "DeclaredTypeSystem", true);
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
    assertEquals(Arrays.asList("Word"), selectText(cas, NAME + ".T1"));
  }

  private List<String> selectText(CAS cas, String type) {
    List<String> values = new ArrayList<String>();
    for (AnnotationFS annotation : CasUtil.select(cas, cas.getTypeSystem().getType("org.apache.uima.ruta.MyType"))) {
      values.add(annotation.getCoveredText());
    }

    return values;
  }
}