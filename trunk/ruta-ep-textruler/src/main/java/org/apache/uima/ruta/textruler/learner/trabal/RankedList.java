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

package org.apache.uima.ruta.textruler.learner.trabal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankedList extends ArrayList<Condition> {

  private static final long serialVersionUID = -5978107285325156323L;

  Map<Condition, Double> ranking;

  Map<String, Double> idf;

  public RankedList(Map<String, Double> i) {
    super();
    ranking = new HashMap<Condition, Double>();
    idf = i;
  }

  public RankedList(Collection<? extends Condition> c, Map<String, Double> i) {
    super();
    ranking = new HashMap<Condition, Double>();
    idf = i;
    addAll(c);
  }

  /**
   * Adds the item to the list and increases its ranking by one.
   * 
   * @return true, if the item was newly inserted into the list.
   */
  public boolean add(Condition e) {
    boolean result;
    double value;
    if (idf.containsKey(e.getItem().getAnnotation().getType().getShortName()))
      value = idf.get(e.getItem().getAnnotation().getType().getShortName());
    else
      value = 1.0;
    if (ranking.containsKey(e)) {
      Double rank = ranking.get(e);
      rank = new Double(rank.doubleValue() + value);
      ranking.put(e, rank);
      result = false;
    } else {
      super.add(e);
      ranking.put(e, new Double(value));
      result = true;
    }
    return result;
  }

  /**
   * Adds the item to the list and increases its ranking by the given number.
   * 
   */
  public void add(double index, Condition e) {
    if (ranking.containsKey(e)) {
      Double rank = ranking.get(e);
      rank = new Double(rank.doubleValue() + index);
      ranking.put(e, rank);
    } else {
      super.add(e);
      ranking.put(e, new Double(index));
    }
  }

  /**
   * Adds the items to the ranked list and increases their ranking by their value in the given list.
   * 
   */
  public void addAll(RankedList list) {
    for (Condition each : list) {
      add(list.rankingOf(each), each);
    }
  }

  /**
   * Adds the items to the list and increases their ranking by one.
   * 
   */
  public boolean addAll(Collection<? extends Condition> c) {
    for (Condition each : c) {
      add(each);
    }
    return true;
  }

  /**
   * Adds the items to the list and increases their ranking by the given number.
   * 
   */
  public boolean addAll(double index, Collection<? extends Condition> c) {
    for (Condition each : c) {
      add(index, each);
    }
    return true;
  }

  public Condition remove(int index) {
    Condition element = super.get(index);
    if (element != null) {
      if (ranking.containsKey(element)) {
        ranking.remove(element);
        super.remove(index);
        return element;
      }
    }
    return null;
  }

  public boolean remove(Object o) {
    if (size() > 0) {
      if (contains(o) && ranking.containsKey(o)) {
        super.remove(o);
        ranking.remove(o);
        return true;
      }
    }
    return false;
  }

  public List<Condition> subList(int start, int end) {
    return super.subList(start, end);
  }

  public boolean contains(Object o) {
    return super.contains(o);
  }

  public void clear() {
    super.clear();
    ranking.clear();
  }

  public int size() {
    return super.size();
  }

  public RankedList clone() {
    RankedList clone = new RankedList(idf);
    for (Condition element : subList(0, size())) {
      clone.add(rankingOf(element), element.clone());
    }
    return clone;
  }

  public Condition get(int i) {
    return super.get(i);
  }

  /**
   * Returns the ranking of the given object.
   * 
   * @param each
   * @return ranking
   */
  public double rankingOf(Condition each) {
    if (contains(each)) {
      return ranking.get(each).doubleValue();
    }
    return 0;
  }

  public Map<Condition, Double> getRanking() {
    return ranking;
  }

  /**
   * Sorts the elements of the list in order of its ranking.
   */
  public void sort() {
    List<Condition> newList = new ArrayList<Condition>();
    for (int i = 0; i < size(); i++) {
      for (int j = 0; j < newList.size(); j++) {
        if (ranking.get(get(i)).doubleValue() >= ranking.get(newList.get(j)).doubleValue()) {
          newList.add(j, get(i));
          break;
        }
      }
      if (!newList.contains(get(i)))
        newList.add(get(i));
    }
    super.clear();
    super.addAll(newList);
  }

  /**
   * Unites the lists and adds their values for common elements.
   * 
   * @param list
   *          A list of the same type.
   */
  public RankedList unite(RankedList list) {
    RankedList clone = clone();
    for (Condition element : list.subList(0, list.size())) {
      clone.add(list.rankingOf(element), element.clone());
    }
    clone.sort();
    return clone;
  }

  /**
   * Removes all elements that are not part of both lists. Also adds the values of elements that
   * occur in both lists.
   * 
   * @param list
   *          A list of the same type.
   */
  public RankedList cut(RankedList list) {
    RankedList clone = clone();
    for (Condition element : subList(0, size())) {
      if (list.contains(element)) {
        clone.add(list.rankingOf(element), element.clone());
      } else {
        clone.remove(element);
      }
    }
    clone.sort();
    return clone;
  }

  /**
   * Removes all elements of the given list that occur in this list. Also adds the values of
   * elements that occur in both lists.
   * 
   * @param list
   *          A list of the same type.
   */
  public RankedList subtract(RankedList list) {
    RankedList clone = clone();
    for (Condition element : subList(0, size())) {
      if (list.contains(element)) {
        clone.remove(element);
      } else {
        clone.add(list.rankingOf(element), element.clone());
      }
    }
    clone.sort();
    return clone;
  }

  @Override
  public Condition set(int index, Condition element) {
    if (size() >= index) {
      double value = ranking.get(get(index));
      if (contains(element)) {
        ranking.put(element, new Double(ranking.get(element).doubleValue() + index));
        super.remove(element);
      } else
        ranking.put(element, value);
      ranking.remove(get(index));
    }
    return super.set(index, element);
  }

  /**
   * Adds the element to the list and assigns the ranking of the element located at the index. If
   * the element already exists, it is only assigned the ranking value.
   */
  @Override
  public void add(int index, Condition element) {
    if (size() >= index) {
      double value = ranking.get(get(index));
      if (!contains(element)) {
        ranking.put(element, value);
        super.add(index, element);
      }
    }
  }

  @Override
  public boolean addAll(int index, Collection<? extends Condition> c) {
    if (size() >= index) {
      double value = ranking.get(get(index));
      for (Condition element : c) {
        if (!contains(element)) {
          ranking.put(element, value);
          super.add(index, element);
          return true;
        }
      }
    }
    return false;
  }

  @Override
  protected void removeRange(int fromIndex, int toIndex) {
    for (int i = fromIndex; i < toIndex; i++) {
      ranking.remove(get(i));
    }
    super.removeRange(fromIndex, toIndex);
  }

}
