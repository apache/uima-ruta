package org.apache.uima.tm.textmarker.action;

import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.tm.textmarker.kernel.TextMarkerBlock;
import org.apache.uima.tm.textmarker.kernel.TextMarkerEnvironment;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.expression.TextMarkerExpression;
import org.apache.uima.tm.textmarker.kernel.expression.bool.BooleanExpression;
import org.apache.uima.tm.textmarker.kernel.expression.list.ListExpression;
import org.apache.uima.tm.textmarker.kernel.expression.number.NumberExpression;
import org.apache.uima.tm.textmarker.kernel.expression.string.StringExpression;
import org.apache.uima.tm.textmarker.kernel.expression.type.TypeExpression;
import org.apache.uima.tm.textmarker.kernel.rule.RuleMatch;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class AddAction extends AbstractTextMarkerAction {

  private String var;

  private List<TextMarkerExpression> elements;

  public AddAction(String var, List<TextMarkerExpression> list) {
    super();
    this.var = var;
    this.elements = list;
  }

  public String getListExpr() {
    return var;
  }

  public List<TextMarkerExpression> getElements() {
    return elements;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void execute(RuleMatch match, TextMarkerRuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    TextMarkerBlock parent = element.getParent();
    TextMarkerEnvironment environment = parent.getEnvironment();
    List list = environment.getVariableValue(var, List.class);
    // Class<?> vtype = environment.getVariableType(var);
    Class<?> vgtype = environment.getVariableGenericType(var);
    for (TextMarkerExpression each : elements) {
      if (each instanceof ListExpression) {
        ListExpression l = (ListExpression) each;
        list.addAll(l.getList(parent));
      } else if (vgtype.equals(Boolean.class) && each instanceof BooleanExpression) {
        list.add(((BooleanExpression) each).getBooleanValue(parent));
      } else if (vgtype.equals(Integer.class) && each instanceof NumberExpression) {
        list.add(((NumberExpression) each).getIntegerValue(parent));
      } else if (vgtype.equals(Double.class) && each instanceof NumberExpression) {
        list.add(((NumberExpression) each).getDoubleValue(parent));
      } else if (vgtype.equals(Type.class) && each instanceof TypeExpression) {
        list.add(((TypeExpression) each).getType(parent));
      } else if (vgtype.equals(String.class) && each instanceof StringExpression) {
        list.add(((StringExpression) each).getStringValue(parent));
      }
    }
  }
}
