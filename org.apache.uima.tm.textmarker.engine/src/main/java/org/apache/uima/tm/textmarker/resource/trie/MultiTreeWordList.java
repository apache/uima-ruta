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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;
import org.apache.uima.tm.textmarker.resource.TextMarkerWordList;


/**
 * Class MultiTreeWordList.
 * 
 * @author Daniel Wieth, 1570292
 * 
 */
public class MultiTreeWordList implements TextMarkerWordList {

  private static final String ENCODING = "UTF-8";

  private MultiTreeWordListPersistence persistence = new MultiTreeWordListPersistence();;

  /** The root of the TreeWordList. */
  protected MultiTextNode root;

  /** The cost model we are using. */
  private EditDistanceCostMap costMap;

  /**
   * Default constructor.
   */
  public MultiTreeWordList() {
    this(new String[] {});
  }

  /**
   * Default constructor uses just one file.
   * 
   * @param pathname
   *          the pathname of the used file.
   */
  public MultiTreeWordList(String pathname) {

    this.root = new MultiTextNode();
    this.costMap = new EditDistanceCostMap();
    File directory = new File(pathname);

    if (!directory.isDirectory()) {
      if (directory.getName().endsWith(".txt")) {
        buildNewTree(directory.getAbsolutePath());
      }
      if (directory.getName().endsWith(".mtwl")) {
        persistence.readMTWL(root, directory.getAbsolutePath());
      }
      return;
    }

    File[] listFiles = directory.listFiles();

    for (File data : listFiles) {
      if (data.getName().endsWith(".txt")) {
        buildNewTree(data.getAbsolutePath());
      }
      if (data.getName().endsWith(".mtwl")) {
        persistence.readMTWL(root, data.getAbsolutePath());
      }
    }
  }

  /**
   * Constructs a TreeWordList from a file with path = filename
   * 
   * @param filename
   *          path of the file to create a TextWordList from
   */
  public MultiTreeWordList(String[] pathnames) {

    this.root = new MultiTextNode();
    this.costMap = new EditDistanceCostMap();

    for (String pathname : pathnames) {

      if (pathname.endsWith(".mtwl")) {
        persistence.readMTWL(root, pathname);
      }
      if (pathname.endsWith(".txt")) {
        buildNewTree(pathname);
      }
    }
  }

