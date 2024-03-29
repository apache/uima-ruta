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

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaEnvironment;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.action.AbstractRutaAction;
import org.apache.uima.ruta.block.RutaBlock;
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

  @Override
  public Collection<? extends AnnotationFS> getAnchors(RutaStream stream) {
    return matcher.getMatchingAnnotations(getParent(), stream);
  }

  @Override
  public List<RuleMatch> startMatch(RuleMatch ruleMatch, RuleApply ruleApply,
          ComposedRuleElementMatch containerMatch, RuleElement entryPoint, RutaStream stream,
          InferenceCrowd crowd) {
    List<RuleMatch> result = new ArrayList<>();
    Collection<? extends AnnotationFS> anchors = getAnchors(stream);
    boolean useAlternatives = anchors.size() != 1;
    for (AnnotationFS eachAnchor : anchors) {

      // clean up temp variables since we start a new matching iteration
      ruleMatch.getRule().clearOwnLabels();

      if (earlyExit(eachAnchor, ruleApply, stream)) {
        // ... for different matching paradigms that avoid some matches
        continue;
      }

      ComposedRuleElementMatch extendedContainerMatch = containerMatch;
      RuleMatch extendedMatch = ruleMatch;
      if (useAlternatives) {
        extendedContainerMatch = containerMatch.copy();
        extendedMatch = ruleMatch.copy(extendedContainerMatch, true);
      }
      doMatch(true, eachAnchor, extendedMatch, extendedContainerMatch, true, stream, crowd);
      if (equals(entryPoint) && ruleApply == null) {
        result.add(extendedMatch);
      } else if (extendedMatch.matched()) {
        RuleElement after = getContainer().getNextElement(true, this);
        RuleElement before = getContainer().getNextElement(false, this);
        RutaRuleElement sideStepOrigin = hasAncestor(false) ? this : null;
        MatchContext context = new MatchContext(this, extendedMatch, true);
        if (quantifier.continueMatch(true, context, eachAnchor, extendedContainerMatch, stream,
                crowd)) {
          List<RuleMatch> continueOwnMatch = continueOwnMatch(true, eachAnchor, extendedMatch,
                  ruleApply, extendedContainerMatch, sideStepOrigin, entryPoint, stream, crowd);
          result.addAll(continueOwnMatch);
        } else {
          if (after != null) {
            sideStepOrigin = hasAncestor(false) ? this : null;
            List<RuleMatch> continueMatch = after.continueMatch(true, eachAnchor, extendedMatch,
                    ruleApply, extendedContainerMatch, sideStepOrigin, entryPoint, stream, crowd);
            result.addAll(continueMatch);
          } else if ((stream.isDynamicAnchoring() || isStartAnchor()) && before != null) {
            sideStepOrigin = hasAncestor(true) ? this : null;
            List<RuleMatch> continueMatch = before.continueMatch(false, eachAnchor, extendedMatch,
                    ruleApply, extendedContainerMatch, sideStepOrigin, entryPoint, stream, crowd);
            result.addAll(continueMatch);
          }

          if (after == null && before == null) {
            if (getContainer() instanceof ComposedRuleElement) {
              sideStepOrigin = hasAncestor(false) ? this : null;
              ComposedRuleElement composed = (ComposedRuleElement) getContainer();
              List<RuleMatch> fallbackContinue = composed.fallbackContinue(true, false, eachAnchor,
                      extendedMatch, ruleApply, extendedContainerMatch, sideStepOrigin, entryPoint,
                      stream, crowd);
              result.addAll(fallbackContinue);
            } else if (getContainer() instanceof RuleElementIsolator) {
              // TODO move and refactor this:
              doneMatching(extendedMatch, ruleApply, stream, crowd);
            }
          }
        }
      } else {
        if (getContainer() instanceof ComposedRuleElement) {
          ComposedRuleElement composed = (ComposedRuleElement) getContainer();
          List<RuleMatch> fallbackContinue = composed.fallbackContinue(true, true, eachAnchor,
                  extendedMatch, ruleApply, extendedContainerMatch, null, entryPoint, stream,
                  crowd);
          result.addAll(fallbackContinue);
        }
      }
    }
    return result;
  }

  @Override
  public List<RuleMatch> continueOwnMatch(boolean after, AnnotationFS annotation,
          RuleMatch ruleMatch, RuleApply ruleApply, ComposedRuleElementMatch containerMatch,
          RuleElement sideStepOrigin, RuleElement entryPoint, RutaStream stream,
          InferenceCrowd crowd) {
    List<RuleMatch> result = new ArrayList<>();
    MatchContext context = new MatchContext(this, ruleMatch, after);
    if (quantifier.continueMatch(after, context, annotation, containerMatch, stream, crowd)) {
      boolean stopMatching = false;
      AnnotationFS eachAnchor = annotation;
      AnnotationFS lastAnchor = annotation;
      ComposedRuleElementMatch extendedContainerMatch = containerMatch;
      RuleMatch extendedMatch = ruleMatch;
      while (!stopMatching) {
        context = new MatchContext(this, extendedMatch, after);
        if (!quantifier.continueMatch(after, context, eachAnchor, extendedContainerMatch, stream,
                crowd)) {
          stopMatching = true;
          stepbackMatch(after, lastAnchor, extendedMatch, ruleApply, extendedContainerMatch,
                  sideStepOrigin, stream, crowd, entryPoint);
          break;
        }
        Collection<? extends AnnotationFS> nextAnnotations = getNextAnnotations(after, eachAnchor,
                stream);
        if (nextAnnotations.size() == 0) {
          stopMatching = true;
          result = stepbackMatch(after, eachAnchor, extendedMatch, ruleApply,
                  extendedContainerMatch, sideStepOrigin, stream, crowd, entryPoint);
        } else if (nextAnnotations.size() == 1) {
          lastAnchor = eachAnchor;
          eachAnchor = nextAnnotations.iterator().next();
          doMatch(after, eachAnchor, extendedMatch, extendedContainerMatch, false, stream, crowd);
          if (equals(entryPoint)) {
            result.add(extendedMatch);
          } else if (extendedMatch.matched()) {
            if (quantifier.continueMatch(after, context, eachAnchor, extendedContainerMatch, stream,
                    crowd)) {
              // continue in while loop
            } else {
              stopMatching = true;
              result = continueMatchSomewhereElse(after, false, eachAnchor, extendedMatch,
                      ruleApply, extendedContainerMatch, sideStepOrigin, entryPoint, stream, crowd);
            }
          } else {
            stopMatching = true;
            result = stepbackMatch(after, lastAnchor, extendedMatch, ruleApply,
                    extendedContainerMatch, sideStepOrigin, stream, crowd, entryPoint);
          }
        } else {
          stopMatching = true;
          result = continueMatch(after, lastAnchor, extendedMatch, ruleApply,
                  extendedContainerMatch, sideStepOrigin, entryPoint, stream, crowd);
        }
      }
    } else {
      result = stepbackMatch(after, annotation, ruleMatch, ruleApply, containerMatch,
              sideStepOrigin, stream, crowd, entryPoint);
    }
    return result;
  }

  protected List<RuleMatch> continueMatchSomewhereElse(boolean after, boolean failed,
          AnnotationFS eachAnchor, RuleMatch extendedMatch, RuleApply ruleApply,
          ComposedRuleElementMatch extendedContainerMatch, RuleElement sideStepOrigin,
          RuleElement entryPoint, RutaStream stream, InferenceCrowd crowd) {
    List<RuleMatch> result = new ArrayList<>();
    RuleElement nextRuleElement = getContainer().getNextElement(after, this);
    if (nextRuleElement != null) {
      result = nextRuleElement.continueMatch(after, eachAnchor, extendedMatch, ruleApply,
              extendedContainerMatch, sideStepOrigin, entryPoint, stream, crowd);
    } else if (sideStepOrigin != null && !failed && containedIn(sideStepOrigin, getContainer())) {
      // continue directly with the sidestep if it is contained in this container
      // if not, we might miss matches in the same direction
      result = sideStepOrigin.continueSideStep(after, extendedMatch, ruleApply,
              extendedContainerMatch, entryPoint, stream, crowd);
    } else if (getContainer() instanceof ComposedRuleElement) {
      ComposedRuleElement composed = (ComposedRuleElement) getContainer();
      result = composed.fallbackContinue(after, failed, eachAnchor, extendedMatch, ruleApply,
              extendedContainerMatch, sideStepOrigin, entryPoint, stream, crowd);
    }
    return result;
  }

  private boolean containedIn(RuleElement sideStepOrigin, RuleElementContainer container) {
    // TODO: should we support this in interface?
    if (container == null || sideStepOrigin == null) {
      return false;
    }
    List<RuleElement> ruleElements = container.getRuleElements();
    if (ruleElements.contains(sideStepOrigin)) {
      return true;
    } else {
      for (RuleElement ruleElement : ruleElements) {
        if (ruleElement instanceof RuleElementContainer) {
          if (containedIn(sideStepOrigin, (RuleElementContainer) ruleElement)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  @Override
  public List<RuleMatch> continueMatch(boolean after, AnnotationFS annotation, RuleMatch ruleMatch,
          RuleApply ruleApply, ComposedRuleElementMatch containerMatch, RuleElement sideStepOrigin,
          RuleElement entryPoint, RutaStream stream, InferenceCrowd crowd) {
    List<RuleMatch> result = new ArrayList<>();
    // if() for really lazy quantifiers
    MatchContext context = new MatchContext(this, ruleMatch, after);
    if (quantifier.continueMatch(after, context, annotation, containerMatch, stream, crowd)) {
      Collection<? extends AnnotationFS> nextAnnotations = getNextAnnotations(after, annotation,
              stream);
      if (isNotConsumable(nextAnnotations)) {
        result = stepbackMatch(after, annotation, ruleMatch, ruleApply, containerMatch,
                sideStepOrigin, stream, crowd, entryPoint);
      }
      boolean useAlternatives = nextAnnotations.size() > 1;
      for (AnnotationFS eachAnchor : nextAnnotations) {
        if (earlyExit(eachAnchor, ruleApply, stream)) {
          // ... for different matching paradigms that avoid some matches
          continue;
        }

        ComposedRuleElementMatch extendedContainerMatch = containerMatch;
        RuleMatch extendedMatch = ruleMatch;
        if (useAlternatives) {
          extendedContainerMatch = containerMatch.copy();
          extendedMatch = ruleMatch.copy(extendedContainerMatch, after);
        }
        doMatch(after, eachAnchor, extendedMatch, extendedContainerMatch, false, stream, crowd);

        if (equals(entryPoint) && ruleApply == null) {
          result.add(extendedMatch);
        } else if (extendedMatch.matched()) {
          context = new MatchContext(this, extendedMatch, after);
          if (quantifier.continueMatch(after, context, eachAnchor, extendedContainerMatch, stream,
                  crowd)) {
            List<RuleMatch> continueOwnMatch = continueOwnMatch(after, eachAnchor, extendedMatch,
                    ruleApply, extendedContainerMatch, sideStepOrigin, entryPoint, stream, crowd);
            result.addAll(continueOwnMatch);
          } else {
            List<RuleMatch> continueMatchSomewhereElse = continueMatchSomewhereElse(after, false,
                    eachAnchor, extendedMatch, ruleApply, extendedContainerMatch, sideStepOrigin,
                    entryPoint, stream, crowd);
            result.addAll(continueMatchSomewhereElse);
          }
        } else {
          if (equals(entryPoint)) {
            // hotfix for UIMA-3820
            result.add(extendedMatch);
          } else {
            List<RuleMatch> stepbackMatch = stepbackMatch(after, annotation, extendedMatch,
                    ruleApply, extendedContainerMatch, sideStepOrigin, stream, crowd, entryPoint);
            result.addAll(stepbackMatch);
          }
        }
      }
    } else {
      result = stepbackMatch(after, annotation, ruleMatch, ruleApply, containerMatch,
              sideStepOrigin, stream, crowd, entryPoint);
    }
    return result;
  }

  protected boolean isNotConsumable(Collection<? extends AnnotationFS> nextAnnotations) {
    return nextAnnotations.isEmpty();
  }

  protected List<RuleMatch> stepbackMatch(boolean after, AnnotationFS annotation,
          RuleMatch ruleMatch, RuleApply ruleApply, ComposedRuleElementMatch containerMatch,
          RuleElement sideStepOrigin, RutaStream stream, InferenceCrowd crowd,
          RuleElement entryPoint) {
    List<RuleMatch> result = new ArrayList<>();
    if (equals(entryPoint) && ruleApply == null) {
      result.add(ruleMatch);
      return result;
    }
    List<RuleElementMatch> matchInfo = getMatch(ruleMatch, containerMatch);
    MatchContext context = new MatchContext(this, ruleMatch, after);
    if (matchInfo == null) {
      context.getParent().getEnvironment().addMatchToVariable(ruleMatch, this, context, stream);
      if (quantifier.isOptional(context, stream)) {
        result = continueMatchSomewhereElse(after, false, annotation, ruleMatch, ruleApply,
                containerMatch, sideStepOrigin, entryPoint, stream, crowd);
      } else if (getContainer() instanceof ComposedRuleElement) {
        ComposedRuleElement cre = (ComposedRuleElement) getContainer();
        result = cre.fallbackContinue(after, true, annotation, ruleMatch, ruleApply, containerMatch,
                sideStepOrigin, entryPoint, stream, crowd);
        // was:
        // [Peter] why only check the parent? the grandparent could be optional!
        // should we add the second part again for the explanation component?
        // if (cre.getQuantifier().isOptional(parent, stream)) {
        // result = continueMatchSomewhereElse(after, true, annotation, ruleMatch, ruleApply,
        // containerMatch, sideStepOrigin, entryPoint, stream, crowd);
        // } else {
        // RuleElementMatch failedMatch = new RuleElementMatch(this, containerMatch);
        // failedMatch.setBaseConditionMatched(false);
        // containerMatch.addInnerMatch(this, failedMatch, stream);
        // ComposedRuleElement composed = (ComposedRuleElement) getContainer();
        // result = composed.fallbackContinue(after, true, annotation, ruleMatch, ruleApply,
        // containerMatch, sideStepOrigin, entryPoint, stream, crowd);
        // }
      }
    } else {
      List<RuleElementMatch> evaluateMatches = quantifier.evaluateMatches(matchInfo, context,
              stream, crowd);
      // TODO enforce match update?
      ruleMatch.setMatched(evaluateMatches != null);
      if (ruleMatch.matched()) {
        result = continueMatchSomewhereElse(after, false, annotation, ruleMatch, ruleApply,
                containerMatch, sideStepOrigin, entryPoint, stream, crowd);
      } else {
        if (getContainer() instanceof ComposedRuleElement) {
          ComposedRuleElement composed = (ComposedRuleElement) getContainer();
          List<RuleMatch> fallbackContinue = composed.fallbackContinue(after, true, annotation,
                  ruleMatch, ruleApply, containerMatch, sideStepOrigin, entryPoint, stream, crowd);
          result.addAll(fallbackContinue);
        } else {
          // should never happen!
          doneMatching(ruleMatch, ruleApply, stream, crowd);
        }
      }
    }
    return result;
  }

  @Override
  public void doMatch(boolean after, AnnotationFS annotation, RuleMatch ruleMatch,
          ComposedRuleElementMatch containerMatch, boolean ruleAnchor, RutaStream stream,
          InferenceCrowd crowd) {
    RuleElementMatch result = new RuleElementMatch(this, containerMatch);
    result.setRuleAnchor(ruleAnchor);
    List<EvaluatedCondition> evaluatedConditions = new ArrayList<>(conditions.size());
    // boolean base = matcher.match(annotation, stream, getParent());
    boolean base = true;
    MatchContext context = new MatchContext(annotation, this, ruleMatch, after);

    List<AnnotationFS> textsMatched = annotation != null ? asList(annotation) : emptyList();

    // already set the matched text and inform others
    result.setMatchInfo(base, textsMatched, stream);
    RutaEnvironment environment = context.getParent().getEnvironment();
    environment.addMatchToVariable(ruleMatch, this, context, stream);
    if (base) {
      for (AbstractRutaCondition condition : conditions) {
        crowd.beginVisit(condition, null);
        EvaluatedCondition eval = condition.eval(context, stream, crowd);
        crowd.endVisit(condition, null);
        evaluatedConditions.add(eval);
        if (!eval.isValue()) {
          break;
        }
      }
    }
    result.setConditionInfo(base, evaluatedConditions);
    if (result.matched()) {
      boolean inlinedRulesMatched = matchInlinedRules(ruleMatch, result, stream, crowd);
      result.setInlinedRulesMatched(inlinedRulesMatched);
    } else {
      // update label for failed match after evaluating conditions
      environment.removeVariableValue(getLabel(), context);
    }
    ruleMatch.setMatched(ruleMatch.matched() && result.matched());
  }

  @Override
  public String toString() {
    String simpleName = getQuantifier().getClass().getSimpleName();
    return matcher.toString() + " " + (simpleName.equals("NormalQuantifier") ? "" : simpleName)
    // + (conditions.isEmpty() ? "" : "(" + conditions.toString() + ")" + "\\n")
    // + (actions.isEmpty() ? "" : "{" + actions.toString() + "}")
    ;
  }

  public RutaMatcher getMatcher() {
    return matcher;
  }

  @Override
  public List<AbstractRutaCondition> getConditions() {
    return conditions;
  }

  @Override
  public List<AbstractRutaAction> getActions() {
    return actions;
  }

  @Override
  public RutaBlock getParent() {
    return parent;
  }

  @Override
  public long estimateAnchors(RutaStream stream) {
    // TODO what about the match context?
    if (quantifier.isOptional(null, stream)) {
      return matcher.estimateAnchors(parent, stream) + Integer.MAX_VALUE;
    }
    return matcher.estimateAnchors(parent, stream);
  }

  public Collection<? extends AnnotationFS> getNextAnnotations(boolean after,
          AnnotationFS annotation, RutaStream stream) {
    if (after) {
      return matcher.getAnnotationsAfter(this, annotation, getParent(), stream);
    } else {
      return matcher.getAnnotationsBefore(this, annotation, getParent(), stream);
    }
  }

}
