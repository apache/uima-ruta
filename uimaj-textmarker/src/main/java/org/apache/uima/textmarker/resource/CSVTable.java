/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
*/

package org.apache.uima.textmarker.resource;

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
