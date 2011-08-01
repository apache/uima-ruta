package org.apache.uima.tm.textmarker.ui.apply;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.resources.IFile;

public class ApplyScriptKeepBasicsHandler extends AbstarctApplyScriptHandler {

  @Override
  AbstractApplyScriptHandlerJob getJob(ExecutionEvent event, IFile path) {
    return new ApplyScriptKeepBasicsJob(event, path);
  }

}
