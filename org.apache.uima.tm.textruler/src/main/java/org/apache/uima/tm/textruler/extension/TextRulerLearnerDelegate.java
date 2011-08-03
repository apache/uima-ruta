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

package org.apache.uima.tm.textruler.extension;

import org.apache.uima.tm.textruler.extension.TextRulerLearner.TextRulerLearnerState;

/**
 * 
 * An TextRulerLearnerDelegate is used for an TextRulerLearner so that it can e.g. send status
 * updates to some one that might be interested (in our case the controller and though the UI). In
 * addition to that, the algorithm can ask the delegate if he should abort his work (e.g. because
 * the user clicked the abort button).
 * 
 */
public interface TextRulerLearnerDelegate {

  public void algorithmStatusUpdate(TextRulerLearner alg, String statusString,
          TextRulerLearnerState state, boolean ruleBaseChanged);

  public boolean shouldAbort();

}
