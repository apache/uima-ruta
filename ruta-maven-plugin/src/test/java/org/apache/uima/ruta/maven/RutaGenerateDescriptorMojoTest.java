/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.uima.ruta.maven;

import java.io.File;

import org.apache.maven.execution.DefaultMavenExecutionRequest;
import org.apache.maven.execution.MavenExecutionRequest;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.ProjectBuilder;
import org.apache.maven.project.ProjectBuildingRequest;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.util.XMLInputSource;
import org.codehaus.plexus.util.FileUtils;

import junit.framework.Assert;


public class RutaGenerateDescriptorMojoTest extends AbstractMojoTestCase {

  public void testSimple() throws Exception {
    MavenProject project = build("simple");
    
    File descDirectory = new File(project.getBasedir(), "target/generated-sources/ruta/descriptor");
    File aeFile = new File(descDirectory, "SimpleEngine.xml");
    Assert.assertTrue(aeFile.exists());
    File tsFile = new File(descDirectory, "SimpleTypeSystem.xml");
    Assert.assertTrue(tsFile.exists());
    
    AnalysisEngineDescription aed = UIMAFramework.getXMLParser().parseAnalysisEngineDescription(new XMLInputSource(aeFile));
    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(aed);
    
    CAS cas = ae.newCAS();
    cas.setDocumentText("This is a test.");
    ae.process(cas);
    
    Type type = cas.getTypeSystem().getType("Simple.MyType");
    AnnotationIndex<AnnotationFS> ai = cas.getAnnotationIndex(type);
    assertEquals(1, ai.size());
    assertEquals("is", ai.iterator().next().getCoveredText());
    
    cas.release();
    
  }

  
  
  public MavenProject build(String projectName) throws Exception {

    File projectSourceDirectory = getTestFile("src/test/resources/" + projectName);
    File projectDirectory = getTestFile("target/project-" + projectName + "-test");

    // Stage project to target folder
    FileUtils.copyDirectoryStructure(projectSourceDirectory, projectDirectory);
    
    File pomFile = new File(projectDirectory, "/pom.xml");
    assertNotNull(pomFile);
    assertTrue(pomFile.exists());

    // create the MavenProject from the pom.xml file
    MavenExecutionRequest executionRequest = new DefaultMavenExecutionRequest();
    ProjectBuildingRequest buildingRequest = executionRequest.getProjectBuildingRequest();
    ProjectBuilder projectBuilder = this.lookup(ProjectBuilder.class);
    MavenProject project = projectBuilder.build(pomFile, buildingRequest).getProject();
    assertNotNull(project);

    // copy resources
    File resource = new File(projectDirectory, "src/main/resources");
    if (resource.exists()) {
      FileUtils.copyDirectoryStructure(resource, new File(project.getBuild().getOutputDirectory()));
    }
    
    File ruta = new File(projectDirectory, "src/main/ruta");
    if (ruta.exists()) {
      FileUtils.copyDirectoryStructure(ruta, new File(project.getBuild().getOutputDirectory()));
    }
    
    // load the Mojo
    RutaGenerateDescriptorMojo generate = (RutaGenerateDescriptorMojo) this.lookupConfiguredMojo(project, "generate");
    assertNotNull(generate);

    // set the MavenProject on the Mojo (AbstractMojoTestCase does not do this by default)
    setVariableValueToObject(generate, "project", project);

    // execute the Mojo
    generate.execute();
    return project;
  }
}