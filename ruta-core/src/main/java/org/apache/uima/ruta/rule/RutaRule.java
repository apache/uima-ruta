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

package org.apache.uima.ruta.rule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaConstants;
import org.apache.uima.ruta.RutaEnvironment;
import org.apache.uima.ruta.RutaStatement;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.action.AbstractRutaAction;
import org.apache.uima.ruta.block.RutaBlock;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class RutaRule extends AbstractRule {

  private ComposedRuleElement root;

  /**
   * labels of all rule elements including those in inlined rules. The values store the values of
   * overridden variables.
   */
  private Map<String, Object> labels;

  private Collection<String> ownLabels;

  public RutaRule(List<RuleElement> elements, RutaBlock parent, int id) {
    super(parent, id);
    this.root = new ComposedRuleElement(elements, null, null, null, null, parent);
    this.labels = new LinkedHashMap<>();
    this.ownLabels = new ArrayList<>();
  }

  @Override
  public RuleApply apply(RutaStream stream, InferenceCrowd crowd) {
    return apply(stream, crowd, stream.isGreedyAnchoring());
  }

  public RuleApply apply(RutaStream stream, InferenceCrowd crowd, boolean remember) {
    RuleApply ruleApply = new RuleApply(this, remember);
    MatchContext context = new MatchContext(getParent());
    prepareEnvironment(context, stream);
    crowd.beginVisit(this, ruleApply);
    RuleMatch ruleMatch = new RuleMatch(this);
    root.startMatch(ruleMatch, ruleApply, null, null, stream, crowd);
    crowd.endVisit(this, ruleApply);
    cleanupEnvironment(context, stream);
    return ruleApply;
  }

  @Override
  public String toString() {
    return root == null ? "<empty>" : root.toString();
  }

  public final List<RuleElement> getRuleElements() {
    return root.getRuleElements();
  }

  @Override
  public RutaEnvironment getEnvironment() {
    return getParent().getEnvironment();
  }

  public void setRuleElements(List<RuleElement> elements) {
    if (elements != null && elements.size() == 1
            && elements.get(0) instanceof ConjunctRulesRuleElement) {
      root = (ComposedRuleElement) elements.get(0);
    } else {
      root.setRuleElements(elements);
    }
    if (elements != null) {
      // update label map
      for (RuleElement ruleElement : elements) {
        fillLabelMap(ruleElement, true);
      }
    }

  }

  private void fillLabelMap(RuleElement ruleElement, boolean own) {
    if (!StringUtils.isBlank(ruleElement.getLabel())) {
      labels.put(ruleElement.getLabel(), null);
      if (own) {
        ownLabels.add(ruleElement.getLabel());
      }
    }
    fillLabelMapWithActions(ruleElement.getActions(), own);
    if (ruleElement instanceof ComposedRuleElement) {
      ComposedRuleElement cre = (ComposedRuleElement) ruleElement;
      List<RuleElement> ruleElements = cre.getRuleElements();
      for (RuleElement each : ruleElements) {
        fillLabelMap(each, own);
      }
    }
    fillLabelMapWithInlinedRules(ruleElement.getInlinedConditionRuleBlocks());
    fillLabelMapWithInlinedRules(ruleElement.getInlinedActionRuleBlocks());
  }

  private void fillLabelMapWithActions(List<AbstractRutaAction> actions, boolean own) {
    if (actions != null) {
      for (AbstractRutaAction action : actions) {
        if (action != null && !StringUtils.isBlank(action.getLabel())) {
          labels.put(action.getLabel(), null);
          if (own) {
            ownLabels.add(action.getLabel());
          }
        }
      }
    }
  }

  private void fillLabelMapWithInlinedRules(List<List<RutaStatement>> blocks) {
    if (blocks != null) {
      for (List<RutaStatement> list : blocks) {
        for (RutaStatement eachInlined : list) {
          if (eachInlined instanceof RutaRule) {
            RutaRule inlinedRule = (RutaRule) eachInlined;
            inlinedRule.setInlined(true);
            fillLabelMap(inlinedRule.getRoot(), false);
          }
        }
      }
    }
  }

  private void prepareEnvironment(MatchContext context, RutaStream stream) {
    if (isInlined()) {
      // only the actual rule may setup the environment
      return;
    }
    RutaBlock parent = context.getParent();
    RutaEnvironment environment = parent.getEnvironment();
    for (Entry<String, Object> entry : labels.entrySet()) {
      String label = entry.getKey();
      if (environment.isVariable(label)) {
        Class<?> variableType = environment.getVariableType(label);
        Class<?> variableGenericType = environment.getVariableGenericType(label);
        if (variableType != null && variableGenericType != null
                && variableType.isAssignableFrom(List.class)
                && variableGenericType.isAssignableFrom(AnnotationFS.class)) {
          labels.put(label, environment.getVariableValue(label, stream));
        } else if (variableType != null && variableType.isAssignableFrom(AnnotationFS.class)) {
        } else {
          String type = variableType == null ? "unknown" : variableType.getSimpleName();
          throw new RuntimeException("Overriding global variable '" + label + "' of type '" + type
                  + "' with a local label variable is not allowed (in script "
                  + context.getParent().getName() + ")!");
        }
      } else {
        environment.addVariable(label, RutaConstants.RUTA_VARIABLE_ANNOTATION_LIST);
      }
    }
  }

  private void cleanupEnvironment(MatchContext context, RutaStream stream) {
    if (isInlined()) {
      // only the actual rule may revert the environment
      return;
    }
    RutaBlock parent = context.getParent();
    RutaEnvironment environment = parent.getEnvironment();
    for (Entry<String, Object> entry : labels.entrySet()) {
      String label = entry.getKey();
      Object value = entry.getValue();
      if (value == null) {
        environment.removeVariable(label);
      } else {
        environment.setVariableValue(label, value);
      }
    }
  }

  public ComposedRuleElement getRoot() {
    return root;
  }

  public Collection<String> getLabels() {
    return labels.keySet();
  }

  public Collection<String> getOwnLabels() {
    return ownLabels;
  }

  public void clearOwnLabels() {
    RutaEnvironment environment = getParent().getEnvironment();
    environment.clearTempVariables(ownLabels);
  }

}
