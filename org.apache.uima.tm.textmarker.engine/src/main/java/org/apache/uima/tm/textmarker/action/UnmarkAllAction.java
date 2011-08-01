package org.apache.uima.tm.textmarker.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.expression.list.TypeListExpression;
import org.apache.uima.tm.textmarker.kernel.expression.type.TypeExpression;
import org.apache.uima.tm.textmarker.kernel.rule.RuleElementMatch;
import org.apache.uima.tm.textmarker.kernel.rule.RuleMatch;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class UnmarkAllAction extends TypeSensitiveAction {

  private TypeListExpression list;

  public UnmarkAllAction(TypeExpression type, TypeListExpression list) {
    super(type);
    this.list = list;
  }

  @Override
  public void execute(RuleMatch match, TextMarkerRuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    List<RuleElementMatch> matchInfo = match.getMatchInfo(element);
    if (matchInfo == null || matchInfo.isEmpty()) {
      return;
    }
    RuleElementMatch ruleElementMatch = matchInfo.get(0);
    List<AnnotationFS> textsMatched = ruleElementMatch.getTextsMatched();
    AnnotationFS first = textsMatched.get(0);
    TextMarkerBasic firstBasicInWindow = stream.getFirstBasicInWindow(first);
    Type t = type.getType(element.getParent());
    TypeSystem typeSystem = stream.getCas().getTypeSystem();
    List<Type> properlySubsumedTypes = typeSystem.getProperlySubsumedTypes(t);
    List<Type> retainList = new ArrayList<Type>();

    if (list != null) {
      retainList = list.getList(element.getParent());
    }

    for (Type type : properlySubsumedTypes) {
      boolean keep = false;
      for (Type retainType : retainList) {
        if (typeSystem.subsumes(retainType, type)) {
          keep = true;
          break;
        }
      }
      if (!keep) {
        stream.removeAnnotation(firstBasicInWindow, type);
      }
    }
  }

}
