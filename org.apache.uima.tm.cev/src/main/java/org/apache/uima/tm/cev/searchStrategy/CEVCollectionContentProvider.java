package org.apache.uima.tm.cev.searchStrategy;

import java.util.Collection;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * Implementierung eines StructuredContentProviders fuer den Auswahldialog fuer das Typsystem beim
 * Oeffnen eines XMI-Files
 * 
 * @author Marco Nehmeier
 * 
 * @param <T>
 *          Collection
 */
public class CEVCollectionContentProvider<T extends Collection<?>> implements
        IStructuredContentProvider {

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
   */
  public Object[] getElements(Object inputElement) {
    return ((T) inputElement).toArray();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.viewers.IContentProvider#dispose()
   */
  public void dispose() {
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
   * java.lang.Object, java.lang.Object)
   */
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
  }
}
