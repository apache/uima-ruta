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
import java.net.MalformedURLException;

import org.apache.commons.io.FileUtils;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.CAS;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.internal.ResourceManagerFactory;
import org.apache.uima.fit.internal.ResourceManagerFactory.ResourceManagerCreator;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceManager;
import org.junit.Test;

public class ResourcesFromDataPathTest {

  @Test
  public void test() throws Exception {

    final String datapath = "target/datapath/";
    new File(datapath).mkdirs();
    FileUtils.copyFile(new File("src/test/resources/org/apache/uima/ruta/action/MarkFastTest.ruta"),
            new File(datapath + "MarkFastTest.ruta"));
    FileUtils.copyFile(
            new File("src/test/resources/org/apache/uima/ruta/action/MarkFastTestList.txt"),
            new File(datapath + "MarkFastTestList.txt"));
    ResourceManagerCreator oldCreator = ResourceManagerFactory.getResourceManagerCreator();
    try {
      ResourceManagerFactory.setResourceManagerCreator(new ResourceManagerCreator() {
  
        @Override
        public ResourceManager newResourceManager() throws ResourceInitializationException {
  
          ResourceManager resourceManager = UIMAFramework.newDefaultResourceManager();
          try {
            resourceManager.setDataPath(datapath);
          } catch (MalformedURLException e) {
            throw new ResourceInitializationException(e);
          }
          return resourceManager;
        }
      });
  
      AnalysisEngine ae = AnalysisEngineFactory.createEngine(RutaEngine.class,
              RutaEngine.PARAM_MAIN_SCRIPT, "MarkFastTest");
      CAS cas = RutaTestUtils.getCAS(FileUtils.readFileToString(
              new File("src/test/resources/org/apache/uima/ruta/action/MarkFastTest.txt")));
      ae.process(cas);
  
      RutaTestUtils.assertAnnotationsEquals(cas, 1, 3, "1 0 0", "100", "2 0 0");
      RutaTestUtils.assertAnnotationsEquals(cas, 2, 3, "1 0 0", "100", "2 0 0");
      RutaTestUtils.assertAnnotationsEquals(cas, 3, 1, "100");
      RutaTestUtils.assertAnnotationsEquals(cas, 4, 1, "100");
    }
    finally {
      ResourceManagerFactory.setResourceManagerCreator(oldCreator);
    }
  }
}
