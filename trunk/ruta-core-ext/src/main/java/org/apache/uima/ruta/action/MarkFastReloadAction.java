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
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.uima.UimaContext;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.engine.RutaEngine;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.bool.SimpleBooleanExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.number.SimpleNumberExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.string.IStringListExpression;
import org.apache.uima.ruta.expression.type.ITypeExpression;
import org.apache.uima.ruta.resource.RutaResourceLoader;
import org.apache.uima.ruta.resource.TreeWordList;
import org.apache.uima.ruta.rule.MatchContext;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleMatch;
import org.apache.uima.ruta.visitor.InferenceCrowd;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class MarkFastReloadAction extends AbstractMarkAction {

    private static ConcurrentHashMap<String, TwlCacheEntry> twlCache = new ConcurrentHashMap<String, TwlCacheEntry>();

    private IStringExpression listName;

    private IStringListExpression stringList;

    private IBooleanExpression ignore;

    private INumberExpression ignoreLength;

    private IBooleanExpression ignoreWS;

    private class TwlCacheEntry {
        private TreeWordList twl;
        private long lastModified;

        public TwlCacheEntry(TreeWordList twl, long lastModified) {
            this.lastModified = lastModified;
            this.twl = twl;
        }
    }

    public MarkFastReloadAction(ITypeExpression type, IStringExpression listName, IBooleanExpression ignore, INumberExpression ignoreLength, IBooleanExpression ignoreWS) {
        super(type);
        this.listName = listName;
        this.ignore = ignore == null ? new SimpleBooleanExpression(false) : ignore;
        this.ignoreLength = ignoreLength == null ? new SimpleNumberExpression(Integer.valueOf(0)) : ignoreLength;
        this.ignoreWS = ignoreWS == null ? new SimpleBooleanExpression(true) : ignoreWS;
    }

    @Override
    public void execute(MatchContext context, RutaStream stream, InferenceCrowd crowd) {
      
        TreeWordList wl = null;

        RuleMatch match = context.getRuleMatch();
        RuleElement element = context.getElement();
        String listNameValue = listName.getStringValue(context, stream);

        ResourceLoader resourceLoader = new RutaResourceLoader(element.getParent().getEnvironment().getResourcePaths());
        Resource resource = resourceLoader.getResource(listNameValue);
        if (resource.exists()) {
            File resourceFile = null;
            try {
                resourceFile = resource.getFile();
            } catch (IOException e1) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Unable to obtain file from resource: " + listNameValue, e1);
            }

            TwlCacheEntry cacheEntry = twlCache.get(listNameValue);
            if (cacheEntry == null || cacheEntry != null && resourceFile.lastModified() > cacheEntry.lastModified) {
                Logger.getLogger(this.getClass().getName()).info("Creating Tree Word List from resource: " + listNameValue);

                UimaContext uimaContext = element.getParent().getContext();
                Boolean dictRemoveWS = false;
                if (uimaContext != null) {
                    dictRemoveWS = (Boolean) uimaContext.getConfigParameterValue(RutaEngine.PARAM_DICT_REMOVE_WS);
                    if (dictRemoveWS == null) {
                        dictRemoveWS = false;
                    }
                }

                try {
                    wl = new TreeWordList(resource, dictRemoveWS);
                } catch (IOException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Unable to create TWL: " + listNameValue, e);
                }

                twlCache.put(listNameValue, new TwlCacheEntry(wl, resourceFile.lastModified()));
            } else {
                wl = cacheEntry.twl;
            }

        } else {
            Logger.getLogger(this.getClass().getName()).severe("Can't find resource: " + listNameValue);
        }

        List<AnnotationFS> matchedAnnotationsOf = match.getMatchedAnnotationsOfElement(element);
        for (AnnotationFS annotationFS : matchedAnnotationsOf) {
            RutaStream windowStream = stream.getWindowStream(annotationFS, annotationFS.getType());
            Collection<AnnotationFS> found = wl.find(windowStream, this.getIgnore().getBooleanValue(context, stream),
                    this.getIgnoreLength().getIntegerValue(context, stream), null, 0, this.getIgnoreWS().getBooleanValue(context, stream));
            for (AnnotationFS annotation : found) {
                createAnnotation(annotation, context, windowStream);
            }
        }
    }

    public IStringExpression getListName() {
        return listName;
    }

    public IStringListExpression getStringList() {
        return stringList;
    }

    public IBooleanExpression getIgnore() {
        return ignore;
    }

    public INumberExpression getIgnoreLength() {
        return ignoreLength;
    }

    public IBooleanExpression getIgnoreWS() {
        return ignoreWS;
    }

    
}
