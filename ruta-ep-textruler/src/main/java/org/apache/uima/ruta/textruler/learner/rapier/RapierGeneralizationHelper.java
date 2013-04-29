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

package org.apache.uima.textmarker.textruler.learner.rapier;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.uima.textmarker.textruler.core.TextRulerRuleItem;
import org.apache.uima.textmarker.textruler.core.TextRulerRulePattern;
import org.apache.uima.textmarker.textruler.core.TextRulerToolkit;
import org.apache.uima.textmarker.textruler.core.TextRulerWordConstraint;

public class RapierGeneralizationHelper {

  // ----------------------------------------------------------------------------------------------------------------------------------------
  // --- ITEM(s) GENERALIZATION
  // -------------------------------------------------------------------------------------------------------------
  // ----------------------------------------------------------------------------------------------------------------------------------------
  private static ArrayList<TextRulerRuleItem> getGeneralizationsForRuleItems(
          TextRulerRuleItem item1, TextRulerRuleItem item2) {
    ArrayList<TextRulerRuleItem> item1List = new ArrayList<TextRulerRuleItem>();
    ArrayList<TextRulerRuleItem> item2List = new ArrayList<TextRulerRuleItem>();
    if (item1 != null)
      item1List.add(item1);
    if (item2 != null)
      item2List.add(item2);
    return getGeneralizationsForRuleItemLists(item1List, item2List);
  }

