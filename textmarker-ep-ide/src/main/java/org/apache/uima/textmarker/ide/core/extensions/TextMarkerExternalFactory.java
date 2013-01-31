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

package org.apache.uima.textmarker.ide.core.extensions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.apache.uima.textmarker.ide.core.TextMarkerExtensionManager;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerAction;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerCondition;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerExpression;
import org.eclipse.dltk.ast.expressions.Expression;

public class TextMarkerExternalFactory {

  private Map<String, IIDEConditionExtension> conditionExtensions;

  private Map<String, IIDEActionExtension> actionExtensions;

  private Map<String, IIDENumberFunctionExtension> numberFunctionExtensions;

  private Map<String, IIDEBooleanFunctionExtension> booleanFunctionExtensions;

  private Map<String, IIDEStringFunctionExtension> stringFunctionExtensions;

  private Map<String, IIDETypeFunctionExtension> typeFunctionExtensions;

  public TextMarkerExternalFactory() {
    super();
    conditionExtensions = new HashMap<String, IIDEConditionExtension>();
    actionExtensions = new HashMap<String, IIDEActionExtension>();
    numberFunctionExtensions = new HashMap<String, IIDENumberFunctionExtension>();
    booleanFunctionExtensions = new HashMap<String, IIDEBooleanFunctionExtension>();
    stringFunctionExtensions = new HashMap<String, IIDEStringFunctionExtension>();
    typeFunctionExtensions = new HashMap<String, IIDETypeFunctionExtension>();
    IIDEConditionExtension[] cextensions = TextMarkerExtensionManager.getDefault()
            .getIDEConditionExtensions();
    for (IIDEConditionExtension each : cextensions) {
      String[] knownExtensions = each.getKnownExtensions();
      for (String string : knownExtensions) {
        conditionExtensions.put(string, each);
      }
    }
    IIDEActionExtension[] aextensions = TextMarkerExtensionManager.getDefault()
            .getIDEActionExtensions();
    for (IIDEActionExtension each : aextensions) {
      String[] knownExtensions = each.getKnownExtensions();
      for (String string : knownExtensions) {
        actionExtensions.put(string, each);
      }
    }
    IIDENumberFunctionExtension[] nfextensions = TextMarkerExtensionManager.getDefault()
            .getIDENumberFunctionExtensions();
    for (IIDENumberFunctionExtension each : nfextensions) {
      String[] knownExtensions = each.getKnownExtensions();
      for (String string : knownExtensions) {
        numberFunctionExtensions.put(string, each);
      }
    }
    IIDEBooleanFunctionExtension[] bfextensions = TextMarkerExtensionManager.getDefault()
            .getIDEBooleanFunctionExtensions();
    for (IIDEBooleanFunctionExtension each : bfextensions) {
      String[] knownExtensions = each.getKnownExtensions();
      for (String string : knownExtensions) {
        booleanFunctionExtensions.put(string, each);
      }
    }
    IIDEStringFunctionExtension[] sfextensions = TextMarkerExtensionManager.getDefault()
            .getIDEStringFunctionExtensions();
    for (IIDEStringFunctionExtension each : sfextensions) {
      String[] knownExtensions = each.getKnownExtensions();
      for (String string : knownExtensions) {
        stringFunctionExtensions.put(string, each);
      }
    }
    IIDETypeFunctionExtension[] tfextensions = TextMarkerExtensionManager.getDefault()
            .getIDETypeFunctionExtensions();
    for (IIDETypeFunctionExtension each : tfextensions) {
      String[] knownExtensions = each.getKnownExtensions();
      for (String string : knownExtensions) {
        typeFunctionExtensions.put(string, each);
      }
    }

  }

  public TextMarkerCondition createExternalCondition(Token id, List<Expression> args)
          throws RecognitionException {
    String name = id.getText();
    IIDEConditionExtension extension = conditionExtensions.get(name);
    if (extension != null) {
      return extension.createCondition(id, args);
    }
    throw new NoViableAltException();
  }

  public TextMarkerAction createExternalAction(Token id, List<Expression> args)
          throws RecognitionException {
    String name = id.getText();
    IIDEActionExtension extension = actionExtensions.get(name);
    if (extension != null) {
      return extension.createAction(id, args);
    }
    throw new NoViableAltException("Unknown action : " + name, id.getType(), id.getType(),
            id.getInputStream());
  }

  public TextMarkerExpression createExternalNumberFunction(Token id, List<Expression> args)
          throws RecognitionException {
    String name = id.getText();
    IIDENumberFunctionExtension extension = numberFunctionExtensions.get(name);
    if (extension != null) {
      return extension.createNumberFunction(id, args);
    }
    throw new NoViableAltException("Unknown number function : " + name, id.getType(), id.getType(),
            id.getInputStream());
  }

  public TextMarkerExpression createExternalBooleanFunction(Token id, List<Expression> args)
          throws RecognitionException {
    String name = id.getText();
    IIDEBooleanFunctionExtension extension = booleanFunctionExtensions.get(name);
    if (extension != null) {
      return extension.createBooleanFunction(id, args);
    }
    throw new NoViableAltException("Unknown number function : " + name, id.getType(), id.getType(),
            id.getInputStream());
  }

  public TextMarkerExpression createExternalStringFunction(Token id, List<Expression> args)
          throws RecognitionException {
    String name = id.getText();
    IIDEStringFunctionExtension extension = stringFunctionExtensions.get(name);
    if (extension != null) {
      return extension.createStringFunction(id, args);
    }
    throw new NoViableAltException("Unknown number function : " + name, id.getType(), id.getType(),
            id.getInputStream());
  }

  public TextMarkerExpression createExternalTypeFunction(Token id, List<Expression> args)
          throws RecognitionException {
    String name = id.getText();
    IIDETypeFunctionExtension extension = typeFunctionExtensions.get(name);
    if (extension != null) {
      return extension.createTypeFunction(id, args);
    }
    throw new NoViableAltException("Unknown number function : " + name, id.getType(), id.getType(),
            id.getInputStream());
  }

  public void addExtension(String id, ITextMarkerExtension extension) {
    if (extension instanceof IIDEActionExtension) {
      addActionExtension(id, (IIDEActionExtension) extension);
    } else if (extension instanceof IIDEConditionExtension) {
      addConditionExtension(id, (IIDEConditionExtension) extension);
    }
  }

  public void addConditionExtension(String id, IIDEConditionExtension extension) {
    conditionExtensions.put(id, extension);
  }

  public void addActionExtension(String id, IIDEActionExtension extension) {
    actionExtensions.put(id, extension);
  }

}
