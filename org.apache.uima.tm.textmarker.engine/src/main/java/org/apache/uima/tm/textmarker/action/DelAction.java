package org.apache.uima.tm.textmarker.action;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.rule.RuleElementMatch;
import org.apache.uima.tm.textmarker.kernel.rule.RuleMatch;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class DelAction extends AbstractTextMarkerAction {

  public DelAction() {
    super();
  }

  @Override
  public void execute(RuleMatch match, TextMarkerRuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    for (RuleElementMatch each : match.getMatchInfo(element)) {
      for (AnnotationFS eachAnnotation : each.getTextsMatched()) {
        if (eachAnnotation instanceof TextMarkerBasic) {
          TextMarkerBasic basic = (TextMarkerBasic) eachAnnotation;
          basic.setReplacement("");
        }
      }
    }
  }

}
