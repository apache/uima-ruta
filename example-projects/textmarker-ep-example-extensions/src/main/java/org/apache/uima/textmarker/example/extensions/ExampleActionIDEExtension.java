package org.apache.uima.textmarker.example.extensions;

import java.util.List;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.apache.uima.textmarker.ide.core.extensions.IIDEActionExtension;
import org.eclipse.dltk.ast.expressions.Expression;

public class ExampleActionIDEExtension implements IIDEActionExtension {
  private final String[] strings = new String[] {"ExampleAction"};

  public String[] getKnownExtensions() {
    return strings;
  }

  public void checkSyntax(Token name, List<Expression> arguments) throws RecognitionException {
//    TODO
  }

}
