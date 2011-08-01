package org.apache.uima.tm.textmarker.action;

import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.expression.bool.BooleanExpression;
import org.apache.uima.tm.textmarker.kernel.expression.bool.SimpleBooleanExpression;
import org.apache.uima.tm.textmarker.kernel.expression.string.SimpleStringExpression;
import org.apache.uima.tm.textmarker.kernel.expression.string.StringExpression;
import org.apache.uima.tm.textmarker.kernel.expression.type.TypeExpression;
import org.apache.uima.tm.textmarker.kernel.rule.RuleMatch;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerColoring;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class ColorAction extends AbstractTextMarkerAction {

  private StringExpression bgcolor;

  private StringExpression fgcolor;

  private BooleanExpression selected;

  private TypeExpression type;

  public ColorAction(TypeExpression type, StringExpression bgcolor, StringExpression fgcolor,
          BooleanExpression selected) {
    super();
    this.type = type;
    this.bgcolor = bgcolor == null ? new SimpleStringExpression("red") : bgcolor;
    this.fgcolor = fgcolor == null ? new SimpleStringExpression("red") : fgcolor;
    this.selected = selected == null ? new SimpleBooleanExpression(false) : selected;
  }

  @Override
  public void execute(RuleMatch match, TextMarkerRuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    Type casType = stream.getJCas().getCasType(TextMarkerColoring.type);
    FeatureStructure newAnnotationFS = stream.getCas().createFS(casType);
    TextMarkerColoring coloring = null;
    if (newAnnotationFS instanceof TextMarkerColoring) {
      coloring = (TextMarkerColoring) newAnnotationFS;
      coloring.setBgColor(bgcolor.getStringValue(element.getParent()));
      coloring.setFgColor(fgcolor.getStringValue(element.getParent()));
      coloring.setSelected(selected.getBooleanValue(element.getParent()));
      coloring.setTargetType(type.getType(element.getParent()).getName());
      coloring.addToIndexes();
    }
  }

  public StringExpression getFgColor() {
    return fgcolor;
  }

  public StringExpression getBgColor() {
    return bgcolor;
  }

  public BooleanExpression getSelected() {
    return selected;
  }

  public TypeExpression getType() {
    return type;
  }

}
