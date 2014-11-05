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

import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.engine.RutaEngine;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class CSVTable implements RutaTable {

  private List<List<String>> tableData;

  private Map<Integer, RutaWordList> columnWordLists = new HashMap<Integer, RutaWordList>(2);

  /**
   * @param table
   *          A CSV table.
   * @throws IOException
   *           When there is a problem opening, reading or closing the table.
   */
  public CSVTable(Resource table) throws IOException {
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

  public CSVTable(String location) throws IOException {
    this(new FileSystemResource(location));
  }

  public CSVTable(InputStream stream) throws IOException {
    super();
    buildTable(stream);
  }

  private void buildTable(InputStream stream) {
    Scanner sc = new Scanner(stream, Charset.forName("UTF-8").name());
    sc.useDelimiter("\\n");
    tableData = new ArrayList<List<String>>();
    while (sc.hasNext()) {
      String line = sc.next().trim();
      line = line.replaceAll(";;", "; ;");
      String[] lineElements = line.split(";");
      List<String> row = Arrays.asList(lineElements);
      tableData.add(row);
    }
    sc.close();
  }

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

  public String getEntry(int row, int column) {
    return tableData.get(row).get(column);
  }

  public List<String> getRowWhere(int column, String value) {
    List<String> columnData = getColumnData(column);
    int i = 0;
    for (String string : columnData) {
      if (string.toLowerCase().equals(value.toLowerCase())) {
        return tableData.get(i);
      }
      i++;
    }
    i = 0;
    for (String string : columnData) {
      if (string.toLowerCase().replaceAll("\\s", "").equals(value.toLowerCase())) {
        return tableData.get(i);
      }
      i++;
    }
    return new ArrayList<String>();
  }
}
