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

import java.util.HashMap;
import java.util.Map;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class RutaModule extends RutaElement {

  private final RutaBlock rootBlock;

  private Map<String, RutaModule> scripts;

  private Map<String, AnalysisEngine> engines;

  private Map<String, RutaBlock> blocks;

  public RutaModule(RutaBlock rootBlock) {
    super();
    this.rootBlock = rootBlock;
    scripts = new HashMap<String, RutaModule>();
    engines = new HashMap<String, AnalysisEngine>();
    blocks = new HashMap<String, RutaBlock>();
  }

  public ScriptApply apply(RutaStream stream, InferenceCrowd crowd) {
    crowd.beginVisit(this, null);
    ScriptApply result = rootBlock.apply(stream, crowd);
    crowd.endVisit(this, result);
    return result;
  }

  public RutaBlock getBlock(String id) {
    if (id == null || id.equals(rootBlock.getName())) {
      return rootBlock;
    }
    RutaBlock ownBlock = blocks.get(id);
    if(ownBlock != null) {
      return ownBlock;
    }
    int indexOf = id.indexOf('.');
    if(indexOf != -1) {
      String otherScriptName = id.substring(0, indexOf);
      String tail = id.substring(indexOf + 1, id.length());
      RutaModule otherScript = getScript(otherScriptName);
      if(otherScript != null) {
        return otherScript.getBlock(tail);
      }
    } else {
      RutaModule otherScript = getScript(id);
      if(otherScript != null) {
        return otherScript.getBlock(null);
      }
    }
    return null;
  }

  public RutaModule getScript(String name) {
    if (name.equals(rootBlock.getName())) {
      return this;
    }
    RutaModule result = scripts.get(name);
    if (result == null) {
      for (String each : scripts.keySet()) {
        String[] split = each.split("\\.");
        String last = split[split.length - 1];
        if (last.equals(name)) {
          return scripts.get(each);
        }
      }
    }
    return result;
  }

  public void addScript(String name, RutaModule script) {
    scripts.put(name, script);
  }

  public void addBlock(String id, RutaBlock block) {
    blocks.put(id, block);
  }

  public void setScriptDependencies(Map<String, RutaModule> additionalScripts) {
    for (String eachTarget : scripts.keySet()) {
      RutaModule module = additionalScripts.get(eachTarget);
      addScript(eachTarget, module);
    }
  }

  public AnalysisEngine getEngine(String name) {
    AnalysisEngine result = engines.get(name);
    if (result == null) {
      for (String each : engines.keySet()) {
        String[] split = each.split("\\.");
        String last = split[split.length - 1];
        if (last.equals(name)) {
          return engines.get(each);
        }
      }
    }
    return result;
  }

  public void addEngine(String name, AnalysisEngine engine) {
    engines.put(name, engine);
  }

  public void addUimafitEngine(String name, AnalysisEngine engine) {
    engines.put(name, engine);
  }

  public void setEngineDependencies(Map<String, AnalysisEngine> additionalEngines) {
    for (String eachTarget : engines.keySet()) {
      AnalysisEngine engine = additionalEngines.get(eachTarget);
      addEngine(eachTarget, engine);
    }
  }

  public Map<String, RutaModule> getScripts() {
    return scripts;
  }

  public Map<String, RutaBlock> getBlocks() {
    return blocks;
  }

  public Map<String, AnalysisEngine> getEngines() {
    return engines;
  }

  public RutaBlock getRootBlock() {
    return rootBlock;
  }

}
