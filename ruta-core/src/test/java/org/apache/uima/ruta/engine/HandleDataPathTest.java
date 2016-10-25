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
import java.net.URL;
import java.net.URLClassLoader;
import java.util.regex.Pattern;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.internal.ResourceManagerFactory;
import org.apache.uima.fit.internal.ResourceManagerFactory.ResourceManagerCreator;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.resource.impl.ResourceManager_impl;
import org.junit.Assert;
import org.junit.Test;

public class HandleDataPathTest {

  @Test
  public void testDefault() throws Exception {
    assertDataPathEntries(false, new String[] { "datapath" });
  }

  @Test
  public void testModify() throws Exception {
    assertDataPathEntries(true, new String[] { "datapath", "desc1", "desc2" });
  }

  private void assertDataPathEntries(boolean modify, String[] expected) throws ResourceInitializationException {
    ResourceManagerFactory.setResourceManagerCreator(new ResourceManagerCreator() {

      @Override
      public ResourceManager newResourceManager() throws ResourceInitializationException {

        URL[] urls = new URL[1];
        urls[0] = HandleDataPathTest.class.getResource("/org/apache/uima/ruta/CustomViewTest.ruta");
        ResourceManager resourceManager = new ResourceManager_impl(new URLClassLoader(urls));
        try {
          resourceManager.setDataPath("datapath");
        } catch (MalformedURLException e) {
          throw new ResourceInitializationException(e);
        }
        return resourceManager;
      }
    });
    AnalysisEngine ruta = AnalysisEngineFactory.createEngine(RutaEngine.class,
            RutaEngine.PARAM_MODIFY_DATAPATH, modify, RutaEngine.PARAM_DESCRIPTOR_PATHS,
            new String[] { "desc1", "desc2" });
    String dataPath = ruta.getResourceManager().getDataPath();
    String[] paths = dataPath.split(Pattern.quote(File.pathSeparator));
    Assert.assertEquals(expected.length, paths.length);
    for (int i = 0; i < expected.length; i++) {
      String e = expected[i];
      String dp = paths[i];
      Assert.assertEquals(e, dp);
    }
  }

}
