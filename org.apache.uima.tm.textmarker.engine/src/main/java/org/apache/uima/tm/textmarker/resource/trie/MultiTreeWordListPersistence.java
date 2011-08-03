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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class MultiTreeWordListPersistence {

  /**
   * 
   * Reads the XML-File with the specified path and creates a TreeWordList.
   * 
   * @param path
   *          The location of the XML-File.
   */
  public void readMTWL(MultiTextNode root, String path) {
    readMTWL(root, path, "UTF-8");
  }

  public void readMTWL(MultiTextNode root, String path, String encoding) {
    try {
      FileInputStream input = new FileInputStream(path);
      InputStreamReader stream = new InputStreamReader(input, encoding);
      TrieXMLEventHandler handler = new TrieXMLEventHandler(root);
      SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
      SAXParser saxParser = saxParserFactory.newSAXParser();
      XMLReader reader = saxParser.getXMLReader();
      // was:
      // XMLReader reader = XMLReaderFactory.createXMLReader();
      reader.setContentHandler(handler);
      reader.setErrorHandler(handler);
      reader.parse(new InputSource(stream));
    } catch (IOException e) {
      e.printStackTrace();
    } catch (SAXException e) {
      e.printStackTrace();
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    }
  }

  public void createMTWLFile(MultiTextNode root, String path) {
    createMTWLFile(root, path, "UTF-8");
  }

  public void createMTWLFile(MultiTextNode root, String path, String encoding) {
    try {
      FileOutputStream output = new FileOutputStream(path);
      OutputStreamWriter writer = new OutputStreamWriter(output, encoding);
      writer.write("<?xml version=\"1.0\" ?><root>");
      for (MultiTextNode node : root.getChildren().values()) {
        writeTextNode(writer, node);
      }
      writer.write("</root>");
      writer.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void writeTextNode(Writer writer, MultiTextNode node) {
    try {
      writer.write("\n");
      // String s = "<n e=\"" + Boolean.toString(node.isWordEnd()) + "\">";
      String s = "<n>";
      writer.write(s);
      writer.write("<c><![CDATA[" + node.getValue() + "]]></c>");
      if (Boolean.valueOf(node.isWordEnd())) {
        for (String type : node.getTypes()) {
          String t = "<t>" + type + "</t>";
          writer.write(t);
        }
      }
      for (MultiTextNode child : node.getChildren().values()) {
        writeTextNode(writer, child);
      }
      writer.write("</n>");
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
