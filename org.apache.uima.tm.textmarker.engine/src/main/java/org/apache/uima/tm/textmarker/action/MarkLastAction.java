package org.apache.uima.tm.textmarker.action;

import java.util.List;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.expression.type.TypeExpression;
import org.apache.uima.tm.textmarker.kernel.rule.RuleMatch;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class MarkLastAction extends AbstractMarkAction {

  public MarkLastAction(TypeExpression type) {
    super(type);
  }

  @Override
  public void execute(RuleMatch match, TextMarkerRuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    AnnotationFS matchedAnnotation = match.getMatchedAnnotation(stream, null);
    List<TextMarkerBasic> list = stream.getBasicsInWindow(matchedAnnotation);
    if (!list.isEmpty()) {
      TextMarkerBasic last = list.get(list.size() - 1);
      createAnnotation(last, element, stream, last);
    }
  }

}