  // generalize two given pattern elements or pattern element lists and return
  // all possible generalizations as
  // TextRulerRuleItem objects
  private static ArrayList<TextRulerRuleItem> getGeneralizationsForRuleItemLists(
          ArrayList<TextRulerRuleItem> item1List, ArrayList<TextRulerRuleItem> item2List) {
    ArrayList<RapierRuleItem> proposedWordConstraints = new ArrayList<RapierRuleItem>();
    ArrayList<RapierRuleItem> proposedTagConstraints = new ArrayList<RapierRuleItem>();
    ArrayList<RapierRuleItem> proposedClassConstraints = new ArrayList<RapierRuleItem>();
    ArrayList<TextRulerRuleItem> result = new ArrayList<TextRulerRuleItem>();

    int resultListLen1 = 0;
    int resultListLen2 = 0;
    boolean oneListIsEmpty = false;

    if (item1List.size() == 0 && item2List.size() == 0) {
      TextRulerToolkit.log("ERROR !");
    }
    if (item1List.size() == 0 || item2List.size() == 0) {
      // TextRulerToolkit.log("SPECIAL CASE WITH ONE LIST OF ZERO SIZE");
      oneListIsEmpty = true;
    }

    boolean hasEmptyWordList = false;
    int maxWordCount = 0;
    boolean hasEmptyTagList = false;
    int maxTagCount = 0;
    for (TextRulerRuleItem rt : item2List) {
      RapierRuleItem t = (RapierRuleItem) rt;
      resultListLen2 += t.isListItem() ? t.listLen() : 1;
      if (t.getWordConstraints().size() > maxWordCount)
        maxWordCount = t.getWordConstraints().size();
      if (t.getWordConstraints().size() == 0)
        hasEmptyWordList = true;
      if (t.getTagConstraints().size() > maxTagCount)
        maxTagCount = t.getTagConstraints().size();
      if (t.getTagConstraints().size() == 0)
        hasEmptyTagList = true;
    }
    for (TextRulerRuleItem rt : item1List) {
      RapierRuleItem t = (RapierRuleItem) rt;
      resultListLen1 += t.isListItem() ? t.listLen() : 1;
      if (t.getWordConstraints().size() > maxWordCount)
        maxWordCount = t.getWordConstraints().size();
      if (t.getWordConstraints().size() == 0)
        hasEmptyWordList = true;
      if (t.getTagConstraints().size() > maxTagCount)
        maxTagCount = t.getTagConstraints().size();
      if (t.getTagConstraints().size() == 0)
        hasEmptyTagList = true;
    }
    int resultListLen = resultListLen1 > resultListLen2 ? resultListLen1 : resultListLen2; // take
    // the
    // bigger
    // of
    // both
    if (resultListLen == 1 && !oneListIsEmpty)
      resultListLen = 0; // lists with a length of 1 can only occur when
    // one itemList is empty! THAT CANNOT HAPPEN
    // HERE!

    // generalize word constraints:
    if (hasEmptyWordList) // at least one constraint of both is empty
    {
      // do nothing here, proposed.wordItems stays empty
      proposedWordConstraints.add(new RapierRuleItem());
    } else // create union of both constraints AND (if both constraints
    // weren't the same) drop constraint
    {
      RapierRuleItem proposed = new RapierRuleItem();
      for (TextRulerRuleItem t : item1List)
        proposed.addWordConstraints(((RapierRuleItem) t).getWordConstraints());
      for (TextRulerRuleItem t : item2List)
        proposed.addWordConstraints(((RapierRuleItem) t).getWordConstraints());

      proposedWordConstraints.add(proposed);

      // if the union of both constraints is a real union (one does not
      // subsume the other completely),
      // we have to add the DROPPING OF THE CONSTRAINT as a second
      // proposed word constraint
      if (maxWordCount != proposed.getWordConstraints().size()) // the
      // union
      // is a
      // real
      // bigger
      // set
      // than
      {
        proposedWordConstraints.add(new RapierRuleItem());
      }
    }

    if (hasEmptyTagList) // at least one constraint of both is empty
    {
      // do nothing here, proposed.tagItems stays empty
      proposedTagConstraints.add(new RapierRuleItem());
    } else // create union of both constraints AND (if both constraints
    // weren't the same) drop constraint
    {
      RapierRuleItem proposed = new RapierRuleItem();
      for (TextRulerRuleItem t : item1List)
        proposed.addTagConstraints(((RapierRuleItem) t).getTagConstraints());
      for (TextRulerRuleItem t : item2List)
        proposed.addTagConstraints(((RapierRuleItem) t).getTagConstraints());

      proposedTagConstraints.add(proposed);

      // if the union of both constraints is a real union (one does not
      // subsume the other completely),
      // we have to add the DROPPING OF THE CONSTRAINT as a second
      // proposed tag constraint
      if (maxTagCount != proposed.getTagConstraints().size()) // the union
      // is a real
      // bigger
      // set than
      {
        proposedTagConstraints.add(new RapierRuleItem());
      }
    }

    // TODO semantic class generalization
    proposedClassConstraints.add(new RapierRuleItem()); // add only NO
    // class
    // constraint
    // version for
    // now!

    // finally, create all combinations of the above proposed items
    for (RapierRuleItem wt : proposedWordConstraints) {
      for (RapierRuleItem tt : proposedTagConstraints) {
        for (RapierRuleItem ct : proposedClassConstraints) {
          RapierRuleItem newItem = new RapierRuleItem();
          for (TextRulerWordConstraint wi : wt.getWordConstraints())
            newItem.addWordConstraint(wi.copy());
          for (String ti : tt.getTagConstraints())
            newItem.addTagConstraint(ti);
          for (String tc : ct.getClassConstraints())
            newItem.addClassConstraint(tc);
          newItem.setListLen(resultListLen);
          newItem.setListBeginsAtZero(oneListIsEmpty && resultListLen > 0);
          result.add(newItem);
        }
      }
    }
    return result;
  }

