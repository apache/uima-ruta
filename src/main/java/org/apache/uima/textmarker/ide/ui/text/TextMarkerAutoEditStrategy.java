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

package org.apache.uima.textmarker.ide.ui.text;

import java.util.regex.Pattern;

import org.apache.uima.textmarker.ide.ui.TextMarkerPartitions;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.ui.CodeFormatterConstants;
import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DefaultIndentLineAutoEditStrategy;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.DocumentCommand;
import org.eclipse.jface.text.DocumentRewriteSession;
import org.eclipse.jface.text.DocumentRewriteSessionType;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.jface.text.rules.FastPartitioner;


public class TextMarkerAutoEditStrategy extends DefaultIndentLineAutoEditStrategy {
  // TODO
  IPreferenceStore fStore;

  String fPartitioning;

  final int maxCharsAway = 100;

  public TextMarkerAutoEditStrategy(IPreferenceStore store, String part) {
    fStore = store;
    fPartitioning = part;
  }

  /*
   * possible prefereces:
   * 
   * general indent on/off smart tab key behaviour autoclose "strings", 'strings' autoclose
   * {brackets}, [], () smart paste
   */

  private boolean isSmartTab() {
    return fStore.getBoolean(PreferenceConstants.EDITOR_SMART_TAB);
  }

  private boolean isSmartMode() {
    return fStore.getBoolean(PreferenceConstants.EDITOR_SMART_INDENT);
  }

  private boolean closeBrackets() {
    return fStore.getBoolean(PreferenceConstants.EDITOR_CLOSE_BRACKETS);
  }

  private boolean isSmartPaste() {
    return fStore.getBoolean(PreferenceConstants.EDITOR_SMART_PASTE);
  }

  private boolean closeStrings() {
    return fStore.getBoolean(PreferenceConstants.EDITOR_CLOSE_STRINGS);
  }

  private int getIndentSize() {
    return fStore.getInt(CodeFormatterConstants.FORMATTER_INDENTATION_SIZE);
  }

  private String getTabStyle() {
    return fStore.getString(CodeFormatterConstants.FORMATTER_TAB_CHAR);
  }

  private String getIndent() {
    if (getTabStyle().equals(CodeFormatterConstants.SPACE)) {
      int size = getIndentSize();
      String res = "";
      for (int i = 0; i < size; i++)
        res += " ";
      return res;
    } else
      return "\t";
  }

  private boolean isLineDelimiter(IDocument document, String text) {
    String[] delimiters = document.getLegalLineDelimiters();
    if (delimiters != null)
      return TextUtilities.equals(delimiters, text) > -1;
    return false;
  }

  /**
   * Returns the leading whitespaces and tabs.
   * 
   * @param document
   *          - the document being parsed
   * @param line
   *          - the line being searched
   * @return the leading whitespace
   * @throws BadLocationException
   *           in case <code>line</code> is invalid in the document
   */
  private String getLineIndent(IDocument document, int line) throws BadLocationException {
    if (line > -1) {
      int start = document.getLineOffset(line);
      int end = start + document.getLineLength(line); // was - 1
      int whiteend = findEndOfWhiteSpace(document, start, end);
      return document.get(start, whiteend - start);
    }
    return ""; //$NON-NLS-1$
  }

  /**
   * Returns the leading whitespaces and tabs.
   * 
   * @param line
   *          - the line being searched
   * @return the leading whitespace
   */
  public String getLineIndent(String line) {
    int end = line.length();
    int whiteend = end;
    int offset = 0;
    while (offset < end) {
      char c = line.charAt(offset);
      if (c != ' ' && c != '\t') {
        whiteend = offset;
        break;
      }
      offset++;
    }
    return line.substring(0, whiteend);
  }

