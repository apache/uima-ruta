package org.apache.uima.tm.cev.html.artifactViewer;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import org.htmlparser.visitors.NodeVisitor;

public class HTMLVisitor extends NodeVisitor {

  // Map mit HTML-ID und den Positionen der zugehoerigen HTML-Tags im Code
  // int[0] Startpos des Einleitenden Tags
  // int[1] Endpos des Einleitenden Tags
  // int[3] Startpos des Schliessenden Tags
  // int[4] Endpos des Schliessenden Tags
  private LinkedHashMap<String, int[]> idPosMap;

  // Zaehler fuer die IDs
  private int idCount;

  // Prefix fuer die IDs
  private String idName = "CAS_VIEWER_ID";

  // Stack fuer die Tags beim Parsen
  private LinkedList<String> tagStack;

  // Stack fuer die IDs beim Parsen
  private LinkedList<String> idStack;

  /**
   * Konstruktor
   * 
   * @param html
   *          HTML-Code
   */
  public HTMLVisitor(String html) {
    idPosMap = new LinkedHashMap<String, int[]>();
    idCount = 0;

    while (html.indexOf(idName) > -1)
      idName += "_";
  }

  /**
   * Gibt die Map mit den Id-Positionen zurueck
   * 
   * @return Map
   */
  public LinkedHashMap<String, int[]> getIDPosMap() {
    return idPosMap;
  }

  /**
   * Gibt den ID-Prefix zurueck
   * 
   * @return ID-Prefix
   */
  public String getIDName() {
    return idName;
  }

  /**
   * Gibt den ID-Zaehler zurueck
   * 
   * @return Zaehler
   */
  public int getIdCount() {
    return idCount;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.htmlparser.visitors.NodeVisitor#beginParsing()
   */
  @Override
  public void beginParsing() {
    // Initialisiert die Stacks
    tagStack = new LinkedList<String>();
    idStack = new LinkedList<String>();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.htmlparser.visitors.NodeVisitor#visitTag(org.htmlparser.Tag)
   */
  @Override
  public void visitTag(org.htmlparser.Tag tag) {
    // Pruefen ob das Tag schon eine ID hat
    String id = tag.getAttribute("ID");

    // Wenn nicht ID setzen
    String tagName = tag.getTagName();
    // TODO: added isIdTag
    if (id == null && isIdTag(tagName)) {
      id = idName + Integer.toString(idCount++);
      tag.setAttribute("ID", id);
    }

    if (id != null) {
      // Startpos setzen
      idPosMap.put(id, new int[] { tag.getStartPosition(), tag.getEndPosition(), 0, 0 });

      // auf Stack setzen
      tagStack.addFirst(tag.getTagName());
      idStack.addFirst(id);
    }
  }

  private boolean isIdTag(String tagName) {
    if (tagName.startsWith("!"))
      return false;
    String lowerCase = tagName.toLowerCase();
    if (lowerCase.equals("base") || lowerCase.equals("head") || lowerCase.equals("html")
            || lowerCase.equals("meta") || lowerCase.equals("script") || lowerCase.equals("style")
            || lowerCase.equals("title"))
      return false;
    return true;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.htmlparser.visitors.NodeVisitor#visitEndTag(org.htmlparser.Tag)
   */
  @Override
  public void visitEndTag(org.htmlparser.Tag tag) {

    if (isIdTag(tag.getTagName())) {
      while (true) {
        if (tagStack.isEmpty()) {
          System.out.println(tag.getTagName() + " : " + tag.getText() + " : " + tag.toString());
        }
        if (tagStack.getFirst().equals(tag.getTagName())) {
          break;
        }
        tagStack.removeFirst();
        idStack.removeFirst();
      }
      tagStack.removeFirst();
      int[] pos = idPosMap.get(idStack.removeFirst());
      pos[2] = tag.getStartPosition();
      pos[3] = tag.getEndPosition();
    }

  }

}
