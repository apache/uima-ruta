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

import org.eclipse.debug.core.ILaunch;
import org.eclipse.dltk.testing.DLTKTestingPlugin;
import org.eclipse.dltk.testing.ITestingClient;
import org.eclipse.dltk.testing.ITestingProcessor;
import org.eclipse.dltk.testing.model.ITestRunSession;

class TextMarkerOutputProcessor implements ITestingProcessor {
  private ILaunch launch;

  public TextMarkerOutputProcessor(ILaunch launch) {
    this.launch = launch;
  }

  public void done() {
    client.testTerminated(0);
  }

  static int i = 0;

  private ITestRunSession session;

  private ITestingClient client;

  public void processLine(String line) {

    // System.out.println("#" + line);
    if (line.length() == 0) {
      return;
    }
    final String name = line;

    int id = ++i;
    client.testTree(id, name, false, 0);
    client.testStarted(id, name);
    // client.receiveMessage(MessageIds.TRACE_START);
    // client.receiveMessage("This is Trace");
    // client.receiveMessage(MessageIds.TRACE_END);
    session.setTotalCount(id);
    if (i % 3 == 0) {
      client.testFailed(id, name);
      client.traceMessage("This is trace");
    } else if (i % 4 == 0) {
      client.testError(id, name);
      client.traceMessage("This is trace");
    } else {
      client.testEnded(id, name);
    }
    // client.receiveMessage(MessageIds.TEST_END + name + ","
    // + name);

  }

  public void start() {
    i = 0;
    session = DLTKTestingPlugin.getTestRunSession(launch);
    if (session == null)
      return;

    client = session.getTestRunnerClient();

    client.testRunStart(100);

  }
}
