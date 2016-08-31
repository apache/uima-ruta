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

package org.apache.uima.ruta.testing.ui.views;

import java.util.HashMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;


public class TestCasData implements Cloneable {

  public static final String RESULT_FOLDER = "results";

  private boolean wasEvaluated = false;

  private IPath resultPath;

  private IPath testFilePath;

  private int falsePositiveCount = 0;

  private int falseNegativeCount = 0;

  private int truePositiveCount = 0;

  private HashMap typeEvalData = null;

  public TestCasData(IPath testFilePath) {
    this.testFilePath = testFilePath;
    this.typeEvalData = new HashMap();
  }

  public IPath getPath() {
    return testFilePath;
  }

  public void setResultPath(IPath resultPath) {
    this.resultPath = resultPath;
  }

  public IPath getResultPath() {
    return resultPath;
  }

  public int getFalsePositiveCount() {
    return this.falsePositiveCount;
  }

  public int getFalseNegativeCount() {
    return this.falseNegativeCount;
  }

  public int getTruePositiveCount() {
    return this.truePositiveCount;
  }

  public void setFalsePositiveCount(int falsePositiveCount) {
    this.falsePositiveCount = falsePositiveCount;
  }

  public void setFalseNegativeCount(int falseNegativeCount) {
    this.falseNegativeCount = falseNegativeCount;
  }

  public void setTruePositiveCount(int truePositiveCount) {
    this.truePositiveCount = truePositiveCount;
  }

  public boolean wasEvaluated() {
    return this.wasEvaluated;
  }

  public void setEvaluationStatus(boolean wasEvaluated) {
    this.wasEvaluated = wasEvaluated;
  }

  public void loadPreviousResults(IResource r) {
    String fileName = testFilePath.removeFileExtension().lastSegment();
    resultPath = testFilePath.removeLastSegments(1).append(RESULT_FOLDER);
    fileName = fileName + ".result.xmi";
    resultPath = resultPath.append(fileName);
    IProject project = r.getProject();
    IFile resultFile = project.getFile(resultPath);
  }

  public HashMap getTypeEvalData() {
    return typeEvalData;
  }

  public void setTypeEvalData(HashMap typeEvalData) {
    this.typeEvalData = typeEvalData;
  }

}
