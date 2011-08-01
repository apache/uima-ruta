package org.apache.uima.tm.textmarker.kernel.visitor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.tm.textmarker.kernel.ScriptApply;
import org.apache.uima.tm.textmarker.kernel.TextMarkerElement;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;


public class TimeProfilerVisitor implements TextMarkerInferenceVisitor {

  private Map<TextMarkerElement, Long> timeInfo;

  public TimeProfilerVisitor() {
    super();
    timeInfo = new HashMap<TextMarkerElement, Long>();
  }

  public void beginVisit(TextMarkerElement element, ScriptApply result) {
    getTimeInfo().put(element, System.currentTimeMillis());
  }

  public void endVisit(TextMarkerElement element, ScriptApply result) {
    Long start = getTimeInfo().get(element);
    long value = System.currentTimeMillis() - start;
    getTimeInfo().put(element, value);
  }

  public void finished(TextMarkerStream stream, List<TextMarkerInferenceVisitor> visitors) {
    // others do the work
  }

  public Map<TextMarkerElement, Long> getTimeInfo() {
    return timeInfo;
  }

}
