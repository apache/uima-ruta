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

package org.apache.uima.ruta.expression.list;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.IRutaExpression;
import org.apache.uima.ruta.expression.annotation.IAnnotationExpression;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.type.ITypeExpression;
import org.apache.uima.ruta.rule.MatchContext;

public class UntypedListExpression extends ListExpression<Object> {

  private List<IRutaExpression> list;

  public UntypedListExpression(List<IRutaExpression> list) {
    super();
    this.list = list;
  }

  @Override
  public List<Object> getList(MatchContext context, RutaStream stream) {
    List<Object> result = new ArrayList<Object>();
    for (IRutaExpression each : list) {
      // TODO support arrays
      if (each instanceof IBooleanExpression) {
        result.add(((IBooleanExpression) each).getBooleanValue(context, stream));
      } else if (each instanceof INumberExpression) {
        result.add(((INumberExpression) each).getDoubleValue(context, stream));
      } else if (each instanceof ITypeExpression) {
        result.add(((ITypeExpression) each).getType(context, stream));
      } else if (each instanceof IAnnotationExpression) {
        result.add(((IAnnotationExpression) each).getAnnotation(context, stream));
      } else if (each instanceof ListExpression) {
        result.add(((ListExpression<?>) each).getList(context, stream));
      } else if (each instanceof IStringExpression) {
        result.add(((IStringExpression) each).getStringValue(context, stream));
      }
    }
    return result;
  }

  public List<IRutaExpression> getList() {
    return list;
  }

}