  /**
   * Find line with number <=line, that is not empty and is not a comment line starting with #
   * 
   * @param d
   *          the document to search in
   * @param line
   *          number of starting line
   * @return number of code line or -1 if no such line found
   * @throws BadLocationException
   */
  private int getLastNonEmptyLine(IDocument d, int line) throws BadLocationException {
    int res = line;

    while (res > -1) {
      String str = getDocumentLine(d, res).trim();
      if ((!str.startsWith("#")) && str.length() > 0)
        return res;
      res--;
    }

    return res;
  }

  /**
   * Fetched line from document
   * 
   * @param d
   *          the document
   * @param line
   *          number of req. line
   * @return string with line
   * @throws BadLocationException
   *           if <b>line</b> is not correct line number
   */
  public String getDocumentLine(IDocument d, int line) throws BadLocationException {
    int start = d.getLineOffset(line);
    int length = d.getLineLength(line);
    return d.get(start, length);
  }

  /**
   * Get partition type covering offset
   * 
   * @param d
   * @param offset
   * @return
   * @throws BadLocationException
   */
  private String getRegionType(IDocument d, int offset) throws BadLocationException {
    int p = ((offset == d.getLength()) ? offset - 1 : offset);
    ITypedRegion region = TextUtilities.getPartition(d, fPartitioning, p, true);
    return region.getType();
  }

  /**
   * Searchs an pair from offset, forward of backwards. Can skip strings and comments (uses
   * textmarker partitioning).
   * 
   * @param d
   * @param offset
   * @param forward
   * @param opening
   * @param closing
   * @param skipCommentLines
   * @param skipStrings
   * @return offset of pair, or -1 if not found
   * @throws BadLocationException
   */
  private int searchPair(IDocument d, int offset, boolean forward, char opening, char closing,
          boolean skipCommentLines, boolean skipStrings) throws BadLocationException {
    int deep = 0;
    int i = offset;
    if (forward) {
      while (i < d.getLength()) {
        ITypedRegion region = TextUtilities.getPartition(d, fPartitioning, i, true);
        if (region.getType() == TextMarkerPartitions.TM_COMMENT && skipCommentLines) {
          i = region.getOffset() + region.getLength();
          continue;
        }
        if (region.getType() == TextMarkerPartitions.TM_STRING && skipStrings) {
          i = region.getOffset() + region.getLength();
          continue;
        }
        char c = d.getChar(i);
        if (c == opening)
          deep++;
        if (c == closing) {
          if (deep == 0)
            return i;
          deep--;
        }
        i++;
        if (i - offset > maxCharsAway)
          return -1;
      }
    } else {
      while (i >= 0) {
        ITypedRegion region = TextUtilities.getPartition(d, fPartitioning, i, true);
        if (region.getType() == TextMarkerPartitions.TM_COMMENT && skipCommentLines) {
          i = region.getOffset() - 1;
          continue;
        }
        if (region.getType() == TextMarkerPartitions.TM_STRING && skipStrings) {
          i = region.getOffset() - 1;
          continue;
        }
        char c = d.getChar(i);
        if (c == closing)
          deep++;
        if (c == opening) {
          if (deep == 0)
            return i;
          deep--;
        }
        i--;
        if (offset - i > maxCharsAway)
          return -1;
      }
    }
    return -1;
  }

  /**
   * Finds line < <b>line</b>, such the indent of it is less than of <b>line</b>
   * 
   * @param d
   *          the document to search in
   * @param line
   *          appropriate line number
   * @return if found, number < <b>line</b>, else <b>line</b>
   */
  private int findIndentStart(IDocument d, int line) throws BadLocationException {
    String curIndent = getLineIndent(d, line);
    int curIndentLength = getPhysicalLength(curIndent);
    int cur = line - 1;
    while (cur >= 0) {
      String curLine = getDocumentLine(d, cur);
      String ind = getLineIndent(d, cur);
      if ((!curLine.trim().startsWith("#")) && getPhysicalLength(ind) < curIndentLength)
        return cur;
      cur--;
    }
    return line;
  }

