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

package org.apache.uima.textmarker.ide.formatter.preferences;

import java.net.URL;

import org.apache.uima.textmarker.ide.formatter.TextMarkerFormatterConstants;
import org.eclipse.dltk.ui.formatter.FormatterModifyTabPage;
import org.eclipse.dltk.ui.formatter.IFormatterControlManager;
import org.eclipse.dltk.ui.formatter.IFormatterModifyDialog;
import org.eclipse.dltk.ui.util.SWTFactory;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;


public class TextMarkerFormatterLineWrappingPage extends FormatterModifyTabPage {

  /**
   * @param dialog
   */
  public TextMarkerFormatterLineWrappingPage(IFormatterModifyDialog dialog) {
    super(dialog);
  }

  @Override
  protected void createOptions(IFormatterControlManager manager, Composite parent) {
    Group emptyLinesGroup = SWTFactory.createGroup(parent,
            Messages.TextMarkerFormatterLineWrappingPage_generalWrapping, 2, 1,
            GridData.FILL_HORIZONTAL);
    manager.createNumber(emptyLinesGroup, TextMarkerFormatterConstants.MAX_LINE_LENGTH,
            Messages.TextMarkerFormatterLineWrappingPage_maximumLineLenght);
    // manager.createNumber(emptyLinesGroup,
    // TextMarkerFormatterConstants.LINES_FILE_BETWEEN_BLOCK,
    // Messages.TextMarkerFormatterBlankLinesPage_betweenBlocks);
    // manager.createNumber(emptyLinesGroup,
    // TextMarkerFormatterConstants.LINES_FILE_BETWEEN_CLASS,
    // Messages.TextMarkerFormatterBlankLinesPage_betweenClasses);
    // manager.createNumber(emptyLinesGroup,
    // TextMarkerFormatterConstants.LINES_FILE_BETWEEN_METHOD,
    // Messages.TextMarkerFormatterBlankLinesPage_betweenMethods);
    // //
    // Group emptyLinesInternalGroup = SWTFactory.createGroup(parent,
    // Messages.TextMarkerFormatterBlankLinesPage_blankLinesWithingClassModuleDeclarations, 2, 1,
    // GridData.FILL_HORIZONTAL);
    // manager.createNumber(emptyLinesInternalGroup,
    // TextMarkerFormatterConstants.LINES_BEFORE_FIRST,
    // Messages.TextMarkerFormatterBlankLinesPage_befireFirstDeclaration);
    // manager.createNumber(emptyLinesInternalGroup,
    // TextMarkerFormatterConstants.LINES_BEFORE_MODULE,
    // Messages.TextMarkerFormatterBlankLinesPage_beforeNestedModuleDeclarations);
    // manager.createNumber(emptyLinesInternalGroup,
    // TextMarkerFormatterConstants.LINES_BEFORE_CLASS,
    // Messages.TextMarkerFormatterBlankLinesPage_beforeNestedClassDeclarations);
    // manager.createNumber(emptyLinesInternalGroup,
    // TextMarkerFormatterConstants.LINES_BEFORE_METHOD,
    // Messages.TextMarkerFormatterBlankLinesPage_beforeMethodDeclarations);
    // //
    // Group preserveGroup = SWTFactory.createGroup(parent,
    // Messages.TextMarkerFormatterBlankLinesPage_existingBlankLines, 2, 1,
    // GridData.FILL_HORIZONTAL);
    // manager.createNumber(preserveGroup,
    // TextMarkerFormatterConstants.LINES_PRESERVE,
    // Messages.TextMarkerFormatterBlankLinesPage_numberOfEmptyLinesToPreserve);
  }

  @Override
  protected URL getPreviewContent() {
    return getClass().getResource("wrapping-preview.tm"); //$NON-NLS-1$
  }

}
