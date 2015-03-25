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

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;
import org.sonatype.plexus.build.incremental.BuildContext;


/**
 * Generate descriptors from UIMA Ruta script files.
 *
 */
@Mojo(name = "generate", defaultPhase = LifecyclePhase.PROCESS_RESOURCES, requiresDependencyResolution = ResolutionScope.COMPILE)
public class RutaGenerateDescriptorMojo extends AbstractMojo {
  @Component
  private MavenProject project;

  @Component
  private BuildContext buildContext;

  /**
   * The directory where the generated type system descriptors will be written.
   */
  @Parameter(defaultValue = "${project.build.directory}/generated-sources/ruta/descriptor", required = true)
  private File typeSystemOutputDirectory;
  
  /**
   * The directory where the generated analysis engine descriptors will be written.
   */
  @Parameter(defaultValue = "${project.build.directory}/generated-sources/ruta/descriptor", required = true)
  private File analysisEngineOutputDirectory;


  public void execute() throws MojoExecutionException, MojoFailureException {
    
  }

  
}
