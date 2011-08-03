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

package org.apache.uima.tm.textmarker.batch;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.CAS;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.util.XMLInputSource;

public class TextMarkerEngineTest {

  private XMLInputSource in;

  private ResourceSpecifier specifier;

  private AnalysisEngine ae;

  private String text = "Der Hund jagt die Katze. Die Katze ist <b>fett</b>.";

  public static void main(String[] args) {
    try {
      new TextMarkerEngineTest().test();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void test() throws Exception {
    in = new XMLInputSource(getClass().getResource("/TextMarkerEngineDescriptor.xml"));
    specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);
    ae = UIMAFramework.produceAnalysisEngine(specifier);
    CAS cas = ae.newCAS();
    cas.setDocumentText(text);
    ae.setConfigParameterValue("ScriptLocation",
            "C:/work/runtime-EclipseApplication/test/scripts/test.tm");
    ae.process(cas);
  }

}
