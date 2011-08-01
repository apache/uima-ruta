package org.apache.uima.tm.textmarker.condition;

import java.util.List;

import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.expression.list.TypeListExpression;
import org.apache.uima.tm.textmarker.kernel.expression.type.TypeExpression;
import org.apache.uima.tm.textmarker.kernel.rule.EvaluatedCondition;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class AfterCondition extends TypeSentiveCondition {

  public AfterCondition(TypeExpression type) {
    super(type);
  }

  public AfterCondition(TypeListExpression list) {
    super(list);
  }

  @Override
  public EvaluatedCondition eval(TextMarkerBasic basic, Type matchedType,
          TextMarkerRuleElement element, TextMarkerStream stream, InferenceCrowd crowd) {
    if (!isWorkingOnList()) {
      Type t = type.getType(element.getParent());
      boolean result = check(basic, stream, t);
      return new EvaluatedCondition(this, result);
    } else {
      boolean result = false;
      List<Type> types = getList().getList(element.getParent());
      for (Type t : types) {
        result |= check(basic, stream, t);
        if (result == true) {
          break;
        }
      }
      return new EvaluatedCondition(this, result);
    }
  }

  private boolean check(TextMarkerBasic basic, TextMarkerStream stream, Type t) {
    boolean result = false;
    FSIterator<AnnotationFS> it = stream.getCas().getAnnotationIndex(t).iterator(basic);
    if (!it.isValid()) {
      it.moveToLast();
    }
    while (it.isValid()) {
      AnnotationFS a = (AnnotationFS) it.get();
      if (a.getBegin() <= basic.getBegin()) {
        result = true;
        break;
      }
      it.moveToPrevious();
    }
    return result;
  }

}
