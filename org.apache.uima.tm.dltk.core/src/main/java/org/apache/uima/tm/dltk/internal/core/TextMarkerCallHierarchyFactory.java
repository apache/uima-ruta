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
