package org.apache.uima.tm.textmarker.ui.apply;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.tm.textmarker.engine.TextMarkerEngine;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.resources.IFile;


public class ApplyScriptNoXMIJob extends AbstractApplyScriptHandlerJob {

  ApplyScriptNoXMIJob(ExecutionEvent event, IFile scriptFile) {
    super(event, scriptFile, false);
  }

  @Override
  void initAE(AnalysisEngine ae) {
    ae.setConfigParameterValue(TextMarkerEngine.REMOVE_BASICS, false);
  }

}