  // ----------------------------------------------------------------------------------------------------------------------------------------
  // --- EQUAL SIZE PATTERN GENERALIZATION
  // --------------------------------------------------------------------------------------------------
  // ----------------------------------------------------------------------------------------------------------------------------------------
  private static ArrayList<TextRulerRulePattern> getGeneralizationsForRuleItemPatternsOfEqualSize(
          TextRulerRulePattern pattern1, TextRulerRulePattern pattern2) {
    ArrayList<TextRulerRulePattern> resultList = new ArrayList<TextRulerRulePattern>();

    ArrayList<ArrayList<TextRulerRuleItem>> generalizationTable = new ArrayList<ArrayList<TextRulerRuleItem>>();
    Iterator<TextRulerRuleItem> it2 = pattern2.iterator();
    if (pattern1.size() != pattern2.size()) {
      TextRulerToolkit.log("ERROR!");
    }
    for (TextRulerRuleItem item1 : pattern1) {
      TextRulerRuleItem item2 = it2.next();
      // get all possible LGGs of the current two elements and save them
      // into the matrix
      ArrayList<TextRulerRuleItem> allLGGs = getGeneralizationsForRuleItems(item1, item2);
      generalizationTable.add(allLGGs);
      // TextRulerToolkit.log("--- GET GENERALISATIONS FOR TWO TERMS:  --"+t1+"--   --"+t2+"--");
      // ArrayList<MLRapierRuleTerm> allLGGs =
      // this.getGeneralizationsForRuleTerms(t1, t2);
      // for (MLRapierRuleTerm term : allLGGs)
      // TextRulerToolkit.log("--- "+term);
      // TextRulerToolkit.log("--- END");
    }

    // now we have patternSize lists of possible generalizations, one list
    // per original pattern item pair of
    // pattern1 and pattern2. we now have to build all possible
    // combinations. Each combination is a
    // new pattern
    recursiveBuildAllRuleItemCombinations(generalizationTable, 0, new TextRulerRulePattern(),
            resultList);
    return resultList;
  }

  private static void recursiveBuildAllRuleItemCombinations(
          ArrayList<ArrayList<TextRulerRuleItem>> table, int curIndex,
          TextRulerRulePattern currentPattern, ArrayList<TextRulerRulePattern> resultPatterns) {
    if (curIndex >= table.size()) {
      // make a deep copy of the current pattern:
      TextRulerRulePattern copy = new TextRulerRulePattern();
      for (TextRulerRuleItem item : currentPattern)
        copy.add(item.copy());
      resultPatterns.add(copy);
    } else {
      for (TextRulerRuleItem item : table.get(curIndex)) {
        currentPattern.add(item);
        recursiveBuildAllRuleItemCombinations(table, curIndex + 1, currentPattern, resultPatterns);
        currentPattern.remove(currentPattern.size() - 1);
      }
    }
  }

  // ----------------------------------------------------------------------------------------------------------------------------------------
  // --- DISTINCT SIZE PATTERN GENERALIZATION
  // -----------------------------------------------------------------------------------------------
  // ----------------------------------------------------------------------------------------------------------------------------------------

