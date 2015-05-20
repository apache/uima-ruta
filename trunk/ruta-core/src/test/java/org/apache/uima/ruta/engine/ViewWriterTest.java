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

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.XmiCasDeserializer;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;
import org.junit.Test;
import org.xml.sax.SAXException;

public class ViewWriterTest {

  private static final String TEST_TYPE = "uima.ruta.test.T1";

  private static final String NEW_VIEW = "newView";

  @Test
  public void test() throws IOException, URISyntaxException, InvalidXMLException,
          ResourceInitializationException, ResourceConfigurationException,
          AnalysisEngineProcessException, SAXException {
    URL url = RutaEngine.class.getClassLoader().getResource("ViewWriter.xml");
    if (url == null) {
      url = RutaTestUtils.class.getClassLoader().getResource(
              "org/apache/uima/ruta/engine/ViewWriter.xml");
    }
    File tempFile = File.createTempFile("ViewWriterTest", ".xmi");
    XMLInputSource in = new XMLInputSource(url);
    ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);

    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(specifier);
    ae.setConfigParameterValue(ViewWriter.PARAM_INPUT_VIEW, NEW_VIEW);
    ae.setConfigParameterValue(ViewWriter.PARAM_OUTPUT_VIEW, CAS.NAME_DEFAULT_SOFA);
    ae.setConfigParameterValue(ViewWriter.PARAM_OUTPUT, tempFile.getAbsolutePath());
    ae.reconfigure();

    CAS cas = ae.newCAS();
    cas.setDocumentText("This is the default view.");
    CAS newView = cas.createView(NEW_VIEW);
    newView.setDocumentText("This is a new view.");
    Type type = cas.getTypeSystem().getType(TEST_TYPE);
    AnnotationFS createAnnotation = newView.createAnnotation(type, 5, 7);
    newView.addFsToIndexes(createAnnotation);

    ae.process(cas);

    cas.reset();
    FileInputStream stream = new FileInputStream(tempFile);
    XmiCasDeserializer.deserialize(stream, cas, true);

    assertEquals("This is a new view.", cas.getDocumentText());
    type = cas.getTypeSystem().getType(TEST_TYPE);
    AnnotationIndex<AnnotationFS> ai = cas.getAnnotationIndex(type);
    AnnotationFS next = ai.iterator().next();
    assertEquals("is", next.getCoveredText());

    if (cas != null) {
      cas.release();
    }

    if (newView != null) {
      newView.release();
    }
    stream.close();
    in.close();
    tempFile.delete();
  }

  @Test
  public void testInRuta() throws IOException, URISyntaxException, InvalidXMLException,
          ResourceInitializationException, ResourceConfigurationException,
          AnalysisEngineProcessException, SAXException {
    File xmiOutputFile = File.createTempFile("ViewWriterTest", ".xmi");
    File scriptFile = File.createTempFile("ViewWriterTest", ".ruta");
    StringBuilder scriptSB = new StringBuilder();
    scriptSB.append("ENGINE ViewWriter;\n");
    String absolutePath = xmiOutputFile.getAbsolutePath().replaceAll("\\\\", "/");
    scriptSB.append("CONFIGURE(ViewWriter, \"inputView\" = \"" + NEW_VIEW
            + "\", \"outputView\" = \"_InitialView\", \"output\" = \"" + absolutePath
            + "\"), EXEC(ViewWriter);\n");

    FileUtils.saveString2File(scriptSB.toString(), scriptFile, "UTF-8");
    URL url = RutaEngine.class.getClassLoader().getResource("BasicEngine.xml");
    if (url == null) {
      url = RutaTestUtils.class.getClassLoader().getResource("org/apache/uima/ruta/TestEngine.xml");
    }
    URL urlDesc = RutaEngine.class.getClassLoader().getResource("ViewWriter.xml");
    if (urlDesc == null) {
      urlDesc = RutaTestUtils.class.getClassLoader().getResource(
              "org/apache/uima/ruta/engine/ViewWriter.xml");
    }
    File descFile = new File(urlDesc.toURI());

    XMLInputSource in = new XMLInputSource(url);
    ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);
    AnalysisEngineDescription aed = (AnalysisEngineDescription) specifier;
    TypeSystemDescription tsd = aed.getAnalysisEngineMetaData().getTypeSystem();
    tsd.addType(TEST_TYPE, "Type for Testing", "uima.tcas.Annotation");

    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(specifier);
    String module = scriptFile.getName().substring(0, scriptFile.getName().length() - 5);
    ae.setConfigParameterValue(RutaEngine.PARAM_MAIN_SCRIPT, module);
    ae.setConfigParameterValue(RutaEngine.PARAM_SCRIPT_PATHS, new String[] { scriptFile.getParentFile()
            .getAbsolutePath() });
    String aeName = descFile.getName().substring(0, descFile.getName().length() - 4);
    ae.setConfigParameterValue(RutaEngine.PARAM_ADDITIONAL_ENGINES, new String[] { aeName });
    ae.setConfigParameterValue(RutaEngine.PARAM_DESCRIPTOR_PATHS, new String[] { descFile.getParentFile()
            .getAbsolutePath() });
    ae.reconfigure();

    CAS cas = ae.newCAS();
    cas.setDocumentText("This is the default view.");
    CAS newView = cas.createView(NEW_VIEW);
    newView.setDocumentText("This is a new view.");
    Type type = cas.getTypeSystem().getType(TEST_TYPE);
    AnnotationFS createAnnotation = newView.createAnnotation(type, 10, 13);
    newView.addFsToIndexes(createAnnotation);

    ae.process(cas);

    cas.reset();
    FileInputStream stream = new FileInputStream(xmiOutputFile);
    XmiCasDeserializer.deserialize(stream, cas, true);

    assertEquals("This is a new view.", cas.getDocumentText());
    type = cas.getTypeSystem().getType(TEST_TYPE);
    AnnotationIndex<AnnotationFS> ai = cas.getAnnotationIndex(type);
    assertEquals(1, ai.size());
    AnnotationFS next = ai.iterator().next();
    assertEquals("new", next.getCoveredText());

    if (cas != null) {
      cas.release();
    }
    if (newView != null) {
      newView.release();
    }
    stream.close();
    in.close();
    xmiOutputFile.delete();
    scriptFile.delete();
  }

}
