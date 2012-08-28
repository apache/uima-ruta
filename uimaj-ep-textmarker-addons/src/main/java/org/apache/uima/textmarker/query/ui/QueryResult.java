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

package org.apache.uima.textmarker.query.ui;

import java.io.File;

public class QueryResult {

  private final int begin;

  private final int end;

  private final File file;

  private final String text;

  public QueryResult(int begin, int end, String text, File file) {
    super();
    this.begin = begin;
    this.end = end;
    this.text = text;
    this.file = file;
  }

  public File getFile() {
    return file;
  }

  public int getEnd() {
    return end;
  }

  public int getBegin() {
    return begin;
  }

  public String getText() {
    return text;
  }

}
