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
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.JCas;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.action.AbstractRutaAction;
import org.apache.uima.ruta.condition.AbstractRutaCondition;
import org.apache.uima.ruta.rule.quantifier.RuleElementQuantifier;
import org.apache.uima.ruta.type.RutaFrame;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class ComposedRuleElement extends AbstractRuleElement implements RuleElementContainer {

  protected List<RuleElement> elements;

  protected RuleElementContainer caretaker;

  private Boolean conjunct = null;

  private Comparator<RuleMatch> ruleMatchComparator = new RuleMatchComparator();

  public ComposedRuleElement(List<RuleElement> elements, RuleElementQuantifier quantifier,
          List<AbstractRutaCondition> conditions, List<AbstractRutaAction> actions,
          RuleElementContainer container, RutaBlock parent) {
    super(quantifier, conditions, actions, container, parent);
    this.elements = elements;
    this.caretaker = new RuleElementCaretaker(this);
  }

  public void apply(RuleMatch match, RutaStream symbolStream, InferenceCrowd crowd) {
    applyRuleElements(match, symbolStream, crowd);
    super.apply(match, symbolStream, crowd);
  }

  public List<RuleMatch> startMatch(RuleMatch ruleMatch, RuleApply ruleApply,
          ComposedRuleElementMatch containerMatch, RuleElement entryPoint, RutaStream stream,
          InferenceCrowd crowd) {
    List<RuleMatch> result = new ArrayList<RuleMatch>();
    if (conjunct == null) {
      ComposedRuleElementMatch composedMatch = createComposedMatch(ruleMatch, containerMatch,
              stream);
      RuleElement anchorElement = getAnchoringRuleElement(stream);
      result = anchorElement.startMatch(ruleMatch, ruleApply, composedMatch, entryPoint, stream,
              crowd);
    } else if (!conjunct) {
      // disjunctive
      Map<RuleMatch, ComposedRuleElementMatch> ruleMatches = new LinkedHashMap<RuleMatch, ComposedRuleElementMatch>();
      for (RuleElement each : elements) {
        ComposedRuleElementMatch extendedContainerMatch = containerMatch.copy();
        RuleMatch extendedMatch = ruleMatch.copy(extendedContainerMatch, true);
        ComposedRuleElementMatch composedMatch = createComposedMatch(extendedMatch,
                extendedContainerMatch, stream);
        List<RuleMatch> startRuleMatches = each.startMatch(extendedMatch, null, composedMatch,
                this, stream, crowd);
        for (RuleMatch startRuleMatch : startRuleMatches) {
          ComposedRuleElementMatch startElementMatch = (ComposedRuleElementMatch) startRuleMatch
                  .getLastMatch(this, true);
          ruleMatches.put(startRuleMatch, startElementMatch);
        }
      }

      Map<RuleMatch, ComposedRuleElementMatch> mergedMatches = mergeDisjunctiveRuleMatches(
              ruleMatches, true, stream);
      Set<Entry<RuleMatch, ComposedRuleElementMatch>> entrySet = mergedMatches.entrySet();
      for (Entry<RuleMatch, ComposedRuleElementMatch> entry : entrySet) {
        RuleMatch eachRuleMatch = entry.getKey();
        ComposedRuleElementMatch eachComposedMatch = entry.getValue();
        AnnotationFS lastAnnotation = eachRuleMatch.getLastMatchedAnnotation(this, true, null,
                parent, stream);
        boolean failed = !eachComposedMatch.matched();
        List<RuleMatch> fallbackContinue = fallbackContinue(true, failed, lastAnnotation,
                eachRuleMatch, ruleApply, eachComposedMatch, null, entryPoint, stream, crowd);
        result.addAll(fallbackContinue);
      }
    } else if (conjunct) {
      // conjunctive
      Map<RuleMatch, ComposedRuleElementMatch> ruleMatches = new LinkedHashMap<RuleMatch, ComposedRuleElementMatch>();
      RuleElement anchoringRuleElement = getAnchoringRuleElement(stream);
      RutaRuleElement sideStepOrigin = null;

      if (anchoringRuleElement instanceof RutaRuleElement && hasAncestor(false)) {
        sideStepOrigin = (RutaRuleElement) anchoringRuleElement;
      }
      ComposedRuleElementMatch composedMatch = createComposedMatch(ruleMatch, containerMatch,
              stream);
      List<RuleMatch> startRuleMatches = anchoringRuleElement.startMatch(ruleMatch, null,
              composedMatch, this, stream, crowd);
      for (RuleMatch eachStartRuleMatch : startRuleMatches) {
        if (eachStartRuleMatch.matched()) {
          AnnotationFS prefixAnnotation = getPrefixAnnotation(eachStartRuleMatch, stream);
          for (RuleElement each : elements) {
            if (each.equals(anchoringRuleElement)) {
              continue;
            }
            ComposedRuleElementMatch startElementMatch = (ComposedRuleElementMatch) eachStartRuleMatch
                    .getLastMatch(this, true);
            List<RuleMatch> continueMatch = each.continueMatch(true, prefixAnnotation,
                    eachStartRuleMatch, null, startElementMatch, null, this, stream, crowd);
            for (RuleMatch startRuleMatch : continueMatch) {
              ComposedRuleElementMatch elementMatch = (ComposedRuleElementMatch) startRuleMatch
                      .getLastMatch(this, true);
              ruleMatches.put(startRuleMatch, elementMatch);
            }
          }
        }
      }

      Map<RuleMatch, ComposedRuleElementMatch> mergedMatches = mergeConjunctiveRuleMatches(
              ruleMatches, true);
      Set<Entry<RuleMatch, ComposedRuleElementMatch>> entrySet = mergedMatches.entrySet();
      for (Entry<RuleMatch, ComposedRuleElementMatch> entry : entrySet) {
        RuleMatch eachRuleMatch = entry.getKey();
        ComposedRuleElementMatch eachComposedMatch = entry.getValue();
        AnnotationFS lastAnnotation = eachRuleMatch.getLastMatchedAnnotation(this, true, null,
                parent, stream);
        boolean failed = !eachComposedMatch.matched();
        List<AnnotationFS> textsMatched = eachComposedMatch.getTextsMatched();
        if ((!stream.isGreedyAnchoring() && !stream.isOnlyOnce())
                || !earlyExit(textsMatched.get(0), ruleApply, stream)) {
          List<RuleMatch> fallbackContinue = fallbackContinue(true, failed, lastAnnotation,
                  eachRuleMatch, ruleApply, eachComposedMatch, sideStepOrigin, entryPoint, stream,
                  crowd);
          result.addAll(fallbackContinue);
        }
      }
    }
    return result;
  }

  private AnnotationFS getPrefixAnnotation(RuleMatch ruleMatch, RutaStream stream) {
    AnnotationFS lastMatchedAnnotation = ruleMatch.getLastMatchedAnnotation(this, true, null,
            parent, stream);
    if (lastMatchedAnnotation.getBegin() == 0) {
      JCas jCas = stream.getJCas();
      AnnotationFS dummy = new RutaFrame(jCas, 0, 0);
      return dummy;
    }
    return stream.getEndAnchor(lastMatchedAnnotation.getBegin());
  }

  protected ComposedRuleElementMatch createComposedMatch(RuleMatch ruleMatch,
          ComposedRuleElementMatch containerMatch, RutaStream stream) {
    ComposedRuleElementMatch composedMatch = new ComposedRuleElementMatch(this, containerMatch);
    includeMatch(ruleMatch, containerMatch, composedMatch, stream);
    return composedMatch;
  }

  public List<RuleMatch> continueMatch(boolean after, AnnotationFS annotation, RuleMatch ruleMatch,
          RuleApply ruleApply, ComposedRuleElementMatch containerMatch,
          RutaRuleElement sideStepOrigin, RuleElement entryPoint, RutaStream stream,
          InferenceCrowd crowd) {
    List<RuleMatch> result = new ArrayList<RuleMatch>();
    if (conjunct == null) {
      // inner next sequential
      RuleElement nextElement = getNextElement(after, this);
      if (nextElement != null) {
        ComposedRuleElementMatch composedMatch = createComposedMatch(ruleMatch, containerMatch,
                stream);
        result = nextElement.continueMatch(after, annotation, ruleMatch, ruleApply, composedMatch,
                sideStepOrigin, entryPoint, stream, crowd);
      } else {
        result = fallback(after, false, annotation, ruleMatch, ruleApply, containerMatch,
                sideStepOrigin, entryPoint, stream, crowd);
      }
    } else if (!conjunct) {
      // disjunctive
      Map<RuleMatch, ComposedRuleElementMatch> ruleMatches = new HashMap<RuleMatch, ComposedRuleElementMatch>();
      for (RuleElement each : elements) {
        ComposedRuleElementMatch extendedContainerMatch = containerMatch.copy();
        RuleMatch extendedMatch = ruleMatch.copy(extendedContainerMatch, after);
        ComposedRuleElementMatch composedMatch = createComposedMatch(extendedMatch,
                extendedContainerMatch, stream);
        List<RuleMatch> continueRuleMatches = each.continueMatch(after, annotation, extendedMatch,
                null, composedMatch, sideStepOrigin, this, stream, crowd);
        for (RuleMatch continueRuleMatch : continueRuleMatches) {
          ComposedRuleElementMatch startElementMatch = (ComposedRuleElementMatch) continueRuleMatch
                  .getLastMatch(this, true);
          ruleMatches.put(continueRuleMatch, startElementMatch);
        }
      }
      // TODO sort matches, no need to merge them, right?!
      Map<RuleMatch, ComposedRuleElementMatch> mergedMatches = mergeDisjunctiveRuleMatches(
              ruleMatches, after, stream);
      Set<Entry<RuleMatch, ComposedRuleElementMatch>> entrySet = mergedMatches.entrySet();
      for (Entry<RuleMatch, ComposedRuleElementMatch> entry : entrySet) {
        RuleMatch eachRuleMatch = entry.getKey();
        ComposedRuleElementMatch eachComposedMatch = entry.getValue();
        AnnotationFS lastAnnotation = eachRuleMatch.getLastMatchedAnnotation(this, after,
                annotation, parent, stream);
        boolean failed = !eachComposedMatch.matched();
        List<AnnotationFS> textsMatched = eachRuleMatch.getMatchedAnnotationsOfRoot();
        if ((!stream.isGreedyAnchoring() && !stream.isOnlyOnce())
                || (!textsMatched.isEmpty() && !earlyExit(textsMatched.get(0), ruleApply, stream))) {
          List<RuleMatch> fallbackContinue = fallbackContinue(after, failed, lastAnnotation,
                  eachRuleMatch, ruleApply, eachComposedMatch, sideStepOrigin, entryPoint, stream,
                  crowd);
          result.addAll(fallbackContinue);
        }
      }
    } else if (conjunct) {
      // conjunctive
      Map<RuleMatch, ComposedRuleElementMatch> ruleMatches = new HashMap<RuleMatch, ComposedRuleElementMatch>();

      RuleElement anchoringRuleElement = getAnchoringRuleElement(stream);
      ComposedRuleElementMatch composedMatch = createComposedMatch(ruleMatch, containerMatch,
              stream);
      List<RuleMatch> startRuleMatches = anchoringRuleElement.continueMatch(after, annotation,
              ruleMatch, null, composedMatch, sideStepOrigin, this, stream, crowd);

      for (RuleMatch eachStartRuleMatch : startRuleMatches) {
        for (RuleElement each : elements) {
          if (each.equals(anchoringRuleElement)) {
            continue;
          }
          ComposedRuleElementMatch startElementMatch = (ComposedRuleElementMatch) eachStartRuleMatch
                  .getLastMatch(this, true);
          List<RuleMatch> continueMatch = each.continueMatch(true, annotation, eachStartRuleMatch,
                  null, startElementMatch, null, this, stream, crowd);
          for (RuleMatch startRuleMatch : continueMatch) {
            ComposedRuleElementMatch elementMatch = (ComposedRuleElementMatch) startRuleMatch
                    .getLastMatch(this, true);
            ruleMatches.put(startRuleMatch, elementMatch);
          }
        }
      }
      Map<RuleMatch, ComposedRuleElementMatch> mergedMatches = mergeConjunctiveRuleMatches(
              ruleMatches, after);
      Set<Entry<RuleMatch, ComposedRuleElementMatch>> entrySet = mergedMatches.entrySet();
      for (Entry<RuleMatch, ComposedRuleElementMatch> entry : entrySet) {
        RuleMatch eachRuleMatch = entry.getKey();
        ComposedRuleElementMatch eachComposedMatch = entry.getValue();
        AnnotationFS lastAnnotation = eachRuleMatch.getLastMatchedAnnotation(this, after,
                annotation, parent, stream);

        boolean failed = !eachComposedMatch.matched();

        List<AnnotationFS> textsMatched = eachRuleMatch.getMatchedAnnotationsOfRoot();
        if ((!stream.isGreedyAnchoring() && !stream.isOnlyOnce())
                || (!textsMatched.isEmpty() && !earlyExit(textsMatched.get(0), ruleApply, stream))) {
          List<RuleMatch> fallbackContinue = fallbackContinue(after, failed, lastAnnotation,
                  eachRuleMatch, ruleApply, eachComposedMatch, sideStepOrigin, entryPoint, stream,
                  crowd);
          result.addAll(fallbackContinue);
        }
      }
    }
    return result;
  }

  private Map<RuleMatch, ComposedRuleElementMatch> mergeConjunctiveRuleMatches(
          Map<RuleMatch, ComposedRuleElementMatch> ruleMatches, boolean direction) {
    // TODO hotfix: this needs a correct implementation
    return ruleMatches;
    // Map<RuleMatch, ComposedRuleElementMatch> result = new HashMap<RuleMatch,
    // ComposedRuleElementMatch>();
    // Set<Entry<RuleMatch, ComposedRuleElementMatch>> entrySet = ruleMatches.entrySet();
    // Entry<RuleMatch, ComposedRuleElementMatch> largestEntry = null;
    // boolean allMatched = true;
    // AnnotationFS largestAnnotation = null;
    // for (Entry<RuleMatch, ComposedRuleElementMatch> entry : entrySet) {
    // RuleMatch ruleMatch = entry.getKey();
    // ComposedRuleElementMatch elementMatch = entry.getValue();
    // allMatched &= elementMatch.matched();
    // AnnotationFS lastMatchedAnnotation = ruleMatch.getLastMatchedAnnotation(getFirstElement(),
    // direction);
    // if (largestEntry == null) {
    // largestEntry = entry;
    // largestAnnotation = lastMatchedAnnotation;
    // } else {
    // if (lastMatchedAnnotation != null
    // && largestAnnotation != null
    // && lastMatchedAnnotation.getCoveredText().length() > largestAnnotation
    // .getCoveredText().length()) {
    // largestEntry = entry;
    // largestAnnotation = lastMatchedAnnotation;
    // }
    // }
    // }
    // if (allMatched) {
    // result.put(largestEntry.getKey(), largestEntry.getValue());
    // }
    // return result;
  }

  private Map<RuleMatch, ComposedRuleElementMatch> mergeDisjunctiveRuleMatches(
          Map<RuleMatch, ComposedRuleElementMatch> ruleMatches, boolean direction, RutaStream stream) {
    // TODO hotfix: this needs a correct implementation
    Map<RuleMatch, ComposedRuleElementMatch> result = new TreeMap<RuleMatch, ComposedRuleElementMatch>(
            ruleMatchComparator);
    Set<Entry<RuleMatch, ComposedRuleElementMatch>> entrySet = ruleMatches.entrySet();
    Entry<RuleMatch, ComposedRuleElementMatch> largestEntry = null;
    AnnotationFS largestAnnotation = null;
    for (Entry<RuleMatch, ComposedRuleElementMatch> entry : entrySet) {
      RuleMatch ruleMatch = entry.getKey();
      ComposedRuleElementMatch elementMatch = entry.getValue();
      if (elementMatch.matched()) {
        result.put(ruleMatch, elementMatch);
      } else {
        AnnotationFS lastMatchedAnnotation = ruleMatch.getLastMatchedAnnotation(getFirstElement(),
                direction, null, parent, stream);
        if (largestEntry == null) {
          largestEntry = entry;
          largestAnnotation = lastMatchedAnnotation;
        } else {
          if (lastMatchedAnnotation != null
                  && largestAnnotation != null
                  && lastMatchedAnnotation.getCoveredText().length() > largestAnnotation
                          .getCoveredText().length()) {
            largestEntry = entry;
            largestAnnotation = lastMatchedAnnotation;
          }
        }
      }
    }
    if (result.isEmpty()) {
      if (largestEntry != null) {
        result.put(largestEntry.getKey(), largestEntry.getValue());
      } else {
        result = ruleMatches;
      }
    }
    return result;
  }

  public List<RuleMatch> continueOwnMatch(boolean after, AnnotationFS annotation,
          RuleMatch ruleMatch, RuleApply ruleApply, ComposedRuleElementMatch containerMatch,
          RutaRuleElement sideStepOrigin, RuleElement entryPoint, RutaStream stream,
          InferenceCrowd crowd) {
    List<RuleMatch> result = new ArrayList<RuleMatch>();
    if (!stream.isSimpleGreedyForComposed()) {
      result = continueMatch(after, annotation, ruleMatch, ruleApply, containerMatch,
              sideStepOrigin, entryPoint, stream, crowd);
    } else {
      // HOTFIX
      boolean stopMatching = false;
      boolean failed = false;
      AnnotationFS nextAnnotation = annotation;
      while (!stopMatching) {
        RuleElement nextElement = getNextElement(after, this);
        if (nextElement != null) {
          ComposedRuleElementMatch composedMatch = createComposedMatch(ruleMatch, containerMatch,
                  stream);
          nextElement.continueMatch(after, nextAnnotation, ruleMatch, ruleApply, composedMatch,
                  sideStepOrigin, this, stream, crowd);
          ComposedRuleElementMatch parentContainerMatch = containerMatch.getContainerMatch();
          List<RuleElementMatch> match = getMatch(ruleMatch, parentContainerMatch);
          int lenghtBefore = match.size();
          List<RuleElementMatch> evaluateMatches = quantifier.evaluateMatches(match, parent,
                  stream, crowd);
          ruleMatch.setMatched(ruleMatch.matched() && evaluateMatches != null);
          if (evaluateMatches != null && evaluateMatches.size() != lenghtBefore) {
            failed = true;
            stopMatching = true;
          }
          if (!quantifier.continueMatch(after, nextAnnotation, this, ruleMatch, containerMatch,
                  stream, crowd)) {
            stopMatching = true;
          }
          if (evaluateMatches != null) {
            List<AnnotationFS> textsMatched = evaluateMatches.get(evaluateMatches.size() - 1)
                    .getTextsMatched();
            nextAnnotation = textsMatched.get(textsMatched.size() - 1);
          }
        } else {
          stopMatching = true;
        }
      }
      result = fallback(after, failed, nextAnnotation, ruleMatch, ruleApply, containerMatch,
              sideStepOrigin, entryPoint, stream, crowd);
    }
    return result;
  }

  public List<RuleMatch> fallbackContinue(boolean after, boolean failed, AnnotationFS annotation,
          RuleMatch ruleMatch, RuleApply ruleApply, ComposedRuleElementMatch containerMatch,
          RutaRuleElement sideStepOrigin, RuleElement entryPoint, RutaStream stream,
          InferenceCrowd crowd) {
    List<RuleMatch> result = new ArrayList<RuleMatch>();
    RuleElementContainer container = getContainer();
    doMatch(containerMatch, ruleMatch, stream, crowd);
    if (this.equals(entryPoint) && ruleApply == null) {
      result.add(ruleMatch);
    } else if (container == null) {
      result = fallback(after, failed, annotation, ruleMatch, ruleApply, containerMatch,
              sideStepOrigin, entryPoint, stream, crowd);
    } else {
      ComposedRuleElementMatch parentContainerMatch = containerMatch.getContainerMatch();
      RuleElement nextElement = container.getNextElement(after, this);
      List<RuleElementMatch> match = getMatch(ruleMatch, parentContainerMatch);
      int sizeBefore = match.size();
      boolean continueMatch = quantifier.continueMatch(after, annotation, this, ruleMatch,
              parentContainerMatch, stream, crowd);
      List<RuleElementMatch> evaluateMatches = quantifier.evaluateMatches(match, parent, stream,
              crowd);
      int sizeAfter = evaluateMatches != null ? evaluateMatches.size() : sizeBefore;
      boolean removedFailedMatches = sizeAfter < sizeBefore;
      if(removedFailedMatches) {
        containerMatch.enforceUpdate();
      }
      ruleMatch.setMatched((ruleMatch.matched() || removedFailedMatches)
              && (evaluateMatches != null || continueMatch || sideStepOrigin != null));
      if (failed) {
        // TODO failed was caused by a child: should here failed = false?
        if (!removedFailedMatches && evaluateMatches != null && continueMatch) {
          result = continueOwnMatch(after, annotation, ruleMatch, ruleApply, parentContainerMatch,
                  sideStepOrigin, entryPoint, stream, crowd);
        } else if (nextElement != null) {
          AnnotationFS backtrackedAnnotation = getBacktrackedAnnotation(after, evaluateMatches,
                  annotation);
          if (backtrackedAnnotation != null) {
            result = nextElement.continueMatch(after, backtrackedAnnotation, ruleMatch, ruleApply,
                    parentContainerMatch, sideStepOrigin, entryPoint, stream, crowd);
          } else {
            result = fallback(after, failed, annotation, ruleMatch, ruleApply,
                    parentContainerMatch, sideStepOrigin, entryPoint, stream, crowd);
          }
        } else {
          if (this.equals(entryPoint)) {
            // hotfix for UIMA-3820
            result.add(ruleMatch);
          } else {
            result = fallback(after, failed, annotation, ruleMatch, ruleApply,
                    parentContainerMatch, sideStepOrigin, entryPoint, stream, crowd);
          }
        }
      } else {
        if (continueMatch && !removedFailedMatches) {
          result = continueOwnMatch(after, annotation, ruleMatch, ruleApply, parentContainerMatch,
                  sideStepOrigin, entryPoint, stream, crowd);
        } else if (nextElement != null) {
          result = nextElement.continueMatch(after, annotation, ruleMatch, ruleApply,
                  parentContainerMatch, sideStepOrigin, entryPoint, stream, crowd);
        } else {
          result = fallback(after, failed, annotation, ruleMatch, ruleApply, parentContainerMatch,
                  sideStepOrigin, entryPoint, stream, crowd);
        }
      }
    }
    return result;
  }

  private AnnotationFS getBacktrackedAnnotation(boolean after,
          List<RuleElementMatch> evaluateMatches, AnnotationFS annotation) {
    if (evaluateMatches == null) {
      return null;
    }
    if (evaluateMatches.isEmpty()) {
      return annotation;
    }
    if (after) {
      List<AnnotationFS> textsMatched = evaluateMatches.get(evaluateMatches.size() - 1)
              .getTextsMatched();
      if (textsMatched.isEmpty()) {
        return null;
      }
      AnnotationFS backtrackedAnnotation = textsMatched.get(textsMatched.size() - 1);
      return backtrackedAnnotation;
    } else {
      List<AnnotationFS> textsMatched = evaluateMatches.get(0).getTextsMatched();
      if (textsMatched.isEmpty()) {
        return null;
      }
      AnnotationFS backtrackedAnnotation = textsMatched.get(0);
      return backtrackedAnnotation;
    }
  }

  private List<RuleMatch> fallback(boolean after, boolean failed, AnnotationFS annotation,
          RuleMatch ruleMatch, RuleApply ruleApply, ComposedRuleElementMatch containerMatch,
          RutaRuleElement sideStepOrigin, RuleElement entryPoint, RutaStream stream,
          InferenceCrowd crowd) {
    List<RuleMatch> result = new ArrayList<RuleMatch>();
    RuleElementContainer parentContainer = getContainer();
    if (parentContainer instanceof ComposedRuleElement) {
      ComposedRuleElement parentElement = (ComposedRuleElement) parentContainer;
      result = parentElement.fallbackContinue(after, failed, annotation, ruleMatch, ruleApply,
              containerMatch, sideStepOrigin, entryPoint, stream, crowd);
    } else if (sideStepOrigin != null) {
      result = sideStepOrigin.continueSideStep(after, ruleMatch, ruleApply, containerMatch,
              entryPoint, stream, crowd);
    } else {
      result.add(ruleMatch);
      doneMatching(ruleMatch, ruleApply, stream, crowd);
    }
    return result;
  }

  private void includeMatch(RuleMatch ruleMatch, ComposedRuleElementMatch containerMatch,
          ComposedRuleElementMatch composedMatch, RutaStream stream) {
    if (containerMatch == null) {
      ruleMatch.setRootMatch(composedMatch);
    } else {
      containerMatch.addInnerMatch(this, composedMatch, false, stream);
    }
  }

  private void doMatch(ComposedRuleElementMatch match, RuleMatch ruleMatch, RutaStream stream,
          InferenceCrowd crowd) {
    List<AnnotationFS> textsMatched = match.getTextsMatched();
    if (textsMatched == null || textsMatched.isEmpty()) {
      match.evaluateInnerMatches(true, stream);
      return;
    }
    int begin = textsMatched.get(0).getBegin();
    int end = textsMatched.get(textsMatched.size() - 1).getEnd();
    AnnotationFS annotation = stream.getCas().createAnnotation(stream.getCas().getAnnotationType(),
            begin, end);

    List<EvaluatedCondition> evaluatedConditions = new ArrayList<EvaluatedCondition>(
            conditions.size());
    for (AbstractRutaCondition condition : conditions) {
      crowd.beginVisit(condition, null);
      EvaluatedCondition eval = condition.eval(annotation, this, stream, crowd);
      crowd.endVisit(condition, null);
      evaluatedConditions.add(eval);
    }
    match.setConditionInfo(evaluatedConditions);
    match.evaluateInnerMatches(true, stream);
    boolean inlinedRulesMatched = matchInnerRules(ruleMatch, stream, crowd);
    match.setInlinedRulesMatched(inlinedRulesMatched);
  }

  public Collection<AnnotationFS> getAnchors(RutaStream stream) {
    RuleElement anchorElement = getAnchoringRuleElement(stream);
    Collection<AnnotationFS> anchors = anchorElement.getAnchors(stream);
    return anchors;
  }

  public long estimateAnchors(RutaStream stream) {
    long result = 1;
    for (RuleElement each : elements) {
      result += each.estimateAnchors(stream);
    }
    if (quantifier.isOptional(getParent(), stream)) {
      // three times since sibling elements maybe need to be checked
      result *= 3 * (int) stream.getIndexPenalty();
    }
    return result;
  }

  public RuleElement getAnchoringRuleElement(RutaStream stream) {
    return caretaker.getAnchoringRuleElement(stream);
  }

  public List<RuleElement> getRuleElements() {
    return elements;
  }

  public void setRuleElements(List<RuleElement> elements) {
    this.elements = elements;
  }

  public RuleElement getFirstElement() {
    return caretaker.getFirstElement();
  }

  public RuleElement getLastElement() {
    return caretaker.getLastElement();
  }

  public void applyRuleElements(RuleMatch ruleMatch, RutaStream stream, InferenceCrowd crowd) {
    caretaker.applyRuleElements(ruleMatch, stream, crowd);
  }

  public String toString() {
    String con = "";
    if (conjunct != null) {
      con = conjunct ? "&" : "|";
    }
    String simpleName = getQuantifier().getClass().getSimpleName();
    return "(" + con + (elements == null ? "null" : elements.toString()) + ")"
            + (simpleName.equals("NormalQuantifier") ? "" : simpleName)
    // + (conditions.isEmpty() ? "" : "(" + conditions.toString() + ")" + "\\n")
    // + (actions.isEmpty() ? "" : "{" + actions.toString() + "}")
    ;
  }

  public RuleElement getNextElement(boolean after, RuleElement ruleElement) {
    // return caretaker.getNextElement(after, ruleElement);
    if (conjunct == null || this.equals(ruleElement)) {
      return caretaker.getNextElement(after, ruleElement);
    } else {
      return null;
    }
  }

  public void setConjunct(Boolean conjunct) {
    this.conjunct = conjunct;
  }

  public Boolean getConjunct() {
    return conjunct;
  }

}
