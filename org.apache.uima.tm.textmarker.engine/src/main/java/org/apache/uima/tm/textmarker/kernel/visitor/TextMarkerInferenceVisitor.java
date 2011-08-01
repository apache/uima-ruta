package org.apache.uima.tm.textmarker.kernel.visitor;

import java.util.List;

import org.apache.uima.tm.textmarker.kernel.ScriptApply;
import org.apache.uima.tm.textmarker.kernel.TextMarkerElement;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;


public interface TextMarkerInferenceVisitor {

  void beginVisit(TextMarkerElement element, ScriptApply result);

  void endVisit(TextMarkerElement element, ScriptApply result);

  void finished(TextMarkerStream stream, List<TextMarkerInferenceVisitor> visitors);

}
