package org.apache.uima.tm.textmarker.action;

import java.util.Collection;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.expression.bool.BooleanExpression;
import org.apache.uima.tm.textmarker.kernel.expression.bool.SimpleBooleanExpression;
import org.apache.uima.tm.textmarker.kernel.expression.number.NumberExpression;
import org.apache.uima.tm.textmarker.kernel.expression.number.SimpleNumberExpression;
import org.apache.uima.tm.textmarker.kernel.expression.resource.WordListExpression;
import org.apache.uima.tm.textmarker.kernel.expression.type.TypeExpression;
import org.apache.uima.tm.textmarker.kernel.rule.RuleMatch;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;
import org.apache.uima.tm.textmarker.resource.TextMarkerWordList;
import org.apache.uima.tm.textmarker.resource.TreeWordList;


public class MarkFastAction extends AbstractMarkAction {

  private WordListExpression list;

  private BooleanExpression ignore;

  private NumberExpression ignoreLength;

  public MarkFastAction(TypeExpression type, WordListExpression list, BooleanExpression ignore,
          NumberExpression ignoreLength) {
    super(type);
    this.list = list;
    this.ignore = ignore == null ? new SimpleBooleanExpression(false) : ignore;
    this.ignoreLength = ignoreLength == null ? new SimpleNumberExpression(Integer.valueOf(0))
            : ignoreLength;
  }

  @Override
  public void execute(RuleMatch match, TextMarkerRuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    // Annotation matchedAnnotation = match.getMatchedAnnotation(stream,
    // null);
    // TODO matched annotation is Document!!! HOTFIX
    TextMarkerWordList wl = list.getList(element.getParent());
    if (wl instanceof TreeWordList) {
      Collection<AnnotationFS> found = wl.find(stream, ignore.getBooleanValue(element.getParent()),
              ignoreLength.getIntegerValue(element.getParent()), null, 0);
      for (AnnotationFS annotation : found) {
        TextMarkerBasic anchor = stream.getFirstBasicInWindow(annotation);
        createAnnotation(anchor, element, stream, annotation);
      }
    }
    // if(list.contains(matchedAnnotation.getCoveredText(), ignore, 0)) {
    // createAnnotation(match, stream, matchedAnnotation);
    // } else {
    // List<TextMarkerBasic> annotationsInWindow =
    // stream.getBasicAnnotationsInWindow(matchedAnnotation,
    // TextMarkerBasic.class);
    // for (TextMarkerBasic textMarkerBasic : annotationsInWindow) {
    // if(list.contains(textMarkerBasic.getCoveredText(), ignore, 0)) {
    // createAnnotation(match, stream, textMarkerBasic);
    // }
    // }
    // }
  }

  public WordListExpression getList() {
    return list;
  }

  public BooleanExpression isIgnore() {
    return ignore;
  }

}
