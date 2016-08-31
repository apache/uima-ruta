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

import org.apache.uima.ruta.ide.RutaIdeCorePlugin;
import org.apache.uima.ruta.ide.parser.ast.RutaScriptBlock;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.compiler.task.ITodoTaskPreferences;
import org.eclipse.dltk.compiler.task.TodoTaskPreferences;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.builder.AbstractTodoTaskBuildParticipantType;
import org.eclipse.dltk.core.builder.IBuildParticipant;

public class RutaTodoParserType extends AbstractTodoTaskBuildParticipantType {

  // @Override
  // protected ITodoTaskPreferences getPreferences(IScriptProject project) {
  // return new TodoTaskPreferencesOnPreferenceLookupDelegate(RutaIdePlugin.PLUGIN_ID,
  // project);
  // }

  @Override
  protected ITodoTaskPreferences getPreferences(IScriptProject project) {
    return new TodoTaskPreferences(RutaIdeCorePlugin.getDefault().getPluginPreferences());
  }

  @Override
  protected IBuildParticipant getBuildParticipant(ITodoTaskPreferences preferences) {
    return new RutaTodoTaskAstParser(preferences);
  }

  private static class RutaTodoTaskAstParser extends TodoTaskBuildParticipant implements
          IBuildParticipant {

    public RutaTodoTaskAstParser(ITodoTaskPreferences preferences) {
      super(preferences);
    }

    @Override
    protected boolean isSimpleNode(ASTNode node) {
      if (node instanceof RutaScriptBlock) {
        return true;
      }
      return super.isSimpleNode(node);
    }

    @Override
    protected int findCommentStart(char[] content, int begin, int end) {
      if (!isCheckRanges()) {
        return super.findCommentStart(content, begin, end);
      }
      for (int i = begin; i < end - 1; ++i) {
        if (content[i] == '/' && isValid(i)) {
          return i + 2;
        }
      }
      return -1;
    }

  }

}
