package org.apache.uima.tm.textmarker.kernel.rule;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.tm.textmarker.kernel.ScriptApply;


public class RuleApply extends ScriptApply {

  private int tried = 0;

  private int applied = 0;

  private boolean acceptMatches;

  private List<RuleMatch> list;

  public RuleApply(TextMarkerRule rule) {
    this(rule, false);
  }

  public RuleApply(TextMarkerRule rule, boolean remember) {
    super(rule);
    list = new ArrayList<RuleMatch>();
    acceptMatches = remember;
  }

  public void add(RuleMatch match) {
    if (match.matched()) {
      applied++;
    }
    tried++;
    if (acceptMatches) {
      list.add(match);
    }
  }

  public List<RuleMatch> getList() {
    return list;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(((TextMarkerRule) getElement()).getId() + "\t");
    sb.append(applied + "/" + tried + ":\t");
    sb.append(getElement().toString());
    return sb.toString();
  }

  public int getTried() {
    return tried;
  }

  public int getApplied() {
    return applied;
  }

  public boolean isAcceptMatches() {
    return acceptMatches;
  }

  public void setAcceptMatches(boolean acceptMatches) {
    this.acceptMatches = acceptMatches;
  }
}
