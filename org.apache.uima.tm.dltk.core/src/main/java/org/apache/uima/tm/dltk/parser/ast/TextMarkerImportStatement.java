package org.apache.uima.tm.dltk.parser.ast;

import org.eclipse.dltk.ast.Modifiers;
import org.eclipse.dltk.ast.references.SimpleReference;

public class TextMarkerImportStatement extends TextMarkerSimpleStatement {
  private int type;

  public TextMarkerImportStatement(int sourceStart, int sourceEnd, SimpleReference importRef,
          int type) {
    super(sourceStart, sourceEnd, importRef);
    this.type = type;
  }

  @Override
  public int getKind() {
    return Modifiers.AccNameSpace;
  }

  /**
   * See {@link TMStatementConstants}
   * 
   * @return
   */
  public int getType() {
    return this.type;
  }

}
