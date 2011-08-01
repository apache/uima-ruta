package org.apache.uima.tm.dltk.internal.ui.documentation;

import org.eclipse.dltk.core.IBuffer;
import org.eclipse.dltk.corext.documentation.SingleCharReader;

public class TextMarkerCommentReader extends SingleCharReader {

  private IBuffer fBuffer;

  private int fCurrPos;

  private int fStartPos;

  private int fEndPos;

  private boolean fWasNewLine;

  public TextMarkerCommentReader(IBuffer buf, int start, int end) {
    fBuffer = buf;
    fStartPos = start;
    fEndPos = end;

    reset();
  }

  /**
   * @see java.io.Reader#read()
   */
  @Override
  public int read() {
    if (fCurrPos < fEndPos) {
      char ch;
      if (fWasNewLine) {
        do {
          ch = fBuffer.getChar(fCurrPos++);
        } while (fCurrPos < fEndPos && Character.isWhitespace(ch));
        if (ch == '#') {
          if (fCurrPos < fEndPos) {
            do {
              ch = fBuffer.getChar(fCurrPos++);
            } while (ch == '#');
          } else {
            return -1;
          }
        }
      } else {
        ch = fBuffer.getChar(fCurrPos++);
      }
      fWasNewLine = (ch == '\n' || ch == '\r');

      return ch;
    }
    return -1;
  }

  /**
   * @see java.io.Reader#close()
   */
  @Override
  public void close() {
    fBuffer = null;
  }

  /**
   * @see java.io.Reader#reset()
   */
  @Override
  public void reset() {
    fCurrPos = fStartPos;
    fWasNewLine = true;
  }

  /**
   * Returns the offset of the last read character in the passed buffer.
   */
  public int getOffset() {
    return fCurrPos;
  }
}
