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

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.uima.ruta.rule.RuleElement;

public abstract class AbstractState {

  protected LinkedList<RuleElement> possibleTransitions;

  protected HashMap<RuleElement, AbstractState> transitions;

  protected int depth;

  public AbstractState(int depth) {
    this.transitions = new HashMap<RuleElement, AbstractState>();
    this.possibleTransitions = new LinkedList<RuleElement>();
    this.depth = depth;
  }

  public abstract AbstractState getTransition(RuleElement elem);

  public int getDepth() {
    return this.depth;
  }

  public void addTransition(RuleElement element, AbstractState targetState) {
    this.possibleTransitions.add(element);
    this.transitions.put(element, targetState);
  }

  public LinkedList<RuleElement> getPossibleTransitions() {
    return this.possibleTransitions;
  }

}
