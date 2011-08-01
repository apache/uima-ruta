package org.apache.uima.tm.textmarker.resource;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CSVTable implements TextMarkerTable {

  private List<List<String>> tableData;

  private Map<Integer, TextMarkerWordList> columnWordLists;

  public CSVTable(String location) {
    super();
    columnWordLists = new HashMap<Integer, TextMarkerWordList>(2);
    try {
      buildTable(location);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void buildTable(String location) throws Exception {
    FileInputStream in = new FileInputStream(location);
    Scanner sc = new Scanner(in);
    sc.useDelimiter("\\n");
    tableData = new ArrayList<List<String>>();
    while (sc.hasNext()) {
      String line = sc.next().trim();
      line = line.replaceAll(";;", "; ;");
      String[] lineElements = line.split(";");
      List<String> row = Arrays.asList(lineElements);
      tableData.add(row);
    }
  }

  public TextMarkerWordList getWordList(int index) {
    TextMarkerWordList list = columnWordLists.get(index);
    if (list == null) {
      if (index > 0 && index < tableData.get(0).size()) {
        list = new TreeWordList(getColumnData(index - 1));
        columnWordLists.put(index, list);
      }
    }
    return list;
  }

  private List<String> getColumnData(int i) {
    List<String> result = new LinkedList<String>();
    for (List<String> each : tableData) {
      if (each.size() > i) {
        result.add(each.get(i));
      }
    }
    return result;
  }

  public String getEntry(int row, int column) {
    return tableData.get(row).get(column);
  }

  @Override
  public List<String> getRowWhere(int column, String value) {
    List<String> columnData = getColumnData(column);
    int i = 0;
    for (String string : columnData) {
      if (string.equals(value)) {
        return tableData.get(i);
      }
      i++;
    }
    return new ArrayList<String>();
  }
}
