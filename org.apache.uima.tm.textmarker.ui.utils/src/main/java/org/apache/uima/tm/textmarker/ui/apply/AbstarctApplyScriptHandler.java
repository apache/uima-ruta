package org.apache.uima.tm.textmarker.ui.apply;

import org.apache.uima.tm.textmarker.ui.utils.TextMarkerUtilsPlugin;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.resources.IFile;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.part.FileEditorInput;


public abstract class AbstarctApplyScriptHandler implements IHandler {

  public void addHandlerListener(IHandlerListener handlerListener) {
  }

  public void dispose() {
  }

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    IWorkbench workbench = TextMarkerUtilsPlugin.getDefault().getWorkbench();
    IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
    IEditorPart part = page.getActiveEditor();
    IEditorInput editorInput = part.getEditorInput();
    if (editorInput instanceof FileEditorInput) {
      FileEditorInput input = (FileEditorInput) editorInput;
      IFile path = input.getFile();
      getJob(event, path).schedule();
    }
    return null;
  }

  abstract AbstractApplyScriptHandlerJob getJob(ExecutionEvent event, IFile path);

  public boolean isEnabled() {
    return true;
  }

  public boolean isHandled() {
    return true;
  }

  public void removeHandlerListener(IHandlerListener handlerListener) {

  }
}
