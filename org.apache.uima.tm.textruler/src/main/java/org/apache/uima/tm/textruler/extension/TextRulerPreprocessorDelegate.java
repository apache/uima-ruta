package org.apache.uima.tm.textruler.extension;

/**
 * Small helper class for possible UI implementations for the preprocessor (see .ui)
 * 
 */
public interface TextRulerPreprocessorDelegate {

  public void preprocessorStatusUpdate(TextRulerPreprocessor p, String statusString);

  public boolean shouldAbort();

}
