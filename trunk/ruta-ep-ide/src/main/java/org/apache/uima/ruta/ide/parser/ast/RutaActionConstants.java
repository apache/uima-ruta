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

public interface RutaActionConstants {
  public static final int A_CALL = ExpressionConstants.USER_EXPRESSION_START + RutaParser.CALL;
  
  public static final int A_EXEC = ExpressionConstants.USER_EXPRESSION_START + RutaParser.EXEC;

  public static final int A_ASSIGN = ExpressionConstants.USER_EXPRESSION_START + RutaParser.ASSIGN;

  public static final int A_CREATE = ExpressionConstants.USER_EXPRESSION_START + RutaParser.CREATE;

  public static final int A_FILL = ExpressionConstants.USER_EXPRESSION_START + RutaParser.FILL;

  public static final int A_LOG = ExpressionConstants.USER_EXPRESSION_START + RutaParser.LOG;
}
