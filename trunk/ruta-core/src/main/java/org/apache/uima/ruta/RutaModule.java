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
import java.util.List;
import java.util.Map;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.ruta.block.RutaBlock;
import org.apache.uima.ruta.visitor.InferenceCrowd;

public class RutaModule extends RutaElement {

  private final RutaBlock rootBlock;

  private Map<String, RutaModule> scripts;

  private Map<String, AnalysisEngine> descriptorEngines;
  
  private Map<String, AnalysisEngine> uimafitEngines;

  private Map<String, List<String>> configurationData;
  
  private Map<String, RutaBlock> blocks;

  public RutaModule(RutaBlock rootBlock) {
    super();
    this.rootBlock = rootBlock;
    scripts = new HashMap<String, RutaModule>();
    descriptorEngines = new HashMap<String, AnalysisEngine>();
    uimafitEngines = new HashMap<String, AnalysisEngine>();
    configurationData = new HashMap<String, List<String>>();
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
    if (ownBlock != null) {
      return ownBlock;
    }
    int indexOf = id.indexOf('.');
    if (indexOf != -1) {
      String otherScriptName = id.substring(0, indexOf);
      String tail = id.substring(indexOf + 1, id.length());
      RutaModule otherScript = getScript(otherScriptName);
      if (otherScript != null) {
        return otherScript.getBlock(tail);
      }
    } else {
      RutaModule otherScript = getScript(id);
      if (otherScript != null) {
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
    Map<String, AnalysisEngine> engines = getAllEngines();
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

  public void addDescriptorEngine(String name, AnalysisEngine engine) {
    descriptorEngines.put(name, engine);
  }

  public void addUimafitEngine(String name, AnalysisEngine engine) {
    uimafitEngines.put(name, engine);
  }

  public void addConfigurationData(String name, List<String> configuration) {
    configurationData.put(name, configuration);
  }
  
  public void setDescriptorEngineDependencies(Map<String, AnalysisEngine> additionalEngines) {
    for (String eachTarget : descriptorEngines.keySet()) {
      AnalysisEngine engine = additionalEngines.get(eachTarget);
      addDescriptorEngine(eachTarget, engine);
    }
  }
  
  public void setUimafitEngineDependencies(Map<String, AnalysisEngine> additionalEngines) {
    for (String eachTarget : uimafitEngines.keySet()) {
      AnalysisEngine engine = additionalEngines.get(eachTarget);
        addUimafitEngine(eachTarget, engine);
    }
  }

  public Map<String, RutaModule> getScripts() {
    return scripts;
  }

  public Map<String, RutaBlock> getBlocks() {
    return blocks;
  }

  public Map<String, AnalysisEngine> getAllEngines() {
    Map<String, AnalysisEngine> result = new HashMap<>();
    result.putAll(descriptorEngines);
    result.putAll(uimafitEngines);
    return result;
  }

  public Map<String, AnalysisEngine> getDescriptorEngines() {
    return descriptorEngines;
  }

  public Map<String, AnalysisEngine> getUimafitEngines() {
    return uimafitEngines;
  }
  
  public RutaBlock getRootBlock() {
    return rootBlock;
  }

  public Map<String, List<String>> getConfigurationData() {
    return configurationData;
  }

  public List<String> getConfigurationData(String engine) {
    return configurationData.get(engine);
  }

}