  /**
   * Creates a new Tree in the existing treeWordList from a file with path pathname
   * 
   * @param pathname
   *          Absolut path of the file containing the word for the treeWordList
   */
  public void buildNewTree(String pathname) {

    try {
      File f = new File(pathname);
      FileInputStream fstream = new FileInputStream(f);
      BufferedReader br = new BufferedReader(new InputStreamReader(fstream, ENCODING));
      String s = null;

      while ((s = br.readLine()) != null) {
        addWord(s.trim(), f.getName());
      }
      fstream.close();
      br.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Add a new String into the MultiTreeWordList.
   * 
   * @param s
   *          The String to add
   * @param type
   *          The type of the string.
   */
  public void addWord(String s, String type) {

    // Create Nodes from all chars of the strings besides the last one
    MultiTextNode pointer = root;

    for (Character each : s.toCharArray()) {

      MultiTextNode childNode = pointer.getChildNode(each);

      if (childNode == null) {
        childNode = new MultiTextNode(each, false);
        pointer.addChild(childNode);
      }

      pointer = childNode;
    }
    pointer.setWordEnd(s.length() > 0);
    pointer.addType(type);
  }

  /**
   * Returns all Types contained by the MultiTreeWordList.
   * 
   * @return all Types contained by the MultiTreeWordList.
   */
  public Collection<String> getTypes() {
    return getTypeCone(root);
  }

  /**
   * Returns all types contained by the cone of the MultiTextNode node, including the types of node
   * itself.
   * 
   * @param node
   *          The node where we start, the root of the cone.
   * @return all types contained by the cone of the MultiTextNode node, including the types of node
   *         itself.
   */
  public Collection<String> getTypeCone(MultiTextNode node) {

    List<String> returnList = new LinkedList<String>();

    if (node.getTypes() != null) {
      for (String s : node.getTypes()) {
        if (!returnList.contains(s)) {
          returnList.add(s);
        }
      }
    }

    for (Character c : node.getChildren().keySet()) {
      for (String s : getTypeCone(node.getChildNode(c))) {
        if (!returnList.contains(s)) {
          returnList.add(s);
        }
      }
    }

    return returnList;
  }

  /**
   * Returns all strings contained by the MultiTreeWordList.
   * 
   * @return All strings contained by the MultiTreeWordList.
   */
  public Collection<String> keySet() {
    List<String> keySet = new LinkedList<String>(keySet(root, ""));
    Collections.sort(keySet);
    return keySet;
  }

  /**
   * Returns all strings contained by the cone of the MultiTextNode node and uses prefix as the
   * prefix of all the strings.
   * 
   * @param node
   *          the node we are considering.
   * @param prefix
   *          the prefix until now.
   * @return All strings contained by the cone of the MultiTextNode node.
   */
  private Collection<String> keySet(MultiTextNode node, String prefix) {

    List<String> resultList = new LinkedList<String>();

    // Recursion stop.
    if (node.isWordEnd()) {
      resultList.add(prefix);
    }

    // Recursion step.
    for (Character c : node.getChildren().keySet()) {
      String temp = prefix + String.valueOf(c);
      resultList.addAll(keySet(node.getChildNode(c), temp));
    }

    return resultList;
  }

  /**
   * Returns all types of the very string s.
   * 
   * @param s
   *          The string with the types.
   * @return All types from the very string s.
   */
  public Collection<String> getTypes(String s) {
    return getTypes(s, false);
  }

  /**
   * Returns the types of the string s.
   * 
   * @param s
   *          The string with the types.
   * @param ignoreCase
   *          Indicates, whether we search case sensitive or not.
   * @return The types of the string s.
   */
  public Collection<String> getTypes(String s, boolean ignoreCase) {

    // Collection<Set<String>> types = editDistanceClever(root, s, "", 0.0,
    // 0,
    // ignoreCase, false, costMap, false, false).values();
    // Map<String, Set<String>> types = editDistanceClever(root, s, "", 0.0,
    // 0, ignoreCase, false, costMap, false, false);
    Map<String, Set<String>> types = editDistance(s, 0, ignoreCase, "");
    Set<String> returnSet = new HashSet<String>();

    for (Entry<String, Set<String>> each : types.entrySet()) {
      returnSet.addAll(each.getValue());
    }

    return returnSet;
  }

  /**
   * Returns a list of types which belong to a string.
   * 
   * @param string
   *          The string which types we want to have.
   * @param ignoreCase
   *          Indicates whether we search case sensitive or not.
   * @param ignoreLength
   *          If the length of the string is less than of equal to this, we search case insensitive.
   * @param edit
   *          Indicates whether we use an edit distance or not.
   * @param distance
   *          The edit distance to a string contained by the MultiTreeWordList.
   * @param ignoreToken
   *          Characters which can be ignored.
   * @return Returns a list of types which belong to a string.
   */

  public List<String> contains(String string, boolean ignoreCase, int ignoreLength, boolean edit,
          double distance, String ignoreToken) {

    List<String> resultList = new LinkedList<String>();
    Map<String, Set<String>> editDistance;

    if (string.length() >= ignoreLength && ignoreCase) {
      editDistance = editDistance(string, (int) distance, true, ignoreToken, false);
    } else {
      editDistance = editDistance(string, (int) distance, false, ignoreToken, false);
    }
    for (Entry<String, Set<String>> each : editDistance.entrySet()) {
      resultList.addAll(each.getValue());
    }
    return resultList;
  }

  /**
   * Checks whether a string is contained by the MultiTreeWordList or not.
   * 
   * @param string
   *          The string which is contained or not.
   * @param ignoreCase
   *          Indicates whether we search case sensitive or not.
   * @param ignoreLength
   *          If the length of the string is less than of equal to this, we search case insensitive.
   * @param edit
   *          Indicates whether we use an edit distance or not.
   * @param distance
   *          The edit distance to a string contained by the MultiTreeWordList.
   * @param ignoreToken
   *          Characters which can be ignored.
   * @return true, if the string is contained by the MultiTreeWordList, false otherwise.
   */
  public boolean containsBool(String string, boolean ignoreCase, int ignoreLength, boolean edit,
          double distance, String ignoreToken) {
    return editDistanceBool(root, string, "", distance, 0, ignoreCase, false, costMap);
  }

  /**
   * Checks whether the tree contains exaclty the string s.
   * 
   * @param s
   *          The string which is contained or not.
   * @return True, if the TreeWordList contains exactly the string s, false otherwise.
   */
  public boolean contains(String s) {
    return contains(s, false);
  }

  /**
   * Checks whether the tree contains the string s.
   * 
   * @param s
   *          The string which is contained or not.
   * @param ignoreCase
   *          Indicates whether we search case sensitive or not.
   * @return True, if the TreeWordList contains the string s, false otherwise.
   */
  public boolean contains(String s, boolean ignoreCase) {
    return contains(s, ignoreCase, 0, new char[] {}, 0);
  }

  /**
   * Checks if the MultiTreeWordList contains the string s.
   * 
   * @param s
   *          The string which is contained or not.
   * @param ignoreCase
   *          Indicates whether we search case sensitive or not.
   * @param size
   *          The index of the string.
   * @param ignoreChars
   *          Characters which can be ignored.
   * @param maxIgnoreChars
   *          The maximum number of ignored characters.
   * @return true, if TreeWordList contains the string, false otherwise.
   */
  public boolean contains(String s, boolean ignoreCase, int size, char[] ignoreChars,
          int maxIgnoreChars) {

    EditDistanceCostMap edm = new EditDistanceCostMap();

    for (Character c : ignoreChars) {
      edm.setDeleteCosts(c, 0.0);
    }

    return editDistanceBool(root, s, "", maxIgnoreChars, 0, ignoreCase, false, edm);
  }

  /**
   * Checks if the MultiTreeWordList contains a prefix of the string s.
   * 
   * @param s
   *          The string which is contained or not.
   * @param ignoreCase
   *          Indicates whether we search case sensitive or not.
   * @param size
   *          The index of the string.
   * @param ignoreChars
   *          Characters which can be ignored.
   * @param maxIgnoreChars
   *          The maximum number of ignored characters.
   * @return true, if TreeWordList contains a prefix of the string, false otherwise.
   */
  public boolean containsFragment(String s, boolean ignoreCase, int size, char[] ignoreChars,
          int maxIgnoreChars) {
    MultiTextNode pointer = root;
    return recursiveContains(pointer, s, 0, ignoreCase && s.length() > size, true, ignoreChars,
            maxIgnoreChars);
  }

  /**
   * Checks whether prefix of a string is contained by the MultiTreeWordList or not.
   * 
   * @param string
   *          The string whose prefix is contained or not.
   * @param ignoreCase
   *          Indicates whether we search case sensitive or not.
   * @param ignoreLength
   *          If the length of the string is less than of equal to this, we search case insensitive.
   * @param edit
   *          Indicates whether we use an edit distance or not.
   * @param distance
   *          The edit distance to a string contained by the MultiTreeWordList.
   * @param ignoreToken
   *          Characters which can be ignored.
   * @return true, if a prefix of the string is contained by the MultiTreeWordList, false otherwise.
   */
  public boolean containsFragmentBool(String string, boolean ignoreCase, int ignoreLength,
          boolean edit, double distance, String ignoreToken) {

    if (string.length() >= ignoreLength && ignoreCase) {
      return editDistanceBool(root, string, "", distance, 0, true, true, costMap);
    } else {
      return editDistanceBool(root, string, "", distance, 0, false, true, costMap);
    }
  }

  /**
   * Returns a list of types which belong to a prefix of a string that is contained by the
   * MultiTreeWordList.
   * 
   * @param string
   *          The string whose prefix's types we are interested in.
   * @param ignoreCase
   *          Indicates whether we search case sensitive or not.
   * @param ignoreLength
   *          If the length of the string is less than of equal to this, we search case insensitive.
   * @param edit
   *          Indicates whether we use an edit distance or not.
   * @param distance
   *          The edit distance to a string contained by the MultiTreeWordList.
   * @param ignoreToken
   *          Characters which can be ignored.
   * @return A list of types which belong to a prefix of a string that is contained by the
   *         MultiTreeWordList.
   */
  public List<String> containsFragment(String string, boolean ignoreCase, int ignoreLength,
          boolean edit, double distance, String ignoreToken) {

    List<String> resultList = new LinkedList<String>();
    Map<String, Set<String>> resultMap = null;

    if (string.length() >= ignoreLength && ignoreCase) {
      resultMap = editDistance(string, (int) distance, true, ignoreToken, true);
    } else {
      resultMap = editDistance(string, (int) distance, false, ignoreToken, true);
    }

    for (Set<String> set : resultMap.values()) {
      for (String s : set) {
        if (!resultList.contains(s)) {
          // resultList.addAll(resultMap.get(set));
          resultList.add(s);
        }
      }
    }

    return resultList;
  }

  /**
   * Returns true, if the MultiTreeWordList contains the string text, false otherwise.
   * 
   * @param pointer
   *          The MultiTextNode we are looking at.
   * @param text
   *          The string which is contained or not.
   * @param index
   *          The index of the string text we checked until now.
   * @param ignoreCase
   *          Indicates whether we search case sensitive or not.
   * @param fragment
   *          Indicates whether we are looking for a prefix of the string text.
   * @param ignoreChars
   *          Characters which can be ignored.
   * @param maxIgnoreChars
   *          Maximum number of characters which are allowed to be ignored.
   * @return True, if the TreeWordList contains the string text, false otherwise.
   */
  private boolean recursiveContains(MultiTextNode pointer, String text, int index,
          boolean ignoreCase, boolean fragment, char[] ignoreChars, int maxIgnoreChars) {

    if (pointer == null) {
      return false;
    }

    if (index == text.length()) {
      return fragment || pointer.isWordEnd();
    }

    char charAt = text.charAt(index);
    boolean charAtIgnored = false;

    if (ignoreChars != null) {
      for (char each : ignoreChars) {
        if (each == charAt) {
          charAtIgnored = true;
          break;
        }
      }
      charAtIgnored &= index != 0;
    }

    int next = ++index;

    if (ignoreCase) {

      // Lower Case Node.
      MultiTextNode childNodeL = pointer.getChildNode(Character.toLowerCase(charAt));

      // Upper Case Node.
      MultiTextNode childNodeU = pointer.getChildNode(Character.toUpperCase(charAt));

      if (charAtIgnored && childNodeL == null && childNodeU == null) {
        // Character is ignored and does not appear.
        return recursiveContains(pointer, text, next, ignoreCase, fragment, ignoreChars,
                maxIgnoreChars);
      } else {
        // Recursion.
        return recursiveContains(childNodeL, text, next, ignoreCase, fragment, ignoreChars,
                maxIgnoreChars)
                || recursiveContains(childNodeU, text, next, ignoreCase, fragment, ignoreChars,
                        maxIgnoreChars);
      }

    } else {
      // Case sensitive.
      MultiTextNode childNode = pointer.getChildNode(charAt);

      if (charAtIgnored && childNode == null) {
        // Recursion with incremented index.
        return recursiveContains(pointer, text, next, ignoreCase, fragment, ignoreChars,
                maxIgnoreChars);
      } else {
        // Recursion with new node.
        return recursiveContains(childNode, text, next, ignoreCase, fragment, ignoreChars,
                maxIgnoreChars);
      }
    }
  }

  public Collection<AnnotationFS> find(TextMarkerStream stream, Map<String, Type> typeMap,
          boolean ignoreCase, int ignoreLength, boolean edit, double distance, String ignoreToken) {

    Collection<AnnotationFS> results = new HashSet<AnnotationFS>();
    stream.moveToFirst();
    FSIterator<AnnotationFS> streamPointer = stream.copy();

    while (stream.isValid()) {
      TextMarkerBasic anchorBasic = (TextMarkerBasic) stream.get();
      streamPointer.moveTo(anchorBasic);

      List<TextMarkerBasic> basicsToAdd = new ArrayList<TextMarkerBasic>();
      basicsToAdd.add(anchorBasic);
      String text = anchorBasic.getCoveredText();
      StringBuilder candidate = new StringBuilder(text);
      String lastCandidate = candidate.toString();
      List<AnnotationFS> interResults = new ArrayList<AnnotationFS>();

      while (streamPointer.isValid()) {

        List<String> types = containsFragment(candidate.toString(), ignoreCase, ignoreLength, edit,
                distance, ignoreToken);

        if (!types.isEmpty()) {
          streamPointer.moveToNext();
          if (streamPointer.isValid()) {
            TextMarkerBasic next = (TextMarkerBasic) streamPointer.get();
            // List<String> contains = contains(candidate,
            // ignoreCase,
            // ignoreLength, edit, distance, ignoreToken);

            tryToCreateAnnotation(stream, results, basicsToAdd, candidate.toString(), interResults,
                    ignoreCase, ignoreLength, edit, distance, ignoreToken, typeMap);
            //			
            lastCandidate = candidate.toString();
            candidate.append(next.getCoveredText());
            basicsToAdd.add(next);

          } else {
            // !streamPointer.isValid();
            tryToCreateAnnotation(stream, results, basicsToAdd, lastCandidate, interResults,
                    ignoreCase, ignoreLength, edit, distance, ignoreToken, typeMap);
          }
        } else {

          // containsFragment.isEmpty();
          // basicsToAdd.remove(basicsToAdd.size() - 1);
          // tryToCreateAnnotation(stream, results, basicsToAdd,
          // lastCandidate, interResults, ignoreCase,
          // ignoreLength, edit, distance, ignoreToken, typeMap);

          // breaks inner while()-loop.
          break;
        }

      }

      stream.moveToNext();
    }

    return results;
  }

  public List<AnnotationFS> find(TextMarkerStream stream, boolean ignoreCase, int size,
          char[] ignoreChars, int maxIgnoredChars) {
    assert false;
    return new ArrayList<AnnotationFS>();
  }

  private void tryToCreateAnnotation(TextMarkerStream stream, Collection<AnnotationFS> results,
          List<TextMarkerBasic> basicsToAdd, String lastCandidate, List<AnnotationFS> interResult,
          boolean ignoreCase, int ignoreLength, boolean edit, double distance, String ignoreToken,
          Map<String, Type> map) {

    List<String> contains = contains(lastCandidate, ignoreCase, ignoreLength, edit, distance,
            ignoreToken);
    if (basicsToAdd.size() >= 1 || contains.isEmpty()) {
      for (String each : contains) {
        Type type = map.get(each);
        if (type != null) {
          int begin = basicsToAdd.get(0).getBegin();
          int end = basicsToAdd.get(basicsToAdd.size() - 1).getEnd();
          AnnotationFS newFS = stream.getCas().createAnnotation(type, begin, end);
          results.add(newFS);
        }
      }
    } else if (interResult != null && !interResult.isEmpty()) {
      results.addAll(interResult);
    }
  }

  /**
   * Returns a map with all strings with a specified edit distance to the string query as keys and
   * the files they belong to as values.
   * 
   * @param query
   *          The query string.
   * @return A map with all strings with a specified edit distance to the string query as keys and
   *         the files they belong to as values.
   */
  public Map<String, Set<String>> editDistance(String query, int distance) {
    return editDistance(query, distance, false, "");
  }

  /**
   * Returns a map with all strings with a specified edit distance to the string query as keys and
   * the files they belong to as values.
   * 
   * @param query
   *          The query string.
   * @param distance
   *          The specified edit distance.
   * @param ignoreCase
   *          Indicates whether we search case sensitive or not.
   * @return A map with all strings with a specified edit distance to the string query as keys and
   *         the files they belong to as values.
   */
  public Map<String, Set<String>> editDistance(String query, int distance, boolean ignoreCase,
          String ignoreToken) {
    return editDistance(query, distance, ignoreCase, ignoreToken, false);
  }

  /**
   * Returns a map with all strings with a specified edit distance to the string query as keys and
   * the files they belong to as values.
   * 
   * @param query
   *          The query string.
   * @param distance
   *          The specified edit distance.
   * @param ignoreCase
   *          Indicates whether we search case sensitive or not.
   * @param fragment
   *          Indicates whether we search for fragments of the query string or not.
   * @return A map with all strings with a specified edit distance to the string query as keys and
   *         the files they belong to as values.
   */
  public Map<String, Set<String>> editDistance(String query, int distance, boolean ignoreCase,
          String ignoreToken, boolean fragment) {

    // The second alternative realizes the fragment functionality by
    // setting the insert costs of the ignored character to zero. This
    // is much more elegant and easier to maintain. I don't know if the
    // other way is faster, so I did not delete it yet.

    Map<Character, Double> oldInsertCosts = new HashMap<Character, Double>();
    EditDistanceCostMap edcm = new EditDistanceCostMap();

    // We need to store the old insert costs before we set them to zero.
    for (char c : ignoreToken.toCharArray()) {
      oldInsertCosts.put(c, edcm.getInsertCosts(c));
      edcm.setInsertCosts(c, 0.0);
    }

    Map<String, Set<String>> result = null;

    if (ignoreCase) {
      result = editDistanceClever(root, query.toLowerCase(), "", distance, 0, true, fragment, edcm,
              false, false);
    } else {
      result = editDistanceClever(root, query, "", distance, 0, false, fragment, edcm, false, false);
    }

    // Restoring of the old insert costs.
    for (Entry<Character, Double> c : oldInsertCosts.entrySet()) {
      edcm.setDeleteCosts(c.getKey(), c.getValue());
    }

    return result;
  }

  /**
   * Returns a map with all strings with a specified edit distance to the string query as keys and
   * the files they belong to as values.
   * 
   * @param node
   *          The MultiTextNode which is under consideration at the moment.
   * @param query
   *          The query string.
   * @param result
   *          The result which matched until now.
   * @param distance
   *          The remaining edit distance.
   * @param index
   *          The index of the query string at the moment.
   * @param ignoreCase
   *          Indicates whether we search case sensitive or not.
   * @param fragment
   *          Indicates whether we search for fragments of the query string or not.
   * @param edm
   *          The edit distance cost map we are using.
   * @param lastActionInsert
   *          Indicates whether the last action was an insert action.
   * @param lastActionDelete
   *          Indicates whether the last action was a delete action.
   * @return A map with all strings with a specified edit distance to the string query as keys and
   *         the files they belong to as values.
   */
  private Map<String, Set<String>> editDistanceClever(MultiTextNode node, String query,
          String result, double distance, int index, boolean ignoreCase, boolean fragment,
          EditDistanceCostMap edm, boolean lastActionInsert, boolean lastActionDelete) {

    EditDistanceResultMap resultMap = new EditDistanceResultMap();

    if (!lastActionInsert) {
      // Delete.
      if (distance - edm.getDeleteCosts(node.getValue()) >= 0 && result.length() > 0) {
        resultMap.putAll(editDistanceClever(node, query, result, distance
                - edm.getDeleteCosts(node.getValue()), index + 1, ignoreCase, fragment, edm, false,
                true));
      }
    }

    // Recursion stop.
    if (node.isWordEnd() || fragment) {

      HashMap<String, Set<String>> temp = new HashMap<String, Set<String>>();

      double remainingInsertCosts = 0.0;

      // Accumulating remaining insert costs if the query is longer than
      // the word in the trie.
      for (int i = index; i < query.length(); i++) {
        remainingInsertCosts += edm.getInsertCosts(query.charAt(i));
      }

      if (remainingInsertCosts <= distance) {
        // if (remainingInsertCosts <= distance &&
        // !node.getTypes().isEmpty()) {
        // if (query.length() - index <= distance) {

        if (fragment) {
          temp.put(result, new HashSet<String>(getTypeCone(node)));
        } else {
          temp.put(result, new HashSet<String>(node.getTypes()));
        }

        resultMap.putAll(temp);
      }

      // Important: word end does not mean no children any more!
      if (node.getChildren() == null) {
        return resultMap;
      }
    }

    // Recursion.
    for (MultiTextNode tempNode : node.getChildren().values()) {

      if (index < query.length()) {
        if (ignoreCase) {
          if (Character.toLowerCase(tempNode.getValue()) == Character.toLowerCase(query
                  .charAt(index))) {
            resultMap.putAll(editDistanceClever(tempNode, query, result + tempNode.getValue(),
                    distance, index + 1, ignoreCase, fragment, edm, false, false));
          }
        } else {
          if (tempNode.getValue() == query.charAt(index)) {
            resultMap.putAll(editDistanceClever(tempNode, query, result + tempNode.getValue(),
                    distance, index + 1, ignoreCase, fragment, edm, false, false));
          }
        }
      }

      if (distance - edm.getReplaceCosts(node.getValue(), tempNode.getValue()) >= 0) {

        // Substitute.
        resultMap.putAll(editDistanceClever(tempNode, query, result + tempNode.getValue(), distance
                - edm.getReplaceCosts(node.getValue(), tempNode.getValue()), index + 1, ignoreCase,
                fragment, edm, false, false));
      }

      if (!lastActionDelete) {
        if (distance - edm.getInsertCosts(tempNode.getValue()) >= 0) {
          // Insert - use the same index twice.
          resultMap.putAll(editDistanceClever(tempNode, query, result + tempNode.getValue(),
                  distance - edm.getInsertCosts(tempNode.getValue()), index, ignoreCase, fragment,
                  edm, true, false));
        }
      }
    }

    return resultMap;
  }

  /**
   * Checks if a string is contained by the MultiTreeWordList.
   * 
   * @param node
   *          The MultiTextNode which is under consideration at the moment.
   * @param query
   *          The query string.
   * @param result
   *          The result which matched until now.
   * @param distance
   *          The remaining edit distance.
   * @param index
   *          The index of the query string at the moment.
   * @param ignoreCase
   *          Indicates whether we search case sensitive or not.
   * @param fragment
   *          Indicates whether we search for fragments of the query string or not.
   * @param edm
   *          The edit distance cost map we are using.
   * @return A map with all strings with a specified edit distance to the string query as keys and
   *         the files they belong to as values.
   */
  private boolean editDistanceBool(MultiTextNode node, String query, String result,
          double distance, int index, boolean ignoreCase, boolean fragment, EditDistanceCostMap edm) {

    boolean deletion = false;
    boolean insertion = false;
    boolean substitution = false;
    boolean noop = false;

    // Recursion stop.
    if (fragment) {
      if (index == query.length()) {
        return true;
      }
    }

    if (node.isWordEnd()) {

      double remainingInsertCosts = 0.0;

      // Accumulating remaining insert costs if the query is longer than
      // the word in the trie.
      for (int i = index; i < query.length(); i++) {
        remainingInsertCosts += edm.getInsertCosts(query.charAt(i));
      }

      if (remainingInsertCosts <= distance) {
        // if (query.length() - index <= distance) {
        return true;
      }
    }

    // Delete.
    if (distance - edm.getDeleteCosts(node.getValue()) >= 0 && result.length() > 0) {
      deletion = editDistanceBool(node, query, result, distance
              - edm.getDeleteCosts(node.getValue()), index + 1, ignoreCase, fragment, edm);

      if (deletion) {
        return true;
      }
    }

    // Recursion.
    for (MultiTextNode tempNode : node.getChildren().values()) {

      if (index < query.length()) {
        if (ignoreCase) {
          if (Character.toLowerCase(tempNode.getValue()) == Character.toLowerCase(query
                  .charAt(index))) {
            noop = editDistanceBool(tempNode, query, result + tempNode.getValue(), distance,
                    index + 1, ignoreCase, fragment, edm);
          }
        } else {
          if (tempNode.getValue() == query.charAt(index)) {
            noop = editDistanceBool(tempNode, query, result + tempNode.getValue(), distance,
                    index + 1, ignoreCase, fragment, edm);
          }
        }

        if (noop) {
          return true;
        }
      }

      if (distance - edm.getReplaceCosts(node.getValue(), tempNode.getValue()) >= 0) {

        // Substitute.
        substitution = editDistanceBool(tempNode, query, result + tempNode.getValue(), distance
                - edm.getReplaceCosts(node.getValue(), tempNode.getValue()), index + 1, ignoreCase,
                fragment, edm);

        if (substitution) {
          return true;
        }
      }

      if (distance - edm.getInsertCosts(tempNode.getValue()) >= 0) {
        // Insert - use the same index twice.
        insertion = editDistanceBool(tempNode, query, result + tempNode.getValue(), distance
                - edm.getInsertCosts(tempNode.getValue()), index, ignoreCase, fragment, edm);

        if (insertion) {
          return true;
        }
      }

    }

    return false;
  }

  // private Map<String, Set<String>> editDistance(MultiTextNode node, String query, String result,
  // double distance, int index, boolean ignoreCase, String ignoreToken, boolean fragment,
  // EditDistanceCostMap edm) {
  //
  // // TODO: fragment implementieren, falls es gebraucht wird.
  //
  // EditDistanceResultMap resultMap = new EditDistanceResultMap();
  //
  // // Delete.
  // if (distance - edm.getDeleteCosts(node.getValue()) >= 0 && result.length() > 0) {
  // resultMap.putAll(editDistance(node, query, result, distance
  // - edm.getDeleteCosts(node.getValue()), index + 1, ignoreCase, ignoreToken, fragment,
  // edm));
  // }
  //
  // // Recursion stop.
  // if (node.isWordEnd()) {
  //
  // HashMap<String, Set<String>> temp = new HashMap<String, Set<String>>();
  //
  // if (query.length() - index <= distance) {
  // temp.put(result, new HashSet<String>(node.getTypes()));
  // resultMap.putAll(temp);
  // }
  //
  // // Ignore token at the end of the word.
  // if (ignoreToken.contains(String.valueOf(node.getValue()))) {
  // temp.put(result, new HashSet<String>(node.getTypes()));
  // resultMap.putAll(temp);
  // }
  //
  // if (node.getChildren() == null) {
  // return resultMap;
  // }
  // }
  //
  // // Recursion.
  // for (MultiTextNode tempNode : node.getChildren().values()) {
  //
  // if (index < query.length()) {
  // if (ignoreCase) {
  // if (Character.toLowerCase(tempNode.getValue()) == Character.toLowerCase(query
  // .charAt(index))) {
  // resultMap.putAll(editDistance(tempNode, query, result + tempNode.getValue(), distance,
  // index + 1, ignoreCase, ignoreToken, fragment, edm));
  // }
  // } else {
  // if (tempNode.getValue() == query.charAt(index)) {
  // resultMap.putAll(editDistance(tempNode, query, result + tempNode.getValue(), distance,
  // index + 1, ignoreCase, ignoreToken, fragment, edm));
  // }
  // }
  // }
  //
  // if (distance - edm.getReplaceCosts(node.getValue(), tempNode.getValue()) >= 0) {
  //
  // // Substitute.
  // resultMap.putAll(editDistance(tempNode, query, result + tempNode.getValue(), distance
  // - edm.getReplaceCosts(node.getValue(), tempNode.getValue()), index + 1, ignoreCase,
  // ignoreToken, fragment, edm));
  // }
  //
  // // Ignore token.
  // if (ignoreToken.contains(String.valueOf(tempNode.getValue()))) {
  // resultMap.putAll(editDistance(tempNode, query, result + tempNode.getValue(), distance,
  // index, ignoreCase, ignoreToken, fragment, edm));
  // } else {
  // if (distance - edm.getInsertCosts(tempNode.getValue()) >= 0) {
  // // Insert - use the same index twice.
  // resultMap.putAll(editDistance(tempNode, query, result + tempNode.getValue(), distance
  // - edm.getInsertCosts(tempNode.getValue()), index, ignoreCase, ignoreToken,
  // fragment, edm));
  // }
  // }
  // }
  //
  // return resultMap;
  // }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((costMap == null) ? 0 : costMap.hashCode());
    result = prime * result + ((root == null) ? 0 : root.hashCode());
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
    MultiTreeWordList other = (MultiTreeWordList) obj;
    if (costMap == null) {
      if (other.costMap != null)
        return false;
    } else if (!costMap.equals(other.costMap))
      return false;
    if (root == null) {
      if (other.root != null)
        return false;
    } else if (!root.equals(other.root))
      return false;
    return true;
  }

  public void createMTWLFile(String path) {
    persistence.createMTWLFile(root, path);
  }

}
