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
      doMatch(eachAnchor, extendedMatch, extendedContainerMatch, true, stream, crowd);
      if (extendedMatch.matched()) {
        RuleElement after = getContainer().getNextElement(true, this);
        RuleElement before = getContainer().getNextElement(false, this);
        TextMarkerRuleElement sideStepOrigin = hasAncestor(false) ? this : null;
        if (quantifier.continueMatch(true, eachAnchor, this, extendedMatch, extendedContainerMatch,
                stream, crowd)) {
          continueMatch(true, eachAnchor, extendedMatch, ruleApply, extendedContainerMatch,
                  sideStepOrigin, null, stream, crowd);
        } else {
          if (after != null) {
            sideStepOrigin = hasAncestor(false) ? this : null;
            after.continueMatch(true, eachAnchor, extendedMatch, ruleApply, extendedContainerMatch,
                    sideStepOrigin, null, stream, crowd);
          } else if (stream.isDynamicAnchoring() && before != null) {
            sideStepOrigin = hasAncestor(true) ? this : null;
            before.continueMatch(false, eachAnchor, extendedMatch, ruleApply,
                    extendedContainerMatch, sideStepOrigin, null, stream, crowd);
          }

          if (after == null && before == null) {
            if (getContainer() instanceof ComposedRuleElement) {
              sideStepOrigin = hasAncestor(false) ? this : null;
              ComposedRuleElement composed = (ComposedRuleElement) getContainer();
              composed.fallbackContinue(true, false, eachAnchor, extendedMatch, ruleApply,
                      extendedContainerMatch, sideStepOrigin, entryPoint, stream, crowd);

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
                  extendedContainerMatch, null, null, stream, crowd);

        }
      }

    }
  }

  @Override
  public void continueMatch(boolean after, AnnotationFS annotation, RuleMatch ruleMatch,
          RuleApply ruleApply, ComposedRuleElementMatch containerMatch,
          TextMarkerRuleElement sideStepOrigin, RuleElement entryPoint, TextMarkerStream stream,
          InferenceCrowd crowd) {
    // if() for really lazy quantifiers
    if (quantifier.continueMatch(after, annotation, this, ruleMatch, containerMatch, stream, crowd)) {
      Collection<AnnotationFS> nextAnnotations = getNextAnnotations(after, annotation, stream);
      if (nextAnnotations.isEmpty()) {
        stepbackMatch(after, annotation, ruleMatch, ruleApply, containerMatch, sideStepOrigin,
                stream, crowd, entryPoint);
      }
      boolean useAlternatives = entryPoint == null; // nextAnnotations.size() > 1;
      for (AnnotationFS eachAnchor : nextAnnotations) {
        ComposedRuleElementMatch extendedContainerMatch = containerMatch;
        RuleMatch extendedMatch = ruleMatch;
        if (useAlternatives) {
          extendedContainerMatch = containerMatch.copy();
          extendedMatch = ruleMatch.copy(extendedContainerMatch);
        }

        doMatch(eachAnchor, extendedMatch, extendedContainerMatch, false, stream, crowd);

        if (this.equals(entryPoint)) {
          return;
        }
        if (extendedMatch.matched()) {
          if (quantifier.continueMatch(after, annotation, this, extendedMatch,
                  extendedContainerMatch, stream, crowd)) {
            continueMatch(after, eachAnchor, extendedMatch, ruleApply, extendedContainerMatch,
                    sideStepOrigin, entryPoint, stream, crowd);
          } else {
            RuleElement nextRuleElement = getContainer().getNextElement(after, this);
            if (nextRuleElement != null) {
              nextRuleElement.continueMatch(after, eachAnchor, extendedMatch, ruleApply,
                      extendedContainerMatch, sideStepOrigin, null, stream, crowd);
            } else if (getContainer() instanceof ComposedRuleElement) {
              ComposedRuleElement composed = (ComposedRuleElement) getContainer();
              composed.fallbackContinue(after, false, eachAnchor, extendedMatch, ruleApply,
                      extendedContainerMatch, sideStepOrigin, entryPoint, stream, crowd);
            }
          }
        } else {
          stepbackMatch(after, annotation, extendedMatch, ruleApply, extendedContainerMatch,
                  sideStepOrigin, stream, crowd, entryPoint);
        }
      }
    } else {
      stepbackMatch(after, annotation, ruleMatch, ruleApply, containerMatch, sideStepOrigin,
              stream, crowd, entryPoint);
    }
  }

  private void stepbackMatch(boolean after, AnnotationFS annotation, RuleMatch ruleMatch,
          RuleApply ruleApply, ComposedRuleElementMatch containerMatch,
          TextMarkerRuleElement sideStepOrigin, TextMarkerStream stream, InferenceCrowd crowd,
          RuleElement entryPoint) {
    if (ruleApply == null) {
      return;
    }

    List<RuleElementMatch> matchInfo = getMatch(ruleMatch, containerMatch);
    if (matchInfo == null) {
      if (quantifier.isOptional(parent)) {
        RuleElement nextRuleElement = getContainer().getNextElement(after, this);
        if (nextRuleElement != null) {
          nextRuleElement.continueMatch(after, annotation, ruleMatch, ruleApply, containerMatch,
                  sideStepOrigin, null, stream, crowd);
        } else if (getContainer() instanceof ComposedRuleElement) {
          ComposedRuleElement composed = (ComposedRuleElement) getContainer();
          composed.fallbackContinue(after, true, annotation, ruleMatch, ruleApply, containerMatch,
                  sideStepOrigin, entryPoint, stream, crowd);
        }
      } else if (getContainer() instanceof ComposedRuleElement) {
        RuleElementMatch failedMatch = new RuleElementMatch(this, containerMatch);
        failedMatch.setBaseConditionMatched(false);
        containerMatch.addInnerMatch(this, failedMatch);
        ComposedRuleElement composed = (ComposedRuleElement) getContainer();
        composed.fallbackContinue(after, true, annotation, ruleMatch, ruleApply, containerMatch,
                sideStepOrigin, null, stream, crowd);
      }
    } else {
      List<RuleElementMatch> evaluateMatches = quantifier.evaluateMatches(matchInfo, parent, crowd);
      ruleMatch.setMatched(evaluateMatches != null);
      if (ruleMatch.matched()) {
        RuleElement nextRuleElement = getContainer().getNextElement(after, this);
        if (nextRuleElement != null) {
          nextRuleElement.continueMatch(after, annotation, ruleMatch, ruleApply, containerMatch,
                  sideStepOrigin, null, stream, crowd);
        } else {
          if (getContainer() instanceof ComposedRuleElement) {
            ComposedRuleElement composed = (ComposedRuleElement) getContainer();
            composed.fallbackContinue(after, true, annotation, ruleMatch, ruleApply,
                    containerMatch, sideStepOrigin, null, stream, crowd);
          }
        }
      }
    }
  }

  public void continueSideStep(boolean after, RuleMatch ruleMatch, RuleApply ruleApply,
          ComposedRuleElementMatch containerMatch, RuleElement entryPoint, TextMarkerStream stream,
          InferenceCrowd crowd) {
    boolean newDirection = !after;

    List<AnnotationFS> matchedAnnotationsOf = ruleMatch.getMatchedAnnotationsOf(this, stream);
    AnnotationFS annotation = null;
    if (newDirection) {
      annotation = matchedAnnotationsOf.get(matchedAnnotationsOf.size() - 1);
    } else {
      annotation = matchedAnnotationsOf.get(0);
    }
    ComposedRuleElementMatch sideStepContainerMatch = containerMatch;
    if (!containerMatch.getRuleElement().equals(getContainer())) {
      List<List<RuleElementMatch>> matchInfo = ruleMatch
              .getMatchInfo((ComposedRuleElement) getContainer());
      if (newDirection) {
        List<RuleElementMatch> list = matchInfo.get(matchInfo.size() - 1);
        sideStepContainerMatch = (ComposedRuleElementMatch) list.get(list.size() - 1);
      } else {
        List<RuleElementMatch> list = matchInfo.get(0);
        sideStepContainerMatch = (ComposedRuleElementMatch) list.get(0);
      }
    }

    if (quantifier.continueMatch(newDirection, annotation, this, ruleMatch, sideStepContainerMatch,
            stream, crowd)) {
      continueMatch(newDirection, annotation, ruleMatch, ruleApply, sideStepContainerMatch, null,
              entryPoint, stream, crowd);
    } else {
      RuleElement nextRuleElement = getContainer().getNextElement(newDirection, this);
      if (nextRuleElement != null) {
        nextRuleElement.continueMatch(newDirection, annotation, ruleMatch, ruleApply,
                sideStepContainerMatch, null, null, stream, crowd);
      } else if (getContainer() instanceof ComposedRuleElement) {
        ComposedRuleElement composed = (ComposedRuleElement) getContainer();
        composed.fallbackContinue(newDirection, false, annotation, ruleMatch, ruleApply,
                sideStepContainerMatch, null, entryPoint, stream, crowd);
      }
    }

  }

  private void doMatch(AnnotationFS annotation, RuleMatch ruleMatch,
          ComposedRuleElementMatch containerMatch, boolean ruleAnchor, TextMarkerStream stream,
          InferenceCrowd crowd) {
    // TODO rewite this method!
    RuleElementMatch result = new RuleElementMatch(this, containerMatch);
    result.setRuleAnchor(ruleAnchor);
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
