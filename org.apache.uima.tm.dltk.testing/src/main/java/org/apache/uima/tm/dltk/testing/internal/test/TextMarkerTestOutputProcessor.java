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

package org.apache.uima.tm.dltk.testing.internal.test;

import org.eclipse.debug.core.ILaunch;
import org.eclipse.dltk.testing.DLTKTestingPlugin;
import org.eclipse.dltk.testing.ITestingClient;
import org.eclipse.dltk.testing.ITestingProcessor;
import org.eclipse.dltk.testing.model.ITestRunSession;

class TextMarkerTestOutputProcessor implements ITestingProcessor {
  private ILaunch launch;

  long start = 0;

  public TextMarkerTestOutputProcessor(ILaunch launch) {
    this.launch = launch;
    // System.out.println("TextMarkerTestOutputProcessor created");
  }

  int index = 0;

  private ITestRunSession session;

  private ITestingClient client;

  private boolean skip = false;

  private String message;

  private int state = STATE_NORMAL;

  private static final int STATE_NORMAL = 0;

  private static final int STATE_RESULT_WAS = 1;

  private static final int STATE_RESULT_ACTUAL = 2;

  private String resultActual = "";

  private String resultExpected = "";

  public void done() {
    // System.out.println("DONE");
    if (session == null || client == null) {
      // System.out.println("Session is NULL");
      return;
    }
    session.setTotalCount(index);
    client.testTerminated((int) (System.currentTimeMillis() - start));
  }

  public void processLine(String line) {
    // System.out.println("#" + line);
    if (session == null || client == null) {
      return;
    }
    // System.out.println("@");
    if (line.length() == 0) {
      return;
    }
    if (line.startsWith("====")) {
      if (line.endsWith("FAILED")) {
        if (!skip) {
          message = line.substring(line.indexOf(" ", line.indexOf(" ") + 1), line.lastIndexOf(" "));

        }
        if (skip) {
          int lastIndexOf = line.indexOf(" ", line.indexOf(" ") + 1);
          String name = line.substring(5, lastIndexOf);

          int id = ++index;

          client.testTree(id, name, false, 0);
          client.testStarted(id, name);
          session.setTotalCount(id);
          client.testError(id, name);

          client.testActual(resultActual);
          client.testExpected(resultExpected);
          client.traceStart();
          client.traceMessage(message);
          client.traceEnd();

          resetState();
        }
        skip = !skip;
      }
    } else if (line.equals("---- Result was:")) {
      state = STATE_RESULT_WAS;
      return;
    } else if (line.equals("---- Result should have been (exact matching):")) {
      state = STATE_RESULT_ACTUAL;
      return;
    }
    switch (state) {
      case STATE_RESULT_ACTUAL:
        String d = resultExpected.length() > 0 ? "\n" : "";
        resultExpected += d + line;
        break;
      case STATE_RESULT_WAS:
        d = resultActual.length() > 0 ? "\n" : "";
        resultActual += d + line;
        break;
    }

    if (!skip) {
      if (line.startsWith("++++")) {

        int lastIndexOf = line.lastIndexOf(" ");
        String name = line.substring(5, lastIndexOf);
        String state = line.substring(lastIndexOf + 1);
        if ("PASSED".equals(state)) {
          int id = ++index;
          client.testTree(id, name, false, 0);
          client.testStarted(id, name);
          session.setTotalCount(id);
          client.testEnded(id, name);
          resetState();
        } else {
          // We need to test for SKIPPED:
          String sk = "SKIPPED:";
          if (line.indexOf(sk) != -1) {
            lastIndexOf = line.lastIndexOf(sk);
            name = line.substring(5, lastIndexOf);
            state = line.substring(lastIndexOf + sk.length());

            int id = ++index;
            client.testTree(id, name, false, 0);
            client.testStarted(id, name);
            session.setTotalCount(id);
            client.testFailed(id, name);

            client.traceStart();
            client.traceMessage(state);
            client.traceEnd();
            resetState();
          }
        }
      }
    }
  }

  private void resetState() {
    state = STATE_NORMAL;
    resultActual = "";
    resultExpected = "";
  }

  public void start() {
    // System.out.println("!!!!!!START!!!!!!");
    this.start = System.currentTimeMillis();
    index = 0;
    session = DLTKTestingPlugin.getTestRunSession(launch);
    if (session == null)
      return;

    client = session.getTestRunnerClient();
    if (client != null) {
      client.testRunStart(0);
    }
  }
}
