package org.apache.uima.tm.textruler.rapier;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class RapierRulePriorityQueue implements Iterable<RapierRule> {

  private PriorityQueue<RapierRule> ruleList;

  private PriorityQueue<RapierRule> reverseRuleList;

  private int maxSize;

  public RapierRulePriorityQueue(int maxSize) {
    this.maxSize = maxSize;

    ruleList = new PriorityQueue<RapierRule>(maxSize, new Comparator<RapierRule>() {
      public int compare(RapierRule o1, RapierRule o2) {
        if (o1.getPriority() > o2.getPriority())
          return 1;
        else if (o1.getPriority() < o2.getPriority())
          return -1;
        else
          return 0;
      }
    });
    this.reverseRuleList = new PriorityQueue<RapierRule>(maxSize, new Comparator<RapierRule>() {
      public int compare(RapierRule o1, RapierRule o2) {
        if (o1.getPriority() < o2.getPriority())
          return 1;
        else if (o1.getPriority() > o2.getPriority())
          return -1;
        else
          return 0;
      }
    });
  }

  public Iterator<RapierRule> iterator() {
    return ruleList.iterator();
  }

  public void clear() {
    ruleList.clear();
    reverseRuleList.clear();
  }

  public void addAll(Collection<RapierRule> rules) {
    for (RapierRule r : rules)
      add(r);
  }

  public void add(RapierRule rule) {
    ruleList.add(rule);
    reverseRuleList.add(rule);
    while (ruleList.size() > maxSize) {
      Object tail = reverseRuleList.peek();
      ruleList.remove(tail);
      reverseRuleList.remove(tail);
    }
    // TextRulerToolkit.log("ADD TO RULE LIST, SIZE="+ruleList.size()+"    revSize="+reverseRuleList.size());
    // for (RapierRule r : ruleList)
    // {
    // TextRulerToolkit.log("Rule Value: "+r.getPriority()+"  peek: "+ruleList.peek().getPriority());
    // }
  }

  public RapierRule peek() {
    return ruleList.peek();
  }

}
