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

package org.apache.uima.textmarker.testing.ui.views.util;

import java.io.File;
import java.io.FileInputStream;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.impl.XmiCasDeserializer;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.resource.metadata.FsIndexDescription;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.textmarker.ide.TextMarkerIdePlugin;
import org.apache.uima.textmarker.ide.core.builder.TextMarkerProjectUtils;
import org.apache.uima.util.CasCreationUtils;
import org.apache.uima.util.XMLInputSource;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;

public class CASLoader {

  public static CAS loadCas(IResource r, IPath file) {
    String elementName = r.getLocation().lastSegment();
    // Remove Fileend if elementName exists
    int lastIndexOf = elementName.lastIndexOf(".tm");
    if (lastIndexOf != -1) {
      elementName = elementName.substring(0, lastIndexOf);
    }

    IPath engineDescriptorPath = TextMarkerProjectUtils.getEngineDescriptorPath(r.getLocation(),
            r.getProject());
    try {
      XMLInputSource in = new XMLInputSource(engineDescriptorPath.toPortableString());
      ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);
      AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(specifier);

      String desc = null;
      desc = engineDescriptorPath.toPortableString();
      XMLInputSource in2 = new XMLInputSource(desc);
      Object descriptor = UIMAFramework.getXMLParser().parse(in2);
      CAS testCas = null;
      if (descriptor instanceof AnalysisEngineDescription) {
        testCas = CasCreationUtils.createCas((AnalysisEngineDescription) descriptor);
      } else if (descriptor instanceof TypeSystemDescription) {
        TypeSystemDescription tsDesc = (TypeSystemDescription) descriptor;
        tsDesc.resolveImports();
        testCas = CasCreationUtils.createCas(tsDesc, null, new FsIndexDescription[0]);
        // TODO: where are the type priorities?
      }

      FileInputStream inputStream = null;
      try {
        inputStream = new FileInputStream(
        // resource.getLocation().toFile()
                new File(file.toPortableString()));

        XmiCasDeserializer.deserialize(inputStream, testCas, true);

      } finally {
        if (inputStream != null) {
          inputStream.close();
        }
      }
      return testCas;
    } catch (Exception e) {
      TextMarkerIdePlugin.error(e);
    }
    return null;
  }
}
