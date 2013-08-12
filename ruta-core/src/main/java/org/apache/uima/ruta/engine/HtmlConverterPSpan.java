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

package org.apache.uima.ruta.engine;

public class HtmlConverterPSpan implements Comparable<HtmlConverterPSpan> {
  private int begin;

  private int end;

  private String txt;

  public HtmlConverterPSpan(int begin, int end, String txt) {
    super();
    this.begin = begin;
    this.end = end;
    this.txt = txt;
  }

  public String getTxt() {
    return txt;
  }

  public int getLength() {
    return this.end - this.begin;
  }

  public int getBegin() {
    return begin;
  }

  public int getEnd() {
    return end;
  }

  public int compareTo(HtmlConverterPSpan o) {
    if (this.begin == o.begin) {
      if (this.end == o.end) {
        return 0;
      }
      return this.end < o.end ? -1 : +1;
    }
    return this.begin < o.end ? -1 : +1;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + begin;
    result = prime * result + end;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    HtmlConverterPSpan other = (HtmlConverterPSpan) obj;
    if (begin != other.begin)
      return false;
    if (end != other.end)
      return false;
    return true;
  }

  @Override
  public String toString() {
    String shortTxt = this.txt.length() > 100 ? this.txt.substring(0, 100) + "..." : this.txt;
    return String.format("[%d-%d : %s]", begin, end, shortTxt);
  }
}