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

package org.apache.uima.ruta.example.extensions;

import java.util.List;

import org.apache.uima.ruta.RutaElement;
import org.apache.uima.ruta.expression.RutaExpression;
import org.apache.uima.ruta.expression.number.NumberFunctionExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;
import org.apache.uima.ruta.extensions.IRutaNumberFunctionExtension;
import org.apache.uima.ruta.extensions.RutaParseException;
import org.apache.uima.ruta.verbalize.RutaVerbalizer;

public class ExampleNumberFunctionExtension implements IRutaNumberFunctionExtension {

  private final String[] knownExtensions = new String[] { "ExampleNumberFunction" };

  private final Class<?>[] extensions = new Class[] { ExampleNumberFunction.class };

  public String verbalize(RutaElement element, RutaVerbalizer verbalizer) {
    if (element instanceof ExampleTypeFunction) {
      return verbalizeName(element) + "("
              + verbalizer.verbalize(((ExampleBooleanFunction) element).getExpr()) + ")";
    } else {
      return "UnknownNumberFunction";
    }
  }

  public NumberFunctionExpression createNumberFunction(String name, List<RutaExpression> args)
          throws RutaParseException {
    if (args == null || args.size() != 1 || !(args.get(0) instanceof TypeExpression)) {
      throw new RutaParseException("ExampleNumberFunction accepts only one TypeExpression as argument!");
    }
    return new ExampleNumberFunction((TypeExpression) args.get(0));
  }

  public String verbalizeName(RutaElement element) {
    return knownExtensions[0];
  }

  public String[] getKnownExtensions() {
    return knownExtensions;
  }

  public Class<?>[] extensions() {
    return extensions;
  }

}
