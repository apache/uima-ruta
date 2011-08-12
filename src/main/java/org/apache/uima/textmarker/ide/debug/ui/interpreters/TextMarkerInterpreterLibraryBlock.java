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

package org.apache.uima.textmarker.ide.debug.ui.interpreters;

import org.apache.uima.textmarker.ide.TextMarkerIdePlugin;
import org.eclipse.dltk.internal.debug.ui.interpreters.AbstractInterpreterLibraryBlock;
import org.eclipse.dltk.internal.debug.ui.interpreters.AddScriptInterpreterDialog;
import org.eclipse.dltk.internal.debug.ui.interpreters.LibraryLabelProvider;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IBaseLabelProvider;

/**
 * Control used to edit the libraries associated with a Interpreter install
 */
public class TextMarkerInterpreterLibraryBlock extends AbstractInterpreterLibraryBlock {

  public TextMarkerInterpreterLibraryBlock(AddScriptInterpreterDialog d) {
    super(d);
  }

  @Override
  protected IBaseLabelProvider getLabelProvider() {
    return new LibraryLabelProvider();
  }

  @Override
  protected IDialogSettings getDialogSettions() {
    return TextMarkerIdePlugin.getDefault().getDialogSettings();
  }
}
