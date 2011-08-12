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

package org.apache.uima.textmarker.ide.core.search;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IParent;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.SearchPattern;
import org.eclipse.dltk.core.search.SearchRequestor;
import org.eclipse.dltk.core.search.matching.MatchLocator;
import org.eclipse.dltk.internal.core.BuiltinSourceModule;
import org.eclipse.dltk.internal.core.ExternalSourceModule;
import org.eclipse.dltk.internal.core.Openable;
import org.eclipse.dltk.internal.core.SourceModule;

public class TextMarkerMatchLocator extends MatchLocator {

  public TextMarkerMatchLocator(SearchPattern pattern, SearchRequestor requestor,
          IDLTKSearchScope scope, IProgressMonitor progressMonitor) {
    super(pattern, requestor, scope, progressMonitor);
  }

  @Override
  protected IModelElement createMethodHandle(ISourceModule module, String methodName) {
    IMethod methodHandle = null;
    // resolveDuplicates(methodHandle);
    return methodHandle;
  }

  @Override
  protected IModelElement createHandle(MethodDeclaration method, IModelElement parent) {
    if (parent instanceof IType) {
      IType type = (IType) parent;
      return createMethodHandle(type, new String(method.getName()));
    } else if (parent instanceof ISourceModule) {
      if (method.getDeclaringTypeName() != null) {
        return createMethodHandle((ISourceModule) parent, method.getDeclaringTypeName()
                + method.getName());
      } else {
        return createMethodHandle((ISourceModule) parent, method.getName());
      }
    }
    return null;
  }

  @Override
  protected IModelElement createTypeHandle(IType parent, String name) {
    return super.createTypeHandle(parent, name);
  }

  @Override
  protected IType createTypeHandle(String name) {
    Openable openable = this.currentPossibleMatch.openable;
    if (openable instanceof SourceModule || openable instanceof ExternalSourceModule
            || openable instanceof BuiltinSourceModule) {
      IParent e = ((IParent) openable);
      if (name.indexOf("::") != -1) {
        String[] split = name.split("::");
        for (int i = 0; i < split.length; i++) {
          if (e instanceof ISourceModule) {
            e = ((ISourceModule) e).getType(split[i]);
          } else if (e instanceof IType) {
            e = ((IType) e).getType(split[i]);
          } else {
            e = null;
          }
          if (e == null) {
            return null;
          }
        }
        if (e != null && e instanceof IType) {
          return (IType) e;
        }
      }
    }
    return super.createTypeHandle(name);
  }
}