  // this is the low level version for patterns of distinct sizes! it creates
  // ALL possible combinations how to pair/map pattern items
  // of the shorter with those of the longer pattern. the optimizing version
  // (getOptimizedGeneralizationsForRuleItemPatternsOfDistinctSize)
  // uses this method for its pattern segments! (see below)
  private static ArrayList<TextRulerRulePattern> getGeneralizationsForRuleItemPatternsOfDistinctSize(
          TextRulerRulePattern pattern1, TextRulerRulePattern pattern2) {
    ArrayList<TextRulerRulePattern> resultList = new ArrayList<TextRulerRulePattern>();
    if (pattern1.size() == pattern2.size()) {
      TextRulerToolkit.log("ERROR! CALL getGeneralizationsForRuleItemPatternsOfEqualSize instead!");
      if (TextRulerToolkit.DEBUG)
        return null;
    }

    TextRulerRulePattern longerPattern = pattern1;
    TextRulerRulePattern shorterPattern = pattern2;
    if (pattern2.size() > pattern1.size()) {
      longerPattern = pattern2;
      shorterPattern = pattern1;
    }

    if (longerPattern.size() <= 1 || shorterPattern.size() <= 1) {
      // Special case 1: one of the pattern terms list is empty AND
      // special case 2: one has only ONE element
      if (longerPattern.size() + shorterPattern.size() == 0) {
        TextRulerToolkit.log("ERROR !! BOTH PATTERNS ARE EMPTY!");
        if (TextRulerToolkit.DEBUG)
          return null;
      }

      // get all possible generalizations of the two patterns. result of
      // each generalization is ONE rule item, so we
      // don't use TextRulerRulePattern here since this IS NOT a rule
      // pattern! it's a list of possible generalizations:
      ArrayList<TextRulerRuleItem> generalizations = getGeneralizationsForRuleItemLists(
              longerPattern, shorterPattern);
      // create a one element result pattern for each:
      for (TextRulerRuleItem item : generalizations) {
        TextRulerRulePattern p = new TextRulerRulePattern();
        p.add(item);
        resultList.add(p);
      }
    }
    // else SPECIAL CASE 3 // TODO make those values configurable ?
    else if (((longerPattern.size() - shorterPattern.size()) > 6) || (longerPattern.size() > 10)) {
      int resultListLen1 = 0;
      for (TextRulerRuleItem rt : shorterPattern)
        resultListLen1 += ((RapierRuleItem) rt).isListItem() ? ((RapierRuleItem) rt).listLen() : 1;
      int resultListLen2 = 0;
      for (TextRulerRuleItem rt : longerPattern)
        resultListLen2 += ((RapierRuleItem) rt).isListItem() ? ((RapierRuleItem) rt).listLen() : 1;

      RapierRuleItem singleItem = new RapierRuleItem();
      singleItem.setListLen(resultListLen1 > resultListLen2 ? resultListLen1 : resultListLen2);
      TextRulerRulePattern singleItemPattern = new TextRulerRulePattern();
      singleItemPattern.add(singleItem);
      resultList.add(singleItemPattern);
    } else { // sizes are different and both > 1
      // create all possible generalization combinations, that is: how can
      // we map elements of the shorter pattern
      // to the ones of the longer pattern and then generalize each
      // mapping (each group) of items?
      ArrayList<ArrayList<RapierPatternItemMapping>> combinationList = new ArrayList<ArrayList<RapierPatternItemMapping>>();

      recursiveBuildAllPossiblePatternMappingSequences(longerPattern, shorterPattern,
              new ArrayList<RapierPatternItemMapping>(), combinationList);

      for (ArrayList<RapierPatternItemMapping> mappingSequence : combinationList) {
        resultList.addAll(getGeneralizationsForPatternMappingSequence(mappingSequence));
      }
    }
    return resultList;
  }

