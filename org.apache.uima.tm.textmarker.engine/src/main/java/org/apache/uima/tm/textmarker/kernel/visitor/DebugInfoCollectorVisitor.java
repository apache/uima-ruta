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

package org.apache.uima.tm.textmarker.kernel.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.apache.uima.tm.textmarker.kernel.BlockApply;
import org.apache.uima.tm.textmarker.kernel.ScriptApply;
import org.apache.uima.tm.textmarker.kernel.TextMarkerBlock;
import org.apache.uima.tm.textmarker.kernel.TextMarkerElement;
import org.apache.uima.tm.textmarker.kernel.TextMarkerModule;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.rule.RuleApply;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRule;
import org.apache.uima.tm.textmarker.kernel.type.DebugScriptApply;
import org.apache.uima.tm.textmarker.verbalize.TextMarkerVerbalizer;


public class DebugInfoCollectorVisitor implements TextMarkerInferenceVisitor {

  private boolean createDebugInfo;

  private List<String> ids;

  private DebugInfoFactory debugFactory;

  private boolean withMatches;

  private ScriptApply rootApply;

  private Map<TextMarkerStatement, Stack<ScriptApply>> applies;

  private Stack<TextMarkerElement> callStack;

  public DebugInfoCollectorVisitor(boolean createDebugInfo, boolean withMatches, List<String> ids,
          TextMarkerVerbalizer verbalizer) {
    super();
    this.createDebugInfo = createDebugInfo;
    this.withMatches = withMatches;
    this.ids = ids;

    debugFactory = new DebugInfoFactory(verbalizer);
    applies = new HashMap<TextMarkerStatement, Stack<ScriptApply>>();
    callStack = new Stack<TextMarkerElement>();
  }

  public DebugInfoCollectorVisitor(boolean createDebugInfo) {
    super();
    this.createDebugInfo = createDebugInfo;
    this.ids = new ArrayList<String>();
  }

  public boolean isCreateDebugInfo() {
    return createDebugInfo;
  }

  public boolean createDebugInfo(TextMarkerRule rule) {
    return createDebugInfo || ids.contains("" + rule.getId());
  }

  public void beginVisit(TextMarkerElement element, ScriptApply result) {
    if (element instanceof TextMarkerStatement) {
      callStack.push(element);
      TextMarkerStatement stmt = (TextMarkerStatement) element;
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

  public void endVisit(TextMarkerElement element, ScriptApply result) {
    // TODO create UIMA stuff here not later -> save memory!
    if (element instanceof TextMarkerStatement) {
      TextMarkerStatement stmt = (TextMarkerStatement) element;
      TextMarkerBlock parent = stmt.getParent();
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
            if (element instanceof TextMarkerRule && parent.getRule().equals(element)
                    && result instanceof RuleApply) {
              blockApply.setRuleApply((RuleApply) result);

              // } else {
              // blockApply.add(result);
              // }

            } else if (stack.size() == 1) {
              if (callStack.size() > 1) {
                // TODO hotfixed
                TextMarkerElement tme = callStack.get(callStack.size() - 2);
                if (tme.equals(parent)
                // || tme.equals(element)
                ) {
                  blockApply.add(result);
                } else {
                  // System.out.println();
                  // TODO too many blocks added
                }
              } else {
                blockApply.add(result);

              }
            } else {
              // TODO refactor !!! ... really!!!!
              TextMarkerElement tme = callStack.get(callStack.size() - 2);
              if (tme.equals(parent)
              // || tme.equals(element)
              ) {
                blockApply.add(result);
              } else {
                // System.out.println();
                // TODO too many blocks added
              }
            }
          }
        }
      }
      stack.pop();
      callStack.pop();
    }
    if (element instanceof TextMarkerModule) {
      rootApply = result;
    }
  }

  public void finished(TextMarkerStream stream, List<TextMarkerInferenceVisitor> visitors) {
    if (createDebugInfo) {
      Map<TextMarkerElement, Long> timeInfo = getTimeInfo(visitors);

      DebugScriptApply debugScriptApply = debugFactory.createDebugScriptApply(rootApply, stream,
              false, withMatches, timeInfo);
      debugScriptApply.addToIndexes();
    }
  }

  private Map<TextMarkerElement, Long> getTimeInfo(List<TextMarkerInferenceVisitor> visitors) {
    for (TextMarkerInferenceVisitor each : visitors) {
      if (each instanceof TimeProfilerVisitor) {
        return ((TimeProfilerVisitor) each).getTimeInfo();
      }
    }
    return null;
  }

}
