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

package org.apache.uima.ruta.descriptor;

import java.util.Collections;
import java.util.List;

public class RutaBuildOptions {

  private List<String> language = Collections.emptyList();

  private List<String> engines = Collections.emptyList();

  private boolean importByName = false;

  private boolean resolveImports = false;

  private boolean errorOnDuplicateShortNames;

  private String encoding;

  private String typeSystemSuffix = "TypeSystem";
  
  private String analysisEngineSuffix = "Engine";
  
  public RutaBuildOptions(List<String> language, List<String> engines) {
    super();
    this.language = language;
    this.engines = engines;
  }
  
  public RutaBuildOptions() {
    super();
  }

  public List<String> getLanguage() {
    return language;
  }

  public List<String> getEngines() {
    return engines;
  }

  public void setImportByName(boolean importByName) {
    this.importByName = importByName;
  }

  public boolean isImportByName() {
    return importByName;
  }

  public void setResolveImports(boolean resolveImports) {
    this.resolveImports = resolveImports;
  }

  public boolean isResolveImports() {
    return resolveImports;
  }

  public void setErrorOnDuplicateShortNames(boolean error) {
    this.errorOnDuplicateShortNames = error;
  }

  public boolean isErrorOnDuplicateShortNames() {
    return errorOnDuplicateShortNames;
  }

  public String getEncoding() {
    return encoding;
  }

  public void setEncoding(String encoding) {
    this.encoding = encoding;
  }

  public String getTypeSystemSuffix() {
    return typeSystemSuffix;
  }

  public void setTypeSystemSuffix(String typeSystemSuffix) {
    this.typeSystemSuffix = typeSystemSuffix;
  }

  public String getAnalysisEngineSuffix() {
    return analysisEngineSuffix;
  }

  public void setAnalysisEngineSuffix(String analysisEngineSuffix) {
    this.analysisEngineSuffix = analysisEngineSuffix;
  }

}
