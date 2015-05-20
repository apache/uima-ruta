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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class MultiTreeWordListPersistence {

  /**
   * Reads the XML-File with the specified path and creates a TreeWordList.
   * 
   * @param root
   *          - the root node of the tree
   * @param path
   *          - path of the word list
   * @throws IOException
   */
  public void readMTWL(MultiTextNode root, String path) throws IOException {
    readMTWL(root, new FileInputStream(path), "UTF-8");
  }

  /**
   * Sniffs the content type for xml type.
   * 
   * @param is
   *          the inputStream to sniff. Must support {@link InputStream#markSupported()}
   * @return true if this stream starts with '{@literal <?xml}'
   */
  public static boolean isSniffedXmlContentType(InputStream is) throws IOException {
    if (is == null)
      throw new IOException("Stream is null");
    if (!is.markSupported()) {
      throw new IOException("Cannot mark stream. just wrap it in a BufferedInputStream");
    }
    byte[] bytes = new byte[5]; // peek first five letters
    is.mark(5);
    is.read(bytes);
    String prefix = new String(bytes);
    is.reset();
    if ("<?xml".equals(prefix)) {
      return true;
    }
    return false;
  }

  public void readMTWL(MultiTextNode root, InputStream stream, String encoding) throws IOException {
    try {
      InputStream is = new BufferedInputStream(stream); // adds mark/reset support
      boolean isXml = isSniffedXmlContentType(is);
      if (!isXml) { // MTWL is encoded
        is = new ZipInputStream(is);
        ((ZipInputStream) is).getNextEntry(); // zip must contain a single entry
      }
      InputStreamReader streamReader = new InputStreamReader(is, encoding);
      TrieXMLEventHandler handler = new TrieXMLEventHandler(root);
      SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
      SAXParser saxParser = saxParserFactory.newSAXParser();
      XMLReader reader = saxParser.getXMLReader();
      // was:
      // XMLReader reader = XMLReaderFactory.createXMLReader();
      reader.setContentHandler(handler);
      reader.setErrorHandler(handler);
      reader.parse(new InputSource(streamReader));
    } catch (SAXException e) {
      e.printStackTrace();
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    }
  }

  public void createMTWLFile(MultiTextNode root, String path) throws IOException {
    createMTWLFile(root, path, true, "UTF-8");
  }

  public void createMTWLFile(MultiTextNode root, boolean compressed, String path)
          throws IOException {
    createMTWLFile(root, path, compressed, "UTF-8");
  }

  public void createMTWLFile(MultiTextNode root, String path, boolean compressed, String encoding)
          throws IOException {
    if (compressed) {
      writeCompressedMTWLFile(root, path, encoding);
    } else {
      writeUncompressedMTWLFile(root, path, encoding);
    }
  }

  private void writeCompressedMTWLFile(MultiTextNode root, String path, String encoding)
          throws IOException {
    FileOutputStream fos = new FileOutputStream(path);
    BufferedOutputStream bos = new BufferedOutputStream(fos);
    ZipOutputStream zos = new ZipOutputStream(bos);
    OutputStreamWriter writer = new OutputStreamWriter(zos, encoding);
    zos.putNextEntry(new ZipEntry(path));
    writeMTWLFile(root, writer);
    writer.flush();
    zos.closeEntry();
    writer.close();
  }

  private void writeUncompressedMTWLFile(MultiTextNode root, String path, String encoding)
          throws IOException {
    FileOutputStream output = new FileOutputStream(path);
    OutputStreamWriter writer = new OutputStreamWriter(output, encoding);
    writeMTWLFile(root, writer);
    writer.close();
  }

  private void writeMTWLFile(MultiTextNode root, OutputStreamWriter writer) throws IOException {
    writer.write("<?xml version=\"1.0\" ?><root>");
    for (MultiTextNode node : root.getChildren().values()) {
      writeTextNode(writer, node);
    }
    writer.write("</root>");
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
