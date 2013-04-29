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

package org.apache.uima.ruta.ide.formatter.preferences;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.apache.uima.ruta.ide.formatter.preferences.messages"; //$NON-NLS-1$

  // public static String RutaFormatterBlankLinesPage_afterGlobalStatements;
  // public static String RutaFormatterBlankLinesPage_befireFirstDeclaration;
  // public static String RutaFormatterBlankLinesPage_beforeBlockDeclarations;
  // public static String RutaFormatterBlankLinesPage_betweenBlocks;
  public static String RutaFormatterBlankLinesPage_blankLinesBeforeDeclarations;

  public static String RutaFormatterBlankLinesPage_beforeDeclarations;

  // public static String RutaFormatterBlankLinesPage_existingBlankLines;
  // public static String RutaFormatterBlankLinesPage_numberOfEmptyLinesToPreserve;
  // public static String RutaFormatterCommentsPage_commentFormatting;
  // public static String RutaFormatterCommentsPage_enableCommentWrapping;
  // public static String RutaFormatterCommentsPage_maximumLineWidthForComments;
  public static String RutaFormatterLineWrappingPage_maximumLineLenght;

  public static String RutaFormatterLineWrappingPage_generalWrapping;

  public static String RutaFormatterIndentationTabPage_statementsWithinBlockBody;

  public static String RutaFormatterIndentationTabPage_assignmentsWithinCreateAction;

  public static String RutaFormatterIndentationTabPage_generalSettings;

  public static String RutaFormatterIndentationTabPage_indentationCharacter;

  public static String RutaFormatterIndentationTabPage_indentationSize;

  public static String RutaFormatterIndentationTabPage_indentWithinBlocks;

  public static String RutaFormatterIndentationTabPage_indentWithinCreateActions;

  public static String RutaFormatterIndentationTabPage_tabSize;

  public static String RutaFormatterModifyDialog_blankLines;

  // public static String RutaFormatterModifyDialog_comments;
  public static String RutaFormatterModifyDialog_indentation;

  public static String RutaFormatterModifyDialog_lineWrapping;

  public static String RutaFormatterModifyDialog_RutaFormatter;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
  }
}
