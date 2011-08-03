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

package org.apache.uima.tm.dltk.core;

public class CodeModel {
  private String[] codeLines;

  private int[] codeLineLengths;

  public CodeModel(String code) {
    this.codeLines = code.split("\n");
    int count = this.codeLines.length;

    this.codeLineLengths = new int[count];

    int sum = 0;
    for (int i = 0; i < count; ++i) {
      this.codeLineLengths[i] = sum;
      sum += this.codeLines[i].length() + 1;
    }
  }

  public int[] getBounds(int lineNumber) {
    String codeLine = this.codeLines[lineNumber];

    int start = this.codeLineLengths[lineNumber];
    int end = start + codeLine.length();

    return new int[] { start, end };
  }

  public int getLineNumber(int start, int end) {
    int len = this.codeLines.length;
    for (int i = 0; i < len; ++i) {
      String codeLine = this.codeLines[i];

      int s = this.codeLineLengths[i];
      int e = start + codeLine.length();

      if (start <= s && end <= e) {
        return i + 1;
      }
    }
    return this.codeLines.length;
  }
}
