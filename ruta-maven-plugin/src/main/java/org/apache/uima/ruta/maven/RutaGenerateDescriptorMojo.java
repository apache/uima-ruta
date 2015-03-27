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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.antlr.runtime.RecognitionException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.ruta.descriptor.RutaBuildOptions;
import org.apache.uima.ruta.descriptor.RutaDescriptorFactory;
import org.apache.uima.ruta.descriptor.RutaDescriptorInformation;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLizable;
import org.codehaus.plexus.util.FileUtils;
import org.sonatype.plexus.build.incremental.BuildContext;
import org.xml.sax.SAXException;

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

  /**
   * The template descriptor for the generated type system.
   */
  @Parameter(required = false)
  private File typeSystemTemplate;

  /**
   * The template descriptor for the generated analysis engine.
   */
  @Parameter(required = false)
  private File analysisEngineTemplate;

  /**
   * Script paths of the generated analysis engine descriptor.
   */
  @Parameter(required = false)
  private String[] scriptPaths;

  /**
   * Descriptor paths of the generated analysis engine descriptor.
   */
  @Parameter(required = false)
  private String[] descriptorPaths;

  /**
   * Resource paths of the generated analysis engine descriptor.
   */
  @Parameter(required = false)
  private String[] resourcePaths;

  /**
   * Suffix used for the generate type system descriptors
   */
  @Parameter(defaultValue = "TypeSystem", required = true)
  private String typeSystemSuffix;

  /**
   * Suffix used for the generate analysis engine descriptors
   */
  @Parameter(defaultValue = "Engine", required = true)
  private String analysisEngineSuffix;

  /**
   * Source file encoding.
   */
  @Parameter(defaultValue = "${project.build.sourceEncoding}", required = true)
  private String encoding;

  public void execute() throws MojoExecutionException, MojoFailureException {

    if (!typeSystemOutputDirectory.exists()) {
      typeSystemOutputDirectory.mkdirs();
      buildContext.refresh(typeSystemOutputDirectory);
    }

    if (!analysisEngineOutputDirectory.exists()) {
      analysisEngineOutputDirectory.mkdirs();
      buildContext.refresh(analysisEngineOutputDirectory);
    }

    RutaDescriptorFactory factory = new RutaDescriptorFactory();
    if (typeSystemTemplate != null) {
      try {
        factory.setDefaultTypeSystem(typeSystemTemplate.toURI().toURL());
      } catch (MalformedURLException e) {
        getLog().warn("Failed to get URL of " + typeSystemTemplate, e);
      }
    }
    if (analysisEngineTemplate != null) {
      try {
        factory.setDefaultEngine(analysisEngineTemplate.toURI().toURL());
      } catch (MalformedURLException e) {
        getLog().warn("Failed to get URL of " + analysisEngineTemplate, e);
      }
    }

    URLClassLoader classloader = getClassloader(project, getLog());

    RutaBuildOptions options = new RutaBuildOptions();
    String[] files = FileUtils.getFilesFromExtension(project.getBuild().getOutputDirectory(),
            new String[] { "ruta" });

    for (String fileString : files) {
      File file = new File(fileString);
      try {
        RutaDescriptorInformation descriptorInformation = factory.parseDescriptorInformation(file,
                encoding);
        String engineOutput = new File(analysisEngineOutputDirectory,
                descriptorInformation.getScriptName() + analysisEngineSuffix + ".xml")
                .getAbsolutePath();
        String typeSystemOutput = new File(typeSystemOutputDirectory,
                descriptorInformation.getScriptName() + typeSystemSuffix + ".xml")
                .getAbsolutePath();
        Pair<AnalysisEngineDescription, TypeSystemDescription> descriptions = factory
                .createDescriptions(engineOutput, typeSystemOutput, descriptorInformation, options,
                        scriptPaths, descriptorPaths, resourcePaths, classloader);
        write(descriptions.getKey(), engineOutput);
        write(descriptions.getValue(), typeSystemOutput);
      } catch (RecognitionException re) {
        getLog().warn("Failed to parse UIMA Ruta script file: " + file.getAbsolutePath(), re);
      } catch (IOException ioe) {
        getLog().warn("Failed to load UIMA Ruta script file: " + file.getAbsolutePath(), ioe);
      } catch (SAXException saxe) {
        getLog().warn("Failed to write descriptor: " + file.getAbsolutePath(), saxe);
      } catch (URISyntaxException urise) {
        getLog().warn("Failed to get uri: " + file.getAbsolutePath(), urise);
      } catch (ResourceInitializationException rie) {
        getLog().warn("Failed initialize resource: " + file.getAbsolutePath(), rie);
      } catch (InvalidXMLException ixmle) {
        getLog().warn("Invalid XML while building descriptor: " + file.getAbsolutePath(), ixmle);
      }
    }

  }

  private void write(XMLizable desc, String aFilename) throws SAXException, IOException {
    OutputStream os = null;
    try {
      File out = new File(aFilename);
      getLog().debug("Writing descriptor to: " + out);
      os = new FileOutputStream(out);
      desc.toXML(os);
    } finally {
      IOUtils.closeQuietly(os);
    }
  }

  /**
   * Create a class loader which covers the classes compiled in the current project and all
   * dependencies.
   */
  public static URLClassLoader getClassloader(MavenProject aProject, Log aLog)
          throws MojoExecutionException {
    List<URL> urls = new ArrayList<URL>();
    try {
      for (Object object : aProject.getCompileClasspathElements()) {
        String path = (String) object;
        aLog.debug("Classpath entry: " + object);
        urls.add(new File(path).toURI().toURL());
      }
    } catch (IOException e) {
      throw new MojoExecutionException("Unable to assemble classpath: "
              + ExceptionUtils.getRootCauseMessage(e), e);
    } catch (DependencyResolutionRequiredException e) {
      throw new MojoExecutionException("Unable to resolve dependencies: "
              + ExceptionUtils.getRootCauseMessage(e), e);
    }

    for (Artifact dep : (Set<Artifact>) aProject.getDependencyArtifacts()) {
      try {
        if (dep.getFile() == null) {
          // Unresolved file because it is in the wrong scope (e.g. test?)
          continue;
        }
        aLog.debug("Classpath entry: " + dep.getGroupId() + ":" + dep.getArtifactId() + ":"
                + dep.getVersion() + " -> " + dep.getFile());
        urls.add(dep.getFile().toURI().toURL());
      } catch (Exception e) {
        throw new MojoExecutionException("Unable get dependency artifact location for "
                + dep.getGroupId() + ":" + dep.getArtifactId() + ":" + dep.getVersion()
                + ExceptionUtils.getRootCauseMessage(e), e);
      }
    }
    return new URLClassLoader(urls.toArray(new URL[] {}),
            RutaGenerateDescriptorMojo.class.getClassLoader());
  }
}
