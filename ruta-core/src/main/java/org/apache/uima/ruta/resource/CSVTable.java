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

package org.apache.uima.ruta.resource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.uima.ruta.block.RutaBlock;
import org.apache.uima.ruta.engine.RutaEngine;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class CSVTable implements RutaTable {
  public static final String DEFAULT_CSV_SEPARATOR = ";";

  private List<List<String>> tableData;

  private Map<Integer, RutaWordList> columnWordLists = new HashMap<Integer, RutaWordList>(2);

  private final String separator;

  /**
   * @param table
   *          A CSV table.
   * @param selectedSeparator
   *         The separator that should be used to splitup between columns in the CSV file
   *
   * @throws IOException
   *           When there is a problem opening, reading or closing the table.
   */
  public CSVTable(Resource table, String selectedSeparator) throws IOException {
    separator = selectedSeparator;
    InputStream stream = null;
    try {
      stream = table.getInputStream();
      buildTable(stream);
    } finally {
      if (stream != null) {
        stream.close();
      }
    }
  }

  public CSVTable(String location, String selectedSeparator) throws IOException {
    this(new FileSystemResource(location), selectedSeparator);
  }

  public CSVTable(InputStream stream, String selectedSeparator) throws IOException {
    super();
    separator = selectedSeparator;
    buildTable(stream);
  }

  private void buildTable(InputStream stream) {
    Scanner sc = new Scanner(stream, Charset.forName("UTF-8").name());
    sc.useDelimiter("\\n");
    tableData = new ArrayList<List<String>>();
    while (sc.hasNext()) {
      String line = sc.next().trim();
      // Quote separator to ignore special characters in regex
      String quotedSeparator = Pattern.quote(separator);
      // add spacer between 2 followed separators without any other characters
      line = line.replaceAll(quotedSeparator + quotedSeparator, separator + " " + separator);
      String[] lineElements = line.split(quotedSeparator);
      List<String> row = Arrays.asList(lineElements);
      tableData.add(row);
    }
    sc.close();
  }

  @Override
  public RutaWordList getWordList(int index, RutaBlock parent) {
    RutaWordList list = columnWordLists.get(index);
    if (list == null) {
      if (index > 0 && index <= tableData.get(0).size()) {
        Boolean dictRemoveWS = (Boolean) parent.getContext().getConfigParameterValue(
                RutaEngine.PARAM_DICT_REMOVE_WS);
        if (dictRemoveWS == null) {
          dictRemoveWS = false;
        }
        list = new TreeWordList(getColumnData(index - 1), dictRemoveWS);
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
      } else {
        result.add("");
      }
    }
    return result;
  }

  @Override
  public String getEntry(int row, int column) {
    return tableData.get(row).get(column);
  }

  @Override
  public List<String> getRowWhere(int column, String lookupValue, boolean ignoreCase) {
    List<String> columnData = getColumnData(column);
    int i = 0;
    for (String tableValue : columnData) {
      if (ignoreCase ? tableValue.equalsIgnoreCase(lookupValue) : tableValue.equals(lookupValue)) {
        return tableData.get(i);
      }
      i++;
    }
    i = 0;
    for (String tableValue : columnData) {
      String tableValueWithoutSpacers = tableValue.replaceAll("\\s", "");
      if (ignoreCase ? tableValueWithoutSpacers.equalsIgnoreCase(lookupValue) : tableValueWithoutSpacers.equals(lookupValue)) {
        return tableData.get(i);
      }
      i++;
    }
    return new ArrayList<>();
  }
}
