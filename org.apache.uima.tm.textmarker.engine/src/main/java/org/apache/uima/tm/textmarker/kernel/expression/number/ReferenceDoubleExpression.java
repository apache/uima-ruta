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

package org.apache.uima.tm.textmarker.kernel.expression.number;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;

public class ReferenceDoubleExpression extends NumberExpression {

  private final String var;

  public ReferenceDoubleExpression(String var) {
    super();
    this.var = var;
  }

  @Override
  public double getDoubleValue(TextMarkerStatement parent) {
    Object value = parent.getEnvironment().getVariableValue(getVar());
    double variableValue = 0;
    if (value instanceof Number) {
      variableValue = ((Number) value).doubleValue();
    }
    return variableValue;
  }

  @Override
  public int getIntegerValue(TextMarkerStatement parent) {
    Object value = parent.getEnvironment().getVariableValue(getVar());
    int variableValue = 0;
    if (value instanceof Number) {
      variableValue = ((Number) value).intValue();
    }
    return variableValue;
  }

  @Override
  public String getStringValue(TextMarkerStatement parent) {
    Class<?> variableType = parent.getEnvironment().getVariableType(getVar());
    if (variableType.equals(Integer.class)) {
      return "" + getIntegerValue(parent);
    } else {
      return "" + getDoubleValue(parent);
    }
  }

  public String getVar() {
    return var;
  }
}
