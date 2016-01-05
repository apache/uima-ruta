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

package org.apache.uima.ruta.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.IRutaExpression;
import org.apache.uima.ruta.rule.MatchContext;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class ImplicitVariableAssignmentAction extends AbstractRutaAction {

  private final String var;

  private final String op;

  private final IRutaExpression arg;

  public ImplicitVariableAssignmentAction(String varString, String opString, IRutaExpression arg) {
    super();
    this.var = varString;
    this.op = opString;
    this.arg = arg;
  }

  @Override
  public void execute(MatchContext context, RutaStream stream, InferenceCrowd crowd) {
    // only normal assignment is supported right now
    if(StringUtils.equals(op, "=")) {
      stream.assignVariable(var, arg, context, stream);
    }
  }

  public String getVar() {
    return var;
  }

  public String getOp() {
    return op;
  }

  public IRutaExpression getArg() {
    return arg;
  }

}