  // creates all possible combinations how to pair together items from the
  // longer and the shorter source pattern, e.g.
  // 1 2 3 4 5 vs. 1 2 3 = 1/1+2+3 2/4 3/5, ...
  private static void recursiveBuildAllPossiblePatternMappingSequences(
          TextRulerRulePattern longerPattern, TextRulerRulePattern shorterPattern,
          ArrayList<RapierPatternItemMapping> currentMappingSequence,
          ArrayList<ArrayList<RapierPatternItemMapping>> resultList) {
    int windowSize = longerPattern.size() - shorterPattern.size() + 1;

    if (shorterPattern.size() > longerPattern.size()) {
      TextRulerToolkit.log("ERROR: SHORTER > LONGER !!");
    }
    if (longerPattern.size() == 0 || shorterPattern.size() == 0) {
      TextRulerToolkit.log("ERROR: SHORTER == LONGER == 0!");
    } else {
      // if the remaining (sub-)patterns are of equal size or one has only
      // one element left, create one last item mapping and
      // a final result mapping sequence:
      if (shorterPattern.size() == 1 || (longerPattern.size() == shorterPattern.size())) {
        RapierPatternItemMapping lastMapping = new RapierPatternItemMapping();
        lastMapping.shorterPattern.addAll(shorterPattern);
        lastMapping.longerPattern.addAll(longerPattern);
        ArrayList<RapierPatternItemMapping> newMappingSequence = new ArrayList<RapierPatternItemMapping>();
        newMappingSequence.addAll(currentMappingSequence);
        newMappingSequence.add(lastMapping);
        resultList.add(newMappingSequence);
      } else { // otherwise we have to create all possible combinations of
        // the longer and shorter remaining pattern:
        TextRulerRuleItem firstItem = shorterPattern.get(0);
        // combine with 0, 0/1, ... 0/1/2/.../windowSize-1
        for (int maxi = 0; maxi < windowSize; maxi++) {
          RapierPatternItemMapping newMapping = new RapierPatternItemMapping();
          newMapping.shorterPattern.add(firstItem);
          for (int li = 0; li <= maxi; li++)
            newMapping.longerPattern.add(longerPattern.get(li));
          currentMappingSequence.add(newMapping);
          TextRulerRulePattern restLongerPattern = new TextRulerRulePattern();
          TextRulerRulePattern restShorterPattern = new TextRulerRulePattern();
          for (int i = 1; i < shorterPattern.size(); i++)
            restShorterPattern.add(shorterPattern.get(i));
          for (int i = maxi + 1; i < longerPattern.size(); i++)
            restLongerPattern.add(longerPattern.get(i));

          // recurse:
          recursiveBuildAllPossiblePatternMappingSequences(restLongerPattern, restShorterPattern,
                  currentMappingSequence, resultList);

          // remove last segment to get back to the same state as
          // before the recursion:
          currentMappingSequence.remove(currentMappingSequence.size() - 1);
        }
      }
    }
  }

  // here the input is called a MAPPING instead of a pattern segmentation in
  // order to distinguish between the two levels of
  // dividing the problem: a pattern segmentation is a special mapping of
  // equal items in the two to generalize source patterns;
  // the segments that result through that segmentation still need to be
  // generalized (see getGeneralizationsForPatternSegmentation)
  // if sucha semgent has subpattersn of different size,
  // getGeneralizationsForRuleItemPatternsOfDistinctSize is used to
  // generalize it, which uses THIS METHOD HERE to get all generalizations for
  // a special MAPPING. a mapping (in comparison to the
  // segmentation!) is a mapping between the longer and shorter pattern items
  // which then get directly generalized here!
  // in order to show this difference, we use the (inernally exactly the
  // same!) class RapierPatternItemMapping instead of
  // RapierPatternSegment!)
  private static ArrayList<TextRulerRulePattern> getGeneralizationsForPatternMappingSequence(
          ArrayList<RapierPatternItemMapping> patternMappingSequence) {
    ArrayList<TextRulerRulePattern> resultList = new ArrayList<TextRulerRulePattern>();
    ArrayList<ArrayList<TextRulerRuleItem>> generalizationTable = new ArrayList<ArrayList<TextRulerRuleItem>>();

    // every mapping has several possible generalizations, so we store all
    // of them in that generalizationTable, one list of
    // generalizations for each mapping:
    for (RapierPatternItemMapping mapping : patternMappingSequence) {
      ArrayList<TextRulerRuleItem> lggList = getGeneralizationsForRuleItemLists(
              mapping.shorterPattern, mapping.longerPattern);
      generalizationTable.add(lggList);
    }

    // afterwards we have again to create all possible combinations of those
    // lists (like in the equalSizeGeneralization):
    // Each combination is a new pattern
    recursiveBuildAllRuleItemCombinations(generalizationTable, 0, new TextRulerRulePattern(),
            resultList);
    return resultList;
  }

  // ----------------------------------------------------------------------------------------------------------------------------------------
  // --- FIND MATCHINGS BETWEEN PATTERNS FOR GENERALIZATION
  // -----------------------------------------------------------------------------------------------
  // ----------------------------------------------------------------------------------------------------------------------------------------

