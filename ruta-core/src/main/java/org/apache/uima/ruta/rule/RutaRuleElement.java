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
import java.util.Collections;
import java.util.List;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.action.AbstractRutaAction;
import org.apache.uima.ruta.condition.AbstractRutaCondition;
import org.apache.uima.ruta.rule.quantifier.RuleElementQuantifier;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class RutaRuleElement extends AbstractRuleElement {

  private RutaMatcher matcher;

  @SuppressWarnings("unchecked")
  protected final InferenceCrowd emptyCrowd = new InferenceCrowd(Collections.EMPTY_LIST);

  public RutaRuleElement(RutaMatcher matcher, RuleElementQuantifier quantifier,
          List<AbstractRutaCondition> conditions, List<AbstractRutaAction> actions,
          RuleElementContainer container, RutaBlock parent) {
    super(quantifier, conditions, actions, container, parent);
    this.matcher = matcher;
  }

  public Collection<AnnotationFS> getAnchors(RutaStream stream) {
    return matcher.getMatchingAnnotations(stream, getParent());
  }

  public void startMatch(RuleMatch ruleMatch, RuleApply ruleApply,
          ComposedRuleElementMatch containerMatch, RuleElement entryPoint, RutaStream stream,
          InferenceCrowd crowd) {
    Collection<AnnotationFS> anchors = getAnchors(stream);
    boolean useAlternatives = entryPoint == null && !(anchors.size() == 1);
    for (AnnotationFS eachAnchor : anchors) {
      ComposedRuleElementMatch extendedContainerMatch = containerMatch;
      RuleMatch extendedMatch = ruleMatch;
      if (useAlternatives) {
        extendedContainerMatch = containerMatch.copy();
        extendedMatch = ruleMatch.copy(extendedContainerMatch);
      } else {
        extendedMatch.update(extendedContainerMatch);
      }
      doMatch(eachAnchor, extendedMatch, extendedContainerMatch, true, stream, crowd);
      if (extendedMatch.matched()) {
        RuleElement after = getContainer().getNextElement(true, this);
        RuleElement before = getContainer().getNextElement(false, this);
        RutaRuleElement sideStepOrigin = hasAncestor(false) ? this : null;
        if (quantifier.continueMatch(true, eachAnchor, this, extendedMatch, extendedContainerMatch,
                stream, crowd)) {
          continueOwnMatch(true, eachAnchor, extendedMatch, ruleApply, extendedContainerMatch,
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

  public void continueOwnMatch(boolean after, AnnotationFS annotation, RuleMatch ruleMatch,
          RuleApply ruleApply, ComposedRuleElementMatch containerMatch,
          RutaRuleElement sideStepOrigin, RuleElement entryPoint, RutaStream stream,
          InferenceCrowd crowd) {
    if (quantifier.continueMatch(after, annotation, this, ruleMatch, containerMatch, stream, crowd)) {
      boolean stopMatching = false;
      AnnotationFS eachAnchor = annotation;
      AnnotationFS lastAnchor = annotation;
      ComposedRuleElementMatch extendedContainerMatch = containerMatch;
      RuleMatch extendedMatch = ruleMatch;
      extendedMatch.update(extendedContainerMatch);
      while (!stopMatching) {
        if (!quantifier.continueMatch(after, eachAnchor, this, extendedMatch,
                extendedContainerMatch, stream, crowd)) {
          stopMatching = true;
          stepbackMatch(after, lastAnchor, extendedMatch, ruleApply, extendedContainerMatch,
                  sideStepOrigin, stream, crowd, entryPoint);
          break;
        }
        Collection<AnnotationFS> nextAnnotations = getNextAnnotations(after, eachAnchor, stream);
        if (nextAnnotations.size() == 0) {
          stopMatching = true;
          stepbackMatch(after, eachAnchor, extendedMatch, ruleApply, extendedContainerMatch,
                  sideStepOrigin, stream, crowd, entryPoint);
        } else if (nextAnnotations.size() == 1) {
          lastAnchor = eachAnchor;
          eachAnchor = nextAnnotations.iterator().next();
          doMatch(eachAnchor, extendedMatch, extendedContainerMatch, false, stream, crowd);
          if (this.equals(entryPoint)) {
            return;
          }
          if (extendedMatch.matched()) {
            if (quantifier.continueMatch(after, eachAnchor, this, extendedMatch,
                    extendedContainerMatch, stream, crowd)) {
              // continue in while loop
            } else {
              stopMatching = true;
              continueMatchSomewhereElse(after, false, eachAnchor, extendedMatch, ruleApply,
                      extendedContainerMatch, sideStepOrigin, entryPoint, stream, crowd);
            }
          } else {
            stopMatching = true;
            stepbackMatch(after, lastAnchor, extendedMatch, ruleApply, extendedContainerMatch,
                    sideStepOrigin, stream, crowd, entryPoint);
          }
        } else {
          stopMatching = true;
          continueMatch(after, lastAnchor, extendedMatch, ruleApply, extendedContainerMatch,
                  sideStepOrigin, entryPoint, stream, crowd);
        }
      }
    } else {
      stepbackMatch(after, annotation, ruleMatch, ruleApply, containerMatch, sideStepOrigin,
              stream, crowd, entryPoint);
    }
  }

  private void continueMatchSomewhereElse(boolean after, boolean failed, AnnotationFS eachAnchor,
          RuleMatch extendedMatch, RuleApply ruleApply,
          ComposedRuleElementMatch extendedContainerMatch, RutaRuleElement sideStepOrigin,
          RuleElement entryPoint, RutaStream stream, InferenceCrowd crowd) {
    RuleElement nextRuleElement = getContainer().getNextElement(after, this);
    if (nextRuleElement != null) {
      nextRuleElement.continueMatch(after, eachAnchor, extendedMatch, ruleApply,
              extendedContainerMatch, sideStepOrigin, entryPoint, stream, crowd);
    } else if (getContainer() instanceof ComposedRuleElement) {
      ComposedRuleElement composed = (ComposedRuleElement) getContainer();
      composed.fallbackContinue(after, failed, eachAnchor, extendedMatch, ruleApply,
              extendedContainerMatch, sideStepOrigin, entryPoint, stream, crowd);
    }

  }

  public void continueMatch(boolean after, AnnotationFS annotation, RuleMatch ruleMatch,
          RuleApply ruleApply, ComposedRuleElementMatch containerMatch,
          RutaRuleElement sideStepOrigin, RuleElement entryPoint, RutaStream stream,
          InferenceCrowd crowd) {
    // if() for really lazy quantifiers
    if (quantifier.continueMatch(after, annotation, this, ruleMatch, containerMatch, stream, crowd)) {
      Collection<AnnotationFS> nextAnnotations = getNextAnnotations(after, annotation, stream);
      if (nextAnnotations.isEmpty()) {
        stepbackMatch(after, annotation, ruleMatch, ruleApply, containerMatch, sideStepOrigin,
                stream, crowd, entryPoint);
      }
      boolean useAlternatives = entryPoint == null && !(nextAnnotations.size() == 1);
      for (AnnotationFS eachAnchor : nextAnnotations) {
        ComposedRuleElementMatch extendedContainerMatch = containerMatch;
        RuleMatch extendedMatch = ruleMatch;
        if (useAlternatives) {
          extendedContainerMatch = containerMatch.copy();
          extendedMatch = ruleMatch.copy(extendedContainerMatch);
        } else {
          extendedMatch.update(extendedContainerMatch);
        }

        doMatch(eachAnchor, extendedMatch, extendedContainerMatch, false, stream, crowd);

        if (this.equals(entryPoint) && ruleApply == null) {
          return;
        }
        if (extendedMatch.matched()) {
          if (quantifier.continueMatch(after, annotation, this, extendedMatch,
                  extendedContainerMatch, stream, crowd)) {
            continueOwnMatch(after, eachAnchor, extendedMatch, ruleApply, extendedContainerMatch,
                    sideStepOrigin, entryPoint, stream, crowd);
          } else {
            continueMatchSomewhereElse(after, false, eachAnchor, extendedMatch, ruleApply,
                    extendedContainerMatch, sideStepOrigin, entryPoint, stream, crowd);
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
          RutaRuleElement sideStepOrigin, RutaStream stream, InferenceCrowd crowd,
          RuleElement entryPoint) {
    if (ruleApply == null) {
      return;
    }
    List<RuleElementMatch> matchInfo = getMatch(ruleMatch, containerMatch);
    if (matchInfo == null) {
      if (quantifier.isOptional(parent, stream)) {
        continueMatchSomewhereElse(after, true, annotation, ruleMatch, ruleApply, containerMatch,
                sideStepOrigin, entryPoint, stream, crowd);
      } else if (getContainer() instanceof ComposedRuleElement) {
        ComposedRuleElement cre = (ComposedRuleElement) getContainer();
        if(cre.getQuantifier().isOptional(parent, stream)) {
          continueMatchSomewhereElse(after, true, annotation, ruleMatch, ruleApply, containerMatch,
                  sideStepOrigin, entryPoint, stream, crowd);
        } else {
        RuleElementMatch failedMatch = new RuleElementMatch(this, containerMatch);
        failedMatch.setBaseConditionMatched(false);
        containerMatch.addInnerMatch(this, failedMatch, stream);
        ComposedRuleElement composed = (ComposedRuleElement) getContainer();
        composed.fallbackContinue(after, true, annotation, ruleMatch, ruleApply, containerMatch,
                sideStepOrigin, entryPoint, stream, crowd);
        }}
    } else {
      List<RuleElementMatch> evaluateMatches = quantifier.evaluateMatches(matchInfo, parent,
              stream, crowd);
      ruleMatch.setMatched(evaluateMatches != null);
      if (ruleMatch.matched()) {
        continueMatchSomewhereElse(after, true, annotation, ruleMatch, ruleApply, containerMatch,
                sideStepOrigin, null, stream, crowd);
      } else {
        doneMatching(ruleMatch, ruleApply, stream, crowd);
      }
    }
  }

  public void continueSideStep(boolean after, RuleMatch ruleMatch, RuleApply ruleApply,
          ComposedRuleElementMatch containerMatch, RuleElement entryPoint, RutaStream stream,
          InferenceCrowd crowd) {
    boolean newDirection = !after;

    List<AnnotationFS> matchedAnnotationsOf = ruleMatch.getMatchedAnnotationsOf(this);
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
          ComposedRuleElementMatch containerMatch, boolean ruleAnchor, RutaStream stream,
          InferenceCrowd crowd) {
    // TODO rewite this method!
    RuleElementMatch result = new RuleElementMatch(this, containerMatch);
    result.setRuleAnchor(ruleAnchor);
    List<EvaluatedCondition> evaluatedConditions = new ArrayList<EvaluatedCondition>(
            conditions.size());
    boolean base = matcher.match(annotation, stream, getParent());
    List<AnnotationFS> textsMatched = new ArrayList<AnnotationFS>(1);
    if (base) {
      for (AbstractRutaCondition condition : conditions) {
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

    result.setMatchInfo(base, textsMatched, evaluatedConditions, stream);
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

  public RutaMatcher getMatcher() {
    return matcher;
  }

  public List<AbstractRutaCondition> getConditions() {
    return conditions;
  }

  public List<AbstractRutaAction> getActions() {
    return actions;
  }

  public RutaBlock getParent() {
    return parent;
  }

  public int estimateAnchors(RutaStream stream) {
    if (quantifier.isOptional(getParent(), stream)) {
      return Integer.MAX_VALUE;
    }
    return matcher.estimateAnchors(parent, stream);
  }

  public Collection<AnnotationFS> getNextAnnotations(boolean after, AnnotationFS annotation,
          RutaStream stream) {
    if (after) {
      return matcher.getAnnotationsAfter(this, annotation, stream, getParent());
    } else {
      return matcher.getAnnotationsBefore(this, annotation, stream, getParent());
    }
  }

}
