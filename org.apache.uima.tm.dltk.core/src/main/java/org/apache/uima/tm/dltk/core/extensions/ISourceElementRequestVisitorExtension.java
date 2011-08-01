package org.apache.uima.tm.dltk.core.extensions;

import org.eclipse.dltk.ast.declarations.Declaration;

public interface ISourceElementRequestVisitorExtension {

  int getModifiers(Declaration s);

  // boolean visit(Statement statement, TextMarkerSourceElementRequestVisitor
  // original);
  //
  // boolean extendedExitRequired(MethodDeclaration method,
  // TextMarkerSourceElementRequestVisitor tmSourceElementRequestVisitor);
  //
  // ExitFromType getExitExtended(MethodDeclaration method,
  // TextMarkerSourceElementRequestVisitor tmSourceElementRequestVisitor);
  //
  // ExitFromType processField(FieldDeclaration decl,
  // TextMarkerSourceElementRequestVisitor tmSourceElementRequestVisitor);

}
