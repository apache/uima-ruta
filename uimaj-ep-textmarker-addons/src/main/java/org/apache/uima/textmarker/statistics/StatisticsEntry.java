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

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.DoubleArrayFSImpl;
import org.apache.uima.cas.impl.IntArrayFSImpl;
import org.apache.uima.cas.impl.StringArrayFSImpl;

public class StatisticsEntry {
  private static final String PARTS = "part";

  private static final String AMOUNTS = "amount";

  private static final String TOTALS = "total";

  private static final String NAMES = "name";

  private static final String TYPE = "org.apache.uima.textmarker.type.Statistics";

  private final String name;

  private final double total;

  private final int amount;

  private final double part;

  public StatisticsEntry(String name, double total, int amount, double part) {
    super();
    this.name = name;
    this.total = total;
    this.amount = amount;
    this.part = part;

  }

  public static List<StatisticsEntry> createEntries(FeatureStructure fs) {
    List<StatisticsEntry> result = new ArrayList<StatisticsEntry>();
    Type type = fs.getCAS().getTypeSystem().getType(TYPE);
    Feature fname = type.getFeatureByBaseName(NAMES);
    Feature ftotal = type.getFeatureByBaseName(TOTALS);
    Feature famount = type.getFeatureByBaseName(AMOUNTS);
    Feature fparts = type.getFeatureByBaseName(PARTS);
    StringArrayFSImpl vname = (StringArrayFSImpl) fs.getFeatureValue(fname);
    DoubleArrayFSImpl vtotal = (DoubleArrayFSImpl) fs.getFeatureValue(ftotal);
    IntArrayFSImpl vamount = (IntArrayFSImpl) fs.getFeatureValue(famount);
    DoubleArrayFSImpl vpart = (DoubleArrayFSImpl) fs.getFeatureValue(fparts);

    String[] narray = vname.toArray();
    double[] tarray = vtotal.toArray();
    int[] aarray = vamount.toArray();
    double[] parray = vpart.toArray();

    int i = 0;
    for (String each : narray) {
      result.add(new StatisticsEntry(each, tarray[i], aarray[i], parray[i]));
      i++;
    }

    return result;
  }

  public double getPart() {
    return part;
  }

  public int getAmount() {
    return amount;
  }

  public double getTotal() {
    return total;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return getName() + " : " + getTotal() + " : " + getAmount() + " : " + getPart();
  }
}
