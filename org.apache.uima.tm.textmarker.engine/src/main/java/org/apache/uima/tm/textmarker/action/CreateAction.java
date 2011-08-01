package org.apache.uima.tm.textmarker.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.expression.TextMarkerExpression;
import org.apache.uima.tm.textmarker.kernel.expression.number.NumberExpression;
import org.apache.uima.tm.textmarker.kernel.expression.string.StringExpression;
import org.apache.uima.tm.textmarker.kernel.expression.type.TypeExpression;
import org.apache.uima.tm.textmarker.kernel.rule.RuleMatch;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class CreateAction extends AbstractStructureAction {

  private TypeExpression structureType;

  private Map<StringExpression, TextMarkerExpression> features;

  private List<NumberExpression> indexes;

  public CreateAction(TypeExpression structureType,
          Map<StringExpression, TextMarkerExpression> features, List<NumberExpression> indexes) {
    super();
    this.structureType = structureType;
    this.features = features == null ? new HashMap<StringExpression, TextMarkerExpression>()
            : features;
    this.indexes = (indexes == null || indexes.isEmpty()) ? null : indexes;
  }

  @Override
  public void execute(RuleMatch match, TextMarkerRuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    List<Integer> indexList = getIndexList(match, element);
    AnnotationFS matchedAnnotation = match.getMatchedAnnotation(stream, indexList);
    if (matchedAnnotation == null) {
      return;
    }
    Type type = structureType.getType(element.getParent());
    FeatureStructure newFS = stream.getCas().createFS(type);
    if (newFS instanceof Annotation) {
      Annotation a = (Annotation) newFS;
      a.setBegin(matchedAnnotation.getBegin());
      a.setEnd(matchedAnnotation.getEnd());
      TextMarkerBasic first = stream.getFirstBasicInWindow(matchedAnnotation);
      if (first == null) {
        first = match.getFirstBasic();
      }
      stream.addAnnotation(first, a);
    }
    TOP newStructure = null;
    if (newFS instanceof TOP) {
      newStructure = (TOP) newFS;
      fillFeatures(newStructure, features, matchedAnnotation, element, stream);
      newStructure.addToIndexes();
    }
  }

  // TODO refactor duplicate methods -> MarkAction
  protected List<Integer> getIndexList(RuleMatch match, TextMarkerRuleElement element) {
    List<Integer> indexList = new ArrayList<Integer>();
    if (indexes == null || indexes.isEmpty()) {
      int self = match.getRule().getElements().indexOf(element) + 1;
      indexList.add(self);
      return indexList;
    }
    int last = Integer.MAX_VALUE - 1;
    for (NumberExpression each : indexes) {
      int value = each.getIntegerValue(element.getParent());
      for (int i = Math.min(value, last + 1); i < value; i++) {
        indexList.add(i);
      }
      indexList.add(value);
    }
    return indexList;
  }

  public TypeExpression getStructureType() {
    return structureType;
  }

  public Map<StringExpression, TextMarkerExpression> getFeatures() {
    return features;
  }

  public List<NumberExpression> getIndexes() {
    return indexes;
  }
}
