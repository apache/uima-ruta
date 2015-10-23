package org.apache.uima.ruta.ide.validator;

import java.util.Collection;
import java.util.HashSet;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.ruta.ide.parser.ast.RutaRuleElement;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;

public class RuleElementLabelVisitor extends ASTVisitor{

  private Collection<String> labels = new HashSet<>();
  
  @Override
  public boolean visit(Expression s) throws Exception {
    if(s instanceof RutaRuleElement) {
      RutaRuleElement element = (RutaRuleElement) s;
      if(!StringUtils.isBlank(element.getLabel())) {
        labels.add(element.getLabel());
        return true;
      }
    }
    return false;
  }

  public Collection<String> getLabels() {
    return labels;
  }

}