package org.apache.uima.tm.textruler.wien;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.textruler.core.TextRulerAnnotation;
import org.apache.uima.tm.textruler.core.TextRulerBasicLearner;
import org.apache.uima.tm.textruler.core.TextRulerExample;
import org.apache.uima.tm.textruler.core.TextRulerExampleDocument;
import org.apache.uima.tm.textruler.core.TextRulerRuleItem;
import org.apache.uima.tm.textruler.core.TextRulerRulePattern;
import org.apache.uima.tm.textruler.core.TextRulerSlotPattern;
import org.apache.uima.tm.textruler.core.TextRulerTarget;
import org.apache.uima.tm.textruler.core.TextRulerToolkit;
import org.apache.uima.tm.textruler.extension.TextRulerLearnerDelegate;

public class Wien extends TextRulerBasicLearner {

  TextRulerRulePattern hPattern;

  TextRulerRulePattern tPattern;

  Map<String, PatternPair> headTailCache = new HashMap<String, PatternPair>();

  Map<String, List<TextRulerRulePattern>> interTupelSeparatorsCache = new HashMap<String, List<TextRulerRulePattern>>();

  public static class PatternPair {
    public TextRulerRulePattern l = new TextRulerRulePattern();

    public TextRulerRulePattern r = new TextRulerRulePattern();
  }

  ArrayList<PatternPair> patternPairs = new ArrayList<PatternPair>();

  WienRule theRule;

  public enum constraint3ReturnType {
    C3_SUCCESS, C3_L1CandidateSuffixError, C3_TailCandidateH_L1Error, C3_TailCandidateRK_PrefixError, C3_TailCandidateNotFoundError, C3_TailCandidateSucceedsL1InTailError, C3_L1CandidateInterTupleSeparatorSuffixError, C3_TailCandidatePrecedesL1InterTupleSeparatorError
  };

  public Wien(String inputDir, String prePropTmFile, String tmpDir, String[] slotNames,
          Set<String> filterSet, TextRulerLearnerDelegate delegate) {
    super(inputDir, prePropTmFile, tmpDir, slotNames, filterSet, delegate);
  }

  @Override
  public boolean collectNegativeCoveredInstancesWhenTesting() {
    return false;
  }

