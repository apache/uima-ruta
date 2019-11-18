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
package org.apache.uima.ruta.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.internal.ResourceManagerFactory;
import org.apache.uima.fit.internal.ResourceManagerFactory.ResourceManagerCreator;
import org.apache.uima.fit.util.CasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.ruta.RutaProcessRuntimeException;
import org.apache.uima.ruta.TypeUsageInformation;
import org.apache.uima.ruta.descriptor.RutaBuildOptions;
import org.apache.uima.ruta.descriptor.RutaDescriptorFactory;
import org.apache.uima.ruta.descriptor.RutaDescriptorInformation;
import org.junit.Assert;
import org.junit.Test;

public class RutaEngineTest {

  @Test
  public void testTypeUsageInformation() throws Exception {
    String script = "TYPE typeVar;\n";
    script += "CW{-> FalsePositive};\n";
    script += "RETAINTYPE(WS);\n";
    script += "DECLARE Type1;\n";
    script += "org.apache.uima.ruta.type.FalseNegative;\n";
    script += "BLOCK(block) Document{}{\n";
    script += "DECLARE Type2;\n";
    script += "SW.ct==\"a\"{-> CREATE(RutaAnnotation, \"score\"= W.begin)};\n";
    script += "}\n";
    AnalysisEngine ae = AnalysisEngineFactory.createEngine(RutaEngine.class,
            RutaEngine.PARAM_VAR_NAMES, new String[] { "typeVar" }, RutaEngine.PARAM_VAR_VALUES,
            new String[] { "TruePositive" }, RutaEngine.PARAM_RULES, script,
            RutaEngine.PARAM_INDEX_ONLY_MENTIONED_TYPES, Boolean.valueOf(true),
            RutaEngine.PARAM_INDEX_ADDITONALLY, new String[] { "COMMA" });
    RutaEngine engine = (RutaEngine) FieldUtils.readField(ae, "mAnalysisComponent", true);

    TypeUsageInformation typeUsageInformation = (TypeUsageInformation) FieldUtils.readField(engine,
            "typeUsageInformation", true);

    List<String> mentionedTypes = typeUsageInformation.getMentionedTypes();
    Collections.sort(mentionedTypes);
    Assert.assertEquals(
            Arrays.asList("COMMA", "CW", "Document", "Document", "SW", "SW.ct", "TruePositive", "W",
                    "W.begin", "WS", "org", "org.apache", "org.apache.uima", "org.apache.uima.ruta",
                    "org.apache.uima.ruta.type", "org.apache.uima.ruta.type.BREAK",
                    "org.apache.uima.ruta.type.FalseNegative", "org.apache.uima.ruta.type.MARKUP",
                    "org.apache.uima.ruta.type.SPACE", "uima.tcas.DocumentAnnotation"),
            mentionedTypes);

    CAS cas = RutaTestUtils.getCAS("This is a test.");
    JCas jcas = cas.getJCas();
    ae.process(jcas);

    Collection<String> usedTypes = typeUsageInformation.getUsedTypes();
    List<String> usedTypesList = new ArrayList<>(usedTypes);
    Collections.sort(usedTypesList);
    Assert.assertEquals(Arrays.asList("org.apache.uima.ruta.type.BREAK",
            "org.apache.uima.ruta.type.COMMA", "org.apache.uima.ruta.type.CW",
            "org.apache.uima.ruta.type.FalseNegative", "org.apache.uima.ruta.type.MARKUP",
            "org.apache.uima.ruta.type.SPACE", "org.apache.uima.ruta.type.SW",
            "org.apache.uima.ruta.type.TruePositive", "org.apache.uima.ruta.type.W",
            "org.apache.uima.ruta.type.WS", "uima.tcas.DocumentAnnotation"), usedTypesList);

  }

  @Test(expected = NotImplementedException.class)
  public void testInferenceVisitors() throws Throwable {

    String document = "Some text.";
    String script = "CW;";

    AnalysisEngine ae = AnalysisEngineFactory.createEngine(RutaEngine.class, RutaEngine.PARAM_RULES,
            script, RutaEngine.PARAM_INFERENCE_VISITORS,
            new String[] { TestRutaInferenceVisitor.class.getName() });

    CAS cas = RutaTestUtils.getCAS(document);

    try {
      ae.process(cas);
    } catch (AnalysisEngineProcessException e) {
      Throwable cause = e.getCause();
      throw cause;
    }
  }

