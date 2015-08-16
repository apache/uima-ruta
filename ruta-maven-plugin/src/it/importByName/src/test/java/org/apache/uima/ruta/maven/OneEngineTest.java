package org.apache.uima.ruta.maven;

import java.io.File;

import static org.junit.Assert.*;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.util.XMLInputSource;
import org.junit.Test;

public class OneEngineTest {
  
  @Test
  public void test() throws Exception{
    File descDirectory = new File("target/generated-sources/ruta/descriptor");
    File aeFile1 = new File(descDirectory, "my/package/one/OneEngine.xml");
    assertTrue(aeFile1.exists());
    File aeFile2 = new File(descDirectory, "my/package/two/TwoEngine.xml");
    assertTrue(aeFile2.exists());
    File aeFile3 = new File(descDirectory, "my/package/three/ThreeEngine.xml");
    assertTrue(aeFile3.exists());
    File tsFile1 = new File(descDirectory, "my/package/one/OneTypeSystem.xml");
    assertTrue(tsFile1.exists());
    File tsFile2 = new File(descDirectory, "my/package/two/TwoTypeSystem.xml");
    assertTrue(tsFile2.exists());
    File tsFile3 = new File(descDirectory, "my/package/three/ThreeTypeSystem.xml");
    assertTrue(tsFile3.exists());
    
    AnalysisEngineDescription aed = UIMAFramework.getXMLParser().parseAnalysisEngineDescription(new XMLInputSource(aeFile1));
    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(aed);
    CAS cas = ae.newCAS();
    cas.setDocumentText("This is a test.");
    ae.process(cas);
    
    Type type1 = cas.getTypeSystem().getType("my.package.one.One.Type1");
    AnnotationIndex<AnnotationFS> ai1 = cas.getAnnotationIndex(type1);
    assertEquals(1, ai1.size());
    assertEquals("This", ai1.iterator().next().getCoveredText());
    
    Type type2 = cas.getTypeSystem().getType("my.package.two.Two.Type2");
    AnnotationIndex<AnnotationFS> ai2 = cas.getAnnotationIndex(type2);
    assertEquals(1, ai2.size());
    assertEquals("This", ai2.iterator().next().getCoveredText());
    
    Type type3 = cas.getTypeSystem().getType("my.package.three.Three.Type3");
    AnnotationIndex<AnnotationFS> ai3 = cas.getAnnotationIndex(type3);
    assertEquals(1, ai3.size());
    assertEquals("This", ai3.iterator().next().getCoveredText());
    
    cas.release();
  }
  
}
