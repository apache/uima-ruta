package org.apache.uima.tm.dltk.ui.text;

import org.apache.uima.tm.dltk.core.TextMarkerConstants;
import org.eclipse.jface.text.IDocument;


public interface TextMarkerPartitions {

  public final static String TM_PARTITIONING = TextMarkerConstants.TM_PARTITIONING;

  public final static String TM_COMMENT = "__textmarker_comment";

  public final static String TM_STRING = "__textmarker_string";

  public static final String TM_INNER_CODE = "__tm_inner_code";

  public final static String[] TM_PARTITION_TYPES = new String[] { TextMarkerPartitions.TM_STRING,
      TextMarkerPartitions.TM_COMMENT, IDocument.DEFAULT_CONTENT_TYPE };
}
