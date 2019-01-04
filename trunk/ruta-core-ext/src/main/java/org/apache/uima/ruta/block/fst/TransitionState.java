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


package org.apache.uima.ruta.block.fst;

import java.util.LinkedList;

import org.apache.uima.ruta.RutaStatement;
import org.apache.uima.ruta.rule.RuleElement;

public class TransitionState extends AbstractState {
  
  private LinkedList<RutaStatement> isPartOfRules;
  
  public TransitionState(int depth) {
    super(depth);
    this.isPartOfRules = new LinkedList<RutaStatement>();
  }
  
  @Override
  public AbstractState getTransition(RuleElement elem) {
    return this.transitions.get(elem);
  }
  
  public void addRule(RutaStatement statement) {
    this.isPartOfRules.add(statement);
  }
  
  public LinkedList<RutaStatement> getRules() {
    return this.isPartOfRules;
  }
  
  @Override
  public String toString() {
    return "TransitionState";
  }

}
