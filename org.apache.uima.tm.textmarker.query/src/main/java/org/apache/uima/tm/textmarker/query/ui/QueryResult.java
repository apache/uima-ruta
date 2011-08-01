package org.apache.uima.tm.textmarker.query.ui;

import java.io.File;

public class QueryResult {

  private String text;

  private File file;

  public QueryResult(String text, File file) {
    super();
    this.setText(text);
    this.setFile(file);
  }

  public void setFile(File file) {
    this.file = file;
  }

  public File getFile() {
    return file;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }

}
