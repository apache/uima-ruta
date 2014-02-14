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

package org.apache.uima.ruta.ide.core.parser;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Token;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.ide.core.IRutaKeywords;
import org.apache.uima.ruta.ide.core.RutaKeywordsManager;
import org.apache.uima.ruta.ide.core.builder.DescriptorManager;
import org.apache.uima.ruta.ide.parser.ast.RutaModuleDeclaration;
import org.apache.uima.ruta.parser.RutaLexer;
import org.eclipse.dltk.ast.parser.AbstractSourceParser;
import org.eclipse.dltk.ast.parser.IModuleDeclaration;
import org.eclipse.dltk.compiler.env.IModuleSource;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.core.DLTKCore;

public class RutaSourceParser extends AbstractSourceParser {

  private CommonTokenStream tokenStream;

  private IProblemReporter problemReporter = null;

  public RutaSourceParser(/* IProblemReporter reporter */) {
    // this.problemReporter = reporter;
  }

  public static class TMLexer extends RutaLexer {
    public TMLexer(CharStream lexer) {
      super(lexer);
    }

    @Override
    public Token nextToken() {
      startPos = getCharPositionInLine();
      return super.nextToken();
    }
  }

  public IModuleDeclaration parse(IModuleSource input, IProblemReporter reporter) {
    return parse(input.getFileName(), input.getSourceContents(), reporter);
  }

  public IModuleDeclaration parse(String fileName, String content, IProblemReporter reporter) {
    this.problemReporter = reporter;

    RutaModuleDeclaration moduleDeclaration = new RutaModuleDeclaration(content.length(), true);

    CharStream st = new ANTLRStringStream(content);
    RutaLexer lexer = new TMLexer(st);

    CommonTokenStream tokens = new CommonTokenStream(lexer);
    this.tokenStream = tokens;

    RutaParser parser = new RutaParser(this.tokenStream);

    for (String each : RutaKeywordsManager.getKeywords(IRutaKeywords.BASIC)) {
      parser.addPredefinedType(each);
    }
    // TODO refacor , also in grammar
    List<String> variables = parser.getVariables();
    Map<String, String> variableTypes = parser.getVariableTypes();
    for (String each : RutaKeywordsManager.getKeywords(IRutaKeywords.CONDITION)) {
      variables.add(each);
      variableTypes.put(each, "CONDITION");
    }
    for (String each : RutaKeywordsManager.getKeywords(IRutaKeywords.ACTION)) {
      variables.add(each);
      variableTypes.put(each, "ACTION");
    }
    for (String each : RutaKeywordsManager.getKeywords(IRutaKeywords.BOOLEANFUNCTION)) {
      variables.add(each);
      variableTypes.put(each, "BOOLEANFUNCTION");
    }
    for (String each : RutaKeywordsManager.getKeywords(IRutaKeywords.NUMBERFUNCTION)) {
      variables.add(each);
      variableTypes.put(each, "NUMBERFUNCTION");
    }
    for (String each : RutaKeywordsManager.getKeywords(IRutaKeywords.STRINGFUNCTION)) {
      variables.add(each);
      variableTypes.put(each, "STRINGFUNCTION");
    }
    for (String each : RutaKeywordsManager.getKeywords(IRutaKeywords.TYPEFUNCTION)) {
      variables.add(each);
      variableTypes.put(each, "TYPEFUNCTION");
    }
    parser.addPredefinedType("Document");
    parser.addPredefinedType("Annotation");

    parser.md = moduleDeclaration;
    parser.length = content.length();
    parser.converter = new DLTKTokenConverter(content.toCharArray());
    parser.reporter = new DLTKRutaErrorReporter(parser.converter, problemReporter, parser);

    parser.descriptor = new DescriptorManager();

    moduleDeclaration.descriptorInfo = parser.descriptor;

    String name = "Dynamic";
    if (fileName != null) {
      File fn = new File(fileName);
      name = fn.getName();
      int lastIndexOf = name.lastIndexOf(RutaEngine.SCRIPT_FILE_EXTENSION);
      if (lastIndexOf > 0) {
        name = name.substring(0, lastIndexOf);
      }
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
