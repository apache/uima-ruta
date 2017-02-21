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

import java.util.Collection;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.ruta.type.TruePositive;
import org.junit.Test;

public class MemoryFootprintTest {

  public static void main(String[] args) throws Exception {
//     Thread.sleep(20000);

    System.out.println("Start");
    new MemoryFootprintTest().test();
    new MemoryFootprintTest().test();
//    new MemoryFootprintTest().test();
//    new MemoryFootprintTest().test();
  }

  @Test
  public void test() throws Exception {

    int lines = 25000;
    String line = "This is a simple line with a number like 24 and some special characters like %&$.\n";
    String rules = "CW SW{-> TruePositive};CW SW{-> TruePositive};CW SW{-> TruePositive};CW SW{-> TruePositive};CW SW{-> TruePositive};";

    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < lines; i++) {
      sb.append(line);
    }

    JCas jcas = JCasFactory.createJCas();
    jcas.setDocumentText(sb.toString());

    AnalysisEngine ae = AnalysisEngineFactory.createEngine(RutaEngine.class, RutaEngine.PARAM_RULES,
            rules);

    long start = System.currentTimeMillis();
    ae.process(jcas);
    long end = System.currentTimeMillis();
    System.out.println("took " + (end - start) / 1000);
    System.out.println("freeMemory " + Runtime.getRuntime().freeMemory() / 1000000);
    System.out.println("maxMemory " + Runtime.getRuntime().maxMemory() / 1000000);
    System.out.println("totalMemory " + Runtime.getRuntime().totalMemory() / 1000000);

    Collection<TruePositive> select = JCasUtil.select(jcas, TruePositive.class);
    System.out.println("TruePositive: " + select.size());
    jcas.release();
  }

}
