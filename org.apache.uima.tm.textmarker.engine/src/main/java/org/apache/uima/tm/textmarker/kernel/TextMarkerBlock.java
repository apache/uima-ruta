package org.apache.uima.tm.textmarker.kernel;

import java.util.List;

import org.apache.uima.cas.CAS;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRule;


public abstract class TextMarkerBlock extends TextMarkerStatement {

  protected final String id;

  protected TextMarkerEnvironment environment;

  protected TextMarkerRule rule;

  protected List<TextMarkerStatement> elements;

  private String namespace;

  private TextMarkerModule script;

  public TextMarkerBlock(String id, TextMarkerRule rule, List<TextMarkerStatement> elements,
          TextMarkerBlock parent, String defaultNamespace, CAS cas) {
    super(parent);
    this.id = id;
    this.rule = rule;
    this.elements = elements;
    this.environment = new TextMarkerEnvironment(cas, this);
    this.namespace = defaultNamespace;
  }

  public TextMarkerRule getRule() {
    return rule;
  }

  @Override
  public TextMarkerEnvironment getEnvironment() {
    return environment;
  }

  public List<TextMarkerStatement> getElements() {
    return elements;
  }

  public void setElements(List<TextMarkerStatement> elements) {
    this.elements = elements;
  }

  public TextMarkerModule getScript() {
    // TODO refactor!
    if (getParent() != null) {
      return getParent().getScript();
    } else {
      return script;
    }
  }

  public void setScript(TextMarkerModule script) {
    this.script = script;
  }

  public String getNamespace() {
    return namespace;
  }

  public String getId() {
    return id;
  }

}
