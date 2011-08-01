package org.apache.uima.tm.dltk.internal.core;

import org.apache.uima.tm.dltk.core.TextMarkerMatchLocatorParser;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.dltk.core.search.AbstractSearchFactory;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.IMatchLocatorParser;
import org.eclipse.dltk.core.search.SearchPattern;
import org.eclipse.dltk.core.search.SearchRequestor;
import org.eclipse.dltk.core.search.indexing.SourceIndexerRequestor;
import org.eclipse.dltk.core.search.matching.MatchLocator;


/**
 * TextMarker search factory
 */
public class TextMarkerSearchFactory extends AbstractSearchFactory {

  /*
   * @see
   * org.eclipse.dltk.core.ISearchFactory#createMatchParser(org.eclipse.dltk.core.search.matching
   * .MatchLocator)
   */
  public IMatchLocatorParser createMatchParser(MatchLocator locator) {
    return new TextMarkerMatchLocatorParser(locator);
  }

  /*
   * @see
   * org.eclipse.dltk.core.ISearchFactory#createMatchLocator(org.eclipse.dltk.core.search.SearchPattern
   * , org.eclipse.dltk.core.search.SearchRequestor, org.eclipse.dltk.core.search.IDLTKSearchScope,
   * org.eclipse.core.runtime.SubProgressMonitor)
   */
  @Override
  public MatchLocator createMatchLocator(SearchPattern pattern, SearchRequestor requestor,
          IDLTKSearchScope scope, SubProgressMonitor monitor) {
    return new MatchLocator(pattern, requestor, scope, monitor); // TextMarkerMatchLocator(pattern,
    // requestor, scope, monitor);
  }

  /*
   * @see org.eclipse.dltk.core.search.AbstractSearchFactory#createSourceRequestor()
   */
  @Override
  public SourceIndexerRequestor createSourceRequestor() {
    return new TextMarkerSourceIndexerRequestor();
  }
}
