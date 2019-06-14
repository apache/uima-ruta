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

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.JCas;
import org.apache.uima.ruta.RutaEnvironment;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.action.AbstractRutaAction;
import org.apache.uima.ruta.block.RutaBlock;
import org.apache.uima.ruta.condition.AbstractRutaCondition;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.expression.AnnotationTypeExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.type.RutaBasic;
import org.apache.uima.ruta.type.RutaFrame;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class WildCardRuleElement extends AbstractRuleElement {

  public WildCardRuleElement(List<AbstractRutaCondition> conditions,
          List<AbstractRutaAction> actions, RuleElementContainer container, RutaBlock parent) {
    super(null, conditions, actions, container, parent);
  }

  @Override
  public List<RuleMatch> startMatch(RuleMatch ruleMatch, RuleApply ruleApply,
          ComposedRuleElementMatch containerMatch, RuleElement entryPoint, RutaStream stream,
          InferenceCrowd crowd) {
    List<RuleMatch> result = continueMatch(true, null, ruleMatch, ruleApply, containerMatch, null,
            entryPoint, stream, crowd);
    return result;
  }

  @Override
  public List<RuleMatch> continueMatch(boolean after, AnnotationFS annotation, RuleMatch ruleMatch,
          RuleApply ruleApply, ComposedRuleElementMatch containerMatch,
          RutaRuleElement sideStepOrigin, RuleElement entryPoint, RutaStream stream,
          InferenceCrowd crowd) {
    Pair<RuleElement, Integer> next = getNextRuleElement(after, this);
    RuleElement nextElement = next.getLeft();
    int nextDepth = next.getRight().intValue();
    List<RuleMatch> result = tryWithNextRuleElement(nextElement, after, annotation, ruleMatch,
            ruleApply, containerMatch, nextDepth, sideStepOrigin, entryPoint, stream, crowd);
    return result;
  }

  private Pair<RuleElement, Integer> getNextRuleElement(boolean after, RuleElement element) {
    RuleElement nextElement = null;
    RuleElement current = element;
    int nextDepth = -1;
    while (nextElement == null && current != null && current.getContainer() != null) {
      RuleElementContainer container = current.getContainer();
      nextElement = container.getNextElement(after, current);
      if (container instanceof RuleElement) {
        current = (RuleElement) container;
        nextDepth++;
      } else {
        break;
      }
    }
    if (nextElement == null) {
      nextDepth = 0;
    }
    return new ImmutablePair<RuleElement, Integer>(nextElement, Integer.valueOf(nextDepth));
  }

  private List<RuleMatch> tryWithNextRuleElement(RuleElement nextElement, boolean after,
          AnnotationFS annotation, RuleMatch ruleMatch, RuleApply ruleApply,
          ComposedRuleElementMatch containerMatch, int nextDepth, RutaRuleElement sideStepOrigin,
          RuleElement entryPoint, RutaStream stream, InferenceCrowd crowd) {
    List<RuleMatch> result = new ArrayList<RuleMatch>();
    // what is the next stuff that should match?
    if (nextElement == null) {
      AnnotationFS afs = getCoveredByWildCard(after, annotation, null, stream);
      doMatch(afs, ruleMatch, containerMatch, annotation == null, stream, crowd);
      ComposedRuleElement composed = (ComposedRuleElement) getContainer();
      // [Peter] was ruleMatch.matched(), but it did not fail when matches?!
      result = composed.fallbackContinue(after, !ruleMatch.matched(), afs, ruleMatch, ruleApply,
              containerMatch, sideStepOrigin, entryPoint, stream, crowd);
    } else if (nextElement instanceof RutaRuleElement) {
      RutaRuleElement re = (RutaRuleElement) nextElement;
      RutaMatcher matcher = re.getMatcher();
      if (matcher instanceof RutaAnnotationTypeMatcher) {
        result = tryWithNextAnnotationType(after, annotation, nextElement, null, ruleMatch,
                ruleApply, containerMatch, nextDepth, sideStepOrigin, entryPoint, stream, crowd);
      } else if (matcher instanceof RutaLiteralMatcher) {
        result = tryWithNextLiteral(after, annotation, re, ruleMatch, ruleApply, containerMatch,
                nextDepth, sideStepOrigin, stream, crowd);
      }
    } else if (nextElement instanceof ComposedRuleElement) {
      ComposedRuleElement cre = ((ComposedRuleElement) nextElement);
      result = tryWithNextComposed(after, annotation, cre, ruleMatch, ruleApply, containerMatch,
              nextDepth, sideStepOrigin, stream, crowd);
    } else if (nextElement instanceof WildCardRuleElement) {
      // another wildcard? seriously? then just assume its an "Annotation" type
      CAS cas = stream.getCas();
      result = tryWithNextAnnotationType(after, annotation, nextElement, cas.getAnnotationType(),
              ruleMatch, ruleApply, containerMatch, nextDepth, sideStepOrigin, entryPoint, stream,
              crowd);
    }
    return result;
  }

  private List<RuleMatch> tryWithNextComposed(boolean after, AnnotationFS annotation,
          ComposedRuleElement cre, RuleMatch ruleMatch, RuleApply ruleApply,
          ComposedRuleElementMatch containerMatch, int nextDepth, RutaRuleElement sideStepOrigin,
          RutaStream stream, InferenceCrowd crowd) {
    List<RuleMatch> result = new ArrayList<RuleMatch>();
    AnnotationFS nextOne = annotation;
    boolean doneHere = false;
    while (!doneHere
            && (nextOne = getNextPositionForComposed(cre, after, nextOne, stream)) != null) {
      int pointer = after ? nextOne.getBegin() : nextOne.getEnd();
      RutaBasic anchor = stream.getAnchor(!after, pointer);
      ComposedRuleElementMatch extendedContainerMatch = containerMatch.copy();
      RuleMatch extendedMatch = ruleMatch.copy(extendedContainerMatch, after);
      AnnotationFS coveredByWildCard = getCoveredByWildCard(after, annotation, nextOne, stream);
      doMatch(coveredByWildCard, extendedMatch, extendedContainerMatch, annotation == null, stream,
              crowd);
      if (extendedMatch.matched()) {
        ComposedRuleElementMatch nextContainerMatch = getContainerMatchOfNextElement(
                extendedContainerMatch, nextDepth);
        // Hotfix for UIMA-3002
        int applied = ruleApply.getApplied();
        if (anchor == null) {
          result = cre.startMatch(extendedMatch, ruleApply, nextContainerMatch, cre, stream, crowd);
        } else {
          result = cre.continueMatch(after, anchor, extendedMatch, ruleApply, nextContainerMatch,
                  sideStepOrigin, cre, stream, crowd);
        }
        List<RuleElementMatch> nextList = nextContainerMatch.getInnerMatches().get(cre);
        boolean matched = hasMatched(nextList);
        if (!matched) {
          // Hotfix for UIMA-3002
          if (ruleApply.getApplied() > applied) {
            doneHere = true;
          }
          // end hotfix
          nextOne = stream.getAnchor(after, getNextPointer(!after, nextOne));
        } else {
          doneHere = true;
        }
      } else {
        // conditions of wildcard element did not match, try the next possible anchor for the
        // next rule element
        nextOne = annotation;
      }
    }
    if (!doneHere) {
      ComposedRuleElementMatch nextContainerMatch = getContainerMatchOfNextElement(containerMatch,
              nextDepth);
      result = cre.continueMatch(after, annotation, ruleMatch, ruleApply, nextContainerMatch,
              sideStepOrigin, null, stream, crowd);
    }
    return result;
  }

  private boolean hasMatched(List<RuleElementMatch> nextList) {
    if (nextList == null || nextList.isEmpty()) {
      return false;
    }
    boolean result = true;
    for (RuleElementMatch each : nextList) {
      result &= each.matched();
    }
    return result;
  }

  private AnnotationFS getNextPositionForComposed(ComposedRuleElement cre, boolean after,
          AnnotationFS annotation, RutaStream stream) {
    RuleElement element = getNextAtomicRuleElement(cre, after);
    AnnotationFS result = null;
    Boolean conjunct = cre.getConjunct();
    if (element instanceof WildCardRuleElement) {
      if (after) {
        return stream.getAnchor(after, annotation.getEnd());
      } else {
        return stream.getAnchor(after, annotation.getBegin());
      }
    } else if (conjunct != null && !conjunct) {
      // disjunctive
      List<RuleElement> ruleElements = cre.getRuleElements();
      List<AnnotationFS> nextPostions = new ArrayList<AnnotationFS>();
      for (RuleElement ruleElement : ruleElements) {
        if (ruleElement instanceof ComposedRuleElement) {
          AnnotationFS nextPositionForComposed = getNextPositionForComposed(
                  (ComposedRuleElement) ruleElement, after, annotation, stream);
          if (nextPositionForComposed != null) {
            nextPostions.add(nextPositionForComposed);
          }
        } else if (ruleElement instanceof RutaRuleElement) {
          AnnotationFS nextPositionForAtomic = getNextPositionForAtomic(after, annotation, stream,
                  ruleElement, result);
          if (nextPositionForAtomic != null) {
            nextPostions.add(nextPositionForAtomic);
          }
        }
      }
      if (!nextPostions.isEmpty()) {

        Collections.sort(nextPostions, new AnnotationComparator());
        if (after) {
          result = nextPostions.get(0);
        } else {
          result = nextPostions.get(nextPostions.size() - 1);
        }
      }
    } else {
      result = getNextPositionForAtomic(after, annotation, stream, element, result);
    }

    return result;
  }

  private AnnotationFS getNextPositionForAtomic(boolean after, AnnotationFS annotation,
          RutaStream stream, RuleElement element, AnnotationFS result) {
    RutaRuleElement re = (RutaRuleElement) element;
    RutaMatcher matcher = re.getMatcher();

    if (matcher instanceof RutaLiteralMatcher) {
      RutaLiteralMatcher lm = (RutaLiteralMatcher) matcher;
      IStringExpression expression = lm.getExpression();
      MatchContext context = new MatchContext(this, null, after);
      String stringValue = expression.getStringValue(context, stream);
      AnnotationFS documentAnnotation = stream.getDocumentAnnotation();
      int delta = documentAnnotation.getBegin();
      String document = documentAnnotation.getCoveredText();
      int pointer = annotation.getEnd() - delta;
      int indexOf = document.indexOf(stringValue, pointer);
      if (indexOf < 0) {
        return null;
      } else {
        return stream.getAnchor(after, indexOf);
      }
    } else {
      FSIterator<AnnotationFS> iterator = getIterator(after, annotation, re, null, stream);
      if (iterator == null) {
        return null;
      }

      if (iterator.isValid()) {
        result = iterator.get();
        if (annotation != null && (after && result.getEnd() == annotation.getEnd())
                || (!after && result.getBegin() == annotation.getBegin())) {
          moveOn(after, iterator, stream);
          if (iterator.isValid()) {
            result = iterator.get();
          } else {
            result = null;
          }
        }

      }
    }
    return result;
  }

  private RuleElement getNextAtomicRuleElement(ComposedRuleElement cre, boolean after) {
    if (after) {
      RuleElement firstElement = cre.getFirstElement();
      if (firstElement instanceof ComposedRuleElement) {
        return getNextAtomicRuleElement((ComposedRuleElement) firstElement, after);
      } else {
        return firstElement;
      }
    } else {
      RuleElement lastElement = cre.getLastElement();
      if (lastElement instanceof ComposedRuleElement) {
        return getNextAtomicRuleElement((ComposedRuleElement) lastElement, after);
      } else {
        return lastElement;
      }
    }
  }

  private List<RuleMatch> tryWithNextAnnotationType(boolean after, AnnotationFS annotation,
          RuleElement nextElement, Type defaultType, RuleMatch ruleMatch, RuleApply ruleApply,
          ComposedRuleElementMatch containerMatch, int nextDepth, RutaRuleElement sideStepOrigin,
          RuleElement entryPoint, RutaStream stream, InferenceCrowd crowd) {
    List<RuleMatch> result = new ArrayList<RuleMatch>();
    FSIterator<AnnotationFS> iterator = getIterator(after, annotation, nextElement, defaultType,
            stream);
    // already matched something maybe, but now at the end of the document
    if (iterator == null || !iterator.isValid()) {
      RuleElementContainer c = getContainer();
      if (c instanceof ComposedRuleElement) {
        ComposedRuleElement cre = (ComposedRuleElement) c;

        MatchContext context = new MatchContext(this, null, after);
        if (nextElement.getQuantifier().isOptional(context, stream)) {
          // optional did not match -> match complete window/document
          // TODO refactor

          AnnotationFS nextAnchor = getNextAnchor(after, annotation, nextElement, ruleMatch,
                  containerMatch, sideStepOrigin, stream, crowd);

          AnnotationFS coveredByWildCard = getCoveredByWildCard(after, annotation, nextAnchor,
                  stream);
          doMatch(coveredByWildCard, ruleMatch, containerMatch, annotation == null, stream, crowd);
          if (ruleMatch.matched()) {
            ComposedRuleElementMatch nextContainerMatch = getContainerMatchOfNextElement(
                    containerMatch, nextDepth);
            if (coveredByWildCard == null) {
              result = nextElement.startMatch(ruleMatch, ruleApply, nextContainerMatch, nextElement,
                      stream, crowd);
            } else {
              // TODO match and containermatch should be on the correct level!
              result = nextElement.continueMatch(after, coveredByWildCard, ruleMatch, ruleApply,
                      nextContainerMatch, sideStepOrigin, nextElement, stream, crowd);
            }
          } else {
            result = cre.fallbackContinue(after, true, annotation, ruleMatch, ruleApply,
                    containerMatch, sideStepOrigin, entryPoint, stream, crowd);
          }
        } else {

          if (ruleApply == null && entryPoint != null && entryPoint.equals(nextElement)) {
            return result;
          } else {
            result = cre.fallbackContinue(after, true, annotation, ruleMatch, ruleApply,
                    containerMatch, sideStepOrigin, entryPoint, stream, crowd);
          }
        }
      }
      return result;
    }
    if (iterator.isValid() && !stream.isVisible(iterator.get())) {
      moveOn(after, iterator, stream);
    }
    boolean doneHere = false;
    while (!doneHere && iterator.isValid() && stream.isVisible(iterator.get())) {
      AnnotationFS nextOne = iterator.get();
      int pointer = after ? nextOne.getBegin() : nextOne.getEnd();
      AnnotationFS anchor = stream.getAnchor(!after, pointer);

      ComposedRuleElementMatch extendedContainerMatch = containerMatch.copy();
      RuleMatch extendedMatch = ruleMatch.copy(extendedContainerMatch, after);

      AnnotationFS coveredByWildCard = getCoveredByWildCard(after, annotation, nextOne, stream);
      doMatch(coveredByWildCard, extendedMatch, extendedContainerMatch, annotation == null, stream,
              crowd);
      if (extendedMatch.matched()) {
        ComposedRuleElementMatch nextContainerMatch = getContainerMatchOfNextElement(
                extendedContainerMatch, nextDepth);
        if (anchor == null) {
          anchor = stream.getVeryFirstBeforeWindow(after);
        }
        // TODO match and containermatch should be on the correct level!
        result = nextElement.continueMatch(after, anchor, extendedMatch, ruleApply,
                nextContainerMatch, sideStepOrigin, nextElement, stream, crowd);
//        List<RuleElementMatch> nextList = nextContainerMatch.getInnerMatches().get(nextElement);

        // cannot use container match since there could be new alternatives in the depth search
        boolean nextElementDidMatch = nextElementDidMatch(result, nextElement);

        if (!nextElementDidMatch) {
          moveOn(after, iterator, stream);
        } else {
          doneHere = true;
        }
      } else {
        // conditions of wildcard element did not match, try the next possible anchor for the
        // next rule element
        moveOn(after, iterator, stream);
      }
    }
    return result;
  }

  private AnnotationFS getNextAnchor(boolean after, AnnotationFS annotation,
          RuleElement nextElement, RuleMatch ruleMatch, ComposedRuleElementMatch containerMatch,
          RutaRuleElement sideStepOrigin, RutaStream stream, InferenceCrowd crowd) {
    AnnotationFS nextAnchor = null;
    Pair<RuleElement, Integer> nextNext = getNextRuleElement(after, nextElement);
    if (nextNext != null && nextNext.getLeft() != null) {
      List<RuleMatch> tryWithNextNextRuleElement = tryWithNextRuleElement(nextNext.getLeft(), after,
              annotation, ruleMatch, null, containerMatch, nextNext.getRight().intValue(),
              sideStepOrigin, nextNext.getLeft(), stream, crowd);
      for (RuleMatch eachNextRuleMatch : tryWithNextNextRuleElement) {
        if (eachNextRuleMatch.matched()) {
          List<AnnotationFS> matchedAnnotationsOfElement = eachNextRuleMatch
                  .getMatchedAnnotationsOfElement(nextNext.getLeft());
          if (matchedAnnotationsOfElement != null && !matchedAnnotationsOfElement.isEmpty()) {
            nextAnchor = after ? matchedAnnotationsOfElement.get(0)
                    : matchedAnnotationsOfElement.get(matchedAnnotationsOfElement.size() - 1);
            break;
          }
        }
      }
    }
    return nextAnchor;
  }

  private boolean nextElementDidMatch(List<RuleMatch> result, RuleElement nextElement) {
    if (result == null || result.isEmpty()) {
      return false;
    }

    for (RuleMatch ruleMatch : result) {
      if (ruleMatch.matched()) {
        return true;
      }
    }
    for (RuleMatch ruleMatch : result) {
      List<List<RuleElementMatch>> matchInfo = ruleMatch.getMatchInfo(nextElement);
      List<RuleElementMatch> matches = matchInfo.get(matchInfo.size() - 1);
      if (matches != null) {
        for (RuleElementMatch ruleElementMatch : matches) {
          if (ruleElementMatch != null && ruleElementMatch.matched()) {
            return true;
          }
        }
      }

    }

    return false;
  }

  private ComposedRuleElementMatch getContainerMatchOfNextElement(
          ComposedRuleElementMatch extendedContainerMatch, int nextDepth) {
    ComposedRuleElementMatch result = extendedContainerMatch;
    for (int i = 0; i < nextDepth; i++) {
      result = result.getContainerMatch();
    }
    return result;
  }

  private FSIterator<AnnotationFS> getIterator(boolean after, AnnotationFS annotation,
          RuleElement nextElement, Type defaultType, RutaStream stream) {
    FSIterator<AnnotationFS> iterator = null;
    if (defaultType == null) {
      MatchContext context = new MatchContext(nextElement.getParent());
      RutaRuleElement re = (RutaRuleElement) nextElement;
      RutaMatcher matcher = re.getMatcher();
      if (matcher instanceof RutaAnnotationTypeMatcher) {
        RutaAnnotationTypeMatcher atm = (RutaAnnotationTypeMatcher) matcher;
        AnnotationTypeExpression expression = (AnnotationTypeExpression) atm.getExpression();
        if (expression.getAnnotationExpression() != null
                || expression.getAnnotationListExpression() != null) {
          iterator = new AnnotationListFSIterator(expression.getAnnotationList(context, stream));
          iterator.moveTo(annotation);
        } else {
          Type type = matcher.getType(parent, stream);
          iterator = getIteratorOfType(after, type, annotation, stream);
        }
      } else if (matcher != null) {
        // fallback
        Type type = matcher.getType(parent, stream);
        iterator = getIteratorOfType(after, type, annotation, stream);
      }
    } else {
      iterator = getIteratorOfType(after, defaultType, annotation, stream);
    }
    if (iterator != null && iterator.isValid() && iterator.get().equals(annotation)) {
      moveOn(after, iterator, stream);
    }
    return iterator;
  }

  private FSIterator<AnnotationFS> getIteratorOfType(boolean after, Type type,
          AnnotationFS annotation, RutaStream stream) {
    CAS cas = stream.getCas();
    // TODO adapt logic to changes in UIMA iterator behavior!
    FSIterator<AnnotationFS> result = null;
    if (stream.getDocumentAnnotation().equals(cas.getDocumentAnnotation())) {
      // no windowing needed
      if (annotation == null) {
        result = cas.getAnnotationIndex(type).withSnapshotIterators().iterator();
      } else {
        AnnotationFS pointer = stream.getAnchor(after, annotation);
        result = cas.getAnnotationIndex(type).withSnapshotIterators().iterator(pointer);
        if (!result.isValid()) {
          if (after) {
            // result.moveToFirst();
          } else {
            // HOTFIX caused by type priorities
            result.moveToLast();
            if (result.isValid()) {
              // HOTFIX avoid pointer to current annotation
              AnnotationFS current = result.get();
              if (current.getEnd() >= annotation.getBegin()) {
                result.moveToPrevious();
              }
            }
          }
        } else {
          if (!after) {
            result.moveToPrevious();
          }
        }
        if (annotation != null && result.isValid()) {
          // hotfix for index overflow...
          AnnotationFS a = result.get();
          // not greater equal because caller method will fix it for same positions, should be fixed
          // right here
          if (after) {
            if (a.getBegin() < annotation.getBegin()) {
              return null;
            }
          } else {
            if (a.getEnd() > annotation.getEnd()) {
              return null;
            }
          }
        }
      }
    } else {
      JCas jcas = null;
      try {
        jcas = cas.getJCas();
      } catch (CASException e) {
        e.printStackTrace();
      }
      RutaFrame window = new RutaFrame(jcas, stream.getDocumentAnnotation().getBegin(),
              stream.getDocumentAnnotation().getEnd());
      if (annotation == null) {
        result = cas.getAnnotationIndex(type).subiterator(window);
      } else {
        result = cas.getAnnotationIndex(type).subiterator(window);
        AnnotationFS pointer = stream.getAnchor(after, annotation);
        result.moveTo(pointer);
        if (!result.isValid()) {
          if (after) {
            // result.moveToFirst();
          } else {
            // TODO due to type priorities: RutaBasic is last -> moveTo will not work
            result.moveToLast();
            if (result.isValid()) {
              // HOTFIX avoid pointer to current annotation
              AnnotationFS current = result.get();
              if (current.getEnd() >= annotation.getBegin()) {
                result.moveToPrevious();
              }
            }
          }
        } else {
          if (!after) {
            result.moveToPrevious();
          }
        }
      }
    }

    return result;
  }

  private List<RuleMatch> tryWithNextLiteral(boolean after, AnnotationFS annotation,
          RutaRuleElement nextElement, RuleMatch ruleMatch, RuleApply ruleApply,
          ComposedRuleElementMatch containerMatch, int nextDepth, RutaRuleElement sideStepOrigin,
          RutaStream stream, InferenceCrowd crowd) {
    List<RuleMatch> result = new ArrayList<RuleMatch>();
    RutaLiteralMatcher matcher = (RutaLiteralMatcher) nextElement.getMatcher();
    if (matcher == null) {
      return result;
    }
    IStringExpression expression = matcher.getExpression();
    MatchContext context = new MatchContext(this, ruleMatch, true);
    String stringValue = expression.getStringValue(context, stream);
    AnnotationFS documentAnnotation = stream.getDocumentAnnotation();
    int delta = documentAnnotation.getBegin();
    String document = documentAnnotation.getCoveredText();
    int pointer = 0;
    if (annotation != null) {
      pointer = annotation.getEnd() - delta;
    }
    int indexOf = 0;
    boolean doneHere = false;
    // TODO matching direction not included in document.indexOf(). Need another method here
    while (!doneHere && (indexOf = document.indexOf(stringValue, pointer)) < document.length()) {
      if (indexOf < 0) {
        // can't match, the next next element will see it.
        ComposedRuleElementMatch nextContainerMatch = getContainerMatchOfNextElement(containerMatch,
                nextDepth);
        nextElement.continueMatch(after, annotation, ruleMatch, ruleApply, nextContainerMatch,
                sideStepOrigin, null, stream, crowd);
        doneHere = true;
        break;
      }
      RutaBasic anchor = stream.getAnchor(after, indexOf + delta);
      RutaBasic endAnchor = stream.getAnchor(!after, indexOf + delta);
      ComposedRuleElementMatch extendedContainerMatch = containerMatch.copy();
      RuleMatch extendedMatch = ruleMatch.copy(extendedContainerMatch, after);
      AnnotationFS coveredByWildCard = getCoveredByWildCard(after, annotation, anchor, stream);
      doMatch(coveredByWildCard, extendedMatch, extendedContainerMatch, annotation == null, stream,
              crowd);
      if (extendedMatch.matched()) {
        ComposedRuleElementMatch nextContainerMatch = getContainerMatchOfNextElement(
                extendedContainerMatch, nextDepth);
        if (endAnchor == null) {
          result = nextElement.startMatch(extendedMatch, ruleApply, nextContainerMatch, nextElement,
                  stream, crowd);
        } else {
          result = nextElement.continueMatch(after, endAnchor, extendedMatch, ruleApply,
                  nextContainerMatch, sideStepOrigin, nextElement, stream, crowd);
        }
        List<RuleElementMatch> nextList = nextContainerMatch.getInnerMatches().get(nextElement);
        if (nextList == null || nextList.isEmpty()) {
          pointer = getNextPointer(after, anchor);
        } else {
          doneHere = true;
        }
      } else {
        pointer = getNextPointer(after, anchor);
      }
    }
    return result;
  }

  private int getNextPointer(boolean after, AnnotationFS anchor) {
    if (after) {
      return anchor.getEnd();
    } else {
      return anchor.getBegin();
    }
  }

  private void moveOn(boolean after, FSIterator<AnnotationFS> iterator, RutaStream stream) {
    if (after) {
      iterator.moveToNext();
    } else {
      iterator.moveToPrevious();
    }
    while (iterator.isValid() && !stream.isVisible(iterator.get())) {
      if (after) {
        iterator.moveToNext();
      } else {
        iterator.moveToPrevious();
      }
    }
  }

  private AnnotationFS getCoveredByWildCard(boolean after, AnnotationFS last, AnnotationFS next,
          RutaStream stream) {
    CAS cas = stream.getCas();
    Type type = cas.getAnnotationType();
    AnnotationFS documentAnnotation = stream.getDocumentAnnotation();

    // order like in the index
    AnnotationFS before = last;
    AnnotationFS later = next;
    if (!after) {
      before = next;
      later = last;
    }

    // without any information, match on everything
    int begin = documentAnnotation.getBegin();
    int end = documentAnnotation.getEnd();

    // limit offsets
    if (before != null) {
      begin = before.getEnd();
    }
    if (later != null) {
      end = later.getBegin();
    }

    int filteredBegin = begin;
    int filteredEnd = end;
    RutaBasic beginAnchor = stream.getBeginAnchor(begin);
    RutaBasic endAnchor = stream.getEndAnchor(end);
    if (beginAnchor != null && !stream.isVisible(beginAnchor)) {
      beginAnchor = stream.getBasicNextTo(false, beginAnchor);
      if (beginAnchor != null) {
        filteredBegin = beginAnchor.getBegin();
      }
    }
    if (endAnchor != null && !stream.isVisible(endAnchor)) {
      endAnchor = stream.getBasicNextTo(true, endAnchor);
      if (endAnchor != null) {
        filteredEnd = endAnchor.getEnd();
      }
    }

    if (filteredBegin < filteredEnd) {
      begin = filteredBegin;
      end = filteredEnd;
    } else {
      type = cas.getTypeSystem().getType(RutaEngine.OPTIONAL_TYPE);
    }
    AnnotationFS afs = cas.createAnnotation(type, begin, end);
    return afs;
  }

  private void doMatch(AnnotationFS annotation, RuleMatch ruleMatch,
          ComposedRuleElementMatch containerMatch, boolean ruleAnchor, RutaStream stream,
          InferenceCrowd crowd) {
    RuleElementMatch result = new RuleElementMatch(this, containerMatch);
    result.setRuleAnchor(ruleAnchor);
    List<EvaluatedCondition> evaluatedConditions = new ArrayList<EvaluatedCondition>(
            conditions.size());
    boolean base = true;

    MatchContext context = new MatchContext(annotation, this, ruleMatch, true);

    List<AnnotationFS> textsMatched = new ArrayList<AnnotationFS>(1);
    if (annotation != null) {
      textsMatched.add(annotation);
    }
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
      boolean inlinedRulesMatched = matchInnerRules(ruleMatch, stream, crowd);
      result.setInlinedRulesMatched(inlinedRulesMatched);
    } else {
      // update label for failed match after evaluating conditions
      environment.removeVariableValue(getLabel(), context);
    }
    ruleMatch.setMatched(ruleMatch.matched() && result.matched());
  }

  @Override
  public List<RuleMatch> continueOwnMatch(boolean after, AnnotationFS annotation,
          RuleMatch ruleMatch, RuleApply ruleApply, ComposedRuleElementMatch containerMatch,
          RutaRuleElement sideStepOrigin, RuleElement entryPoint, RutaStream stream,
          InferenceCrowd crowd) {
    // won't happen
    return Collections.emptyList();
  }

  @Override
  public Collection<AnnotationFS> getAnchors(RutaStream symbolStream) {
    // shouldn't happen
    // really? what about anchoring at start?
    return Collections.emptyList();
  }

  @Override
  public long estimateAnchors(RutaStream stream) {
    return Integer.MAX_VALUE;
  }

  @Override
  public String toString() {
    return "#" + (conditions.isEmpty() ? "" : "(" + conditions.toString() + ")" + "\\n")
            + (actions.isEmpty() ? "" : "{" + actions.toString() + "}");
  }

}
