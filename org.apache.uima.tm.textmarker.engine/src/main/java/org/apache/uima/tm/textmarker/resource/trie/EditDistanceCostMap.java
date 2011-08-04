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

package org.apache.uima.tm.textmarker.resource.trie;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Class EditDistanceMap.
 * 
 * Contains HashMaps with key-value-pairs representing the costs for edit distance operations.
 */
public class EditDistanceCostMap {

  /** Contains the insert costs for given characters. */
  private Map<Character, Double> insertMap;

  /** Contains the delete costs for given characters. */
  private Map<Character, Double> deleteMap;

  /** Contains hashmaps with replace costs for certain characters. */
  private Map<Character, Map<Character, Double>> replaceMap;

  private Double minimalCosts;

  /**
   * Represents the default costs if a character is not in a hashmap for an operation.
   */
  private double defaultCosts;

  /**
   * The default constructor initializes the hashmaps empty and sets the default costs to one.
   */
  public EditDistanceCostMap() {
    this(1.0);
  }

  /**
   * Initializes the default costs an the hashmaps.
   * 
   * @param defaultCosts
   *          The default costs for any operation with any character (pair).
   */
  public EditDistanceCostMap(double defaultCosts) {
    this(defaultCosts, new HashMap<Character, Double>(), new HashMap<Character, Double>(),
            new HashMap<Character, Map<Character, Double>>());
  }

  /**
   * Initializes all attributes of the class.
   * 
   * @param defaultCosts
   *          The default costs for any operation with any character (pair).
   * @param insertMap
   *          The map with the insert costs.
   * @param deleteMap
   *          The map with the delete costs.
   * @param replaceMap
   *          The map with the replace costs.
   */
  public EditDistanceCostMap(double defaultCosts, Map<Character, Double> insertMap,
          Map<Character, Double> deleteMap, Map<Character, Map<Character, Double>> replaceMap) {
    this.defaultCosts = defaultCosts;
    this.minimalCosts = defaultCosts;
    this.deleteMap = deleteMap;
    this.insertMap = insertMap;
    this.replaceMap = replaceMap;
  }

  /**
   * Exports the EditDistanceCostMap to a file with the specified Name in .csv-Format.
   * 
   * @param file
   *          The name of the .csv-file.
   * @return true, if the export worked, false otherwise.
   */
  public boolean export(String file) {

    try {
      BufferedWriter br = new BufferedWriter(new FileWriter(new File(file)));

      br.write("\"defaultcosts\";\"" + defaultCosts + "\";\n");

      for (Character c : insertMap.keySet()) {
        br.write("\"insert\";\"" + c + "\";\"" + getInsertCosts(c) + "\";\n");
      }

      for (Character c : deleteMap.keySet()) {
        br.write("\"delete\";\"" + c + "\";\"" + getDeleteCosts(c) + "\";\n");
      }

      for (Character c : replaceMap.keySet()) {

        Map<Character, Double> replace = replaceMap.get(c);

        for (Character d : replace.keySet()) {
          br.write("\"replace\";\"" + c + "\";\"" + d + "\";\"" + getReplaceCosts(c, d) + "\";\n");
        }
      }

      br.flush();
      br.close();

    } catch (IOException e) {
      return false;
    }

    return true;
  }

  /**
   * Reads an EditDistanceCostMap from a .csv-file with the specified name. The method tries zu
   * catch any exceptions and continues the import.
   * 
   * @param file
   *          The file which is to be imported / read.
   * @return true, if not any one exception occured, false otherwise. If false is returned, the file
   *         hasn't been read completely. The line with the exception was not imported.
   */
  public boolean read(String file) {

    BufferedReader br = null;
    String temp = null;
    String[] line = null;
    boolean retValue = true;

    insertMap.clear();
    deleteMap.clear();
    replaceMap.clear();

    try {
      br = new BufferedReader(new FileReader(new File(file)));
    } catch (FileNotFoundException e) {
      System.err.println("File not found.");
      return false;
    }

    try {

      while ((temp = br.readLine()) != null) {

        line = temp.split(";");

        try {

          // Kills the " at the beginning and at the end of the
          // String.
          for (int i = 0; i < line.length; i++) {

            if (line[i].startsWith("\"")) {
              line[i] = line[i].substring(1);
            }

            if (line[i].endsWith("\"")) {
              line[i] = line[i].substring(0, line[i].length() - 1);
            }
          }

          if (line[0].equals("insert") || line[0].equals("delete")) {

            line[2] = line[2].replaceAll(",", ".");
            double value = Double.parseDouble(line[2]);
            char[] cArray = line[1].toCharArray();

            // The "strings" should have just one character.
            if (cArray.length != 1) {
              System.err.println("Invalid format.");
              retValue = false;
              continue;
            }

            if (line[0].equals("delete")) {
              setDeleteCosts(cArray[0], value);
            } else {
              setInsertCosts(cArray[0], value);
            }

          } else if (line[0].equals("replace")) {

            line[3] = line[3].replaceAll(",", ".");
            double value = Double.parseDouble(line[3]);
            char[] cArray = line[1].toCharArray();
            char[] dArray = line[2].toCharArray();

            if (cArray.length != 1 || dArray.length != 1) {
              System.err.println("Invalid format.");
              retValue = false;
              continue;
            }

            setReplaceCosts(cArray[0], dArray[0], value);

          } else if (line[0].equals("defaultcosts")) {

            line[1] = line[1].replaceAll(",", ".");
            double value = Double.parseDouble(line[1]);
            defaultCosts = value;

          } else {
            System.err.println("Invalid EditDistance" + "CostMap-Operation.");
            retValue = false;
          }

        } catch (StringIndexOutOfBoundsException e) {
          System.err.println("String Index out of bounds.");
          retValue = false;
        } catch (IndexOutOfBoundsException e) {
          System.err.println("Index out of bounds.");
          retValue = false;
        } catch (NumberFormatException e) {
          System.err.println("Number Format Error.");
          retValue = false;
        }
      }

      br.close();

    } catch (IOException e) {
      System.err.println("IO-Error.");
      return false;
    }

    registerMinimalCosts();

    return retValue;
  }

