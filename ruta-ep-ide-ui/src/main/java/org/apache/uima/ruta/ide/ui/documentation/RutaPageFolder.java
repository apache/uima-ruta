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

package org.apache.uima.ruta.ide.ui.documentation;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;

import org.apache.commons.io.IOUtils;
import org.apache.uima.ruta.utils.XmlUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RutaPageFolder {
  private String path;

  private HashMap pages = new HashMap();

  public RutaPageFolder(String path) {
    super();
    this.path = path;
  }

  public void addPage(String keyword, String file) {
    pages.put(keyword, file);
  }

  public boolean verify() {
    if (path == null)
      return false;
    File file = new File(path);
    if (file.exists() && file.isDirectory())
      return true;
    return false;
  }

  public String getPath() {
    return path;
  }

  public HashMap getPages() {
    return pages;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof RutaPageFolder))
      return false;
    if (obj == this)
      return true;
    RutaPageFolder f = (RutaPageFolder) obj;
    if (!f.path.equals(this.path))
      return false;
    if (!f.pages.equals(this.pages))
      return false;
    return true;
  }

  public static List readXML(String data) throws IOException {
    // Wrapper the stream for efficient parsing
    InputStream stream = new ByteArrayInputStream(data.getBytes());

    // Do the parsing and obtain the top-level node
    Element config = null;
    try {
      DocumentBuilder parser = XmlUtils.createDocumentBuilder();
      parser.setErrorHandler(new DefaultHandler());
      config = parser.parse(new InputSource(stream)).getDocumentElement();
    } catch (SAXException e) {
      throw new IOException("Bad XML format");
    } finally {
      IOUtils.closeQuietly(stream);
    }

    if (!config.getNodeName().equalsIgnoreCase("manPages")) {
      throw new RuntimeException("Bad top level node");
    }

    List folders = new ArrayList();

    NodeList list = config.getChildNodes();
    int length = list.getLength();
    for (int i = 0; i < length; ++i) {
      Node node = list.item(i);
      short type = node.getNodeType();
      if (type == Node.ELEMENT_NODE && node.getNodeName().equalsIgnoreCase("location")) {
        Element location = (Element) node;
        String path = location.getAttribute("path");
        RutaPageFolder folder = new RutaPageFolder(path);
        NodeList locationChilds = location.getChildNodes();
        int pages = locationChilds.getLength();
        for (int j = 0; j < pages; ++j) {
          node = locationChilds.item(j);
          type = node.getNodeType();
          if (type == Node.ELEMENT_NODE && node.getNodeName().equalsIgnoreCase("page")) {
            Element word = (Element) node;
            String kw = word.getAttribute("keyword");
            String file = word.getAttribute("file");
            folder.addPage(kw, file);
          }
        }
        folders.add(folder);
      }
    }

    return folders;
  }

}
