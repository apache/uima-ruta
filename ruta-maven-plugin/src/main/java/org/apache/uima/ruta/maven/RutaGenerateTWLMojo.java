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
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
import org.codehaus.plexus.util.FileUtils;
import org.sonatype.plexus.build.incremental.BuildContext;

/**
 * Generate descriptors from UIMA Ruta script files.
 * 
 */
@Mojo(name = "twl", defaultPhase = LifecyclePhase.GENERATE_RESOURCES, requiresDependencyResolution = ResolutionScope.COMPILE)
public class RutaGenerateTWLMojo extends AbstractMojo {

  @Parameter(defaultValue = "${project}", readonly = true)
  private MavenProject project;

  @Component
  private BuildContext buildContext;

  /**
   * The directory where the generated tree word lists will be written to.
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

  /**
   * Fail on error.
   */
  @Parameter(defaultValue = "true", required = true)
  private boolean failOnError;

  @Override
  public void execute() throws MojoExecutionException, MojoFailureException {
    if (!outputDirectory.exists()) {
      outputDirectory.mkdirs();
      buildContext.refresh(outputDirectory);
    }

    this.project.addCompileSourceRoot(outputDirectory.getPath());

    Map<File, File> inputOutputMap = null;
    try {
      inputOutputMap = getModifiedFilesMap(inputFiles, outputDirectory, buildContext);
    } catch (IOException e) {
      handleError("Error accessing input files.", e);
    }

    if (inputOutputMap == null || inputOutputMap.isEmpty()) {
      getLog().debug("No modified files to process... skipping.");
      return;
    }
    getLog().debug("Processing following files: " + inputOutputMap.keySet().toString());

    Set<Entry<File, File>> entrySet = inputOutputMap.entrySet();
    for (Entry<File, File> each : entrySet) {
      File inputFile = each.getKey();
      File outputFile = each.getValue();
      TreeWordList list = null;
      try {
        list = new TreeWordList(inputFile.getAbsolutePath(), false);
      } catch (IOException e) {
        handleError("Error generating twl.", e);
      }
      if (list != null) {
        try {
          outputFile.getParentFile().mkdirs();
          list.createTWLFile(outputFile.getAbsolutePath(), compress, encoding);
          buildContext.refresh(outputFile);
        } catch (IOException e) {
          handleError("Error writing twl file.", e);
        }
      }
    }

  }

  private Map<File, File> getModifiedFilesMap(FileSet fileSet, File outputDirectory,
          BuildContext buildContext) throws IOException {
    Map<File, File> result = new LinkedHashMap<>();

    File directory = new File(fileSet.getDirectory());

    String includes = Utils.toString(fileSet.getIncludes());
    String excludes = Utils.toString(fileSet.getExcludes());

    for (Object each : FileUtils.getFiles(directory, includes, excludes)) {
      if (each instanceof File) {
        File inputFile = (File) each;
        File outputFile = getOutputFile(inputFile, directory, outputDirectory);
        if (outputFile == null || !outputFile.exists()
                || inputFile.lastModified() > outputFile.lastModified()) {
          result.put(inputFile, outputFile);
        }
      }
    }
    return result;
  }

  private File getOutputFile(File inputFile, File inputDirectory, File outputDirectory) {
    String inputName = inputFile.getName();
    String outputName = inputName.substring(0, inputName.length() - 3) + "twl";

    Path inputFilePath = inputFile.toPath();
    Path inputDirectoryPath = inputDirectory.toPath();
    Path outputDirectoryPath = outputDirectory.toPath();

    Path relativize = inputDirectoryPath.relativize(inputFilePath);
    Path resolve = outputDirectoryPath.resolve(relativize);
    Path parent = resolve.getParent();
    Path result = parent.resolve(outputName);
    return result.toFile();
  }

  private void handleError(String message, Exception e) throws MojoExecutionException {
    if (failOnError) {
      throw new MojoExecutionException(message, e);
    }

    getLog().error(message, e);
  }
}
