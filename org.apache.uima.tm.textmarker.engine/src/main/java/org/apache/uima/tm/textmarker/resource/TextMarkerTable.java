package org.apache.uima.tm.textmarker.resource;

import java.util.List;

public interface TextMarkerTable {

  TextMarkerWordList getWordList(int index);

  String getEntry(int row, int column);

  List<String> getRowWhere(int column, String value);

}
