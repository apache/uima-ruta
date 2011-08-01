/**
 * 
 */
package org.apache.uima.tm.dltk.internal.ui.documentation;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class TextMarkerPageFolder {
  private String path;

  private HashMap pages = new HashMap();

  public TextMarkerPageFolder(String path) {
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
    if (!(obj instanceof TextMarkerPageFolder))
      return false;
    if (obj == this)
      return true;
    TextMarkerPageFolder f = (TextMarkerPageFolder) obj;
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
      DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      parser.setErrorHandler(new DefaultHandler());
      config = parser.parse(new InputSource(stream)).getDocumentElement();
    } catch (SAXException e) {
      throw new IOException("Bad XML format");
    } catch (ParserConfigurationException e) {
      stream.close();
      throw new IOException("Bad XML format");
    } finally {
      stream.close();
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
        TextMarkerPageFolder folder = new TextMarkerPageFolder(path);
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