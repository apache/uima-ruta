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

package org.apache.uima.ruta.cde.utils;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

import org.eclipse.swt.widgets.TableColumn;

public class CDEComparatorFactory {

  private int updown;

  public CDEComparatorFactory() {
    updown = 1;
  }

  private Comparator<DocumentData> stringDocumentComparator = new Comparator<DocumentData>() {

    public int compare(DocumentData object1, DocumentData object2) {
      Collator collator = Collator.getInstance();

      String docName1 = object1.getDocument().getName();
      String docName2 = object2.getDocument().getName();

      int result = collator.compare(docName1, docName2) * updown;
      return result;
    }
  };

  private Comparator<DocumentData> resultComparator = new Comparator<DocumentData>() {

    public int compare(DocumentData object1, DocumentData object2) {

      double augment1 = object1.getAugmentedResult();
      double augment2 = object2.getAugmentedResult();

      if (augment1 <= augment2) {
        return 1 * updown;
      } else {
        return -1 * updown;
      }

    }
  };

  private Comparator<DocumentData> f1ScoreComparator = new Comparator<DocumentData>() {

    public int compare(DocumentData object1, DocumentData object2) {

      double augment1 = object1.getFMeasure();
      double augment2 = object2.getFMeasure();

      if (augment1 <= augment2) {
        return 1 * updown;
      } else {
        return -1 * updown;
      }

    }
  };

  private Comparator<ConstraintData> stringConstraintComparator = new Comparator<ConstraintData>() {

    public int compare(ConstraintData object1, ConstraintData object2) {
      Collator collator = Collator.getInstance();

      String constraintName1 = object1.getDescription();
      String constraintName2 = object2.getDescription();

      int result = collator.compare(constraintName1, constraintName2) * updown;
      return result;
    }

  };

  private Comparator<ConstraintData> intConstraintComparator = new Comparator<ConstraintData>() {

    public int compare(ConstraintData object1, ConstraintData object2) {
      ;

      int weight1 = object1.getWeight();
      int weight2 = object2.getWeight();

      return (weight1 - weight2) * updown;
    }

  };

  private Comparator<String[]> resultNameComparator = new Comparator<String[]>() {
    public int compare(String[] object1, String[] object2) {

      Collator collator = Collator.getInstance(Locale.getDefault());
      int result = collator.compare(object1[0], object2[0]) * updown;
      return result;
    }
  };

  private Comparator<String[]> resultValueComparator = new Comparator<String[]>() {
    public int compare(String[] object1, String[] object2) {
      double result1 = Double.valueOf(object1[1]);
      double result2 = Double.valueOf(object2[1]);
      if (result1 >= result2)
        return 1 * updown;
      else {
        return -1 * updown;
      }
    }
  };

  public Comparator getComparator(TableColumn tc) {
    this.updown = updown * (-1);
    String columnName = tc.getText();

    if (columnName.equals("CDE")) {
      return resultComparator;
    }
    if (columnName.equals("F1")) {
      return f1ScoreComparator;
    } else if (columnName.equals("Document")) {
      return stringDocumentComparator;
    } else if (columnName.equals("Constraint")) {
      return stringConstraintComparator;
    } else if (columnName.equals("Weight")) {
      return intConstraintComparator;
    } else if (columnName.equals("Constraint ")) {
      return resultNameComparator;
    } else if (columnName.equals("Result")) {
      return resultValueComparator;
    }
    return Collator.getInstance();
  }

}
