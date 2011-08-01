package org.apache.uima.tm.dltk.internal.debug.ui;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.ILineBreakpoint;
import org.eclipse.dltk.debug.core.DLTKDebugPlugin;
import org.eclipse.dltk.debug.ui.breakpoints.BreakpointUtils;
import org.eclipse.dltk.debug.ui.breakpoints.ScriptToggleBreakpointAdapter;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.texteditor.ITextEditor;

import org.apache.uima.tm.dltk.internal.debug.TextMarkerDebugConstants;

public class TextMarkerToggleBreakpointAdapter extends ScriptToggleBreakpointAdapter {
  // Line breakpoints

  @Override
  protected String getDebugModelId() {
    return TextMarkerDebugConstants.DEBUG_MODEL_ID;
  }

  @Override
  public boolean canToggleLineBreakpoints(IWorkbenchPart part, ISelection selection) {

    return true;
  }

  @Override
  public void toggleLineBreakpoints(IWorkbenchPart part, ISelection selection) throws CoreException {
    if (selection instanceof ITextSelection) {
      ITextEditor textEditor = getTextEditor(part);
      ITextSelection textSelection = (ITextSelection) selection;
      int lineNumber = textSelection.getStartLine() + 1; // one based

      ILineBreakpoint breakpoint = BreakpointUtils.findLineBreakpoint(textEditor, lineNumber);
      if (breakpoint != null) {
        breakpoint.delete();
        return;
      }

      if (textEditor instanceof ScriptEditor) {
        ScriptEditor scriptEditor = (ScriptEditor) textEditor;
        try {
          IDocument doc = scriptEditor.getScriptSourceViewer().getDocument();

          IRegion region = doc.getLineInformation(lineNumber - 1);
          String string = doc.get(region.getOffset(), region.getLength());
          int index = string.indexOf("function");
          if (index != -1) {
            string = string.substring(index + "function".length()).trim();
            int apos = string.indexOf('(');
            if (apos >= 0) {
              string = string.substring(0, apos).trim();
            }

            BreakpointUtils.addMethodEntryBreakpoint(textEditor, lineNumber, string);

            return;
          }
        } catch (BadLocationException e) {
          DLTKDebugPlugin.log(e);
          return;
        }
      }
      // else
      BreakpointUtils.addLineBreakpoint(textEditor, lineNumber);
    }
  }

  // Method breakpoints
  public boolean canToggleMethodBreakpoints(IWorkbenchPart part, ISelection selection) {
    return false;
  }

  public void toggleMethodBreakpoints(IWorkbenchPart part, ISelection selection)
          throws CoreException {

  }

  // Watchpoints
  public boolean canToggleWatchpoints(IWorkbenchPart part, ISelection selection) {
    if (selection instanceof ITextSelection) {
      final ITextSelection textSelection = (ITextSelection) selection;
      final String text = textSelection.getText();
      if (part instanceof ScriptEditor) {
        ScriptEditor scriptEditor = (ScriptEditor) part;
        try {
          IDocument doc = scriptEditor.getScriptSourceViewer().getDocument();
          IRegion region = doc.getLineInformation(textSelection.getStartLine());
          String string = doc.get(region.getOffset(), region.getLength());

          return string.indexOf('=') != -1 || string.trim().startsWith("var ");
        } catch (BadLocationException e) {
          DLTKUIPlugin.log(e);
        }
      }

      return text.indexOf("=") != -1;
    }
    return true;
  }

  public void toggleWatchpoints(IWorkbenchPart part, ISelection selection) throws CoreException {
    if (selection instanceof ITextSelection) {
      ITextEditor editor = getTextEditor(part);
      ITextSelection textSelection = (ITextSelection) selection;
      int lineNumber = textSelection.getStartLine() + 1; // one based

      IResource resource = BreakpointUtils.getBreakpointResource(editor);
      IBreakpoint[] breakpoints = DebugPlugin.getDefault().getBreakpointManager().getBreakpoints();

      for (int i = 0; i < breakpoints.length; i++) {
        IBreakpoint breakpoint = breakpoints[i];
        if (resource.equals(breakpoint.getMarker().getResource())) {
          if (((ILineBreakpoint) breakpoint).getLineNumber() == lineNumber) {
            breakpoint.delete(); // delete existing
            // breakpoint
            return;
          }
        }
      }

      if (editor instanceof ScriptEditor) {

        try {
          ScriptEditor scriptEditor = (ScriptEditor) editor;
          IDocument doc = scriptEditor.getScriptSourceViewer().getDocument();
          IRegion region = doc.getLineInformation(lineNumber - 1);
          String string = doc.get(region.getOffset(), region.getLength());

          int index = string.indexOf('=');
          if (index != -1) {
            string = string.substring(0, index);
          }
          index = string.lastIndexOf('.');
          if (index != -1) {
            string = string.substring(index + 1);
          }
          string = string.trim();
          index = string.lastIndexOf(' ');
          if (index != -1) {
            string = string.substring(index + 1).trim();
          }

          if (string.endsWith(";")) {
            string = string.substring(0, string.length() - 1);
          }

          BreakpointUtils.addWatchPoint(editor, lineNumber, string);
        } catch (BadLocationException e) {
          DLTKUIPlugin.log(e);
        }
      }
    }
  }

  public void toggleBreakpoints(IWorkbenchPart part, ISelection selection) throws CoreException {
    toggleLineBreakpoints(part, selection);
  }

  public boolean canToggleBreakpoints(IWorkbenchPart part, ISelection selection) {
    return canToggleLineBreakpoints(part, selection);
  }
}
