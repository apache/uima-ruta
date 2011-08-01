package org.apache.uima.tm.textmarker.cev.searchStrategy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.uima.tm.cev.dialog.CEVCollectionContentProvider;
import org.apache.uima.tm.cev.extension.ICEVSearchStrategy;
import org.apache.uima.tm.dltk.internal.core.builder.TextMarkerProjectUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.dialogs.ListDialog;


public class DescriptorFolderSearchStrategy implements ICEVSearchStrategy {

  private int priority;

  public DescriptorFolderSearchStrategy(int priority) {
    super();
    this.priority = priority;
  }

  public int getPriority() {
    return priority;
  }

  public IFile searchDescriptor(IFile file) {
    IProject project = file.getProject();
    IFolder folder = project.getFolder(TextMarkerProjectUtils.getDefaultDescriptorLocation());
    try {
      List<IFile> list = collectTypeSystems(folder);
      ListDialog ld = new ListDialog(Display.getCurrent().getActiveShell());
      ld.setContentProvider(new CEVCollectionContentProvider<LinkedList<IFile>>());
      ld.setLabelProvider(new LabelProvider() {
        @Override
        public String getText(Object element) {
          return ((IFile) element).getName();
        }
      });
      if (list == null || list.isEmpty()) {
        return null;
      }

      ld.setTitle("Select Type System Descriptor");
      ld.setInput(list);
      ld.open();

      if (ld.getResult() != null) {
        return (IFile) ld.getResult()[0];
      } else {
        return null;
      }
    } catch (Exception e) {
      return null;
    }

  }

  private List<IFile> collectTypeSystems(IFolder folder) throws CoreException {
    List<IFile> result = new ArrayList<IFile>();
    for (IResource each : folder.members()) {
      if (each instanceof IFolder) {
        result.addAll(collectTypeSystems((IFolder) each));
      } else if (each instanceof IFile && each.getFileExtension() != null
              && each.getFileExtension().equals("xml")) {
        result.add((IFile) each);
      }
    }
    return result;
  }
}
