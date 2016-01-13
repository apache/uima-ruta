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

package org.apache.uima.ruta.condition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaEnvironment;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.IRutaExpression;
import org.apache.uima.ruta.rule.EvaluatedCondition;
import org.apache.uima.ruta.rule.MatchContext;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class MacroCondition extends AbstractRutaCondition {

  private final String name;

  private final Map<String, String> definition;

  private final List<AbstractRutaCondition> conditions;

  private final List<IRutaExpression> arguments;

  public MacroCondition(String name, Map<String, String> definition, List<AbstractRutaCondition> conditions,
          List<IRutaExpression> arguments) {
    super();
    this.name = name;
    this.definition = definition;
    this.conditions = conditions;
    this.arguments = arguments;
  }

  
  @Override
  public EvaluatedCondition eval(MatchContext context, RutaStream stream, InferenceCrowd crowd) {
    boolean result = true;
    prepareEnvironment(context, stream);
    List<EvaluatedCondition> evals = new ArrayList<EvaluatedCondition>();
    for (AbstractRutaCondition each : conditions) {
      crowd.beginVisit(each, null);
      EvaluatedCondition eval = each.eval(context, stream, crowd);
      crowd.endVisit(each, null);
      result &= eval.isValue();
      evals.add(eval);
    }
    cleanupEnvironment(context, stream);
    return new EvaluatedCondition(this, result, evals);
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
      environment.addVariable(name, type);
      stream.assignVariable(name, expression, context);
      index++;
    }
  }

  private void cleanupEnvironment(MatchContext context, RutaStream stream) {
    RutaBlock parent = context.getParent();
    RutaEnvironment environment = parent.getEnvironment();
    Set<Entry<String, String>> entrySet = definition.entrySet();
    for (Entry<String, String> entry : entrySet) {
      String name = entry.getKey();
      environment.removeVariable(name);
    }
  }

  public String getName() {
    return name;
  }

  public Map<String, String> getDefinition() {
    return definition;
  }

  public List<AbstractRutaCondition> getActions() {
    return conditions;
  }

  public List<IRutaExpression> getArguments() {
    return arguments;
  }


}
