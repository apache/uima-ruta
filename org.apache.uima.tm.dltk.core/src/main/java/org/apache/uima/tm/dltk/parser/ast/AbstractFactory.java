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

package org.apache.uima.tm.dltk.parser.ast;

import java.util.List;

import org.antlr.runtime.Token;
import org.apache.uima.tm.dltk.internal.core.parser.TextMarkerParseUtils;
import org.eclipse.dltk.ast.ASTNode;

public abstract class AbstractFactory {

  /**
   * @param token
   * @return (start, end)
   * @throws IllegalArgumentException
   *           when token==null or !(token instanceof CommonToken)
   */
  protected static final int[] getBounds(Token token) throws IllegalArgumentException {
    return TextMarkerParseUtils.getBounds(token);
  }

  /**
   * @param tokenA
   *          startToken
   * @param tokenB
   *          endToken
   * @return positions of a.start // b.end
   * @throws IllegalArgumentException
   *           when some token is null or not instanceof CommonToken
   */
  protected static final int[] getBounds(Token tokenA, Token tokenB)
          throws IllegalArgumentException {
    return TextMarkerParseUtils.getBounds(tokenA, tokenB);
  }

  /**
   * 
   * @param a
   * @param b
   * @return indexarray a.start // b.end
   */
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

  /**
   * @param head
   *          first element. determines bounds[0].
   * @param astnodeListArray
   *          in ascending elements order
   * @return
   */
  protected static final int[] getSurroundingBounds(ASTNode head, List... astnodeListArray) {
    int bounds[] = { 0, 0 };
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
        ASTNode node = list.get(list.size() - 1);
        if (node != null) {
          bounds[1] = Math.max(bounds[0], node.sourceEnd());
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

  /**
   * Applies start/end positions of <code>Token token</code> to <code>Node node</code>
   * 
   * @param node
   * @param token
   */
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
