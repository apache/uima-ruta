package org.apache.uima.tm.textmarker.action;

import org.apache.uima.cas.Type;
import org.apache.uima.tm.textmarker.kernel.TextMarkerBlock;
import org.apache.uima.tm.textmarker.kernel.TextMarkerEnvironment;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.expression.TextMarkerExpression;
import org.apache.uima.tm.textmarker.kernel.expression.bool.BooleanExpression;
import org.apache.uima.tm.textmarker.kernel.expression.number.NumberExpression;
import org.apache.uima.tm.textmarker.kernel.expression.string.StringExpression;
import org.apache.uima.tm.textmarker.kernel.expression.type.TypeExpression;
import org.apache.uima.tm.textmarker.kernel.rule.RuleMatch;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class AssignAction extends AbstractTextMarkerAction {

  private String var;

  private TextMarkerExpression expression;

  public AssignAction(String var, TextMarkerExpression e) {
    super();
    this.var = var;
    this.expression = e;
  }

  @Override
  public void execute(RuleMatch match, TextMarkerRuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    TextMarkerBlock parent = element.getParent();
    TextMarkerEnvironment environment = parent.getEnvironment();
    Class<?> clazz = environment.getVariableType(var);
    if (clazz.equals(Integer.class) && expression instanceof NumberExpression) {
      int v = ((NumberExpression) expression).getIntegerValue(parent);
      environment.setVariableValue(var, v);
    } else if (clazz.equals(Double.class) && expression instanceof NumberExpression) {
      double v = ((NumberExpression) expression).getDoubleValue(parent);
      environment.setVariableValue(var, v);
    } else if (clazz.equals(Type.class) && expression instanceof TypeExpression) {
      Type v = ((TypeExpression) expression).getType(parent);
      environment.setVariableValue(var, v);
    } else if (clazz.equals(Boolean.class) && expression instanceof BooleanExpression) {
      boolean v = ((BooleanExpression) expression).getBooleanValue(parent);
      environment.setVariableValue(var, v);
    } else if (clazz.equals(String.class) && expression instanceof StringExpression) {
      String v = ((StringExpression) expression).getStringValue(parent);
      environment.setVariableValue(var, v);
    }
  }

  public String getVar() {
    return var;
  }

  public TextMarkerExpression getExpression() {
    return expression;
  }

}
