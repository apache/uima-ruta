package org.apache.uima.tm.textmarker.engine;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.JCas;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerColoring;
import org.apache.uima.tools.stylemap.ColorParser;
import org.apache.uima.tools.stylemap.StyleMapEntry;
import org.apache.uima.tools.stylemap.StyleMapXmlParser;
import org.apache.uima.util.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;


public class StyleMapFactory {

  private Map<String, String> colorNameMap;

  public StyleMapFactory() {
    initializeColorNameMap();
  }

  private void initializeColorNameMap() {
    colorNameMap = new HashMap<String, String>();
    colorNameMap.put("#000000", "black");
    colorNameMap.put("#c0c0c0", "silver");
    colorNameMap.put("#808080", "gray");
    colorNameMap.put("#ffffff", "white");
    colorNameMap.put("#800000", "maroon");
    colorNameMap.put("#ff0000", "red");
    colorNameMap.put("#800080", "purple");
    colorNameMap.put("#ff00ff", "fuchsia");
    colorNameMap.put("#008000", "green");
    colorNameMap.put("#00ff00", "lime");
    colorNameMap.put("#808000", "olive");
    colorNameMap.put("#ffff00", "yellow");
    colorNameMap.put("#000080", "navy");
    colorNameMap.put("#0000ff", "blue");
    colorNameMap.put("#00ffff", "aqua");
    colorNameMap.put("#000000", "black");
    colorNameMap.put("#add8e6", "lightblue");
    colorNameMap.put("#90ee90", "lightgreen");
    colorNameMap.put("#ffa500", "orange");
    colorNameMap.put("#ffc0cb", "pink");
    colorNameMap.put("#fa8072", "salmon");
    colorNameMap.put("#00ffff", "cyan");
    colorNameMap.put("#ee82ee", "violet");
    colorNameMap.put("#d2b48c", "tan");
    colorNameMap.put("#a52a2a", "brown");
    colorNameMap.put("#ffffff", "white");
    colorNameMap.put("#9370db", "mediumpurple");
    // in other order for lookup
    colorNameMap.put("black", "#000000");
    colorNameMap.put("silver", "#c0c0c0");
    colorNameMap.put("gray", "#808080");
    colorNameMap.put("white", "#ffffff");
    colorNameMap.put("maroon", "#800000");
    colorNameMap.put("red", "#ff0000");
    colorNameMap.put("purple", "#800080");
    colorNameMap.put("fuchsia", "#ff00ff");
    colorNameMap.put("green", "#008000");
    colorNameMap.put("lime", "#00ff00");
    colorNameMap.put("olive", "#808000");
    colorNameMap.put("yellow", "#ffff00");
    colorNameMap.put("navy", "#000080");
    colorNameMap.put("blue", "#0000ff");
    colorNameMap.put("aqua", "#00ffff");
    colorNameMap.put("black", "#000000");
    colorNameMap.put("lightblue", "#add8e6");
    colorNameMap.put("lightgreen", "#90ee90");
    colorNameMap.put("orange", "#ffa500");
    colorNameMap.put("pink", "#ffc0cb");
    colorNameMap.put("salmon", "#fa8072");
    colorNameMap.put("cyan", "#00ffff");
    colorNameMap.put("violet", "#ee82ee");
    colorNameMap.put("tan", "#d2b48c");
    colorNameMap.put("brown", "#a52a2a");
    colorNameMap.put("white", "#ffffff");
    colorNameMap.put("mediumpurple", "#9370db");
  }

  public void createStyleMap(String styleMapLocation, TextMarkerStream stream) throws IOException {
    List<StyleMapEntry> styleList = createStyleList(stream);
    String styleXml = createStyleXml(styleList);
    writeStyleFile(styleXml, styleMapLocation);
  }

