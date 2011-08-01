package org.apache.uima.tm.textmarker.action;

import java.util.List;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.expression.string.StringExpression;
import org.apache.uima.tm.textmarker.kernel.rule.RuleMatch;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class ReplaceAction extends AbstractTextMarkerAction {

  public StringExpression getReplacement() {
    return replacement;
  }

  private final StringExpression replacement;

  public ReplaceAction(StringExpression replacement) {
    super();
    this.replacement = replacement;
  }

  @Override
  public void execute(RuleMatch match, TextMarkerRuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    AnnotationFS matchedAnnotation = match.getMatchedAnnotation(stream, null);
    List<TextMarkerBasic> annotationsInWindow = stream.getBasicsInWindow(matchedAnnotation);
    boolean replaced = false;
    for (TextMarkerBasic textMarkerBasic : annotationsInWindow) {
      textMarkerBasic.setReplacement(replaced ? "" : replacement
              .getStringValue(element.getParent()));
      replaced = true;
    }
  }

}
