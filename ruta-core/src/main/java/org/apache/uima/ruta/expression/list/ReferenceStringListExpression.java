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

package org.apache.uima.ruta.expression.list;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.string.AbstractStringExpression;

public class ReferenceStringListExpression extends StringListExpression {

  private String var;

  public ReferenceStringListExpression(String var) {
    super();
    this.var = var;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<String> getList(RutaBlock parent, RutaStream stream) {
    List<Object> list = parent.getEnvironment().getVariableValue(var, List.class);
    List<String> result = new ArrayList<String>();
    for (Object each : list) {
      if (each instanceof AbstractStringExpression) {
     // TODO support arrays
        result.add(((AbstractStringExpression) each).getStringValue(parent, null, stream));
      } else if (each instanceof String) {
        result.add((String) each);
      }
    }
    return result;
  }

  public String getVar() {
    return var;
  }
}