  private String createStyleXml(List<StyleMapEntry> styleList) {
    StringBuffer result = new StringBuffer();
    result.append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n");
    result.append("<styleMap>\n");

    for (StyleMapEntry e : styleList) {
      result.append("<rule>\n");
      result.append("<pattern>");
      result.append(e.getPattern());
      result.append("</pattern>\n");
      result.append("<label>");
      String label = e.getLabel();
      if (label != null) {
        if ((label != null) && !label.equals(""))
          result.append(label);
        else
          result.append(e.getAnnotationTypeName());
      } else
        result.append(e.getAnnotationTypeName());

      result.append("</label>\n");
      result.append("<style>");

      String foregroundColor = "#" + Integer.toHexString(e.getForeground().getRGB()).substring(2);
      String backgroundColor = "#" + Integer.toHexString(e.getBackground().getRGB()).substring(2);

      if (colorNameMap.containsKey(foregroundColor)) {
        result.append("color:" + colorNameMap.get(foregroundColor) + ";");
      } else {
        result.append("color:" + foregroundColor + ";");
      }

      if (colorNameMap.containsKey(backgroundColor)) {
        result.append("background:" + colorNameMap.get(backgroundColor) + ";");
      } else {
        result.append("background:" + backgroundColor + ";");
      }
      Boolean ck = e.getChecked();
      String ckString = ck.toString();

      Boolean hid = (Boolean) Boolean.FALSE;
      String hidString = hid.toString();
      // this prevents hidden from being checked,
      // becasue that is not a meaningful combination
      if (hidString.equals("true")) {
        ckString = "false";
      }
      result.append("checked:" + ckString + ";");
      result.append("hidden:" + hidString + ";");

      result.append("</style>\n");
      result.append("</rule>\n");
    }

    result.append("</styleMap>\n");

    return result.toString();
  }

  private void writeStyleFile(String output, String styleMapLocation) throws IOException {
    File file = new File(styleMapLocation);
    FileUtils.saveString2File(output, file);
  }

  private List<StyleMapEntry> createStyleList(TextMarkerStream stream) {
    List<StyleMapEntry> result = new ArrayList<StyleMapEntry>();
    JCas cas = stream.getJCas();
    Type type = cas.getCasType(TextMarkerColoring.type);
    FSIterator<FeatureStructure> iterator = cas.getFSIndexRepository().getAllIndexedFS(type);
    while (iterator.hasNext()) {
      TextMarkerColoring each = (TextMarkerColoring) iterator.next();
      StyleMapEntry entry = new StyleMapEntry();
      entry.setAnnotationTypeName(each.getTargetType());
      String fgColor = each.getFgColor();
      entry.setForeground(parseColorBackground(fgColor));
      String bgColor = each.getBgColor();
      entry.setBackground(parseColorBackground(bgColor));
      entry.setChecked(each.getSelected());
      result.add(entry);
    }

    return result;
  }

  private Color parseColorBackground(String color) {
    if (color == null) {
      return Color.BLACK;
    }
    if (color.startsWith("#")) {
      return Color.decode(color);
    } else {
      String string = (String) colorNameMap.get(color);
      if (string != null)
        return Color.decode(string);
      else
        return Color.LIGHT_GRAY;
    }
  }

  private Color parseColorForeground(String color) {
    if (color.startsWith("#")) {
      return Color.decode(color);
    } else {
      String string = (String) colorNameMap.get(color);
      if (string != null)
        return Color.decode(string);
      else
        return Color.BLACK;
    }
  }

  public Map<String, StyleMapEntry> parseStyleMap(String styleFileString) {
    Map<String, StyleMapEntry> result = new HashMap<String, StyleMapEntry>();
    StyleMapXmlParser smxp = new StyleMapXmlParser(styleFileString);
    ColorParser cp = new ColorParser();
    for (int i = 0; i < smxp.annotType.size(); i++) {
      String typeName = ((String) smxp.annotType.elementAt(i));
      String labelString = ((String) smxp.styleLabel.elementAt(i));
      String featureValue = ((String) smxp.featureValue.elementAt(i));
      String styleColor = smxp.styleColor.elementAt(i).toString();
      StyleMapEntry e = cp.parseAndAssignColors(typeName, featureValue, labelString, styleColor);
      result.put(typeName, e);
    }
    return result;
  }

  public Map<String, StyleMapEntry> parseStyleMapDOM(String styleFileString) {
    Map<String, StyleMapEntry> result = new HashMap<String, StyleMapEntry>();
    if (styleFileString == null) {
      return result;
    }

    File styleMapFile = new File(styleFileString);
    Document parse = null;
    try {
      DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      FileInputStream stream = new FileInputStream(styleMapFile);
      parse = db.parse(stream);
      stream.close();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return null;

    } catch (ParserConfigurationException e) {
      e.printStackTrace();
      return null;

    } catch (FactoryConfigurationError e) {
      e.printStackTrace();
      return null;

    } catch (SAXException e) {
      e.printStackTrace();
      return null;

    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }

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

  protected StyleMapEntry getStyleMapEntry(String type, String label, String styleColor) {
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

  private String getTextValue(final Node node) {
    final Node first = node.getFirstChild();

    if (first != null) {
      return ((Text) first).getNodeValue().trim();

    } else {
      return null;
    }
  }
}
