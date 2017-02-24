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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.ruta.block.RutaBlock;

/**
 * Container for storing information about the usage of types and annotations within a ruta
 * script/module.
 *
 */
public class TypeUsageInformation {

  private List<String> mentionedTypes;

  private Collection<String> usedTypes;
  
  public TypeUsageInformation() {
    super();
    mentionedTypes = new ArrayList<>();
  }

  public void addMentionedType(String mention) {
    mentionedTypes.add(mention);
  }

  public List<String> getMentionedTypes() {
    return mentionedTypes;
  }

  public void resolveTypes(RutaModule module) {
    usedTypes = new HashSet<>();
    resolveTypes(module.getRootBlock());
    for (RutaModule rutaModule : module.getScripts().values()) {
      resolveTypes(rutaModule.getRootBlock());
    }
    
  }

  private void resolveTypes(RutaBlock rootBlock) {
    for (String mention : mentionedTypes) {
      Type type = rootBlock.getEnvironment().getType(mention);
      if(type != null) {
        usedTypes.add(type.getName());
      }
    }
  }

  public Collection<String> getUsedTypes() {
    return usedTypes;
  }

  
}
