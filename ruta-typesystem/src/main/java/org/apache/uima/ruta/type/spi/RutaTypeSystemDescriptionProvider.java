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
package org.apache.uima.ruta.type.spi;

import static org.apache.uima.util.TypeSystemUtil.loadTypeSystemDescriptionsFromClasspath;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.resource.metadata.TypeDescription;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.spi.JCasClassProvider;
import org.apache.uima.spi.TypeSystemDescriptionProvider;

public class RutaTypeSystemDescriptionProvider
        implements TypeSystemDescriptionProvider, JCasClassProvider {

  @Override
  public List<TypeSystemDescription> listTypeSystemDescriptions() {

    return loadTypeSystemDescriptionsFromClasspath(getClass(), //
            "/org/apache/uima/ruta/engine/BasicTypeSystem.xml", //
            "/org/apache/uima/ruta/engine/DefaultSeederTypeSystem.xml", //
            "/org/apache/uima/ruta/engine/HtmlTypeSystem.xml", //
            "/org/apache/uima/ruta/engine/PlainTextTypeSystem.xml", //
            "/org/apache/uima/ruta/engine/RutaBasicTypeSystem.xml", //
            "/org/apache/uima/ruta/engine/RutaInternalTypeSystem.xml", //
            "/org/apache/uima/ruta/engine/SourceDocumentInformation.xml");
  }

  @SuppressWarnings("unchecked")
@Override
  public List<Class<? extends TOP>> listJCasClasses() {

    List<Class<? extends TOP>> classes = new ArrayList<>();
    ClassLoader cl = getClass().getClassLoader();

    List<TypeSystemDescription> typeSystemDescriptions = listTypeSystemDescriptions();
    for (TypeSystemDescription tsd : typeSystemDescriptions) {
      for (TypeDescription td : tsd.getTypes()) {
        try {
          classes.add((Class<? extends TOP>) cl.loadClass(td.getName()));
        } catch (ClassNotFoundException e) {
          // This is acceptable - there may not be a JCas class
        }
      }
    }

    return classes;
  }
}
