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

package org.apache.uima.cev.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.impl.XmiCasDeserializer;
import org.apache.uima.cev.CEVPlugin;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.resource.metadata.FsIndexDescription;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.tools.stylemap.StyleMapEntry;
import org.apache.uima.util.CasCreationUtils;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.SAXException;

public class CEVDocument {

  private ArrayList<CEVData> casData;

  private CAS mainCAS;

  private Object descriptor;

  private Map<String, StyleMapEntry> style;

  public CEVDocument(IFile descriptorFile, IFile casFile) throws IllegalArgumentException,
          IOException, InvalidXMLException, SAXException, ResourceInitializationException {

    casData = new ArrayList<CEVData>();

    descriptor = UIMAFramework.getXMLParser().parse(
            new XMLInputSource(descriptorFile.getLocation().toFile()));

    mainCAS = null;

    if (descriptor instanceof AnalysisEngineDescription) {
      mainCAS = CasCreationUtils.createCas((AnalysisEngineDescription) descriptor);
    } else if (descriptor instanceof TypeSystemDescription) {
      TypeSystemDescription tsDesc = (TypeSystemDescription) descriptor;
      ResourceManager resMgr = UIMAFramework.newDefaultResourceManager();
      IProject project = descriptorFile.getProject();
      IFolder folder = project.getFolder("descriptor");
      resMgr.setDataPath(folder.getLocation().toPortableString());
      tsDesc.resolveImports(resMgr);
      mainCAS = CasCreationUtils.createCas(tsDesc, null, new FsIndexDescription[0]);
    } else {
      throw new IllegalArgumentException("Invalid Type System Descriptor");
    }

    FileInputStream inputStream = null;
    try {
      inputStream = new FileInputStream(casFile.getLocation().toFile());
      XmiCasDeserializer.deserialize(inputStream, mainCAS, true);
    } finally {
      if (inputStream != null)
        inputStream.close();
    }

    try {
      String desc = descriptorFile.getFullPath().removeFileExtension().lastSegment();
      if (desc.toLowerCase().endsWith("typesystem")) {
        desc = desc.substring(0, desc.length() - 10);
      }
      String styleMap = desc.toLowerCase() + "stylemap.xml";

      for (IResource r : descriptorFile.getParent().members())
        if (r.getType() == IResource.FILE && r.getFullPath().lastSegment().startsWith(desc)
                && r.getFullPath().lastSegment().toLowerCase().equals(styleMap)) {

          style = StyleMapReader.parseStyleMapDOM(r.getLocation().toOSString());
          break;
        }

    } catch (CoreException e) {
      CEVPlugin.error(e);
    }

    Iterator viewIter = mainCAS.getViewIterator();

    while (viewIter.hasNext())
      casData.add(new CEVData((CAS) viewIter.next(), getStyleMap()));
  }

  public int count() {
    return casData.size();
  }

  public CEVData getCASData(int index) throws IndexOutOfBoundsException {
    return casData.get(index);
  }

  public CEVData[] getCASData() {
    return casData.toArray(new CEVData[casData.size()]);
  }

  public CAS getMainCas() {
    return mainCAS;
  }

  public void dispose() {
    for (CEVData each : casData) {
      each.dispose();
    }
  }

  public CAS createCas() throws ResourceInitializationException, InvalidXMLException {
    if (descriptor instanceof AnalysisEngineDescription) {
      return CasCreationUtils.createCas((AnalysisEngineDescription) descriptor);
    } else if (descriptor instanceof TypeSystemDescription) {
      TypeSystemDescription tsDesc = (TypeSystemDescription) descriptor;
      tsDesc.resolveImports();
      return CasCreationUtils.createCas(tsDesc, null, new FsIndexDescription[0]);
    } else {
      throw new IllegalArgumentException("Invalid Type System Descriptor");
    }
  }

  public Map<String, StyleMapEntry> getStyleMap() {
    return style;
  }
}