  @Override
  protected void doRun() {
    TextRulerToolkit.log("-- WIEN START");

    headTailCache.clear();
    interTupelSeparatorsCache.clear();

    for (int i = 0; i < slotNames.length; i++)
      patternPairs.add(new PatternPair());

    TextRulerTarget target = new TextRulerTarget(slotNames, this);

    exampleDocuments.createExamplesForTarget(target); // new multislot
    // target examples

    for (TextRulerExample e : exampleDocuments.getAllPositiveExamples()) {
      TextRulerToolkit.log("Example found: " + e);
    }

    try {
      boolean allOk = true;
      sendStatusUpdateToDelegate("Searching for right patterns...",
              TextRulerLearnerState.ML_RUNNING, false);
      if (!findRightPatterns())
        allOk = false;
      sendStatusUpdateToDelegate("Searching for left patterns...",
              TextRulerLearnerState.ML_RUNNING, false);
      if (!findLeftPatterns())
        allOk = false;
      sendStatusUpdateToDelegate("Searching for head, tail and left1 patterns...",
              TextRulerLearnerState.ML_RUNNING, false);
      if (!findHeadTailAndL1Patterns())
        allOk = false;
      // {
      // String s = "";
      // for (TextRulerRuleItem i : hPattern)
      // s += " "+i;
      // s += " ||||";
      // for (TextRulerRuleItem i : patternPairs.get(0).l)
      // s += " "+i;
      // s += " ||||";
      // for (TextRulerRuleItem i : tPattern)
      // s += " "+i;
      // TextRulerToolkit.log(s);
      // }

      if (allOk) {
        sendStatusUpdateToDelegate("Building multi-slot rule.", TextRulerLearnerState.ML_RUNNING,
                false);
        theRule = new WienRule(this, target);
        List<TextRulerSlotPattern> rPatterns = theRule.getPatterns();
        int totalItemCount = 0;
        for (int k = 0; k < slotNames.length; k++) {
          WienRuleItem slotItem = new WienRuleItem((TextRulerAnnotation) null);
          TextRulerSlotPattern rP = new TextRulerSlotPattern();
          rPatterns.add(rP);
          PatternPair p = patternPairs.get(k);
          for (int i = 0; i < p.l.size(); i++) {
            WienRuleItem item = (WienRuleItem) p.l.get(i);
            if (k == 0 && i == 0) // the very first rule item:
            {
              item = item.copy();
              // old version:
              // item.addCondition("-NEAR,wien_tail,10000000,false");
              item.addCondition("-AFTER(wien_tail)");
              item.addCondition("-PARTOF(wien_rulemark)");
            }
            rP.preFillerPattern.add(item);
            totalItemCount++;
          }
          rP.fillerPattern.add(slotItem.copy());
          totalItemCount++;
          for (int i = 0; i < p.r.size(); i++) {
            WienRuleItem item = (WienRuleItem) p.r.get(i);
            totalItemCount++;
            if (k == slotNames.length - 1 && i == p.r.size() - 1) // the
            // very
            // last
            // item
            {
              item = item.copy();
              item.addAction("MARK(wien_rulemark, 1, " + totalItemCount + ")");
            }
            rP.postFillerPattern.add(item);
          }
          totalItemCount++; // the inter-slot ALL*? item has to be
          // counted as well!
        }
        sendStatusUpdateToDelegate("Done", TextRulerLearnerState.ML_DONE, true);
      } else
        sendStatusUpdateToDelegate("Done - Not all patterns could be learned!",
                TextRulerLearnerState.ML_DONE, true);
    } catch (Exception e) {
      e.printStackTrace();
      sendStatusUpdateToDelegate("Aborted due to Exception!", TextRulerLearnerState.ML_ERROR, true);
    }
    headTailCache.clear();
    interTupelSeparatorsCache.clear();
    TextRulerToolkit.log("-- WIEN END");
  }

  protected boolean findRightPatterns() {
    TextRulerExampleDocument doc = exampleDocuments.getDocuments().get(0);
    boolean allFound = true;
    for (int k = 0; k < slotNames.length; k++) {
      List<TextRulerRulePattern> rightContexts = getRightContextForSlot(doc, k);
      System.out.println(rightContexts.get(0));
      int shortest = Integer.MAX_VALUE;
      for (TextRulerRulePattern p : rightContexts)
        shortest = p.size() < shortest ? p.size() : shortest;
      boolean found = false;
      for (int len = 1; len <= shortest; len++) {
        TextRulerRulePattern subPattern = rightContexts.get(0).subPattern(0, len);
        if (testConstraint1(subPattern, k)) {
          // for (TextRulerRuleItem i : subPattern)
          // ((WienRuleItem)i).getWordConstraint().setGeneralizeLinkMarkUp(true);
          patternPairs.get(k).r = subPattern;
          TextRulerToolkit.log("right " + k + ": " + subPattern);
          found = true;
          break;
        }
      }
      if (!found)
        allFound = false;
    }
    return allFound;
  }

