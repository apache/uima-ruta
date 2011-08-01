package org.apache.uima.tm.dltk.parser.ast;

import java.util.List;

import org.eclipse.dltk.ast.statements.Statement;

public class TextMarkerScriptBlock extends TextMarkerBlock {

  public TextMarkerScriptBlock(String name, String namespace, int nameStart, int nameEnd,
          int declStart, int declEnd) {
    super(name, namespace, nameStart, nameEnd, declStart, declEnd);
  }

  @Override
  public void setElements(List<Statement> stmts) {

  }

}
