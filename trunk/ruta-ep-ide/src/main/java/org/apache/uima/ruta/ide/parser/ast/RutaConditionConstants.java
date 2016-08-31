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

package org.apache.uima.ruta.ide.parser.ast;

import org.apache.uima.ruta.ide.core.parser.RutaParser;
import org.eclipse.dltk.ast.expressions.ExpressionConstants;

public class RutaConditionConstants {
  public static final int CONSTANT_OFFSET = ExpressionConstants.USER_EXPRESSION_START;

  // important for formatter to handle the only condition without parantheses
  public static final int COND_MINUS = CONSTANT_OFFSET + RutaParser.MINUS;

  // public static int getConditionConstant(String str) {
  // // RutaParser.tokenNames;
  // return 0;
  // }
}
