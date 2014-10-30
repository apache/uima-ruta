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

package org.apache.uima.ruta;

import java.util.List;

import org.apache.uima.UimaContext;
import org.apache.uima.ruta.rule.RutaRule;

public abstract class RutaBlock extends RutaStatement {

  protected final String name;

  protected RutaEnvironment environment;

  protected RutaRule rule;

  protected List<RutaStatement> elements;

  private String namespace;

  private RutaModule script;

  protected UimaContext context;

  public RutaBlock(RutaBlock parent, String defaultNamespace, UimaContext context) {
    this(null, null, null, parent, defaultNamespace, context);
  }

  public RutaBlock(String name, RutaRule rule, List<RutaStatement> elements, RutaBlock parent,
          String defaultNamespace, UimaContext context) {
    super(parent);
    this.name = name;
    this.rule = rule;
    this.elements = elements;
    this.environment = new RutaEnvironment(this);
    this.namespace = defaultNamespace;
    this.context = context;
  }

  public RutaRule getRule() {
    return rule;
  }

  public void setRule(RutaRule rule) {
    this.rule = rule;
  }

  @Override
  public RutaEnvironment getEnvironment() {
    return environment;
  }

  public List<RutaStatement> getElements() {
    return elements;
  }

  public void setElements(List<RutaStatement> elements) {
    this.elements = elements;
  }

  public RutaModule getScript() {
    if (script != null) {
      return script;
    } else if (getParent() != null) {
      return getParent().getScript();
    } else {
      // may not happen!
      assert (false);
      return null;

    }
  }

  public void setScript(RutaModule script) {
    this.script = script;
  }

  public String getNamespace() {
    return namespace;
  }

  public String getName() {
    return name;
  }

  public UimaContext getContext() {
    return context;
  }

  public void setContext(UimaContext context) {
    this.context = context;
  }

}
