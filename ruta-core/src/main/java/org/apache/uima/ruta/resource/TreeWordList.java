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

package org.apache.uima.ruta.resource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.type.RutaBasic;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

public class TreeWordList implements RutaWordList {
  private TextNode root;

  private String name;

  /**
   * Default constructor
   */
  public TreeWordList() {
    this.root = null;
  }

  /**
   * Constructs a TreeWordList from a resource.
   *
   * @param resource
   *          Resource to create a TextWordList from
   * @throws IllegalArgumentException
   *          When {@code resource.getFileName()} is null or does not end with .txt or .twl.
   */
  public TreeWordList(Resource resource) throws IOException {
    final String name = resource.getFilename();
    InputStream stream = null;
    try {
      stream = resource.getInputStream();
      if (name == null) {
        throw new IllegalArgumentException("List does not have a name.");
      } else if (name.endsWith(".txt")) {
        buildNewTree(stream);
      } else if (name.endsWith(".twl")) {
        readXML(stream, "UTF-8");
      } else {
        throw new IllegalArgumentException("File name should end with .twl or .txt, found " + name);
      }
    } finally {
      if (stream != null) {
        stream.close();
      }
    }

    this.name = name;
  }

  /**
   * Constructs a TreeWordList from a file with path = filename
   *
   * @param pathname
   *          path of the file to create a TextWordList from
   */
  public TreeWordList(String pathname) throws IOException {
    this(new FileSystemResource(pathname));
  }

  /**
   * Constructs a TreeWordList from an open stream with a given name
   * 
   * @param stream
   *          path of the file to create a TextWordList from
   */
  public TreeWordList(InputStream stream, String name) throws IOException {
    if (name.endsWith(".twl")) {
      readXML(stream, "UTF-8");
    }
    if (name.endsWith(".txt")) {
      buildNewTree(stream);
    }
    this.name = new File(name).getName();
  }

  public TreeWordList(List<String> data) {
    buildNewTree(data);
    name = "local";
  }

  public void buildNewTree(List<String> data) {
    this.root = new TextNode();
    for (String s : data) {
      addWord(s);
    }
  }

  /**
   * Creates a new Tree in the existing treeWordList from a file with path pathname
   * 
   * @param stream
   *          Open InputStream containing the word for the treeWordList, this method will close the
   *          stream.
   */
  public void buildNewTree(InputStream stream) throws IOException {
    Scanner scan = new Scanner(stream, "UTF-8");
    // creating a new tree
    this.root = new TextNode();
    while (scan.hasNextLine()) {
      String s = scan.nextLine().trim();
      // HOTFIX for old formats
      if (s.endsWith("=")) {
        s = s.substring(0, s.length() - 1);
        s = s.trim();
      }
      addWord(s);
    }
    scan.close();
  }

  /**
   * Returns the root node of the tree
   * 
   * @return the root node
   */
  public TextNode getRoot() {
    return this.root;
  }

  /**
   * Add a new String into the TreeWordList
   * 
   * @param s
   *          The String to add
   */
  public void addWord(String s) {
    // Create Nodes from all chars of the strings besides the last one
    TextNode pointer = root;
    for (Character each : s.toCharArray()) {
      TextNode childNode = pointer.getChildNode(each);
      if (childNode == null) {
        childNode = new TextNode(each, false);
        pointer.addChild(childNode);
      }
      pointer = childNode;
    }
    pointer.setWordEnd(s.length() > 0);
  }

  /**
   * Checks if TreeWordList contains String s
   */
  public boolean contains(String s, boolean ignoreCase, int size, char[] ignoreChars,
          int maxIgnoreChars, boolean ignoreWS) {
    if(s == null) {
      return false;
    }
    TextNode pointer = root;
    return recursiveContains(pointer, s, 0, ignoreCase && s.length() > size, false, ignoreChars,
            maxIgnoreChars, ignoreWS);
  }

