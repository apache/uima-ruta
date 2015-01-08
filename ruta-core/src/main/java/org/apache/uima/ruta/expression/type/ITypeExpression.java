package org.apache.uima.ruta.expression.type;

import org.apache.uima.cas.Type;
import org.apache.uima.ruta.RutaBlock;

public interface ITypeExpression {

  /**
   * Returns the actual type of the TypeExpression
   * 
   * @param parent - the block of the element 
   * @return annotation type
   * @throws IllegalArgumentException if the type cannot be resolved.
   */
  Type getType(RutaBlock parent);
  
  
}
