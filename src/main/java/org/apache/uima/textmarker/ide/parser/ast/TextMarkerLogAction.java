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

package org.apache.uima.textmarker.ide.parser.ast;

import java.util.List;

public class TextMarkerLogAction extends TextMarkerAction {
  int[] logLevelBounds;

  boolean logLevelAssigned;

  public TextMarkerLogAction(int exprStart, int exprEnd, String name, int nameStart, int nameEnd,
          List exprs, int levelStart, int levelEnd) {
    super(exprStart, exprEnd, exprs, TMActionConstants.A_LOG, name, nameStart, nameEnd);
    logLevelBounds = new int[2];
    logLevelBounds[0] = levelStart;
    logLevelBounds[1] = levelEnd;
    if (levelStart > 0 && levelEnd > 0) {
      logLevelAssigned = true;
    }
  }

  /**
   * @return may throw nullpointer if no level assigned
   */
  public int getLogLevelStart() {
    return logLevelBounds[0];
  }

  /**
   * @return may throw nullpointer if no level assigned
   */
  public int getLogLevelEnd() {
    return logLevelBounds[1];
  }

  public boolean isLogLevelAssigned() {
    return logLevelAssigned;
  }
}
