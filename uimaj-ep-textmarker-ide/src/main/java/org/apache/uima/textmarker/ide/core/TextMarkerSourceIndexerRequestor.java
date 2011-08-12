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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.ast.Modifiers;
import org.eclipse.dltk.core.search.indexing.SourceIndexerRequestor;

public class TextMarkerSourceIndexerRequestor extends SourceIndexerRequestor {
  protected String[] realEnclosingTypeNames = new String[5];

  protected int realdepth = 0;

  @Override
  public void acceptMethodReference(char[] methodName, int argCount, int sourcePosition,
          int sourceEndPosition) {
    // System.out.println("TextMarkerSourceIndexerRequestor:Add Method Reference: "
    // + new String(methodName));
    String mName = new String(methodName);
    String[] ns = mName.split("::");
    if (ns.length > 0) {
      this.indexer.addMethodReference(ns[ns.length - 1].toCharArray(), argCount);
    }
    for (int i = 0; i < ns.length - 1; ++i) {
      if (ns[i].length() > 0) {
        this.indexer.addTypeReference(ns[i].toCharArray());
      }
    }
  }

  @Override
  public boolean enterTypeAppend(String fullName, String delimiter) {
    if (fullName.startsWith("::")) {
      String name = fullName.substring(2);
      String[] split = name.split("::");

      List cEnclodingNames = new ArrayList();
      for (int i = 0; i < split.length; i++) {
        this.indexer.addTypeDeclaration(Modifiers.AccNameSpace, this.pkgName, split[i],
                eclosingTypeNamesFrom(cEnclodingNames, split, i), null);
      }
      this.pushTypeName(name.toCharArray());
    } else {

      List cEnclodingNames = enclosingTypeNamesAsList();
      String[] split = fullName.split("::");
      for (int i = 0; i < split.length; i++) {
        this.indexer.addTypeDeclaration(Modifiers.AccNameSpace, this.pkgName, split[i],
                eclosingTypeNamesFrom(cEnclodingNames, split, i), null);
      }
      this.pushTypeName(fullName.toCharArray());
    }
    return true;
  }

  private char[][] eclosingTypeNamesFrom(List enclosingNames, String[] split, int i) {
    char[][] result = new char[enclosingNames.size() + i][];
    int index = 0;
    for (Iterator iterator = enclosingNames.iterator(); iterator.hasNext();) {
      String name = (String) iterator.next();
      result[index++] = name.toCharArray();
    }
    for (int j = 0; j < i; j++) {
      result[index++] = split[j].toCharArray();
    }
    if (result.length > 0) {
      return result;
    }
    return null;
  }

  private List enclosingTypeNamesAsList() {
    List cEnclosingNames = new ArrayList();
    char[][] enclosingTypeNames2 = enclosingTypeNames();
    if (enclosingTypeNames2 == null) {
      return cEnclosingNames;
    }
    for (int i = 0; i < enclosingTypeNames2.length; i++) {
      cEnclosingNames.add(new String(enclosingTypeNames2[i]));
    }
    ;
    return cEnclosingNames;
  }

  @Override
  public boolean enterTypeAppend(TypeInfo info, String fullName, String delimiter) {
    return false;
  }

  @Override
  public void enterMethodRemoveSame(MethodInfo info) {
    enterMethod(info);
  }

  @Override
  public boolean enterMethodWithParentType(MethodInfo info, String parentName, String delimiter) {
    enterMethod(info);
    return false;
  }

  @Override
  public void popTypeName() {
    if (depth > 0) {
      String name = realEnclosingTypeNames[realdepth - 1];
      realEnclosingTypeNames[--realdepth] = null;
      String[] split = name.split("::");
      for (int i = 0; i < split.length; ++i) {
        super.popTypeName();
      }
    }
  }

  @Override
  public void pushTypeName(char[] typeName) {
    String type = new String(typeName);
    String[] split = type.split("::");
    for (int i = 0; i < split.length; i++) {
      super.pushTypeName(split[i].toCharArray());
    }
    if (realdepth == realEnclosingTypeNames.length)
      System.arraycopy(realEnclosingTypeNames, 0, realEnclosingTypeNames = new String[depth * 2],
              0, realdepth);
    realEnclosingTypeNames[realdepth++] = type;
  }
}
