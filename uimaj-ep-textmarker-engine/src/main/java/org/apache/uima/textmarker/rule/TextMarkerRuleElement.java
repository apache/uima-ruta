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

package org.apache.uima.textmarker.rule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.textmarker.TextMarkerBlock;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.action.AbstractTextMarkerAction;
import org.apache.uima.textmarker.condition.AbstractTextMarkerCondition;
import org.apache.uima.textmarker.rule.quantifier.RuleElementQuantifier;
import org.apache.uima.textmarker.visitor.InferenceCrowd;

public class TextMarkerRuleElement extends AbstractRuleElement {

  private TextMarkerMatcher matcher;

  @SuppressWarnings("unchecked")
  protected final InferenceCrowd emptyCrowd = new InferenceCrowd(Collections.EMPTY_LIST);

  public TextMarkerRuleElement(TextMarkerMatcher matcher, RuleElementQuantifier quantifier,
          List<AbstractTextMarkerCondition> conditions, List<AbstractTextMarkerAction> actions,
          RuleElementContainer container, TextMarkerBlock parent) {
    super(quantifier, conditions, actions, container, parent);
    this.matcher = matcher;
  }

  public Collection<AnnotationFS> getAnchors(TextMarkerStream stream) {
    return matcher.getMatchingAnnotations(stream, getParent());
  }

  @Override
  public void startMatch(RuleMatch ruleMatch, RuleApply ruleApply,
          ComposedRuleElementMatch containerMatch, RuleElement entryPoint, TextMarkerStream stream,
          InferenceCrowd crowd) {
    Collection<AnnotationFS> anchors = getAnchors(stream);

    boolean useAlternatives = entryPoint == null; // anchors.size() > 1;
    for (AnnotationFS eachAnchor : anchors) {
      ComposedRuleElementMatch extendedContainerMatch = containerMatch;
      RuleMatch extendedMatch = ruleMatch;
      if (useAlternatives) {
        extendedContainerMatch = containerMatch.copy();
        extendedMatch = ruleMatch.copy(extendedContainerMatch);
      }
      doMatch(eachAnchor, extendedMatch, extendedContainerMatch, stream, crowd);
      if (extendedMatch.matched()) {
        if (quantifier.continueMatch(true, eachAnchor, this, extendedMatch, extendedContainerMatch,
                stream, crowd)) {
          continueMatch(true, eachAnchor, extendedMatch, ruleApply, extendedContainerMatch, null,
                  stream, crowd);
        } else {
          RuleElement after = getContainer().getNextElement(true, this);
          RuleElement before = getContainer().getNextElement(false, this);
          if (after != null) {
            after.continueMatch(true, eachAnchor, extendedMatch, ruleApply, extendedContainerMatch,
                    null, stream, crowd);
          }
          // TODO add sidestep functionality here for dynamic anchoring
          if (before != null) {
            before.continueMatch(false, eachAnchor, extendedMatch, ruleApply,
                    extendedContainerMatch, null, stream, crowd);
          }

          if (after == null && before == null) {
            if (getContainer() instanceof ComposedRuleElement) {
              ComposedRuleElement composed = (ComposedRuleElement) getContainer();
              composed.fallbackContinue(true, false, eachAnchor, extendedMatch, ruleApply,
                      extendedContainerMatch, entryPoint, stream, crowd);

            } else if (getContainer() instanceof RuleElementIsolator) {
              // TODO move and refactor this:
              doneMatching(extendedMatch, ruleApply, stream, crowd);
            }
          }
        }
      } else {
        if (getContainer() instanceof ComposedRuleElement) {
          ComposedRuleElement composed = (ComposedRuleElement) getContainer();
          composed.fallbackContinue(true, true, eachAnchor, extendedMatch, ruleApply,
                  extendedContainerMatch, null, stream, crowd);

        }
      }

    }
  }

  @Override
  public void continueMatch(boolean after, AnnotationFS annotation, RuleMatch ruleMatch,
          RuleApply ruleApply, ComposedRuleElementMatch containerMatch, RuleElement entryPoint,
          TextMarkerStream stream, InferenceCrowd crowd) {
    // if() for really lazy quantifiers
    if (quantifier.continueMatch(after, annotation, this, ruleMatch, containerMatch, stream, crowd)) {
      Collection<AnnotationFS> nextAnnotations = getNextAnnotations(after, annotation, stream);
      if (nextAnnotations.isEmpty()) {
        stepbackMatch(after, annotation, ruleMatch, ruleApply, containerMatch, stream, crowd,
                entryPoint);
      }
      boolean useAlternatives = entryPoint == null; // nextAnnotations.size() > 1;
      for (AnnotationFS eachAnchor : nextAnnotations) {
        ComposedRuleElementMatch extendedContainerMatch = containerMatch;
        RuleMatch extendedMatch = ruleMatch;
        if (useAlternatives) {
          extendedContainerMatch = containerMatch.copy();
          extendedMatch = ruleMatch.copy(extendedContainerMatch);
        }

        doMatch(eachAnchor, extendedMatch, extendedContainerMatch, stream, crowd);

        if (this.equals(entryPoint)) {
          return;
        }
        if (extendedMatch.matched()) {
          if (quantifier.continueMatch(after, annotation, this, extendedMatch,
                  extendedContainerMatch, stream, crowd)) {
            continueMatch(after, eachAnchor, extendedMatch, ruleApply, extendedContainerMatch,
                    entryPoint, stream, crowd);
          } else {
            RuleElement nextRuleElement = getContainer().getNextElement(after, this);
            if (nextRuleElement != null) {
              nextRuleElement.continueMatch(after, eachAnchor, extendedMatch, ruleApply,
                      extendedContainerMatch, null, stream, crowd);
            } else if (getContainer() instanceof ComposedRuleElement) {
              ComposedRuleElement composed = (ComposedRuleElement) getContainer();
              composed.fallbackContinue(after, false, eachAnchor, extendedMatch, ruleApply,
                      extendedContainerMatch, entryPoint, stream, crowd);
            }
          }
        } else {
          stepbackMatch(after, annotation, extendedMatch, ruleApply, extendedContainerMatch,
                  stream, crowd, entryPoint);
        }
      }
    } else {
      stepbackMatch(after, annotation, ruleMatch, ruleApply, containerMatch, stream, crowd,
              entryPoint);
    }
  }

