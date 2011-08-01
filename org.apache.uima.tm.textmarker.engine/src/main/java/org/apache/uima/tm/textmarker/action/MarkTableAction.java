package org.apache.uima.tm.textmarker.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.tm.textmarker.kernel.TextMarkerBlock;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.expression.number.NumberExpression;
import org.apache.uima.tm.textmarker.kernel.expression.resource.WordTableExpression;
import org.apache.uima.tm.textmarker.kernel.expression.string.StringExpression;
import org.apache.uima.tm.textmarker.kernel.expression.type.TypeExpression;
import org.apache.uima.tm.textmarker.kernel.rule.RuleMatch;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;
import org.apache.uima.tm.textmarker.resource.TextMarkerTable;
import org.apache.uima.tm.textmarker.resource.TextMarkerWordList;


public class MarkTableAction extends AbstractTextMarkerAction {

  private final TypeExpression typeExpr;

  private final WordTableExpression tableExpr;

  private final Map<StringExpression, NumberExpression> featureMap;

  private final NumberExpression indexExpr;

  public MarkTableAction(TypeExpression typeExpr, NumberExpression indexExpr,
          WordTableExpression tableExpr, Map<StringExpression, NumberExpression> featureMap) {
    super();
    this.typeExpr = typeExpr;
    this.indexExpr = indexExpr;
    this.tableExpr = tableExpr;
    this.featureMap = featureMap;
  }

  @Override
  public void execute(RuleMatch match, TextMarkerRuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    TextMarkerBlock block = element.getParent();
    TextMarkerTable table = tableExpr.getTable(block);
    int index = indexExpr.getIntegerValue(block);
    Type type = typeExpr.getType(block);
    Map<String, Integer> map = new HashMap<String, Integer>();
    for (StringExpression each : featureMap.keySet()) {
      map.put(each.getStringValue(block), featureMap.get(each).getIntegerValue(block));
    }
    TextMarkerWordList wordList = table.getWordList(index);
    Collection<AnnotationFS> found = wordList.find(stream, false, 0, new char[] {}, 0);
    for (AnnotationFS annotationFS : found) {
      List<String> rowWhere = table.getRowWhere(index - 1, annotationFS.getCoveredText());
      FeatureStructure newFS = stream.getCas().createFS(type);
      if (newFS instanceof Annotation) {
        Annotation a = (Annotation) newFS;
        a.setBegin(annotationFS.getBegin());
        a.setEnd(annotationFS.getEnd());
        TextMarkerBasic first = stream.getFirstBasicInWindow(annotationFS);
        if (first == null) {
          first = match.getFirstBasic();
        }
        stream.addAnnotation(first, a);
      }
      TOP newStructure = null;
      if (newFS instanceof TOP) {
        newStructure = (TOP) newFS;
        fillFeatures(newStructure, map, annotationFS, element, rowWhere, stream);
        newStructure.addToIndexes();
      }
    }
  }

  private void fillFeatures(TOP structure, Map<String, Integer> map, AnnotationFS annotationFS,
          TextMarkerRuleElement element, List<String> row, TextMarkerStream stream) {
    List<?> featuresList = structure.getType().getFeatures();
    for (int i = 0; i < featuresList.size(); i++) {
      Feature targetFeature = (Feature) featuresList.get(i);
      String name = targetFeature.getName();
      String shortFName = name.substring(name.indexOf(":") + 1, name.length());
      Integer entryIndex = map.get(shortFName);
      Type range = targetFeature.getRange();
      if (entryIndex != null && row.size() >= entryIndex) {
        String value = row.get(entryIndex - 1);
        if (range.getName().equals("uima.cas.String")) {
          structure.setStringValue(targetFeature, value);
        } else if (range.getName().equals("uima.cas.Integer")) {
          Integer integer = Integer.parseInt(value);
          structure.setIntValue(targetFeature, integer);
        } else if (range.getName().equals("uima.cas.Double")) {
          Double d = Double.parseDouble(value);
          structure.setDoubleValue(targetFeature, d);
        } else if (range.getName().equals("uima.cas.Boolean")) {
          Boolean b = Boolean.parseBoolean(value);
          structure.setBooleanValue(targetFeature, b);
        } else {
        }
      }

    }
  }
}
