package org.apache.uima.tm.textmarker.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.ListUtils;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.expression.bool.BooleanExpression;
import org.apache.uima.tm.textmarker.kernel.expression.list.ListExpression;
import org.apache.uima.tm.textmarker.kernel.rule.RuleMatch;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class MergeAction extends AbstractTextMarkerAction {

  private List<ListExpression> lists;

  private BooleanExpression unionExpr;

  private String target;

  public MergeAction(BooleanExpression union, String target, List<ListExpression> list) {
    super();
    this.unionExpr = union;
    this.target = target;
    this.lists = list;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void execute(RuleMatch match, TextMarkerRuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    boolean union = unionExpr.getBooleanValue(element.getParent());
    List<Object> list = new ArrayList<Object>();
    if (union) {
      for (ListExpression<Object> each : lists) {
        list.addAll(each.getList(element.getParent()));
      }
    } else {
      List<Object> lastList = null;
      for (int i = 1; i < lists.size(); i++) {
        if (lastList != null) {
          lastList = ListUtils.intersection(lastList, lists.get(i).getList(element.getParent()));
        } else {
          lastList = ListUtils.intersection(lists.get(i - 1).getList(element.getParent()), lists
                  .get(i).getList(element.getParent()));
        }
      }
      list = lastList;
    }
    if (list != null) {
      element.getParent().getEnvironment().setVariableValue(target, list);
    }
  }

  public BooleanExpression getUnion() {
    return unionExpr;
  }

  public List<ListExpression> getLists() {
    return lists;
  }

  public String getTarget() {
    return target;
  }

}
