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

import java.util.Map;
import java.util.Set;

import org.apache.uima.tm.textruler.core.TextRulerToolkit;
import org.apache.uima.tm.textruler.extension.TextRulerLearner.TextRulerLearnerState;


/**
 * This class encapsulates the functionality for administrating an algorithm that was added via the
 * eclipse extension point. It e.g. holds the three extension point parameters name, factory class
 * and the id, cares about running the algorithm in an own thread, act as the algorithm's delegate,
 * hold the algorithms results and status string and pass it to a possible UI
 * (TextRulerControllerDelegate), ...
 * 
 */
public class TextRulerLearnerController implements TextRulerLearnerDelegate {

  // these three are extracted from the extension points:
  protected String name; // name of the algorithm

  protected TextRulerLearnerFactory algoritmFactory; // the factory that can create an instance of

  // it

  protected String id; // the id of this algorithm

  // other member variables:
  protected boolean enabled; // flag if this algorithm is enabled for learning

  protected TextRulerLearner algorithm; // the algorithm, null if it is not running currently

  protected Thread thread; // the thread in which we run the algorithm, null if not running

  protected TextRulerControllerDelegate delegate; // the delegate for status notifications, etc.

  protected String currentStatusString; // the current status String that the algorithm reported to

  // us

  protected TextRulerLearnerState algorithmState = TextRulerLearnerState.ML_UNDEFINED; // the

  // current
  // state

  protected String resultString; // the current rules result string

  public TextRulerLearnerController(String id, String name, TextRulerLearnerFactory factory) {
    super();
    this.id = id;
    this.name = name;
    this.algoritmFactory = factory;
  }

  public String getID() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() // give back a status string that the UI can directly print or
  // whatever...
  {
    if (currentStatusString != null)
      return getName() + " - " + currentStatusString;
    else
      return getName();
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean value) {
    enabled = value;
  }

  public boolean isRunning() {
    return thread != null;
  }

  private void sendUpdateToDelegate(boolean ruleBaseChanged) {
    if (delegate != null)
      delegate.algorithmStatusUpdate(this, currentStatusString, algorithmState, ruleBaseChanged);
  }

  // TextRulerLearnerDelegate Methods
  public boolean shouldAbort() {
    return TextRulerController.shouldAbort();
  }

  public void algorithmStatusUpdate(TextRulerLearner alg, String statusString,
          TextRulerLearnerState state, boolean ruleBaseChanged) {
    resultString = alg.getResultString();
    algorithmState = state;
    currentStatusString = statusString;
    sendUpdateToDelegate(ruleBaseChanged);
  }

  public String getCurrentResultString() {
    if (algorithm != null)
      resultString = algorithm.getResultString();
    return resultString;
  }

  private void algorithmDidEnd() {
    thread = null;
    if (delegate != null)
      delegate.algorithmDidEnd(this);
    delegate = null;
    algorithm = null;
    TextRulerController.anAlgorithmDidEnd(this);
  }

  public boolean runInNewThread(final String inputFolderPath, final String additionalFolderPath,
          final String preprocessorTMfile, final String[] slotNames, final Set<String> filters,
          final String tempBaseDirectory, TextRulerControllerDelegate delegate,
          final Map<String, Object> algorithmParams) {
    if (thread == null) {
      final TextRulerLearnerDelegate algDelegate = this;
      this.delegate = delegate;
      thread = new Thread(new Runnable() {
        public void run() {
          String tmpDir = TextRulerToolkit.addTrailingSlashToPath(tempBaseDirectory + getID());
          algorithm = algoritmFactory.createAlgorithm(inputFolderPath, additionalFolderPath,
                  preprocessorTMfile, tmpDir, slotNames, filters, algDelegate);
          algorithm.setParameters(algorithmParams);
          algorithm.run();
          algorithmDidEnd();
          System.gc();
        }
      }

      );
      thread.setPriority(Thread.NORM_PRIORITY);
      thread.start();
      return true;
    } else {
      return false;
    }
  }

  public void resetStatusString() {
    currentStatusString = null;
  }

  public TextRulerLearnerFactory getFactory() {
    return algoritmFactory;
  }

}
