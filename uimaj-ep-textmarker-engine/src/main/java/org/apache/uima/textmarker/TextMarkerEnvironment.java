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

package org.apache.uima.textmarker;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.jcas.tcas.DocumentAnnotation;
import org.apache.uima.textmarker.action.AbstractTextMarkerAction;
import org.apache.uima.textmarker.condition.AbstractTextMarkerCondition;
import org.apache.uima.textmarker.expression.bool.BooleanExpression;
import org.apache.uima.textmarker.expression.list.ListExpression;
import org.apache.uima.textmarker.expression.list.SimpleBooleanListExpression;
import org.apache.uima.textmarker.expression.list.SimpleNumberListExpression;
import org.apache.uima.textmarker.expression.list.SimpleStringListExpression;
import org.apache.uima.textmarker.expression.list.SimpleTypeListExpression;
import org.apache.uima.textmarker.expression.number.NumberExpression;
import org.apache.uima.textmarker.expression.resource.LiteralWordListExpression;
import org.apache.uima.textmarker.expression.resource.LiteralWordTableExpression;
import org.apache.uima.textmarker.expression.string.StringExpression;
import org.apache.uima.textmarker.resource.CSVTable;
import org.apache.uima.textmarker.resource.MultiTreeWordList;
import org.apache.uima.textmarker.resource.TextMarkerTable;
import org.apache.uima.textmarker.resource.TextMarkerWordList;
import org.apache.uima.textmarker.resource.TreeWordList;

public class TextMarkerEnvironment {

  private final Object annotationTypeDummy = new Object();

  private Map<String, Type> types;

  private Map<String, TextMarkerWordList> wordLists;

  private Map<String, CSVTable> tables;

  private TextMarkerBlock owner;

  private Map<String, String> namespaces;

  private Map<String, Object> variableValues;

  private Map<String, Class<?>> variableTypes;

  private Map<String, Class<?>> availableTypes;

  private Map<String, Class<?>> variableGenericTypes;

  private Map<String, Class<?>> availableListTypes;

  private String[] resourcePaths = null;

  private CAS cas;

  private Map<String, Object> initializedVariables;

  public TextMarkerEnvironment(TextMarkerBlock owner) {
    super();
    this.owner = owner;
    types = new HashMap<String, Type>();
    namespaces = new HashMap<String, String>();
    wordLists = new HashMap<String, TextMarkerWordList>();
    tables = new HashMap<String, CSVTable>();
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
    availableTypes.put("CONDITION", AbstractTextMarkerCondition.class);
    availableTypes.put("ACTION", AbstractTextMarkerAction.class);
    availableTypes.put("WORDLIST", TextMarkerWordList.class);
    availableTypes.put("WORDTABLE", TextMarkerTable.class);
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
  }

  public void initializeTypes(CAS cas) {
    this.cas = cas;
    Type topType = null;
    try {
      topType = cas.getJCas().getCasType(TOP.type);
      if (topType != null) {
        List<Type> list = cas.getTypeSystem().getProperlySubsumedTypes(topType);
        for (Type type : list) {
          addType(type);
        }
      }
      Type documentType = cas.getJCas().getCasType(DocumentAnnotation.type);
      addType("Document", documentType);
      Type annotationType = cas.getJCas().getCasType(org.apache.uima.jcas.tcas.Annotation.type);
      addType("Annotation", annotationType);
    } catch (CASException e) {
    }

  }

