package org.apache.uima.tm.dltk.internal.core.builder;

import java.util.List;

public class TextMarkerBuildOptions {

  private final List<String> language;

  private final List<String> engines;

  private boolean importByName = false;

  private boolean resolveImports = false;

  private boolean errorOnDuplicateShortNames;

  public TextMarkerBuildOptions(List<String> language, List<String> engines) {
    super();
    this.language = language;
    this.engines = engines;
  }

  public List<String> getLanguage() {
    return language;
  }

  public List<String> getEngines() {
    return engines;
  }

  public void setImportByName(boolean importByName) {
    this.importByName = importByName;
  }

  public boolean isImportByName() {
    return importByName;
  }

  public void setResolveImports(boolean resolveImports) {
    this.resolveImports = resolveImports;
  }

  public boolean isResolveImports() {
    return resolveImports;
  }

  public void setErrorOnDuplicateShortNames(boolean error) {
    this.errorOnDuplicateShortNames = error;
  }

  public boolean isErrorOnDuplicateShortNames() {
    return errorOnDuplicateShortNames;
  }

}