  protected boolean findLeftPatterns() {
    TextRulerExampleDocument doc = exampleDocuments.getDocuments().get(0);
    // skip l 0 !
    if (slotNames.length < 2)
      return true;
    boolean allFound = true;
    for (int k = 1; k < slotNames.length; k++) {
      List<TextRulerRulePattern> leftContexts = getLeftContextForSlot(doc, k);
      int shortest = Integer.MAX_VALUE;
      for (TextRulerRulePattern p : leftContexts)
        shortest = p.size() < shortest ? p.size() : shortest;
      TextRulerRulePattern sourcePattern = leftContexts.get(0);
      boolean found = false;
      for (int len = 1; len <= shortest; len++) {
        // get suffix:
        TextRulerRulePattern subPattern = sourcePattern.subPattern(sourcePattern.size() - len, len);
        if (testConstraint2(subPattern, k)) {
          patternPairs.get(k).l = subPattern;
          for (TextRulerRuleItem i : subPattern)
            ((WienRuleItem) i).getWordConstraint().setGeneralizeLinkMarkUp(true);
          TextRulerToolkit.log("left " + k + ": " + subPattern);
          found = true;
          break;
        }
      }
      if (!found)
        allFound = false;
    }
    return allFound;
  }

  protected boolean findHeadTailAndL1Patterns() {
    List<TextRulerExampleDocument> docs = exampleDocuments.getDocuments();
    TextRulerExampleDocument doc0 = docs.get(0);
    TextRulerRulePattern head = new TextRulerRulePattern();
    TextRulerRulePattern tail = new TextRulerRulePattern();
    getPageHeadAndTailPortion(doc0, head, tail);

    final class HLCandidate {
      public TextRulerRulePattern head = new TextRulerRulePattern();

      public TextRulerRulePattern l1 = new TextRulerRulePattern();
    }

    // a small optimization:
    // find out the maximum possible length for l1 in doc0 since l1 is much
    // smaller than the possible head length!
    List<TextRulerRulePattern> interTupleSeparators = getInterTupleSepatators(doc0);
    int shortestL1 = head.size() - 1;
    for (TextRulerRulePattern its : interTupleSeparators)
      shortestL1 = its.size() < shortestL1 ? its.size() : shortestL1;

    List<HLCandidate> hlCandidates = new ArrayList<HLCandidate>();
    // create candidates for each separation of the head and tail patterns:
    for (int separator = head.size() - 1; separator > 0; separator--) {
      HLCandidate c = new HLCandidate();
      for (int i = 0; i < head.size(); i++) {
        if (i < separator)
          c.head.add(head.get(i));
        else {
          WienRuleItem it = (WienRuleItem) head.get(i).copy();
          it.getWordConstraint().setGeneralizeLinkMarkUp(true);
          c.l1.add(it);
        }
      }
      hlCandidates.add(c);
      TextRulerToolkit.log(c.head.size() + " vs. " + c.l1.size());
      if (c.l1.size() >= shortestL1)
        break;
    }

    long total = 0;

    // get total h l1 t combination count:
    long tCand = (tail.size() * (tail.size() + 1)) / 2;
    for (HLCandidate c : hlCandidates) {
      total += ((c.head.size() - 1) * (c.head.size())) / 2;
    }
    total *= tCand;

    long current = 0;
    int oldPercent = -1;

    for (HLCandidate c : hlCandidates) {
      // for each "candidate" which represents a l1 suffix pattern of the
      // head tokens and a rest pattern for the h pattern,
      // we have to create every sub pattern of the remaining h pattern as
      // a h candidate:
      TextRulerRulePattern l1 = c.l1;
      TextRulerRulePattern h = null;

      boolean l1Sucks = false;

      for (int endI = c.head.size() - 1; endI > 0; endI--) {
        for (int startI = endI; startI > 0; startI--) {
          h = new TextRulerRulePattern();
          for (int i = startI; i <= endI; i++)
            h.add(c.head.get(i));

          // now for each h candidate we have to create each t
          // candidate:
          TextRulerRulePattern t = null;
          for (int tstartI = 0; tstartI < tail.size(); tstartI++) {
            for (int tendI = tstartI; tendI < tail.size(); tendI++) {
              int percent = Math.round(((float) current * 100 / total));
              if (percent != oldPercent) {
                oldPercent = percent;
                if (percent > 100)
                  percent = 100;
                // TextRulerToolkit.log(current+" / "+total);
                sendStatusUpdateToDelegate("Testing C3, " + percent + "%",
                        TextRulerLearnerState.ML_RUNNING, false);
              }
              if (shouldAbort())
                return false;
              current++;

              t = new TextRulerRulePattern();
              for (int i = tstartI; i <= tendI; i++)
                t.add(tail.get(i));

              // no we have a possible candidate triple: h, t and
              // l1:

              constraint3ReturnType c3Result = testConstraint3(h, t, l1);

              if (c3Result == constraint3ReturnType.C3_SUCCESS) {
                hPattern = h;
                tPattern = t;
                patternPairs.get(0).l = l1;
                return true;
              } else if (c3Result == constraint3ReturnType.C3_L1CandidateSuffixError
                      || c3Result == constraint3ReturnType.C3_L1CandidateInterTupleSeparatorSuffixError) {
                l1Sucks = true;
                current += tail.size() - tendI - 1;
                break;
              } else if (c3Result == constraint3ReturnType.C3_TailCandidateH_L1Error
                      || c3Result == constraint3ReturnType.C3_TailCandidateSucceedsL1InTailError) {
                // no special pruning options here... we simply
                // have to test the next t-candidate
              } else if (c3Result == constraint3ReturnType.C3_TailCandidateRK_PrefixError
                      || c3Result == constraint3ReturnType.C3_TailCandidateNotFoundError) {
                // all candidates with the same start item are
                // bad, so leave this inner loop:
                current += tail.size() - tendI - 1;
                break;
              } else if (c3Result == constraint3ReturnType.C3_TailCandidatePrecedesL1InterTupleSeparatorError) {
                // this is a problematic case... the cause could
                // be L1 or the current Tail pattern,
                // so we can't do nothing about it! just try the
                // next t-candidate
              }
            }
            if (l1Sucks) {
              current += (tail.size() - tstartI - 1) * (tail.size() - tstartI) / 2;
              break;
            }
          }
          if (l1Sucks) {
            if (startI > 0)
              current += (startI - 1) * tCand;
            break;
          }
        }
        if (l1Sucks) {
          current += (endI * (endI + 1) / 2) * tCand;
          break;
        }
      }
    }
    return false;
  }

