package org.apache.uima.tm.textmarker.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.expression.string.StringExpression;
import org.apache.uima.tm.textmarker.kernel.rule.RuleMatch;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class GetListAction extends AbstractTextMarkerAction {

  private static final String TYPES = "Types";

  private static final String TYPES_AT_END = "Types:End";

  private static final String TYPES_AT_BEGIN = "Types:Begin";

  private String var;

  private StringExpression opExpr;

  public GetListAction(String var, StringExpression op) {
    super();
    this.var = var;
    this.opExpr = op;
  }

  @Override
  public void execute(RuleMatch match, TextMarkerRuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    String op = opExpr.getStringValue(element.getParent());
    List<Type> list = new ArrayList<Type>();
    int indexOf = match.getRule().getElements().indexOf(element);
    List<Integer> indexes = new ArrayList<Integer>();
    indexes.add(indexOf+1);
    AnnotationFS matched = match.getMatchedAnnotation(stream, indexes);
    TextMarkerBasic firstBasic = stream.getFirstBasicInWindow(matched);
    Collection<AnnotationFS> anchors = firstBasic.getAnchors();
    if (TYPES_AT_BEGIN.equals(op)) {
      for (AnnotationFS each : anchors) {
        list.add(each.getType());
      }
    } else {
      Type annotationType = stream.getCas().getAnnotationType();
      if (TYPES_AT_END.equals(op)) {
        List<AnnotationFS> inWindow = stream.getAnnotationsInWindow(matched, annotationType);
        for (AnnotationFS each : inWindow) {
          if (each.getEnd() == matched.getEnd()) {
            list.add(each.getType());
          }
        }
      } else if (TYPES.equals(op)) {
        List<AnnotationFS> inWindow = stream.getAnnotationsInWindow(matched, annotationType);
        for (AnnotationFS each : inWindow) {
          if (each.getBegin() == matched.getBegin() && each.getEnd() == matched.getEnd()) {
            list.add(each.getType());
          }
          if(each.getBegin() > matched.getBegin() || each.getEnd() < matched.getEnd()) {
            break;
          }
        }
      }
    }
    element.getParent().getEnvironment().setVariableValue(var, list);
  }

}