  // find matches in two patterns by comparing the items of the patterns and
  // return all possible segmentations of those two patterns.
  private static void recursiveFindPatternSegmentsByMatchingPatternItems(
          TextRulerRulePattern longerPattern, TextRulerRulePattern shorterPattern,
          ArrayList<RapierPatternSegment> currentSegmentation,
          ArrayList<ArrayList<RapierPatternSegment>> resultList) {
    int cmpWindowSize = longerPattern.size() - shorterPattern.size() + 1;

    // is one
    // (rest-)pattern
    // empty ?
    if (longerPattern.size() == 0 || shorterPattern.size() == 0) {
      // create result segmentation
      ArrayList<RapierPatternSegment> newSegmentation = new ArrayList<RapierPatternSegment>();
      newSegmentation.addAll(currentSegmentation); // add current
      // add rest if
      // recursive state
      // anything is
      // left one of
      // the
      // patterns
      if (longerPattern.size() + shorterPattern.size() > 0) {
        RapierPatternSegment lastSegment = new RapierPatternSegment();
        for (TextRulerRuleItem i : shorterPattern)
          lastSegment.shorterPattern.add(i);
        for (TextRulerRuleItem i : longerPattern)
          lastSegment.longerPattern.add(i);
        newSegmentation.add(lastSegment);
      }
      resultList.add(newSegmentation);
    } else {
      boolean matched = false;
      for (int si = 0; si < shorterPattern.size(); si++) {
        // compare element si with si, si+1, ... si+cmpWindowSize-1
        for (int li = si; li < si + cmpWindowSize; li++) {
          if (longerPattern.get(li).equals(shorterPattern.get(si))) {
            // matched pair found!
            matched = true;
            // --> calculate pattern segments, add them to the
            // current one and pass the rest of the
            // patterns to the next recursion level:
            RapierPatternSegment newSegment = new RapierPatternSegment();
            for (int i = 0; i < si; i++)
              newSegment.shorterPattern.add(shorterPattern.get(i));
            for (int i = 0; i < li; i++)
              newSegment.longerPattern.add(longerPattern.get(i));

            boolean addedLeftSegmentation = false;
            if (newSegment.longerPattern.size() > 0 || newSegment.shorterPattern.size() > 0) {
              // only add if the segmentation is not empty!
              currentSegmentation.add(newSegment);
              addedLeftSegmentation = true;
            }

            RapierPatternSegment matchedSegment = new RapierPatternSegment();
            matchedSegment.shorterPattern.add(shorterPattern.get(si));
            matchedSegment.longerPattern.add(longerPattern.get(li));
            currentSegmentation.add(matchedSegment);

            // the rest is now the rest to the right of both (so
            // li+1 and si+1 to the ends...)
            TextRulerRulePattern restLongerPattern = new TextRulerRulePattern();
            TextRulerRulePattern restShorterPattern = new TextRulerRulePattern();
            for (int i = li + 1; i < longerPattern.size(); i++)
              restLongerPattern.add(longerPattern.get(i));
            for (int i = si + 1; i < shorterPattern.size(); i++)
              restShorterPattern.add(shorterPattern.get(i));

            // recurse...
            if (restLongerPattern.size() > restShorterPattern.size())
              recursiveFindPatternSegmentsByMatchingPatternItems(restLongerPattern,
                      restShorterPattern, currentSegmentation, resultList);
            else
              recursiveFindPatternSegmentsByMatchingPatternItems(restShorterPattern,
                      restLongerPattern, currentSegmentation, resultList);

            // remove added segments so that we are in the same
            // state as before the recursion:
            if (addedLeftSegmentation)
              currentSegmentation.remove(currentSegmentation.size() - 1); // remove the left side
            // segment
            currentSegmentation.remove(currentSegmentation.size() - 1); // remove
            // the
            // matched
            // segment
          }
        }
      }
      if (!matched) // add remaining items of both lists in one pattern
      // segment
      {
        ArrayList<RapierPatternSegment> newSegmentation = new ArrayList<RapierPatternSegment>();
        newSegmentation.addAll(currentSegmentation);

        RapierPatternSegment lastSegment = new RapierPatternSegment();
        for (TextRulerRuleItem i : shorterPattern)
          lastSegment.shorterPattern.add(i);
        for (TextRulerRuleItem i : longerPattern)
          lastSegment.longerPattern.add(i);
        newSegmentation.add(lastSegment);
        resultList.add(newSegmentation);
      }
    }
  }

