package org.apache.uima.tm.textmarker.action;

import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.expression.type.TypeExpression;
import org.apache.uima.tm.textmarker.kernel.rule.RuleElementMatch;
import org.apache.uima.tm.textmarker.kernel.rule.RuleMatch;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class UnmarkAction extends TypeSensitiveAction {

  public UnmarkAction(TypeExpression type) {
    super(type);
  }

  @Override
  public void execute(RuleMatch match, TextMarkerRuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    List<RuleElementMatch> matchInfo = match.getMatchInfo(element);
    if (matchInfo == null || matchInfo.isEmpty()) {
      return;
    }
    RuleElementMatch ruleElementMatch = matchInfo.get(0);
    List<AnnotationFS> textsMatched = ruleElementMatch.getTextsMatched();
    AnnotationFS first = textsMatched.get(0);
    TextMarkerBasic firstBasicInWindow = stream.getFirstBasicInWindow(first);
    Type t = type.getType(element.getParent());
    stream.removeAnnotation(firstBasicInWindow, t);
  }

}
