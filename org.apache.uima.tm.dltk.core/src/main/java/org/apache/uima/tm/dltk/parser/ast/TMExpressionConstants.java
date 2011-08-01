/**
 * 
 */
package org.apache.uima.tm.dltk.parser.ast;

import java.util.HashMap;
import java.util.Map;

import org.apache.uima.tm.textmarker.parser.TextMarkerLexer;
import org.eclipse.dltk.ast.expressions.ExpressionConstants;


/**
 * @author Martin Toepfer
 * 
 */
public final class TMExpressionConstants implements ExpressionConstants {
  public static final int E_EXP = USER_EXPRESSION_START + TextMarkerLexer.EXP;

  public static final int E_LOGN = USER_EXPRESSION_START + TextMarkerLexer.LOGN;

  public static final int E_SIN = USER_EXPRESSION_START + TextMarkerLexer.SIN;

  public static final int E_COS = USER_EXPRESSION_START + TextMarkerLexer.COS;

  public static final int E_TAN = USER_EXPRESSION_START + TextMarkerLexer.TAN;

  public static final int E_QUANTIFIER_LIT = USER_EXPRESSION_START + TextMarkerLexer.QUESTION;

  public static final int E_INNERLIST = USER_EXPRESSION_START + TextMarkerLexer.RBRACK;

  public static final int E_RESSOURCE = USER_EXPRESSION_START + TextMarkerLexer.RessourceLiteral;

  public static final String E_EXP_STR = "EXP";

  public static final String E_LOGN_STR = "LOGN";

  public static final String E_SIN_STR = "SIN";

  public static final String E_COS_STR = "COS";

  public static final String E_TAN_STR = "TAN";

  public static final String E_RESSOURCE_STR = "RESSOURCE";

  public static final String E_QUANTIFIER_LIT_STR = "QUANTIFIER";

  public static final String E_INNERLIST_STR = "INNERLIST";

  public static final Map<String, Integer> opIDs = new HashMap<String, Integer>();

  static {
    opIDs.put(E_EXP_STR, Integer.valueOf(E_EXP));
    opIDs.put(E_LOGN_STR, Integer.valueOf(E_LOGN));
    opIDs.put(E_SIN_STR, Integer.valueOf(E_SIN));
    opIDs.put(E_COS_STR, Integer.valueOf(E_COS));
    opIDs.put(E_TAN_STR, Integer.valueOf(E_TAN));
    opIDs.put(E_RESSOURCE_STR, Integer.valueOf(E_RESSOURCE));
    opIDs.put(E_QUANTIFIER_LIT_STR, Integer.valueOf(E_QUANTIFIER_LIT));
    opIDs.put(E_INNERLIST_STR, Integer.valueOf(E_INNERLIST));
  }
}
