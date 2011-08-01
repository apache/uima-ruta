package org.apache.uima.tm.textruler.lp2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

import org.apache.uima.tm.textruler.core.TextRulerToolkit;

public class LP2CurrentBestRulesQueue implements Iterable<LP2Rule> {

  private PriorityQueue<LP2Rule> ruleList;

  private PriorityQueue<LP2Rule> reverseRuleList;

  private int maxSize;

  private static int cmpRules(LP2Rule o1, LP2Rule o2) {
    // 1st criterion: sort by decreasing number of positive matches:
    if (o1.getCoveringStatistics().getCoveredPositivesCount() > o2.getCoveringStatistics()
            .getCoveredPositivesCount())
      return -1;
    else if (o1.getCoveringStatistics().getCoveredPositivesCount() < o2.getCoveringStatistics()
            .getCoveredPositivesCount())
      return 1;
    else {
      // 2nd criterion: sort by increasing error rate:
      if (o1.getErrorRate() < o2.getErrorRate())
        return -1;
      else if (o1.getErrorRate() > o2.getErrorRate())
        return 1;
      else {
        // 3rd criterion:
        // TODO:
        // if one rule has more positive matches than a threshold then
        // prefere the one with more generic
        // conditions; else prefer the other one.

        // test for now: prefer more general rules !
        int c1 = o1.totalConstraintCount();
        int c2 = o2.totalConstraintCount();
        if (c1 < c2)
          return -1;
        else if (c1 > c2)
          return 1;
        else {
          return o1.getRuleString().compareTo(o2.getRuleString());
        }
      }
    }

  }

  public LP2CurrentBestRulesQueue(int maxSize) {
    this.maxSize = Math.max(1, maxSize);

    ruleList = new PriorityQueue<LP2Rule>(this.maxSize, new Comparator<LP2Rule>() {
      public int compare(LP2Rule o1, LP2Rule o2) {
        return cmpRules(o1, o2);
      }
    });
    this.reverseRuleList = new PriorityQueue<LP2Rule>(this.maxSize, new Comparator<LP2Rule>() {
      public int compare(LP2Rule o1, LP2Rule o2) {
        return -cmpRules(o1, o2);
      }
    });
  }

  public Iterator<LP2Rule> iterator() {
    return ruleList.iterator();
  }

  public void clear() {
    ruleList.clear();
    reverseRuleList.clear();
  }

  public void addAll(Collection<LP2Rule> rules) {
    for (LP2Rule r : rules)
      add(r);
  }

  public void add(LP2Rule rule) {
    ruleList.add(rule);
    reverseRuleList.add(rule);
    // TextRulerToolkit.log("ADD TO RULE LIST, SIZE="+ruleList.size()+"    revSize="+reverseRuleList.size());
    // for (LP2Rule r : ruleList)
    // {
    // TextRulerToolkit.log("Rule Value: "+r.getPriority()+"  peek: "+ruleList.peek().getPriority());
    // }
  }

  public boolean contains(LP2Rule rule) {
    return ruleList.contains(rule);
  }

  // returns the removed objects
  public Collection<LP2Rule> cutToMaxSize() {
    ArrayList<LP2Rule> result = new ArrayList<LP2Rule>();
    while (ruleList.size() > maxSize) {
      Object tail = reverseRuleList.peek();
      ruleList.remove(tail);
      reverseRuleList.remove(tail);
      result.add((LP2Rule) tail);
    }
    return result;
  }

  public LP2Rule peek() {
    return ruleList.peek();
  }

  public void remove(LP2Rule r) {
    ruleList.remove(r);
    reverseRuleList.remove(r);
  }

  public LP2Rule[] toArray() {
    LP2Rule[] result = new LP2Rule[ruleList.size()];
    int i = 0;
    for (LP2Rule r : ruleList) {
      result[i] = r;
      i++;
    }
    return result;
  }

  protected void removeSubsumedRules() {
    ArrayList<LP2Rule> removeList = new ArrayList<LP2Rule>();
    LP2Rule[] rulesArray = toArray();
    for (int index1 = 0; index1 < rulesArray.length - 1; index1++) {
      LP2Rule rule1 = rulesArray[index1];
      for (int index2 = index1 + 1; index2 < rulesArray.length; index2++) {
        LP2Rule rule2 = rulesArray[index2];
        if (rule1.getCoveringStatistics().getCoveredPositiveExamples().containsAll(
                rule2.getCoveringStatistics().getCoveredPositiveExamples()))
          removeList.add(rule2);
      }

    }
    // if (TextRulerToolkit.DEBUG && removeList.size() > 0)
    // TextRulerToolkit.log("[removeSubsumedRules] REMOVED "+removeList.size()+" RULES");
    for (LP2Rule r : removeList)
      remove(r);
  }

  public void printDebug() {
    TextRulerToolkit.log("-------CURRENT BEST RULES:");
    for (LP2Rule r : ruleList) {
      TextRulerToolkit.log(r.getRuleString() + " " + r.getCoveringStatistics() + "  error="
              + r.getErrorRate() + "  constraints=" + r.totalConstraintCount());
    }
  }

  public int size() {
    return ruleList.size();
  }
}
