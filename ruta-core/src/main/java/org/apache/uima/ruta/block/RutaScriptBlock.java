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

package org.apache.uima.ruta.block;

import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStatement;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.ScriptApply;
import org.apache.uima.ruta.rule.AbstractRule;
import org.apache.uima.ruta.rule.AbstractRuleMatch;
import org.apache.uima.ruta.rule.MatchContext;
import org.apache.uima.ruta.rule.RuleApply;
import org.apache.uima.ruta.rule.RuleMatch;
import org.apache.uima.ruta.rule.RutaMatcher;
import org.apache.uima.ruta.rule.RutaRule;
import org.apache.uima.ruta.rule.RutaRuleElement;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class RutaScriptBlock extends RutaBlock {

  public RutaScriptBlock(String id, RutaRule rule, List<RutaStatement> elements, RutaBlock parent,
          String defaultNamespace) {
    super(id, rule, elements, parent, defaultNamespace,
            parent != null ? parent.getContext() : null);
  }

  @Override
  public ScriptApply apply(RutaStream stream, InferenceCrowd crowd) {
    BlockApply result = new BlockApply(this);
    getEnvironment().ensureMaterializedInitialValues(new MatchContext(this), stream);
    crowd.beginVisit(this, result);
    RuleApply apply = rule.apply(stream, crowd, true);
    for (AbstractRuleMatch<? extends AbstractRule> eachMatch : apply.getList()) {
      if (eachMatch.matched()) {
        List<AnnotationFS> matchedAnnotations = ((RuleMatch) eachMatch).getMatchedAnnotations(null,
                null);
        if (matchedAnnotations == null || matchedAnnotations.isEmpty()) {
          continue;
        }
        AnnotationFS each = matchedAnnotations.get(0);
        if (each == null) {
          continue;
        }
        // TODO refactor!!
        RutaMatcher matcher = ((RutaRuleElement) rule.getRuleElements().get(0)).getMatcher();
        if (matcher == null) {
          continue;
        }

        Type type = matcher.getType(getParent() == null ? this : getParent(), stream);
        RutaStream window = stream.getWindowStream(each, type);
        for (RutaStatement element : getElements()) {
          if (element != null) {
            element.apply(window, crowd);
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
    int elementSize = elements == null ? 0 : elements.size();
    return "BLOCK(" + name + ") " + ruleString + " containing " + elementSize + " Elements";
  }

}
