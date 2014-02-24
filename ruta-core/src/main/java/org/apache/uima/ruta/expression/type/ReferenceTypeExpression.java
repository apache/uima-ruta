/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.uima.ruta.expression.type;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;

public class ReferenceTypeExpression extends TypeExpression {

  private final String var;

  public ReferenceTypeExpression(String varString) {
    super();
    this.var = varString;
  }

  @Override
  public String toString() {
    return getVar();
  }

  public String getVar() {
    return var;
  }

  /**
   * Returns the actual type of the TypeExpression
   * 
   * @param parent - the block of the element 
   * @return annotation type
   * @throws IllegalArgumentException if the type cannot be resolved.
   */
  @Override
  public Type getType(RutaBlock parent) {
    Type type = parent.getEnvironment().getVariableValue(var, Type.class);
    if(type == null) {
      throw new IllegalArgumentException("Not able to resolve type variable: " + var);
    }
    return type;
  }

  @Override
  public String getStringValue(RutaBlock parent, AnnotationFS annotation, RutaStream stream) {
    Type type = getType(parent);
    return type != null ? type.getName() : "null";
  }

}
