package org.apache.uima.tm.textmarker.kernel.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.textmarker.kernel.TextMarkerBlock;
import org.apache.uima.tm.textmarker.kernel.TextMarkerEnvironment;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class TextMarkerRule extends TextMarkerStatement {

  private final List<RuleElement> elements;

  private final int id;

  public TextMarkerRule(List<RuleElement> elements, TextMarkerBlock parent, int id) {
    super(parent);
    this.elements = elements;
    this.id = id;
  }

  @Override
  public RuleApply apply(TextMarkerStream stream, InferenceCrowd crowd) {
    return apply(stream, crowd, false);
  }

  public RuleApply apply(TextMarkerStream stream, InferenceCrowd crowd, boolean remember) {
    RuleApply result = new RuleApply(this, remember);
    crowd.beginVisit(this, result);
    List<TextMarkerBasic> anchors = getAnchors(stream);
    // FSIterator<AnnotationFS> anchors = getAnchors2(stream);
    for (TextMarkerBasic each : anchors) {

      // while (anchors.isValid()) {
      TextMarkerBasic currentBasic = each;
      // TextMarkerBasic currentBasic = (TextMarkerBasic) anchors.get();
      RuleMatch ruleMatch = new RuleMatch(this);
      List<RuleElementMatch> ruleElementMatches = new ArrayList<RuleElementMatch>();
      for (int i = 0; i < elements.size(); i++) {
        RuleElement re = elements.get(i);
        // for really lazy rule elements, that don't even want to match anything
        if (re.continueMatch(i, elements, currentBasic, null, ruleElementMatches, stream)) {
          RuleElementMatch match = re.match(currentBasic, stream, crowd);
          ruleElementMatches.add(match);
          TextMarkerBasic nextBasic = stream.nextAnnotation(match);
          // AnnotationFS fs1 = anchors.get();
          if (re.continueMatch(i, elements, nextBasic, match, ruleElementMatches, stream)) {
            TextMarkerBasic lastBasic = currentBasic;
            if (nextBasic != null) {
              i--;
              currentBasic = nextBasic;
              continue;
            } else if (!match.matched()) {
              nextBasic = lastBasic;
              ruleElementMatches.remove(ruleElementMatches.size() - 1);
            }
          }
          TextMarkerBasic previousBasic = currentBasic;
          currentBasic = nextBasic;
          boolean changedMatches = ruleMatch.processMatchInfo(re, ruleElementMatches, stream);
          if (!ruleMatch.matched() /* || currentBasic == null */) {
            break;
          } else {
            if (currentBasic == null
                    && re.continueMatch(i, elements, nextBasic, match, ruleElementMatches, stream)
                    && stream.nextAnnotation(match) != null) {
              currentBasic = previousBasic;
            } else if (currentBasic == null && changedMatches && match.matched()) {
            } else if (currentBasic == null && changedMatches) {
              currentBasic = previousBasic;
            }
          }
          ruleElementMatches = new ArrayList<RuleElementMatch>();

        }
      }
      if (ruleMatch.matched()) {
        fireRule(ruleMatch, stream, crowd);
      }
      result.add(ruleMatch);
      // anchors.moveToNext();
    }
    crowd.endVisit(this, result);
    return result;
  }

  private void fireRule(RuleMatch matchInfos, TextMarkerStream symbolStream, InferenceCrowd crowd) {
    if (matchInfos.matched()) {
      Map<RuleElement, List<RuleElementMatch>> map = matchInfos.getMatchInfos();
      for (RuleElement eachElement : map.keySet()) {
        eachElement.apply(matchInfos, symbolStream, crowd);
      }
    }
  }

  public List<TextMarkerBasic> getAnchors(TextMarkerStream symbolStream) {
    return elements.get(0).getAnchors(symbolStream);
  }

  public FSIterator<AnnotationFS> getAnchors2(TextMarkerStream symbolStream) {
    return elements.get(0).getAnchors2(symbolStream);
  }

  @Override
  public String toString() {
    return elements.toString();
  }

  public final List<RuleElement> getElements() {
    return elements;
  }

  public int getId() {
    return id;
  }

  @Override
  public TextMarkerEnvironment getEnvironment() {
    return getParent().getEnvironment();
  }

}
