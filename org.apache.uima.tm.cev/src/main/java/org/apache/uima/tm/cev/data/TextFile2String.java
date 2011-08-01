package org.apache.uima.tm.cev.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.uima.tm.cev.CEVPlugin;


public class TextFile2String {

  private static String encoding = "UTF-8";

  static {
    // ResourceBundle ctuBundle = ResourceBundle.getBundle("converter");
    // encoding = ctuBundle.getString("TextFileRecoder.encoding");
  }

  public static String read(File file) {
    return read(file, encoding);
  }

  public static String read(File file, String encoding) {
    StringBuffer contents = new StringBuffer();

    BufferedReader input = null;
    try {

      InputStreamReader isr = encoding == null ? new FileReader(file) : new InputStreamReader(
              new FileInputStream(file), encoding);

      input = new BufferedReader(isr);
      String line = null;

      while ((line = input.readLine()) != null) {
        contents.append(line);
        contents.append(System.getProperty("line.separator"));
      }
    } catch (FileNotFoundException ex) {
      CEVPlugin.error(ex);
    } catch (IOException ex) {
      CEVPlugin.error(ex);
    } finally {
      try {
        if (input != null) {
          // flush and close both "input" and its underlying
          // FileReader
          input.close();
        }
      } catch (IOException ex) {
        CEVPlugin.error(ex);
      }
    }
    return contents.toString();
  }

  public static void write(String content, File file) throws FileNotFoundException, IOException {
    write(content, file, encoding);
  }

  public static void write(String content, File file, String enc) throws FileNotFoundException,
          IOException {
    Writer output = null;
    try {
      output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), enc));
      output.write(content);
    } finally {
      if (output != null)
        output.close();
    }
  }

}
