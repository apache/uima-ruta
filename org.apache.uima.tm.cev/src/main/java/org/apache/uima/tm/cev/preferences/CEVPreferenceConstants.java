package org.apache.uima.tm.cev.preferences;

/**
 * Konstanten fuer die Preferences
 * 
 * @author Marco Nehmeier
 * 
 */
public class CEVPreferenceConstants {

  // Anntotation-Filter
  public static final String P_ANNOTATION_FILTER = "annotationFilter";

  // Annotation-Repr.
  public static final String P_ANNOTATION_REPR = "annotationRpr";

  // HTML
  public static final String P_ANNOTATION_REPR_HTML = "html";

  // Text
  public static final String P_ANNOTATION_REPR_TEXT = "text";

  // Werte fuer Repr.
  public static final String[][] P_ANNOTATION_REPR_VALUES = new String[][] {
      { "&HTML", P_ANNOTATION_REPR_HTML }, { "&Text", P_ANNOTATION_REPR_TEXT } };

  // Baumordnung
  public static final String P_ANNOTATION_BROWSER_TREE_ORDER = "annotationBrowserTreeOrder";

  // Typ
  public static final String P_ANNOTATION_BROWSER_TREE_ORDER_TYPE = "type";

  // Annot
  public static final String P_ANNOTATION_BROWSER_TREE_ORDER_ANNOT = "annot";

  // Werte fuer Baumordnung
  public static final String[][] P_ANNOTATION_BROWSER_TREE_ORDER_VALUE = new String[][] {
      { "&Type Ordered", P_ANNOTATION_BROWSER_TREE_ORDER_TYPE },
      { "&Annotation Ordered", P_ANNOTATION_BROWSER_TREE_ORDER_ANNOT } };

  public static final String P_ANNOTATION_EDITOR_TRIM = "trim";

  public static final String P_AUTO_REFRESH = "autoRefresh";

  public static final String P_SELECT_ONLY = "selectOnly";

}
