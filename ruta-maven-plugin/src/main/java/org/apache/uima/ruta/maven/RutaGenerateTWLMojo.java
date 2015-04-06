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
import java.io.IOException;
import java.util.List;

import org.apache.maven.model.FileSet;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;
import org.apache.uima.ruta.resource.TreeWordList;
import org.sonatype.plexus.build.incremental.BuildContext;

/**
 * Generate descriptors from UIMA Ruta script files.
 * 
 */
@Mojo(name = "twl", defaultPhase = LifecyclePhase.PROCESS_RESOURCES, requiresDependencyResolution = ResolutionScope.COMPILE)
public class RutaGenerateTWLMojo extends AbstractMojo {
  @Component
  private MavenProject project;

  @Component
  private BuildContext buildContext;

  /**
   * The directory where the generated tree word lists will be written.
   */
  @Parameter(defaultValue = "${project.build.directory}/generated-sources/ruta/resources/", required = true)
  private File outputDirectory;

  /**
   * The source files for the tree word list.
   */
  @Parameter(required = true)
  private FileSet inputFiles;

  /**
   * Source file encoding.
   */
  @Parameter(defaultValue = "${project.build.sourceEncoding}", required = true)
  private String encoding;

  /**
   * Compress resulting tree word list.
   */
  @Parameter(defaultValue = "true", required = true)
  private boolean compress;

  public void execute() throws MojoExecutionException, MojoFailureException {
    if (!outputDirectory.exists()) {
      outputDirectory.mkdirs();
      buildContext.refresh(outputDirectory);
    }

    List<File> files = null;
    try {
      files = Utils.getFiles(inputFiles);
    } catch (IOException e) {
      getLog().warn("Error accessing input files.", e);
    }

    for (File file : files) {
      TreeWordList list = null;
      try {
        list = new TreeWordList(file.getAbsolutePath(), false);
      } catch (IOException e) {
        getLog().warn("Error generating twl.", e);
      }
      if (list != null) {
        String outputName = file.getName().substring(0, file.getName().length() - 3) + "twl";
        File outputFile = new File(outputDirectory, outputName);
        try {
          list.createTWLFile(outputFile.getAbsolutePath(), compress, "UTF-8");
        } catch (IOException e) {
          getLog().warn("Error writing twl file.", e);
        }
      }
    }

  }
}
