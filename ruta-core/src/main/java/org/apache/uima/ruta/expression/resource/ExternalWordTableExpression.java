package org.apache.uima.ruta.expression.resource;

import java.util.List;

import org.apache.uima.ruta.RutaStatement;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.resource.RutaTable;

public class ExternalWordTableExpression extends WordTableExpression {

  private final List<IStringExpression> args;

  private final String className;

  public ExternalWordTableExpression(String className, List<IStringExpression> args) {
    super();
    this.className = className;
    this.args = args;
  }

  @Override
  public RutaTable getTable(RutaStatement element) {
    return null;
  }

  public List<IStringExpression> getArgs() {
    return args;
  }

  public String getClassName() {
    return className;
  }

  
}
