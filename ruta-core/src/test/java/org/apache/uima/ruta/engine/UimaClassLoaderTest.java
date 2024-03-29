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

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.internal.ResourceManagerFactory;
import org.apache.uima.fit.internal.ResourceManagerFactory.ResourceManagerCreator;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.ruta.type.FalsePositive;
import org.apache.uima.ruta.type.TruePositive;
import org.junit.jupiter.api.Test;

public class UimaClassLoaderTest {

  @Test
  public void test() throws Exception {
    URL url = UimaClassLoaderTest.class
            .getResource("/org/apache/uima/ruta/engine/UimafitTest.ruta");
    final File cpDir = new File(url.toURI()).getParentFile();

    ResourceManagerCreator oldCreator = ResourceManagerFactory.getResourceManagerCreator();
    try {
      ResourceManagerFactory.setResourceManagerCreator(new ResourceManagerCreator() {

        @Override
        public ResourceManager newResourceManager() throws ResourceInitializationException {
          ResourceManager resourceManager = null;
          try {
            resourceManager = UIMAFramework.newDefaultResourceManager();
            resourceManager.setExtensionClassPath(this.getClass().getClassLoader(),
                    cpDir.getAbsolutePath(), true);
            resourceManager.setDataPath("datapath");
          } catch (MalformedURLException e) {
            throw new ResourceInitializationException(e);
          }
          return resourceManager;
        }
      });

      AnalysisEngine ae = AnalysisEngineFactory.createEngine(RutaEngine.class,
              RutaEngine.PARAM_MAIN_SCRIPT, "UimafitTest");
      JCas jcas = ae.newJCas();
      jcas.setDocumentText("This is a test.");
      new TruePositive(jcas, 0, 4).addToIndexes();
      ae.process(jcas);
      Collection<FalsePositive> select = JCasUtil.select(jcas, FalsePositive.class);
      assertThat(select).isNotEmpty();
    } finally {
      ResourceManagerFactory.setResourceManagerCreator(oldCreator);
    }
  }

  @Test
  public void testResource() throws Exception {
    URL url = UimaClassLoaderTest.class
            .getResource("/org/apache/uima/ruta/action/MarkFastTestList.txt");
    final File cpDir = new File(url.toURI()).getParentFile();

    ResourceManagerCreator oldCreator = ResourceManagerFactory.getResourceManagerCreator();
    try {
      ResourceManagerFactory.setResourceManagerCreator(new ResourceManagerCreator() {

        @Override
        public ResourceManager newResourceManager() throws ResourceInitializationException {
          ResourceManager resourceManager = null;
          try {
            resourceManager = UIMAFramework.newDefaultResourceManager();
            resourceManager.setExtensionClassPath(this.getClass().getClassLoader(),
                    cpDir.getAbsolutePath(), true);
            resourceManager.setDataPath("datapath");
          } catch (MalformedURLException e) {
            throw new ResourceInitializationException(e);
          }
          return resourceManager;
        }
      });

      AnalysisEngine ae = AnalysisEngineFactory.createEngine(RutaEngine.class,
              RutaEngine.PARAM_RULES,
              "WORDLIST list1 = 'MarkFastTestList.txt';MARKFAST(FalsePositive, list1, false, 0, true);");
      JCas jcas = ae.newJCas();
      jcas.setDocumentText("1 0 0");
      ae.process(jcas);
      Collection<FalsePositive> select = JCasUtil.select(jcas, FalsePositive.class);
      assertThat(!select.isEmpty()).isTrue();
    } finally {
      ResourceManagerFactory.setResourceManagerCreator(oldCreator);
    }
  }

}
