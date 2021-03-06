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

package org.apache.uima.ruta.ide.validator;

import java.util.Collection;
import java.util.HashSet;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.ruta.ide.parser.ast.RutaAction;
import org.apache.uima.ruta.ide.parser.ast.RutaRuleElement;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;

public class RuleElementLabelVisitor extends ASTVisitor {

  private Collection<String> labels = new HashSet<>();

  @Override
  public boolean visit(Expression s) throws Exception {
    if (s instanceof RutaRuleElement) {
      RutaRuleElement element = (RutaRuleElement) s;
      if (!StringUtils.isBlank(element.getLabel())) {
        labels.add(element.getLabel());
      }
      return true;
    } else if (s instanceof RutaAction) {
      RutaAction action = (RutaAction) s;
      if (!StringUtils.isBlank(action.getLabel())) {
        labels.add(action.getLabel());
      }
      return true;
    }
    return false;
  }

  public Collection<String> getLabels() {
    return labels;
  }

}