  private static ArrayList<TextRulerRulePattern> getGeneralizationsForPatternSegmentation(
          ArrayList<RapierPatternSegment> patternSegmentation) {
    // for creating those, we need a table:
    // each segment of the patternSegmentation creates a bunch of possible
    // new generalized sub patterns (that's the inner
    // ArrayList<TextRulerRulePattern>)
    // since we have a whole sequence of pattern semgents (a whole
    // segmentation), we need the outer ArrayList to collect
    // all generalizations of all pattern segments:
    ArrayList<ArrayList<TextRulerRulePattern>> generalizationTable = new ArrayList<ArrayList<TextRulerRulePattern>>();

    // now, we create all generalizations of each pattern segment and
    // collect them in that table:
    for (RapierPatternSegment pSeg : patternSegmentation) {
      ArrayList<TextRulerRulePattern> pSegGeneralizations;

      if (pSeg.longerPattern.size() == pSeg.shorterPattern.size())
        pSegGeneralizations = getGeneralizationsForRuleItemPatternsOfEqualSize(pSeg.longerPattern,
                pSeg.shorterPattern);
      else
        pSegGeneralizations = getGeneralizationsForRuleItemPatternsOfDistinctSize(
                pSeg.longerPattern, pSeg.shorterPattern);

      generalizationTable.add(pSegGeneralizations);
    }

    // finally, we have to build all combinations of them in form of
    // MLRulePatterns:
    ArrayList<TextRulerRulePattern> resultList = new ArrayList<TextRulerRulePattern>(); // the
    // result
    // is
    // a
    // list
    // of
    // new
    // generalized
    // patterns

    recursiveBuildAllRuleItemCombinationsFromPatterns(generalizationTable, 0,
            new TextRulerRulePattern(), resultList);

    return resultList;
  }

  private static void recursiveBuildAllRuleItemCombinationsFromPatterns(
          ArrayList<ArrayList<TextRulerRulePattern>> table, int curIndex,
          TextRulerRulePattern currentPattern, ArrayList<TextRulerRulePattern> resultPatterns) {
    if (curIndex >= table.size()) {
      // make a deep copy of the current pattern:
      TextRulerRulePattern copy = new TextRulerRulePattern();
      for (TextRulerRuleItem item : currentPattern)
        copy.add(item.copy());
      resultPatterns.add(copy);
    } else {
      for (TextRulerRulePattern pattern : table.get(curIndex)) {
        currentPattern.addAll(pattern);
        recursiveBuildAllRuleItemCombinationsFromPatterns(table, curIndex + 1, currentPattern,
                resultPatterns);
        for (int i = 0; i < pattern.size(); i++)
          currentPattern.remove(currentPattern.size() - 1);
      }
    }
  }

  // ----------------------------------------------------------------------------------------------------------------------------------------
  // --- WORKING ON PATTERNS OF DISTINCT LENGTH - OPTIMIZED
  // ---------------------------------------------------------------------------------
  // ----------------------------------------------------------------------------------------------------------------------------------------

