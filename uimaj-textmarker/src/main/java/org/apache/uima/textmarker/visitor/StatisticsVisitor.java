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

package org.apache.uima.textmarker.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.DoubleArray;
import org.apache.uima.jcas.cas.IntegerArray;
import org.apache.uima.jcas.cas.StringArray;
import org.apache.uima.textmarker.ScriptApply;
import org.apache.uima.textmarker.TextMarkerElement;
import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.action.AbstractTextMarkerAction;
import org.apache.uima.textmarker.condition.AbstractTextMarkerCondition;
import org.apache.uima.textmarker.type.Statistics;
import org.apache.uima.textmarker.utils.UIMAUtils;
import org.apache.uima.textmarker.verbalize.TextMarkerVerbalizer;

public class StatisticsVisitor implements TextMarkerInferenceVisitor {

  private Map<String, Long> conditionTime;

  private Map<String, Long> actionTime;

  private Map<String, Integer> conditionAmount;

  private Map<String, Integer> actionAmount;

  private Map<String, Long> conditionDelta;

  private Map<String, Long> actionDelta;

  private TextMarkerVerbalizer verbalizer;

  public StatisticsVisitor(TextMarkerVerbalizer verbalizer) {
    super();
    this.verbalizer = verbalizer;
    conditionTime = new HashMap<String, Long>();
    actionTime = new HashMap<String, Long>();
    conditionAmount = new HashMap<String, Integer>();
    actionAmount = new HashMap<String, Integer>();
    conditionDelta = new HashMap<String, Long>();
    actionDelta = new HashMap<String, Long>();
  }

  public void beginVisit(TextMarkerElement element, ScriptApply result) {
    if (element instanceof AbstractTextMarkerCondition) {
      AbstractTextMarkerCondition c = (AbstractTextMarkerCondition) element;
      String name = verbalizer.verbalizeName(c);
      Integer amount = conditionAmount.get(name);
      if (amount == null)
        amount = 0;
      amount++;
      conditionAmount.put(name, amount);
      conditionDelta.put(name, System.currentTimeMillis());
    } else if (element instanceof AbstractTextMarkerAction) {
      AbstractTextMarkerAction a = (AbstractTextMarkerAction) element;
      String name = verbalizer.verbalizeName(a);
      Integer amount = actionAmount.get(name);
      if (amount == null)
        amount = 0;
      amount++;
      actionAmount.put(name, amount);
      actionDelta.put(name, System.currentTimeMillis());
    }
  }

  public void endVisit(TextMarkerElement element, ScriptApply result) {
    if (element instanceof AbstractTextMarkerCondition) {
      AbstractTextMarkerCondition c = (AbstractTextMarkerCondition) element;
      String name = verbalizer.verbalizeName(c);
      Long start = conditionDelta.get(name);
      long delta = System.currentTimeMillis() - start;
      Long total = conditionTime.get(name);
      if (total == null)
        total = 0L;
      total += delta;
      conditionTime.put(name, total);
    } else if (element instanceof AbstractTextMarkerAction) {
      AbstractTextMarkerAction a = (AbstractTextMarkerAction) element;
      String name = verbalizer.verbalizeName(a);
      Long start = actionDelta.get(name);
      long delta = System.currentTimeMillis() - start;
      Long total = actionTime.get(name);
      if (total == null)
        total = 0L;
      total += delta;
      actionTime.put(name, total);
    }
  }

  public void finished(TextMarkerStream stream, List<TextMarkerInferenceVisitor> visitors) {
    List<String> names = new ArrayList<String>();
    List<Double> totals = new ArrayList<Double>();
    List<Integer> amounts = new ArrayList<Integer>();
    List<Double> parts = new ArrayList<Double>();
    for (String each : conditionTime.keySet()) {
      double total = conditionTime.get(each);
      double amount = conditionAmount.get(each);
      double part = total / amount;
      part *= 10000;
      part = Math.round(part);
      part /= 10000;
      names.add(each);
      totals.add(total);
      amounts.add((int) amount);
      parts.add(part);
    }

    for (String each : actionTime.keySet()) {
      double total = actionTime.get(each);
      double amount = actionAmount.get(each);
      double part = total / amount;
      part *= 10000;
      part = Math.round(part);
      part /= 10000;
      names.add(each);
      totals.add(total);
      amounts.add((int) amount);
      parts.add(part);
    }
    JCas jCas = stream.getJCas();
    StringArray nameArray = UIMAUtils.toStringArray(jCas, names.toArray(new String[] {}));
    DoubleArray totalArray = UIMAUtils.toDoubleArray(jCas, getDoubleArray(totals));
    IntegerArray amountArray = UIMAUtils.toIntegerArray(jCas, getIntegerArray(amounts));
    DoubleArray partArray = UIMAUtils.toDoubleArray(jCas, getDoubleArray(parts));

    Type type = jCas.getCasType(Statistics.type);
    Feature fname = type.getFeatureByBaseName("name");
    Feature ftotal = type.getFeatureByBaseName("total");
    Feature famount = type.getFeatureByBaseName("amount");
    Feature fparts = type.getFeatureByBaseName("part");
    FeatureStructure fs = jCas.getCas().createFS(type);
    fs.setFeatureValue(fname, nameArray);
    fs.setFeatureValue(ftotal, totalArray);
    fs.setFeatureValue(famount, amountArray);
    fs.setFeatureValue(fparts, partArray);
    jCas.getCas().addFsToIndexes(fs);
  }

  private int[] getIntegerArray(List<Integer> array) {
    int[] result = new int[array.size()];
    int i = 0;
    for (Integer each : array) {
      result[i++] = each;
    }
    return result;
  }

  private double[] getDoubleArray(List<Double> array) {
    double[] result = new double[array.size()];
    int i = 0;
    for (Double each : array) {
      result[i++] = each;
    }
    return result;
  }

  public Map<String, Long> getConditionTime() {
    return conditionTime;
  }

  public Map<String, Long> getActionTime() {
    return actionTime;
  }

  public Map<String, Integer> getConditionAmount() {
    return conditionAmount;
  }

  public Map<String, Integer> getActionAmount() {
    return actionAmount;
  }

}
