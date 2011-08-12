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

import java.io.File;
import java.util.List;
import java.util.Map;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Token;
import org.apache.uima.textmarker.ide.core.ITextMarkerKeywords;
import org.apache.uima.textmarker.ide.core.TextMarkerKeywordsManager;
import org.apache.uima.textmarker.ide.core.builder.DescriptorManager;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerModuleDeclaration;
import org.apache.uima.textmarker.parser.TextMarkerLexer;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.parser.AbstractSourceParser;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.core.DLTKCore;

public class TextMarkerSourceParser extends AbstractSourceParser {

  private CommonTokenStream tokenStream;

  private IProblemReporter problemReporter = null;

  public TextMarkerSourceParser(/* IProblemReporter reporter */) {
    // this.problemReporter = reporter;
  }

  public static class TMLexer extends TextMarkerLexer {
    public TMLexer(CharStream lexer) {
      super(lexer);
    }

    @Override
    public Token nextToken() {
      startPos = getCharPositionInLine();
      return super.nextToken();
    }
  }

  public ModuleDeclaration parse(char[] fileName, char[] content, IProblemReporter reporter) {// throws
    this.problemReporter = reporter;

    TextMarkerModuleDeclaration moduleDeclaration = new TextMarkerModuleDeclaration(content.length,
            true);

    CharStream st = new ANTLRStringStream(new String(content));
    TextMarkerLexer lexer = new TMLexer(st);

    CommonTokenStream tokens = new CommonTokenStream(lexer);
    this.tokenStream = tokens;

    TextMarkerParser parser = new TextMarkerParser(this.tokenStream);

    for (String each : TextMarkerKeywordsManager.getKeywords(ITextMarkerKeywords.BASIC)) {
      parser.addPredefinedType(each);
    }
    // TODO refacor , also in grammar
    List<String> variables = parser.getVariables();
    Map<String, String> variableTypes = parser.getVariableTypes();
    for (String each : TextMarkerKeywordsManager.getKeywords(ITextMarkerKeywords.CONDITION)) {
      variables.add(each);
      variableTypes.put(each, "CONDITION");
    }
    for (String each : TextMarkerKeywordsManager.getKeywords(ITextMarkerKeywords.ACTION)) {
      variables.add(each);
      variableTypes.put(each, "ACTION");
    }
    for (String each : TextMarkerKeywordsManager.getKeywords(ITextMarkerKeywords.BOOLEANFUNCTION)) {
      variables.add(each);
      variableTypes.put(each, "BOOLEANFUNCTION");
    }
    for (String each : TextMarkerKeywordsManager.getKeywords(ITextMarkerKeywords.NUMBERFUNCTION)) {
      variables.add(each);
      variableTypes.put(each, "NUMBERFUNCTION");
    }
    for (String each : TextMarkerKeywordsManager.getKeywords(ITextMarkerKeywords.STRINGFUNCTION)) {
      variables.add(each);
      variableTypes.put(each, "STRINGFUNCTION");
    }
    for (String each : TextMarkerKeywordsManager.getKeywords(ITextMarkerKeywords.TYPEFUNCTION)) {
      variables.add(each);
      variableTypes.put(each, "TYPEFUNCTION");
    }
    parser.addPredefinedType("Document");
    parser.addPredefinedType("Annotation");

    parser.md = moduleDeclaration;
    parser.length = content.length;
    parser.converter = new DLTKTokenConverter(content);
    parser.reporter = new DLTKTextMarkerErrorReporter(parser.converter, problemReporter, parser);

    parser.descriptor = new DescriptorManager();

    moduleDeclaration.descriptorInfo = parser.descriptor;

    String name = "Dynamic";
    if (fileName != null) {
      File fn = new File(new String(fileName));
      name = fn.getName();
      int lastIndexOf = name.lastIndexOf(".tm");
      name = name.substring(0, lastIndexOf);
    }
    try {
      parser.file_input(name);
    } catch (Throwable e) {
      if (DLTKCore.DEBUG_PARSER) {
        e.printStackTrace();
      }
    }
    return moduleDeclaration;
  }

  public CommonTokenStream getTokenStream() {
    return tokenStream;
  }

}
