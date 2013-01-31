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

package org.apache.uima.textmarker.ide.testing;

import org.apache.uima.textmarker.ide.parser.ast.TextMarkerStatement;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.testing.AbstractTestingElementResolver;
import org.eclipse.dltk.testing.ITestingElementResolver;

public class TextMarkerTestMemberResolver extends AbstractTestingElementResolver implements
        ITestingElementResolver {
  @Override
  protected ASTNode findNode(final String testName, ModuleDeclaration decl, String method) {
    final ASTNode[] nde = new ASTNode[] { null };
    try {
      decl.traverse(new ASTVisitor() {

        @Override
        public boolean visitGeneral(ASTNode node) throws Exception {
          if (node instanceof TextMarkerStatement && ((TextMarkerStatement) node).getCount() > 2) {
            TextMarkerStatement st = (TextMarkerStatement) node;
            Expression cmd = st.getAt(0);
            if (cmd instanceof SimpleReference) {
              String cmdName = ((SimpleReference) cmd).getName();
              if (cmdName.startsWith("::")) {
                cmdName = cmdName.substring(2);
              }
              if ("test".equals(cmdName) || "tmtest::test".equals(cmdName)) {

                // List findLevelsTo = findLevelsTo(decl, node);
                Expression name = st.getAt(1);
                if (name instanceof SimpleReference) {
                  String nameValue = ((SimpleReference) name).getName();
                  if (testName.equals(nameValue)) {
                    nde[0] = st;
                  }
                }
              }
            }
          }
          return true;
        }
      });
    } catch (CoreException e) {
      if (DLTKCore.DEBUG) {
        e.printStackTrace();
      }
    } catch (Exception e) {
      if (DLTKCore.DEBUG) {
        e.printStackTrace();
      }
    }
    return nde[0];
  }
}
