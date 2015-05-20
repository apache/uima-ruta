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

package org.apache.uima.ruta.ide.core.extensions;

import java.util.List;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.apache.uima.ruta.ide.parser.ast.ActionFactory;
import org.apache.uima.ruta.ide.parser.ast.ConditionFactory;
import org.apache.uima.ruta.ide.parser.ast.ExpressionFactory;
import org.apache.uima.ruta.ide.parser.ast.RutaAction;
import org.apache.uima.ruta.ide.parser.ast.RutaCondition;
import org.eclipse.dltk.ast.expressions.Expression;

public class RutaExternalFactory {

  public RutaExternalFactory() {
    super();
  }

  public RutaCondition createExternalCondition(Token id, List<Expression> args)
          throws RecognitionException {
    return ConditionFactory.createCondition(id, args);
  }

  public RutaAction createExternalAction(Token id, List<Expression> args)
          throws RecognitionException {
    return ActionFactory.createAction(id, args);
  }

  public Expression createExternalNumberFunction(Token id, List<Expression> args)
          throws RecognitionException {
    return ExpressionFactory.createNumberFunction(id, args);
  }

  public Expression createExternalBooleanFunction(Token id, List<Expression> args)
          throws RecognitionException {
    return ExpressionFactory.createBooleanFunction(id, args);
  }

  public Expression createExternalStringFunction(Token id, List<Expression> args)
          throws RecognitionException {
    return ExpressionFactory.createStringFunction(id, args);
  }

  public Expression createExternalTypeFunction(Token id, List<Expression> args)
          throws RecognitionException {
    return ExpressionFactory.createTypeFunction(id, args);
  }

}
