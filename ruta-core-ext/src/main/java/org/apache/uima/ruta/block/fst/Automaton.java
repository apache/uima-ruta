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


package org.apache.uima.ruta.block.fst;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStatement;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.block.RutaBlock;
import org.apache.uima.ruta.condition.AbstractRutaCondition;
import org.apache.uima.ruta.rule.ComposedRuleElementMatch;
import org.apache.uima.ruta.rule.EvaluatedCondition;
import org.apache.uima.ruta.rule.MatchContext;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleElementMatch;
import org.apache.uima.ruta.rule.RuleMatch;
import org.apache.uima.ruta.rule.RutaMatcher;
import org.apache.uima.ruta.rule.RutaRule;
import org.apache.uima.ruta.rule.RutaRuleElement;
import org.apache.uima.ruta.verbalize.RutaVerbalizer;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class Automaton {

  private RutaVerbalizer verbalizer;

  private RootState root;

  /**
   * Constructor that builds the FST from the Rules in rulesList
   * 
   * @param rulesList
   *          - the rules to build the FST from
   */
  public Automaton(List<RutaStatement> rulesList) {
    this.verbalizer = new RutaVerbalizer();
    this.root = this.buildAutomaton(rulesList);
  }

  /**
   * Builds the FST from the Rules in rulesList.
   * 
   * @param rulesList
   *          - the rules to build the FST from
   * @return the RootState for the built FST
   */
  public RootState buildAutomaton(List<RutaStatement> rulesList) {
    RootState root = new RootState();
    for (RutaStatement statement : rulesList) {
      AbstractState startState = root;
      if (statement instanceof RutaRule) {
        RutaRule rule = (RutaRule) statement;
        int depth = 1;
        for (RuleElement element : rule.getRuleElements()) {
          AbstractState targetState = null;
          for (RuleElement toCompare : startState.getPossibleTransitions()) {
            if (ruleElementEquals(element, toCompare)) {
              targetState = startState.getTransition(toCompare);
            }
            if (targetState instanceof EndState) {
              // Falls zwei Regeln genau gleich, erstelle trotzdem
              // zwei verschiedene Endzustände
              targetState = null;
            }
          }
          if (targetState == null) { // Erstelle neuen Zielzustand
            if (depth == rule.getRuleElements().size()) {
              targetState = new EndState(statement, depth);
            } else {
              targetState = new TransitionState(depth);
              ((TransitionState) targetState).addRule(statement);
            }
            startState.addTransition(element, targetState);
          } else { // Benutze existierenden Zielzustand
            if (targetState instanceof TransitionState) {
              ((TransitionState) targetState).addRule(statement);
            }
          }
          startState = targetState;
          depth++;
        }
      }
    }
    return root;
  }

  /**
   * Compares two RuleElements. They are if their verbalization, e. g. "CW" or "SW" is equal, not if
   * elem1 == elem2 or elem1.equals(elem2)
   * 
   * @param elem1
   *          - The first RuleElement to compare
   * @param elem2
   *          - The second RuleElement to compare
   * 
   * @return true if the verbalization of the two RuleElements equals, else false
   */
  public boolean ruleElementEquals(RuleElement elem1, RuleElement elem2) {
    return verbalizer.verbalize(elem1).equals(verbalizer.verbalize(elem2));
  }

  /**
   * Starts the execution of the Automaton in the rootState
   * 
   * @param stream
   *          - the RutaStream (is needed in the called functions)
   * @param crowd
   *          - the InferenceCrowd (is needed in the called functions)
   * @param parent
   *          - the RutaBlock (is needed in called functions)
   */
  public void apply(RutaStream stream, InferenceCrowd crowd, RutaBlock parent) {
    for (RuleElement element : root.getPossibleTransitions()) {
      AbstractState targetState = root.getTransition(element);
      RutaMatcher matcher = ((RutaRuleElement) element).getMatcher();
      for (AnnotationFS annoFS : matcher.getMatchingAnnotations(parent, stream)) {
        if (targetState instanceof TransitionState) {
          LinkedList<RuleMatch> ruleMatches = createMatches(annoFS,
                  ((TransitionState) targetState).getRules(), stream, crowd);
          doTransition((TransitionState) targetState, annoFS, element, ruleMatches, stream, crowd,
                  parent);
        } else {
          RuleMatch ruleMatch = createMatch(annoFS, (RutaRule) ((EndState) targetState).getRule(),
                  stream, crowd);
          addAnnotation((EndState) targetState, ruleMatch, stream, crowd);
        }
      }
    }
  }

  /**
   * Pursues the execution of the Automaton in the next state.
   * 
   * @param startState
   *          - the state to go on from
   * @param anno
   *          - the matched Annotation from the previous transition
   * @param ruleElement
   *          - the last matched RuleElement
   * @param matches
   *          - the list of RuleMatches corresponding to the rules in the state
   * @param stream
   *          - the RutaStream (is needed in the called functions)
   * @param crowd
   *          - the InferenceCrowd (is needed in the called functions)
   * @param parent
   *          - the RutaBlock (is needed in called functions)
   */
  private void doTransition(TransitionState startState, AnnotationFS anno, RuleElement ruleElement,
          LinkedList<RuleMatch> matches, RutaStream stream, InferenceCrowd crowd, RutaBlock parent) {
    for (RuleElement element : startState.getPossibleTransitions()) {
      RutaMatcher matcher = ((RutaRuleElement) element).getMatcher();
      AbstractState targetState = startState.getTransition(element);
      for (AnnotationFS annoFS : matcher.getAnnotationsAfter((RutaRuleElement) ruleElement, anno,
              parent, stream)) {
        if (targetState instanceof TransitionState) {
          LinkedList<RuleMatch> ruleMatches = filterMatches(annoFS, matches,
                  (TransitionState) targetState, stream, crowd);
          doTransition((TransitionState) targetState, annoFS, element, ruleMatches, stream, crowd,
                  parent);
        } else {
          RuleMatch ruleMatch = filterMatch(annoFS, matches, (EndState) targetState, stream, crowd);
          addAnnotation((EndState) targetState, ruleMatch, stream, crowd);
        }
      }
    }
  }

  /**
   * Creates the Annotation after a full matching of a rule, this means the FST reached an EndState
   * 
   * 
   * @param targetState
   *          - the reached Endstate representing the rule
   * @param ruleMatch
   *          - the RuleMatch corresponding the Rule
   * @param stream
   *          - the RutaStream (is needed in the called functions)
   * @param crowd
   *          - the InferenceCrowd (is needed in the called functions)
   */
  public void addAnnotation(EndState targetState, RuleMatch ruleMatch, RutaStream stream,
          InferenceCrowd crowd) {
    RutaStatement statement = targetState.getRule();
    if (statement instanceof RutaRule) {
      RutaRule rule = (RutaRule) statement;
      rule.getRoot().apply(ruleMatch, stream, crowd);
    }
  }

  /**
   * Checks the matching of the annotation and updates the RuleMatch
   * 
   * @param annotation
   *          - the matched Annotation
   * @param ruleMatch
   *          - the RuleMatch to update
   * @param element
   *          - the matched RuleElement
   * @param containerMatch
   *          - the ComposedRuleElement of the rule containing element
   * @param stream
   *          - the RutaStream (is needed in the called functions)
   * @param crowd
   *          - the InferenceCrowd (is needed in the called functions)
   */
  private void doMatch(AnnotationFS annotation, RuleMatch ruleMatch, RuleElement element,
          ComposedRuleElementMatch containerMatch, RutaStream stream, InferenceCrowd crowd) {
    RuleElementMatch result = new RuleElementMatch(element, containerMatch);
    List<EvaluatedCondition> evaluatedConditions = new ArrayList<EvaluatedCondition>(element
            .getConditions().size());
    // boolean base = matcher.match(annotation, stream, getParent());
    boolean base = true;
    MatchContext context = new MatchContext(annotation, element, ruleMatch, true);
        
    List<AnnotationFS> textsMatched = new ArrayList<AnnotationFS>(1);
    if (annotation != null) {
      textsMatched.add(annotation);
    }
    result.setMatchInfo(base, textsMatched, stream);
    if (base) {
      for (AbstractRutaCondition condition : element.getConditions()) {
        crowd.beginVisit(condition, null);
        EvaluatedCondition eval = condition.eval(context, stream, crowd);
        crowd.endVisit(condition, null);
        evaluatedConditions.add(eval);
      }
    }
    if (annotation != null) {
      textsMatched.add(annotation);
    }
    result.setConditionInfo(base, evaluatedConditions);
    ruleMatch.setMatched(ruleMatch.matched() && result.matched());
  }

  /**
   * Creates a list of RuleMatches with one RuleMatch with the annotation for every rule for a
   * transition from the RootState into a TransitionState
   * 
   * @param annoFS
   *          - the matched annotation
   * @param rulesList
   *          - the list of rules
   * @param stream
   *          - the RutaStream (is needed in the called functions)
   * @param crowd
   *          - the InferenceCrowd (is needed in the called functions)
   * 
   * @return - a list with the created RuleMatches
   */
  private LinkedList<RuleMatch> createMatches(AnnotationFS annoFS, List<RutaStatement> rulesList,
          RutaStream stream, InferenceCrowd crowd) {
    // RootState -> TransitionState
    LinkedList<RuleMatch> ruleMatches = new LinkedList<RuleMatch>();
    for (RutaStatement statement : rulesList) {
      RutaRule rule = (RutaRule) statement;
      RuleElement element = rule.getRuleElements().get(0);
      RuleMatch match = new RuleMatch(rule);
      ComposedRuleElementMatch rootMatch = new ComposedRuleElementMatch(rule.getRoot(), null);
      match.setRootMatch(rootMatch);
      doMatch(annoFS, match, element, rootMatch, stream, crowd);
      ruleMatches.add(match);
    }
    return ruleMatches;
  }

  /**
   * Creates the match for the transition from the RootState directly into an EndState (happens if
   * the rule only has one RuleElement).
   * 
   * @param annoFS
   *          - the matches annotation for the RuleMatch
   * @param rule
   *          - the matched rule for the RuleMatch
   * @param stream
   *          - the RutaStream (is needed in the called functions)
   * @param crowd
   *          - the InferenceCrowd (is needed in the called functions)
   * 
   * @return the created RuleMatch for the rule with the matched annotation
   */
  private RuleMatch createMatch(AnnotationFS annoFS, RutaRule rule, RutaStream stream,
          InferenceCrowd crowd) {
    // RootState -> EndState
    RuleElement element = rule.getRuleElements().get(0);
    RuleMatch match = new RuleMatch(rule);
    ComposedRuleElementMatch rootMatch = new ComposedRuleElementMatch(rule.getRoot(), null);
    match.setRootMatch(rootMatch);
    doMatch(annoFS, match, element, rootMatch, stream, crowd);
    return match;
  }

  /**
   * Filters the rules for a transition from a TransitionState into a TransitionState and adds an
   * InnerMatch for the matched Annotationen to the corresponding RuleMatches
   * 
   * @param annoFS
   *          - the matched Annotation
   * @param ruleMatches
   *          - the RuleMatches which are filtered
   * @param targetState
   *          - the TransitionState for which the RuleMatches are filtered
   * @param stream
   *          - the RutaStream (is needed in the called functions)
   * @param crowd
   *          - the InferenceCrowd (is needed in the called functions)
   * 
   * @return the RuleMatches for the Rules which are represented by the TransitionState targetState
   */
  private LinkedList<RuleMatch> filterMatches(AnnotationFS annoFS,
          LinkedList<RuleMatch> ruleMatches, TransitionState targetState, RutaStream stream,
          InferenceCrowd crowd) {
    // TransitionState -> TransitionState
    LinkedList<RuleMatch> retList = new LinkedList<RuleMatch>();
    for (RuleMatch match : ruleMatches) {
      for (RutaStatement statement : targetState.getRules()) {
        RutaRule rule = (RutaRule) statement;
        if (match.getRule().equals(rule)) {
          RuleElement element = rule.getRuleElements().get(targetState.getDepth() - 1);
          doMatch(annoFS, match, element, match.getRootMatch(), stream, crowd);
          retList.add(match);
        }
      }
    }
    return retList;
  }

  /**
   * Filters the one RuleMatch from the list of RuleMatches, which corresponds to the Rule from the
   * EndState state and adds the Annotation to the list of matched Annotation of this RuleMatch
   * 
   * @param annoFS
   *          - the Annotation to add
   * @param ruleMatches
   *          - the list of RuleMatches to filter the single RuleMatch from
   * @param state
   *          - the state the rule gets taken from
   * @param stream
   *          stream - the RutaStream (is needed in the called functions)
   * @param crowd
   *          - the InferenceCrowd (is needed in the called functions)
   * 
   * @return the single RuleMatch corresponding to the rule from the state
   */
  private RuleMatch filterMatch(AnnotationFS annoFS, LinkedList<RuleMatch> ruleMatches,
          EndState state, RutaStream stream, InferenceCrowd crowd) {
    // TransitionState -> EndState
    RutaRule rule = (RutaRule) state.getRule();
    for (RuleMatch match : ruleMatches) {
      if (rule.equals(match.getRule())) {
        RuleElement element = rule.getRuleElements().get(state.getDepth() - 1);
        doMatch(annoFS, match, element, match.getRootMatch(), stream, crowd);
        return match;
      }
    }
    // Should not happen!
    return ruleMatches.get(0);
  }
}
