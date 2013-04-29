package org.apache.uima.ruta.rule;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.ruta.ScriptApply;
import org.apache.uima.ruta.RutaStatement;

public class RuleApply extends ScriptApply {

  private List<AbstractRuleMatch<? extends AbstractRule>> list;
  
  protected int tried = 0;

  protected int applied = 0;
  
  private boolean acceptMatches;
  
  public RuleApply(RutaStatement tme, boolean remember) {
    super(tme);
    list = new ArrayList<AbstractRuleMatch<? extends AbstractRule>>();
    this.acceptMatches = remember;
  }

  public List<AbstractRuleMatch<? extends AbstractRule>> getList() {
    return list;
  }

  public void add(AbstractRuleMatch<? extends AbstractRule> match) {
    if (match.matchedCompletely()) {
      applied++;
    }
    tried++;
    if (acceptMatches) {
      list.add(match);
    }
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
  
  public void addAll(List<RuleMatch> matches) {
    for (RuleMatch ruleMatch : matches) {
      add(ruleMatch);
    }
  }
  
}