  // "optimized", because this method uses the pattern item matching
  // optimization (search for equal items and make a segmentation, etc.)
  private static ArrayList<TextRulerRulePattern> getOptimizedGeneralizationsForRuleItemPatternsOfDistinctSize(
          TextRulerRulePattern pattern1, TextRulerRulePattern pattern2) {
    ArrayList<ArrayList<RapierPatternSegment>> matchedCombinationList = new ArrayList<ArrayList<RapierPatternSegment>>();

    // in order to reduce the amount of possible combinations how to combine
    // the elements of the shorter pattern with the
    // elements of the longer pattern, we first search for equal pattern
    // items in both patterns. those equal patterns get hardwired
    // in the combination and the left and right remaining subpatterns stay
    // as a "divided smaller problem" that needs to be conquered...
    // the result of the search is a list of possible segmentations of the
    // patterns. all semgementations are those "smaller"
    // left problems that we then need to generalize in the original manner.
    // if no equal items are found, one segmentation with only one segment
    // (the orignal longer and shoter pattern) is returned and
    // has to be generalized.
    if (pattern1.size() > pattern2.size())
      recursiveFindPatternSegmentsByMatchingPatternItems(pattern1, pattern2,
              new ArrayList<RapierPatternSegment>(), matchedCombinationList);
    else
      recursiveFindPatternSegmentsByMatchingPatternItems(pattern2, pattern1,
              new ArrayList<RapierPatternSegment>(), matchedCombinationList);

    // if (TextRulerToolkit.DEBUG && matchedCombinationList.size() > 1)
    // {
    // TextRulerToolkit.log("PATTERN SEQUENCES FOUND: "+matchedCombinationList.size());
    // for (ArrayList<RapierPatternSegment> patternSequence :
    // matchedCombinationList)
    // {
    // TextRulerToolkit.log("\tNEXT SEQUENCE");
    // for (RapierPatternSegment pSeg : patternSequence)
    // {
    // TextRulerToolkit.log("\t\t"+pSeg.longerPattern);
    // TextRulerToolkit.log("\t\t"+pSeg.shorterPattern);
    // }
    // }
    // }

    ArrayList<TextRulerRulePattern> resultList = new ArrayList<TextRulerRulePattern>();

    for (ArrayList<RapierPatternSegment> patternSegmentation : matchedCombinationList) {
      // TODO filter out possible duplicates ?
      resultList.addAll(getGeneralizationsForPatternSegmentation(patternSegmentation));
    }
    return resultList;
  }

  // ----------------------------------------------------------------------------------------------------------------------------------------
  // --- THE FINAL RESULT: a genarlization method
  // -------------------------------------------------------------------------------------------
  // ----------------------------------------------------------------------------------------------------------------------------------------

  // input: two sequences of rule items (=patterns) that shall be
  // generalized... matchings are searched for a optimized search
  // and to get a not too big count of generalizations...
  // result: a (probably very large!) list of possible generalizations, e.g.
  // used for all slotfiller generalizations of two rules...
  public static ArrayList<TextRulerRulePattern> getGeneralizationsForRuleItemPatterns(
          TextRulerRulePattern pattern1, TextRulerRulePattern pattern2) {
    ArrayList<TextRulerRulePattern> result = new ArrayList<TextRulerRulePattern>();

    // if (TextRulerToolkit.DEBUG)
    // {
    // TextRulerToolkit.log("\tgetGeneralizationsForRuleItemPatterns:");
    // TextRulerToolkit.log("\tPattern1:"+pattern1);
    // TextRulerToolkit.log("\tPattern2:"+pattern2);
    // }

    if (pattern1.size() == 0 && pattern2.size() == 0) {
      return result; // return empty list
    } else if (pattern1.size() == pattern2.size()) // both have the same
    // pattern item count
    {
      // generalizing is easy then: simply generalize each pair of items:
      result = getGeneralizationsForRuleItemPatternsOfEqualSize(pattern1, pattern2);
    } else {
      // TextRulerToolkit.logIf(TextRulerToolkit.DEBUG && pattern1.size()
      // == 0 || pattern2.size() == 0, "SpecialCaseWithZeroLength");
      result = getOptimizedGeneralizationsForRuleItemPatternsOfDistinctSize(pattern1, pattern2);
    }

    // if (TextRulerToolkit.DEBUG)
    // {
    // TextRulerToolkit.log("\t\tGeneralizations: "+result.size());
    // for (TextRulerRulePattern lggPattern : result)
    // TextRulerToolkit.log("\t\t\t"+lggPattern);
    // }

    return result;
  }

}
