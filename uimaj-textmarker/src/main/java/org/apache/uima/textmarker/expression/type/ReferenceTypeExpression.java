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

package org.apache.uima.textmarker.expression.type;

import org.apache.uima.cas.Type;
import org.apache.uima.textmarker.TextMarkerStatement;

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

  @Override
  public Type getType(TextMarkerStatement parent) {
    return parent.getEnvironment().getVariableValue(var, Type.class);
  }

  @Override
  public String getStringValue(TextMarkerStatement parent) {
    Type type = getType(parent);
    return type != null ? type.getName() : "null";
  }

}
