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

package org.apache.uima.tm.dltk.formatter.preferences;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.apache.uima.tm.dltk.formatter.preferences.messages"; //$NON-NLS-1$

  // public static String TextMarkerFormatterBlankLinesPage_afterGlobalStatements;
  // public static String TextMarkerFormatterBlankLinesPage_befireFirstDeclaration;
  // public static String TextMarkerFormatterBlankLinesPage_beforeBlockDeclarations;
  // public static String TextMarkerFormatterBlankLinesPage_betweenBlocks;
  public static String TextMarkerFormatterBlankLinesPage_blankLinesBeforeDeclarations;

  public static String TextMarkerFormatterBlankLinesPage_beforeDeclarations;

  // public static String TextMarkerFormatterBlankLinesPage_existingBlankLines;
  // public static String TextMarkerFormatterBlankLinesPage_numberOfEmptyLinesToPreserve;
  // public static String TextMarkerFormatterCommentsPage_commentFormatting;
  // public static String TextMarkerFormatterCommentsPage_enableCommentWrapping;
  // public static String TextMarkerFormatterCommentsPage_maximumLineWidthForComments;
  public static String TextMarkerFormatterLineWrappingPage_maximumLineLenght;

  public static String TextMarkerFormatterLineWrappingPage_generalWrapping;

  public static String TextMarkerFormatterIndentationTabPage_statementsWithinBlockBody;

  public static String TextMarkerFormatterIndentationTabPage_assignmentsWithinCreateAction;

  public static String TextMarkerFormatterIndentationTabPage_generalSettings;

  public static String TextMarkerFormatterIndentationTabPage_indentationCharacter;

  public static String TextMarkerFormatterIndentationTabPage_indentationSize;

  public static String TextMarkerFormatterIndentationTabPage_indentWithinBlocks;

  public static String TextMarkerFormatterIndentationTabPage_indentWithinCreateActions;

  public static String TextMarkerFormatterIndentationTabPage_tabSize;

  public static String TextMarkerFormatterModifyDialog_blankLines;

  // public static String TextMarkerFormatterModifyDialog_comments;
  public static String TextMarkerFormatterModifyDialog_indentation;

  public static String TextMarkerFormatterModifyDialog_lineWrapping;

  public static String TextMarkerFormatterModifyDialog_TextMarkerFormatter;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
  }
}
