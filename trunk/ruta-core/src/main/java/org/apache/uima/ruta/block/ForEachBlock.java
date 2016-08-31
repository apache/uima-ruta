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

package org.apache.uima.ruta.block;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaConstants;
import org.apache.uima.ruta.RutaStatement;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.ScriptApply;
import org.apache.uima.ruta.expression.annotation.AnnotationVariableExpression;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.rule.AbstractRule;
import org.apache.uima.ruta.rule.AbstractRuleMatch;
import org.apache.uima.ruta.rule.ComposedRuleElement;
import org.apache.uima.ruta.rule.MatchContext;
import org.apache.uima.ruta.rule.RuleApply;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleMatch;
import org.apache.uima.ruta.rule.RutaAnnotationMatcher;
import org.apache.uima.ruta.rule.RutaMatcher;
import org.apache.uima.ruta.rule.RutaRule;
import org.apache.uima.ruta.rule.RutaRuleElement;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class ForEachBlock extends RutaBlock {

  private boolean anchorsSet = false;

  private IBooleanExpression direction;
  
  public ForEachBlock(String varName, IBooleanExpression direction, RutaRule rule, List<RutaStatement> elements, RutaBlock parent,
          String defaultNamespace) {
    super(varName, rule, elements, parent, defaultNamespace,
            parent != null ? parent.getContext() : null);
    this.direction = direction;
  }

  @Override
  public ScriptApply apply(RutaStream stream, InferenceCrowd crowd) {
    BlockApply result = new BlockApply(this);
    crowd.beginVisit(this, result);

    setRuleElementAnchor();

    boolean leftToRight = true;
    if(direction != null) {
      leftToRight = direction.getBooleanValue(new MatchContext(getParent()), stream);
    }
    
    RuleApply apply = rule.apply(stream, crowd, true);
    List<AbstractRuleMatch<? extends AbstractRule>> list = apply.getList();
    if(!leftToRight) {
      Collections.reverse(list);
    }
    for (AbstractRuleMatch<? extends AbstractRule> eachMatch : list) {
      if (eachMatch.matched()) {

        List<AnnotationFS> matchedAnnotations = ((RuleMatch) eachMatch).getMatchedAnnotations(null,
                null);
        if (matchedAnnotations == null || matchedAnnotations.isEmpty()) {
          continue;
        }

        for (AnnotationFS eachMatchedAnnotation : matchedAnnotations) {
          environment.addVariable(name, RutaConstants.RUTA_VARIABLE_ANNOTATION);
          environment.setVariableValue(name, eachMatchedAnnotation);

          for (RutaStatement element : elements) {
            if (element != null) {
              element.apply(stream, crowd);
            }
          }
        }
      }
    }
    crowd.endVisit(this, result);
    return result;
  }

  private void setRuleElementAnchor() {
    if (anchorsSet) {
      return;
    }
    for (RutaStatement eachElement : elements) {
      if (eachElement instanceof RutaRule) {
        RutaRule eachRule = (RutaRule) eachElement;
        List<RuleElement> ruleElements = eachRule.getRuleElements();
        for (RuleElement ruleElement : ruleElements) {
          boolean set = setRuleElementAnchorRecursively(ruleElement);
          if (set) {
            break;
          }
        }
      }
    }

    anchorsSet = true;
  }

  private boolean setRuleElementAnchorRecursively(RuleElement ruleElement) {
    if (ruleElement instanceof RutaRuleElement) {
      RutaMatcher matcher = ((RutaRuleElement) ruleElement).getMatcher();
      if (matcher instanceof RutaAnnotationMatcher
              && matcher.getExpression() instanceof AnnotationVariableExpression) {
        AnnotationVariableExpression expr = (AnnotationVariableExpression) matcher.getExpression();
        boolean equals = StringUtils.equals(name, expr.getVar());
        if(equals) {
          ruleElement.setStartAnchor(equals);
        }
        return equals;
      }
    } else if (ruleElement instanceof ComposedRuleElement) {
      List<RuleElement> ruleElements = ((ComposedRuleElement) ruleElement).getRuleElements();
      for (RuleElement eachInnerRuleElement : ruleElements) {
        boolean set = setRuleElementAnchorRecursively(eachInnerRuleElement);
        if(set) {
          return set;
        }
      }
    }
    return false;
  }

  @Override
  public String toString() {
    String ruleString = rule == null ? "Document" : rule.toString();
    int elementSize = elements == null ? 0 : elements.size();
    return "FOREACH(" + name + ") " + ruleString + " containing " + elementSize + " Elements";
  }

  public IBooleanExpression getDirection() {
    return direction;
  }

}
