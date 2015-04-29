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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.antlr.runtime.RecognitionException;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.resource.impl.ResourceManager_impl;
import org.apache.uima.resource.metadata.ConfigurationParameterSettings;
import org.apache.uima.resource.metadata.FeatureDescription;
import org.apache.uima.resource.metadata.TypeDescription;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.ruta.engine.HtmlAnnotator;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.util.InvalidXMLException;
import org.junit.BeforeClass;
import org.junit.Test;

public class GenerateDescriptorTest {

  private static URL basicAEUrl;

  private static URL basicTSUrl;

  @BeforeClass
  public static void setUpClass() {
    basicAEUrl = HtmlAnnotator.class.getClassLoader().getResource("BasicEngine.xml");
    if (basicAEUrl == null) {
      basicAEUrl = HtmlAnnotator.class.getClassLoader().getResource(
              "org/apache/uima/ruta/engine/BasicEngine.xml");
    }

    basicTSUrl = HtmlAnnotator.class.getClassLoader().getResource("BasicTypeSystem.xml");
    if (basicTSUrl == null) {
      basicTSUrl = HtmlAnnotator.class.getClassLoader().getResource(
              "org/apache/uima/ruta/engine/BasicTypeSystem.xml");
    }
  }

  @Test
  public void testCreateAnalysisEngineDescription() throws InvalidXMLException, IOException,
          RecognitionException, URISyntaxException {
    String script = "";
    script += "PACKAGE test.package;\n";
    script += "ENGINE org.apache.uima.ruta.engine.HtmlAnnotator;\n";
    script += "UIMAFIT org.apache.uima.ruta.engine.PlainTextAnnotator;\n";
    script += "SCRIPT org.apache.uiima.ruta.Additional;\n";

    RutaDescriptorFactory rdf = new RutaDescriptorFactory(basicTSUrl, basicAEUrl);
    RutaDescriptorInformation descriptorInformation = rdf.parseDescriptorInformation(script);
    RutaBuildOptions options = new RutaBuildOptions();
    String typeSystemOutput = new File(basicTSUrl.toURI()).getAbsolutePath();
    ClassLoader classLoader = GenerateDescriptorTest.class.getClassLoader();
    AnalysisEngineDescription aed = rdf.createAnalysisEngineDescription(typeSystemOutput,
            descriptorInformation, options, null, null, null, classLoader);

    ConfigurationParameterSettings cps = aed.getAnalysisEngineMetaData()
            .getConfigurationParameterSettings();

    String mainScript = (String) cps.getParameterValue(RutaEngine.PARAM_MAIN_SCRIPT);
    assertEquals("test.package.Anonymous", mainScript);
    
    String[] additionalEngines = (String[]) cps.getParameterValue(RutaEngine.PARAM_ADDITIONAL_ENGINES);
    assertNotNull(additionalEngines);
    assertEquals("org.apache.uima.ruta.engine.HtmlAnnotator", additionalEngines[0]);
    
    String[] additionalUimafitEngines = (String[]) cps.getParameterValue(RutaEngine.PARAM_ADDITIONAL_UIMAFIT_ENGINES);
    assertNotNull(additionalUimafitEngines);
    assertEquals("org.apache.uima.ruta.engine.PlainTextAnnotator", additionalUimafitEngines[0]);
    
    String[] additionalScripts = (String[]) cps.getParameterValue(RutaEngine.PARAM_ADDITIONAL_SCRIPTS);
    assertNotNull(additionalScripts);
    assertEquals("org.apache.uiima.ruta.Additional", additionalScripts[0]);
    
  }

  @Test
  public void testCreateTypeSystemDescription() throws URISyntaxException, IOException,
          RecognitionException, InvalidXMLException, ResourceInitializationException {
    String script = "";
    script += "PACKAGE test.package;\n";
    script += "TYPESYSTEM org.apache.uima.ruta.engine.HtmlTypeSystem;\n";
    script += "DECLARE SimpleType;\n";
    script += "DECLARE SimpleType ComplexType (SimpleType fs, STRING s, BOOLEAN b, INT i);\n";
    script += "DECLARE ComplexType2 (SimpleType fs, STRING s, BOOLEAN b, INT i);\n";
    script += "BLOCK(sub) Document{}{\n";
    script += "DECLARE InnerType;\n";
    script += "}\n";

    RutaDescriptorFactory rdf = new RutaDescriptorFactory(basicTSUrl, basicAEUrl);
    RutaDescriptorInformation descriptorInformation = rdf.parseDescriptorInformation(script);
    RutaBuildOptions options = new RutaBuildOptions();
    String typeSystemOutput = new File(basicTSUrl.toURI()).getAbsolutePath();
    ClassLoader classLoader = GenerateDescriptorTest.class.getClassLoader();
    TypeSystemDescription tsd = rdf.createTypeSystemDescription(typeSystemOutput,
            descriptorInformation, options, classLoader);
    ResourceManager rm = new ResourceManager_impl(classLoader);
    tsd.resolveImports(rm);

    TypeDescription tagType = tsd.getType("org.apache.uima.ruta.type.html.TAG");
    assertNotNull(tagType);

    TypeDescription simpleType = tsd.getType("test.package.Anonymous.SimpleType");
    assertNotNull(simpleType);
    assertEquals("uima.tcas.Annotation", simpleType.getSupertypeName());

    TypeDescription complexType = tsd.getType("test.package.Anonymous.ComplexType");
    assertNotNull(complexType);
    assertEquals("Type defined in test.package.Anonymous", complexType.getDescription());
    assertEquals("test.package.Anonymous.SimpleType", complexType.getSupertypeName());
    FeatureDescription[] features = complexType.getFeatures();
    Map<String, String> featureMap = new HashMap<>();
    featureMap.put("fs", "test.package.Anonymous.SimpleType");
    featureMap.put("s", "uima.cas.String");
    featureMap.put("b", "uima.cas.Boolean");
    featureMap.put("i", "uima.cas.Integer");
    assertEquals(4, features.length);
    for (FeatureDescription each : features) {
      String f = featureMap.get(each.getName());
      assertNotNull(f);
    }
    
    TypeDescription complexType2 = tsd.getType("test.package.Anonymous.ComplexType2");
    assertNotNull(complexType2);
    assertEquals("Type defined in test.package.Anonymous", complexType2.getDescription());
    assertEquals("uima.tcas.Annotation", complexType2.getSupertypeName());
    FeatureDescription[] features2 = complexType2.getFeatures();
    Map<String, String> featureMap2 = new HashMap<>();
    featureMap2.put("fs", "test.package.Anonymous.SimpleType");
    featureMap2.put("s", "uima.cas.String");
    featureMap2.put("b", "uima.cas.Boolean");
    featureMap2.put("i", "uima.cas.Integer");
    assertEquals(4, features2.length);
    for (FeatureDescription each : features2) {
      String f = featureMap2.get(each.getName());
      assertNotNull(f);
    }

    TypeDescription innerType = tsd.getType("test.package.Anonymous.sub.InnerType");
    assertNotNull(innerType);
    assertEquals("uima.tcas.Annotation", innerType.getSupertypeName());

  }

}
