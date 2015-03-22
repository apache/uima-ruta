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

import java.io.File;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.ruta.RutaModule;
import org.apache.uima.ruta.parser.RutaLexer;
import org.apache.uima.ruta.parser.RutaParser;

public class RutaDescriptorFactory {

  public RutaDescriptorFactory() {
    super();
  }

  public TypeSystemDescription createTypeSystemDescription(String rules) throws RecognitionException {
    CharStream st = new ANTLRStringStream(rules);
    RutaLexer lexer = new RutaLexer(st);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    RutaParser parser = new RutaParser(tokens);
    RutaDescriptorInformation descInfo = new RutaDescriptorInformation();
//    parser.setContext(context);
//    parser.setExternalFactory(factory);
//    parser.setResourcePaths(resourcePaths);
//    parser.setResourceManager(resourceManager);
    parser.setDescriptorInformation(descInfo);
    parser.file_input("Anonymous");
//    RutaDescriptorBuilder builder = new RutaDescriptorBuilder(defaultTypeSystem, defaultEngine);
    return null;
  }
  
  public AnalysisEngineDescription createAnalysisEngineDescription() {
    return null;
  }
  
  
}

