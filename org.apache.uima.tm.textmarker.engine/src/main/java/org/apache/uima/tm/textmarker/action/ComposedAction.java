package org.apache.uima.tm.textmarker.action;

import java.util.List;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.rule.RuleMatch;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class ComposedAction extends AbstractTextMarkerAction {

  private final List<AbstractTextMarkerAction> actions;

  public ComposedAction(List<AbstractTextMarkerAction> actions) {
    super();
    this.actions = actions;
  }

  @Override
  public void execute(RuleMatch match, TextMarkerRuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    for (AbstractTextMarkerAction each : actions) {
      crowd.beginVisit(each, null);
      each.execute(match, element, stream, crowd);
      crowd.endVisit(each, null);
    }
  }

  public List<AbstractTextMarkerAction> getActions() {
    return actions;
  }

}
