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

package org.apache.uima.ruta;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.util.InvalidXMLException;
import org.junit.Test;

/**
 * There was a {@link NullPointerException} when calling
 * {@link org.apache.uima.ruta.type.RutaBasic#getEndAnchors(org.apache.uima.cas.Type)}
 * with a type that is not annotated in the CAS when running on the low memory profile. This
 * test ensures this bug does not come back.
 */
public class ShiftWithLowMemoryProfileTest {
  @Test
  public void testWithLowMemory() throws AnalysisEngineProcessException, InvalidXMLException, ResourceConfigurationException, IOException, URISyntaxException, ResourceInitializationException {
    String name = this.getClass().getSimpleName();
    String namespace = this.getClass().getPackage().getName().replaceAll("\\.", "/");
    Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put(RutaEngine.PARAM_LOW_MEMORY_PROFILE, true);

    CAS cas = RutaTestUtils.process(namespace + "/" + name + RutaEngine.SCRIPT_FILE_EXTENSION,
            namespace + "/" + name + ".txt", parameters, 50);


    Type t = cas.getTypeSystem().getType("org.apache.uima.ruta.type.W");
    AnnotationIndex<AnnotationFS> ai = cas.getAnnotationIndex(t);
    assertEquals(1, ai.size());
    FSIterator<AnnotationFS> iterator = ai.iterator();
    assertEquals("A", iterator.next().getCoveredText());
    cas.release();
  }
}
