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
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.resource.impl.ResourceManager_impl;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.extensions.IRutaExtension;
import org.apache.uima.ruta.extensions.RutaExternalFactory;
import org.apache.uima.ruta.parser.RutaLexer;
import org.apache.uima.ruta.parser.RutaParser;
import org.apache.uima.util.InvalidXMLException;

public class RutaDescriptorFactory {

//  public static final String ANONYMOUS = "Anonymous";

  private URL defaultTypeSystem;

  private URL defaultEngine;

  public RutaDescriptorFactory() {
    super();
    this.defaultEngine = RutaDescriptorFactory.class.getClassLoader().getResource(
            "org/apache/uima/ruta/engine/BasicEngine.xml");
    this.defaultTypeSystem = RutaDescriptorFactory.class.getClassLoader().getResource(
            "org/apache/uima/ruta/engine/BasicTypeSystem.xml");
  }

  public RutaDescriptorFactory(String defaultTypeSystem, String defaultEngine)
          throws MalformedURLException {
    super();
    this.defaultTypeSystem = new File(defaultTypeSystem).toURI().toURL();
    this.defaultEngine = new File(defaultEngine).toURI().toURL();
  }

  public RutaDescriptorFactory(URL defaultTypeSystem, URL defaultEngine) {
    super();
    this.defaultTypeSystem = defaultTypeSystem;
    this.defaultEngine = defaultEngine;
  }

  public TypeSystemDescription createTypeSystemDescription(String typeSystemOutput,
          RutaDescriptorInformation descriptorInformation, RutaBuildOptions options,
          ClassLoader classloader) throws RecognitionException, IOException, InvalidXMLException,
          ResourceInitializationException, URISyntaxException {

    RutaDescriptorBuilder builder = new RutaDescriptorBuilder(defaultTypeSystem, defaultEngine);

    TypeSystemDescription typeSystemDescription = builder.createTypeSystemDescription(
            descriptorInformation, typeSystemOutput, options, null);

    return typeSystemDescription;
  }

  public AnalysisEngineDescription createAnalysisEngineDescription(String engineOutput,
          RutaDescriptorInformation descriptorInformation, RutaBuildOptions options,
          String[] scriptPaths, String[] descriptorPaths, String[] resourcePaths,
          ClassLoader classloader) throws IOException, RecognitionException, InvalidXMLException {

    RutaDescriptorBuilder builder = new RutaDescriptorBuilder(defaultTypeSystem, defaultEngine);

    AnalysisEngineDescription analysisEngineDescription = builder.createAnalysisEngineDescription(
            descriptorInformation, null, null, engineOutput, options, scriptPaths, descriptorPaths,
            resourcePaths);

    return analysisEngineDescription;
  }

  public Pair<AnalysisEngineDescription, TypeSystemDescription> createDescriptions(
          String engineOutput, String typeSystemOutput,
          RutaDescriptorInformation descriptorInformation, RutaBuildOptions options,
          String[] scriptPaths, String[] descriptorPaths, String[] resourcePaths)
          throws IOException, RecognitionException, InvalidXMLException,
          ResourceInitializationException, URISyntaxException {

    RutaDescriptorBuilder builder = new RutaDescriptorBuilder(defaultTypeSystem, defaultEngine);

    TypeSystemDescription typeSystemDescription = builder.createTypeSystemDescription(
            descriptorInformation, typeSystemOutput, options, descriptorPaths);

    AnalysisEngineDescription analysisEngineDescription = builder.createAnalysisEngineDescription(
            descriptorInformation, typeSystemDescription, typeSystemOutput, engineOutput, options,
            scriptPaths, descriptorPaths, resourcePaths);

    return new ImmutablePair<AnalysisEngineDescription, TypeSystemDescription>(
            analysisEngineDescription, typeSystemDescription);
  }

  public RutaDescriptorInformation parseDescriptorInformation(File scriptFile,
          RutaBuildOptions options) throws IOException, RecognitionException {
    CharStream st = new ANTLRFileStream(scriptFile.getAbsolutePath(), options.getEncoding());
    RutaLexer lexer = new RutaLexer(st);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    RutaParser parser = new RutaParser(tokens);
    RutaDescriptorInformation descInfo = new RutaDescriptorInformation();
    parser.setDescriptorInformation(descInfo);
    // TODO no context?
    parser.setContext(null);
    parser.setExternalFactory(initializeExternalFactory(options));
    // TODO no resource, avoid fail on missing resources while parsing
    parser.setResourcePaths(new String[0]);
    ResourceManager rm = getResourceManager(options);
    parser.setResourceManager(rm);
    String name = scriptFile.getName();
    int lastIndexOf = name.lastIndexOf(RutaEngine.SCRIPT_FILE_EXTENSION);
    if (lastIndexOf != -1) {
      name = name.substring(0, lastIndexOf);
    }
    descInfo.setScriptName(name);
    parser.file_input(name);
    return descInfo;
  }

  public RutaDescriptorInformation parseDescriptorInformation(String script) throws IOException,
          RecognitionException {
    return parseDescriptorInformation(script, new RutaBuildOptions());
  }

  public RutaDescriptorInformation parseDescriptorInformation(String script,
          RutaBuildOptions options) throws IOException, RecognitionException {
    CharStream st = new ANTLRStringStream(script);
    RutaLexer lexer = new RutaLexer(st);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    RutaParser parser = new RutaParser(tokens);
    RutaDescriptorInformation descInfo = new RutaDescriptorInformation();
    parser.setDescriptorInformation(descInfo);
    // TODO no context?
    parser.setContext(null);
    parser.setExternalFactory(initializeExternalFactory(options));
    // TODO no resource, avoid fail on missing resources while parsing
    parser.setResourcePaths(new String[0]);
    ResourceManager rm = getResourceManager(options);
    parser.setResourceManager(rm);
    // TODO use constant
    String name = "Anonymous";
    descInfo.setScriptName(name);
    parser.file_input(name);
    return descInfo;
  }

  private ResourceManager getResourceManager(RutaBuildOptions options) {
    ResourceManager rm = null;
    if(options.getClassLoader() != null) {
      rm = new ResourceManager_impl(options.getClassLoader());
    } else {
      rm = UIMAFramework.newDefaultResourceManager();
    }
    return rm;
  }

  private RutaExternalFactory initializeExternalFactory(RutaBuildOptions options) {
    RutaExternalFactory factory = new RutaExternalFactory();
    List<String> languageExtensions = options.getLanguageExtensions();
    for (String each : languageExtensions) {
      try {
        Class<?> forName = null;
        if (options.getClassLoader() != null) {
        	forName = options.getClassLoader().loadClass(each);
        } else {
          forName = Class.forName(each);
        }
        if (forName != null && IRutaExtension.class.isAssignableFrom(forName)) {
          IRutaExtension extension = (IRutaExtension) forName.newInstance();
          for (String name : extension.getKnownExtensions()) {
            factory.addExtension(name, extension);
          }
        }
      } catch (Exception e) {
        // System.out.println("EXTENSION ERROR: " + each);
      }
    }
    return factory;
  }

  public URL getDefaultTypeSystem() {
    return defaultTypeSystem;
  }

  public void setDefaultTypeSystem(URL defaultTypeSystem) {
    this.defaultTypeSystem = defaultTypeSystem;
  }

  public URL getDefaultEngine() {
    return defaultEngine;
  }

  public void setDefaultEngine(URL defaultEngine) {
    this.defaultEngine = defaultEngine;
  }

}
