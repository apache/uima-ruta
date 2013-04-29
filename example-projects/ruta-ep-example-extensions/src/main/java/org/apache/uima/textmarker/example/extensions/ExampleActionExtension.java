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

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.ruta.RutaElement;
import org.apache.uima.ruta.action.AbstractRutaAction;
import org.apache.uima.ruta.expression.RutaExpression;
import org.apache.uima.ruta.expression.number.NumberExpression;
import org.apache.uima.ruta.extensions.IRutaActionExtension;
import org.apache.uima.ruta.verbalize.RutaVerbalizer;

import antlr.ANTLRException;

public class ExampleActionExtension implements IRutaActionExtension {

  private final String[] knownExtensions = new String[] { "ExampleAction" };

  private final Class<?>[] extensions = new Class[] { ExampleAction.class };

  public String verbalize(RutaElement element, RutaVerbalizer verbalizer) {
    if (element instanceof ExampleAction) {
      return verbalizeName(element) + "("
              + verbalizer.verbalizeExpressionList(((ExampleAction) element).getIndexExprList()) + ")";
    } else {
      return "UnknownAction";
    }
  }

  public AbstractRutaAction createAction(String name, List<RutaExpression> args)
          throws ANTLRException {
    List<NumberExpression> arguments = new ArrayList<NumberExpression>();
    if (args != null) {
      for (RutaExpression each : args) {
        if (each instanceof NumberExpression) {
          arguments.add((NumberExpression) each);
        } else {
          throw new ANTLRException("ExampleAction accepts only NumberExpressions as arguments");
        }
      }
    }
    return new ExampleAction(arguments);
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