  /**
   * Calculates real length of string. So any char except \t has length 1, \t has length 8.
   * 
   * @param str
   *          string to process
   * @return length
   */
  public int getPhysicalLength(String str) {
    int res = 0;
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) == '\t')
        res += 8; // TODO
      else
        res++;
    }
    return res;
  }

  /**
   * Return pair to brace. Ex. '(' for ')', e.t.c.
   * 
   * @param b
   *          input brace
   * @return peer brace
   */
  private char getBracePair(char b) {
    switch (b) {
      case '(':
        return ')';
      case ')':
        return '(';
      case '[':
        return ']';
      case ']':
        return '[';
      case '{':
        return '}';
      case '}':
        return '{';
      case '\"':
        return '\"';
      case '\'':
        return '\'';
    }
    return b;
  }

  /**
   * Installs a partitioner with <code>document</code>.
   * 
   * @param document
   *          the document
   */
  private static void installStuff(Document document) {
    String[] types = new String[] { TextMarkerPartitions.TM_STRING,
        TextMarkerPartitions.TM_COMMENT, IDocument.DEFAULT_CONTENT_TYPE };
    FastPartitioner partitioner = new FastPartitioner(new TextMarkerPartitionScanner(), types);
    partitioner.connect(document);
    document.setDocumentPartitioner(TextMarkerPartitions.TM_PARTITIONING, partitioner);
  }

  /**
   * Removes partitioner with <code>document</code>.
   * 
   * @param document
   *          the document
   */
  private static void removeStuff(Document document) {
    document.setDocumentPartitioner(TextMarkerPartitions.TM_PARTITIONING, null);
  }

  /**
   * STRATEGIES
   */

  /**
   * Main indenting algorithm. Needs correct partitioning to work.
   * 
   * @param d
   *          the processed document
   * @param line
   *          line, indenting of which we wanna know
   * @param newLine
   *          if <b>line</b> is new line, so we have pressed enter-key, and need indentation for new
   *          line
   * @param offset
   *          position, where we have jumped to new line
   * @return correct indent for line, or null if we should use "default" indent. So calling side
   *         should determine, change indent or not, or may be use indent of previous line.
   * @throws BadLocationException
   */
  private String calcLineIndent(IDocument d, int line, boolean newLine, int offset)
          throws BadLocationException {

    boolean isDocumentEnd = (offset == d.getLength());

    // STRINGS
    if (newLine) {
      // if we wrap string
      if (getRegionType(d, offset) == TextMarkerPartitions.TM_STRING) {
        int realLine = d.getLineOfOffset(offset);
        String curIndent = getLineIndent(d, realLine);
        // if we just closed string
        if (d.getChar(offset - 1) != '"') {
          // if we are fully in string
          if (getRegionType(d, d.getLineOffset(realLine)) == TextMarkerPartitions.TM_STRING) {
            return curIndent;
          }
          // if we first time wrap string
          return curIndent + getIndent();
        }
      }
    } else {
      // don't correct strings
      if (getRegionType(d, d.getLineOffset(line)) == TextMarkerPartitions.TM_STRING) {
        return getLineIndent(d, line); // notice, that we don't use
        // null
      }
    }

    // LINE JOINING
    if (newLine) {
      int realLine = d.getLineOfOffset(offset);
      if (line > 0) {
        String previousLine = "";
        if (realLine == line - 1) {
          int start = d.getLineOffset(realLine);
          previousLine = d.get(start, offset - start);
        } else
          previousLine = getDocumentLine(d, line - 1);
        if (previousLine.trim().endsWith("\\")) {
          String prePreviousLine = getDocumentLine(d, line - 2);
          if (prePreviousLine.trim().endsWith("\\"))
            return getLineIndent(d, line - 1);
          return getLineIndent(d, line - 1) + getIndent() + getIndent();
        }
        if (line > 1) {
          String prePreviousLine = getDocumentLine(d, line - 2);
          if (prePreviousLine.trim().endsWith("\\")) {
            // find start
            int t = line - 2;
            while (t > 0 && getDocumentLine(d, t - 1).trim().endsWith("\\")) {
              t--;
            }
            return getLineIndent(d, t);
          }
        }
      }
    } else {
      /*
       * If this line is explicitly joined: If the previous line was also joined, line it up with
       * that one, otherwise add two 'shiftwidth'
       */
      if (line > 0) {
        String previousLine = getDocumentLine(d, line - 1);
        if (previousLine.trim().endsWith("\\")) {
          if (line > 1) {
            String prePreviousLine = getDocumentLine(d, line - 2);
            if (prePreviousLine.trim().endsWith("\\"))
              return getLineIndent(d, line - 1);
          }
          return getLineIndent(d, line - 1) + getIndent() + getIndent();
        }
      }
    }

    // Search backwards for the previous non-empty line.
    int lastNonEmptyLine = getLastNonEmptyLine(d, line - 1);

    if (lastNonEmptyLine < 0) {
      // This is the first non-empty line, use zero indent.
      return "";
    }

    // first check, if we not inside string, if yes, jump to start
    ITypedRegion region = TextUtilities.getPartition(d, fPartitioning, d
            .getLineOffset(lastNonEmptyLine), true);
    if (region.getType() == TextMarkerPartitions.TM_STRING) {
      lastNonEmptyLine = d.getLineOfOffset(region.getOffset());
    }

    // If the previous line is inside parenthesis, use the indent of the
    // starting line.
    int plnumstart;
    String previousLineIndent = "";
    int pairOffset = searchPair(d, d.getLineOffset(lastNonEmptyLine), false, '(', ')', true, true);

    if (pairOffset >= 0) {
      plnumstart = d.getLineOfOffset(pairOffset);
      previousLineIndent = getLineIndent(d, plnumstart);
    } else {
      plnumstart = lastNonEmptyLine;
      previousLineIndent = getLineIndent(d, lastNonEmptyLine);
    }

    /*
     * When inside parenthesis: If at the first line below the parenthesis add two 'shiftwidth',
     * otherwise same as previous line. i = (a + b + c)
     */
    int p = searchPair(d, offset - 1, false, '(', ')', true, true);
    if (p >= 0) {
      if (d.getLineOfOffset(p) == lastNonEmptyLine) {
        // When the start is inside parenthesis, only indent one
        // 'shiftwidth'.
        int pp = searchPair(d, p, false, '(', ')', true, true);
        if (pp >= 0)
          return getLineIndent(d, lastNonEmptyLine) + getIndent();
        return getLineIndent(d, lastNonEmptyLine) + getIndent() + getIndent();
      }
      if (d.getLineOfOffset(p) == plnumstart) {
        return getLineIndent(d, lastNonEmptyLine);
      }
      if (d.getLineOfOffset(p) == line && !newLine)
        return null;
      return previousLineIndent;
    }

    // Get the line and remove a trailing comment.

    String pline = "";
    if (lastNonEmptyLine == line - 1 && newLine) {
      pline = d.get(d.getLineOffset(line - 1), offset - d.getLineOffset(line - 1));
    } else {
      pline = getDocumentLine(d, lastNonEmptyLine);
    }
    int plineLen = pline.length();
    int i;
    for (i = 0; i < plineLen; i++) {
      if (pline.charAt(i) == '#') {
        pline = pline.substring(0, i);
        break;
      }
    }

    String plineTrimmed = pline.trim();

    try {// If the current line begins with a keyword that lines up with
      // "try"
      String curLine = "";
      if (lastNonEmptyLine == line - 1 && newLine) {
        curLine = d.get(offset, d.getLineLength(line - 1) + d.getLineOffset(line - 1) - offset);
      } else {
        curLine = getDocumentLine(d, line).trim();
      }

      if (curLine.startsWith("except") || curLine.startsWith("finally")) {
        int lnum = line - 1;

        while (lnum >= 0) {
          String temp = getDocumentLine(d, lnum).trim();
          if (temp.startsWith("try") || temp.startsWith("except")) {
            String ind = getLineIndent(d, lnum);
            return ind;
          }
          lnum--;
        }
        // no mathing "try"
        // System.out.println ("No matching 'try'!");
        return null;
      }

      // If the current line begins with a header keyword, dedent
      if (curLine.startsWith("elif") || curLine.startsWith("else")) {

        // Unless the previous line was a one-liner
        String temp = getDocumentLine(d, lastNonEmptyLine).trim();
        if (temp.startsWith("for") || temp.startsWith("if") || temp.startsWith("try")
                || temp.startsWith("while")) {
          return previousLineIndent;
        }
        int sline = findIndentStart(d, lastNonEmptyLine);
        String reqIndent = getLineIndent(d, sline);
        return reqIndent;
      }

    } catch (BadLocationException e) {
      // do nothing, we just don't have current line
    }

    // If the previous line was a stop-execution statement...
    String regex = "^\\s*(break|continue|raise|pass|return)(\\s+.*$|$)";
    if (Pattern.matches(regex, plineTrimmed)) {
      // find indent
      int sline = findIndentStart(d, lastNonEmptyLine);
      String reqIndent = getLineIndent(d, sline);
      if (newLine || isDocumentEnd
              || (getPhysicalLength(getLineIndent(d, line)) > getPhysicalLength(reqIndent))) {
        return reqIndent;
      }
      // trust the user
      return null;
    }

    // If the previous line ended with a colon, indent this line
    if (plineTrimmed.endsWith(":"))
      return previousLineIndent + getIndent();

    if (pairOffset >= 0 && newLine) {
      return previousLineIndent;
    }

    // after-string
    int prevLine = getLastNonEmptyLine(d, line - 1);
    if (getRegionType(d, d.getLineOffset(prevLine)) == TextMarkerPartitions.TM_STRING)
      return previousLineIndent;

    return null;
  }

  /**
   * If we have pressed ":" for example, than we need to reindent line. This function changes
   * document and sets correct indent for current line.
   * 
   * @param d
   * @param c
   */
  private void reindent(IDocument d, DocumentCommand c) {
    try {
      if (getRegionType(d, c.offset) != IDocument.DEFAULT_CONTENT_TYPE)
        return;
      int line = d.getLineOfOffset(c.offset);
      String newIndent = calcLineIndent(d, line, false, c.offset);
      if (newIndent == null)
        return;
      String curIndent = getLineIndent(d, line);
      if (getPhysicalLength(curIndent) < getPhysicalLength(newIndent))
        return;
      d.replace(d.getLineOffset(line), curIndent.length(), newIndent);
      c.offset += (newIndent.length() - curIndent.length());
    } catch (BadLocationException e) {
    }
  }

  /**
   * Processes command in work with brackets, strings, etc
   * 
   * @param d
   * @param c
   */
  private void autoClose(IDocument d, DocumentCommand c) {
    if (c.offset == -1)
      return;
    try {
      if (d.getChar(c.offset - 1) == '\\')
        return;
    } catch (BadLocationException e1) {
    }
    if ('\"' == c.text.charAt(0) && !closeStrings())
      return;
    if ('\'' == c.text.charAt(0) && !closeStrings())
      return;
    if (!closeBrackets()
            && ('[' == c.text.charAt(0) || '(' == c.text.charAt(0) || '{' == c.text.charAt(0)))
      return;
    try {

      switch (c.text.charAt(0)) {
        case '\"':
        case '\'':
          // if we close existing quote, do nothing
          if ('\"' == c.text.charAt(0) && c.offset > 0 && "\"".equals(d.get(c.offset - 1, 1)))
            return;

          if ('\'' == c.text.charAt(0) && c.offset > 0 && "\'".equals(d.get(c.offset - 1, 1)))
            return;

          if (c.offset != d.getLength() && c.text.charAt(0) == d.get(c.offset, 1).charAt(0))
            c.text = "";
          else {
            c.text += c.text;
            c.length = 0;
          }

          c.shiftsCaret = false;
          c.caretOffset = c.offset + 1;
          break;
        case '(':
        case '{':
        case '[':
          // check partition
          if (getRegionType(d, c.offset) != IDocument.DEFAULT_CONTENT_TYPE)
            return;
          if (c.offset != d.getLength() && c.text.charAt(0) == d.get(c.offset, 1).charAt(0))
            return;

          try { // in class closing
            String regex = "^\\s*class\\s+.*";
            String regex2 = ".*\\(.*\\).*";
            int start = d.getLineOffset(d.getLineOfOffset(c.offset));
            String curLine = d.get(start, c.offset - start);
            if (Pattern.matches(regex, curLine) && !Pattern.matches(regex2, curLine)) {
              c.text = "():";
              c.shiftsCaret = false;
              c.caretOffset = c.offset + 1;
              return;
            }
          } catch (BadLocationException e) {
          }

          // add closing peer
          c.text = c.text + getBracePair(c.text.charAt(0));
          c.length = 0;

          c.shiftsCaret = false;
          c.caretOffset = c.offset + 1;
          break;
        case '}':
        case ']':
        case ')':
          // check partition
          if (getRegionType(d, c.offset) != IDocument.DEFAULT_CONTENT_TYPE)
            return;
          if (!closeBrackets())
            return;
          // if we already have bracket we should jump over it
          if (c.offset != d.getLength() && c.text.charAt(0) == d.get(c.offset, 1).charAt(0)) {
            c.text = "";
            c.shiftsCaret = false;
            c.caretOffset = c.offset + 1;
            return;
          }
          break;
      }
    } catch (BadLocationException e) {
      e.printStackTrace();
    }
  }

  /**
   * When we have pressed \t, sometimes we wanna not to tabulate, and jump to correct line start
   * position.
   * 
   * @param d
   * @param c
   * @return
   */
  private boolean smartIndentJump(IDocument d, DocumentCommand c) {
    if (c.offset == -1 || d.getLength() == 0)
      return false;
    try {
      // int p = (c.offset == d.getLength() ? c.offset - 1 : c.offset);
      int line = d.getLineOfOffset(c.offset);
      int start = d.getLineOffset(line);
      // calculate indentation of this line
      String resultIndent = calcLineIndent(d, line, false, start);
      String currentIndent = getLineIndent(d, line);
      if (resultIndent == null) { // we should save indentation
        String curLine = getDocumentLine(d, line);
        if (curLine.trim().length() > 0) // if indentation is "real",
          // use it
          resultIndent = currentIndent;
        else {
          // get current block level
          int pl = getLastNonEmptyLine(d, line - 1); // find last
          // code line
          if (pl >= 0) {
            String plStr = getDocumentLine(d, pl).trim();
            // simple indent-guess strategy
            String regex = "^\\s*(break|continue|raise|pass|return)(\\s+.*$|$)";
            if (plStr.endsWith(":"))
              resultIndent = getLineIndent(plStr) + getIndent();
            else if (Pattern.matches(regex, plStr)) {
              // find indent
              int sline = findIndentStart(d, pl);
              resultIndent = getLineIndent(d, sline);
            } else
              resultIndent = getLineIndent(d, pl);
          } else
            return false; // no indent is applicable, do nothing
        }
      }

      if (c.offset >= start + resultIndent.length())
        return false; // we already in the place

      if (!currentIndent.startsWith(resultIndent)) { // create indent
        c.offset = start;
        c.length = currentIndent.length();
        c.shiftsCaret = false;
        c.text = resultIndent;
        c.caretOffset = d.getLineOffset(line) + resultIndent.length();
        return true;
      }

      c.length = 0;
      c.shiftsCaret = false;
      c.text = "";
      c.caretOffset = d.getLineOffset(line) + resultIndent.length();
      return true;
    } catch (BadLocationException e) {
      e.printStackTrace();
    }
    return false;
  }

  /**
   * Reindents c.text when pasting.
   * 
   * @param d
   * @param c
   */
  private void smartPaste(IDocument d, DocumentCommand c) {
    /*
     * We are creating temp document, inserting text as is, and then sequentially calling
     * calcLineIndent for each new line.
     */
    try {
      String content = d.get(0, c.offset) + c.text;
      Document temp = new Document(content);
      DocumentRewriteSession session = temp
              .startRewriteSession(DocumentRewriteSessionType.STRICTLY_SEQUENTIAL);
      installStuff(temp);
      int offset = c.offset;
      int line = temp.getLineOfOffset(offset);
      String lastIndent = getLineIndent(temp, line);
      int firstLineOffset = temp.getLineOffset(line);
      String commonIndent = temp.get(firstLineOffset, c.offset - firstLineOffset);
      line++;
      try {
        while (getDocumentLine(temp, line).trim().length() == 0)
          line++;
        offset = temp.getLineOffset(line);
      } catch (BadLocationException e) {
        // ok, we are inserting only one string...
        offset = temp.getLength();
      }
      while (offset < temp.getLength()) {
        // calculate indentation of this line
        String resultIndent = calcLineIndent(temp, line, false, temp.getLineOffset(line));
        // change current line offset
        String currentIndent = getLineIndent(temp, line);
        // String dbg = temp.get ();
        if (resultIndent == null) {
          resultIndent = commonIndent + currentIndent;
          if (getPhysicalLength(resultIndent) > getPhysicalLength(lastIndent))
            resultIndent = lastIndent;
        }
        temp.replace(offset, currentIndent.length(), resultIndent);
        String currentLine = getDocumentLine(temp, line);
        if (currentLine.trim().length() > 0 && (!currentLine.trim().startsWith("#")))
          lastIndent = resultIndent;

        // dbg = temp.get ();
        if (temp.getLineOffset(line) + temp.getLineLength(line) == temp.getLength())
          break;
        line++;
        offset = temp.getLineOffset(line);
      }
      temp.stopRewriteSession(session);
      removeStuff(temp);
      c.text = temp.get(c.offset, temp.getLength() - c.offset);
    } catch (BadLocationException e) {
      e.printStackTrace();
    }

  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.jface.text.IAutoEditStrategy#customizeDocumentCommand(org.eclipse.jface.text.IDocument
   * , org.eclipse.jface.text.DocumentCommand)
   */
  @Override
  public void customizeDocumentCommand(IDocument d, DocumentCommand c) {
    if (c.doit == false)
      return;
    if (c.length == 0 && c.text != null && isLineDelimiter(d, c.text)) {
      if (!isSmartMode()) {
        super.customizeDocumentCommand(d, c);
        return;
      }
      try {
        String indent = calcLineIndent(d, d.getLineOfOffset(c.offset) + 1, true, c.offset);
        if (indent == null)
          super.customizeDocumentCommand(d, c);
        else {
          if (DLTKCore.DEBUG) {
            System.err.println("Bug:PTN-9");
          }
          // if (c.offset - 1 >= 0 &&
          // d.getChar(c.offset - 1) != '"' &&
          // getRegionType(d, c.offset - 1) ==
          // TextMarkerPartitions.TM_STRING)
          // c.text = "\\" + c.text + indent;
          // else {
          c.text = c.text + indent;
          // }
        }
      } catch (BadLocationException e) {
        super.customizeDocumentCommand(d, c);
      }
      return;
    } else {
      if (c.length <= 1 && c.text.length() == 1) {
        switch (c.text.charAt(0)) {
          case ':':
            reindent(d, c);
            break;
          case '\"':
          case '\'':
          case '(':
          case '{':
          case '[':
          case '}':
          case ']':
          case ')':
            autoClose(d, c);
            break;
          case '\t':
            boolean jumped = false;
            if (isSmartTab()) {
              jumped = smartIndentJump(d, c);
            }
            if (!jumped) {
              c.text = getIndent();
            }
            break;
        }
      } else if (c.text.length() >= 1 && isSmartPaste())
        smartPaste(d, c); // no smart backspace for paste
    }
  }

}
