package org.apache.uima.tm.textmarker.seed;

import org.apache.uima.cas.CAS;

public interface TextMarkerAnnotationSeeder {

  void seed(String text, CAS cas);

}
