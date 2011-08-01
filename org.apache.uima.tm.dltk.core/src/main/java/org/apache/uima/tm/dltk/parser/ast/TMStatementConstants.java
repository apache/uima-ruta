/**
 * 
 */
package org.apache.uima.tm.dltk.parser.ast;

/**
 * @author Martin Toepfer
 * 
 */
public interface TMStatementConstants {
  public static final int S_USERSTART = 1;

  public static final int S_IMPORT = S_USERSTART;

  public static final int S_IMPORT_TYPESYSTEM = S_IMPORT | 1 << 1;

  public static final int S_IMPORT_SCRIPT = S_IMPORT | 1 << 2;

  public static final int S_IMPORT_ENGINE = S_IMPORT | 1 << 3;

  public static final int S_DECLARATIONS = S_USERSTART | 1 << 4;
}
