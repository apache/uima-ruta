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

package org.apache.uima.textmarker;

import java.util.HashMap;
import java.util.Map;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.textmarker.visitor.InferenceCrowd;

public class TextMarkerModule extends TextMarkerElement {

  private final TextMarkerBlock rootBlock;

  private Map<String, TextMarkerModule> scripts;

  private Map<String, AnalysisEngine> engines;

  private Map<String, TextMarkerBlock> blocks;

  public TextMarkerModule(TextMarkerBlock rootBlock) {
    super();
    this.rootBlock = rootBlock;
    scripts = new HashMap<String, TextMarkerModule>();
    engines = new HashMap<String, AnalysisEngine>();
    blocks = new HashMap<String, TextMarkerBlock>();
  }

  public ScriptApply apply(TextMarkerStream stream, InferenceCrowd crowd) {
    crowd.beginVisit(this, null);
    ScriptApply result = rootBlock.apply(stream, crowd);
    crowd.endVisit(this, result);
    return result;
  }

  public TextMarkerBlock getBlock(String id) {
    if (id == null || id.equals(rootBlock.getId())) {
      return rootBlock;
    }
    return blocks.get(id);
  }

  public TextMarkerModule getScript(String name) {
    if (name.equals(rootBlock.getId())) {
      return this;
    }
    TextMarkerModule result = scripts.get(name);
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

  public void addScript(String name, TextMarkerModule script) {
    scripts.put(name, script);
  }

  public void addBlock(String id, TextMarkerBlock block) {
    blocks.put(id, block);
  }

  public void setScriptDependencies(Map<String, TextMarkerModule> additionalScripts) {
    for (String eachTarget : scripts.keySet()) {
      TextMarkerModule textMarkerModule = additionalScripts.get(eachTarget);
      addScript(eachTarget, textMarkerModule);
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

  public void setEngineDependencies(Map<String, AnalysisEngine> additionalEngines) {
    for (String eachTarget : engines.keySet()) {
      AnalysisEngine engine = additionalEngines.get(eachTarget);
      addEngine(eachTarget, engine);
    }
  }

  public Map<String, TextMarkerModule> getScripts() {
    return scripts;
  }

  public Map<String, TextMarkerBlock> getBlocks() {
    return blocks;
  }

  public Map<String, AnalysisEngine> getEngines() {
    return engines;
  }

}
