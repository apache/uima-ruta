package org.apache.uima.tm.dltk.parser.ast.declarations;

import org.eclipse.dltk.ast.declarations.Declaration;
import org.eclipse.dltk.ast.references.SimpleReference;

public class TextMarkerPackageDeclaration extends Declaration {
  SimpleReference ref;

  public TextMarkerPackageDeclaration(int start, int end, SimpleReference ref) {
    super(start, end);
    this.setName(ref.getName());
    this.setNameStart(ref.sourceStart());
    this.setNameEnd(ref.sourceEnd());
  }
}
