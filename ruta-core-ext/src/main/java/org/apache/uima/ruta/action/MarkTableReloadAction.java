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

package org.apache.uima.ruta.action;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.bool.SimpleBooleanExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.type.ITypeExpression;
import org.apache.uima.ruta.resource.CSVTable;
import org.apache.uima.ruta.resource.RutaResourceLoader;
import org.apache.uima.ruta.resource.RutaTable;
import org.apache.uima.ruta.resource.RutaWordList;
import org.apache.uima.ruta.rule.MatchContext;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.visitor.InferenceCrowd;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class MarkTableReloadAction extends AbstractRutaAction {

    private static ConcurrentHashMap<String, TableCacheEntry> tableCache = new ConcurrentHashMap<String, TableCacheEntry>();

    private final ITypeExpression typeExpr;

    private final IStringExpression tableName;

    private final Map<IStringExpression, INumberExpression> featureMap;

    private final INumberExpression indexExpr;

    private final IBooleanExpression ignoreCase;

    private final INumberExpression ignoreLength;

    private final IStringExpression ignoreChar;

    private final INumberExpression maxIgnoreChar;
    
    private IBooleanExpression ignoreWS = new SimpleBooleanExpression(true);
    
    private class TableCacheEntry {
        private RutaTable table;
        private long lastModified;

        public TableCacheEntry(RutaTable table, long lastModified) {
            this.lastModified = lastModified;
            this.table = table;
        }
    }

    public MarkTableReloadAction(ITypeExpression typeExpr, INumberExpression indexExpr,
            IStringExpression tableName, Map<IStringExpression, INumberExpression> featureMap,
            IBooleanExpression ignoreCase, INumberExpression ignoreLength,
            IStringExpression ignoreChar, INumberExpression maxIgnoreChar) {
      super();
      this.typeExpr = typeExpr;
      this.indexExpr = indexExpr;
      this.tableName = tableName;
      this.featureMap = featureMap;
      this.ignoreCase = ignoreCase;
      this.ignoreLength = ignoreLength;
      this.ignoreChar = ignoreChar;
      this.maxIgnoreChar = maxIgnoreChar;
    }
    
    public void setIgnoreWS(IBooleanExpression ignoreWS) {
      this.ignoreWS = ignoreWS;
    }

    @Override
    public void execute(MatchContext context, RutaStream stream, InferenceCrowd crowd) {

        // Get the table from the cache or reload it if it has changed
        RutaTable table = null;
        
        
        
        String tableNameValue = tableName.getStringValue(context, stream);

        ResourceLoader resourceLoader = new RutaResourceLoader(context.getParent().getEnvironment().getResourcePaths());
        Resource resource = resourceLoader.getResource(tableNameValue);
        if (resource.exists()) {
            File resourceFile = null;
            try {
                resourceFile = resource.getFile();
            } catch (IOException e1) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Unable to obtain file from resource: " + tableNameValue, e1);
            }

            TableCacheEntry cacheEntry = tableCache.get(tableNameValue);
            if (cacheEntry == null || cacheEntry != null && resourceFile.lastModified() > cacheEntry.lastModified) {
                Logger.getLogger(this.getClass().getName()).info("Creating Table Word List from resource: " + tableNameValue);

                try {
                    table = new CSVTable(resource, CSVTable.DEFAULT_CSV_SEPARATOR);
                } catch (IOException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Unable to load CSV file: " + tableNameValue, e);
                }

                tableCache.put(tableNameValue, new TableCacheEntry(table, resourceFile.lastModified()));
            } else {
                table = cacheEntry.table;
            }

        } else {
            Logger.getLogger(this.getClass().getName()).severe("Can't find resource: " + tableNameValue);
        }
        
        
        // The original code from the RUTA action
        int index = indexExpr.getIntegerValue(context, stream);
        Type type = typeExpr.getType(context, stream);
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (IStringExpression each : featureMap.keySet()) {
          map.put(each.getStringValue(context, stream), featureMap.get(each)
                  .getIntegerValue(context, stream));
        }

        boolean ignoreCaseValue = ignoreCase != null ? ignoreCase.getBooleanValue(context, stream) : false;
        int ignoreLengthValue = ignoreLength != null ? ignoreLength.getIntegerValue(context, stream) : 0;
        String ignoreCharValue = ignoreChar != null ? ignoreChar.getStringValue(context, stream) : "";
        int maxIgnoreCharValue = maxIgnoreChar != null ? maxIgnoreChar.getIntegerValue(context, stream) : 0;
        boolean ignoreWSValue = ignoreWS != null ? ignoreWS.getBooleanValue(context, stream) : false;

        
        RutaWordList wordList = table.getWordList(index, context.getParent());
        Collection<AnnotationFS> found = wordList.find(stream, ignoreCaseValue, ignoreLengthValue,
                ignoreCharValue.toCharArray(), maxIgnoreCharValue, ignoreWSValue);
        for (AnnotationFS annotationFS : found) {
          // HOTFIX: for feature assignment
          String candidate = stream.getVisibleCoveredText(annotationFS);
          if(!StringUtils.isBlank(ignoreCharValue)) {
            for (int i = 0; i < maxIgnoreCharValue; i++) {
              candidate = candidate.replaceFirst("[" + ignoreCharValue + "]", "");
            }
          }
          List<String> rowWhere = table.getRowWhere(index - 1, candidate);
          if (rowWhere.isEmpty() && ignoreCaseValue && candidate.length() > ignoreLengthValue) {
            // TODO: does not cover all variants
            rowWhere = table.getRowWhere(index - 1, candidate.toLowerCase());
          }
          FeatureStructure newFS = stream.getCas().createFS(type);
          if (newFS instanceof Annotation) {
            Annotation a = (Annotation) newFS;
            a.setBegin(annotationFS.getBegin());
            a.setEnd(annotationFS.getEnd());
            stream.addAnnotation(a, context.getRuleMatch());
          }
          TOP newStructure = null;
          if (newFS instanceof TOP) {
            newStructure = (TOP) newFS;
            fillFeatures(newStructure, map, annotationFS, context.getElement(), rowWhere, stream);
            newStructure.addToIndexes();
          }
        }
    }
    
    private void fillFeatures(TOP structure, Map<String, Integer> map, AnnotationFS annotationFS,
            RuleElement element, List<String> row, RutaStream stream) {
      List<?> featuresList = structure.getType().getFeatures();
      for (int i = 0; i < featuresList.size(); i++) {
        Feature targetFeature = (Feature) featuresList.get(i);
        String name = targetFeature.getName();
        String shortFName = name.substring(name.indexOf(":") + 1, name.length());
        Integer entryIndex = map.get(shortFName);
        Type range = targetFeature.getRange();
        if (entryIndex != null && row.size() >= entryIndex) {
          String value = row.get(entryIndex - 1);
          if (range.getName().equals(CAS.TYPE_NAME_STRING)) {
            structure.setStringValue(targetFeature, value);
          } else if (range.getName().equals(CAS.TYPE_NAME_INTEGER)) {
            Integer integer = Integer.parseInt(value);
            structure.setIntValue(targetFeature, integer);
          } else if (range.getName().equals(CAS.TYPE_NAME_DOUBLE)) {
            Double d = Double.parseDouble(value);
            structure.setDoubleValue(targetFeature, d);
          } else if (range.getName().equals(CAS.TYPE_NAME_FLOAT)) {
            Float d = Float.parseFloat(value);
            structure.setFloatValue(targetFeature, d);
          } else if (range.getName().equals(CAS.TYPE_NAME_BYTE)) {
            Byte d = Byte.parseByte(value);
            structure.setByteValue(targetFeature, d);
          } else if (range.getName().equals(CAS.TYPE_NAME_SHORT)) {
            Short d = Short.parseShort(value);
            structure.setShortValue(targetFeature, d);
          } else if (range.getName().equals(CAS.TYPE_NAME_LONG)) {
            Long d = Long.parseLong(value);
            structure.setLongValue(targetFeature, d);
          } else if (range.getName().equals(CAS.TYPE_NAME_BOOLEAN)) {
            Boolean b = Boolean.parseBoolean(value);
            structure.setBooleanValue(targetFeature, b);
          } else {
          }
        }

      }
    }
    

    public ITypeExpression getTypeExpr() {
        return typeExpr;
      }

      public IStringExpression getTableExpr() {
        return tableName;
      }

      public Map<IStringExpression, INumberExpression> getFeatureMap() {
        return featureMap;
      }

      public INumberExpression getIndexExpr() {
        return indexExpr;
      }

      public IBooleanExpression getIgnoreCase() {
        return ignoreCase;
      }

      public INumberExpression getIgnoreLength() {
        return ignoreLength;
      }

      public IStringExpression getIgnoreChar() {
        return ignoreChar;
      }

      public INumberExpression getMaxIgnoreChar() {
        return maxIgnoreChar;
      }

      
}
