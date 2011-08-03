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

package org.apache.uima.tm.dltk.internal.ui.documentation;

import java.io.Reader;
import java.io.StringReader;

import org.eclipse.dltk.core.IBuffer;
import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ISourceRange;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.ui.documentation.IScriptDocumentationProvider;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;

public class TextMarkerCommentDocumentationProvider extends ScriptDocumentationProvider implements
        IScriptDocumentationProvider {

  @Override
  protected String getLine(Document d, int line) throws BadLocationException {
    return d.get(d.getLineOffset(line), d.getLineLength(line));
  }

  protected String getHeaderComment(IMember member) {
    if (member instanceof IField) {
      return null;
    }
    try {
      ISourceRange range = member.getSourceRange();
      if (range == null)
        return null;

      IBuffer buf = null;

      ISourceModule compilationUnit = member.getSourceModule();
      if (!compilationUnit.isConsistent()) {
        return null;
      }

      buf = compilationUnit.getBuffer();

      final int start = range.getOffset();

      String contents = buf.getContents();

      String result = "";

      Document doc = new Document(contents);
      try {
        int line = doc.getLineOfOffset(start);
        line--;
        if (line < 0)
          return null;
        boolean emptyEnd = true;
        while (line >= 0) {
          String curLine = getLine(doc, line);
          String curLineTrimmed = curLine.trim();
          if ((curLineTrimmed.length() == 0 && emptyEnd) || curLineTrimmed.startsWith("#")) {
            if (curLineTrimmed.length() != 0)
              emptyEnd = false;
            result = curLine + result;
          } else
            break;

          line--;
        }
      } catch (BadLocationException e) {
        return null;
      }

      return result;

    } catch (ModelException e) {
    }
    return null;
  }

  public Reader getInfo(IMember member, boolean lookIntoParents, boolean lookIntoExternal) {
    String header = getHeaderComment(member);
    return new StringReader(convertToHTML(header));
  }

  @Override
  protected String convertToHTML(String header) {
    StringBuffer result = new StringBuffer();
    // result.append("<p>\n");
    Document d = new Document(header);
    for (int line = 0;; line++) {
      try {
        String str = getLine(d, line).trim();
        if (str == null)
          break;
        while (str.length() > 0 && str.startsWith("#"))
          str = str.substring(1);
        while (str.length() > 0 && str.endsWith("#"))
          str = str.substring(0, str.length() - 1);
        if (str.length() == 0)
          result.append("<p>");
        else {
          if (str.trim().matches("\\w*:")) {
            result.append("<h4>");
            result.append(str);
            result.append("</h4>");
          } else
            result.append(str + "<br>");
        }
      } catch (BadLocationException e) {
        break;
      }

    }
    // result.append("</p>\n");
    return result.toString();
  }

  public Reader getInfo(String content) {
    return null;
  }
}
