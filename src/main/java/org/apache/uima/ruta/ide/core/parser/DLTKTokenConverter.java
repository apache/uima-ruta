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

package org.apache.uima.textmarker.ide.core.parser;

import org.antlr.runtime.Token;
import org.eclipse.dltk.ast.DLTKToken;

public class DLTKTokenConverter {
  String content;

  CodeModel model;

  private static class CodeModel {
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
      String codeLine = codeLines[lineNumber];

      int start = codeLineLengths[lineNumber];
      int end = start + codeLine.length();

      return new int[] { start, end };
    }
  }

  public DLTKTokenConverter(char[] content0) {
    this.content = new String(content0);
    this.model = new CodeModel(content);
  }

  /**
   * @param token
   * @return
   */
  @Deprecated
  public DLTKToken convert(Token token) {
    if (token == null) {
      DLTKToken t = new DLTKToken(0, "");
      t.setLine(1);
      return t;
    }
    int line = token.getLine() - 1;
    if (line < 0) {
      line = 0;
    }
    int[] bounds = this.model.getBounds(line);
    DLTKToken t = new DLTKToken(token.getType(), token.getText());
    int offset = token.getCharPositionInLine();
    if (offset < 0) {
      offset = 0;
    }
    t.setColumn(bounds[0] + offset);
    t.setLine(line);
    return t;
  }

  public int convert(int line, int offset) {
    if (line > 0) {
      int[] bounds = this.model.getBounds(line - 1);
      return bounds[0] + offset;
    }
    return offset;
  }

  @Deprecated
  public int length() {
    return this.content.length();
  }
}
