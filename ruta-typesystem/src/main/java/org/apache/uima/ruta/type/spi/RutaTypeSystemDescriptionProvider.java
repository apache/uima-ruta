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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.uima.UIMAFramework;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.resource.impl.ResourceManager_impl;
import org.apache.uima.resource.metadata.TypeDescription;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.spi.JCasClassProvider;
import org.apache.uima.spi.TypeSystemDescriptionProvider;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;

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

  public static List<TypeSystemDescription> loadTypeSystemDescriptionsFromClasspath(
          Class<?> aContext, String... typeSystemDescriptionFiles) {

    ResourceManager resMgr = new ResourceManager_impl(aContext.getClassLoader());
    try {
      List<TypeSystemDescription> typeSystemDescriptions = new ArrayList<>();

      for (String typeSystem : typeSystemDescriptionFiles) {
        URL resource = aContext.getResource(typeSystem);
        if (resource == null) {
          UIMAFramework.getLogger()
                  .error("Unable to locate type system description as a resource [{}]", typeSystem);
          continue;
        }

        try {
          TypeSystemDescription tsd = UIMAFramework.getXMLParser()
                  .parseTypeSystemDescription(new XMLInputSource(resource));
          tsd.resolveImports(resMgr);
          typeSystemDescriptions.add(tsd);
        } catch (InvalidXMLException | IOException e) {
          UIMAFramework.getLogger().error("Error loading type system description [{}] from [{}]",
                  typeSystem, resource, e);
        }
      }

      return typeSystemDescriptions;
    } finally {
      resMgr.destroy();
    }
  }
}
