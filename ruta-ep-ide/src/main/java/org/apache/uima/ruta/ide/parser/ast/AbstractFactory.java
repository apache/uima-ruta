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

package org.apache.uima.ruta.ide.parser.ast;

import java.util.List;

import org.antlr.runtime.Token;
import org.apache.uima.ruta.ide.core.parser.RutaParseUtils;
import org.eclipse.dltk.ast.ASTNode;

public abstract class AbstractFactory {

  protected static final int[] getBounds(Token token) throws IllegalArgumentException {
    return RutaParseUtils.getBounds(token);
  }

  protected static final int[] getBounds(Token tokenA, Token tokenB)
          throws IllegalArgumentException {
    return RutaParseUtils.getBounds(tokenA, tokenB);
  }

  protected static final int[] getBounds(Token a, ASTNode b) {
    int[] bounds = { 0, 0 };
    if (a == null && b == null) {
      return bounds;
    }
    if (b != null) {
      bounds[0] = b.sourceStart();
      bounds[1] = b.sourceEnd();
    }
    if (a != null) {
      bounds[0] = Math.min(bounds[0], getBounds(a)[0]);
      bounds[1] = Math.max(bounds[1], getBounds(a)[1]);
    }
    return bounds;
  }

  protected static final int[] getBounds(ASTNode a, ASTNode b) {
    int[] bounds = { 0, 0 };
    if (a == null && b == null) {
      return bounds;
    }
    if (a != null) {
      bounds[0] = a.sourceStart();
      bounds[1] = a.sourceEnd();
    }
    if (b != null) {
      bounds[0] = Math.min(bounds[0], b.sourceStart());
      bounds[1] = Math.max(bounds[1], b.sourceEnd());
    }
    return bounds;
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  protected static final int[] getSurroundingBounds(ASTNode head, List... astnodeListArray) {
    int bounds[] = { Integer.MAX_VALUE, -1 };
    if (head != null) {
      bounds[0] = head.sourceStart();
      bounds[1] = head.sourceEnd();
    }
    if (astnodeListArray != null) {
      for (int i = 0; i < astnodeListArray.length; i++) {
        List<ASTNode> list = astnodeListArray[i];
        if (list == null || list.isEmpty()) {
          continue;
        }
        ASTNode firstNode = list.get(0);
        if (firstNode != null) {
          bounds[0] = Math.min(bounds[0], firstNode.sourceStart());
        }

        ASTNode lastNode = list.get(list.size() - 1);
        if (lastNode != null) {
          bounds[1] = Math.max(bounds[0], lastNode.sourceEnd());
        }
      }
    }
    return bounds;
  }

  protected static final void setMaxEnd(int[] bounds, Token end) {
    if (end != null && bounds != null && bounds.length >= 1) {
      bounds[1] = Math.max(bounds[1], getBounds(end)[1]);
    }
  }

  protected static final void setMinBegin(int[] bounds, Token begin) {
    if (begin != null && bounds != null && bounds.length >= 1) {
      bounds[0] = Math.min(bounds[0], getBounds(begin)[1]);
    }
  }

  protected static final void setBounds(ASTNode node, Token token) {
    if (node == null || token == null) {
      return;
    }
    int[] bounds = getBounds(token);
    if (bounds == null) {
      return;
    }
    node.setStart(bounds[0]);
    node.setEnd(bounds[1]);
  }

}
