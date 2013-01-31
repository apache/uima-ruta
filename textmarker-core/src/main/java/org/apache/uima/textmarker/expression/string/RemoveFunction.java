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

package org.apache.uima.textmarker.expression.string;

import java.util.List;

import org.apache.uima.textmarker.TextMarkerStatement;


public class RemoveFunction extends StringFunctionExpression {

  private List<StringExpression> list;

  private String var;

  public RemoveFunction(String v, List<StringExpression> list) {
    super();
    this.var = v;
    this.list = list;
  }

  @Override
  public String getStringValue(TextMarkerStatement parent) {
    StringBuilder result = new StringBuilder();
    String value = parent.getEnvironment().getVariableValue(var, String.class);
    for (StringExpression each : list) {
      String string = each.getStringValue(parent);
      String[] split = value.split(string);
      for (String r : split) {
        result.append(r);
      }
    }
    return result.toString();
  }

  public List<StringExpression> getList() {
    return list;
  }

  public String getVar() {
    return var;
  }

}