  private void stepbackMatch(boolean after, AnnotationFS annotation, RuleMatch ruleMatch,
          RuleApply ruleApply, ComposedRuleElementMatch containerMatch, TextMarkerStream stream,
          InferenceCrowd crowd, RuleElement entryPoint) {
    if (ruleApply == null) {
      return;
    }

    List<RuleElementMatch> matchInfo = getMatch(ruleMatch, containerMatch);
    if (matchInfo == null) {
      if (quantifier.isOptional(parent)) {
        RuleElement nextRuleElement = getContainer().getNextElement(after, this);
        if (nextRuleElement != null) {
          nextRuleElement.continueMatch(after, annotation, ruleMatch, ruleApply, containerMatch,
                  null, stream, crowd);
        } else if (getContainer() instanceof ComposedRuleElement) {
          ComposedRuleElement composed = (ComposedRuleElement) getContainer();
          composed.fallbackContinue(after, true, annotation, ruleMatch, ruleApply, containerMatch,
                  entryPoint, stream, crowd);
        }
      } else if (getContainer() instanceof ComposedRuleElement) {
        ComposedRuleElement composed = (ComposedRuleElement) getContainer();
        composed.fallbackContinue(after, true, annotation, ruleMatch, ruleApply, containerMatch,
                null, stream, crowd);
      }
    } else {
      List<RuleElementMatch> evaluateMatches = quantifier.evaluateMatches(matchInfo, parent, crowd);
      ruleMatch.setMatched(evaluateMatches != null);
      if (ruleMatch.matched()) {
        RuleElement nextRuleElement = getContainer().getNextElement(after, this);
        if (nextRuleElement != null) {
          nextRuleElement.continueMatch(after, annotation, ruleMatch, ruleApply, containerMatch,
                  null, stream, crowd);
        } else {
          if (getContainer() instanceof ComposedRuleElement) {
            ComposedRuleElement composed = (ComposedRuleElement) getContainer();
            composed.fallbackContinue(after, true, annotation, ruleMatch, ruleApply,
                    containerMatch, null, stream, crowd);
          }
        }
      }
    }
  }

  private void doMatch(AnnotationFS annotation, RuleMatch ruleMatch,
          ComposedRuleElementMatch containerMatch, TextMarkerStream stream, InferenceCrowd crowd) {
    // TODO rewite this method!
    RuleElementMatch result = new RuleElementMatch(this, containerMatch);
    List<EvaluatedCondition> evaluatedConditions = new ArrayList<EvaluatedCondition>(
            conditions.size());
    boolean base = matcher.match(annotation, stream, getParent());
    List<AnnotationFS> textsMatched = new ArrayList<AnnotationFS>(1);
    if (base) {
      for (AbstractTextMarkerCondition condition : conditions) {
        crowd.beginVisit(condition, null);
        EvaluatedCondition eval = condition.eval(annotation, this, stream, crowd);
        crowd.endVisit(condition, null);
        // matched &= eval.isValue();
        evaluatedConditions.add(eval);
      }
    }

    if (annotation != null) {
      textsMatched.add(annotation);
    }

    result.setMatchInfo(base, textsMatched, evaluatedConditions);
    ruleMatch.setMatched(ruleMatch.matched() && result.matched());
    List<RuleElementMatch> rems = new ArrayList<RuleElementMatch>();
    rems.add(result);
    ruleMatch.processMatchInfo(this, rems, stream);
  }

  @Override
  public String toString() {
    String simpleName = getQuantifier().getClass().getSimpleName();
    return matcher.toString() + " " + (simpleName.equals("NormalQuantifier") ? "" : simpleName)
            + (conditions.isEmpty() ? "" : "(" + conditions.toString() + ")" + "\\n")
            + (actions.isEmpty() ? "" : "{" + actions.toString() + "}");
  }

  public TextMarkerMatcher getMatcher() {
    return matcher;
  }

  public List<AbstractTextMarkerCondition> getConditions() {
    return conditions;
  }

  public List<AbstractTextMarkerAction> getActions() {
    return actions;
  }

  public TextMarkerBlock getParent() {
    return parent;
  }

  @Override
  public int estimateAnchors(TextMarkerStream stream) {
    if (quantifier.isOptional(getParent())) {
      return Integer.MAX_VALUE;
    }
    return matcher.estimateAnchors(parent, stream);
  }

  public Collection<AnnotationFS> getNextAnnotations(boolean after, AnnotationFS annotation,
          TextMarkerStream stream) {
    if (after) {
      return matcher.getAnnotationsAfter(this, annotation, stream, getParent());
    } else {
      return matcher.getAnnotationsBefore(this, annotation, stream, getParent());
    }
  }

}
