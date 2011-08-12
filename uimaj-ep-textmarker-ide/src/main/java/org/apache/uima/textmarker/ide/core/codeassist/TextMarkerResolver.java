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

package org.apache.uima.textmarker.ide.core.codeassist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.textmarker.ide.core.parser.TextMarkerParseUtils;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.Modifiers;
import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IParent;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;


public class TextMarkerResolver {
  private IResolveElementParent resolver;

  private ModuleDeclaration moduleDeclaration;

  private ISourceModule sourceModule;

  public TextMarkerResolver(ISourceModule sourceModule, ModuleDeclaration moduleDeclaration,
          IResolveElementParent resolver) {
    this(sourceModule, moduleDeclaration);
    this.resolver = resolver;
  }

  public TextMarkerResolver(ISourceModule sourceModule, ModuleDeclaration moduleDeclaration) {
    this.sourceModule = sourceModule;
    this.moduleDeclaration = moduleDeclaration;
  }

  public IModelElement findModelElementFrom(ASTNode node) {
    List statements = moduleDeclaration.getStatements();
    List elements = new ArrayList();
    searchAddElementsTo(statements, node, sourceModule, elements);
    if (elements.size() == 1) {
      return (IModelElement) elements.get(0);
    }
    return null;
  }

  public interface IResolveElementParent {
    IModelElement findElementParent(ASTNode node, String name, IParent parent);
  }

  public void searchAddElementsTo(List statements, final ASTNode node, IParent element,
          List selectionElements) {
    if (statements == null || element == null) {
      return;
    }
    Iterator i = statements.iterator();
    while (i.hasNext()) {
      ASTNode nde = (ASTNode) i.next();
      if (nde.equals(node)) {
        if (node instanceof MethodDeclaration) {
          String oName = ((MethodDeclaration) node).getName();
          if (oName.indexOf("::") != -1) {
            String pName = oName.substring(0, oName.lastIndexOf("::"));
            pName = pName.replaceAll("::", "\\$");

            if (pName.startsWith("$")) {
              if (pName.equals("$")) {
                element = sourceModule;
              } else {
                try {
                  element = findTypeFrom(sourceModule.getChildren(), "", pName, '$');
                } catch (ModelException e) {
                  if (DLTKCore.DEBUG) {
                    e.printStackTrace();
                  }
                }
              }
            } else {
              pName = "$" + pName;
              try {
                element = findTypeFrom(element.getChildren(), "", pName, '$');
                if (element == null) {
                  return;
                }
              } catch (ModelException e) {
                e.printStackTrace();
                return;
              }
            }
          }
        }
        String nodeName = getNodeChildName(node);
        if (nodeName != null) {
          IModelElement e = null;
          if (nodeName.startsWith("::")) {
            nodeName = nodeName.substring(2);
            e = findChildrenByName(nodeName, sourceModule);
          } else {
            e = findChildrenByName(nodeName, element);
          }
          if (e == null && resolver != null) {
            e = resolver.findElementParent(node, nodeName, element);

          }
          if (e != null) {
            List toRemove = new ArrayList();
            for (int k = 0; k < selectionElements.size(); ++k) {
              IModelElement ke = (IModelElement) selectionElements.get(k);
              String keName = ke.getElementName();
              if (keName.equals(nodeName)) {
                toRemove.add(ke);
              }
            }
            for (int k = 0; k < toRemove.size(); ++k) {
              selectionElements.remove(toRemove.get(k));
            }
            selectionElements.add(e);
          }
        }
        return;
      }
      if (nde.sourceStart() <= node.sourceStart() && node.sourceEnd() <= nde.sourceEnd()) {
        if (element instanceof IParent) {
          if (nde instanceof TypeDeclaration) {
            TypeDeclaration type = (TypeDeclaration) nde;
            String typeName = getNodeChildName(type);
            IModelElement e = findChildrenByName(typeName, element);
            if (e == null && type.getName().startsWith("::")) {
              try {
                e = (IModelElement) findTypeFrom(sourceModule.getChildren(), "", type.getName()
                        .replaceAll("::", "\\$"), '$');
              } catch (ModelException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
              }
            }
            if (e instanceof IParent) {
              // was: if (e != null || e instanceof IParent)
              List stats = ((TypeDeclaration) nde).getStatements();
              searchAddElementsTo(stats, node, (IParent) e, selectionElements);
            }
          } else if (nde instanceof MethodDeclaration) {
            searchInMethod(node, element, nde, selectionElements);
          } /*
             * else if (nde instanceof TextMarkerStatement) { TextMarkerStatement s =
             * (TextMarkerStatement) nde; Expression commandId = s.getAt(0); final IParent e =
             * element; if (commandId != null && commandId instanceof SimpleReference) { String
             * qname = ((SimpleReference) commandId) .getName(); } }
             */
          else {
            final IParent e = element;
            List statements2 = findExtractBlocks(nde);
            if (statements2.size() > 0) {
              searchAddElementsTo(statements2, node, e, selectionElements);
            }
          }
        }
        return;
      }
    }
  }

