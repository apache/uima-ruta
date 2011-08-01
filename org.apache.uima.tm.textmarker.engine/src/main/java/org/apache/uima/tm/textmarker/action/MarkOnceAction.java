package org.apache.uima.tm.textmarker.action;

import java.util.List;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.expression.number.NumberExpression;
import org.apache.uima.tm.textmarker.kernel.expression.type.TypeExpression;
import org.apache.uima.tm.textmarker.kernel.rule.RuleMatch;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class MarkOnceAction extends MarkAction {

  public MarkOnceAction(TypeExpression type, NumberExpression scoreValue,
          List<NumberExpression> list) {
    super(type, scoreValue, list);
  }

  @Override
  public void execute(RuleMatch match, TextMarkerRuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    List<Integer> indexList = getIndexList(match, element);
    AnnotationFS matchedAnnotation = match.getMatchedAnnotation(stream, indexList);
    CAS cas = stream.getCas();
    if (matchedAnnotation == null)
      return;
    Type t = type.getType(element.getParent());
    AnnotationFS createAnnotation = cas.createAnnotation(t, matchedAnnotation.getBegin(),
            matchedAnnotation.getEnd());
    boolean contains = false;
    FSIterator<AnnotationFS> iterator = cas.getAnnotationIndex(t).iterator(createAnnotation);
    while (iterator.isValid()
            && ((AnnotationFS) iterator.get()).getEnd() == createAnnotation.getEnd()) {
      AnnotationFS a = (AnnotationFS) iterator.get();
      if (a.getBegin() == createAnnotation.getBegin() && a.getEnd() == createAnnotation.getEnd()
              && a.getType().getName().equals(createAnnotation.getType().getName())) {
        contains = true;
        break;
      }
      iterator.moveToNext();
    }
    if (!contains) {
      super.execute(match, element, stream, crowd);
    }
  }

}
