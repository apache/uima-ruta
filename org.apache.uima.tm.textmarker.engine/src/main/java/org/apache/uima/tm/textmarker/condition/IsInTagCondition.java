package org.apache.uima.tm.textmarker.condition;

import java.util.List;
import java.util.Map;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.expression.string.StringExpression;
import org.apache.uima.tm.textmarker.kernel.rule.EvaluatedCondition;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class IsInTagCondition extends TerminalTextMarkerCondition {

  private final StringExpression tag;

  private final List<StringExpression> tags;

  private final List<StringExpression> values;

  public IsInTagCondition(StringExpression tag, List<StringExpression> tags,
          List<StringExpression> values) {
    super();
    this.tag = tag;
    this.tags = tags;
    this.values = values;
  }

  @Override
  public EvaluatedCondition eval(TextMarkerBasic basic, Type matchedType,
          TextMarkerRuleElement element, TextMarkerStream stream, InferenceCrowd crowd) {
    boolean result = true;
    AnnotationFS annotation = stream.expandAnchor(basic, matchedType);
    for (TextMarkerBasic each : stream.getBasicsInWindow(annotation)) {
      Map<String, String> tags = each.getTags();
      result &= tags.containsKey(tag.getStringValue(element.getParent()));
    }
    return new EvaluatedCondition(this, result);
  }

  public StringExpression getTag() {
    return tag;
  }

  public List<StringExpression> getTags() {
    return tags;
  }

  public List<StringExpression> getValues() {
    return values;
  }

}
