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

package org.apache.uima.textmarker;

import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.textmarker.rule.RuleApply;
import org.apache.uima.textmarker.rule.RuleMatch;
import org.apache.uima.textmarker.rule.TextMarkerRule;
import org.apache.uima.textmarker.rule.TextMarkerRuleElement;
import org.apache.uima.textmarker.visitor.InferenceCrowd;

public class TextMarkerAutomataBlock extends TextMarkerBlock {

  public TextMarkerAutomataBlock(String id, TextMarkerRule rule,
          List<TextMarkerStatement> elements, TextMarkerBlock parent, String defaultNamespace) {
    super(id, rule, elements, parent, defaultNamespace);
  }

  @Override
  public ScriptApply apply(TextMarkerStream stream, InferenceCrowd crowd) {
    BlockApply result = new BlockApply(this);
    crowd.beginVisit(this, result);
    RuleApply apply = rule.apply(stream, crowd, true);
    for (RuleMatch eachMatch : apply.getList()) {
      if (eachMatch.matched()) {
        AnnotationFS each = eachMatch.getMatchedAnnotations(stream, null, null).get(0);
        if (each == null) {
          continue;
        }
        List<Type> types = ((TextMarkerRuleElement) rule.getRuleElements().get(0)).getMatcher()
                .getTypes(getParent(), stream);
        for (Type eachType : types) {
          TextMarkerStream window = stream.getWindowStream(each, eachType);
          for (TextMarkerStatement element : getElements()) {
            if (element != null) {
              element.apply(window, crowd);
            }
          }
        }
      }
    }
    crowd.endVisit(this, result);
    return result;
  }

  @Override
  public String toString() {
    String ruleString = rule == null ? "Document" : rule.toString();
    return "RULES(" + id + ") " + ruleString + " containing " + elements.size() + " Elements";
  }

  public void setMatchRule(TextMarkerRule rule) {
    this.rule = rule;
  }

}