  /**
   * Retrieves the insert costs for the specified character.
   * 
   * @param c
   *          The character which insert costs should be retrieved.
   * @return The insert costs of the character.
   */
  public double getInsertCosts(char c) {

    if (insertMap.get(c) == null) {
      return defaultCosts;
    }

    return insertMap.get(c);
  }

  /**
   * Retrieves the delete costs for the specified character.
   * 
   * @param c
   *          The character which delete costs should be retrieved.
   * @return The delete costs of the character.
   */
  public double getDeleteCosts(char c) {

    if (deleteMap.get(c) == null) {
      return defaultCosts;
    }

    return deleteMap.get(c);
  }

  /**
   * Retrieves the replace costs for the specified pair of characters.
   * 
   * @param c
   *          The first specified character.
   * @param d
   *          The second specified character.
   * @return The replace costs for the pair of characters.
   */
  public double getReplaceCosts(char c, char d) {

    if (replaceMap.get(c) == null) {
      return defaultCosts;
    }

    Map<Character, Double> cMap = replaceMap.get(c);

    if (cMap.get(d) == null) {
      return defaultCosts;
    }

    return cMap.get(d);
  }

  /**
   * Sets the insert costs of the specified character to the specified value.
   * 
   * @param c
   *          The specified character.
   * @param value
   *          The specified value.
   */
  public void setInsertCosts(char c, double value) {
    insertMap.put(c, value);
    registerMinimalCosts();
  }

  /**
   * Sets the delete costs of the specified character to the specified value.
   * 
   * @param c
   *          The specified character.
   * @param value
   *          The specified value.
   */
  public void setDeleteCosts(char c, double value) {
    deleteMap.put(c, value);
    registerMinimalCosts();
  }

  /**
   * Sets the replace costs of the specified pair of characters to the specified value.
   * 
   * @param c
   *          The first specified character.
   * @param d
   *          The second specified character.
   * @param value
   *          The specified value.
   */
  public void setReplaceCosts(char c, char d, double value) {

    if (replaceMap.get(c) == null) {
      replaceMap.put(c, new HashMap<Character, Double>());
    }

    replaceMap.get(c).put(d, value);
    registerMinimalCosts();
  }

  private void registerMinimalCosts() {

    for (Double d : deleteMap.values()) {
      minimalCosts = Math.min(minimalCosts, d);
    }

    for (Double d : insertMap.values()) {
      minimalCosts = Math.min(minimalCosts, d);
    }

    for (Map<Character, Double> tmp : replaceMap.values()) {
      for (Double d : tmp.values()) {
        minimalCosts = Math.min(minimalCosts, d);
      }
    }
  }

  public double getMinimalCosts() {
    return minimalCosts;
  }

  public double getDefaultCosts() {
    return defaultCosts;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    long temp;
    temp = Double.doubleToLongBits(defaultCosts);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    result = prime * result + ((deleteMap == null) ? 0 : deleteMap.hashCode());
    result = prime * result + ((insertMap == null) ? 0 : insertMap.hashCode());
    result = prime * result + ((replaceMap == null) ? 0 : replaceMap.hashCode());
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
    EditDistanceCostMap other = (EditDistanceCostMap) obj;
    if (Double.doubleToLongBits(defaultCosts) != Double.doubleToLongBits(other.defaultCosts))
      return false;
    if (deleteMap == null) {
      if (other.deleteMap != null)
        return false;
    } else if (!deleteMap.equals(other.deleteMap))
      return false;
    if (insertMap == null) {
      if (other.insertMap != null)
        return false;
    } else if (!insertMap.equals(other.insertMap))
      return false;
    if (replaceMap == null) {
      if (other.replaceMap != null)
        return false;
    } else if (!replaceMap.equals(other.replaceMap))
      return false;
    return true;
  }
}
