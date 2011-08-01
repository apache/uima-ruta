package org.apache.uima.tm.textmarker.kernel.visitor;

import java.util.List;

import org.apache.uima.tm.textmarker.kernel.ScriptApply;
import org.apache.uima.tm.textmarker.kernel.TextMarkerElement;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;


public class InferenceCrowd implements TextMarkerInferenceVisitor {

  private final List<TextMarkerInferenceVisitor> visitors;

  public InferenceCrowd(List<TextMarkerInferenceVisitor> visitors) {
    super();
    this.visitors = visitors;
  }

  public void beginVisit(TextMarkerElement element, ScriptApply result) {
    for (TextMarkerInferenceVisitor each : visitors) {
      each.beginVisit(element, result);
    }
  }

  public void endVisit(TextMarkerElement element, ScriptApply result) {
    for (TextMarkerInferenceVisitor each : visitors) {
      each.endVisit(element, result);
    }
  }

  public void finished(TextMarkerStream stream) {
    for (TextMarkerInferenceVisitor each : visitors) {
      each.finished(stream, visitors);
    }
  }

  public void finished(TextMarkerStream stream, List<TextMarkerInferenceVisitor> visitors) {
    finished(stream);
  }

}