  public static IModelElement findChildrenByName(String childName, IParent element) {
    try {
      if (element == null) {
        return null;
      }
      String nextName = null;
      int pos;
      if ((pos = childName.indexOf("::")) != -1) {
        nextName = childName.substring(pos + 2);
        childName = childName.split("::")[0];
      }
      IModelElement[] children = element.getChildren();
      if (children != null) {
        for (int i = 0; i < children.length; ++i) {
          String name = children[i].getElementName();
          if (children[i] instanceof IField && name.indexOf('(') != -1) {
            name = name.substring(0, name.indexOf('('));
          }
          if (name.equals(childName)) {
            if (nextName == null) {
              return children[i];
            } else if (children[i] instanceof IParent) {
              return findChildrenByName(nextName, (IParent) children[i]);
            }
          }
        }
      }
    } catch (ModelException e) {
      if (DLTKCore.DEBUG) {
        e.printStackTrace();
      }
    }
    return null;
  }

  public static IParent findTypeFrom(IModelElement[] childs, String name, String parentName,
          char delimiter) {
    try {
      for (int i = 0; i < childs.length; ++i) {
        if (childs[i] instanceof IType) {
          if ((((IType) childs[i]).getFlags() & Modifiers.AccNameSpace) == 0) {
            continue;
          }
          IType type = (IType) childs[i];
          String qname = name + delimiter + type.getElementName();
          if (qname.equals(parentName)) {
            return type;
          }
          IParent val = findTypeFrom(type.getChildren(), qname, parentName, delimiter);
          if (val != null) {
            return val;
          }
        }
      }
    } catch (ModelException e) {
      if (DLTKCore.DEBUG) {
        e.printStackTrace();
      }
    }
    return null;
  }

  public static String getNodeChildName(ASTNode node) {
    if (node instanceof MethodDeclaration) {
      MethodDeclaration method = (MethodDeclaration) node;
      String name = method.getName();
      if (name.indexOf("::") != -1) {
        return name.substring(name.lastIndexOf("::") + 2);
      }
      return name;
    } else if (node instanceof TypeDeclaration) {
      TypeDeclaration type = (TypeDeclaration) node;
      String name = type.getName();
      /*
       * if (name.startsWith("::")) { return name.substring(2); }
       */
      return name;
    } else if (node instanceof Statement) {
      String[] var = TextMarkerParseUtils.returnVariable((Statement) node);
      if (var != null) {
        return var[0];
      }
    } else if (node instanceof FieldDeclaration) {
      return ((FieldDeclaration) node).getName();
    }
    return null;
  }

  public void searchInMethod(final ASTNode node, IParent element, ASTNode nde,
          List selectionElements) {
    MethodDeclaration method = (MethodDeclaration) nde;
    String methodName = method.getName();
    if (methodName.indexOf("::") != -1) {
      String pName = methodName.substring(0, methodName.lastIndexOf("::"));
      pName = pName.replaceAll("::", "\\$");
      if (pName.equals("$")) {
        element = sourceModule;
      } else {
        try {
          element = TextMarkerResolver.findTypeFrom(sourceModule.getChildren(), "", pName, '$');
          if (element == null) {
            return;
          }
        } catch (ModelException e) {
          e.printStackTrace();
          return;
        }
      }
      methodName = TextMarkerResolver.getNodeChildName(nde);
    }
    IModelElement e = TextMarkerResolver.findChildrenByName(methodName, element);
    if (e != null && e instanceof IParent) {
      List stats = ((MethodDeclaration) nde).getStatements();
      searchAddElementsTo(stats, node, (IParent) e, selectionElements);
    }
  }

  public static List findExtractBlocks(ASTNode node) {
    final List statements2 = new ArrayList();
    ASTVisitor visitor = new ASTVisitor() {
      @Override
      public boolean visit(Expression s) throws Exception {
        if (s instanceof Block) {
          statements2.addAll(((Block) s).getStatements());
        }
        return super.visit(s);
      }
    };
    try {
      node.traverse(visitor);
    } catch (Exception e) {
      if (DLTKCore.DEBUG) {
        e.printStackTrace();
      }
    }
    return statements2;
  }
}
