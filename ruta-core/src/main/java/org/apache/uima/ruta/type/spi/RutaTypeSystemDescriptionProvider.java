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

import org.apache.uima.spi.TypeSystemProvider_ImplBase;

public class RutaTypeSystemDescriptionProvider extends TypeSystemProvider_ImplBase {

  public RutaTypeSystemDescriptionProvider() {
    setTypeSystemLocations( //
            "/org/apache/uima/ruta/engine/BasicTypeSystem.xml", //
            "/org/apache/uima/ruta/engine/DefaultSeederTypeSystem.xml", //
            "/org/apache/uima/ruta/engine/HtmlTypeSystem.xml", //
            "/org/apache/uima/ruta/engine/PlainTextTypeSystem.xml", //
            "/org/apache/uima/ruta/engine/RutaBasicTypeSystem.xml", //
            "/org/apache/uima/ruta/engine/RutaInternalTypeSystem.xml", //
            "/org/apache/uima/ruta/engine/SourceDocumentInformation.xml");
  }
}
