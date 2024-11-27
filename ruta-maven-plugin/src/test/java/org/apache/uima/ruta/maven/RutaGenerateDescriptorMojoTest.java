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

import static org.assertj.core.api.Assertions.assertThat;
import static org.codehaus.plexus.util.FileUtils.copyDirectoryStructure;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.apache.maven.plugin.testing.MojoRule;
import org.apache.maven.project.MavenProject;
import org.apache.uima.UIMAFramework;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.util.XMLInputSource;
import org.junit.Rule;
import org.junit.Test;

public class RutaGenerateDescriptorMojoTest {

  public @Rule MojoRule rule = new MojoRule();

  @Test
  public void testSimple() throws Exception {
    MavenProject project = build("simple");

    var descDirectory = new File(project.getBasedir(), "target/generated-sources/ruta/descriptor");
    var aeFile = new File(descDirectory, "SimpleEngine.xml");
    assertThat(aeFile).exists();

    var tsFile = new File(descDirectory, "SimpleTypeSystem.xml");
    assertThat(tsFile).exists();

    var aed = UIMAFramework.getXMLParser()
            .parseAnalysisEngineDescription(new XMLInputSource(aeFile));
    var ae = UIMAFramework.produceAnalysisEngine(aed);

    var cas = ae.newCAS();
    cas.setDocumentText("This is a test.");
    ae.process(cas);

    assertThat(cas.<Annotation> select("Simple.MyType").asList()) //
            .extracting(Annotation::getCoveredText) //
            .containsExactly("is");
  }

  public MavenProject build(String projectName) throws Exception {

    var projectSourceDirectory = new File("src/test/resources/" + projectName);
    var projectDirectory = new File("target/project-" + projectName + "-test");

    // Stage project to target folder
    copyDirectoryStructure(projectSourceDirectory, projectDirectory);

    // create the MavenProject from the pom.xml file
    var project = rule.readMavenProject(projectDirectory);
    assertNotNull(project);

    // copy resources
    var resource = new File(projectDirectory, "src/main/resources");
    if (resource.exists()) {
      copyDirectoryStructure(resource, new File(project.getBuild().getOutputDirectory()));
    }

    var ruta = new File(projectDirectory, "src/main/ruta");
    if (ruta.exists()) {
      copyDirectoryStructure(ruta, new File(project.getBuild().getOutputDirectory()));
    }

    // load the Mojo
    var generate = (RutaGenerateDescriptorMojo) rule.lookupConfiguredMojo(project, "generate");
    assertNotNull(generate);

    // set the MavenProject on the Mojo (AbstractMojoTestCase does not do this by default)
    rule.setVariableValueToObject(generate, "project", project);

    // execute the Mojo
    generate.execute();
    return project;
  }
}