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

import java.io.File;
import java.io.IOException;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.FileUtils;

/**
 * This Analysis Engine can be utilized to write the covered text of annotations in a text file,
 * whereas each covered text is put into a new line. If the analysis engine, for example, is
 * configured for the type <code>uima.example.Person</code>, then all covered texts of all Person
 * annotations are stored in a text file, one person in each line. A descriptor file for this
 * Analysis Engine is located in the folder <code>descriptor/utils</code> of a UIMA Ruta project.
 * 
 */
public class AnnotationWriter extends JCasAnnotator_ImplBase {

  /**
   * This string parameter specifies the absolute path of the resulting file named
   * <code>output.txt</code>. However, if an annotation of the type
   * <code>org.apache.uima.examples.SourceDocumentInformation</code> is given, then the value of
   * this parameter is interpreted to be relative to the URI stored in the annotation and the name
   * of the file will be adapted to the name of the source file. The default value of this parameter
   * is <code>/../output/</code>.
   */
  public static final String PARAM_OUTPUT = "Output";

  @ConfigurationParameter(name = PARAM_OUTPUT, mandatory = false, defaultValue = "/../output/")
  private String output;

  /**
   * This string parameter specifies the encoding of the resulting file. The default value of this
   * parameter is <code>UTF-8</code>.
   */
  public static final String PARAM_ENCODING = "Encoding";

  @ConfigurationParameter(name = PARAM_ENCODING, mandatory = false, defaultValue = "UTF-8")
  private String encoding;

  /**
   * Only the covered texts of annotations of the type specified with this parameter are stored in
   * the resulting file. The default value of this parameter is
   * <code>uima.tcas.DocumentAnnotation</code>, which will store the complete document in a new
   * file.
   */
  public static final String PARAM_TYPE = "Type";

  @ConfigurationParameter(name = PARAM_TYPE, mandatory = false, defaultValue = ">uima.tcas.DocumentAnnotation")
  private String type;

  private UimaContext context;

  @Override
  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    super.initialize(aContext);
    if (aContext == null && context != null) {
      aContext = context;
    }
    if (aContext != null) {
      output = (String) aContext.getConfigParameterValue(PARAM_OUTPUT);
      type = (String) aContext.getConfigParameterValue(PARAM_TYPE);
      encoding = (String) aContext.getConfigParameterValue(PARAM_ENCODING);
      this.context = aContext;
    }
  }

  @Override
  public void process(JCas jcas) throws AnalysisEngineProcessException {
    CAS cas = jcas.getCas();
    Type targetType = cas.getTypeSystem().getType(type);
    FSIterator<AnnotationFS> iterator = cas.getAnnotationIndex(targetType).iterator();

    StringBuilder newDocument = new StringBuilder();
    while (iterator.isValid()) {
      AnnotationFS fs = iterator.get();
      newDocument.append(fs.getCoveredText());
      newDocument.append("\n");
      iterator.moveToNext();
    }

    Type sdiType = cas.getTypeSystem().getType(RutaEngine.SOURCE_DOCUMENT_INFORMATION);

    String filename = "output.txt";
    File file = new File(output, filename);
    if (sdiType != null) {
      FSIterator<AnnotationFS> sdiit = cas.getAnnotationIndex(sdiType).iterator();
      if (sdiit.isValid()) {
        AnnotationFS annotationFS = sdiit.get();
        Feature uriFeature = sdiType.getFeatureByBaseName("uri");
        String stringValue = annotationFS.getStringValue(uriFeature);
        File f = new File(stringValue);
        String name = f.getName();
        if (!name.endsWith(".txt")) {
          name = name + ".txt";
        }
        file = new File(f.getParent() + output, name);
      }

    }
    try {
      FileUtils.saveString2File(newDocument.toString(), file, encoding);
    } catch (IOException e) {
      throw new AnalysisEngineProcessException(e);
    }
  }
}
