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

package org.apache.uima.ruta.expression.bool;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.annotation.IAnnotationExpression;
import org.apache.uima.ruta.rule.MatchContext;

public class BooleanAnnotationExpression extends AbstractBooleanExpression {

  private final IAnnotationExpression e1;

  private final String op;

  private final IAnnotationExpression e2;

  public BooleanAnnotationExpression(IAnnotationExpression e1, String op,
          IAnnotationExpression e2) {
    super();
    this.e1 = e1;
    this.op = op;
    this.e2 = e2;
  }

  @Override
  public boolean getBooleanValue(MatchContext context, RutaStream stream) {
    AnnotationFS first = getFristExpression().getAnnotation(context, stream);
    AnnotationFS second = getSecondExpression().getAnnotation(context, stream);
    return eval(first, getOperator(), second);
  }

  private boolean eval(AnnotationFS t1, String op, AnnotationFS t2) {
    if ("==".equals(op)) {
      if (t1 == null) {
        return t2 == null;
      }
      return t1.equals(t2);
    } else if ("!=".equals(op)) {
      if (t1 == null) {
        return t2 != null;
      }
      return !t1.equals(t2);
    }
    return false;
  }

  public IAnnotationExpression getFristExpression() {
    return e1;
  }

  public String getOperator() {
    return op;
  }

  public IAnnotationExpression getSecondExpression() {
    return e2;
  }

  @Override
  public String getStringValue(MatchContext context, RutaStream stream) {
    return e1.getStringValue(context, stream) + " " + op + " " + e2.getStringValue(context, stream);
  }

}
