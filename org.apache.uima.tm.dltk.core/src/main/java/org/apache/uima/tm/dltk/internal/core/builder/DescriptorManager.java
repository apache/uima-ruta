package org.apache.uima.tm.dltk.internal.core.builder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class DescriptorManager {

  private static final boolean CONVERT = false;

  private List<String> typeShortNames;

  private List<StringTriple> typeTriples;

  private Collection<String> importedTypeSystems;

  private Map<String, Collection<StringTriple>> features;

  private Collection<String> importedScripts;

  private Collection<String> importedEngines;

  public DescriptorManager() {
    super();
    typeShortNames = new ArrayList<String>();
    typeTriples = new ArrayList<StringTriple>();
    importedTypeSystems = new HashSet<String>();
    importedScripts = new HashSet<String>();
    importedEngines = new HashSet<String>();
    features = new HashMap<String, Collection<StringTriple>>();
  }

  public void addType(String name, String description, String parent) {
    if (parent == null) {
      parent = "uima.tcas.Annotation";
    }
    typeShortNames.add(name);
    typeTriples.add(new StringTriple(name, description, parent));
  }

  public void addFeature(String type, String name, String description, String parent) {
    Collection<StringTriple> list = features.get(type);
    if (list == null) {
      list = new ArrayList<StringTriple>();
      features.put(type, list);
    }
    list.add(new StringTriple(name, description, parent));
  }

  public void addScript(String name, boolean convert) {
    if (convert) {
      name = name.replaceAll("[.]", "/");
    }
    importedScripts.add(name);
  }

  public void addEngine(String name, boolean convert) {
    if (convert) {
      name = name.replaceAll("[.]", "/");
    }
    importedEngines.add(name);
  }

  public void addTypeSystem(String name, boolean convert) {
    if (convert) {
      name = name.replaceAll("[.]", "/");
    }
    importedTypeSystems.add(name);
  }

  public void addScript(String name) {
    addScript(name, CONVERT);
  }

  public void addEngine(String name) {
    addEngine(name, CONVERT);
  }

  public void addTypeSystem(String name) {
    addTypeSystem(name, CONVERT);
  }

  public List<String> getTypeShortNames() {
    return typeShortNames;
  }

  public List<StringTriple> getTypeTriples() {
    return typeTriples;
  }

  public Map<String, Collection<StringTriple>> getFeatures() {
    return features;
  }

  public Collection<String> getImportedTypeSystems() {
    return importedTypeSystems;
  }

  public Collection<String> getImportedScripts() {
    return importedScripts;
  }

  public Collection<String> getImportedEngines() {
    return importedEngines;
  }

}
