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

package org.apache.uima.textmarker.condition;

import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.expression.list.TypeListExpression;
import org.apache.uima.textmarker.expression.type.TypeExpression;
import org.apache.uima.textmarker.rule.EvaluatedCondition;
import org.apache.uima.textmarker.rule.TextMarkerRuleElement;
import org.apache.uima.textmarker.type.TextMarkerBasic;
import org.apache.uima.textmarker.visitor.InferenceCrowd;

public class PartOfNeqCondition extends TypeSentiveCondition {

  public PartOfNeqCondition(TypeExpression type) {
    super(type);
  }

  public PartOfNeqCondition(TypeListExpression list) {
    super(list);
  }

  @Override
  public EvaluatedCondition eval(TextMarkerBasic basic, Type matchedType,
          TextMarkerRuleElement element, TextMarkerStream stream, InferenceCrowd crowd) {
    if (!isWorkingOnList()) {
      Type t = type.getType(element.getParent());
      boolean result = check(basic, matchedType, stream, t);
      return new EvaluatedCondition(this, result);
    } else {
      boolean result = false;
      List<Type> types = getList().getList(element.getParent());
      for (Type t : types) {
        result |= check(basic, matchedType, stream, t);
        if (result == true) {
          break;
        }
      }
      return new EvaluatedCondition(this, result);
    }
  }

  private boolean check(TextMarkerBasic basic, Type matchedType, TextMarkerStream stream, Type t) {
    AnnotationFS expandAnchor = stream.expandAnchor(basic, matchedType);
    stream.moveTo(basic);
    while (stream.isValid()) {
      TextMarkerBasic each = (TextMarkerBasic) stream.get();
      AnnotationFS afs = each.getType(t.getName());
      if (afs != null
              && afs.getType().equals(t)
              && ((afs.getBegin() < expandAnchor.getBegin() && afs.getEnd() > expandAnchor.getEnd())
                      || (afs.getBegin() == expandAnchor.getBegin() && afs.getEnd() > expandAnchor
                              .getEnd()) || (afs.getBegin() < expandAnchor.getBegin() && afs
                      .getEnd() == expandAnchor.getEnd()))) {
        return true;
      }
      stream.moveToPrevious();
    }
    return false;
  }

}
