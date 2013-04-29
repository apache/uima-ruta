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

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * TextRulerStatisticsCollector is used for collecting data while e.g. testing a rule.
 * 
 * It holds a HashMap for the covered positive and negative MLExamples of e.g. the training
 * documents, counts the true positives (coveredPositives), false positives (coveredNegatives), ...
 * 
 * Currently false negatives (missedPositives) are not counted (see
 * TextRulerExampleDocument.compareOriginalDocumentWithTestCAS) but this functionality can be easily
 * added (since it exists as commented out code)
 * 
 */
public class TextRulerStatisticsCollector {

  protected int p = 0; // covered positive examples (true positives)

  protected int n = 0; // covered negative examples (false positives)

  // protected int missedPositives = 0; // (false negatives)

  protected Set<TextRulerExample> coveredPositives = new HashSet<TextRulerExample>();

  protected Set<TextRulerExample> coveredNegatives = new HashSet<TextRulerExample>();

  public TextRulerStatisticsCollector() {
    super();
  }

  public TextRulerStatisticsCollector(TextRulerStatisticsCollector c) {
    p = c.p;
    n = c.n;
    coveredPositives.addAll(c.coveredPositives);
    coveredNegatives.addAll(c.coveredNegatives);
  }

  public int getTotalCoveredExamples() {
    return p + n;
  }

  public int getCoveredPositivesCount() {
    if (TextRulerToolkit.DEBUG && p != coveredPositives.size()) {
      TextRulerToolkit.log("WHY is P different from coveredPositives.size() ??");
    }
    return p;
  }

  public int getCoveredNegativesCount() {
    return n;
  }

  public Set<TextRulerExample> getCoveredPositiveExamples() {
    return coveredPositives;
  }

  public Set<TextRulerExample> getCoveredNegativeExamples() {
    return coveredNegatives;
  }

  // public int getMissedPositives()
  // {
  // return missedPositives;
  // }

  public void reflectCountsFromCoveredExamples() {
    p = coveredPositives.size();
    n = coveredNegatives.size();
  }

  public void reset() {
    p = 0;
    n = 0;
    // missedPositives = 0 ;
    // coveredDocuments = 0;
    coveredPositives.clear();
    coveredNegatives.clear();
  }

  public void incCoveredPositives(int count) {
    p += count;
  }

  public void incCoveredNegatives(int count) {
    n += count;
  }

  public void addCoveredPositive(TextRulerExample e) {
    if (coveredPositives.add(e))
      incCoveredPositives(1);
    else
      TextRulerToolkit.logIfDebug("TRIED TO ADD A POSITIVE COVERED EXAMPLE TWICE !!");
  }

  public void addCoveredNegative(TextRulerExample e) {
    if (coveredNegatives.add(e))
      incCoveredNegatives(1);
    else
      TextRulerToolkit.logIfDebug("TRIED TO ADD A NEGATIVE COVERED EXAMPLE TWICE !!");
  }

  public TextRulerStatisticsCollector copy() {
    return new TextRulerStatisticsCollector(this);
  }

  public void add(TextRulerStatisticsCollector c) {
    incCoveredNegatives(c.n);
    incCoveredPositives(c.p);
    coveredPositives.addAll(c.coveredPositives);
    coveredNegatives.addAll(c.coveredNegatives);
  }

  @Override
  public String toString() {
    return "p=" + p + "; n=" + n;
  }

  // public void incCoveredMissedPositives(int count)
  // {
  // missedPositives += count;
  // }

}
