package org.apache.uima.tm.dltk.internal.testing;

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