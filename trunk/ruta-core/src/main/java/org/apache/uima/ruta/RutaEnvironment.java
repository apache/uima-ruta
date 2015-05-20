/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.uima.ruta;

import static org.apache.uima.util.Level.SEVERE;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.antlr.runtime.CommonToken;
import org.apache.uima.UIMAFramework;
import org.apache.uima.UimaContext;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.fit.factory.TypeSystemDescriptionFactory;
import org.apache.uima.resource.ResourceAccessException;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.resource.metadata.TypeDescription;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.ruta.action.AbstractRutaAction;
import org.apache.uima.ruta.condition.AbstractRutaCondition;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.list.ListExpression;
import org.apache.uima.ruta.expression.list.SimpleBooleanListExpression;
import org.apache.uima.ruta.expression.list.SimpleNumberListExpression;
import org.apache.uima.ruta.expression.list.SimpleStringListExpression;
import org.apache.uima.ruta.expression.list.SimpleTypeListExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.resource.LiteralWordListExpression;
import org.apache.uima.ruta.expression.resource.LiteralWordTableExpression;
import org.apache.uima.ruta.expression.resource.WordListExpression;
import org.apache.uima.ruta.expression.resource.WordTableExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.type.SimpleTypeExpression;
import org.apache.uima.ruta.resource.CSVTable;
import org.apache.uima.ruta.resource.MultiTreeWordList;
import org.apache.uima.ruta.resource.RutaResourceLoader;
import org.apache.uima.ruta.resource.RutaTable;
import org.apache.uima.ruta.resource.RutaWordList;
import org.apache.uima.ruta.resource.TreeWordList;
import org.apache.uima.util.InvalidXMLException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class RutaEnvironment {

  private final Object annotationTypeDummy = new Object();

  private Map<String, Type> types;

  private Map<String, RutaWordList> wordLists;

  private Map<String, RutaTable> tables;

  private RutaBlock owner;

  /**
   * Mapping from short type name (e.g. {@code W}) to their disambiguated long type names (e.g.
   * {@code org.apache.uima.ruta.type.W}).
   */
  private Map<String, String> namespaces;

  /**
   * Mapping from ambiguous short type names to all their possible long type names.
   */
  private Map<String, Set<String>> ambiguousTypeAlias;

  /**
   * Set of imported typesystems.
   */
  private Set<String> typesystems;

  /**
   * An alias from a long to a short name.
   */
  private static class Alias {
    final String longName;

    final String shortName;

    Alias(String longName, String shortName) {
      this.longName = longName;
      this.shortName = shortName;
    }
  }

  /**
   * Types that are imported in the environment. Keys are type system descriptors and values are
   * aliased types.
   */
  private Map<String, List<Alias>> typeImports;

  /**
   * Packages that are imported in the environment without a typesystem specification.
   *
   * Keys are package names and values are aliases. An empty string as alias means that all types
   * from the package should be imported in the default namespace.
   */
  private Map<String, List<String>> packageImports;

  /**
   * Set of types that are declared in the script.
   */
  private Set<String> declaredAnnotationTypes;

  private Map<String, Object> variableValues;

  private Map<String, Class<?>> variableTypes;

  private Map<String, Class<?>> availableTypes;

  private Map<String, Class<?>> variableGenericTypes;

  private Map<String, Class<?>> availableListTypes;

  private String[] resourcePaths = null;

  private CAS cas;

  private Map<String, Object> initializedVariables;

  private ResourceManager resourceManager;

  public RutaEnvironment(RutaBlock owner) {
    super();
    this.owner = owner;

    types = new HashMap<String, Type>();
    namespaces = new HashMap<String, String>();
    ambiguousTypeAlias = new HashMap<String, Set<String>>();
    typesystems = new HashSet<String>();
    typeImports = new HashMap<String, List<Alias>>();
    packageImports = new HashMap<String, List<String>>();
    declaredAnnotationTypes = new HashSet<String>();
    wordLists = new HashMap<String, RutaWordList>();
    tables = new HashMap<String, RutaTable>();
    variableValues = new HashMap<String, Object>();
    variableTypes = new HashMap<String, Class<?>>();
    variableGenericTypes = new HashMap<String, Class<?>>();
    availableTypes = new HashMap<String, Class<?>>();
    availableTypes.put("INT", Integer.class);
    availableTypes.put("STRING", String.class);
    availableTypes.put("DOUBLE", Double.class);
    availableTypes.put("FLOAT", Float.class);
    availableTypes.put("BOOLEAN", Boolean.class);
    availableTypes.put("TYPE", Type.class);
    availableTypes.put("CONDITION", AbstractRutaCondition.class);
    availableTypes.put("ACTION", AbstractRutaAction.class);
    availableTypes.put("WORDLIST", RutaWordList.class);
    availableTypes.put("WORDTABLE", RutaTable.class);
    availableTypes.put("BOOLEANLIST", List.class);
    availableTypes.put("INTLIST", List.class);
    availableTypes.put("DOUBLELIST", List.class);
    availableTypes.put("FLOATLIST", List.class);
    availableTypes.put("STRINGLIST", List.class);
    availableTypes.put("TYPELIST", List.class);
    availableListTypes = new HashMap<String, Class<?>>();
    availableListTypes.put("BOOLEANLIST", Boolean.class);
    availableListTypes.put("INTLIST", Integer.class);
    availableListTypes.put("DOUBLELIST", Double.class);
    availableListTypes.put("FLOATLIST", Float.class);
    availableListTypes.put("STRINGLIST", String.class);
    availableListTypes.put("TYPELIST", Type.class);
    resourcePaths = getResourcePaths();
    initializedVariables = new HashMap<String, Object>();

    // Always import BasicTypeSystem
    addTypeSystem("org.apache.uima.ruta.engine.BasicTypeSystem");
  }

  /**
   * Import short type names.
   *
   * @param cas
   *          Cas to initialize the types for.
   * @param strictImport
   *          Specify whether all types should be imported (false) or only types
   */
  public void initializeTypes(CAS cas, boolean strictImport) {
    this.cas = cas;
    try {
      if (strictImport) {
        importDeclaredTypes(cas.getTypeSystem());
        importDeclaredTypesystems(cas.getTypeSystem());
        importTypeAliases(cas.getTypeSystem());
        importPackageAliases(cas.getTypeSystem());
      } else {
        // import all types known to the cas
        importAllTypes(cas.getTypeSystem());
        importTypeAliases(cas.getTypeSystem());
        importPackageAliases(cas.getTypeSystem());
      }

      // "Document" can be resolved to "uima.tcas.DocumentAnnotation" or
      // "org.apache.uima.ruta.type.Document",
      // we force it to the former
      ambiguousTypeAlias.remove("Document");
      namespaces.remove("Document");
      Type documentType = cas.getTypeSystem().getType(UIMAConstants.TYPE_DOCUMENT);
      addType("Document", documentType);

      Type annotationType = cas.getJCas().getCasType(org.apache.uima.jcas.tcas.Annotation.type);
      addType("Annotation", annotationType);
    } catch (CASException e) {
      UIMAFramework.getLogger(getClass()).log(SEVERE, "Cannot initialize types.", e);
    } catch (InvalidXMLException e) {
      UIMAFramework.getLogger(getClass()).log(SEVERE, "Cannot initialize types.", e);
    }

  }

  /**
   * Imports all types that are known to a type system.
   *
   * @param ts
   *          Type system to import.
   * @throws CASException
   */
  private void importAllTypes(TypeSystem ts) throws CASException {
    Type topType = ts.getTopType();
    if (topType != null) {
      List<Type> list = ts.getProperlySubsumedTypes(topType);
      for (Type type : list) {
        addType(type);
      }
    }
  }

  /**
   * Import all types that are declared by the script.
   *
   * @param casTS
   *          Type system containing all known types.
   * @throws InvalidXMLException
   *           When import cannot be resolved.
   */
  private void importDeclaredTypes(TypeSystem casTS) throws InvalidXMLException {
    for (String name : declaredAnnotationTypes) {
      Type type = casTS.getType(name);
      if (type != null) {
        addType(type);
      } else {
        throw new RuntimeException("Type '" + name + "' not found");
      }
    }
  }

  /**
   * Import all typesystems that are imported in the script.
   *
   * @param casTS
   *          Type system containing all known types.
   * @throws InvalidXMLException
   *           When import cannot be resolved.
   */
  private void importDeclaredTypesystems(TypeSystem casTS) throws InvalidXMLException {
    String[] descriptors = typesystems.toArray(new String[typesystems.size()]);
    TypeSystemDescription ts = TypeSystemDescriptionFactory
            .createTypeSystemDescription(descriptors);
    ts.resolveImports();
    for (TypeDescription td : ts.getTypes()) {
      Type type = casTS.getType(td.getName());
      if (type != null) {
        addType(type);
      } else {
        throw new RuntimeException("Type '" + td.getName() + "' not found");
      }
    }
  }

  /**
   * Imports all type aliases.
   *
   * @param casTS
   *          Cas type system.
   */
  private void importTypeAliases(TypeSystem casTS) {
    for (List<Alias> aliases : typeImports.values()) {
      for (Alias alias : aliases) {
        Type type = casTS.getType(alias.longName);
        if (type == null) {
          throw new RuntimeException("Type '" + alias.longName + "' not found");
        }
        addType(alias.shortName, casTS.getType(alias.longName));
      }
    }
  }

  /**
   * Import all packages that are imported by the script.
   *
   * @param casTS
   *          Type system containing all known types.
   */
  private void importPackageAliases(TypeSystem casTS) {
    Iterator<Type> iter = casTS.getTypeIterator();
    while (iter.hasNext()) {
      Type type = iter.next();
      String name = type.getName();
      String pkg = name.substring(0, Math.max(name.lastIndexOf('.'), 0));
      List<String> aliases = packageImports.get(pkg);
      if (aliases != null) {
        for (String alias : aliases) {
          if (alias.isEmpty()) {
            addType(type);
          } else {
            addType(alias + "." + type.getShortName(), type);
          }
        }
      }
    }
  }

  public String[] getResourcePaths() {
    if (resourcePaths == null) {
      RutaBlock parent = owner.getParent();
      if (parent != null) {
        return parent.getEnvironment().getResourcePaths();
      }
    }
    return resourcePaths;
  }

  public void setResourcePaths(String[] resourcePaths) {
    this.resourcePaths = resourcePaths;
  }

  public boolean ownsType(String match) {
    match = expand(match);
    return types.keySet().contains(match);
  }

  private String expand(String string) {
    String complete = namespaces.get(string);
    if (complete == null) {
      if (!string.contains(".")) {
        complete = namespaces.get(string);
        if (complete == null) {
          complete = string;
        }
      } else {
        complete = string;
        String[] split = complete.split("\\p{Punct}");
        String name = split[split.length - 1];
        importType(complete, name);
      }
    }
    return complete;
  }

  /**
   * Resolves an annotation type.
   *
   * @param match
   *          Annotation type to resolve.
   * @return Resolved annotation type or null if match is unknown.
   * @throws IllegalArgumentException
   *           When {@code match} is ambiguous.
   */
  public Type getType(String match) {
    // make sure that match is not ambiguous
    Set<String> ambiguousTargets = ambiguousTypeAlias.get(match);
    if (ambiguousTargets != null) {
      StringBuilder message = new StringBuilder(match);
      message.append(" is ambiguous, use one of the following instead : ");
      for (String target : ambiguousTargets) {
        message.append(target).append(' ');
      }
      throw new IllegalArgumentException(message.toString());
    }

    // try to resolve match
    String expanded = expand(match);
    Type type = types.get(expanded);
    if (type == null) {
      RutaBlock parent = owner.getParent();
      if (parent != null) {
        type = parent.getEnvironment().getType(match);
      }
    }
    return type;
  }

  public void addType(String string, Type type) {
    importType(type.getName(), string);
    types.put(type.getName(), type);
  }

  public void addType(Type type) {
    addType(type.getShortName(), type);
  }

  public void declareType(String name) {
    declaredAnnotationTypes.add(name);
  }

  /**
   * Add a typesystem to the script.
   *
   * @param descriptor
   *          Type system's descriptor path.
   */
  public void addTypeSystem(String descriptor) {
    typesystems.add(descriptor);
  }

  /**
   * Import a type in the current namespace.
   *
   * @param longName
   *          Complete type name.
   * @param shortName
   *          Short type name (without namespace).
   */
  private void importType(String longName, String shortName) {
    Set<String> targets = ambiguousTypeAlias.get(shortName);
    if (targets != null) {
      // shortName is already ambiguous, add longName to its list of possible targets
      targets.add(longName);
    } else {
      String existing = namespaces.put(shortName, longName);

      if (existing != null && !existing.equals(longName)) {
        // shortName can now be resolved to "existing" or "longName"
        targets = new HashSet<String>(2);
        targets.add(existing);
        targets.add(longName);

        // add existing mapping and longName to its list of possible targets
        ambiguousTypeAlias.put(shortName, targets);

        // remove shortName from the namespace because it is ambiguous
        namespaces.remove(shortName);
      }
    }
  }

  /**
   * Import a type from a type system.
   *
   * @param typesystem
   *          Typesystem from which to import the type or null.
   * @param longName
   *          Type to import.
   * @param shortName
   *          Short name to use for this type.
   */
  public void importTypeFromTypeSystem(String typesystem, String longName, String shortName) {
    String key = typesystem != null ? typesystem : "";
    List<Alias> aliases = typeImports.get(key);

    if (aliases == null) {
      aliases = new ArrayList<Alias>();
      typeImports.put(key, aliases);
    }

    aliases.add(new Alias(longName, shortName));
  }

  /**
   * Import a type from a type system.
   *
   * The type is aliased by its unqualified name.
   *
   * @param typesystem
   *          Typesystem from which to import the type or null.
   * @param longName
   *          Type to import.
   */
  public void importTypeFromTypeSystem(String typesystem, String longName) {
    importTypeFromTypeSystem(typesystem, longName,
            longName.substring(longName.lastIndexOf('.') + 1));
  }

  /**
   * Import all the types from a package.
   *
   * @param typesystem
   *          Type system describing the package to load.
   * @param packageName
   *          Package to load or null to load all packages.
   * @param alias
   *          Alias of the package. Null or empty string to use no alias.
   */
  public void importPackageFromTypeSystem(String typesystem, String packageName, String alias) {
    TypeSystemDescription tsd = TypeSystemDescriptionFactory
            .createTypeSystemDescription(typesystem);
    try {
      tsd.resolveImports(getResourceManager());
    } catch (InvalidXMLException e) {
      throw new RuntimeException("Cannot resolve imports in " + typesystem, e);
    }

    for (TypeDescription td : tsd.getTypes()) {
      String qname = td.getName();
      if (packageName == null
              || (qname.startsWith(packageName) && qname.indexOf('.', packageName.length() + 1) == -1)) {
        // td is in packageName
        if (alias != null) {
          String shortName = alias + "." + qname.substring(qname.lastIndexOf('.') + 1);
          importTypeFromTypeSystem(typesystem, qname, shortName);
        } else {
          importTypeFromTypeSystem(typesystem, qname);
        }
      }
    }
  }

  /**
   * Imports all the packages from the specified type system.
   *
   * @param typesystem
   *          Typesystem to load.
   * @param alias
   *          Alias for all the packages.
   */
  public void importAllPackagesFromTypeSystem(String typesystem, String alias) {
    importPackageFromTypeSystem(typesystem, null, alias);
  }

  /**
   * Import all the types from a package that are available at runtime.
   *
   * @param packageName
   *          Package to load.
   * @param alias
   *          Alias of the package. Null or empty string to use no alias.
   */
  public void importPackage(String packageName, String alias) {
    List<String> aliases = packageImports.get(packageName);
    if (aliases == null) {
      aliases = new ArrayList<String>(1);
      packageImports.put(packageName, aliases);
    }

    aliases.add(alias == null ? "" : alias);
  }

  public RutaWordList getWordList(String list) {
    RutaWordList result = wordLists.get(list);
    UimaContext context = owner.getContext();
    Boolean dictRemoveWS = false;
    if (context != null) {
      dictRemoveWS = (Boolean) context
              .getConfigParameterValue(RutaEngine.PARAM_DICT_REMOVE_WS);
      if (dictRemoveWS == null) {
        dictRemoveWS = false;
      }
    }
    if (result == null) {
      if (list.endsWith("txt") || list.endsWith("twl") || list.endsWith("mtwl")) {
        ResourceLoader resourceLoader = new RutaResourceLoader(getResourcePaths());
        Resource resource = resourceLoader.getResource(list);
        if (resource.exists()) {
          try {
            if (list.endsWith("mtwl")) {
              wordLists.put(list, new MultiTreeWordList(resource));
            } else {
              wordLists.put(list, new TreeWordList(resource, dictRemoveWS));
            }
          } catch (IOException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
                    "Error reading word list" + list, e);
          }
        } else {
          Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Can't find " + list + "!");
        }
      } else {
        try {
          RutaWordList rutaTable = (RutaWordList) context.getResourceObject(list);
          wordLists.put(list, rutaTable);
        } catch (ResourceAccessException e) {
          Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
                  "Can't find external resource table" + list, e);
        }
      }
    }

    return wordLists.get(list);
  }

  public RutaTable getWordTable(String table) {
    RutaTable result = tables.get(table);
    if (result == null) {
      if (table.endsWith("csv")) {
        ResourceLoader resourceLoader = new RutaResourceLoader(getResourcePaths());
        Resource resource = resourceLoader.getResource(table);
        if (resource.exists()) {
          try {
            tables.put(table, new CSVTable(resource));
          } catch (IOException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
                    "Error reading csv table " + table, e);
          }
        } else {
          Logger.getLogger(this.getClass().getName())
                  .log(Level.SEVERE, "Can't find " + table + "!");
        }
      } else {
        try {
          RutaTable rutaTable = (RutaTable) owner.getContext().getResourceObject(table);
          tables.put(table, rutaTable);
        } catch (ResourceAccessException e) {
          Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
                  "Can't find external resource table" + table, e);
        }
      }
    }

    return tables.get(table);
  }

  public void addVariable(String name, Class<?> type, Class<?> generic) {
    variableTypes.put(name, type);
    if (generic != null) {
      variableGenericTypes.put(name, generic);
    }
    variableValues.put(name, getInitialValue(name, type));
  }

  @SuppressWarnings("unchecked")
  private Object getInitialValue(String name, Class<?> type) {
    Object init = initializedVariables.get(name);
    if (init != null) {
      if (init instanceof List) {
        ArrayList<Object> list = new ArrayList<Object>();
        list.addAll((Collection<? extends Object>) init);
        return list;
      }
      return init;
    }
    if (Integer.class.equals(type)) {
      return 0;
    } else if (Double.class.equals(type)) {
      return 0d;
    } else if (Float.class.equals(type)) {
      return 0f;
    } else if (String.class.equals(type)) {
      return "";
    } else if (Boolean.class.equals(type)) {
      return false;
    } else if (Type.class.equals(type)) {
      if (cas == null) {
        return annotationTypeDummy;
      } else {
        return cas.getAnnotationType();
      }
    } else if (List.class.equals(type)) {
      return new ArrayList<Object>();
    }
    return null;
  }

  public void addVariable(String name, String type) {
    addVariable(name, availableTypes.get(type), availableListTypes.get(type));
  }

  public boolean ownsVariable(String name) {
    return variableTypes.containsKey(name);
  }

  public boolean ownsVariableOfType(String name, String type) {
    Class<?> varclass = variableTypes.get(name);
    Class<?> aclass = availableTypes.get(type);
    boolean list = true;
    if (aclass.equals(List.class)) {
      Class<?> vt = variableGenericTypes.get(name);
      Class<?> at = availableListTypes.get(type);
      list = vt != null && vt.equals(at);
    }
    return list && varclass != null && varclass.equals(aclass);
  }

  public boolean isVariable(String name) {
    return ownsVariable(name) || owner.getParent().getEnvironment().isVariable(name);
  }

  public boolean isVariableOfType(String name, String type) {
    return ownsVariableOfType(name, type)
            || (owner.getParent() != null && owner.getParent().getEnvironment()
                    .isVariableOfType(name, type));
  }

  public Class<?> getVariableType(String name) {
    Class<?> result = variableTypes.get(name);
    if (result != null) {
      return result;
    } else if (owner.getParent() != null) {
      return owner.getParent().getEnvironment().getVariableType(name);
    }
    return null;
  }

  public Class<?> getVariableGenericType(String name) {
    Class<?> result = variableGenericTypes.get(name);
    if (result != null) {
      return result;
    } else if (owner.getParent() != null) {
      return owner.getParent().getEnvironment().getVariableGenericType(name);
    }
    return null;
  }

  public <T> T getVariableValue(String name, Class<T> type) {
    boolean containsKey = variableValues.containsKey(name);
    Object result = variableValues.get(name);

    if (result instanceof String && type.equals(Type.class)) {
      // "cast" string to type, because initial values were set when there was no cas/type system
      // yet
      result = types.get(result);
    }

    if (containsKey && result == null) {
      // TODO find the problem with the null values!
      // this might now work for word lists in another env.
      return type.cast(getInitialValue(name, type));
    }
    if (result == annotationTypeDummy) {
      return type.cast(cas.getAnnotationType());
    }
    if (result != null) {
      if(RutaWordList.class.isAssignableFrom(type) && result instanceof WordListExpression) {
        WordListExpression wle = (WordListExpression) result;
        RutaWordList list = wle.getList(owner);
        return type.cast(list);
      } else if(RutaTable.class.isAssignableFrom(type) && result instanceof WordTableExpression) {
        WordTableExpression wte = (WordTableExpression) result;
        RutaTable table = wte.getTable(owner);
        return type.cast(table);
      } else {
        return type.cast(result);
      }
    } else if (owner.getParent() != null) {
      return owner.getParent().getEnvironment().getVariableValue(name, type);
    }
    return null;
  }

  public Object getVariableValue(String name) {
    return getVariableValue(name, Object.class);
  }

  @SuppressWarnings("rawtypes")
  public Object getLiteralValue(String var, Object value) {
    if (ownsVariable(var)) {
      Class<?> clazz = variableTypes.get(var);
      if (value instanceof INumberExpression) {
        INumberExpression ne = (INumberExpression) value;
        if (clazz.equals(Integer.class)) {
          return ne.getIntegerValue(owner, null, null);
        } else if (clazz.equals(Double.class)) {
          return ne.getDoubleValue(owner, null, null);
        } else if (clazz.equals(Float.class)) {
          return ne.getFloatValue(owner, null, null);
        } else if (clazz.equals(String.class)) {
          return ne.getStringValue(owner, null, null);
        }
      } else if (clazz.equals(String.class) && value instanceof IStringExpression) {
        IStringExpression se = (IStringExpression) value;
        return se.getStringValue(owner, null, null);
      } else if (clazz.equals(Boolean.class) && value instanceof IBooleanExpression) {
        IBooleanExpression be = (IBooleanExpression) value;
        return be.getBooleanValue(owner, null, null);
      }
      if (clazz.equals(RutaWordList.class) && value instanceof LiteralWordListExpression) {
        return value;
      } else if (clazz.equals(RutaWordList.class) && value instanceof String) {
        return value;
      } else if (clazz.equals(RutaTable.class) && value instanceof LiteralWordTableExpression) {
        return value;
      } else if (clazz.equals(RutaTable.class) && value instanceof String) {
        return value;
      } else if (clazz.equals(List.class) && value instanceof ListExpression) {
        List list = getList((ListExpression) value);
        return list;
      } else if (clazz.equals(Type.class) && value instanceof CommonToken) {
        String typeName = ((CommonToken) value).getText();
        return typeName;
      } else if (clazz.equals(Type.class) && value instanceof SimpleTypeExpression) {
        String typeName = ((SimpleTypeExpression) value).getTypeString();
        return typeName;
      }

      return null;
    } else {
      return owner.getParent().getEnvironment().getLiteralValue(var, value);
    }
  }

  @SuppressWarnings("unchecked")
  public void setInitialVariableValue(String var, Object value) {
    if (ownsVariable(var)) {
      if (value instanceof List) {
        List<Object> initValue = new ArrayList<Object>();
        initValue.addAll((Collection<? extends Object>) value);
        initializedVariables.put(var, initValue);
      } else {
        initializedVariables.put(var, value);
      }
      setVariableValue(var, value);
    } else if (owner.getParent() != null) {
      owner.getParent().getEnvironment().setInitialVariableValue(var, value);
    }
  }

  public void setVariableValue(String var, Object value) {
    if (ownsVariable(var)) {
      Class<?> clazz = variableTypes.get(var);
      if (value == null) {
        value = getInitialValue(var, clazz);
      }
      variableValues.put(var, value);
    } else if (owner.getParent() != null) {
      owner.getParent().getEnvironment().setVariableValue(var, value);
    }
  }

  @SuppressWarnings("rawtypes")
  private List getList(ListExpression value) {
    if (value instanceof SimpleBooleanListExpression) {
      SimpleBooleanListExpression e = (SimpleBooleanListExpression) value;
      return e.getList();
    } else if (value instanceof SimpleNumberListExpression) {
      SimpleNumberListExpression e = (SimpleNumberListExpression) value;
      return e.getList();
    } else if (value instanceof SimpleStringListExpression) {
      SimpleStringListExpression e = (SimpleStringListExpression) value;
      return e.getList();
    } else if (value instanceof SimpleTypeListExpression) {
      SimpleTypeListExpression e = (SimpleTypeListExpression) value;
      return e.getList();
    }
    return null;
  }

  public void reset(CAS cas) {
    this.cas = cas;
    Set<Entry<String, Object>> entrySet = variableValues.entrySet();
    for (Entry<String, Object> entry : entrySet) {
      String key = entry.getKey();
      Object initialValue = getInitialValue(key, variableTypes.get(key));
      if (initialValue != null) {
        // not for word lists
        entry.setValue(initialValue);
      }
    }
  }

  public ResourceManager getResourceManager() {
    if (resourceManager != null) {
      return resourceManager;
    } else {
      RutaBlock parent = owner.getParent();
      if (parent != null) {
        return parent.getEnvironment().getResourceManager();
      }
    }
    // at least return default resource manager
    return UIMAFramework.newDefaultResourceManager();
  }

  public void setResourceManager(ResourceManager resourceManager) {
    this.resourceManager = resourceManager;
  }

}
