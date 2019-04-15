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
package org.apache.uima.ruta.expression.string;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.fit.factory.TypeSystemDescriptionFactory;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.metadata.AllowedValue;
import org.apache.uima.resource.metadata.FsIndexDescription;
import org.apache.uima.resource.metadata.TypeDescription;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.resource.metadata.impl.AllowedValue_impl;
import org.apache.uima.resource.metadata.impl.TypeSystemDescription_impl;
import org.apache.uima.ruta.engine.Ruta;
import org.apache.uima.ruta.type.FalseNegative;
import org.apache.uima.ruta.type.FalsePositive;
import org.apache.uima.ruta.type.TruePositive;
import org.apache.uima.util.CasCreationUtils;
import org.junit.Assert;
import org.junit.Test;

public class StringSubtypeAllowedValuesTest {

  @Test
  public void testValidValue() throws Exception {
    CAS cas = createCAS();

    String script = "Document{-> CREATE(Test, \"allowed\" = \"A\")};";
    script += "Document{-> CREATE(Test, \"allowed\" = \"B\")};";
    // using eval types just as helper types
    script += "Test.allowed == \"A\"{-> TruePositive};";
    script += "Test.allowed == \"B\"{-> TruePositive};";
    script += "t:Test{t.allowed == \"A\" -> TruePositive};";
    script += "t:Test{t.allowed == \"B\" -> TruePositive};";
    script += "Test.allowed == \"\"{-> FalseNegative};";
    script += "Test.allowed == \"C\"{-> FalsePositive};";

    Ruta.apply(cas, script);

    JCas jcas = cas.getJCas();
    Assert.assertEquals(4, JCasUtil.select(jcas, TruePositive.class).size());
    Assert.assertEquals(0, JCasUtil.select(jcas, FalseNegative.class).size());
    Assert.assertEquals(0, JCasUtil.select(jcas, FalsePositive.class).size());

  }

  @Test(expected = AnalysisEngineProcessException.class)
  public void testInvalidValue() throws Exception {

    CAS cas = createCAS();

    String script = "Document{-> CREATE(Test, \"allowed\" = \"D\")};";

    Ruta.apply(cas, script);
  }

  private CAS createCAS() throws ResourceInitializationException {
    TypeSystemDescription typeSystemDescription = new TypeSystemDescription_impl();
    String subStringTypeName = "ruta.SubString";
    TypeDescription stringTypeDescription = typeSystemDescription.addType(subStringTypeName,
            "for testing", CAS.TYPE_NAME_STRING);
    stringTypeDescription.setAllowedValues(new AllowedValue[] { new AllowedValue_impl("A", "A"),
        new AllowedValue_impl("B", "B"), new AllowedValue_impl("C", "C") });
    TypeDescription testTypeDescription = typeSystemDescription.addType("ruta.Test", "for testing",
            CAS.TYPE_NAME_ANNOTATION);
    testTypeDescription.addFeature("allowed", "for testing", subStringTypeName);

    TypeSystemDescription typeSystemDescription2 = TypeSystemDescriptionFactory
            .createTypeSystemDescription();

    Collection<TypeSystemDescription> typeSystems = new ArrayList<>();
    typeSystems.add(typeSystemDescription);
    typeSystems.add(typeSystemDescription2);
    TypeSystemDescription mergedTypeSystem = CasCreationUtils.mergeTypeSystems(typeSystems);
    CAS cas = CasCreationUtils.createCas(mergedTypeSystem, null, new FsIndexDescription[0]);
    cas.setDocumentText("Some text.");
    return cas;
  }

}
