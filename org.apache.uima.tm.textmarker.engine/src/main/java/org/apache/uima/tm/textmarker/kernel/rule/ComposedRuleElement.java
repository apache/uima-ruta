package org.apache.uima.tm.textmarker.kernel.rule;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.textmarker.kernel.TextMarkerBlock;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.rule.quantifier.RuleElementQuantifier;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class ComposedRuleElement extends AbstractRuleElement {

  private List<RuleElement> elements;

  private TextMarkerBlock parent;

  public ComposedRuleElement(List<RuleElement> elements, RuleElementQuantifier quantifier,
          TextMarkerBlock parent) {
    super(quantifier);
    this.elements = elements;
    this.parent = parent;

  }

  public void apply(RuleMatch matchInfos, TextMarkerStream symbolStream, InferenceCrowd crowd) {
    for (RuleElement each : elements) {
      each.apply(matchInfos, symbolStream, crowd);
    }
  }

  public List<TextMarkerBasic> getAnchors(TextMarkerStream symbolStream) {
    return elements.get(0).getAnchors(symbolStream);
  }

  public FSIterator<AnnotationFS> getAnchors2(TextMarkerStream symbolStream) {
    return elements.get(0).getAnchors2(symbolStream);
  }

  public TextMarkerMatcher getMatcher() {
    return elements.get(0).getMatcher();
  }

  public TextMarkerBlock getParent() {
    return parent;
  }

  public RuleElementMatch match(TextMarkerBasic anchor, TextMarkerStream stream,
          InferenceCrowd crowd) {
    RuleElementMatch result = new RuleElementMatch(this);
    TextMarkerBasic current = anchor;
    List<RuleElementMatch> innerMatches = new ArrayList<RuleElementMatch>();
    for (RuleElement each : elements) {
      RuleElementMatch match = each.match(current, stream, crowd);
      current = stream.nextAnnotation(match);
      innerMatches.add(match);
    }
    result.setInnerMatches(innerMatches);
    return result;
  }

  public List<RuleElement> getElements() {
    return elements;
  }
}
