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

package org.apache.uima.ruta.block;

import java.util.List;

import org.apache.uima.ruta.RutaElement;
import org.apache.uima.ruta.expression.RutaExpression;
import org.apache.uima.ruta.extensions.IRutaBlockExtension;
import org.apache.uima.ruta.extensions.RutaParseException;
import org.apache.uima.ruta.verbalize.RutaVerbalizer;

public class DocumentBlockExtension implements IRutaBlockExtension {

  private final String[] knownExtensions = new String[] { "DOCUMENTBLOCK" };

  private final Class<?>[] extensions = new Class[] { DocumentBlock.class };

  public String verbalize(RutaElement element, RutaVerbalizer verbalizer) {
    if (element instanceof DocumentBlock) {
      DocumentBlock b = (DocumentBlock) element;
      String verbalize = verbalizer.verbalize(b, false);
      verbalize = verbalize.replaceFirst("BLOCK", verbalizeName(element));
      return verbalize;
    } else {
      return "UnknownBlock";
    }
  }

  @Override
  public RutaBlock createBlock(String name, List<RutaExpression> args, RutaBlock env)
          throws RutaParseException {
    return new DocumentBlock(env, name);
  }


  public String verbalizeName(RutaElement element) {
    return knownExtensions[0];
  }

  public String[] getKnownExtensions() {
    return knownExtensions;
  }

  public Class<?>[] extensions() {
    return extensions;
  }


}
