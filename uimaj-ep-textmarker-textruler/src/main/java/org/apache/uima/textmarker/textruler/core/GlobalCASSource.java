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

package org.apache.uima.textmarker.textruler.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.CAS;

/**
 * 
 * This class tries to be a workaround for a potential UIMA CAS Memory Leak. We have to keep the
 * total count of CAS objects constant, even if we call the whole TextRulerController machinery
 * multiple times in a batch-like row!
 * 
 * The idea is: Everybody that needs a CAS does not create one itself but asks this static class
 * here. If it is done with the CAS, it releases it here again, but it is held in memory and reused
 * when somebody else needs a reset CAS again.
 * 
 * Clients are for example CasCache, TextRulerBasicLearner, ...
 * 
 */
public class GlobalCASSource {

  private static List<CAS> free = new ArrayList<CAS>();

  private static List<CAS> inUsage = new ArrayList<CAS>();

  private static int count = 0;

  public static synchronized CAS allocCAS(AnalysisEngine ae) {
    if (free.size() > 0) {
      CAS result = free.get(free.size() - 1);
      free.remove(free.size() - 1);
      inUsage.add(result);
      return result;
    } else {
      try {
        count++;
        // TextRulerToolkit.log("[GlobalCASSource] CREATE NEW CAS: "+count);
        CAS newCas = ae.newCAS();
        inUsage.add(newCas);
        return newCas;
      } catch (Exception e) {
        e.printStackTrace();
        return null;
      }
    }
  }

  public static synchronized void releaseCAS(CAS cas) {
    if (inUsage.contains(cas)) {
      cas.reset();
      inUsage.remove(cas);
      free.add(cas);
      // TextRulerToolkit.log("[GlobalCASSource] RELEASED CAS, total CAS count = "+count);
    } else {
      TextRulerToolkit
              .log("[GlobalCASSource.release] Error, tried to release an unknown CAS object!");
    }

  }

}
