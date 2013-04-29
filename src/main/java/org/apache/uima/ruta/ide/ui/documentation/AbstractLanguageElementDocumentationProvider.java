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

package org.apache.uima.textmarker.ide.ui.documentation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.uima.textmarker.ide.TextMarkerIdePlugin;
import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.ui.documentation.IScriptDocumentationProvider;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;

public abstract class AbstractLanguageElementDocumentationProvider implements
        IScriptDocumentationProvider {

  protected Map<String, String> map;

  public AbstractLanguageElementDocumentationProvider(String file) {
    super();
    map = new HashMap<String, String>();
    if (file != null) {
      try {
        fillMap(file);
      } catch (IOException e) {
      }
    }
  }

  public Reader getInfo(String content) {
    String string = map.get(content);
    if (string == null) {
      return null;
    }
    return new StringReader(string);
  }

  public Reader getInfo(IMember element, boolean lookIntoParents, boolean lookIntoExternal) {
    return null;
  }

  private void fillMap(String documentationFile) throws IOException {
    InputStream resourceAsStream = getClass().getResourceAsStream(documentationFile);
    BufferedReader reader = new BufferedReader(new InputStreamReader(resourceAsStream));
    StringBuilder sb = new StringBuilder();
    while (true) {
      String line;
      line = reader.readLine();
      if (line == null) {
        break;
      }
      sb.append(line + "\n");
    }

    String document = sb.toString();

    try {
      Parser parser = new Parser(document);
      NodeList list = parser.parse(null);
      HtmlDocumentationVisitor visitor = new HtmlDocumentationVisitor(document);
      list.visitAllNodesWith(visitor);
      map.putAll(visitor.getMap());
    } catch (Exception e) {
      TextMarkerIdePlugin.error(e);
    }

  }
}
