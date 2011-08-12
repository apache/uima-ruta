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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.ui.documentation.IScriptDocumentationProvider;

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
    // TODO Auto-generated method stub
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

    String string = sb.toString();
    string = string.replaceAll("\\[\\{TableOfContents\\}\\]", "");
    String[] split = string.split("[-][-][-][-]");

    Pattern compile = Pattern.compile("!! __([A-Z]+)__");
    for (String each : split) {
      String docu = each.trim();
      Matcher matcher = compile.matcher(each);
      String group = null;
      if (matcher.find()) {
        group = matcher.group(1);
        docu = docu.replaceAll("!! __" + group + "__", "<h1>" + group + "</h1>");
      }
      docu = docu.replaceAll("__Definition__", "<h2>Definition</h2>");
      docu = docu.replaceAll("__Example__", "<h2>Example</h2>");
      docu = docu.replaceAll("%%prettify", "");
      docu = docu.replaceAll("/%", "");
      docu = docu.replaceAll("\\{\\{\\{", "<code>");
      docu = docu.replaceAll("\\}\\}\\}", "</code>");

      if (group != null) {
        map.put(group, docu);
      }
    }
  }
}
