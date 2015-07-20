package org.apache.uima.ruta;

import static org.apache.uima.ruta.engine.Ruta.apply;
import static org.apache.uima.ruta.engine.RutaTestUtils.getCAS;

import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.extensions.RutaParseRuntimeException;
import org.junit.Test;

/** Tests for exceptions thrown when parsing a Ruta script */
public class RutaParsingErrorTest {

  @Test
  // TODO maybe this test is not necessary...
  public void test() throws Exception {
    CAS cas = getCAS("aaaa.");
    apply(cas, "\"aaaa.\" -> T1;");
    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, "aaaa.");
  }

  @Test(expected = RutaParseRuntimeException.class)
  public void testEscapedDot() throws Exception {
    CAS cas = getCAS("aaaa");
    apply(cas, "\"aaaa\\.\" -> T1;");
  }
}
