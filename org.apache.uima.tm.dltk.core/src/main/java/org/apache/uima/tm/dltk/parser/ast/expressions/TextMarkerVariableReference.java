/**
 * 
 */
package org.apache.uima.tm.dltk.parser.ast.expressions;

import org.apache.uima.tm.dltk.parser.ast.TMTypeConstants;
import org.eclipse.dltk.ast.references.VariableReference;


/**
 * @author Martin Toepfer
 * 
 */
public class TextMarkerVariableReference extends VariableReference {
  private int typeId;

  /**
   * @param start
   * @param end
   * @param name
   * @param typedId
   *          raw type id from {@link TMTypeConstants}
   */
  public TextMarkerVariableReference(int start, int end, String name, int typedId) {
    super(start, end, name);
    this.typeId = typedId;
  }

  public int getType() {
    return this.typeId;
  }

  public void setType(int type) {
    this.typeId = type;
  }
}
