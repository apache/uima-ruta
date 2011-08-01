package org.apache.uima.tm.dltk.internal.core.builder;

public class StringTriple {

  private final String name;

  private final String description;

  private final String parent;

  public StringTriple(String name, String description, String parent) {
    super();
    this.name = name;
    this.description = description;
    this.parent = parent;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getParent() {
    return parent;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this)
      return true;
    if (!(obj instanceof StringTriple)) {
      return false;
    }
    StringTriple t = (StringTriple) obj;
    return name.equals(t.getName()) && parent.equals(t.getParent());
  }

  @Override
  public int hashCode() {
    return name.hashCode() + parent.hashCode() * 37;
  }
}