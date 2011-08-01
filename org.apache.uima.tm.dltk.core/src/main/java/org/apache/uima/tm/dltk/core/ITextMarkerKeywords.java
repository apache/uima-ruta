package org.apache.uima.tm.dltk.core;

public interface ITextMarkerKeywords {

  public static final int ALL = 0;

  public static final int DECLARATION = 1;

  public static final int BASIC = 2;

  public static final int CONDITION = 4;

  public static final int ACTION = 5;

  public static final int BOOLEANFUNCTION = 6;

  public static final int NUMBERFUNCTION = 7;

  public static final int STRINGFUNCTION = 8;

  public static final int TYPEFUNCTION = 9;

  public static final int THEN = 10;

  public static final int START_INDEX = 1;

  public static final int END_INDEX = 11;

  String[] getKeywords(int type);
}
