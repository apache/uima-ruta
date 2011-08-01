package org.apache.uima.tm.cev.searchStrategy;

import java.util.Collections;
import java.util.LinkedList;

import org.apache.uima.tm.cev.dialog.CEVCollectionContentProvider;
import org.apache.uima.tm.cev.extension.ICEVSearchStrategy;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.dialogs.ListDialog;


public class SelectInFolderStrategy implements ICEVSearchStrategy {

  private int priority;

  public SelectInFolderStrategy(int priority) {
    super();
    this.priority = priority;
  }

  public IFile searchDescriptor(IFile file) {
    LinkedList<IFile> files = new LinkedList<IFile>();
    try {
      for (IResource r : file.getParent().members()) {
        if (r.getType() == IResource.FILE
                && r.getFullPath().getFileExtension().toLowerCase().equals("xml")
                && !r.getFullPath().removeFileExtension().lastSegment().toLowerCase().endsWith(
                        "stylemap"))
          files.add((IFile) r);
      }
    } catch (CoreException e) {
      return null;
    }
    if (files.isEmpty()) {
      return null;
    }
    if (files.size() == 1) {
      return files.get(0);
    }
    Collections.sort(files, new IFileComparator());
    ListDialog ld = new ListDialog(Display.getCurrent().getActiveShell());
    ld.setContentProvider(new CEVCollectionContentProvider<LinkedList<IFile>>());
    ld.setLabelProvider(new LabelProvider() {
      @Override
      public String getText(Object element) {
        return ((IFile) element).getName();
      }
    });
    ld.setTitle("Select Type System Descriptor");
    ld.setInput(files);
    ld.open();

    if (ld.getResult() != null) {
      return (IFile) ld.getResult()[0];
    } else {
      return null;
    }
  }

  public int getPriority() {
    return priority;
  }

}
