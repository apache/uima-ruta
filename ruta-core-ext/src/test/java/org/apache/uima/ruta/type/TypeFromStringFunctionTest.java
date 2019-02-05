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

package org.apache.uima.ruta.type;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.util.InvalidXMLException;
import org.junit.Test;
import org.xml.sax.SAXException;

public class TypeFromStringFunctionTest {

  @Test
  public void test() throws ResourceInitializationException, InvalidXMLException, IOException,
          AnalysisEngineProcessException, ResourceConfigurationException, URISyntaxException,
          SAXException {
    String document = "This is a test.\n1900/12/24 and 24.4.1982\nCW\norg.apache.uima.ruta.type.NUM";

    String script = "";
    script += "STRING typeString;";
    script += "CAP{-> MATCHEDTEXT(typeString), MARK(typeFromString(typeString))};";
    script += "typeFromString(typeString){-> MARK(T1)};";
    script += "(W PERIOD W PERIOD W PERIOD W PERIOD W PERIOD CAP){-> MATCHEDTEXT(typeString), MARK(typeFromString(typeString))};";
    script += "typeFromString(typeString){-> MARK(T2)};";

    Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put(RutaEngine.PARAM_ADDITIONAL_EXTENSIONS,
            new String[] { TypeFromStringFunctionExtension.class.getName() });
    CAS cas = RutaTestUtils.getCAS(document);
    Ruta.apply(cas, script, parameters);

    Type t = null;
    AnnotationIndex<AnnotationFS> ai = null;

    t = RutaTestUtils.getTestType(cas, 1);
    ai = cas.getAnnotationIndex(t);
    assertEquals(7, ai.size());

    t = RutaTestUtils.getTestType(cas, 2);
    ai = cas.getAnnotationIndex(t);
    assertEquals(8, ai.size());

    if (cas != null) {
      cas.release();
    }

  }
}
