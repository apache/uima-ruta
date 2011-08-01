package org.apache.uima.tm.textmarker.action;

import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.expression.number.NumberExpression;
import org.apache.uima.tm.textmarker.kernel.expression.type.TypeExpression;
import org.apache.uima.tm.textmarker.kernel.rule.RuleElementMatch;
import org.apache.uima.tm.textmarker.kernel.rule.RuleMatch;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class ExpandAction extends MarkAction {

  public ExpandAction(TypeExpression type, List<NumberExpression> list) {
    super(type, null, list);
  }

  @Override
  public void execute(RuleMatch match, TextMarkerRuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    List<Integer> indexList = getIndexList(match, element);
    AnnotationFS matchedAnnotation = match.getMatchedAnnotation(stream, indexList);

    List<RuleElementMatch> matchInfo = match.getMatchInfo(element);
    if (matchInfo == null || matchInfo.isEmpty()) {
      return;
    }
    RuleElementMatch ruleElementMatch = matchInfo.get(0);
    List<AnnotationFS> textsMatched = ruleElementMatch.getTextsMatched();
    AnnotationFS first = textsMatched.get(0);
    TextMarkerBasic anchor = stream.getFirstBasicInWindow(first);
    Type t = type.getType(element.getParent());
    stream.removeAnnotation(anchor, t);
    createAnnotation(anchor, element, stream, matchedAnnotation);
  }

}
