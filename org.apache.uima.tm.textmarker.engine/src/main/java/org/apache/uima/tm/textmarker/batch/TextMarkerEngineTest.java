package org.apache.uima.tm.textmarker.batch;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.CAS;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.util.XMLInputSource;

public class TextMarkerEngineTest {

  private XMLInputSource in;

  private ResourceSpecifier specifier;

  private AnalysisEngine ae;

  private String text = "Der Hund jagt die Katze. Die Katze ist <b>fett</b>.";

  public static void main(String[] args) {
    try {
      new TextMarkerEngineTest().test();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void test() throws Exception {
    in = new XMLInputSource(getClass().getResource("/TextMarkerEngineDescriptor.xml"));
    specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);
    ae = UIMAFramework.produceAnalysisEngine(specifier);
    CAS cas = ae.newCAS();
    cas.setDocumentText(text);
    ae.setConfigParameterValue("ScriptLocation",
            "C:/work/runtime-EclipseApplication/test/scripts/test.tm");
    ae.process(cas);
  }

}