  public String[] getResourcePaths() {
    if (resourcePaths == null) {
      TextMarkerBlock parent = owner.getParent();
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
    String complete;
    if (string.indexOf(".") == -1) {
      complete = namespaces.get(string);
      if (complete == null) {
        complete = string;
      }
    } else {
      complete = string;
      String[] split = complete.split("\\p{Punct}");
      String name = split[split.length - 1];
      namespaces.put(name, complete);
    }
    return complete;
  }

  public Type getType(String match) {
    String expanded = expand(match);
    Type type = types.get(expanded);
    if (type == null) {
      TextMarkerBlock parent = owner.getParent();
      if (parent != null) {
        type = parent.getEnvironment().getType(match);
      }
    }
    return type;
  }

  public void addType(String string, Type type) {
    namespaces.put(string, type.getName());
    types.put(type.getName(), type);
  }

  public void addType(Type type) {
    addType(type.getShortName(), type);
  }

  public TextMarkerWordList getWordList(String list) {
    TextMarkerWordList result = wordLists.get(list);
    if (result == null) {
      boolean found = false;
      for (String eachPath : resourcePaths) {
        File file = new File(eachPath, list);
        if (!file.exists()) {
          continue;
        }
        found = true;
        if (file.getName().endsWith("mtwl")) {
          wordLists.put(list, new MultiTreeWordList(file.getAbsolutePath()));
        } else {
          wordLists.put(list, new TreeWordList(file.getAbsolutePath()));
        }
        break;
      }
      if (!found) {
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Can't find " + list + "!");
      }
    }
    return wordLists.get(list);
  }

  public TextMarkerTable getWordTable(String table) {
    TextMarkerTable result = tables.get(table);
    if (result == null) {
      boolean found = false;
      for (String eachPath : resourcePaths) {
        File file = new File(eachPath, table);
        if (!file.exists()) {
          continue;
        }
        found = true;
        tables.put(table, new CSVTable(file.getAbsolutePath()));
        break;
      }
      if (!found) {
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Can't find " + table + "!");
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

  private Object getInitialValue(String name, Class<?> type) {
    Object init = initializedVariables.get(name);
    if (init != null) {
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
    if (containsKey && result == null) {
      // TODO find the problem with the null values!
      // this might now work for word lists in another env.
      return type.cast(getInitialValue(name, type));
    }
    if (result == annotationTypeDummy) {
      return type.cast(cas.getAnnotationType());
    }
    if (result != null) {
      return type.cast(result);
    } else if (owner.getParent() != null) {
      return owner.getParent().getEnvironment().getVariableValue(name, type);
    }
    return null;
  }

  public Object getVariableValue(String name) {
    return getVariableValue(name, Object.class);
  }

//  public Object getLiteralValue(String var, Object value) {
//    if (ownsVariable(var)) {
//      Class<?> clazz = variableTypes.get(var);
//      if (value instanceof NumberExpression) {
//        NumberExpression ne = (NumberExpression) value;
//        if (clazz.equals(Integer.class)) {
//          return ne.getIntegerValue(owner);
//        } else if (clazz.equals(Double.class)) {
//          return ne.getDoubleValue(owner);
//        } else if (clazz.equals(Float.class)) {
//          return ne.getFloatValue(owner);
//        } else if (clazz.equals(String.class)) {
//          return ne.getStringValue(owner);
//        }
//      } else if (clazz.equals(String.class) && value instanceof StringExpression) {
//        StringExpression se = (StringExpression) value;
//        return se.getStringValue(owner);
//      } else if (clazz.equals(Boolean.class) && value instanceof BooleanExpression) {
//        BooleanExpression be = (BooleanExpression) value;
//        return be.getBooleanValue(owner);
//      } else if(clazz.equals(TextMarkerWordList.class)||clazz.equals(TextMarkerTable.class) ) {
//        // TODO: refactor this: lists are handled by setVariableValue... resources should be ae resources in future!
//        return value;
//      }
//      return null;
//    } else {
//      return owner.getParent().getEnvironment().getLiteralValue(var, value);
//    }
//  }

  public Object getLiteralValue(String var, Object value) {
    if (ownsVariable(var)) {
      Class<?> clazz = variableTypes.get(var);
      if (value instanceof NumberExpression) {
        NumberExpression ne = (NumberExpression) value;
        if (clazz.equals(Integer.class)) {
          return ne.getIntegerValue(owner);
        } else if (clazz.equals(Double.class)) {
          return ne.getDoubleValue(owner);
        } else if (clazz.equals(Float.class)) {
          return ne.getFloatValue(owner);
        } else if (clazz.equals(String.class)) {
          return ne.getStringValue(owner);
        }
      } else if (clazz.equals(String.class) && value instanceof StringExpression) {
        StringExpression se = (StringExpression) value;
        return se.getStringValue(owner);
      } else if (clazz.equals(Boolean.class) && value instanceof BooleanExpression) {
        BooleanExpression be = (BooleanExpression) value;
        return be.getBooleanValue(owner);
      }
      if (clazz.equals(TextMarkerWordList.class) && value instanceof LiteralWordListExpression) {
        LiteralWordListExpression lle = (LiteralWordListExpression) value;
        String path = lle.getText();
        TextMarkerWordList wordList = getWordList(path);
        return wordList;
      } else if (clazz.equals(TextMarkerWordList.class)) {
        TextMarkerWordList list = getWordList((String) value);
        return list;
      } else if (clazz.equals(TextMarkerTable.class) && value instanceof LiteralWordTableExpression) {
        LiteralWordTableExpression lte = (LiteralWordTableExpression) value;
        String path = lte.getText();
        TextMarkerTable table = getWordTable(path);
        return table;
      } else if (clazz.equals(TextMarkerTable.class)) {
        TextMarkerTable table = getWordTable((String) value);
        return table;
      } else if (clazz.equals(List.class) && value instanceof ListExpression) {
        List list = getList((ListExpression) value);
        return list;
      } 
      
      return null;
    } else {
      return owner.getParent().getEnvironment().getLiteralValue(var, value);
    }
  }
  
  
  public void setInitialVariableValue(String var, Object value) {
    if (ownsVariable(var)) {
      initializedVariables.put(var, value);
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

//  public void setVariableValue(String var, Object value) {
//    if (ownsVariable(var)) {
//      Class<?> clazz = variableTypes.get(var);
//      if (clazz.equals(TextMarkerWordList.class) && value instanceof LiteralWordListExpression) {
//        LiteralWordListExpression lle = (LiteralWordListExpression) value;
//        String path = lle.getText();
//        TextMarkerWordList wordList = getWordList(path);
//        variableValues.put(var, wordList);
//      } else if (clazz.equals(TextMarkerWordList.class)) {
//        TextMarkerWordList list = getWordList((String) value);
//        variableValues.put(var, list);
//      } else if (clazz.equals(TextMarkerTable.class) && value instanceof LiteralWordTableExpression) {
//        LiteralWordTableExpression lte = (LiteralWordTableExpression) value;
//        String path = lte.getText();
//        TextMarkerTable table = getWordTable(path);
//        variableValues.put(var, table);
//      } else if (clazz.equals(TextMarkerTable.class)) {
//        TextMarkerTable table = getWordTable((String) value);
//        variableValues.put(var, table);
//      } else if (clazz.equals(List.class) && value instanceof ListExpression) {
//        List list = getList((ListExpression) value);
//        variableValues.put(var, list);
//      } else {
//        if (value == null) {
//          value = getInitialValue(var, clazz);
//        }
//        variableValues.put(var, value);
//      }
//    } else if (owner.getParent() != null) {
//      owner.getParent().getEnvironment().setVariableValue(var, value);
//    }
//  }
  
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

}
