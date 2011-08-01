package org.apache.uima.tm.cev.data;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.uima.tm.cev.CEVPlugin;
import org.apache.uima.tools.stylemap.StyleMapEntry;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;


/**
 * Utilityklasse zum lesen von Stylemaps
 * 
 * @author Marco Nehmeier
 */
public class StyleMapReader {

  // Colormap
  public static final Map<String, String> COLOR_NAME_MAP;

  // Map mit Farben initialisieren
  static {
    COLOR_NAME_MAP = new HashMap<String, String>();
    COLOR_NAME_MAP.put("#000000", "black");
    COLOR_NAME_MAP.put("#c0c0c0", "silver");
    COLOR_NAME_MAP.put("#808080", "gray");
    COLOR_NAME_MAP.put("#ffffff", "white");
    COLOR_NAME_MAP.put("#800000", "maroon");
    COLOR_NAME_MAP.put("#ff0000", "red");
    COLOR_NAME_MAP.put("#800080", "purple");
    COLOR_NAME_MAP.put("#ff00ff", "fuchsia");
    COLOR_NAME_MAP.put("#008000", "green");
    COLOR_NAME_MAP.put("#00ff00", "lime");
    COLOR_NAME_MAP.put("#808000", "olive");
    COLOR_NAME_MAP.put("#ffff00", "yellow");
    COLOR_NAME_MAP.put("#000080", "navy");
    COLOR_NAME_MAP.put("#0000ff", "blue");
    COLOR_NAME_MAP.put("#00ffff", "aqua");
    COLOR_NAME_MAP.put("#000000", "black");
    COLOR_NAME_MAP.put("#add8e6", "lightblue");
    COLOR_NAME_MAP.put("#90ee90", "lightgreen");
    COLOR_NAME_MAP.put("#ffa500", "orange");
    COLOR_NAME_MAP.put("#ffc0cb", "pink");
    COLOR_NAME_MAP.put("#fa8072", "salmon");
    COLOR_NAME_MAP.put("#00ffff", "cyan");
    COLOR_NAME_MAP.put("#ee82ee", "violet");
    COLOR_NAME_MAP.put("#d2b48c", "tan");
    COLOR_NAME_MAP.put("#a52a2a", "brown");
    COLOR_NAME_MAP.put("#ffffff", "white");
    COLOR_NAME_MAP.put("#9370db", "mediumpurple");

    COLOR_NAME_MAP.put("black", "#000000");
    COLOR_NAME_MAP.put("silver", "#c0c0c0");
    COLOR_NAME_MAP.put("gray", "#808080");
    COLOR_NAME_MAP.put("white", "#ffffff");
    COLOR_NAME_MAP.put("maroon", "#800000");
    COLOR_NAME_MAP.put("red", "#ff0000");
    COLOR_NAME_MAP.put("purple", "#800080");
    COLOR_NAME_MAP.put("fuchsia", "#ff00ff");
    COLOR_NAME_MAP.put("green", "#008000");
    COLOR_NAME_MAP.put("lime", "#00ff00");
    COLOR_NAME_MAP.put("olive", "#808000");
    COLOR_NAME_MAP.put("yellow", "#ffff00");
    COLOR_NAME_MAP.put("navy", "#000080");
    COLOR_NAME_MAP.put("blue", "#0000ff");
    COLOR_NAME_MAP.put("aqua", "#00ffff");
    COLOR_NAME_MAP.put("black", "#000000");
    COLOR_NAME_MAP.put("lightblue", "#add8e6");
    COLOR_NAME_MAP.put("lightgreen", "#90ee90");
    COLOR_NAME_MAP.put("orange", "#ffa500");
    COLOR_NAME_MAP.put("pink", "#ffc0cb");
    COLOR_NAME_MAP.put("salmon", "#fa8072");
    COLOR_NAME_MAP.put("cyan", "#00ffff");
    COLOR_NAME_MAP.put("violet", "#ee82ee");
    COLOR_NAME_MAP.put("tan", "#d2b48c");
    COLOR_NAME_MAP.put("brown", "#a52a2a");
    COLOR_NAME_MAP.put("white", "#ffffff");
    COLOR_NAME_MAP.put("mediumpurple", "#9370db");
  }

