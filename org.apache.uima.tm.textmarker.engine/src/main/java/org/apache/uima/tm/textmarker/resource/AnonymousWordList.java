package org.apache.uima.tm.textmarker.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;


public class AnonymousWordList implements TextMarkerWordList {

  private final List<String> list;

  public AnonymousWordList(List<String> strings) {
    super();
    this.list = strings;
  }

  public boolean contains(String s, boolean ignoreCase, int size, char[] ignoreChars,
          int maxIgnoreChars) {
    return list.contains(s);
  }

  public boolean containsFragment(String s, boolean ignoreCase, int size, char[] ignoreChars,
          int maxIgnoreChars) {
    return contains(s, ignoreCase, size, ignoreChars, maxIgnoreChars);
  }

  public List<AnnotationFS> find(TextMarkerStream stream, boolean ignoreCase, int size,
          char[] ignoreToken, int maxIgnoredTokens) {
    List<AnnotationFS> result = new ArrayList<AnnotationFS>();
    for (String each : list) {
      stream.moveToFirst();
      while (stream.hasNext()) {
        TextMarkerBasic next = (TextMarkerBasic) stream.next();
        if (each.equals(next.getCoveredText())) {
          result.add(next);
        }
      }
    }
    return result;
  }

  public List<AnnotationFS> find(TextMarkerStream stream, Map<String, Type> typeMap,
          boolean ignoreCase, int ignoreLength, boolean edit, double distance, String ignoreToken) {
    return new ArrayList<AnnotationFS>();
  }

  public List<String> contains(String string, boolean ignoreCase, int ignoreLength, boolean edit,
          double distance, String ignoreToken) {
    // TODO Auto-generated method stub
    return null;
  }

  public List<String> containsFragment(String string, boolean ignoreCase, int ignoreLength,
          boolean edit, double distance, String ignoreToken) {
    // TODO Auto-generated method stub
    return null;
  }

}
