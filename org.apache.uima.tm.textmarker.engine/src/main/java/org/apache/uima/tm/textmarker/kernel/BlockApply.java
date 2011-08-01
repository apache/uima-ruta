package org.apache.uima.tm.textmarker.kernel;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.tm.textmarker.kernel.rule.RuleApply;


public class BlockApply extends ScriptApply {

  public BlockApply(TextMarkerStatement tme) {
    super(tme);
  }

  private List<ScriptApply> innerApplies = new ArrayList<ScriptApply>();

  private RuleApply ruleApply;

  public void add(ScriptApply apply) {
    innerApplies.add(apply);
  }

  public List<ScriptApply> getInnerApplies() {
    return innerApplies;
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    for (ScriptApply each : getInnerApplies()) {
      result.append(each.toString());
      result.append("\n");
    }
    return result.toString() + " : " + hashCode();
  }

  public RuleApply getRuleApply() {
    return ruleApply;
  }

  public void setRuleApply(RuleApply ruleApply) {
    this.ruleApply = ruleApply;
  }

}