  @Test
  public void testMaxRuleMatches() throws Throwable {

    String document = "This is some text.";
    String script = "W;";

    AnalysisEngine ae = AnalysisEngineFactory.createEngine(RutaEngine.class, RutaEngine.PARAM_RULES,
            script, RutaEngine.PARAM_MAX_RULE_MATCHES, Integer.valueOf(2));

    CAS cas = RutaTestUtils.getCAS(document);

    try {
      ae.process(cas);
    } catch (AnalysisEngineProcessException e) {
      Throwable cause = e.getCause();
      Assert.assertTrue(cause instanceof RutaProcessRuntimeException);
      String message = cause.getMessage();
      Assert.assertTrue(message.startsWith("Rule exceeded the allowed amount of matches"));
      return;
    }
    Assert.fail("expected RutaProcessRuntimeException");
  }

  @Test
  public void testMaxRuleElementMatches() throws Throwable {

    String document = "This is some text.";
    String script = "W+;";

    AnalysisEngine ae = AnalysisEngineFactory.createEngine(RutaEngine.class, RutaEngine.PARAM_RULES,
            script, RutaEngine.PARAM_MAX_RULE_ELEMENT_MATCHES, Integer.valueOf(2));

    CAS cas = RutaTestUtils.getCAS(document);

    try {
      ae.process(cas);
    } catch (AnalysisEngineProcessException e) {
      Throwable cause = e.getCause();
      Assert.assertTrue(cause instanceof RutaProcessRuntimeException);
      String message = cause.getMessage();
      Assert.assertTrue(message.startsWith("Rule element exceeded the allowed amount of matches"));
      return;
    }
    Assert.fail("expected RutaProcessRuntimeException");
  }

  @Test
  public void testResourceManagerWithUimaFITInEXEC() throws Throwable {

    final ResourceManager resourceManager = ResourceManagerFactory.newResourceManager();
    ResourceManagerFactory.setResourceManagerCreator(new ResourceManagerCreator() {

      @Override
      public ResourceManager newResourceManager() throws ResourceInitializationException {
        return resourceManager;
      }
    });

    String document = "This is some text.";
    String script = "UIMAFIT org.apache.uima.ruta.engine.DummyAnnotator;";
    script += "EXEC(DummyAnnotator);";

    AnalysisEngine ae = AnalysisEngineFactory.createEngine(RutaEngine.class, RutaEngine.PARAM_RULES,
            script, RutaEngine.PARAM_ADDITIONAL_UIMAFIT_ENGINES,
            new String[] { DummyAnnotator.class.getName() });

    CAS cas = RutaTestUtils.getCAS(document);
    ae.process(cas);
  }

  @Test
  public void testProcessWithRulesDeclareWithoutPackage() throws Throwable {

    String rules = "DECLARE MyType; SW{-> MyType};";
    String text = "This is a test";

    RutaDescriptorFactory rdf = new RutaDescriptorFactory();
    RutaBuildOptions buildOptions = new RutaBuildOptions();
    RutaDescriptorInformation descriptorInformation = rdf.parseDescriptorInformation(rules, "",
            buildOptions);
    Pair<AnalysisEngineDescription, TypeSystemDescription> descriptions = rdf
            .createDescriptions(null, null, descriptorInformation, buildOptions, null, null, null);

    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(descriptions.getLeft());
    CAS cas = ae.newCAS();
    cas.setDocumentText(text);

    ae.process(cas);

    Type type = cas.getTypeSystem().getType("MyType");
    Assert.assertNotNull(type);
    Collection<AnnotationFS> select = CasUtil.select(cas, type);
    Assert.assertEquals(3, select.size());

  }

  @Test
  public void testProcessWithRulesDeclareWithPackage() throws Throwable {

    String rules = "PACKAGE pack; DECLARE MyType; SW{-> MyType};";
    String text = "This is a test";

    RutaDescriptorFactory rdf = new RutaDescriptorFactory();
    RutaBuildOptions buildOptions = new RutaBuildOptions();
    RutaDescriptorInformation descriptorInformation = rdf.parseDescriptorInformation(rules, "",
            buildOptions);
    Pair<AnalysisEngineDescription, TypeSystemDescription> descriptions = rdf
            .createDescriptions(null, null, descriptorInformation, buildOptions, null, null, null);

    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(descriptions.getLeft());
    CAS cas = ae.newCAS();
    cas.setDocumentText(text);

    ae.process(cas);

    Type type = cas.getTypeSystem().getType("pack.MyType");
    Assert.assertNotNull(type);
    Collection<AnnotationFS> select = CasUtil.select(cas, type);
    Assert.assertEquals(3, select.size());

  }

}
