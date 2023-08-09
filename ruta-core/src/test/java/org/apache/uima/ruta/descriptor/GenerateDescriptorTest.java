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

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.fit.util.CasUtil;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.resource.impl.ResourceManager_impl;
import org.apache.uima.resource.metadata.ConfigurationParameterSettings;
import org.apache.uima.resource.metadata.FeatureDescription;
import org.apache.uima.resource.metadata.TypeDescription;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.ruta.engine.HtmlAnnotator;
import org.apache.uima.ruta.engine.RutaEngine;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class GenerateDescriptorTest {

  private static URL basicAEUrl;

  private static URL basicTSUrl;

  @BeforeAll
  public static void setUpClass() {

    GenerateDescriptorTest.basicAEUrl = GenerateDescriptorTest.class.getClassLoader()
            .getResource("BasicEngine.xml");
    if (GenerateDescriptorTest.basicAEUrl == null) {
      GenerateDescriptorTest.basicAEUrl = HtmlAnnotator.class.getClassLoader()
              .getResource("org/apache/uima/ruta/engine/BasicEngine.xml");
    }

    GenerateDescriptorTest.basicTSUrl = GenerateDescriptorTest.class.getClassLoader()
            .getResource("BasicTypeSystem.xml");
    if (GenerateDescriptorTest.basicTSUrl == null) {
      GenerateDescriptorTest.basicTSUrl = HtmlAnnotator.class.getClassLoader()
              .getResource("org/apache/uima/ruta/engine/BasicTypeSystem.xml");
    }
  }

  @Test
  public void testCreateAnalysisEngineDescription() throws Exception {

    String script = "";
    script += "PACKAGE test.package;\n";
    script += "ENGINE org.apache.uima.ruta.engine.HtmlAnnotator;\n";
    script += "UIMAFIT org.apache.uima.ruta.engine.PlainTextAnnotator;\n";
    script += "SCRIPT org.apache.uiima.ruta.Additional;\n";

    RutaDescriptorFactory rdf = new RutaDescriptorFactory(GenerateDescriptorTest.basicTSUrl,
            GenerateDescriptorTest.basicAEUrl);
    RutaDescriptorInformation descriptorInformation = rdf.parseDescriptorInformation(script);
    RutaBuildOptions options = new RutaBuildOptions();
    String typeSystemOutput = "target/temp/testCreateAnalysisEngineDescription_TypeSystem.xml";
    ClassLoader classLoader = GenerateDescriptorTest.class.getClassLoader();
    AnalysisEngineDescription aed = rdf.createAnalysisEngineDescription(typeSystemOutput,
            descriptorInformation, options, null, null, null, classLoader);

    ConfigurationParameterSettings cps = aed.getAnalysisEngineMetaData()
            .getConfigurationParameterSettings();

    String mainScript = (String) cps.getParameterValue(RutaEngine.PARAM_MAIN_SCRIPT);
    String rules = (String) cps.getParameterValue(RutaEngine.PARAM_RULES);
    assertThat(mainScript).as("mainScript param should be null").isNull();
    assertThat(rules).as("rules param should not null").isNotNull();

    String[] additionalEngines = (String[]) cps
            .getParameterValue(RutaEngine.PARAM_ADDITIONAL_ENGINES);
    assertThat(additionalEngines).isNotNull();
    assertThat(additionalEngines[0]).isEqualTo("org.apache.uima.ruta.engine.HtmlAnnotator");

    String[] additionalUimafitEngines = (String[]) cps
            .getParameterValue(RutaEngine.PARAM_ADDITIONAL_UIMAFIT_ENGINES);
    assertThat(additionalUimafitEngines).isNotNull();
    assertThat(additionalUimafitEngines[0])
            .isEqualTo("org.apache.uima.ruta.engine.PlainTextAnnotator");

    String[] additionalScripts = (String[]) cps
            .getParameterValue(RutaEngine.PARAM_ADDITIONAL_SCRIPTS);
    assertThat(additionalScripts).isNotNull();
    assertThat(additionalScripts[0]).isEqualTo("org.apache.uiima.ruta.Additional");

  }

  @Test
  public void testCreateTypeSystemDescription() throws Exception {

    String script = "";
    script += "PACKAGE test.package;\n";
    script += "TYPESYSTEM org.apache.uima.ruta.engine.HtmlTypeSystem;\n";
    script += "DECLARE SimpleType;\n";
    script += "DECLARE SimpleType ComplexType (SimpleType fs, STRING s, BOOLEAN b, INT i);\n";
    script += "DECLARE ComplexType2 (SimpleType fs, STRING s, BOOLEAN b, INT i);\n";
    script += "BLOCK(sub) Document{}{\n";
    script += "DECLARE InnerType;\n";
    script += "}\n";

    RutaDescriptorFactory rdf = new RutaDescriptorFactory(GenerateDescriptorTest.basicTSUrl,
            GenerateDescriptorTest.basicAEUrl);
    RutaDescriptorInformation descriptorInformation = rdf.parseDescriptorInformation(script);
    RutaBuildOptions options = new RutaBuildOptions();
    String typeSystemOutput = "target/temp/testCreateTypeSystemDescription_TypeSystem.xml";
    ClassLoader classLoader = GenerateDescriptorTest.class.getClassLoader();
    TypeSystemDescription tsd = rdf.createTypeSystemDescription(typeSystemOutput,
            descriptorInformation, options, classLoader);
    ResourceManager rm = new ResourceManager_impl(classLoader);
    tsd.resolveImports(rm);

    TypeDescription tagType = tsd.getType("org.apache.uima.ruta.type.html.TAG");
    assertThat(tagType).isNotNull();

    TypeDescription simpleType = tsd.getType("test.package.Anonymous.SimpleType");
    assertThat(simpleType).isNotNull();
    assertThat(simpleType.getSupertypeName()).isEqualTo("uima.tcas.Annotation");

    TypeDescription complexType = tsd.getType("test.package.Anonymous.ComplexType");
    assertThat(complexType).isNotNull();
    assertThat(complexType.getDescription()).isEqualTo("Type defined in test.package.Anonymous");
    assertThat(complexType.getSupertypeName()).isEqualTo("test.package.Anonymous.SimpleType");
    FeatureDescription[] features = complexType.getFeatures();
    Map<String, String> featureMap = new HashMap<>();
    featureMap.put("fs", "test.package.Anonymous.SimpleType");
    featureMap.put("s", "uima.cas.String");
    featureMap.put("b", "uima.cas.Boolean");
    featureMap.put("i", "uima.cas.Integer");
    assertThat(features).hasSize(4);
    for (FeatureDescription each : features) {
      String f = featureMap.get(each.getName());
      assertThat(f).isNotNull();
    }

    TypeDescription complexType2 = tsd.getType("test.package.Anonymous.ComplexType2");
    assertThat(complexType2).isNotNull();
    assertThat(complexType2.getDescription()).isEqualTo("Type defined in test.package.Anonymous");
    assertThat(complexType2.getSupertypeName()).isEqualTo("uima.tcas.Annotation");
    FeatureDescription[] features2 = complexType2.getFeatures();
    Map<String, String> featureMap2 = new HashMap<>();
    featureMap2.put("fs", "test.package.Anonymous.SimpleType");
    featureMap2.put("s", "uima.cas.String");
    featureMap2.put("b", "uima.cas.Boolean");
    featureMap2.put("i", "uima.cas.Integer");
    assertThat(features2).hasSize(4);
    for (FeatureDescription each : features2) {
      String f = featureMap2.get(each.getName());
      assertThat(f).isNotNull();
    }

    TypeDescription innerType = tsd.getType("test.package.Anonymous.sub.InnerType");
    assertThat(innerType).isNotNull();
    assertThat(innerType.getSupertypeName()).isEqualTo("uima.tcas.Annotation");

  }

  @Test
  public void testTypeWithRuleScriptNameWithPackage() throws Exception {

    String script = "";
    script += "PACKAGE test.package;\n";
    script += "DECLARE SimpleType;\n";

    RutaDescriptorFactory rdf = new RutaDescriptorFactory(GenerateDescriptorTest.basicTSUrl,
            GenerateDescriptorTest.basicAEUrl);
    RutaBuildOptions options = new RutaBuildOptions();
    RutaDescriptorInformation descriptorInformation = rdf.parseDescriptorInformation(script, null,
            options);
    String typeSystemOutput = "target/temp/testTypeWithRuleScriptNameWithPackage_TypeSystem.xml";
    ClassLoader classLoader = GenerateDescriptorTest.class.getClassLoader();
    TypeSystemDescription tsd = rdf.createTypeSystemDescription(typeSystemOutput,
            descriptorInformation, options, classLoader);
    ResourceManager rm = new ResourceManager_impl(classLoader);
    tsd.resolveImports(rm);

    TypeDescription tagType = tsd.getType("test.package.SimpleType");
    assertThat(tagType).isNotNull();

  }

  @Test
  public void testTypeWithRuleScriptNameWithoutPackage() throws Exception {

    String script = "";
    script += "DECLARE SimpleType;\n";

    RutaDescriptorFactory rdf = new RutaDescriptorFactory(GenerateDescriptorTest.basicTSUrl,
            GenerateDescriptorTest.basicAEUrl);
    RutaBuildOptions options = new RutaBuildOptions();
    RutaDescriptorInformation descriptorInformation = rdf.parseDescriptorInformation(script, null,
            options);
    String typeSystemOutput = "target/temp/testTypeWithRuleScriptNameWithoutPackage_TypeSystem.xml";
    ClassLoader classLoader = GenerateDescriptorTest.class.getClassLoader();
    TypeSystemDescription tsd = rdf.createTypeSystemDescription(typeSystemOutput,
            descriptorInformation, options, classLoader);
    ResourceManager rm = new ResourceManager_impl(classLoader);
    tsd.resolveImports(rm);

    TypeDescription tagType = tsd.getType("SimpleType");
    assertThat(tagType).isNotNull();

  }

  @Test
  public void testScriptOnly() throws Exception {

    String script = "DECLARE Type; CW{-> Type};";

    RutaDescriptorFactory rdf = new RutaDescriptorFactory(GenerateDescriptorTest.basicTSUrl,
            GenerateDescriptorTest.basicAEUrl);
    RutaDescriptorInformation descriptorInformation = rdf.parseDescriptorInformation(script);
    RutaBuildOptions options = new RutaBuildOptions();
    Pair<AnalysisEngineDescription, TypeSystemDescription> descriptions = rdf
            .createDescriptions(null, null, descriptorInformation, options, null, null, null);
    assertThat(descriptions.getValue().getType("Anonymous.Type"))
            .as("Typesystem does not contain declaredtype!").isNotNull();
    assertThat(descriptions.getKey().getAnalysisEngineMetaData().getTypeSystem()
            .getType("Anonymous.Type")).as("AE typesystem does not contain declared type!")
                    .isNotNull();
    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(descriptions.getKey());
    CAS cas = ae.newCAS();
    cas.setDocumentText("This is a test.");
    ae.process(cas);
    Type type = cas.getTypeSystem().getType("Anonymous.Type");
    assertThat(CasUtil.select(cas, type)).hasSize(1);
    cas.release();
  }

}