  /**
   * Stylemap einlesen und die Farbinformationen als Map zurueckgeben
   * 
   * @param styleFileString
   *          Filename zur Stylemap
   * @return Map mit Farbinformationen
   */
  public static Map<String, StyleMapEntry> parseStyleMapDOM(String styleFileString) {
    File styleMapFile = new File(styleFileString);
    Map<String, StyleMapEntry> result = new HashMap<String, StyleMapEntry>();
    Document parse = null;

    // Einlesen/parsen
    try {
      DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      FileInputStream stream = new FileInputStream(styleMapFile);
      parse = db.parse(stream);
      stream.close();

    } catch (FileNotFoundException e) {
      CEVPlugin.error(e);
      return null;

    } catch (ParserConfigurationException e) {
      CEVPlugin.error(e);
      return null;

    } catch (FactoryConfigurationError e) {
      CEVPlugin.error(e);
      return null;

    } catch (SAXException e) {
      CEVPlugin.error(e);
      return null;

    } catch (IOException e) {
      CEVPlugin.error(e);
      return null;
    }

    // Document analysieren
    final Node root = parse.getDocumentElement();
    final NodeList nodeList = root.getChildNodes();

    for (int i = 0; i < nodeList.getLength(); ++i) {
      final Node node = nodeList.item(i);
      final String nodeName = node.getNodeName();
      // "rule" node ?
      if (!nodeName.equals("rule")) { //$NON-NLS-1$
        continue;
      }

      // Collect type or pattern, label, and color text style
      NodeList childrenList = node.getChildNodes();
      String type = ""; //$NON-NLS-1$ 
      String label = ""; //$NON-NLS-1$ 
      String colorText = ""; //$NON-NLS-1$ 
      for (int j = 0; j < childrenList.getLength(); ++j) {
        final Node child = childrenList.item(j);
        final String childName = child.getNodeName();

        if (childName.equals("pattern")) { //$NON-NLS-1$ 
          type = getTextValue(child);
        } else if (childName.equals("label")) { //$NON-NLS-1$ 
          label = getTextValue(child);
        } else if (childName.equals("style")) { //$NON-NLS-1$ 
          colorText = getTextValue(child);
        }
      }

      StyleMapEntry styleMapEntry = getStyleMapEntry(type, label, colorText);
      result.put(styleMapEntry.getAnnotationTypeName(), styleMapEntry);
    }
    return result;
  }

  /**
   * String aus Stylemap nach Fraben parsen und ein StyleMapEntry zurueckgeben
   * 
   * @param type
   *          Typ
   * @param label
   *          Label
   * @param styleColor
   *          String
   * @return
   */
  protected static StyleMapEntry getStyleMapEntry(String type, String label, String styleColor) {
    StyleMapEntry result = new StyleMapEntry();
    result.setAnnotationTypeName(type);
    result.setLabel(label);
    StringTokenizer token = new StringTokenizer(styleColor, ":;");
    if (!token.hasMoreTokens()) {
      return null; // No token
    }

    token.nextToken();
    String fgString = token.nextToken().toLowerCase().trim();
    result.setForeground(parseColorForeground(fgString));

    token.nextToken();
    String bgString = token.nextToken().toLowerCase().trim();
    result.setBackground(parseColorBackground(bgString));

    boolean checked = false; // default to Checked
    if (token.hasMoreTokens()) {
      String ck = token.nextToken();
      String tf = token.nextToken();
      if (ck.equals("checked")) {
        if (tf.equals("false")) {
          checked = false;
        } else if (tf.equals("true")) {
          checked = true;
        }
      }
    }
    result.setChecked(checked);

    boolean hidden = false;
    if (token.hasMoreTokens()) {
      String ck = token.nextToken();
      String tf = token.nextToken();
      if (ck.equals("hidden")) {
        if (tf.equals("true")) {
          hidden = true;
        }
      }
    }
    result.setHidden(hidden);
    return result;
  }

  private static String getTextValue(final Node node) {
    final Node first = node.getFirstChild();

    if (first != null) {
      return ((Text) first).getNodeValue().trim();

    } else {
      return null;
    }
  }

  private static Color parseColorBackground(String color) {
    if (color.startsWith("#")) {
      return Color.decode(color);
    } else {
      String string = COLOR_NAME_MAP.get(color);
      if (string != null)
        return Color.decode(string);
      else
        return Color.LIGHT_GRAY;
    }
  }

  private static Color parseColorForeground(String color) {
    if (color.startsWith("#")) {
      return Color.decode(color);
    } else {
      String string = COLOR_NAME_MAP.get(color);
      if (string != null)
        return Color.decode(string);
      else
        return Color.BLACK;
    }
  }
}
