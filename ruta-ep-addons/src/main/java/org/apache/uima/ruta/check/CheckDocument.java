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

package org.apache.uima.ruta.check;

import java.util.Collection;
import java.util.TreeSet;

public class CheckDocument extends CheckElement {

  public String source;

  public final Collection<String> checkedTypes = new TreeSet<String>();
  
  public CheckDocument(String source) {
    super();
    this.source = source;
  }

  public String toXML() {
    StringBuilder sb = new StringBuilder();
    sb.append("<document source=\"");
    sb.append(source);
    sb.append("\" >");
    sb.append("\n");
    for (String each : checkedTypes) {
      sb.append("<type>");
      sb.append(each);
      sb.append("</type>");
      sb.append("\n");
    }
    sb.append("</document>");
    sb.append("\n");
    return sb.toString();
  }
  
  
}
