/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.uima.ruta.ide.ui.console;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.debug.ui.DLTKDebugUIPlugin;
import org.eclipse.dltk.internal.ui.editor.EditorUtility;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.console.IHyperlink;
import org.eclipse.ui.console.TextConsole;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

/**
 * A hyperlink from a stack trace line of the form "(file "*.*")"
 */
public class RutaFileHyperlink implements IHyperlink {

  private TextConsole fConsole;

  public RutaFileHyperlink(TextConsole console) {
    fConsole = console;
  }

  public void linkEntered() {
  }

  public void linkExited() {
  }

  public void linkActivated() {
    try {
      String fileName;
      int lineNumber;
      try {
        String linkText = getLinkText();
        fileName = getFileName(linkText);
        lineNumber = getLineNumber(linkText);
      } catch (CoreException e1) {
        ErrorDialog.openError(DLTKDebugUIPlugin.getActiveWorkbenchShell(),
                ConsoleMessages.RutaFileHyperlink_Error, ConsoleMessages.RutaFileHyperlink_Error,
                e1.getStatus());
        return;
      }

      // documents start at 0
      if (lineNumber > 0) {
        lineNumber--;
      }
      Object sourceElement = getSourceModule(fileName);
      if (sourceElement != null) {
        IEditorPart part = EditorUtility.openInEditor(sourceElement);
        IEditorPart editorPart = EditorUtility.openInEditor(sourceElement);
        if (editorPart instanceof ITextEditor && lineNumber >= 0) {
          ITextEditor textEditor = (ITextEditor) editorPart;
          IDocumentProvider provider = textEditor.getDocumentProvider();
          IEditorInput input = part.getEditorInput();
          provider.connect(input);
          IDocument document = provider.getDocument(input);
          try {
            IRegion line = document.getLineInformation(lineNumber);
            textEditor.selectAndReveal(line.getOffset(), line.getLength());
          } catch (BadLocationException e) {

          }
          provider.disconnect(input);
        }
        return;
      }
      // did not find source
      MessageDialog.openInformation(DLTKDebugUIPlugin.getActiveWorkbenchShell(),
              ConsoleMessages.RutaFileHyperlink_Information_1, MessageFormat.format(
                      ConsoleMessages.RutaFileHyperlink_Source_not_found_for__0__2,
                      new String[] { fileName }));
    } catch (CoreException e) {
      DLTKDebugUIPlugin.errorDialog(
              ConsoleMessages.RutaFileHyperlink_An_exception_occurred_while_following_link__3, e);
      return;
    }
  }

  public String getEditorId(IEditorInput input, Object inputObject) {
    try {
      IEditorDescriptor descriptor = IDE.getEditorDescriptor(input.getName());
      return descriptor.getId();
    } catch (PartInitException e) {
      return null;
    }
  }

  public IEditorInput getEditorInput(Object item) {
    return EditorUtility.getEditorInput(item);
  }

  protected Object getSourceModule(String fileName) {
    IFile f = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(new Path(fileName));
    return f;
  }

  /**
   * Returns the fully qualified name of the type to open
   * 
   * @return fully qualified type name
   * @exception CoreException
   *              if unable to parse the type name
   */
  protected String getFileName(String linkText) throws CoreException {
    Pattern p = Pattern.compile("\\(file \"(.*)\"");
    Matcher m = p.matcher(linkText);
    if (m.find()) {
      String name = m.group(1);
      return name;
    }
    IStatus status = new Status(IStatus.ERROR, DLTKDebugUIPlugin.getUniqueIdentifier(), 0,
            ConsoleMessages.RutaFileHyperlink_Unable_to_parse_type_name_from_hyperlink__5, null);
    throw new CoreException(status);
  }

  /**
   * Returns the line number associated with the stack trace or -1 if none.
   * 
   * @exception CoreException
   *              if unable to parse the number
   */
  protected int getLineNumber(String linkText) throws CoreException {
    Pattern p = Pattern.compile("line (\\d*)");
    Matcher m = p.matcher(linkText);
    if (m.find()) {
      String lineText = m.group(1);
      try {
        return Integer.parseInt(lineText);
      } catch (NumberFormatException e) {
        IStatus status = new Status(IStatus.ERROR, DLTKDebugUIPlugin.getUniqueIdentifier(), 0,
                ConsoleMessages.RutaFileHyperlink_Unable_to_parse_line_number_from_hyperlink__6, e);
        throw new CoreException(status);
      }
    }
    IStatus status = new Status(IStatus.ERROR, DLTKDebugUIPlugin.getUniqueIdentifier(), 0,
            ConsoleMessages.RutaFileHyperlink_Unable_to_parse_line_number_from_hyperlink__7, null);
    throw new CoreException(status);
  }

  /**
   * Returns the console this link is contained in.
   * 
   * @return console
   */
  protected TextConsole getConsole() {
    return fConsole;
  }

  /**
   * Returns this link's text
   * 
   * @exception CoreException
   *              if unable to retrieve the text
   */
  protected String getLinkText() throws CoreException {
    try {
      IDocument document = getConsole().getDocument();
      IRegion region = getConsole().getRegion(this);
      int regionOffset = region.getOffset();

      int lineNumber = document.getLineOfOffset(regionOffset);
      IRegion lineInformation = document.getLineInformation(lineNumber);
      int lineOffset = lineInformation.getOffset();
      String line = document.get(lineOffset, lineInformation.getLength());

      int regionOffsetInLine = regionOffset - lineOffset;

      int linkEnd = line.indexOf(')', regionOffsetInLine);
      int linkStart = line.lastIndexOf(' ', regionOffsetInLine);

      return line.substring(linkStart == -1 ? 0 : linkStart + 1, linkEnd + 1);
    } catch (BadLocationException e) {
      IStatus status = new Status(IStatus.ERROR, DLTKDebugUIPlugin.getUniqueIdentifier(), 0,
              ConsoleMessages.RutaFileHyperlink_Unable_to_retrieve_hyperlink_text__8, e);
      throw new CoreException(status);
    }
  }

}
