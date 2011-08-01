/**
 * 
 */
package org.apache.uima.tm.dltk.parser.ast.actions;

import java.util.List;

import org.apache.uima.tm.dltk.parser.ast.TMActionConstants;


/**
 * @author Martin Toepfer
 * 
 */
public class TextMarkerLogAction extends TextMarkerAction {
  int[] logLevelBounds;

  boolean logLevelAssigned;

  public TextMarkerLogAction(int exprStart, int exprEnd, String name, int nameStart, int nameEnd,
          List exprs, int levelStart, int levelEnd) {
    super(exprStart, exprEnd, exprs, TMActionConstants.A_LOG, name, nameStart, nameEnd);
    logLevelBounds = new int[2];
    logLevelBounds[0] = levelStart;
    logLevelBounds[1] = levelEnd;
    if (levelStart > 0 && levelEnd > 0) {
      logLevelAssigned = true;
    }
  }

  /**
   * @return may throw nullpointer if no level assigned
   */
  public int getLogLevelStart() {
    return logLevelBounds[0];
  }

  /**
   * @return may throw nullpointer if no level assigned
   */
  public int getLogLevelEnd() {
    return logLevelBounds[1];
  }

  public boolean isLogLevelAssigned() {
    return logLevelAssigned;
  }
}
