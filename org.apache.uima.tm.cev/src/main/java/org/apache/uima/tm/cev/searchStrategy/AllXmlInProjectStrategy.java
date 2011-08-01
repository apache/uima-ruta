package org.apache.uima.tm.cev.searchStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.uima.tm.cev.dialog.CEVCollectionContentProvider;
import org.apache.uima.tm.cev.extension.ICEVSearchStrategy;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.dialogs.ListDialog;


public class AllXmlInProjectStrategy implements ICEVSearchStrategy {

  private int priority;

  public AllXmlInProjectStrategy(int priority) {
    super();
    this.priority = priority;
  }

  public int getPriority() {
    return priority;
  }

  public IFile searchDescriptor(IFile file) {
    IProject project = file.getProject();
    try {
      List<IFile> list = collectTypeSystems(project);
      if (list.size() == 1) {
        return list.get(0);
      }
      Collections.sort(list, new IFileComparator());
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

  private List<IFile> collectTypeSystems(IContainer folder) throws CoreException {
    List<IFile> result = new ArrayList<IFile>();
    for (IResource each : folder.members()) {
      if (each instanceof IContainer) {
        result.addAll(collectTypeSystems((IContainer) each));
      } else if (each instanceof IFile
              && each.getFileExtension().equals("xml")
              && !each.getFullPath().removeFileExtension().lastSegment().toLowerCase().endsWith(
                      "stylemap")) {
        result.add((IFile) each);
      }
    }
    return result;
  }
}
