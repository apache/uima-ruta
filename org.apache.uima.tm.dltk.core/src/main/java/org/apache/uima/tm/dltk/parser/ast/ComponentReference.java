package org.apache.uima.tm.dltk.parser.ast;

import org.eclipse.dltk.ast.references.SimpleReference;

public class ComponentReference extends SimpleReference {

  public static int SCRIPT = 1;

  public static int ENGINE = 2;

  public static int TYPESYSTEM = 3;

  private int type;

  public ComponentReference(int start, int end, String name) {
    super(start, end, name);
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }
}
