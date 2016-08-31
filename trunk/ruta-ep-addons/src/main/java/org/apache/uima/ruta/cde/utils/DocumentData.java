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

package org.apache.uima.ruta.cde.utils;

import java.io.File;
import java.util.ArrayList;

public class DocumentData {

	private File document;
	
	private double augmentedResult;
	
	private double fMeasure;
	
	private ArrayList<String[]> results;
	
	public DocumentData(File document) {
	  this.document = document;
	  this.augmentedResult = 0;
	  this.fMeasure = 0;
	  this.results = new ArrayList<String[]>();
	}
	
	public void setDocument(File document) {
		this.document = document;
	}
	
	public void setAugmentedResult (double result) {
		this.augmentedResult = result;
	}
	
	public File getDocument() {
		return this.document;
	}
	
	public double getAugmentedResult () {
		return this.augmentedResult;
	}

  public ArrayList<String[]> getResults() {
    return results;
  }

  public void setResults(ArrayList<String[]> results) {
    this.results = results;
  }
  
  public void setFMeasure(double fMeasure) {
    this.fMeasure = fMeasure;
  }
  
  public double getFMeasure() {
    return this.fMeasure;
  }
  
}
