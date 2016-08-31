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

package org.apache.uima.ruta.expression.number;

import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.rule.MatchContext;

public class NegativeNumberExpression extends AbstractNumberExpression {

  private final INumberExpression ne;

  public NegativeNumberExpression(INumberExpression simpleNumberExpression) {
    super();
    this.ne = simpleNumberExpression;
  }

  public double getDoubleValue(MatchContext context, RutaStream stream) {
    return -ne.getDoubleValue(context, stream);
  }

  public float getFloatValue(MatchContext context, RutaStream stream) {
    return -ne.getFloatValue(context, stream);
  }

  public int getIntegerValue(MatchContext context, RutaStream stream) {
    return -ne.getIntegerValue(context, stream);
  }

  public String getStringValue(MatchContext context, RutaStream stream) {
    return "-" + ne.getStringValue(context, stream);
  }

  public INumberExpression getExpression() {
    return ne;
  }

}
