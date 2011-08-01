package org.apache.uima.tm.dltk.internal.core;

import org.apache.uima.tm.dltk.internal.core.search.TextMarkerCallProcessor;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.ICallHierarchyFactory;
import org.eclipse.dltk.core.ICallProcessor;
import org.eclipse.dltk.core.ICalleeProcessor;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.search.IDLTKSearchScope;


public class TextMarkerCallHierarchyFactory implements ICallHierarchyFactory {

  public ICalleeProcessor createCalleeProcessor(IMethod method, IProgressMonitor monitor,
          IDLTKSearchScope scope) {
    return new TextMarkerCalleeProcessor(method, monitor, scope);
  }

  public ICallProcessor createCallProcessor() {
    return new TextMarkerCallProcessor();
  }
}
