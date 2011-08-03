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

package org.apache.uima.tm.textmarker.kernel.expression.list;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;
import org.apache.uima.tm.textmarker.kernel.expression.bool.BooleanExpression;


public class ReferenceBooleanListExpression extends BooleanListExpression {

  private String var;

  public ReferenceBooleanListExpression(String var) {
    super();
    this.var = var;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Boolean> getList(TextMarkerStatement parent) {
    List<Object> list = parent.getEnvironment().getVariableValue(var, List.class);
    List<Boolean> result = new ArrayList<Boolean>();
    for (Object each : list) {
      if (each instanceof BooleanExpression) {
        result.add(((BooleanExpression) each).getBooleanValue(parent));
      } else if (each instanceof Boolean) {
        result.add((Boolean) each);
      }
    }
    return result;
  }

  public String getVar() {
    return var;
  }

}
