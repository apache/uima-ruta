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

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.ConstraintFactory;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FSTypeConstraint;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.JCas;
import org.apache.uima.textmarker.TextMarkerBlock;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.action.AbstractTextMarkerAction;
import org.apache.uima.textmarker.condition.AbstractTextMarkerCondition;
import org.apache.uima.textmarker.expression.string.StringExpression;
import org.apache.uima.textmarker.type.TextMarkerBasic;
import org.apache.uima.textmarker.type.TextMarkerFrame;
import org.apache.uima.textmarker.visitor.InferenceCrowd;

public class WildCardRuleElement extends AbstractRuleElement {

  public WildCardRuleElement(List<AbstractTextMarkerCondition> conditions,
          List<AbstractTextMarkerAction> actions, RuleElementContainer container,
          TextMarkerBlock parent) {
    super(null, conditions, actions, container, parent);
  }

  public void startMatch(RuleMatch ruleMatch, RuleApply ruleApply,
          ComposedRuleElementMatch containerMatch, RuleElement entryPoint, TextMarkerStream stream,
          InferenceCrowd crowd) {
    continueMatch(true, null, ruleMatch, ruleApply, containerMatch, null, entryPoint, stream, crowd);
  }

  public void continueMatch(boolean after, AnnotationFS annotation, RuleMatch ruleMatch,
          RuleApply ruleApply, ComposedRuleElementMatch containerMatch,
          TextMarkerRuleElement sideStepOrigin, RuleElement entryPoint, TextMarkerStream stream,
          InferenceCrowd crowd) {
    RuleElement nextElement = getContainer().getNextElement(after, this);

    tryWithNextRuleElement(nextElement, after, annotation, ruleMatch, ruleApply, containerMatch,
            sideStepOrigin, entryPoint, stream, crowd);

  }

  private void tryWithNextRuleElement(RuleElement nextElement, boolean after,
          AnnotationFS annotation, RuleMatch ruleMatch, RuleApply ruleApply,
          ComposedRuleElementMatch containerMatch, TextMarkerRuleElement sideStepOrigin,
          RuleElement entryPoint, TextMarkerStream stream, InferenceCrowd crowd) {
    // what is the next stuff that should match?
    if (nextElement == null) {
      AnnotationFS afs = getCoveredByWildCard(after, annotation, null, stream);
      doMatch(afs, ruleMatch, containerMatch, annotation == null, stream, crowd);
      ComposedRuleElement composed = (ComposedRuleElement) getContainer();
      composed.fallbackContinue(after, ruleMatch.matched(), afs, ruleMatch, ruleApply,
              containerMatch, sideStepOrigin, entryPoint, stream, crowd);
    } else if (nextElement instanceof TextMarkerRuleElement) {
      TextMarkerRuleElement re = (TextMarkerRuleElement) nextElement;
      TextMarkerMatcher matcher = re.getMatcher();
      if (matcher instanceof TextMarkerTypeMatcher) {
        tryWithNextType(after, annotation, nextElement, null, ruleMatch, ruleApply, containerMatch,
                sideStepOrigin, stream, crowd);
      } else if (matcher instanceof TextMarkerLiteralMatcher) {
        tryWithNextLiteral(after, annotation, re, ruleMatch, ruleApply, containerMatch,
                sideStepOrigin, stream, crowd);
      } else if (matcher instanceof TextMarkerDisjunctiveMatcher) {
        tryWithNextType(after, annotation, re, null, ruleMatch, ruleApply, containerMatch,
                sideStepOrigin, stream, crowd);
      }

    } else if (nextElement instanceof ComposedRuleElement) {

      ComposedRuleElement cre = ((ComposedRuleElement) nextElement);
      tryWithNextComposed(after, annotation, cre, ruleMatch, ruleApply, containerMatch,
              sideStepOrigin, stream, crowd);
      RuleElement nextInnerRuleElement = null;
      if (after) {
        nextInnerRuleElement = cre.getFirstElement();
      } else {
        nextInnerRuleElement = cre.getLastElement();
      }
      // TODO won't work ...!!!
      ComposedRuleElementMatch composedMatch = new ComposedRuleElementMatch(cre, containerMatch);
      if (containerMatch == null) {
        ruleMatch.setRootMatch(composedMatch);
      } else {
        containerMatch.addInnerMatch(this, composedMatch, false);
      }
      tryWithNextRuleElement(nextInnerRuleElement, after, annotation, ruleMatch, ruleApply,
              composedMatch, sideStepOrigin, entryPoint, stream, crowd);
    } else if (nextElement instanceof WildCardRuleElement) {
      // another wildcard? seriously? then just assume its an "Annotation" type
      CAS cas = stream.getCas();
      tryWithNextType(after, annotation, nextElement, cas.getAnnotationType(), ruleMatch,
              ruleApply, containerMatch, sideStepOrigin, stream, crowd);
    }
  }

