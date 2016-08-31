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

package org.apache.uima.ruta.textruler.learner.rapier;

import java.util.Set;

import org.apache.uima.ruta.textruler.core.TextRulerExample;

public class RapierDebugHelper {

  public static boolean debugCheckIfRuleCoversItsSeedRuleCoverings(RapierRule rule) {
    Set<TextRulerExample> parent1Positives = rule.getParent1().getCoveringStatistics()
            .getCoveredPositiveExamples();
    Set<TextRulerExample> parent2Positives = rule.getParent2().getCoveringStatistics()
            .getCoveredPositiveExamples();
    Set<TextRulerExample> rulePositives = rule.getCoveringStatistics().getCoveredPositiveExamples();
    return rulePositives.containsAll(parent1Positives)
            && rulePositives.containsAll(parent2Positives);
  }

}