  protected void getPageHeadAndTailPortion(TextRulerExampleDocument doc, TextRulerRulePattern head,
          TextRulerRulePattern tail) {
    String key = doc.getCasFileName();
    if (headTailCache.containsKey(key)) {
      PatternPair p = headTailCache.get(key);
      head.addAll(p.l);
      tail.addAll(p.r);
    } else {
      CAS cas = doc.getCAS();
      TextRulerExample firstExample = doc.getPositiveExamples().get(0);
      TextRulerExample lastExample = doc.getPositiveExamples().get(
              doc.getPositiveExamples().size() - 1);
      TypeSystem ts = cas.getTypeSystem();
      Type tokenType = ts.getType(TextRulerToolkit.TM_ALL_TYPE_NAME);
      List<AnnotationFS> headTokens = TextRulerToolkit.getAnnotationsBeforePosition(cas,
              firstExample.getAnnotations()[0].getBegin(), 0, TextRulerToolkit
                      .getFilterSetWithSlotNames(slotNames, filterSet), tokenType);
      TextRulerAnnotation[] lastExampleAnnotations = lastExample.getAnnotations();
      List<AnnotationFS> tailTokens = TextRulerToolkit.getAnnotationsAfterPosition(cas,
              lastExampleAnnotations[lastExampleAnnotations.length - 1].getEnd(), 0,
              TextRulerToolkit.getFilterSetWithSlotNames(slotNames, filterSet), tokenType);
      for (AnnotationFS afs : headTokens)
        head.add(new WienRuleItem(new TextRulerAnnotation(afs, doc)));
      for (AnnotationFS afs : tailTokens)
        tail.add(new WienRuleItem(new TextRulerAnnotation(afs, doc)));
      PatternPair p = new PatternPair();
      p.l.addAll(head);
      p.r.addAll(tail);
      headTailCache.put(key, p);
    }
  }

