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

package org.apache.uima.ruta.extensions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.runtime.Token;
import org.apache.uima.UimaContext;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.action.AbstractRutaAction;
import org.apache.uima.ruta.condition.AbstractRutaCondition;
import org.apache.uima.ruta.expression.RutaExpression;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.string.AbstractStringExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;

public class RutaExternalFactory {

  private Map<String, IRutaConditionExtension> conditionExtensions;

  private Map<String, IRutaActionExtension> actionExtensions;

  private Map<String, IRutaTypeFunctionExtension> typeFunctionExtensions;

  private Map<String, IRutaBooleanFunctionExtension> booleanFunctionExtensions;

  private Map<String, IRutaStringFunctionExtension> stringFunctionExtensions;

  private Map<String, IRutaNumberFunctionExtension> numberFunctionExtensions;

  private Map<String, IRutaBlockExtension> blockExtensions;

  private UimaContext context;

  public RutaExternalFactory() {
    super();
    conditionExtensions = new HashMap<String, IRutaConditionExtension>();
    actionExtensions = new HashMap<String, IRutaActionExtension>();
    booleanFunctionExtensions = new HashMap<String, IRutaBooleanFunctionExtension>();
    stringFunctionExtensions = new HashMap<String, IRutaStringFunctionExtension>();
    numberFunctionExtensions = new HashMap<String, IRutaNumberFunctionExtension>();
    typeFunctionExtensions = new HashMap<String, IRutaTypeFunctionExtension>();
    blockExtensions = new HashMap<String, IRutaBlockExtension>();
  }

  public AbstractRutaCondition createExternalCondition(Token id, List<RutaExpression> args)
          throws RutaParseException {
    String name = id.getText();
    IRutaConditionExtension extension = conditionExtensions.get(name);
    if (extension != null) {
      return extension.createCondition(name, args);
    }
    // Throw exception
    return null;
  }

  public AbstractRutaAction createExternalAction(Token id, List<RutaExpression> args)
          throws RutaParseException {
    String name = id.getText();
    IRutaActionExtension extension = actionExtensions.get(name);
    if (extension != null) {
      return extension.createAction(name, args);
    }
    // TODO throw exception
    return null;
  }

  public TypeExpression createExternalTypeFunction(Token id, List<RutaExpression> args)
          throws RutaParseException {
    String name = id.getText();
    IRutaTypeFunctionExtension extension = typeFunctionExtensions.get(name);
    if (extension != null) {
      return extension.createTypeFunction(name, args);
    }
    // Throw exception
    return null;
  }

  public IBooleanExpression createExternalBooleanFunction(Token id, List<RutaExpression> args)
          throws RutaParseException {
    String name = id.getText();
    IRutaBooleanFunctionExtension extension = booleanFunctionExtensions.get(name);
    if (extension != null) {
      return extension.createBooleanFunction(name, args);
    }
    // Throw exception
    return null;
  }

  public AbstractStringExpression createExternalStringFunction(Token id, List<RutaExpression> args)
          throws RutaParseException {
    String name = id.getText();
    IRutaStringFunctionExtension extension = stringFunctionExtensions.get(name);
    if (extension != null) {
      return extension.createStringFunction(name, args);
    }
    // Throw exception
    return null;
  }

  public INumberExpression createExternalNumberFunction(Token id, List<RutaExpression> args)
          throws RutaParseException {
    String name = id.getText();
    IRutaNumberFunctionExtension extension = numberFunctionExtensions.get(name);
    if (extension != null) {
      return extension.createNumberFunction(name, args);
    }
    // Throw exception
    return null;
  }

  public RutaBlock createExternalBlock(Token type, List<RutaExpression> args, RutaBlock env)
          throws RutaParseException {
    if (type == null) {
      return null;
    }
    String t = type.getText();
    IRutaBlockExtension extension = blockExtensions.get(t);
    if (extension != null) {
      RutaBlock rutaBlock = extension.createBlock(t, args, env);
      rutaBlock.setContext(context);
      return rutaBlock;
    }
    return null;
  }

