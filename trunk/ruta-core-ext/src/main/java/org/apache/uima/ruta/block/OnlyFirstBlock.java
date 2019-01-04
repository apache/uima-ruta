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
import org.apache.uima.ruta.rule.RuleApply;
import org.apache.uima.ruta.rule.RuleMatch;
import org.apache.uima.ruta.rule.RutaRuleElement;
import org.apache.uima.ruta.visitor.InferenceCrowd;

/**
 * A block construct in UIMA Ruta, which stops applying its elements after the first successful
 * statement
 * 
 */
public class OnlyFirstBlock extends RutaBlock {

  public OnlyFirstBlock(RutaBlock parent, String defaultNamespace) {
    super(parent, defaultNamespace, parent != null ? parent.getContext() : null);
  }

  @Override
  public ScriptApply apply(RutaStream stream, InferenceCrowd crowd) {
    BlockApply result = new BlockApply(this);
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
        boolean stop = false;
        Type type = ((RutaRuleElement) rule.getRuleElements().get(0)).getMatcher()
                .getType(getParent() == null ? this : getParent(), stream);
          RutaStream window = stream.getWindowStream(each, type);
          for (RutaStatement element : getElements()) {
            if (stop)
              break;
            if (element != null) {
              ScriptApply elementApply = element.apply(window, crowd);
              if (elementApply instanceof BlockApply) {
                BlockApply ba = (BlockApply) elementApply;
                if (ba.getRuleApply().getApplied() > 0) {
                  stop = true;
                }
              } else if (elementApply instanceof RuleApply) {
                RuleApply ra = (RuleApply) elementApply;
                if (ra.getApplied() > 0) {
                  stop = true;
                }
              }
            }
        }
      }
    }
    crowd.endVisit(this, result);
    return result;
  }

}
