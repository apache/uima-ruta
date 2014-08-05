package org.apache.uima.ruta.string;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.junit.Test;

public class ToUpperCaseStringFunctionTest {
  @Test
  public void test() {
    String document = "Peter Markus test PETER MARKUS test";
    String script = "STRING s;\n";
    script += "STRINGLIST sl;\n";
    script += "CW{-> MATCHEDTEXT(s), ADD(sl, toUpperCase(s))};\n";
    script += "CAP{INLIST(sl) -> T1};";

    Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put(RutaEngine.PARAM_ADDITIONAL_EXTENSIONS,
            new String[] { StringOperationsExtension.class.getName() });
    CAS cas = null;
    try {
      cas = RutaTestUtils.getCAS(document);
      Ruta.apply(cas, script, parameters);
    } catch (Exception e) {
      e.printStackTrace();
    }

    Type t = null;
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;

    t = RutaTestUtils.getTestType(cas, 1);
    ai = cas.getAnnotationIndex(t);
    assertEquals(2, ai.size());
    iterator = ai.iterator();
    assertEquals("PETER", iterator.next().getCoveredText());
    assertEquals("MARKUS", iterator.next().getCoveredText());

    if (cas != null) {
      cas.release();
    }

  }
}