  public boolean containsFragment(String s, boolean ignoreCase, int size, char[] ignoreChars,
          int maxIgnoreChars, boolean ignoreWS) {
    TextNode pointer = root;
    return recursiveContains(pointer, s, 0, ignoreCase && s.length() > size, true, ignoreChars,
            maxIgnoreChars, ignoreWS);
  }

  private boolean recursiveContains(TextNode pointer, String text, int index, boolean ignoreCase,
          boolean fragment, char[] ignoreChars, int maxIgnoreChars, boolean ignoreWS) {
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
      TextNode childNodeL = pointer.getChildNode(Character.toLowerCase(charAt));
      TextNode childNodeU = pointer.getChildNode(Character.toUpperCase(charAt));
      if (childNodeL == null && ignoreWS) {
        childNodeL = skipWS(pointer, charAt);
      }
      if (childNodeU == null && ignoreWS) {
        childNodeU = skipWS(pointer, charAt);
      }
      if (charAtIgnored && childNodeL == null && childNodeU == null) {
        return recursiveContains(pointer, text, next, ignoreCase, fragment, ignoreChars,
                maxIgnoreChars, ignoreWS);
      } else {
        return recursiveContains(childNodeL, text, next, ignoreCase, fragment, ignoreChars,
                maxIgnoreChars, ignoreWS)
                | recursiveContains(childNodeU, text, next, ignoreCase, fragment, ignoreChars,
                        maxIgnoreChars, ignoreWS);
      }
    } else {
      TextNode childNode = pointer.getChildNode(charAt);
      if (childNode == null && ignoreWS) {
        childNode = skipWS(pointer, charAt);
      }
      if (charAtIgnored && childNode == null) {
        return recursiveContains(pointer, text, next, ignoreCase, fragment, ignoreChars,
                maxIgnoreChars, ignoreWS);
      } else {
        return recursiveContains(childNode, text, next, ignoreCase, fragment, ignoreChars,
                maxIgnoreChars, ignoreWS);
      }
    }
  }

  private TextNode skipWS(TextNode pointer, char charAt) {
    TextNode childNode = pointer.getChildNode(' ');
    if (childNode != null) {
      TextNode node = childNode.getChildNode(charAt);
      if (node == null) {
        return skipWS(childNode, charAt);
      } else {
        return node;
      }
    }
    return null;
  }

  public List<AnnotationFS> find(RutaStream stream, boolean ignoreCase, int size,
          char[] ignoreChars, int maxIgnoredChars, boolean ignoreWS) {
    ArrayList<AnnotationFS> results = new ArrayList<AnnotationFS>();
    stream.moveToFirst();
    FSIterator<AnnotationFS> streamPointer = stream.copy();
    while (stream.isValid()) {
      RutaBasic anchorBasic = (RutaBasic) stream.get();
      streamPointer.moveTo(anchorBasic);

      List<RutaBasic> basicsToAdd = new ArrayList<RutaBasic>();
      basicsToAdd.add(anchorBasic);
      String text = anchorBasic.getCoveredText();
      StringBuilder candidate = new StringBuilder(text);
      // String lastCandidate = candidate.toString();
      Annotation interResult = null;
      while (streamPointer.isValid()) {
        if (containsFragment(candidate.toString(), ignoreCase, size, ignoreChars, maxIgnoredChars,
                ignoreWS)) {
          streamPointer.moveToNext();
          if (streamPointer.isValid()) {
            RutaBasic next = (RutaBasic) streamPointer.get();
            if (contains(candidate.toString(), ignoreCase, size, ignoreChars, maxIgnoredChars,
                    ignoreWS)) {
              interResult = new Annotation(stream.getJCas(), basicsToAdd.get(0).getBegin(),
                      basicsToAdd.get(basicsToAdd.size() - 1).getEnd());
            }
            // lastCandidate = candidate.toString();
            candidate.append(next.getCoveredText());
            basicsToAdd.add(next);
          } else {
            tryToCreateAnnotation(stream, ignoreCase, size, results, basicsToAdd,
                    candidate.toString(), interResult, ignoreChars, maxIgnoredChars, ignoreWS);
          }
        } else {
          basicsToAdd.remove(basicsToAdd.size() - 1);
          tryToCreateAnnotation(stream, ignoreCase, size, results, basicsToAdd,
                  candidate.toString(), interResult, ignoreChars, maxIgnoredChars, ignoreWS);
          break;
        }

      }
      stream.moveToNext();
    }
    return results;
  }

  public List<AnnotationFS> find(RutaStream stream, boolean ignoreCase, int size, boolean ignoreWS) {
    return find(stream, ignoreCase, size, null, 0, ignoreWS);
  }

  private void tryToCreateAnnotation(RutaStream stream, boolean ignoreCase, int size,
          ArrayList<AnnotationFS> results, List<RutaBasic> basicsToAdd, String lastCandidate,
          Annotation interResult, char[] ignoreChars, int maxIgnoredChars, boolean ignoreWS) {
    if (basicsToAdd.size() >= 1
            && contains(lastCandidate, ignoreCase, size, ignoreChars, maxIgnoredChars, ignoreWS)) {

      results.add(new Annotation(stream.getJCas(), basicsToAdd.get(0).getBegin(), basicsToAdd.get(
              basicsToAdd.size() - 1).getEnd()));
    } else if (interResult != null) {
      results.add(interResult);
    }
  }

  public void readXML(InputStream stream, String encoding) throws IOException {
    try {
      InputStreamReader streamReader = new InputStreamReader(stream, encoding);
      this.root = new TextNode();
      XMLEventHandler handler = new XMLEventHandler(root);
      SAXParserFactory factory = SAXParserFactory.newInstance();
      SAXParser parser = factory.newSAXParser();
      XMLReader reader = parser.getXMLReader();
      // XMLReader reader = XMLReaderFactory.createXMLReader();
      reader.setContentHandler(handler);
      reader.setErrorHandler(handler);
      reader.parse(new InputSource(streamReader));
    } catch (SAXParseException spe) {
      StringBuffer sb = new StringBuffer(spe.toString());
      sb.append("\n  Line number: " + spe.getLineNumber());
      sb.append("\n Column number: " + spe.getColumnNumber());
      sb.append("\n Public ID: " + spe.getPublicId());
      sb.append("\n System ID: " + spe.getSystemId() + "\n");
      System.out.println(sb.toString());
    } catch (SAXException se) {
      System.out.println("loadDOM threw " + se);
      se.printStackTrace(System.out);
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    }
  }

  public void createXMLFile(String path, String encoding) {
    try {
      FileOutputStream output = new FileOutputStream(path);
      OutputStreamWriter writer = new OutputStreamWriter(output, encoding);
      writer.write("<?xml version=\"1.0\" ?>");
      writer.write("<root>");
      for (TextNode child : root.getChildren().values()) {
        writeNode(writer, child);
      }
      writer.write("</root>");
      writer.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void writeNode(Writer writer, TextNode node) {
    try {
      String output = "<node char=\"" + node.getValue() + "\" isWordEnd=\""
              + Boolean.toString(node.isWordEnd()) + "\">";
      writer.write(output);
      for (TextNode child : node.getChildren().values()) {
        writeNode(writer, child);

      }
      writer.write("</node>");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String toString() {
    return name;
  }

  public List<AnnotationFS> find(RutaStream stream, Map<String, Object> typeMap, boolean ignoreCase,
          int ignoreLength, boolean edit, double distance, String ignoreToken) {
    return null;
  }

  public List<String> contains(String string, boolean ignoreCase, int ignoreLength, boolean edit,
          double distance, String ignoreToken) {
    return null;
  }

  public List<String> containsFragment(String string, boolean ignoreCase, int ignoreLength,
          boolean edit, double distance, String ignoreToken) {
    return null;
  }

  public void startDocument() {

  }

  public void endDocument() {

  }

}