  protected List<TextRulerRulePattern> getInterTupleSepatators(TextRulerExampleDocument doc) {
    String key = doc.getCasFileName();
    if (interTupelSeparatorsCache.containsKey(key)) {
      return interTupelSeparatorsCache.get(key);
    } else {
      List<TextRulerRulePattern> result = new ArrayList<TextRulerRulePattern>();
      CAS cas = doc.getCAS();
      TypeSystem ts = cas.getTypeSystem();
      Type tokenType = ts.getType(TextRulerToolkit.TM_ALL_TYPE_NAME);
      List<TextRulerExample> examples = doc.getPositiveExamples();
      for (int i = 0; i < examples.size() - 1; i++) {
        // get separator between i'th and (i+1)'th example:
        TextRulerAnnotation[] exampleAnnotations1 = examples.get(i).getAnnotations();
        TextRulerAnnotation[] exampleAnnotations2 = examples.get(i + 1).getAnnotations();
        TextRulerAnnotation lastOf1 = exampleAnnotations1[exampleAnnotations1.length - 1];
        TextRulerAnnotation firstOf2 = exampleAnnotations2[0];
        List<AnnotationFS> theTokens = TextRulerToolkit.getAnnotationsWithinBounds(cas, lastOf1
                .getEnd(), firstOf2.getBegin(), TextRulerToolkit.getFilterSetWithSlotNames(
                slotNames, filterSet), tokenType);
        TextRulerRulePattern thePattern = new TextRulerRulePattern();
        for (AnnotationFS afs : theTokens)
          thePattern.add(new WienRuleItem(new TextRulerAnnotation(afs, doc)));
        if (thePattern.size() > 0)
          result.add(thePattern);

      }
      interTupelSeparatorsCache.put(key, result);
      return result;
    }
  }

  protected List<TextRulerRulePattern> getRightContextForSlot(TextRulerExampleDocument doc,
          int slotIndex) {
    List<TextRulerRulePattern> result = new ArrayList<TextRulerRulePattern>();
    CAS cas = doc.getCAS();
    TypeSystem ts = cas.getTypeSystem();
    Type tokenType = ts.getType(TextRulerToolkit.TM_ALL_TYPE_NAME);
    List<TextRulerExample> examples = doc.getPositiveExamples();
    boolean isLastSlot = slotIndex >= slotNames.length - 1;
    for (int ei = 0; ei < examples.size(); ei++) {
      boolean isLastExample = ei == examples.size() - 1;
      TextRulerExample e = examples.get(ei);
      // get stuff between slot slotIndex and slotIndex+1
      TextRulerAnnotation slotAnnotation = e.getAnnotations()[slotIndex];
      TextRulerAnnotation nextSlotAnnotation;

      if (!isLastSlot)
        nextSlotAnnotation = e.getAnnotations()[slotIndex + 1];
      else {
        if (!isLastExample) // the next slot annotation is the first
          // example annotation of the next template:
          nextSlotAnnotation = examples.get(ei + 1).getAnnotations()[0];
        else
          nextSlotAnnotation = null;
      }

      List<AnnotationFS> theTokens;
      if (nextSlotAnnotation == null)
        theTokens = TextRulerToolkit.getAnnotationsAfterPosition(cas, slotAnnotation.getEnd(), 0,
                TextRulerToolkit.getFilterSetWithSlotNames(slotNames, filterSet), tokenType);
      else
        theTokens = TextRulerToolkit.getAnnotationsWithinBounds(cas, slotAnnotation.getEnd(),
                nextSlotAnnotation.getBegin(), TextRulerToolkit.getFilterSetWithSlotNames(
                        slotNames, filterSet), tokenType);
      TextRulerRulePattern thePattern = new TextRulerRulePattern();
      for (AnnotationFS afs : theTokens)
        thePattern.add(new WienRuleItem(new TextRulerAnnotation(afs, doc)));
      if (thePattern.size() > 0)
        result.add(thePattern);
    }
    return result;
  }

