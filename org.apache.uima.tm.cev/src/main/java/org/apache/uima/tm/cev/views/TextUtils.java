package org.apache.uima.tm.cev.views;

public class TextUtils {

  public static String shrinkNamespace(String namespace) {
    String result = "";
    if (namespace != null) {
      String[] split = namespace.split("[.]");
      for (int i = 0; i < split.length - 1; i++) {
        if (split[i].length() != 0) {
          result += split[i].charAt(0);
          result += ".";
        }
      }
      if (split.length == 0) {
        return namespace;
      } else {
        result += split[split.length - 1];
      }
    }
    return result;
  }

}
