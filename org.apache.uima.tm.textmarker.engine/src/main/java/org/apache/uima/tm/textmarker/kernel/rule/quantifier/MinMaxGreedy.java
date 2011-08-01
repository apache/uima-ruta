package org.apache.uima.tm.textmarker.kernel.rule.quantifier;

import java.util.List;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.expression.number.NumberExpression;
import org.apache.uima.tm.textmarker.kernel.expression.number.SimpleNumberExpression;
import org.apache.uima.tm.textmarker.kernel.rule.RuleElement;
import org.apache.uima.tm.textmarker.kernel.rule.RuleElementMatch;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class MinMaxGreedy implements RuleElementQuantifier {

  private NumberExpression min;

  private NumberExpression max;

  public MinMaxGreedy(NumberExpression min, NumberExpression max, boolean interval) {
    super();
    if (!interval) {
      this.min = min;
      this.max = min;
    } else {
      this.min = min;
      this.max = max;
      if (this.max == null) {
        this.max = new SimpleNumberExpression(Integer.MAX_VALUE);
      }
    }
  }

  public boolean continueMatch(int index, List<RuleElement> elements, TextMarkerBasic next,
          RuleElementMatch match, List<RuleElementMatch> matches, TextMarkerStream stream,
          InferenceCrowd crowd) {
    int minValue = min.getIntegerValue(elements.get(index).getParent());
    int maxValue = max.getIntegerValue(elements.get(index).getParent());
    int matchedSize = matches.size();
    return matchedSize < maxValue
            || (!match.matched() && matchedSize >= minValue && matchedSize <= maxValue);
  }

  public List<RuleElementMatch> evaluateMatches(List<RuleElementMatch> matches,
          TextMarkerStatement element, InferenceCrowd crowd) {
    int minValue = min.getIntegerValue(element.getParent());
    int maxValue = max.getIntegerValue(element.getParent());
    int matchedSize = matches.size();
    boolean result = matchedSize >= minValue && matchedSize <= maxValue;
    if (result) {
      return matches;
    } else {
      return null;
    }
  }

  public NumberExpression getMin() {
    return min;
  }

  public NumberExpression getMax() {
    return max;
  }
}
