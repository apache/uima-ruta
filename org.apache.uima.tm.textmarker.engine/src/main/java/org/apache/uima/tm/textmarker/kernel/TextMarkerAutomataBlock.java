package org.apache.uima.tm.textmarker.kernel;

import java.util.List;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.textmarker.kernel.rule.RuleApply;
import org.apache.uima.tm.textmarker.kernel.rule.RuleMatch;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRule;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class TextMarkerAutomataBlock extends TextMarkerBlock {

  public TextMarkerAutomataBlock(String id, TextMarkerRule rule, List<TextMarkerStatement> elements,
          TextMarkerBlock parent, String defaultNamespace, CAS cas) {
    super(id, rule, elements, parent, defaultNamespace, cas);
  }

  @Override
  public ScriptApply apply(TextMarkerStream stream, InferenceCrowd crowd) {
    BlockApply result = new BlockApply(this);
    crowd.beginVisit(this, result);
    RuleApply apply = rule.apply(stream, crowd, true);
    for (RuleMatch eachMatch : apply.getList()) {
      if (eachMatch.matched()) {
        AnnotationFS each = eachMatch.getMatchedAnnotation(stream, null);
        if (each == null) {
          continue;
        }
        Type type = rule.getElements().get(0).getMatcher().getType(getParent(), stream);
        TextMarkerStream window = stream.getWindowStream(each, type);
        for (TextMarkerStatement element : getElements()) {
          if (element != null) {
            element.apply(window, crowd);
          }
        }
      }
    }
    crowd.endVisit(this, result);
    return result;
  }

  @Override
  public String toString() {
    String ruleString = rule == null ? "Document" : rule.toString();
    return "BLOCK(" + id + ") " + ruleString + " containing " + elements.size() + " Elements";
  }

  public void setMatchRule(TextMarkerRule rule) {
    this.rule = rule;
  }

}
