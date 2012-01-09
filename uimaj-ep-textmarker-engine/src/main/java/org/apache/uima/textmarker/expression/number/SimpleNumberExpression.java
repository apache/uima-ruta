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

package org.apache.uima.textmarker.expression.number;

import org.apache.uima.textmarker.TextMarkerStatement;

public class SimpleNumberExpression extends NumberExpression {

  private final Number number;

  public SimpleNumberExpression(Number number) {
    super();
    this.number = number;
  }

  @Override
  public double getDoubleValue(TextMarkerStatement parent) {
    return number.doubleValue();
  }

  @Override
  public double getFloatValue(TextMarkerStatement parent) {
    return number.floatValue();
  }
  
  @Override
  public int getIntegerValue(TextMarkerStatement parent) {
    return number.intValue();
  }

  public Number getNumber() {
    return number;
  }

  @Override
  public String getStringValue(TextMarkerStatement parent) {
    boolean floating = number.intValue() != number.doubleValue();
    if (floating) {
      return "" + getDoubleValue(parent);
    } else {
      return "" + getIntegerValue(parent);
    }
  }

}
