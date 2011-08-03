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

package org.apache.uima.tm.textmarker.kernel.expression.bool;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStatement;

public class SimpleBooleanFunction extends BooleanExpression {

  private String op;

  private BooleanExpression e1;

  private BooleanExpression e2;

  public SimpleBooleanFunction(String text, BooleanExpression e1, BooleanExpression e2) {
    super();
    this.op = text;
    this.e1 = e1;
    this.e2 = e2;
  }

  @Override
  public boolean getBooleanValue(TextMarkerStatement parent) {
    boolean b1 = e1.getBooleanValue(parent);
    boolean b2 = e2.getBooleanValue(parent);
    if ("XOR".equals(op)) {
      return (b1 || b2) && !(b1 && b2);
    } else if ("==".equals(op)) {
      return b1 == b2;
    } else if ("!=".equals(op)) {
      return b1 != b2;
    }
    return false;
  }

  @Override
  public String getStringValue(TextMarkerStatement parent) {
    return e1.getStringValue(parent) + " " + op + " " + e2.getStringValue(parent);
  }

}
