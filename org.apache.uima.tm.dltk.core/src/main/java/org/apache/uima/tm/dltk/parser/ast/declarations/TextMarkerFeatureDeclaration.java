package org.apache.uima.tm.dltk.parser.ast.declarations;

import org.eclipse.dltk.ast.references.SimpleReference;

public class TextMarkerFeatureDeclaration extends TextMarkerAbstractDeclaration {

  private String type;

  public TextMarkerFeatureDeclaration(String name, String type, int nameStart, int nameEnd,
          int declStart, int declEnd, SimpleReference ref) {
    super(name, nameStart, nameEnd, declStart, declEnd, ref);
    this.setType(type);
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

}
