package org.apache.uima.tm.textmarker.verbalize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.cas.Type;
import org.apache.uima.tm.textmarker.action.AbstractTextMarkerAction;
import org.apache.uima.tm.textmarker.condition.AbstractTextMarkerCondition;
import org.apache.uima.tm.textmarker.kernel.TextMarkerBlock;
import org.apache.uima.tm.textmarker.kernel.TextMarkerElement;
import org.apache.uima.tm.textmarker.kernel.expression.TextMarkerExpression;
import org.apache.uima.tm.textmarker.kernel.extensions.ITextMarkerExtension;
import org.apache.uima.tm.textmarker.kernel.rule.RuleElement;


public class TextMarkerVerbalizer {

  private Map<Class<?>, ITextMarkerExtension> externalVerbalizers = new HashMap<Class<?>, ITextMarkerExtension>();

  private ActionVerbalizer actionVerbalizer;

  private ConditionVerbalizer conditionVerbalizer;

  private ExpressionVerbalizer expressionVerbalizer;

  private ScriptVerbalizer scriptVerbalizer;

  private VerbalizerUtils verbalizerUtils;

  public TextMarkerVerbalizer() {
    super();
    actionVerbalizer = new ActionVerbalizer(this);
    conditionVerbalizer = new ConditionVerbalizer(this);
    expressionVerbalizer = new ExpressionVerbalizer(this);
    scriptVerbalizer = new ScriptVerbalizer(this);
    verbalizerUtils = new VerbalizerUtils(this);
  }

  public void addExternalVerbalizers(ITextMarkerExtension verbalizer) {
    Class<?>[] extensions = verbalizer.extensions();
    for (Class<?> eachClass : extensions) {
      externalVerbalizers.put(eachClass, verbalizer);
    }
  }

  public String verbalize(TextMarkerElement element) {
    if (externalVerbalizers.keySet().contains(element.getClass())) {
      return externalVerbalizers.get(element.getClass()).verbalize(element, this);
    } else if (element instanceof AbstractTextMarkerAction) {
      return actionVerbalizer.verbalize((AbstractTextMarkerAction) element);
    } else if (element instanceof AbstractTextMarkerCondition) {
      return conditionVerbalizer.verbalize((AbstractTextMarkerCondition) element);
    } else if (element instanceof TextMarkerExpression) {
      return expressionVerbalizer.verbalize((TextMarkerExpression) element);
    } else {
      return scriptVerbalizer.verbalize(element);
    }
  }

  public String verbalizeName(TextMarkerElement element) {
    if (externalVerbalizers.keySet().contains(element.getClass())) {
      return externalVerbalizers.get(element.getClass()).verbalizeName(element);
    } else if (element instanceof AbstractTextMarkerAction) {
      return actionVerbalizer.verbalizeName((AbstractTextMarkerAction) element);
    } else if (element instanceof AbstractTextMarkerCondition) {
      return conditionVerbalizer.verbalizeName((AbstractTextMarkerCondition) element);
    }
    return element.getClass().getSimpleName();
  }

  public String verbalize(TextMarkerBlock block, boolean withElements) {
    return scriptVerbalizer.verbalizeBlock(block, withElements);
  }

  public String verbalize(RuleElement element) {
    return scriptVerbalizer.verbalizeRuleElement(element);
  }

  public String verbalizeType(Type type) {
    if (type.getName().equals("uima.tcas.DocumentAnnotation")) {
      return "Document";
    } else {
      return type.getShortName();
    }
  }

  public String verbalizeList(List<?> list) {
    return verbalizerUtils.verbalizeList(list);
  }

  public String verbalizeTypeList(List<Type> list) {
    return verbalizerUtils.verbalizeTypeList(list);
  }

  public String verbalizeExpressionList(List<? extends TextMarkerExpression> list) {
    return verbalizerUtils.verbalizeExpressionList(list);
  }

}
