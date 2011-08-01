package org.apache.uima.tm.textmarker.condition;

import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.expression.list.TypeListExpression;
import org.apache.uima.tm.textmarker.kernel.expression.type.TypeExpression;
import org.apache.uima.tm.textmarker.kernel.rule.EvaluatedCondition;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class PartOfNeqCondition extends TypeSentiveCondition {

  public PartOfNeqCondition(TypeExpression type) {
    super(type);
  }

  public PartOfNeqCondition(TypeListExpression list) {
    super(list);
  }

  @Override
  public EvaluatedCondition eval(TextMarkerBasic basic, Type matchedType,
          TextMarkerRuleElement element, TextMarkerStream stream, InferenceCrowd crowd) {
    if (!isWorkingOnList()) {
      Type t = type.getType(element.getParent());
      boolean result = check(basic, matchedType, stream, t);
      return new EvaluatedCondition(this, result);
    } else {
      boolean result = false;
      List<Type> types = getList().getList(element.getParent());
      for (Type t : types) {
        result |= check(basic, matchedType, stream, t);
        if (result == true) {
          break;
        }
      }
      return new EvaluatedCondition(this, result);
    }
  }

  private boolean check(TextMarkerBasic basic, Type matchedType, TextMarkerStream stream, Type t) {
    AnnotationFS expandAnchor = stream.expandAnchor(basic, matchedType);
    stream.moveTo(basic);
    while (stream.isValid()) {
      TextMarkerBasic each = (TextMarkerBasic) stream.get();
      AnnotationFS afs = each.getType(t.getName());
      if (afs != null
              && afs.getType().equals(t)
              && ((afs.getBegin() < expandAnchor.getBegin() && afs.getEnd() > expandAnchor.getEnd())
                      || (afs.getBegin() == expandAnchor.getBegin() && afs.getEnd() > expandAnchor
                              .getEnd()) || (afs.getBegin() < expandAnchor.getBegin() && afs
                      .getEnd() == expandAnchor.getEnd()))) {
        return true;
      }
      stream.moveToPrevious();
    }
    return false;
  }

}
