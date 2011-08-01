package org.apache.uima.tm.textmarker.testing.searchStrategy;

import org.apache.uima.tm.cev.extension.ICEVSearchStrategy;
import org.apache.uima.tm.dltk.internal.core.builder.TextMarkerProjectUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;


public class TestingSearchStrategy implements ICEVSearchStrategy {

  private int priority;

  public TestingSearchStrategy(int priority) {
    super();
    this.priority = priority;
  }

  @Override
  public int getPriority() {
    return priority;
  }

  @Override
  public IFile searchDescriptor(IFile file) {
    IPath location = file.getLocation();
    IProject project = file.getProject();
    IFolder testFolder = project.getFolder(TextMarkerProjectUtils.getDefaultTestLocation());
    IFolder descFolder = project.getFolder(TextMarkerProjectUtils.getDefaultDescriptorLocation());
    IPath relativeTo = location.makeRelativeTo(testFolder.getLocation());
    IPath segments = relativeTo.removeLastSegments(2);
    String scriptName = segments.lastSegment();
    scriptName += "TypeSystem.xml";
    segments = segments.removeLastSegments(1);
    IFolder descPackageFolder = null;
    try {
      descPackageFolder = descFolder.getFolder(segments);
    } catch (Exception e) {
      return null;
    }
    if (descPackageFolder == null || !descPackageFolder.exists()) {
      return null;
    }
    IFile result = descPackageFolder.getFile(scriptName);
    if (result == null || !result.exists()) {
      return null;
    }
    return result;
  }

}
