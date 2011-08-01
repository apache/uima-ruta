package org.apache.uima.tm.dltk.internal.core.packages;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.uima.tm.dltk.core.TextMarkerPlugin;
import org.apache.uima.tm.dltk.parser.ast.declarations.TextMarkerPackageDeclaration;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.statements.Statement;


public class TextMarkerBuildPathPackageCollector extends ASTVisitor {

  private final List requireDirectives = new ArrayList();

  private final Set packagesRequired = new HashSet();

  private final Set packagesProvided = new HashSet();

  public void process(ModuleDeclaration declaration) {
    try {
      declaration.traverse(this);
    } catch (Exception e) {
      TextMarkerPlugin.error(e);
    }
  }

  @Override
  public boolean visit(Statement s) throws Exception {
    if (s instanceof TextMarkerPackageDeclaration) {
      final TextMarkerPackageDeclaration pkg = (TextMarkerPackageDeclaration) s;
      packagesProvided.add(pkg.getName());
      return false;
    }
    return super.visit(s);
  }

  /**
   * @return the requireDirectives
   */
  public List getRequireDirectives() {
    return requireDirectives;
  }

  /**
   * @return the packagesRequired
   */
  public Set getPackagesRequired() {
    return packagesRequired;
  }

  /**
   * @return the packagesProvided
   */
  public Set getPackagesProvided() {
    return packagesProvided;
  }

}
