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

package org.apache.uima.ruta.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.BlockApply;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaElement;
import org.apache.uima.ruta.RutaModule;
import org.apache.uima.ruta.RutaStatement;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.ScriptApply;
import org.apache.uima.ruta.rule.AbstractRule;
import org.apache.uima.ruta.rule.AbstractRuleMatch;
import org.apache.uima.ruta.rule.RuleApply;
import org.apache.uima.ruta.rule.RutaRule;
import org.apache.uima.ruta.type.DebugScriptApply;
import org.apache.uima.ruta.verbalize.RutaVerbalizer;

public class DebugInfoCollectorVisitor implements RutaInferenceVisitor {

  private boolean createDebugInfo;

  private List<String> ids;

  private DebugInfoFactory debugFactory;

  private boolean withMatches;

  private ScriptApply rootApply;

  private Map<RutaStatement, Stack<ScriptApply>> applies;

  private Stack<RutaElement> callStack;

  public DebugInfoCollectorVisitor(boolean createDebugInfo, boolean withMatches, List<String> ids,
          RutaVerbalizer verbalizer) {
    super();
    this.createDebugInfo = createDebugInfo;
    this.withMatches = withMatches;
    this.ids = ids;

    debugFactory = new DebugInfoFactory(verbalizer);
    applies = new HashMap<RutaStatement, Stack<ScriptApply>>();
    callStack = new Stack<RutaElement>();
  }

  public DebugInfoCollectorVisitor(boolean createDebugInfo) {
    super();
    this.createDebugInfo = createDebugInfo;
    this.ids = new ArrayList<String>();
  }

  public boolean isCreateDebugInfo() {
    return createDebugInfo;
  }

  public boolean createDebugInfo(RutaRule rule) {
    return createDebugInfo || ids.contains("" + rule.getId());
  }

  public void beginVisit(RutaElement element, ScriptApply result) {
    if (element instanceof RutaStatement) {
      callStack.push(element);
      RutaStatement stmt = (RutaStatement) element;
      Stack<ScriptApply> stack = applies.get(stmt);
      if (stack == null) {
        stack = new Stack<ScriptApply>();
        applies.put(stmt, stack);
      }
      stack.push(result);
      applies.put(stmt, stack);
      if (result instanceof RuleApply) {
        RuleApply ra = (RuleApply) result;
        ra.setAcceptMatches(ra.isAcceptMatches() || withMatches);
      }
    }
  }

  public void endVisit(RutaElement element, ScriptApply result) {
    // TODO create UIMA stuff here not later -> save memory!
    if (element instanceof RutaStatement) {
      RutaStatement stmt = (RutaStatement) element;
      RutaBlock parent = stmt.getParent();
      Stack<ScriptApply> stack = applies.get(stmt);
      if (stack == null) {
        stack = new Stack<ScriptApply>();
        applies.put(stmt, stack);
      }
      // replace
      if (!stack.isEmpty()) {
        stack.pop();
      }
      stack.push(result);
      applies.put(stmt, stack);
      if (parent != null) {
        Stack<ScriptApply> parentStack = applies.get(parent);
        if (parentStack != null && !parentStack.isEmpty()) {
          ScriptApply parentApply = parentStack.peek();
          if (parentApply instanceof BlockApply) {
            BlockApply blockApply = (BlockApply) parentApply;
            if (element instanceof RutaRule && parent.getRule().equals(element)
                    && result instanceof RuleApply) {
              blockApply.setRuleApply((RuleApply) result);

              // } else {
              // blockApply.add(result);
              // }

            } else if (stack.size() == 1) {
              if (callStack.size() > 1) {
                // TODO hotfixed
                RutaElement tme = callStack.get(callStack.size() - 2);
                if (tme.equals(parent)
                // || tme.equals(element)
                ) {
                  blockApply.add(result);
                } else {
                  // TODO too many blocks added
                }
              } else {
                blockApply.add(result);

              }
            } else {
              // TODO refactor !!! ... really!!!!
              RutaElement tme = callStack.get(callStack.size() - 2);
              if (tme.equals(parent)
              // || tme.equals(element)
              ) {
                blockApply.add(result);
              } else {
                // TODO too many blocks added
              }
            }
          }
        }
      }
      stack.pop();
      callStack.pop();
    }
    if (element instanceof RutaModule) {
      rootApply = result;
    }
  }

  public void finished(RutaStream stream, List<RutaInferenceVisitor> visitors) {
    if (createDebugInfo) {
      Map<RutaElement, Long> timeInfo = getTimeInfo(visitors);

      DebugScriptApply debugScriptApply = debugFactory.createDebugScriptApply(rootApply, stream,
              false, withMatches, timeInfo);
      debugScriptApply.addToIndexes();
    }
  }

  private Map<RutaElement, Long> getTimeInfo(List<RutaInferenceVisitor> visitors) {
    for (RutaInferenceVisitor each : visitors) {
      if (each instanceof TimeProfilerVisitor) {
        return ((TimeProfilerVisitor) each).getTimeInfo();
      }
    }
    return null;
  }

  public void annotationAdded(AnnotationFS annotation,
          AbstractRuleMatch<? extends AbstractRule> creator) {

  }

}
