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

import java.util.List;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;

/**
 * This is a generic container class for contextual information during rule matching
 *
 */
public class MatchContext {

  private AnnotationFS annotation;

  private RuleElement element;

  private RutaBlock parent;

  private RuleMatch ruleMatch;

  private boolean direction = true;

  /**
   * Container object of the current context during matching
   * 
   * @param annotation
   * @param element
   * @param ruleMatch
   * @param direction
   *          , true: left to right, false, right to left
   */
  public MatchContext(AnnotationFS annotation, RuleElement element, RuleMatch ruleMatch,
          boolean direction) {
    super();
    this.annotation = annotation;
    this.element = element;
    if (element != null) {
      this.parent = element.getParent();
    }
    this.ruleMatch = ruleMatch;
    this.direction = direction;
  }

  /**
   * Container object of the current context during matching
   * 
   * @param element
   * @param ruleMatch
   * @param direction
   *          , true: left to right, false, right to left
   */
  public MatchContext(RuleElement element, RuleMatch ruleMatch, boolean direction) {
    this(null, element, ruleMatch, direction);
  }

  /**
   * Container object of the current context during matching
   * 
   * @param element
   * @param ruleMatch
   */
  public MatchContext(AbstractRuleElement element, RuleMatch ruleMatch) {
    this(element, ruleMatch, true);
  }

  /**
   * Container object of the current context during matching
   * 
   * @param annotation
   * @param element
   * @param ruleMatch
   * @param direction
   *          , true: left to right, false, right to left
   */
  public MatchContext(RutaBlock parent) {
    super();
    this.parent = parent;
  }

  public AnnotationFS getAnnotation() {
    if (annotation != null) {
      return annotation;
    } else if (element != null && ruleMatch != null) {
      List<AnnotationFS> matchedAnnotationsOfElement = ruleMatch
              .getMatchedAnnotationsOfElement(element);
      if (matchedAnnotationsOfElement != null && !matchedAnnotationsOfElement.isEmpty()) {
        if (direction) {
          return matchedAnnotationsOfElement.get(matchedAnnotationsOfElement.size() - 1);
        } else {
          return matchedAnnotationsOfElement.get(0);
        }
      }
    }
    return null;
  }

  public void setAnnotation(AnnotationFS annotation) {
    this.annotation = annotation;
  }

  public RuleElement getElement() {
    return element;
  }

  public void setElement(RuleElement element) {
    this.element = element;
  }

  public RuleMatch getRuleMatch() {
    return ruleMatch;
  }

  public void setRuleMatch(RuleMatch ruleMatch) {
    this.ruleMatch = ruleMatch;
  }

  public boolean getDirection() {
    return direction;
  }

  public void setDirection(boolean direction) {
    this.direction = direction;
  }

  public RutaBlock getParent() {
    return parent;
  }

  public void setParent(RutaBlock parent) {
    this.parent = parent;
  }

}
