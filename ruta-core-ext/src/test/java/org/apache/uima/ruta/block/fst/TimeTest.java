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

package org.apache.uima.ruta.block.fst;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.uima.cas.CAS;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.util.FileUtils;
import org.junit.jupiter.api.Test;
public class TimeTest {

  private static final int numOfTests = 1;

  private static final String rulesPositive = "CW CW CW CW{-> T1};\n" + "CW CW CW SW{-> T2};\n"
          + "CW CW CW CW CW{-> T3};\n" + "CW CW CW CW SW{-> T4};\n";

  private static final String rulesAverage = "COLON CW {-> T1};\n"
          + "W COLON {-> MARK(T2, 1, 1)};\n" + "T1 T2 {-> MARK(T3, 1, 2)};\n"
          + "T1 W T2 {-> MARK(T3, 1, 3)};\n" + "T1 W W T2 {-> MARK(T3, 1, 4)};\n"
          + "T1 W W W T2 {-> MARK(T3, 1, 5)};\n";

  private static final String rulesNegative = "SW CW CW CW{-> T1};\n" + "W CW CW CW{-> T2};\n"
          + "COLON CW CW CW{-> T3};\n" + "PERIOD CW CW CW{-> T4};\n";

  @Test
  public void test() throws URISyntaxException, IOException {
    URL resource = TimeTest.class.getResource("text.txt");
    File file = new File(resource.toURI());
    String text = FileUtils.file2String(file, "UTF-8");

    testTime(text, rulesPositive, "optimal");
    testTime(text, rulesAverage, "normal");
    testTime(text, rulesNegative, "worst-case");
  }

  public void testTime(String text, String rules, String test) {
    String scriptNormal = rules;
    long timeNormal = 0;
    applyScript(text, scriptNormal);
    for (int i = 0; i < numOfTests; i++) {
      timeNormal += applyScript(text, scriptNormal);
    }

    String scriptDynamic = "Document{-> DYNAMICANCHORING(true)}" + rules;
    long timeDynamic = 0;
    applyScript(text, scriptDynamic);
    for (int i = 0; i < numOfTests; i++) {
      timeDynamic += applyScript(text, scriptDynamic);
    }

    String scriptFST = "FST Document{}{\n" + rules + "}\n";
    long timeFST = 0;
    applyScript(text, scriptFST);
    for (int i = 0; i < numOfTests; i++) {
      timeFST += applyScript(text, scriptFST);
    }

    System.out.println("Test " + test + ": " + "\n\tFST: \t\t" + (timeFST / (numOfTests * 1000.))
            + "\n\tNormal w/o DA:\t" + (timeNormal / (numOfTests * 1000.)) + "\n\tNormal w/  DA:\t"
            + (timeDynamic / (numOfTests * 1000.)));
    // assertTrue("FST not faster than normal execution", timeFST < timeNormal);
  }

  private long applyScript(String text, String script) {
    CAS cas;
    Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put(RutaEngine.PARAM_ADDITIONAL_EXTENSIONS,
            new String[] { FSTBlockExtension.class.getName() });
    try {
      long startTime = System.currentTimeMillis();
      cas = RutaTestUtils.getCAS(text);
      Ruta.apply(cas, script, parameters);
      long endTime = System.currentTimeMillis();
      return endTime - startTime;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }

}
