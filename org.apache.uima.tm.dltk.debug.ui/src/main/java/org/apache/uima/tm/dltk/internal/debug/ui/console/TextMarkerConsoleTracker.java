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

package org.apache.uima.tm.dltk.internal.debug.ui.console;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.ui.console.IHyperlink;
import org.eclipse.ui.console.IPatternMatchListenerDelegate;
import org.eclipse.ui.console.PatternMatchEvent;
import org.eclipse.ui.console.TextConsole;

public class TextMarkerConsoleTracker implements IPatternMatchListenerDelegate {
  private TextConsole console;

  public void connect(TextConsole console) {
    this.console = console;
  }

  public void disconnect() {
    console = null;
  }

  protected TextConsole getConsole() {
    return console;
  }

  public void matchFound(PatternMatchEvent event) {
    try {
      int offset = event.getOffset();
      int length = event.getLength();
      IHyperlink link = new TextMarkerFileHyperlink(console);
      console.addHyperlink(link, offset + 1, length - 2);
    } catch (BadLocationException e) {
    }
  }
}
