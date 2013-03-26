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

package org.apache.uima.textmarker.engine;

import static org.junit.Assert.assertEquals;

import java.net.URL;

import org.apache.uima.UIMAFramework;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.resource.metadata.FsIndexDescription;
import org.apache.uima.resource.metadata.TypePriorities;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.textmarker.TextMarkerTestUtils;
import org.apache.uima.util.CasCreationUtils;
import org.apache.uima.util.XMLInputSource;
import org.junit.Test;

public class TextMarkerTest {

  @Test
  public void test() throws Exception {
    URL typePrioritiesUrl = TextMarkerTestUtils.class.getResource("TypePriorities.xml");
    URL tsUrl = TextMarkerTestUtils.class.getResource("BasicTypeSystem.xml");
    Object descriptor = UIMAFramework.getXMLParser().parse(new XMLInputSource(tsUrl));
    TypeSystemDescription tsDesc = (TypeSystemDescription) descriptor;
    tsDesc.addType("uima.textmarker.T1", "", "uima.tcas.Annotation");
    tsDesc.addType("uima.textmarker.T2", "", "uima.tcas.Annotation");
    tsDesc.addType("uima.textmarker.T3", "", "uima.tcas.Annotation");
    tsDesc.resolveImports();
    TypePriorities typePriorities = UIMAFramework.getXMLParser().parseTypePriorities(new XMLInputSource(typePrioritiesUrl));
    CAS cas = CasCreationUtils.createCas(tsDesc, typePriorities, new FsIndexDescription[0]);
    
    cas.setDocumentText("Some document.");
    
    TextMarker.apply(cas, "CW{-> MARK(T1)} SW;");
    AnnotationIndex<AnnotationFS> ai = null;
    FSIterator<AnnotationFS> iterator = null;
    ai = cas.getAnnotationIndex(cas.getTypeSystem().getType("uima.textmarker.T1"));
    iterator = ai.iterator();
    assertEquals(1, ai.size());
    assertEquals("Some", iterator.next().getCoveredText());
    
    TextMarker.apply(cas, "T1 SW{-> MARK(T2)};");
    ai = cas.getAnnotationIndex(cas.getTypeSystem().getType("uima.textmarker.T2"));
    iterator = ai.iterator();
    assertEquals(1, ai.size());
    assertEquals("document", iterator.next().getCoveredText());
    
    TextMarker.apply(cas, "T1{-> MARK(T3,1,2,3)} T2 PERIOD;");
    ai = cas.getAnnotationIndex(cas.getTypeSystem().getType("uima.textmarker.T3"));
    iterator = ai.iterator();
    assertEquals(1, ai.size());
    assertEquals("Some document.", iterator.next().getCoveredText());
    
    
    cas.release();
  }
}
