package org.apache.uima.tm.textmarker.testing.ui.views.util;

import java.util.ArrayList;

import org.apache.uima.tm.textmarker.testing.ui.views.TestCasData;


public class Memento {

  private ArrayList<TestCasData> testFileList;
 
  public Memento (ArrayList<TestCasData> testData) {
    this.testFileList = testData;
  }
  
  public ArrayList<TestCasData> getTestFileList () {
    return this.testFileList;
  }
  
  
}