  public void addExtension(String id, IRutaExtension extension) {
    if (extension instanceof IRutaActionExtension) {
      addActionExtension(id, (IRutaActionExtension) extension);
    } else if (extension instanceof IRutaConditionExtension) {
      addConditionExtension(id, (IRutaConditionExtension) extension);
    } else if (extension instanceof IRutaBooleanFunctionExtension) {
      addBooleanFunctionExtension(id, (IRutaBooleanFunctionExtension) extension);
    } else if (extension instanceof IRutaStringFunctionExtension) {
      addStringFunctionExtension(id, (IRutaStringFunctionExtension) extension);
    } else if (extension instanceof IRutaNumberFunctionExtension) {
      addNumberFunctionExtension(id, (IRutaNumberFunctionExtension) extension);
    } else if (extension instanceof IRutaTypeFunctionExtension) {
      addTypeFunctionExtension(id, (IRutaTypeFunctionExtension) extension);
    } else if (extension instanceof IRutaBlockExtension) {
      addBlockExtension(id, (IRutaBlockExtension) extension);
    }
  }

  public void addConditionExtension(String id, IRutaConditionExtension extension) {
    conditionExtensions.put(id, extension);
  }

  public void addActionExtension(String id, IRutaActionExtension extension) {
    actionExtensions.put(id, extension);
  }

  public void addNumberFunctionExtension(String id, IRutaNumberFunctionExtension extension) {
    numberFunctionExtensions.put(id, extension);
  }

  public void addBooleanFunctionExtension(String id, IRutaBooleanFunctionExtension extension) {
    booleanFunctionExtensions.put(id, extension);
  }

  public void addStringFunctionExtension(String id, IRutaStringFunctionExtension extension) {
    stringFunctionExtensions.put(id, extension);
  }

  public void addTypeFunctionExtension(String id, IRutaTypeFunctionExtension extension) {
    typeFunctionExtensions.put(id, extension);
  }

  public void addBlockExtension(String id, IRutaBlockExtension extension) {
    blockExtensions.put(id, extension);
  }

  public boolean isInitialized() {
    return !actionExtensions.isEmpty() || !conditionExtensions.isEmpty()
            || !booleanFunctionExtensions.isEmpty() || !numberFunctionExtensions.isEmpty()
            || !stringFunctionExtensions.isEmpty() || !typeFunctionExtensions.isEmpty();
  }

  public Map<String, IRutaNumberFunctionExtension> getNumberFunctionExtensions() {
    return numberFunctionExtensions;
  }

  public void setNumberFunctionExtensions(
          Map<String, IRutaNumberFunctionExtension> numberFunctionExtensions) {
    this.numberFunctionExtensions = numberFunctionExtensions;
  }

  public Map<String, IRutaStringFunctionExtension> getStringFunctionExtensions() {
    return stringFunctionExtensions;
  }

  public void setStringFunctionExtensions(
          Map<String, IRutaStringFunctionExtension> stringFunctionExtensions) {
    this.stringFunctionExtensions = stringFunctionExtensions;
  }

  public Map<String, IRutaBooleanFunctionExtension> getBooleanFunctionExtensions() {
    return booleanFunctionExtensions;
  }

  public void setBooleanFunctionExtensions(
          Map<String, IRutaBooleanFunctionExtension> booleanFunctionExtensions) {
    this.booleanFunctionExtensions = booleanFunctionExtensions;
  }

  public Map<String, IRutaTypeFunctionExtension> getTypeFunctionExtensions() {
    return typeFunctionExtensions;
  }

  public void setTypeFunctionExtensions(
          Map<String, IRutaTypeFunctionExtension> typeFunctionExtensions) {
    this.typeFunctionExtensions = typeFunctionExtensions;
  }

  public Map<String, IRutaActionExtension> getActionExtensions() {
    return actionExtensions;
  }

  public void setActionExtensions(Map<String, IRutaActionExtension> actionExtensions) {
    this.actionExtensions = actionExtensions;
  }

  public Map<String, IRutaConditionExtension> getConditionExtensions() {
    return conditionExtensions;
  }

  public void setConditionExtensions(Map<String, IRutaConditionExtension> conditionExtensions) {
    this.conditionExtensions = conditionExtensions;
  }

  public Map<String, IRutaBlockExtension> getBlockExtensions() {
    return blockExtensions;
  }

  public void setBlockExtensions(Map<String, IRutaBlockExtension> blockExtensions) {
    this.blockExtensions = blockExtensions;
  }

  public UimaContext getContext() {
    return context;
  }

  public void setContext(UimaContext context) {
    this.context = context;
  }

}
