package org.apache.uima.tm.textmarker.condition;

import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.expression.type.TypeExpression;
import org.apache.uima.tm.textmarker.kernel.rule.EvaluatedCondition;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class LastCondition extends TypeSentiveCondition {

  public LastCondition(TypeExpression type) {
    super(type);
  }

  @Override
  public EvaluatedCondition eval(TextMarkerBasic basic, Type matchedType,
          TextMarkerRuleElement element, TextMarkerStream stream, InferenceCrowd crowd) {
    AnnotationFS expand = stream.expandAnchor(basic, matchedType);
    List<TextMarkerBasic> annotationsInWindow = stream.getBasicsInWindow(expand);
    TextMarkerBasic textMarkerBasic = annotationsInWindow.get(annotationsInWindow.size() - 1);
    boolean subsumes = stream.getJCas().getTypeSystem().subsumes(type.getType(element.getParent()),
            textMarkerBasic.getType());
    return new EvaluatedCondition(this, subsumes);
  }

}