  protected List<TextRulerRulePattern> getLeftContextForSlot(TextRulerExampleDocument doc,
          int slotIndex) {
    if (slotIndex == 0)
      return null;
    List<TextRulerRulePattern> result = new ArrayList<TextRulerRulePattern>();
    CAS cas = doc.getCAS();
    TypeSystem ts = cas.getTypeSystem();
    Type tokenType = ts.getType(TextRulerToolkit.TM_ALL_TYPE_NAME);
    List<TextRulerExample> examples = doc.getPositiveExamples();

    boolean isFirstSlot = slotIndex == 0;
    for (int ei = 0; ei < examples.size(); ei++) {
      boolean isFirstExample = ei == 0;
      TextRulerExample e = examples.get(ei);
      // get stuff between slot slotIndex and slotIndex+1
      TextRulerAnnotation slotAnnotation = e.getAnnotations()[slotIndex];
      TextRulerAnnotation prevSlotAnnotation;

      if (!isFirstSlot)
        prevSlotAnnotation = e.getAnnotations()[slotIndex - 1];
      else {
        if (!isFirstExample)
          prevSlotAnnotation = examples.get(ei - 1).getAnnotations()[slotNames.length - 1];
        else
          prevSlotAnnotation = null;
      }

      List<AnnotationFS> theTokens;
      if (prevSlotAnnotation == null)
        theTokens = TextRulerToolkit.getAnnotationsBeforePosition(cas, slotAnnotation.getBegin(),
                0, TextRulerToolkit.getFilterSetWithSlotNames(slotNames, filterSet), tokenType);
      else
        theTokens = TextRulerToolkit.getAnnotationsWithinBounds(cas, prevSlotAnnotation.getEnd(),
                slotAnnotation.getBegin(), TextRulerToolkit.getFilterSetWithSlotNames(slotNames,
                        filterSet), tokenType);
      TextRulerRulePattern thePattern = new TextRulerRulePattern();
      for (AnnotationFS afs : theTokens)
        thePattern.add(new WienRuleItem(new TextRulerAnnotation(afs, doc), true));
      if (thePattern.size() > 0)
        result.add(thePattern);
    }
    return result;
  }

  protected List<TextRulerRulePattern> getSlotFillerPatterns(TextRulerExampleDocument doc,
          int slotIndex) {
    List<TextRulerRulePattern> result = new ArrayList<TextRulerRulePattern>();
    CAS cas = doc.getCAS();
    TypeSystem ts = cas.getTypeSystem();
    Type tokenType = ts.getType(TextRulerToolkit.TM_ALL_TYPE_NAME);
    List<TextRulerExample> examples = doc.getPositiveExamples();
    for (TextRulerExample e : examples) {
      TextRulerAnnotation slotAnnotation = e.getAnnotations()[slotIndex];
      List<AnnotationFS> theTokens = TextRulerToolkit.getAnnotationsWithinBounds(cas,
              slotAnnotation.getBegin(), slotAnnotation.getEnd(), TextRulerToolkit
                      .getFilterSetWithSlotNames(slotNames, filterSet), tokenType);
      TextRulerRulePattern thePattern = new TextRulerRulePattern();
      for (AnnotationFS afs : theTokens)
        thePattern.add(new WienRuleItem(new TextRulerAnnotation(afs, doc)));
      if (thePattern.size() > 0)
        result.add(thePattern);
    }
    return result;
  }

  protected constraint3ReturnType testConstraint3(TextRulerRulePattern h, TextRulerRulePattern t,
          TextRulerRulePattern l1) {
    for (TextRulerExampleDocument doc : exampleDocuments.getDocuments()) {
      constraint3ReturnType r = testConstraint3(doc, h, t, l1);
      if (r != constraint3ReturnType.C3_SUCCESS)
        return r;
    }
    return constraint3ReturnType.C3_SUCCESS;
  }

