package org.apache.uima.tm.textmarker.kernel.rule;

import java.util.List;

import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.textmarker.kernel.TextMarkerBlock;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public interface RuleElement {

  void apply(RuleMatch matchInfos, TextMarkerStream symbolStream, InferenceCrowd crowd);

  RuleElementMatch match(TextMarkerBasic currentBasic, TextMarkerStream stream, InferenceCrowd crowd);

  List<RuleElementMatch> evaluateMatches(List<RuleElementMatch> matches, TextMarkerBlock parent);

  List<TextMarkerBasic> getAnchors(TextMarkerStream symbolStream);

  FSIterator<AnnotationFS> getAnchors2(TextMarkerStream symbolStream);

  boolean continueMatch(int index, List<RuleElement> elements, TextMarkerBasic next,
          RuleElementMatch match, List<RuleElementMatch> matches, TextMarkerStream stream);

  TextMarkerBlock getParent();

  TextMarkerMatcher getMatcher();

}
