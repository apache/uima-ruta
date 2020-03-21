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

import java.util.List;

import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.string.AbstractStringExpression;
import org.apache.uima.ruta.rule.MatchContext;

public abstract class ListExpression<T> extends AbstractStringExpression {

  public abstract List<T> getList(MatchContext context, RutaStream stream);

  public abstract List<?> getRawList(MatchContext context, RutaStream stream);

//  @Override
//  public List<IRutaExpression> getRawList(MatchContext context, RutaStream stream) {
//    return null;
//  }

  @Override
  public String getStringValue(MatchContext context, RutaStream stream) {
    return getList(context, stream).toString();
  }

}