  protected boolean testConstraint1(TextRulerExampleDocument doc, TextRulerRulePattern rk, int k) {
    List<TextRulerRulePattern> rightContexts = getRightContextForSlot(doc, k);
    for (TextRulerRulePattern rx : rightContexts) {
      if (rx.find(rk) != 0)
        return false;
    }
    List<TextRulerRulePattern> contents = getSlotFillerPatterns(doc, k);
    for (TextRulerRulePattern c : contents) {
      if (c.find(rk) >= 0)
        return false;
    }

    return true;
  }

  protected boolean testConstraint1(TextRulerRulePattern rk, int k) {
    for (TextRulerExampleDocument doc : exampleDocuments.getDocuments()) {
      if (!testConstraint1(doc, rk, k))
        return false;
    }
    return true;
  }

  protected boolean testConstraint2(TextRulerExampleDocument doc, TextRulerRulePattern lk, int k) {
    List<TextRulerRulePattern> leftContexts = getLeftContextForSlot(doc, k);
    for (TextRulerRulePattern lx : leftContexts) {
      if (lx.size() < lk.size())
        return false;
      int pos = lx.find(lk);
      if (pos < 0 || pos != lx.size() - lk.size())
        return false;
    }
    return true;
  }

  protected boolean testConstraint2(TextRulerRulePattern lk, int k) {
    for (TextRulerExampleDocument doc : exampleDocuments.getDocuments()) {
      if (!testConstraint2(doc, lk, k))
        return false;
    }
    return true;
  }

  protected constraint3ReturnType testConstraint3(TextRulerExampleDocument doc,
          TextRulerRulePattern h, TextRulerRulePattern t, TextRulerRulePattern l1) {
    final boolean logReasons = false;

    TextRulerRulePattern head = new TextRulerRulePattern();
    TextRulerRulePattern tail = new TextRulerRulePattern();

    getPageHeadAndTailPortion(doc, head, tail);

    // 1: l1 must be a proper suffix of the portion between the end of h and
    // the first slot filler:
    // (head / h) / l1 = l1

    int hPos = head.find(h);

    // TOOD precalculate this outside this method ?
    TextRulerRulePattern restForL1 = head.subPattern(hPos + h.size(), -1).copy();
    for (TextRulerRuleItem it : restForL1)
      ((WienRuleItem) it).getWordConstraint().setGeneralizeLinkMarkUp(true);
    int l1Pos = restForL1.find(l1);
    if (l1Pos < 0 || l1Pos != restForL1.size() - l1.size()) {
      TextRulerToolkit.logIf(logReasons, "REASON 1\n\tl1         \t" + l1 + "\n\trestforl1\t"
              + restForL1);
      return constraint3ReturnType.C3_L1CandidateSuffixError;
    }

    // 2: t must not occur in the subpattern after h and before l1
    if (l1Pos > 0) {
      TextRulerRulePattern patternBetweenHandL1 = restForL1.subPattern(0, l1Pos);
      if (patternBetweenHandL1.size() >= t.size()) {
        if (patternBetweenHandL1.find(t) >= 0) {
          TextRulerToolkit.logIf(logReasons, "REASON 2");
          return constraint3ReturnType.C3_TailCandidateH_L1Error;
        }
      }
    }

    // 2a: addons, not specified in WIEN paper !!
    TextRulerRulePattern lastSlotRightPattern = patternPairs.get(slotNames.length - 1).r;
    if (t.find(lastSlotRightPattern) == 0) // the right boundary of the last
    // slot may not be part of the
    // tail pattern!
    {
      TextRulerToolkit.logIf(logReasons, "REASON 3: " + lastSlotRightPattern + "\tTail: " + t);
      return constraint3ReturnType.C3_TailCandidateRK_PrefixError;
    }

    int tPos = tail.find(t);
    if (tPos < 0) {
      TextRulerToolkit.logIf(logReasons, "REASON 4");
      return constraint3ReturnType.C3_TailCandidateNotFoundError;
    } // this is an own constraint definition: if a document does not have
    // the tail in it,
    // what should we do then ? is this a n error or is this okay since the
    // document may not have any tail after the data ?

    // 3: l1 must not precede t in the page's tail:
    int l1tPos = tail.find(l1);
    if (l1tPos >= 0) // l1 occurs in the page's tail:
    {
      if (l1tPos < tPos) {
        TextRulerToolkit.logIf(logReasons, "REASON 5");
        return constraint3ReturnType.C3_TailCandidateSucceedsL1InTailError;
      }
    }

    List<TextRulerRulePattern> interTupleSeparators = getInterTupleSepatators(doc);

    for (TextRulerRulePattern itSep : interTupleSeparators) {
      // 4: l1 must be a proper suffix of each of the inter-tuple
      // separators:
      TextRulerRulePattern itSepCopy = itSep.copy();
      for (TextRulerRuleItem it : itSepCopy)
        ((WienRuleItem) it).getWordConstraint().setGeneralizeLinkMarkUp(true);
      int l1itsPos = itSepCopy.find(l1);
      if (l1itsPos < 0 || l1itsPos != itSepCopy.size() - l1.size()) {
        TextRulerToolkit.logIf(logReasons, "REASON 6: \n\tl1\t" + l1 + "\n\titSep\t" + itSep);
        return constraint3ReturnType.C3_L1CandidateInterTupleSeparatorSuffixError;
      }

      // 5: t must never precede l1 in any inter-tuple separator:
      int itstPos = itSep.find(t);
      if (itstPos >= 0 && itstPos < l1itsPos) {
        TextRulerToolkit.logIf(logReasons, "REASON 7");
        return constraint3ReturnType.C3_TailCandidatePrecedesL1InterTupleSeparatorError;
      }

    }
    return constraint3ReturnType.C3_SUCCESS;
  }

