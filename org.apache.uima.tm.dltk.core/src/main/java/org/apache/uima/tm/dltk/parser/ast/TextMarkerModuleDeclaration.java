package org.apache.uima.tm.dltk.parser.ast;

import org.apache.uima.tm.dltk.internal.core.builder.DescriptorManager;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;


public class TextMarkerModuleDeclaration extends ModuleDeclaration {

  public DescriptorManager descriptorInfo;

  public TextMarkerModuleDeclaration(int sourceLength) {
    super(sourceLength);
  }

  public TextMarkerModuleDeclaration(int length, boolean rebuild) {
    super(length, rebuild);
  }

  public String toString() {
    return this.getClass().getSimpleName() + " : " + super.toString();
  }
}
