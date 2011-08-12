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

package org.apache.uima.textmarker.ide.core;

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