  public String getResultString() {
    if (theRule == null)
      return "<no results yet>";
    String result = getTMFileHeaderString() + "DECLARE wien_tail;\n" + "DECLARE wien_rulemark;\n"
            + "DECLARE wien_content;\n" + "BOOLEAN wien_redo;\n\n"
            + "// tail/head/content area stuff:\n";

    TextRulerRulePattern hCopy = hPattern.copy();

    ((WienRuleItem) hCopy.get(0)).addCondition("-PARTOF(wien_content)");
    result += hCopy + " ALL*?{->MARK(wien_content)};\n";

    TextRulerRulePattern tCopy = tPattern.copy();
    ((WienRuleItem) tCopy.get(0)).addCondition("PARTOF(wien_content)");

    result += tCopy + "{->MARK(wien_tail";
    if (tPattern.size() > 1)
      result += ", 1, " + tPattern.size();
    result += ")};\n\n";

    result += "BLOCK(findData) wien_content {\n"
            + "\t// find out if tail is before the next occurence of l1\n"
            + "\t"
            + theRule.getRuleString()
            + "\n"
            + "\tDocument{->ASSIGN(wien_redo, false)};\n"
            + "\twien_tail{PARTOF(wien_rulemark)->UNMARK(wien_tail), ASSIGN(wien_redo, true)}; // remove tail marks that are no longer relevant for us after the last rule !\n"
            + "\tDocument{IF(wien_redo)->CALL(filename.findData)};\n" + "}\n";

    result += "\n// cleaning up:\n" + "wien_tail{->UNMARK(wien_tail)};\n"
            + "wien_rulemark{->UNMARK(wien_rulemark)};\n"
            + "wien_content{->UNMARK(wien_content)};\n";
    return result;
  }

  public void setParameters(Map<String, Object> params) {
  }

}
