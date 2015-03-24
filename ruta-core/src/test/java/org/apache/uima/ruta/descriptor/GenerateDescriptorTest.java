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

import java.net.URISyntaxException;
import java.net.URL;

import org.apache.uima.ruta.engine.HtmlAnnotator;
import org.junit.BeforeClass;
import org.junit.Test;

public class GenerateDescriptorTest {

//  private static URL basicAEUrl;
//  private static URL basicTSUrl;
//
//  @BeforeClass
//  public static void setUpClass() {
//    basicAEUrl = HtmlAnnotator.class.getClassLoader().getResource("BasicEngine.xml");
//    if (basicAEUrl == null) {
//      basicAEUrl = HtmlAnnotator.class.getClassLoader().getResource(
//              "org/apache/uima/ruta/engine/BasicEngine.xml");
//    }
//    
//    basicTSUrl = HtmlAnnotator.class.getClassLoader().getResource("BasicTypeSystem.xml");
//    if (basicTSUrl == null) {
//      basicTSUrl = HtmlAnnotator.class.getClassLoader().getResource(
//              "org/apache/uima/ruta/engine/BasicTypeSystem.xml");
//    }
//  }
//  
//  
//  @Test
//  public void testAnalysisEngineDescriptor() {
//    
//  }
//  
//  @Test
//  public void testTypeSystemDescriptor() throws URISyntaxException {
//    String script = "";
//    script += "PACKAGE test.package;\n";
//    script += "TYPESYSTEM HtmlTypeSystem;\n";
//    script += "DECLARE SimpleType;\n";
//    script += "DECLARE SimpleType ComplexType (SimpleType fs, STRING s, BOOLEAN b, INT i);\n";
//    script += "BLOCK(sub) Document{}{\n";
//    script += "DECLARE InnerType;\n";
//    script += "}\n";
//    
//    RutaDescriptorFactory rdf = new RutaDescriptorFactory(basicTSUrl, basicAEUrl);
//    RutaDescriptorInformation descriptorInformation = rdf.parseDescriptorInformation(script);
//    RutaBuildOptions options = new RutaBuildOptions();
//    rdf.createTypeSystemDescription(typeSystemOutput, descriptorInformation, options, GenerateDescriptorTest.class.getClassLoader())
//    
//    
//  }
//  
  
}
