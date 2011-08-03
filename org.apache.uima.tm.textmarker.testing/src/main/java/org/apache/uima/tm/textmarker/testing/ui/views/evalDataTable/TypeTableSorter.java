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

package org.apache.uima.tm.textmarker.testing.ui.views.evalDataTable;

import java.text.Collator;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

public class TypeTableSorter extends ViewerSorter {
  
  private static final int ASCENDING = 0;

  private static final int DESCENDING = 1;

  private int column;

  private int direction;

  /**
   * Does the sort. If it's a different column from the previous sort, do an
   * ascending sort. If it's the same column as the last sort, toggle the sort
   * direction.
   * 
   * @param column
   */
  public void doSort(int column) {
    if (column == this.column) {
      // Same column as last sort; toggle the direction
      direction = 1 - direction;
    } else {
      // New column; do an ascending sort
      this.column = column;
      direction = ASCENDING;
    }
  }

  /**
   * Compares the object for sorting
   */
  public int compare(Viewer viewer, Object e1, Object e2) {
    int rc = 0;
    TypeEvalData td1 = (TypeEvalData) e1;
    TypeEvalData td2 = (TypeEvalData) e2;
    
    

    
    switch(column) {
      case TypeEvalTableConst.COLUMN_TYPE_NAME:
        rc = td1.getTypeName().compareTo(td2.getTypeName());
        break;
        
      case TypeEvalTableConst.COLUMN_TRUE_POSITIVES:
        rc = td1.getTruePositives() - td2.getTruePositives();
        
        break;
      case TypeEvalTableConst.COLUMN_FALSE_POSITIVES:
        rc = td1.getFalsePositives() - td2.getFalsePositives();
        break;
      case TypeEvalTableConst.COLUMN_FALSE_NEGATIVES:
        rc = td1.getFalseNegatives() - td2.getFalseNegatives();
        break;
      case TypeEvalTableConst.COLUMN_PRECISION:
        rc = Double.compare(td1.getPrecision() , td2.getPrecision());
        
      case TypeEvalTableConst.COLUMN_RECALL:
        rc = Double.compare(td1.getRecall() , td2.getRecall());
        break;
      case TypeEvalTableConst.COLUMN_F1:
        rc = Double.compare(td1.getFOne() , td2.getFOne());;
        break;
    }
    
    if (direction == DESCENDING)
      rc = -rc;

    return rc;    
    
    
//    int rc = 0;
//    Player p1 = (Player) e1;
//    Player p2 = (Player) e2;
//
//    // Determine which column and do the appropriate sort
//    switch (column) {
//    case PlayerConst.COLUMN_FIRST_NAME:
//      rc = collator.compare(p1.getFirstName(), p2.getFirstName());
//      break;
//    case PlayerConst.COLUMN_LAST_NAME:
//      rc = collator.compare(p1.getLastName(), p2.getLastName());
//      break;
//    case PlayerConst.COLUMN_POINTS:
//      rc = p1.getPoints() > p2.getPoints() ? 1 : -1;
//      break;
//    case PlayerConst.COLUMN_REBOUNDS:
//      rc = p1.getRebounds() > p2.getRebounds() ? 1 : -1;
//      break;
//    case PlayerConst.COLUMN_ASSISTS:
//      rc = p1.getAssists() > p2.getAssists() ? 1 : -1;
//      break;
//    }
//
//    // If descending order, flip the direction
//    if (direction == DESCENDING)
//      rc = -rc;
//
//    return rc;
  }

}
