/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.uima.textmarker.verbalize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.cas.Type;
import org.apache.uima.textmarker.TextMarkerBlock;
import org.apache.uima.textmarker.TextMarkerElement;
import org.apache.uima.textmarker.action.AbstractTextMarkerAction;
import org.apache.uima.textmarker.condition.AbstractTextMarkerCondition;
import org.apache.uima.textmarker.expression.TextMarkerExpression;
import org.apache.uima.textmarker.extensions.ITextMarkerExtension;
import org.apache.uima.textmarker.rule.ComposedRuleElement;
import org.apache.uima.textmarker.rule.RuleElement;
import org.apache.uima.textmarker.rule.TextMarkerRuleElement;

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

  public String verbalizeMatcher(TextMarkerRuleElement tmre) {
    return scriptVerbalizer.verbalizeMatcher(tmre);
  }

  public String verbalizeComposed(ComposedRuleElement cre) {
    return scriptVerbalizer.verbalizeComposed(cre);
  }

}
