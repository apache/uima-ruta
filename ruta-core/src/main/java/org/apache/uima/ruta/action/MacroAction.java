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

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaEnvironment;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.IRutaExpression;
import org.apache.uima.ruta.rule.MatchContext;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class MacroAction extends AbstractRutaAction {

  private final String name;

  private final Map<String, String> definition;

  private final List<AbstractRutaAction> actions;

  private final Set<String> vars;

  private final List<IRutaExpression> arguments;

  public MacroAction(String name, Map<String, String> definition, List<AbstractRutaAction> actions,
          Set<String> vars, List<IRutaExpression> arguments) {
    super();
    this.name = name;
    this.definition = definition;
    this.actions = actions;
    this.vars = vars;
    this.arguments = arguments;
  }

  @Override
  public void execute(MatchContext context, RutaStream stream, InferenceCrowd crowd) {
    prepareEnvironment(context, stream);
    for (AbstractRutaAction eachAction : actions) {
      crowd.beginVisit(eachAction, null);
      eachAction.execute(context, stream, crowd);
      crowd.endVisit(eachAction, null);
    }
    cleanupEnvironment(context, stream);
  }

  private void prepareEnvironment(MatchContext context, RutaStream stream) {
    RutaBlock parent = context.getParent();
    RutaEnvironment environment = parent.getEnvironment();
    Set<Entry<String, String>> entrySet = definition.entrySet();
    int index = 0;
    for (Entry<String, String> entry : entrySet) {
      String name = entry.getKey();
      String type = entry.getValue();
      IRutaExpression expression = arguments.get(index);
      if (!vars.contains(name)) {
        environment.addVariable(name, type);
        stream.assignVariable(name, expression, context);
      } else {
        String var = environment.getVariableNameOfExpression(expression);
        environment.addAliasVariable(name, var);
      }
      index++;
    }
  }

  private void cleanupEnvironment(MatchContext context, RutaStream stream) {
    RutaBlock parent = context.getParent();
    RutaEnvironment environment = parent.getEnvironment();
    Set<Entry<String, String>> entrySet = definition.entrySet();
    for (Entry<String, String> entry : entrySet) {
      String name = entry.getKey();
      if (!vars.contains(name)) {
        environment.removeVariable(name);
      } else {
        environment.removeAliasVariable(name);
      }
    }
  }

  public String getName() {
    return name;
  }

  public Map<String, String> getDefinition() {
    return definition;
  }

  public List<AbstractRutaAction> getActions() {
    return actions;
  }

  public List<IRutaExpression> getArguments() {
    return arguments;
  }

}
