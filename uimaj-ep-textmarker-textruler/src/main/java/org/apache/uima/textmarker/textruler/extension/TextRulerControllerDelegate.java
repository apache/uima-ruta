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

package org.apache.uima.textmarker.textruler.extension;

import org.apache.uima.textmarker.textruler.extension.TextRulerLearner.TextRulerLearnerState;

/**
 * TextRulerControllerDelegate provides some useful notificaion methods e.g. a UI class can
 * implement to get some important information.
 */
public interface TextRulerControllerDelegate {

  public void algorithmDidEnd(TextRulerLearnerController algController);

  public void algorithmStatusUpdate(TextRulerLearnerController algController, String statusString,
          TextRulerLearnerState state, boolean ruleBaseChanged);

  public void preprocessorStatusUpdate(TextRulerPreprocessor p, String statusString);

  public void globalStatusUpdate(String str);

}
