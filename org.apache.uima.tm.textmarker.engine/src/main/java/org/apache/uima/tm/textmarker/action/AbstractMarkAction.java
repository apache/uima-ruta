package org.apache.uima.tm.textmarker.action;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.expression.type.TypeExpression;
import org.apache.uima.tm.textmarker.kernel.rule.RuleMatch;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;


public abstract class AbstractMarkAction extends TypeSensitiveAction {

  public AbstractMarkAction(TypeExpression type) {
    super(type);
  }

  protected void createAnnotation(RuleMatch match, TextMarkerRuleElement element,
          TextMarkerStream stream, AnnotationFS matchedAnnotation) {
    TextMarkerBasic first = stream.getFirstBasicInWindow(matchedAnnotation);
    if (first == null) {
      first = match.getFirstBasic();
    }
    createAnnotation(first, element, stream, matchedAnnotation);
  }

  protected Annotation createAnnotation(TextMarkerBasic anchor, TextMarkerRuleElement element,
          TextMarkerStream stream, AnnotationFS matchedAnnotation) {
    Type t = type.getType(element.getParent());
    AnnotationFS newAnnotationFS = stream.getCas().createAnnotation(t,
            matchedAnnotation.getBegin(), matchedAnnotation.getEnd());
    Annotation newAnnotation = null;
    if (newAnnotationFS instanceof Annotation) {
      newAnnotation = (Annotation) newAnnotationFS;
      newAnnotation.addToIndexes();
    } else {
      return null;
    }
    stream.addAnnotation(anchor, newAnnotation);
    return newAnnotation;
  }

  @Override
  public String toString() {
    return super.toString() + "," + type.getClass().getSimpleName();
  }

}
