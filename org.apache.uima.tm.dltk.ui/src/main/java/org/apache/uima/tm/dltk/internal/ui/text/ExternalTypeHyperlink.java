package org.apache.uima.tm.dltk.internal.ui.text;

import org.eclipse.core.resources.IFile;
import org.eclipse.dltk.internal.ui.editor.DLTKEditorMessages;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.hyperlink.IHyperlink;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.texteditor.ITextEditor;

public class ExternalTypeHyperlink implements IHyperlink {
  private final IRegion region;

  private final IFile file;

  private final String nodeText;

  private final String tsString;

  private final ITextEditor textEditor;

  public ExternalTypeHyperlink(String nodeText, IRegion region, IFile file, String tsString,
          ITextEditor textEditor) {
    this.nodeText = nodeText;
    this.region = region;
    this.file = file;
    this.tsString = tsString;
    this.textEditor = textEditor;
  }

  @Override
  public IRegion getHyperlinkRegion() {
    return region;
  }

  @Override
  public String getHyperlinkText() {
    return nodeText + " in " + tsString;
  }

  @Override
  public String getTypeLabel() {
    return DLTKEditorMessages.ModelElementHyperlink_typeLabel;
  }

  @Override
  public void open() {
    IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
    try {
      page.openEditor(new FileEditorInput(file), "taeconfigurator.editors.MultiPageEditor");
    } catch (PartInitException e) {
      e.printStackTrace();
    }

  }

}
