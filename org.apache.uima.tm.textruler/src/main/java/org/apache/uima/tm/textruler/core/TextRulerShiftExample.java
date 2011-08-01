package org.apache.uima.tm.textruler.core;

/**
 * 
 * This class introduces the special additional information of an example for learning
 * LP2-Correction Rules. It needs besides the original, correct slot boundary annotation the wrong
 * annotation, or the other way round, it needs the correct annotation where to shift the boundary
 * tag.
 * 
 * Since TextRulerExample provides a possibility to store more than one Annotation for
 * MultiSlot-Exmaples, we easily can use this storage for those two annotations. But for the sake of
 * code reading and better understanding, this additional class with named accessors is created.
 * 
 */
public class TextRulerShiftExample extends TextRulerExample {

  public TextRulerShiftExample(TextRulerExampleDocument document,
          TextRulerAnnotation wrongAnnotation, TextRulerAnnotation correctAnnotation,
          boolean isPositive, TextRulerTarget target) {
    super(document, (TextRulerAnnotation[]) null, isPositive, target);
    annotations = new TextRulerAnnotation[2];
    annotations[0] = wrongAnnotation;
    annotations[1] = correctAnnotation;
  }

  public TextRulerAnnotation wrongAnnotation() {
    return annotations[0];
  }

  public TextRulerAnnotation correctAnnotation() {
    return annotations[1];
  }

  @Override
  public String toString() {
    String wrongStr = "" + wrongAnnotation().getBegin();
    String correctStr = "" + correctAnnotation().getBegin();
    return wrongStr + " --> " + correctStr;
  }

}
