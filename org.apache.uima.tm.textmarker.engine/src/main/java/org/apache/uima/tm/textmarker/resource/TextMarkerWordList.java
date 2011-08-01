package org.apache.uima.tm.textmarker.resource;

import java.util.Collection;
import java.util.Map;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;


public interface TextMarkerWordList {

  boolean contains(String string, boolean ignoreCase, int size, char[] ignoreChars,
          int maxIgnoreChars);

  boolean containsFragment(String string, boolean ignoreCase, int size, char[] ignoreChars,
          int maxIgnoreChars);

  Collection<String> contains(String string, boolean ignoreCase, int ignoreLength, boolean edit,
          double distance, String ignoreToken);

  Collection<String> containsFragment(String string, boolean ignoreCase, int ignoreLength,
          boolean edit, double distance, String ignoreToken);

  Collection<AnnotationFS> find(TextMarkerStream stream, boolean ignoreCase, int size,
          char[] ignoreToken, int maxIgnoredTokens);

  Collection<AnnotationFS> find(TextMarkerStream stream, Map<String, Type> typeMap,
          boolean ignoreCase, int ignoreLength, boolean edit, double distance, String ignoreToken);

}
