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

package org.apache.uima.textmarker.statistics;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class StatisticsLabelProvider extends LabelProvider implements ITableLabelProvider {

  private StatisticsViewPage owner;

  public StatisticsLabelProvider(StatisticsViewPage owner) {
    super();
    this.owner = owner;
  }

  public Image getColumnImage(Object element, int columnIndex) {
    return null;
  }

  public String getColumnText(Object element, int columnIndex) {
    StatisticsEntry entry = (StatisticsEntry) element;
    switch (columnIndex) {
      case 0:
        return entry.getName();
      case 1:
        return entry.getTotal() + "ms";
      case 2:
        return entry.getAmount() + "";
      case 3:
        return entry.getPart() + "ms";
      default:
        throw new RuntimeException("Should not happen");
    }

  }

}
