package org.apache.uima.tm.cev.preferences;

public class CEVPreferenceConstants {

  public static final String P_ANNOTATION_FILTER = "annotationFilter";

  public static final String P_ANNOTATION_REPR = "annotationRpr";

  public static final String P_ANNOTATION_REPR_HTML = "html";

  public static final String P_ANNOTATION_REPR_TEXT = "text";

  public static final String[][] P_ANNOTATION_REPR_VALUES = new String[][] {
      { "&HTML", P_ANNOTATION_REPR_HTML }, { "&Text", P_ANNOTATION_REPR_TEXT } };

  public static final String P_ANNOTATION_BROWSER_TREE_ORDER = "annotationBrowserTreeOrder";

  public static final String P_ANNOTATION_BROWSER_TREE_ORDER_TYPE = "type";

  public static final String P_ANNOTATION_BROWSER_TREE_ORDER_ANNOT = "annot";

  public static final String[][] P_ANNOTATION_BROWSER_TREE_ORDER_VALUE = new String[][] {
      { "&Type Ordered", P_ANNOTATION_BROWSER_TREE_ORDER_TYPE },
      { "&Annotation Ordered", P_ANNOTATION_BROWSER_TREE_ORDER_ANNOT } };

  public static final String P_ANNOTATION_EDITOR_TRIM = "trim";

  public static final String P_AUTO_REFRESH = "autoRefresh";

  public static final String P_SELECT_ONLY = "selectOnly";

}
