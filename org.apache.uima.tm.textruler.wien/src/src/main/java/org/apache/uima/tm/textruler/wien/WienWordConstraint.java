package org.apache.uima.tm.textruler.wien;

import org.apache.uima.tm.textruler.core.TextRulerAnnotation;
import org.apache.uima.tm.textruler.core.TextRulerWordConstraint;

public class WienWordConstraint extends TextRulerWordConstraint {

  protected boolean generalizeLinkMarkUp;

  public WienWordConstraint(TextRulerAnnotation tokenAnnotation) {
    super(tokenAnnotation);
    generalizeLinkMarkUp = false;
  }

  public WienWordConstraint(WienWordConstraint copyFrom) {
    super(copyFrom);
    generalizeLinkMarkUp = copyFrom.generalizeLinkMarkUp;
  }

  @Override
  public WienWordConstraint copy() {
    return new WienWordConstraint(this);
  }

  public void setGeneralizeLinkMarkUp(boolean b) {
    generalizeLinkMarkUp = b;
  }

  @Override
  public String toString() {
    if (isRegExpConstraint()) {
      String theText = tokenAnnotation.getCoveredText();
      if (generalizeLinkMarkUp && typeShortName().equals("MARKUP")
              && (theText.startsWith("<a") || theText.startsWith("<A"))) {
        // special case for A HREF stuff, since hrefs are often
        // different but the struct is the same!
        // this is quick hack that should be configurable, but for now
        // it works!
        return theText.substring(0, 2) + ".*>";
      } else
        return super.toString();
    } else
      return super.toString();
  }

}
