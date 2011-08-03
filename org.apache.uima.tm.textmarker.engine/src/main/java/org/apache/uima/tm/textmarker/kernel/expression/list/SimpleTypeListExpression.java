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

import org.apache.uima.cas.Type;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;
import org.apache.uima.tm.textmarker.kernel.expression.type.TypeExpression;


public class SimpleTypeListExpression extends TypeListExpression {

  private List<TypeExpression> list;

  public SimpleTypeListExpression(List<TypeExpression> list) {
    super();
    this.list = list;
  }

  @Override
  public List<Type> getList(TextMarkerStatement parent) {
    List<Type> result = new ArrayList<Type>();
    for (TypeExpression each : list) {
      result.add(each.getType(parent));
    }
    return result;
  }

  public List<TypeExpression> getList() {
    return list;
  }
}
