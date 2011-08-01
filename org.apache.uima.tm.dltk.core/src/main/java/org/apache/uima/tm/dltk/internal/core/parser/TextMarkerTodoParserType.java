package org.apache.uima.tm.dltk.internal.core.parser;

import org.apache.uima.tm.dltk.core.TextMarkerPlugin;
import org.apache.uima.tm.dltk.parser.ast.TextMarkerScriptBlock;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.compiler.task.ITodoTaskPreferences;
import org.eclipse.dltk.compiler.task.TodoTaskPreferences;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.builder.AbstractTodoTaskBuildParticipantType;
import org.eclipse.dltk.core.builder.IBuildParticipant;


public class TextMarkerTodoParserType extends AbstractTodoTaskBuildParticipantType {

  // @Override
  // protected ITodoTaskPreferences getPreferences(IScriptProject project) {
  // return new TodoTaskPreferencesOnPreferenceLookupDelegate(TextMarkerPlugin.PLUGIN_ID, project);
  // }

  @Override
  protected ITodoTaskPreferences getPreferences(IScriptProject project) {
    return new TodoTaskPreferences(TextMarkerPlugin.getDefault().getPluginPreferences());
  }

  @Override
  protected IBuildParticipant getBuildParticipant(ITodoTaskPreferences preferences) {
    return new TextMarkerTodoTaskAstParser(preferences);
  }

  private static class TextMarkerTodoTaskAstParser extends TodoTaskBuildParticipant implements
          IBuildParticipant {

    public TextMarkerTodoTaskAstParser(ITodoTaskPreferences preferences) {
      super(preferences);
    }

    @Override
    protected boolean isSimpleNode(ASTNode node) {
      if (node instanceof TextMarkerScriptBlock) {
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