  private void tryWithNextComposed(boolean after, AnnotationFS annotation, ComposedRuleElement cre,
          RuleMatch ruleMatch, RuleApply ruleApply, ComposedRuleElementMatch containerMatch,
          TextMarkerRuleElement sideStepOrigin, TextMarkerStream stream, InferenceCrowd crowd) {
    AnnotationFS nextOne = annotation;
    boolean doneHere = false;
    while (!doneHere
            && (nextOne = getNextPositionForComposed(cre, after, nextOne, stream)) != null) {
      TextMarkerBasic endAnchor = stream.getEndAnchor(nextOne.getBegin());
      ComposedRuleElementMatch extendedContainerMatch = containerMatch.copy();
      RuleMatch extendedMatch = ruleMatch.copy(extendedContainerMatch);
      AnnotationFS coveredByWildCard = getCoveredByWildCard(after, annotation, nextOne, stream);

      doMatch(coveredByWildCard, extendedMatch, extendedContainerMatch, annotation == null, stream,
              crowd);
      if (extendedMatch.matched()) {
        cre.continueMatch(after, endAnchor, extendedMatch, ruleApply,
                extendedContainerMatch, sideStepOrigin, cre, stream, crowd);
        List<RuleElementMatch> nextList = extendedContainerMatch.getInnerMatches().get(cre);
        boolean matched = hasMatched(nextList);
        if (!matched) {
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
      cre.continueMatch(after, annotation, ruleMatch, ruleApply, containerMatch, sideStepOrigin,
              null, stream, crowd);
    }
  }

  private boolean hasMatched(List<RuleElementMatch> nextList) {
    if(nextList == null || nextList.isEmpty()) {
      return false;
    }
    boolean result = true;
    for (RuleElementMatch each : nextList) {
      result &= each.matched();
    }
    return result;
  }

  private AnnotationFS getNextPositionForComposed(ComposedRuleElement cre, boolean after,
          AnnotationFS annotation, TextMarkerStream stream) {
    RuleElement element = getNextAtomicRuleElement(cre, after);
    AnnotationFS result = null;
    if (element instanceof WildCardRuleElement) {
      if (after) {
        return stream.getAnchor(after, annotation.getEnd());
      } else {
        return stream.getAnchor(after, annotation.getBegin());
      }
    } else {
      TextMarkerRuleElement re = (TextMarkerRuleElement) element;
      TextMarkerMatcher matcher = re.getMatcher();
      if (matcher instanceof TextMarkerTypeMatcher) {
        FSIterator<AnnotationFS> iterator = getIterator(after, annotation, re, null, stream);
//        moveOn(after, iterator);
        if (iterator.isValid()) {
          result = iterator.get();
        }
      } else if (matcher instanceof TextMarkerLiteralMatcher) {
        TextMarkerLiteralMatcher lm = (TextMarkerLiteralMatcher) matcher;
        StringExpression expression = lm.getExpression();
        String stringValue = expression.getStringValue(parent);
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

  private void tryWithNextType(boolean after, AnnotationFS annotation, RuleElement nextElement,
          Type defaultType, RuleMatch ruleMatch, RuleApply ruleApply,
          ComposedRuleElementMatch containerMatch, TextMarkerRuleElement sideStepOrigin,
          TextMarkerStream stream, InferenceCrowd crowd) {
    FSIterator<AnnotationFS> iterator = getIterator(after, annotation, nextElement, defaultType,
            stream);
    boolean doneHere = false;
    while (!doneHere && iterator.isValid() && stream.isVisible(iterator.get())) {
      AnnotationFS nextOne = iterator.get();
      TextMarkerBasic endAnchor = stream.getEndAnchor(nextOne.getBegin());
      ComposedRuleElementMatch extendedContainerMatch = containerMatch.copy();
      RuleMatch extendedMatch = ruleMatch.copy(extendedContainerMatch);

      AnnotationFS coveredByWildCard = getCoveredByWildCard(after, annotation, nextOne, stream);

      doMatch(coveredByWildCard, extendedMatch, extendedContainerMatch, annotation == null, stream,
              crowd);
      if (extendedMatch.matched()) {
        nextElement.continueMatch(after, endAnchor, extendedMatch, ruleApply,
                extendedContainerMatch, sideStepOrigin, nextElement, stream, crowd);
        List<RuleElementMatch> nextList = extendedContainerMatch.getInnerMatches().get(nextElement);
        if (nextList == null || nextList.isEmpty()) {
          moveOn(after, iterator);
        } else {
          doneHere = true;
        }
      } else {
        // conditions of wildcard element did not match, try the next possible anchor for the
        // next rule element
        moveOn(after, iterator);
      }
    }
  }

  private FSIterator<AnnotationFS> getIterator(boolean after, AnnotationFS annotation,
          RuleElement nextElement, Type defaultType, TextMarkerStream stream) {
    CAS cas = stream.getCas();
    FSIterator<AnnotationFS> iterator = null;
    if (defaultType == null) {
      TextMarkerRuleElement re = (TextMarkerRuleElement) nextElement;
      TextMarkerMatcher matcher = re.getMatcher();
      if (matcher instanceof TextMarkerTypeMatcher) {
        TextMarkerTypeMatcher typeMatcher = (TextMarkerTypeMatcher) re.getMatcher();
        List<Type> types = typeMatcher.getTypes(parent, stream);
        Type type = types.get(0);
        iterator = getIteratorOfType(type, annotation, stream);
      } else if (matcher instanceof TextMarkerDisjunctiveMatcher) {
        List<Type> types = matcher.getTypes(parent, stream);
        iterator = getIteratorForDisjunctive(cas, types, after, annotation, stream);
      } else {
        // should not happen
      }
    } else {
      iterator = getIteratorOfType(defaultType, annotation, stream);
    }
    return iterator;
  }

  private FSIterator<AnnotationFS> getIteratorOfType(Type type, AnnotationFS annotation,
          TextMarkerStream stream) {
    CAS cas = stream.getCas();
    FSIterator<AnnotationFS> result = null;
    if(stream.getDocumentAnnotation().equals(cas.getDocumentAnnotation())) {
      // no windowing needed
      if(annotation == null) {
        result = cas.getAnnotationIndex(type).iterator();
      } else {
        result = cas.getAnnotationIndex(type).iterator(annotation);
      }
    } else {
      JCas jcas = null;
      try {
        jcas = cas.getJCas();
      } catch (CASException e) {
        e.printStackTrace();
      }
      TextMarkerFrame window = new TextMarkerFrame(jcas, stream.getDocumentAnnotation().getBegin(), stream.getDocumentAnnotation().getEnd());
      if(annotation == null) {
        result = cas.getAnnotationIndex(type).subiterator(window);
      } else {
        result = cas.getAnnotationIndex(type).subiterator(window);
        result.moveTo(annotation);
      }
    }
    
    return result;
  }

  private void tryWithNextLiteral(boolean after, AnnotationFS annotation,
          TextMarkerRuleElement nextElement, RuleMatch ruleMatch, RuleApply ruleApply,
          ComposedRuleElementMatch containerMatch, TextMarkerRuleElement sideStepOrigin,
          TextMarkerStream stream, InferenceCrowd crowd) {
    TextMarkerLiteralMatcher matcher = (TextMarkerLiteralMatcher) nextElement.getMatcher();
    StringExpression expression = matcher.getExpression();
    String stringValue = expression.getStringValue(parent);
    AnnotationFS documentAnnotation = stream.getDocumentAnnotation();
    int delta = documentAnnotation.getBegin();
    String document = documentAnnotation.getCoveredText();
    int pointer = 0;
    if(annotation != null) {
      pointer = annotation.getEnd() - delta;
    }
    int indexOf = 0;
    boolean doneHere = false;
    // TODO matching direction not included in document.indexOf(). Need another method here
    while (!doneHere && (indexOf = document.indexOf(stringValue, pointer)) < document.length()) {
      if (indexOf < 0) {
        // can't match, the next next element will see it.
        nextElement.continueMatch(after, annotation, ruleMatch, ruleApply, containerMatch,
                sideStepOrigin, null, stream, crowd);
        doneHere = true;
        break;
      }
      TextMarkerBasic anchor = stream.getAnchor(after, indexOf+delta);
      TextMarkerBasic endAnchor = stream.getAnchor(!after, indexOf+delta);
      ComposedRuleElementMatch extendedContainerMatch = containerMatch.copy();
      RuleMatch extendedMatch = ruleMatch.copy(extendedContainerMatch);
      AnnotationFS coveredByWildCard = getCoveredByWildCard(after, annotation, anchor, stream);
      doMatch(coveredByWildCard, extendedMatch, extendedContainerMatch, annotation == null, stream,
              crowd);
      if (extendedMatch.matched()) {
        nextElement.continueMatch(after, endAnchor, extendedMatch, ruleApply,
                extendedContainerMatch, sideStepOrigin, nextElement, stream, crowd);
        List<RuleElementMatch> nextList = extendedContainerMatch.getInnerMatches().get(nextElement);
        if (nextList == null || nextList.isEmpty()) {
          pointer = getNextPointer(after, anchor);
        } else {
          doneHere = true;
        }
      } else {
        pointer = getNextPointer(after, anchor);
      }
    }
  }

  private int getNextPointer(boolean after, AnnotationFS anchor) {
    if (after) {
      return anchor.getEnd();
    } else {
      return anchor.getBegin();
    }
  }

  private FSIterator<AnnotationFS> getIteratorForDisjunctive(CAS cas, List<Type> types,
          boolean after, AnnotationFS annotation, TextMarkerStream stream) {
    ConstraintFactory cf = cas.getConstraintFactory();
    FSTypeConstraint typeConstraint = cf.createTypeConstraint();
    for (Type each : types) {
      typeConstraint.add(each);
    }
    FSIterator<AnnotationFS> windowIt = cas.getAnnotationIndex().subiterator(
            stream.getDocumentAnnotation());
    FSIterator<AnnotationFS> iterator = cas.createFilteredIterator(windowIt, typeConstraint);
    if (annotation != null) {
      iterator.moveTo(annotation);
      if (!after) {
        iterator.moveToPrevious();
      }
    } else {
      if (after) {
        iterator.moveToFirst();
      } else {
        iterator.moveToLast();
      }
    }

    return iterator;
  }

  private void moveOn(boolean after, FSIterator<AnnotationFS> iterator) {
    if (after) {
      iterator.moveToNext();
    } else {
      iterator.moveToPrevious();
    }
  }

  private AnnotationFS getCoveredByWildCard(boolean after, AnnotationFS last,
          AnnotationFS next, TextMarkerStream stream) {
    CAS cas = stream.getCas();
    Type type = cas.getAnnotationType();
    AnnotationFS documentAnnotation = stream.getDocumentAnnotation();

    // order like in the index
    AnnotationFS before = last;
    AnnotationFS later = next;
    if(!after) {
      before = next;
      later = last;
    }
    
    // without any information, match on everything
    int begin = documentAnnotation.getBegin();
    int end = documentAnnotation.getEnd();
    
    // limit offsets
    if(before != null) {
      begin = before.getEnd();
    }
    if(later != null) {
      end = later.getBegin();
    }
    
    AnnotationFS afs = cas.createAnnotation(type, begin, end);
    
    return afs;
  }

  private void doMatch(AnnotationFS annotation, RuleMatch ruleMatch,
          ComposedRuleElementMatch containerMatch, boolean ruleAnchor, TextMarkerStream stream,
          InferenceCrowd crowd) {
    RuleElementMatch result = new RuleElementMatch(this, containerMatch);
    result.setRuleAnchor(ruleAnchor);
    List<EvaluatedCondition> evaluatedConditions = new ArrayList<EvaluatedCondition>(
            conditions.size());
    boolean base = true;
    List<AnnotationFS> textsMatched = new ArrayList<AnnotationFS>(1);
    if (base) {
      for (AbstractTextMarkerCondition condition : conditions) {
        crowd.beginVisit(condition, null);
        EvaluatedCondition eval = condition.eval(annotation, this, stream, crowd);
        crowd.endVisit(condition, null);
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

  public void continueOwnMatch(boolean after, AnnotationFS annotation, RuleMatch ruleMatch,
          RuleApply ruleApply, ComposedRuleElementMatch containerMatch,
          TextMarkerRuleElement sideStepOrigin, RuleElement entryPoint, TextMarkerStream stream,
          InferenceCrowd crowd) {
    // won't happen
  }

  public Collection<AnnotationFS> getAnchors(TextMarkerStream symbolStream) {
    // shouldn't happen
    // really? what about anchoring at start?
    return Collections.emptyList();
  }

  public int estimateAnchors(TextMarkerStream stream) {
    return Integer.MAX_VALUE;
  }

  public String toString() {
    return "#" + (conditions.isEmpty() ? "" : "(" + conditions.toString() + ")" + "\\n")
            + (actions.isEmpty() ? "" : "{" + actions.toString() + "}");
  }

}
