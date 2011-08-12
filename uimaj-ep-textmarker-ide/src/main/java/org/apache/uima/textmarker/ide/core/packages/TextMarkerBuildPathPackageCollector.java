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

package org.apache.uima.textmarker.ide.core.packages;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.uima.textmarker.ide.TextMarkerIdePlugin;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerPackageDeclaration;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.statements.Statement;

public class TextMarkerBuildPathPackageCollector extends ASTVisitor {

  private final List requireDirectives = new ArrayList();

  private final Set packagesRequired = new HashSet();

  private final Set packagesProvided = new HashSet();

  public void process(ModuleDeclaration declaration) {
    try {
      declaration.traverse(this);
    } catch (Exception e) {
      TextMarkerIdePlugin.error(e);
    }
  }

  @Override
  public boolean visit(Statement s) throws Exception {
    if (s instanceof TextMarkerPackageDeclaration) {
      final TextMarkerPackageDeclaration pkg = (TextMarkerPackageDeclaration) s;
      packagesProvided.add(pkg.getName());
      return false;
    }
    return super.visit(s);
  }

  /**
   * @return the requireDirectives
   */
  public List getRequireDirectives() {
    return requireDirectives;
  }

  /**
   * @return the packagesRequired
   */
  public Set getPackagesRequired() {
    return packagesRequired;
  }

  /**
   * @return the packagesProvided
   */
  public Set getPackagesProvided() {
    return packagesProvided;
  }

}
