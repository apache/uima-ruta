package org.apache.uima.tm.dltk.parser.ast.declarations;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.references.SimpleReference;

public class TextMarkerTypeDeclaration extends TextMarkerAbstractDeclaration {

  private List<TextMarkerFeatureDeclaration> features = new ArrayList<TextMarkerFeatureDeclaration>();

  public TextMarkerTypeDeclaration(String name, int nameStart, int nameEnd, int declStart,
          int declEnd, SimpleReference ref) {
    super(name, nameStart, nameEnd, declStart, declEnd, ref);
  }

  public TextMarkerTypeDeclaration(String name, int nameStart, int nameEnd, int declStart,
          int declEnd, SimpleReference ref, List<TextMarkerFeatureDeclaration> features) {
    super(name, nameStart, nameEnd, declStart, declEnd, ref);
    this.setFeatures(features);
  }

  public void traverse(ASTVisitor visitor) throws Exception {
    if (visitor.visit(this)) {
      getRef().traverse(visitor);
      for (TextMarkerFeatureDeclaration each : getFeatures()) {
        each.traverse(visitor);
      }
      visitor.endvisit(this);
    }
  }

  public void setFeatures(List<TextMarkerFeatureDeclaration> features) {
    this.features = features;
  }

  public List<TextMarkerFeatureDeclaration> getFeatures() {
    return features;
  }
}
