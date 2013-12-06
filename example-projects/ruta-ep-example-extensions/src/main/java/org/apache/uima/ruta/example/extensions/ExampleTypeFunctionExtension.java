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
import org.apache.uima.ruta.expression.string.AbstractStringExpression;
import org.apache.uima.ruta.expression.type.TypeFunctionExpression;
import org.apache.uima.ruta.extensions.IRutaTypeFunctionExtension;
import org.apache.uima.ruta.extensions.RutaParseException;
import org.apache.uima.ruta.verbalize.RutaVerbalizer;

public class ExampleTypeFunctionExtension implements IRutaTypeFunctionExtension {

  private final String[] knownExtensions = new String[] { "ExampleTypeFunction" };

  private final Class<?>[] extensions = new Class[] { ExampleTypeFunction.class };

  public String verbalize(RutaElement element, RutaVerbalizer verbalizer) {
    if (element instanceof ExampleTypeFunction) {
      return verbalizeName(element) + "("
              + verbalizer.verbalize(((ExampleTypeFunction) element).getExpr()) + ")";
    } else {
      return "UnknownTypeFunction";
    }
  }

  public TypeFunctionExpression createTypeFunction(String name, List<RutaExpression> args)
          throws RutaParseException {
    if (args == null || args.size() != 1 || !(args.get(0) instanceof AbstractStringExpression)) {
      throw new RutaParseException("ExampleTypeFunction accepts only one StringExpression as argument!");
    }
    return new ExampleTypeFunction((AbstractStringExpression) args.get(0));
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
