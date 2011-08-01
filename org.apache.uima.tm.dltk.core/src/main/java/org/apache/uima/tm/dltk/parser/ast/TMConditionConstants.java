/**
 * 
 */
package org.apache.uima.tm.dltk.parser.ast;

import org.apache.uima.tm.dltk.internal.core.parsers.TextMarkerParser;
import org.eclipse.dltk.ast.expressions.ExpressionConstants;


/**
 * @author Martin Toepfer
 * 
 */
public class TMConditionConstants {
  public static final int CONSTANT_OFFSET = ExpressionConstants.USER_EXPRESSION_START;

  // important for formatter to handle the only condition without parantheses
  public static final int COND_MINUS = CONSTANT_OFFSET + TextMarkerParser.MINUS;

  // public static int getConditionConstant(String str) {
  // // TextMarkerParser.tokenNames;
  // return 0;
  // }
}
