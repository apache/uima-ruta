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

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.block.RutaBlock;
import org.apache.uima.ruta.rule.MatchContext;

public class TypeListVariableExpression extends AbstractTypeListExpression {

  private String var;

  public TypeListVariableExpression(String var) {
    super();
    this.var = var;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Type> getList(MatchContext context, RutaStream stream) {
    RutaBlock parent = context.getParent();
    List<Object> list = parent.getEnvironment().getVariableValue(var, List.class, stream);
    List<Type> result = new ArrayList<Type>();
    for (Object each : list) {
      if (each instanceof ITypeExpression) {
        result.add(((ITypeExpression) each).getType(context, stream));
      } else if (each instanceof Type) {
        result.add((Type) each);
      } else if(each instanceof String) {
        Type type = parent.getEnvironment().getType((String) each);
        if(type != null) {
          result.add(type);
        } else {
          throw new IllegalArgumentException("Not able to resolve type: " + each + 
                  " in script " +context.getParent().getName());
        }
      }
    }
    return result;
  }

  public String getVar() {
    return var;
  }

 
}
