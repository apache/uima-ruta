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

import org.eclipse.dltk.ast.Modifiers;
import org.eclipse.dltk.ast.references.SimpleReference;

public class TextMarkerImportStatement extends TextMarkerSimpleStatement {
  private int type;

  public TextMarkerImportStatement(int sourceStart, int sourceEnd, SimpleReference importRef,
          int type) {
    super(sourceStart, sourceEnd, importRef);
    this.type = type;
  }

  @Override
  public int getKind() {
    return Modifiers.AccNameSpace;
  }

  /**
   * See {@link TMStatementConstants}
   * 
   * @return
   */
  public int getType() {
    return this.type;
  }

}
