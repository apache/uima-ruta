package org.apache.uima.tm.textmarker.kernel.rule;

import java.util.List;

import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.textmarker.kernel.TextMarkerBlock;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.expression.TextMarkerExpression;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;


public interface TextMarkerMatcher {

  List<TextMarkerBasic> getMatchingBasics(TextMarkerStream stream, TextMarkerBlock parent);

  FSIterator<AnnotationFS> getMatchingBasics2(TextMarkerStream stream, TextMarkerBlock parent);

  boolean match(TextMarkerBasic currentBasic, TextMarkerStream stream, TextMarkerBlock parent);

  Type getType(TextMarkerBlock parent, TextMarkerStream stream);

  TextMarkerExpression getExpression();

}
