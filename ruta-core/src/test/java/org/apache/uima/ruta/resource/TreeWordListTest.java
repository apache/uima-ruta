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
package org.apache.uima.ruta.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.jcas.JCas;
import org.apache.uima.ruta.FilterManager;
import org.apache.uima.ruta.ReindexUpdateMode;
import org.apache.uima.ruta.RutaIndexingConfiguration;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.engine.RutaTestUtils;
import org.apache.uima.ruta.seed.TextSeeder;
import org.apache.uima.ruta.type.RutaBasic;
import org.apache.uima.ruta.visitor.InferenceCrowd;
import org.junit.Assert;
import org.junit.Test;

public class TreeWordListTest {

  @Test
  public void testWithAction() throws Exception {

    String text = "ab";
    String script = "STRINGLIST list = {\"ab\", \"a c\", \"a d\"};";
    script += "MARKFAST(T1, list);";

    CAS cas = RutaTestUtils.getCAS(text);
    Ruta.apply(cas, script);

    RutaTestUtils.assertAnnotationsEquals(cas, 1, 1, text);
  }

  @Test
  public void testFind() throws Exception {

    String text = "ab";
    List<String> data = Arrays.asList(text, "a c", "a d");
    TreeWordList twl = new TreeWordList(data, false);

    JCas jcas = JCasFactory.createJCas();
    jcas.setDocumentText(text);
    CAS cas = jcas.getCas();
    RutaStream stream = createStream(text, cas);

    List<AnnotationFS> result1 = twl.find(stream, false, 0, null, 0, false);
    Assert.assertEquals(1, result1.size());
    Assert.assertEquals(text, result1.get(0).getCoveredText());

    List<AnnotationFS> result2 = twl.find(stream, false, 0, null, 0, true);
    Assert.assertEquals(1, result2.size());
    Assert.assertEquals(text, result2.get(0).getCoveredText());

    List<AnnotationFS> result3 = twl.find(stream, true, 0, null, 0, false);
    Assert.assertEquals(1, result3.size());
    Assert.assertEquals(text, result3.get(0).getCoveredText());
  }

  private RutaStream createStream(String text, CAS cas) {
    Type basicType = cas.getTypeSystem().getType(RutaBasic.class.getName());

    Collection<Type> filterTypes = getDefaultFilterTypes(cas);

    FilterManager filter = new FilterManager(filterTypes, true, cas);
    TextSeeder seeder = new TextSeeder();
    seeder.seed(text, cas);
    InferenceCrowd crowd = new InferenceCrowd(new ArrayList<>());
    RutaStream stream = new RutaStream(cas, basicType, filter, false, false, true, null, crowd);

    RutaIndexingConfiguration config = new RutaIndexingConfiguration();
    config.setIndexOnly(new String[] { CAS.TYPE_NAME_ANNOTATION });
    config.setReindexOnly(new String[] { CAS.TYPE_NAME_ANNOTATION });
    config.setReindexUpdateMode(ReindexUpdateMode.ADDITIVE);
    stream.initalizeBasics(config);
    return stream;
  }

  private Collection<Type> getDefaultFilterTypes(CAS cas) {
    Collection<Type> filterTypes = new ArrayList<Type>();
    TypeSystem typeSystem = cas.getTypeSystem();
    String[] defaultFilteredTypes = new String[] { "org.apache.uima.ruta.type.SPACE",
        "org.apache.uima.ruta.type.BREAK", "org.apache.uima.ruta.type.MARKUP" };
    for (String each : defaultFilteredTypes) {
      Type type = typeSystem.getType(each);
      if (type != null) {
        filterTypes.add(type);
      }
    }
    return filterTypes;
  }

}