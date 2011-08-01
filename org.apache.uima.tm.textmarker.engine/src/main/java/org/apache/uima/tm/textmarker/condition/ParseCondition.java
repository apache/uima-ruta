package org.apache.uima.tm.textmarker.condition;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.textmarker.kernel.TextMarkerEnvironment;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.rule.EvaluatedCondition;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class ParseCondition extends AbstractTextMarkerCondition {

  private final String var;

  public ParseCondition(String var) {
    super();
    this.var = var;
  }

  @Override
  public EvaluatedCondition eval(TextMarkerBasic basic, Type matchedType,
          TextMarkerRuleElement element, TextMarkerStream stream, InferenceCrowd crowd) {
    AnnotationFS annotation = stream.expandAnchor(basic, matchedType);
    String text = annotation.getCoveredText();
    TextMarkerEnvironment env = element.getParent().getEnvironment();
    Class<?> type = env.getVariableType(var);
    try {
      if (Integer.class.equals(type)) {
        text = normalizeNumber(text);
        int value = Integer.valueOf(text);
        env.setVariableValue(var, value);
        return new EvaluatedCondition(this, true);
      } else if (Double.class.equals(type)) {
        text = normalizeNumber(text);
        double value = Double.valueOf(text);
        env.setVariableValue(var, value);
        return new EvaluatedCondition(this, true);
      } else if (String.class.equals(type)) {
        env.setVariableValue(var, text);
        return new EvaluatedCondition(this, true);
      } else if (Boolean.class.equals(type)) {
        env.setVariableValue(var, !"".equals(text));
        return new EvaluatedCondition(this, true);
      } else if (Type.class.equals(type)) {
        Type value = stream.getCas().getTypeSystem().getType(text);
        env.setVariableValue(var, value);
        return new EvaluatedCondition(this, true);
      } else {
        return new EvaluatedCondition(this, false);
      }
    } catch (Exception e) {
      return new EvaluatedCondition(this, false);
    }
  }

  private String normalizeNumber(String text) {
    String[] split = text.split("[,]");
    if (split.length == 2) {
      return text.replaceAll(",", ".");
    }
    return text;
  }

  public String getVar() {
    return var;
  }

}
