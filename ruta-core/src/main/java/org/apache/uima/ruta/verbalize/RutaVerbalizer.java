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

package org.apache.uima.ruta.verbalize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.cas.Type;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaElement;
import org.apache.uima.ruta.action.AbstractRutaAction;
import org.apache.uima.ruta.condition.AbstractRutaCondition;
import org.apache.uima.ruta.expression.IRutaExpression;
import org.apache.uima.ruta.expression.RutaExpression;
import org.apache.uima.ruta.extensions.IRutaExtension;
import org.apache.uima.ruta.rule.ComposedRuleElement;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RutaRuleElement;

public class RutaVerbalizer {

  private Map<Class<?>, IRutaExtension> externalVerbalizers = new HashMap<Class<?>, IRutaExtension>();

  private ActionVerbalizer actionVerbalizer;

  private ConditionVerbalizer conditionVerbalizer;

  private ExpressionVerbalizer expressionVerbalizer;

  private ScriptVerbalizer scriptVerbalizer;

  private VerbalizerUtils verbalizerUtils;

  public RutaVerbalizer() {
    super();
    actionVerbalizer = new ActionVerbalizer(this);
    conditionVerbalizer = new ConditionVerbalizer(this);
    expressionVerbalizer = new ExpressionVerbalizer(this);
    scriptVerbalizer = new ScriptVerbalizer(this);
    verbalizerUtils = new VerbalizerUtils(this);
  }

  public void addExternalVerbalizers(IRutaExtension verbalizer) {
    Class<?>[] extensions = verbalizer.extensions();
    for (Class<?> eachClass : extensions) {
      externalVerbalizers.put(eachClass, verbalizer);
    }
  }

  public String verbalize(Object element) {
    if (externalVerbalizers.keySet().contains(element.getClass()) && element instanceof RutaElement) {
      return externalVerbalizers.get(element.getClass()).verbalize((RutaElement) element, this);
    } else if (element instanceof AbstractRutaAction) {
      return actionVerbalizer.verbalize((AbstractRutaAction) element);
    } else if (element instanceof AbstractRutaCondition) {
      return conditionVerbalizer.verbalize((AbstractRutaCondition) element);
    } else if (element instanceof IRutaExpression) {
      return expressionVerbalizer.verbalize((RutaExpression) element);
    } else if(element instanceof RutaElement){
      return scriptVerbalizer.verbalize((RutaElement) element);
    } else if(element instanceof RutaBlock){
      return verbalize((RutaBlock) element, false);
    } else {
      return element.getClass().getSimpleName();
    }
  }
  
  public String verbalizeName(RutaElement element) {
    if (externalVerbalizers.keySet().contains(element.getClass())) {
      return externalVerbalizers.get(element.getClass()).verbalizeName(element);
    } else if (element instanceof AbstractRutaAction) {
      return actionVerbalizer.verbalizeName((AbstractRutaAction) element);
    } else if (element instanceof AbstractRutaCondition) {
      return conditionVerbalizer.verbalizeName((AbstractRutaCondition) element);
    }
    return element.getClass().getSimpleName();
  }

  public String verbalize(RutaBlock block, boolean withElements) {
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

  public String verbalizeExpressionList(List<? extends IRutaExpression> list) {
    return verbalizerUtils.verbalizeExpressionList(list);
  }

  public String verbalizeMatcher(RutaRuleElement tmre) {
    return scriptVerbalizer.verbalizeMatcher(tmre);
  }

  public String verbalizeComposed(ComposedRuleElement cre) {
    return scriptVerbalizer.verbalizeComposed(cre);
  }


}
