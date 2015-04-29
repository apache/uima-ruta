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

package org.apache.uima.ruta.expression.resource;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.ruta.RutaStatement;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.resource.RutaWordList;

public class ExternalWordListExpression extends WordListExpression {

  private final List<IStringExpression> args;

  private final String className;

  public ExternalWordListExpression(String className, List<IStringExpression> args) {
    super();
    this.className = className;
    this.args = args;
  }

  @Override
  public RutaWordList getList(RutaStatement element) {
    List<String> argList = new ArrayList<String>();
    for (IStringExpression each : args) {
      argList.add(each.getStringValue(element.getParent(), null, null));
    }
    
    
    return null;
  }

  public List<IStringExpression> getArgs() {
    return args;
  }

  public String getClassName() {
    return className;
  }

}
