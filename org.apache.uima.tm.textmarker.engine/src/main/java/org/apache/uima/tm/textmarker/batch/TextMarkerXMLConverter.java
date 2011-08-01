package org.apache.uima.tm.textmarker.batch;

import java.io.File;

import org.apache.uima.tm.textmarker.resource.TreeWordList;


public class TextMarkerXMLConverter {

  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("Please add paths");
    } else {
      File dir = new File(args[0]);
      // TextMarkerVersionConverter converter = new
      // TextMarkerVersionConverter();
      // for (File file : dir.listFiles(new FilenameFilter() {
      //
      // @Override
      // public boolean accept(File dir, String name) {
      // return !name.endsWith("tm");
      // }
      //			
      // })) {
      // File output = new File(file.getParentFile(), file.getName() +
      // ".tm");
      // try {
      // converter.convertOldToNew(file, output);
      // } catch (IOException e) {
      // e.printStackTrace();
      // }
      // }

      for (File file : dir.listFiles()) {

        String path = file.getAbsolutePath();

        if (path.endsWith("txt")) {
          TreeWordList list = new TreeWordList(file.getAbsolutePath());
          String newFilePath = path.substring(0, path.length() - 3) + "twl";
          System.out.println(newFilePath);
          list.createXMLFile(newFilePath, "UTF-8");
        }
      }
    }
  }
}
