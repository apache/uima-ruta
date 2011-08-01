package org.apache.uima.tm.textmarker.seed;

import java.io.BufferedReader;
import java.io.StringReader;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.jcas.tcas.Annotation;

public class DefaultSeeder implements TextMarkerAnnotationSeeder {

  public void seed(String text, CAS cas) {
    BufferedReader reader = new BufferedReader(new StringReader(text));
    final SeedLexer sourceLexer = new SeedLexer(reader);
    try {
      sourceLexer.setJCas(cas.getJCas());
    } catch (CASException e1) {
    }
    Annotation a = null;

    try {
      a = sourceLexer.yylex();
    } catch (Exception e) {
    }
    while (a != null) {
      a.addToIndexes();
      try {
        a = sourceLexer.yylex();
      } catch (Exception e) {
      }
    }
  }
}